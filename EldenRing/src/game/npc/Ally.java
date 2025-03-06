package game.npc;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DespawnActorAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import game.classes.Classes;
import game.enemies.CannotAttack;
import game.enemies.Enemy;
import game.utils.ClassesMenu;
import game.utils.ResetManager;
import game.utils.Resettable;
import game.utils.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * An ally class that extends Actor class and implements Npc
 * @author Ying Mah
 * @version 1.0
 * @see Actor
 */
public class Ally extends Actor implements Npc,Resettable{
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * A Constructor for ally with attributes given below.
     *
     */
    public Ally() {
        super("Ally", 'A', 0);
        addCapability(CannotAttack.ALLY);
        this.behaviours.put(3,new AttackBehaviour());
        this.behaviours.put(999, new WanderBehaviour());
        generateClasses();
        // reset function
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * getter method for behaviours that can be performed by ally at it's turn
     * @return Hash map <integer,behaviours> that can be performed
     */
    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }

    /**
     * Playturn function to automatically manage Ally's behaviour/ action
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return action to be done by ally this turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (hasCapability(Status.REMOVED)){
            return new DespawnActorAction();
        }
        for (Behaviour behaviour : getBehaviours().values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * A method to generate class and assign attributes for current ally
     */
    public void generateClasses(){
        Classes classes = ClassesMenu.randomClass();
        resetMaxHp(classes.getHitPoint());
        addWeaponToInventory(classes.getWeapon());
    }

    /**
     * A reset function to be run when player dies
     */
    public void reset(){
        if (ResetManager.getInstance().getPlayer().hasCapability(Status.PLAYER_DIED)){
            addCapability(Status.REMOVED);
        }
    }
}

package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DespawnActorAction;
import game.behaviours.DespawnBehaviour;
import game.utils.ResetManager;
import game.utils.Resettable;
import game.utils.Status;
import game.actions.TransformAction;

/**
 * An enemy that extends Undead enemy type that will not move until it survives three rounds without being hit!
 * @author Kai Aw
 * @version 1.0
 * @see Undead
 */
public class PilesOfBone extends Undead implements Resettable {
    private int round = 0;
    private Undead undead;

    /**
     * Constructor of PilesOfBone
     */
    public PilesOfBone(Undead undead) {
        super("Piles of Bone", 'X', 1,0,undead.getMinDropRunes(), undead.getMaxDropRunes());
        getBehaviours().clear();    // reset it's behaviour
        this.undead = undead;       // defined as an undead type enemy
        ResetManager.getInstance().registerResettable(this); // reset
        addWeaponToInventory(undead.getWeaponInventory().get(0));
    }

    /**
     * playTurn will check it's behaviours to decide which to perform
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action that will be performed by PilesOfBone
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (hasCapability(Status.REMOVED)){
            return new DespawnActorAction();
        }
        Action action = new DespawnBehaviour().getAction(this,map);
        if(action != null)
            return action;
        round += 1;
        if (round >3){
            undead.heal(undead.getMAX_HITPOINT());
            return new TransformAction(undead);     // transform to movable skeleton with full hitpoints
        }
        return new DoNothingAction();
    }

    /**
     * isConscious will identify and decide if Undead enemy is still alive or dead
     * @return boolean value that tells if PilesOfBone is still alive or dead
     */
    public boolean isConscious() {
        return hitPoints>0;
    }



}

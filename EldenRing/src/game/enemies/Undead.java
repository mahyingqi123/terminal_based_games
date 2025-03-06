package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DespawnActorAction;
import game.actions.TransformAction;
import game.behaviours.Behaviour;
import game.utils.Status;

/**
 * Undead abstract class that extends from Enemy abstract class
 * It is used to differentiate the enemy type specifically
 * @author Kai Aw
 * @version 1.0
 * @see Enemy
 */
public abstract class Undead extends Enemy{
    private final int MAX_HITPOINT;

    /**
     * Undead constructor that will create fish object with specific name, displayChar, hitpoints
     * spawnRate, minDropRunes, and maxDropRunes.
     * @param name String value of Undead enemy name
     * @param displayChar character that represents on the map gameplay
     * @param hitPoints integer value of enemy's health
     * @param spawnRate integer value of spawn rate on map
     * @param minDropRunes integer value of minimum runes drop
     * @param maxDropRunes integer value of maximum runes drop
     */
    public Undead(String name, char displayChar, int hitPoints, int spawnRate, int minDropRunes, int maxDropRunes) {
        super(name, displayChar, hitPoints, CannotAttack.UNDEAD,spawnRate,minDropRunes,maxDropRunes);
        this.MAX_HITPOINT = hitPoints;
    }

    /**
     * playTurn will check it's status to decide transformation into PilesOfBone or carry out certain behaviours
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return action    specific action that will be carried out
     */
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (hasCapability(Status.KILLED)){
            return new TransformAction(new PilesOfBone(this));
        }
        return super.playTurn(actions,lastAction,map,display);
    }

    @Override
    /**
     * isConscious will identify and decide if Undead enemy is still alive or dead
     * @return boolean value that tells Undead status
     */
    public boolean isConscious() {
        if(!super.isConscious()){
            addCapability(Status.KILLED);
        }
        return true;
    }

    /**
     * getter method to retrieve max hitpoints of undead enemy
     * @return integer value of max hitpoints
     */
    public int getMAX_HITPOINT() {
        return MAX_HITPOINT;
    }

}

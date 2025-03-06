package game.trader;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.utils.Status;

/**
 * Trader class that extends Actor to achieve DRY principle as it reduces codes of repetition
 * It is a special NPC that will appear in safe place to allow trade (buy and sell) with player!
 * @author Jun Ng
 * @version 1.0
 * @see Actor
 */
public abstract class Trader extends Actor {

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     */
    public Trader(String name, char displayChar) {
        super(name, displayChar, 0);
        addCapability(Status.CAN_TRADE);
    }

    /**
     * playTurn method that allow player to carry out interaction (default is no action)
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return doNothingAction by default
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }



}

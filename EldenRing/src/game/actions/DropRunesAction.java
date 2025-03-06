package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Runes;
import game.utils.ResetManager;
import game.utils.RunesManager;
import game.utils.Status;

/**
 * DropRunesAction class that extends Action class as it can be executed as an action that will perfrom runes
 * dropping accordingly to the type of actor
 * reaches Single Responsibility Principle as it handles runes dropping action in game
 * @author Ying Mah
 * @version 1.0
 * @see Action
 */
public class DropRunesAction extends Action {
    private Actor receiver;

    /**
     * Empty constructor for DropRunesAction
     */
    public DropRunesAction(){
    }

    /**
     * Constructor that define the runes receiver
     * @param receiver actor that will be receiving the dropped runes
     */
    public DropRunesAction(Actor receiver){
        this.receiver = receiver;
    }

    /**
     * Drops the runes that the actor has, if receiver is a player, transfer it to the receiver
     * if the dropper is a player, drops it on the ground
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string showing how many runes is dropped
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        RunesManager runesManager = RunesManager.getInstance();
        int droppedRunesValue = runesManager.getRunes(actor).getValue();
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            Runes droppedRunes = new Runes(droppedRunesValue);
            droppedRunes.addCapability(Status.DROPPED);
            ResetManager.getInstance().getPlayer().getLastLocation().addItem(droppedRunes);
            ResetManager.getInstance().registerResettable(droppedRunes);
            runesManager.getRunes(actor).setRunes(0);
        }else{
            runesManager.addRunes(receiver,droppedRunesValue);
            runesManager.removeRunes(actor);
        }
        return actor +" dropped "+droppedRunesValue+" runes";
    }

    /**
     * to override super class abstract method
     * @param actor The actor performing the action.
     * @return null
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}

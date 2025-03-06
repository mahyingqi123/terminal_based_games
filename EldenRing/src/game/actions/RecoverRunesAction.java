package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Runes;
import game.utils.RunesManager;

/**
 * Consume class that extends Action class as it can be executed as an action that is done by Player
 * It allows user consume items that is consumable, this helps to reduce repeated code (DRY) and it also
 * reaches Single Responsibility Principle as it handles consume action in game
 * @author Ying Mah
 * @version 1.0
 * @see Action
 */
public class RecoverRunesAction extends Action {
    private Runes runes;

    /**
     * Constructor of RecoverRunesAction
     * @param runes
     */
    public RecoverRunesAction(Runes runes){
        this.runes = runes;
    }

    /**
     * execute method that is overriden that is called to ensure Player has feature to recover runes from ground
     * Thus, this helps Player to regain it's runes from previous death only
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String output of action done (recover the runes with amount stated specifically)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        RunesManager.getInstance().getRunes(actor).addRunes(runes.getValue());
        map.locationOf(actor).removeItem(runes);
        return menuDescription(actor);
    }

    /**
     * String output method that print out the action of recover runes
     * @param actor The actor performing the action.
     * @return String output
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor+" recovers "+runes.getValue()+" runes";
    }
}

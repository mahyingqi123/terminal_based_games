package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;

/**
 * Consume class that extends Action class as it can be executed as an action that is done by Player
 * It allows user consume items that is consumable, this helps to reduce repeated code (DRY) and it also
 * reaches Single Responsibility Principle as it handles consume action in game
 * @author Ying Mah
 * @version 1.0
 * @see Action
 */
public class ConsumeAction extends Action {
    private Consumable consumeItem;

    /**
     * Set item to be consumable so Player can consume items in its inventory
     * @param consumeItem item that is consumable
     */
    public ConsumeAction(Consumable consumeItem){
        this.consumeItem=consumeItem;
    }

    /**
     * execute method that is overriden that is called to ensure Player has feature to consume items
     * Thus, this helps Player to survive longer in wild after fighting with enemies
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String output that specify consume action that is done by player
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        consumeItem.consume(actor);
        return menuDescription(actor);
    }

    /**
     * menuDescription method (toString) that print out action done
     * @param actor The actor performing the action.
     * @return String output of consume action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor +" consume "+consumeItem+" ("+consumeItem.getUses()+"/"+consumeItem.getMaximumUses()+")";
    }
}

package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Exchangeable;
import game.weapons.Returnable;

/**
 * ExchangeAction class that extends Action class as it can be executed as an action that is done by Player.
 * It allows player to exchange item for other item from Finger Reader Enia, this helps to reduce repeated code (DRY) and it also
 * reaches Single Responsibility Principle as it handles exchange action in game.
 * @author Jun Ng
 * @version 2.0
 * @see Action
 */

public class ExchangeAction extends Action {

    private Exchangeable exchangeItem;
    private Returnable returnItem;


    /**
     * Set item to be exchangeable so Player can exchange with Enia
     * Set item to returnable so that Player can know whats the return item.
     * @param exchangeItem item object.
     * @param returnItem item object.
     */
    public ExchangeAction(Exchangeable exchangeItem, Returnable returnItem){
        this.exchangeItem = exchangeItem;
        this.returnItem = returnItem;
    }

    /**
     * execute method that is override that is called to remove exchange item from actor inventory
     * thus, add return item to inventory of actor.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String output that tells the action done by Actor.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        exchangeItem.removeFromActorInventory(actor);
        returnItem.addToActorInventory(actor);
        return menuDescription(actor);
    }

    /**
     * menuDescription method (toString) that print out action done.
     * @param actor The actor performing the action.
     * @return String output of exchange action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " exchanges " + exchangeItem + " for " + returnItem;
    }
}

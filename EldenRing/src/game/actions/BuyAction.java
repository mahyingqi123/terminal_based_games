package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.utils.RunesManager;
import game.weapons.Buyable;

/**
 * BuyAction class that extends Action class as it can be executed as an action that is done by Player
 * It allows user to buy items from Merchant, this helps to reduce repeated code (DRY) and it also
 * reaches Single Responsibility Principle as it handles buy action in game
 * @author Jun Ng
 * @version 1.0
 * @see Action
 * @modified Ying Mah, Jun Ng
 */
public class BuyAction extends Action {
    private Buyable buyItem;
    private int price;

    /**
     * Set item to be purchasable so Player can purchase from Merchant
     * Set price to buy price of the item so player know how much the item cost
     * @param buyItem item object
     */
    public BuyAction(Buyable  buyItem){
        this.buyItem = buyItem;
        this.price = buyItem.getBuyPrice();
    }

    /**
     * execute method that is overriden that is called to ensure Player has sufficient runes to buy item
     * Thus, this helps Player to not have any negative debt which is not allowed in game
     * Finally, it will add the item into the inventory of actor (Player)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String output that tells the action done by Actor
     */
    @Override
    public String execute(Actor actor, GameMap map){
        RunesManager runesManager = RunesManager.getInstance();
        if (runesManager.getRunes(actor).getValue() >= price) {
            runesManager.deductedRunes(actor, price);
            buyItem.addToActorInventory(actor);
            return menuDescription(actor);
        }
        else{
            return actor + " has no enough runes";
        }
    }

    /**
     * menuDescription method (toString) that print out action done
     * @param actor The actor performing the action.
     * @return String output of buy action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buy " + buyItem + " for " + price;
    }
}

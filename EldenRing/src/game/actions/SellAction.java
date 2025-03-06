package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.utils.RunesManager;
import game.weapons.Sellable;

/**
 * SellAction class that extends Action class as it can be executed as an action that is done by Player
 * It allows user to sell items to Merchant, this helps to reduce repeated code (DRY) and it also
 * reaches Single Responsibility Principle as it handles sell action in game
 * @author Jun Ng
 * @version 1.0
 * @see Action
 * @modified Ying Mah
 */
public class SellAction extends Action {
    private Sellable sellItem;
    private int price;

    /**
     * Set item to be sellable so Player can sell to Merchant
     * Set price to sell price of the item so player know how much the item cost
     * @param sellItem
     */
    public SellAction(Sellable sellItem){
        this.sellItem = sellItem;
        this.price = sellItem.getSellPrice();
    }

    /**
     * execute method that is overriden that is called to ensure player can sell this item
     * Finally, it will remove the item from the inventory of actor (Player)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String output of sell action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        RunesManager runesManager = RunesManager.getInstance();
        runesManager.addRunes(actor,price);
        sellItem.removeFromActorWeaponInventory(actor);
        return menuDescription(actor);
    }

    /**
     * String output of sell action
     * @param actor The actor performing the action.
     * @return String value of action carried out by selling item
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells " + sellItem + " for " + price;
    }
}

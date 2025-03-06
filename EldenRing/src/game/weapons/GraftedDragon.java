package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.SellAction;
import game.utils.Status;
import java.util.ArrayList;
import java.util.List;

/**
 * A weapon that only can get by exchanging RemembranceOfTheGrafted
 * It's damage is 89 with hit rate of 90
 * It is sellable item and is an returnable item
 * @author Jun Ng
 * @version 1.0
 * @see WeaponItem
 */
public class GraftedDragon extends WeaponItem implements Sellable,Returnable{

    private int sellPrice = 200;

    /**
     * Constructor of GraftedDragon with fixed attributes
     */
    public GraftedDragon(){
        super("Grafted Dragon",'N',89,"hit",90);
    }

    /**
     * getter of sell price
     * @return integer value (runes) - 200
     */
    @Override
    public int getSellPrice() {
        return sellPrice;
    }

    /**
     * remove this item from inventory of actor (player)
     * @param actor whom inventory will be removed of 1 copy of this item
     */
    @Override
    public void removeFromActorWeaponInventory(Actor actor) {
        actor.removeWeaponFromInventory(this);
    }

    /**
     * return list of action that can be performed
     * specifically this item can be sold by actor (player)
     * @return action list
     */
    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        if (hasCapability(Status.CAN_SELL)) {
            actions.add(new SellAction(this));
        }
        return actions;
    }

    /**
     * tick method to add action of sellable if the actor is nearby to the merchant
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        removeCapability(Status.CAN_SELL);                              // always remove sellable option first
        for (Exit exit: currentLocation.getExits()) {
            if (exit.getDestination().containsAnActor()) {
                Actor otherActor = exit.getDestination().getActor();
                if (otherActor.hasCapability(Status.CAN_TRADE)) {       // if nearby actor is Merchant, can trade
                    this.addCapability(Status.CAN_SELL);                // thus, add back sell action (nearby to merchant)
                    break;
                }
            }
        }
    }

    /**
     * able to add item to player's inventory
     * @param actor that will have the item added into their inventory
     */
    @Override
    public void addToActorInventory(Actor actor) {
        actor.addWeaponToInventory(this);
    }
}

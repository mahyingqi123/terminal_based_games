package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ExchangeAction;
import game.actions.SellAction;
import game.utils.Status;
import game.weapons.AxeOfGodrick;
import game.weapons.GraftedDragon;
import game.weapons.Returnable;
import game.weapons.Sellable;
import java.util.ArrayList;
import java.util.List;

/**
 * An Item that can use to exchange to AxeOfGodrick and GraftedDragon or sell for 20000
 * @author Jun Ng
 * @version 2.0
 * @see Item
 */
public class RemembranceOfTheGrafted extends Item implements Sellable,Exchangeable {

    private int sellPrice = 20000;
    private List<Returnable> returnItemList;

    /***
     * Constructor of RemembranceOfTheGrafted
     * add AxeOfGodrick and GraftedDragon to returnItemList
     */
    public RemembranceOfTheGrafted() {
        super("Remembrance of the Grafted",'O',true);
        returnItemList = new ArrayList<>();
        returnItemList.add(new AxeOfGodrick());
        returnItemList.add(new GraftedDragon());
    }

    /**
     * getter of sell price
     * @return integer value (runes) - 20000
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
        actor.removeItemFromInventory(this);
    }

    /**
     * return list of action that can be performed
     * specifically this item can be sold by actor (player) and exchange by actor(player)
     * @return action list
     */
    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        if (hasCapability(Status.CAN_SELL)) {
            actions.add(new SellAction(this));
        }
        if (hasCapability(Status.CAN_EXCHANGE)){
            for(Returnable item:returnItemList) {
                actions.add(new ExchangeAction(this,item));
            }
        }
        return actions;
    }

    /**
     * tick method to add action of sellable and exchangeable if the actor is nearby to the merchant
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        removeCapability(Status.CAN_SELL);                              // always remove sellable option first
        removeCapability(Status.CAN_EXCHANGE);
        for (Exit exit: currentLocation.getExits()) {
            if (exit.getDestination().containsAnActor()) {
                Actor otherActor = exit.getDestination().getActor();
                if (otherActor.hasCapability(Status.CAN_TRADE)) {       // if nearby actor is Merchant, can trade
                    this.addCapability(Status.CAN_SELL);                // thus, add back sell action (nearby to merchant)
                }
                if(otherActor.hasCapability(Status.CAN_EXCHANGE)){
                    this.addCapability(Status.CAN_EXCHANGE);
                }
                break;
            }
        }
    }

    /**
     * remove this item from inventory of actor (player)
     * @param actor whom inventory will be removed of 1 copy of this item
     */
    @Override
    public void removeFromActorInventory(Actor actor) {
        actor.removeItemFromInventory(this);
    }

}

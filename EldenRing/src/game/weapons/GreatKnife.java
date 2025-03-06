package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.SellAction;
import game.specialSkill.QuickStep;
import game.utils.Status;
import java.util.ArrayList;
import java.util.List;

/**
 * A weapon that can be used by Bandit since the start of game as picking Bandit
 * It's damage is 75 with hit rate of 70
 * It has special skill known as Quick Step and it is sellable item
 * @author Ying Mah
 * @version 1.0
 * @see WeaponItem
 * @modified Jun Ng
 */
public class GreatKnife extends WeaponItem implements Buyable,Sellable {

    private int buyPrice = 3500;
    private int sellPrice = 350;
    /**
     * Constructor of GreatKnife with fixed attributes
     */
    public GreatKnife() {
        super("Great Knife", '/', 75, "hit", 70);
        addCapability(Status.HAS_SPECIAL_SKILL);
    }

    /**
     * getSKill will perform it's QuickStep ability
     * @param target target actor
     * @param direction to apply this special skill
     * @return QuickStep
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new QuickStep(target,direction,this);
    }

    /**
     * getter of buy price
     * @return integer value (runes) - 3500
     */
    @Override
    public int getBuyPrice() {
        return buyPrice;
    }

    /**
     * getter of sell price
     * @return integer value (runes) - 350
     */
    @Override
    public int getSellPrice() {
        return sellPrice;
    }

    /**
     * able to add item to player's inventory
     * @param actor that will have the item added into their inventory
     */
    @Override
    public void addToActorInventory(Actor actor) {
        actor.addWeaponToInventory(this);           // add into inventory
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

}

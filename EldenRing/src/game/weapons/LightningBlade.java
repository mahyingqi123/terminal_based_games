package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.SellAction;
import game.specialSkill.ElectricShock;
import game.utils.Status;
import java.util.ArrayList;
import java.util.List;

/**
 * A Lightning Blade weapon that can only be used by Player, it extends from WeaponItem to achieve DRY principle
 * @author Kai Aw
 * @version 1.0
 * @see WeaponItem
 */
public class LightningBlade extends WeaponItem implements Buyable,Sellable {

    private int sellPrice = 1000;
    private int buyPrice = 10000;
    /**
     * Constructor of Lightning Blade
     */
    public LightningBlade() {
        super("Lightning Blade", '/', 60, "slash", 90);
        addCapability(Status.HAS_SPECIAL_SKILL);
    }

    /**
     * getter of sell price
     * @return integer value (runes)
     */
    @Override
    public int getSellPrice() {
        return sellPrice;
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
     * remove this item from inventory of actor (player)
     * @param actor whom inventory will be removed of 1 copy of this item
     */
    @Override
    public void removeFromActorWeaponInventory(Actor actor) {
        actor.removeWeaponFromInventory(this);
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
     * getSKill will perform it's Spinning Attack ability
     * @param target target actor
     * @param direction to apply this special skill
     * @return Spinning Attack
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new ElectricShock(target, direction, this);
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
        removeCapability(Status.CAN_SELL);
        for (Exit exit: currentLocation.getExits()) {
            if (exit.getDestination().containsAnActor()) {
                Actor otherActor = exit.getDestination().getActor();
                if (otherActor.hasCapability(Status.CAN_TRADE)) {
                    this.addCapability(Status.CAN_SELL);
                    break;
                }
            }
        }
    }

}

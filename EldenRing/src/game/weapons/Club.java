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
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Ying Mah, Jun Ng
 *
 */
public class Club extends WeaponItem implements Buyable,Sellable{

    private int buyPrice = 600;
    private int sellPrice = 100;
    /**
     * Constructor
     */
    public Club() {
        super("Club", '!', 103, "bonks", 80);
    }

    @Override
    public int getBuyPrice() {
        return buyPrice;
    }

    @Override
    public void addToActorInventory(Actor actor) {
        actor.addWeaponToInventory(this);
    }

    @Override
    public int getSellPrice() {
        return sellPrice;
    }

    @Override
    public void removeFromActorWeaponInventory(Actor actor) {
        actor.removeWeaponFromInventory(this);
    }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        if (hasCapability(Status.CAN_SELL)) {
            actions.add(new SellAction(this));
        }
        return actions;
    }

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

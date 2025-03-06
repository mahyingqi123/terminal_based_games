package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RecoverRunesAction;
import game.utils.ResetManager;
import game.utils.Resettable;
import game.utils.RunesManager;
import game.utils.Status;
import java.util.ArrayList;
import java.util.List;

/**
 * Runes class that extends item class and implements resettable interface
 * An item that acts as money in game so player can use it to purchase items
 * @author Jun Ng
 * @version 1.0
 * @see Item
 * @Modified by Ying Mah
 */
public class Runes extends Item implements Resettable {
    private Location location;
    private int value;

    /**
     * Constructor of runes
     * @param value of runes
     */
    public Runes(int value) {
        super("Runes", '$', false);
        this.value = value;
    }

    /**
     * reset function to ensure it can be recovered by player
     * or ensure it will be gone after player's consecutive death
     */
    @Override
    public void reset() {
        if(ResetManager.getInstance().getPlayer().hasCapability(Status.PLAYER_DIED)){
            addCapability(Status.REMOVED);
        }
    }

    /**
     * tick function that find this item's location to remove it from map
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (hasCapability(Status.REMOVED)){
            currentLocation.removeItem(this);
        }
    }

    /**
     * getter method of value
     * @return integer value of runes
     */
    public int getValue() {
        return value;
    }

    /**
     * setter method of value
     * @param value of runes
     */
    public void setRunes(int value) {
        this.value = value;
    }

    /**
     * increment runes depending on input
     * @param value of runes to be added to player
     */
    public void addRunes(int value){
        this.value += value;
    }

    /**
     * decrement runes depending on input
     * @param value of runes to be deducted from player
     *
     */
    public void deductedRunes(int value){
        this.value -= value;
    }

    /**
     * Add available action to list of action (if player standing on it, he can recover the rune item)
     * @return action list
     */
    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        if (hasCapability(Status.DROPPED)){
            actions.add(new RecoverRunesAction(this));
        }
        return actions;
    }

}

package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;
import game.utils.ResetManager;
import game.utils.Resettable;
import java.util.*;

/**
 * FlaskOfCrimsonTears class that extends item class and implements consumable and resettable interface
 * An item that can be consumed by player to regain partial health so they can survive longer in wild
 * @author Ying Mah
 * @version 1.0
 * @see Item
 */
public class FlaskOfCrimsonTears extends Item implements Consumable, Resettable {
    private final static int maximumUses = 2;
    private int uses = maximumUses;

    /***
     * Constructor of FlaskOfCrimsonTears
     */
    public FlaskOfCrimsonTears(){
        super("Flask of Crimson Tears", ' ', false);
        addAction(new ConsumeAction(this));                         // has consume action
        ResetManager.getInstance().registerResettable(this);                   // reset to default amount of use
    }

    /**
     * consume method that will add 250 hitpoint to player's current hitpoints (cap at player's max hitpoint)
     * @param actor actor who consume this item
     */
    public void consume(Actor actor){
        actor.heal(250);          // add health
        uses--;                         // decrement by 1 time usage
    }

    /**
     * Add available action to list of action (if still has consumable amount)
     * @return action list
     */
    @Override
    public List<Action> getAllowableActions() {
        ArrayList<Action> actions = new ArrayList<>();
        if(uses>0) {
            actions.add(new ConsumeAction(this));
        }
        return actions;
    }

    /**
     * reset amount of usage back to default
     */
    @Override
    public void reset() {
        uses = maximumUses;
    }

    /**
     * return available use amount left
     * @return integer value of use
     */
    public int getUses() {
        return uses;
    }

    /**
     * return maximum amount of consumable use
     * @return integer value of maximum use
     */
    public int getMaximumUses() {
        return maximumUses;
    }
}

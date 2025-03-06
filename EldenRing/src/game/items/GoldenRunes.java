package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.utils.RandomNumberGenerator;
import game.utils.RunesManager;

/**
 *GoldenRunes class that extends item class and implements consumable interface.
 * An Item that can be consumed by player to gain any amount of runes ranging from 200-10000 runes.
 * @author Jun Ng
 * @version 2.0
 * @see Item
 */
public class GoldenRunes extends Item implements Consumable {
    ConsumeAction consumeAction = new ConsumeAction(this);
    private final static int maximumUses = 1;
    private int uses = maximumUses;

    /**
     * Constructor of GoldenRunes
     */
    public GoldenRunes() {
        super("Golden Runes", '*', true);
    }

    /**
     * tick method been override to add consume action when item is in actor inventory
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if(actor.getItemInventory().contains(this)){
            addAction(consumeAction);
        }
    }

    /**
     * getDropAction method been override to remove consumeAction on the item before dropping the item
     * @param actor actor that drop the item
     * @return DropAction
     */
    @Override
    public DropAction getDropAction(Actor actor) {
        removeAction(consumeAction);
        return super.getDropAction(actor);
    }

    /**
     * consume method that will add Runes ranging from 200-10000 runes to actor current runes
     * @param actor actor that consume item
     */
    @Override
    public void consume(Actor actor) {
        RunesManager.getInstance().addRunes(actor, RandomNumberGenerator.getRandomInt(200,10000));
        uses--;
        actor.removeItemFromInventory(this);
    }

    /**
     * return available use amount left
     * @return integer value of use
     */
    @Override
    public int getUses() {
        return uses;
    }

    /**
     * return maximum amount of consumable use
     * @return integer value of maximum use
     */
    @Override
    public int getMaximumUses() {
        return maximumUses;
    }
}

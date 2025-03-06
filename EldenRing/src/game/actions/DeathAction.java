package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.Runes;
import game.utils.*;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Ying Mah, Kai Aw
 *
 */
public class DeathAction extends Action {
    private Actor attacker;

    /**
     * Constructor that define the attribute, attacker (actor)
     * @param actor Actor that involves in attacking
     */
    public DeathAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map    The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {

        String result = System.lineSeparator()+target+ " is killed";

        // drop all items of Actor
        if (!target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            if (attacker.hasCapability(Status.HOSTILE_TO_ENEMY) && target.hasCapability(Status.HOSTILE_TO_PLAYER)) {
                result += new DropRunesAction(attacker).execute(target,map);
            }
            // loop through items, weapons, and perform dropActions to remove every item from Actor
            ActionList dropActions = new ActionList();
            for (Item item : target.getItemInventory())
                dropActions.add(item.getDropAction(target));
            for (WeaponItem weapon : target.getWeaponInventory())
                dropActions.add(weapon.getDropAction(target));
            for (Action drop : dropActions)
                drop.execute(target, map);
            map.removeActor(target);
        }else{
            result += System.lineSeparator() +FancyMessage.YOU_DIED;                          // string output
            target.addCapability(Status.PLAYER_DIED);                                         // add status to player
            ResetManager resetManager = ResetManager.getInstance();
            resetManager.run();                                                 // reset
            result += new DropRunesAction().execute(target,map);
            map.removeActor(target);
            map.addActor(target, resetManager.getReviveLocation());         // spawn player at desired prefixed location
        }

        return result;
    }

    /**
     * menuDescription method (toString) that print out action done
     * @param actor The actor performing the action.
     * @return String output of death action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed.";
    }
}

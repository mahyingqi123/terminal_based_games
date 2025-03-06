package game.items;

import edu.monash.fit2099.engine.actors.Actor;


/**
 * Exchangeable interface that can be implemented and enforced classes to have all the method below
 * Thus, the item can be exchange and removed from player's inventory
 * @author Jun Ng
 * @version 2.0
 */
public interface Exchangeable {
    public void removeFromActorInventory(Actor actor);
}

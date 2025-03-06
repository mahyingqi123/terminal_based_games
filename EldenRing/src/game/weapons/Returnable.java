package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;


/**
 * Returnable interface that can be implemented and enforced classes to have all the method below
 * Thus, the item can be return and added to player's inventory
 * @author Jun Ng
 * @version 2.0
 */
public interface Returnable {
    public void addToActorInventory(Actor actor);
}

package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Buyable interface that can be implemented and enforced classes to have all the method below
 * Thus, the item can be purchased and added into player's inventory
 * @author Ying Mah
 * @version 1.0
 */
public interface Buyable{
    public int getBuyPrice();
    public void addToActorInventory(Actor actor);
}

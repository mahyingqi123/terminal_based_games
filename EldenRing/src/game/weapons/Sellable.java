package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * Sellable interface that can be implemented and enforced classes to have all the method below
 * Thus, the item can be sold and removed from player's inventory
 * @author Ying Mah
 * @version 1.0
 * @see Weapon
 */
public interface Sellable{
    public int getSellPrice();
    public void removeFromActorWeaponInventory(Actor actor);
}

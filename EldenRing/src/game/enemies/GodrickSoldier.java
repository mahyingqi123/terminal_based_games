package game.enemies;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.Grossmesser;

/**
 * An enemy that extends Stormveil enemy type
 * @author Kai Aw
 * @version 1.0
 * @see Stormveil
 */
public class GodrickSoldier extends Stormveil{

    /**
     * Constructor of GodrickSoldier
     */
    public GodrickSoldier(){
        super("Godrick Soldier", 'p', 198, 45,38,70);

        WeaponItem grossmesser = new Grossmesser(); // use weapon grossmesser (heavy crossbow yet to implement)
        addWeaponToInventory(grossmesser);          // use grossmesser
    }

}

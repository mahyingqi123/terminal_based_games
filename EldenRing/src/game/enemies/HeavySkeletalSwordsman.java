package game.enemies;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.Grossmesser;

/**
 * An enemy that extends Undead enemy type
 * @author Kai Aw
 * @version 1.0
 * @see Undead
 */
public class HeavySkeletalSwordsman extends Undead{

    /**
     * constructor of HeavySkeletalSwordsman
     */
    public HeavySkeletalSwordsman() {
        super("Heavy Skeletal Swordsman", 'q', 153, 27,35,892);
        WeaponItem grossmesser = new Grossmesser(); // use weapon
        addWeaponToInventory(grossmesser); // has weapon
    }
}

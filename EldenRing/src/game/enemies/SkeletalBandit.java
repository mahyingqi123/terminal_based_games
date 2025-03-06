package game.enemies;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.Scimitar;

/**
 * An enemy that extends Undead enemy type
 * @author Kai Aw
 * @version 1.0
 * @see Undead
 */
public class SkeletalBandit extends Undead{

    /**
     * constructor of SkeletalBandit
     */
    public SkeletalBandit() {
        super("Skeletal Bandit", 'b', 184,27,35,892);
        WeaponItem scimitar = new Scimitar(); // use weapon
        addWeaponToInventory(scimitar); // has weapon
    }
}

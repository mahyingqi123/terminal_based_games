package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.weapons.GiantPincer;

/**
 * An enemy that extends Fish enemy type
 * @author Kai Aw
 * @version 1.0
 * @see Fish
 */
public class GiantCrab extends Fish{

    /**
     * constructor of GiantCrab
     */
    public GiantCrab(){
        super("Giant Crab", 'C', 407, 2,318,4961);
        addWeaponToInventory(new GiantPincer(208,90));  // it's weapon
    }


}

package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * An enemy that extends Stormveil enemy type
 * @author Kai Aw
 * @version 1.0
 * @see Stormveil
 */

public class Dog extends Stormveil{

    /**
     * Constructor of Dog
     */
    public Dog(){
        super("Dog", 'a', 104, 37,52,1390);
    }

    /**
     * getter method of intrinsic weapon of Dog
     * @return IntrinsicWeapon (hand attack)
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(101, "bites", 93);
    }
}

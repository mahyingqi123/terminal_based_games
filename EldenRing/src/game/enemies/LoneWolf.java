package game.enemies;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * BEHOLD, DOG!
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Kai Aw
 *
 */
public class LoneWolf extends Mammal {

    /**
     * constructor of LoneWolf
     */
    public LoneWolf() {
        super("Lone Wolf", 'h', 102, 33,55,1470);
    }

    /**
     * getter method of intrinsic weapon of LoneWolf
     * @return IntrinsicWeapon (hand attack)
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "bites", 95);
    }
}

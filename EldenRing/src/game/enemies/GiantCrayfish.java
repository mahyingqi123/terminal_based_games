package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.weapons.GiantPincer;

/**
 * An enemy that extends Fish enemy type
 * @author Kai Aw
 * @version 1.0
 * @see Fish
 */
public class GiantCrayfish extends Fish{

    /**
     * constructor of GiantCrayFish
     */
    public GiantCrayfish(){
        super("Giant Crayfish", 'R', 4803, 1,500,2374);
        addWeaponToInventory(new GiantPincer(527,100)); // has weapon
    }


}

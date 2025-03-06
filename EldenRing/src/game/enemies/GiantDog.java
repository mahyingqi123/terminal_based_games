package game.enemies;
import game.weapons.GiantClaw;

/**
 * An enemy that extends Dog enemy type
 * @author Kai Aw
 * @version 1.0
 * @see Mammal
 */
public class GiantDog extends Mammal {

    /**
     * constructor of GiantDog
     */
    public GiantDog() {
        super("Giant Dog", 'G', 693, 4,313,1808);
        addWeaponToInventory(new GiantClaw(314,90));
    }

}

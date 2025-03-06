package game.npc;

import game.classes.Classes;
import game.enemies.CannotAttack;
import game.enemies.Enemy;
import game.utils.ClassesMenu;
import game.utils.ResetManager;
import game.utils.Status;

/**
 * An invader class that extends Enemy class and implements Npc
 * @author Ying Mah
 * @version 1.0
 * @see Enemy
 */
public class Invader extends Enemy implements Npc{

    /**
     * Constructor Invader to create Invader with attributes shown below
     *
     */
    public Invader() {
        super("Invader", 'ඞ', 0, CannotAttack.INVADER, 0, 1358, 5578);
        getBehaviours().remove(2);
        generateClasses();
    }

    /**
     * A reset function to be run when player dies
     */
    @Override
    public void reset() {
        if (ResetManager.getInstance().getPlayer().hasCapability(Status.PLAYER_DIED)){
            addCapability(Status.REMOVED);
        }
    }

    /**
     * A method to generate class and assign attributes for current invader
     */
    public void generateClasses(){
        Classes classes = ClassesMenu.randomClass();
        resetMaxHp(classes.getHitPoint());
        addWeaponToInventory(classes.getWeapon());
    }
}

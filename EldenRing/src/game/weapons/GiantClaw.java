package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.specialSkill.Slam;
import game.utils.Status;

/**
 * A enemy weapon that can only be used by enemy (GiantDog) and it extends from WeaponItem to achieve DRY principle
 * @author Ying Mah
 * @version 1.0
 * @see WeaponItem
 */
public class GiantClaw extends WeaponItem {
    /**
     * Constructor of GiantClaw
     */
    public GiantClaw(int damage,int hitRate) {
        super("Giant Claw", ' ', damage, "slams", hitRate);
        this.portable = false;
        addCapability(Status.HAS_SPECIAL_SKILL);        // it has special skill, SLAM
    }

    /**
     * getSkill method that return the special skill (slam)
     * @param target target actor
     * @param direction to apply this special effect (Area Of Effect onto surrounding with SLAM)
     * @return Slam
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new Slam(this);
    }
}

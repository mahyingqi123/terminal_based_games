package game.specialSkill;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.utils.Status;

import java.util.Stack;

/**
 * SpecialSkill class that extends Action to achieve DRY principle as it reduces codes of repetition
 * This can be applied to some enemies and weapons as they can have a special skill!
 * @author Ying Mah
 * @version 1.0
 * @see Action
 */
public abstract class SpecialSkill extends Action {

    private Actor target;           // target to get hit
    private String direction;       // direction to apply hit
    private int hitRate;            // chance of hit
    private int damage;             // damage applied
    private String verb;            // representation of special skill in word
    private WeaponItem weaponItem;  // weapon that has special skill
    private Status effect;   // weapon effect (cause effect on enemy)

    /**
     * getter of effect
     * @return Status effect
     */
    public Status getEffect() {
        return effect;
    }

    /**
     * setter of effect
     * @param effect
     */
    public void setEffect(Status effect) {
        this.effect = effect;
    }

    /**
     * getter of damage
     * @return integer value damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * getter of verb
     * @return String value
     */
    public String getVerb() {
        return verb;
    }

    /**
     * getter of hit rate
     * @return integer value of hit rate
     */
    public int getHitRate() {
        return hitRate;
    }

    /**
     * setter of damage
     * @param damage integer value of damage that can be applied
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * setter of hit rate
     * @param hitRate integer value of getting hit
     */
    public void setHitRate(int hitRate) {
        this.hitRate = hitRate;
    }

    /**
     * setter of target
     * @param target selected actor to get hit
     */

    public void setTarget(Actor target) {
        this.target = target;
    }

    /**
     * setter of weapon item
     * @param weaponItem selected to be used to hit other actor
     */
    public void setWeaponItem(WeaponItem weaponItem) {
        this.weaponItem = weaponItem;
    }

    /**
     * setter of direction
     * @param direction direction to apply such effect
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * setter of verb
     * @param verb String that represent this effect
     */
    public void setVerb(String verb) {
        this.verb = verb;
    }

    /**
     * getter of weapon item
     * @return weapon item
     */
    public WeaponItem getWeaponItem() {
        return weaponItem;
    }

    /**
     * getter of target
     * @return Actor target
     */
    public Actor getTarget() {
        return target;
    }

    /**
     * getter of direction
     * @return String value of direction
     */
    public String getDirection() {
        return direction;
    }
}

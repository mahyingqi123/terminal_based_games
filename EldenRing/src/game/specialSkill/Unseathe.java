package game.specialSkill;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;

/**
 * Unseathe class that extends SpecialSkill to achieve DRY principle as it reduces codes of repetition
 * It is a type of special skill that is used by Uchigatana weapon
 * @author Ying Mah
 * @version 1.0
 * @see SpecialSkill
 */
public class Unseathe extends SpecialSkill{

    /**
     * Constructor of unseathe that will set target, direction and weapon that can has this effect
     * @param target    actor
     * @param direction string to apply effect
     * @param weapon    weapon item
     */
    public Unseathe(Actor target, String direction, WeaponItem weapon){
        setDirection(direction);
        setTarget(target);
        setWeaponItem(weapon);
        setDamage(2* getWeaponItem().damage());
        setHitRate(60);
        setVerb("unseathes");
    }

    /**
     * execute method that will carry out effect onto specific direction
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String output that tells where the effect apply onto
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        return actor+" uses "+ getWeaponItem()+" "+getVerb()+" " +System.lineSeparator()+new AttackAction(getTarget(),getDirection(),this).execute(actor,map);
    }

    /**
     * toString method to print out the menu description onto console
     * @param actor The actor performing the action.
     * @return String output
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor +" uses "+getWeaponItem()+" "+getVerb()+" on "+getTarget()+" at "+getDirection();
    }
}

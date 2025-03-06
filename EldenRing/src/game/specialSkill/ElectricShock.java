package game.specialSkill;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.utils.Status;

/**
 * ElectricShock class that extends SpecialSkill to achieve DRY principle as it reduces codes of repetition
 * It is a type of special skill that is used by weapon (LightningBlade for example)
 * @author Kai Aw
 * @version 1.0
 * @see SpecialSkill
 */

public class ElectricShock extends SpecialSkill{

    /**
     * Constructor of electric shock that will set target, direction and weapon that can has this effect
     * @param target    actor
     * @param direction string to apply effect
     * @param weapon    weapon item
     */
    public ElectricShock(Actor target, String direction, WeaponItem weapon){
        setDirection(direction);
        setTarget(target);
        setWeaponItem(weapon);
        setDamage(getWeaponItem().damage()*2);
        setHitRate(90);
        setVerb("Electric Shock");
        setEffect(Status.PARALYZED);
    }

    /**
     * execute method that will carry out Electric Shock onto a specific enemy (actor)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String output of result
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return actor+" uses "+ getWeaponItem()+" "+getVerb()+" "+" on "+getTarget()
                +System.lineSeparator()+new AttackAction(getTarget(),getDirection(),this).execute(actor,map);
            }

    /**
     * String output of action done to print on console
     * @param actor The actor performing the action.
     * @return String output
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor+" uses "+getWeaponItem() + "'s " + getVerb()+" on "+ getTarget();
    }
}

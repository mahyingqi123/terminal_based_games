package game.specialSkill;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;

/**
 * SpinningAttack class that extends SpecialSkill to achieve DRY principle as it reduces codes of repetition
 * It is a type of special skill that is used by weapon (Grossmesser for example)
 * @author Kai Aw
 * @version 1.0
 * @see SpecialSkill
 */
public class SpinningAttack extends SpecialSkill {

    /**
     * Constructor of SpinningAttack that is added to weapon
     * @param weaponItem that can be applied with Spinning Attack
     */
    public SpinningAttack(WeaponItem weaponItem){
        setWeaponItem(weaponItem);
    }

    /**
     * execute method that will carry out Spinning Attack onto surrounding actors
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String output of result
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result=actor+" uses spinning attack";
        for(Exit exit:map.locationOf(actor).getExits()){
            Location destination = exit.getDestination();
            Actor otherActor = destination.getActor();
            if (otherActor!=null) {
                result += System.lineSeparator()+new AttackAction(otherActor,exit.getName(),getWeaponItem()).execute(actor,map);
            }
        }
        return result;
    }

    /**
     * String output of action done to print on console
     * @param actor The actor performing the action.
     * @return String output
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor+" uses "+getWeaponItem() +" spinning attack ";
    }
}

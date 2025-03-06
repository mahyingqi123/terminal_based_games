package game.specialSkill;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;

/**
 * Slam class that extends SpecialSkill to achieve DRY principle as it reduces codes of repetition
 * It is a type of special skill that is used by Fish enemies (GiantCrab for example)
 * @author Kai Aw
 * @version 1.0
 * @see SpecialSkill
 */
public class Slam extends SpecialSkill {

    /**
     * Constructor of SLam that has attributes as damage, and accuracy (hit rate)
     * @param weaponItem the weapon that uses slam
     */
    public Slam(WeaponItem weaponItem){
        setWeaponItem(weaponItem);
    }

    /**
     * execute method that identify and hit the selected actors on map (within a certain range)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String output
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result="";
        result+=actor +" uses slam area attack";
        for(Exit exit:map.locationOf(actor).getExits()){
            Location destination = exit.getDestination();
            Actor otherActor = destination.getActor();
            if(otherActor!=null) {
                result += System.lineSeparator()+new AttackAction(otherActor,exit.getName(),getWeaponItem()).execute(actor,map);
            }
        }
        return result;
    }

    /**
     * String output
     * @param actor The actor performing the action.
     * @return String output
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}

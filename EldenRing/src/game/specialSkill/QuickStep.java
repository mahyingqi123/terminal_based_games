package game.specialSkill;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;

/**
 * QuickStep class that extends SpecialSkill to achieve DRY principle as it reduces codes of repetition
 * It is a type of special skill that is used by The Great Knife weapon
 * @author Ying Mah
 * @version 1.0
 * @see SpecialSkill
 */
public class QuickStep extends SpecialSkill{

    /**
     * Constructor of QuickStep that use to decide its target in a specific direction with using a weapon by actor
     * @param target actor who is being targeted
     * @param direction direction of this actor
     * @param weapon weapon to be used
     */
    public QuickStep(Actor target, String direction, WeaponItem weapon){
        setDirection(direction);
        setTarget(target);
        setWeaponItem(weapon);
        // set damage, hit rate, and weapon's verb
        setDamage(75);
        setHitRate(70);
        setVerb(getWeaponItem().verb());
    }

    /**
     * execute method that will perform attack on the actor on the map
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return result of String after action is carried out
     */
    @Override
    public String execute(Actor actor, GameMap map) {


        // add AttackAction to String output and execute such action

        String result = actor +" uses quickstep";
        result +=System.lineSeparator()+new AttackAction(getTarget(),getDirection(),this).execute(actor,map);
        for(Exit exit:map.locationOf(actor).getExits()){
            if (!exit.getDestination().containsAnActor() &&exit.getDestination().canActorEnter(actor)){
                result += System.lineSeparator()+actor +" moves to "+exit.getName();
                new MoveActorAction(exit.getDestination(),exit.getName()).execute(actor,map);
                break;
            }
        }
        return result;
    }

    /**
     * String output that is carried out by actor with using QuickStep special skill
     * @param actor The actor performing the action.
     * @return String output
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor +" uses "+getWeaponItem()+" quickstep on "+getTarget()+" at "+getDirection()+" and moves away";
    }
}

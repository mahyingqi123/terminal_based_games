package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enemies.CannotAttack;
import game.utils.RandomNumberGenerator;
import game.utils.Status;
import game.actions.AttackAction;
import java.util.List;

/**
 * A class that figures out a potential attack that can be carried out by enemies
 * @see edu.monash.fit2099.demo.mars.Application
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Ying Mah
 *
 */
public class AttackBehaviour implements Behaviour {
    /**
     * determine type of attack that can be carried out by the enemy
     * @param enemy the Actor acting
     * @param map the GameMap containing the Actor
     * @return Action of all potential attack (special skill, normal attack, weapon attack etc)
     */
    @Override
    public Action getAction(Actor enemy, GameMap map) {
        List<Exit> exits = map.locationOf(enemy).getExits();
        WeaponItem weapon;
        for(Exit exit:exits){
            Location destination = exit.getDestination();
            // check if there is other actor at exit
            if (destination.containsAnActor()) {
                Actor otherActor = destination.getActor();
                // check if other actor is player or not same type
                if(!enemy.findCapabilitiesByType(CannotAttack.class).equals(otherActor.findCapabilitiesByType(CannotAttack.class))) {
                    // if actor has weapon, use weapon to attack
                    if (!enemy.getWeaponInventory().isEmpty()) {
                        //Use first weapon
                        weapon = enemy.getWeaponInventory().get(0);

                        // check if weapon has special skill
                        if (weapon.hasCapability(Status.HAS_SPECIAL_SKILL)) {
                            // determine whether to use special skill
                            if (RandomNumberGenerator.getRandomInt(100) <= 50) {
                                return weapon.getSkill(destination.getActor(), exit.getName());
                            }
                        }
                        //Not using special skill
                        return new AttackAction(destination.getActor(), exit.getName(), enemy.getWeaponInventory().get(0));
                    } else {
                        // not using weapon
                        return new AttackAction(destination.getActor(), exit.getName());
                    }
                }
            }

        }
        return null;
    }
}

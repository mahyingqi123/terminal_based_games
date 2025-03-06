package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DespawnActorAction;
import game.utils.RandomNumberGenerator;
import game.utils.Status;

/**
 * A class that figures out a MoveAction that will move the actor one step
 * closer to a target Actor.
 * @see edu.monash.fit2099.demo.mars.Application
 * @author Ying Mah
 *
 */
public class DespawnBehaviour implements Behaviour{

    @Override
    /**
     * getAction that helps enemy to decide it's chances of despawning from map
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return Action that decide to be despawned or continue to stay on map of enemy
     */
    public Action getAction(Actor actor, GameMap map) {
        if (RandomNumberGenerator.getRandomInt(100)<=10 && !actor.hasCapability(Status.FOLLOWING)) {
            return new DespawnActorAction();
        }
        return null;
    }
}

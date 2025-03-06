package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * DespawnActorAction class that extends Action class as it can be executed as an action that is done by Player
 * It allows game to despawn enemies from map.
 * It reaches Single Responsibility Principle as it handles despawn action in game
 * @author Ying Mah
 * @version 1.0
 * @see Action
 */
public class DespawnActorAction extends Action {

    /**
     * When the target fall into despawn rate (remove from map)
     * will be removed from map
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String output of whom being removed (enemy usually)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return menuDescription(actor);
    }

    @Override
    /**
     * String output method that return null
     */
    public String menuDescription(Actor actor) {
        return actor + " has been removed from the map";
    }
}

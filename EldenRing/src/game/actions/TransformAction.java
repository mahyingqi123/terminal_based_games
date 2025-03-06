package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Transform class that extends Action class as it can be executed as an action that is done by actor
 * Mainly is used for Undead enemies as they can transform to PilesOfBone after first death
 * AND BACK TO Undead enemy after 3 safety rounds of not getting hitted
 * reaches Single Responsibility Principle as it handles only transform action in game
 * @author Ying Mah
 * @version 1.0
 * @see Action
 */
public class TransformAction extends Action {
    private Actor transformTo;

    /**
     * Constructor of TransformAction
     * @param actor
     */
    public TransformAction(Actor actor){
        this.transformTo = actor;
    }

    /**
     * execute method that is overriden that is called to ensure "some actors can transform to another actor"
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String output of transform action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        map.removeActor(actor);
        map.addActor(transformTo,here);
        return actor +" transformed to "+transformTo;
    }

    /**
     * String output method that return null
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}

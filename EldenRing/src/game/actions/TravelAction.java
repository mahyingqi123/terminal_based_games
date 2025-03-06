package game.actions;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.Status;

/**
 * TravelAction class that extends MoveActorAction class as it can be executed as an action that is done by Player
 * It allows player to travel between different map using GoldenFogDoor, this helps to reduce repeated code (DRY) and it also
 * reaches Single Responsibility Principle as it handles travel action in game
 * @author Jun Ng
 * @version 2.0
 * @see MoveActorAction
 */
public class TravelAction extends MoveActorAction {

    /**
     * Constructor that use super to call parent to complete Travel Action
     * @param moveToLocation destination location
     * @param direction String of destination
     */
    public TravelAction(Location moveToLocation, String direction){
        super(moveToLocation,direction);

    }

    /**
     * execute method that is overriden that is called to ensure only actor with status Travel
     * can perform travel action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String output that tells the action done by actor
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(actor.hasCapability(Status.CAN_TRAVEL)){
            map.moveActor(actor, moveToLocation);
        }
        return menuDescription(actor);
    }

    /**
     * menuDescription method (toString) that print out action done
     * @param actor The actor performing the action.
     * @return String output of travel action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " travels to " + direction;
    }
}

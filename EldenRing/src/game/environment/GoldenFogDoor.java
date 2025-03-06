package game.environment;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TravelAction;
import game.utils.Status;

/**
 * GoldenFogDoor extends Ground abstract class
 * A ground type that can use for travel to another map by actor
 * @author Jun Ng
 * @version 2.0
 * @see Ground
 */
public class GoldenFogDoor extends Ground {

    private ActionList actions;

    /**
     * Constructor for GoldenFogDoor
     * @param travelAction an action
     */
    public GoldenFogDoor(TravelAction travelAction){
        super('D');
        actions = new ActionList();
        addTravel(travelAction);
    }

    /**
     * canActorEnter method checks only actor has fall status can walk onto the ground type.
     * @param actor the Actor to check
     * @return boolean
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.CAN_TRAVEL);
    }

    /**
     * ActionList method been override to return ActionList
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return ActionList
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        return actions;
    }

    /**
     * addTravel method add new travel action to action list
     * @param travelAction travel action that allow actor to move around maps
     */
    public void addTravel(TravelAction travelAction){
        actions.add(travelAction);
    }
}

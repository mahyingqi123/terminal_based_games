package game.positions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RestAction;

/**
 * SiteOfLostGrace class that extends ground class as it is a special place (location)  for players to rest
 * @author Ying Mah
 * @version 1.0
 * @see Ground
 */
public class SiteOfLostGrace extends Ground {
    private String name = "The First Step";

    /**
     * Constructor of SiteOfLostGrace that it is represented with symbol U on map
     */
    public SiteOfLostGrace() {
        super('U');
    }

    /**
     * Return the list of action that can be done by user when it is close to this SiteOfLostGrace
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return list of action that player can consider to perform it
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList list = new ActionList();
        list.add(new RestAction(this, location));
        return list;
    }

    /**
     * getter method for name
     * @return String name, The First Step
     */
    public String getName() {
        return name;
    }
}

package game.positions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SummonAction;

import java.util.List;

/**
 * SummonSign class that extends ground class as it is a special place (location)  for players to summon Npc
 * @author Ying Mah
 * @version 1.0
 * @see Ground
 */
public class SummonSign extends Ground {
    /**
     * Constructor of SummonSign that it is represented with symbol = on map
     */
    public SummonSign() {
        super('=');
    }

    /**
     * Return the list of action which contains SummonAction that can be done by user when it is close to this SummonSign
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return list of action that player can consider to perform it
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList list = new ActionList();
        list.add(new SummonAction(location));
        return list;
    }
}

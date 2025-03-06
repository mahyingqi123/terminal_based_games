package game.environment;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DeathAction;
import game.utils.Status;

/**
 * Cliff class extends Ground abstract class
 * A ground that will make actor die when step on it.
 * @author Jun Ng
 * @version 2.0
 * @see Ground
 */
public class Cliff extends Ground {
    /**
     * Constructor fot Cliff.
     */
    public Cliff(){
        super('+');
    }

    /**
     * canActorEnter method checks only actor has fall status can walk onto the ground type.
     * @param actor the Actor to check
     * @return boolean
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.CAN_FALL);
    }

    /**
     * tick method been override to perform death action when a actor walk onto cliff.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if(location.containsAnActor()){
            new DeathAction(location.getActor()).execute(location.getActor(), location.map());
        }
    }
}

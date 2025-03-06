package game.environment;

import edu.monash.fit2099.engine.positions.Location;
import game.enemies.*;

/**
 * PuddleOfWater class that extends environment abstract class
 * it specifies its type of enemy that can be spawned at environments
 * @author Jun Ng
 * @version 1.0
 * @see Environment
 * @modified Kai Aw
 */
public class PuddleOfWater extends Environment{

    /**
     * Constructor PuddleOfWater
     */
    public PuddleOfWater() {
        super('~');
    }

    /**
     * Return a specific enemy to be spawned

     * @return enemy
     */
    @Override
    public Enemy typeOfEnemy(){
        if (getSide() == Side.EASTSIDE) {
            return new GiantCrayfish();
        } else{
            return new GiantCrab();
        }
    }

    /**
     * to determine which enemy to be spawned at this location
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        super.tick(location);
        spawn(typeOfEnemy(), location);
    }
}
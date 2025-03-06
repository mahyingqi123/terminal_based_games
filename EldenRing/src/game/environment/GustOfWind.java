package game.environment;

import edu.monash.fit2099.engine.positions.Location;
import game.enemies.*;

/**
 * GustOfWind class that extends environment abstract class
 * it specifies its type of enemy that can be spawned at environments
 * @author Jun Ng
 * @version 1.0
 * @see Environment
 * @modified by Kai Aw
 */
public class GustOfWind extends Environment{

    /**
     * Constructor GustOfWind
     */
    public GustOfWind(){
        super('&');
    }

    /**
     * Return a specific enemy to be spawned
     * @return enemy
     */
    @Override
    public Enemy typeOfEnemy(){
        if (getSide() == Side.EASTSIDE) {
            return new GiantDog();
        } else {
            return new LoneWolf();
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
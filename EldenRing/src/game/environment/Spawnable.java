package game.environment;

import edu.monash.fit2099.engine.positions.Location;
import game.enemies.Enemy;

/**
 * Spawnable interface that helps several places to ensure that it implements the following functions
 * @author Jun Ng
 * @version 1.0
 */
public interface Spawnable {

    /**
     * To be implemented
     * @param enemy enemy to be spawned
     * @param location location to allow enemy to be spawned
     */
    void spawn(Enemy enemy, Location location);

    /**
     * To be implemented
     * @return specific enemy
     */
    Enemy typeOfEnemy();
}

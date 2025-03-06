package game.environment;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enemies.Enemy;
import game.utils.RandomNumberGenerator;

/**
 * Environment class that extends ground class and implements Spawnable interface
 * it can achieve easier addition of other environment that can spawn enemies and achieve DRY principle
 * @author Kai Aw
 * @version 1.0
 * @see Ground
 */
public abstract class Environment extends Ground implements Spawnable {

    private Side side;

    /**
     * Constructor Environment
     * @param displayChar character to be displayed on map
     */
    public Environment(char displayChar) {
        super(displayChar);
    }

    /**
     * tick method that will check location and spawn dog enemy within it's range
     * followed by calling typeOfEnemy() to determine enemy
     * Lastly, spawn() function to spawn the desired enemy
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (side == null) {
            if (location.x() <= location.map().getXRange().max() / 2) {
                side = Side.WESTSIDE;
            } else {
                side = Side.EASTSIDE;
            }
        }
    }

    /**
     * setter method for setting its side based on map position
     * @return Side attribute that tells its region on mmap
     */
    public Side getSide() {
        return side;
    }

    /**
     * Spawn the desired enemy if it passes several conditions
     * @param enemy enemy to be spawned
     * @param location location to allow enemy to be spawned
     */
    public void spawn(Enemy enemy, Location location) {
        if (!location.containsAnActor() && RandomNumberGenerator.getRandomInt(100) <= enemy.getSpawnRate()) {
            location.map().addActor(enemy, location);
        }
    }
}

package game.environment;

import edu.monash.fit2099.engine.positions.Location;
import game.enemies.Enemy;
import game.enemies.HeavySkeletalSwordsman;
import game.enemies.SkeletalBandit;

/**
 * Graveyard class that extends environment abstract class
 * it specifies its type of enemy that can be spawned at environments
 * @author Jun Ng
 * @version 1.0
 * @see Environment
 */
public class Graveyard extends Environment{

    /**
     * Constructor Graveyard
     */
    public Graveyard(){super('n');}

    /**
     * Return a specific enemy to be spawned
     * @return enemy
     */
    public Enemy typeOfEnemy(){
        if (getSide() == Side.EASTSIDE) {
            return new SkeletalBandit();
        } else{
            return new HeavySkeletalSwordsman();
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

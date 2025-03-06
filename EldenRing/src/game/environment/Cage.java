package game.environment;

import edu.monash.fit2099.engine.positions.Location;
import game.enemies.Dog;
import game.enemies.Enemy;

/**
 * Cage class that extends environment abstract class
 * it specifies its type of enemy that can be spawned at environments
 * a new envrionment in sub-region, Stormveil castle!
 * @author Kai Aw
 * @version 1.0
 * @see Environment
 */

public class Cage extends Environment{

    /**
     * Constructor of Cage
     */
    public Cage(){
        super('<');
    }

    /**
     * Return a specific enemy to be spawned
     * currently, only have a type of enemy (thus, return Dogs)
     * @return enemy
     */
    @Override
    public Enemy typeOfEnemy(){
        return new Dog();
    }

    /**
     * spawn the specific enemy at this location
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        spawn(typeOfEnemy(), location);
    }

}

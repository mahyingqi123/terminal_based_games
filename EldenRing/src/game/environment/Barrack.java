package game.environment;

import edu.monash.fit2099.engine.positions.Location;
import game.enemies.Enemy;
import game.enemies.GodrickSoldier;

/**
 * Barrack class that extends environment abstract class
 * it specifies its type of enemy that can be spawned at environments
 * a new envrionment in sub-region, Stormveil castle!
 * @author Kai Aw
 * @version 1.0
 * @see Environment
 */

public class Barrack extends Environment{

    /**
     * Constructor of Barrack
     */
    public Barrack(){
        super('B');
    }

    /**
     * Return a specific enemy to be spawned
     * currently, only have a type of enemy (thus, return Godrick Soldier)
     * @return enemy
     */
    @Override
    public Enemy typeOfEnemy(){
        return new GodrickSoldier();
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

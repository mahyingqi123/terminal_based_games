package game.enemies;

/**
 * Stormveil sub-region abstract class that extends from Enemy abstract class
 * It is used to differentiate the enemy type specifically only spawning in Stormveil castle
 * @author Kai Aw
 * @version 1.0
 * @see Enemy
 */

public abstract class Stormveil extends Enemy{

    public Stormveil(String name, char displayChar, int hitPoints, int spawnRate, int minDropRunes, int maxDropRunes){
        super(name, displayChar, hitPoints, CannotAttack.STORMVEIL,spawnRate,minDropRunes,maxDropRunes);
    }

}

package game.enemies;

import game.utils.Status;

/**
 * Fish abstract class that extends from Enemy abstract class
 * It is used to differentiate the enemy type specifically
 * @author Kai Aw
 * @version 1.0
 * @see Enemy
 */
public abstract class Fish extends Enemy{

    /**
     * Fish constructor that will create fish object with specific name, displayChar, hitpoints
     * spawnRate, minDropRunes, and maxDropRunes.
     * @param name String value of Fish enemy name
     * @param displayChar character that represents on the map gameplay
     * @param hitPoints integer value of enemy's health
     * @param spawnRate integer value of spawn rate on map
     * @param minDropRunes integer value of minimum runes drop
     * @param maxDropRunes integer value of maximum runes drop
     */
    public Fish(String name, char displayChar, int hitPoints, int spawnRate, int minDropRunes, int maxDropRunes) {
        super(name, displayChar, hitPoints, CannotAttack.FISH,spawnRate,minDropRunes,maxDropRunes);
    }

}

package game.classes;

import game.weapons.GreatKnife;

/**
 * Bandit class that extends Classes, as it achieves DRY principle as they share common methods and attributes
 * @author Ying Mah
 * @version 1.0
 * @see Classes
 */
public class Bandit extends Classes {

    /**
     * Constructor of Bandit that define it with
     * using Great Knife weapon
     * having hitpoint of 414
     * name is Bandit
     */
    public Bandit(){
        super(new GreatKnife(), 414, "Bandit");
    }
}

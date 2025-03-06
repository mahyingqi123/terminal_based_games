package game.classes;

import game.weapons.Club;

/**
 * Wretch class that extends Classes, as it achieves DRY principle as they share common methods and attributes
 * @author Ying Mah
 * @version 1.0
 * @see Classes
 */
public class Wretch extends Classes {

    /**
     * Constructor of Wretch that define it with
     * using Club weapon
     * having hitpoint of 414
     * name is Wretch
     */
    public Wretch(){
        super(new Club(), 414, "Wretch");
    }
}

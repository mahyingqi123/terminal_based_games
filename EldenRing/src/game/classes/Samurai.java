package game.classes;

import game.weapons.Uchigatana;

/**
 * Samurai class that extends Classes, as it achieves DRY principle as they share common methods and attributes
 * @author Ying Mah
 * @version 1.0
 * @see Classes
 */
public class Samurai extends Classes {

    /**
     * Constructor of Samurai that define it with
     * using Uchigatana weapon
     * having hitpoint of 455
     * name is Samurai
     */
    public Samurai(){
        super(new Uchigatana(), 455, "Samurai");
    }

}

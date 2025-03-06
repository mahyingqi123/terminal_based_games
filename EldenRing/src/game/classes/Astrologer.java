package game.classes;

import game.weapons.LightningBlade;
import game.weapons.LightningBlade;

/**
 * Astrologer class that extends Classes, as it achieves DRY principle as they share common methods and attributes
 * @author Ying Mah
 * @version 1.0
 * @see Classes
 */
public class Astrologer extends Classes{
    /**
     * Constructor of Astrologer that define it with
     * using LightningBlade
     * having hitpoint of 414
     * name is Bandit
     */
    public Astrologer() {
        super(new LightningBlade(), 396, "Astrologer");
    }
}

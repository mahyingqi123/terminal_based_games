package game.classes;

import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * Classes interface that can be implemented and enforced classes to have all the method below
 * @author Ying Mah, Kai Aw
 * @version 1.0
 */
public abstract class Classes {

    private final WeaponItem weapon;
    private final int hitPoint;
    private final String name;

    /**
     * Constructor of classes
     * @param weaponItem weapon that is used by the class character
     * @param health hitpoint it starts with the class character
     * @param nameClass name that belongs to the class character
     */
    public Classes(WeaponItem weaponItem, int health, String nameClass){
        weapon = weaponItem;
        hitPoint = health;
        name = nameClass;
    }

    /**
     * return the String name of Class
     * @return String value, Class
     */
    public String getName() {
        return name;
    }

    /**
     * return the integer value of hitpoints of the class character
     * @return integer value, hitpoint attribute
     */
    public int getHitPoint() {
        return hitPoint;
    }

    /**
     * return the weapon that is being used by Class
     * @return WeaponItem
     */
    public WeaponItem getWeapon() {
        return weapon;
    }

}

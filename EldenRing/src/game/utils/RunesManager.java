package game.utils;

import edu.monash.fit2099.engine.actors.Actor;
import game.items.Runes;
import java.util.Hashtable;
import java.util.Map;

/**
 * A runes manager class that act as middle man to handle transactions of rune
 * @author Jun Ng
 * @version 1.0
 * @author Adrian Kristanto
 * Modified by: Ying Mah
 *
 */
public class RunesManager {
    private Map<Actor,Runes> runesList;
    public static RunesManager runesManagerInstance;    // rune manager instance

    private RunesManager(){
        this.runesList = new Hashtable<>();
    }

    /**
     * getInstance method rto ensure always only an unique rune manager
     * @return RunesManager instance
     */
    public static RunesManager getInstance(){
        if(runesManagerInstance==null){
            runesManagerInstance = new RunesManager();
        }
        return runesManagerInstance;
    }

    /**
     * add runes item and attach to actor (player usually)
     * @param actor usually is plauer as it can accumulate runes
     * @param value initially is 0, it can add up after gaining runes from floor or enemy
     */
    public void add(Actor actor, int value){
        runesList.put(actor,new Runes(value));
    }

    /**
     * getter method to get actor's rune value in inventory
     * @param actor usually is player
     * @return Runes item
     */
    public Runes getRunes(Actor actor){
        return runesList.get(actor);
    }

    /**
     * accumulate runes value
     * @param actor usually is player
     * @param value integer value to be added to player's rune
     */
    public void addRunes(Actor actor,int value){
        runesList.get(actor).addRunes(value);
    }

    public void removeRunes(Actor actor){runesList.remove(actor);}

    /**
     * deduct runes value
     * @param actor usually is player
     * @param value integer value to be deducted from player's rune
     */
    public void deductedRunes(Actor actor,int value){
        runesList.get(actor).deductedRunes(value);
    }


}

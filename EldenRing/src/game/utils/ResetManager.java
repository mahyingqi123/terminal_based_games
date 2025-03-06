package game.utils;

import edu.monash.fit2099.engine.positions.Location;
import game.Player;


import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Ying Mah
 *
 */
public class ResetManager {
    private List<Resettable> resettables;
    private static ResetManager instance;
    private Player player;
    private Location reviveLocation;

    /**
     * HINT 1: where have we seen a private constructor before?
     * HINT 2: see the instance attribute above.
     */
    private ResetManager() {
        this.resettables = new ArrayList<>();
    }

    /**
     * getInstance method to always ensure only a ResetManager exist in game to handle reset
     * @return resetManager
     */
    public static ResetManager getInstance(){
        if (instance == null){
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * run method to reset everything
     */
    public void run() {
        for(Resettable r:resettables){
            r.reset();
        }
    }

    /**
     * setter method to define player
     * @param player that is playing the game
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * getter method for player
     * @return player instance
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * register resstable to something that exist in game (so that they will be reset after reset is called)
     * @param resettable add into List if is needed to be reset
     */
    public void registerResettable(Resettable resettable){
        resettables.add(resettable);
    }

    /**
     * return the specific revive location after reset or player is dead
     * @return Location on the map
     */
    public Location getReviveLocation() {
        return reviveLocation;
    }

    /**
     * set the specific revive location for player to respawn after reset or player is dead
     * @param reviveLocation Location on the map to be revive check point
     */
    public void setReviveLocation(Location reviveLocation) {
        this.reviveLocation = reviveLocation;
    }
    public void removeResettable(Resettable resettable){
        resettables.remove(resettable);
    }
}

package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.positions.SiteOfLostGrace;
import game.utils.ResetManager;
import game.utils.Status;

/**
 * Rest class that extends Action class as it can be executed as an action that is done by Player
 * It allows user to rest when it reaches SiteOfLostGrace to regain full hitpoints
 * this helps to reduce repeated code (DRY) as it extends from Action
 * it also reaches Single Responsibility Principle as it handles rest action in game
 * @author Ying Mah
 * @version 1.0
 * @see Action
 */
public class RestAction extends Action {
    private SiteOfLostGrace siteOfLostGrace;
    private Location location;

    /**
     * Constructor of RestAction
     * @param siteOfLostGrace a place that helps player to recover / come back alive (attribute)
     * @param location specific location attribute
     */
    public RestAction(SiteOfLostGrace siteOfLostGrace,Location location){
        this.siteOfLostGrace = siteOfLostGrace;
        this.location = location;
    }

    /**
     * execute method that is overriden that is called to ensure Player has feature to recover it's hitpoints
     * Other than that, it also ensure players to have a place to be spawned after death
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String output of game reset (player's death)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addCapability(Status.RESTING);
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.run();
        resetManager.setReviveLocation(location);
        return "Game reset";
    }

    /**
     * String output of action done by Player
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor+" rests at "+siteOfLostGrace.getName();
    }

}

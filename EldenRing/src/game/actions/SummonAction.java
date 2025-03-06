package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.npc.Ally;
import game.npc.Invader;
import game.utils.RandomNumberGenerator;

/**
 * SummonAction class that extends Action class as it can be executed as an action that is done by Player
 * It allows user to randomly summon ally or invader, this helps to reduce repeated code (DRY) and it also
 * reaches Single Responsibility Principle as it handles consume action in game
 * @author Ying Mah
 * @version 1.0
 * @see Action
 */
public class SummonAction extends Action {
    private Location summonPoint;

    /**
     * Constructor to set summonPoint to determine where to summon the Npc
     * @param summonPoint the location where Npc to be summoned around
     */
    public SummonAction(Location summonPoint){
        this.summonPoint = summonPoint;
    }

    /**
     * execute method that is overriden that is called to let player summon an NPC
     * It randomly summons an NPC at an available free exit
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String output of sell action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Actor npc;
        if (RandomNumberGenerator.getRandomInt(100)<50){
            npc = new Ally();
        }else {
            npc = new Invader();
        }
        for(Exit exit:summonPoint.getExits()){
            Location location = exit.getDestination();
            if (!location.containsAnActor()){
                location.addActor(npc);
                break;
            }
        }
        return "An "+npc+" is summoned";
    }

    /**
     * String output of summon action
     * @param actor The actor performing the action.
     * @return String value of action carried out by summoning
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor +" summons a guest from another realm";
    }
}

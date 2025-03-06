package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.utils.RandomNumberGenerator;
import game.utils.Status;

/**
 * ParalyzeAction class that extends Action class as it can be executed as an action that is done by Player
 * It allows user to paralyze an enemy, this helps to reduce repeated code (DRY) and it also
 * reaches Single Responsibility Principle as it handles buy action in game
 * @author Kai Aw
 * @version 1.0
 * @see Action
 */
public class ParalyzeAction extends Action {

    /**
     * The Actor that is to be paralyzed
     */
    private Actor target;

    /**
     * Constructor.
     *
     * @param target the Actor to be paralyzed
     */
    public ParalyzeAction(Actor target) {
        this.target = target;
    }

    /**
     * When executed, the chance of it being continuously paralyzed is determined by RNG at a rate of 80%
     * being unmovable, and 20% chance to break through loops of paralyze effect
     * @param actor The actor performing the attack action.
     * @param map The map the actor is on.
     * @return the result of the paralyze effect (continue or end status effect)
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        if (RandomNumberGenerator.getRandomInt(100) <= 80){
            return target + " is paralyzed, he could not move!";
        }
        else{
            target.removeCapability(Status.PARALYZED);
            return target + "has break through paralyze effect and it can move next round!";
        }
    }

    /**
     * Describes which target the actor is paralyzed
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return target + " is paralyzed and it cannot move!";
    }


}

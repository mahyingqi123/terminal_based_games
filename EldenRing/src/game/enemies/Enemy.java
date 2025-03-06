package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.DespawnActorAction;
import game.actions.ParalyzeAction;
import game.behaviours.*;
import game.behaviours.Behaviour;
import game.utils.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Enemy abstract class that extends from Actor class and it implements from Resettable
 * This helps to achieve DRY principles as it ensures that every enemy can share the same codes
 * It is used to determine some actors are enemies for easier implementation of player vs enemy
 * @author Kai Aw
 * @version 2.0
 * @see Actor
 */
public abstract class Enemy extends Actor implements Resettable {

    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private int maxDropRunes;
    private int minDropRunes;
    private int spawnRate;

    /**
     * Constructor Enemy to create enemies with attributes shown below
     * @param name String name of enemy
     * @param displayChar character symbol of enemy
     * @param hitPoints integer value of enemy's health
     * @param cannotAttackType Enum type that determine it's kind to prevent attack on own kind
     * @param spawnRate integer value of spawn rate on map
     * @param minDropRunes integer value of minimum runes drop
     * @param maxDropRunes integer value of maximum runes drop
     */
    public Enemy(String name, char displayChar, int hitPoints, CannotAttack cannotAttackType, int spawnRate, int minDropRunes, int maxDropRunes) {
        super(name, displayChar, hitPoints);

        // capability
        addCapability(cannotAttackType);
        addCapability(Status.HOSTILE_TO_PLAYER);

        // new attributes added
        this.spawnRate=spawnRate;
        this.minDropRunes = minDropRunes;
        this.maxDropRunes = maxDropRunes;
        RunesManager.getInstance().add(this,generateDropRunes());

        // behaviours of Enemy
        this.behaviours.put(2,new DespawnBehaviour());
        this.behaviours.put(3,new AttackBehaviour());
        this.behaviours.put(999, new WanderBehaviour());

        // reset function
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * getter method for spawn rate enemy
     * @return integer value of spawn rate
     */
    public int getSpawnRate() {
        return spawnRate;
    }

    /**
     * getter method for behaviours that can be performed by enemy at it's turn
     * @return Hash map <integer,behaviours> that can be performed
     */
    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }

    /**
     * playTurn will check it's behaviours to decide which to perform
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return action    specific action that will be carried out
     */
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // reset (removal)
        if (hasCapability(Status.REMOVED)){
            return new DespawnActorAction();
        }

        if (hasCapability(Status.PARALYZED)){
            return new ParalyzeAction(this);
        }

        for (Behaviour behaviour : getBehaviours().values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * reset function that will remove enemy from map
     */
    @Override
    public void reset() {
        addCapability(Status.REMOVED);
    }

    /**
     * The enemy can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return ActionList that tells what the actor can perform on this enemy
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && !this.hasCapability(Status.FOLLOWING)) {
            this.getBehaviours().put(1, new FollowBehaviour(otherActor));

        }
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
            for(WeaponItem weapon:otherActor.getWeaponInventory()){
                actions.add(new AttackAction(this,direction,weapon));
                if (otherActor.hasCapability(Status.HAS_SPECIAL_SKILL)){
                    actions.add(weapon.getSkill(this,direction));
                }
            }
        }
        return actions;
    }

    /**
     * getter for maximum runes drop
     * @return integer value of maximum runes drop
     */
    public int getMaxDropRunes() {
        return maxDropRunes;
    }

    /**
     * getter for minimum runes drop
     * @return integer value of minimum runes drop
     */
    public int getMinDropRunes() {
        return minDropRunes;
    }

    /**
     * generator of random runes dropped possibility based on boundaries
     * @return integer value of runes drop
     */
    public int generateDropRunes(){
        return RandomNumberGenerator.getRandomInt(minDropRunes,maxDropRunes);
    }
 }


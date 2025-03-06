package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.FlaskOfCrimsonTears;
import game.classes.Classes;
import game.utils.ResetManager;
import game.utils.Resettable;
import game.utils.RunesManager;
import game.utils.Status;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Ying Mah, Jun Ng
 *
 */
public class Player extends Actor implements Resettable {
	private Location lastLocation;
	private final Menu menu = new Menu();

	/**
	 * Constructor of player that will has several attributes such as
	 * name, display character on map, hit points
	 * they will also has capability to be hitted by enemy
	 * they can carry weapons and items such as FLaskOfCrimsonTears in it inventory
	 * @param character character that the player chose
	 */
	public Player(Classes character) {
		super("Tarnished", '@', character.getHitPoint());
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.CAN_FALL);
		this.addCapability(Status.CAN_TRAVEL);
		this.addWeaponToInventory(character.getWeapon());
		this.addItemToInventory(new FlaskOfCrimsonTears());
		maxHitPoints = character.getHitPoint();						// based on character's maximum hitpoint
		ResetManager.getInstance().registerResettable(this);		// reset player
		RunesManager.getInstance().add(this,0);			// set runes to 0
	}

	/**
	 * playTurn function that will list out every action that can be performed by player (one per round only, CHOOSE WISELY)
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return action / show player's menu (possible action)
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		setLastLocation(map.locationOf(this));
		removeCapability(Status.PLAYER_DIED);
		removeCapability(Status.PLAYER_RESTING);

		// Handle multi-turn Actions
		display.println("Hitpoint: "+printHp()+", runes:"+RunesManager.getInstance().getRunes(this).getValue());
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * reset (hit point cap at max hit point)
	 */
	@Override
	public void reset() {
		hitPoints=maxHitPoints;
	}

	/**
	 * add intrinsic weapon to player (can punch with hand)
	 * @return Intrinsic Weapon
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(11, "punch",100);
	}

	/**
	 * set the last location of actor
	 * @param lastLocation the last location of actor
	 */
	public void setLastLocation(Location lastLocation) {
		this.lastLocation = lastLocation;
	}

	/**
	 * Return the last location of player
	 * @return
	 */
	public Location getLastLocation() {
		return lastLocation;
	}
}

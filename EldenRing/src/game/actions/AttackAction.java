package game.actions;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.specialSkill.SpecialSkill;
import game.utils.RandomNumberGenerator;

/**
 * An Action to attack another Actor.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: null
 *
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	private Actor target;

	/**
	 * The direction of incoming attack.
	 */
	private String direction;

	/**
	 * Weapon used for the attack
	 */
	private Weapon weapon;
	private SpecialSkill specialSkill;

	/**
	 * Constructor.
	 *
	 * @param target actor to be attacked
	 * @param direction the direction where the attack should be performed (only used for display purposes)
	 */
	public AttackAction(Actor target, String direction, Weapon weapon) {
		this.target = target;
		this.direction = direction;
		this.weapon = weapon;
	}

	/**
	 * Constructor with intrinsic weapon as default
	 *
	 * @param target actor to be attacked
	 * @param direction the direction where the attack should be performed (only used for display purposes)
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Constructor with specialSkill as default
	 * @param target actor to be attacked
	 * @param direction the direction where the attack should be performed (only used for display purposes)
	 * @param specialSkill special skill used to perform attack action
	 */
	public AttackAction(Actor target, String direction, SpecialSkill specialSkill){
		this.target = target;
		this.direction = direction;
		this.specialSkill = specialSkill;
	}

	/**
	 * When executed, the chance to hit of the weapon that the Actor used is computed to determine whether
	 * the actor will hit the target. If so, deal damage to the target and determine whether the target is killed.
	 *
	 * @param actor The actor performing the attack action.
	 * @param map The map the actor is on.
	 * @return the result of the attack, e.g. whether the target is killed, etc.
	 * @see DeathAction
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		int damage, hitRate;
		String verb;

		if (specialSkill!=null){
			damage = specialSkill.getDamage();
			verb = specialSkill.getVerb();
			hitRate = specialSkill.getHitRate();

		}else{
			if (weapon == null) {
				weapon = actor.getIntrinsicWeapon();
			}
			verb = weapon.verb();
			damage = weapon.damage();
			hitRate= weapon.chanceToHit();
		}
		if (!(RandomNumberGenerator.getRandomInt(100) <= hitRate)) {
			return actor + " misses " + target + ".";
		}
		String result = actor + " " + verb + " " + target + " for " + damage + " damage.";
		target.hurt(damage);

		// status effect applied by using the specific special skill
		if (specialSkill != null){
			if (specialSkill.getEffect() != null){
				target.addCapability(specialSkill.getEffect());
				result += System.lineSeparator()+ target+" has been "+ specialSkill.getEffect();
			}
		}
		if (!target.isConscious()) {
			result += new DeathAction(actor).execute(target, map);
		}
		return result;
	}

	/**
	 * Describes which target the actor is attacking with which weapon
	 *
	 * @param actor The actor performing the action.
	 * @return a description used for the menu UI
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction + " with " + (weapon != null ? weapon : "Intrinsic Weapon");
	}
}

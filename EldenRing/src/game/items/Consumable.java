package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Consumable interface that helps players to consume item from inventory
 * @author Ying Mah
 * @version 1.0
 * @see
 */
public interface Consumable {
    void consume(Actor actor);
    int getUses();
    int getMaximumUses();
}

package game.trader;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BuyAction;
import game.weapons.*;

/**
 * MerchantKale class that extends Trader to achieve DRY principle as it reduces codes of repetition
 * It is a special NPC named Kale that will appear in safe place to allow trade (buy and sell) with player!
 * @author Jun Ng
 * @version 1.0
 * @see Trader
 */
public class MerchantKale extends Trader{

    /**
     * Constructor of MerchantKale to create such NPC on map!
     */
    public MerchantKale() {
        super("Merchant Kale", 'K');
    }

    /**
     * allowableAction that will allow user to perform actions as listed in method
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of action that can be carried out
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        //buy action
        actions.add(new BuyAction(new Club()));
        actions.add(new BuyAction(new GreatKnife()));
        actions.add(new BuyAction(new Uchigatana()));
        actions.add(new BuyAction(new Scimitar()));
        actions.add(new BuyAction(new LightningBlade()));
        return actions;
    }
}

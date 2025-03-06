package game.trader;

import game.utils.Status;

/**
 * FingerReaderEnia extends Trader to achieve DRY principle as it reduces codes of repetition
 * It is a special NPC named Enia that will appear in safe place to allow trade (exchange and sell) with player!
 * @author Jun Ng
 * @version 2.0
 * @see Trader
 */
public class FingerReaderEnia extends Trader{

    /**
     * Constructor of FingerReaderEnia to create such NPC on map
     * It has capability to exchange item
     */
    public FingerReaderEnia(){
        super("Finger Reader Enia",'E');
        addCapability(Status.CAN_EXCHANGE);
    }
}

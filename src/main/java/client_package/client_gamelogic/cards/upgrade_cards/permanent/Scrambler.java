package client_package.client_gamelogic.cards.upgrade_cards.permanent;

import client_package.client_gamelogic.cards.CardName;
import client_package.client_gamelogic.cards.upgrade_cards.UpgradeCard;

public class Scrambler extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public Scrambler() {super(CardName.SCRAMBLER);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        /*
        Cost: 3
        Effect: If you attack a robot, that player replaces the card in their next register
            with the top card of their deck, unless it is the final register.
         */
    }
}

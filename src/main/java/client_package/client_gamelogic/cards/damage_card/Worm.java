package client_package.client_gamelogic.cards.damage_card;

import static gamelogic.cards.CardName.WORM;

/**
 * @author Qinyi
 * When you program a worm damage card, you must immediately reboot your robot
 */
public class Worm extends DamageCard {

    public Worm(){
        super(WORM);
    }
    public void discard(){
        game.getWormDrawPile().push(this);
    }

    public void activateCard(){
    }

}

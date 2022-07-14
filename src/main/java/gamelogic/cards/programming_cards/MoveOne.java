package gamelogic.cards.programming_cards;

import server_package.Server;

import static gamelogic.cards.CardName.MOVE_ONE;

public class MoveOne extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public MoveOne() {super(MOVE_ONE);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        Server.serverLogger.info("Move One");
        // Move your robot one space in the direction it is facing.
        player.getRobot().forward(1);
    }
}

package client_package.client_gamelogic;

import client_package.Client;
import client_package.ClientObject;
import client_package.client_gamelogic.cards.Card;
import client_package.client_gamelogic.game_elements.robot.Robot;
import server_package.Server;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Mark Ringer
 */
public class CPlayer {

    int figure;

    private ClientObject client;

    Game game;
    int EnergyCubes;
    int clientID;
    private Robot robot;
    private int handCards = 0;
    private ArrayList<Card> registerCards= new ArrayList(9);

    public CPlayer(ClientObject clientObject, Game game){
        Client.clientLogger.info("Created CPlayer");
        if(clientObject.getId() == game.getClient().getId()){

            this.game = game;
            //TODO own client needs player
            ThisCPlayer thisPlayer = (ThisCPlayer) this;

            Client client = (Client) clientObject;
            this.clientID = client.getId();
            client.setPlayer(thisPlayer);
            this.client = client;
        } else {
            OtherClient client = (OtherClient) clientObject;
            try {
                this.robot = new Robot(figure,this);
                this.getRobot().setFigure(client.getFigure());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.clientID = client.getId();
            this.game = game;
            client.setPlayer(this);
            this.client = client;
        }



    }




    public Game getGame() {
        return game;
    }



    public int getEnergyCubes() {
        return EnergyCubes;
    }
    public Robot getRobot() {
        return robot;
    }

    public void registerCard(Card card, int position){
        registerCards.add(position,card);
    }
    public Card[] getRegisterCards(){
        return registerCards.toArray(new Card[0]);
    }

    public int getHandCardsCount() {
        return handCards;
    }

    public void addHandCards(int count){
        handCards += count;
    }

    public void setHandCards(int handCards) {
        this.handCards = handCards;
    }

    @Override
    public String toString() {
        return client.toString();
    }

    public int getClientID() {
        return clientID;
    }
}

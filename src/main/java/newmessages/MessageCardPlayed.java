package newmessages;

import client_package.AI.AIClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.cards.CardName;
import server_package.Client;

import java.io.IOException;


/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageCardPlayed extends Message{

    public int clientID;
    public String card;

    public MessageCardPlayed(int clientID, String card){
        this.clientID = clientID;
        this.card = card;
        type = "CardPlayed";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("card", new JsonPrimitive(card));
        content = jsonObject;
        System.out.println("Created CardPlayed Message: " + this);
    }

    public MessageCardPlayed(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        card = content.get("card").getAsString();
        System.out.println("Created CardPlayed Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param client
     * @param isBasic
     * @author Ringer
     */
    @Override
    public void activateMessageInBackend(Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        /*if (client != null) client.getPlayer().placeCard(CardName.parseCardName(card));
        else throw new ClientNotFoundException("Client with ID = "+ clientID +" not found");

         */
    }

    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    @Override
    public void activateMessageInAIFrontend(AIClient client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}

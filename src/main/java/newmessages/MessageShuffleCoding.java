package newmessages;

import client_package.client_gamelogic.Player;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Game;
import server_package.SClient;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageShuffleCoding extends Message{

    public int clientID;

    /**
     * @param clientID
     */
    public MessageShuffleCoding(int clientID) {
        this.clientID = clientID;
        type = "ShuffleCoding";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        content = jsonObject;
        System.out.println("Created Shuffle Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageShuffleCoding(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        System.out.println("Created Shuffle Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param sClient
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException{

    }
}

package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm
 */

public class MessageCheckPointReached extends Message{

    public int clientID;
    public int number;

    public MessageCheckPointReached(int clientID, int number){
        super(clientID,number);
        this.clientID = clientID;
        this.number = number;
        type = "CheckPointReached";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("number", new JsonPrimitive(number));
        content = jsonObject;
        System.out.println("Created CheckPoint Message: " + this);
    }

    public MessageCheckPointReached(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        number = content.get("number").getAsInt();
        System.out.println("Created CheckPoint Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}

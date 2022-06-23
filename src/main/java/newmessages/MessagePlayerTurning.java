package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm
 */

public class MessagePlayerTurning extends Message{

    public int clientID;
    public String rotation;

    public MessagePlayerTurning(int clientID, String rotation){
        super(clientID, rotation);
        this.clientID = clientID;
        this.rotation = rotation;
        type = "PlayerTurning";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clienID", new JsonPrimitive(clientID));
        jsonObject.add("rotation", new JsonPrimitive(rotation));
        content = jsonObject;
        System.out.println("Created Turn Message: " + this);
    }

    public MessagePlayerTurning(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        rotation = content.get("rotation").getAsString();
        System.out.println("Created Turn Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessage(Client client) throws IOException, ClientNotFoundException {

    }
}

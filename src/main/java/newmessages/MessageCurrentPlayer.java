package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessageCurrentPlayer extends Message{

    public int clientID;

    public MessageCurrentPlayer(int clientID) {
        super(clientID);
        this.clientID = clientID;
        type = "CurrentPlayer";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        content = jsonObject;
        System.out.println("Created Player Message: " + this);
    }

    public MessageCurrentPlayer(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        System.out.println("Created Player Message: " + this + " from JSON: " + jsonObject);
    }
}
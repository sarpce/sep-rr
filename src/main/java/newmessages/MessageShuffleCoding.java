package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessageShuffleCoding extends Message{

    public int clientID;

    public MessageShuffleCoding(int clientID) {
        super(clientID);
        this.clientID = clientID;
        type = "ShuffleCoding";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        content = jsonObject;
        System.out.println("Created Shuffle Message: " + this);
    }

    public MessageShuffleCoding(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        System.out.println("Created Shuffle Message: " + this + " from JSON: " + jsonObject);
    }
}
package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */
public class MessageWelcome extends Message{

    public int clientID;

    public MessageWelcome(int clientID) {
        //super(clientID);
        this.clientID = clientID;
        type = "Welcome";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        content = jsonObject;
        System.out.println("Created Welcome Message: " + this);
    }

    public MessageWelcome(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        System.out.println("Created GroupIdentification Message: " + this + " from JSON: " + jsonObject);
    }
}
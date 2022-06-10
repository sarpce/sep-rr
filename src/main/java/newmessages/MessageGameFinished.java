package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessageGameFinished extends Message{

    public int clientID;

    public MessageGameFinished(int clientID){
        super(clientID);
        this.clientID =clientID;
        type = "GameFinished";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        content = jsonObject;
        System.out.println("Created Finish Message: " + this);
    }
    public MessageGameFinished(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        System.out.println("Created Finished Message: " + this + " from JSON: " + jsonObject);
    }
}
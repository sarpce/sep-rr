package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.nio.charset.StandardCharsets;

/**
 * @author Isabel Muhm
 */

public class MessageSelectionFinished extends Message{

    public int clientID;

    public MessageSelectionFinished(int clientID){
        super(clientID);
        this.clientID = clientID;
        type = "SelectionFinished";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        content = jsonObject;
        System.out.println("Created SelectionFinished Message: " + this);
    }

    public MessageSelectionFinished(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clienID").getAsInt();
        System.out.println("Created SelectionFinished Message: " + this + " from JSON: " + jsonObject);
    }
}
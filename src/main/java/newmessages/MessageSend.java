package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessageSend extends Message{

    public String message;
    public int to;

    public MessageSend(String message, int to) {
        super(message, to);
        this.message = message;
        this.to = to;
        type = "SendChat";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("message", new JsonPrimitive(message));
        jsonObject.add("to", new JsonPrimitive(to));
        content = jsonObject;
        System.out.println("Created Send Message: " + this);
    }

    public MessageSend(JsonObject jsonObject) {
        super(jsonObject);
        message = content.get("message").getAsString();
        to = content.get("to").getAsInt();
        System.out.println("Created Send Message: " + this + " from JSON: " + jsonObject);
    }
}
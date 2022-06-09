package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessageRegister extends Message{

    public int clientID;
    public int register;
    public boolean filled;

    public MessageRegister(int clientID, int register, boolean filled){
        super(clientID, register, filled);
        this.clientID =clientID;
        this.register = register;
        this.filled= filled;
        type = "CardSelected";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("register", new JsonPrimitive(register));
        jsonObject.add("filled", new JsonPrimitive(filled));
        content = jsonObject;
        System.out.println("Created Register Message: " + this);
    }

    public MessageRegister(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        register = content.get("register").getAsInt();
        filled = content.get("filled").getAsBoolean();
        System.out.println("Created Register Message: " + this + " from JSON: " + jsonObject);
    }
}
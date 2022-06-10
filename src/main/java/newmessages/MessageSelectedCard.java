package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessageSelectedCard extends Message{

    public String card;
    public int register;

    public MessageSelectedCard(String card, int register){
        super(card, register);
        this.card = card;
        this.register = register;
        type = "SelectedCard";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("card", new JsonPrimitive(card));
        jsonObject.add("register", new JsonPrimitive(register));
        content = jsonObject;
        System.out.println("Created SelectCard Message: " + this);
    }

    public MessageSelectedCard(JsonObject jsonObject){
        super(jsonObject);
        card = content.get("card").getAsString();
        register = content.get("register").getAsInt();
        System.out.println("Created SelectCard Message: " + this + " from JSON: " + jsonObject);
    }
}
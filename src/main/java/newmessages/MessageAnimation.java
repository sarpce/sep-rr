package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * @author Isabel Muhm
 */

public class MessageAnimation extends Message{

    public String animationType;

    public MessageAnimation(String animationType){
        super(animationType);
        this.animationType = animationType;
        type = "Animation";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type", new JsonPrimitive(animationType));
        content = jsonObject;
        System.out.println("Created Animation Message: " + this);
    }

    public MessageAnimation(JsonObject jsonObject){
        super(jsonObject);
        animationType = content.get("type").getAsString();
        System.out.println("Created Animation Message: " + this + " from JSON: " + jsonObject);
    }
}
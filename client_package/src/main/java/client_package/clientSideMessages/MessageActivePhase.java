package client_package.clientSideMessages;

import client_package.AI.AIClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.io.IOException;


/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageActivePhase extends Message {

    public int phase;

    /**
     * @param phase
     */
    public MessageActivePhase(int phase){
        this.phase = phase;
        type = "ActivePhase";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("phase", new JsonPrimitive(phase));
        content = jsonObject;
        System.out.println("Created Phase Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageActivePhase(JsonObject jsonObject) {
        super(jsonObject);
        phase = content.get("phase").getAsInt();
        System.out.println("Created Phase Message: " + this + " from JSON: " + jsonObject);
    }



    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    @Override
    public void activateMessageInAIFrontend(AIClient client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}

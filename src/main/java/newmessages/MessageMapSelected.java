package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.map.MapName;
import server_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm
 */

public class MessageMapSelected extends Message{

    public String map;

    public MessageMapSelected(String map) {
        super(map);
        this.map = map;
        type = "MapSelected";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("map", new JsonPrimitive(map));
        content = jsonObject;
        System.out.println("Created MapSelected Message: " + this);
    }

    public MessageMapSelected(JsonObject jsonObject) {
        super(jsonObject);
        map = content.get("map").getAsString();
        System.out.println("Created MapSelected Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @uthor Ringer
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessage(Client client) throws IOException, ClientNotFoundException {

        try {
            client.getServer().getGame().setMapName(MapName.valueOf(map));
            for (Client clientSend:client.getServer().getClientList()) {
                clientSend.sendSelf(this);
            }
        } catch (IllegalArgumentException e){
            //client.sendSelf(new MessageSelectMap());
        }

    }
}

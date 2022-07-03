package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Direction;

import java.io.IOException;

public class Pit extends GameElement{
    public Pit(){
        
    }

    /**
     * @author Ringer
     * builds an Object from a JsonObject
     * @param jsonObject
     * @return
     * @throws IOException
     */

    public Pit(JsonObject jsonObject) throws IOException {
        Gson gson = new Gson();

        Pit pit = new Pit();
        pit.isOnBoard = jsonObject.get("isOnBoard").getAsString();

    }

    /**
     * @author Ringer
     * transforms the object to a jsonObject
     * @return
     */
    @Override
    public JsonObject toJson(){

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type",new JsonPrimitive(type.toString()));
        jsonObject.add("isOnBoard",new JsonPrimitive(isOnBoard));

        return jsonObject;
    }


}
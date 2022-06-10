package gamelogic.game_elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Direction;

import java.io.IOException;

public class StartPoint extends GameElement{
    public StartPoint(){
        super(ElementName.STARTPOINT);
    }


    public static StartPoint fromJson(JsonObject jsonObject) throws IOException {
        Gson gson = new Gson();
        StartPoint startPoint = new StartPoint();
        startPoint.isOnBoard = jsonObject.get("isOnBoard").getAsString();

        return startPoint;
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
        jsonObject.add("orientations",gson.toJsonTree(orientations));
        return jsonObject;
    }

}

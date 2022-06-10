package gamelogic.game_elements;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Direction;

public class Antenna extends GameElement{
    public Antenna(Direction direction){
        type = ElementName.ANTENNA;
        orientations.add(direction.toString());
    }


    /**
     * @author Ringer
     * transforms from jsonObject
     * @param jsonObject
     * @return
     */
    public static Antenna fromJson(JsonObject jsonObject){
        /*
        Direction direction = jsonObject.get("orientations").getAsJsonArray().get(0).;
        Antenna antenna = new Antenna()

         */
        return new Antenna(Direction.NORTH);
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
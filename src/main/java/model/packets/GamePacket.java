package model.packets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import controller.ObstacleAdapter;
import model.Obstacle.SavedObstacles;
import model.adaaptors.GamePacketAdaptor;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GamePacket {
    public String dropType ;
    public String dropName;
    public String command ;
    public int amount ;

    public String getDropType() {
        return dropType;
    }

    public String getDropName() {
        return dropName;
    }

    public int getAmount() {
        return amount;
    }

    public String getCommand(){
        return command;
    }

    public static GamePacket convertJsonToGamePacketObject(String data){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(GamePacket.class, new GamePacketAdaptor());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Type type = new TypeToken<GamePacket>(){}.getType();
        return gson.fromJson(data,type);
    }

    public static String convertGamePacketToJsonString(GamePacket gamePacket){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(GamePacket.class, new GamePacketAdaptor());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String jsonAsString = gson.toJson(gamePacket);

        return jsonAsString  ;
    }
}

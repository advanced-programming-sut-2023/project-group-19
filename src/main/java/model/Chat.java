package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import controller.ObstacleAdapter;
import model.Obstacle.SavedObstacles;
import model.adaaptors.ChatAdaptor;

import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;

public class Chat {
    public String name ;
    public Socket socket ;
    public String type ;
    public Chat(){

    }

    public Chat(Socket socket ,String name,String type) {
        this.type = type ;
        this.name = name;
        this.socket = socket;
        User.getCurrentUser().getChats().add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getType() {
        return type;
    }

    public static ArrayList<Chat> convertChatsToJsonForm(String data){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Chat.class, new ChatAdaptor());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        if (data == null) return null;
        Type type = new TypeToken<ArrayList<Chat>>(){}.getType();
        ArrayList<Chat> a2 = gson.fromJson(data,type);
        return a2;
    }
}

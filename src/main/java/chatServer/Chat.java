package chatServer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Chat {
    public String name ;
    public int port ;
    public String type ;
    public Chat(String name , int port , String type){
        this.type = type ;
        this.port =  port ;
        this.name = name ;
    }
    public Chat(){

    }
    public static String convertFromJsonToArrayListMessages(ArrayList<Chat> chats){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Chat.class, new ChatAdaptor());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Type type = new TypeToken<ArrayList<Chat>>(){}.getType();
        return gson.toJson(chats,type);
    }

    public String getName() {
        return name;
    }

    public int getPort() {
        return port;
    }

    public String getType() {
        return type;
    }
}

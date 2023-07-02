package chatServer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Chat2 {
    public String name ;
    public int port ;
    public String type ;
    public Chat2(String name , int port , String type){
        this.type = type ;
        this.port =  port ;
        this.name = name ;
    }
    public Chat2(){

    }
    public static String convertFromJsonToArrayListMessages(ArrayList<Chat2> chats){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Chat2.class, new ChatAdaptor());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        System.out.println("1");
        Type type = new TypeToken<ArrayList<Chat2>>(){}.getType();
        System.out.println("2");
        String text = gson.toJson(chats,type);
        System.out.println("Text: "+text);
        return text;
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

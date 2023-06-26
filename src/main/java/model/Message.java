package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import controller.ObstacleAdapter;
import javafx.scene.image.ImageView;
import model.Obstacle.SavedObstacles;
import model.adaaptors.MessageAdaptor;
import view.MessageGetter;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.util.ArrayList;

public class Message {
    public String sender;
    public String content;
    public String reaction;
    public String sentTime;
    public ImageView avatar;
    public boolean seen;
    public Message(){

    }

    public Message(String sender, String content, boolean seen, ImageView avatar) {
        this.sender = sender;
        this.content = content;
        this.seen = seen;
        this.avatar = avatar;
        LocalTime localTime = LocalTime.now();
        String[] list = localTime.toString().split(":");
        this.sentTime = list[0]+":"+list[1];
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }


    public String getContent() {
        return content;
    }

    public String getSentTime() {
        return sentTime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }


    public ImageView getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageView avatar) {
        this.avatar = avatar;
    }

    public static ArrayList<Message> getWholeMessagesFromJson(String data){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Message.class, new MessageAdaptor());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        if (data.equals("")) return null;
        Type type = new TypeToken<ArrayList<Message>>(){}.getType();
        ArrayList<Message> a2 = gson.fromJson(data,type);
        return a2;
    }
    public static Message getMessageFromJson(String data){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Message.class, new MessageAdaptor());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        if (data.equals("")) return null;
        Type type = new TypeToken<Message>(){}.getType();
        Message message = gson.fromJson(data,type);
        return message;
    }
    public static String convertMessageToJson(Message message){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Message.class, new MessageAdaptor());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String jsonAsString = gson.toJson(message);
        return jsonAsString;
    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }
}

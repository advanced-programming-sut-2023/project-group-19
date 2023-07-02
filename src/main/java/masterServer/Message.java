package masterServer;


import chatServer.MessageAdaptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.util.ArrayList;

public class Message {
    public String sender;
    public String content;
    public String reaction ;
    public boolean seen;
    public String sentTime;
    public String avatar;
    public String messageKey;
    public Message(){

    }

    public Message(String sender, String content, boolean seen, String avatar) {
        this.sender = sender;
        this.content = content;
        this.seen = seen;
//        this.avatar = avatar;
        LocalTime localTime = LocalTime.now();
        String[] list = localTime.toString().split(":");
        this.sentTime = list[0]+":"+list[1];
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public void setSentTime(String sentTime) {
        this.sentTime = sentTime;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public String getReaction() {
        return reaction;
    }

    public String getSentTime() {
        return sentTime;
    }

    public String getAvatar() {
        return avatar;
    }
    public static Message convertFromJsonToMessage(String data){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Message.class, new MessageAdaptor());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Type type = new TypeToken<Message>(){}.getType();
        return gson.fromJson(data,type);
    }
    public static String convertFromJsonToArrayListMessages(ArrayList<Message> messages){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Message.class, new MessageAdaptor());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Type type = new TypeToken<ArrayList<Message>>(){}.getType();
        return gson.toJson(messages,type);
    }

    public static String convertFromJsonToMessageToString(Message messages){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Message.class, new MessageAdaptor());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Type type = new TypeToken<Message>(){}.getType();
        return gson.toJson(messages,type);
    }


    //    public ImageView getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(ImageView avatar) {
//        this.avatar = avatar;
//    }
}

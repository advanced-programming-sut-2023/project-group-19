package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Chat;
import model.Obstacle.SavedObstacles;
import model.User;
import model.adaaptors.ChatAdaptor;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserAdaptor extends TypeAdapter<User> {
    @Override
    public void write(JsonWriter writer, User user) throws IOException {
        writer.beginObject();
        writer.name("username");
        writer.value(user.getUsername());
        writer.name("password");
        writer.value(user.getPassword());
        writer.name("nickname");
        writer.value(user.getNickname());
        writer.name("email");
        writer.value(user.getEmail());
        writer.name("recoveryQuestion");
        writer.value(user.getRecoveryQuestion());
        writer.name("slogan");
        writer.value(user.getSlogan());
        writer.name("recoveryQuestionNumber");
        writer.value(user.getRecoveryQuestionNumber());
        writer.name("highScore");
        writer.value(user.getHighScore());
        writer.name("rank");
        writer.value(user.getRank());
        writer.name("avatar");
        writer.value(user.getAvatar().getImage().getUrl());
        writer.endObject();
    }
//    public String returnChatsObject(ArrayList<Chat> chats){
//        GsonBuilder builder = new GsonBuilder();
//        builder.registerTypeAdapter(Chat.class, new ChatAdaptor());
//        builder.setPrettyPrinting();
//        Gson gson = builder.create();
//        Type type = new TypeToken<ArrayList<Chat>>(){}.getType();
//        return gson.toJson(chats,type);
//    }

    @Override
    public User read(JsonReader reader) throws IOException {
        User user = new User();
        reader.beginObject();
        String fieldname = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                //get the current token
                fieldname = reader.nextName();
            }

            if ("username".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                user.username = reader.nextString();
            }

            if("password".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                user.password =(reader.nextString());
            }
            if("nickname".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                user.nickname = (reader.nextString());
            }
            if("email".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                user.email = (reader.nextString());
            }
            if("recoveryQuestion".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                user.recoveryQuestion = (reader.nextString());
            }
            if("slogan".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                user.slogan = (reader.nextString());
            }
            if("recoveryQuestionNumber".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                user.recoveryQuestionNumber = (reader.nextInt());
            }
            if("highScore".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                user.highScore = (reader.nextInt());
            }
            if("rank".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                user.rank = (reader.nextInt());
            }
            if("avatar".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                String url = reader.nextString();
                user.setAvatar(new ImageView(new Image(url)));
            }

        }
        reader.endObject();
        return user;
    }
}

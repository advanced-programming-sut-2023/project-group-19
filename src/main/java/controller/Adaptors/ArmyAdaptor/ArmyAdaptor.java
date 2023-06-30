//package controller.Adaptors.ArmyAdaptor;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.TypeAdapter;
//import com.google.gson.reflect.TypeToken;
//import com.google.gson.stream.JsonReader;
//import com.google.gson.stream.JsonToken;
//import com.google.gson.stream.JsonWriter;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import model.Chat;
//import model.Human.Troop.Army;
//import model.User;
//import model.adaaptors.ChatAdaptor;
//
//import java.awt.*;
//import java.io.IOException;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ArmyAdaptor extends TypeAdapter<Army> {
//    @Override
//    public void write(JsonWriter writer, Army army) throws IOException {
//        writer.beginObject();
//        writer.name("state");
//        writer.value(army.getState().ordinal());
//        writer.name("isIntFight");
//        writer.value(army.isIntFight());
//        writer.name("hasMovedForDefensiveState");
//        writer.value(army.isHasMovedForDefensiveState());
//        writer.name("pastXcordinate");
//        writer.value(army.getPastXcordinate());
//        writer.name("pastYcordinate");
//        writer.value(army.getPastYcordinate());
//        writer.name("imageView");
//        writer.value(army.getImageView().getImage().getUrl());
//        writer.name("defaultImage");
//        writer.value(army.getDefaultImage().getUrl());
//        writer.name("armyForm");
//        writer.value(army.getArmyForm());
//        writer.name("hp");
//        writer.value(army.getHp());
//        writer.name("maxHp");
//        writer.value(army.getMaxHp());
//        writer.name("speed");
//        writer.value(army.getSpeed());
//        writer.name("defencePower");
//        writer.value(army.getDefencePower());
//        writer.name("attackPower");
//        writer.value(army.getAttackPower());
//        writer.name("startXCoordinate");
//        writer.value(army.getStartXCoordinate());
//        writer.name("startYCoordinate");
//        writer.value(army.getStartYCoordinate());
//        writer.name("xCoordinate");
//        writer.value(army.getxCoordinate());
//        writer.name("yCoordinate");
//        writer.value(army.getyCoordinate());
//        writer.name("goalXCoordinate");
//        writer.value(army.getGoalXCoordinate());
//        writer.name("goalXCoordinate");
//        writer.value(army.getGoalXCoordinate());
//        writer.name("goalYCoordinate");
//        writer.value(army.getGoalYCoordinate());
//        writer.name("finalXCoordinate");
//        writer.value(army.getFinalXCoordinate());
//        writer.name("finalYCoordinate");
//        writer.value(army.getFinalYCoordinate());
//        writer.name("restOfMoves");
//        writer.value(army.getRestOfMoves());
//        writer.name("typeOfArmy");
//        writer.value(army.getTypeOfArmy().ordinal());
//        writer.name("direction");
//        writer.value(army.getDirection());
//        writer.name("restOfMoves");
//        writer.value(army.getRestOfMoves());
//        writer.name("path");
//        writer.value(convertListOfPathIntoString(army.getMyPath()));
//        writer.endObject();
//    }
//    private String convertListOfPathIntoString(List<Integer> path){
//        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<Chat>>(){}.getType();
//        return gson.toJson(path,type);
//    }
//
//    @Override
//    public Army read(JsonReader reader) throws IOException {
//        Army army = new Army();
////        reader.beginObject();
////        String fieldname = null;
////
////        while (reader.hasNext()) {
////            JsonToken token = reader.peek();
////
////            if (token.equals(JsonToken.NAME)) {
////                //get the current token
////                fieldname = reader.nextName();
////            }
////
////            if ("state".equals(fieldname)) {
////                //move to next token
////                token = reader.peek();
////                user.username = reader.nextString();
////            }
////
////            if("password".equals(fieldname)) {
////                //move to next token
////                token = reader.peek();
////                user.password =(reader.nextString());
////            }
////            if("nickname".equals(fieldname)) {
////                //move to next token
////                token = reader.peek();
////                user.nickname = (reader.nextString());
////            }
////            if("REQUEST_TYPE".equals(fieldname)) {
////                //move to next token
////                token = reader.peek();
////                user.REQUEST_TYPE = (reader.nextString());
////            }
////            if("email".equals(fieldname)) {
////                //move to next token
////                token = reader.peek();
////                user.email = (reader.nextString());
////            }
////            if("recoveryQuestion".equals(fieldname)) {
////                //move to next token
////                token = reader.peek();
////                user.recoveryQuestion = (reader.nextString());
////            }
////            if("slogan".equals(fieldname)) {
////                //move to next token
////                token = reader.peek();
////                user.slogan = (reader.nextString());
////            }
////            if("recoveryQuestionNumber".equals(fieldname)) {
////                //move to next token
////                token = reader.peek();
////                user.recoveryQuestionNumber = (reader.nextInt());
////            }
////            if("highScore".equals(fieldname)) {
////                //move to next token
////                token = reader.peek();
////                user.highScore = (reader.nextInt());
////            }
////            if("rank".equals(fieldname)) {
////                //move to next token
////                token = reader.peek();
////                user.rank = (reader.nextInt());
////            }
////            if("avatar".equals(fieldname)) {
////                //move to next token
////                token = reader.peek();
////                String url = reader.nextString();
////                user.setAvatar(new ImageView(new Image(url)));
////            }
////
////        }
////        reader.endObject();
////        return user;
////    }
////}

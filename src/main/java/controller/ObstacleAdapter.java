package controller;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import model.Obstacle.SavedObstacles;

import java.io.IOException;

public class ObstacleAdapter extends TypeAdapter<SavedObstacles> {
    @Override
    public void write(JsonWriter writer, SavedObstacles savedObstacles) throws IOException {
        writer.beginObject();
        writer.name("name");
        writer.value(savedObstacles.getName());
        writer.name("type");
        writer.value(savedObstacles.getType());
        writer.endObject();
    }


    @Override
    public SavedObstacles read(JsonReader reader) throws IOException {
        SavedObstacles savedObstacles = new SavedObstacles();
        reader.beginObject();
        String fieldname = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                //get the current token
                fieldname = reader.nextName();
            }

            if ("name".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                savedObstacles.name = reader.nextString();
            }

            if("type".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                savedObstacles.type = reader.nextString();
            }
//            if("notBuildable".equals(fieldname)) {
//                //move to next token
//                token = reader.peek();
//                savedObstacles.type = reader.nextString();
//            }
//            if("notBuildable".equals(fieldname)) {
//                //move to next token
//                token = reader.peek();
//                savedObstacles.type = reader.nextString();
//            }
//            if("notPassable".equals(fieldname)) {
//                //move to next token
//                token = reader.peek();
//                savedObstacles.type = reader.nextString();
//            }

        }
        reader.endObject();
        return savedObstacles;
    }
}

package basicGameModel;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class ObstacleAdapter extends TypeAdapter<SavedObstacles> {
    @Override
    public void write(JsonWriter writer, SavedObstacles savedObstacles) throws IOException {
        writer.beginObject();
        writer.name("name");
        writer.value(savedObstacles.getName());
        writer.name("type");
        writer.value(savedObstacles.getType());
        writer.name("notBuildable");
        writer.value(savedObstacles.isNotBuildable());
        writer.name("numberOfPlayers");
        writer.value(savedObstacles.getNumberOfPlayers());
        writer.name("nameOfMap");
        writer.value(savedObstacles.getNameOfMap());
        writer.name("notPassable");
        writer.value(savedObstacles.isNotPassable());
        writer.name("x");
        writer.value(savedObstacles.getX());
        writer.name("y");
        writer.value(savedObstacles.getY());
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
            if ("nameOfMap".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                savedObstacles.nameOfMap = reader.nextString();
            }
            if ("numberOfPlayers".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                savedObstacles.numberOfPlayers = reader.nextInt();
            }

            if("type".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                savedObstacles.type = reader.nextString();
            }
            if("notBuildable".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                savedObstacles.notBuildable = reader.nextBoolean();
            }
            if("notPassable".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                savedObstacles.notPassable = reader.nextBoolean();
            }
            if("x".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                savedObstacles.x = reader.nextInt();
            }
            if("y".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                savedObstacles.y = reader.nextInt();
            }

        }
        reader.endObject();
        return savedObstacles;
    }
}

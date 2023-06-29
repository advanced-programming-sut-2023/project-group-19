package gameServer;

import chatServer.Chat;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class GamePacketAdaptor extends TypeAdapter<GamePacket> {

    @Override
    public void write(JsonWriter writer, GamePacket gamePacket) throws IOException {
        writer.beginObject();
        writer.name("dropType");
        writer.value(gamePacket.getDropType());
        writer.name("dropName");
        writer.value(gamePacket.getDropName());
        writer.name("amount");
        writer.value(gamePacket.getAmount());
        writer.name("command");
        writer.value(gamePacket.getCommand());
        writer.endObject();
    }

    @Override
    public GamePacket read(JsonReader reader) throws IOException {
        GamePacket gamePacket = new GamePacket();
        reader.beginObject();
        String fieldname = null;
        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                //get the current token
                fieldname = reader.nextName();
            }
            if ("dropType".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                gamePacket.dropType = reader.nextString();
            }
            if("dropName".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                gamePacket.dropName =(reader.nextString());
            }
            if("amount".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                gamePacket.amount = (reader.nextInt());
            }
            if("command".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                gamePacket.command = (reader.nextString());
            }
        }
        reader.endObject();
        return gamePacket;
    }
}

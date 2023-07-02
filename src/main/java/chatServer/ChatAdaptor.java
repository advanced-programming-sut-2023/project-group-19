package chatServer;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class ChatAdaptor extends TypeAdapter<Chat2> {
    @Override
    public void write(JsonWriter writer, Chat2 chat) throws IOException {
        writer.beginObject();
        writer.name("name");
        writer.value(chat.getName());
        writer.name("port");
        writer.value(chat.getPort());
        writer.name("type");
        writer.value(chat.getType());
        writer.endObject();
    }

    @Override
    public Chat2 read(JsonReader reader) throws IOException {
        Chat2 chat = new Chat2();
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
                chat.name = reader.nextString();
            }

            if("port".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                chat.port =(reader.nextInt());
            }
            if("type".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                chat.type = (reader.nextString());
            }
        }
        reader.endObject();
        return chat;
    }
}

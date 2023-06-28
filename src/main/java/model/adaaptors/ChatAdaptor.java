package model.adaaptors;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import model.Chat;

import java.io.IOException;
import java.net.Socket;

public class ChatAdaptor extends TypeAdapter<Chat> {
    @Override
    public void write(JsonWriter writer, Chat chat) throws IOException {
        writer.beginObject();
        writer.name("name");
        writer.value(chat.getName());
        writer.name("socket");
        writer.value(chat.getSocket().getPort());
        writer.name("type");
        writer.value(chat.getType());
        writer.endObject();
    }

    @Override
    public Chat read(JsonReader reader) throws IOException {
        Chat chat = new Chat();
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
                Socket socket  =  new Socket("localhost",reader.nextInt());
                chat.socket = socket;
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

package model.adaaptors;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Message;
import model.User;

import java.io.IOException;
import java.io.InputStream;

public class MessageAdaptor extends TypeAdapter<Message> {
    @Override
    public void write(JsonWriter writer, Message message) throws IOException {
        writer.beginObject();
        writer.name("sender");
        writer.value(message.getSender());
        writer.name("content");
        writer.value(message.getContent());
        writer.name("sentTime");
        writer.value(message.getSentTime());
        writer.name("avatar");
        writer.value("n");
        writer.name("seen");
        writer.value(false);
        writer.endObject();
    }

    @Override
    public Message read(JsonReader reader) throws IOException {
        Message message = new Message();
        reader.beginObject();
        String fieldname = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                //get the current token
                fieldname = reader.nextName();
            }

            if ("sender".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                message.sender = reader.nextString();
            }

            if("content".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                message.content =(reader.nextString());
            }
            if("sentTime".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                message.sentTime = (reader.nextString());
            }
            if("avatar".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                String url = reader.nextString();
                message.avatar = new ImageView();
            }
            if("seen".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                message.seen = (reader.nextBoolean());
            }

        }
        reader.endObject();
        return message;
    }
}

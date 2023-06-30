package view.Model;

import javafx.scene.layout.HBox;
import model.Chat;

import java.net.Socket;

public class NewHBox extends HBox {
    private Chat chat;
    private String name;

    public NewHBox(Chat chat, String name) {
        this.chat = chat;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}

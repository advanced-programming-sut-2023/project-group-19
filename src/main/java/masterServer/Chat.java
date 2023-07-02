package masterServer;

import java.util.ArrayList;

public class Chat {
    public String chatId;
    Chat(String chatId){
        this.chatId = chatId;
    }
    public ArrayList<String> chatMembers = new ArrayList<>();
    public ArrayList<Message> chatMessages = new ArrayList<>();

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public ArrayList<String> getChatMembers() {
        return chatMembers;
    }

    public void setChatMembers(ArrayList<String> chatMembers) {
        this.chatMembers = chatMembers;
    }

    public ArrayList<Message> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(ArrayList<Message> chatMessages) {
        this.chatMessages = chatMessages;
    }
}
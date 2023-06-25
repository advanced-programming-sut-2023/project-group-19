package model;

import model.Human.Troop.Army;

import java.util.ArrayList;

public class Chat {
    private ArrayList<User> chatMembers;
    private ChatType chatType;

    public Chat(ArrayList<User> chatMembers, ChatType chatType) {
        this.chatMembers = chatMembers;
        this.chatType = chatType;
    }

    public ArrayList<User> getChatMembers() {
        return chatMembers;
    }

    public void addToChatMembers(User newChatMember) {
        chatMembers.add(newChatMember);
    }

    public ChatType getChatType() {
        return chatType;
    }

    public void setChatType(ChatType chatType) {
        this.chatType = chatType;
    }
}

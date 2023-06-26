package model;

import javafx.scene.image.ImageView;

import java.time.LocalTime;

public class Message {
    public String sender;
    public String content;
    public String reaction;
    public boolean seen;
    public String sentTime;
    public ImageView avatar;

    public Message(String sender, String content, boolean seen, ImageView avatar) {
        this.sender = sender;
        this.content = content;
        this.seen = seen;
        this.avatar = avatar;
        LocalTime localTime = LocalTime.now();
        String[] list = localTime.toString().split(":");
        this.sentTime = list[0]+":"+list[1];
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }


    public ImageView getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageView avatar) {
        this.avatar = avatar;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }
}

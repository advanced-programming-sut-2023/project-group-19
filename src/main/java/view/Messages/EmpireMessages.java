package view.Messages;

import model.Manage;

public enum EmpireMessages {
    POPULARITY_FACTOR("""
            Popularity Factors :
            1.Food
            2.Tax
            3.Religion
            4.Fear
            """),;
    private String messages;

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    EmpireMessages(String messages) {
        this.messages=messages;
    }
}

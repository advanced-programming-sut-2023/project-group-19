package model;

import java.net.Socket;

public class Chat {
    private String name ;
    private Socket socket ;
    private String type ;

    public Chat(Socket socket ,String name,String type) {
        this.type = type ;
        this.name = name;
        this.socket = socket;
        User.getCurrentUser().getChats().add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}

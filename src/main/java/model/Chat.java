package model;

import java.net.Socket;
import java.security.PublicKey;

public class Chat {
    private String name ;
    private Socket socket ;
    private String type ;

    public Chat(String name,String type) {
        this.type = type ;
        this.name = name;
//        this.socket = socket;
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

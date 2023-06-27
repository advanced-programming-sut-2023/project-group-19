package view.Model;

import javafx.scene.layout.HBox;

import java.net.Socket;

public class NewHBox extends HBox {
    private int port;
    private String name;
    private Socket socket;

    public NewHBox(int port,String name) {
        this.port = port;

        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
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

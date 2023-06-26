package chatServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer extends Thread{
    public int port ;
    public ArrayList<Message> allMessages =  new ArrayList<>();
    public ArrayList<Socket> allSockets = new ArrayList<>();
    public ArrayList<Socket> inChatUsers = new ArrayList<>();
    public ChatServer(int port) {
        this.port = port ;
        System.out.println("Starting Broker service...");
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                ChatConnection chatConnection = new ChatConnection(socket,this);
                chatConnection.start();
            }
        } catch (
                IOException e) {
        }
    }
}

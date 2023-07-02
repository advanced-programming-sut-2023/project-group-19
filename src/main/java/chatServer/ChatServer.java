package chatServer;

import masterServer.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatServer extends Thread{
    public int port ;
    public ArrayList<Message> allMessages =  new ArrayList<>();
    public ArrayList<Socket> allSockets = new ArrayList<>();
    public ArrayList<Socket> inChatUsers = new ArrayList<>();
    public HashMap<String, Chat2> saveUserChats;
    public ChatServer(int port) {
//        Message message = new Message("mamad","salam",true,"ddfscrd");
//        allMessages.add(message);
        this.port = port ;
        System.out.println("Starting Broker service chat server...");
        System.out.println("port of chat server is: " + port);
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

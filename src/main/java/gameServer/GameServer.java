package gameServer;

import basicGameModel.User;
import masterServer.Connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer extends Thread{
    private int port ;
    public ArrayList<Socket> socketOfPlayers =  new ArrayList<>();

    public GameServer(int port) {
        this.port =  port ;
        System.out.println("Starting Broker service...");

    }
    public ArrayList<User> players =  new ArrayList<>();

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                socketOfPlayers.add(socket);
                GameConnection gameConnection = new GameConnection(socket,this);
                gameConnection.start();
            }
        } catch (IOException e) {
            //TODO: try to reconnect...
        }
    }
}

package gameServer;

import basicGameModel.User;
import masterServer.Connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer extends Thread{
    private int numberOfPlayers ;
    //when the game is played please on the lock
    public boolean lock ;
    private int port ;
    public GameServer(int port,int numberOfPlayers) {
        this.port =  port ;
        this.numberOfPlayers  = numberOfPlayers ;
        System.out.println("Starting Game Server...");
    }
    public ArrayList<Socket> socketOfPlayers =  new ArrayList<>();

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

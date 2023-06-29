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
        System.out.println("Starting Broker service...");
    }
    public ArrayList<User> players =  new ArrayList<>();

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                numberOfPlayers ++ ;
                Connection connection = new Connection(socket);
                connection.start();
                if(numberOfPlayers == players.size()) lock = true ;
            }
        } catch (IOException e) {
            //TODO: try to reconnect...
        }
    }
}

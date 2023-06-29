package masterServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MasterServer {
    //Armin : my Teammates,please pay attention that port of master server is: 8080
    public static int gamePort = 7000 ;
    public static int chatPort = 6000 ;
    public static int globalChatPort = 8000 ;


    public MasterServer(int port) {
        System.out.println("Starting Broker service2323...");
        try {
            System.out.println(port);
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                //The code that placed in the below is a blocking code
                Socket socket = serverSocket.accept();
                Connection connection = new Connection(socket);
                connection.start();
            }
        } catch (IOException e) {
            //TODO: try to reconnect...
        }
    }
}

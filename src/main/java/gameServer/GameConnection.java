package gameServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GameConnection extends Thread{
    Socket socket;
    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;
    GameServer gameServer ;

    public GameConnection(Socket socket,GameServer gameServer) throws IOException {
        System.out.println("New connection form: " + socket.getInetAddress() + ":" + socket.getPort());
        this.socket = socket;
        this.gameServer = gameServer ;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }
    @Override
    public void run() {
        super.run();
    }
    private void handleCommand() throws IOException {
        String data = dataInputStream.readUTF();
        //every command you got send it to whole active sockets
        for(Socket socket : gameServer.socketOfPlayers){
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(data);
        }
    }


}

package gameServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GameConnection extends Thread{
    private Socket socket;
    private GameServer gameServer;
    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;


    public GameConnection(Socket socket,GameServer gameServer) throws IOException {
        System.out.println("New connection form: " + socket.getInetAddress() + ":" + socket.getPort());
        this.socket = socket;
        this.gameServer = gameServer ;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }
    @Override
    public void run() {
        try {
            handleCommand();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void handleCommand() throws IOException {
        String data = dataInputStream.readUTF();
        for(Socket socket : gameServer.socketOfPlayers){
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(data);
        }
    }


}

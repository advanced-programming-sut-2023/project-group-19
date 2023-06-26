package gameServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GameConnection extends Thread{
    Socket socket;
    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;

    public GameConnection(Socket socket) throws IOException {
        System.out.println("New connection form: " + socket.getInetAddress() + ":" + socket.getPort());
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }
    @Override
    public void run() {
        super.run();
    }

}

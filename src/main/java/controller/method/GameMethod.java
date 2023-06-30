package controller.method;

import model.packets.GamePacket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GameMethod {

    private Socket socket;
    private DataOutputStream dataOutputStream ;
    private DataInputStream dataInputStream ;

    public GameMethod(Socket socket) throws IOException {
        this.socket = socket ;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }
    public void sendDataToGameServer(GamePacket gamePacket) throws IOException {
        String data = GamePacket.convertGamePacketToJsonString(gamePacket);
        dataOutputStream.writeUTF(data);
    }
    public void getDataFromGameServer(){
        GameDataGetter gameDataGetter = new GameDataGetter(dataInputStream);
        gameDataGetter.start();
    }
}

package controller.method;

import model.Message;
import model.packets.GamePacket;

import java.io.DataInputStream;
import java.io.IOException;

public class GameDataGetter extends Thread {
    public DataInputStream dataInputStream ;

    public GameDataGetter(DataInputStream dataInputStream){
        this.dataInputStream  = dataInputStream ;
    }

    @Override
    public void run() {
        try {
            gameGetterData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void gameGetterData() throws IOException {
        String data = dataInputStream.readUTF();
        GamePacket gamePacket = GamePacket.convertJsonToGamePacketObject(data);
        //TODO : main code of server
    }
}

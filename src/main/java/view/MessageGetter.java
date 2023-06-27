package view;

import model.Message;

import java.io.DataInputStream;
import java.io.IOException;

public class MessageGetter extends Thread{
    public DataInputStream dataInputStream ;
    public MessageGetter(DataInputStream dataInputStream){
        this.dataInputStream  = dataInputStream ;
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(500);
                getMessage();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted with reason : " + e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(Thread.currentThread().getName() + " Stopped");
        return;
    }
    public void getMessage() throws IOException {
        String data = dataInputStream.readUTF();
        System.out.println(data);
        Message message = Message.getMessageFromJson(data);
        System.out.println(message.content);
    }
}

package view;

import javafx.application.Platform;
import model.Message;

import java.io.DataInputStream;
import java.io.IOException;

public class MessageGetter extends Thread{
    public DataInputStream dataInputStream ;
    public Message newMessage;
    public MessageGetter(DataInputStream dataInputStream){
        this.dataInputStream  = dataInputStream ;
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(500);
                Message message = getMessage();
                System.out.println("Thread : "+message.getContent());
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        String content = message.getContent();
                        if (content.matches("#\\d+#.*")){
                            try {
                                System.out.println("Trying tryCatch");
                                Lobby.editMessage(message);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }else if (content.matches("#\\S+#.+")){
                            System.out.println("Entered the condition of JustForMe");
                            try {
                                Lobby.deleteMessageJustForMe(message);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }else{
                            Lobby.addNewMessageToChat(message);
                        }
                    }
                });

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
    public Message getMessage() throws IOException {
        String data = dataInputStream.readUTF();
        Message message = Message.getMessageFromJson(data);
        System.out.println("Message is : "+message.getContent());
        return message;
    }
}
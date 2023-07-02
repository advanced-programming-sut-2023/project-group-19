package controller.method;

import javafx.application.Platform;
import model.Message;
import view.Lobby;

import java.io.DataInputStream;
import java.io.IOException;

public class MessageGetter extends Thread{
    public DataInputStream dataInputStream ;
    public Message newMessage;

    MessageGetter messageGetter = this ;
    public MessageGetter(DataInputStream dataInputStream){
        this.dataInputStream  = dataInputStream ;
    }
    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Thread.sleep(500);
                Message message = getMessage();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (message == null) return;
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
        if(data.equals("####")) return null ;
        System.out.println("into message getter : " + data);
        Message message = Message.getMessageFromJson(data);
        return message;
    }

}

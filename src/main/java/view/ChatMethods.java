package view;

import com.google.gson.Gson;
import javafx.scene.image.ImageView;
import model.Chat;
import model.Manage;
import model.Message;
import model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ChatMethods {
    //TODO : chat must be a thread
    public DataInputStream dataInputStream ;
    public DataOutputStream dataOutputStream ;

    public void refreshChats() throws IOException {
        Manage.masterServerDataOutputStream.writeUTF("REFRESH_CHAT");
        Manage.masterServerDataOutputStream.writeUTF(User.getCurrentUser().getUsername());
        String data =  Manage.masterServerDataInputStream.readUTF();
        if(data.equals("null")) return;
        ArrayList<Chat> chats = Chat.convertChatsToJsonForm(data);
        //do someThing
        for(Chat chat : chats){
            User.getCurrentUser().getChats().add(chat);
            //do someThing
        }
    }
    //TODO : به ازای هر چت که روی ان کلیک شد یک ابچمت از این کلاس اسخنه شود

    public ChatMethods(Socket socket) throws IOException {
        dataOutputStream  = new DataOutputStream(socket.getOutputStream());
        dataInputStream  = new DataInputStream(socket.getInputStream());
    }
    public MessageGetter messageGetter ;

    public void addUsersToGroup(ArrayList<String> users,String name) throws IOException {
        dataOutputStream.writeUTF("ADD_MEMBER_TO_GROUP");
        Gson gson = new Gson();
        String s = gson.toJson(users);
        dataOutputStream.writeUTF(s);
        dataOutputStream.writeUTF(name);
    }

    public void addNewPrivateChat(String username) throws IOException, InterruptedException {
        Manage.masterServerDataOutputStream.writeUTF("ADD_PRIVATE_CHAT");
        String data = Manage.masterServerDataInputStream.readUTF();
        Manage.masterServerDataOutputStream.writeUTF(User.getCurrentUser().username);
        Manage.masterServerDataOutputStream.writeUTF(username);
        Thread.sleep(500);
        Socket socket = new Socket("localhost",Integer.parseInt(data));
        new Chat(socket,username,"PRIVATE");
    }
    public void addNewGroupChat(String name) throws IOException, InterruptedException {
        Manage.masterServerDataOutputStream.writeUTF("ADD_GROUP_CHAT");
        Manage.masterServerDataOutputStream.writeUTF(name);
        Manage.masterServerDataOutputStream.writeUTF(User.getCurrentUser().getUsername());
        String data = Manage.masterServerDataInputStream.readUTF();
        Thread.sleep(500);
        Socket socket = new Socket("localhost",Integer.parseInt(data));
        new Chat(socket,name,"GROUP");
    }
    public ArrayList<Message> enterToChat() throws IOException {
        //TODO : the socket of chat must be given
        dataOutputStream.writeUTF("ENTER_CHAT");
        String data = dataInputStream.readUTF();
        ArrayList<Message> messages = Message.getWholeMessagesFromJson(data);
        getMessagesFromServer(dataInputStream);
        return messages;
    }
    public void getMessagesFromServer(DataInputStream dataInputStream) throws IOException {
        messageGetter = new MessageGetter(dataInputStream);
        messageGetter.start();
    }
    public void exitFromChat() throws IOException { //left
        dataOutputStream.writeUTF("EXIT_CHAT");
        MessageGetter.interrupted();
    }
    public void sendMessage(String text) throws IOException {
        Message message = new Message("ali",text,false,new ImageView());
        String data = Message.convertMessageToJson(message);
        dataOutputStream.writeUTF("RECEIVE_MESSAGE");
        dataOutputStream.writeUTF(data);
    }

    public void editMessage(Message newMessage, Message oldMessage) throws IOException {
        String ancientOne = Message.convertMessageToJson(oldMessage);
        String newOne = Message.convertMessageToJson(newMessage);
        dataOutputStream.writeUTF("EDIT_MESSAGE");
        dataOutputStream.writeUTF(ancientOne);
        dataOutputStream.writeUTF(newOne);
    }
    public ArrayList<Message> getAllMessages() throws IOException {
        dataOutputStream.writeUTF("GET_ALL_MESSAGES");
        String data = dataInputStream.readUTF();
        ArrayList<Message> messages = Message.getWholeMessagesFromJson(data);
        return messages;
    }
    public void deleteJustForMe(Message message) throws IOException {
        dataOutputStream.writeUTF("DELETE_JUST_FOR_ME");
        String removedMessage = Message.convertMessageToJson(message);
        dataOutputStream.writeUTF(removedMessage);
    }
}
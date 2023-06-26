package view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Chat;
import model.Manage;
import model.Message;
import model.User;

import javax.print.DocFlavor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;

public class ChatMethods {
    //TODO : chat must be a thread
    public DataInputStream dataInputStream ;
    public DataOutputStream dataOutputStream ;

    public void refreshChats() throws IOException {
        Manage.masterServerDataOutputStream.writeUTF("REFRESH_CHAT");
        String data =  Manage.masterServerDataInputStream.readUTF();
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
        String data = Manage.masterServerDataInputStream.readUTF();
        Thread.sleep(500);
        Socket socket = new Socket("localhost",Integer.parseInt(data));
        new Chat(socket,name,"GROUP");

    }
    public void enterToChat() throws IOException {
        //TODO : the socket of chat must be given
        dataOutputStream.writeUTF("ENTER_CHAT");
        String data = dataInputStream.readUTF();
        ArrayList<Message> messages = Message.getWholeMessagesFromJson(data);
        if (messages == null) {
            //
        }else{
            //
        }
        getMessagesFromServer(dataInputStream);
    }
    public void getMessagesFromServer(DataInputStream dataInputStream) throws IOException {
        messageGetter = new MessageGetter(dataInputStream);
        messageGetter.start();
    }
    public void exitFromChat() throws IOException {
        dataOutputStream.writeUTF("EXIT_CHAT");
        MessageGetter.interrupted();
    }
    public void sendMessage(String text) throws IOException {
        Message message = new Message(User.getCurrentUser().getUsername(),text,false,User.getCurrentUser().getAvatar());
        String data = Message.convertMessageToJson(message);
        dataOutputStream.writeUTF("RECEIVE_MESSAGE");
        dataOutputStream.writeUTF(data);
    }

}
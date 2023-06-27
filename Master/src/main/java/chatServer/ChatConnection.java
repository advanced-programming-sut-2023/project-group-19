package chatServer;

import basicGameModel.User;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import masterServer.Connection;
import masterServer.MasterServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;

public class ChatConnection extends Thread{
    public Socket socket;
    public DataInputStream dataInputStream ;
    public ChatServer chatServer;
    public DataOutputStream dataOutputStream ;
    public ChatConnection(Socket socket,ChatServer chatServer) throws IOException {
        System.out.println("New connection form: chat connection" + socket.getInetAddress() + ":" + socket.getPort());
        this.chatServer = chatServer ;
        chatServer.allSockets.add(socket);
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }
    @Override
    public void run() {
        try {
            while(true) handleCommand();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void handleCommand() throws IOException {
        String typeOfRequest = dataInputStream.readUTF();
        switch (typeOfRequest){
            case "EXIT_CHAT":
                exitChat();
                break;
            case "RECEIVE_MESSAGE" :
                receiveMessage();
                break;
            case "ENTER_CHAT":
                showMessages();
                break;
            case "ADD_MEMBER_TO_GROUP":
                addMemberToGroup();
                break;
            case "EDIT_MESSAGE":
                editMessage();
                break;
            case "GET_ALL_MESSAGES":
                getAllMessages();
                break;
            case "DELETE_JUST_FOR_ME":
                deleteJustForMe();
                break;
        }
    }

    private void addMemberToGroup() throws IOException {
        String data = dataInputStream.readUTF();
        String groupName = dataInputStream.readUTF();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        ArrayList<String> users = gson.fromJson(data,type);
        for(String username : users) {
            Socket socket = Connection.allSockets.get(username);
            Chat chat =  new Chat(groupName,socket.getPort(),"GROUP");
            Connection.chatsMustBeAddedToChatListOfClients.computeIfAbsent(socket, k -> new ArrayList<>());
            Connection.chatsMustBeAddedToChatListOfClients.get(socket).add(chat);
        }
    }

    private void exitChat() throws IOException {
        chatServer.inChatUsers.remove(socket);
    }

    private Socket findChatSocketFromPort(int port){
        for(Socket socket : chatServer.allSockets){
            if(socket.getPort() ==  port) return socket ;
        }
        return null ;
    }

    private void showMessages() throws IOException {
        chatServer.inChatUsers.add(socket);
        dataOutputStream.writeUTF(Message.convertFromJsonToArrayListMessages(chatServer.allMessages));
    }
    private void receiveMessage() throws IOException {
        String data = dataInputStream.readUTF();
        Message message = Message.convertFromJsonToMessage(data);
        chatServer.allMessages.add(message);
        sendMessageToWholeSockets(data);
    }

    private void sendMessageToWholeSockets(String data) throws IOException {
        for(Socket socket : chatServer.inChatUsers){
            System.out.println("Inside loop---> "+data);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(data);
        }
    }
    private void editMessage() throws IOException {
        boolean flag = false;
        String renewMessage;
        String oldMessage = dataInputStream.readUTF();
        String newMessage = dataInputStream.readUTF();
//        System.out.println("Old: "+oldMessage);
//        System.out.println("New: "+newMessage);
        Message ancientOne = Message.convertFromJsonToMessage(oldMessage);
        Message newOne = Message.convertFromJsonToMessage(newMessage);
        for (int i = 0 ; i < chatServer.allMessages.size() ; i++){
            Message message = chatServer.allMessages.get(i);
            if (message.getContent().equals(ancientOne.getContent())
                    && message.getSender().equals(ancientOne.getSender())){
                chatServer.allMessages.remove(message);
                if (message.getContent().length() != 0) {
                    chatServer.allMessages.add(i, newOne);
                }
                newOne.setContent("#"+i+"#"+newOne.getContent());
                renewMessage = Message.convertFromJsonToMessageToString(newOne);
                sendMessageToWholeSockets(renewMessage);
                break;
            }
        }
    }
    private void getAllMessages() throws IOException {
        dataOutputStream.writeUTF(Message.convertFromJsonToArrayListMessages(chatServer.allMessages));
    }

    private void deleteJustForMe() throws IOException {
        boolean flag = false;
        String finalMessageToDelete = "";
        String chosenMessage = dataInputStream.readUTF();
        Message toBeDeleted = Message.convertFromJsonToMessage(chosenMessage);

        for (int i = 0 ; i < chatServer.allMessages.size() ; i++){
            Message message = chatServer.allMessages.get(i);
            if (message.getContent().equals(toBeDeleted.getContent())
                    && message.getSender().equals(toBeDeleted.getSender())) {
                chatServer.allMessages.remove(toBeDeleted);
                if (message.getContent().length() != 0) {
                    chatServer.allMessages.add(i, toBeDeleted);
                }
                toBeDeleted.setContent("#"+toBeDeleted.getSender()+"#"+toBeDeleted.getContent());
                finalMessageToDelete = Message.convertFromJsonToMessageToString(toBeDeleted);
                flag = true;
                break;
            }
        }
        if (flag) sendMessageToWholeSockets(finalMessageToDelete);
    }
}

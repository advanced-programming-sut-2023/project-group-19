package masterServer;

import basicGameModel.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;

import chatServer.Chat;
import chatServer.ChatConnection;
import chatServer.ChatServer;
import chatServer.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import gameServer.GameServer;

import java.util.HashMap;
public class Connection extends Thread{
    public static HashMap<Integer,GameServer> allGames =  new HashMap<>();
    public static HashMap<Integer, ChatServer> allChats = new HashMap<>();
    public static HashMap<String,ArrayList<Chat>> usersSavedChats = new HashMap<>();
    public static HashMap<String,Socket> allSockets = new HashMap<>();
    public static HashMap<Socket,ArrayList<Chat>> chatsMustBeAddedToChatListOfClients = new HashMap<>();


    static  {
        ChatServer chatServer = new ChatServer(MasterServer.chatPort);
        chatServer.start();
        allChats.put(chatServer.port, chatServer);
        MasterServer.chatPort ++ ;
    }

    public Socket socket;
    final DataInputStream dataInputStream;
    final DataOutputStream  dataOutputStream;
    public Connection(Socket socket) throws IOException {
        System.out.println("New connection form connection: " + socket.getInetAddress() + ":" + socket.getPort());
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        //sendUsersArrayListToUsers
        dataOutputStream.writeUTF(User.makeGsonFromUser());
        //
    }
    @Override
    public synchronized void run() {
        while (true) {
            try {
                handleCommand();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void handleCommand() throws IOException {
        String requestType = dataInputStream.readUTF();
        switch (requestType) {
            case "NEW_GAME":
//                handleNewGame(request);
                break;
            case "JOIN_GAME":
//                handlJoinGame(request);
                break;
            case  "CREATE_USER":
                createUser();
                break;
            case "LOGIN_USER":
                addUserToOnlineUsers();
                break;
            case "ADD_MAP":
                addNewMap();
                break;
            case "REFRESH_CHAT":
                refreshChat();
                break;
            case "ADD_PRIVATE_CHAT":
                addNewPrivateChat();
                break;
            case "ADD_GROUP_CHAT":
                addNewGroupChat();
                break;
            case "ADD_NEW_MAP_TO_SERVER":
                addNewMap();
                break;
            case "GET_SAVED_MAPS":
                sendArrayListOfSavedMapsToClient();
                break;
        }
    }



    private void addNewGroupChat() throws IOException {
        String name = dataInputStream.readUTF();
        dataOutputStream.writeUTF(Integer.toString(MasterServer.chatPort));
        ChatServer chatServer = new ChatServer(MasterServer.chatPort);
        chatServer.start();
        allChats.put(MasterServer.chatPort,chatServer);
        Chat chat = new Chat(name,MasterServer.chatPort,"GROUP");
        MasterServer.chatPort ++ ;
    }

    private void refreshChat() throws IOException {
        ArrayList<Chat> chats = chatsMustBeAddedToChatListOfClients.get(socket);
        String data = Chat.convertFromJsonToArrayListMessages(chats);
        dataOutputStream.writeUTF(data);
    }
    public void addGlobalChat() throws IOException {
        dataOutputStream.writeUTF(Integer.toString(MasterServer.chatPort));
        String username = dataInputStream.readUTF();
        ChatServer chatServer = new ChatServer(MasterServer.chatPort);
        chatServer.start();
        allChats.put(MasterServer.chatPort,chatServer);
        MasterServer.chatPort ++ ;
    }
    public void addNewPrivateChat() throws IOException {
        dataOutputStream.writeUTF(Integer.toString(MasterServer.chatPort));
        String ownerName = dataInputStream.readUTF();
        String username = dataInputStream.readUTF();
        ChatServer chatServer = new ChatServer(MasterServer.chatPort);
        int port = chatServer.port ;
        chatServer.start();
        allChats.put(MasterServer.chatPort,chatServer);
        Chat chat = new Chat(username,MasterServer.chatPort,"PV");
        MasterServer.chatPort ++ ;
        addSecondUserChat(username,ownerName);
    }
    private void addSecondUserChat(String username,String ownerName) {
        Socket socket = allSockets.get(username);
        chatsMustBeAddedToChatListOfClients.computeIfAbsent(socket, k -> new ArrayList<>());
        Chat chat = new Chat(ownerName,socket.getPort(),"PV");
        chatsMustBeAddedToChatListOfClients.get(socket).add(chat);
    }
    private void addNewMap() throws IOException {
        String data = dataInputStream.readUTF();
        System.out.println(data);
        ArrayList<SavedObstacles> savedObstacles = Map.convertJsonObstacleToObject(data);
        Map.arrayListArrayListOfObject.add(savedObstacles);
        Map map = Map.buildMap(savedObstacles);
        Map.getSavedMaps().add(map);
    }
    private void sendArrayListOfSavedMapsToClient() throws IOException {
        dataOutputStream.writeUTF(Integer.toString(Map.arrayListArrayListOfObject.size()));
        for(ArrayList<SavedObstacles> arrayList : Map.arrayListArrayListOfObject){
            String data = Map.convertArrayLIstOfMapIntoJsonForm(arrayList);
            dataOutputStream.writeUTF(data);
        }
    }
    private void addUserToOnlineUsers() throws IOException {
        String data = dataInputStream.readUTF();
        User user =  User.makeUserFromGson(data);
        User.onlineUsers.add(user);
        dataOutputStream.writeUTF("login successfully");
    }
    private void createUser() throws IOException {
        String data = dataInputStream.readUTF();
        User user = User.createUserFromGson(data);
        dataOutputStream.writeUTF("Welcome my lord");
        allSockets.put(user.getUsername(), socket);
        usersSavedChats.put(user.getUsername(),new ArrayList<>());
    }

    private void handlJoinGame(MasterRequest request) throws IOException {
        if(allGames.get(request.portOfJoinToGame).lock) return;
        Socket socket = new Socket("localhost", request.portOfJoinToGame);
        User sender = User.getUserByName(request.usernameOfSender);
    }
    private void handleNewGame(MasterRequest request) {
        GameServer gameServer = new GameServer(MasterServer.gamePort,request.playerNumber);
        gameServer.start();
        allGames.put(MasterServer.gamePort,gameServer);
        MasterServer.gamePort ++ ;
    }
}
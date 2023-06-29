package masterServer;

import basicGameModel.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.security.Key;
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

public class Connection extends Thread {

    private ArrayList<Map> maps = new ArrayList<>();
    public static HashMap<Integer, GameServer> allGames = new HashMap<>();
    public static HashMap<Integer, ChatServer> allChats = new HashMap<>();
    public static HashMap<String, ArrayList<Chat>> usersSavedChats = new HashMap<>();

    // key : port of server
    // value : chat server
    public static HashMap<String, Socket> allSockets = new HashMap<>();
    public static HashMap<Socket, ArrayList<Chat>> chatsMustBeAddedToChatListOfClients = new HashMap<>();

    static {
        System.out.println("**");
        ChatServer chatServer = new ChatServer(MasterServer.chatPort);
        chatServer.start();
        allChats.put(chatServer.port, chatServer);
        MasterServer.chatPort++;
    }


    public Socket socket;
    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;

    public Connection(Socket socket) throws IOException {
        System.out.println("New connection from connection: " + socket.getInetAddress() + ":" + socket.getPort());
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        //sendUsersArrayListToUsers
//        dataOutputStream.writeUTF(User.makeGsonFromUser());
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
//                handleJoinGame(request);
                break;
            case "CREATE_USER":
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
            case "GET_CHATS":
                getAllChats();
                break;
            case "SEARCH_USER":
                findUserFromUsername();
                break;
        }
    }

    private void getAllChats() throws IOException {
        String username = dataInputStream.readUTF();
        String data = Chat.convertFromJsonToArrayListMessages(usersSavedChats.get(username));
        dataOutputStream.writeUTF(data);
    }

    private void addNewGroupChat() throws IOException {
        String name = dataInputStream.readUTF();
        String owner = dataInputStream.readUTF();
        dataOutputStream.writeUTF(Integer.toString(MasterServer.chatPort));
        ChatServer chatServer = new ChatServer(MasterServer.chatPort);
        chatServer.start();
        allChats.put(MasterServer.chatPort, chatServer);
        Chat chat = new Chat(name, chatServer.port,    "GROUP");
        addChatToUsersList(owner,chat);
//        Connection.chatsMustBeAddedToChatListOfClients.computeIfAbsent(socket, k -> new ArrayList<>());
//        chatsMustBeAddedToChatListOfClients.get(socket).add(chat);
        MasterServer.chatPort++;
    }

    private void refreshChat() throws IOException {
        String owner = dataInputStream.readUTF();
        ArrayList<Chat> chats = chatsMustBeAddedToChatListOfClients.get(socket);
        if (chats != null)
            addChatToUsersArrayList(owner,chats);
        String data = Chat.convertFromJsonToArrayListMessages(chats);
        dataOutputStream.writeUTF(data);
        chatsMustBeAddedToChatListOfClients.get(socket).clear();
    }

    public void addGlobalChat() throws IOException {
        dataOutputStream.writeUTF(Integer.toString(MasterServer.chatPort));
        String username = dataInputStream.readUTF();
        ChatServer chatServer = new ChatServer(MasterServer.chatPort);
        chatServer.start();
        allChats.put(MasterServer.chatPort, chatServer);
        MasterServer.chatPort++;
    }

    public void addNewPrivateChat() throws IOException {
        dataOutputStream.writeUTF(Integer.toString(MasterServer.chatPort));
        String ownerName = dataInputStream.readUTF();
        System.out.println("Owner Name: "+ownerName);
        String username = dataInputStream.readUTF();
        System.out.println("username : "+username);
        ChatServer chatServer = new ChatServer(MasterServer.chatPort);
        int port = chatServer.port;
        chatServer.start();
        allChats.put(MasterServer.chatPort, chatServer);
        System.out.println("All chats: "+allChats.size());
        for (java.util.Map.Entry<Integer, ChatServer> map : allChats.entrySet()){
            System.out.println(map.getValue().getName()+" "+map.getKey());
        }
        Chat chat = new Chat(username, MasterServer.chatPort, "PV");
        addChatToUsersList(ownerName,chat);
        MasterServer.chatPort++;
        addSecondUserChat(username, ownerName,port);
    }

    private void addSecondUserChat(String username, String ownerName, int port) {
        Socket socket = allSockets.get(username);
        if (chatsMustBeAddedToChatListOfClients.size() != 0){
            System.out.println(" > 0");
        }
        chatsMustBeAddedToChatListOfClients.computeIfAbsent(socket, k -> new ArrayList<>());
        Chat chat = new Chat(ownerName, port, "PV");
        chatsMustBeAddedToChatListOfClients.get(socket).add(chat);
        if (chatsMustBeAddedToChatListOfClients.size() != 0){
            System.out.println("ADDED Successfully");
        }
    }

    private void addChatToUsersList(String ownerName, Chat chat){
        if (usersSavedChats.get(ownerName) == null) {
            usersSavedChats.put(ownerName, new ArrayList<>());
        }
        usersSavedChats.get(ownerName).add(chat);
    }

    private void addChatToUsersArrayList(String ownerName, ArrayList<Chat> chats){
        usersSavedChats.computeIfAbsent(ownerName, k -> new ArrayList<>());
        usersSavedChats.get(ownerName).addAll(chats);
    }

    private void addNewMap() throws IOException {
        String data = dataInputStream.readUTF();
        Map map = Map.convertJsonObstacleToObject(data);
        Map.getSavedMaps().add(map);
    }

    private void addUserToOnlineUsers() throws IOException {
        String data = dataInputStream.readUTF();
        User user = User.makeUserFromGson(data);
        User.onlineUsers.add(user);
        dataOutputStream.writeUTF("login successfully");
        //below line is added for chat test
        allSockets.put(user.getUsername(), socket);
    }

    private void createUser() throws IOException {
        String data = dataInputStream.readUTF();
        User user = User.createUserFromGson(data);
        dataOutputStream.writeUTF("Welcome my lord");
        allSockets.put(user.getUsername(), socket);
        usersSavedChats.put(user.getUsername(), new ArrayList<>());
    }

    private void handleJoinGame(MasterRequest request) throws IOException {
        if (allGames.get(request.portOfJoinToGame).lock) return;
        Socket socket = new Socket("localhost", request.portOfJoinToGame);
        User sender = User.getUserByName(request.usernameOfSender);
        allGames.get(request.portOfJoinToGame).players.add(sender);
        //port
    }

    private void handleNewGame(MasterRequest request) {
        GameServer gameServer = new GameServer(MasterServer.gamePort, request.playerNumber);
        gameServer.start();
        allGames.put(MasterServer.gamePort, gameServer);
        MasterServer.gamePort++;
    }

    private void findUserFromUsername() throws IOException {
        String username = dataInputStream.readUTF();
        for (User user : User.users) {
            if (user.getUsername().equals(username)){
                String data = User.makeGsonFromUser(); //we need to make User to String
                dataOutputStream.writeUTF(data);
                break;
            }
        }
    }
}

// master server
// game server = new Socket(dataOutputstream.readUTF(),loccolhost)
// chat server = new Socket(dataOutputstream.readUTF(),loccolhost)
// json --> data in
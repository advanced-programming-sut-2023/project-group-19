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
import gameServer.GameConnection;
import gameServer.GameRequest;
import gameServer.GameServer;

import java.util.Arrays;
import java.util.HashMap;

public class Connection extends Thread {

    private ArrayList<Map> maps = new ArrayList<>();
    public static HashMap<Integer, GameServer> allGames = new HashMap<>();
    public static ArrayList<GameRequest> allGameRequests = new ArrayList<>();
    public static HashMap<Integer, ChatServer> allChats = new HashMap<>();
    public static HashMap<String, ArrayList<Chat>> usersSavedChats = new HashMap<>();

    // key : port of server
    // value : chat server
    public static HashMap<String, Socket> allSockets = new HashMap<>();
    public static HashMap<Socket, ArrayList<Chat>> chatsMustBeAddedToChatListOfClients = new HashMap<>();

    static {
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
            case "GET_CHATS":
                getAllChats();
                break;
            case "SEARCH_USER":
                findUserFromUsername();
            case "ADD_NEW_GAME_REQUEST":
                addNewGameRequest();
                break;
            case "REFRESH_LOBBY":
                refreshLobby();
                break;
            case "JOIN-GAME":
                addToGame();
                break;
            case "LEAVE-GAME":
                leaveGame();
                break;
            case "PRIVATE_PUBLIC":
                privatePublicDecide();
            case "ADD_NEW_MAP_TO_SERVER":
                addNewMap();
                break;
            case "GET_SAVED_MAPS":
                sendArrayListOfSavedMapsToClient();
                break;
        }
    }

    private void getAllChats() throws IOException {
        String username = dataInputStream.readUTF();
        String data = Chat.convertFromJsonToArrayListMessages(usersSavedChats.get(username));
        dataOutputStream.writeUTF(data);
    }
    public void privatePublicDecide() throws IOException {
        String input = dataInputStream.readUTF();
        String[] split = input.split("#");
        GameRequest gameRequest = findGameRequest(split[0]);
        gameRequest.gameRequestType = split[1];
    }

    public void leaveGame() throws IOException {
        String input = dataInputStream.readUTF();
        String[] split = input.split("#");
        GameRequest gameRequest = findGameRequest(split[0]);
        if (gameRequest.getAdminUsername().equals(split[1])) {
            if (gameRequest.allMembersUserName.size() == 1) {
                allGameRequests.remove(gameRequest);
            } else {
                gameRequest.setAdminUsername(gameRequest.allMembersUserName.get(1));
                gameRequest.allMembersUserName.remove(split[1]);
            }
        } else {
            gameRequest.allMembersUserName.remove(split[1]);
        }
    }

    public void addToGame() throws IOException {
        String input = dataInputStream.readUTF();
        String[] split = input.split("#");
        GameRequest gameRequest = findGameRequest(split[0]);
        if (gameRequest.capacity >= gameRequest.allMembersUserName.size())
            gameRequest.allMembersUserName.add(split[1]);
    }

    public GameRequest findGameRequest(String gameId) {
        for (int h = 0; h < allGameRequests.size(); h++) {
            if (allGameRequests.get(h).getId().equals(gameId)) {
                return allGameRequests.get(h);
            }
        }
        return null;
    }

    public void refreshLobby() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < allGameRequests.size(); i++) {
            stringBuilder.append(allGameRequests.get(i).getAdminUsername() + '#' + allGameRequests.get(i).getId() + '#' + allGameRequests.get(i).getCapacity()
                    + '#' + allGameRequests.get(i).getGameRequestType() + '\n');
            for (int u = 0; u < allGameRequests.get(i).allMembersUserName.size(); u++) {
                stringBuilder.append(allGameRequests.get(i).allMembersUserName.get(u) + '#');
            }
            stringBuilder.append('\n');
        }
        String output = stringBuilder.toString();
        dataOutputStream.writeUTF(output);
    }

    public void addNewGameRequest() throws IOException {
        String request = dataInputStream.readUTF();
        String[] command = request.split("#");
        GameRequest gameRequest = new GameRequest(command[1], Integer.parseInt(command[2]), command[3]);
        gameRequest.adminUsername = command[0];
        gameRequest.allMembersUserName.add(command[0]);
        allGameRequests.add(gameRequest);
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
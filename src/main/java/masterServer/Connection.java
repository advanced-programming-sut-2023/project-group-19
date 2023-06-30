package masterServer;

import basicGameModel.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
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
import gameServer.GameConnection;
import gameServer.GameRequest;
import gameServer.GameServer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class Connection extends Thread {

    private ArrayList<Map> maps = new ArrayList<>();
    public static HashMap<Integer, GameServer> allGames = new HashMap<>();
    public static ArrayList<GameRequest> allGameRequests = new ArrayList<>();
    public static ArrayList<UsersFriend> allFriendshipRequests = new ArrayList<>();
    public static HashMap<Integer, ChatServer> allChats = new HashMap<>();
    public static HashMap<String, ArrayList<Chat>> usersSavedChats = new HashMap<>();

    public static HashMap<Socket,String> addUsersThatMustBeAddedToGame = new HashMap<>();

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

    {
        GameRequest gameRequest = new GameRequest("1",2,"No");
        GameRequest gameRequest2 = new GameRequest("2",2,"No");
        GameRequest gameRequest3 = new GameRequest("3",2,"No");
        allGameRequests.add(gameRequest);
        allGameRequests.add(gameRequest2);
        allGameRequests.add(gameRequest3);
    }


    public Socket socket;
    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;

    public Connection(Socket socket) throws IOException {
        System.out.println("New connection form connection: " + socket.getInetAddress() + ":" + socket.getPort());
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        //sendUsersArrayListToUsers
//        dataOutputStream.writeUTF(User.makeGsonFromUser());makeGsonFromUser;
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
                break;
            case "START_GAME":
                startGame();
                break;
            case "START_GAME_REQUEST":
                getRequestStartGameFromUser();
                break;
            case "ASK_FRIENDSHIP":
                askFriend();
                break;
            case "ACCEPT_FRIENDSHIP" :
                acceptFriend();
                break;
            case "GET_FRIENDSHIP_REQUESTS" :
                getFriendShipRequest();
                break;

        }
    }
    private void startGame() throws IOException {
        String idOfGameRequest = dataInputStream.readUTF();
        ArrayList<String> players ;
        for(GameRequest gameRequest : allGameRequests){
            if(!gameRequest.id.equals(idOfGameRequest)) continue;
            players = gameRequest.allMembersUserName ;
            sendStartGameRequestToAllPlayersIntoGame(players);
            break;
        }
    }
    public void getFriendShipRequest() throws IOException {
        String input = dataInputStream.readUTF();
        UsersFriend usersFriend = null;
        for(int u = 0 ;  u < allFriendshipRequests.size() ; u++){
            if(allFriendshipRequests.get(u).userName.equals(input)){
                usersFriend = allFriendshipRequests.get(u);
                break;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        if(usersFriend != null) {
            for (int j = 0; j < usersFriend.friendRequest.size(); j++) {
                stringBuilder.append(usersFriend.friendRequest.get(j) + '#');
            }
        }
        dataOutputStream.writeUTF(stringBuilder.toString());
    }
    public void askFriend() throws IOException {
        String input = dataInputStream.readUTF();
        System.out.println(input);
        String[] split = input.split("#");
        boolean available = false;
        for(int i = 0 ;  i < allFriendshipRequests.size() ; i++){
            if(allFriendshipRequests.get(i).getUserName().equals(split[1])){
                if(!allFriendshipRequests.get(i).friendRequest.contains(split[0]))
                    allFriendshipRequests.get(i).friendRequest.add(split[0]);
                available = true;
                break;
            }
        }
        if(!available) {
            UsersFriend usersFriend = new UsersFriend(split[1]);
            allFriendshipRequests.add(usersFriend);
            usersFriend.friendRequest.add(split[0]);
        }
    }
    public void acceptFriend() throws IOException {
        String input = dataInputStream.readUTF();
        String[] split = input.split("#");
        for(int i = 0 ;  i < allFriendshipRequests.size() ; i++){
            if(allFriendshipRequests.get(i).getUserName().equals(split[0])){
                allFriendshipRequests.get(i).friendRequest.remove(split[1]);
            }
        }
    }
    private void getRequestStartGameFromUser() throws IOException {
        String portOfGame = addUsersThatMustBeAddedToGame.get(socket);
        if(portOfGame == null) dataOutputStream.writeUTF("null");
        else dataOutputStream.writeUTF(portOfGame);
    }

    private void sendStartGameRequestToAllPlayersIntoGame(ArrayList<String> players) throws IOException {
        GameServer gameServer = new GameServer(MasterServer.gamePort);
        gameServer.start();
        for(String username : players){
            Socket socket = Connection.allSockets.get(username);
            addUsersThatMustBeAddedToGame.put(socket,Integer.toString(MasterServer.gamePort));
        }
        MasterServer.gamePort ++ ;
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
            stringBuilder.append(allGameRequests.get(i).getAdminUsername() + '#'
                    + allGameRequests.get(i).getId() + '#' + allGameRequests.get(i).getCapacity()
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
        Chat chat = new Chat(name, MasterServer.chatPort, "GROUP");
        addChatToUsersList(owner, chat);
        MasterServer.chatPort++;
    }

    private void refreshChat() throws IOException {
        String owner = dataInputStream.readUTF();
        ArrayList<Chat> chats = chatsMustBeAddedToChatListOfClients.get(socket);
        addChatToUsersArrayList(owner, chats);
        String data = Chat.convertFromJsonToArrayListMessages(chats);
        dataOutputStream.writeUTF(data);
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
        String username = dataInputStream.readUTF();
        ChatServer chatServer = new ChatServer(MasterServer.chatPort);
        chatServer.start();
        allChats.put(MasterServer.chatPort, chatServer);
        Chat chat = new Chat(username, MasterServer.chatPort, "PV");
        addChatToUsersList(ownerName, chat);
        MasterServer.chatPort++;
        addSecondUserChat(username, ownerName);
    }

    private void addSecondUserChat(String username, String ownerName) {
        Socket socket = allSockets.get(username);
        chatsMustBeAddedToChatListOfClients.computeIfAbsent(socket, k -> new ArrayList<>());
        Chat chat = new Chat(ownerName, socket.getPort(), "PV");
        chatsMustBeAddedToChatListOfClients.get(socket).add(chat);
    }

    private void addChatToUsersList(String ownerName, Chat chat) {
        if (usersSavedChats.get(ownerName) == null) {
            usersSavedChats.put(ownerName, new ArrayList<>());
        }
        usersSavedChats.get(ownerName).add(chat);
    }

    private void addChatToUsersArrayList(String ownerName, ArrayList<Chat> chats) {
        if (usersSavedChats.get(ownerName) == null) {
            usersSavedChats.put(ownerName, new ArrayList<>());
        }
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
    }

    private void createUser() throws IOException {
        String data = dataInputStream.readUTF();
        User user = User.createUserFromGson(data);
        dataOutputStream.writeUTF("Welcome my lord");
        allSockets.put(user.getUsername(), socket);
        usersSavedChats.put(user.getUsername(), new ArrayList<>());
    }
}

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
    public static HashMap<Integer, ChatServer> allChats = new HashMap<>();
    public static HashMap<String, ArrayList<Chat>> usersSavedChats = new HashMap<>();
    private static  HashMap<Socket,String> onlineUsersHashMap = new HashMap<>();

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
    public Socket socket;
    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;

    public Connection(Socket socket) throws IOException {
        System.out.println("New connection form connection: " + socket.getInetAddress() + ":" + socket.getPort());
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        //sendUsersArrayListToUsers
        //
    }

    @Override
    public synchronized void run() {
        while (true) {
            try {
                handleCommand();
            } catch (IOException e) {
                System.out.println("Connection " + socket.getInetAddress() + ":" + socket.getPort() + " lost!");
                String username =  onlineUsersHashMap.get(socket);
                User.onlineUsers.remove(username);
                break;
            }
        }
    }

    private void handleCommand() throws IOException {
        String requestType = dataInputStream.readUTF();
        switch (requestType) {
            case "CREATE_USER":
                createUser();
                break;
            case "SEND_LIST_OF_USERS":
                sendListOfUsers();
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
            case "EDIT_USER":
                editUser();
                break;
            case "START_GAME_REQUEST":
                getRequestStartGameFromUser();
                break;
            case "GET_USER_FOR_SCOREBOARD":
                getUsersForScoreBoard();
                break;
        }
    }

    private void editUser() throws IOException {
        String data = dataInputStream.readUTF();
        String index = dataInputStream.readUTF();
        User user = User.makeUserFromGson(data);
        System.out.println(index);
        User userOrgUser = User.users.get(Integer.parseInt(index));
        makeConstantUser(userOrgUser ,user);
    }
    private void makeConstantUser(User orgUser , User copy){
        orgUser.username = copy.username ;
        orgUser.password = copy.password;
        orgUser.avatar = copy.avatar;
        orgUser.email =  copy.email ;
        orgUser.highScore = copy.highScore ;
        orgUser.nickname = copy.nickname;
        orgUser.slogan = copy.slogan ;
        orgUser.recoveryQuestion = copy.recoveryQuestion;
        orgUser.recoveryQuestionNumber = copy.recoveryQuestionNumber ;
        orgUser.rank = copy.rank ;
    }

    private void sendListOfUsers() throws IOException {
        System.out.println("send list of users 1");
        System.out.println(User.makeGsonFromUser());
        System.out.println("send list of users" + 2);
        dataOutputStream.writeUTF(User.makeGsonFromUser());
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
    private void getRequestStartGameFromUser() throws IOException {
        String portOfGame = addUsersThatMustBeAddedToGame.get(socket);
        if(portOfGame == null) dataOutputStream.writeUTF("null");
        else dataOutputStream.writeUTF(portOfGame);
    }
    private void getUsersForScoreBoard() throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(User.class, new UserAdaptor());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        ArrayList<User> editedUsers = new ArrayList<>();
        System.out.println(User.users.size());
        for(User user : User.users){
            if(isOnline(user.getUsername())) user.username = "Online - " + user.getUsername();
            else user.username = "Offline - " + user.getUsername();
            editedUsers.add(user);
        }
        String test = gson.toJson(editedUsers);
        dataOutputStream.writeUTF(test);
        for(User user :  User.users){
            user.username = user.username.replaceAll("((Online - )|(Offline - ))","");
        }
    }
//    private ArrayList<User> getArrayListOfClonedUser(ArrayList<User> references) throws IOException {
//        ArrayList<User> clonedUsers = new ArrayList<>();
//        for(User user  : references){
//            User user2 = setConstructorForUser(user);
//            clonedUsers.add(user2);
//        }
//        return clonedUsers ;
//    }
//    private User setConstructorForUser(User reference) throws IOException {
//        User user = new User(reference.getUsername(),reference.password,reference.nickname,
//                reference.email,reference.recoveryQuestion,reference.slogan,
//                reference.recoveryQuestionNumber);
//        return user ;
//    }
    private boolean isOnline(String username){
        for(String string : User.onlineUsers){
            if(username.equals(string)) return true ;
        }
        return false ;
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
        User.onlineUsers.add(user.getUsername());
        dataOutputStream.writeUTF("login successfully");
        onlineUsersHashMap.put(socket,user.username);
    }

    private void createUser() throws IOException {
        String data = dataInputStream.readUTF();
        System.out.println(data);
        User user = User.createUserFromGson(data);
        System.out.println("--");
        System.out.println("into createUser connection" + user.getUsername());
        System.out.println("--");
        allSockets.put(user.getUsername(), socket);
        usersSavedChats.put(user.getUsername(), new ArrayList<>());
    }
}

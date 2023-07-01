package masterServer;

import basicGameModel.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.time.LocalTime;
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
    private static  HashMap<Socket,String> onlineUsersHashMap = new HashMap<>();

    public static HashMap<String,String> lastSeenOfUsers = new HashMap<>();

    public static HashMap<Socket, String> addUsersThatMustBeAddedToGame = new HashMap<>();

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
    }

    @Override
    public synchronized void run() {
        while (true) {
            try {
                handleCommand();
            } catch (IOException e) {
                System.out.println("Connection " + socket.getInetAddress() + ":" + socket.getPort() + " lost!");
                String username =  onlineUsersHashMap.get(socket);
                String time = getTimeNow();
                lastSeenOfUsers.put(username,time);
                User.onlineUsers.remove(username);
                break;
            }
        }
    }
    private String getTimeNow(){
        LocalTime localTime = LocalTime.now();
        String[] list = localTime.toString().split(":");
        return list[0]+":"+list[1];
    }

    private void handleCommand() throws IOException {
        String requestType = dataInputStream.readUTF();
        System.out.println("Type of Req: "+requestType);
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
            case "ADD_NEW_MAP_TO_SERVER":
                addNewMap();
                break;
            case "GET_SAVED_MAPS":
                sendArrayListOfSavedMapsToClient();
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
            case "ACCEPT_FRIENDSHIP":
                acceptFriend();
                break;
            case "GET_FRIENDSHIP_REQUESTS":
                getFriendShipRequest();
                break;
            case "GET_USER_FOR_SCOREBOARD":
                getUsersForScoreBoard();
                break;

        }
    }

    private void startGame() throws IOException {
        String idOfGameRequest = dataInputStream.readUTF();
        ArrayList<String> players;
        for (GameRequest gameRequest : allGameRequests) {
            if (!gameRequest.id.equals(idOfGameRequest)) continue;
            players = gameRequest.allMembersUserName;
            sendStartGameRequestToAllPlayersIntoGame(players);
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

    private void makeConstantUser(User userOrgUser, User user) {
        userOrgUser.username = user.username ;
        userOrgUser.password = user.password;
        userOrgUser.avatar = user.avatar;
        userOrgUser.email =  user.email ;
        userOrgUser.highScore = user.highScore ;
        userOrgUser.nickname = user.nickname;
        userOrgUser.slogan = user.slogan ;
        userOrgUser.recoveryQuestion = user.recoveryQuestion;
        userOrgUser.recoveryQuestionNumber = user.recoveryQuestionNumber ;
        userOrgUser.rank = user.rank ;
    }




    public void getFriendShipRequest() throws IOException {
        String input = dataInputStream.readUTF();
        UsersFriend usersFriend = null;
        for (int u = 0; u < allFriendshipRequests.size(); u++) {
            if (allFriendshipRequests.get(u).userName.equals(input)) {
                usersFriend = allFriendshipRequests.get(u);
                break;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (usersFriend != null) {
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
        for (int i = 0; i < allFriendshipRequests.size(); i++) {
            if (allFriendshipRequests.get(i).getUserName().equals(split[1])) {
                if (!allFriendshipRequests.get(i).friendRequest.contains(split[0]))
                    allFriendshipRequests.get(i).friendRequest.add(split[0]);
                available = true;
                break;
            }
        }
        if (!available) {
            UsersFriend usersFriend = new UsersFriend(split[1]);
            allFriendshipRequests.add(usersFriend);
            usersFriend.friendRequest.add(split[0]);
        }
    }

    public void acceptFriend() throws IOException {
        String input = dataInputStream.readUTF();
        String[] split = input.split("#");
        for (int i = 0; i < allFriendshipRequests.size(); i++) {
            if (allFriendshipRequests.get(i).getUserName().equals(split[0])) {
                allFriendshipRequests.get(i).friendRequest.remove(split[1]);
            }
        }
    }

    private void sendListOfUsers() throws IOException {
        System.out.println("send list of users 1");
        System.out.println(User.makeGsonFromUser());
        System.out.println("send list of users" + 2);
        dataOutputStream.writeUTF(User.makeGsonFromUser());
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

    private void getAllChats() throws IOException {
        String username = dataInputStream.readUTF();
        String data = Chat.convertFromJsonToArrayListMessages(usersSavedChats.get(username));
        dataOutputStream.writeUTF(data);
    }
    private boolean isOnline(String username){
        for(String string : User.onlineUsers){
            if(username.equals(string)) return true ;
        }
        return false ;
    }
    private void getUsersForScoreBoard() throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(User.class, new UserAdaptor());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        ArrayList<User> editedUsers = new ArrayList<>();
        for(User user : User.users){
            if(isOnline(user.getUsername())) user.username = "Online - " + user.getUsername();
            else {
                String time = lastSeenOfUsers.get(user.getUsername()) ;
                if(time == null) time = "";
                user.username =  time + " Offline - " + user.getUsername();
            }
            editedUsers.add(user);
        }
        String test = gson.toJson(editedUsers);
        dataOutputStream.writeUTF(test);
        for(User user :  User.users){
            user.username = user.username.replaceAll("((Online - )|(Offline - )/((\\d+:\\d+) Offline - ))","");
        }
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
        System.out.println("Entered refresh chat");
        String owner = dataInputStream.readUTF();
        System.out.println("Owner: " + owner);
        ArrayList<Chat> chats = chatsMustBeAddedToChatListOfClients.get(socket);
        System.out.println(chats);
//        dataOutputStream.writeUTF(String.valueOf(chats != null && chats.size() != 0));
//        addChatToUsersArrayList(owner, chats);
        if (chats != null)
            addChatToUsersArrayList(owner,chats);
        String data = Chat.convertFromJsonToArrayListMessages(chats);
        System.out.println("data: "+data);
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
        System.out.println("Entered addNewPrivateChat");
        dataOutputStream.writeUTF(Integer.toString(MasterServer.chatPort));
        String ownerName = dataInputStream.readUTF();
        String username = dataInputStream.readUTF();
        ChatServer chatServer = new ChatServer(MasterServer.chatPort);
        int port = chatServer.port;
        chatServer.start();
        allChats.put(MasterServer.chatPort, chatServer);
        Chat chat = new Chat(username, MasterServer.chatPort, "PV");
        addChatToUsersList(ownerName,chat);
        MasterServer.chatPort++;
        addSecondUserChat(username, ownerName,port);
    }

    private void addSecondUserChat(String username, String ownerName, int port) {
        Socket socket = allSockets.get(username);
        System.out.println("Socket: "+socket);
        chatsMustBeAddedToChatListOfClients.computeIfAbsent(socket, k -> new ArrayList<>());
        Chat chat = new Chat(ownerName, port, "PV");
        chatsMustBeAddedToChatListOfClients.get(socket).add(chat);
        System.out.println("Size is: " + chatsMustBeAddedToChatListOfClients.size());
    }

    private void addNewMap() throws IOException {
//        String data = dataInputStream.readUTF();
//        System.out.println(data);
//        ArrayList<SavedObstacles> savedObstacles = Map.convertJsonObstacleToObject(data);
////        System.out.println(savedObstacles.get(0).na);
//        Map.arrayListArrayListOfObject.add(savedObstacles);
//        Map map = Map.buildMap(savedObstacles);
//        Map.getSavedMaps().add(map);
    }

    private void sendArrayListOfSavedMapsToClient() throws IOException {
//        dataOutputStream.writeUTF(Integer.toString(Map.arrayListArrayListOfObject.size()));
//        for (ArrayList<SavedObstacles> arrayList : Map.arrayListArrayListOfObject) {
//            String data = Map.convertArrayLIstOfMapIntoJsonForm(arrayList);
//            System.out.println(data);
//            dataOutputStream.writeUTF(data);
//        }
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


    private void addUserToOnlineUsers() throws IOException {
        String data = dataInputStream.readUTF();
        User user = User.makeUserFromGson(data);
        User.onlineUsers.add(user.getUsername());
        dataOutputStream.writeUTF("login successfully");
        onlineUsersHashMap.put(socket,user.username);
    }

    private void createUser() throws IOException {
        String data = dataInputStream.readUTF();
        User user = User.createUserFromGson(data);
        allSockets.put(user.getUsername(), socket);
        usersSavedChats.put(user.getUsername(), new ArrayList<>());
    }


    private void findUserFromUsername() throws IOException {
        String username = dataInputStream.readUTF();
        for (User user : User.users) {
            if (user.getUsername().equals(username)) {
                String data = User.makeGsonFromUser(); //we need to make User to String
                dataOutputStream.writeUTF(data);
                break;
            }
        }
    }
}

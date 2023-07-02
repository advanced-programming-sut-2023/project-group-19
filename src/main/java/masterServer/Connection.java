package masterServer;

import basicGameModel.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.*;

import basicGameModel.Map;
import chatServer.Chat2;
import chatServer.ChatServer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gameServer.GameRequest;
import gameServer.GameServer;

import javax.swing.text.html.ImageView;

public class Connection extends Thread {

    private ArrayList<Map> maps = new ArrayList<>();
    public static ArrayList<GameRequest> allGameRequests = new ArrayList<>();
    public static ArrayList<UsersFriend> allFriendshipRequests = new ArrayList<>();
    public static ArrayList<GameCommands> allGameCommands = new ArrayList<>();
    public static ArrayList<Chat> allGameChats = new ArrayList<>();
    public static HashMap<Integer, ChatServer> allChats = new HashMap<>();
    public static HashMap<String, ArrayList<Chat2>> usersSavedChats = new HashMap<>();
    private static HashMap<Socket, String> onlineUsersHashMap = new HashMap<>();
    public static HashMap<String, String> lastSeenOfUsers = new HashMap<>();

    public static HashMap<Socket, String> addUsersThatMustBeAddedToGame = new HashMap<>();
    public static HashMap<String, Socket> allSockets = new HashMap<>();
    public static HashMap<Socket, ArrayList<Chat2>> chatsMustBeAddedToChatListOfClients = new HashMap<>();

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
                String username = onlineUsersHashMap.get(socket);
                String time = getTimeNow();
                lastSeenOfUsers.put(username, time);
                User.onlineUsers.remove(username);
                break;
            }
        }
    }

    private String getTimeNow() {
        LocalTime localTime = LocalTime.now();
        String[] list = localTime.toString().split(":");
        return list[0] + ":" + list[1];
    }

    private synchronized void handleCommand() throws IOException {
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
//                addNewMap();
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
            case "ADD_NEW_MAP_TO_SERVER":
//                addNewMap();
                break;
            case "GET_SAVED_MAPS":
//                sendArrayListOfSavedMapsToClient();
                break;
            case "START_GAME":
                startGame();
                break;
            case "ADMIN_START_GAME":

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
            case "SEND_SAVED_CHATS_TO_CLIENT":
                sendSavedChatsToClient();
                break;
            case "MAKE_CHAT_GROUP_FOR_TEAM":
                makeChatGroupForTeam();
                break;
            case "GAME_COMMANDS":
                gameCommands();
                break;
            case "CHAT":
                chat();
                break;
        }
    }

    public void chat() throws IOException {
        String command = dataInputStream.readUTF();
        switch (command) {
            case "CREATE_CHAT":
                createChat();
                break;
            case "CHAT_SIZE" :
                chatSize();
                break;
            case "GET_ALL_CHATS" :
                getUsersChats();
                break;
            case "GET_MESSAGES":
                getMessages();
                break;
            case "SEND_MESSAGES":
                sendMessage();
                break;
            case "DELETE_MESSAGE":
                deleteMessage();
                break;
            case "EDIT_MESSAGE":
                editMessage();
                break;
        }
    }
    public void chatSize() throws IOException {
        String input = dataInputStream.readUTF();
        for(int i = 0 ;  i < allGameChats.size() ; i++){
            if(allGameChats.get(i).chatId.equals(input)){
                dataOutputStream.writeUTF(String.valueOf(allGameChats.get(i).chatMembers.size()));
                return;
            }
        }
    }

    public void createChat() throws IOException {
        String input = dataInputStream.readUTF();
        String[] split = input.split("#");
        for(int g = 0 ; g < allGameChats.size() ; g++){
            if(allGameChats.get(g).getChatId().equals(split[0])){
                return;
            }
        }
        Chat chat = new Chat(split[0]);
        for (int i = 1; i < split.length; i++) {
            chat.chatMembers.add(split[i]);
        }
        allGameChats.add(chat);
    }
    public void getUsersChats() throws IOException {
        String input = dataInputStream.readUTF();
        StringBuilder stringBuilder = new StringBuilder();
        for(int u = 0 ; u < allGameChats.size() ; u++){
            if(allGameChats.get(u).chatMembers.contains(input)){
                stringBuilder.append(allGameChats.get(u).getChatId() + "#");
            }
        }
        dataOutputStream.writeUTF(stringBuilder.toString());
    }

    public void getMessages() throws IOException {
        String input = dataInputStream.readUTF();
        String [] split = input.split("#");
        for (int i = 0; i < allGameChats.size(); i++) {
            if (allGameChats.get(i).getChatId().equals(split[1])) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < allGameChats.get(i).getChatMessages().size(); j++) {
                    Message message = allGameChats.get(i).getChatMessages().get(j);
                    if(!message.sender.equals(split[0]))
                    {
                        message.seen = true;
                    }
                    stringBuilder.append(message.getSender() + '#' + message.getContent() + '#' + message.seen + '#'
                            + message.getAvatar() + '#' + message.getSentTime() + '#' + message.getMessageKey() + '\n');
                }
                dataOutputStream.writeUTF(stringBuilder.toString());
                return;
            }
        }
        dataOutputStream.writeUTF("");
    }
    // chat id / username / message
    public void sendMessage() throws IOException {
        String input = dataInputStream.readUTF();
        String[] split = input.split("#");
        String chatId = split[0];
        Message message = new Message(split[1], split[2], false, split[4]);
        message.setSentTime(split[5]);
        for (int u = 0; u < allGameChats.size(); u++) {
            if (allGameChats.get(u).getChatId().equals(chatId)) {
                message.setMessageKey(chatId + allGameChats.get(u).chatMessages.size());
                allGameChats.get(u).getChatMessages().add(message);
                return;
            }
        }
    }

    public void deleteMessage() throws IOException {
        String input = dataInputStream.readUTF();
        String[] split = input.split("#");
        for (int i = 0; i < allGameChats.size(); i++) {
            if (allGameChats.get(i).getChatId().equals(split[0])) {
                for (int j = 0; j < allGameChats.get(i).getChatMessages().size(); j++) {
                    if (allGameChats.get(i).getChatMessages().get(j).getMessageKey().equals(split[1])) {
                        allGameChats.get(i).getChatMessages().remove(j);
                        return;
                    }
                }
            }
        }

    }

    public void editMessage() throws IOException {
        String input = dataInputStream.readUTF();
        String[] split = input.split("#");
        for (int i = 0; i < allGameChats.size(); i++) {
            if (allGameChats.get(i).getChatId().equals(split[0])) {
                for (int j = 0; j < allGameChats.get(i).getChatMessages().size(); j++) {
                    if (allGameChats.get(i).getChatMessages().get(j).getMessageKey().equals(split[1])) {
                        allGameChats.get(i).getChatMessages().get(j).setContent(split[2]);
                    }
                }
            }
        }
    }







    private void makeChatGroupForTeam() throws IOException {
        ChatServer chatServer = new ChatServer(MasterServer.chatPort);
        chatServer.start();
        allChats.put(MasterServer.chatPort, chatServer);
        dataOutputStream.writeUTF(Integer.toString(MasterServer.chatPort));
        MasterServer.chatPort++;
    }

    private void sendSavedChatsToClient() throws IOException {
        String usernameOfClient = dataInputStream.readUTF();
        ArrayList<Chat2> chats = usersSavedChats.get(usernameOfClient);
        System.out.println(chats == null);
        String outPut;
        if (chats != null) {
            outPut = Chat2.convertFromJsonToArrayListMessages(chats);
        } else outPut = "null";
        dataOutputStream.writeUTF(outPut);
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
        dataOutputStream.writeUTF("stop");
    }

    private void editUser() throws IOException {
        String data = dataInputStream.readUTF();
        String index = dataInputStream.readUTF();
        User user = User.makeUserFromGson(data);
        User userOrgUser = User.users.get(Integer.parseInt(index));
        makeConstantUser(userOrgUser, user);
    }

    private void makeConstantUser(User userOrgUser, User user) {
        userOrgUser.username = user.username;
        userOrgUser.password = user.password;
        userOrgUser.avatar = user.avatar;
        userOrgUser.email = user.email;
        userOrgUser.highScore = user.highScore;
        userOrgUser.nickname = user.nickname;
        userOrgUser.slogan = user.slogan;
        userOrgUser.recoveryQuestion = user.recoveryQuestion;
        userOrgUser.recoveryQuestionNumber = user.recoveryQuestionNumber;
        userOrgUser.rank = user.rank;
    }


    public void gameCommands() throws IOException {
        String command = dataInputStream.readUTF();
        switch (command) {
            case "GAME_BEGIN":
                gameBegin();
                break;
            case "DROP_BUILDING":
                dropBuilding();
                break;
            case "DROP_UNIT":
                dropUnit();
                break;
            case "MOVE_UNIT":
                moveUnit();
                break;
            case "GET_MY_COMMANDS":
                getMyCommands();
                break;
        }
    }

    public void getMyCommands() throws IOException {
        String input = dataInputStream.readUTF();
        for (int i = 0; i < allGameCommands.size(); i++) {
            if (allGameCommands.get(i).getUserName().equals(input)) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int h = 0; h < allGameCommands.get(i).getCommandsToDo().size(); h++) {
                    stringBuilder.append(allGameCommands.get(i).getCommandsToDo().get(h) + '\n');
                }
                dataOutputStream.writeUTF(stringBuilder.toString());
                allGameCommands.get(i).commandsToDo.clear();
                return;
            }
        }
        dataOutputStream.writeUTF("chert");
    }

    public void gameBegin() throws IOException {
        String input = dataInputStream.readUTF();
        GameCommands gameCommands = new GameCommands(input);
        allGameCommands.add(gameCommands);
    }

    public void dropBuilding() throws IOException {
        String input = dataInputStream.readUTF();
        String[] split = input.split("#");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DROP_BUILDING" + '#');
        for (int h = 1; h < split.length; h++) {
            stringBuilder.append(split[h] + '#');
        }
        for (int f = 0; f < allGameCommands.size(); f++) {
            if (!allGameCommands.get(f).getUserName().equals(split[0])) {
                allGameCommands.get(f).commandsToDo.add(stringBuilder.toString());
            }
        }
    }

    public void dropUnit() throws IOException {
        String input = dataInputStream.readUTF();
        String[] split = input.split("#");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DROP_UNIT" + '#');
        for (int h = 1; h < split.length; h++) {
            stringBuilder.append(split[h] + '#');
        }
        for (int f = 0; f < allGameCommands.size(); f++) {
            if (!allGameCommands.get(f).getUserName().equals(split[0])) {
                allGameCommands.get(f).commandsToDo.add(stringBuilder.toString());
            }
        }
    }

    // move unit :
//    moveUnit + # + passingArmy.getxCoordinate() + '#' +passingArmy.getyCoordinate() + '#' +
//    passingArmy.getNames().getName() + '#' +xOfDestination + '#' + yOfDestination
    public void moveUnit() throws IOException {
        String input = dataInputStream.readUTF();
        String[] split = input.split("#");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MOVE_UNIT" + '#');
        for (int h = 1; h < split.length; h++) {
            stringBuilder.append(split[h] + '#');
        }
        for (int f = 0; f < allGameCommands.size(); f++) {
            if (!allGameCommands.get(f).getUserName().equals(split[0])) {
                allGameCommands.get(f).commandsToDo.add(stringBuilder.toString());
            }
        }
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
        String data = User.makeGsonFromUser();
        dataOutputStream.writeUTF(data);
    }

    private void getRequestStartGameFromUser() throws IOException {
        String portOfGame = addUsersThatMustBeAddedToGame.get(socket);
        if (portOfGame == null) dataOutputStream.writeUTF("null");
        else dataOutputStream.writeUTF(portOfGame);
    }

    private void sendStartGameRequestToAllPlayersIntoGame(ArrayList<String> players) throws IOException {
        GameServer gameServer = new GameServer(MasterServer.gamePort);
        gameServer.start();
        for (String username : players) {
            Socket socket = Connection.allSockets.get(username);
            addUsersThatMustBeAddedToGame.put(socket, Integer.toString(MasterServer.gamePort));
        }
        MasterServer.gamePort++;
    }

    private void getAllChats() throws IOException {
        String username = dataInputStream.readUTF();
        String data = Chat2.convertFromJsonToArrayListMessages(usersSavedChats.get(username));
        dataOutputStream.writeUTF(data);
    }

    private boolean isOnline(String username) {
        for (String string : User.onlineUsers) {
            if (username.equals(string)) return true;
        }
        return false;
    }

    private void getUsersForScoreBoard() throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(User.class, new UserAdaptor());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        ArrayList<User> editedUsers = new ArrayList<>();
        for (User user : User.users) {
            if (isOnline(user.getUsername())) user.username = "Online - " + user.getUsername();
            else {
                String time = lastSeenOfUsers.get(user.getUsername());
                if (time == null) time = "";
                user.username = time + " Offline - " + user.getUsername();
            }
            editedUsers.add(user);
        }
        String test = gson.toJson(editedUsers);
        dataOutputStream.writeUTF(test);
        for (User user : User.users) {
            user.username = user.username.replaceAll("((Online - )|(Offline - )/((\\d+:\\d+) Offline - ))", "");
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
        if (gameRequest.capacity == gameRequest.allMembersUserName.size()) {
            gameRequest.startGame = true;
        }
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

    public static int[] second = new int[100];
    public static int[] size = new int[100];

    public void addNewGameRequest() throws IOException {
        String request = dataInputStream.readUTF();
        String[] command = request.split("#");
        GameRequest gameRequest = new GameRequest(command[1], Integer.parseInt(command[2]), command[3]);
        gameRequest.adminUsername = command[0];
        gameRequest.allMembersUserName.add(command[0]);
        allGameRequests.add(gameRequest);
        size[allGameRequests.size() - 1] = 1;
        second[allGameRequests.size() - 1] = 0;
    }

    private void addNewGroupChat() throws IOException {
        String name = dataInputStream.readUTF();
        String owner = dataInputStream.readUTF();
        dataOutputStream.writeUTF(Integer.toString(MasterServer.chatPort));
        ChatServer chatServer = new ChatServer(MasterServer.chatPort);
        chatServer.start();
        allChats.put(MasterServer.chatPort, chatServer);
        Chat2 chat = new Chat2(name, MasterServer.chatPort, "GROUP");
        addChatToUsersList(owner, chat);
        MasterServer.chatPort++;
    }

    private void refreshChat() throws IOException {
        String owner = dataInputStream.readUTF();
        ArrayList<Chat2> chats = chatsMustBeAddedToChatListOfClients.get(socket);
        if (chats != null)
            addChatToUsersArrayList(owner, chats);
        String data = Chat2.convertFromJsonToArrayListMessages(chats);
        dataOutputStream.writeUTF(data);
        if (chats == null) return;
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
        String username = dataInputStream.readUTF();
        ChatServer chatServer = new ChatServer(MasterServer.chatPort);
        int port = chatServer.port;
        chatServer.start();
        allChats.put(MasterServer.chatPort, chatServer);
        Chat2 chat = new Chat2(username, MasterServer.chatPort, "PV");
        saveChatToHashMap(ownerName, chat);
        addChatToUsersList(ownerName, chat);
        MasterServer.chatPort++;
        addSecondUserChat(username, ownerName, port);
    }

    private void addSecondUserChat(String username, String ownerName, int port) {
        Socket socket = allSockets.get(username);
        chatsMustBeAddedToChatListOfClients.computeIfAbsent(socket, k -> new ArrayList<>());
        Chat2 chat = new Chat2(ownerName, port, "PV");
        saveChatToHashMap(username, chat);
        chatsMustBeAddedToChatListOfClients.get(socket).add(chat);
    }

    public static void saveChatToHashMap(String username, Chat2 chat) {
        usersSavedChats.computeIfAbsent(username, k -> new ArrayList<>());
        usersSavedChats.get(username).add(chat);
    }

//    private void addNewMap() throws IOException {
//        String data = dataInputStream.readUTF();
////        System.out.println(data);
//        ArrayList<SavedObstacles> savedObstacles = Map.convertJsonObstacleToObject(data);
////        System.out.println(savedObstacles.get(0).na);
//        Map.arrayListArrayListOfObject.add(savedObstacles);
//        Map map = Map.buildMap(savedObstacles);
//        Map.getSavedMaps().add(map);
//    }

//    private void sendArrayListOfSavedMapsToClient() throws IOException {
//        dataOutputStream.writeUTF(Integer.toString(Map.arrayListArrayListOfObject.size()));
//        for (ArrayList<SavedObstacles> arrayList : Map.arrayListArrayListOfObject) {
//            String data = Map.convertArrayLIstOfMapIntoJsonForm(arrayList);
////            System.out.println(data);
//            dataOutputStream.writeUTF(data);
//        }
//    }

    private void addChatToUsersList(String ownerName, Chat2 chat) {
        if (usersSavedChats.get(ownerName) == null) {
            usersSavedChats.put(ownerName, new ArrayList<>());
        }
        usersSavedChats.get(ownerName).add(chat);
    }

    private void addChatToUsersArrayList(String ownerName, ArrayList<Chat2> chats) {
        if (usersSavedChats.get(ownerName) == null) {
            usersSavedChats.put(ownerName, new ArrayList<>());
        }
        usersSavedChats.get(ownerName).addAll(chats);
    }


    private void addUserToOnlineUsers() throws IOException {
        String data = dataInputStream.readUTF();
        User user = User.makeUserFromGson(data);
        User.onlineUsers.add(user.getUsername());
        allSockets.put(user.getUsername(), socket);
        onlineUsersHashMap.put(socket, user.username);
    }

    private void createUser() throws IOException {
        String data = dataInputStream.readUTF();
        User user = User.createUserFromGson(data);
//        usersSavedChats.put(user.getUsername(), new ArrayList<>());
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
package gameServer;

import java.util.ArrayList;

public class GameRequest {
    public String id;
    public int capacity;
    public String adminUsername;
    public String gameRequestType;
    public ArrayList<String> allMembersUserName = new ArrayList<>();
    public boolean startGame = false;

    public GameRequest(String id, int capacity, String type) {
        this.id = id;
        this.capacity = capacity;
        this.gameRequestType = type;
    }

    public boolean isStartGame() {
        return startGame;
    }

    public void setStartGame(boolean startGame) {
        this.startGame = startGame;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getGameRequestType() {
        return gameRequestType;
    }

    public void setGameRequestType(String gameRequestType) {
        this.gameRequestType = gameRequestType;
    }

    public ArrayList<String> getAllMembersUserName() {
        return allMembersUserName;
    }

    public void setAllMembersUserName(ArrayList<String> allMembersUserName) {
        this.allMembersUserName = allMembersUserName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

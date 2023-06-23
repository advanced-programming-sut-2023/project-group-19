package model;

import java.util.ArrayList;

public class Game {
    private final static ArrayList<User> allPlayers = new ArrayList<User>();
    private String id;
    private int capacity;

    public Game(User gameAdmin, String id) {
        this.getAllPlayers().add(gameAdmin);
        this.id = id;
        this.capacity = 1;
    }

    public ArrayList<User> getAllPlayers() {
        return allPlayers;
    }

    public void addToAllPlayers(User newPlayer) {
        allPlayers.add(newPlayer);
        increaseCapacity();
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

    public void increaseCapacity() {
        this.capacity++;
    }

}

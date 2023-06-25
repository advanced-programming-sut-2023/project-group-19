package model;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Game {


    public ArrayList<User> allPlayers = new ArrayList<>();
    private String id;
    private int capacity;
    private User gameAdmin;
    private ImageView imageView;
    private boolean isPublic;

    public Game(User gameAdmin, String id, boolean isPublic, int capacity) {
        this.gameAdmin = gameAdmin;
        this.id = id;
        this.isPublic = isPublic;
        this.capacity = capacity;
    }

    public ArrayList<User> getAllPlayers() {
        return allPlayers;
    }

    public void addToAllPlayers(User newPlayer) {
        allPlayers.add(newPlayer);
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

    public User getGameAdmin() {
        return gameAdmin;
    }

    public void setGameAdmin(User gameAdmin) {
        this.gameAdmin = gameAdmin;
    }

    public void setImageView (ImageView imageView){
        this.imageView = imageView;
    }
    public ImageView getImageView(){
        return imageView;
    }

    public User isMemberOfGame(String username){
        for (User player : allPlayers) {
            if (player.getUsername().equals(username)){
                return player;
            }
        }
        return null;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}

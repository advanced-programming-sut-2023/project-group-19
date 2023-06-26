package model;

import controller.JsonController;
import javafx.scene.image.Image;
import view.ProfileMenu;


import  javafx.scene.image.ImageView;
import java.awt.*;
import java.io.IOException;
import java.net.CookieStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class User implements Comparable<User> {
    private static ArrayList<String> captchas = new ArrayList<>();
    private static HashMap<Integer, String> securityQuestions = new HashMap<>();
    private static ArrayList<String> randomSlogans = new ArrayList<>();
    public  ArrayList<Game> myGame = new ArrayList<>();
    public ArrayList<User> myFriends = new ArrayList<>();
    public ArrayList<Chat> myChats = new ArrayList<>();

    public static HashMap<Integer, String> getSecurityQuestions() {
        return securityQuestions;
    }

    public static ArrayList<String> getRandomSlogans() {
        return randomSlogans;
    }

    public static ArrayList<User> loginUsers = new ArrayList<>();

    static {
        randomSlogans.add("I march to death...Though I wish it was my own...");
        randomSlogans.add("They think I\'m monster...and I prove them right");

        captchas.add("3855");
        captchas.add("9386");
        captchas.add("8003");
        captchas.add("5771");
    }


    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        User.currentUser = currentUser;
    }

    private static User currentUser;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String recoveryQuestion;
    private String slogan;
    private int recoveryQuestionNumber;
    private int highScore;
    public static ArrayList<User> users = new ArrayList<>();
    private int rank;

    private ImageView avatar = new ImageView();
    {
        Image image = new Image(User.class.getResource("/avatars/5.png").toExternalForm());
        avatar.setImage(image);
        avatar.setFitWidth(100);
        avatar.setFitHeight(100);
    }

    public User(String username, String password, String nickname, String email, String recoveryQuestion, String slogan, int recoveryQuestionNumber) throws IOException {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.recoveryQuestion = recoveryQuestion;
        this.slogan = slogan;
        this.recoveryQuestionNumber = recoveryQuestionNumber;
        users.add(this);
        Collections.sort(users);
        //
        Manage.allUsers.add(this);
        //JsonController.writeIntoFile(Manage.allUsers, "User.json");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRecoveryQuestion() {
        return recoveryQuestion;
    }


    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public int getRecoveryQuestionNumber() {
        return recoveryQuestionNumber;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getRank() {
        return rank;
    }

    public void setRank() {
        this.rank = users.indexOf(this) + 1;
    }

    public static User getUserByName(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }

    public int compareTo(User o) {
        if (highScore != o.getHighScore()) return o.highScore - highScore;
        if (!username.equals(o.username)) return (username.compareTo(o.username));
        return 0;
    }

    public static User getUserByEmail(String email) {
        String changedEmail;
        for (User user : users) {
            changedEmail = user.getEmail().toLowerCase();
            if (changedEmail.equals(email)) return user;
        }
        return null;
    }
    public static ArrayList<String> getCaptchas(){
        return captchas;
    }

    public ImageView getAvatar(){
        return avatar ;
    }
    public void setAvatar(ImageView avatar){
        this.avatar = avatar ;
    }

    public ArrayList<Game> getMyGameList() {
        return myGame;
    }

    public void addToMyGameList(Game game) {
        myGame.add(game);
    }

    public ArrayList<User> getMyFriends() {
        return myFriends;
    }

    public void addToMyFriends(User newFriend) {
        myFriends.add(newFriend);
    }

    public ArrayList<Chat> getChats() {
        return myChats;
    }

}


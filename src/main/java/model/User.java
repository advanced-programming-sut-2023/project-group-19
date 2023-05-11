package model;

import controller.JsonController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import model.Manage;

public class User implements Comparable<User> {
    private static HashMap<Integer, String> securityQuestions = new HashMap<>();
    private static ArrayList<String> randomSlogans = new ArrayList<>();

    public static HashMap<Integer, String> getSecurityQuestions() {
        return securityQuestions;
    }

    public static ArrayList<String> getRandomSlogans() {
        return randomSlogans;
    }

    public static ArrayList<User> loginUsers = new ArrayList<>();

    public static ArrayList<User> getUsers() {
        return users;
    }

    static {
        securityQuestions.put(1, "What is my father\'s name?");
        securityQuestions.put(2, "What was my first pet\'s name?");
        securityQuestions.put(3, "What is my mother\'s last name?");
        //
        randomSlogans.add("I march to death...Though I wish it was my own...");
        randomSlogans.add("They think I\'m monster...and I prove them right");
        //TODO
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

    public static ArrayList<User> getColone() {
        return new ArrayList<>(users);
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
        JsonController.writeIntoFile(Manage.allUsers, "User.json");
    }

    public void addUserToAllUsersArrayList(User user) {
        Manage.allUsers.add(user);
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

    public void setRecoveryQuestion(String recoveryQuestion) {
        this.recoveryQuestion = recoveryQuestion;
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

    public void setRecoveryQuestionNumber(int recoveryQuestionNumber) {
        this.recoveryQuestionNumber = recoveryQuestionNumber;
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
}

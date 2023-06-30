package basicGameModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;


public class User implements Comparable<User> {
    public static ArrayList<User> users = new ArrayList<>();

    public static ArrayList<String> onlineUsers = new ArrayList<>();
    public String username;
    public String password;
    public String nickname;
    public String email;
    public String recoveryQuestion;
    public String slogan;
    public int recoveryQuestionNumber;
    public int highScore;
    public int rank;

    public String avatar ;

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
    public User(){

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


    public synchronized String getSlogan() {
        return slogan;
    }

    public synchronized void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public synchronized int getRecoveryQuestionNumber() {
        return recoveryQuestionNumber;
    }

    public synchronized int getHighScore() {
        return highScore;
    }

    public synchronized void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public synchronized int getRank() {
        return rank;
    }

    public synchronized void setRank() {
        this.rank = users.indexOf(this) + 1;
    }

    public synchronized static User getUserByName(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }

    public synchronized int compareTo(User o) {
        if (highScore != o.getHighScore()) return o.highScore - highScore;
        if (!username.equals(o.username)) return (username.compareTo(o.username));
        return 0;
    }

    public synchronized static User getUserByEmail(String email) {
        String changedEmail;
        for (User user : users) {
            changedEmail = user.getEmail().toLowerCase();
            if (changedEmail.equals(email)) return user;
        }
        return null;
    }

    public synchronized String getAvatar() {
        return avatar;
    }

    public synchronized static User createUserFromGson(String data){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(User.class, new UserAdaptor());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Type type = new TypeToken<User>(){}.getType();
        User user = gson.fromJson(data,type);
        users.add(user);
        return user;
    }
    public synchronized static User makeUserFromGson(String data){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(User.class, new UserAdaptor());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Type type = new TypeToken<User>(){}.getType();
        User user = gson.fromJson(data,type);
        return user ;
    }
    public synchronized static String makeGsonFromUser(){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(User.class, new UserAdaptor());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String test = gson.toJson(users);
        return test ;
    }
    //    public ImageView getAvatar(){
//        return avatar ;
//    }
//    public void setAvatar(ImageView avatar){
//        this.avatar = avatar ;
//    }
}

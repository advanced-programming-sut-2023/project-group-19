package model.packets;

import javafx.scene.image.ImageView;
import model.User;

import java.util.ArrayList;

public class UserPacket {

    public String username = "ali";
    public String password = "ali";
    public String nickname = "ali";
    public String email = "ali";
    public String recoveryQuestion = "ali";
    public String slogan = "ali";
    public int recoveryQuestionNumber = 2;
    public int highScore = 2 ;
    public int rank = 2;
    public String avatar = "ali";

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getRecoveryQuestion() {
        return recoveryQuestion;
    }

    public String getSlogan() {
        return slogan;
    }

    public int getRecoveryQuestionNumber() {
        return recoveryQuestionNumber;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getRank() {
        return rank;
    }

    public String getAvatar() {
        return avatar;
    }
}

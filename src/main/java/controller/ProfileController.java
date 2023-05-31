package controller;

import model.Manage;
import model.User;
import view.Messages.ProfileMenuMessage;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

public class ProfileController {

    public static String changingFields(String username, String email, String nickname, String slogan) {
        User user = User.getCurrentUser();
        StringBuilder stringBuilder = new StringBuilder();
        if(!username.equals(user.getUsername())) stringBuilder.append("username \n");
        if(!email.equals(user.getEmail())) stringBuilder.append("email \n");
        if(!nickname.equals(user.getNickname())) stringBuilder.append("nickname \n");
        if(!slogan.equals(user.getSlogan())) stringBuilder.append("slogan \n");
        return stringBuilder.toString();
    }

    public void changeSlogan(String text){
        User.getCurrentUser().setSlogan(text);
    }
    public void changeEmail(String text){
        User.getCurrentUser().setEmail(text);
    }
    public void changeUsername(String text){
        User.getCurrentUser().setUsername(text);
    }
    public void changeNickname(String text){
        User.getCurrentUser().setNickname(text);
    }
    public void changePassword(String text){
        User.getCurrentUser().setPassword(text);
    }
}

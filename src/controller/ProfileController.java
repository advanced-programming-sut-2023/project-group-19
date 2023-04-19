package controller;

import model.User;
import view.Messages.ProfileMenuMessage;

public class ProfileController {
    public static ProfileMenuMessage changeUsername(String username){
        User user = User.getCurrentUser();
        if(!username.matches(".*[A-Za-z0-9_].*")) return ProfileMenuMessage.INVALID_FORM_USERNAME;
        user.setUsername(username);
        return ProfileMenuMessage.SUCCESS ;
    }
    public static ProfileMenuMessage changeNickname(String nickname){
        User user = User.getCurrentUser();
        user.setNickname(nickname);
        return ProfileMenuMessage.SUCCESS ;
    }
    public static ProfileMenuMessage changingPasswordErrorHandelling(String oldPassword , String newPassword){
        User user = User.getCurrentUser();
        if(!user.getPassword().equals(oldPassword)) return ProfileMenuMessage.INCORRECT_PASSWORD ;
        if(oldPassword.equals(newPassword)) return ProfileMenuMessage.SIMILAR_PASSWORD ;
        if(!newPassword.matches(".*[a-z].*")) return ProfileMenuMessage.WEAK_PASSWORD_FOR_LOWERCASE;
        if(!newPassword.matches(".*[A-Z].*")) return ProfileMenuMessage.WEAK_PASSWORD_FOR_UPPERCASE;
        if(!newPassword.matches(".*[0-9].*")) return ProfileMenuMessage.WEAK_PASSWORD_FOR_NUMBER;
        if(newPassword.length() < 6) return ProfileMenuMessage.WEAK_PASSWORD_FOR_LENGTH;
        return ProfileMenuMessage.SUCCESS;
    }
    public static ProfileMenuMessage changeSlogan(String slogan){
        User user = User.getCurrentUser();
        user.setSlogan(slogan);
        return ProfileMenuMessage.SUCCESS;
    }
    public static ProfileMenuMessage changePassword(String newPassword){
        User user = User.getCurrentUser();
        user.setPassword(newPassword);
        return ProfileMenuMessage.SUCCESS;
    }
    public static ProfileMenuMessage changeEmail(String email){
        if(!email.matches("[A-Za-z0-9\\.]+@[A-Za-z0-9]*\\.+[A-Za-z0-9\\.]*")) return ProfileMenuMessage.INVALID_FORM_EMAIL ;
        User user = User.getCurrentUser();
        user.setEmail(email);
        return ProfileMenuMessage.SUCCESS ;


    }

    public static int showHighScore(){
        return User.getCurrentUser().getHighScore();
    }
    public static int showRank(){
        return User.getCurrentUser().getRank();
    }
    public static String showSlogan(){
        String slogan = User.getCurrentUser().getSlogan();
        if(slogan != null) return User.getCurrentUser().getSlogan();
        else return "Slogan is empty!";
    }
    public static String showDisplay(){
        String slogan ;
        User user = User.getCurrentUser();
        if(user.getSlogan() == null) slogan = "empty";
        else slogan = user.getSlogan();
        String text ;
        text = "username: " + user.getUsername() +"\n" +
                "password: " + user.getPassword() +"\n" +
                "nickname: " + user.getNickname() +"\n" +
                "email: " + user.getEmail() + "\n" +
                "slogan: " + slogan + "\n" +
                "high score: " + user.getHighScore();
        return text;
    }
}

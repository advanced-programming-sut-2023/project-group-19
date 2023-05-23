package controller;

import model.Manage;
import model.User;
import view.Messages.ProfileMenuMessage;
import view.Messages.RegisterMessages;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

public class ProfileController {

    public static String removeSlogan() throws IOException {
        User user = User.getCurrentUser();
        user.setSlogan("empty");
        JsonController.writeIntoFile(Manage.allUsers, "User.json");
        changeFiedsOfCurrentUser(user);
        return "removing slogan is successfully";
    }

    public static void changeFiedsOfCurrentUser(User user) throws IOException {
        JsonController.readDataFile("LoggedInUser.json");
        if (JsonController.saveLoggedInUserFileData() == null) return;
        JsonController.writeIntoFile(user, "LoggedInUser.json");
    }

    public static ProfileMenuMessage changeUsername(String username) throws IOException {
        if (User.getUserByName(username) != null) return ProfileMenuMessage.REPEATED;
        User user = User.getCurrentUser();
        if (!username.matches(".*[A-Za-z0-9_].*")) return ProfileMenuMessage.INVALID_FORM_USERNAME;
        user.setUsername(username);
        JsonController.writeIntoFile(User.users, "User.json");
        changeFiedsOfCurrentUser(user);
        Collections.sort(User.users);
        return ProfileMenuMessage.SUCCESS;
    }

    private static String changeTextIwithoutCot(String text) {
        if (text.charAt(0) == '\"' && text.charAt(text.length() - 1) == '\"') {
            text = text.replaceAll("\"", "");
        }
        return text;
    }

    public static ProfileMenuMessage changeNickname(String nickname) throws IOException {
        nickname = changeTextIwithoutCot(nickname);
        User user = User.getCurrentUser();
        user.setNickname(nickname);
        JsonController.writeIntoFile(User.users, "User.json");
        changeFiedsOfCurrentUser(user);
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changingPasswordErrorHandelling(String oldPassword, String newPassword) {
        oldPassword = changeTextIwithoutCot(oldPassword);
        newPassword = changeTextIwithoutCot(newPassword);
        User user = User.getCurrentUser();
        if (!user.getPassword().equals(getHashCode(oldPassword))) return ProfileMenuMessage.INCORRECT_PASSWORD;
        if (oldPassword.equals(newPassword)) return ProfileMenuMessage.SIMILAR_PASSWORD;
        if (!newPassword.matches(".*[a-z].*")) return ProfileMenuMessage.WEAK_PASSWORD_FOR_LOWERCASE;
        if (!newPassword.matches(".*[A-Z].*")) return ProfileMenuMessage.WEAK_PASSWORD_FOR_UPPERCASE;
        if (!newPassword.matches(".*[\\W\\_].*"))
            return ProfileMenuMessage.WEAK_PASSWORD_FOR_NOTHING_CHARS_EXCEPT_ALPHABETICAL;
        if (!newPassword.matches(".*[0-9].*")) return ProfileMenuMessage.WEAK_PASSWORD_FOR_NUMBER;
        if (newPassword.length() < 6) return ProfileMenuMessage.WEAK_PASSWORD_FOR_LENGTH;
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changeSlogan(String slogan) throws IOException {
        slogan = changeTextIwithoutCot(slogan);
        User user = User.getCurrentUser();
        user.setSlogan(slogan);
        JsonController.writeIntoFile(User.users, "User.json");
        changeFiedsOfCurrentUser(user);
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changePassword(String newPassword) throws IOException {
        newPassword = changeTextIwithoutCot(newPassword);
        User user = User.getCurrentUser();
        user.setPassword(getHashCode(newPassword));
        JsonController.writeIntoFile(User.users, "User.json");
        changeFiedsOfCurrentUser(user);
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changeEmail(String email) throws IOException {
        String newEmail = email.toLowerCase();
        if (User.getUserByEmail(newEmail) != null) return ProfileMenuMessage.REPEATED;
        if (!email.matches("[A-Za-z0-9\\.]+@[A-Za-z0-9]*\\.+[A-Za-z0-9\\.]*"))
            return ProfileMenuMessage.INVALID_FORM_EMAIL;
        User user = User.getCurrentUser();
        user.setEmail(email);
        JsonController.writeIntoFile(User.users, "User.json");
        changeFiedsOfCurrentUser(user);
        return ProfileMenuMessage.SUCCESS;
    }

    public static int showHighScore() {
        return User.getCurrentUser().getHighScore();
    }

    public static int showRank() throws IOException {
        User user = User.getCurrentUser();
        User.getCurrentUser().setRank();
        int rank = user.getRank();
        JsonController.writeIntoFile(User.users, "User.json");
        changeFiedsOfCurrentUser(user);
        return rank;
    }

    public static String showSlogan() {
        String slogan = User.getCurrentUser().getSlogan();
        if (slogan.equals("empty")) return "Slogan is empty!";
        else return slogan;
    }

    public static String showDisplay() throws IOException {
        String slogan;
        User user = User.getCurrentUser();
        User.getCurrentUser().setRank();
        JsonController.writeIntoFile(User.users, "User.json");
        changeFiedsOfCurrentUser(user);
        if (user.getSlogan() == null) slogan = "empty";
        else slogan = user.getSlogan();
        String text;
        text = "username: " + user.getUsername() + "\n" +
                "nickname: " + user.getNickname() + "\n" +
                "email: " + user.getEmail() + "\n" +
                "slogan: " + slogan + "\n" +
                "high score: " + user.getHighScore() + "\n" +
                "rank: " + user.getRank();
        return text;
    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

    public static String getHashCode(String text) {
        try {
            return toHexString(getSHA(text));
        } catch (NoSuchAlgorithmException e) {
//            System.out.println("Exception thrown for incorrect algorithm: " + e);
        }
        return null;
    }
}

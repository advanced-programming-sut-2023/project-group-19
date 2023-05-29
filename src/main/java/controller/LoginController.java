package controller;

import model.*;

import model.User;
import view.Messages.RegisterMessages;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class LoginController {

    public static RegisterMessages checkUsername(String username){
        if (User.getUserByName(username) != null) return RegisterMessages.USERNAME_REPEATED;
        if (!username.matches("[A-Za-z0-9_ ]+")) return RegisterMessages.INCORRECT_FORM_OF_USERNAME;
        return RegisterMessages.SUCCESS ;
    }
    public static RegisterMessages  checkPassword(String password){
        if (!password.matches(".*[a-z].*")) return RegisterMessages.WEAK_PASSWORD_FOR_LOWERCASE;
        if (!password.matches(".*[A-Z].*")) return RegisterMessages.WEAK_PASSWORD_FOR_UPPERCASE;
        if (!password.matches(".*[0-9].*")) return RegisterMessages.WEAK_PASSWORD_FOR_NUMBER;
        if (!password.matches(".*[\\W\\_].*"))
            return RegisterMessages.WEAK_PASSWORD_FOR_NOTHING_CHARS_EXCEPT_ALPHABETICAL;
        if (password.length() < 6) return RegisterMessages.WEAK_PASSWORD_FOR_LENGTH;
        return RegisterMessages.SUCCESS ;
    }

    public static RegisterMessages checkErrorForRegister(String username, String password, String confirmPassword,
                                                         String email, String nickname, String slogan) {
        if (slogan == null || username == null || password == null || email == null || nickname == null || (!password.equals("random") && confirmPassword == null)) {
            return RegisterMessages.EMPTY_FIELD;
        }
        slogan = changeTextIwithoutCot(slogan);
        if (User.getUserByName(username) != null) return RegisterMessages.USERNAME_REPEATED;
        if (!username.matches("[A-Za-z0-9_ ]+")) return RegisterMessages.INCORRECT_FORM_OF_USERNAME;
        if (slogan.equals("random")) return RegisterMessages.GET_RANDOM_SLOGANS;
        if (password.equals("random")) return RegisterMessages.GET_RANDOM_PASSWORD;
        password = changeTextIwithoutCot(password);
        confirmPassword = changeTextIwithoutCot(confirmPassword);
        if (!password.matches(".*[a-z].*")) return RegisterMessages.WEAK_PASSWORD_FOR_LOWERCASE;
        if (!password.matches(".*[A-Z].*")) return RegisterMessages.WEAK_PASSWORD_FOR_UPPERCASE;
        if (!password.matches(".*[0-9].*")) return RegisterMessages.WEAK_PASSWORD_FOR_NUMBER;
        if (!password.matches(".*[\\W\\_].*"))
            return RegisterMessages.WEAK_PASSWORD_FOR_NOTHING_CHARS_EXCEPT_ALPHABETICAL;
        if (password.length() < 6) return RegisterMessages.WEAK_PASSWORD_FOR_LENGTH;
        if (!password.equals(confirmPassword)) return RegisterMessages.NOT_SIMILAR_PASSWORD;
        String changedEmail = email.toLowerCase();
        if (User.getUserByEmail(changedEmail) != null) return RegisterMessages.REPEATED_EMAIL;
        if (!email.matches("[A-Za-z0-9\\.]+@[A-Za-z0-9]+\\.+[A-Za-z0-9\\.]+"))
            return RegisterMessages.INVALID_FORM_EMAIL;
        return RegisterMessages.SUCCESS;
    }

    public static RegisterMessages changePassword(User user, String password) throws IOException {
        password = changeTextIwithoutCot(password);
        if (!password.matches(".*[a-z].*")) return RegisterMessages.WEAK_PASSWORD_FOR_LOWERCASE;
        if (!password.matches(".*[\\W\\_].*"))
            return RegisterMessages.WEAK_PASSWORD_FOR_NOTHING_CHARS_EXCEPT_ALPHABETICAL;
        if (!password.matches(".*[A-Z].*")) return RegisterMessages.WEAK_PASSWORD_FOR_UPPERCASE;
        if (!password.matches(".*[0-9].*")) return RegisterMessages.WEAK_PASSWORD_FOR_NUMBER;
        if (password.length() < 6) return RegisterMessages.WEAK_PASSWORD_FOR_LENGTH;
        user.setPassword(getHashCode(password));
        JsonController.writeIntoFile(Manage.allUsers, "User.json");
        return RegisterMessages.SUCCESS;
    }

    private static String changeTextIwithoutCot(String text) {
        if (text.charAt(0) == '\"' && text.charAt(text.length() - 1) == '\"') {
            text = text.replaceAll("\"", "");
        }
        return text;
    }

    public static void register(String username, String password, String nickname, String email, String answeroFSecQuestion
            , String slogan, String numberOfSecQuesion) throws IOException {
        password = changeTextIwithoutCot(password);
        answeroFSecQuestion = changeTextIwithoutCot(answeroFSecQuestion);
        slogan = changeTextIwithoutCot(slogan);
        nickname = changeTextIwithoutCot(nickname);
        String newPassword = getHashCode(password);
        new User(username, newPassword, nickname, email, answeroFSecQuestion, slogan, Integer.parseInt(numberOfSecQuesion));
    }

    public static RegisterMessages checkSecurityAsks(int number, String answer, String confirmAnswer) {
        if (answer == null || confirmAnswer == null || number < 1 || number > 3)
            return RegisterMessages.TRY_ANOTHER_SEC_ASK;
        answer = checkIfRepetedUserName(answer);
        confirmAnswer = checkIfRepetedUserName(confirmAnswer);
        if (answer.equals(confirmAnswer)) return RegisterMessages.IS_OK_ASKS;
        else return RegisterMessages.TRY_ANOTHER_SEC_ASK;
    }

    public static String checkIfRepetedUserName(String username) {
        if (User.getUserByName(username) != null) username = makeUserNameForUser(username);
        return username;
    }

    public static String isLoggedUser(String username) throws IOException {
        User user;
        if ((user = User.getUserByName(username)) == null) return "this user is not exist!";
        JsonController.writeIntoFile(user, "LoggedInUser.json");
        return "your username for next login is saved!";
    }

    public static RegisterMessages loginUser(String username, String password) {
        password = changeTextIwithoutCot(password);
        User user;
        if ((user = User.getUserByName(username)) == null) return RegisterMessages.NOT_EXIST_USERNAME;
        if (!user.getPassword().equals(getHashCode(password))) return RegisterMessages.NOT_SIMILAR_PASSWORD;
        User.setCurrentUser(user);
        User.loginUsers.add(user);
        return RegisterMessages.SUCCESS;
    }

    public static String makeUserNameForUser(String username) {
        Random rand = new Random();
        int int_random = rand.nextInt(500000);
        return (username + int_random);
    }

    public static String generateRandomPassword() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String number = "1234567890";
        String lowercase = alphabet.toLowerCase();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 3;
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
            randomChar = lowercase.charAt(index);
            sb.append(randomChar);
            index = random.nextInt(10);
            randomChar = number.charAt(index);
            sb.append(randomChar);
        }
        sb.append("#");
        return sb.toString();
    }

    public static String getRandomSlogan() {
        int size = User.getRandomSlogans().size();
        Random random = new Random();
        int index = random.nextInt(size);
        return (User.getRandomSlogans().get(index));
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

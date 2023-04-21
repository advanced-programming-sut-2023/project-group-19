package controller;
import model.*;

import model.User;
import view.Commands.LoginAndRegisterCommands;
import view.Messages.RegisterMessages;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random ;

import static controller.JsonController.saveAllUsersFileData;

public class LoginController {
//    static {
//        saveAllUsersFileData();
//    }
    public static RegisterMessages checkErrorForRegister(String username , String password , String confirmPassword ,
                                                         String email , String nickname , String slogan){
        if(slogan == null || username == null || password == null || email == null || nickname == null || (!password.equals("random") && confirmPassword == null) ){
            return RegisterMessages.EMPTY_FIELD;
        }
        if(User.getUserByName(username) != null) return RegisterMessages.USERNAME_REPETED ;
        if(!username.matches(".*[A-Za-z0-9_].*")) return RegisterMessages.INCORRECT_FORM_OF_USERNAME;
        if(slogan.equals("random")) return RegisterMessages.GET_RANDOM_SLOGANS;
        if(password.equals("random")) return RegisterMessages.GET_RANDOM_PASSWORD ;
        if(!password.matches(".*[a-z].*")) return RegisterMessages.WEAK_PASSWORD_FOR_LOWERCASE;
        if(!password.matches(".*[A-Z].*")) return RegisterMessages.WEAK_PASSWORD_FOR_UPPERCASE;
        if(!password.matches(".*[0-9].*")) return RegisterMessages.WEAK_PASSWORD_FOR_NUMBER;
        if(!password.matches(".*[\\W\\_].*")) return RegisterMessages.WEAK_PASSWORD_FOR_NOTHING_CHARS_EXEPT_ALPHABETICAL;
        if(password.length() < 6) return RegisterMessages.WEAK_PASSWORD_FOR_LENGTH;
        if(!password.equals(confirmPassword)) return RegisterMessages.NOT_SIMILAR_PASSWORD ;
        String changedEmail = email.toLowerCase() ;
        if(User.getUserByEmail(changedEmail) != null) return RegisterMessages.REPETED_EMAIL;
        if(!email.matches("[A-Za-z0-9\\.]+@[A-Za-z0-9]*\\.+[A-Za-z0-9\\.]*")) return RegisterMessages.INVALID_FORM_EMAIL ;
        return RegisterMessages.SUCCESS ;
    }
    public static RegisterMessages changePassword(User user , String password){
        if(!password.matches(".*[a-z].*")) return RegisterMessages.WEAK_PASSWORD_FOR_LOWERCASE;
        if(!password.matches(".*[\\W\\_].*")) return RegisterMessages.WEAK_PASSWORD_FOR_NOTHING_CHARS_EXEPT_ALPHABETICAL;
        if(!password.matches(".*[A-Z].*")) return RegisterMessages.WEAK_PASSWORD_FOR_UPPERCASE;
        if(!password.matches(".*[0-9].*")) return RegisterMessages.WEAK_PASSWORD_FOR_NUMBER;
        if(password.length() < 6) return RegisterMessages.WEAK_PASSWORD_FOR_LENGTH;
        user.setPassword(password);
        return RegisterMessages.SUCCESS;
    }
    public static void register(String username , String password , String nickname , String email , String answeroFSecQuestion
            , String slogan , String numberOfSecQuesion) throws IOException {
        String newPassword = getHashCode(password);
        User user = new User(username,newPassword,nickname,email,answeroFSecQuestion,slogan,Integer.parseInt(numberOfSecQuesion));
        user.setPassword(password);
//        user.addUserToAllUsersArrayList(user);
    }
    public static RegisterMessages checkSecurityAsks(int number , String answer , String confirmAnswer){
        if(answer == null || confirmAnswer == null || number < 0 || number > 3) return RegisterMessages.TRY_ANOTHER_SEC_ASK;
        if(answer.equals(confirmAnswer)) return RegisterMessages.IS_OK_ASKS;
        else return RegisterMessages.TRY_ANOTHER_SEC_ASK;
    }
    public static String checkIfRepetedUserName(String username){
        if(User.getUserByName(username) != null) username = makeUserNameForUser(username);
        return username ;
    }
    public static String isLoggedUser(String username) throws IOException {
        User user ;
        if((user = User.getUserByName(username)) == null ) return "this user is not exist!";
        String password = user.getPassword();
        String newPassword = getHashCode(password);
        user.setPassword(newPassword);
        JsonController.writeIntoFile(user,"LoggedInUser.json");
        user.setPassword(password);
        return "your username for next login is saved!";
    }
    public static RegisterMessages loginUser(String username , String password){
        User user ;
        if((user = User.getUserByName(username)) == null) return RegisterMessages.NOT_EXIST_USERNAME ;
        System.out.println(user.getPassword());
        if(!user.getPassword().equals(password)) return RegisterMessages.NOT_SIMILAR_PASSWORD ;
        User.setCurrentUser(user);
        return RegisterMessages.SUCCESS ;
    }
    public static String makeUserNameForUser(String username){
        Random rand = new Random();
        int int_random = rand.nextInt(500000);
        return (username + int_random);
    }
    public static String generateRandomPassword(){
        // create a string of all characters
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String number = "1234567890";
        String lowercase = alphabet.toLowerCase();

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 3;

        for(int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length());

            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);

            // append the character to string builder
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
    public static String getRandomSlogan(){
        int size = User.getRandomSlogans().size();
        Random random = new Random();
        int index = random.nextInt(size - 1);
        return (User.getRandomSlogans().get(index));
    }
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    // Driver code
    public static String  getHashCode(String text)
    {
        try
        {
            return toHexString(getSHA(text));
        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
        }
        return null ;
    }
}

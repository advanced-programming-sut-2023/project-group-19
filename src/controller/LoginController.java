package controller;
import model.*;

import model.User;
import view.Commands.LoginAndRegisterCommands;
import view.Messages.RegisterMessages;
import java.util.Random ;

public class LoginController {
    public static RegisterMessages checkErrorForRegister(String username , String password , String confirmPassword ,
                                                         String email , String nickname , String slogan){
        if(username == null || password == null || email == null || nickname == null || (!password.equals("random") && confirmPassword == null) ){
            return RegisterMessages.EMPTY_FIELD;
        }
        if(slogan.equals("random")) return RegisterMessages.GET_RANDOM_SLOGANS;
        if(password.equals("random")) return RegisterMessages.GET_RANDOM_PASSWORD ;
        if(!username.matches(".*[A-Za-z0-9_].*")) return RegisterMessages.INCORRECT_FORM_OF_USERNAME;
        if(User.getUserByName(username) != null) return RegisterMessages.USERNAME_REPETED ;
        if(!password.matches(".*[a-z].*")) return RegisterMessages.WEAK_PASSWORD_FOR_LOWERCASE;
        if(!password.matches(".*[A-Z].*")) return RegisterMessages.WEAK_PASSWORD_FOR_UPPERCASE;
        if(!password.matches(".*[0-9].*")) return RegisterMessages.WEAK_PASSWORD_FOR_NUMBER;
        if(!password.matches(".*[\\!\\@\\#\\$\\%\\^\\&].*")) return RegisterMessages.WEAK_PASSWORD_FOR_NOTHING_CHARS_EXEPT_ALPHABETICAL;
        if(password.length() < 6) return RegisterMessages.WEAK_PASSWORD_FOR_LENGTH;
        if(!password.equals(confirmPassword)) return RegisterMessages.NOT_SIMILAR_PASSWORD ;
        String changedEmail = email.toLowerCase() ;
        if(User.getUserByEmail(changedEmail) != null) return RegisterMessages.REPETED_EMAIL;
        if(!email.matches("[A-Za-z0-9\\.]+@[A-Za-z0-9]*\\.+[A-Za-z0-9\\.]*")) return RegisterMessages.INVALID_FORM_EMAIL ;
        return RegisterMessages.SUCCESS ;
    }
    public static RegisterMessages changePassword(User user , String password){
        if(!password.matches(".*[a-z].*")) return RegisterMessages.WEAK_PASSWORD_FOR_LOWERCASE;
        if(!password.matches(".*[A-Z].*")) return RegisterMessages.WEAK_PASSWORD_FOR_UPPERCASE;
        if(!password.matches(".*[0-9].*")) return RegisterMessages.WEAK_PASSWORD_FOR_NUMBER;
        if(password.length() < 6) return RegisterMessages.WEAK_PASSWORD_FOR_LENGTH;
        user.setPassword(password);
        return RegisterMessages.SUCCESS;
    }
    public static void Register(String username , String password , String email , String nickname , String slogan
            , String numberOfSecQuesion , String answeroFSecQuestion){
        new User(username,password,nickname,email,answeroFSecQuestion,slogan,Integer.parseInt(numberOfSecQuesion));
    }
    public static RegisterMessages checkSecurityAsks(int number , String answer , String confirmAnswer){
        if(answer.equals(confirmAnswer)) return RegisterMessages.IS_OK_ASKS;
        else return RegisterMessages.TRY_ANOTHER_SEC_ASK;
    }
    public static String checkIfRepetedUserName(String username){
        if(User.getUserByName(username) != null) username = makeUserNameForUser(username);
        return username ;
    }
    public static RegisterMessages loginUser(String username , String password){
        User user ;
        if((user = User.getUserByName(username)) == null) return RegisterMessages.NOT_EXIST_USERNAME ;
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
}

package view;
import com.google.gson.JsonSyntaxException;
import controller.JsonController;
import model.* ;
//import controller.Controller;
import controller.LoginController;
import view.Commands.LoginAndRegisterCommands;
import view.Messages.RegisterMessages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
public class LoginMenu {
    public static void run(Scanner scanner) throws InterruptedException, IOException {
        JsonController.readDataFile("User.json");
        JsonController.saveAllUsersFileData();
        isLoggedUser(scanner);
        System.out.println("Welcome to Login menu!");
        String command ;
        Matcher matcher ;
        while(true){
            command = scanner.nextLine();
            if((LoginAndRegisterCommands.getMatcher(command , LoginAndRegisterCommands.FOR_REGISTER)) != null){
                checkElementsToRegisterUser(command,scanner);
            }else if((LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.LOGIN)) != null){
                EnterToLogin(command,scanner);
            }else if((LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.FORGOT_MY_PASSWORD)) != null){
                ForgotPasswordCheck(command,scanner);
            }else System.out.println("Invalid command!");
        }
    }
//    static {
//        try {
//            User user1 = new User("ali" , "123" , "mamad" , "String email", "String recoveryQuestion", "String slogan", 2);
//            System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(Manage.allUsers.size() + "jfjf");
//    }

    public static void isLoggedUser(Scanner scanner) throws InterruptedException, IOException {
        JsonController.readDataFile("LoggedInUser.json");
        User user = JsonController.saveLoggedInUserFileData();
        if(user != null){
            User orgUser = User.getUserByName(user.getUsername());
            User.setCurrentUser(orgUser);
            System.out.println("Login successfully");
            MainMenu.run(scanner);
        }
    }
    //user create -u armin -p mamad mamad -email hhh -n kazem -s dd
    private static int numberToWait = 1 ;
    private static void ForgotPasswordCheck(String command,Scanner scanner) throws IOException {
        String username;
        Matcher matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_USERNAME_CHECK);
        if(matcher != null)username = matcher.group("username").replaceAll("\"","");
        else {
            System.out.println("Username not found!");
            return;
        }
        User user = User.getUserByName(username);
        if(user == null) {
            System.out.println("This user does not exist!");
            return;
        }
        String secQuestion = (User.getSecurityQuestions().get(user.getRecoveryQuestionNumber()));
        System.out.println("For recovering password answer the following question:\n " + secQuestion);
        String answer = scanner.nextLine();
        if(answer.equals(user.getRecoveryQuestion())){
            System.out.println("Please enter your new password: ");
            answer = scanner.nextLine();
            RegisterMessages message =  LoginController.changePassword(user,answer);
            switch (message){
                case WEAK_PASSWORD_FOR_LENGTH:
                    System.out.println("Length of your password must be at least 6 characters!");
                    return;
                case WEAK_PASSWORD_FOR_LOWERCASE:
                    System.out.println("You should use lowercase characters");
                    return;
                case WEAK_PASSWORD_FOR_NOTHING_CHARS_EXEPT_ALPHABETICAL:
                    System.out.println("You should use chars except alphabetical!");
                    return;
                case WEAK_PASSWORD_FOR_UPPERCASE:
                    System.out.println("You should use uppercase characters!");
                    return;
                case WEAK_PASSWORD_FOR_NUMBER:
                    System.out.println("You should use numbers into your password!");
                    return;
                case SUCCESS:
                    System.out.println("Password changing is done!");
                    return;
            }


        }else{
            System.out.println("This is not correct.Try again!");
            return;
        }


    }
    private static void EnterToLogin(String command,Scanner scanner) throws InterruptedException, IOException {
        String username = null ;
        String password = null;
        Matcher matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.LOGIN_GET_USERNAME);
        if(matcher != null) username = matcher.group("username").replaceAll("\"","");
        matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.LOGIN_GET_PASSWORD);
        if(matcher != null) password = matcher.group("password").replaceAll("\"","");;
        if(username == null || password == null){
            System.out.println("invalid command");
            return;
        }

        System.out.println(password);
        RegisterMessages message = LoginController.loginUser(username , password);
        System.out.println(username + " \n" + password);
        System.out.println(message);
        switch (message){
            case NOT_EXIST_USERNAME :
                System.out.println("Username does not exist!");
                return;
            case NOT_SIMILAR_PASSWORD :
                System.out.println("Wrong password!");
                Thread.sleep(5000L * numberToWait);
                //TODO : has problem
                numberToWait ++ ;
                return;
            case SUCCESS:
                matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.IS_LOGGED_USER);
                if(matcher != null){
                    String result = LoginController.isLoggedUser(username);
                    System.out.println(result);
                    if(result.equals("This user dose not exist!")) return;
                }
                numberToWait = 1 ;
                System.out.println("Login successfully!");
                MainMenu.run(scanner);
                return;
        }
    }
    public static void checkElementsToRegisterUser(String command , Scanner scanner) throws IOException {
        String username;String password ;String confirmPassword = null;String email ;String nickname ;String slogan ;
        Matcher matcher ;
        matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_USERNAME_CHECK);
        if(matcher == null) username = null ;
        else username = matcher.group("username").replaceAll("\"","");
        matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_PASSWORD_CHECK);
        if(matcher == null) password =  null ;
        else {
            password = matcher.group("password").replaceAll("\"","");
            confirmPassword = matcher.group("confirmPassword");
            if(confirmPassword != null) confirmPassword = confirmPassword.replaceAll("\"","");
        }
        matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_EMAIL_CHECK);
        if(matcher == null) email =  null ;
        else {
            email = matcher.group("email").replaceAll("\"","");
        }
        matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_NICKNAME_CHECK);
        if(matcher == null) nickname =  null ;
        else {
            nickname = matcher.group("nickname").replaceAll("\"","");
        }
        matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_SLOGAN_CHECK);
        if(matcher == null){
            matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_SLOGAN_CHECK_FOR_EMPTY);
            if(matcher == null) slogan = "empty";
            else slogan = null ;
        }
        else slogan = matcher.group("slogan").replaceAll("\"","");

        sendInformationsOfRegisterUser(username,password,confirmPassword,email,nickname,slogan,scanner);
    }
    private static void sendInformationsOfRegisterUser(String username , String password , String confirmPassword ,
                                                       String email , String nickname , String slogan , Scanner scanner) throws IOException {
        System.out.println(username + " " + password + " " + confirmPassword + " " + email + " " + nickname + " " + slogan);
//        System.out.println(password);
//        System.out.println(confirmPassword);
//        System.out.println(email);
        RegisterMessages message = LoginController.checkErrorForRegister(username,password,confirmPassword,email,nickname,slogan);
//        System.out.println(message);
        switch (message){
            case USERNAME_REPETED :
                username = LoginController.makeUserNameForUser(username);
                System.out.println("Your name is repeated but the name " + username + " exists now. Would you like to use it? Type yes if you want!");
                String answer = scanner.nextLine();
                if(answer.equals("yes") || answer.equals("Yes"))
                    sendInformationsOfRegisterUser(username,password,confirmPassword,email,nickname,slogan,scanner);
                else {
                    System.out.println("Try again");
                    return;
                }
                break;
            case GET_RANDOM_SLOGANS:
                slogan = LoginController.getRandomSlogan();
                System.out.println("Your slogan is \"" + slogan + "\"");
                sendInformationsOfRegisterUser(username,password,confirmPassword,email,nickname,slogan,scanner);
                break;
            case GET_RANDOM_PASSWORD:
                password = LoginController.generateRandomPassword();
                System.out.println("Your random password is " + password + ".Please type it");
                String ans = scanner.nextLine();
                while(true){
                    if(ans.equals(password)) {
                        sendInformationsOfRegisterUser(username,password,password,email,nickname,slogan,scanner);
                        break;
                    }
                    System.out.println("Try again!");
                    ans = scanner.nextLine();
                }
                return;
            case INCORRECT_FORM_OF_USERNAME:
                System.out.println("Your format of username is invalid!");
                return;
            case WEAK_PASSWORD_FOR_NOTHING_CHARS_EXEPT_ALPHABETICAL:
                System.out.println("You should use chars except alphabetical!");
                return;
            case WEAK_PASSWORD_FOR_LENGTH:
                System.out.println("length of your password is must be at least 6 characters");
                return;
            case WEAK_PASSWORD_FOR_LOWERCASE:
                System.out.println("You should use lowercase characters!");
                return;
            case WEAK_PASSWORD_FOR_UPPERCASE:
                System.out.println("You should use uppercase characters!");
                return;
            case WEAK_PASSWORD_FOR_NUMBER:
                System.out.println("You should use number inside your password!");
                return;
            case NOT_SIMILAR_PASSWORD:
                System.out.println("Not similar your password!");
                return;
            case INVALID_FORM_EMAIL:
                System.out.println("Invalid form of email!");
                return;
            case REPETED_EMAIL:
                System.out.println("Your email is repeated!");
                return;
            case EMPTY_FIELD:
                System.out.println("Your given entry has empty field!");
                return;
            case SUCCESS:
                String[] list = askSecurityQuestion(scanner);
                if(list == null) {
                    System.out.println("There is something wrong with your entry!");
                    return;
                }
                else{
                    LoginController.register(username,password,nickname,email,list[1],slogan,list[0]);
//                    User user = new User(username,password,nickname,email,list[1],slogan,Integer.parseInt(list[0]));
                }
//                System.out.println(Manage.allUsers.size());
                System.out.println("register successfully");
        }

    }
    private static String[] askSecurityQuestion(Scanner scanner){
        System.out.println("Pick your security quesion: \n1.What is my father\'s name? " +
                "\n2.What was my first pet\'s name? " +
                "\n3.What is my mother\'s last name?");
        String command = scanner.nextLine();
        return getMatcherForRegister(command);

    }
    private static String[] getMatcherForRegister(String command){
        int number = 0 ;

        String ask = null;
        String askConfirm = null;
        Matcher matcher ;
        matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.GET_QUESTION_NUMBER);
        if(matcher != null)
            number = Integer.parseInt(matcher.group("number").replaceAll("\"",""));

        matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.GET_QUESTION_ASK);
        if(matcher != null)
            ask = matcher.group("ask").replaceAll("\"","");
        matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.GET_QUESTION_ASK_CONFIRM);
        if(matcher != null)
            askConfirm = matcher.group("askConfirm").replaceAll("\"","");
        RegisterMessages message = LoginController.checkSecurityAsks(number,ask,askConfirm);
        if(message.equals(RegisterMessages.IS_OK_ASKS)){
            String[] list = new String[2];
            list[0]  = Integer.toString(number);
            list[1] = ask ;
            return list ;
        }
//        System.out.println(number + "\n" + ask + "\n" + askConfirm);
        return null ;

    }
}
//TODO : fix all commands regex
//TODO : fix all users arrayList




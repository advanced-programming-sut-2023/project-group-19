package view;
import model.* ;
//import controller.Controller;
import controller.LoginController;
import view.Commands.LoginAndRegisterCommands;
import view.Messages.RegisterMessages;

import java.util.Scanner;
import java.util.regex.Matcher;
public class LoginMenu {
    public static void run(Scanner scanner) throws InterruptedException {
        String command ;
        Matcher matcher ;
        while(true){
            command = scanner.nextLine();
            if((matcher = LoginAndRegisterCommands.getMatcher(command , LoginAndRegisterCommands.FOR_REGISTER)) != null){
                checkElementsToRegisterUser(command,scanner);
            }else if((matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.LOGIN)) != null){
                EnterToLogin(command,scanner);
            }else if((matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_USERNAME_CHECK)) != null){
                ForgotPasswordCheck(command,scanner);
            }else System.out.println("Invalid command!");
        }
    }
    //user create -u armin -p mamad mamad -email hhh -n kazem -s dd
    private static int numberToWait = 1 ;
    private static void ForgotPasswordCheck(String command,Scanner scanner){
        String username;
        Matcher matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_USERNAME_CHECK);
        if(matcher != null)username = matcher.group("username").trim().substring(3);
        else {
            System.out.println("username not found!");
            return;
        }
        User user = User.getUserByName(username);
        if(user == null) {
            System.out.println("this user not exist!");
            return;
        }
        String secQuestion = (User.getSecurityQuestions().get(user.getRecoveryQuestionNumber()));
        System.out.println("your security question is " + secQuestion + ".please answer it");
        String answer = scanner.nextLine();
        if(answer.equals(user.getRecoveryQuestion())){
            System.out.println("please enter your new password :");
            answer = scanner.nextLine();
            RegisterMessages message =  LoginController.changePassword(user,answer);
            switch (message){
                case WEAK_PASSWORD_FOR_LENGTH:
                    System.out.println("length of your password is must be at least 6 characters");
                    return;
                case WEAK_PASSWORD_FOR_LOWERCASE:
                    System.out.println("you should use lowercase chars");
                    return;
                case WEAK_PASSWORD_FOR_NOTHING_CHARS_EXEPT_ALPHABETICAL:
                    System.out.println("you should use chars exept alphbetical");
                    return;
                case WEAK_PASSWORD_FOR_UPPERCASE:
                    System.out.println("you should use uppercase chars");
                    return;
                case WEAK_PASSWORD_FOR_NUMBER:
                    System.out.println("you should use numbsers into your pssword");
                    return;
                case SUCCESS:
                    System.out.println("password changing is done!");
                    return;
            }


        }else{
            System.out.println("this not correct!try agian");
            return;
        }


    }
    private static void EnterToLogin(String command,Scanner scanner) throws InterruptedException {
        String username = null ;
        String password = null;
        Matcher matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.LOGIN_GET_USERNAME);
        if(matcher != null) username = matcher.group("username").trim().substring(3);
        matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.LOGIN_GET_PASSWORD);
        if(matcher != null) password = matcher.group("password").trim().substring(3);
        RegisterMessages message = LoginController.loginUser(username , password);
        switch (message){
            case NOT_EXIST_USERNAME :
                System.out.println("not exist username");
                return;
            case NOT_SIMILAR_PASSWORD :
                System.out.println("incorrect form of password");
                Thread.sleep(5000L * numberToWait);
                numberToWait ++ ;
                return;
            case SUCCESS:
                numberToWait = 1 ;
                System.out.println("login successfully");
                MainMenu.run(scanner);
                return;
        }
    }
    public static void checkElementsToRegisterUser(String command , Scanner scanner){
        String username;String password ;String confirmPassword = null;String email ;String nickname ;String slogan ;
        Matcher matcher ;
        matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_USERNAME_CHECK);
        if(matcher == null) username = null ;
        else username = matcher.group("username").substring(3).replaceAll("\"","");
        matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_PASSWORD_CHECK);
        if(matcher == null) password =  null ;
        else {
            password = matcher.group("password").trim().substring(3).replaceAll("\"","");
            confirmPassword = matcher.group("confirmPassword");
            if(confirmPassword != null) confirmPassword = confirmPassword.trim().replaceAll("\"","");
        }
        matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_EMAIL_CHECK);
        if(matcher == null) email =  null ;
        else {
            email = matcher.group("email").trim().substring(7).replaceAll("\"","");
        }
        matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_NICKNAME_CHECK);
        if(matcher == null) nickname =  null ;
        else {
            nickname = matcher.group("nickname").trim().substring(3).replaceAll("\"","");
        }
        matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_SLOGAN_CHECK);
        if(matcher == null){
            matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.REGISTER_SLOGAN_CHECK_FOR_EMPTY);
            if(matcher == null) slogan = "empty";
            else slogan = null ;
        }
        else slogan = matcher.group("slogan").trim().substring(3).replaceAll("\"","");

        sendInformationsOfRegisterUser(username,password,confirmPassword,email,nickname,slogan,scanner);
    }
    private static void sendInformationsOfRegisterUser(String username , String password , String confirmPassword ,
                                                       String email , String nickname , String slogan , Scanner scanner){
//        System.out.println(username + " " + password + " " + confirmPassword + " " + email + " " + nickname + " " + slogan);
        System.out.println(password);
        System.out.println(confirmPassword);
        RegisterMessages message = LoginController.checkErrorForRegister(username,password,confirmPassword,email,nickname,slogan);
        System.out.println(message);
        switch (message){
            case USERNAME_REPETED :
                username = LoginController.makeUserNameForUser(username);
                System.out.println("your name is repeted but the name of " + username + " is exist now. would you like to use it? type yes if you want!");
                String answer = scanner.nextLine();
                if(answer.equals("yes"))
                    sendInformationsOfRegisterUser(username,password,confirmPassword,email,nickname,slogan,scanner);
                else {
                    System.out.println("try agian");
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
                System.out.println("your random password is " + password + " please type it");
                String ans = scanner.nextLine();
                while(true){
                    if(ans.equals(password)) {
                        sendInformationsOfRegisterUser(username,password,password,email,nickname,slogan,scanner);
                        break;
                    }
                    System.out.println("try again!");
                    ans = scanner.nextLine();
                }
                return;
            case INCORRECT_FORM_OF_USERNAME:
                System.out.println("your form of username is invalid!");
                return;
            case WEAK_PASSWORD_FOR_NOTHING_CHARS_EXEPT_ALPHABETICAL:
                System.out.println("you should use chars exept alphabetical");
                return;
            case WEAK_PASSWORD_FOR_LENGTH:
                System.out.println("length of your password is must be at least 6 characters");
                return;
            case WEAK_PASSWORD_FOR_LOWERCASE:
                System.out.println("you should use lowercase chars");
                return;
            case WEAK_PASSWORD_FOR_UPPERCASE:
                System.out.println("you should use uppercase chars");
                return;
            case WEAK_PASSWORD_FOR_NUMBER:
                System.out.println("you should use numbsers into your pssword");
                return;
            case NOT_SIMILAR_PASSWORD:
                System.out.println("not similar your password");
                return;
            case INVALID_FORM_EMAIL:
                System.out.println("invalid form email");
                return;
            case REPETED_EMAIL:
                System.out.println("your email is repeted");
                return;
            case EMPTY_FIELD:
                System.out.println("has empty field");
                return;
            case SUCCESS:
                String[] list = askSecurityQuestion(scanner);
                if(list == null) {
                    System.out.println("ypu have to fix whole fields correctly");
                    return;
                }
                else LoginController.Register(username,password,email,nickname,slogan,list[0],list[1]);
                System.out.println("register succseefully");
        }

    }
    private static String[] askSecurityQuestion(Scanner scanner){
        System.out.println("Pick your security quesion: 1.What is my father\'s name? " +
                "2.What was my first pet\'s name? " +
                "3.What is my mother\'s last name?");
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
            number = Integer.parseInt(matcher.group("number").trim().substring(3));
        matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.GET_QUESTION_ASK);
        if(matcher != null)
            ask = matcher.group("ask").trim().substring(3);
        matcher = LoginAndRegisterCommands.getMatcher(command,LoginAndRegisterCommands.GET_QUESTION_ASK_CONFIRM);
        if(matcher != null)
            askConfirm = matcher.group("askConfirm").trim().substring(3);
        RegisterMessages message = LoginController.checkSecurityAsks(number,ask,askConfirm);
        if(message.equals(RegisterMessages.IS_OK_ASKS)){
            String[] list = new String[2];
            list[0]  = Integer.toString(number);
            list[1] = ask ;
            return list ;
        }
        System.out.println("your asks is not similar");
        return null ;

    }
}




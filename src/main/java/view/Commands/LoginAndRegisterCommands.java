package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginAndRegisterCommands {

    FOR_REGISTER("\\s*user\\s+create"),
    REGISTER_USERNAME_CHECK("-u\\s+(?<username>(?=\")\"[^\"]+\"|\\S+)"),
    REGISTER_PASSWORD_CHECK("-p\\s+(?<password>(?=\")\"[^\"-]+\"|[^ -]+)\\s(?<confirmPassword>(?=\")\"[^\"-]+\"|[^ -]+)?"),
    REGISTER_PASSWORD_CHECK_2("(?<confirmPassword>(?=\")\"[^\"-]+\"|[^ -]+)?\\s*-p\\s+(?<password>(?=\")\"[^\"-]+\"|[^ -]+)"),
    REGISTER_EMAIL_CHECK("(--email|-e)\\s+(?<email>(?=\")\"[^\"]+\"|\\S+)"),
    REGISTER_NICKNAME_CHECK("-n\\s+(?<nickname>(?=\")\"[^\"]+\"|\\S+)"),
    REGISTER_SLOGAN_CHECK("-s\\s+(?<slogan>(?=\")\"[^\"]+\"|\\S+)"),
    REGISTER_SLOGAN_CHECK_FOR_EMPTY(" (?<sloganCheck>-s)"),
    GET_QUESTION_NUMBER("-q\\s+(?<number>\\d)"),
    GET_QUESTION_ASK("-a\\s+(?<ask>(?=\")\"[^\"]+\"|\\S+)"),
    GET_QUESTION_ASK_CONFIRM("-c\\s+(?<askConfirm>(?=\")\"[^\"]+\"|\\S+)"),
    LOGIN("user login"),
    IS_LOGGED_USER("--stay-logged-in"),
    LOGIN_GET_USERNAME("-u\\s+(?<username>(?=\")\"[^\"]+\"|\\S+)"),
    LOGIN_GET_PASSWORD("-p\\s+(?<password>(?=\")\"[^\"]+\"|\\S+)"),
    FORGOT_MY_PASSWORD("forgot my password"),
    ASK_SEQURITY_QUESTION("question pick");
    String regex ;
    LoginAndRegisterCommands(String regex){
        this.regex = regex;
    }
    public static Matcher getMatcher(String command , LoginAndRegisterCommands mainRegex){
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(command);
        if(matcher.find()) return matcher ;
        return null ;
    }
}

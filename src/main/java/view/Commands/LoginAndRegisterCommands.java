package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginAndRegisterCommands {

    FOR_REGISTER("\\s*user\\s+create"),
    REGISTER_USERNAME_CHECK("-u\\s+(?<username>(?=\")\"[^\"-]+\"|[^- ]+)"),
    REGISTER_PASSWORD_CHECK("-p\\s+(?<password>(?=\")\"[^\"-]+\"|[^- ]+)\\s(?<confirmPassword>(?=\")\"[^\"-]+\"|[^- ]+)?"),
    REGISTER_EMAIL_CHECK("(--email|-e)\\s+(?<email>(?=\")\"[^\"-]+\"|[^- ]+)"),
    REGISTER_NICKNAME_CHECK("-n\\s+(?<nickname>(?=\")\"[^\"-]+\"|[^- ]+)"),
    REGISTER_SLOGAN_CHECK("-s\\s+(?<slogan>(?=\")\"[^\"-]+\"|[^- ]+)"),
    REGISTER_SLOGAN_CHECK_FOR_EMPTY(" (?<sloganCheck>-s)"),
    GET_QUESTION_NUMBER("-q\\s+(?<number>\\d)"),
    GET_QUESTION_ASK("-a\\s+(?<ask>(?=\")\"[^\"]+\"|\\S+)"),
    GET_QUESTION_ASK_CONFIRM("-c\\s+(?<askConfirm>(?=\")\"[^\"-]+\"|[^- ]+)"),
    LOGIN("user login"),
    IS_LOGGED_USER("--stay-logged-in"),
    LOGIN_GET_USERNAME("-u\\s+(?<username>(?=\")\"[^\"-]+\"|[^- ]+)"),
    LOGIN_GET_PASSWORD("-p\\s+(?<password>(?=\")\"[^\"-]+\"|[^- ]+)"),
    FORGOT_MY_PASSWORD("forgot my password -u\\s+(?<username>(?=\")\"[^\"-]+\"|[^- ]+)"),
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

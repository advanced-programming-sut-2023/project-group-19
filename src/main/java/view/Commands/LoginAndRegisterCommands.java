package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginAndRegisterCommands {
    FOR_REGISTER("\\s*user\\s+create"),
    REGISTER_USERNAME_CHECK("(?<username>(-u\\s+(?=\"+)[^-]+)\"{1}|(-u\\s+(?=\"{0})[^\\s-]+))"),
    REGISTER_PASSWORD_CHECK("(?<password>(-p\\s+(?=\"+)[^-]+)\"{1}|(-p\\s+(?=\"{0})[^\\s-]+))\\s*(?<confirmPassword>((?=\"+)[^-]+\"{1}|(\\S+)))?"),
    REGISTER_EMAIL_CHECK("(?<email>(-email\\s+(?=\"+)[^-]+)\"{1}|(-email\\s+(?=\"{0})[^\\s-]+))"),
    REGISTER_NICKNAME_CHECK("(?<nickname>(-n\\s+(?=\"+)[^-]+)\"{1}|(-n\\s+(?=\"{0})[^\\s-]+))"),
    REGISTER_SLOGAN_CHECK("(?<slogan>(-s\\s+(?=\"+)[^-]+)\"{1}|(-s\\s+(?=\"{0})[^\\s-]+))"),
    REGISTER_SLOGAN_CHECL_FOR_EMPTY(" (?<sloganCheck>-s)"),
    GET_QUESTION_NUMBER("(?<numberOfQuestion>-q [1-3])"),
    GET_QUESTION_ASK("(?<ask>(-s\\s+(?=\"+)[^-]+)\"{1}|(-s\\s+(?=\"{0})[^\\s-]+))"),
    GET_QUESTION_ASK_CONFIRM("(?<askConfirm>(-s\\s+(?=\"+)[^-]+)\"{1}|(-s\\s+(?=\"{0})[^\\s-]+))"),
    LOGIN("user login"),
    LOGIN_GET_USERNAME("(?<username>(-u\\s+(?=\"+)[^-]+)\"{1}|(-u\\s+(?=\"{0})[^\\s-]+))"),
    LOGIN_GET_PASSWORD("(?<password>(-p\\s+(?=\"+)[^-]+)\"{1}|(-p\\s+(?=\"{0})[^\\s-]+))"),
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

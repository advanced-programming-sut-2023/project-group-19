package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {
    ENTER_PROFILE_MENU("enter profile menu"),
    LOGOUT("\\s*logou\\s*");
    String regex ;
    MainMenuCommands(String regex){
        this.regex = regex;
    }
    public static Matcher getMatcher(String command , MainMenuCommands mainRegex){
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(command);
        if(matcher.find()) return matcher ;
        return null ;
    }
}

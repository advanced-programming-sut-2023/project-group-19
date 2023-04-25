package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {
    ENTER_PROFILE_MENU("enter\\s+profile\\s+menu"),
    LOGOUT("\\s*logou\\s*"),
    SHOW_MAP("show\\s+map"),
    SHOW_MAP_X("-x\\s+(?<x>\\d+)"),
    SHOW_MAP_Y("-y\\s+(?<y>\\d+)");
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

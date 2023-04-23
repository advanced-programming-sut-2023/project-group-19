package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CreateMapCommands {
    MOVING_MAP("map"),
    MOVING_MAP_INTO_DIRECTION("(?<type>\\S+)\\s+(?<number>\\d+)?");
    String regex ;
    CreateMapCommands(String regex){
        this.regex = regex;
    }
    public static Matcher getMatcher(String command , CreateMapCommands mainRegex){
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(command);
        if(matcher.find()) return matcher ;
        return null ;
    }
}

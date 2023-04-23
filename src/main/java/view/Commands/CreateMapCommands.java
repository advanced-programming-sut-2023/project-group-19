package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CreateMapCommands {
    MOVING_MAP("map"),
    SHOW_DETAIL_X("-x\\s+(?<x>\\d+)"),
    SHOW_DETAIL_Y("-y\\s+(?<y>\\d+)"),
    SHOW_DETAIL("show\\s+detail");

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

package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CreateMapCommands {
    MOVING_MAP("^map"),
    SHOW_X("-x\\s+(?<x>\\d+)"),
    SHOW_Y("-y\\s+(?<y>\\d+)"),
    CREATE_MAP("create\\s+map\\s+-s\\s+(?<size>\\d+)"),
    SET_TEXTURE("settexture"),
    DROP_ROCK("droprock"),
    DROP_TREE("droptree"),
    GET_DIRECTION("-d\\s+(?<direction>\\S+)"),
    SET_TEXTURE_X("-x\\s+(?<x>\\d+)"),
    SET_TEXTURE_X1("-x1\\s+(?<x1>\\d+)"),
    SET_TEXTURE_X2("-x2\\s+(?<x2>\\d+)"),
    SET_TEXTURE_Y("-y\\s+(?<y>\\d+)"),
    SET_TEXTURE_Y1("-y1\\s+(?<y1>\\d+)"),
    SET_TEXTURE_Y2("-y2\\s+(?<y2>\\d+)"),
    CLEAR("clear"),
    SET_TEXTURE_TYPE("-t\\s+(?<type>\\S+)"),
    SHOW_DETAIL("show\\s+detail"),
    LOCATE_CASTLE("locate\\s+castle"),
    EXIT("exit"),
    ;

    String regex;

    CreateMapCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, CreateMapCommands mainRegex) {
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(command);
        if (matcher.find()) return matcher;
        return null;
    }
}

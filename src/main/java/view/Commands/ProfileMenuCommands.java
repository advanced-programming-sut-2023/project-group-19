package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands {
    PROFILE_USERNAME_CHANGE("(?<username>(-u\\s+(?=\"+)[^-]+)\"{1}|(-u\\s+(?=\"{0})[^\\s-]+))"),
    PROFILE_NICKNAME_CHANGE("profile\\s+change\\s+(?<nickname>(-n\\s+(?=\"+)[^-]+)\"{1}|(-n\\s+(?=\"{0})[^\\s-]+))"),
    PROFILE_PASSWORD_CHANGE("(?<old>(-o\\s+(?=\"+)[^-]+)\"{1}|(-o\\s+(?=\"{0})[^\\s-]+))\\s+(?<new>(-n\\s+(?=\"+)[^-]+)\"{1}|(-n\\s+(?=\"{0})[^\\s-]+))"),
    PROFILE_EMAIL_CHANGE("(?<email>(-e\\s+(?=\"+)[^-]+)\"{1}|(-e\\s+(?=\"{0})[^\\s-]+))"),
    SHOW_SLOGAN_CHANGE("profile\\s+change\\s+slogan"),
    PROFILE_SLOGAN_CHANGE("(?<slogan>(-s\\s+(?=\"+)[^-]+)\"{1}|(-s\\s+(?=\"{0})[^\\s-]+))");
    String regex ;
    ProfileMenuCommands(String regex){
        this.regex = regex ;
    }
    public static Matcher getMatcher(String command , ProfileMenuCommands mainRegex){
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(command);
        if(matcher.find()) return matcher ;
        return null ;
    }

}


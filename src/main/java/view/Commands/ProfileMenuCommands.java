package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands {
    PROFILE_USERNAME_CHANGE("profile\\s+change\\s+-u\\s+(?<username>(?=\")\"[^\"]+\"|\\S+)"),
    PROFILE_NICKNAME_CHANGE("profile\\s+change\\s+-n\\s+(?<nickname>(?=\")\"[^\"]+\"|\\S+)"),
    PROFILE_PASSWORD_CHANGE("profile\\s+change\\s+password"),
    PROFILE_GET_OLD_PASSWORD("-o\\s+(?<old>(?=\")\"[^\"-]+\"|[^ -]+)"),
    PROFILE_GET_NEW_PASSWORD("-n\\s+(?<new>(?=\")\"[^\"-]+\"|[^ -]+)"),

    PROFILE_EMAIL_CHANGE("profile\\s+change\\s+(--email|-e)\\s+(?<email>(?=\")\"[^\"]+\"|\\S+)"),
    PROFILE_SLOGAN_CHANGE("profile\\s+change\\s+slogan\\s+-s\\s+(?<slogan>(?=\")\"[^\"]+\"|\\S+)");
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


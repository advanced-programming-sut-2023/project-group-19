package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum EmpireCommands {
    SHOW_FACTORS("\s*show popularity factors\s*"),
    SHOW_POPULARITY("\s*show popularity\s*"),
    SHOW_FOOD_LIST("\s*show food list\s*"),
    SET_FOOD_RATE_NUMBER("\s*food rate -r (?<rateNumber>\\d+)\s*"),
    SHOW_FOOD_RATE("\s*food rate show\s*"),
    SET_TAX_RATE_NUMBER("\s*tax rate -r (?<rateNumber>\\d+)\s*"),
    SHOW_TAX_RATE("\s*tax rate show\s*"),
    SET_FEAR_RATE("\s*fear rate -r (?<rateNumber>\\d+)\s*"),
    LOGOUT("\s*Logout\s*");
    String regex;
    EmpireCommands(String regex){
        this.regex = regex ;
    }
    public static Matcher getMatcher(String command , EmpireCommands mainRegex){
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(command);
        if(matcher.find()) return matcher ;
        return null ;
    }
}

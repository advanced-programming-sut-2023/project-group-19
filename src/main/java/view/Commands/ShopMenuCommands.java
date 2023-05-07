package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ShopMenuCommands {
    SHOW_PRICE_LIST("show price list"),
    BUY("\\s*buy\\s+"),
    ITEM_NAME_CHECK("-i(?<itemName>.+)"),
    ITEM_AMOUNT_CHECK("-a(?<itemAmount>.+)"),
    SELL("\\s*sell\\s+"),
    OPERATION_ACCEPTED("\\s*yes|Yes\\s*"),
    OPERATION_CANCELLED("\\s*no|No\\s*"),
    LOGOUT("\\s*Logout\\s*");
    String regex;
    ShopMenuCommands(String regex){
        this.regex = regex ;
    }
    public static Matcher getMatcher(String command , ShopMenuCommands mainRegex){
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(command);
        if(matcher.find()) return matcher ;
        return null ;
    }
}

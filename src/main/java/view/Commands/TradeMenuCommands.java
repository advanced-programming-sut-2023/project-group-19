package view.Commands;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public enum TradeMenuCommands {
    SEND_REQUEST_RESOURCE_TYPE_CHECK("(?<resourceType>(-t\\s+(?=\"+)[^-]+)\"{1}|(-t\\s+(?=\"{0})[^\\s-]+))"),
    SEND_REQUEST_RESOURCE_AMOUNT_CHECK("(?<resourceAmount>-a\\s+\\d+)"),
    SEND_REQUEST_RESOURCE_PRICE_CHECK("?<resourcePrice>-p\\s+\\d+"),
    SEND_REQUEST_RESOURCE_MESSAGE_CHECK("?<resourceMessage>(-m\\s+(?=\"+)[^-]+)\"{1}|(-m\\s+(?=\"{0})[^\\s-]+)"),
    SHOW_TRADE_LIST("\\s*trade\\s+list"),
    TRADE_ACCEPTED_ID_CHECK("?<id>(-i\\s+(?=\"+)[^-]+)\"{1}|(-i\\s+(?=\"{0})[^\\s-]+))"),
    TRADE_ACCEPTED_MESSAGE_CHECK("?<tradeMessage>(-m\\s+(?=\"+)[^-]+)\"{1}|(-m\\s+(?=\"{0})[^\\s-]+)"),
    SHOW_TRADE_HISTORY("\\s*trade\\s+history");

    String regex;
    TradeMenuCommands(String regex){
        this.regex = regex ;
    }
    public static Matcher getMatcher(String command , TradeMenuCommands mainRegex){
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(command);
        if(matcher.find()) return matcher ;
        return null ;
    }
}

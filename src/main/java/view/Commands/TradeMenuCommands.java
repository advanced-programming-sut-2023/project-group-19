package view.Commands;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public enum TradeMenuCommands {
    TRADE("trade -t .+"),
    FORMAT_OF_NUMBER_FOR_SELECTED_BUILDING("\\s*\\d+\\s*"),
    SEND_REQUEST_RESOURCE_TYPE_CHECK("(?<resourceType>(-t\\s+(?=\"+)[^-]+)\"{1}|(-t\\s+(?=\"{0})[^\\s-]+))"),
    SEND_REQUEST_RESOURCE_AMOUNT_CHECK("(?<resourceAmount>-a\\s+\\d+)"),
    SEND_REQUEST_RESOURCE_PRICE_CHECK("?<resourcePrice>-p\\s+\\d+"),
    SEND_REQUEST_RESOURCE_MESSAGE_CHECK("?<resourceMessage>(-m\\s+(?=\"+)[^-]+)\"{1}|(-m\\s+(?=\"{0})[^\\s-]+)"),
    SHOW_TRADE_LIST("\\s*trade\\s+list"),
    SHOW_TRADE_HISTORY("\\s*trade\\s+history"),
    TRADE_ACCEPTED("\\s*trade\\s+accept\\s.+"),
    TRADE_ACCEPTED_ID_CHECK("?<id>(-i\\s+(?=\"+)[^-]+)\"{1}|(-i\\s+(?=\"{0})[^\\s-]+))"),
    TRADE_ACCEPTED_MESSAGE_CHECK("?<tradeMessage>(-m\\s+(?=\"+)[^-]+)\"{1}|(-m\\s+(?=\"{0})[^\\s-]+)"),
    SENDER_OF_REQUEST("Sender:\\s+(?<sender>.*)"),
    RESOURCE_TYPE("Requested resource:\\s(?<type>.*)"),
    RESOURCE_AMOUNT_OF_REQUEST("Amount of resource:\\s(?<amount>).*"),
    MESSAGE_OF_REQUEST_OR_DONATION("Attached Message:\\s+(?<message>.*)"),
    LOGOUT("\\s*Logout");

    private Pattern name;
    public Pattern getName() {
        return name;
    }

    public void setName(Pattern name) {
        this.name = name;
    }

    TradeMenuCommands(String name) {
        this.name = Pattern.compile(name);
    }
    public static Matcher getMatcher(String input, TradeMenuCommands command) {
        Matcher matcher = command.name.matcher(input);
        return matcher.matches() ? matcher : null;
    }
}

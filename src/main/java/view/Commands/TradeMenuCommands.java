package view.Commands;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public enum TradeMenuCommands {
    TRADE("trade .+"),
    SELECT_EMPIRE("select -e (?<name>.+)"),
    SEND_REQUEST_RESOURCE_TYPE_CHECK(".*-t (?<resourceType>\\S+).*"),
    SEND_REQUEST_RESOURCE_AMOUNT_CHECK(".*-a (?<resourceAmount>\\d+).*"),
    SEND_REQUEST_RESOURCE_PRICE_CHECK(".*-p (?<resourcePrice>\\d+).*"),
    SEND_REQUEST_RESOURCE_MESSAGE_CHECK(".* -m (?<resourceMessage>.+).*"),
    SHOW_TRADE_LIST("trade list"),
    SHOW_TRADE_HISTORY("trade history"),
    TRADE_ACCEPTED("trade accept"),
    TRADE_ACCEPTED_ID_CHECK(".*-i (?<id>\\S+) .*"),
    TRADE_ACCEPTED_MESSAGE_CHECK(".*-m (?<tradeMessage>.+) .*"),
    SENDER_OF_REQUEST("Sender: (?<sender>.*).*"),
    RESOURCE_TYPE(".*Requested resource: (?<type>\\S+).*"),
    RESOURCE_PRICE(".*Suggesting price: (?<price>\\d+).*"),
    RESOURCE_AMOUNT_OF_REQUEST(".*Amount of resource: (?<amount>\\d+).*"),
    MESSAGE_OF_REQUEST_OR_DONATION(".*Attached Message: (?<message>.*)"),
    LOGOUT("Logout"),
    NEXT("Next");

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

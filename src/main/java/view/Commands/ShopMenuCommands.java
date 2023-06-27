package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ShopMenuCommands {
    SHOW_PRICE_LIST("show price list"),
    BUY("buy .+"),
    ITEM_NAME_CHECK(".*-i (?<itemName>\\S+).*"),
    ITEM_AMOUNT_CHECK(".*-a (?<itemAmount>\\d+).*"),
    SELL("sell .+"),
    OPERATION_ACCEPTED("\\s*yes|Yes\\s*"),
    OPERATION_CANCELLED("\\s*no|No\\s*"),

    LOGOUT("\\s*Logout\\s*");
    private Pattern name;

    public Pattern getName() {
        return name;
    }

    public void setName(Pattern name) {
        this.name = name;
    }

    ShopMenuCommands(String name) {
        this.name = Pattern.compile(name);
    }

    public static Matcher getMatcher(String input, ShopMenuCommands command) {
        Matcher matcher = command.name.matcher(input);
        return matcher.matches() ? matcher : null;
    }
}

package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum BuildingCommands {
    BUILDING_COMMANDS_DROP_BUILDING(".*dropbuilding.*"),
    BUILDING_COMMANDS_FIND_X(".*-x\\s+(?<x>\\d+).*"),
    BUILDING_COMMANDS_FIND_Y(".*-y\\s+(?<y>\\d+).*"),
    BUILDING_COMMANDS_SELECT_BUILDING(".*select building.*"),
    BUILDING_COMMANDS_FIND_DROP_BUILDING_TYPE(".*-type\\s+(?<type>\\w+).*"),
    INVALID_COMMAND("invalid command"),
    LOGOUT("Logout"),
    ;

    private Pattern name;

    public Pattern getName() {
        return name;
    }

    public void setName(Pattern name) {
        this.name = name;
    }

    BuildingCommands(String name) {
        this.name = Pattern.compile(name);
    }

    public static Matcher getMatcher(String input, BuildingCommands command) {
        Matcher matcher = command.name.matcher(input);
        return matcher.matches() ? matcher : null;
    }
}

package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SelectedBuildingCommands {
    SELECTED_BUILDING_TAX_RATE("^taxrate (?<taxRate>\\d+)$"),
    SELECTED_BUILDING_DRAW_BRIDGE("^drawBridge (?<bridgeCondition>(up|down))$"),
    SELECTED_BUILDING_CREATE_TROOP("^create (?<troopName>\\w+) (?<count>\\d+)$"),
    SELECTED_BUILDING_BARRACKS_TROOP_NAME_CHECK("(archer|spearMan|maceMan|crossbowMan|pikeMan|swordMan|knight)"),
    SELECTED_BUILDING_SIEGE_TENT_TROOP_NAME_CHECK("^(catapult|trebuchet|siegeTower|fireBalista|batteringRam|portableShield)$"),
    SELECTED_BUILDING_MERCENARY_TROOP_NAME_CHECK("(arabianBow|slave|slinger|assassin|horseArcher|arabianSwordMan|fireThrower)"),
    SELECTED_BUILDING_ENGINEER_GUILD_TROOP_NAME_CHECK("^(engineer|ladderMan|tunneler)$"),
    SELECTED_BUILDING_CHURCH_TROOP_NAME_CHECK("^blackMonk$"),
    BUILDING_COMMANDS_CREATE_UNIT("createunit"),
    BUILDING_COMMANDS_REPAIR("repair"),
    BUILDING_COMMANDS_FIND_CREATE_UNIT_TYPE("-t\\s+(?<type>\\w+)"),
    BUILDING_COMMANDS_FIND_COUNT("-c\\s+(?<count>\\d+)");

    String regex;

    SelectedBuildingCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, ProfileMenuCommands mainRegex) {
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(command);
        if (matcher.find()) return matcher;
        return null;
    }
}

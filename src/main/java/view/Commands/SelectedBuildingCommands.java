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
    SELECTED_BUILDING_COMMANDS_ALL_TROOPS_NAME("(archer|spearMan|maceMan|crossbowMan|pikeMan|swordMan|knight|catapult|trebuchet|" +
            "siegeTower|fireBalista|batteringRam|portableShield|arabianBow|slave|slinger|assassin|horseArcher|arabianSwordMan|fireThrower|blackMonk)"),
    SELECTED_BUILDING_CREATE_UNIT_BUILDINGS_NAME("barracks|mercenary|engineerGuild|big church|small church|siege tent"),
    SELECTED_BUILDING_COMMANDS_CREATE_UNIT("createunit"),
    SELECTED_BUILDING_COMMANDS_REPAIR("repair"),
    SELECTED_BUILDING_COMMANDS_FIND_CREATE_UNIT_TYPE("-t\\s+(?<type>\\w+)"),
    REPAIR_SHOW_NAME("small stone gatehouse|Big Stone Gatehouse|Draw Bridge|" +
            "Lookout Tower|Perimeter Tower|Defend Tower|Square Tower|Round Tower|" +
            "Armoury|Barracks|Mercenary|Engineer Guild|Killing Pit|Oil Smelter|" +
            "Pitch Ditch|Caged War Dogs|Siege Tent|Stable|"),
    SELECTED_BUILDING_COMMANDS_FIND_COUNT("-c\\s+(?<count>\\d+)");



    private Pattern name;

    public Pattern getName() {
        return name;
    }

    public void setName(Pattern name) {
        this.name = name;
    }

    SelectedBuildingCommands(String name) {
        this.name = Pattern.compile(name);
    }

    public static Matcher getMatcher(String input, SelectedBuildingCommands command) {
        Matcher matcher = command.name.matcher(input);
        return matcher.matches() ? matcher : null;
    }
}

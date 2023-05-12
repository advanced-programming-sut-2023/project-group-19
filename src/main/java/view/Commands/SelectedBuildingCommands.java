package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SelectedBuildingCommands {
    SELECTED_BUILDING_TAX_RATE("^taxrate (?<taxRate>\\d+)$"),
    SELECTED_BUILDING_DRAW_BRIDGE("^DrawBridge (?<bridgeCondition>(up|down))$"),
    SELECTED_BUILDING_BARRACKS_TROOP_NAME_CHECK("archer|spearMan|maceMan|crossbowMan|pikeMan|swordMan|knight"),
    SELECTED_BUILDING_SIEGE_TENT_TROOP_NAME_CHECK("catapult|trebuchet|siegeTower|fireBallista|batteringRam|portableShield"),
    SELECTED_BUILDING_MERCENARY_TROOP_NAME_CHECK("arabianBow|slave|slinger|assassin|horseArcher|arabianSwordMan|fireThrower"),
    SELECTED_BUILDING_ENGINEER_GUILD_TROOP_NAME_CHECK("engineer|ladderMan|tunneler"),
    SELECTED_BUILDING_CHURCH_TROOP_NAME_CHECK("blackMonk"),
    SELECTED_BUILDING_COMMANDS_ALL_TROOPS_NAME("archer|spearMan|maceMan|crossbowMan|pikeMan|swordMan|knight|catapult|trebuchet|" +
            "siegeTower|fireBallista|batteringRam|portableShield|arabianBow|slave|slinger|assassin|horseArcher|arabianSwordMan|fireThrower|blackMonk|" +
            "engineer|ladderMan|tunneler"),
    SELECTED_BUILDING_CREATE_UNIT_BUILDINGS_NAME("Barracks|Mercenary|EngineerGuild|BigChurch|SmallChurch|SiegeTent"),
    SELECTED_BUILDING_COMMANDS_CREATE_UNIT(".*createunit.*"),
    SELECTED_BUILDING_COMMANDS_REPAIR("repair"),
    SELECTED_BUILDING_COMMANDS_FIND_CREATE_UNIT_TYPE(".*-t\\s+(?<type>\\w+).*"),
    REPAIR_SHOW_NAME("SmallStoneGatehouse|BigStoneGatehouse|DrawBridge|" +
            "LookoutTower|PerimeterTower|DefendTower|SquareTower|RoundTower|" +
            "Armoury|Barracks|Mercenary|EngineerGuild|KillingPit|OilSmelter|" +
            "PitchDitch|CagedWarDogs|SiegeTent|Stable|"),
    SELECTED_BUILDING_COMMANDS_FIND_COUNT(".*-c\\s+(?<count>\\d+)\\s*");


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

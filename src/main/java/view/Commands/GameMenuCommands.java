package view.Commands;

import view.GameMenu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    SELECT_UNITS("select unit .+"),
    MOVE_UNITS("move unit to .+"),
    COORDINATE_X(".*-x (?<x>\\d+).*"),
    COORDINATE_Y(".*-y (?<y>\\d+).*"),
    TYPE_OF_UNIT(".*-s (?<type>standing|defensive|offensive).*"),
    SET_UNIT("set .+"),
    PATROL_UNIT("patrol unit .+"),
    STOP_PATROL_UNIT("stop patrol .+"),
    COORDINATE_X1(".*-x1 (?<x>\\d+).*"),
    COORDINATE_Y1(".*-y1 (?<y>\\d+).*"),
    COORDINATE_X2(".*-x2 (?<x>\\d+).*"),
    COORDINATE_Y2(".*-y2 (?<y>\\d+).*"),
    ATTACK("attack .+"),
    ATTACK_BY_SOLDIERS(".*-e (?<x>\\d+) (?<y>\\d+).*"),
    POUR_OIL("pour oil .+"),
    DIRECTION(".*-d (?<direction>\\S+).*"),
    DIG_TUNNEL("dig tunnel .+"),
    BUILD_EQUIPMENT("build .+"),
    EQUIPMENT(".*-q (?<equipmentName>\\S+).*"),
    DISBAND_UNIT("disband unit"),
    ENTER_MAP_MENU("Enter MapMenu"),
    TYPE(".*-t (?<type>\\S+).*"),
    DROP_UNIT("dropunit .+"),
    COUNT(".*-c (?<count>\\d+).*"),
    ENTER_SHOP_MENU("Enter ShopMenu"),
    ENTER_TRADE_MENU("Enter TradeMenu"),
    ENTER_BUILDING_MENU("Enter BuildingMenu"),
    ENTER_EMPIRE_MENU("Enter EmpireMenu"),
    NEXT_TURN("Next Turn"),
    TURN_PITCH_DITCH_ON("fire shot .+"),
    CONQUER_GATES("conquer gate .+"),
    ATTACK_BY_BATTERING_RAM("battering ram .+"),
    DIG_DITCH("dig ditch .+"),
    REMOVE_DITCH("remove ditch .+"),
    FILL_DITCH("fill ditch .+"),
    MOVE_BY_SIEGE_TOWER("siegeTower .+"),
    LOGOUT("Logout"),
    ;
    private Pattern name;
    public Pattern getName() {
        return name;
    }

    public void setName(Pattern name) {
        this.name = name;
    }

    GameMenuCommands(String name) {
        this.name = Pattern.compile(name);
    }
    public static Matcher getMatcher(String input, GameMenuCommands command) {
        Matcher matcher = command.name.matcher(input);
        return matcher.matches() ? matcher : null;
    }
}

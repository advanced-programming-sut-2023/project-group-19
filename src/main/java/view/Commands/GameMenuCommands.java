package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    SELECT_UNITS("select unit .+"),
    MOVE_UNITS("move unit to .+"),
    COORDINATE_X("-x (?<x>\\d+)"),
    COORDINATE_Y("-y (?<y>\\d+)"),
    TYPE_OF_UNIT("-s (?<type>standing|defensive|offensive)"),
    SET_UNIT("set\\s+.+"),
    PATROL_UNIT("patrol unit .+"),
    STOP_PATROL_UNIT("stop patrol .+"),
    COORDINATE_X1("-x1 (?<x>.+)"),
    COORDINATE_Y1("-y1 (?<y>.+)"),
    COORDINATE_X2("-x2 (?<x>.+)"),
    COORDINATE_Y2("-y2 (?<y>.+)"),
    ATTACK("attack .+"),
    ATTACK_BY_SOLDIERS("-e (?<enemyx>\\d) (?<enemyy>\\d)"),
    POUR_OIL("pour oil .+"),
    DIRECTION("-d (?<direction>.+)"),
    DIG_TUNNEL("dig tunnel .+"),
    BUILD_EQUIPMENT("build .+"),
    EQUIPMENT("-q (?<equipment name>.+)"),
    DISBAND_UNIT("disband unit"),
    ENTER_MAP_MENU("Enter MapMenu"),
    TYPE("-t (?<type>.+)"),
    DROP_BUILDING("dropbuilding .+"),
    DROP_UNIT("dropunit .+"),
    COUNT("-c (?<count>.+)"),
    ENTER_SHOP_MENU("Enter ShopMenu"),
    ENTER_TRADE_MENU("Enter TradeMenu"),
    ENTER_BUILDING_MENU("Enter BuildingMenu"),
    ENTER_EMPIRE_MENU("Enter EmpireMenu"),
    NEXT_TURN("Next Turn"),
    TURN_PITCH_DITCH_ON("fire shot .+"),
    CONQUER_GATES("conquer gate .+"),
    SET_PORTABLE_SHIELD("set portable shield .+"),
    ATTACK_BY_BATTERING_RAM("battering ram .+"),
    DIG_DITCH("dig ditch .+"),
    REMOVE_DITCH("remove ditch .+"),
    FILL_DITCH("fill ditch .+"),
    MOVE_BY_SIEGE_TOWER("attack siegeTower .+"),
    LOGOUT("Logout"),
    ;
    String regex;
    GameMenuCommands(String regex){
        this.regex = regex ;
    }
    public static Matcher getMatcher(String command , GameMenuCommands mainRegex){
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(command);
        if(matcher.find()) return matcher ;
        return null ;
    }
}

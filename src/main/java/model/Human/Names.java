package model.Human;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Names {
    ARCHER("archer"),
    CROSSBOWMEN("crossbowMen"),
    SPEAR_MEN("spearMen"),
    PIKE_MEN("pikeMen"),
    MACE_MEN("maceMen"),
    SWORDSMEN("swordsMen"),
    KNIGHT("knight"),
    TUNNELER("tunneler"),
    LADDER_MEN("ladderMen"),
    ENGINEER("engineer"),
    BLACK_MONK("blackMonk"),
    ARCHER_BOW("archerBow"),
    SLAVES("slaves"),
    SLINGERS("slingers"),
    ASSASSINS("assassins"),
    HORSE_ARCHERS("horseArchers"),
    ARABIAN_SWORDSMEN("arabianSwordMen"),
    FireThrowers("fireThrowers"),
    CATAPULT("catapult"),
    TREBUCHET("trebuchet"),
    SIEGE_TOWER("siegeTower"),
    FIRE_BALLISTA("fireBallista"),
    BATTERING_RAM("batteringRam"),
    PORTABLE_SHIELD("portableShield"),
    STANDING_ARMY("standing"),
    DEFENSIVE_ARMY("defensive"),
    OFFENSIVE("offensive"),
    PATROL_UNIT("patrolUnit"),
    NORTH("north"),
    SOUTH("south"),
    WEST("west"),
    EAST("east"),
    ;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Names(String s) {
        this.name = s;
    }

    public static Matcher getMatcher(String input, Names names) {
        Matcher matcher = Pattern.compile(names.name).matcher(input);
        return matcher;
    }

}

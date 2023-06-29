package basicGameModel.troop;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Names {
    ARCHER("archer"),
    CROSSBOWMEN("crossbowMen"), //left
    SPEAR_MEN("spearMen"), //left
    PIKE_MEN("pikeMen"), //left
    MACE_MEN("maceMen"),
    SWORDSMEN("swordsMen"),
    KNIGHT("knight"), //left
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
    FireThrowers("fireThrowers"), //left
    CATAPULT("catapult"), //left
    TREBUCHET("trebuchet"),//left
    SIEGE_TOWER("siegeTower"),
    FIRE_BALLISTA("fireBallista"),
    BATTERING_RAM("batteringRam"),
    PORTABLE_SHIELD("portableShield"),//left
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

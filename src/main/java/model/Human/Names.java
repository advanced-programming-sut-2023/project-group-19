package model.Human;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Names {
    ARCHER("Archer"),
    CROSSBOWMEN("Cross Bow Men"),
    SPEAR_MEN("Spear Men"),
    PIKE_MEN("Pike Men"),
    MACE_MEN("Mace Men"),
    SWORDSMEN("Swords Men"),
    KNIGHT("Knight"),
    TUNNELER("Tunneler"),
    LADDER_MEN("Ladder Men"),
    ENGINEER("Engineer"),
    BLACK_MONK("Black Monk"),
    ARCHER_BOW("Archer Bow"),
    SLAVES("Slaves"),
    SLINGERS("Slingers"),
    ASSASSINS("Assassins"),
    HORSE_ARCHERS("Horse Archers"),
    ARABIAN_SWORDSMEN("Arabian Swordsmen"),
    FireThrowers("Fire Throwers"),
    CATAPULT("catapult"),
    TREBUCHET("trebuchet"),
    SIEGE_TOWER("siegeTower"),
    FIRE_BALLISTA("fireBallista"),
    BATTERING_RAM("batteringRam"),
    PORTABLE_SHIELD("portableShield"),
    WORKER("worker"),
    PEASANT("peasant"),
    VERTICAL("Vertical"),
    HORIZONTAL("Horizontal"),
    STANDING_AMRY("standing"),
    DEFENSIVE_ARMY("defensive"),
    OFFENSIVE("offensive"),
    PATROL_UNIT("patrol unit");

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

    public static Matcher getMatcher(String input, model.Human.Names names) {
        Matcher matcher = Pattern.compile(names.name).matcher(input);
        return matcher;
    }

}

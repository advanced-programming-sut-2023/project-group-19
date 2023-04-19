package model.Building;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Names {
    SMALL_STONE_GATE_HOUSE("Small Stone Gatehouse"),
    BIG_STONE_GATE_HOUSE("Big Stone Gatehouse"),
    DRAW_BRIDGE("Draw Bridge"),
    LOOKOUT_TOWER("Lookout Tower"),
    PERIMETER_TOWER("Perimeter Tower"),
    DEFEND_TOWER("Defend Tower"),
    SQUARE_TOWER("Square Tower"),
    ROUND_TOWER("Round Tower"),
    IRON_DIG("Iron Dig"),
    FOOD_PROCESSING_STOCKPILE("Food Processing Stockpile"),
    HOUSE("House"),
    TURRET("Turret"),
    RANGED_UNITS("Ranged Units"),
    MOUNTED_SIEGE_EQUIPMENT("Mounted Siege Equipment"),
    ARMOURY("Armoury"),
    ARMOUR("Armour"),
    TUNNEL("Tunnel"),
    WEAPON("Weapon"),
    WORKSHOP("Workshop"),
    BARRACK("Barrack"),
    TROOP("Troop"),
    PEASANT("Peasant"),
    ENGINEER_GUILD("Engineer Guild"),
    KILLING_PIT("Killing Pit"),
    STABLE("Stable"),
    APPLE_FARM("Apple Farm"),
    DAIRY_PRODUCT("Dairy Product"),
    OAT_FARM("Oat Farm"),
    HUNTING_POST("Hunting Post"),
    WHEAT_FARM("Wheat Farm"),
    BAKERY("Bakery"),
    BEAR_FACTORY("Bear Factory"),
    KEEPS_AND_STRONGHOLDS("Keeps And Strongholds"),
    TOWERS_AND_GATE_HORSES("Towers And Gate Horses"),
    TOWN_BUILDINGS("Town Buildings"),
    WEAPON_BUILDINGS("Weapon Buildings"),
    HOVEL("Hovels"),
    WELL("Well"),
    SMALL_CHURCH("Small Church"),
    BIG_CHURCH("Big Church"),
    CATHEDRAL("Cathedral"),
    BIG_WALL("Big Wall"),
    SMALL_WALL("Small Wall"),
    STAIR("Stair"),
    WATER_POT("Water Pot"),
    GOOD_THINGS("Good Things"),
    BAD_THINGS("Bad Things"),
    ARMOURER("Armourer"),
    BLACKSMITH("Blacksmith"),
    FLETCHER("Fletcher"),
    POLE_TURNER("Pole Turner"),
    SIGNPOST("Signpost"),
    TUNNEL_ENTRANCE("Tunnel Entrance"),
    APOTHECARY("Apothecary"),
    WOODCUTTER("Wood Cutter"),
    STOCKPILE("Stockpile"),
    QUARRY("Quarry"),
    PITCH_RIG("Pitch Rig"),
    MARKET("Market"),
    OX_TETHER("Ox Tether"),
    MILL("Mill"),
    INN("Inn"),
    DITCH("Ditch"),
    BRAZIER("Brazier"),
    SIEGE("Siege"),
    TUNNELER("Tunneler"),
    HOP("Hop"),
    MERCENARY_POST("Mercenary Post"),
    LADDER_MAN("Ladder Man"),
    OIL_SMELTER("Oil Smelter"),
    PITCH_DITCH("Pitch Ditch"),
    CAGED_WAR_DOGS("Caged War Dogs"),
    SIEGE_TENT("Siege Tent");
    private String name;

    Names(String s) {
        this.name = s;
    }

    public static Matcher getMatcher(String input, Names names) {
        Matcher matcher = Pattern.compile(names.name).matcher(input);
        return matcher;
    }
}
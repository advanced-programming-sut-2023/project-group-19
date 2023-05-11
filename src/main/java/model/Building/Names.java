package model.Building;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Names {
    CASTLE("Castle"),
    SMALL_STONE_GATE_HOUSE("SmallStoneGatehouse"),
    BIG_STONE_GATE_HOUSE("BigStoneGatehouse"),
    DRAW_BRIDGE("DrawBridge"),
    LOOKOUT_TOWER("LookoutTower"),
    PERIMETER_TOWER("PerimeterTower"),
    DEFEND_TOWER("DefendTower"),
    SQUARE_TOWER("SquareTower"),
    ROUND_TOWER("RoundTower"),
    IRON_DIG("IronDig"),
    FOOD_STOCKPILE("FoodStockpile"),
    HOUSE("House"),
    ARMOURY("Armoury"),
    TORTURE_CHAMBER("TortureChamber"),
    GARDEN("garden"),
    TUNNEL("Tunnel"),
    BARRACK("Barracks"),
    TROOP("Troop"),
    PEASANT("Peasant"),
    ENGINEER_GUILD("EngineerGuild"),
    KILLING_PIT("KillingPit"),
    STABLE("Stable"),
    APPLE_FARM("AppleFarm"),
    DAIRY_PRODUCT("DairyProduct"),
    OAT_FARM("OatFarm"),
    HUNTING_POST("HuntingPost"),
    WHEAT_FARM("WheatFarm"),
    BAKERY("Bakery"),
    BEAR_FACTORY("BearFactory"),
    SMALL_CHURCH("SmallChurch"),
    BIG_CHURCH("BigChurch"),
    CATHEDRAL("Cathedral"),
    BIG_WALL("BigWall"),
    SMALL_WALL("SmallWall"),
    STAIR("Stair"),
    ARMOURER("Armourer"),
    BLACKSMITH("Blacksmith"),
    FLETCHER("Fletcher"),
    POLE_TURNER("PoleTurner"),
    SIGNPOST("Signpost"),
    TUNNEL_ENTRANCE("TunnelEntrance"),
    APOTHECARY("Apothecary"),
    WOODCUTTER("WoodCutter"),
    RESOURCES_STOCKPILE("ResourcesStockpile"),
    QUARRY("Quarry"),
    PITCH_RIG("PitchRig"),
    MARKET("Market"),
    OX_TETHER("OxTether"),
    MILL("Mill"),
    INN("Inn"),
    DITCH("Ditch"),
    BRAZIER("Brazier"),
    SIEGE("Siege"),
    TUNNELER("Tunneler"),
    HOP("Hop"),
    SHOP("Shop"),
    MERCENARY_POST("MercenaryPost"),
    LADDER_MAN("LadderMan"),
    OIL_SMELTER("OilSmelter"),
    PITCH_DITCH("PitchDitch"),
    CAGED_WAR_DOGS("CagedWarDogs"),
    SIEGE_TENT("SiegeTent"),
    NS("North-South"),
    WE("West-East"),
    BIG_WALL_THICKNESS("Thick"),
    SMALL_WALL_THICKNESS("Thin"),;
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
        return Pattern.compile(names.name).matcher(input);
    }
}
package basicGameModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Names {
    CASTLE("Castle"),
    SMALL_STONE_GATE_HOUSE("SmallStoneGatehouse"),
    APOTHECARY("Apothecary"),
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
    GARDEN("Garden"),
    TUNNEL("Tunnel"),
    BARRACK("Barracks"),
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
    BIG_WALL("BigWall"),
    SMALL_WALL("SmallWall"),
    STAIR("Stair"),
    ARMOURER("Armourer"),
    BLACKSMITH("Blacksmith"),
    FLETCHER("Fletcher"),
    POLE_TURNER("PoleTurner"),
    WOODCUTTER("WoodCutter"),
    RESOURCES_STOCKPILE("ResourcesStockpile"),
    QUARRY("Quarry"),
    PITCH_RIG("PitchRig"),
    OX_TETHER("OxTether"),
    MILL("Mill"),
    INN("Inn"),
    TUNNELER("Tunneler"),
    SHOP("Shop"),
    MERCENARY_POST("Mercenary"),
    OIL_SMELTER("OilSmelter"),
    PITCH_DITCH("PitchDitch"),
    CAGED_WAR_DOGS("CagedWarDogs"),
    SIEGE_TENT("SiegeTent"),
    NS("North-South"),
    WE("West-East");
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
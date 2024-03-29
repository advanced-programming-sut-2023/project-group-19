package model;

import java.util.ArrayList;

public class Manage {
    public static ArrayList<Empire> allEmpires = new ArrayList<>();
    public final static ArrayList<String> namesOfAllPossibleBuildings = new ArrayList<>();

    static {
        namesOfAllPossibleBuildings.add("Armoury");
        namesOfAllPossibleBuildings.add("Barracks");
        namesOfAllPossibleBuildings.add("CagedWarDogs");
        namesOfAllPossibleBuildings.add("SmallChurch");
        namesOfAllPossibleBuildings.add("BigChurch");
        namesOfAllPossibleBuildings.add("DrawBridge");
        namesOfAllPossibleBuildings.add("AppleFarm");
        namesOfAllPossibleBuildings.add("DairyProduct");
        namesOfAllPossibleBuildings.add("OatFarm");
        namesOfAllPossibleBuildings.add("HuntingPost");
        namesOfAllPossibleBuildings.add("WheatFarm");
        namesOfAllPossibleBuildings.add("Bakery");
        namesOfAllPossibleBuildings.add("BeerFactory");
        namesOfAllPossibleBuildings.add("House");
        namesOfAllPossibleBuildings.add("Mill");
        namesOfAllPossibleBuildings.add("IronDig");
        namesOfAllPossibleBuildings.add("OxTether");
        namesOfAllPossibleBuildings.add("OilSmelter");
        namesOfAllPossibleBuildings.add("Quarry");
        namesOfAllPossibleBuildings.add("WoodCutter");
        namesOfAllPossibleBuildings.add("Inn");
        namesOfAllPossibleBuildings.add("KillingPit");
        namesOfAllPossibleBuildings.add("Market");
        namesOfAllPossibleBuildings.add("PitchRig");
        namesOfAllPossibleBuildings.add("PitchDitch");
        namesOfAllPossibleBuildings.add("Mercenary");
        namesOfAllPossibleBuildings.add("EngineerGuild");
        namesOfAllPossibleBuildings.add("TunnelerGuild");
        namesOfAllPossibleBuildings.add("Shop");
        namesOfAllPossibleBuildings.add("SiegeTent");
        namesOfAllPossibleBuildings.add("Stable");
        namesOfAllPossibleBuildings.add("ResourcesStockpile");
        namesOfAllPossibleBuildings.add("FoodStockpile");
        namesOfAllPossibleBuildings.add("SmallStoneGatehouse");
        namesOfAllPossibleBuildings.add("BigStoneGatehouse");
        namesOfAllPossibleBuildings.add("LookoutTower");
        namesOfAllPossibleBuildings.add("PerimeterTower");
        namesOfAllPossibleBuildings.add("DefendTower");
        namesOfAllPossibleBuildings.add("SquareTower");
        namesOfAllPossibleBuildings.add("RoundTower");
        namesOfAllPossibleBuildings.add("Tunnel");
        namesOfAllPossibleBuildings.add("BigWall");
        namesOfAllPossibleBuildings.add("SmallWall");
        namesOfAllPossibleBuildings.add("Stair");
        namesOfAllPossibleBuildings.add("Castle");
        namesOfAllPossibleBuildings.add("Armourer");
        namesOfAllPossibleBuildings.add("Blacksmith");
        namesOfAllPossibleBuildings.add("Fletcher");
        namesOfAllPossibleBuildings.add("PoleTurner");
        namesOfAllPossibleBuildings.add("Garden");
        namesOfAllPossibleBuildings.add("TortureChamber");
    }

    private static Empire currentEmpire;
    public static ArrayList<User> allUsers = new ArrayList<>();

    public static Empire getCurrentEmpire() {
        return currentEmpire;
    }

    public static ArrayList<String> getNamesOfAllPossibleBuildings() {
        return namesOfAllPossibleBuildings;
    }

    public static void setCurrentEmpire(Empire currentEmpire) {
        Manage.currentEmpire = currentEmpire;
    }

    public static ArrayList<Empire> getAllEmpires() {
        return allEmpires;
    }

    public static Empire getEmpireByNickname(String nickname) {
        for (Empire empire : allEmpires) {
            if (empire.getName().equals(nickname)) {
                return empire;
            }
        }
        return null;
    }
}

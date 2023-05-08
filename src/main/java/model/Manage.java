package model;

import javax.print.attribute.standard.MediaSize;
import java.security.PublicKey;
import java.util.ArrayList;

public class Manage {
    public static ArrayList<Empire> allEmpires = new ArrayList<>();
    public final static ArrayList<String> namesOfAllPossibleBuildings = new ArrayList<>();

    {
        namesOfAllPossibleBuildings.add("Armoury");
        namesOfAllPossibleBuildings.add("Barracks");
        namesOfAllPossibleBuildings.add("Caged War Dogs");
        namesOfAllPossibleBuildings.add("Small Church");
        namesOfAllPossibleBuildings.add("Big Church");
        namesOfAllPossibleBuildings.add("Draw Bridge");
        namesOfAllPossibleBuildings.add("Apple Farm");
        namesOfAllPossibleBuildings.add("Dairy Product");
        namesOfAllPossibleBuildings.add("Oat Farm");
        namesOfAllPossibleBuildings.add("Hunting Post");
        namesOfAllPossibleBuildings.add("Wheat Farm");
        namesOfAllPossibleBuildings.add("Bakery");
        namesOfAllPossibleBuildings.add("Beer Factory");
        namesOfAllPossibleBuildings.add("House");
        namesOfAllPossibleBuildings.add("Mill");
        namesOfAllPossibleBuildings.add("Iron Dig");
        namesOfAllPossibleBuildings.add("Ox Tether");
        namesOfAllPossibleBuildings.add("Oil Smelter");
        namesOfAllPossibleBuildings.add("Quarry");
        namesOfAllPossibleBuildings.add("Wood Cutter");
        namesOfAllPossibleBuildings.add("Inn");
        namesOfAllPossibleBuildings.add("Killing Pit");
        namesOfAllPossibleBuildings.add("Market");
        namesOfAllPossibleBuildings.add("Pitch Ditch");
        namesOfAllPossibleBuildings.add("Mercenary");
        namesOfAllPossibleBuildings.add("Engineer Guild");
        namesOfAllPossibleBuildings.add("Tunneler Guild");
        namesOfAllPossibleBuildings.add("Shop");
        namesOfAllPossibleBuildings.add("Siege Tent");
        namesOfAllPossibleBuildings.add("Stable");
        namesOfAllPossibleBuildings.add("Resources Stockpile");
        namesOfAllPossibleBuildings.add("Food Stockpile");
        namesOfAllPossibleBuildings.add("Small Stone Gatehouse");
        namesOfAllPossibleBuildings.add("Big Stone Gatehouse");
        namesOfAllPossibleBuildings.add("Lookout Tower");
        namesOfAllPossibleBuildings.add("Perimeter Tower");
        namesOfAllPossibleBuildings.add("Defend Tower");
        namesOfAllPossibleBuildings.add("Square Tower");
        namesOfAllPossibleBuildings.add("Round Tower");
        namesOfAllPossibleBuildings.add("Tunnel");
        namesOfAllPossibleBuildings.add("Well");
        namesOfAllPossibleBuildings.add("Water Pot");
        namesOfAllPossibleBuildings.add("Big Wall");
        namesOfAllPossibleBuildings.add("Small Wall");
        namesOfAllPossibleBuildings.add("Stair");
        namesOfAllPossibleBuildings.add("Castle");
        namesOfAllPossibleBuildings.add("Armourer");
        namesOfAllPossibleBuildings.add("Blacksmith");
        namesOfAllPossibleBuildings.add("Fletcher");
        namesOfAllPossibleBuildings.add("Pole Turner");
    }

    private static Empire currentEmpire;
    public static ArrayList<User> allUsers = new ArrayList<>();

    public static Empire getCurrentEmpire() {
        return currentEmpire;
    }

    public static ArrayList<String> getNamesOfAllPossibleBuildings() {
        return getNamesOfAllPossibleBuildings();
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

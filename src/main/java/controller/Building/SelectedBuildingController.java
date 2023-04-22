package controller.Building;

import model.Building.Building;
import model.Building.DrawBridge;
import model.Empire;

import java.util.HashMap;

public class SelectedBuildingController {
    public static Empire empire;
    public static Building selectedBuilding;

    public SelectedBuildingMessages gatehouse(int taxRate) {
        if (taxRate > 7 | taxRate < -3) {
            empire.setTaxRateNumber(taxRate);
            return SelectedBuildingMessages.TAX_RATE_CHANGE_SUCCESSFUL;
        }
        return SelectedBuildingMessages.TAX_RATE_OUT_OF_BONDS;
    }

    public SelectedBuildingMessages DrawBridge(String bridgeCondition) {
        if (bridgeCondition.equals("down")) {
            if (((DrawBridge) selectedBuilding).bridgeOpen)
                return SelectedBuildingMessages.BRIDGE_ALREADY_OPEN;
            ((DrawBridge) selectedBuilding).setBridgeOpen(true);
            return SelectedBuildingMessages.BRIDGE_OPENED;
        } else {
            if (!((DrawBridge) selectedBuilding).bridgeOpen)
                return SelectedBuildingMessages.BRIDGE_ALREADY_CLOSE;
            ((DrawBridge) selectedBuilding).setBridgeOpen(true);
            return SelectedBuildingMessages.BRIDGE_CLOSED;
        }
    }

    public SelectedBuildingMessages empireHasEnoughRecoursesToBuyTroopFromBlacksmith(Empire empire, HashMap<String, Integer> listOfTroopsBuyPrice,
                                                                                     HashMap<String, Integer> allWeaponTools, String troopName, int troopCount) {
        int empiresGoldCount = empire.getGoldCount();
        int empiresPeasantCount = empire.getPeasantCount();
        if (empiresPeasantCount < troopCount) return SelectedBuildingMessages.NOT_ENOUGH_PEASANTS;
        int totalBuyPrice = listOfTroopsBuyPrice.get(troopName) * troopCount;
        if (totalBuyPrice > empiresGoldCount) return SelectedBuildingMessages.NOT_ENOUGH_GOLD;
        int requiredLeatherArmour = allWeaponTools.get("leatherArmour") * troopCount;
        int requiredMetalArmour = allWeaponTools.get("metalArmour") * troopCount;
        int requiredBow = allWeaponTools.get("bow") * troopCount;
        int requiredSword = allWeaponTools.get("sword") * troopCount;
        int requiredMace = allWeaponTools.get("mace") * troopCount;
        int requiredSpear = allWeaponTools.get("spear") * troopCount;
        int requiredPeak = allWeaponTools.get("peak") * troopCount;
        int requiredHorse = allWeaponTools.get("horse") * troopCount;
        if (!(requiredLeatherArmour < empire.getLeatherArmourCount() && requiredMetalArmour < empire.getMetalArmourCount()
                && requiredBow < empire.getBowCount() && requiredSword < empire.getSwordCount()
                && requiredMace < empire.getMaceCount() && requiredSpear < empire.getSpearCount()
                && requiredPeak < empire.getPeakCount() && requiredHorse < empire.getHorseCount())) {
            return SelectedBuildingMessages.NOT_ENOUGH_RESOURCES;
        }
        return SelectedBuildingMessages.ENOUGH_RESOURCES;
    }

    public void blacksmith(String troopName, int count) {
        HashMap<String, Integer> listOfTroopsBuyPrice = new HashMap<>(); // good name and its buy price

        {
            listOfTroopsBuyPrice.put("archer", 8);
            listOfTroopsBuyPrice.put("spearMan", 8);
            listOfTroopsBuyPrice.put("maceMan", 20);
            listOfTroopsBuyPrice.put("crossbowMan", 20);
            listOfTroopsBuyPrice.put("pikeMan", 20);
            listOfTroopsBuyPrice.put("swordMan", 40);
            listOfTroopsBuyPrice.put("knight", 40);
        }
        HashMap<String, Integer> allWeaponTools = new HashMap<>();

        {
            allWeaponTools.put("leatherArmour", 0);
            allWeaponTools.put("metalArmour", 0);
            allWeaponTools.put("bow", 0);
            allWeaponTools.put("sword", 0);
            allWeaponTools.put("mace", 0);
            allWeaponTools.put("spear", 0);
            allWeaponTools.put("peak", 0);
            allWeaponTools.put("horse", 0);
        }
        switch (troopName) {
            case "archer":
                allWeaponTools.replace("bow", 1);
            case "spearMan":
                allWeaponTools.replace("spear", 1);
            case "maceMan":
                allWeaponTools.replace("mace", 1);
                allWeaponTools.replace("leatherArmour", 1);
            case "crossbowMan":
                allWeaponTools.replace("bow", 1);
                allWeaponTools.replace("leatherArmour", 1);
            case "pikeMan":
                allWeaponTools.replace("peak", 1);
                allWeaponTools.replace("metalArmour", 1);
            case "swordMan":
                allWeaponTools.replace("sword", 1);
                allWeaponTools.replace("metalArmour", 1);
            case "knight":
                allWeaponTools.replace("sword", 1);
                allWeaponTools.replace("metalArmour", 1);
                allWeaponTools.replace("horse", 1);
        }

    }

    public void mercenary(String troopName, int count) {

    }

    public void engineerGuild(String troopName, int count) {

    }

    public void shop() {

    }

    public void cagedWarDogs() {
        //TODO : should be filled when we started the game menu
    }

    public void siegeTent(String siegeName, int count) {

    }


}

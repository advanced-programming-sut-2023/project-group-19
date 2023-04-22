package controller.Building;

import model.Building.Building;
import model.Building.DrawBridge;
import model.Empire;
import model.Human.Troop.ArabArmy;
import model.Human.Troop.Army;
import model.Human.Troop.Engineer;
import model.Human.Troop.EuropeArmy;
import model.Map;

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
            if (((DrawBridge) selectedBuilding).bridgeOpen) return SelectedBuildingMessages.BRIDGE_ALREADY_OPEN;
            ((DrawBridge) selectedBuilding).setBridgeOpen(true);
            int x = ((DrawBridge) selectedBuilding).getX();
            int y = ((DrawBridge) selectedBuilding).getY();
            Map.notPassable[x][y] = false;
            return SelectedBuildingMessages.BRIDGE_OPENED;
        } else {
            if (!((DrawBridge) selectedBuilding).bridgeOpen) return SelectedBuildingMessages.BRIDGE_ALREADY_CLOSE;
            ((DrawBridge) selectedBuilding).setBridgeOpen(true);
            int x = ((DrawBridge) selectedBuilding).getX();
            int y = ((DrawBridge) selectedBuilding).getY();
            Map.notPassable[x][y] = true;
            return SelectedBuildingMessages.BRIDGE_CLOSED;
        }
    }

    public void buyFromBarracks(Empire empire, int troopPrice, HashMap<String, Integer> allWeaponTools, String troopName, int troopCount) {
        empire.setGoldCount(empire.getGoldCount() - troopPrice * troopCount);
        empire.setPeasantCount(empire.getPeasantCount() - troopCount);
        empire.setTroopCount(empire.getTroopCount() + troopCount);
        empire.setTroopCount(empire.getTroopCount() + troopCount);
        empire.setLeatherArmourCount(empire.getLeatherArmourCount() - allWeaponTools.get("leatherArmour") * troopCount);
        empire.setMetalArmourCount(empire.getMetalArmourCount() - allWeaponTools.get("metalArmour") * troopCount);
        empire.setBowCount(empire.getBowCount() - allWeaponTools.get("bow") * troopCount);
        empire.setSwordCount(empire.getSwordCount() - allWeaponTools.get("sword") * troopCount);
        empire.setMaceCount(empire.getMaceCount() - allWeaponTools.get("mace") * troopCount);
        empire.setSpearCount(empire.getSpearCount() - allWeaponTools.get("spear") * troopCount);
        empire.setPeakCount(empire.getPeakCount() - allWeaponTools.get("peak") * troopCount);
        empire.setHorseCount(empire.getHorseCount() - allWeaponTools.get("horse") * troopCount);
        switch (troopName) { //add troops created to the empire army
            case "archer":
                empire.setEuropeArcherCount(empire.getEuropeArcherCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    EuropeArmy archer = new EuropeArmy(empire);
                    empire.empireArmy.add(archer);
                }
            case "spearMan":
                empire.setSpearManCount(empire.getSpearManCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    EuropeArmy spearMan = new EuropeArmy(empire);
                    empire.empireArmy.add(spearMan);
                }
            case "maceMan":
                empire.setMaceManCount(empire.getMaceManCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    EuropeArmy maceMan = new EuropeArmy(empire);
                    empire.empireArmy.add(maceMan);
                }
            case "crossbowMan":
                empire.setCrossbowManCount(empire.getCrossbowManCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    EuropeArmy crossbowMan = new EuropeArmy(empire);
                    empire.empireArmy.add(crossbowMan);
                }
            case "pikeMan":
                empire.setPikeManCount(empire.getPikeManCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    EuropeArmy pikeMan = new EuropeArmy(empire);
                    empire.empireArmy.add(pikeMan);
                }
            case "swordMan":
                empire.setSwordManCount(empire.getSwordManCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    EuropeArmy swordMan = new EuropeArmy(empire);
                    empire.empireArmy.add(swordMan);
                }
            case "knight":
                empire.setKnightCount(empire.getKnightCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    EuropeArmy knight = new EuropeArmy(empire);
                    empire.empireArmy.add(knight);
                }
        }
    }

    public SelectedBuildingMessages empireHasEnoughRecoursesToBuyTroopFromBarracks(Empire empire, HashMap<String, Integer> listOfTroopsBuyPrice, HashMap<String, Integer> allWeaponTools, String troopName, int troopCount) {
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
        if (!(requiredLeatherArmour < empire.getLeatherArmourCount() && requiredMetalArmour < empire.getMetalArmourCount() && requiredBow < empire.getBowCount() && requiredSword < empire.getSwordCount() && requiredMace < empire.getMaceCount() && requiredSpear < empire.getSpearCount() && requiredPeak < empire.getPeakCount() && requiredHorse < empire.getHorseCount())) {
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
                if (empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromBarracks(empire, listOfTroopsBuyPrice.get("archer"), allWeaponTools, troopName, count);
                }
            case "spearMan":
                allWeaponTools.replace("spear", 1);
                if (empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromBarracks(empire, listOfTroopsBuyPrice.get("spearMan"), allWeaponTools, troopName, count);
                }
            case "maceMan":
                allWeaponTools.replace("mace", 1);
                allWeaponTools.replace("leatherArmour", 1);
                if (empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromBarracks(empire, listOfTroopsBuyPrice.get("maceMan"), allWeaponTools, troopName, count);
                }
            case "crossbowMan":
                allWeaponTools.replace("bow", 1);
                allWeaponTools.replace("leatherArmour", 1);
                if (empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromBarracks(empire, listOfTroopsBuyPrice.get("crossbowMan"), allWeaponTools, troopName, count);
                }
            case "pikeMan":
                allWeaponTools.replace("peak", 1);
                allWeaponTools.replace("metalArmour", 1);
                if (empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromBarracks(empire, listOfTroopsBuyPrice.get("pikeMan"), allWeaponTools, troopName, count);
                }
            case "swordMan":
                allWeaponTools.replace("sword", 1);
                allWeaponTools.replace("metalArmour", 1);
                if (empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromBarracks(empire, listOfTroopsBuyPrice.get("swordMan"), allWeaponTools, troopName, count);
                }
            case "knight":
                allWeaponTools.replace("sword", 1);
                allWeaponTools.replace("metalArmour", 1);
                allWeaponTools.replace("horse", 1);
                if (empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromBarracks(empire, listOfTroopsBuyPrice.get("knight"), allWeaponTools, troopName, count);
                }
        }

    }

    public void buyFromMercenary(Empire empire, int troopPrice, String troopName, int troopCount) {
        empire.setGoldCount(empire.getGoldCount() - troopPrice * troopCount);
        empire.setPeasantCount(empire.getPeasantCount() - troopCount);
        empire.setTroopCount(empire.getTroopCount() + troopCount);
        switch (troopName) {
            case "arabianBow":
                empire.setArabianBowCount(empire.getArabianBowCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    ArabArmy arabianBow = new ArabArmy(empire);
                    empire.empireArmy.add(arabianBow);
                }
            case "slave":
                empire.setSlaveCount(empire.getSlaveCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    ArabArmy slave = new ArabArmy(empire);
                    empire.empireArmy.add(slave);
                }
            case "slinger":
                empire.setSlingerCount(empire.getSlingerCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    ArabArmy slinger = new ArabArmy(empire);
                    empire.empireArmy.add(slinger);
                }
            case "assassin":
                empire.setAssassinCount(empire.getAssassinCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    ArabArmy assassin = new ArabArmy(empire);
                    empire.empireArmy.add(assassin);
                }
            case "horseArcher":
                empire.setHorseArcherCount(empire.getHorseArcherCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    ArabArmy horseArcher = new ArabArmy(empire);
                    empire.empireArmy.add(horseArcher);
                }
            case "arabianSwordMan":
                empire.setArabianSwordManCount(empire.getArabianSwordManCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    ArabArmy arabianSwordMan = new ArabArmy(empire);
                    empire.empireArmy.add(arabianSwordMan);
                }
            case "fireThrower":
                empire.setFireThrowerCount(empire.getFireThrowerCount() + troopCount);
                for (int i = 0; i < troopCount; i++) {
                    ArabArmy fireThrower = new ArabArmy(empire);
                    empire.empireArmy.add(fireThrower);
                }
        }
    }

    public void mercenary(String troopName, int count) {
        int empireGoldCount = empire.getGoldCount();
        HashMap<String, Integer> listOfTroopsBuyPrice = new HashMap<>(); // good name and its buy price

        {
            listOfTroopsBuyPrice.put("arabianBow", 75);
            listOfTroopsBuyPrice.put("slave", 5);
            listOfTroopsBuyPrice.put("slinger", 15);
            listOfTroopsBuyPrice.put("assassin", 60);
            listOfTroopsBuyPrice.put("horseArcher", 10);
            listOfTroopsBuyPrice.put("arabianSwordMan", 10);
            listOfTroopsBuyPrice.put("fireThrower", 100);
        }
        switch (troopName) {
            case "arabianBow":
                if (empireGoldCount > listOfTroopsBuyPrice.get("arabianBow") * count) {
                    buyFromMercenary(empire, listOfTroopsBuyPrice.get("arabianBow"), troopName, count);
                }
            case "slave":
                if (empireGoldCount > listOfTroopsBuyPrice.get("slave") * count) {
                    buyFromMercenary(empire, listOfTroopsBuyPrice.get("slave"), troopName, count);
                }
            case "slinger":
                if (empireGoldCount > listOfTroopsBuyPrice.get("slinger") * count) {
                    buyFromMercenary(empire, listOfTroopsBuyPrice.get("slinger"), troopName, count);
                }
            case "assassin":
                if (empireGoldCount > listOfTroopsBuyPrice.get("assassin") * count) {
                    buyFromMercenary(empire, listOfTroopsBuyPrice.get("assassin"), troopName, count);
                }
            case "horseArcher":
                if (empireGoldCount > listOfTroopsBuyPrice.get("horseArcher") * count) {
                    buyFromMercenary(empire, listOfTroopsBuyPrice.get("horseArcher"), troopName, count);
                }
            case "arabianSwordMan":
                if (empireGoldCount > listOfTroopsBuyPrice.get("arabianSwordMan") * count) {
                    buyFromMercenary(empire, listOfTroopsBuyPrice.get("arabianSwordMan"), troopName, count);
                }
            case "fireThrower":
                if (empireGoldCount > listOfTroopsBuyPrice.get("fireThrower") * count) {
                    buyFromMercenary(empire, listOfTroopsBuyPrice.get("fireThrower"), troopName, count);
                }
        }
    }

    public void buyFromEngineerGuild(Empire empire, int troopPrice, String troopName, int troopCount) {
        empire.setGoldCount(empire.getGoldCount() - troopPrice * troopCount);
        empire.setPeasantCount(empire.getPeasantCount() - troopCount);
        switch (troopName) {
            case "engineer":
                empire.setEngineerCount(empire.getEngineerCount() + troopCount);
                empire.setEngineerCount(empire.getEngineerCount() + troopCount);
            case "ladderMan":
                empire.setLadderManCount(empire.getLadderManCount() + troopCount);
                empire.setLadderManCount(empire.getLadderManCount() + troopCount);
            case "tunneler":
                empire.setTunnelerCount(empire.getTunnelerCount() + troopCount);
                empire.setTunnelerCount(empire.getTunnelerCount() + troopCount);
        }
    }

    public void engineerGuild(String troopName, int count) {
        int empireGoldCount = empire.getGoldCount();
        HashMap<String, Integer> engineerGuildTroopPrice = new HashMap<>();

        {
            engineerGuildTroopPrice.put("engineer", 30);
            engineerGuildTroopPrice.put("ladderMan", 5);
            engineerGuildTroopPrice.put("tunneler", 30);
        }
        switch (troopName) {
            case "arabianBow":
                if (empireGoldCount > engineerGuildTroopPrice.get("arabianBow") * count) {
                    buyFromMercenary(empire, engineerGuildTroopPrice.get("arabianBow"), troopName, count);
                }
            case "slave":
                if (empireGoldCount > engineerGuildTroopPrice.get("slave") * count) {
                    buyFromMercenary(empire, engineerGuildTroopPrice.get("slave"), troopName, count);
                }
            case "slinger":
                if (empireGoldCount > engineerGuildTroopPrice.get("slinger") * count) {
                    buyFromMercenary(empire, engineerGuildTroopPrice.get("slinger"), troopName, count);
                }
        }

    }

    public void shop() {
        //TODO : pass this function to the shop menu


    }

    public void cagedWarDogs() {
        //TODO : should be filled when we started the game menu


    }

    public void siegeTent(String siegeName, int count) {

    }


}

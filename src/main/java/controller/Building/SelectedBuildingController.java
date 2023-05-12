package controller.Building;

import controller.GameController;
import model.Building.Building;
import model.Building.DrawBridge;
import model.Empire;
import model.Manage;
import model.Map;
import view.Messages.SelectedBuildingMessages;

import java.util.HashMap;
import java.util.regex.Matcher;

public class SelectedBuildingController {
    public static Empire empire = Manage.getCurrentEmpire();
    public static Building selectedBuilding;
    public SelectedBuildingMessages gatehouse(Matcher matcherTaxRate) {
        int taxRate = Integer.parseInt(matcherTaxRate.group("taxRate"));
        if (taxRate > 7 | taxRate < -3) {
            empire.setTaxRateNumber(taxRate);
            return SelectedBuildingMessages.TAX_RATE_CHANGE_SUCCESSFUL;
        }
        return SelectedBuildingMessages.TAX_RATE_OUT_OF_BONDS;
    }

    public SelectedBuildingMessages drawBridge(Matcher matcherBridgeCondition) {
        String bridgeCondition = matcherBridgeCondition.group("bridgeCondition");
        if (GameController.enemyInRange(((DrawBridge) selectedBuilding).getX(), ((DrawBridge) selectedBuilding).getY())) {
            int x = ((DrawBridge) selectedBuilding).getX();
            int y = ((DrawBridge) selectedBuilding).getY();
            Map.notPassable[x][y] = false;
            return SelectedBuildingMessages.ENEMY_IN_RANGE;
        } else if (bridgeCondition.equals("down")) {
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
        empire.setLeatherArmourCount(empire.getLeatherArmourCount() - allWeaponTools.get("leatherArmour") * troopCount);
        empire.setMetalArmourCount(empire.getMetalArmourCount() - allWeaponTools.get("metalArmour") * troopCount);
        empire.setBowCount(empire.getBowCount() - allWeaponTools.get("bow") * troopCount);
        empire.setSwordCount(empire.getSwordCount() - allWeaponTools.get("sword") * troopCount);
        empire.setMaceCount(empire.getMaceCount() - allWeaponTools.get("mace") * troopCount);
        empire.setSpearCount(empire.getSpearCount() - allWeaponTools.get("spear") * troopCount);
        empire.setPeakCount(empire.getPeakCount() - allWeaponTools.get("peak") * troopCount);
        empire.setHorseCount(empire.getHorseCount() - allWeaponTools.get("horse") * troopCount);
        switch (troopName) {
            case "archer":
                empire.setEuropeArcherCount(empire.getEuropeArcherCount() + troopCount);
            case "spearMan":
                empire.setSpearManCount(empire.getSpearManCount() + troopCount);
            case "maceMan":
                empire.setMaceManCount(empire.getMaceManCount() + troopCount);
            case "crossbowMan":
                empire.setCrossbowManCount(empire.getCrossbowManCount() + troopCount);
            case "pikeMan":
                empire.setPikeManCount(empire.getPikeManCount() + troopCount);
            case "swordMan":
                empire.setSwordManCount(empire.getSwordManCount() + troopCount);
            case "knight":
                empire.setKnightCount(empire.getKnightCount() + troopCount);
        }
    }

    public SelectedBuildingMessages empireHasEnoughRecoursesToBuyTroopFromBarracks(Empire empire, HashMap<String, Integer> listOfTroopsBuyPrice,
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
                && requiredBow < empire.getBowCount() && requiredSword < empire.getSwordCount() &&
                requiredMace < empire.getMaceCount() && requiredSpear < empire.getSpearCount() &&
                requiredPeak < empire.getPeakCount() && requiredHorse < empire.getHorseCount())) {
            return SelectedBuildingMessages.NOT_ENOUGH_RESOURCES;
        }
        return SelectedBuildingMessages.ENOUGH_RESOURCES;
    }

    public SelectedBuildingMessages Barracks(Matcher matcherTroopName, Matcher matcherCount) {
        String troopName = matcherTroopName.group("type");
        int count = Integer.parseInt(matcherCount.group("count"));
        HashMap<String, Integer> listOfTroopsBuyPrice = new HashMap<>();

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
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count);
                }
            case "spearMan":
                allWeaponTools.replace("spear", 1);
                if (empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromBarracks(empire, listOfTroopsBuyPrice.get("spearMan"), allWeaponTools, troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count);
                }
            case "maceMan":
                allWeaponTools.replace("mace", 1);
                allWeaponTools.replace("leatherArmour", 1);
                if (empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromBarracks(empire, listOfTroopsBuyPrice.get("maceMan"), allWeaponTools, troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count);
                }
            case "crossbowMan":
                allWeaponTools.replace("bow", 1);
                allWeaponTools.replace("leatherArmour", 1);
                if (empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromBarracks(empire, listOfTroopsBuyPrice.get("crossbowMan"), allWeaponTools, troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count);
                }
            case "pikeMan":
                allWeaponTools.replace("peak", 1);
                allWeaponTools.replace("metalArmour", 1);
                if (empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromBarracks(empire, listOfTroopsBuyPrice.get("pikeMan"), allWeaponTools, troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count);
                }
            case "swordMan":
                allWeaponTools.replace("sword", 1);
                allWeaponTools.replace("metalArmour", 1);
                if (empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromBarracks(empire, listOfTroopsBuyPrice.get("swordMan"), allWeaponTools, troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count);
                }
            case "knight":
                allWeaponTools.replace("sword", 1);
                allWeaponTools.replace("metalArmour", 1);
                allWeaponTools.replace("horse", 1);
                if (empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromBarracks(empire, listOfTroopsBuyPrice.get("knight"), allWeaponTools, troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return empireHasEnoughRecoursesToBuyTroopFromBarracks(empire, listOfTroopsBuyPrice, allWeaponTools, troopName, count);
                }
            default:
                return null;
        }

    }

    public SelectedBuildingMessages enoughResourcesToBuyFromMercenary(Empire empire, int troopPrice, int troopCount) {
        int empiresGoldCount = empire.getGoldCount();
        int empiresPeasantCount = empire.getPeasantCount();
        if (empiresPeasantCount < troopCount) return SelectedBuildingMessages.NOT_ENOUGH_PEASANTS;
        int totalBuyPrice = troopPrice * troopCount;
        if (totalBuyPrice > empiresGoldCount) return SelectedBuildingMessages.NOT_ENOUGH_GOLD;
        return SelectedBuildingMessages.ENOUGH_RESOURCES;
    }

    public void buyFromMercenary(Empire empire, int troopPrice, String troopName, int troopCount) {
        empire.setGoldCount(empire.getGoldCount() - troopPrice * troopCount);
        empire.setPeasantCount(empire.getPeasantCount() - troopCount);
        empire.setTroopCount(empire.getTroopCount() + troopCount);
        switch (troopName) {
            case "arabianBow":
                empire.setArabianBowCount(empire.getArabianBowCount() + troopCount);
            case "slave":
                empire.setSlaveCount(empire.getSlaveCount() + troopCount);
            case "slinger":
                empire.setSlingerCount(empire.getSlingerCount() + troopCount);
            case "assassin":
                empire.setAssassinCount(empire.getAssassinCount() + troopCount);
            case "horseArcher":
                empire.setHorseArcherCount(empire.getHorseArcherCount() + troopCount);
            case "arabianSwordMan":
                empire.setArabianSwordManCount(empire.getArabianSwordManCount() + troopCount);
            case "fireThrower":
                empire.setFireThrowerCount(empire.getFireThrowerCount() + troopCount);
        }
    }

    public SelectedBuildingMessages mercenary(Matcher matcherTroopName, Matcher matcherCount) {
        String troopName = matcherTroopName.group("type");
        int count = Integer.parseInt(matcherCount.group("count"));
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
                if (enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("arabianBow"), count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromMercenary(empire, listOfTroopsBuyPrice.get("arabianBow"), troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("arabianBow"), count);
                }
            case "slave":
                if (enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("slave"), count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromMercenary(empire, listOfTroopsBuyPrice.get("slave"), troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("slave"), count);
                }
            case "slinger":
                if (enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("slinger"), count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromMercenary(empire, listOfTroopsBuyPrice.get("slinger"), troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("slinger"), count);
                }
            case "assassin":
                if (enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("assassin"), count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromMercenary(empire, listOfTroopsBuyPrice.get("assassin"), troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("assassin"), count);
                }
            case "horseArcher":
                if (enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("horseArcher"), count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromMercenary(empire, listOfTroopsBuyPrice.get("horseArcher"), troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("horseArcher"), count);
                }
            case "arabianSwordMan":
                if (enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("arabianSwordMan"), count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromMercenary(empire, listOfTroopsBuyPrice.get("arabianSwordMan"), troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("arabianSwordMan"), count);
                }
            case "fireThrower":
                if (enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("fireThrower"), count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromMercenary(empire, listOfTroopsBuyPrice.get("fireThrower"), troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return enoughResourcesToBuyFromMercenary(empire, listOfTroopsBuyPrice.get("fireThrower"), count);
                }
            default:
                return null;
        }

    }

    public SelectedBuildingMessages enoughResourcesToBuyFromEngineerGuild(Empire empire, int troopPrice, int troopCount) {
        int empiresGoldCount = empire.getGoldCount();
        int empiresPeasantCount = empire.getPeasantCount();
        if (empiresPeasantCount < troopCount) return SelectedBuildingMessages.NOT_ENOUGH_PEASANTS;
        int totalBuyPrice = troopPrice * troopCount;
        if (totalBuyPrice > empiresGoldCount) return SelectedBuildingMessages.NOT_ENOUGH_GOLD;
        return SelectedBuildingMessages.ENOUGH_RESOURCES;
    }

    public void buyFromEngineerGuild(Empire empire, int troopPrice, String troopName, int troopCount) {
        empire.setGoldCount(empire.getGoldCount() - troopPrice * troopCount);
        empire.setPeasantCount(empire.getPeasantCount() - troopCount);
        switch (troopName) {
            case "engineer":
                empire.setEngineerCount(empire.getEngineerCount() + troopCount);
            case "ladderMan":
                empire.setLadderManCount(empire.getLadderManCount() + troopCount);
            case "tunneler":
                empire.setTunnelerCount(empire.getTunnelerCount() + troopCount);
        }
    }

    public SelectedBuildingMessages engineerGuild(Matcher matcherTroopName, Matcher matcherCount) {
        String troopName = matcherTroopName.group("type");
        int count = Integer.parseInt(matcherCount.group("count"));
        HashMap<String, Integer> engineerGuildTroopPrice = new HashMap<>();

        {
            engineerGuildTroopPrice.put("engineer", 30);
            engineerGuildTroopPrice.put("ladderMan", 5);
            engineerGuildTroopPrice.put("tunneler", 30);
        }
        switch (troopName) {
            case "engineer":
                if (enoughResourcesToBuyFromEngineerGuild(empire, engineerGuildTroopPrice.get("engineer"), count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromEngineerGuild(empire, engineerGuildTroopPrice.get("engineer"), troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return (enoughResourcesToBuyFromEngineerGuild(empire, engineerGuildTroopPrice.get("engineer"), count));
                }
            case "ladderMan":
                if (enoughResourcesToBuyFromEngineerGuild(empire, engineerGuildTroopPrice.get("ladderMan"), count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromEngineerGuild(empire, engineerGuildTroopPrice.get("ladderMan"), troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return (enoughResourcesToBuyFromEngineerGuild(empire, engineerGuildTroopPrice.get("ladderMan"), count));
                }
            case "tunneler":
                if (enoughResourcesToBuyFromEngineerGuild(empire, engineerGuildTroopPrice.get("tunneler"), count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromEngineerGuild(empire, engineerGuildTroopPrice.get("tunneler"), troopName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return (enoughResourcesToBuyFromEngineerGuild(empire, engineerGuildTroopPrice.get("tunneler"), count));
                }
            default:
                return null;
        }

    }

    public SelectedBuildingMessages enoughResourcesToBuyFromSiegeTent(Empire empire, int troopPrice, int troopCount) {
        int empiresGoldCount = empire.getGoldCount();
        int empiresEngineerCount = empire.getEngineerCount();
        if (empiresEngineerCount * 3 < troopCount) return SelectedBuildingMessages.NOT_ENOUGH_ENGINEERS;
        int totalBuyPrice = troopPrice * troopCount;
        if (totalBuyPrice > empiresGoldCount) return SelectedBuildingMessages.NOT_ENOUGH_GOLD;
        return SelectedBuildingMessages.ENOUGH_RESOURCES;
    }

    public void buyFromSiegeTent(Empire empire, int troopPrice, String troopName, int troopCount) {
        empire.setGoldCount(empire.getGoldCount() - troopPrice * troopCount);
        empire.setEngineerCount(empire.getEngineerCount() - 3 * troopCount);
        empire.setTroopCount(empire.getTroopCount() + troopCount);
        switch (troopName) {
            case "catapult":
                empire.setCatapultCount(empire.getCatapultCount() + troopCount);
            case "trebuchet":
                empire.setTrebuchetCount(empire.getTrebuchetCount() + troopCount);
            case "siegeTower":
                empire.setSiegeTowerCount(empire.getSiegeTowerCount() + troopCount);
            case "fireBallista":
                empire.setFireBalistaCount(empire.getFireBalistaCount() + troopCount);
            case "batteringRam":
                empire.setBatteringRamCount(empire.getBatteringRamCount() + troopCount);
            case "portableShield":
                empire.setPortableShieldCount(empire.getPortableShieldCount() + 3 * troopCount);
        }
    }

    public SelectedBuildingMessages siegeTent(Matcher matcherTroopName, Matcher matcherCount) {
        String siegeName = matcherTroopName.group("type");
        int count = Integer.parseInt(matcherCount.group("count"));

        HashMap<String, Integer> siegeTentTroopsPrice = new HashMap<>();

        {
            siegeTentTroopsPrice.put("catapult", 150);
            siegeTentTroopsPrice.put("trebuchet", 150);
            siegeTentTroopsPrice.put("siegeTower", 150);
            siegeTentTroopsPrice.put("fireBallista", 150);
            siegeTentTroopsPrice.put("batteringRam", 150);
            siegeTentTroopsPrice.put("portableShield", 5);
        }
        switch (siegeName) {
            case "catapult":
                if (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("catapult"), count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromSiegeTent(empire, siegeTentTroopsPrice.get("catapult"), siegeName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("catapult"), count));
                }
            case "trebuchet":
                if (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("trebuchet"), count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromSiegeTent(empire, siegeTentTroopsPrice.get("trebuchet"), siegeName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("trebuchet"), count));
                }
            case "siegeTower":
                if (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("siegeTower"), count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromSiegeTent(empire, siegeTentTroopsPrice.get("siegeTower"), siegeName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("siegeTower"), count));
                }
            case "fireBallista":
                if (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("fireBallista"), count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromSiegeTent(empire, siegeTentTroopsPrice.get("fireBallista"), siegeName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("fireBallista"), count));
                }
            case "batteringRam":
                if (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("batteringRam"), count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromSiegeTent(empire, siegeTentTroopsPrice.get("batteringRam"), siegeName, count);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("batteringRam"), count));
                }
            case "portableShield":
                if (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("portableShield"), count / 3).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
                    buyFromSiegeTent(empire, siegeTentTroopsPrice.get("portableShield"), siegeName, count / 3);
                    return SelectedBuildingMessages.PURCHASE_SUCCESS;
                } else {
                    return (enoughResourcesToBuyFromSiegeTent(empire, siegeTentTroopsPrice.get("portableShield"), count / 3));
                }
            default:
                return null;
        }

    }

    public SelectedBuildingMessages enoughResourcesToBuyFromChurch(Empire empire, int troopPrice, int troopCount) {
        int empiresGoldCount = empire.getGoldCount();
        int empiresPeasantsCount = empire.getPeasantCount();
        if (empiresPeasantsCount < troopCount) return SelectedBuildingMessages.NOT_ENOUGH_PEASANTS;
        int totalBuyPrice = troopPrice * troopCount;
        if (totalBuyPrice > empiresGoldCount) return SelectedBuildingMessages.NOT_ENOUGH_GOLD;
        return SelectedBuildingMessages.ENOUGH_RESOURCES;
    }

    public void buyFromChurch(Empire empire, int troopPrice, int troopCount) {
        empire.setGoldCount(empire.getGoldCount() - troopPrice * troopCount);
        empire.setPeasantCount(empire.getEngineerCount() - troopCount);
        empire.setTroopCount(empire.getTroopCount() + troopCount);
        empire.setBlackMonkCount(empire.getBlackMonkCount() + troopCount);
    }

    public SelectedBuildingMessages church(Matcher matcherCount) {
        int count = Integer.parseInt(matcherCount.group("count"));
        HashMap<String, Integer> churchTroopsPrice = new HashMap<>();
        {
            churchTroopsPrice.put("blackMonk", 20);
        }
        if (enoughResourcesToBuyFromChurch(empire, churchTroopsPrice.get("blackMonk"), count).equals(SelectedBuildingMessages.ENOUGH_RESOURCES)) {
            buyFromChurch(empire, churchTroopsPrice.get("blackMonk"), count);
            return SelectedBuildingMessages.PURCHASE_SUCCESS;
        } else {
            return (enoughResourcesToBuyFromChurch(empire, churchTroopsPrice.get("blackMonk"), count));
        }

    }


}

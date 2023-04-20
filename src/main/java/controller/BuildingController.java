package controller;

import model.Building.Building;
import model.Building.DrawBridge;
import model.Building.Names;
import model.Building.StoneGateWay;
import model.Empire;
import model.Manage;
import model.Map;
import view.Commands.BuildingMessages;

public class BuildingController {
    public static int size;
    private Building selectedBuilding;
    public static Empire currentEmpire;

    public BuildingMessages checkCoordinate(int x, int y) {
        if (x < 0 || y < 0 || x > size || y > size) {
            return BuildingMessages.INVALID_COORDINATE;
        }
        return BuildingMessages.CONTINUE;
    }

    public boolean HasBuildingInThisPlace(int x, int y) {
        return Map.buildingMap[x][y].size() != 0;
    }

    public boolean correctGroundType(int x, int y, Building newBuilding) {
        return Map.getGroundType()[x][y].get(0).equals(newBuilding.getRequiredGroundType());
    }

    public Building findSelectedBuilding(int x, int y) {
        if (Map.buildingMap[x][y].size() != 0)
            return Map.buildingMap[x][y].get(0);
        else {
            return null;
        }
    }

    public boolean empireHasEnoughResourcesToBuildTheBuilding(Building building, Empire empire) {
        return building.cost.get("wood") <= empire.getWoodCount() && building.cost.get("stone") <= empire.getStoneCount() &&
                building.cost.get("gold") <= empire.getGoldCount() && building.cost.get("gold") <= empire.getIronCount()
                && building.cost.get("oil") <= empire.getOilAmount();
    }

    public void buildingCheckout(Building building, Empire empire) {
        empire.setWoodCount(empire.getWoodCount() - building.cost.get("wood"));
        empire.setStoneCount(empire.getStoneCount() - building.cost.get("stone"));
        empire.setGoldCount(empire.getGoldCount() - building.cost.get("gold"));
        empire.setIronCount(empire.getIronCount() - building.cost.get("iron"));
        empire.setOilAmount(empire.getOilAmount() - building.cost.get("oil"));
    }

    //TODO : TAKE CARE THAT BEFORE CREATING A BUILDING WE MUST FIRST CHECK THAT EMPIRE HAS THE REQUIRED RESOURCES TO BUILD THAT BUILDING
    public BuildingMessages callBuildingFunction(int x, int y, String type) {
        switch (type) {
            case "Small Stone Gatehouse":
                StoneGateWay smallStoneGateWay = new StoneGateWay(currentEmpire);
                smallStoneGateWay.smallGateWay(x, y, smallStoneGateWay);
                if (empireHasEnoughResourcesToBuildTheBuilding(smallStoneGateWay, currentEmpire)) {
                    buildingCheckout(smallStoneGateWay , currentEmpire);
                    Map.AddToBuildingMap(x, y, smallStoneGateWay);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Big Stone Gatehouse":
                StoneGateWay bigStoneGateWay = new StoneGateWay(currentEmpire);
                bigStoneGateWay.bigGateWay(x, y, bigStoneGateWay);
                if (empireHasEnoughResourcesToBuildTheBuilding(bigStoneGateWay, currentEmpire)) {
                    buildingCheckout(bigStoneGateWay , currentEmpire);
                    Map.AddToBuildingMap(x, y, bigStoneGateWay);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Draw Bridge":
                DrawBridge drawBridge = new DrawBridge(currentEmpire);
                drawBridge.drawBridge(x, y);
                if (empireHasEnoughResourcesToBuildTheBuilding(drawBridge, currentEmpire)) {
                    buildingCheckout(drawBridge , currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
        }
        return BuildingMessages.INVALID_BUILDING_NAME;
    }

    public BuildingMessages dropBuilding(int x, int y, String type, Building newBuilding) {
        if (checkCoordinate(x, y) == BuildingMessages.CONTINUE) {
            for (int i = 0; i < Manage.getNamesOfAllPossibleBuildings().size(); i++) {
                if (Manage.getNamesOfAllPossibleBuildings().get(i).equals(type)) {
                    if (correctGroundType(x, y, newBuilding)) {
                        if (HasBuildingInThisPlace(x, y)) {
                            callBuildingFunction(x, y, type);
                            break;
                        } else return BuildingMessages.FILLED_CELL;
                    } else return BuildingMessages.INPROPER_GROUND_TYPE;
                }
            }
            return BuildingMessages.INVALID_BUILDING_NAME;
        }
        return BuildingMessages.INVALID_COORDINATE;
    }

    public BuildingMessages selectBuilding(int x, int y) {
        if (checkCoordinate(x, y) == BuildingMessages.CONTINUE) {
            if (HasBuildingInThisPlace(x, y)) {
                if (Map.getBuildingMap()[x][y].get(0).getOwner().getName().equals(currentEmpire.getName())) {
                    selectedBuilding = Map.getBuildingMap()[x][y].get(0);
                } else return BuildingMessages.NO_ACCESS;
            } else return BuildingMessages.EMPTY_CELL;
        }
        return BuildingMessages.INVALID_COORDINATE;
    }

    public BuildingMessages repairBuilding(int x, int y) {
        // TODO A MENU FOR COMMANDS AFTER SELECTING THE BUILDING TO SEE WHICH CHANGE IS GONNA BE APPLIED
        ///TODO SHOULD WE PRINT THE HP OF EVERY BUILDING IN SELECT BUILDING?
        //TODO AFTER COMPLETING THE ARMIES SEARCH TO SEE IF ENEMIES ARE IN THE GIVEN POSITION
        int requiredStone = 50;
        Building building = findSelectedBuilding(x, y);
        if (building != null) {
            int currentHp = building.getHp();
            int maxHp = building.getMaxHp();
            if (currentHp == maxHp) {
                return BuildingMessages.HP_ALREADY_FULL;
            } else {
                if (currentEmpire.getStoneCount() >= 50) {
                    currentEmpire.setStoneCount(currentEmpire.getStoneCount() - requiredStone);
                    building.setHp(maxHp);
                    return BuildingMessages.SUCCESSFUL_REPAIR;
                } else {
                    return BuildingMessages.INSUFFICIENT_STONE;
                }
            }
        } else {
            return BuildingMessages.CONTINUE;
        }
    }

    public void increaseCapacityLimitation(int capacity) {
        currentEmpire.setMaxPossiblePopulation(currentEmpire.getMaxPossiblePopulation() + capacity);
    }
}

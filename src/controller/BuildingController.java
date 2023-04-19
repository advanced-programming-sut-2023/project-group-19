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
    private Building selectedBuilding;
    private Class typeOfSelectedBuilding;
    private Empire currentEmpire;

    public BuildingMessages checkCoordinate(int x, int y) {
        if (x < 0 || y < 0) {
            return BuildingMessages.INVALID_COORDINATE;
        }
        return BuildingMessages.CONTINUE;
    }

    public BuildingMessages callBuildingFunction(int x, int y, String type) {
        switch (type) {
            case "Small Stone Gatehouse":
                StoneGateWay smallStoneGateWay = new StoneGateWay(currentEmpire);
                smallStoneGateWay.smallGateWay(x, y, smallStoneGateWay);
                return BuildingMessages.SUCCESS;
            case "Big Stone Gatehouse":
                StoneGateWay bigStoneGateWay = new StoneGateWay(currentEmpire);
                bigStoneGateWay.bigGateWay(x, y, bigStoneGateWay);
                return BuildingMessages.SUCCESS;
            case "Draw Bridge":
                DrawBridge drawBridge = new DrawBridge(currentEmpire);
                drawBridge.drawBridge(x, y);
                return BuildingMessages.SUCCESS;
        }
        return BuildingMessages.INVALID_BUILDING_NAME;
    }

    public BuildingMessages dropBuilding(int x, int y, String type, Building newBuilding) {
        if (checkCoordinate(x, y) == BuildingMessages.CONTINUE) {
            for (int i = 0; i < Manage.getNamesOfAllPossibleBuildings().size(); i++) {
                if (Manage.getNamesOfAllPossibleBuildings().get(i).equals(type)) {
                    if (Map.getGroundType()[x][y].get(0).equals(newBuilding.getRequiredGroundType())) {
                        if (Map.getBuildingMap()[x][y].get(0) == null) {
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
            if (Map.getBuildingMap()[x][y].get(0) != null) {
                if (Map.getBuildingMap()[x][y].get(0).getOwner().getName().equals(currentEmpire.getName())) {
                    selectedBuilding = Map.getBuildingMap()[x][y].get(0);
                    typeOfSelectedBuilding = selectedBuilding.getClass();
                } else return BuildingMessages.NO_ACCESS;
            } else return BuildingMessages.EMPTY_CELL;
        }
        return BuildingMessages.INVALID_COORDINATE;
    }

    public BuildingMessages repairBuilding(int x, int y) {
        // TODO A MENU FOR COMMANDS AFTER SELECTING THE BUILDING TO SEE WHICH CHANGE IS GONNA BE APPLIED
        ///TODO SHOULD WE PRINT THE HP OF EVERY BUILDING IN SELECT BUILDING?
        //TODO AFTER COMPLETING THE ARMIES SEARCH TO SEE IF ENEMIES ARE IN THE GIVEN POSITION
        int requiredStone;
        StoneGateWay stoneGateWay;
        DrawBridge drawBridge;
        switch (typeOfSelectedBuilding.toString()) {
            case "model.Building.StoneGateWay":
                stoneGateWay = (StoneGateWay) Map.getBuildingMap()[x][y].get(0);
                requiredStone = stoneGateWay.getDefaultHP() - stoneGateWay.getHp();
                if (requiredStone > currentEmpire.getStoneCount()) {
                    return BuildingMessages.NOT_ENOUGH_STONE;
                }
                stoneGateWay.setHp(stoneGateWay.getDefaultHP());
                Map.getBuildingMap()[x][y].add(stoneGateWay);
                Map.getBuildingMap()[x][y].remove(0);
                currentEmpire.setStoneCount(currentEmpire.getStoneCount() - requiredStone);
                selectedBuilding = null;
                typeOfSelectedBuilding = null;
                return BuildingMessages.SUCCESS;
            case "model.Building.DrawBridge":
                drawBridge = (DrawBridge) Map.getBuildingMap()[x][y].get(0);
                requiredStone = drawBridge.getDefaultHP() - drawBridge.getHp();
                if (requiredStone > currentEmpire.getStoneCount()) {
                    return BuildingMessages.NOT_ENOUGH_STONE;
                }
                drawBridge.setHp(drawBridge.getDefaultHP());
                Map.getBuildingMap()[x][y].add(drawBridge);
                Map.getBuildingMap()[x][y].remove(0);
                currentEmpire.setStoneCount(currentEmpire.getStoneCount() - requiredStone);
                selectedBuilding = null;
                typeOfSelectedBuilding = null;
                return BuildingMessages.SUCCESS;
        }
        return BuildingMessages.CONTINUE;
    }

    public BuildingMessages capacityLimitation(int capacity, Names name) {//Q: Usage???
        if ((capacity > 8 && name == Names.SMALL_STONE_GATE_HOUSE) ||
                (capacity > 10 && name == Names.BIG_STONE_GATE_HOUSE)) {
            return BuildingMessages.FULL_CAPACITY_OF_BUILDING;
        }
        return BuildingMessages.CONTINUE;
    }
}

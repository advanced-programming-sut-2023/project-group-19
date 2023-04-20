package controller;

import model.Building.*;
import model.Empire;
import model.Manage;
import model.Map;
import view.Commands.BuildingMessages;

public class BuildingController {
    public static int size;
    public static Building selectedBuilding;
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
        if (Map.buildingMap[x][y].size() != 0) return Map.buildingMap[x][y].get(0);
        else {
            return null;
        }
    }

    public boolean empireHasEnoughResourcesToBuildTheBuilding(Building building, Empire empire) {
        return building.cost.get("wood") <= empire.getWoodCount() && building.cost.get("stone") <= empire.getStoneCount() && building.cost.get("gold") <= empire.getGoldCount() && building.cost.get("gold") <= empire.getIronCount() && building.cost.get("oil") <= empire.getOilAmount();
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
                smallStoneGateWay.smallGateWay();
                if (empireHasEnoughResourcesToBuildTheBuilding(smallStoneGateWay, currentEmpire)) {
                    buildingCheckout(smallStoneGateWay, currentEmpire);
                    Map.AddToBuildingMap(x, y, smallStoneGateWay);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Big Stone Gatehouse":
                StoneGateWay bigStoneGateWay = new StoneGateWay(currentEmpire);
                bigStoneGateWay.bigGateWay();
                if (empireHasEnoughResourcesToBuildTheBuilding(bigStoneGateWay, currentEmpire)) {
                    buildingCheckout(bigStoneGateWay, currentEmpire);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Lookout Tower":
                Tower lookoutTower = new Tower(currentEmpire);
                lookoutTower.lookoutTower();
                if (empireHasEnoughResourcesToBuildTheBuilding(lookoutTower, currentEmpire)) {
                    buildingCheckout(lookoutTower, currentEmpire);
                    Map.AddToBuildingMap(x, y, lookoutTower);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Perimeter Tower":
                Tower perimeterTower = new Tower(currentEmpire);
                perimeterTower.perimeterTower();
                if (empireHasEnoughResourcesToBuildTheBuilding(perimeterTower, currentEmpire)) {
                    buildingCheckout(perimeterTower, currentEmpire);
                    Map.AddToBuildingMap(x, y, perimeterTower);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Defend Tower":
                Tower defendTower = new Tower(currentEmpire);
                defendTower.defendTower();
                if (empireHasEnoughResourcesToBuildTheBuilding(defendTower, currentEmpire)) {
                    buildingCheckout(defendTower, currentEmpire);
                    Map.AddToBuildingMap(x, y, defendTower);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Square Tower":
                Tower squareTower = new Tower(currentEmpire);
                squareTower.squareTower();
                if (empireHasEnoughResourcesToBuildTheBuilding(squareTower, currentEmpire)) {
                    buildingCheckout(squareTower, currentEmpire);
                    Map.AddToBuildingMap(x, y, squareTower);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Round Tower":
                Tower roundTower = new Tower(currentEmpire);
                roundTower.roundTower();
                if (empireHasEnoughResourcesToBuildTheBuilding(roundTower, currentEmpire)) {
                    buildingCheckout(roundTower, currentEmpire);
                    Map.AddToBuildingMap(x, y, roundTower);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            /*case "Draw Bridge":
                DrawBridge drawBridge = new DrawBridge(currentEmpire);
                drawBridge.drawBridge(x, y);
                if (empireHasEnoughResourcesToBuildTheBuilding(drawBridge, currentEmpire)) {
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
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
                    buildingCheckout(drawBridge, currentEmpire);
                    Map.AddToBuildingMap(x, y, drawBridge);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }*/
            case "Apple Farm":
                Goods appleFarm = new Goods(currentEmpire);
                appleFarm.appleFarm();
                if (empireHasEnoughResourcesToBuildTheBuilding(appleFarm, currentEmpire)) {
                    buildingCheckout(appleFarm, currentEmpire);
                    Map.AddToBuildingMap(x, y, appleFarm);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Dairy Product":
                Goods dairyProduct = new Goods(currentEmpire);
                dairyProduct.dairyProduct();
                if (empireHasEnoughResourcesToBuildTheBuilding(dairyProduct, currentEmpire)) {
                    buildingCheckout(dairyProduct, currentEmpire);
                    Map.AddToBuildingMap(x, y, dairyProduct);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Oat Farm":
                Goods oatFarm = new Goods(currentEmpire);
                oatFarm.oatFarm();
                if (empireHasEnoughResourcesToBuildTheBuilding(oatFarm, currentEmpire)) {
                    buildingCheckout(oatFarm, currentEmpire);
                    Map.AddToBuildingMap(x, y, oatFarm);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Hunting Post":
                Goods huntingPost = new Goods(currentEmpire);
                huntingPost.huntingPost();
                if (empireHasEnoughResourcesToBuildTheBuilding(huntingPost, currentEmpire)) {
                    buildingCheckout(huntingPost, currentEmpire);
                    Map.AddToBuildingMap(x, y, huntingPost);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Wheat Farm":
                Goods wheatFarm = new Goods(currentEmpire);
                wheatFarm.wheatFarm();
                if (empireHasEnoughResourcesToBuildTheBuilding(wheatFarm, currentEmpire)) {
                    buildingCheckout(wheatFarm, currentEmpire);
                    Map.AddToBuildingMap(x, y, wheatFarm);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Bakery":
                Goods bakery = new Goods(currentEmpire);
                bakery.bakery();
                if (empireHasEnoughResourcesToBuildTheBuilding(bakery, currentEmpire)) {
                    buildingCheckout(bakery, currentEmpire);
                    Map.AddToBuildingMap(x, y, bakery);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Bear Factory":
                Goods bearFactory = new Goods(currentEmpire);
                bearFactory.bearFactory();
                if (empireHasEnoughResourcesToBuildTheBuilding(bearFactory, currentEmpire)) {
                    buildingCheckout(bearFactory, currentEmpire);
                    Map.AddToBuildingMap(x, y, bearFactory);
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
        ///TODO SHOULD WE PRINT THE HP OF EVERY BUILDING IN SELECT BUILDING? ----> i dont think soo(Arian)
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

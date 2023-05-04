package controller.Building;

import model.Building.*;
import model.Empire;
import model.Manage;
import model.Map;
import view.BuildingMenu;
import view.Messages.BuildingMessages;
import view.SelectedBuildingMenu;

import java.util.Scanner;
import java.util.regex.Matcher;


public class BuildingController {
    public static int size;

    public static Empire currentEmpire;
    public static Building selectedBuilding;

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
        return Map.getGroundType()[x][y].get(0).getGroundType().equals(newBuilding.getRequiredGroundType());
    }

    public Building findSelectedBuilding(int x, int y) {
        if (Map.buildingMap[x][y].size() != 0) return Map.buildingMap[x][y].get(0);
        else {
            return null;
        }
    }

    public boolean empireHasEnoughResourcesToBuildTheBuilding(Building building, Empire empire) {
        return building.cost.get("wood") <= empire.getWoodCount() && building.cost.get("stone") <= empire.getStoneCount() &&
                building.cost.get("gold") <= empire.getGoldCount() && building.cost.get("gold") <= empire.getIronCount() &&
                building.cost.get("oil") / 5 <= empire.getOilAmount();
    }

    public boolean empireHasEnoughWorkersToBuildTheBuilding(Building building, Empire empire) {
        return (building.workersNeeded.get("engineer") <= empire.getEngineerCount() &&
                building.workersNeeded.get("worker") <= empire.getWorkerCount());
    }

    public boolean canBuildStockpile(int x, int y) {
        if (Map.buildingMap[x + 1][y].get(0).getName().getName().equals("Stockpile") ||
                Map.buildingMap[x - 1][y].get(0).getName().getName().equals("Stockpile") ||
                Map.buildingMap[x][y + 1].get(0).getName().getName().equals("Stockpile") ||
                Map.buildingMap[x][y - 1].get(0).getName().getName().equals("Stockpile")) {
            return true;
        }
        return false;
    }

    public void buildingCheckout(Building building, Empire empire) {
        empire.setWoodCount(empire.getWoodCount() - building.cost.get("wood"));
        empire.setStoneCount(empire.getStoneCount() - building.cost.get("stone"));
        empire.setGoldCount(empire.getGoldCount() - building.cost.get("gold"));
        empire.setIronCount(empire.getIronCount() - building.cost.get("iron"));
        empire.setOilAmount(empire.getOilAmount() - building.cost.get("oil") / 5);
        empire.setWorkerCount(empire.getWorkerCount() - building.workersNeeded.get("engineer"));
        empire.setEngineerCount(empire.getEngineerCount() - building.workersNeeded.get("worker"));
    }

    public boolean validationOfStairsLocation(int x, int y) {
        if (Map.getBuildingMap()[x - 1][y].get(0) instanceof StoneGateWay || Map.getBuildingMap()[x + 1][y].get(0) instanceof StoneGateWay
                || Map.getBuildingMap()[x][y - 1].get(0) instanceof StoneGateWay || Map.getBuildingMap()[x][y + 1].get(0) instanceof StoneGateWay
                || Map.getBuildingMap()[x - 1][y].get(0) instanceof Wall || Map.getBuildingMap()[x + 1][y].get(0) instanceof Wall
                || Map.getBuildingMap()[x][y - 1].get(0) instanceof Wall || Map.getBuildingMap()[x][y + 1].get(0) instanceof Wall) {
            return true;
        }
        return false;
    }

    //TODO : TAKE CARE THAT BEFORE CREATING A BUILDING WE MUST FIRST CHECK THAT EMPIRE HAS THE REQUIRED RESOURCES TO BUILD THAT BUILDING
    public BuildingMessages callBuildingFunction(int x, int y, String type, Scanner scanner) {
        String direction;
        Names directionOfGate;
        switch (type) {
            case "Small Stone Gatehouse":
                System.out.println(BuildingMessages.ENTER_DIRECTION.getMessages());
                direction = scanner.nextLine();
                if (direction.equals(Names.NS.getName())) directionOfGate = Names.NS;
                else if (direction.equals(Names.WE.getName())) directionOfGate = Names.WE;
                else return BuildingMessages.INVALID_DIRECTION;
                StoneGateWay smallStoneGateWay = new StoneGateWay(currentEmpire);
                smallStoneGateWay.smallGateWay(directionOfGate);
                if (empireHasEnoughResourcesToBuildTheBuilding(smallStoneGateWay, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(smallStoneGateWay, currentEmpire)) {
                        buildingCheckout(smallStoneGateWay, currentEmpire);
                        Map.AddToBuildingMap(x, y, smallStoneGateWay);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Big Stone Gatehouse":
                System.out.println(BuildingMessages.ENTER_DIRECTION.getMessages());
                direction = scanner.nextLine();
                if (direction.equals(Names.NS.getName())) directionOfGate = Names.NS;
                else if (direction.equals(Names.WE.getName())) directionOfGate = Names.WE;
                else return BuildingMessages.INVALID_DIRECTION;
                StoneGateWay bigStoneGateWay = new StoneGateWay(currentEmpire);
                bigStoneGateWay.bigGateWay(directionOfGate);
                if (empireHasEnoughResourcesToBuildTheBuilding(bigStoneGateWay, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(bigStoneGateWay, currentEmpire)) {
                        buildingCheckout(bigStoneGateWay, currentEmpire);
                        Map.AddToBuildingMap(x, y, bigStoneGateWay);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Draw Bridge":
                DrawBridge drawBridge = new DrawBridge(currentEmpire);
                drawBridge.drawBridge(x, y);
                if (empireHasEnoughResourcesToBuildTheBuilding(drawBridge, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(drawBridge, currentEmpire)) {
                        buildingCheckout(drawBridge, currentEmpire);
                        Map.AddToBuildingMap(x, y, drawBridge);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Lookout Tower":
                Tower lookoutTower = new Tower(currentEmpire);
                lookoutTower.lookoutTower();
                if (empireHasEnoughResourcesToBuildTheBuilding(lookoutTower, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(lookoutTower, currentEmpire)) {
                        buildingCheckout(lookoutTower, currentEmpire);
                        Map.AddToBuildingMap(x, y, lookoutTower);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Perimeter Tower":
                Tower perimeterTower = new Tower(currentEmpire);
                perimeterTower.perimeterTower();
                if (empireHasEnoughResourcesToBuildTheBuilding(perimeterTower, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(perimeterTower, currentEmpire)) {
                        buildingCheckout(perimeterTower, currentEmpire);
                        Map.AddToBuildingMap(x, y, perimeterTower);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Defend Tower":
                Tower defendTower = new Tower(currentEmpire);
                defendTower.defendTower();
                if (empireHasEnoughResourcesToBuildTheBuilding(defendTower, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(defendTower, currentEmpire)) {
                        buildingCheckout(defendTower, currentEmpire);
                        Map.AddToBuildingMap(x, y, defendTower);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Square Tower":
                Tower squareTower = new Tower(currentEmpire);
                squareTower.squareTower();
                if (empireHasEnoughResourcesToBuildTheBuilding(squareTower, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(squareTower, currentEmpire)) {
                        buildingCheckout(squareTower, currentEmpire);
                        Map.AddToBuildingMap(x, y, squareTower);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Round Tower":
                Tower roundTower = new Tower(currentEmpire);
                roundTower.roundTower();
                if (empireHasEnoughResourcesToBuildTheBuilding(roundTower, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(roundTower, currentEmpire)) {
                        buildingCheckout(roundTower, currentEmpire);
                        Map.AddToBuildingMap(x, y, roundTower);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Armoury":
                Armoury armoury = new Armoury(currentEmpire);
                armoury.armoury();
                if (empireHasEnoughResourcesToBuildTheBuilding(armoury, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(armoury, currentEmpire)) {
                        buildingCheckout(armoury, currentEmpire);
                        currentEmpire.setWeaponsCapacity(currentEmpire.getWeaponsCapacity() + armoury.getCapacity());
                        Map.AddToBuildingMap(x, y, armoury);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Barrack":
                PrepareLaboursAndFighters barracks = new PrepareLaboursAndFighters(currentEmpire);
                barracks.barracks();
                if (empireHasEnoughResourcesToBuildTheBuilding(barracks, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(barracks, currentEmpire)) {
                        buildingCheckout(barracks, currentEmpire);
                        Map.AddToBuildingMap(x, y, barracks);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Mercenary Post":
                PrepareLaboursAndFighters mercenaryPost = new PrepareLaboursAndFighters(currentEmpire);
                mercenaryPost.mercenary();
                if (empireHasEnoughResourcesToBuildTheBuilding(mercenaryPost, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(mercenaryPost, currentEmpire)) {
                        buildingCheckout(mercenaryPost, currentEmpire);
                        Map.AddToBuildingMap(x, y, mercenaryPost);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Engineer Guild":
                PrepareLaboursAndFighters engineerGuild = new PrepareLaboursAndFighters(currentEmpire);
                engineerGuild.engineerGuild();
                if (empireHasEnoughResourcesToBuildTheBuilding(engineerGuild, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(engineerGuild, currentEmpire)) {
                        buildingCheckout(engineerGuild, currentEmpire);
                        Map.AddToBuildingMap(x, y, engineerGuild);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Killing Pit":
                KillingPit killingPit = new KillingPit(currentEmpire);
                killingPit.killingPit();
                if (empireHasEnoughResourcesToBuildTheBuilding(killingPit, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(killingPit, currentEmpire)) {
                        buildingCheckout(killingPit, currentEmpire);
                        Map.AddToBuildingMap(x, y, killingPit);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Inn":
                // TODO : add inn to the functional buildings and then fix this part
                Inn inn = new Inn(currentEmpire);
                inn.inn();
                if (empireHasEnoughResourcesToBuildTheBuilding(inn, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(inn, currentEmpire)) {
                        buildingCheckout(inn, currentEmpire);
                        Map.AddToBuildingMap(x, y, inn);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Mill":
                Industry mill = new Industry(currentEmpire);
                mill.mill();
                if (empireHasEnoughResourcesToBuildTheBuilding(mill, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(mill, currentEmpire)) {
                        buildingCheckout(mill, currentEmpire);
                        Map.AddToBuildingMap(x, y, mill);
                        currentEmpire.setMillCount(currentEmpire.getMillCount() + 1);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Iron Dig":
                Industry ironDig = new Industry(currentEmpire);
                ironDig.ironDig();
                if (empireHasEnoughResourcesToBuildTheBuilding(ironDig, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(ironDig, currentEmpire)) {
                        buildingCheckout(ironDig, currentEmpire);
                        Map.AddToBuildingMap(x, y, ironDig);
                        currentEmpire.setIronMineCount(currentEmpire.getIronMineCount() + 1);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Market":
                Market market = new Market(currentEmpire);
                market.market();
                if (empireHasEnoughResourcesToBuildTheBuilding(market, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(market, currentEmpire)) {
                        buildingCheckout(market, currentEmpire);
                        Map.AddToBuildingMap(x, y, market);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Ox Tether":
                Industry oxTether = new Industry(currentEmpire);
                oxTether.oxTether();
                if (empireHasEnoughResourcesToBuildTheBuilding(oxTether, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(oxTether, currentEmpire)) {
                        buildingCheckout(oxTether, currentEmpire);
                        currentEmpire.setOxTetherCount(currentEmpire.getOxTetherCount() + 1);
                        Map.AddToBuildingMap(x, y, oxTether);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Pitch Rig":
                Industry pitchRig = new Industry(currentEmpire);
                pitchRig.pitchRig();
                if (empireHasEnoughResourcesToBuildTheBuilding(pitchRig, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(pitchRig, currentEmpire)) {
                        buildingCheckout(pitchRig, currentEmpire);
                        currentEmpire.setPitchRigCount(currentEmpire.getPitchRigCount() + 1);
                        Map.AddToBuildingMap(x, y, pitchRig);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Quarry":
                Industry quarry = new Industry(currentEmpire);
                quarry.quarry();
                if (empireHasEnoughResourcesToBuildTheBuilding(quarry, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(quarry, currentEmpire)) {
                        buildingCheckout(quarry, currentEmpire);
                        Map.AddToBuildingMap(x, y, quarry);
                        currentEmpire.setQuarryCount(currentEmpire.getQuarryCount() + 1);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Food Stockpile":
                Stockpile foodStockpile = new Stockpile(currentEmpire);
                foodStockpile.foodStockpile();
                if (empireHasEnoughResourcesToBuildTheBuilding(foodStockpile, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(foodStockpile, currentEmpire)) {
                        if (canBuildStockpile(x, y)) {
                            buildingCheckout(foodStockpile, currentEmpire);
                            Map.AddToBuildingMap(x, y, foodStockpile);
                            currentEmpire.setFoodCapacity(currentEmpire.getFoodCapacity() + foodStockpile.maxFoodCapacity);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.STOCK_PILE_ERROR;
                        }
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Resources Stockpile":
                Stockpile resourcesStockpile = new Stockpile(currentEmpire);
                resourcesStockpile.resourcesStockpile();
                if (empireHasEnoughResourcesToBuildTheBuilding(resourcesStockpile, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(resourcesStockpile, currentEmpire)) {
                        if (canBuildStockpile(x, y)) {
                            buildingCheckout(resourcesStockpile, currentEmpire);
                            Map.AddToBuildingMap(x, y, resourcesStockpile);
                            currentEmpire.setResourcesCapacity(currentEmpire.getResourcesCapacity() + resourcesStockpile.maxResourcesCapacity);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.STOCK_PILE_ERROR;
                        }
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Wood Cutter":
                Industry woodCutter = new Industry(currentEmpire);
                woodCutter.woodCutter();
                if (empireHasEnoughResourcesToBuildTheBuilding(woodCutter, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(woodCutter, currentEmpire)) {
                        buildingCheckout(woodCutter, currentEmpire);
                        Map.AddToBuildingMap(x, y, woodCutter);
                        currentEmpire.setWoodCutterCount(currentEmpire.getWoodCutterCount() + 1);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "House":
                House house = new House(currentEmpire);
                house.house();
                if (empireHasEnoughResourcesToBuildTheBuilding(house, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(house, currentEmpire)) {
                        buildingCheckout(house, currentEmpire);
                        Map.AddToBuildingMap(x, y, house);
                        currentEmpire.setPopulation(currentEmpire.getPopulation() + house.getCapacity());
                        currentEmpire.setPeasantCount(currentEmpire.getPeasantCount() + house.getCapacity());
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Small Church":
                Church smallChurch = new Church(currentEmpire);
                smallChurch.smallChurch();
                if (empireHasEnoughResourcesToBuildTheBuilding(smallChurch, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(smallChurch, currentEmpire)) {
                        buildingCheckout(smallChurch, currentEmpire);
                        Map.AddToBuildingMap(x, y, smallChurch);
                        currentEmpire.setPopularity(currentEmpire.popularity + smallChurch.getPopularityIncreaseRate());
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Big Church":
                Church bigChurch = new Church(currentEmpire);
                bigChurch.bigChurch();
                if (empireHasEnoughResourcesToBuildTheBuilding(bigChurch, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(bigChurch, currentEmpire)) {
                        buildingCheckout(bigChurch, currentEmpire);
                        Map.AddToBuildingMap(x, y, bigChurch);
                        currentEmpire.setPopularity(currentEmpire.getPopularity() + bigChurch.getPopularityIncreaseRate());
                        currentEmpire.setPriestCount(currentEmpire.getPriestCount() + 1);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Armourer":
                Weapon armourer = new Weapon(currentEmpire);
                armourer.armourer();
                if (empireHasEnoughResourcesToBuildTheBuilding(armourer, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(armourer, currentEmpire)) {
                        buildingCheckout(armourer, currentEmpire);
                        Map.AddToBuildingMap(x, y, armourer);
                        currentEmpire.setArmourerBuildingCount(currentEmpire.getArmourerBuildingCount() + 1);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Blacksmith":
                Weapon blackSmith = new Weapon(currentEmpire);
                blackSmith.blacksmith();
                if (empireHasEnoughResourcesToBuildTheBuilding(blackSmith, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(blackSmith, currentEmpire)) {
                        buildingCheckout(blackSmith, currentEmpire);
                        Map.AddToBuildingMap(x, y, blackSmith);
                        currentEmpire.setBlacksmithBuildingCount(currentEmpire.getBlacksmithBuildingCount() + 1);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Fletcher":
                Weapon fletcher = new Weapon(currentEmpire);
                fletcher.fletcher();
                if (empireHasEnoughResourcesToBuildTheBuilding(fletcher, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(fletcher, currentEmpire)) {
                        buildingCheckout(fletcher, currentEmpire);
                        Map.AddToBuildingMap(x, y, fletcher);
                        currentEmpire.setFletcherBuildingCount(currentEmpire.getFletcherBuildingCount() + 1);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Pole Turner":
                Weapon poleTurner = new Weapon(currentEmpire);
                poleTurner.poleTurner();
                if (empireHasEnoughResourcesToBuildTheBuilding(poleTurner, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(poleTurner, currentEmpire)) {
                        buildingCheckout(poleTurner, currentEmpire);
                        Map.AddToBuildingMap(x, y, poleTurner);
                        currentEmpire.setPoleTurnerBuildingCount(currentEmpire.getPoleTurnerBuildingCount() + 1);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Oil Smelter":
                OilSmelter oilSmelter = new OilSmelter(currentEmpire);
                oilSmelter.oilSmelter();
                if (empireHasEnoughResourcesToBuildTheBuilding(oilSmelter, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(oilSmelter, currentEmpire)) {
                        buildingCheckout(oilSmelter, currentEmpire);
                        Map.AddToBuildingMap(x, y, oilSmelter);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Pitch Ditch":
                PitchDitch pitchDitch = new PitchDitch(currentEmpire);
                pitchDitch.pitchDitch();
                if (empireHasEnoughResourcesToBuildTheBuilding(pitchDitch, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(pitchDitch, currentEmpire)) {
                        buildingCheckout(pitchDitch, currentEmpire);
                        Map.AddToBuildingMap(x, y, pitchDitch);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Caged War Dogs":
                CagedWarDogs cagedWarDogs = new CagedWarDogs(currentEmpire);
                cagedWarDogs.cagedWarDogs();
                if (empireHasEnoughResourcesToBuildTheBuilding(cagedWarDogs, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(cagedWarDogs, currentEmpire)) {
                        buildingCheckout(cagedWarDogs, currentEmpire);
                        Map.AddToBuildingMap(x, y, cagedWarDogs);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Siege Tent":
                SiegeTent siegeTent = new SiegeTent(currentEmpire);
                siegeTent.siegeTent();
                if (empireHasEnoughResourcesToBuildTheBuilding(siegeTent, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(siegeTent, currentEmpire)) {
                        buildingCheckout(siegeTent, currentEmpire);
                        Map.AddToBuildingMap(x, y, siegeTent);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Stable":
                Stable stable = new Stable(currentEmpire);
                stable.stable();
                if (empireHasEnoughResourcesToBuildTheBuilding(stable, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(stable, currentEmpire)) {
                        buildingCheckout(stable, currentEmpire);
                        Map.AddToBuildingMap(x, y, stable);
                        currentEmpire.setStableBuildingCount(currentEmpire.getStableBuildingCount() + 1);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Apple Farm":
                Goods appleFarm = new Goods(currentEmpire);
                appleFarm.appleFarm();
                if (empireHasEnoughResourcesToBuildTheBuilding(appleFarm, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(appleFarm, currentEmpire)) {
                        buildingCheckout(appleFarm, currentEmpire);
                        Map.AddToBuildingMap(x, y, appleFarm);
                        currentEmpire.setAppleFarmCount(currentEmpire.getAppleFarmCount() + 1);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Dairy Product":
                Goods dairyProduct = new Goods(currentEmpire);
                dairyProduct.dairyProduct();
                if (empireHasEnoughResourcesToBuildTheBuilding(dairyProduct, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(dairyProduct, currentEmpire)) {
                        buildingCheckout(dairyProduct, currentEmpire);
                        Map.AddToBuildingMap(x, y, dairyProduct);
                        currentEmpire.setDairyFactoryRate(currentEmpire.getDairyFactoryRate() + 1);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Oat Farm":
                Goods oatFarm = new Goods(currentEmpire);
                oatFarm.oatFarm();
                if (empireHasEnoughResourcesToBuildTheBuilding(oatFarm, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(oatFarm, currentEmpire)) {
                        buildingCheckout(oatFarm, currentEmpire);
                        Map.AddToBuildingMap(x, y, oatFarm);
                        currentEmpire.setOatFarmCount(currentEmpire.getOatFarmCount() + 1);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Hunting Post":
                Goods huntingPost = new Goods(currentEmpire);
                huntingPost.huntingPost();
                if (empireHasEnoughResourcesToBuildTheBuilding(huntingPost, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(huntingPost, currentEmpire)) {
                        buildingCheckout(huntingPost, currentEmpire);
                        Map.AddToBuildingMap(x, y, huntingPost);
                        currentEmpire.setHuntingPostCount(currentEmpire.getHuntingPostCount() + 1);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Wheat Farm":
                Goods wheatFarm = new Goods(currentEmpire);
                wheatFarm.wheatFarm();
                if (empireHasEnoughResourcesToBuildTheBuilding(wheatFarm, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(wheatFarm, currentEmpire)) {
                        buildingCheckout(wheatFarm, currentEmpire);
                        Map.AddToBuildingMap(x, y, wheatFarm);
                        currentEmpire.setWheatFarmCount(currentEmpire.getWheatFarmCount() + 1);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Bakery":
                Goods bakery = new Goods(currentEmpire);
                bakery.bakery();
                if (empireHasEnoughResourcesToBuildTheBuilding(bakery, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(bakery, currentEmpire)) {
                        buildingCheckout(bakery, currentEmpire);
                        Map.AddToBuildingMap(x, y, bakery);
                        currentEmpire.setBakeryCount(currentEmpire.getBakeryCount() + 1);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Bear Factory":
                Goods bearFactory = new Goods(currentEmpire);
                bearFactory.bearFactory();
                if (empireHasEnoughResourcesToBuildTheBuilding(bearFactory, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(bearFactory, currentEmpire)) {
                        buildingCheckout(bearFactory, currentEmpire);
                        Map.AddToBuildingMap(x, y, bearFactory);
                        currentEmpire.setBeerFactoryCount(currentEmpire.getBeerFactoryCount() + 1);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
                //TODO : add fear impact on production buildings and soldiers
            case "garden":
                FearControl garden = new FearControl(currentEmpire);
                garden.garden();
                if (empireHasEnoughResourcesToBuildTheBuilding(garden, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(garden, currentEmpire)) {
                        buildingCheckout(garden, currentEmpire);
                        Map.AddToBuildingMap(x, y, garden);
                        if (currentEmpire.getFearRateNumber() != -5) {
                            currentEmpire.setFearRateNumber(currentEmpire.getFearRateNumber() - 1);
                        }
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "torture chamber":
                FearControl tortureChamber = new FearControl(currentEmpire);
                tortureChamber.tortureChamber();
                if (empireHasEnoughResourcesToBuildTheBuilding(tortureChamber, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(tortureChamber, currentEmpire)) {
                        buildingCheckout(tortureChamber, currentEmpire);
                        Map.AddToBuildingMap(x, y, tortureChamber);
                        if (currentEmpire.getFearRateNumber() != 5) {
                            currentEmpire.setFearRateNumber(currentEmpire.getFearRateNumber() + 1);
                        }
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Big Wall":
                Wall bigWall = new Wall(currentEmpire);
                bigWall.bigWall();
                if (empireHasEnoughResourcesToBuildTheBuilding(bigWall, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(bigWall, currentEmpire)) {
                        buildingCheckout(bigWall, currentEmpire);
                        Map.AddToBuildingMap(x, y, bigWall);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Small Wall":
                Wall smallWall = new Wall(currentEmpire);
                smallWall.smallWall();
                if (empireHasEnoughResourcesToBuildTheBuilding(smallWall, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(smallWall, currentEmpire)) {
                        buildingCheckout(smallWall, currentEmpire);
                        Map.AddToBuildingMap(x, y, smallWall);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Stairs":
                Wall stairs = new Wall(currentEmpire);
                stairs.stair();
                if (empireHasEnoughResourcesToBuildTheBuilding(stairs, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(stairs, currentEmpire)) {
                        if (validationOfStairsLocation(x, y)) {
                            buildingCheckout(stairs, currentEmpire);
                            Map.AddToBuildingMap(x, y, stairs);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = false;
                        } else return BuildingMessages.INPROPER_COORDINATE;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
        }
        return BuildingMessages.INVALID_BUILDING_NAME;
    }

    //TODO : check the ground type while adding the buildings in callBuildingFunction
    public BuildingMessages dropBuilding(Matcher xGroup, Matcher yGroup, Matcher typeGroup, Scanner scanner) {
        int x = Integer.parseInt(xGroup.group("x"));
        int y = Integer.parseInt(yGroup.group("y"));
        String type = typeGroup.group("type");
        if (checkCoordinate(x, y) == BuildingMessages.CONTINUE) {
            if (!Map.notBuildable[x][y]) {
                for (int i = 0; i < Manage.getNamesOfAllPossibleBuildings().size(); i++) {
                    if (Manage.getNamesOfAllPossibleBuildings().get(i).equals(type)) {
                        if (HasBuildingInThisPlace(x, y)) {
                            return callBuildingFunction(x, y, type, scanner);
                        } else return BuildingMessages.INVALID_BUILDING_NAME;
                    }
                }
            } else
                return BuildingMessages.FILLED_CELL;
        }
        return BuildingMessages.INVALID_COORDINATE;
    }

    public BuildingMessages selectBuilding(Matcher xGroup, Matcher yGroup) {
        int x = Integer.parseInt(xGroup.group("x"));
        int y = Integer.parseInt(yGroup.group("y"));
        if (checkCoordinate(x, y) == BuildingMessages.CONTINUE) {
            if (HasBuildingInThisPlace(x, y)) {
                if (Map.getBuildingMap()[x][y].get(0).getOwner().getName().equals(currentEmpire.getName())) {
                    if (currentEmpire.getName().equals(Map.getBuildingMap()[x][y].get(0).getOwner().getName())) {
                        selectedBuilding = Map.getBuildingMap()[x][y].get(0);
                        return BuildingMessages.SUCCESSFUL_SELECT;
                    }
                    return BuildingMessages.BUILDING_IS_NOT_FOR_THIS_EMPIRE;
                } else return BuildingMessages.NO_ACCESS;
            } else return BuildingMessages.EMPTY_CELL;
        }
        return BuildingMessages.INVALID_COORDINATE;
    }

    public static BuildingMessages repairBuilding(Building building) {
        // TODO A MENU FOR COMMANDS AFTER SELECTING THE BUILDING TO SEE WHICH CHANGE IS GONNA BE APPLIED
        ///TODO SHOULD WE PRINT THE HP OF EVERY BUILDING IN SELECT BUILDING? ----> i dont think soo(Arian)
        //TODO AFTER COMPLETING THE ARMIES SEARCH TO SEE IF ENEMIES ARE IN THE GIVEN POSITION
        int requiredStone = 50;
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

    public void SelectedBuilding(Matcher xGroup, Matcher yGroup) {
        int x = Integer.parseInt(xGroup.group("x"));
        int y = Integer.parseInt(yGroup.group("y"));
        Building building = findSelectedBuilding(x, y);
        SelectedBuildingController selectedBuildingController = new SelectedBuildingController();
        SelectedBuildingController.empire = currentEmpire;
        SelectedBuildingController.selectedBuilding = building;
        //TODO : i have defined the algorithm of the chosen buildings in the selectedBuildingController soo the only thing that must be done is that
        //TODO --> is to properly call that functions in maybe a menu !
//        System.out.println(building.showBuildingName());
    }
}

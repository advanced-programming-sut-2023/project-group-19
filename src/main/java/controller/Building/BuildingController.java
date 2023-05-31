package controller.Building;

import model.Building.*;
import model.Empire;
import model.Manage;
import model.Map;
import view.Messages.BuildingMessages;
import view.OldView.SelectedBuildingMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class BuildingController {

    public static int size = Map.mapSize;
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

    public boolean empireHasEnoughResourcesToBuildTheBuilding(Building building, Empire empire) {
        return building.cost.get("wood") <= empire.getWoodCount() && building.cost.get("stone") <= empire.getStoneCount() &&
                building.cost.get("gold") <= empire.getGoldCount() && building.cost.get("iron") <= empire.getIronCount() &&
                building.cost.get("oil") / 5 <= empire.getOilAmount();
    }

    public boolean empireHasEnoughWorkersToBuildTheBuilding(Building building, Empire empire) {
        return (building.workersNeeded.get("engineer") <= empire.getEngineerCount() &&
                building.workersNeeded.get("worker") <= empire.getWorkerCount());
    }

    public static void dropFirstStockpile(int x, int y) {
        Stockpile foodStockpile = new Stockpile(currentEmpire);
        foodStockpile.foodStockpile();
        Stockpile resourcesStockpile = new Stockpile(currentEmpire);
        resourcesStockpile.resourcesStockpile();
        Map.buildingMap[x + 1][y].clear();
        Map.buildingMap[x - 1][y].clear();
        Map.buildingMap[x + 1][y].add(foodStockpile);
        Map.buildingMap[x - 1][y].add(resourcesStockpile);
    }

    public boolean canBuildStockpile(int x, int y, String BuildingName) {
        if (Map.buildingMap[x + 1][y].size() != 0) {
            if (Map.buildingMap[x + 1][y].get(0).getName().equals(BuildingName))
                return true;
        }
        if (Map.buildingMap[x - 1][y].size() != 0) {
            if (Map.buildingMap[x - 1][y].get(0).getName().equals(BuildingName))
                return true;
        }
        if (Map.buildingMap[x][y + 1].size() != 0) {
            if (Map.buildingMap[x][y + 1].get(0).getName().equals(BuildingName))
                return true;
        }
        if (Map.buildingMap[x][y - 1].size() != 0) {
            return Map.buildingMap[x][y - 1].get(0).getName().equals(BuildingName);
        }
        return false;
    }

    public void buildingCheckout(Building building, Empire empire) {
        empire.setWoodCount(empire.getWoodCount() - building.cost.get("wood"));
        empire.setStoneCount(empire.getStoneCount() - building.cost.get("stone"));
        empire.setGoldCount(empire.getGoldCount() - building.cost.get("gold"));
        empire.setIronCount(empire.getIronCount() - building.cost.get("iron"));
        empire.setOilAmount(empire.getOilAmount() - building.cost.get("oil") / 5);
        empire.setWorkerCount(empire.getWorkerCount() - building.workersNeeded.get("worker"));
        empire.setEngineerCount(empire.getEngineerCount() - building.workersNeeded.get("engineer"));
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

    public BuildingMessages callBuildingFunction(int x, int y, String type) {
        String direction;
        Names directionOfGate;
        switch (type) {
            case "Shop":
                Shop shop = new Shop(currentEmpire);
                shop.shop();
                if (correctGroundType(x, y, shop)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(shop, Manage.getCurrentEmpire())) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(shop, currentEmpire)) {
                            buildingCheckout(shop, currentEmpire);
                            Map.AddToBuildingMap(x, y, shop);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "Castle":
                Castle castle = new Castle(currentEmpire);
                castle.castle();
                if (correctGroundType(x, y, castle)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(castle, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(castle, currentEmpire)) {
                            buildingCheckout(castle, currentEmpire);
                            Map.AddToBuildingMap(x, y, castle);
                            currentEmpire.castleXCoordinate = x;
                            currentEmpire.castleYCCoordinate = y;
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            dropFirstStockpile(x, y);
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "SmallStoneGatehouse":
                System.out.println(BuildingMessages.ENTER_DIRECTION.getMessages());
//                if (direction.equals(Names.NS.getName())) directionOfGate = Names.NS;
//                else if (direction.equals(Names.WE.getName())) directionOfGate = Names.WE;
//                else return BuildingMessages.INVALID_DIRECTION;
                StoneGateWay smallStoneGateWay = new StoneGateWay(currentEmpire);
                smallStoneGateWay.smallGateWay(Names.NS);
                if (correctGroundType(x, y, smallStoneGateWay)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(smallStoneGateWay, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(smallStoneGateWay, currentEmpire)) {
                            buildingCheckout(smallStoneGateWay, currentEmpire);
                            Map.AddToBuildingMap(x, y, smallStoneGateWay);
                            Map.notBuildable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wall[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "BigStoneGatehouse":
                System.out.println(BuildingMessages.ENTER_DIRECTION.getMessages());
//                if (direction.equals(Names.NS.getName())) directionOfGate = Names.NS;
//                else if (direction.equals(Names.WE.getName())) directionOfGate = Names.WE;
//                else return BuildingMessages.INVALID_DIRECTION;
                StoneGateWay bigStoneGateWay = new StoneGateWay(currentEmpire);
                bigStoneGateWay.bigGateWay(Names.NS);
                if (correctGroundType(x, y, bigStoneGateWay)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(bigStoneGateWay, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(bigStoneGateWay, currentEmpire)) {
                            buildingCheckout(bigStoneGateWay, currentEmpire);
                            Map.AddToBuildingMap(x, y, bigStoneGateWay);
                            Map.notBuildable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wall[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "DrawBridge":
                DrawBridge drawBridge = new DrawBridge(currentEmpire);
                drawBridge.drawBridge(x, y);
                if (correctGroundType(x, y, drawBridge)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(drawBridge, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(drawBridge, currentEmpire)) {
                            buildingCheckout(drawBridge, currentEmpire);
                            Map.AddToBuildingMap(x, y, drawBridge);
                            Manage.getCurrentEmpire().DrawBridge.add(x * size + y);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            Map.wall[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "LookoutTower":
                Tower lookoutTower = new Tower(currentEmpire);
                lookoutTower.lookoutTower();
                if (correctGroundType(x, y, lookoutTower)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(lookoutTower, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(lookoutTower, currentEmpire)) {
                            buildingCheckout(lookoutTower, currentEmpire);
                            Map.AddToBuildingMap(x, y, lookoutTower);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            Map.wall[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "PerimeterTower":
                Tower perimeterTower = new Tower(currentEmpire);
                perimeterTower.perimeterTower();
                if (correctGroundType(x, y, perimeterTower)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(perimeterTower, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(perimeterTower, currentEmpire)) {
                            buildingCheckout(perimeterTower, currentEmpire);
                            Map.AddToBuildingMap(x, y, perimeterTower);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            Map.wall[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "DefendTower":
                Tower defendTower = new Tower(currentEmpire);
                defendTower.defendTower();
                if (correctGroundType(x, y, defendTower)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(defendTower, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(defendTower, currentEmpire)) {
                            buildingCheckout(defendTower, currentEmpire);
                            Map.AddToBuildingMap(x, y, defendTower);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            Map.wall[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "SquareTower":
                Tower squareTower = new Tower(currentEmpire);
                squareTower.squareTower();
                if (correctGroundType(x, y, squareTower)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(squareTower, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(squareTower, currentEmpire)) {
                            buildingCheckout(squareTower, currentEmpire);
                            Map.AddToBuildingMap(x, y, squareTower);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            Map.wall[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "RoundTower":
                Tower roundTower = new Tower(currentEmpire);
                roundTower.roundTower();
                if (correctGroundType(x, y, roundTower)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(roundTower, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(roundTower, currentEmpire)) {
                            buildingCheckout(roundTower, currentEmpire);
                            Map.AddToBuildingMap(x, y, roundTower);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wall[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "Armoury":
                Armoury armoury = new Armoury(currentEmpire);
                armoury.armoury();
                if (correctGroundType(x, y, armoury)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(armoury, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(armoury, currentEmpire)) {
                            buildingCheckout(armoury, currentEmpire);
                            currentEmpire.setWeaponsCapacity(currentEmpire.getWeaponsCapacity() + armoury.getCapacity());
                            Map.AddToBuildingMap(x, y, armoury);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "Barracks":
                PrepareLaboursAndFighters barracks = new PrepareLaboursAndFighters(currentEmpire);
                barracks.barracks();
                if (correctGroundType(x, y, barracks)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(barracks, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(barracks, currentEmpire)) {
                            buildingCheckout(barracks, currentEmpire);
                            Map.AddToBuildingMap(x, y, barracks);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "Mercenary":
                PrepareLaboursAndFighters mercenaryPost = new PrepareLaboursAndFighters(currentEmpire);
                mercenaryPost.mercenary();
                if (correctGroundType(x, y, mercenaryPost)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(mercenaryPost, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(mercenaryPost, currentEmpire)) {
                            buildingCheckout(mercenaryPost, currentEmpire);
                            Map.AddToBuildingMap(x, y, mercenaryPost);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "EngineerGuild":
                PrepareLaboursAndFighters engineerGuild = new PrepareLaboursAndFighters(currentEmpire);
                engineerGuild.engineerGuild();
                if (correctGroundType(x, y, engineerGuild)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(engineerGuild, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(engineerGuild, currentEmpire)) {
                            buildingCheckout(engineerGuild, currentEmpire);
                            Map.AddToBuildingMap(x, y, engineerGuild);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "KillingPit":
                KillingPit killingPit = new KillingPit(currentEmpire);
                killingPit.killingPit();
                if (correctGroundType(x, y, killingPit)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(killingPit, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(killingPit, currentEmpire)) {
                            buildingCheckout(killingPit, currentEmpire);
                            Map.AddToBuildingMap(x, y, killingPit);
                            Map.notBuildable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "Inn":
                Inn inn = new Inn(currentEmpire);
                inn.inn();
                if (correctGroundType(x, y, inn)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(inn, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(inn, currentEmpire)) {
                            buildingCheckout(inn, currentEmpire);
                            Map.AddToBuildingMap(x, y, inn);
                            currentEmpire.setInnCount(currentEmpire.getInnCount() + 1);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "Mill":
                Industry mill = new Industry(currentEmpire);
                mill.mill();
                if (correctGroundType(x, y, mill)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(mill, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(mill, currentEmpire)) {
                            buildingCheckout(mill, currentEmpire);
                            Map.AddToBuildingMap(x, y, mill);
                            currentEmpire.setMillCount(currentEmpire.getMillCount() + 1);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "IronDig":
                Industry ironDig = new Industry(currentEmpire);
                ironDig.ironDig();
                if (correctGroundType(x, y, ironDig)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(ironDig, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(ironDig, currentEmpire)) {
                            buildingCheckout(ironDig, currentEmpire);
                            Map.AddToBuildingMap(x, y, ironDig);
                            currentEmpire.setIronMineCount(currentEmpire.getIronMineCount() + 1);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "OxTether":
                Industry oxTether = new Industry(currentEmpire);
                oxTether.oxTether();
                if (correctGroundType(x, y, oxTether)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(oxTether, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(oxTether, currentEmpire)) {
                            buildingCheckout(oxTether, currentEmpire);
                            currentEmpire.setOxTetherCount(currentEmpire.getOxTetherCount() + 1);
                            Map.AddToBuildingMap(x, y, oxTether);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "PitchRig":
                Industry pitchRig = new Industry(currentEmpire);
                pitchRig.pitchRig();
                if (correctGroundType(x, y, pitchRig)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(pitchRig, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(pitchRig, currentEmpire)) {
                            buildingCheckout(pitchRig, currentEmpire);
                            currentEmpire.setPitchRigCount(currentEmpire.getPitchRigCount() + 1);
                            Map.AddToBuildingMap(x, y, pitchRig);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "Quarry":
                Industry quarry = new Industry(currentEmpire);
                quarry.quarry();
                if (correctGroundType(x, y, quarry)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(quarry, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(quarry, currentEmpire)) {
                            buildingCheckout(quarry, currentEmpire);
                            Map.AddToBuildingMap(x, y, quarry);
                            currentEmpire.setQuarryCount(currentEmpire.getQuarryCount() + 1);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "FoodStockpile":
                Stockpile foodStockpile = new Stockpile(currentEmpire);
                foodStockpile.foodStockpile();
                if (correctGroundType(x, y, foodStockpile)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(foodStockpile, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(foodStockpile, currentEmpire)) {
                            if (canBuildStockpile(x, y, "Food Stockpile")) {
                                buildingCheckout(foodStockpile, currentEmpire);
                                Map.AddToBuildingMap(x, y, foodStockpile);
                                currentEmpire.setFoodCapacity(currentEmpire.getFoodCapacity() + foodStockpile.maxFoodCapacity);
                                Map.notBuildable[x][y] = true;
                                Map.notPassable[x][y] = true;
                                Map.wallPassable[x][y] = true;
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
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "ResourcesStockpile":
                Stockpile resourcesStockpile = new Stockpile(currentEmpire);
                resourcesStockpile.resourcesStockpile();
                if (correctGroundType(x, y, resourcesStockpile)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(resourcesStockpile, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(resourcesStockpile, currentEmpire)) {
                            if (canBuildStockpile(x, y, "Resources Stockpile")) {
                                buildingCheckout(resourcesStockpile, currentEmpire);
                                Map.AddToBuildingMap(x, y, resourcesStockpile);
                                currentEmpire.setResourcesCapacity(currentEmpire.getResourcesCapacity() + resourcesStockpile.maxResourcesCapacity);
                                Map.notBuildable[x][y] = true;
                                Map.notPassable[x][y] = true;
                                Map.wallPassable[x][y] = true;
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
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "WoodCutter":
                Industry woodCutter = new Industry(currentEmpire);
                woodCutter.woodCutter();
                if (correctGroundType(x, y, woodCutter)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(woodCutter, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(woodCutter, currentEmpire)) {
                            buildingCheckout(woodCutter, currentEmpire);
                            Map.AddToBuildingMap(x, y, woodCutter);
                            currentEmpire.setWoodCutterCount(currentEmpire.getWoodCutterCount() + 1);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "House":
                House house = new House(currentEmpire);
                house.house();
                if (correctGroundType(x, y, house)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(house, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(house, currentEmpire)) {
                            buildingCheckout(house, currentEmpire);
                            Map.AddToBuildingMap(x, y, house);
                            currentEmpire.setPopulation(currentEmpire.getPopulation() + house.getCapacity());
                            currentEmpire.setPeasantCount(currentEmpire.getPeasantCount() + house.getCapacity());
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "SmallChurch":
                Church smallChurch = new Church(currentEmpire);
                smallChurch.smallChurch();
                if (correctGroundType(x, y, smallChurch)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(smallChurch, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(smallChurch, currentEmpire)) {
                            buildingCheckout(smallChurch, currentEmpire);
                            Map.AddToBuildingMap(x, y, smallChurch);
                            currentEmpire.setPopularityFactorReligious(currentEmpire.getPopularityFactorReligious() + smallChurch.getPopularityIncreaseRate());
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "BigChurch":
                Church bigChurch = new Church(currentEmpire);
                bigChurch.bigChurch();
                if (correctGroundType(x, y, bigChurch)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(bigChurch, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(bigChurch, currentEmpire)) {
                            buildingCheckout(bigChurch, currentEmpire);
                            Map.AddToBuildingMap(x, y, bigChurch);
                            currentEmpire.setPopularityFactorReligious(currentEmpire.getPopularityFactorReligious() + bigChurch.getPopularityIncreaseRate());
                            currentEmpire.setPriestCount(currentEmpire.getPriestCount() + 1);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "Armourer":
                Weapon armourer = new Weapon(currentEmpire);
                armourer.armourer();
                if (correctGroundType(x, y, armourer)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(armourer, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(armourer, currentEmpire)) {
                            buildingCheckout(armourer, currentEmpire);
                            Map.AddToBuildingMap(x, y, armourer);
                            currentEmpire.setArmourerBuildingCount(currentEmpire.getArmourerBuildingCount() + 1);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "Blacksmith":
                Weapon blackSmith = new Weapon(currentEmpire);
                blackSmith.blacksmith();
                if (correctGroundType(x, y, blackSmith)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(blackSmith, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(blackSmith, currentEmpire)) {
                            buildingCheckout(blackSmith, currentEmpire);
                            Map.AddToBuildingMap(x, y, blackSmith);
                            currentEmpire.setBlacksmithBuildingCount(currentEmpire.getBlacksmithBuildingCount() + 1);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "Fletcher":
                Weapon fletcher = new Weapon(currentEmpire);
                fletcher.fletcher();
                if (correctGroundType(x, y, fletcher)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(fletcher, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(fletcher, currentEmpire)) {
                            buildingCheckout(fletcher, currentEmpire);
                            Map.AddToBuildingMap(x, y, fletcher);
                            currentEmpire.setFletcherBuildingCount(currentEmpire.getFletcherBuildingCount() + 1);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "PoleTurner":
                Weapon poleTurner = new Weapon(currentEmpire);
                poleTurner.poleTurner();
                if (correctGroundType(x, y, poleTurner)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(poleTurner, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(poleTurner, currentEmpire)) {
                            buildingCheckout(poleTurner, currentEmpire);
                            Map.AddToBuildingMap(x, y, poleTurner);
                            currentEmpire.setPoleTurnerBuildingCount(currentEmpire.getPoleTurnerBuildingCount() + 1);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "OilSmelter":
                OilSmelter oilSmelter = new OilSmelter(currentEmpire);
                oilSmelter.oilSmelter();
                if (correctGroundType(x, y, oilSmelter)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(oilSmelter, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(oilSmelter, currentEmpire)) {
                            buildingCheckout(oilSmelter, currentEmpire);
                            Map.AddToBuildingMap(x, y, oilSmelter);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            Manage.getCurrentEmpire().pourOilCoordinate.add(x * size + y);
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "PitchDitch":
                PitchDitch pitchDitch = new PitchDitch(currentEmpire);
                pitchDitch.pitchDitch();
                if (correctGroundType(x, y, pitchDitch)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(pitchDitch, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(pitchDitch, currentEmpire)) {
                            buildingCheckout(pitchDitch, currentEmpire);
                            Map.AddToBuildingMap(x, y, pitchDitch);
                            Map.notBuildable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "CagedWarDogs":
                CagedWarDogs cagedWarDogs = new CagedWarDogs(currentEmpire);
                cagedWarDogs.cagedWarDogs();
                if (correctGroundType(x, y, cagedWarDogs)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(cagedWarDogs, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(cagedWarDogs, currentEmpire)) {
                            buildingCheckout(cagedWarDogs, currentEmpire);
                            Map.AddToBuildingMap(x, y, cagedWarDogs);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            Manage.getCurrentEmpire().cagedWarDogsCoordinate.add(x * size + y);
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "SiegeTent":
                SiegeTent siegeTent = new SiegeTent(currentEmpire);
                siegeTent.siegeTent();
                if (correctGroundType(x, y, siegeTent)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(siegeTent, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(siegeTent, currentEmpire)) {
                            buildingCheckout(siegeTent, currentEmpire);
                            Map.AddToBuildingMap(x, y, siegeTent);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "Stable":
                Stable stable = new Stable(currentEmpire);
                stable.stable();
                if (correctGroundType(x, y, stable)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(stable, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(stable, currentEmpire)) {
                            buildingCheckout(stable, currentEmpire);
                            Map.AddToBuildingMap(x, y, stable);
                            currentEmpire.setStableBuildingCount(currentEmpire.getStableBuildingCount() + 1);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "AppleFarm":
                Goods appleFarm = new Goods(currentEmpire);
                appleFarm.appleFarm();
                if (correctGroundType(x, y, appleFarm)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(appleFarm, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(appleFarm, currentEmpire)) {
                            buildingCheckout(appleFarm, currentEmpire);
                            Map.AddToBuildingMap(x, y, appleFarm);
                            currentEmpire.setAppleFarmCount(currentEmpire.getAppleFarmCount() + 1);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "DairyProduct":
                Goods dairyProduct = new Goods(currentEmpire);
                dairyProduct.dairyProduct();
                if (correctGroundType(x, y, dairyProduct)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(dairyProduct, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(dairyProduct, currentEmpire)) {
                            buildingCheckout(dairyProduct, currentEmpire);
                            Map.AddToBuildingMap(x, y, dairyProduct);
                            currentEmpire.setDairyFactoryRate(currentEmpire.getDairyFactoryRate() + 1);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "OatFarm":
                Goods oatFarm = new Goods(currentEmpire);
                oatFarm.oatFarm();
                if (correctGroundType(x, y, oatFarm)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(oatFarm, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(oatFarm, currentEmpire)) {
                            buildingCheckout(oatFarm, currentEmpire);
                            Map.AddToBuildingMap(x, y, oatFarm);
                            currentEmpire.setOatFarmCount(currentEmpire.getOatFarmCount() + 1);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "HuntingPost":
                Goods huntingPost = new Goods(currentEmpire);
                huntingPost.huntingPost();
                if (correctGroundType(x, y, huntingPost)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(huntingPost, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(huntingPost, currentEmpire)) {
                            buildingCheckout(huntingPost, currentEmpire);
                            Map.AddToBuildingMap(x, y, huntingPost);
                            currentEmpire.setHuntingPostCount(currentEmpire.getHuntingPostCount() + 1);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "WheatFarm":
                Goods wheatFarm = new Goods(currentEmpire);
                wheatFarm.wheatFarm();
                if (correctGroundType(x, y, wheatFarm)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(wheatFarm, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(wheatFarm, currentEmpire)) {
                            buildingCheckout(wheatFarm, currentEmpire);
                            Map.AddToBuildingMap(x, y, wheatFarm);
                            currentEmpire.setWheatFarmCount(currentEmpire.getWheatFarmCount() + 1);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "Bakery":
                Goods bakery = new Goods(currentEmpire);
                bakery.bakery();
                if (correctGroundType(x, y, bakery)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(bakery, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(bakery, currentEmpire)) {
                            buildingCheckout(bakery, currentEmpire);
                            Map.AddToBuildingMap(x, y, bakery);
                            currentEmpire.setBakeryCount(currentEmpire.getBakeryCount() + 1);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "BearFactory":
                Goods bearFactory = new Goods(currentEmpire);
                bearFactory.bearFactory();
                if (correctGroundType(x, y, bearFactory)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(bearFactory, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(bearFactory, currentEmpire)) {
                            buildingCheckout(bearFactory, currentEmpire);
                            Map.AddToBuildingMap(x, y, bearFactory);
                            currentEmpire.setBeerFactoryCount(currentEmpire.getBeerFactoryCount() + 1);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "Garden":
                FearControl garden = new FearControl(currentEmpire);
                garden.garden();
                if (correctGroundType(x, y, garden)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(garden, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(garden, currentEmpire)) {
                            buildingCheckout(garden, currentEmpire);
                            Map.AddToBuildingMap(x, y, garden);
                            if (currentEmpire.getFearRateNumber() != -5) {
                                currentEmpire.setFearRateNumber(currentEmpire.getFearRateNumber() - 1);
                            }
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                }
                return BuildingMessages.IMPROPER_GROUND_TYPE;
            case "TortureChamber":
                FearControl tortureChamber = new FearControl(currentEmpire);
                tortureChamber.tortureChamber();
                if (correctGroundType(x, y, tortureChamber)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(tortureChamber, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(tortureChamber, currentEmpire)) {
                            buildingCheckout(tortureChamber, currentEmpire);
                            Map.AddToBuildingMap(x, y, tortureChamber);
                            if (currentEmpire.getFearRateNumber() != 5) {
                                currentEmpire.setFearRateNumber(currentEmpire.getFearRateNumber() + 1);
                            }
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wallPassable[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "BigWall":
                Wall bigWall = new Wall(currentEmpire);
                bigWall.bigWall();
                if (empireHasEnoughResourcesToBuildTheBuilding(bigWall, currentEmpire)) {
                    if (empireHasEnoughWorkersToBuildTheBuilding(bigWall, currentEmpire)) {
                        buildingCheckout(bigWall, currentEmpire);
                        Map.AddToBuildingMap(x, y, bigWall);
                        Map.notBuildable[x][y] = true;
                        Map.notPassable[x][y] = true;
                        Map.wall[x][y] = true;
                        return BuildingMessages.SUCCESS;
                    } else {
                        return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                    }
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "SmallWall":
                Wall smallWall = new Wall(currentEmpire);
                smallWall.smallWall();
                if (correctGroundType(x, y, smallWall)) {
                    if (empireHasEnoughResourcesToBuildTheBuilding(smallWall, currentEmpire)) {
                        if (empireHasEnoughWorkersToBuildTheBuilding(smallWall, currentEmpire)) {
                            buildingCheckout(smallWall, currentEmpire);
                            Map.AddToBuildingMap(x, y, smallWall);
                            Map.notBuildable[x][y] = true;
                            Map.notPassable[x][y] = true;
                            Map.wall[x][y] = true;
                            return BuildingMessages.SUCCESS;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                } else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
            case "Stair":
                Wall stairs = new Wall(currentEmpire);
                stairs.stair();
                if (correctGroundType(x, y, stairs)) {
                    System.out.println(11);
                    if (empireHasEnoughResourcesToBuildTheBuilding(stairs, currentEmpire)) {
                        System.out.println(22);
                        if (empireHasEnoughWorkersToBuildTheBuilding(stairs, currentEmpire)) {
                            System.out.println(33);
//                            if (validationOfStairsLocation(x, y)) {
                                System.out.println(44);
                                buildingCheckout(stairs, currentEmpire);
                                Map.AddToBuildingMap(x, y, stairs);
                                Map.notBuildable[x][y] = true;
                                Map.wall[x][y] = true;
                                return BuildingMessages.SUCCESS;
//                            } else return BuildingMessages.IMPROPER_COORDINATE;
                        } else {
                            return BuildingMessages.NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING;
                        }
                    } else {
                        return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                    }
                }
                else {
                    return BuildingMessages.IMPROPER_GROUND_TYPE;
                }
        }
        return BuildingMessages.INVALID_BUILDING_NAME;
    }

    public BuildingMessages dropBuilding(int x , int y, String type) {
        System.out.println(Manage.getCurrentEmpire());
        if (checkCoordinate(x, y) == BuildingMessages.CONTINUE) {
            if (!Map.notBuildable[x][y]) {
                for (int i = 0; i < Manage.getNamesOfAllPossibleBuildings().size(); i++) {
                    if (Manage.getNamesOfAllPossibleBuildings().get(i).equals(type)) {
                        if (!HasBuildingInThisPlace(x, y)) {
                            return callBuildingFunction(x, y, type);
                        } else return BuildingMessages.FILLED_CELL;
                    }
                }
                return BuildingMessages.INVALID_BUILDING_NAME;
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
                        SelectedBuildingMenu.buildingXCoordinate = x;
                        SelectedBuildingMenu.buildingYCoordinate = y;
                        return BuildingMessages.SUCCESSFUL_SELECT;
                    }
                    return BuildingMessages.BUILDING_IS_NOT_FOR_THIS_EMPIRE;
                } else return BuildingMessages.NO_ACCESS;
            } else return BuildingMessages.EMPTY_CELL;
        }
        return BuildingMessages.INVALID_COORDINATE;
    }

    public static BuildingMessages repairBuilding(Building building) {
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
}

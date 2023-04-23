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
                drawBridge.drawBridge(x , y);
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
            case "Armoury":
                Armoury armoury = new Armoury(currentEmpire);
                armoury.armoury();
                if (empireHasEnoughResourcesToBuildTheBuilding(armoury, currentEmpire)){
                    buildingCheckout(armoury, currentEmpire);
                    Map.AddToBuildingMap(x, y,armoury);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Barrack":
                PrepareLaboursAndFighters barracks = new PrepareLaboursAndFighters(currentEmpire);
                barracks.barracks();
                if (empireHasEnoughResourcesToBuildTheBuilding(barracks, currentEmpire)) {
                    buildingCheckout(barracks, currentEmpire);
                    Map.AddToBuildingMap(x, y, barracks);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Mercenary Post":
                PrepareLaboursAndFighters mercenaryPost = new PrepareLaboursAndFighters(currentEmpire);
                mercenaryPost.mercenary();
                if (empireHasEnoughResourcesToBuildTheBuilding(mercenaryPost, currentEmpire)) {
                    buildingCheckout(mercenaryPost, currentEmpire);
                    Map.AddToBuildingMap(x, y, mercenaryPost);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Engineer Guild":
                PrepareLaboursAndFighters engineerGuild = new PrepareLaboursAndFighters(currentEmpire);
                engineerGuild.engineerGuild();
                if (empireHasEnoughResourcesToBuildTheBuilding(engineerGuild, currentEmpire)) {
                    buildingCheckout(engineerGuild, currentEmpire);
                    Map.AddToBuildingMap(x, y, engineerGuild);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Killing Pit":
                KillingPit killingPit = new KillingPit(currentEmpire);
                killingPit.killingPit();
                if (empireHasEnoughResourcesToBuildTheBuilding(killingPit, currentEmpire)) {
                    buildingCheckout(killingPit, currentEmpire);
                    Map.AddToBuildingMap(x, y, killingPit);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Inn":
                Inn inn = new Inn(currentEmpire);
                inn.inn();
                if (empireHasEnoughResourcesToBuildTheBuilding(inn, currentEmpire)) {
                    buildingCheckout(inn, currentEmpire);
                    Map.AddToBuildingMap(x, y, inn);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Mill":
                Industry mill = new Industry(currentEmpire);
                mill.mill();
                if (empireHasEnoughResourcesToBuildTheBuilding(mill, currentEmpire)) {
                    buildingCheckout(mill, currentEmpire);
                    Map.AddToBuildingMap(x, y, mill);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Iron Dig":
                Industry ironDig = new Industry(currentEmpire);
                ironDig.ironDig();
                if (empireHasEnoughResourcesToBuildTheBuilding(ironDig, currentEmpire)) {
                    buildingCheckout(ironDig, currentEmpire);
                    Map.AddToBuildingMap(x, y, ironDig);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Market":
                Market market= new Market(currentEmpire);
                market.market();
                if (empireHasEnoughResourcesToBuildTheBuilding(market, currentEmpire)) {
                    buildingCheckout(market, currentEmpire);
                    Map.AddToBuildingMap(x, y, market);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Ox Tether":
                Industry oxTether = new Industry(currentEmpire);
                oxTether.oxTether();
                if (empireHasEnoughResourcesToBuildTheBuilding(oxTether, currentEmpire)) {
                    buildingCheckout(oxTether, currentEmpire);
                    Map.AddToBuildingMap(x, y, oxTether);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Pitch Rig":
                Industry pitchRig = new Industry(currentEmpire);
                pitchRig.pitchRig();
                if (empireHasEnoughResourcesToBuildTheBuilding(pitchRig, currentEmpire)) {
                    buildingCheckout(pitchRig, currentEmpire);
                    Map.AddToBuildingMap(x, y, pitchRig);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Quarry":
                Industry quarry = new Industry(currentEmpire);
                quarry.quarry();
                if (empireHasEnoughResourcesToBuildTheBuilding(quarry, currentEmpire)) {
                    buildingCheckout(quarry, currentEmpire);
                    Map.AddToBuildingMap(x, y, quarry);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Stockpile":
                Stockpile stockpile = new Stockpile(currentEmpire);
                stockpile.industryStockpile();
                if (empireHasEnoughResourcesToBuildTheBuilding(stockpile, currentEmpire)) {
                    buildingCheckout(stockpile, currentEmpire);
                    Map.AddToBuildingMap(x, y, stockpile);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Wood Cutter":
                Industry woodCutter = new Industry(currentEmpire);
                woodCutter.woodCutter();
                if (empireHasEnoughResourcesToBuildTheBuilding(woodCutter, currentEmpire)) {
                    buildingCheckout(woodCutter, currentEmpire);
                    Map.AddToBuildingMap(x, y, woodCutter);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "House":
                House house = new House(currentEmpire);
                house.house();
                if (empireHasEnoughResourcesToBuildTheBuilding(house, currentEmpire)) {
                    buildingCheckout(house, currentEmpire);
                    Map.AddToBuildingMap(x, y, house);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Small Church":
                Church smallChurch = new Church(currentEmpire);
                smallChurch.smallChurch();
                if (empireHasEnoughResourcesToBuildTheBuilding(smallChurch, currentEmpire)) {
                    buildingCheckout(smallChurch, currentEmpire);
                    Map.AddToBuildingMap(x, y, smallChurch);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Big Church":
                Church bigChurch = new Church(currentEmpire);
                bigChurch.bigChurch();
                if (empireHasEnoughResourcesToBuildTheBuilding(bigChurch, currentEmpire)) {
                    buildingCheckout(bigChurch, currentEmpire);
                    Map.AddToBuildingMap(x, y, bigChurch);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Armourer":
                Weapon armourer = new Weapon(currentEmpire);
                armourer.armourer();
                if (empireHasEnoughResourcesToBuildTheBuilding(armourer, currentEmpire)) {
                    buildingCheckout(armourer, currentEmpire);
                    Map.AddToBuildingMap(x, y, armourer);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Blacksmith":
                Weapon blackSmith = new Weapon(currentEmpire);
                blackSmith.blacksmith();
                if (empireHasEnoughResourcesToBuildTheBuilding(blackSmith, currentEmpire)) {
                    buildingCheckout(blackSmith, currentEmpire);
                    Map.AddToBuildingMap(x, y, blackSmith);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Fletcher":
                Weapon fletcher = new Weapon(currentEmpire);
                fletcher.fletcher();
                if (empireHasEnoughResourcesToBuildTheBuilding(fletcher, currentEmpire)) {
                    buildingCheckout(fletcher, currentEmpire);
                    Map.AddToBuildingMap(x, y, fletcher);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Pole Turner":
                Weapon poleTurner=new Weapon(currentEmpire);
                poleTurner.poleTurner();
                if (empireHasEnoughResourcesToBuildTheBuilding(poleTurner, currentEmpire)) {
                    buildingCheckout(poleTurner, currentEmpire);
                    Map.AddToBuildingMap(x, y, poleTurner);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Tunnel":
                Tunnel tunnel = new Tunnel(currentEmpire);
                tunnel.tunnel();
                if (empireHasEnoughResourcesToBuildTheBuilding(tunnel, currentEmpire)) {
                    buildingCheckout(tunnel, currentEmpire);
                    Map.AddToBuildingMap(x, y, tunnel);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Oil Smelter":
                OilSmelter oilSmelter = new OilSmelter(currentEmpire);
                oilSmelter.oilSmelter();
                if (empireHasEnoughResourcesToBuildTheBuilding(oilSmelter, currentEmpire)) {
                    buildingCheckout(oilSmelter, currentEmpire);
                    Map.AddToBuildingMap(x, y, oilSmelter);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Pitch Ditch":
                PitchDitch pitchDitch = new PitchDitch(currentEmpire);
                pitchDitch.pitchDitch();
                if (empireHasEnoughResourcesToBuildTheBuilding(pitchDitch, currentEmpire)) {
                    buildingCheckout(pitchDitch, currentEmpire);
                    Map.AddToBuildingMap(x, y, pitchDitch);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Caged War Dogs":
                CagedWarDogs cagedWarDogs = new CagedWarDogs(currentEmpire);
                cagedWarDogs.cagedWarDogs();
                if (empireHasEnoughResourcesToBuildTheBuilding(cagedWarDogs, currentEmpire)) {
                    buildingCheckout(cagedWarDogs, currentEmpire);
                    Map.AddToBuildingMap(x, y, cagedWarDogs);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Siege Tent":
                SiegeTent siegeTent = new SiegeTent(currentEmpire);
                siegeTent.siegeTent();
                if (empireHasEnoughResourcesToBuildTheBuilding(siegeTent, currentEmpire)) {
                    buildingCheckout(siegeTent, currentEmpire);
                    Map.AddToBuildingMap(x, y, siegeTent);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
            case "Stable":
                Stable stable = new Stable(currentEmpire);
                stable.stable();
                if (empireHasEnoughResourcesToBuildTheBuilding(stable, currentEmpire)) {
                    buildingCheckout(stable, currentEmpire);
                    Map.AddToBuildingMap(x, y, stable);
                    Map.notBuildable[x][y] = true;
                    Map.notPassable[x][y] = true;
                    return BuildingMessages.SUCCESS;
                } else {
                    return BuildingMessages.INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING;
                }
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
    //TODO : check the ground type while adding the buildings in callBuildingFunction
    public BuildingMessages dropBuilding(Matcher xGroup, Matcher yGroup, Matcher typeGroup) {
        int x = Integer.parseInt(xGroup.group("x"));
        int y = Integer.parseInt(yGroup.group("y"));
        String type = typeGroup.group("type");
        if (checkCoordinate(x, y) == BuildingMessages.CONTINUE) {
            if(!Map.notBuildable[x][y] ) {
                for (int i = 0; i < Manage.getNamesOfAllPossibleBuildings().size(); i++) {
                    if (Manage.getNamesOfAllPossibleBuildings().get(i).equals(type)) {
                        if (HasBuildingInThisPlace(x, y)) {
                            callBuildingFunction(x, y, type);
                            break;
                        } else return BuildingMessages.INVALID_BUILDING_NAME;
                    }
                }
            }
            else
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
                    if(currentEmpire.getName().equals(Map.getBuildingMap()[x][y].get(0).getOwner().getName())) {
                        selectedBuilding = Map.getBuildingMap()[x][y].get(0);
                        return BuildingMessages.SUCCESSFUL_SELECT;
                    }
                    return BuildingMessages.BUILDING_IS_NOT_FOR_THIS_EMPIRE;
                } else return BuildingMessages.NO_ACCESS;
            } else return BuildingMessages.EMPTY_CELL;
        }
        return BuildingMessages.INVALID_COORDINATE;
    }

    public BuildingMessages repairBuilding(Matcher xGroup, Matcher yGroup) {
        // TODO A MENU FOR COMMANDS AFTER SELECTING THE BUILDING TO SEE WHICH CHANGE IS GONNA BE APPLIED
        ///TODO SHOULD WE PRINT THE HP OF EVERY BUILDING IN SELECT BUILDING? ----> i dont think soo(Arian)
        //TODO AFTER COMPLETING THE ARMIES SEARCH TO SEE IF ENEMIES ARE IN THE GIVEN POSITION
        int x = Integer.parseInt(xGroup.group("x"));
        int y = Integer.parseInt(yGroup.group("y"));
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
    public void SelectedBuilding(Matcher xGroup, Matcher yGroup){
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

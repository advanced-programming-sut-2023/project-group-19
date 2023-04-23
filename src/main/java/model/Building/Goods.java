package model.Building;

import model.Empire;

import java.util.HashMap;

public class Goods extends Building implements BuildingConstantFunctions {
    public Goods(Empire government) {
        super(government);
    }

    private int rate;
    private Names names;

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Names getNames() {
        return names;
    }

    //TODO add WORKER
    public HashMap<String, Integer> cost = new HashMap<>();

    //TODO call createBuildingCost before usage
    public void createBuildingCost(int wood, int stone, int gold, int iron, int oil) {
        cost.put("wood", wood);
        cost.put("stone", stone);
        cost.put("gold", gold);
        cost.put("iron", iron);
        cost.put("oil", oil);
    }
    public HashMap<String, Integer> workersNeeded = new HashMap<>();
    public void createBuildingWorkersNeeded(int engineer, int worker) {
        workersNeeded.put("engineer", engineer);
        workersNeeded.put("worker", worker);

    }

    public void appleFarm() {
        rate = 20;
        names = Names.APPLE_FARM;
        createBuildingCost(5, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0 , 1);
    }

    public void dairyProduct() {
        rate = 5;
        names = Names.DAIRY_PRODUCT;
        createBuildingCost(10, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0 , 1);
    }

    public void oatFarm() {
        rate = 20;
        names = Names.OAT_FARM;
        createBuildingCost(15, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0 , 1);
    }

    public void huntingPost() {
        rate = 15;
        names = Names.HUNTING_POST;
        createBuildingCost(5, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0 , 1);
    }

    public void wheatFarm() {
        rate = 20;
        names = Names.WHEAT_FARM;
        createBuildingCost(15, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0 , 1);
    }

    public void bakery() {
        rate = 5;
        names = Names.BAKERY;
        createBuildingCost(10, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0 , 1);
    }

    public void bearFactory() {
        rate = 5;
        names = Names.BEAR_FACTORY;
        createBuildingCost(10, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0 , 1);
    }

    @Override
    public int maxHp() {
        return 999999999;
    }

    @Override
    public int hp() {
        return 999999999;
    }

    @Override
    public String groundType() {
        return null;
    }

    @Override
    public String showBuildingName() {
        return names.getName();
    }
}

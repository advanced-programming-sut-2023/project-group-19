package model.Building;

import model.Empire;

import java.util.HashMap;

public class Goods extends Building {
    Goods(Empire government) {
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

    public void appleFarm() {
        names = Names.APPLE_FARM;
        createBuildingCost(5, 0, 0, 0, 0);
    }

    public void dairyProduct() {
        names = Names.DAIRY_PRODUCT;
        createBuildingCost(10, 0, 0, 0, 0);
    }

    public void oatFarm() {
        names = Names.OAT_FARM;
        createBuildingCost(15, 0, 0, 0, 0);
    }

    public void huntingPost() {
        names = Names.HUNTING_POST;
        createBuildingCost(5, 0, 0, 0, 0);
    }

    public void wheatFarm() {
        names = Names.WHEAT_FARM;
        createBuildingCost(15, 0, 0, 0, 0);
    }

    public void bakery() {
        names = Names.BAKERY;
        createBuildingCost(10, 0, 0, 0, 0);
    }

    public void bearFactory() {
        names = Names.BEAR_FACTORY;
        createBuildingCost(10, 0, 0, 0, 0);
    }
}

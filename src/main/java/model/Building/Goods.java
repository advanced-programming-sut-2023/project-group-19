package model.Building;

import model.Empire;
import model.GroundType;

import java.util.HashMap;

public class Goods extends Building implements BuildingConstantFunctions {
    public Goods(Empire government) {
        super(government);
    }

    public Names getNames() {
        return this.name;
    }

    public void createBuildingCost(int wood, int stone, int gold, int iron, int oil) {
        this.cost.put("wood", wood);
        this.cost.put("stone", stone);
        this.cost.put("gold", gold);
        this.cost.put("iron", iron);
        this.cost.put("oil", oil);
    }

    public void createBuildingWorkersNeeded(int engineer, int worker) {
        this.workersNeeded.put("engineer", engineer);
        this.workersNeeded.put("worker", worker);
    }
    //TODO : decide the required ground type to build the farms
    public void appleFarm() {
        this.name = Names.APPLE_FARM;
        this.hp = 500;
        this.maxHp = 500;
        this.requiredGroundType = GroundType.FILLFUL_DASH.getGroundType();
        createBuildingCost(5, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 1);
    }

    public void dairyProduct() {
        this.name = Names.DAIRY_PRODUCT;
        this.hp = 500;
        this.maxHp = 500;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        createBuildingCost(10, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 1);
    }

    public void oatFarm() {
        this.name = Names.OAT_FARM;
        this.hp = 500;
        this.maxHp = 500;
        this.requiredGroundType = GroundType.FILLFUL_DASH.getGroundType();
        createBuildingCost(15, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 1);
    }

    public void huntingPost() {
        this.name = Names.HUNTING_POST;
        this.hp = 500;
        this.maxHp = 500;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        createBuildingCost(5, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 1);
    }

    public void wheatFarm() {
        this.name = Names.WHEAT_FARM;
        this.hp = 500;
        this.maxHp = 500;
        this.requiredGroundType = GroundType.FILLFUL_DASH.getGroundType();
        createBuildingCost(15, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 1);
    }

    public void bakery() {
        this.name = Names.BAKERY;
        this.hp = 500;
        this.maxHp = 500;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        createBuildingCost(10, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 1);
    }

    public void bearFactory() {
        this.name = Names.BEAR_FACTORY;
        this.hp = 500;
        this.maxHp = 500;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        createBuildingCost(10, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 1);
    }

    @Override
    public int maxHp() {
        return this.maxHp;
    }

    @Override
    public int hp() {
        return this.hp;
    }

    @Override
    public String groundType() {
        return this.requiredGroundType;
    }

    @Override
    public int height() {
        return this.height;
    }

    @Override
    public String showBuildingName() {
        return this.name.getName();
    }
}

package model.Building;

import model.Empire;
import model.GroundType;

import java.util.HashMap;

public class Stockpile extends Building implements BuildingConstantFunctions {
    public Stockpile(Empire government) {
        super(government);
    }

    public final int maxFoodCapacity = 100;
    public final int maxResourcesCapacity = 100;
    private int currentFoodCapacity;
    private int currentResourcesCapacity;

    public int getMaxFoodCapacity() {
        return maxFoodCapacity;
    }

    public int getMaxResourcesCapacity() {
        return maxResourcesCapacity;
    }

    public int getCurrentFoodCapacity() {
        return currentFoodCapacity;
    }

    public void setCurrentFoodCapacity(int currentFoodCapacity) {
        this.currentFoodCapacity = currentFoodCapacity;
    }

    public int getCurrentResourcesCapacity() {
        return currentResourcesCapacity;
    }

    public void setCurrentResourcesCapacity(int currentResourcesCapacity) {
        this.currentResourcesCapacity = currentResourcesCapacity;
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

    public void resourcesStockpile() {
        this.hp = 500;
        this.maxHp = 500;
        this.name = Names.RESOURCES_STOCKPILE;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        createBuildingCost(0, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 0);
    }

    public void foodStockpile() {
        this.hp = 500;
        this.maxHp = 500;
        this.name = Names.FOOD_STOCKPILE;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        createBuildingCost(5, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 0);
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

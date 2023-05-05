package model.Building;

import model.Empire;

import java.util.HashMap;

public class Stockpile extends Building implements BuildingConstantFunctions {
    public Stockpile(Empire government) {
        super(government);
    }

    public final int maxFoodCapacity = 100;
    public final int maxResourcesCapacity = 100;
    private int currentFoodCapacity;
    private int currentResourcesCapacity;
    private Names names;

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

    public void resourcesStockpile() {
        //TODO:INCREASE THE CURRENT CAPACITY EVERY TIME YOU ADD A THING TO STOCKPILE
        currentResourcesCapacity = 0;
        names = Names.RESOURCES_STOCKPILE;
        createBuildingCost(0, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 0);
    }

    public void foodStockpile() {
        names = Names.FOOD_STOCKPILE;
        createBuildingCost(5, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 0);
    }


    @Override
    public int maxHp() {
        return 0;
    }

    @Override
    public int hp() {
        return 0;
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

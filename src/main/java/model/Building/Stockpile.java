package model.Building;

import model.Empire;

import java.util.HashMap;

public class Stockpile extends Building implements BuildingConstantFunctions {
    public Stockpile(Empire government) {
        super(government);
    }

    public final int maxFoodCapacity=200;
    public final int maxIndustryCapacity=200;
    private int currentFoodCapacity;
    private int currentIndustryCapacity;
    private Names names;

    public int getMaxFoodCapacity() {
        return maxFoodCapacity;
    }

    public int getMaxIndustryCapacity() {
        return maxIndustryCapacity;
    }

    public int getCurrentFoodCapacity() {
        return currentFoodCapacity;
    }

    public void setCurrentFoodCapacity(int currentFoodCapacity) {
        this.currentFoodCapacity = currentFoodCapacity;
    }

    public int getCurrentIndustryCapacity() {
        return currentIndustryCapacity;
    }

    public void setCurrentIndustryCapacity(int currentIndustryCapacity) {
        this.currentIndustryCapacity = currentIndustryCapacity;
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

    public void industryStockpile() {
        //TODO:INCREASE THE CURRENT CAPACITY EVERY TIME YOU ADD A THING TO STOCKPILE
        currentIndustryCapacity=0;
        names = Names.STOCKPILE;
        createBuildingCost(0, 0, 0, 0, 0);
    }

    public void foodProcessingStockpile() {
        names = Names.FOOD_PROCESSING_STOCKPILE;
        createBuildingCost(5, 0, 0, 0, 0);
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

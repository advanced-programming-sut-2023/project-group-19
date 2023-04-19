package model.Building;

import model.Empire;

import java.util.HashMap;

public class Stockpile extends Building {
    Stockpile(Empire government) {
        super(government);
    }
    private int foodCapacity;
    private int industryCapacity;
    private Names names;

    public int getFoodCapacity() {
        return foodCapacity;
    }

    public void setFoodCapacity(int foodCapacity) {
        this.foodCapacity = foodCapacity;
    }

    public int getIndustryCapacity() {
        return industryCapacity;
    }

    public void setIndustryCapacity(int industryCapacity) {
        this.industryCapacity = industryCapacity;
    }

    public Names getNames() {
        return names;
    }
    //TODO add WORKER
    public HashMap<String , Integer> cost = new HashMap<>();
    //TODO call createBuildingCost before usage
    public void createBuildingCost(int wood , int stone , int gold , int iron , int oil ){
        cost.put("wood" , wood);
        cost.put("stone" , stone);
        cost.put("gold" , gold);
        cost.put("iron" , iron);
        cost.put("oil" , oil);
    }
    public void industryStockpile(){
        names = Names.STOCKPILE;
        createBuildingCost(0 , 0 , 0 , 0 , 0);
    }
    public void foodProcessingStockpile(){
        names = Names.FOOD_PROCESSING_STOCKPILE;
        createBuildingCost(5 , 0 , 0 , 0 , 0);
    }


}

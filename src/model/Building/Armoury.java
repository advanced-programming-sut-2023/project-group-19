package model.Building;

import model.Empire;

import java.util.HashMap;

public class Armoury extends Building {
    public Armoury(Empire government) {
        super(government);
    }
    private int capacity;
    private Names names;

    public Names getNames() {
        return names;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    //TODO hash map for armory
    public HashMap<String , Integer> cost = new HashMap<>();
    //TODO call createBuildingCost before usage
    public void createBuildingCost(int wood , int stone , int gold , int iron , int oil ){
        cost.put("wood" , wood);
        cost.put("stone" , stone);
        cost.put("gold" , gold);
        cost.put("iron" , iron);
        cost.put("oil" , oil);
    }
    public void armoury(){
        names = Names.ARMOURY;
        createBuildingCost(5 , 0 , 0 , 0 , 0);
    }
}

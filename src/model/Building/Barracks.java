package model.Building;

import model.Empire;

import java.util.HashMap;

public class Barracks extends Building {
    Barracks(Empire government) {
        super(government);
    }

    private int costOfBarrack;
    private Names names;

    public int getCostOfBarrack() {
        return costOfBarrack;
    }

    public void setCostOfBarrack(int costOfBarrack) {
        this.costOfBarrack = costOfBarrack;
    }

    public HashMap<String, Integer> cost = new HashMap<>();

    //TODO call createBuildingCost before usage
    public void createBuildingCost(int wood, int stone, int gold, int iron, int oil) {
        cost.put("wood", wood);
        cost.put("stone", stone);
        cost.put("gold", gold);
        cost.put("iron", iron);
        cost.put("oil", oil);
    }

    public void barracks() {
        names = Names.BARRACK;
        createBuildingCost(0, 15, 0, 0, 0);
    }
}

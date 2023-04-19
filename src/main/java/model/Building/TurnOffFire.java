package model.Building;

import model.Empire;

import java.util.HashMap;

public class TurnOffFire extends Building {
    TurnOffFire(Empire government) {
        super(government);
    }

    private Names names;

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

    public void well() {
        names = Names.WELL;
        createBuildingCost(0, 0, 30, 0, 0);
    }

    public void waterPot() {
        names = Names.WATER_POT;
        createBuildingCost(0, 0, 60, 0, 0);
    }
}

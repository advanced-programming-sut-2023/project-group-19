package model.Building;

import model.Empire;

import java.util.HashMap;

public class CagedWarDogs extends Building {
    CagedWarDogs(Empire government) {
        super(government);
    }

    private Names names;

    public Names getNames() {
        return names;
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

    public void cagedWarDogs() {
        names = Names.CAGED_WAR_DOGS;
        createBuildingCost(10, 0, 100, 0, 0);
    }
}

package model.Building;

import model.Empire;

import java.util.HashMap;

public class OilSmelter extends Building implements BuildingHPAndGroundType {

    OilSmelter(Empire government) {
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

    public void oilSmelter() {
        names = Names.OIL_SMELTER;
        createBuildingCost(0, 0, 100, 10, 0);
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
}

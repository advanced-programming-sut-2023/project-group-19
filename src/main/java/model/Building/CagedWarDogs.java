package model.Building;

import model.Empire;

import java.util.HashMap;

public class CagedWarDogs extends Building implements BuildingConstantFunctions {
    public CagedWarDogs(Empire government) {
        super(government);
    }
    private int hp;

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
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
        hp=500;
        names = Names.CAGED_WAR_DOGS;
        createBuildingCost(10, 0, 100, 0, 0);
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

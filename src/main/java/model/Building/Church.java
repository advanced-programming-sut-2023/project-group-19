package model.Building;

import model.Empire;
import model.Manage;

import java.util.HashMap;

public class Church extends Building implements BuildingConstantFunctions {

    public Church(Empire government) {
        super(government);
    }

    private int smallChurchPopularity;
    private int bigChurchPopularity;
    private Names names;

    public int getSmallChurchPopularity() {
        return smallChurchPopularity;
    }

    public void setSmallChurchPopularity(int smallChurchPopularity) {
        this.smallChurchPopularity = smallChurchPopularity;
    }

    public int getBigChurchPopularity() {
        return bigChurchPopularity;
    }

    public void setBigChurchPopularity(int bigChurchPopularity) {
        this.bigChurchPopularity = bigChurchPopularity;
    }

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

    public void smallChurch() {
        //TODO: PRIESTS INSIDE SMALL CHURCH
        names = Names.SMALL_CHURCH;
        Manage.getCurrentEmpire().popularity+=2;
        createBuildingCost(0, 0, 250, 0, 0);
    }

    public void bigChurch() {
        //TODO: PRODUCING PRIEST & TRAINING THEM
        names = Names.BIG_CHURCH;
        Manage.getCurrentEmpire().popularity+=2;
        createBuildingCost(0, 0, 1000, 0, 0);
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

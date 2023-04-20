package model.Building;

import model.Empire;

import java.util.HashMap;

public class Tower extends Building implements BuildingHPAndGroundType {
    Tower(Empire government) {
        super(government);
    }

    private int hp;
    private Names name;
    private int fireRange;
    private int defendRange;
    //TODO setter for isBig
    private boolean isBig = false;


    public Names getNames() {
        return name;
    }

    public void setNames(Names names) {
        this.name = names;
    }

    public boolean isBig() {
        return isBig;
    }

    public void setBig(boolean big) {
        isBig = big;
    }

    public int getFireRange() {
        return fireRange;
    }

    public void setFireRange(int fireRange) {
        this.fireRange = fireRange;
    }

    public int getDefendRange() {
        return defendRange;
    }

    public void setDefendRange(int defendRange) {
        this.defendRange = defendRange;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
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

    public void lookoutTower() {
        name = Names.LOOKOUT_TOWER;
        createBuildingCost(0, 10, 0, 0, 0);
    }

    public void perimeterTower() {
        name = Names.PERIMETER_TOWER;
        createBuildingCost(0, 10, 0, 0, 0);
    }

    public void defendTower() {
        name = Names.DEFEND_TOWER;
        createBuildingCost(0, 15, 0, 0, 0);
    }

    public void squareTower() {
        name = Names.SQUARE_TOWER;
        createBuildingCost(0, 35, 0, 0, 0);
    }

    public void roundTower() {
        name = Names.ROUND_TOWER;
        createBuildingCost(0, 40, 0, 0, 0);
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

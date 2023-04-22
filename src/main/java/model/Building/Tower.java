package model.Building;

import model.Empire;
import model.Map;

import java.util.HashMap;

public class Tower extends Building {
    public Tower(Empire government) {
        super(government);
    }

    private int hp;
    private int maxHp;
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

    @Override
    public int getMaxHp() {
        return maxHp;
    }

    @Override
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
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
        hp=-800;
        maxHp=800;
        fireRange=650;
        defendRange=700;
        name = Names.LOOKOUT_TOWER;
        createBuildingCost(0, 10, 0, 0, 0);
    }

    public void perimeterTower() {
        hp=-800;
        maxHp=800;
        fireRange=650;
        defendRange=700;
        name = Names.PERIMETER_TOWER;
        createBuildingCost(0, 10, 0, 0, 0);
    }

    public void defendTower() {
        hp=-1000;
        maxHp=1000;
        fireRange=650;
        defendRange=900;
        name = Names.DEFEND_TOWER;
        createBuildingCost(0, 15, 0, 0, 0);
    }

    public void squareTower() {
        hp=-800;
        maxHp=800;
        fireRange=650;
        defendRange=800;
        name = Names.SQUARE_TOWER;
        createBuildingCost(0, 35, 0, 0, 0);
    }

    public void roundTower() {
        hp=-850;
        maxHp=800;
        fireRange=650;
        defendRange=700;
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

    @Override
    public String showBuildingName() {
        return name.getName();
    }
}

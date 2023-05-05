package model.Building;

import model.Empire;
import model.GroundType;
import model.Map;

import java.util.HashMap;

public class Tower extends Building {
    public Tower(Empire government) {
        super(government);
    }

    private int fireRange;
    private int defendRange;
    private boolean isBig = false;
    private final int maxCapacity = 20;

    public Names getNames() {
        return this.name;
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
        return this.maxHp;
    }

    @Override
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void createBuildingCost(int wood, int stone, int gold, int iron, int oil) {
        this.cost.put("wood", wood);
        this.cost.put("stone", stone);
        this.cost.put("gold", gold);
        this.cost.put("iron", iron);
        this.cost.put("oil", oil);
    }

    public void createBuildingWorkersNeeded(int engineer, int worker) {
        this.workersNeeded.put("engineer", engineer);
        this.workersNeeded.put("worker", worker);

    }

    public void lookoutTower() {
        this.hp = 800;
        this.maxHp = 800;
        fireRange = 4;
        defendRange = 4;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        this.height = 4;
        this.name = Names.LOOKOUT_TOWER;
        createBuildingCost(0, 10, 0, 0, 0);
        createBuildingWorkersNeeded(0, 0);
    }

    public void perimeterTower() {
        this.hp = 800;
        this.maxHp = 800;
        fireRange = 3;
        defendRange = 3;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        this.height = 2;
        this.name = Names.PERIMETER_TOWER;
        createBuildingCost(0, 10, 0, 0, 0);
        createBuildingWorkersNeeded(0, 0);
    }

    public void defendTower() {
        this.hp = 1000;
        this.maxHp = 1000;
        fireRange = 3;
        defendRange = 3;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        this.height = 2;
        this.name = Names.DEFEND_TOWER;
        createBuildingCost(0, 15, 0, 0, 0);
        createBuildingWorkersNeeded(0, 0);
    }

    public void squareTower() {
        this.hp = 800;
        this.maxHp = 800;
        fireRange = 3;
        defendRange = 3;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        this.height = 2;
        this.name = Names.SQUARE_TOWER;
        createBuildingCost(0, 35, 0, 0, 0);
        createBuildingWorkersNeeded(0, 0);
    }

    public void roundTower() {
        this.hp = 850;
        this.maxHp = 850;
        fireRange = 3;
        defendRange = 3;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        this.height = 2;
        this.name = Names.ROUND_TOWER;
        createBuildingCost(0, 40, 0, 0, 0);
        createBuildingWorkersNeeded(0, 0);
    }

    @Override
    public int maxHp() {
        return this.maxHp;
    }

    @Override
    public int hp() {
        return this.hp;
    }

    @Override
    public String groundType() {
        return this.requiredGroundType;
    }

    @Override
    public int height() {
        return this.height;
    }

    @Override
    public String showBuildingName() {
        return this.name.getName();
    }
}

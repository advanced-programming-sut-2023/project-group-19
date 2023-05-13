package model.Building;

import model.Empire;
import model.GroundType;

import java.util.HashMap;

public class StoneGateWay extends Building implements BuildingConstantFunctions {
    public StoneGateWay(Empire government) {
        super(government);

    }

    public boolean flagOfEnemy;

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

    //TODO make a condition in the Building menu to see whether we have Gateway or not if we don't make an error
    public void smallGateWay(Names directionOfGate) {
        this.hp = 500;
        this.maxHp = 500;
        this.name = Names.SMALL_STONE_GATE_HOUSE;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        this.height = 2;
        createBuildingCost(0, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 0);
    }

    public void bigGateWay(Names directionOfGate) {
        this.hp = 700;
        this.maxHp = 700;
        this.name = Names.BIG_STONE_GATE_HOUSE;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        this.height = 2;
        createBuildingWorkersNeeded(0, 0);
        createBuildingCost(0, 20, 0, 0, 0);
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
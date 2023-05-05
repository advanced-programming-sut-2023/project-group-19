package model.Building;

import model.Empire;
import model.GroundType;

import java.util.HashMap;

public class Weapon extends Building implements BuildingConstantFunctions {
    public Weapon(Empire government) {
        super(government);
    }

    @Override
    public int getHp() {
        return this.hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    public Names getNames() {
        return this.name;
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

    public void armourer() {
        this.hp = 400;
        this.maxHp = 400;
        this.name = Names.ARMOURER;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        createBuildingCost(20, 0, 100, 0, 0);
        createBuildingWorkersNeeded(0, 1);
    }

    public void blacksmith() {
        this.hp = 500;
        this.maxHp = 500;
        this.name = Names.BLACKSMITH;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        createBuildingCost(20, 0, 100, 0, 0);
        createBuildingWorkersNeeded(0, 1);
    }

    public void fletcher() {
        this.hp = 400;
        this.maxHp = 400;
        this.name = Names.FLETCHER;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        createBuildingCost(20, 0, 100, 0, 0);
        createBuildingWorkersNeeded(0, 1);
    }

    public void poleTurner() {
        this.hp = 400;
        this.maxHp = 400;
        this.name = Names.POLE_TURNER;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        createBuildingCost(20, 0, 100, 0, 0);
        createBuildingWorkersNeeded(0, 1);
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

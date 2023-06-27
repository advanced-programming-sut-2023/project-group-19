package model.Building;

import model.Empire;
import model.GroundType;

import java.util.HashMap;

public class Inn extends Building implements BuildingConstantFunctions {
    public Inn(Empire government) {
        super(government);
    }

    public String getName() {
        return this.name.getName();
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

    public void inn() {
        this.name = Names.INN;
        this.hp = 600;
        this.maxHp = 600;
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

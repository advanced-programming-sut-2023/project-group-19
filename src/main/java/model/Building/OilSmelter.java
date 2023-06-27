package model.Building;

import model.Empire;
import model.GroundType;

import java.util.HashMap;

public class OilSmelter extends Building implements BuildingConstantFunctions {

    public OilSmelter(Empire government) {
        super(government);
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

    public void oilSmelter() {
        this.name = Names.OIL_SMELTER;
        this.hp = 400;
        this.maxHp = 400;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        createBuildingCost(0, 0, 100, 10, 0);
        createBuildingWorkersNeeded(1, 0);
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

package model.Building;

import model.Empire;
import model.GroundType;
import model.Manage;

import java.util.HashMap;

public class Church extends Building implements BuildingConstantFunctions {

    public Church(Empire government) {
        super(government);
    }

    private int popularityIncreaseRate;

    public int getPopularityIncreaseRate() {
        return popularityIncreaseRate;
    }

    public void setPopularityIncreaseRate(int popularityIncreaseRate) {
        this.popularityIncreaseRate = popularityIncreaseRate;
    }


    public Names getNames() {
        return this.name;
    }

    public void createBuildingCost(int wood, int stone, int gold, int iron, int oil) {
        cost.put("wood", wood);
        cost.put("stone", stone);
        cost.put("gold", gold);
        cost.put("iron", iron);
        cost.put("oil", oil);
    }

    public void createBuildingWorkersNeeded(int engineer, int worker) {
        workersNeeded.put("engineer", engineer);
        workersNeeded.put("worker", worker);

    }

    public void smallChurch() {
        this.name = Names.SMALL_CHURCH;
        this.hp = 500;
        this.maxHp = 500;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        popularityIncreaseRate = 1;
        createBuildingCost(0, 0, 250, 0, 0);
        createBuildingWorkersNeeded(0, 0);
    }

    public void bigChurch() {
        this.name = Names.BIG_CHURCH;
        this.hp = 1000;
        this.maxHp = 1000;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        popularityIncreaseRate = 2;
        createBuildingCost(0, 0, 1000, 0, 0);
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

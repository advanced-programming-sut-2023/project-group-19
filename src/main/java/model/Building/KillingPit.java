package model.Building;

import model.Empire;
import model.GroundType;

import java.util.HashMap;

public class KillingPit extends Building implements BuildingConstantFunctions {
    public KillingPit(Empire government) {
        super(government);
    }

    private boolean killingPitIsOn = true;

    public boolean isKillingPitIsOn() {
        return killingPitIsOn;
    }

    public void setKillingPitIsOn(boolean killingPitIsOn) {
        this.killingPitIsOn = killingPitIsOn;
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


    public void killingPit() {
        this.hp = 900;
        this.maxHp = 900;
        this.name = Names.KILLING_PIT;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        createBuildingCost(6, 0, 0, 0, 0);
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

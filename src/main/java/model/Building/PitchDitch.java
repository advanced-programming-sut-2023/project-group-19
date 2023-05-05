package model.Building;

import model.Empire;
import model.GroundType;

import java.util.HashMap;

public class PitchDitch extends Building implements BuildingConstantFunctions {
    public PitchDitch(Empire government) {
        super(government);
    }

    public boolean fireState;
    public int digState;
    //    public boolean digState;
    private boolean pitchDitchIsOn = true;
    public boolean digCompleted;

    public boolean isPitchDitchIsOn() {
        return pitchDitchIsOn;
    }

    public void setPitchDitchIsOn(boolean pitchDitchIsOn) {
        this.pitchDitchIsOn = pitchDitchIsOn;
    }

    public Names getNames() {
        return this.name;
    }

    public boolean isFireState() {
        return fireState;
    }

    public int isDigState() {
        return digState;
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

    public void pitchDitch() {
        this.hp = 100;
        this.maxHp = 100;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        this.name = Names.PITCH_DITCH;
        createBuildingCost(0, 0, 0, 0, 2);
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

package model.Building;

import model.Empire;

import java.util.HashMap;

public class PitchDitch extends Building implements BuildingConstantFunctions {
    public PitchDitch(Empire government) {
        super(government);
    }

    private Names names;
    public boolean fireState;
    public boolean digState;
    private boolean pitchDitchIsOn = true;
    public boolean digCompleted;

    public boolean isPitchDitchIsOn() {
        return pitchDitchIsOn;
    }

    public void setPitchDitchIsOn(boolean pitchDitchIsOn) {
        this.pitchDitchIsOn = pitchDitchIsOn;
    }

    public Names getNames() {
        return names
                ;
    }

    public boolean isFireState() {
        return fireState;
    }

    public boolean isDigState() {
        return digState;
    }

    public boolean isDigCompletely() {
        return digCompleted;
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

    public HashMap<String, Integer> workersNeeded = new HashMap<>();

    public void createBuildingWorkersNeeded(int engineer, int worker) {
        workersNeeded.put("engineer", engineer);
        workersNeeded.put("worker", worker);

    }

    //TODO oil per 5 square
    public void pitchDitch() {
        names = Names.PITCH_DITCH;
        createBuildingCost(0, 0, 0, 0, 2);
        createBuildingWorkersNeeded(0, 0);
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
        return names.getName();
    }
}

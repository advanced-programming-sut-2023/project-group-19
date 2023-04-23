package model.Building;

import model.Empire;
import model.GroundType;

import java.util.HashMap;

public class Industry extends Building implements BuildingConstantFunctions {

    public Industry(Empire government) {
        super(government);
    }

    private int rateOfMill;
    private int rateOfIronDig;
    private int rateOfOxTether;
    private int rateOfPitchRig;
    private int rateOfQuarry;
    private int rateOfWoodCuter;
    private int capacityOfQuarry;
    private Names names;
    private GroundType groundType;

    public int getRateOfMill() {
        return rateOfMill;
    }

    public void setRateOfMill(int rateOfMill) {
        this.rateOfMill = rateOfMill;
    }

    public int getRateOfIronDig() {
        return rateOfIronDig;
    }

    public void setRateOfIronDig(int rateOfIronDig) {
        this.rateOfIronDig = rateOfIronDig;
    }

    public int getRateOfOxTether() {
        return rateOfOxTether;
    }

    public void setRateOfOxTether(int rateOfOxTether) {
        this.rateOfOxTether = rateOfOxTether;
    }

    public int getRateOfPitchRig() {
        return rateOfPitchRig;
    }

    public void setRateOfPitchRig(int rateOfPitchRig) {
        this.rateOfPitchRig = rateOfPitchRig;
    }

    public int getRateOfQuarry() {
        return rateOfQuarry;
    }

    public void setRateOfQuarry(int rateOfQuarry) {
        this.rateOfQuarry = rateOfQuarry;
    }

    public int getRateOfWoodCuter() {
        return rateOfWoodCuter;
    }

    public void setRateOfWoodCuter(int rateOfWoodCuter) {
        this.rateOfWoodCuter = rateOfWoodCuter;
    }

    public int getCapacityOfQuarry() {
        return capacityOfQuarry;
    }

    public void setCapacityOfQuarry(int capacityOfQuarry) {
        this.capacityOfQuarry = capacityOfQuarry;
    }

    public Names getNames() {
        return names;
    }

    public void setNames(Names names) {
        this.names = names;
    }

    //TODO add worker
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

    public void mill() {
        rateOfMill=0;//TODO: NO IDEA
        names = Names.MILL;
        createBuildingCost(20, 0, 0, 0, 0);
    }

    public void ironDig() {
        rateOfIronDig =0;//TODO:NO IDEA
        groundType=GroundType.IRON;
        names = Names.IRON_DIG;
        createBuildingCost(20, 0, 0, 0, 0);
    }

    public void oxTether() {
        rateOfOxTether=0;//TODO:NO IDEA
        names = Names.OX_TETHER;
        createBuildingCost(5, 0, 0, 0, 0);
    }

    public void pitchRig() {
        rateOfPitchRig=0;//TODO:NO IDEA
        names = Names.PITCH_RIG;
        groundType=GroundType.PLAIN;
        createBuildingCost(20, 0, 0, 0, 0);
    }

    public void quarry() {
        rateOfQuarry=0;//TODO: NO IDEA
        capacityOfQuarry=50;
        names = Names.QUARRY;
        createBuildingCost(20, 0, 0, 0, 0);
    }

    public void woodCutter() {
        rateOfWoodCuter=0;//TODO: NO IDEA
        names = Names.WOODCUTTER;
        createBuildingCost(3, 0, 0, 0, 0);
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

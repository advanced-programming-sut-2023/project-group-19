package model.Building;

import model.Empire;

import java.util.HashMap;

public class Industry extends Building implements BuildingHPAndGroundType {

    Industry(Empire government) {
        super(government);
    }

    private int rateOfMill;
    private int rateOfIronDig;
    private int rateOfOxTether;
    private int rateOfPitchRig;
    private int rateOfQuarry;
    private int rateOfWoodCuter;
    private Names names;

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

    public void mill() {
        names = Names.MILL;
        createBuildingCost(20, 0, 0, 0, 0);
    }

    public void ironDig() {
        names = Names.IRON_DIG;
        createBuildingCost(20, 0, 0, 0, 0);
    }

    public void oxTether() {
        names = Names.OX_TETHER;
        createBuildingCost(5, 0, 0, 0, 0);
    }

    public void oilSmelter() {
        names = Names.OIL_SMELTER;
        createBuildingCost(20, 0, 0, 0, 0);
    }

    public void quarry() {
        names = Names.QUARRY;
        createBuildingCost(20, 0, 0, 0, 0);
    }

    public void woodCutter() {
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
}

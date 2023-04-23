package model.Building;

import model.Empire;

import java.util.HashMap;

public class Weapon extends Building implements BuildingConstantFunctions {
    public Weapon(Empire government) {
        super(government);
    }
    private int hp;
    private String supplyName;
    private String supplyCount;
    private int productionRate;
    private Names names;

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public String getSupplyCount() {
        return supplyCount;
    }

    public void setSupplyCount(String supplyCount) {
        this.supplyCount = supplyCount;
    }

    public int getProductionRate() {
        return productionRate;
    }

    public void setProductionRate(int productionRate) {
        this.productionRate = productionRate;
    }

    public Names getNames() {
        return names;
    }

    //TODO add WORKER
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
    //TODO: COMPLETE SUPPLY NAME,SUPPLY COUNT,PRODUCTION RATE
    public void armourer() {
        hp=400;
        names = Names.ARMOURER;
        createBuildingCost(20, 0, 100, 0, 0);
        createBuildingWorkersNeeded(0 , 1);
    }

    public void blacksmith() {
        hp=500;
        names = Names.BLACKSMITH;
        createBuildingCost(20, 0, 100, 0, 0);
        createBuildingWorkersNeeded(0 , 1);
    }

    public void fletcher() {
        hp=400;
        names = Names.FLETCHER;
        createBuildingCost(20, 0, 100, 0, 0);
        createBuildingWorkersNeeded(0 , 1);
    }

    public void poleTurner() {
        hp=400;
        names = Names.POLE_TURNER;
        createBuildingCost(20, 0, 100, 0, 0);
        createBuildingWorkersNeeded(0 , 1);
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

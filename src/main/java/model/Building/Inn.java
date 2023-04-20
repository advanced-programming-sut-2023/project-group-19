package model.Building;

import model.Empire;

import java.util.HashMap;

public class Inn extends Building implements BuildingConstructorsFunctions {
    Inn(Empire government) {
        super(government);
    }

    private int popularityRate;
    private int wineUsage;
    private int rate;
    private Names name;

    public Names getName() {
        return name;
    }

    public int getPopularityRate() {
        return popularityRate;
    }

    public void setPopularityRate(int popularityRate) {
        this.popularityRate = popularityRate;
    }

    public int getWineUsage() {
        return wineUsage;
    }

    public void setWineUsage(int wineUsage) {
        this.wineUsage = wineUsage;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
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

    public void inn() {
        name = Names.INN;
        createBuildingCost(20, 0, 100, 0, 0);
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

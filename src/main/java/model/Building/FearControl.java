package model.Building;

import model.Empire;

import java.util.HashMap;

public class FearControl extends Building implements BuildingConstantFunctions{

    public FearControl(Empire government) {
        super(government);
    }
    private int hp;
    private Names name;

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public Names getName() {
        return name;
    }

    @Override
    public void setName(Names name) {
        this.name = name;
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
        return name.getName();
    }
    public void garden(){
        name = Names.GARDEN;
        createBuildingCost(20, 0, 0, 0, 0);
    }
    public void tortureChamber(){
        name = Names.TORTURE_CHAMBER;
        createBuildingCost(20, 0, 0, 0, 0);
    }
}

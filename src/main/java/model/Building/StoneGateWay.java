package model.Building;

import model.Empire;
import model.Manage;
import model.Map;

import java.util.HashMap;

public class StoneGateWay extends Building {
    public StoneGateWay(Empire government) {
        super(government);

    }

    private static Names name;
    private int capacity;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public static Names getNames() {
        return name;
    }

    public void setNames(Names names) {
        this.name = names;
    }

    private int hp;
    private static int maxHP;

    public static int getMaxHP() {
        return maxHP;
    }

    public static void setMaxHP(int maxHP) {
        maxHP = maxHP;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

//    public HashMap<String, Integer> cost = new HashMap<>();

    //TODO: call createBuildingCost before usage
    public void createBuildingCost(int wood, int stone, int gold, int iron, int oil) {
        cost.put("wood", wood);
        cost.put("stone", stone);
        cost.put("gold", gold);
        cost.put("iron", iron);
        cost.put("oil", oil);
    }

    //TODO make a condition in the Building menu to see whether we have Gateway or not if we don't make an error
    //TODO TAX FUNCTION IN GOVERNMENT MENU FOR GATES
    public void smallGateWay(int x, int y, Building currentBuilding) {
        hp = 500;
        maxHP = 500;
        name = Names.SMALL_STONE_GATE_HOUSE;
        capacity = 8;
        createBuildingCost(0, 0, 0, 0, 0);
    }

    public void bigGateWay(int x, int y, Building currentBuilding) {
        hp = 700;
        maxHP = 700;
        name = Names.BIG_STONE_GATE_HOUSE;
        capacity = 10;
        createBuildingCost(0, 20, 0, 0, 0);
    }
}
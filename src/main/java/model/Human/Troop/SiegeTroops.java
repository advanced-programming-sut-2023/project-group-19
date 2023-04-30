package model.Human.Troop;

import model.Building.BuildingConstantFunctions;
import model.Building.Names;
import model.Empire;

import java.util.HashMap;

public class SiegeTroops extends Army implements BuildingConstantFunctions {
    public SiegeTroops(Empire government) {
        super(government);
    }

    private Names names;


    //TODO add WORKER

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
    public HashMap<String, Integer> cost = new HashMap<>();
    @Override
    public String showBuildingName() {
        return names.getName();
    }
    public void createBuildingCost(int wood, int stone, int gold, int iron, int oil) {
        cost.put("wood", wood);
        cost.put("stone", stone);
        cost.put("gold", gold);
        cost.put("iron", iron);
        cost.put("oil", oil);
    }
    public void catapult(){
        names = Names.CATAPULT;
        createBuildingCost(0, 0, 150, 0, 0);
    }
    public void trebuchet(){
        names = Names.TREBUCHET;
        createBuildingCost(0, 0, 150, 0, 0);
    }
    public void siegeTower(){
        names = Names.SIEGE_TOWER;
        createBuildingCost(0, 0, 150, 0, 0);
    }

    public void fireBalista(){
        names = Names.FIRE_BALISTA;
        createBuildingCost(0, 0, 150, 0, 0);
    }
    public void batteringRam(){
        names = Names.BATTERING_RAM;
        createBuildingCost(0, 0, 150, 0, 0);
    }
    public void portableShield(){
        names = Names.PORTABLE_SHIELD;
        createBuildingCost(0, 0, 5, 0, 0);
    }



}

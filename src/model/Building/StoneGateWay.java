package model.Building;

import model.Empire;

import java.util.ArrayList;
import java.util.HashMap;


public class StoneGateWay extends Building{
    public StoneGateWay(Empire government) {
        super(government);

    }
    private Names name;

    public Names getNames() {
        return name;
    }

    public void setNames(Names names) {
        this.name = names;
    }

    private int hp;
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public HashMap<String , Integer> cost = new HashMap<>();
    //TODO: call createBuildingCost before usage
    public void createBuildingCost(int wood , int stone , int gold , int iron , int oil ){
        cost.put("wood" , wood);
        cost.put("stone" , stone);
        cost.put("gold" , gold);
        cost.put("iron" , iron);
        cost.put("oil" , oil);
    }
    public void smallGateWay(){
        name = Names.SMALL_STONE_GATE_HOUSE;
        createBuildingCost(0 , 0 , 0 , 0 , 0);
    }
    public void bigGateWay(){
        name = Names.BIG_STONE_GATE_HOUSE;
        createBuildingCost(0 , 20 , 0 , 0 , 0);
    }

}


package model.Building;

import model.Empire;

import java.util.HashMap;

public class DrawBridge extends Building {
    DrawBridge(Empire government) {
        super(government);
    }
    private Names names;

    public Names getNames() {
        return names;
    }

    private int hp;
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public HashMap<String , Integer> cost = new HashMap<>();
    //TODO call createBuildingCost before usage
    public void createBuildingCost(int wood , int stone , int gold , int iron , int oil ){
        cost.put("wood" , wood);
        cost.put("stone" , stone);
        cost.put("gold" , gold);
        cost.put("iron" , iron);
        cost.put("oil" , oil);
    }
    public void drawBridge(){
        names = Names.DRAW_BRIDGE;
        createBuildingCost(10 , 0 , 0 , 0 , 0);
    }

}

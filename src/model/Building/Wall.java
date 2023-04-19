package model.Building;

import model.Empire;

import javax.print.attribute.standard.MediaSize;
import java.util.HashMap;

public class Wall extends Building {
    Wall(Empire government) {
        super(government);
    }
    private int hp;
    private Names name;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Names getName() {
        return name;
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
    //TODO only need stone to build the wall
    public void bigWall(int x , int y){
        name = Names.BIG_WALL;
        createBuildingCost(0 , 0 , 0 , 0 , 0);
    }
    public void smallWall(int x , int y){
        name = Names.SMALL_WALL;
        createBuildingCost(0 , 0 , 0 , 0 , 0);
    }
    public void stair(int x , int y){
        name = Names.STAIR;
        createBuildingCost(0 , 0 , 0 , 0 , 0);
    }


}

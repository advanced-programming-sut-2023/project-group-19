package model.Building;

import model.Empire;

import javax.print.attribute.standard.MediaSize;
import java.util.HashMap;

public class Wall extends Building {
    public Wall(Empire government) {
        super(government);
    }

    private int hp;
    private int maxHp;
    private static Names name;
    private static Names thickness;
    public boolean enemyConqueredWall;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Names getName() {
        return name;
    }

    @Override
    public int getMaxHp() {
        return maxHp;
    }

    @Override
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public boolean isEnemyConqueredWall() {
        return enemyConqueredWall;
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

    public HashMap<String, Integer> workersNeeded = new HashMap<>();

    public void createBuildingWorkersNeeded(int engineer, int worker) {
        workersNeeded.put("engineer", engineer);
        workersNeeded.put("worker", worker);

    }

    //TODO only need stone to build the wall
    public void bigWall() {
        hp = 750;
        maxHp = 750;
        name = Names.BIG_WALL;
        thickness = Names.BIG_WALL_THICKNESS;
        createBuildingCost(0, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 0);
    }

    public void smallWall() {
        hp = 500;
        maxHp = 500;
        name = Names.SMALL_WALL;
        thickness = Names.SMALL_WALL_THICKNESS;
        createBuildingCost(0, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 0);
    }

    public void stair() {
        hp = 450;
        maxHp = 450;
        name = Names.STAIR;
        createBuildingCost(0, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 0);
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
}

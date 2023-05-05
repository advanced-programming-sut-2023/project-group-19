package model.Building;

import model.Empire;
import model.GroundType;

import javax.print.attribute.standard.MediaSize;
import java.util.HashMap;

public class Wall extends Building {
    public Wall(Empire government) {
        super(government);
    }

    //TODO call createBuildingCost before usage
    public void createBuildingCost(int wood, int stone, int gold, int iron, int oil) {
        this.cost.put("wood", wood);
        this.cost.put("stone", stone);
        this.cost.put("gold", gold);
        this.cost.put("iron", iron);
        this.cost.put("oil", oil);
    }

    public void createBuildingWorkersNeeded(int engineer, int worker) {
        this.workersNeeded.put("engineer", engineer);
        this.workersNeeded.put("worker", worker);
    }

    public void bigWall() {
        this.hp = 1000;
        this.maxHp = 1000;
        this.height = 4;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        this.name = Names.BIG_WALL;
        createBuildingCost(0, 25, 0, 0, 0);
        createBuildingWorkersNeeded(0, 0);
    }

    public void smallWall() {
        this.hp = 1000;
        this.maxHp = 1000;
        this.height = 3;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        this.name = Names.SMALL_WALL;
        createBuildingCost(0, 15, 0, 0, 0);
        createBuildingWorkersNeeded(0, 0);
    }

    public void stair() {
        this.hp = 450;
        this.maxHp = 450;
        this.height = 2;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        this.name = Names.STAIR;
        createBuildingCost(0, 10, 0, 0, 0);
        createBuildingWorkersNeeded(0, 0);
    }

    @Override
    public int getMaxHp() {
        return this.maxHp;
    }

    @Override
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    @Override
    public int maxHp() {
        return this.maxHp;
    }

    @Override
    public int hp() {
        return this.hp;
    }

    @Override
    public String groundType() {
        return this.requiredGroundType;
    }

    @Override
    public int height() {
        return this.height;
    }

    @Override
    public String showBuildingName() {
        return name.getName();
    }
}

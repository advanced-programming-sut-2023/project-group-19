package model.Building;

import model.Empire;
import model.GroundType;
import model.Manage;
import model.Map;

import java.util.HashMap;

public class DrawBridge extends Building implements BuildingConstantFunctions {
    public DrawBridge(Empire government) {
        super(government);
    }

    private Boolean bridgeState;

    public Names getNames() {
        return this.name;
    }

    public boolean bridgeOpen = false;

    public boolean isBridgeOpen() {
        return bridgeOpen;
    }

    public void setBridgeOpen(boolean bridgeOpen) {
        this.bridgeOpen = bridgeOpen;
    }

    public void setNames(Names names) {
        this.name = names;
    }


    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int x;

    public int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

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

    public void drawBridge(int x, int y) {
        this.x = x;
        this.y = y;
        this.name = Names.DRAW_BRIDGE;
        this.hp = 500;
        this.maxHp = 500;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        bridgeState = true;//passable
        createBuildingCost(10, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 0);
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
        return this.name.getName();
    }
}

package model.Building;

import model.Empire;
import model.Manage;
import model.Map;

import java.util.HashMap;

public class DrawBridge extends Building implements BuildingConstantFunctions {
    public DrawBridge(Empire government) {
        super(government);
    }

    private Names names;
    private Boolean bridgeState;//default : not passable

    public Names getNames() {
        return names;
    }
    public boolean bridgeOpen = false;

    public boolean isBridgeOpen() {
        return bridgeOpen;
    }

    public void setBridgeOpen(boolean bridgeOpen) {
        this.bridgeOpen = bridgeOpen;
    }

    public void setNames(Names names) {
        this.names = names;
    }

    private int hp;
    private static int defaultHP = 500;

    public void setDefaultHP(int defaultHP) {
        DrawBridge.defaultHP = defaultHP;
    }

    public int getDefaultHP() {
        return defaultHP;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    int x;
    int y;

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

    public Boolean getBridgeState() {
        return bridgeState;
    }

    public void setBridgeState(Boolean bridgeState) {
        this.bridgeState = bridgeState;
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

    public void drawBridge( int x  ,int y) {//check how to reduce the cost of building it from Government Cash
        this.x = x;
        this.y = y;
        names = Names.DRAW_BRIDGE;
        bridgeState = true;//passable
        createBuildingCost(10, 0, 0, 0, 0);
        Manage.getCurrentEmpire().setStoneCount(Manage.getCurrentEmpire().getStoneCount() - 10);
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
        return names.getName();
    }
}

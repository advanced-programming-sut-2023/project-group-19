package model.Building;

import model.Empire;

import java.util.HashMap;

public abstract class Building implements BuildingConstantFunctions {
    private Empire owner;
    private String requiredGroundType;
    private boolean castleType;

    public boolean isCastleType() {
        return castleType;
    }

    public void setCastleType(boolean castleType) {
        this.castleType = castleType;
    }

    public Names getName() {
        return name;
    }

    private Names name;
    private int hp;
    private int maxHp;
    public HashMap<String, Integer> cost = new HashMap<>();

    public HashMap<String, Integer> workersNeeded = new HashMap<>();

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public Names getNameEnum() {
        return name;
    }

    public String showBuildingName() {
        return name.getName();
    }

    public void setName(Names name) {
        this.name = name;
    }

    public String getRequiredGroundType() {
        return requiredGroundType;
    }

    public void setRequiredGroundType(String requiredGroundType) {
        this.requiredGroundType = requiredGroundType;
    }

    Building(Empire government) {
        this.owner = government;
    }

    public Empire getOwner() {
        return owner;
    }

    public void setOwner(Empire owner) {
        this.owner = owner;
    }
    //TODO MENU FOR BUIlDING COMMANDS

}

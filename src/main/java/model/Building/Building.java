package model.Building;

import model.Empire;

import java.util.HashMap;

public abstract class Building implements BuildingConstantFunctions {
    private Empire owner;
    public String requiredGroundType;
    public int fireCount;
    public int maxFireCount;
    public boolean onFire;


    public String getName() {
        return name.getName();
    }

    public Names name;
    public int hp;
    public int height;
    public int maxHp;
    public HashMap<String, Integer> cost = new HashMap<>();

    public HashMap<String, Integer> workersNeeded = new HashMap<>();

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

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
        owner = government;
    }

    public Empire getOwner() {
        return owner;
    }



}

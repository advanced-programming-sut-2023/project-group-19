package model.Building;

import model.Empire;
import model.Manage;

public abstract class Building {
    private Empire owner;
    private String requiredGroundType;
    private Names name;
    private int hp;
    private int maxHp;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public Names getName() {
        return name;
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

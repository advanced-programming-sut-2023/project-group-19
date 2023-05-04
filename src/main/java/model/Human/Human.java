package model.Human;

import model.Empire;
import model.Human.Troop.HumanConstantFunctions;

public abstract class Human implements HumanConstantFunctions {
    private Empire owner;
    private int hp;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Human (Empire government){
        this.owner = government;
    }

    public Empire getOwner() {
        return owner;
    }

    public void setOwner(Empire owner) {
        this.owner = owner;
    }
}

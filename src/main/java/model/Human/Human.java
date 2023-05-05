package model.Human;

import model.Empire;
import model.Human.Troop.HumanConstantFunctions;

import java.util.List;

public abstract class Human implements HumanConstantFunctions {
    private Empire owner;
    private int hp;
    public int xCoordinate;
    public int yCoordinate;
    public int restOfMoves;
    public int goalXCoordinate;
    public int goalYCoordinate;
    public List<Integer> myPath;

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

    @Override
    public int maxHp() {
        return 0;
    }

    @Override
    public int hp() {
        return 0;
    }

    @Override
    public int speed() {
        return 0;
    }

    @Override
    public int getCurrentX() {
        return 0;
    }

    @Override
    public int getCurrentY() {
        return 0;
    }

    @Override
    public int restOfMoves() {
        return 0;
    }

    @Override
    public int getGoalX() {
        return 0;
    }

    @Override
    public int getGoalY() {
        return 0;
    }

    @Override
    public List<Integer> myPath() {
        return myPath;
    }
}

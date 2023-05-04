package model.Human;

import model.Empire;

import java.util.List;

public class Worker extends Human {

    Worker(Empire government) {
        super(government);
    }

    private int hp = 1;
    private int maxHp;
    private int speed;
    private int xCoordinate;
    private int yCoordinate;
    public int goalXCoordinate;
    public int goalYCoordinate;
    public int restOfMoves;
    public List<Integer> myPath;
    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    //TODO set minimum speed
    private int defencePower;

    private int attackPower;

    public int getDefencePower() {
        return defencePower;
    }

    public void setDefencePower(int defencePower) {
        this.defencePower = defencePower;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    @Override
    public int maxHp() {
        return maxHp;
    }

    @Override
    public int hp() {
        return hp;
    }

    @Override
    public int speed() {
        return speed;
    }

    @Override
    public int getCurrentX() {
        return xCoordinate;
    }

    @Override
    public int getCurrentY() {
        return yCoordinate;
    }

    @Override
    public int restOfMoves() {
        return restOfMoves;
    }

    @Override
    public int getGoalX() {
        return goalXCoordinate;
    }
    @Override
    public int getGoalY() {
        return goalYCoordinate;
    }

    @Override
    public List<Integer> myPath() {
        return myPath;
    }
}

package model.Human;

import model.Empire;
import model.Human.Troop.HumanConstantFunctions;

import java.util.List;

public class Worker extends Human implements HumanConstantFunctions {

    Worker(Empire government) {
        super(government);
    }

    private int hp;
    private Names names;
    private int maxHp;
    private int speed;
    private int xCoordinate;
    private int yCoordinate;
    public int startXCoordinate;
    public int startYCoordinate;
    public int goalXCoordinate;
    public int goalYCoordinate;
    public int finalXCoordinate;
    public int finalYCoordinate;
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

    public void worker(int x, int y) {
        hp = 50;
        maxHp = 50;
        names = Names.WORKER;
        xCoordinate = x;
        yCoordinate = y;
        speed = 1;
        restOfMoves = 1;
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

    @Override
    public int getStartX() {
        return startXCoordinate;
    }

    @Override
    public int getStartY() {
        return startYCoordinate;
    }

    @Override
    public int getFinalX() {
        return finalXCoordinate;
    }

    @Override
    public int getFinalY() {
        return finalYCoordinate;
    }
}

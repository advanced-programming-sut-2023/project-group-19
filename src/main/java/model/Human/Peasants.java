package model.Human;

import model.Empire;

import java.util.List;

public class Peasants extends Human {
    Peasants(Empire government) {
        super(government);
    }

    private int hp = 1;
    private int maxHp;
    private int speed;
    private int xCoordinate;
    private int yCoordinate;
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public int maxHp() {
        return 0;
    }

    @Override
    public int hp() {
        return hp;
    }

    @Override
    public int speed() {
        return maxHp;
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
    public List<Integer> myPath() {
        return myPath;
    }
}

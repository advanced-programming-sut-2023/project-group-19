package model.Human.Troop;

import model.Empire;
import model.Human.Names;

import java.util.List;

public class Tunneler extends Army implements HumanConstantFunctions {
    public Tunneler(Empire government) {
        super(government);
    }

    private Names names;
    private int hp;
    private int maxHp;
    private int speed;
    private int attackPower;
    public int xCoordinate;
    public int yCoordinate;
    public int restOfMoves;
    public Names typeOfArmy;
    public int startXCoordinate;
    public int startYCoordinate;
    public int goalXCoordinate;
    public int goalYCoordinate;
    public int finalXCoordinate;
    public int finalYCoordinate;
    public List<Integer> myPath;

    public Names getNames() {
        return names;
    }

    public void setNames(Names names) {
        this.names = names;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void Tunneler(int x, int y) {
        hp = 100;
        maxHp = 100;
        typeOfArmy = Names.STANDING_AMRY;
        names = Names.TUNNELER;
        xCoordinate = x;
        yCoordinate = y;
        speed = 7;
        attackPower = 300;
        defencePower = 50;
        restOfMoves = 7;
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

    @Override
    public Names typeOfArmy() {
        return super.typeOfArmy();
    }
}

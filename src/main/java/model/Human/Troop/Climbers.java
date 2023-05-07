package model.Human.Troop;

import model.Empire;
import model.Human.Human;
import model.Human.Names;

import java.util.List;

public class Climbers extends Army implements HumanConstantFunctions {
    public Climbers(Empire government) {
        super(government);
    }

    private Names names;
    private int hp;
    private int maxHp;
    private int speed;
    private int defencePower;
    private int attackPower;
    private int attackRange;

    public int xCoordinate;

    public int yCoordinate;
    public Names typeOfArmy;
    public int restOfMoves;
    public int startXCoordinate;
    public int startYCoordinate;
    public int goalXCoordinate;
    public int goalYCoordinate;
    public int finalXCoordinate;
    public int finalYCoordinate;
    public boolean fight;
    public boolean ladderIsOn;
    public List<Integer> myPath;

    public void setNames(Names names) {
        this.names = names;
    }

    public Names getNames() {
        return names;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

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

    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    public Names getTypeOfArmy() {
        return typeOfArmy;
    }

    public boolean isLadderIsOn() {
        return ladderIsOn;
    }

    public void SpearMen(int x, int y) {
        xCoordinate = x;
        yCoordinate = y;
        hp = 500;
        maxHp = 500;
        names = Names.SPEAR_MEN;
        speed = 5;
        attackPower = 500;
        defencePower = 200;
        restOfMoves = 5;
    }

    public void MaceMen(int x, int y) {
        xCoordinate = x;
        yCoordinate = y;
        hp = 675;
        maxHp = 675;
        names = Names.MACE_MEN;
        speed = 5;
        attackPower = 700;
        defencePower = 250;
        restOfMoves = 5;
    }

    public void LadderMen(int x, int y) {
        xCoordinate = x;
        yCoordinate = y;
        hp = 500;
        maxHp = 500;
        names = Names.LADDER_MEN;
        speed = 7;
        attackPower = 0;
        defencePower = 20;
        restOfMoves = 7;
    }

    public void Assassins(int x, int y) {
        xCoordinate = x;
        yCoordinate = y;
        hp = 800;
        maxHp = 800;
        names = Names.ASSASSINS;
        speed = 5;
        attackPower = 600;
        defencePower = 150;
        restOfMoves = 5;
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


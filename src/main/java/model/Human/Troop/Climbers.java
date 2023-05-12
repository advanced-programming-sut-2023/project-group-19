package model.Human.Troop;

import model.Empire;
import model.Human.Human;
import model.Human.Names;

import java.util.List;

public class Climbers extends Army implements HumanConstantFunctions {
    public Climbers(Empire government) {
        super(government);
    }

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

    public int getAttackPower() {
        return attackPower;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void SpearMen(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.hp = 500;
        this.maxHp = 500;
        this.names = Names.SPEAR_MEN;
        this.typeOfArmy = Names.STANDING_ARMY;
        this.speed = 5;
        this.attackPower = 500;
        this.defencePower = 200;
        this.restOfMoves = 5;
    }

    public void MaceMen(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.hp = 675;
        this.maxHp = 675;
        this.names = Names.MACE_MEN;
        this.typeOfArmy = Names.STANDING_ARMY;
        this.speed = 5;
        this.attackPower = 700;
        this.defencePower = 250;
        this.restOfMoves = 5;
    }

    public void LadderMen(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.hp = 500;
        this.maxHp = 500;
        this. names = Names.LADDER_MEN;
        this.typeOfArmy = Names.STANDING_ARMY;
        this.speed = 7;
        this.attackPower = 0;
        this.defencePower = 20;
        this.restOfMoves = 7;
    }

    public void Assassins(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.hp = 800;
        this.maxHp = 800;
        this. names = Names.ASSASSINS;
        this.typeOfArmy = Names.STANDING_ARMY;
        this.speed = 5;
        this.attackPower = 600;
        this.defencePower = 150;
        this.restOfMoves = 5;
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
        return typeOfArmy;
    }
}


package model.Human.Troop;

import model.Empire;
import model.Human.Human;
import model.Human.Names;

import java.util.List;

public class Army extends Human{
    public Army(Empire government) {
        super(government);
    }
    private Names names;

    public Names getNames() {
        return names;
    }

    private String armyForm;
    private int hp;
    private int maxHp;
    private int speed;
    private int defencePower;
    private int attackPower;
    private int attackRange;

    public int xCoordinate;
    public int yCoordinate;
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

    public String getArmyForm() {
        return armyForm;
    }

    public void setArmyForm(String armyForm) {
        this.armyForm = armyForm;
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
    public List<Integer> myPath() {
        return myPath;
    }

}

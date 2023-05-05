package model.Human.Troop;

import model.Empire;
import model.Human.Human;
import model.Human.Names;

import java.util.List;

public class Army extends Human{
    public Army(Empire government) {
        super(government);
        this.empire = government;
    }
    private Empire empire;
    private Army enemy ;

    public Army getEnemy() {
        return enemy;
    }

    public void setEnemy(Army enemy) {
        this.enemy = enemy;
    }

    public Empire getEmpire() {
        return empire;
    }

    public void setEmpire(Empire empire) {
        this.empire = empire;
    }

    private Names names;

    public enum StateOfEnemy{
        STANDING,
        DEFENSIVE,
        OFFENSIVE ;
    }

    public Names getNames() {
        return names;
    }

    private StateOfEnemy armyForm;
    private int hp;
    private int maxHp;
    private int speed;
    private int defencePower;
    private int attackPower;
    private int attackRange;
    private int pastXcordinate = 500;

    public int getPastXcordinate() {
        return pastXcordinate;
    }

    public void setPastXcordinate(int pastXcordinate) {
        this.pastXcordinate = pastXcordinate;
    }

    public int getPastYcordinate() {
        return pastYcordinate;
    }

    public void setPastYcordinate(int pastYcordinate) {
        this.pastYcordinate = pastYcordinate;
    }

    private int pastYcordinate = 500;

    public int xCoordinate;
    public int yCoordinate;
    public int goalXCoordinate;
    public int goalYCoordinate;
    public List<Integer> myPath;
    public int restOfMoves;

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

    public StateOfEnemy getArmyForm() {
        return armyForm;
    }

    public void setArmyForm(StateOfEnemy armyForm) {
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

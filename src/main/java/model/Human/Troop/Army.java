package model.Human.Troop;

import model.Empire;
import model.Human.Human;
import model.Human.Names;
import model.Human.Names;

import java.util.List;

public class Army extends Human {
    public Army(Empire government) {
        super(government);
        this.empire = government;
    }

    public boolean isIntFight = false ;

    public boolean isIntFight() {
        return isIntFight;
    }

    public boolean hasMovedForDeffensiveState = false ;

    private ArchersAndThrowers archerAttacker = null ;

    public ArchersAndThrowers getArcherAttacker() {
        return archerAttacker;
    }

    public void setArcherAttacker(ArchersAndThrowers archerAttacker) {
        this.archerAttacker = archerAttacker;
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
    public int startXCoordinate;
    public int startYCoordinate;
    public int xCoordinate;
    public int yCoordinate;
    public int goalXCoordinate;
    public int goalYCoordinate;
    public int finalXCoordinate;
    public int finalYCoordinate;
    public List<Integer> myPath;
    public int restOfMoves;
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

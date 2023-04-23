package model.Human.Troop;

import model.Empire;
import model.Human.Human;
import model.Human.Names;

public class Army extends Human {
    public Army(Empire government) {
        super(government);
    }
    private Names names;

    public Names getNames() {
        return names;
    }

    private String armyForm;
    private int hp;
    private int speed;
    private int defencePower;
    private int attackPower;
    private int attackRange;

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
}

package model.Human.Troop;

import model.Empire;

public class FightTools extends Army {
    public FightTools(Empire government) {
        super(government);
    }

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

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public int getDefencePower() {
        return defencePower;
    }

    @Override
    public void setDefencePower(int defencePower) {
        this.defencePower = defencePower;
    }

    @Override
    public int getAttackPower() {
        return attackPower;
    }

    @Override
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    @Override
    public int getAttackRange() {
        return attackRange;
    }

    @Override
    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    //TODO : DELETE THIS CLASS
    public void portableShield() {

    }

    public void ram() {

    }

    public void conquerTower() {

    }

    public void normalCatapult() {

    }

    public void specialCatapult() {

    }

    public void fireThrower() {

    }
}

package model.Human.Troop;

import model.Empire;
import model.Human.Human;
import model.Human.Names;

public class Engineer extends Army {
    public Engineer(Empire government) {
        super(government);
    }

    private int hp;
    private int maxHp;
    private Names names;

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
    private int speed;

    public int xCoordinate;

    public int yCoordinate;

    public boolean isBossOfOilSmelter;
    public boolean isBowlFullOfOil;

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

    public boolean isBossOfOilSmelter() {
        return isBossOfOilSmelter;
    }

    public void setBossOfOilSmelter(boolean bossOfOilSmelter) {
        isBossOfOilSmelter = bossOfOilSmelter;
    }

    public boolean isBowlFullOfOil() {
        return isBowlFullOfOil;
    }

    public void setBowlFullOfOil(boolean bowlFullOfOil) {
        isBowlFullOfOil = bowlFullOfOil;
    }

    public Names getNames() {
        return names;
    }

    public void engineer(int x , int y){
        hp = 1000;
        maxHp = 1000;
        names = Names.ENGINEER;
        xCoordinate = x;
        yCoordinate = y;
    }
}

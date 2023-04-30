package model.Human.Troop;

import model.Empire;
import model.Human.Human;
import model.Human.Names;
public class Climbers extends Army {
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

    public void setNames(Names names) {
        this.names = names;
    }

    public Names getNames() {
        return names;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
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

    public void SpearMen() {
        hp = 500;
        maxHp = 500;
        names = Names.SPEAR_MEN;

    }

    public void MaceMen() {
        hp = 675;
        maxHp = 675;
        names = Names.MACE_MEN;

    }

    public void LadderMen() {
        hp = 500;
        maxHp = 500;
        names = Names.LADDER_MEN;

    }

    public void Assassins() {
        hp = 800;
        maxHp = 800;
        names = Names.ASSASSINS;

    }
}


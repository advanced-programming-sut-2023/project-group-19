package model.Human.Troop;

import model.Empire;
import model.Human.Human;
import model.Human.Names;

public class Soldiers extends Army {
    public Soldiers(Empire government) {
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


    public Names getNames() {
        return names;
    }

    public void setNames(Names names) {
        this.names = names;
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

    public void BlackMonk(int x , int y) {
        hp = 300;
        maxHp = 300;
        names = Names.BLACK_MONK;
        xCoordinate = x;
        yCoordinate = y;

    }
    public void Knight(int x , int y) {
        hp = 900;
        maxHp = 900;
        names = Names.KNIGHT;
        xCoordinate = x;
        yCoordinate = y;
    }

    public void Swordsmen(int x , int y) {
        hp = 650;
        maxHp = 650;
        names = Names.SWORDSMEN;
        xCoordinate = x;
        yCoordinate = y;

    }

    public void PikeMen(int x , int y) {
        hp = 500;
        maxHp = 500;
        names = Names.PIKE_MEN;
        xCoordinate = x;
        yCoordinate = y;

    }

    public void Slaves(int x , int y) {
        hp = 375;
        maxHp = 375;
        names = Names.SLAVES;
        xCoordinate = x;
        yCoordinate = y;

    }
    public void ArabianSwordsmen(int x , int y) {
        hp = 700;
        maxHp = 700;
        names = Names.ARABIAN_SWORDSMEN;
        xCoordinate = x;
        yCoordinate = y;

    }
}

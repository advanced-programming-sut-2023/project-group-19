package model.Human.Troop;

import model.Empire;
import model.Human.Human;
import model.Human.Names;

import java.util.List;

public class Soldiers extends Army implements HumanConstantFunctions{
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
    public int restOfMoves;
    public Names typeOfArmy;
    public List<Integer> myPath;
    public Names getNames() {
        return names;
    }

    public void setNames(Names names) {
        this.names = names;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
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

    public void BlackMonk(int x , int y) {
        hp = 300;
        maxHp = 300;
        typeOfArmy = Names.STANDING_AMRY;
        names = Names.BLACK_MONK;
        xCoordinate = x;
        yCoordinate = y;
        speed = 3;
    }
    public void Knight(int x , int y) {
        hp = 900;
        maxHp = 900;
        typeOfArmy = Names.STANDING_AMRY;
        names = Names.KNIGHT;
        xCoordinate = x;
        yCoordinate = y;
        speed = 9;
    }

    public void Swordsmen(int x , int y) {
        hp = 650;
        maxHp = 650;
        typeOfArmy = Names.STANDING_AMRY;
        names = Names.SWORDSMEN;
        xCoordinate = x;
        yCoordinate = y;
        speed = 1;
    }

    public void PikeMen(int x , int y) {
        hp = 500;
        maxHp = 500;
        typeOfArmy = Names.STANDING_AMRY;
        names = Names.PIKE_MEN;
        xCoordinate = x;
        yCoordinate = y;
        speed = 3;
    }

    public void Slaves(int x , int y) {
        hp = 375;
        maxHp = 375;
        typeOfArmy = Names.STANDING_AMRY;
        names = Names.SLAVES;
        xCoordinate = x;
        yCoordinate = y;
        speed = 7;
    }
    public void ArabianSwordsmen(int x , int y) {
        hp = 700;
        maxHp = 700;
        typeOfArmy = Names.STANDING_AMRY;
        names = Names.ARABIAN_SWORDSMEN;
        xCoordinate = x;
        yCoordinate = y;
        speed = 9;
    }

    @Override
    public int hp() {
        return hp;
    }

    @Override
    public int maxHp() {
        return maxHp;
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

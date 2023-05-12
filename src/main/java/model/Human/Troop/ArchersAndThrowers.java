package model.Human.Troop;

import model.Empire;
import model.Human.Names;

import java.util.HashMap;
import java.util.List;

public class ArchersAndThrowers extends Army implements HumanConstantFunctions {
    public ArchersAndThrowers(Empire government) {
        super(government);
    }

    public int height;
    public Names shieldDirection;
    public Names typeOfArmy;
    public boolean fight;

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


    public Names getShieldDirection() {
        return shieldDirection;
    }

    public Names getTypeOfArmy() {
        return typeOfArmy;
    }



    public void createBuildingCost(int wood, int stone, int gold, int iron, int oil) {
        cost.put("wood", wood);
        cost.put("stone", stone);
        cost.put("gold", gold);
        cost.put("iron", iron);
        cost.put("oil", oil);
    }

    public void createBuildingWorkersNeeded(int engineer, int worker) {
        workersNeeded.put("engineer", engineer);
        workersNeeded.put("worker", worker);

    }

    public void archer(int x, int y) {
        hp = 475;
        maxHp = 475;
        typeOfArmy = Names.STANDING_AMRY;
        names = Names.ARCHER;
        xCoordinate = x;
        yCoordinate = y;
        speed = 7;
        attackRange = 3;
        attackPower = 200;
        defencePower = 50;
        restOfMoves = 7;
    }

    public void Crossbowmen(int x, int y) {
        hp = 700;
        maxHp = 700;
        typeOfArmy = Names.STANDING_AMRY;
        names = Names.CROSSBOWMEN;
        xCoordinate = x;
        yCoordinate = y;
        speed = 3;
        attackRange = 4;
        attackPower = 200;
        defencePower = 50;
        restOfMoves = 3;
    }

    public void ArcherBow(int x, int y) {
        this.hp = 475;
        this.maxHp = 475;
        this.typeOfArmy = Names.STANDING_AMRY;
        this.names = Names.ARCHER_BOW;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.speed = 7;
        this.attackRange = 8;
        this.attackPower = 600;
        this.defencePower = 150;
        this.restOfMoves = 7;
    }

    public void Slingers(int x, int y) {
        this.hp = 700;
        this.maxHp = 700;
        this.typeOfArmy = Names.STANDING_AMRY;
        this.names = Names.SLINGERS;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.speed = 7;
        this.attackRange = 2;
        this.attackPower = 300;
        this.defencePower = 50;
        this.restOfMoves = 7;
    }

    public void HorseArchers(int x, int y) {
        this.hp = 850;
        this.maxHp = 850;
        this.typeOfArmy = Names.STANDING_AMRY;
        this.names = Names.HORSE_ARCHERS;
        this.xCoordinate = x;
        this. yCoordinate = y;
        this.speed = 9;
        this.attackRange = 8;
        this.attackPower = 1000;
        this.defencePower = 350;
        this.restOfMoves = 9;
    }

    public void FireThrowers(int x, int y) {
        this.hp = 850;
        this.maxHp = 850;
        this.typeOfArmy = Names.STANDING_AMRY;
        this.names = Names.FireThrowers;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.speed = 9;
        this.attackRange = 4;
        this.attackPower = 800;
        this.defencePower = 150;
        this.restOfMoves = 9;
    }

    public void catapult(int x, int y) {
        this.hp = 850;
        this.maxHp = 850;
        this.names = Names.CATAPULT;
        this.typeOfArmy = Names.STANDING_AMRY;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.attackRange = 2;
        this.attackPower = 600;
        this.defencePower = 150;
        this.speed = 3;
        this.restOfMoves = 3;
        createBuildingCost(0, 0, 150, 0, 0);
        createBuildingWorkersNeeded(2, 0);
    }

    public void trebuchet(int x, int y) {
        this.hp = 900;
        this.maxHp = 900;
        this.names = Names.TREBUCHET;
        this.typeOfArmy = Names.STANDING_AMRY;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.attackRange = 2;
        this.attackPower = 600;
        this.defencePower = 150;
        this.speed = 3;
        this.restOfMoves = 3;
        createBuildingCost(0, 0, 150, 0, 0);
        createBuildingWorkersNeeded(3, 0);
    }

    public void siegeTower(int x, int y) {
        this.hp = 1000;
        this.maxHp = 1000;
        this.names = Names.SIEGE_TOWER;
        this.typeOfArmy = Names.STANDING_AMRY;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.attackRange = 2;
        this.attackPower = 0;
        this.defencePower = 200;
        this.speed = 3;
        this.restOfMoves = 3;
        createBuildingCost(0, 0, 150, 0, 0);
        createBuildingWorkersNeeded(4, 0);
    }

    public void fireBallista(int x, int y) {
        this.names = Names.FIRE_BALLISTA;
        this.typeOfArmy = Names.STANDING_AMRY;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.attackRange = 2;
        this.attackPower = 800;
        this.defencePower = 150;
        this.speed = 3;
        this.restOfMoves = 3;
        createBuildingCost(0, 0, 150, 0, 0);
    }

    public void batteringRam(int x, int y) {
        this.hp = 1200;
        this.maxHp = 1200;
        this.names = Names.BATTERING_RAM;
        this.typeOfArmy = Names.STANDING_AMRY;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.speed = 7;
        this.attackPower = 700;
        this.defencePower = 100;
        this.restOfMoves = 7;
        createBuildingCost(0, 0, 150, 0, 0);
        createBuildingWorkersNeeded(4, 0);
    }

    public void portableShield(int x, int y) {
        this.hp = 475;
        this.maxHp = 475;
        this.names = Names.PORTABLE_SHIELD;
        this. typeOfArmy = Names.STANDING_AMRY;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this. defencePower = 400;
        createBuildingCost(0, 0, 5, 0, 0);
        createBuildingWorkersNeeded(1, 0);
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

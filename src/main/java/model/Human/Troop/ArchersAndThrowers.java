package model.Human.Troop;

import model.Building.BuildingConstantFunctions;
import model.Empire;
import model.Human.Human;
import model.Human.Names;

import java.util.HashMap;
import java.util.List;

public class ArchersAndThrowers extends Army implements HumanConstantFunctions {
    public ArchersAndThrowers(Empire government) {
        super(government);
    }

    public Names shieldDirection;

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

    public HashMap<String, Integer> cost = new HashMap<>();

    public void createBuildingCost(int wood, int stone, int gold, int iron, int oil) {
        cost.put("wood", wood);
        cost.put("stone", stone);
        cost.put("gold", gold);
        cost.put("iron", iron);
        cost.put("oil", oil);
    }

    public HashMap<String, Integer> workersNeeded = new HashMap<>();

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
        restOfMoves = 7;
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
        restOfMoves = 3;
        attackRange = 4;
        attackPower = 200;
        defencePower = 50;
        restOfMoves = 3;
    }

    public void ArcherBow(int x, int y) {
        hp = 475;
        maxHp = 475;
        typeOfArmy = Names.STANDING_AMRY;
        names = Names.ARCHER_BOW;
        xCoordinate = x;
        yCoordinate = y;
        speed = 7;
        restOfMoves = 7;
        attackRange = 8;
        attackPower = 600;
        defencePower = 150;
        restOfMoves = 7;
    }

    public void Slingers(int x, int y) {
        hp = 700;
        maxHp = 700;
        typeOfArmy = Names.STANDING_AMRY;
        names = Names.SLINGERS;
        xCoordinate = x;
        yCoordinate = y;
        speed = 7;
        restOfMoves = 7;
        attackRange = 2;
        attackPower = 300;
        defencePower = 50;
        restOfMoves = 7;
    }

    public void HorseArchers(int x, int y) {
        hp = 850;
        maxHp = 850;
        typeOfArmy = Names.STANDING_AMRY;
        names = Names.HORSE_ARCHERS;
        xCoordinate = x;
        yCoordinate = y;
        speed = 9;
        restOfMoves = 9;
        attackRange = 5;
        attackPower = 1000;
        defencePower = 350;
        restOfMoves = 9;
    }

    public void FireThrowers(int x, int y) {
        hp = 850;
        maxHp = 850;
        typeOfArmy = Names.STANDING_AMRY;
        names = Names.FireThrowers;
        xCoordinate = x;
        yCoordinate = y;
        speed = 9;
        restOfMoves = 9;
        attackRange = 4;
        attackPower = 800;
        defencePower = 150;
        restOfMoves = 9;
    }

    public void catapult(int x, int y) {
        hp = 850;
        maxHp = 850;
        names = Names.CATAPULT;
        xCoordinate = x;
        yCoordinate = y;
        createBuildingCost(0, 0, 150, 0, 0);
        createBuildingWorkersNeeded(2, 0);
        attackRange = 2;
        attackPower = 600;
        defencePower = 150;
        speed = 3;
        restOfMoves = 3;
    }

    public void trebuchet(int x, int y) {
        hp = 900;
        maxHp = 900;
        names = Names.TREBUCHET;
        xCoordinate = x;
        yCoordinate = y;
        createBuildingCost(0, 0, 150, 0, 0);
        createBuildingWorkersNeeded(3, 0);
        attackRange = 2;
        attackPower = 600;
        defencePower = 150;
        speed = 3;
        restOfMoves = 3;
    }

    public void siegeTower(int x, int y) {
        hp = 1000;
        maxHp = 1000;
        names = Names.SIEGE_TOWER;
        xCoordinate = x;
        yCoordinate = y;
        createBuildingCost(0, 0, 150, 0, 0);
        attackRange = 2;
        attackPower = 0;
        defencePower = 200;
        speed = 3;
        restOfMoves = 3;
    }

    public void fireBallista(int x, int y) {
        names = Names.FIRE_BALLISTA;
        xCoordinate = x;
        yCoordinate = y;
        createBuildingCost(0, 0, 150, 0, 0);
        attackRange = 2;
        attackPower = 800;
        defencePower = 150;
        speed = 3;
        restOfMoves = 3;
    }

    public void batteringRam(int x, int y) {
        hp = 1200;
        maxHp = 1200;
        names = Names.BATTERING_RAM;
        xCoordinate = x;
        yCoordinate = y;
        speed = 7;
        attackPower = 700;
        defencePower = 100;
        restOfMoves = 7;
        createBuildingCost(0, 0, 150, 0, 0);
        createBuildingWorkersNeeded(4, 0);
    }

    public void portableShield(int x, int y) {
        hp = 475;
        maxHp = 475;
        names = Names.PORTABLE_SHIELD;
        xCoordinate = x;
        yCoordinate = y;
        defencePower = 400;
        createBuildingCost(0, 0, 5, 0, 0);
        createBuildingWorkersNeeded(1, 0);
    }

    public static boolean isSeigeTroop(Army army) {
        return army.getNames().equals(Names.CATAPULT) || army.getNames().equals(Names.TREBUCHET)
                || army.getNames().equals(Names.PORTABLE_SHIELD) || army.getNames().equals(Names.BATTERING_RAM)
                || army.getNames().equals(Names.FIRE_BALLISTA) || army.getNames().equals(Names.SIEGE_TOWER);
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

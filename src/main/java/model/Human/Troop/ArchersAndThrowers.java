package model.Human.Troop;

import model.Building.BuildingConstantFunctions;
import model.Empire;
import model.Human.Human;
import model.Human.Names;

import java.util.HashMap;

public class ArchersAndThrowers extends Army  {
    public ArchersAndThrowers(Empire government) {
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
    public Names shieldDirection;

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

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public Names getShieldDirection() {
        return shieldDirection;
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

    public void archer(int x , int y) {
        hp = 475;
        maxHp = 475;
        names = Names.ARCHER;
        xCoordinate = x;
        yCoordinate = y;

    }
    public void Crossbowmen(int x , int y) {
        hp = 700;
        maxHp = 700;
        names = Names.CROSSBOWMEN;
        xCoordinate = x;
        yCoordinate = y;

    }
    public void ArcherBow(int x , int y) {
        hp = 475;
        maxHp = 475;
        names = Names.ARCHER_BOW;
        xCoordinate = x;
        yCoordinate = y;

    }
    public void Slingers(int x , int y) {
        hp = 700;
        maxHp = 700;
        names = Names.SLINGERS;
        xCoordinate = x;
        yCoordinate = y;
    }
    public void HorseArchers(int x , int y) {
        hp = 850;
        maxHp = 850;
        names = Names.HORSE_ARCHERS;
        xCoordinate = x;
        yCoordinate = y;

    }
    public void FireThrowers(int x , int y) {
        hp = 850;
        maxHp = 850;
        names = Names.FireThrowers;
        xCoordinate = x;
        yCoordinate = y;
    }

    public void catapult(int x , int y){
        names = Names.CATAPULT;
        xCoordinate = x;
        yCoordinate = y;
        createBuildingCost(0, 0, 150, 0, 0);
        createBuildingWorkersNeeded(2,0);
    }
    public void trebuchet(int x , int y){
        names =Names.TREBUCHET;
        xCoordinate = x;
        yCoordinate = y;
        createBuildingCost(0, 0, 150, 0, 0);
        createBuildingWorkersNeeded(3,0);

    }
    public void siegeTower(int x , int y){
        names = Names.SIEGE_TOWER;
        xCoordinate = x;
        yCoordinate = y;
        createBuildingCost(0, 0, 150, 0, 0);

    }

    public void fireBallista(int x , int y){
        names = Names.FIRE_BALLISTA;
        xCoordinate = x;
        yCoordinate = y;
        createBuildingCost(0, 0, 150, 0, 0);

    }
    public void batteringRam(int x , int y){
        names = Names.BATTERING_RAM;
        xCoordinate = x;
        yCoordinate = y;
        createBuildingCost(0, 0, 150, 0, 0);
        createBuildingWorkersNeeded(4,0);
    }
    public void portableShield(int x , int y){
        hp = 850;
        maxHp = 850;
        names = Names.PORTABLE_SHIELD;
        xCoordinate = x;
        yCoordinate = y;
        createBuildingCost(0, 0, 5, 0, 0);
        createBuildingWorkersNeeded(1,0);
    }
}

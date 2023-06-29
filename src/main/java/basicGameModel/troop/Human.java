package basicGameModel.troop;

import basicGameModel.Empire;

import java.util.HashMap;
import java.util.List;

public abstract class Human implements HumanConstantFunctions {
    private Empire owner;
    public Names typeOfArmy;
    public Names names;
    public String armyForm;
    public boolean fight;
    public int hp;
    public int maxHp;
    public int speed;
    public int defencePower;
    public int attackPower;
    public int attackRange;
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
    public HashMap<String, Integer> cost = new HashMap<>();
    public HashMap<String, Integer> workersNeeded = new HashMap<>();
    public double xLocationOnMap;
    public double yLocationOnMap;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Human(Empire government) {
        this.owner = government;
    }

    public Empire getOwner() {
        return owner;
    }

    public void setOwner(Empire owner) {
        this.owner = owner;
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
        return speed();
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


    @Override
    public double xLocationOnMap() {
        return xLocationOnMap;
    }

    @Override
    public double yLocationOnMap() {
        return yLocationOnMap;
    }

}

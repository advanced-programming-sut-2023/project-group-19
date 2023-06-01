package model.Human.Troop;

import javafx.scene.image.ImageView;
import model.Empire;
import model.Human.Names;

import java.util.List;


public class Engineer extends Army implements HumanConstantFunctions {
    public Engineer(Empire government) {
        super(government);
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public Names getNames() {
        return names;
    }

    public void engineer(int x, int y) {
        this.hp = 1000;
        this.maxHp = 1000;
        this.armyForm = Names.STANDING_ARMY.getName();
        this.names = Names.ENGINEER;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.speed = 5;
        this.attackPower = 0;
        this.defencePower = 30;
        this.restOfMoves = 5;
        this.imageView = null;
        this.direction = "forward";
    }

    @Override
    public int getCurrentY() {
        return yCoordinate;
    }

    @Override
    public int getCurrentX() {
        return xCoordinate;
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
    public String direction() {
        return direction;
    }
}

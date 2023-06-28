package model.Human.Troop;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Empire;
import model.Human.Human;
import model.Human.Names;

import java.util.HashMap;
import java.util.List;

public class Army extends Human {
    public Army(Empire government) {
        super(government);
        this.empire = government;
    }
    public StateOfStanding  state ;

    public StateOfStanding getState() {
        return state;
    }

    public void setState(StateOfStanding state) {
        this.state = state;
    }

    public enum StateOfStanding {
        BACK ,
        FRONT ,
        RIGHT ,
        LEFT  ,
        ;
    }

    public boolean isIntFight = false;
    public boolean hasMovedForDefensiveState = false;
    public int pastXcordinate;
    public int pastYcordinate;

    public Empire empire;

    public ImageView imageView;
    public Image defaultImage ;
    public String armyForm;
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
    public Names typeOfArmy;
    public String direction;


    public Image getDefaultImage() {
        return defaultImage;
    }

    public boolean isIntFight() {
        return isIntFight;
    }

    public Empire getEmpire() {
        return empire;
    }

    public void setEmpire(Empire empire) {
        this.empire = empire;
    }

    protected Names names;

    public Names getNames() {
        return names;
    }



    public int getPastXcordinate() {
        return pastXcordinate;
    }

    public void setPastXcordinate(int pastXcordinate) {
        this.pastXcordinate = pastXcordinate;
    }

    public int getPastYcordinate() {
        return pastYcordinate;
    }

    public void setPastYcordinate(int pastYcordinate) {
        this.pastYcordinate = pastYcordinate;
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

    public int getAttackRange() {
        return attackRange;
    }

    public String getArmyForm() {
        return this.armyForm;
    }

    public void setArmyForm(String armyForm) {
        this.armyForm = armyForm;
    }

    public void setMyPath(List<Integer> myPath) {
        this.myPath = myPath;
    }

    public void setIntFight(boolean intFight) {
        isIntFight = intFight;
    }

    public boolean isHasMovedForDefensiveState() {
        return hasMovedForDefensiveState;
    }

    public void setHasMovedForDefensiveState(boolean hasMovedForDefensiveState) {
        this.hasMovedForDefensiveState = hasMovedForDefensiveState;
    }

    public void setNames(Names names) {
        this.names = names;
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

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    public int getStartXCoordinate() {
        return startXCoordinate;
    }

    public void setStartXCoordinate(int startXCoordinate) {
        this.startXCoordinate = startXCoordinate;
    }

    public int getStartYCoordinate() {
        return startYCoordinate;
    }

    public void setStartYCoordinate(int startYCoordinate) {
        this.startYCoordinate = startYCoordinate;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int getGoalXCoordinate() {
        return goalXCoordinate;
    }

    public void setGoalXCoordinate(int goalXCoordinate) {
        this.goalXCoordinate = goalXCoordinate;
    }

    public int getGoalYCoordinate() {
        return goalYCoordinate;
    }

    public void setGoalYCoordinate(int goalYCoordinate) {
        this.goalYCoordinate = goalYCoordinate;
    }

    public int getFinalXCoordinate() {
        return finalXCoordinate;
    }

    public void setFinalXCoordinate(int finalXCoordinate) {
        this.finalXCoordinate = finalXCoordinate;
    }

    public int getFinalYCoordinate() {
        return finalYCoordinate;
    }

    public void setFinalYCoordinate(int finalYCoordinate) {
        this.finalYCoordinate = finalYCoordinate;
    }

    public List<Integer> getMyPath() {
        return myPath;
    }

    public int getRestOfMoves() {
        return restOfMoves;
    }

    public void setRestOfMoves(int restOfMoves) {
        this.restOfMoves = restOfMoves;
    }

    public Names getTypeOfArmy() {
        return typeOfArmy;
    }

    public void setTypeOfArmy(Names typeOfArmy) {
        this.typeOfArmy = typeOfArmy;
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

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
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

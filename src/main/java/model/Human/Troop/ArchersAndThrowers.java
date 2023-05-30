package model.Human.Troop;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import model.Empire;
import model.Human.Names;
import view.Model.NewButton;

import java.util.List;

public class ArchersAndThrowers extends Army implements HumanConstantFunctions {
    public ArchersAndThrowers(Empire government) {
        super(government);
    }

    public int height;

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

    public int getAttackPower() {
        return attackPower;
    }

    public int getAttackRange() {
        return attackRange;
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
        this.hp = 475;
        this.maxHp = 475;
        this.armyForm = Names.STANDING_ARMY.getName();
        this.names = Names.ARCHER;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.speed = 7;
        this.attackRange = 4;
        this.attackPower = 100;
        this.defencePower = 50;
        this.restOfMoves = 7;
        this.imageView = new ImageView(new Image(ArchersAndThrowers.class.getResource
                ("/image/Units/MovePics/Archer/forward.png").toExternalForm()));
        this.imageView.setFitHeight(100);
        this.imageView.setFitWidth(100);
    }

    public void Crossbowmen(int x, int y) {
        this.hp = 700;
        this.maxHp = 700;
        this.armyForm = Names.STANDING_ARMY.getName();
        this.names = Names.CROSSBOWMEN;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.speed = 3;
        this.attackRange = 4;
        this.attackPower = 200;
        this.defencePower = 50;
        this.restOfMoves = 3;
    }

    public void ArcherBow(int x, int y) {
        this.hp = 475;
        this.maxHp = 475;
        this.armyForm = Names.STANDING_ARMY.getName();
        this.names = Names.ARCHER_BOW;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.speed = 7;
        this.attackRange = 8;
        this.attackPower = 600;
        this.defencePower = 150;
        this.restOfMoves = 7;
        this.imageView = new ImageView(new Image(ArchersAndThrowers.class.getResource
                ("/image/Units/MovePics/Archer bow/forward.png").toExternalForm()));
        this.imageView.setFitHeight(100);
        this.imageView.setFitWidth(100);
    }

    public void Slingers(int x, int y) {
        this.hp = 700;
        this.maxHp = 700;
        this.armyForm = Names.STANDING_ARMY.getName();
        this.names = Names.SLINGERS;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.speed = 7;
        this.attackRange = 2;
        this.attackPower = 300;
        this.defencePower = 50;
        this.restOfMoves = 7;
        this.imageView = new ImageView(new Image(ArchersAndThrowers.class.getResource
                ("/image/Units/MovePics/Slinger/forward.png").toExternalForm()));
        this.imageView.setFitHeight(100);
        this.imageView.setFitWidth(100);

    }

    public void HorseArchers(int x, int y) {
        this.hp = 850;
        this.maxHp = 850;
        this.armyForm = Names.STANDING_ARMY.getName();
        this.names = Names.HORSE_ARCHERS;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.speed = 9;
        this.attackRange = 8;
        this.attackPower = 1000;
        this.defencePower = 350;
        this.restOfMoves = 9;
        this.imageView = new ImageView(new Image(ArchersAndThrowers.class.getResource
                ("/image/Units/MovePics/Horse Archer/forward.png").toExternalForm()));
        this.imageView.setFitHeight(100);
        this.imageView.setFitWidth(100);
    }

    public void FireThrowers(int x, int y) {
        this.hp = 850;
        this.maxHp = 850;
        this.armyForm = Names.STANDING_ARMY.getName();
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
        this.armyForm = Names.STANDING_ARMY.getName();
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
        this.armyForm = Names.STANDING_ARMY.getName();
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
        this.armyForm = Names.STANDING_ARMY.getName();
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.attackRange = 2;
        this.attackPower = 0;
        this.defencePower = 200;
        this.speed = 3;
        this.restOfMoves = 3;
        createBuildingCost(0, 0, 150, 0, 0);
        createBuildingWorkersNeeded(4, 0);
        this.imageView = new ImageView(new Image(ArchersAndThrowers.class.getResource
                ("/image/Units/MovePics/SiegeTower/forward.png").toExternalForm()));
        this.imageView.setFitHeight(100);
        this.imageView.setFitWidth(100);
    }

    public void fireBallista(int x, int y) {
        this.names = Names.FIRE_BALLISTA;
        this.armyForm = Names.STANDING_ARMY.getName();
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.attackRange = 2;
        this.attackPower = 800;
        this.defencePower = 150;
        this.speed = 3;
        this.restOfMoves = 3;
        createBuildingCost(0, 0, 150, 0, 0);
        this.imageView = new ImageView(new Image(ArchersAndThrowers.class.getResource
                ("/image/Units/MovePics/Ballista/forward.png").toExternalForm()));
        this.imageView.setFitHeight(100);
        this.imageView.setFitWidth(100);
    }

    public void batteringRam(int x, int y) {
        this.hp = 1200;
        this.maxHp = 1200;
        this.names = Names.BATTERING_RAM;
        this.armyForm = Names.STANDING_ARMY.getName();
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.speed = 7;
        this.attackPower = 700;
        this.defencePower = 100;
        this.restOfMoves = 7;
        createBuildingCost(0, 0, 150, 0, 0);
        createBuildingWorkersNeeded(4, 0);
        this.imageView = new ImageView(new Image(ArchersAndThrowers.class.getResource
                ("/image/Units/MovePics/BatteringRam/forward.png").toExternalForm()));
        this.imageView.setFitHeight(100);
        this.imageView.setFitWidth(100);
    }

    public void portableShield(int x, int y) {
        this.hp = 475;
        this.maxHp = 475;
        this.names = Names.PORTABLE_SHIELD;
        this.armyForm = Names.STANDING_ARMY.getName();
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.defencePower = 400;
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

    @Override
    public ImageView imageView() {
        return imageView;
    }

    @Override
    public String direction() {
        return direction;
    }
}

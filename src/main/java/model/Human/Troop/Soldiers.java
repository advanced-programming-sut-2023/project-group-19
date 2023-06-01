package model.Human.Troop;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import model.Empire;
import model.Human.Human;
import model.Human.Names;

import java.util.List;

public class Soldiers extends Army implements HumanConstantFunctions {
    public Soldiers(Empire government) {
        super(government);
    }

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

    public int getAttackPower() {
        return attackPower;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void BlackMonk(int x, int y) {
        this.hp = 300;
        this.maxHp = 300;
        this.armyForm = Names.STANDING_ARMY.getName();
        this.names = Names.BLACK_MONK;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.attackRange = 3;
        this.speed = 3;
        this.restOfMoves = 3;
        this.attackPower = 500;
        this.defencePower = 150;
        this.imageView = new ImageView(new Image(ArchersAndThrowers.class.getResource
                ("/image/Units/MovePics/BlackMonk/forward.png").toExternalForm()));
        this.imageView.setFitHeight(100);
        this.imageView.setFitWidth(100);
        this.direction = "forward";
    }

    public void Knight(int x, int y) {
        this.hp = 900;
        this.maxHp = 900;
        this.armyForm = Names.STANDING_ARMY.getName();
        this.names = Names.KNIGHT;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.attackRange = 5;
        this.speed = 9;
        this.attackPower = 1000;
        this.defencePower = 450;
        this.restOfMoves = 9;
        this.direction = "forward";
    }

    public void Swordsmen(int x, int y) {
        this.hp = 650;
        this.maxHp = 650;
        this.armyForm = Names.STANDING_ARMY.getName();
        this.names = Names.SWORDSMEN;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.attackRange = 3;
        this.speed = 1;
        this.attackPower = 800;
        this.defencePower = 100;
        this.restOfMoves = 1;
        this.imageView = new ImageView(new Image(ArchersAndThrowers.class.getResource
                ("/image/Units/MovePics/SwordsMen/forward.png").toExternalForm()));
        this.imageView.setFitHeight(100);
        this.imageView.setFitWidth(100);
        this.direction = "forward";
    }

    public void PikeMen(int x, int y) {
        this.hp = 500;
        this.maxHp = 500;
        this.armyForm = Names.STANDING_ARMY.getName();
        this.names = Names.PIKE_MEN;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.attackRange = 3;
        this.speed = 3;
        this.restOfMoves = 3;
        this.attackPower = 600;
        this.defencePower = 350;
        this.direction = "forward";
    }

    public void Slaves(int x, int y) {
        this.hp = 400;
        this.maxHp = 400;
        this.armyForm = Names.STANDING_ARMY.getName();
        this.names = Names.SLAVES;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.attackRange = 2;
        this.speed = 7;
        this.restOfMoves = 7;
        this.attackPower = 200;
        this.defencePower = 20;
        this.imageView = new ImageView(new Image(ArchersAndThrowers.class.getResource
                ("/image/Units/MovePics/Slave/forward.png").toExternalForm()));
        this.imageView.setFitHeight(100);
        this.imageView.setFitWidth(100);
        this.direction = "forward";
    }

    public void ArabianSwordsmen(int x, int y) {
        this.hp = 700;
        this.maxHp = 700;
        this.armyForm = Names.STANDING_ARMY.getName();
        this.names = Names.ARABIAN_SWORDSMEN;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.attackRange = 4;
        this.speed = 9;
        this.restOfMoves = 9;
        this.attackPower = 800;
        this.defencePower = 450;
        this.imageView= new ImageView(new Image(ArchersAndThrowers.class.getResource
                ("/image/Units/MovePics/Arabian Swordsmen/forward.png").toExternalForm()));
        this.imageView.setFitHeight(100);
        this.imageView.setFitWidth(100);
        this.direction = "forward";
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
        return super.getImageView();
    }

    @Override
    public void setImageView(ImageView imageView) {
        super.setImageView(imageView);
    }

    @Override
    public String getDirection() {
        return super.getDirection();
    }

    @Override
    public void setDirection(String direction) {
        super.setDirection(direction);
    }
}

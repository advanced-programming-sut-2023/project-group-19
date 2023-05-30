package view.Animations;

import controller.GameController;
import controller.PathFindingController;
import javafx.animation.Transition;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Building.KillingPit;
import model.Human.Troop.Army;
import model.Manage;
import model.Map;

import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MoveAnimation extends Transition implements Initializable {


    private Army army;
    private boolean rotationOn;
    private String direction;

    private Image north,east,south,west,northEast,northWest,southEast,southWest,backward,forward;

    public MoveAnimation(Army army) {
        this.army = army;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.seconds(20));
    }

    @Override
    protected void interpolate(double v) {
        algorithmOfMove();
    }

    public void algorithmOfMove(){
        List<Integer> pathList = army.myPath;
        if (army.restOfMoves != 0) {
            army.goalXCoordinate = pathList.get(0) / PathFindingController.size;
            army.goalYCoordinate = pathList.get(0) % PathFindingController.size;
            Map.getTroopMap()[army.getCurrentX()][army.getCurrentY()].remove(army);
            /*if (army.getDirection().equals("forward")) {

                if (army.getCurrentX() > army.getGoalXCoordinate()){     // move to left
                    direction = "southWest";
                    getPicture();
                    direction = "west";
                    getPicture();

                }

                if (army.getCurrentX() < army.getGoalXCoordinate()){     // move to right
                    direction = "southEast";
                    getPicture();
                    direction = "east";
                    getPicture();
                }

                if (army.getCurrentY() > army.getGoalYCoordinate()){     // move up
                    army.setDirection("backward");
                    getPicture();
                }

                if (army.getCurrentY() < army.getGoalYCoordinate()) {    // move down
                    army.setDirection("forward");
                    getPicture();
                }

            } else if (army.getDirection().equals("backward")) {

                if (army.getCurrentX() > army.getGoalXCoordinate()){     // move to left
                    direction = "northWest";
                    getPicture();
                    direction = "west";
                    getPicture();
                }

                if (army.getCurrentX() < army.getGoalXCoordinate()){     // move to right
                    direction = "northEast";
                    getPicture();
                    direction = "east";
                    getPicture();
                }

                if (army.getCurrentY() > army.getGoalYCoordinate()){     // move up
                    army.setDirection("backward");
                    getPicture();
                }

                if (army.getCurrentY() < army.getGoalYCoordinate()) {    // move down
                    army.setDirection("forward");
                    getPicture();
                }

            }
//            if (GameController.validSquareBySquareCell(army) || GameController.isPlain(army)) {
//                if (Map.getBuildingMap()[army.goalXCoordinate][army.goalYCoordinate].get(0)
//                        instanceof KillingPit) {
//                    Map.getBuildingMap()[army.goalXCoordinate][army.goalYCoordinate].clear();
//                }
//                GameController.removeKilledUnitFromEmpireHashmap(army.getNames().getName(), army.getEmpire());
//                Manage.getCurrentEmpire().empireArmy.remove(army);
//                this.stop();
//            }
*/
            army.xCoordinate = army.goalXCoordinate;
            army.yCoordinate = army.goalYCoordinate;
            army.restOfMoves--;
            Map.getTroopMap()[army.xCoordinate][army.yCoordinate].add(army);
            pathList.remove(0);
        }
    }


    public void updatePicture(){
        north = new Image(MoveAnimation.class.getResource("/image/Units/"+army.getNames().getName()+"north.png").toExternalForm());
        east = new Image(MoveAnimation.class.getResource("/image/Units/"+army.getNames().getName()+"east.png").toExternalForm());
        west = new Image(MoveAnimation.class.getResource("/image/Units/"+army.getNames().getName()+"west.png").toExternalForm());
        south = new Image(MoveAnimation.class.getResource("/image/Units/"+army.getNames().getName()+"south.png").toExternalForm());
        northEast = new Image(MoveAnimation.class.getResource("/image/Units/"+army.getNames().getName()+"northEast.png").toExternalForm());
        northWest = new Image(MoveAnimation.class.getResource("/image/Units/"+army.getNames().getName()+"northWest.png").toExternalForm());
        southEast = new Image(MoveAnimation.class.getResource("/image/Units/"+army.getNames().getName()+"southEast.png").toExternalForm());
        southWest = new Image(MoveAnimation.class.getResource("/image/Units/"+army.getNames().getName()+"southWest.png").toExternalForm());
        backward = new Image(MoveAnimation.class.getResource("/image/Units/"+army.getNames().getName()+"backward.png").toExternalForm());
        forward = new Image(MoveAnimation.class.getResource("/image/Units/"+army.getNames().getName()+"forward.png").toExternalForm());
    }

    /*public void getPicture(){
        switch (direction){
            case "north":
                army.setFill(new ImagePattern(north));
                break;
            case "east":
                army.setFill(new ImagePattern(east));
                break;
            case "west":
                army.setFill(new ImagePattern(west));
                break;
            case "south":
                army.setFill(new ImagePattern(south));
                break;
            case "northEast":
                army.setFill(new ImagePattern(northEast));
                break;
            case "northWest":
                army.setFill(new ImagePattern(northWest));
                break;
            case "southEast":
                army.setFill(new ImagePattern(southEast));
                break;
            case "southWest":
                army.setFill(new ImagePattern(southWest));
                break;
        }
    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //updatePicture();
    }
}


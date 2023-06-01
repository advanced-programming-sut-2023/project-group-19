package view.Animations;

import controller.GameController;
import controller.PathFindingController;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.util.Duration;
import model.Human.Troop.Army;
import view.Model.NewButton;
import view.TileManager;
import java.util.ArrayList;
import java.util.List;

public class MoveAnimation extends Transition {
    private Army army;
    private Image east, west, northEast, northWest, southEast, southWest, backward, forward;
    private NewButton myButton;
    private ArrayList<Node> list;
    private TileManager tileManager;

    private GameController gameController = new GameController();

    public MoveAnimation(Army army, NewButton myButton, ArrayList<Node> list, TileManager tileManager) {
        this.army = army;
        this.myButton = myButton;
        this.list = list;
        this.tileManager = tileManager;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(3000));
        updatePicture();
    }


    public void updatePicture() {
        east = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/east.png").toExternalForm());
        west = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/west.png").toExternalForm());
        //northEast = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/northEast.png").toExternalForm());
        //northWest = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/northWest.png").toExternalForm());
        //southEast = new Image(MoveAnimation.class.getResource("image/Units/MovePics/Archer/southEast.png").toExternalForm());
        //southWest = new Image(MoveAnimation.class.getResource("image/Units/MovePics/Archer/southWest.png").toExternalForm());
        backward = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/backward.png").toExternalForm());

    }
    private Image one = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/east.png").toExternalForm());
    private Image two = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/forward.png").toExternalForm());
    private Image three = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/west.png").toExternalForm());
    private Image four = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/backward.png").toExternalForm());

    @Override
    protected void interpolate(double v) {
        algorithmOfMove();
    }

    /*public void algorithmOfMove() {
        double deltaX =  0, deltaY =0;
        List<Integer> pathList = army.myPath;
        if (i == pathList.size()) {
            System.out.println("stopped");
            this.stop();
        } else {
            army.goalXCoordinate = pathList.get(i) / PathFindingController.size;
            army.goalYCoordinate = pathList.get(i) % PathFindingController.size;
            NewButton newButton = (NewButton) tileManager.list.get(army.goalXCoordinate * 100 + army.goalYCoordinate);
            System.out.println(army.getGoalXCoordinate()+" "+army.getGoalYCoordinate());
            if (army.getCurrentX() > army.getGoalXCoordinate()) {     // move to left
                System.out.println("left");
                direction = "west";
            }

            if (army.getCurrentX() < army.getGoalXCoordinate()) {     // move to right
                System.out.println("right");
                army.direction = "east";
            }

            if (army.getCurrentY() > army.getGoalYCoordinate()) {     // move up
                System.out.println("up");
                army.direction = "backward";
            }

            if (army.getCurrentY() < army.getGoalYCoordinate()) {    // move down
                System.out.println("down");
                army.direction = "forward";
            }
            deltaX = army.getGoalYCoordinate() - army.getCurrentY();
            deltaY = army.getGoalXCoordinate() - army.getCurrentX();
            System.out.println("Chnages : "+newButton.getLayoutX()+" "+ newButton.getLayoutY());
//            army.restOfMoves--;
//            army.setxCoordinate((int) xArmy);
//            army.setyCoordinate((int) yArmy);
            army.getImageView().setLayoutX(newButton.getLayoutX());
            army.getImageView().setLayoutY(newButton.getLayoutY());
            if (army.direction.equals("east")) {
                army.getImageView().setImage(one);
            } else if (army.direction.equals("forward")) {
                army.getImageView().setImage(two);
            }
            newButton.setImageView(army.getImageView());
            i++;

        }
    }*/
    public void algorithmOfMove() {
        double xArmy = 0, yArmy = 0, translateX = 0, translateY = 0;

        List<Integer> pathList = army.myPath;

        if (pathList.size() == 0 || army.restOfMoves == 0) {

            this.stop();
        }
        if (army.restOfMoves != 0 && pathList.size() > 0) {

            army.goalXCoordinate = pathList.get(0) / PathFindingController.size;
            army.goalYCoordinate = pathList.get(0) % PathFindingController.size;

            NewButton newButton = (NewButton) tileManager.list.get(army.goalXCoordinate * 100 + army.goalYCoordinate);


            if (army.getCurrentX() > army.getGoalXCoordinate()) {     // move to left
                army.setDirection("west");
                xArmy = army.goalXCoordinate;
                yArmy = army.goalYCoordinate;
                translateX = army.getImageView().getX() - 25;
            }

            if (army.getCurrentX() < army.getGoalXCoordinate()) {     // move to right
                army.setDirection("east");
                xArmy = army.goalXCoordinate;
                yArmy = army.goalYCoordinate;
                translateX = army.getImageView().getX() + 25;
            }

            if (army.getCurrentY() > army.getGoalYCoordinate()) {     // move up
                army.setDirection("backward");
                yArmy = army.goalYCoordinate;
                xArmy = army.goalXCoordinate;
                translateY = army.getImageView().getY() - 25;
            }

            if (army.getCurrentY() < army.getGoalYCoordinate()) {    // move down
                army.setDirection("forward");
                yArmy = army.goalYCoordinate;
                xArmy = army.goalXCoordinate;
                translateY = army.getImageView().getY() + 25;
            }
            //TODO : Bugs here
            if (army.getDirection().equals("left")) {
                army.getImageView().setImage(three);
            } else if (army.getDirection().equals("right")) {

                army.getImageView().setImage(one);
            } else if (army.getDirection().equals("forward")) {

                army.getImageView().setImage(two);
            } else if (army.getDirection().equals("backward")) {

                army.getImageView().setImage(four);
            }
            army.restOfMoves--;
            army.setxCoordinate((int) xArmy);
            army.setyCoordinate((int) yArmy);
            army.getImageView().setLayoutX(translateX);
            army.getImageView().setLayoutY(translateY);
            newButton.setGraphic(army.getImageView());
            pathList.remove(0);
        }
    }
}


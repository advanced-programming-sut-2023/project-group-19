package view.Animations;

import controller.GameController;
import controller.PathFindingController;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Human.Troop.Army;
import view.Model.NewButton;
import view.TileManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MoveAnimation extends Transition implements Initializable {


    private Army army;
    private String direction;


    private Image east, west, northEast, northWest, southEast, southWest, backward, forward;
    private NewButton myButton;
    private ArrayList<Node> list;
    private Pane pane;
    private TileManager tileManager;
    private GameController gameController = new GameController();
    private int index = 0;
    public MoveAnimation(Army army, NewButton myButton, ArrayList<Node> list, Pane pane,TileManager tileManager) {
        this.army = army;
        this.myButton = myButton;
        this.list = list;
        this.pane = pane;
        this.tileManager = tileManager;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.seconds(10));
        this.setInterpolator(Interpolator.LINEAR);
    }

    @Override
    protected void interpolate(double v) {
        algorithmOfMove();
    }

    public void algorithmOfMove() {
        double xArmy = 0, yArmy = 0, translateX = 0,translateY = 0;

        List<Integer> pathList = army.myPath;

        if (pathList.size() == 0) {

            this.stop();
        }
        if (army.restOfMoves != 0 && pathList.size() > 0) {

            army.goalXCoordinate = pathList.get(0) / PathFindingController.size;
            army.goalYCoordinate = pathList.get(0) % PathFindingController.size;

            NewButton newButton = (NewButton) tileManager.list.get(army.goalXCoordinate * 100 + army.goalYCoordinate);

//            if (newButton.getBuilding().getName().equals(Names.TUNNEL.getName())){
//                pane.getChildren().remove(army.imageView);
//                NewButton currentButton = (NewButton) tileManager.list.get(army.getCurrentX() * 100 + army.getCurrentY());
//                currentButton.getArmy().remove(army);
//                GameController.removeKilledUnitFromEmpireHashmap(army.getNames().getName(), army.getEmpire());
//                Manage.getCurrentEmpire().empireArmy.remove(army);
//                this.stop();
//            }

            if (army.getCurrentX() > army.getGoalXCoordinate()) {     // move to left
                System.out.println("left");
                direction = "west";
                //getPicture();
                xArmy = army.goalXCoordinate;
                yArmy = army.goalYCoordinate;
                translateX = army.imageView.getX() -25;
            }

            if (army.getCurrentX() < army.getGoalXCoordinate()) {     // move to right
                System.out.println("right");
                direction = "east";
                //getPicture();
                xArmy = army.goalXCoordinate;
                yArmy = army.goalYCoordinate;
                translateX = army.imageView.getX() +25;
            }

            if (army.getCurrentY() > army.getGoalYCoordinate()) {     // move up
                System.out.println("up");
                army.direction = "backward";
                //getPicture();
                yArmy = army.goalYCoordinate;
                xArmy = army.goalXCoordinate;
                translateY = army.imageView.getY()-25;
            }

            if (army.getCurrentY() < army.getGoalYCoordinate()) {    // move down
                System.out.println("down");
                army.direction = "forward";
                //getPicture();
                yArmy = army.goalYCoordinate;
                xArmy = army.goalXCoordinate;
                translateY = army.imageView.getY() +25;
            }


            army.restOfMoves--;
            army.setxCoordinate((int) xArmy);
            army.setyCoordinate((int) yArmy);
            army.getImageView().setLayoutX(translateX);
            army.getImageView().setLayoutY(translateY);
            newButton.setGraphic(army.getImageView());
            pathList.remove(0);

        }else{
            this.stop();
        }
    }


    public void updatePicture() {
        east = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/east.png").toExternalForm());
        west = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/west.png").toExternalForm());
        northEast = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/northEast.png").toExternalForm());
        northWest = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/northWest.png").toExternalForm());
        southEast = new Image(MoveAnimation.class.getResource("image/Units/MovePics/Archer/southEast.png").toExternalForm());
        southWest = new Image(MoveAnimation.class.getResource("image/Units/MovePics/Archer/southWest.png").toExternalForm());
        backward = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/backward.png").toExternalForm());
        forward = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/forward.png").toExternalForm());
    }

    public void getPicture() {
        switch (army.direction) {
            case "forward":
                army.setImageView(new ImageView(forward));
                break;
            case "east":
                army.setImageView(new ImageView(east));
                break;
            case "west":
                army.setImageView(new ImageView(west));
                break;
            case "backward":
                army.setImageView(new ImageView(backward));
                break;
            case "northEast":
                army.setImageView(new ImageView(northEast));
                break;
            case "northWest":
                army.setImageView(new ImageView(northWest));
                break;
            case "southEast":
                army.setImageView(new ImageView(southEast));
                break;
            case "southWest":
                army.setImageView(new ImageView(southWest));
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updatePicture();//TODO: Make it a class
    }
}


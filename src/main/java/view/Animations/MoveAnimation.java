package view.Animations;

import controller.GameController;
import controller.PathFindingController;
import javafx.animation.PathTransition;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import model.Human.Troop.Army;
import view.Model.NewButton;
import view.TileManager;
import java.util.ArrayList;
import java.util.List;

public class MoveAnimation{
    private Army army;
    //private Image east, west, northEast, northWest, southEast, southWest, backward, forward;
    private NewButton myButton;

    private TileManager tileManager;
    private double xInMaP;
    private double yInMaP;



    public MoveAnimation(Army army, NewButton myButton, double xInMaP,double yInMaP) {
        this.army = army;
        this.myButton = myButton;
        this.xInMaP = xInMaP;
        this.yInMaP = yInMaP;
        //updatePicture();
    }


//    public void updatePicture() {
//        east = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/east.png").toExternalForm());
//        west = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/west.png").toExternalForm());
//        //northEast = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/northEast.png").toExternalForm());
//        //northWest = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/northWest.png").toExternalForm());
//        //southEast = new Image(MoveAnimation.class.getResource("image/Units/MovePics/Archer/southEast.png").toExternalForm());
//        //southWest = new Image(MoveAnimation.class.getResource("image/Units/MovePics/Archer/southWest.png").toExternalForm());
//        backward = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/backward.png").toExternalForm());
//
//    }
    private Image one = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/east.png").toExternalForm());
    private Image two = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/forward.png").toExternalForm());
    private Image three = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/west.png").toExternalForm());
    private Image four = new Image(MoveAnimation.class.getResource("/image/Units/MovePics/Archer/backward.png").toExternalForm());


    public void move(){
        Line myLine = new Line();
        myLine.setStartX(army.getImageView().getLayoutX());
        myLine.setStartY(army.getImageView().getLayoutY());
        myLine.setEndX(xInMaP*51.2);
        myLine.setEndY(yInMaP*54);
        PathTransition transition = new PathTransition();
        transition.setNode(army.imageView);
        transition.setDuration(Duration.seconds(5));
        transition.setPath(myLine);
        transition.setCycleCount(1);
        transition.play();
    }
}


package view;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Human.Troop.Army;
import model.Map;
import view.ImageAndBackground.MoveAnimationPics;
import view.Model.NewButton;

import javax.print.attribute.standard.PrinterName;

public class MoveAnimation extends Transition {

    private Army passingArmy;
    private NewButton newButton;
    private NewButton current;
    private int goalX;
    private int currentX;
    private int currentY;
    private int goalY;
    private int dx;
    private int dy;
    int i = 0;
    private Pane pane;


    public MoveAnimation(Army passingArmy,
                         int goalX, int goalY,
                         Pane pane, NewButton newButton, NewButton current,
                         int j) {
        this.passingArmy = passingArmy;
        this.goalX = goalX;
        this.goalY = goalY;
        this.currentX = current.getX();
        this.currentY = current.getY();
        this.pane = pane;
        this.dx = goalX - currentX;
        this.dy = goalY - currentY;
        this.newButton = newButton;
        this.current = current;
        pane.getChildren().remove(passingArmy.getImageView());
        pane.getChildren().add(pane.getChildren().size(), passingArmy.getImageView());
        if (j == 0) {
            passingArmy.getImageView().setLayoutX(current.getLayoutX());
            passingArmy.getImageView().setLayoutY(current.getLayoutY());
        }

        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(500));
        this.setInterpolator(Interpolator.LINEAR);
    }

    @Override
    protected void interpolate(double v) {
        double differenceX = 0;
        double differenceY = 0;
        if (newButton.getX() > current.getX()){
            differenceY = 1.6;
        } else if (newButton.getX() < current.getX()){
            differenceY = -1.6;
        } else if (newButton.getY() > current.getY()) {
            differenceX = 1.6;
        } else if (newButton.getY() < current.getY()) {
            differenceX = -1.6;
        }

        if (differenceX != 0)
            passingArmy.getImageView().setLayoutX(passingArmy.getImageView().getLayoutX() + differenceX);
        if (differenceY != 0)
            passingArmy.getImageView().setLayoutY(passingArmy.getImageView().getLayoutY() + differenceY);

    }


    public void setImageForUnits(String unitName, String direction, Army passingArmy) {
        MoveAnimationPics moveAnimationPics = new MoveAnimationPics();
        switch (unitName) {
//            case "arabianSwordMen":
//                switch (direction) {
//                    case "East" -> {
//                        passingArmy.getImageView().setImage(moveAnimationPics.getArabianSwordMenEast());
//                        passingArmy.getImageView().setLayoutX(passingArmy.getCurrentX() * 51.2);
//                        passingArmy.getImageView().setLayoutY(passingArmy.getCurrentY() * 54);
//                    }
//                    case "West" -> passingArmy.getImageView().setImage(moveAnimationPics.getArabianSwordMenWest());
//
//                    case "Backward" ->
//                            passingArmy.getImageView().setImage(moveAnimationPics.getArabianSwordMenBackward());
//                    case "Forward" ->
//                            passingArmy.getImageView().setImage(moveAnimationPics.getArabianSwordMenForward());
//                }
//                break;
//                }
            case "archer":
                switch (direction) {
                    case "EastLeft" -> passingArmy.getImageView().setImage(moveAnimationPics.getArcherEastLeft());
                    case "WestLeft" -> passingArmy.getImageView().setImage(moveAnimationPics.getArcherWestLeft());
                    case "BackwardLeft" ->
                            passingArmy.getImageView().setImage(moveAnimationPics.getArcherBackwardLeft());
                    case "ForwardLeft" -> passingArmy.getImageView().setImage(moveAnimationPics.getArcherForwardLeft());
                    case "EastRight" -> passingArmy.getImageView().setImage(moveAnimationPics.getArcherEastRight());
                    case "WestRight" -> passingArmy.getImageView().setImage(moveAnimationPics.getArcherWestRight());
                    case "BackwardRight" ->
                            passingArmy.getImageView().setImage(moveAnimationPics.getArcherBackwardRight());
                    case "ForwardRight" ->
                            passingArmy.getImageView().setImage(moveAnimationPics.getArcherForwardRight());
                }
                break;
            case "archerBow":
                switch (direction) {
                    case "East" -> passingArmy.getImageView().setImage(moveAnimationPics.getArcherBowEast());
                    case "West" -> passingArmy.getImageView().setImage(moveAnimationPics.getArcherBowWest());
                    case "Backward" -> passingArmy.getImageView().setImage(moveAnimationPics.getArcherBowBackward());
                    case "Forward" -> passingArmy.getImageView().setImage(moveAnimationPics.getArcherBowForward());
                }
                break;
            case "assassins":
                switch (direction) {
                    case "East" -> passingArmy.getImageView().setImage(moveAnimationPics.getAssassinsEast());
                    case "West" -> passingArmy.getImageView().setImage(moveAnimationPics.getAssassinsWest());
                    case "Backward" -> passingArmy.getImageView().setImage(moveAnimationPics.getAssassinsBackward());
                    case "Forward" -> passingArmy.getImageView().setImage(moveAnimationPics.getAssassinsForward());
                }
                break;
            case "batteringRam":
                switch (direction) {
                    case "East" -> passingArmy.getImageView().setImage(moveAnimationPics.getBatteringRamEast());
                    case "West" -> passingArmy.getImageView().setImage(moveAnimationPics.getBatteringRamWest());
                    case "Backward" -> passingArmy.getImageView().setImage(moveAnimationPics.getBatteringRamBackward());
                    case "Forward" -> passingArmy.getImageView().setImage(moveAnimationPics.getBatteringRamForward());
                }
                break;
            case "blackMonk":
                switch (direction) {
                    case "East" -> passingArmy.getImageView().setImage(moveAnimationPics.getBlackMonkEast());
                    case "West" -> passingArmy.getImageView().setImage(moveAnimationPics.getBlackMonkWest());
                    case "Backward" -> passingArmy.getImageView().setImage(moveAnimationPics.getBlackMonkBackward());
                    case "Forward" -> passingArmy.getImageView().setImage(moveAnimationPics.getBlackMonkForward());
                }
                break;
            case "engineer":
                switch (direction) {
                    case "East" -> passingArmy.getImageView().setImage(moveAnimationPics.getEngineerEast());
                    case "West" -> passingArmy.getImageView().setImage(moveAnimationPics.getEngineerWest());
                    case "Backward" -> passingArmy.getImageView().setImage(moveAnimationPics.getEngineerBackward());
                    case "Forward" -> passingArmy.getImageView().setImage(moveAnimationPics.getEngineerForward());
                }
                break;
            case "fireBallista":
                switch (direction) {
                    case "East" -> passingArmy.getImageView().setImage(moveAnimationPics.getFireBallistaEast());
                    case "West" -> passingArmy.getImageView().setImage(moveAnimationPics.getFireBallistaWest());
                    case "Backward" -> passingArmy.getImageView().setImage(moveAnimationPics.getFireBallistaBackward());
                    case "Forward" -> passingArmy.getImageView().setImage(moveAnimationPics.getFireBallistaForward());
                }
                break;
            case "horseArchers":
                switch (direction) {
                    case "East" -> passingArmy.getImageView().setImage(moveAnimationPics.getHorseArchersEast());
                    case "West" -> passingArmy.getImageView().setImage(moveAnimationPics.getHorseArchersWest());
                    case "Backward" -> passingArmy.getImageView().setImage(moveAnimationPics.getHorseArchersBackward());
                    case "Forward" -> passingArmy.getImageView().setImage(moveAnimationPics.getHorseArchersForward());
                }
                break;
            case "ladderMen":
                switch (direction) {
                    case "East" -> passingArmy.getImageView().setImage(moveAnimationPics.getLadderMenEast());
                    case "West" -> passingArmy.getImageView().setImage(moveAnimationPics.getLadderMenWest());
                    case "Backward" -> passingArmy.getImageView().setImage(moveAnimationPics.getLadderMenBackward());
                    case "Forward" -> passingArmy.getImageView().setImage(moveAnimationPics.getLadderMenForward());
                }
                break;
            case "maceMen":
                switch (direction) {
                    case "East" -> passingArmy.getImageView().setImage(moveAnimationPics.getMaceMenEast());
                    case "West" -> passingArmy.getImageView().setImage(moveAnimationPics.getMaceMenWest());
                    case "Backward" -> passingArmy.getImageView().setImage(moveAnimationPics.getMaceMenBackward());
                    case "Forward" -> passingArmy.getImageView().setImage(moveAnimationPics.getMaceMenForward());
                }
                break;
            case "slaves":
                switch (direction) {
                    case "East" -> passingArmy.getImageView().setImage(moveAnimationPics.getSlavesEast());
                    case "West" -> passingArmy.getImageView().setImage(moveAnimationPics.getSlavesWest());
                    case "Backward" -> passingArmy.getImageView().setImage(moveAnimationPics.getSlavesBackward());
                    case "Forward" -> passingArmy.getImageView().setImage(moveAnimationPics.getSlavesForward());
                }
                break;
            case "slingers":
                switch (direction) {
                    case "East" -> passingArmy.getImageView().setImage(moveAnimationPics.getSlingersEast());
                    case "West" -> passingArmy.getImageView().setImage(moveAnimationPics.getSlingersWest());
                    case "Backward" -> passingArmy.getImageView().setImage(moveAnimationPics.getSlingersBackward());
                    case "Forward" -> passingArmy.getImageView().setImage(moveAnimationPics.getSlingersForward());
                }
                break;
            case "swordsMen":
                switch (direction) {
                    case "East" -> passingArmy.getImageView().setImage(moveAnimationPics.getSwordsMenEast());
                    case "West" -> passingArmy.getImageView().setImage(moveAnimationPics.getSwordsMenWest());
                    case "Backward" -> passingArmy.getImageView().setImage(moveAnimationPics.getSwordsMenBackward());
                    case "Forward" -> passingArmy.getImageView().setImage(moveAnimationPics.getSwordsMenForward());
                }
                break;
            case "tunneler":
                switch (direction) {
                    case "East" -> passingArmy.getImageView().setImage(moveAnimationPics.getTunnelerEast());
                    case "West" -> passingArmy.getImageView().setImage(moveAnimationPics.getTunnelerWest());
                    case "Backward" -> passingArmy.getImageView().setImage(moveAnimationPics.getTunnelerBackward());
                    case "Forward" -> passingArmy.getImageView().setImage(moveAnimationPics.getTunnelerForward());
                }
                break;

        }
    }

    public void setDirection(boolean dir) {
        if (dir) {
            if (dx > 0) {
                passingArmy.setDirection("EastRight");
            } else if (dx < 0) {
                passingArmy.setDirection("WestRight");
            } else if (dy > 0) {
                passingArmy.setDirection("ForwardRight");
            } else if (dy < 0) {
                passingArmy.setDirection("BackwardRight");
            }
        } else {
            if (dx > 0) {
                passingArmy.setDirection("EastLeft");
            } else if (dx < 0) {
                passingArmy.setDirection("WestLeft");
            } else if (dy > 0) {
                passingArmy.setDirection("ForwardLeft");
            } else if (dy < 0) {
                passingArmy.setDirection("BackwardLeft");
            }
        }
    }


}

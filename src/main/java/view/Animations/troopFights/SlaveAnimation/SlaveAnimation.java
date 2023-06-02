package view.Animations.troopFights.SlaveAnimation;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;
import model.Human.Troop.Army;

import java.util.ArrayList;

public class SlaveAnimation extends Transition {
    private Image BACK1 = new Image(SlaveAnimation.class.
            getResource("/troop/slaveFight/back/1.png").toExternalForm());
    private Image BACK2 = new Image(SlaveAnimation.class.
            getResource("/troop/slaveFight/back/2.png").toExternalForm());


    private Image FRONT1 = new Image(SlaveAnimation.class.
            getResource("/troop/slaveFight/front/1.png").toExternalForm());
    private Image FRONT2 = new Image(SlaveAnimation.class.
            getResource("/troop/slaveFight/front/2.png").toExternalForm());

    private Image LEFT1 = new Image(SlaveAnimation.class.
            getResource("/troop/slaveFight/left/1.png").toExternalForm());
    private Image LEFT2 = new Image(SlaveAnimation.class.
            getResource("/troop/slaveFight/left/2.png").toExternalForm());


    private Image RIGHT1 = new Image(SlaveAnimation.class.
            getResource("/troop/slaveFight/right/1.png").toExternalForm());
    private Image RIGHT2 = new Image(SlaveAnimation.class.
            getResource("/troop/slaveFight/right/2.png").toExternalForm());


    public ArrayList<Army> armyArrayList = new ArrayList<>();


    public SlaveAnimation(){
        this.setCycleCount(3);
        this.setCycleDuration(Duration.seconds(2));
    }
    @Override
    protected void interpolate(double v) {
        for (Army army : armyArrayList) {
            switch (army.getState()) {
                case BACK :
                    if (v >= 0 && v < 0.5) army.getImageView().setImage(BACK1);
                    if (v >= 0.5) army.getImageView().setImage(BACK2);
                    if(v == 1) army.getImageView().setImage(army.getDefaultImage());
                    break;
                case LEFT:
                    if (v >= 0 && v < 0.5) army.getImageView().setImage(LEFT1);
                    if (v >= 0.5) army.getImageView().setImage(LEFT2);
                    if(v == 1) army.getImageView().setImage(army.getDefaultImage());
                    break;
                case RIGHT:
                    if (v >= 0 && v < 0.5) army.getImageView().setImage(RIGHT1);
                    if (v >= 0.5) army.getImageView().setImage(RIGHT2);
                    if(v == 1) army.getImageView().setImage(army.getDefaultImage());
                    break;
                case FRONT:
                    if (v >= 0 && v < 0.5) army.getImageView().setImage(FRONT1);
                    if (v >= 0.5) army.getImageView().setImage(FRONT2);
                    if(v == 1) army.getImageView().setImage(army.getDefaultImage());
                    break;
            }
        }
    }
    public void setArmyToAnimation(Army army){
        armyArrayList.add(army);
    }
}

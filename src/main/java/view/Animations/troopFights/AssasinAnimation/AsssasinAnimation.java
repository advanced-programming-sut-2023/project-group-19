package view.Animations.troopFights.AssasinAnimation;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;
import model.Human.Troop.Army;
import view.Animations.troopFights.SlaveAnimation.SlaveAnimation;

import java.util.ArrayList;

public class AsssasinAnimation extends Transition {

    private Image BACK1 = new Image(SlaveAnimation.class.
            getResource("/troop/assasinFight/back/1.png").toExternalForm());
    private Image BACK2 = new Image(SlaveAnimation.class.
            getResource("/troop/assasinFight/back/2.png").toExternalForm());
    private Image BACK3 = new Image(SlaveAnimation.class.
            getResource("/troop/assasinFight/back/3.png").toExternalForm());


    private Image FRONT1 = new Image(SlaveAnimation.class.
            getResource("/troop/assasinFight/front/1.png").toExternalForm());
    private Image FRONT2 = new Image(SlaveAnimation.class.
            getResource("/troop/assasinFight/front/2.png").toExternalForm());

    private Image LEFT1 = new Image(SlaveAnimation.class.
            getResource("/troop/assasinFight/left/1.png").toExternalForm());
    private Image LEFT2 = new Image(SlaveAnimation.class.
            getResource("/troop/assasinFight/left/2.png").toExternalForm());


    private Image RIGHT1 = new Image(SlaveAnimation.class.
            getResource("/troop/assasinFight/right/1.png").toExternalForm());
    private Image RIGHT2 = new Image(SlaveAnimation.class.
            getResource("/troop/assasinFight/right/2.png").toExternalForm());
    private Image RIGHT3 = new Image(SlaveAnimation.class.
            getResource("/troop/assasinFight/right/3.png").toExternalForm());
    private Image RIGHT4 = new Image(SlaveAnimation.class.
            getResource("/troop/assasinFight/right/4.png").toExternalForm());


    public ArrayList<Army> armyArrayList = new ArrayList<>();



    public AsssasinAnimation(){
        this.setCycleCount(3);
        this.setCycleDuration(Duration.seconds(2));
    }
    @Override
    protected void interpolate(double v) {
        for (Army army : armyArrayList) {
            switch (army.getState()) {
                case BACK :
                    if (v >= 0 && v < 0.33) army.getImageView().setImage(BACK1);
                    if (v >= 0.33 && v < 0.66) army.getImageView().setImage(BACK2);
                    if (v >= 0.66) army.getImageView().setImage(BACK3);
                    break;
                case LEFT:
                    if (v >= 0 && v < 0.5) army.getImageView().setImage(LEFT1);
                    if (v >= 0.5) army.getImageView().setImage(LEFT2);
                    break;
                case RIGHT:
                    if (v >= 0 && v < 0.25) army.getImageView().setImage(RIGHT1);
                    if (v >= 0.25 && v < 0.5) army.getImageView().setImage(RIGHT2);
                    if (v >= 0.5 && v < 0.75) army.getImageView().setImage(RIGHT3);
                    if (v >= 0.75) army.getImageView().setImage(RIGHT4);
                    break;
                case FRONT:
                    if (v >= 0 && v < 0.5) army.getImageView().setImage(FRONT1);
                    if (v >= 0.5) army.getImageView().setImage(FRONT2);
                    break;
            }
        }
    }
    public void setArmyToAnimation(Army army){
        armyArrayList.add(army);
    }

}

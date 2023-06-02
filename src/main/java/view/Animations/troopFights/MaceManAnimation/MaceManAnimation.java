package view.Animations.troopFights.MaceManAnimation;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;
import model.Human.Troop.Army;

import java.util.ArrayList;

public class MaceManAnimation extends Transition {

    private Image BACK1 = new Image(MaceManAnimation.class.
            getResource("/troop/maceManFight/back/1.png").toExternalForm());
    private Image BACK2 = new Image(MaceManAnimation.class.
            getResource("/troop/maceManFight/back/2.png").toExternalForm());
    private Image BACK3 = new Image(MaceManAnimation.class.
            getResource("/troop/maceManFight/back/3.png").toExternalForm());
    private Image BACK4 = new Image(MaceManAnimation.class.
            getResource("/troop/maceManFight/back/4.png").toExternalForm());


    private Image FRONT1 = new Image(MaceManAnimation.class.
            getResource("/troop/maceManFight/front/1.png").toExternalForm());
    private Image FRONT2 = new Image(MaceManAnimation.class.
            getResource("/troop/maceManFight/front/2.png").toExternalForm());
    private Image FRONT3 = new Image(MaceManAnimation.class.
            getResource("/troop/maceManFight/front/3.png").toExternalForm());
    private Image FRONT4 = new Image(MaceManAnimation.class.
            getResource("/troop/maceManFight/front/4.png").toExternalForm());

    private Image LEFT1 = new Image(MaceManAnimation.class.
            getResource("/troop/maceManFight/left/1.png").toExternalForm());
    private Image LEFT2 = new Image(MaceManAnimation.class.
            getResource("/troop/maceManFight/left/2.png").toExternalForm());

    private Image RIGHT1 = new Image(MaceManAnimation.class.
            getResource("/troop/maceManFight/right/1.png").toExternalForm());
    private Image RIGHT2 = new Image(MaceManAnimation.class.
            getResource("/troop/maceManFight/right/2.png").toExternalForm());


    public ArrayList<Army> armyArrayList = new ArrayList<>();


    public MaceManAnimation(){
        this.setCycleCount(3);
        this.setCycleDuration(Duration.seconds(2));
    }
    @Override
    protected void interpolate(double v) {
        for (Army army : armyArrayList) {
            switch (army.getState()) {
                case BACK :
                    if (v >= 0 && v < 0.25) army.getImageView().setImage(BACK1);
                    if (v >= 0.25 && v < 0.5) army.getImageView().setImage(BACK2);
                    if (v >= 0.5 && v < 0.75) army.getImageView().setImage(BACK3);
                    if (v >= 0.75) army.getImageView().setImage(BACK4);
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
                    if (v >= 0 && v < 0.25) army.getImageView().setImage(FRONT1);
                    if (v >= 0.25 && v < 0.5) army.getImageView().setImage(FRONT2);
                    if (v >= 0.5 && v < 0.75) army.getImageView().setImage(FRONT3);
                    if (v >= 0.75) army.getImageView().setImage(FRONT4);
                    if(v == 1) army.getImageView().setImage(army.getDefaultImage());
                    break;
            }
        }
    }
    public void setArmyToAnimation(Army army){
        armyArrayList.add(army);
    }
}

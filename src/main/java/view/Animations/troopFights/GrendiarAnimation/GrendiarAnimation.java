package view.Animations.troopFights.GrendiarAnimation;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;
import model.Human.Troop.Army;
import view.Animations.troopFights.SwordManAnimation.SwordManAnimation;

import java.util.ArrayList;

public class GrendiarAnimation extends Transition {

    private Image BACK1 = new Image(GrendiarAnimation.class.
            getResource("/troop/grenadierFight/back/1.png").toExternalForm());
    private Image BACK2 = new Image(GrendiarAnimation.class.
            getResource("/troop/grenadierFight/back/2.png").toExternalForm());


    private Image FRONT1 = new Image(GrendiarAnimation.class.
            getResource("/troop/grenadierFight/front/1.png").toExternalForm());
    private Image FRONT2 = new Image(GrendiarAnimation.class.
            getResource("/troop/grenadierFight/front/2.png").toExternalForm());

    private Image LEFT1 = new Image(GrendiarAnimation.class.
            getResource("/troop/grenadierFight/left/1.png").toExternalForm());
    private Image LEFT2 = new Image(GrendiarAnimation.class.
            getResource("/troop/grenadierFight/left/2.png").toExternalForm());

    private Image RIGHT1 = new Image(GrendiarAnimation.class.
            getResource("/troop/grenadierFight/right/1.png").toExternalForm());
    private Image RIGHT2 = new Image(GrendiarAnimation.class.
            getResource("/troop/grenadierFight/right/2.png").toExternalForm());

    public ArrayList<Army> armyArrayList = new ArrayList<>();


    public GrendiarAnimation(){
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

package view.Animations.troopFights.MachineAnimation;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;
import model.Human.Troop.Army;
import view.Animations.troopFights.SwordManAnimation.SwordManAnimation;

import java.util.ArrayList;

public class MachineAnimation extends Transition {
    private Image BACK1 = new Image(SwordManAnimation.class.
            getResource("/troop/swordManFight/back/1.png").toExternalForm());
    private Image BACK2 = new Image(SwordManAnimation.class.
            getResource("/troop/swordManFight/back/2.png").toExternalForm());

    private Image FRONT1 = new Image(SwordManAnimation.class.
            getResource("/troop/swordManFight/front/1.png").toExternalForm());
    private Image FRONT2 = new Image(SwordManAnimation.class.
            getResource("/troop/swordManFight/front/2.png").toExternalForm());

    private Image LEFT1 = new Image(SwordManAnimation.class.
            getResource("/troop/swordManFight/left/1.png").toExternalForm());
    private Image LEFT2 = new Image(SwordManAnimation.class.
            getResource("/troop/swordManFight/left/2.png").toExternalForm());

    private Image RIGHT1 = new Image(SwordManAnimation.class.
            getResource("/troop/swordManFight/right/1.png").toExternalForm());
    private Image RIGHT2 = new Image(SwordManAnimation.class.
            getResource("/troop/swordManFight/right/2.png").toExternalForm());

    public ArrayList<Army> armyArrayList = new ArrayList<>();


    public MachineAnimation(){
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
                    break;
                case LEFT:
                    if (v >= 0 && v < 0.25) army.getImageView().setImage(LEFT1);
                    if (v >= 0.25 && v < 0.5) army.getImageView().setImage(LEFT2);
                    break;
                case RIGHT:
                    if (v >= 0 && v < 0.25) army.getImageView().setImage(RIGHT1);
                    if (v >= 0.25 && v < 0.5) army.getImageView().setImage(RIGHT2);
                    break;
                case FRONT:
                    if (v >= 0 && v < 0.33) army.getImageView().setImage(FRONT1);
                    if (v >= 0.33 && v < 0.66) army.getImageView().setImage(FRONT2);
                    break;
            }
        }
    }
    public void setArmyToAnimation(Army army){
        armyArrayList.add(army);
    }
}

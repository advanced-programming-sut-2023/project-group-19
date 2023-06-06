package view.Animations.troopFights.SlingerAnimation;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;
import model.Human.Troop.Army;
import view.Animations.troopFights.SwordManAnimation.SwordManAnimation;

import java.util.ArrayList;

public class SlingerAnimation extends Transition {
    private Image BACK1 = new Image(SlingerAnimation.class.
            getResource("/troop/slingerFight/back/1.png").toExternalForm());
    private Image BACK2 = new Image(SlingerAnimation.class.
            getResource("/troop/slingerFight/back/2.png").toExternalForm());
    private Image BACK3 = new Image(SlingerAnimation.class.
            getResource("/troop/slingerFight/back/3.png").toExternalForm());


    private Image FRONT1 = new Image(SlingerAnimation.class.
            getResource("/troop/slingerFight/front/1.png").toExternalForm());
    private Image FRONT2 = new Image(SlingerAnimation.class.
            getResource("/troop/slingerFight/front/2.png").toExternalForm());
    private Image FRONT3 = new Image(SlingerAnimation.class.
            getResource("/troop/slingerFight/front/3.png").toExternalForm());

    private Image LEFT1 = new Image(SlingerAnimation.class.
            getResource("/troop/slingerFight/left/1.png").toExternalForm());
    private Image LEFT2 = new Image(SlingerAnimation.class.
            getResource("/troop/slingerFight/left/2.png").toExternalForm());
    private Image LEFT3 = new Image(SlingerAnimation.class.
            getResource("/troop/slingerFight/left/3.png").toExternalForm());


    private Image RIGHT1 = new Image(SlingerAnimation.class.
            getResource("/troop/slingerFight/right/1.png").toExternalForm());
    private Image RIGHT2 = new Image(SlingerAnimation.class.
            getResource("/troop/slingerFight/right/2.png").toExternalForm());
    private Image RIGHT3 = new Image(SlingerAnimation.class.
            getResource("/troop/slingerFight/right/3.png").toExternalForm());
    private Image RIGHT4 = new Image(SlingerAnimation.class.
            getResource("/troop/slingerFight/right/4.png").toExternalForm());

    public ArrayList<Army> armyArrayList = new ArrayList<>();


    public SlingerAnimation(){
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
                    if (v >= 0 && v < 0.33) army.getImageView().setImage(LEFT1);
                    if (v >= 0.33 && v < 0.66) army.getImageView().setImage(LEFT2);
                    if (v >= 0.66) army.getImageView().setImage(LEFT3);
                    break;
                case RIGHT:
                    if (v >= 0 && v < 0.25) army.getImageView().setImage(RIGHT1);
                    if (v >= 0.25 && v < 0.5) army.getImageView().setImage(RIGHT2);
                    if (v >= 0.5 && v < 0.75) army.getImageView().setImage(RIGHT3);
                    if (v >= 0.75) army.getImageView().setImage(RIGHT4);
                    break;
                case FRONT:
                    if (v >= 0 && v < 0.33) army.getImageView().setImage(FRONT1);
                    if (v >= 0.33 && v < 0.66) army.getImageView().setImage(FRONT2);
                    if (v >= 0.66) army.getImageView().setImage(FRONT3);
                    break;
            }
        }
    }
    public void setArmyToAnimation(Army army){
        armyArrayList.add(army);
    }
}

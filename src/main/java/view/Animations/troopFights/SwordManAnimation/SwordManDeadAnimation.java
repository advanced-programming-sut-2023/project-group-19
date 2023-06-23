package view.Animations.troopFights.SwordManAnimation;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;
import model.Empire;
import model.Human.Troop.Army;
import model.Map;
import view.Model.NewButton;
import view.TileManager;

import java.util.ArrayList;

public class SwordManDeadAnimation extends Transition {
    public Map map ;

    private Image DEAD1 = new Image(SwordManAnimation.class.
            getResource("/troop/swordManFight/dead/1.png").toExternalForm());
    private Image DEAD2 = new Image(SwordManAnimation.class.
            getResource("/troop/swordManFight/dead/2.png").toExternalForm());
    private Image DEAD3 = new Image(SwordManAnimation.class.
            getResource("/troop/swordManFight/dead/3.png").toExternalForm());
    private Image DEAD4 = new Image(SwordManAnimation.class.
            getResource("/troop/swordManFight/dead/4.png").toExternalForm());
    private Image DEAD5 = new Image(SwordManAnimation.class.
            getResource("/troop/swordManFight/dead/5.png").toExternalForm());
    private Image DEAD6 = new Image(SwordManAnimation.class.
            getResource("/troop/swordManFight/dead/6.png").toExternalForm());
    private Image DEAD7 = new Image(SwordManAnimation.class.
            getResource("/troop/swordManFight/dead/7.png").toExternalForm());
    private Image DEAD8 = new Image(SwordManAnimation.class.
            getResource("/troop/swordManFight/dead/8.png").toExternalForm());
    private Image DEAD9 = new Image(SwordManAnimation.class.
            getResource("/troop/swordManFight/dead/9.png").toExternalForm());
    private Image DEAD10 = new Image(SwordManAnimation.class.
            getResource("/troop/swordManFight/dead/10.png").toExternalForm());
    private Image DEAD11 = new Image(SwordManAnimation.class.
            getResource("/troop/swordManFight/dead/11.png").toExternalForm());
    private Image DEAD12 = new Image(SwordManAnimation.class.
            getResource("/troop/swordManFight/dead/12.png").toExternalForm());
    private Image DEAD13 = new Image(SwordManAnimation.class.
            getResource("/troop/swordManFight/dead/13.png").toExternalForm());
    private Image DEAD14 = new Image(SwordManAnimation.class.
            getResource("/troop/swordManFight/dead/14.png").toExternalForm());
    TileManager tileManager ;

    public SwordManDeadAnimation(TileManager tileManager){
        this.setCycleCount(1);
        this.setCycleDuration(Duration.seconds(3));
        this.tileManager = tileManager ;
    }
    public ArrayList<Army> armies = new ArrayList<>();


    @Override
    protected void interpolate(double v) {
        for(Army army : armies){
            if(v >= 0 && v < 0.07) army.getImageView().setImage(DEAD1);
            if(v >= 0.14 && v < 0.21) army.getImageView().setImage(DEAD2);
            if(v >= 0.21 && v < 0.28) army.getImageView().setImage(DEAD3);
            if(v >= 0.28 && v < 0.35) army.getImageView().setImage(DEAD4);
            if(v >= 0.42 && v < 0.49) army.getImageView().setImage(DEAD5);
            if(v >= 0.42 && v < 0.56) army.getImageView().setImage(DEAD6);
            if(v >= 0.56 && v < 0.63) army.getImageView().setImage(DEAD7);
            if(v >= 0.63 && v < 0.7) army.getImageView().setImage(DEAD8);
            if(v >= 0.7 && v < 0.77) army.getImageView().setImage(DEAD9);
            if(v >= 0.77 && v < 0.84) army.getImageView().setImage(DEAD10);
            if(v >= 0.84 && v < 0.91) army.getImageView().setImage(DEAD11);
            if(v >= 0.91 && v < 0.95) army.getImageView().setImage(DEAD12);
            if(v >= 0.95 && v < 0.97) army.getImageView().setImage(DEAD13);
            if(v >= 0.97 ) army.getImageView().setImage(DEAD14);
            if(v == 1) {
                int x = army.xCoordinate;
                int y = army.yCoordinate;
                ((NewButton)tileManager.list.get(100 * x + y)).getArmy().remove(army);
                army.getImageView().setImage(null);
                Empire empire = army.getEmpire();
                empire.empireArmy.remove(army);
                map.getTroopMap()[x][y].remove(army);
            }
        }
    }
    public void setArmyToAnimation(Army army){
        armies.add(army);
    }

}

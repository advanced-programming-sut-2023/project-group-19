package view.Animations.troopFights.SlaveAnimation;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;
import model.Empire;
import model.Human.Troop.Army;
import model.Map;
import view.Animations.troopFights.SwordManAnimation.SwordManAnimation;
import view.Model.NewButton;
import view.TileManager;

import java.util.ArrayList;

public class DeadSlaveAnimation extends Transition {
    public Map map ;
    private Image DEAD1 = new Image(SwordManAnimation.class.
            getResource("/troop/slaveFight/dead/1.png").toExternalForm());
    private Image DEAD2 = new Image(SwordManAnimation.class.
            getResource("/troop/slaveFight/dead/2.png").toExternalForm());
    private Image DEAD3 = new Image(SwordManAnimation.class.
            getResource("/troop/slaveFight/dead/3.png").toExternalForm());
    private Image DEAD4 = new Image(SwordManAnimation.class.
            getResource("/troop/slaveFight/dead/4.png").toExternalForm());
    private Image DEAD5 = new Image(SwordManAnimation.class.
            getResource("/troop/slaveFight/dead/5.png").toExternalForm());
    private Image DEAD6 = new Image(SwordManAnimation.class.
            getResource("/troop/slaveFight/dead/6.png").toExternalForm());
    private Image DEAD7 = new Image(SwordManAnimation.class.
            getResource("/troop/slaveFight/dead/7.png").toExternalForm());
    private Image DEAD8 = new Image(SwordManAnimation.class.
            getResource("/troop/slaveFight/dead/8.png").toExternalForm());

    TileManager tileManager ;

    public DeadSlaveAnimation(TileManager tileManager){
        this.setCycleCount(1);
        this.setCycleDuration(Duration.seconds(3));
        this.tileManager = tileManager ;
    }
    public ArrayList<Army> armies = new ArrayList<>();
    @Override
    protected void interpolate(double v) {
        for(Army army : armies) {
            if (v >= 0 && v < 0.125) army.getImageView().setImage(DEAD1);
            if (v >= 0.125 && v < 0.25) army.getImageView().setImage(DEAD2);
            if (v >= 0.25 && v < 0.375) army.getImageView().setImage(DEAD3);
            if (v >= 0.375 && v < 0.5) army.getImageView().setImage(DEAD4);
            if (v >= 0.5 && v < 0.625) army.getImageView().setImage(DEAD5);
            if (v >= 0.625 && v < 0.75) army.getImageView().setImage(DEAD6);
            if (v >= 0.75 && v < 0.875) army.getImageView().setImage(DEAD7);
            if (v >= 0.975) army.getImageView().setImage(DEAD8);
            if (v == 1) {
                int x = army.xCoordinate;
                int y = army.yCoordinate;
                ((NewButton) tileManager.list.get(100 * x + y)).getArmy().remove(army);
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

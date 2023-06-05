package view.Animations.troopFights.MachineAnimation;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;
import model.Empire;
import model.Human.Troop.Army;
import view.Animations.troopFights.SwordManAnimation.SwordManAnimation;
import view.Model.NewButton;
import view.TileManager;

import java.util.ArrayList;

public class DeadMachineAnimation extends Transition {
    private Image DEAD1 = new Image(SwordManAnimation.class.
            getResource("/troop/swordManFight/dead/1.png").toExternalForm());

    public TileManager tileManager ;

    public DeadMachineAnimation(TileManager tileManager){
        this.setCycleCount(1);
        this.setCycleDuration(Duration.seconds(3));
        this.tileManager = tileManager ;
    }
    private ArrayList<Army> armies = new ArrayList<>();


    @Override
    protected void interpolate(double v) {
        for(Army army : armies){
            if(v >= 0) army.getImageView().setImage(DEAD1);
            if(v == 1) {
                int x = army.xCoordinate;
                int y = army.yCoordinate;
                ((NewButton)tileManager.list.get(100 * x + y)).getArmy().remove(army);
                army.getImageView().setImage(null);
                Empire empire = army.getEmpire();
                empire.empireArmy.remove(army);
            }
        }
    }
    public void setArmyToAnimation(Army army){
        armies.add(army);
    }
}

package model.Human.Troop;

import javafx.scene.image.ImageView;
import model.Human.Names;

import java.util.List;

public interface HumanConstantFunctions {
    public int maxHp();

    public int hp();

    public int speed();

    public int getCurrentX();

    public int getCurrentY();

    public int restOfMoves();

    public int getGoalX();

    public int getGoalY();

    public List<Integer> myPath();

    public int getStartX();

    public int getStartY();

    public int getFinalX();

    public int getFinalY();
    public Names typeOfArmy();
    public ImageView imageView();
    public String direction();
    public double xLocationOnMap();
    public double yLocationOnMap();
}

package model.Human.Troop;

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

}

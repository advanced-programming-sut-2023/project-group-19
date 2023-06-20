package model.Obstacle;

public class SavedObstacles {
    public String name ;
    public String type ;
    public boolean notBuildable ;
    public boolean notPassable ;
    public int x ;
    public int y ;

    public String getName() {
        return name;
    }

    public boolean isNotBuildable() {
        return notBuildable;
    }

    public boolean isNotPassable() {
        return notPassable;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getType() {
        return type;
    }


}
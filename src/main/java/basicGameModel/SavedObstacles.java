package basicGameModel;

public class SavedObstacles {
    public String name ;
    public String type ;
//    public String groundType ;
    public boolean notBuildable ;
    public boolean notPassable ;
    public int numberOfPlayers ;
    public String nameOfMap ;

    public int x ;
    public int y ;

//    public String getGroundType() {
//        return groundType;
//    }

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

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public String getNameOfMap() {
        return nameOfMap;
    }
}

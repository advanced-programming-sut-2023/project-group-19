package model.Obstacle;

import model.Human.Names;

public class Stone extends Obstacle {
    //TODO army can not pass from stones
    public String dir ;
    public void stone(String dir){
        this.dir = dir ;
        name = ObstacleName.STONE;
    }
}

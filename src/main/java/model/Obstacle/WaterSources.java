package model.Obstacle;

public class WaterSources extends Obstacle {
    //TODO army can not pass these obstacles : river , bigPond , smallPond , sea
    public void petrol(){
        name = ObstacleName.PETROL;
    }
    public void plain(){
        name = ObstacleName.PLAIN;
    }
    public void shallowWater(){
        name = ObstacleName.SHALLOW_WATER;
    }
    public void sea(){
        name = ObstacleName.SEA;
    }
    public void river(){
        name = ObstacleName.RIVER;
    }
    public void bigPond(){
        name = ObstacleName.BIG_POND;
    }
    public void smallPond(){
        name = ObstacleName.SMALL_POND;
    }
    public void beach(){
        name = ObstacleName.BEACH;
    }


}

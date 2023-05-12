package model.Obstacle;

public class WaterSources extends Obstacle {

    //TODO army can not pass these obstacles : river , bigPond , smallPond , sea
    public void petrol() {
        name = ObstacleName.PETROL;
    }

    public void plain() {
        name = ObstacleName.PLAIN;
    }

    public void shallowWater() {
        name = ObstacleName.SHALLOW_WATER;
    }

    public void sea() {
        name = ObstacleName.SEA;
    }

    public void river() {
        name = ObstacleName.RIVER;
    }

    public void bigPond() {
        name = ObstacleName.BIG_POND;
    }

    public void smallPond() {
        name = ObstacleName.SMALL_POND;
    }

    public void beach() {
        name = ObstacleName.BEACH;
    }

    public static WaterSources getWaterSourcesByName(String name) {
        WaterSources waterSources = new WaterSources();
        if (name.equals("petrol")) {
            waterSources.petrol();
        } else if (name.equals("shallowWater")) {
            waterSources.shallowWater();
        }else if (name.equals("sea")) {
            waterSources.sea();
        } else if (name.equals("river")) {
            waterSources.river();
        } else if (name.equals("bigPond")) {
            waterSources.bigPond();
        } else if (name.equals("smallPond")) {
            waterSources.smallPond();
        } else if (name.equals("beach")) {
            waterSources.beach();
        } else return null;
        return waterSources;
    }


}

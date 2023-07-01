package basicGameModel;

public class Stone extends Obstacle {
    public String dir;

    public void stone(String dir) {
        this.dir = dir;
        name = ObstacleName.STONE;
    }
}

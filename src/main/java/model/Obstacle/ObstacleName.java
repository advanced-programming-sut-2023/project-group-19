package model.Obstacle;

public enum ObstacleName {
    DESERT_TREE("Desert Tree"),
    CherryTree("Cherry Tree"),
    OliveTree("Olive Tree"),
    CoconutTree("Coconut Tree"),
    DateTree("Date Tree"),
    PETROL("Petrol"),
    BIG_POND("Big Pond"),
    SMALL_POND("Small Pond"),
    BEACH("Beach"),
    SEA("Sea"),
    RIVER("River"),
    SHALLOW_WATER("Shallow Water"),
    STONE("Stone"),
    ;
    private String obstacleName;

    ObstacleName(String obstacleName) {
        this.obstacleName = obstacleName;
    }

    public String getObstacleName() {
        return obstacleName;
    }

    public void setObstacleName(String obstacleName) {
        this.obstacleName = obstacleName;
    }
}

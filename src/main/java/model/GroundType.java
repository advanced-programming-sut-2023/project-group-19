package model;

public enum GroundType {
    DEFAULT("default"),
    GROUND_WITH_STONE("gravel"),
    STONE_ROCK("stoneRock"),
    STONE("stone"),
    IRON("iron"),
    GRASS("grass"),
    DASH("dash"),
    FILLFUL_DASH("fillFulDash"),
    PLAIN("plain"),
    ;
    private String groundType;
    GroundType(String groundType) {
        this.groundType = groundType;
    }
    public GroundType getEnumGroundType ;

    public String getGroundType() {
        return groundType;
    }
}

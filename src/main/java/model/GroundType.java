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
    public static GroundType getEnumGroundType(String name){
        if(name.equals("plain")) return PLAIN ;
        else if(name.equals("fillfulDash")) return FILLFUL_DASH ;
        else if(name.equals("dash")) return DASH ;
        else if(name.equals("grass")) return GRASS ;
        else if(name.equals("iron")) return IRON ;
        else if(name.equals("stone")) return STONE ;
        else if(name.equals("stoneRock")) return STONE_ROCK ;
        else if(name.equals("gravel")) return GROUND_WITH_STONE ;
        else if(name.equals("default")) return DEFAULT ;
        else return null ;
    }

    public String getGroundType() {
        return groundType;
    }
}

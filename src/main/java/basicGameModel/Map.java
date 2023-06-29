package basicGameModel;



import basicGameModel.troop.Army;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Map {
    private static ArrayList<Map> savedMaps = new ArrayList<>();
    public static int mapSize = 100;
    public ArrayList<Building>[][] buildingMap;
    public ArrayList<Army>[][] troopMap;
    public ArrayList<Obstacle>[][] obstacleMap;
    public ArrayList<GroundType>[][] groundType;
    public boolean[][] notBuildable;
    public boolean[][] notPassable;
    public boolean[][] wallPassable;
    public boolean[][] wall;

    public ArrayList<Building>[][] getBuildingMap() {
        return buildingMap;
    }
    public ArrayList<SavedObstacles> savingObstacle = new ArrayList<>();
    public static ArrayList<ArrayList<SavedObstacles>> arrayListArrayListOfObject = new ArrayList<>();


    public void AddToBuildingMap(int x, int y, Building newBuilding) {
        buildingMap[x][y].add(newBuilding);
    }

    public ArrayList<Obstacle>[][] getObstacleMap() {
        return obstacleMap;
    }

    public ArrayList<Army>[][] getTroopMap() {
        return troopMap;
    }

    public void CreateMap(int size) {
        Map.mapSize = size;
        buildingMap = new ArrayList[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buildingMap[i][j] = new ArrayList<>();
            }
        }
        troopMap = new ArrayList[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                troopMap[i][j] = new ArrayList<>();
            }
        }
        obstacleMap = new ArrayList[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                obstacleMap[i][j] = new ArrayList<>();
            }
        }
        groundType = new ArrayList[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                groundType[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                groundType[i][j].add(GroundType.DEFAULT);
            }
        }
        Map.mapSize = size;
        notPassable = new boolean[size][size];
        notBuildable = new boolean[size][size];
        wallPassable = new boolean[size][size];
        wall = new boolean[size][size];
    }

    public ArrayList<GroundType>[][] getGroundType() {
        return groundType;
    }

    public static ArrayList<Map> getSavedMaps() {
        return savedMaps;
    }

    public static ArrayList<SavedObstacles> convertJsonObstacleToObject(String data){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(SavedObstacles.class, new ObstacleAdapter());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Type type = new TypeToken<ArrayList<SavedObstacles>>(){}.getType();
        ArrayList<SavedObstacles> a2 = gson.fromJson(data,type);
        return a2 ;
    }

    public static String convertArrayLIstOfMapIntoJsonForm(ArrayList<SavedObstacles> data){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(SavedObstacles.class, new ObstacleAdapter());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String a2 = gson.toJson(data);
        return a2 ;
    }
    public static Map buildMap(ArrayList<SavedObstacles> arrayList){
        Map map = new Map();
        map.CreateMap(Map.mapSize);
        for(SavedObstacles saveObject  : arrayList){
            boolean isGroundType = false ;
            int x = saveObject.x ;
            int y = saveObject.y ;
            switch (saveObject.name) {
                case "TREE":
                    Tree tree = new Tree();
                    map.getObstacleMap()[x][y].add(tree);
                    break;
                case "SEA":
                    WaterSources waterSources = new WaterSources();
                    map.getObstacleMap()[x][y].add(waterSources);
                    break;
                case "STONE":
                    Stone stone = new Stone();
                    map.getObstacleMap()[x][y].add(stone);
                    break;
                case "GROUND_TYPE" :
                    isGroundType = true ;
                    switch (saveObject.type){
                        case "dash":
                            map.getGroundType()[x][y].clear();
                            map.getGroundType()[x][y].add(GroundType.DASH);
                            break;
                        case "grass":
                            map.getGroundType()[x][y].clear();
                            map.getGroundType()[x][y].add(GroundType.GRASS);
                            break;
                        case "gravel":
                            map.getGroundType()[x][y].clear();
                            map.getGroundType()[x][y].add(GroundType.GROUND_WITH_STONE);
                            break;
                        case "iron":
                            map.getGroundType()[x][y].clear();
                            map.getGroundType()[x][y].add(GroundType.DEFAULT);
                            break;
                        case "plain":
                            map.getGroundType()[x][y].clear();
                            map.getGroundType()[x][y].add(GroundType.PLAIN);
                            break;
                    }
                    break;
            }
            if(!isGroundType) {
                map.notBuildable[x][y] = saveObject.notBuildable;
                map.notPassable[x][y] = saveObject.notPassable;
            }
        }
        return map;
    }

}

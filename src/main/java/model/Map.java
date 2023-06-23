package model;

import model.Building.Building;
import model.Human.Troop.Army;
import model.Obstacle.Obstacle;
import model.Obstacle.SavedObstacles;

import java.util.ArrayList;

public class Map {
    private static ArrayList<Map> savedMaps = new ArrayList<>();
    public static int mapSize = 100;

    public static ArrayList<Building>[][] buildingMap;
    public static ArrayList<Army>[][] troopMap;
    public static ArrayList<Obstacle>[][] obstacleMap;

    public ArrayList<GroundType>[][] groundType;
    public static boolean[][] notBuildable;
    public static boolean[][] notPassable;
    public static boolean[][] wallPassable;
    public static boolean[][] wall;
    public static ArrayList<ArrayList<SavedObstacles>> allJsonMaps =  new ArrayList<>();

    public static ArrayList<Building>[][] getBuildingMap() {
        return buildingMap;
    }

    public ArrayList<SavedObstacles> savingObstacle = new ArrayList<>();

    public void AddToBuildingMap(int x, int y, Building newBuilding) {
        buildingMap[x][y].add(newBuilding);
    }

    public static ArrayList<Obstacle>[][] getObstacleMap() {
        return obstacleMap;
    }

    public static ArrayList<Army>[][] getTroopMap() {
        return troopMap;
    }
    {
        allJsonMaps.add(savingObstacle);
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

}

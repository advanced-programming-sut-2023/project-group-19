package model;

import model.Building.Building;
import model.Human.Troop.Army;
import model.Obstacle.Obstacle;

import java.util.ArrayList;

public class Map {

    public Map(){

    }
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
}

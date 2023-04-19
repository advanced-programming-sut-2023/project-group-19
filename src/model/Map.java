package model;

import model.Building.Building;
import model.Human.Troop.Army;
import model.Obstacle.Obstacle;

import java.util.ArrayList;
import java.util.Arrays;

public class Map {
    public static ArrayList<Building>[][] buildingMap;
    public static ArrayList<Army>[][] troopMap;
    public static ArrayList<Obstacle>[][] obstacleMap;
    public static ArrayList<String>[][] groundType;
    public static boolean[][] notPassable; // for troop
    public static boolean[][] notBuildable; // for building

    public static ArrayList<Building>[][] getBuildingMap() {
        return buildingMap;
    }

    public static void AddToBuildingMap(int x , int y , Building newBuilding) {
        buildingMap[x][y].add(newBuilding);
    }

    public static ArrayList<String>[][] getGroundType() {
        return groundType;
    }

    public static void CreateMap(int size){
        buildingMap=new ArrayList[size][size];
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                buildingMap[i][j] = new ArrayList<>();
            }
        }
        troopMap=new ArrayList[size][size];
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                troopMap[i][j] = new ArrayList<>();
            }
        }
        obstacleMap=new ArrayList[size][size];
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                obstacleMap[i][j] = new ArrayList<>();
            }
        }
        groundType=new ArrayList[size][size];
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                groundType[i][j] = new ArrayList<>();
            }
        }
        notPassable = new boolean[size][size];
        notBuildable = new boolean[size][size];
    }
}

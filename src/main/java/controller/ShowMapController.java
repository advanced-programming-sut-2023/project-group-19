package controller;

import model.*;
import model.Building.Building;
import model.Human.Names;
import model.Human.Troop.Army;
import model.Obstacle.Obstacle;

import model.Obstacle.ObstacleName;
import model.Obstacle.WaterSources;

import java.util.HashMap;

public class ShowMapController {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK_BACKGROUND
            = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND
            = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND
            = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND
            = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND
            = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND
            = "\u001B[47m";
    static int size = 200;
    static int x;
    static int y;
    static int leftLimit;
    static int rightLimit;
    static int uplimit;
    static int downLimit;

    public static String showMap(int xInput, int yInput, boolean isMove) {
        if(!CreateMapController.mapIsBuilded)  return "first build a map!";
        if ((xInput <= 0 || xInput > size) || (yInput <= 0 || yInput > size)) return "fill correctly;" +
                "your numbers out of bounds";
        x = xInput;
        y = yInput;
        downLimit = x + 2;
        uplimit = x - 2;
        rightLimit = y + 9;
        leftLimit = y - 9;

        if (uplimit <= 0) uplimit = 1;
        if (downLimit >= size) downLimit = size;
        if (leftLimit <= 0) leftLimit = 1;
        if (rightLimit >= size) rightLimit = size;
        return makeMap();
    }

    static String makeSquare(int row) {
        Building building;
        Army army;
        Obstacle obstacle;
        int yVar = leftLimit;
        int height = 4;
        int length = 6;
        int i, j;
        StringBuilder square = new StringBuilder();
        for (i = 1; i <= height; i++) {

            for (int k = yVar; k <= rightLimit; k++) {
                boolean isThingThere = true;
                for (j = 1; j <= length; j++) {

                    if (i == 1 || i == height || j == 1 || j == length) {

                        square.append("*");
                        if (length == j) square.append("  ");
                    } else {
                        String type = "";
                         if (row == x - 1 && k - 1 == y - 1) {
                            type = "&";
                        }
                        else if (!(Map.getBuildingMap()[row][k - 1]).isEmpty()) {

                            type = "B";
                        } else if (!(Map.getTroopMap()[row][k - 1]).isEmpty()) type = "S";
//                        else if(Manage.getCurrentEmpire() != null &&
//                                !(Map.getTroopMap()[row][k - 1]).isEmpty()){
//                            for(Army army : Map.getTroopMap()[row][k - 1]){
//
//                            }
//                        }
                        else if (!(Map.getObstacleMap()[row][k - 1]).isEmpty()) {
                            obstacle = Map.getObstacleMap()[row][k - 1].get(0);
                             System.out.println(obstacle);
                            ObstacleName name = obstacle.getName();
                             System.out.println(name);
                            if (name.equals(ObstacleName.DESERT_TREE) || name.equals(ObstacleName.OliveTree) ||
                                    name.equals(ObstacleName.DateTree) || name.equals(ObstacleName.CoconutTree) ||
                                    name.equals(ObstacleName.CherryTree)) type = "T";
                        } else type = " ";
                        if (!Map.getGroundType()[row][k - 1].isEmpty()) {
                            if (Map.getGroundType()[row][k - 1].get(0).equals(GroundType.DEFAULT))
                                type = ANSI_BLACK_BACKGROUND + type + ANSI_RESET;
                            else if (Map.getGroundType()[row][k - 1].get(0).equals(GroundType.GROUND_WITH_STONE))
                                type = ANSI_BLACK_BACKGROUND + type + ANSI_RESET;
                            else if (Map.getGroundType()[row][k - 1].get(0).equals(GroundType.IRON))
                                type = ANSI_BLUE_BACKGROUND + type + ANSI_RESET;
                            else if (Map.getGroundType()[row][k - 1].get(0).equals(GroundType.STONE_ROCK))
                                type = ANSI_WHITE_BACKGROUND + type + ANSI_RESET;
                            else if (Map.getGroundType()[row][k - 1].get(0).equals(GroundType.GRASS))
                                type = ANSI_GREEN_BACKGROUND + type + ANSI_RESET;
                            else if (Map.getGroundType()[row][k - 1].get(0).equals(GroundType.FILLFUL_DASH))
                                type = ANSI_GREEN_BACKGROUND + type + ANSI_RESET;
                            else if (Map.getGroundType()[row][k - 1].get(0).equals(GroundType.DASH))
                                type = ANSI_YELLOW_BACKGROUND + type + ANSI_RESET;
                            else if (Map.getGroundType()[row][k - 1].get(0).equals(GroundType.STONE))
                                type = ANSI_RED_BACKGROUND + type + ANSI_RESET;
                        }
                        square.append(type);
                    }
                }
            }
            square.append("\n");
        }
        return square.toString();
    }

    public static String makeMap() {
        int lengthOfMap = rightLimit - leftLimit + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = uplimit; i <= downLimit; i++) {
            for (int k = 0; k < 8 * lengthOfMap; k++) {
                sb.append("-");
            }
            sb.append("\n");
            sb.append(makeSquare(i - 1));
        }
        return sb.toString();

    }

    public static String moveMap(int deltaX, int deltaY) {
        if(!CreateMapController.mapIsBuilded)  return "first build a map!";
        if (leftLimit + deltaY <= 0 || rightLimit + deltaY > size ||
                uplimit + deltaX <= 0 || downLimit + deltaX > size) return "fill correctly;" +
                "your location out of bounds";
        leftLimit += deltaY;
        rightLimit += deltaY;
        uplimit += deltaX;
        downLimit += deltaX;

        return makeMap();
    }

    public static String showDetail(int x, int y) {
        if(!CreateMapController.mapIsBuilded)  return "first build a map!";
        String groundType = Map.getGroundType()[x][y].get(0).getGroundType();
        if(groundType.equals(GroundType.DEFAULT.getGroundType())){
            if(Map.getObstacleMap()[x - 1][y - 1].get(0) instanceof WaterSources){
                WaterSources waterSources = (WaterSources) Map.getObstacleMap()[x - 1][y - 1].get(0);
                groundType = waterSources.getName().getObstacleName();
            }
        }
        StringBuilder soildersString = new StringBuilder("\n");
        HashMap<Names,Integer> soilders = new HashMap<>();
        for(Army army : Map.getTroopMap()[x - 1][y - 1]){
            if(soilders.containsKey(army.getNames())){
               int number = soilders.get(army.getNames()) ;
               number ++ ;
               soilders.put(army.getNames(),number);
            }else{
                soilders.put(army.getNames(),0);
            }
        }
        for(Names key : soilders.keySet()){
            String text = key.getName() + ": " + soilders.get(key) + "\n" ;
            soildersString.append(text);
        }
        String army = soildersString.toString();

        String buildingName = "empty";
        if(!Map.getBuildingMap()[x - 1][y - 1].isEmpty()){
            buildingName = Map.getBuildingMap()[x - 1][y - 1].get(0).getName();
        }
        return "Grounf type is :" + groundType + "\n"+ "army is: " + army + " \n" + "building: " + buildingName ;

    }



}

//    Building building;
//        String buildingName = "empty";
//        Army army;
//        String armyName = "empty";
//        String grounfType = "empty";
//        int numOfArmy = 0;
//        if (!Map.getBuildingMap()[x][y].isEmpty()) {
//            building = Map.getBuildingMap()[x][y].get(0);
//            buildingName = String.valueOf(building.getName());
//        }
//        if (!Map.getTroopMap()[x][y].isEmpty()) {
//            army = Map.getTroopMap()[x][y].get(0);
//            armyName = String.valueOf(army.getNames());
//            numOfArmy = Map.getTroopMap()[x][y].size();
//        }
//        if (!Map.getGroundType()[x][y].isEmpty()) {
//            grounfType = Map.getGroundType()[x][y].get(0).getGroundType();
//        }
//        return "Building: " + buildingName + "\n" +
//                "army: " + armyName + " --> " + numOfArmy + "\n" +
//                "ground type: " + grounfType;
//
//
//    }

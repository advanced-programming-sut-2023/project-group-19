package controller;

import model.*;
import model.Building.Building;
import model.Human.Troop.Army;
import model.Obstacle.Obstacle;

import java.util.Scanner;

import model.Obstacle.ObstacleName;

public class CreateMapController {
    static int size = 200 ;
    static int x ;
    static int y ;
    static int leftLimit ;
    static int rightLimit ;
    static int uplimit ;
    static int downLimit ;
    public static String showMap(int xInput , int yInput){
        if((xInput <= 0  || xInput > size) || (yInput <= 0 || yInput > size)) return "fill correctly;" +
                "your numbers out of bounds";
        x = xInput;
        y = yInput;
        downLimit = x + 2 ;
        uplimit = x - 2 ;
        rightLimit = y + 9 ;
        leftLimit = y - 9 ;
//        System.out.println(y);
//        System.out.println(leftLimit);
//        System.out.println(rightLimit);
        if(uplimit < 0) uplimit = 1 ;
        if(downLimit > size) downLimit = size ;
        if(leftLimit < 0) leftLimit = 1 ;
        if(rightLimit > size) rightLimit = size ;
        return makeMap();
    }
    static String makeSquare(int row) {
        Building building ;
        Army army ;
        Obstacle obstacle ;
        int yVar = leftLimit  ;
        int height =  4 ;
        int length = 6 ;
        int i, j;
        StringBuilder square = new StringBuilder();
        for (i = 1; i <= height; i++) {

            for(int k =  yVar ; k <= rightLimit ; k ++) {
                for (j = 1; j <= length; j++) {

                    if (i == 1 || i == height || j == 1 || j == length) {

                        square.append("*");
                        if (length == j) square.append("  ");
                    }
                    else{
                        char type = 0;
                        if(!(Map.getBuildingMap()[row ][k - 1]).isEmpty()) type =  'B' ;
                        else if(!(Map.getTroopMap()[row][k - 1]).isEmpty()) type =  'S' ;
                        else if(!(Map.getObstacleMap()[row][k - 1]).isEmpty()){
                            obstacle  = Map.getObstacleMap()[row][k - 1].get(0);
                            ObstacleName name = obstacle.getName();
                            if(name.equals(ObstacleName.DESERT_TREE) || name.equals(ObstacleName.OliveTree) ||
                                    name.equals(ObstacleName.DateTree) || name.equals(ObstacleName.CoconutTree) ||
                                    name.equals(ObstacleName.CherryTree)) type = 'T';
                        }else if(row == x - 1 && k - 1 == y - 1){
                            type = '#';
                        }else type = ' ';
                        square.append(type);
                    }
                }
            }
//            System.out.println(row);
//            System.out.println(x - 1);
//            System.out.println("---------------------------------");
            square.append("\n");
        }
//        System.out.println(square.toString());
        return square.toString() ;
        //defult state for lengthOfMap is 19
        //defult state for height is 5
    }
    public static String makeMap(){
        int lengthOfMap = rightLimit - leftLimit + 1 ;
//        System.out.println(uplimit);
//        System.out.println(downLimit);
//        System.out.println(lengthOfMap);
        StringBuilder sb = new StringBuilder();
        String squaresIntoLine ;
//        System.out.println(uplimit);
//        System.out.println(downLimit);
        for(int i = uplimit ; i <= downLimit ; i ++) {
            for(int k = 0 ; k < 8 * lengthOfMap ; k ++){
                sb.append("-");
            }
            sb.append("\n");
            sb.append(makeSquare(i - 1));
        }
        return sb.toString();

    }
}
//downlimit


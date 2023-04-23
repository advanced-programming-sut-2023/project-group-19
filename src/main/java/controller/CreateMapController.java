package controller;

import model.*;

import java.util.Scanner;

public class CreateMapController {
    static int size = 200 ;
    static int x ;
    static int y ;
    static int upLimit = y - 5 ;
    static int downLimit = y + 5 ;
    static int rightLimit = x + 5 ;
    static int leftLimit = x - 5 ;
    public static String showMap(int x , int y){

    }
    static String makeSquare(int size) {
        int height =  4 ;
        int length = 6 ;
        int i, j;
        StringBuilder square = new StringBuilder();

        for (i = 1; i <= height; i++) {

            for(int k =  1 ; k <= size ; k ++) {
                for (j = 1; j <= length; j++) {

                    if (i == 1 || i == height || j == 1 || j == length) {

                        square.append("*");
                        if (length == j) square.append("  ");
                    }
                    else
                        square.append(" ");

                }
            }

            square.append("\n");
        }
        return square.toString() ;
        //defult state for lengthOfMap is 19
        //defult state for height is 5
    }
    public static String makeMap(int lengthOfMap , int heightOfMap){
        String squaresIntoLine = makeSquare(lengthOfMap);
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < heightOfMap ; i ++) {
            for(int k = 0 ; k < 8 * lengthOfMap ; k ++){
                sb.append("-");
            }
            sb.append("\n");
            sb.append(squaresIntoLine);
        }
        return sb.toString();

    }
}

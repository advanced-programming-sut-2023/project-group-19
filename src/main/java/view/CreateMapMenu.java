package view;

import model.Map;
import controller.*;
import view.Commands.CreateMapCommands;
import view.Commands.LoginAndRegisterCommands;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateMapMenu {
    public static void run(Scanner scanner){
        String command ;
        Matcher matcher ;
        while(true){
            command = scanner.nextLine();
            if((matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.MOVING_MAP)) != null){
                movingMap(command);
            }else System.out.println("invalid command");
        }

    }

    public static void movingMap(String command){
        System.out.println("into move map");
        int down = 0;
        int up = 0;
        int left = 0;
        int right = 0;
        Matcher matcher;
        command = command.replaceAll("map","");
        System.out.println(command);
        String regex = "(?<type>\\S+)\\s*(?<number>\\d+)?";
        matcher = Pattern.compile(regex).matcher(command);
//        matcher = CreateMapCommands.getMatcher(command,CreateMapCommands.MOVING_MAP_INTO_DIRECTION);
        while(matcher.find()){
            String type =  matcher.group("type");
            String number = matcher.group("number");
            System.out.println(type + "%%%" + number);
            //show map -x 200 -y 200
            switch (type){
                case "left" :
                    if(number != null) left = -1 * Integer.parseInt(number);
                    else left = -1 ;
                    break;
                case "right" :
                    if(number != null) right = Integer.parseInt(number);
                    else right = 1 ;
                    break;
                case "up" :
                    if(number != null) up = -1 * Integer.parseInt(number);
                    else up = -1 ;
                    break;
                case "down" :
                    if(number != null) down = Integer.parseInt(number);
                    else down = 1 ;
                    break;
            }
        }

        int deltaX = up + down ;
        int deltaY = right + left  ;
        System.out.println(deltaX);
        System.out.println(deltaY);
        System.out.println(CreateMapController.moveMap(deltaX,deltaY));

    }
}

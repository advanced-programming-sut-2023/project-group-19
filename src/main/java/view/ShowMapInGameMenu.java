package view;

import controller.ShowMapController;
import view.Commands.CreateMapCommands;
import view.Commands.GameMenuCommands;
import view.Commands.MainMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowMapInGameMenu {
    public static void run(Scanner scanner) {
        String command;
        while (true) {
            command = scanner.nextLine();
            if (CreateMapCommands.getMatcher(command, CreateMapCommands.MOVING_MAP) != null) {
                movingMap(command);
            }else if (MainMenuCommands.getMatcher(command, MainMenuCommands.SHOW_MAP) != null) {
                showMap(command);
            }
            else if (CreateMapCommands.getMatcher(command, CreateMapCommands.SHOW_DETAIL) != null) {
                showDetail(command);
            } else if ((CreateMapCommands.getMatcher(command, CreateMapCommands.EXIT)) != null) {
                System.out.println("Exit from map menu is successfully done!");
                return;
            }else{
                System.out.println("Invalid command");
            }
        }
    }

    public static void showMap(String command) {
        Matcher matcher;
        matcher = MainMenuCommands.getMatcher(command, MainMenuCommands.SHOW_MAP_X);
        if (matcher == null) {
            System.out.println("fill elements of map correctly!");
            return;
        }
        int x = Integer.parseInt(matcher.group("x"));

        matcher = MainMenuCommands.getMatcher(command, MainMenuCommands.SHOW_MAP_Y);
        if (matcher == null) {
            System.out.println("fill elements of map correctly!");
            return;
        }
        int y = Integer.parseInt(matcher.group("y"));
//        System.out.println(ShowMapController.showMap(x + 1, y + 1, false));
    }


    public static void movingMap(String command) {
        int down = 0;
        int up = 0;
        int left = 0;
        int right = 0;
        Matcher matcher;
        command = command.replaceAll("map", "");
        System.out.println(command);
        String regex = "(?<type>\\S+)\\s*(?<number>\\d+)?";
        matcher = Pattern.compile(regex).matcher(command);
        while (matcher.find()) {
            String type = matcher.group("type");
            String number = matcher.group("number");
            switch (type) {
                case "left":
                    if (number != null) left = -1 * Integer.parseInt(number);
                    else left = -1;
                    break;
                case "right":
                    if (number != null) right = Integer.parseInt(number);
                    else right = 1;
                    break;
                case "up":
                    if (number != null) up = -1 * Integer.parseInt(number);
                    else up = -1;
                    break;
                case "down":
                    if (number != null) down = Integer.parseInt(number);
                    else down = 1;
                    break;
            }
        }
        int deltaX = up + down;
        int deltaY = right + left;
//        System.out.println(ShowMapController.moveMap(deltaX, deltaY));
    }

    public static void showDetail(String command) {
        Matcher matcher;
        matcher = CreateMapCommands.getMatcher(command, CreateMapCommands.SHOW_X);
        if (matcher == null) {
            System.out.println("fill elements of map correctly!");
            return;
        }
        int x = Integer.parseInt(matcher.group("x"));

        matcher = CreateMapCommands.getMatcher(command, CreateMapCommands.SHOW_Y);
        if (matcher == null) {
            System.out.println("fill elements of map correctly!");
            return;
        }
        int y = Integer.parseInt(matcher.group("y"));
//        String result = ShowMapController.showDetail(x + 1, y + 1);
//        System.out.println(result);
    }
}

package view;

import controller.CreateMapController;
import controller.NextTurnController;
import controller.ShowMapController;
import controller.JsonController;
import model.Map;
import model.User;
import view.Commands.MainMenuCommands;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {
    public static void run(Scanner scanner) throws InterruptedException, IOException {
        System.out.println("Welcome to Main menu!");
        String command;
        Matcher matcher;
        while (true) {
            command = scanner.nextLine();

            if ((matcher = MainMenuCommands.getMatcher(command, MainMenuCommands.ENTER_PROFILE_MENU)) != null) {
                System.out.println("entered profile menu successfully");
                ProfileMenu.run(scanner);
            } else if (MainMenuCommands.getMatcher(command, MainMenuCommands.LOGOUT) != null) {
                User.setCurrentUser(null);
                JsonController.emptyFile();
                System.out.println("logged out");
                return;
            } else if ((matcher = MainMenuCommands.getMatcher(command, MainMenuCommands.SHOW_MAP)) != null) {
                showMap(command,scanner);
            } else if(command.matches("\\s*enter\\s+game\\s+menu")){
                enterGameMenu(scanner);
            }
            else System.out.println("Invalid command!");
        }
    }

    private static void enterGameMenu(Scanner scanner) throws IOException, InterruptedException {
        if(CreateMapController.numberOfEmpiers >= 2){
            NextTurnController nextTurnController = new NextTurnController();
            nextTurnController.game(scanner);
            CreateMapController.recovery();
        }else {
            System.out.println("more castle must be build!");
        }
    }
    public static void showMap(String command, Scanner scanner) throws IOException, InterruptedException {
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
        System.out.println(ShowMapController.showMap(x, y, false));
        CreateMapMenu.run(scanner);

    }
}

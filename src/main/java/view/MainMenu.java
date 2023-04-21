package view;

import controller.MainMenuController;
import view.Commands.MainMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {
    public static void run(Scanner scanner) throws InterruptedException {
        String command ;
        Matcher matcher ;
        while (true){
            command = scanner.nextLine();
            if((matcher = MainMenuCommands.getMatcher(command,MainMenuCommands.ENTER_PROFILE_MENU)) != null){
                System.out.println("entered profile menu successfully");
                ProfileMenu.run(scanner);
            }else if(MainMenuCommands.getMatcher(command,MainMenuCommands.LOGOUT) != null){
                MainMenuController.logout();
                System.out.println("logged out");
                return;
            }
        }
    }
}

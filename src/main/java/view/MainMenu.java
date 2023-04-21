package view;

import controller.JsonController;
import controller.MainMenuController;
import view.Commands.MainMenuCommands;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {
    public static void run(Scanner scanner) throws InterruptedException, IOException {
        String command ;
        Matcher matcher ;
        while (true){
            command = scanner.nextLine();
            if((matcher = MainMenuCommands.getMatcher(command,MainMenuCommands.ENTER_PROFILE_MENU)) != null){
                System.out.println("entered profile menu successfully");
                ProfileMenu.run(scanner);
            }else if(MainMenuCommands.getMatcher(command,MainMenuCommands.LOGOUT) != null){
                MainMenuController.logout();
                JsonController.emptyFile();
                System.out.println("logged out");
                return;
            }
        }
    }
    public static void logoutUser(){
    }
}

package view;

import view.Commands.MainMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {
    public static void run(Scanner scanner){
        String command ;
        Matcher matcher ;
        while (true){
            command = scanner.nextLine();

            if((matcher = MainMenuCommands.getMatcher(command,MainMenuCommands.ENTER_PROFILE_MENU)) != null){
                ProfileMenu.run(scanner);
            }
        }
    }
}

package view;

import controller.ShowMapController;
import controller.JsonController;
import model.User;
import view.Commands.MainMenuCommands;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {
    public static void run(Scanner scanner) throws InterruptedException, IOException {
        System.out.println("Welcome to Main menu!");
        String command ;
        Matcher matcher ;
        while (true){
            command = scanner.nextLine();

            if((matcher = MainMenuCommands.getMatcher(command,MainMenuCommands.ENTER_PROFILE_MENU)) != null){
                System.out.println("entered profile menu successfully");
                ProfileMenu.run(scanner);
            }else if(MainMenuCommands.getMatcher(command,MainMenuCommands.LOGOUT) != null){
                User.setCurrentUser(null);
                JsonController.emptyFile();
                System.out.println("logged out");
                return;
            }else if((matcher = MainMenuCommands.getMatcher(command,MainMenuCommands.SHOW_MAP)) != null){
                if(showMap(command,scanner)){
                    //function to game menu!
                }
            }
            else System.out.println("Invalid command!");
        }
    }
    public static void logoutUser(){
    }
    public static boolean showMap(String command,Scanner scanner) throws IOException, InterruptedException {
        Matcher matcher ;
        matcher = MainMenuCommands.getMatcher(command,MainMenuCommands.SHOW_MAP_X);
        if(matcher == null){
            System.out.println("fill elements of map correctly!");
            return false;
        }
        int x = Integer.parseInt(matcher.group("x"));

        matcher = MainMenuCommands.getMatcher(command,MainMenuCommands.SHOW_MAP_Y);
        if(matcher == null){
            System.out.println("fill elements of map correctly!");
            return false;
        }
        int y = Integer.parseInt(matcher.group("y"));
        System.out.println(ShowMapController.showMap(x,y,false));
        return CreateMapMenu.run(scanner);

    }
}

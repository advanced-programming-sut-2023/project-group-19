package view;

import controller.Building.BuildingController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.Commands.BuildingCommands;
import view.Messages.BuildingMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class BuildingMenu extends Application {
    String input;
    BuildingController buildingController = new BuildingController();

    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        pane.setPrefSize(1530,800);
        stage.setScene(scene);
        stage.show();
    }

//    public void run(Scanner scanner) {
//        System.out.println("Welcome to BuildingMenu!");
//        while (true) {
//            input = scanner.nextLine();
//            if (BuildingCommands.getMatcher(input, BuildingCommands.BUILDING_COMMANDS_DROP_BUILDING) != null) {
//                if (BuildingCommands.getMatcher(input, BuildingCommands.BUILDING_COMMANDS_FIND_X) == null ||
//                        BuildingCommands.getMatcher(input, BuildingCommands.BUILDING_COMMANDS_FIND_Y) == null ||
//                        BuildingCommands.getMatcher(input, BuildingCommands.BUILDING_COMMANDS_FIND_DROP_BUILDING_TYPE) == null) {
//                    System.out.println(BuildingCommands.INVALID_COMMAND.getName());
//                    continue;
//                }
//                Matcher matcherX = BuildingCommands.getMatcher(input, BuildingCommands.BUILDING_COMMANDS_FIND_X);
//                Matcher matcherY = BuildingCommands.getMatcher(input, BuildingCommands.BUILDING_COMMANDS_FIND_Y);
//                Matcher matcherType = BuildingCommands.getMatcher(input, BuildingCommands.BUILDING_COMMANDS_FIND_DROP_BUILDING_TYPE);
//                System.out.println(buildingController.dropBuilding(matcherX, matcherY, matcherType, scanner).getMessages());
//            } else if (BuildingCommands.getMatcher(input, BuildingCommands.BUILDING_COMMANDS_SELECT_BUILDING) != null) {
//                Matcher matcherX = BuildingCommands.getMatcher(input, BuildingCommands.BUILDING_COMMANDS_FIND_X);
//                Matcher matcherY = BuildingCommands.getMatcher(input, BuildingCommands.BUILDING_COMMANDS_FIND_Y);
//                if (matcherX != null && matcherY != null) {
//                    if (buildingController.selectBuilding(matcherX, matcherY).equals(BuildingMessages.SUCCESSFUL_SELECT)) {
//                        System.out.println(buildingController.selectBuilding(matcherX, matcherY).getMessages());
//                        SelectedBuildingMenu selectedBuildingMenu = new SelectedBuildingMenu();
//                        SelectedBuildingMenu.selectedBuilding = BuildingController.selectedBuilding;
//                        selectedBuildingMenu.run(scanner);
//                    } else {
//                        System.out.println(buildingController.selectBuilding(matcherX, matcherY).getMessages());
//                    }
//                }
//            } else if (input.equals("Logout")) {
//                System.out.println("welcome to game menu");
//                return;
//            } else {
//                System.out.println(BuildingCommands.INVALID_COMMAND.getName());
//            }
//
//        }
//    }
}

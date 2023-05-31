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
import view.OldView.SelectedBuildingMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class BuildingMenu{
    String input;


//    public void run(Scanner scanner) {
//        if (BuildingCommands.getMatcher(input, BuildingCommands.BUILDING_COMMANDS_DROP_BUILDING) != null) {
//            System.out.println();
//        } else if (BuildingCommands.getMatcher(input, BuildingCommands.BUILDING_COMMANDS_SELECT_BUILDING) != null) {
//            Matcher matcherX = BuildingCommands.getMatcher(input, BuildingCommands.BUILDING_COMMANDS_FIND_X);
//            Matcher matcherY = BuildingCommands.getMatcher(input, BuildingCommands.BUILDING_COMMANDS_FIND_Y);
//            if (matcherX != null && matcherY != null) {
//                if (buildingController.selectBuilding(matcherX, matcherY).equals(BuildingMessages.SUCCESSFUL_SELECT)) {
//                    System.out.println(buildingController.selectBuilding(matcherX, matcherY).getMessages());
//                    SelectedBuildingMenu selectedBuildingMenu = new SelectedBuildingMenu();
//                    SelectedBuildingMenu.selectedBuilding = BuildingController.selectedBuilding;
//                    selectedBuildingMenu.run(scanner);
//                } else {
//                    System.out.println(buildingController.selectBuilding(matcherX, matcherY).getMessages());
//                }
//            }
//        }
//
//
//
//    }
}

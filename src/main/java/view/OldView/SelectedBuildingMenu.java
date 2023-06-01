package view.OldView;

import controller.Building.BuildingController;
import controller.Building.SelectedBuildingController;
import controller.GameController;
import model.Building.Building;
import view.Commands.SelectedBuildingCommands;
import view.Messages.SelectedBuildingMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class SelectedBuildingMenu {
    public static Building selectedBuilding;
    public static int buildingXCoordinate;
    public static int buildingYCoordinate;
    String input;

    public void run(Scanner scanner) {

        SelectedBuildingController selectedBuildingController = new SelectedBuildingController();
        SelectedBuildingController.selectedBuilding = selectedBuilding;
        String buildingName = selectedBuilding.getNameEnum().getName();
//        if (buildingName.equals("Shop")) {
//            ShopMenu shopMenu = new ShopMenu();
//            shopMenu.start(scanner, (Shop) selectedBuilding);
//            return;
//        }
        System.out.println("Welcome to SelectedBuildingMenu!");
        while (true) {
            input = scanner.nextLine();
            if (SelectedBuildingCommands.getMatcher(input, SelectedBuildingCommands.SELECTED_BUILDING_COMMANDS_CREATE_UNIT) != null) {
                if (SelectedBuildingCommands.getMatcher(buildingName, SelectedBuildingCommands.SELECTED_BUILDING_CREATE_UNIT_BUILDINGS_NAME) != null) {
                    Matcher matcherType = SelectedBuildingCommands.getMatcher(input, SelectedBuildingCommands.SELECTED_BUILDING_COMMANDS_FIND_CREATE_UNIT_TYPE);
                    Matcher matcherCount = SelectedBuildingCommands.getMatcher(input, SelectedBuildingCommands.SELECTED_BUILDING_COMMANDS_FIND_COUNT);
                    if (matcherType != null && matcherCount != null) {
                        if (SelectedBuildingCommands.getMatcher(matcherType.group("type"),
                                SelectedBuildingCommands.SELECTED_BUILDING_COMMANDS_ALL_TROOPS_NAME) == null) {
                            System.out.println(SelectedBuildingMessages.INVALID_TROOP_NAME.getName());
                        } else if (SelectedBuildingCommands.getMatcher(matcherType.group("type"),
                                SelectedBuildingCommands.SELECTED_BUILDING_BARRACKS_TROOP_NAME_CHECK) != null && buildingName.equals("Barracks")) {
                            System.out.println(selectedBuildingController.Barracks(matcherType, matcherCount).getName());
                        } else if (SelectedBuildingCommands.getMatcher(matcherType.group("type"),
                                SelectedBuildingCommands.SELECTED_BUILDING_MERCENARY_TROOP_NAME_CHECK) != null && buildingName.equals("Mercenary")) {
                            System.out.println(selectedBuildingController.mercenary(matcherType, matcherCount).getName());
                        } else if (SelectedBuildingCommands.getMatcher(matcherType.group("type"),
                                SelectedBuildingCommands.SELECTED_BUILDING_SIEGE_TENT_TROOP_NAME_CHECK) != null && buildingName.equals("SiegeTent")) {
                            System.out.println(selectedBuildingController.siegeTent(matcherType, matcherCount).getName());
                        } else if (SelectedBuildingCommands.getMatcher(matcherType.group("type"),
                                SelectedBuildingCommands.SELECTED_BUILDING_CHURCH_TROOP_NAME_CHECK) != null
                                && (buildingName.equals("SmallChurch") | buildingName.equals("BigChurch"))) {
                            System.out.println(selectedBuildingController.church(matcherCount).getName());
                        } else if (SelectedBuildingCommands.getMatcher(matcherType.group("type"),
                                SelectedBuildingCommands.SELECTED_BUILDING_ENGINEER_GUILD_TROOP_NAME_CHECK) != null
                                && buildingName.equals("EngineerGuild")) {
                            System.out.println(selectedBuildingController.engineerGuild(matcherType, matcherCount).getName());
                        } else
                            System.out.println(SelectedBuildingMessages.WRONG_BUILDING_TO_CREATE_TROOP.getName());
                    } else {
                        System.out.println(SelectedBuildingMessages.INVALID_COMMAND.getName());
                    }
                } else {
                    System.out.println(SelectedBuildingMessages.BUILDING_CANT_CREATE_UNIT.getName());
                }
            } else if (SelectedBuildingCommands.getMatcher(input, SelectedBuildingCommands.SELECTED_BUILDING_TAX_RATE) != null) {
                if (buildingName.equals("SmallStoneGatehouse") | buildingName.equals("BigStoneGatehouse")) {
                    Matcher matcherTaxRate = SelectedBuildingCommands.getMatcher(input, SelectedBuildingCommands.SELECTED_BUILDING_TAX_RATE);
                    if (matcherTaxRate != null) {
                        System.out.println(selectedBuildingController.gatehouse(matcherTaxRate).getName());
                    } else {
                        System.out.println(SelectedBuildingMessages.INVALID_COMMAND.getName());
                    }
                } else {
                    System.out.println(SelectedBuildingMessages.WRONG_BUILDING_CHOSEN.getName());
                }
            } else if (SelectedBuildingCommands.getMatcher(input, SelectedBuildingCommands.SELECTED_BUILDING_DRAW_BRIDGE) != null) {
                if (buildingName.equals("DrawBridge")) {
                    Matcher matcherBridgeCondition = SelectedBuildingCommands.getMatcher(input, SelectedBuildingCommands.SELECTED_BUILDING_DRAW_BRIDGE);
                    if (matcherBridgeCondition != null) {
                        System.out.println(selectedBuildingController.drawBridge(matcherBridgeCondition).getName());
                    } else {
                        System.out.println(SelectedBuildingMessages.INVALID_COMMAND.getName());
                    }
                } else {
                    System.out.println(SelectedBuildingMessages.WRONG_BUILDING_CHOSEN.getName());
                }
            } else if (SelectedBuildingCommands.getMatcher(input, SelectedBuildingCommands.SELECTED_BUILDING_COMMANDS_REPAIR) != null) {
                if (!GameController.enemyInRange(buildingXCoordinate, buildingYCoordinate)) {
                    if (SelectedBuildingCommands.getMatcher(buildingName, SelectedBuildingCommands.REPAIR_SHOW_NAME) != null) {
                        System.out.println(buildingName);
                        System.out.println(BuildingController.repairBuilding(selectedBuilding).getMessages());
                    } else {
                        System.out.println(BuildingController.repairBuilding(selectedBuilding).getMessages());
                    }
                } else {
                    System.out.println(SelectedBuildingMessages.ENEMY_IN_RANGE.getName());
                }
            } else if (input.equals("Logout")) {
                System.out.println("logged out from the selectedBuilding menu");
                System.out.println("Welcome to BuildingMenu!");
                return;
            } else {
                System.out.println(SelectedBuildingMessages.INVALID_COMMAND.getName());
            }
        }
    }
}

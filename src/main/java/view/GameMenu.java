package view;

import controller.GameController;
import controller.NextTurnController;
import controller.ShowMapController;
import model.Building.Shop;
import model.Empire;
import model.Manage;
import model.Map;
import view.Commands.GameMenuCommands;
import view.Commands.MainMenuCommands;
import view.Messages.GameMenuMessages;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu {
    public static Empire currentEmpire;
    public static GameController gameController = new GameController();

    public void run(Scanner scanner) throws IOException, InterruptedException {
        Matcher x1;
        Matcher y1;
        Matcher x2;
        Matcher y2;
        Matcher type;
        Matcher enemy;
        Matcher direction;
        Matcher equipment;
        Matcher count;
        GameMenuMessages gameMenuMessages;
        while (true) {
            String command = scanner.nextLine();
            if (GameMenuCommands.getMatcher(command, GameMenuCommands.SELECT_UNITS) != null) {
                x1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_X);
                y1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_Y);
                gameMenuMessages = checkFormatOfSingleCoordinateCommands(x1, y1);
                if (gameMenuMessages.getMessages().equals(GameMenuMessages.VALID_COMMAND.getMessages())) {
                    System.out.println(gameController.selectUnit(x1, y1).getMessages());
                } else System.out.println(gameMenuMessages.getMessages());
            }  else if (GameMenuCommands.getMatcher(command, GameMenuCommands.MOVE_UNITS) != null) {
                x1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_X);
                y1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_Y);
                gameMenuMessages = checkFormatOfSingleCoordinateCommands(x1, y1);
                if (gameMenuMessages.getMessages().equals(GameMenuMessages.VALID_COMMAND.getMessages())) {
                    int xCoordinate = Integer.parseInt(x1.group("x"));
                    int yCoordinate = Integer.parseInt(y1.group("y"));
                    System.out.println(gameController.moveUnit(xCoordinate, yCoordinate).getMessages());
                } else System.out.println(gameMenuMessages.getMessages());
            } else if (GameMenuCommands.getMatcher(command, GameMenuCommands.PATROL_UNIT) != null) {
                x1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_X1);
                y1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_Y1);
                x2 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_X2);
                y2 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_Y2);
                gameMenuMessages = checkFormatOfDoubleCoordinateCommands(x1, y1, x2, y2);
                if (gameMenuMessages.getMessages().equals(GameMenuMessages.VALID_COMMAND.getMessages())) {
                    gameController.patrolUnit(x1, y1, x2, y2);
                } else System.out.println(gameMenuMessages.getMessages());
            } else if (GameMenuCommands.getMatcher(command, GameMenuCommands.STOP_PATROL_UNIT) != null) {
                x1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_X);
                y1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_Y);
                gameMenuMessages = checkFormatOfSingleCoordinateCommands(x1, y1);
                if (gameMenuMessages.getMessages().equals(GameMenuMessages.VALID_COMMAND.getMessages())) {
                    int xCoordinate = Integer.parseInt(x1.group("x"));
                    int yCoordinate = Integer.parseInt(y1.group("y"));
                    System.out.println(gameController.stopPatrols(xCoordinate, yCoordinate).getMessages());
                } else System.out.println(gameMenuMessages.getMessages());
            } else if (GameMenuCommands.getMatcher(command, GameMenuCommands.SET_UNIT) != null) {
                x1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_X);
                y1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_Y);
                type = GameMenuCommands.getMatcher(command, GameMenuCommands.TYPE_OF_UNIT);
                gameMenuMessages = checkFormatOfCoordinateCommandsWithType(x1, y1, type);
                if (gameMenuMessages.getMessages().equals(GameMenuMessages.VALID_COMMAND.getMessages())) {
                    System.out.println(gameController.setFormOfUnit(x1, y1, type).getMessages());
                } else System.out.println(gameMenuMessages.getMessages());
            } else if (GameMenuCommands.getMatcher(command, GameMenuCommands.ATTACK) != null) {
                enemy = GameMenuCommands.getMatcher(command, GameMenuCommands.ATTACK_BY_SOLDIERS);
                if (enemy != null) {
                    System.out.println(gameController.readyToAttack(enemy).getMessages());
                } else {
                    x1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_X);
                    y1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_Y);
                    gameMenuMessages = checkFormatOfSingleCoordinateCommands(x1, y1);
                    if (gameMenuMessages.getMessages().equals(GameMenuMessages.VALID_COMMAND.getMessages())) {
                        System.out.println(gameController.attackAllSelectedArchers(x1, y1).getMessages());
                    } else System.out.println(gameMenuMessages.getMessages());
                }
            } else if (GameMenuCommands.getMatcher(command, GameMenuCommands.POUR_OIL) != null) {
                direction = GameMenuCommands.getMatcher(command, GameMenuCommands.DIRECTION);
                if (direction != null) {
                    System.out.println(gameController.pourOil(direction.group("direction")));
                } else System.out.println(GameMenuMessages.EMPTY_DIRECTION_FIELD.getMessages());
            } else if (GameMenuCommands.getMatcher(command, GameMenuCommands.DIG_TUNNEL) != null) {
                x1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_X);
                y1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_Y);
                gameMenuMessages = checkFormatOfSingleCoordinateCommands(x1, y1);
                if (gameMenuMessages.getMessages().equals(GameMenuMessages.VALID_COMMAND.getMessages())) {
                    System.out.println(gameController.digTunnel(x1, y1).getMessages());
                } else System.out.println(gameMenuMessages.getMessages());
            } else if (GameMenuCommands.getMatcher(command, GameMenuCommands.BUILD_EQUIPMENT) != null) {
                equipment = GameMenuCommands.getMatcher(command, GameMenuCommands.EQUIPMENT);
                if (equipment != null) {
                    System.out.println(gameController.buildEquipment(equipment).getMessages());
                } else System.out.println(GameMenuMessages.EMPTY_EQUIPMENT_FIELD.getMessages());
            } else if (GameMenuCommands.getMatcher(command, GameMenuCommands.DISBAND_UNIT) != null) {
                System.out.println(gameController.disbandUnit().getMessages());
            } else if (GameMenuCommands.getMatcher(command, GameMenuCommands.DROP_UNIT) != null) {
                x1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_X);
                y1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_Y);
                type = GameMenuCommands.getMatcher(command, GameMenuCommands.TYPE);
                count = GameMenuCommands.getMatcher(command, GameMenuCommands.COUNT);
                gameMenuMessages = checkFormatOfCoordinateCommandsWithTypeAndCount(x1, y1, type, count);
                if (gameMenuMessages.getMessages().equals(GameMenuMessages.VALID_COMMAND.getMessages())) {
                    System.out.println(gameController.dropUnit(x1, y1, count, type).getMessages());
                } else System.out.println(gameMenuMessages.getMessages());
            } else if (GameMenuCommands.getMatcher(command, GameMenuCommands.TURN_PITCH_DITCH_ON) != null) {
                x1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_X);
                y1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_Y);
                gameMenuMessages = checkFormatOfSingleCoordinateCommands(x1, y1);
                if (gameMenuMessages.getMessages().equals(GameMenuMessages.VALID_COMMAND.getMessages())) {
                    System.out.println(gameController.PitchDitchHauntsEnemy(x1, y1).getMessages());
                } else System.out.println(gameMenuMessages.getMessages());
            } else if (GameMenuCommands.getMatcher(command, GameMenuCommands.CONQUER_GATES) != null) {
                x1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_X);
                y1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_Y);
                gameMenuMessages = checkFormatOfSingleCoordinateCommands(x1, y1);
                if (gameMenuMessages.getMessages().equals(GameMenuMessages.VALID_COMMAND.getMessages())) {
                    System.out.println(gameController.conquerGates(x1, y1).getMessages());
                } else System.out.println(gameMenuMessages.getMessages());
            } else if (GameMenuCommands.getMatcher(command, GameMenuCommands.ATTACK_BY_BATTERING_RAM) != null) {
                x1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_X);
                y1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_Y);
                gameMenuMessages = checkFormatOfSingleCoordinateCommands(x1, y1);
                if (gameMenuMessages.getMessages().equals(GameMenuMessages.VALID_COMMAND.getMessages())) {
                    System.out.println(gameController.damageByBatteringRam(x1, y1).getMessages());
                } else System.out.println(gameMenuMessages.getMessages());
            } else if (GameMenuCommands.getMatcher(command, GameMenuCommands.DIG_DITCH) != null) {
                x1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_X);
                y1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_Y);
                gameMenuMessages = checkFormatOfSingleCoordinateCommands(x1, y1);
                if (gameMenuMessages.getMessages().equals(GameMenuMessages.VALID_COMMAND.getMessages())) {
                    System.out.println(gameController.digDitch(x1, y1).getMessages());
                } else System.out.println(gameMenuMessages.getMessages());
            } else if (GameMenuCommands.getMatcher(command, GameMenuCommands.REMOVE_DITCH) != null) {
                x1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_X);
                y1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_Y);
                gameMenuMessages = checkFormatOfSingleCoordinateCommands(x1, y1);
                if (gameMenuMessages.getMessages().equals(GameMenuMessages.VALID_COMMAND.getMessages())) {
                    System.out.println(gameController.removePitchDitch(x1, y1).getMessages());
                } else System.out.println(gameMenuMessages.getMessages());
            } else if (GameMenuCommands.getMatcher(command, GameMenuCommands.FILL_DITCH) != null) {
                x1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_X);
                y1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_Y);
                gameMenuMessages = checkFormatOfSingleCoordinateCommands(x1, y1);
                if (gameMenuMessages.getMessages().equals(GameMenuMessages.VALID_COMMAND.getMessages())) {
                    System.out.println(gameController.fillDitch(x1, y1).getMessages());
                } else System.out.println(gameMenuMessages.getMessages());
            } else if (GameMenuCommands.getMatcher(command, GameMenuCommands.MOVE_BY_SIEGE_TOWER) != null) {
                x1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_X);
                y1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_Y);
                gameMenuMessages = checkFormatOfSingleCoordinateCommands(x1, y1);
                if (gameMenuMessages.getMessages().equals(GameMenuMessages.VALID_COMMAND.getMessages())) {
                    System.out.println(gameController.siegeTowersAction(x1, y1).getMessages());
                } else System.out.println(gameMenuMessages.getMessages());
            } else if (GameMenuCommands.getMatcher(command, GameMenuCommands.ENTER_SHOP_MENU) != null) {
                x1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_X);
                y1 = GameMenuCommands.getMatcher(command, GameMenuCommands.COORDINATE_Y);
                if (gameController.findShop(x1, y1)) {
                    int x = Integer.parseInt(x1.group("x"));
                    int y = Integer.parseInt(y1.group("y"));
                    Shop shop = (Shop) Map.getBuildingMap()[x][y].get(0);
                    ShopMenu shopMenu = new ShopMenu();
                    shopMenu.run(scanner, shop);
                } else System.out.println(GameMenuMessages.IMPROPER_LOCATION.getMessages());
            } else if(GameMenuCommands.getMatcher(command, GameMenuCommands.ENTER_TO_MAP) != null){
                System.out.println("Welcome to map menu");
                ShowMapInGameMenu.run(scanner);
            }
            else if (GameMenuCommands.getMatcher(command, GameMenuCommands.ENTER_TRADE_MENU) != null) {
                TradeMenu tradeMenu = new TradeMenu();
                tradeMenu.run(scanner);
            } else if (GameMenuCommands.getMatcher(command, GameMenuCommands.ENTER_BUILDING_MENU) != null) {
                BuildingMenu buildingMenu = new BuildingMenu();
                buildingMenu.run(scanner);
            } else if (GameMenuCommands.getMatcher(command, GameMenuCommands.ENTER_EMPIRE_MENU) != null) {
                EmpireMenu empire = new EmpireMenu();
                empire.run(scanner);
            } else if (GameMenuCommands.getMatcher(command, GameMenuCommands.LOGOUT) != null) {
                Manage.allEmpires.remove(currentEmpire);
                GameController.removeEmpireTroopsFromGame(currentEmpire);
                NextTurnController.index--;
                return;
            } else if (GameMenuCommands.getMatcher(command, GameMenuCommands.NEXT_TURN) != null) {
                return;
            } else System.out.println(GameMenuMessages.INVALID_COMMAND.getMessages());
        }
    }

    public GameMenuMessages checkFormatOfSingleCoordinateCommands(Matcher x1, Matcher y1) {
        if (x1 != null && y1 != null) {
            return GameMenuMessages.VALID_COMMAND;
        }
        return GameMenuMessages.EMPTY_COORDINATE_FIELD;
    }

    public GameMenuMessages checkFormatOfDoubleCoordinateCommands(Matcher x1, Matcher y1, Matcher x2, Matcher y2) {
        if (x1 != null && y1 != null && x2 != null && y2 != null) {
            return GameMenuMessages.VALID_COMMAND;
        }
        return GameMenuMessages.EMPTY_COORDINATE_FIELD;
    }

    public GameMenuMessages checkFormatOfCoordinateCommandsWithType(Matcher x1, Matcher y1, Matcher type) {
        if (x1 != null && y1 != null && type != null) {
            return GameMenuMessages.VALID_COMMAND;
        }
        return GameMenuMessages.EMPTY_COORDINATE_FIELD;
    }

    public GameMenuMessages checkFormatOfCoordinateCommandsWithTypeAndCount(Matcher x1, Matcher y1, Matcher type, Matcher count) {
        if (x1 != null && y1 != null && type != null && count != null) {
            return GameMenuMessages.VALID_COMMAND;
        }
        return GameMenuMessages.EMPTY_COORDINATE_FIELD;
    }



}

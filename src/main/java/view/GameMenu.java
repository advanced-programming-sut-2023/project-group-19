package view;

import controller.GameController;
import model.Empire;
import model.User;
import view.Commands.GameMenuCommands;
import view.Messages.GameMenuMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu {
    public static User currentUser;
    public static Empire currentEmpire;
    public void run(Scanner scanner){
        Matcher x1;
        Matcher y1;
        Matcher x2;
        Matcher y2;
        Matcher type;
        Matcher enemy;
        Matcher direction;
        Matcher equipment;
        Matcher count;
        GameController gameController = new GameController();
        GameMenuMessages gameMenuMessages;
        while(true){
            String command = scanner.nextLine();
            if (GameMenuCommands.getMatcher(command , GameMenuCommands.SELECT_UNITS) != null){
                x1 = GameMenuCommands.getMatcher(command , GameMenuCommands.COORDINATE_X);
                y1 = GameMenuCommands.getMatcher(command , GameMenuCommands.COORDINATE_Y);
                gameMenuMessages = checkFormatOfSingleCoordinateCommands(x1 , y1);
                if (gameMenuMessages.getMessages().equals(GameMenuMessages.VALID_COMMAND.getMessages())){
                    gameController.selectUnit(x1 , y1);
                }else System.out.println(gameMenuMessages.getMessages());
            }else if(GameMenuCommands.getMatcher(command, GameMenuCommands.DISBAND_UNIT) != null){
                gameMenuMessages = gameController.disbandUnit();
                System.out.println(gameMenuMessages.getMessages());
            }
            else if (GameMenuCommands.getMatcher(command , GameMenuCommands.MOVE_UNITS) != null) {
                x1 = GameMenuCommands.getMatcher(command , GameMenuCommands.COORDINATE_X);
                y1 = GameMenuCommands.getMatcher(command , GameMenuCommands.COORDINATE_Y);
                gameMenuMessages = checkFormatOfSingleCoordinateCommands(x1 , y1);
                if (gameMenuMessages.getMessages().equals(GameMenuMessages.VALID_COMMAND.getMessages())){
                    gameController.moveUnit(Integer.parseInt(x1.group("x")) , Integer.parseInt(y1.group("y")));
                }else System.out.println(gameMenuMessages.getMessages());
            } else if (GameMenuCommands.getMatcher(command , GameMenuCommands.PATROL_UNIT) != null) {
                x1 = GameMenuCommands.getMatcher(command , GameMenuCommands.COORDINATE_X1);
                y1 = GameMenuCommands.getMatcher(command , GameMenuCommands.COORDINATE_Y1);
                x2 = GameMenuCommands.getMatcher(command , GameMenuCommands.COORDINATE_X2);
                y2 = GameMenuCommands.getMatcher(command , GameMenuCommands.COORDINATE_Y2);
                gameMenuMessages = checkFormatOfDoubleCoordinateCommands(x1 , y1 , x2 , y2);
                if (gameMenuMessages.getMessages().equals(GameMenuMessages.VALID_COMMAND.getMessages())){
                    //TODO : Armin's function
                }else System.out.println(gameMenuMessages.getMessages());
            } else if (GameMenuCommands.getMatcher(command , GameMenuCommands.SET_UNIT) != null) {
                x1 = GameMenuCommands.getMatcher(command , GameMenuCommands.COORDINATE_X1);
                y1 = GameMenuCommands.getMatcher(command , GameMenuCommands.COORDINATE_Y1);
                type = GameMenuCommands.getMatcher(command , GameMenuCommands.TYPE_OF_UNIT);
                gameMenuMessages = checkFormatOfCoordinateCommandsWithType(x1 , y1 ,type);
                if (gameMenuMessages.getMessages().equals(GameMenuMessages.VALID_COMMAND.getMessages())){
                    //TODO : Armin's function
                }else System.out.println(gameMenuMessages.getMessages());
            } else if (GameMenuCommands.getMatcher(command , GameMenuCommands.ATTACK) != null) {
                enemy = GameMenuCommands.getMatcher(command , GameMenuCommands.ATTACK_BY_SOLDIERS);
                if (enemy != null){
                    //TODO : Attack function
                }else {
                    x1 = GameMenuCommands.getMatcher(command , GameMenuCommands.COORDINATE_X1);
                    y1 = GameMenuCommands.getMatcher(command , GameMenuCommands.COORDINATE_Y1);
                    gameMenuMessages = checkFormatOfSingleCoordinateCommands(x1 , y1);
                    if (gameMenuMessages.getMessages().equals(GameMenuMessages.VALID_COMMAND.getMessages())){
                        int x = Integer.parseInt(x1.group("x"));
                        int y = Integer.parseInt(x1.group("y"));
                        GameMenuMessages messages = gameController.attackAllSelectedArchers(x,y);
                        System.out.println(messages.getMessages());
                    }else System.out.println(gameMenuMessages.getMessages());
                }
            } else if (GameMenuCommands.getMatcher(command , GameMenuCommands.POUR_OIL) != null) {
                direction = GameMenuCommands.getMatcher(command , GameMenuCommands.DIRECTION);
                if (direction != null){
                    //TODO : Armin's function
                }else System.out.println(GameMenuMessages.EMPTY_DIRECTION_FIELD.getMessages());
            } else if (GameMenuCommands.getMatcher(command , GameMenuCommands.DIG_TUNNEL) != null) {
                x1 = GameMenuCommands.getMatcher(command , GameMenuCommands.COORDINATE_X1);
                y1 = GameMenuCommands.getMatcher(command , GameMenuCommands.COORDINATE_Y1);
                gameMenuMessages = checkFormatOfSingleCoordinateCommands(x1 , y1);
                if (gameMenuMessages.getMessages().equals(GameMenuMessages.VALID_COMMAND.getMessages())){
                    //TODO : Armin's function
                }else System.out.println(gameMenuMessages.getMessages());
            } else if (GameMenuCommands.getMatcher(command , GameMenuCommands.BUILD_EQUIPMENT) != null) {
                equipment = GameMenuCommands.getMatcher(command , GameMenuCommands.EQUIPMENT);
                if (equipment != null){
                    //TODO : function?
                }else System.out.println(GameMenuMessages.EMPTY_EQUIPMENT_FIELD.getMessages());
            } else if (GameMenuCommands.getMatcher(command , GameMenuCommands.DISBAND_UNIT) != null) {
                //TODO : Armin's function
            } else if (GameMenuCommands.getMatcher(command , GameMenuCommands.ENTER_MAP_MENU) != null) {
                CreateMapMenu createMapMenu = new CreateMapMenu();
                //TODO: Merge with Main
            } else if (GameMenuCommands.getMatcher(command , GameMenuCommands.DROP_BUILDING) != null) {
                x1 = GameMenuCommands.getMatcher(command , GameMenuCommands.COORDINATE_X1);
                y1 = GameMenuCommands.getMatcher(command , GameMenuCommands.COORDINATE_Y1);
                type = GameMenuCommands.getMatcher(command , GameMenuCommands.TYPE);
                gameMenuMessages = checkFormatOfCoordinateCommandsWithType(x1 , y1 , type);
                if (gameMenuMessages.getMessages().equals(GameMenuMessages.VALID_COMMAND.getMessages())){
                    //TODO : Armin's function
                }else System.out.println(gameMenuMessages.getMessages());
            } else if (GameMenuCommands.getMatcher(command, GameMenuCommands.DROP_UNIT) != null) {
                x1 = GameMenuCommands.getMatcher(command , GameMenuCommands.COORDINATE_X1);
                y1 = GameMenuCommands.getMatcher(command , GameMenuCommands.COORDINATE_Y1);
                type = GameMenuCommands.getMatcher(command , GameMenuCommands.TYPE);
                count = GameMenuCommands.getMatcher(command , GameMenuCommands.COUNT);
                gameMenuMessages = checkFormatOfCoordinateCommandsWithTypeAndCount(x1 , y1 , type , count);
                if (gameMenuMessages.getMessages().equals(GameMenuMessages.VALID_COMMAND.getMessages())){
                    //TODO : Armin's function
                }else System.out.println(gameMenuMessages.getMessages());
            } else if (GameMenuCommands.getMatcher(command, GameMenuCommands.ENTER_SHOP_MENU) != null) {
                //TODO : Specify the coordinate of shop
            } else if (GameMenuCommands.getMatcher(command , GameMenuCommands.ENTER_TRADE_MENU) != null) {
                TradeMenu tradeMenu = new TradeMenu();
                tradeMenu.run(scanner);
            } else if (GameMenuCommands.getMatcher(command , GameMenuCommands.ENTER_BUILDING_MENU) != null) {
                BuildingMenu buildingMenu = new BuildingMenu();
                buildingMenu.run(scanner);
            } else if (GameMenuCommands.getMatcher(command , GameMenuCommands.LOGOUT) != null) {
                break;
            } else System.out.println(GameMenuMessages.INVALID_COMMAND.getMessages());
        }
    }

    public GameMenuMessages checkFormatOfSingleCoordinateCommands(Matcher x1 , Matcher y1){
        if (x1 != null && y1 != null){
            return GameMenuMessages.VALID_COMMAND;
        }return GameMenuMessages.EMPTY_COORDINATE_FIELD;
    }

    public GameMenuMessages checkFormatOfDoubleCoordinateCommands(Matcher x1 , Matcher y1 , Matcher x2 , Matcher y2){
        if (x1 != null && y1 != null && x2 != null && y2 != null){
            return GameMenuMessages.VALID_COMMAND;
        }return GameMenuMessages.EMPTY_COORDINATE_FIELD;
    }

    public GameMenuMessages checkFormatOfCoordinateCommandsWithType(Matcher x1 , Matcher y1 , Matcher type){
        if (x1 != null && y1 != null && type != null){
            return GameMenuMessages.VALID_COMMAND;
        }return GameMenuMessages.EMPTY_COORDINATE_FIELD;
    }

    public GameMenuMessages checkFormatOfCoordinateCommandsWithTypeAndCount(Matcher x1 , Matcher y1 , Matcher type , Matcher count){
        if (x1 != null && y1 != null && type != null && count != null){
            return GameMenuMessages.VALID_COMMAND;
        }return GameMenuMessages.EMPTY_COORDINATE_FIELD;
    }
}

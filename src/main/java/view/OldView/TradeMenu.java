package view.OldView;

import controller.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.Commands.TradeMenuCommands;
import view.Controllers.TradeMenuController;
import view.Main;
import view.Messages.TradeMenuMessages;

import java.util.Scanner;
import java.util.regex.Matcher;


public class TradeMenu extends Application {

//    public void run(Scanner scanner) {
//        String command;
//        Matcher id;
//        Matcher price;
//        Matcher message;
//        Matcher resourceType;
//        Matcher resourceAmount;
//        Matcher value;
//        TradeMenuMessages commandValidation;
//        System.out.println("Welcome to TradeMenu!");
//        TradeController tradeController = new TradeController();
//        tradeController.showAllEmpires();
//        tradeController.notification();
//        while (true) {
//            command = scanner.nextLine();
//            if ((id = TradeMenuCommands.getMatcher(command, TradeMenuCommands.SELECT_EMPIRE)) != null) {
//                System.out.println(tradeController.setSelectedEmpire(id).getMessages());
//            } else if (TradeMenuCommands.getMatcher(command, TradeMenuCommands.SHOW_TRADE_LIST) != null) {
//                tradeController.showTradeList();
//            } else if (TradeMenuCommands.getMatcher(command, TradeMenuCommands.TRADE_ACCEPTED) != null) {
//                id = TradeMenuCommands.getMatcher(command, TradeMenuCommands.TRADE_ACCEPTED_ID_CHECK);
//                message = TradeMenuCommands.getMatcher(command, TradeMenuCommands.TRADE_ACCEPTED_MESSAGE_CHECK);
//                commandValidation = checkTradeAcceptanceFormat(id, message);
//                if (commandValidation.getMessages().equals(TradeMenuMessages.VALID_COMMAND.getMessages())) {
//                    System.out.println(tradeController.tradeAcceptance(id, message).getMessages());
//                } else System.out.println(commandValidation.getMessages());
//            } else if (TradeMenuCommands.getMatcher(command, TradeMenuCommands.SHOW_TRADE_HISTORY) != null) {
//                tradeController.showTradeHistory();
//            } else if (TradeMenuCommands.getMatcher(command, TradeMenuCommands.TRADE) != null) {
//                resourceType = TradeMenuCommands.getMatcher(command, TradeMenuCommands.SEND_REQUEST_RESOURCE_TYPE_CHECK);
//                resourceAmount = TradeMenuCommands.getMatcher(command, TradeMenuCommands.SEND_REQUEST_RESOURCE_AMOUNT_CHECK);
//                price = TradeMenuCommands.getMatcher(command, TradeMenuCommands.SEND_REQUEST_RESOURCE_PRICE_CHECK);
//                value = TradeMenuCommands.getMatcher(command,TradeMenuCommands.SEND_REQUEST_RESOURCE_PRICE_CHECK_VALUE);
//                message = TradeMenuCommands.getMatcher(command, TradeMenuCommands.SEND_REQUEST_RESOURCE_MESSAGE_CHECK);
//                commandValidation = checkTradeCommandFormat(resourceType, resourceAmount, price, message,value);
//                if (commandValidation.getMessages().equals(TradeMenuMessages.VALID_COMMAND.getMessages())) {
//                    System.out.println(tradeController.sendRequest(resourceType, resourceAmount, price, message,value).getMessages());
//                }else System.out.println(TradeMenuMessages.INVALID_COMMAND.getMessages());
//            } else if (TradeMenuCommands.getMatcher(command, TradeMenuCommands.LOGOUT) != null) {
//                break;
//            } else System.out.println(TradeMenuMessages.INVALID_COMMAND.getMessages());
//        }
//    }

    public static TradeMenuMessages checkTradeCommandFormat(Matcher resourceType, Matcher resourceAmount, Matcher price, Matcher message,Matcher value) {
        if (resourceType != null) {
            if (resourceAmount != null) {
                if (price != null && value != null) {
                    if (message != null) {
                        return TradeMenuMessages.VALID_COMMAND;
                    } else return TradeMenuMessages.MESSAGE_EMPTY_FIELD_IN_ENTRY;
                } else return TradeMenuMessages.PRICE_EMPTY_FIELD_IN_ENTRY;
            } else return TradeMenuMessages.RESOURCE_AMOUNT_EMPTY_FIELD_IN_ENTRY;
        }
        return TradeMenuMessages.RESOURCE_TYPE_EMPTY_FIELD_IN_ENTRY;
    }

    public static TradeMenuMessages checkTradeAcceptanceFormat(Matcher id, Matcher message) {
        if (id != null) {
            if (message != null) {
                return TradeMenuMessages.VALID_COMMAND;
            } else return TradeMenuMessages.MESSAGE_EMPTY_FIELD_IN_ENTRY;
        }
        return TradeMenuMessages.ID_EMPTY_FIELD_IN_ENTRY;
    }


    @Override
    public void start(Stage stage) throws Exception {
        TradeMenuController tradeController = new TradeMenuController();
        Main.stage = stage;
        Pane pane = new Pane();
        pane.setPrefSize(1000,900);
        pane.setMaxSize(1000,900);
        tradeController.designTradeMenu(tradeController,pane,stage);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
    }
}

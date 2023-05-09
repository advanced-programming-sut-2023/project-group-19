package view;

import controller.*;
import view.Commands.TradeMenuCommands;
import view.Messages.TradeMenuMessages;

import javax.print.DocFlavor;
import java.util.Scanner;
import java.util.regex.Matcher;


public class TradeMenu {

    public void run(Scanner scanner) {
        String command;
        Matcher id;
        Matcher price;
        Matcher message;
        Matcher resourceType;
        Matcher resourceAmount;
        TradeMenuMessages commandValidation;
        System.out.println("Welcome to TradeMenu!");
        TradeController tradeController = new TradeController();
        tradeController.showAllEmpires();
        while (true) {
            command = scanner.nextLine();
            if (TradeMenuCommands.getMatcher(command, TradeMenuCommands.TRADE) != null) {
                resourceType = TradeMenuCommands.getMatcher(command, TradeMenuCommands.SEND_REQUEST_RESOURCE_TYPE_CHECK);
                resourceAmount = TradeMenuCommands.getMatcher(command, TradeMenuCommands.SEND_REQUEST_RESOURCE_AMOUNT_CHECK);
                price = TradeMenuCommands.getMatcher(command, TradeMenuCommands.SEND_REQUEST_RESOURCE_PRICE_CHECK);
                message = TradeMenuCommands.getMatcher(command, TradeMenuCommands.SEND_REQUEST_RESOURCE_MESSAGE_CHECK);
                commandValidation = checkTradeCommandFormat(resourceType, resourceAmount, price, message);
                if (commandValidation.getMessages().equals(TradeMenuMessages.SUCCESS.getMessages())) {
                    tradeController.sendRequest(resourceType, resourceAmount, price, message);
                } else System.out.println(commandValidation.getMessages());
            } else if (TradeMenuCommands.getMatcher(command, TradeMenuCommands.SHOW_TRADE_LIST) != null) {
                tradeController.showTradeList();
            } else if (TradeMenuCommands.getMatcher(command, TradeMenuCommands.TRADE_ACCEPTED) != null) {
                id = TradeMenuCommands.getMatcher(command, TradeMenuCommands.TRADE_ACCEPTED_ID_CHECK);
                message = TradeMenuCommands.getMatcher(command, TradeMenuCommands.TRADE_ACCEPTED_MESSAGE_CHECK);
                commandValidation = checkTradeAcceptanceFormat(id, message);
                if (commandValidation.getMessages().equals(TradeMenuMessages.VALID_COMMAND.getMessages())) {
                    tradeController.tradeAcceptance(id, message);
                } else System.out.println(commandValidation.getMessages());
            } else if (TradeMenuCommands.getMatcher(command, TradeMenuCommands.SHOW_TRADE_HISTORY) != null) {
                tradeController.showTradeHistory();
            } else if (TradeMenuCommands.getMatcher(command, TradeMenuCommands.LOGOUT) != null) {
                break;
            } else System.out.println(TradeMenuMessages.INVALID_COMMAND.getMessages());
        }
    }

    public static TradeMenuMessages checkTradeCommandFormat(Matcher resourceType, Matcher resourceAmount, Matcher price, Matcher message) {
        if (resourceType != null) {
            if (resourceAmount != null) {
                if (price != null) {
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
}

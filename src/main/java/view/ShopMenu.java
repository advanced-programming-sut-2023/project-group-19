package view;

import controller.*;
import model.Building.Shop;
import view.Commands.ShopMenuCommands;
import view.Messages.ShopMenuMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopMenu {
    public static Shop currentShop;

    public void run(Scanner scanner  , Shop shop) {
        String command;
        Matcher itemName;
        Matcher itemAmount;
        ShopMenuMessages commandValidation;
        ShopController shopController = new ShopController();
        currentShop = shop;
        System.out.println("Welcome to ShopMenu!");
        while (true) {
            command = scanner.nextLine();
            if (ShopMenuCommands.getMatcher(command, ShopMenuCommands.SHOW_PRICE_LIST) != null) {
                shopController.showPriceList();
            } else if (ShopMenuCommands.getMatcher(command, ShopMenuCommands.BUY) != null) {
                itemName = ShopMenuCommands.getMatcher(command, ShopMenuCommands.ITEM_NAME_CHECK);
                itemAmount = ShopMenuCommands.getMatcher(command, ShopMenuCommands.ITEM_AMOUNT_CHECK);
                commandValidation = checkCommandFormat(itemName, itemAmount);
                if (commandValidation.getMessages().equals(ShopMenuMessages.VALID_COMMAND.getMessages())) {
                    shopController.buyItem(itemName, itemAmount, scanner);
                } else System.out.println(commandValidation.getMessages());
            } else if (ShopMenuCommands.getMatcher(command, ShopMenuCommands.SELL) != null) {
                itemName = ShopMenuCommands.getMatcher(command, ShopMenuCommands.ITEM_NAME_CHECK);
                itemAmount = ShopMenuCommands.getMatcher(command, ShopMenuCommands.ITEM_AMOUNT_CHECK);
                commandValidation = checkCommandFormat(itemName, itemAmount);
                if (commandValidation.getMessages().equals(ShopMenuMessages.VALID_COMMAND.getMessages())) {
                    shopController.sellItem(itemName, itemAmount, scanner);
                } else System.out.println(commandValidation.getMessages());
            } else if (ShopMenuCommands.getMatcher(command, ShopMenuCommands.LOGOUT) != null) {
                break;
            } else System.out.println(ShopMenuMessages.INVALID_COMMAND.getMessages());
        }
    }

    public static ShopMenuMessages checkCommandFormat(Matcher itemName, Matcher itemAmount) {
        if (itemName != null) {
            if (itemAmount != null) {
                return ShopMenuMessages.VALID_COMMAND;
            } else return ShopMenuMessages.ITEM_AMOUNT_EMPTY_FIELD;
        }
        return ShopMenuMessages.ITEM_NAME_EMPTY_FIELD;
    }
}

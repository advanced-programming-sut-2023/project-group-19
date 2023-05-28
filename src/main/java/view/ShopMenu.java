package view;

import controller.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Building.Shop;
import view.Commands.ShopMenuCommands;
import view.Controllers.ShopMenuController;
import view.Messages.ShopMenuMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopMenu extends Application {
    public static Shop currentShop;

//    public void run(Scanner scanner, Shop shop) {
//        String command;
//        Matcher itemName;
//        Matcher itemAmount;
//        ShopMenuMessages commandValidation;
//        currentShop = shop;
//
//        System.out.println("Welcome to ShopMenu!");
//        while (true) {
//            command = scanner.nextLine();
//            if (ShopMenuCommands.getMatcher(command, ShopMenuCommands.SHOW_PRICE_LIST) != null) {
//
//            } else if (ShopMenuCommands.getMatcher(command, ShopMenuCommands.BUY) != null) {
//                itemName = ShopMenuCommands.getMatcher(command, ShopMenuCommands.ITEM_NAME_CHECK);
//                itemAmount = ShopMenuCommands.getMatcher(command, ShopMenuCommands.ITEM_AMOUNT_CHECK);
//                commandValidation = checkCommandFormat(itemName, itemAmount);
//                if (commandValidation.getMessages().equals(ShopMenuMessages.VALID_COMMAND.getMessages())) {
//                    System.out.println(shopController.buyItem(itemName, itemAmount, scanner).getMessages());
//                } else System.out.println(commandValidation.getMessages());
//            } else if (ShopMenuCommands.getMatcher(command, ShopMenuCommands.SELL) != null) {
//                itemName = ShopMenuCommands.getMatcher(command, ShopMenuCommands.ITEM_NAME_CHECK);
//                itemAmount = ShopMenuCommands.getMatcher(command, ShopMenuCommands.ITEM_AMOUNT_CHECK);
//                commandValidation = checkCommandFormat(itemName, itemAmount);
//                if (commandValidation.getMessages().equals(ShopMenuMessages.VALID_COMMAND.getMessages())) {
//                    System.out.println(shopController.sellItem(itemName, itemAmount, scanner).getMessages());
//                } else System.out.println(commandValidation.getMessages());
//            } else if (ShopMenuCommands.getMatcher(command, ShopMenuCommands.LOGOUT) != null) {
//                System.out.println("welcome to Building menu");
//                break;
//            } else System.out.println(ShopMenuMessages.INVALID_COMMAND.getMessages());
//        }
//    }

    public static ShopMenuMessages checkCommandFormat(Matcher itemName, Matcher itemAmount) {
        if (itemName != null) {
            if (itemAmount != null) {
                return ShopMenuMessages.VALID_COMMAND;
            } else return ShopMenuMessages.ITEM_AMOUNT_EMPTY_FIELD;
        }
        return ShopMenuMessages.ITEM_NAME_EMPTY_FIELD;
    }

    @Override
    public void start(Stage stage) throws Exception {
        ShopController shopController = new ShopController();
        Main.stage = stage;
        Pane pane = new Pane();
        designShopMenu(shopController,pane);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public void designShopMenu(ShopController shopController , Pane pane){
        ShopMenuController shopMenuController = new ShopMenuController();
        shopMenuController.showPriceList(shopController.showPriceList(),pane);
        shopMenuController.setButtons();
    }
}

package view;

import controller.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Building.Shop;
import model.Map;
import view.Commands.ShopMenuCommands;
import view.Controllers.ShopMenuController;
import view.Messages.ShopMenuMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopMenu extends Application {
    public static Shop currentShop;
    @Override
    public void start(Stage stage) throws Exception {
        ShopController shopController = new ShopController();
        Main.stage = stage;
        Pane pane = new Pane();
        pane.setPrefSize(1000,900);
        designShopMenu(shopController,pane,stage);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
    }

    public void designShopMenu(ShopController shopController , Pane pane,Stage stage){
        ShopMenuController shopMenuController = new ShopMenuController();
        shopMenuController.showPriceList(shopController.showPriceList(),pane,stage);
    }
}

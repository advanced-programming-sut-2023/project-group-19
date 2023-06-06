package view;

import controller.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Building.Shop;

import view.Controllers.ShopMenuController;
import view.ImageAndBackground.GameImages;
import view.ImageAndBackground.TradeAndShopImages;


public class ShopMenu extends Application {
    public static Shop currentShop ;
    @Override
    public void start(Stage stage) throws Exception {
        ShopController shopController = new ShopController();
        TradeAndShopImages shopImages = new TradeAndShopImages();
        shopImages.loadImages();
        Main.stage = stage;
        Pane pane = new Pane();
        ImageView background = new ImageView(shopImages.getBackground());
        pane.getChildren().add(background);
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

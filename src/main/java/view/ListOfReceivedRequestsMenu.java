package view;

import controller.TradeController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.*;
import view.ImageAndBackground.TradeAndShopImages;
import view.OldView.TradeMenu;

public class ListOfReceivedRequestsMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        User.makeUsersFromJson();
        TradeAndShopImages tradeAndShopImages = new TradeAndShopImages();
        tradeAndShopImages.loadImages();
        TradeController tradeController = new TradeController();

        Main.stage = stage;
        Button back = new Button();
        back.setPrefSize(100, 40);
        back.setLayoutX(20);
        back.setLayoutY(20);
        back.setStyle("-fx-background-color: #cba883");
        back.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TradeMenu tradeMenu = new TradeMenu();
                try {
                    tradeMenu.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Group group = tradeController.showDonations();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(group);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        ImageView backGround = new ImageView(tradeAndShopImages.getBackground());
        Pane pane = new Pane();
        pane.getChildren().addAll(backGround, scrollPane, back);
        scrollPane.setLayoutX(580);
        scrollPane.setLayoutY(200);
        scrollPane.setPrefWidth(400);
        scrollPane.setPrefHeight(400);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
    }
}

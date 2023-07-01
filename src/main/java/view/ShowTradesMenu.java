package view;

import controller.TradeController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Map;
import view.ImageAndBackground.TradeAndShopImages;

public class ShowTradesMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;
        Pane pane = new Pane();
        pane.setPrefSize(1000, 900);
        designShopTradeMenu(pane, stage);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);


    }

    private void designShopTradeMenu(Pane pane, Stage stage) throws Exception {
        TradeAndShopImages tradeAndShopImages = new TradeAndShopImages();
        tradeAndShopImages.loadImages();
        ImageView background = new ImageView(tradeAndShopImages.getBackground());
        Button back = new Button();
        Button listOfSentRequests = new Button();
        Button listOfAskedForDonation = new Button();
        back.setPrefSize(100, 50);
        listOfSentRequests.setPrefSize(400, 300);
        listOfAskedForDonation.setPrefSize(400, 300);
        back.setLayoutX(30);
        back.setLayoutY(30);
        listOfSentRequests.setLayoutX(250);
        listOfSentRequests.setLayoutY(250);
        listOfAskedForDonation.setLayoutX(850);
        listOfAskedForDonation.setLayoutY(250);
        back.setBackground(null);
        listOfSentRequests.setBackground(null);
        listOfAskedForDonation.setBackground(null);
        back.setText("Back");
        back.setStyle("-fx-background-color: #cba883");
        back.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        ImageView sentReqView = new ImageView(tradeAndShopImages.getListOfPrevReq());
        ImageView seeDonationView = new ImageView(tradeAndShopImages.getListOfDonationReq());
        sentReqView.setFitHeight(300);
        sentReqView.setFitWidth(400);
        seeDonationView.setFitHeight(300);
        seeDonationView.setFitWidth(400);
        listOfSentRequests.setGraphic(sentReqView);
        listOfAskedForDonation.setGraphic(seeDonationView);
        setActionOfButtons(listOfSentRequests, listOfAskedForDonation, back, stage);
        pane.getChildren().addAll(background, listOfSentRequests, listOfAskedForDonation, back);
    }

    private void setActionOfButtons(Button listOfSentRequests, Button listOfAskedForDonation, Button back, Stage stage) throws Exception {
        TradeController tradeController = new TradeController();
        tradeController.notification(stage);
        listOfSentRequests.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ListOfSentRequestsMenu list = new ListOfSentRequestsMenu();
                try {
                    list.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        listOfAskedForDonation.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ListOfReceivedRequestsMenu listOfReceivedRequestsMenu = new ListOfReceivedRequestsMenu();
                try {
                    listOfReceivedRequestsMenu.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ShopMenu shopMenu = new ShopMenu();
                try {
                    shopMenu.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}

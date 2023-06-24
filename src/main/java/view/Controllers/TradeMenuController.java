package view.Controllers;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.ImageAndBackground.TradeAndShopImages;
import view.SendRequestMenu;
import view.ShowTradesMenu;

public class TradeMenuController {

    public TradeAndShopImages tradeAndShopImages = new TradeAndShopImages();


    public void designTradeMenu(TradeMenuController tradeController, Pane pane, Stage stage) {
        tradeAndShopImages.loadImages();
        ImageView background = new ImageView(tradeAndShopImages.getBackground());
        Button createRequest = new Button();
        Button viewPrevRequests = new Button();
        Button back = new Button();
        createRequest.setPrefSize(400, 300);
        viewPrevRequests.setPrefSize(400, 300);
        back.setPrefSize(400, 300);
        createRequest.setLayoutX(250);
        createRequest.setLayoutY(250);
        viewPrevRequests.setLayoutX(850);
        viewPrevRequests.setLayoutY(250);
        back.setLayoutX(570);
        back.setLayoutY(400);
        createRequest.setBackground(null);
        viewPrevRequests.setBackground(null);
        back.setBackground(null);
        ImageView createRequestView = new ImageView(tradeAndShopImages.getSendRequest());
        ImageView prevRequestView = new ImageView(tradeAndShopImages.getViewPreviousTrades());
        ImageView backView = new ImageView(tradeAndShopImages.getBack());
        createRequestView.setFitHeight(300);
        createRequestView.setFitWidth(400);
        prevRequestView.setFitHeight(300);
        prevRequestView.setFitWidth(400);
        backView.setFitHeight(300);
        backView.setFitWidth(400);
        createRequest.setGraphic(createRequestView);
        viewPrevRequests.setGraphic(prevRequestView);
        back.setGraphic(backView);
        setActionOfButtons(createRequest, viewPrevRequests,back, stage);
        pane.getChildren().addAll(background, back, createRequest, viewPrevRequests);
    }

    public void setActionOfButtons(Button createRequest, Button viewPrevRequest,Button back, Stage stage) {
        createRequest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                SendRequestMenu sendRequestMenu = new SendRequestMenu();
                try {
                    sendRequestMenu.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        viewPrevRequest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ShowTradesMenu showTradesMenu = new ShowTradesMenu();
                try {
                    showTradesMenu.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.close();
            }
        });


    }
}

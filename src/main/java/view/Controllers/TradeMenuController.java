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

    public TradeAndShopImages tradeAndShopImages =  new TradeAndShopImages();


    public void designTradeMenu(TradeMenuController tradeController, Pane pane, Stage stage) {
        tradeAndShopImages.loadImages();
        ImageView background = new ImageView(tradeAndShopImages.getBackground());
        Button createRequest = new Button();
        Button viewPrevRequests = new Button();
        createRequest.setPrefSize(400 , 300);
        viewPrevRequests.setPrefSize(400,300);
        createRequest.setLayoutX(250);
        createRequest.setLayoutY(250);
        viewPrevRequests.setLayoutX(850);
        viewPrevRequests.setLayoutY(250);
        createRequest.setBackground(null);
        viewPrevRequests.setBackground(null);
        ImageView createRequestView = new ImageView(tradeAndShopImages.getSendRequest());
        ImageView prevRequestView = new ImageView(tradeAndShopImages.getViewPreviousTrades());
        createRequestView.setFitHeight(300);
        createRequestView.setFitWidth(400);
        prevRequestView.setFitHeight(300);
        prevRequestView.setFitWidth(400);
        createRequest.setGraphic(createRequestView);
        viewPrevRequests.setGraphic(prevRequestView);
        setActionOfButtons(createRequest,viewPrevRequests,stage);
        pane.getChildren().addAll(background,createRequest,viewPrevRequests);
    }

    public void setActionOfButtons(Button createRequest , Button viewPrevRequest,Stage stage){
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

    }
}

package view.Controllers;

import controller.TradeController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import view.Main;
import view.OldView.TradeMenu;

public class TradeMenuController {


    public void designTradeMenu(TradeMenuController tradeController, Pane pane, Stage stage) {
        Button createRequest = new Button();
        Button viewPrevRequests = new Button();
        createRequest.setPrefSize(200 , 30);
        viewPrevRequests.setPrefSize(200,30);
        createRequest.setLayoutX(250);
        createRequest.setLayoutY(450);
        viewPrevRequests.setLayoutX(750);
        viewPrevRequests.setLayoutY(450);
        createRequest.setStyle("-fx-background-color: #cba883");
        viewPrevRequests.setStyle("-fx-background-color: #cba883");
        createRequest.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        viewPrevRequests.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        pane.getChildren().addAll(createRequest,viewPrevRequests);

    }
}

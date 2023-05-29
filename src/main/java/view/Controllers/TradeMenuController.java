package view.Controllers;

import controller.TradeController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.Main;
import view.OldView.TradeMenu;

public class TradeMenuController extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        TradeController tradeController = new TradeController();
        Main.stage = stage;
        Pane pane = new Pane();
        pane.setPrefSize(1000,900);
        designTradeMenu(tradeController,pane,stage);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
    }

    private void designTradeMenu(TradeController tradeController, Pane pane, Stage stage) {

    }
}

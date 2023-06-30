package view.OldView;

import controller.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.Commands.TradeMenuCommands;
import view.Controllers.TradeMenuController;
import view.Main;
import view.Messages.TradeMenuMessages;
import view.TileManager;

import java.util.Scanner;
import java.util.regex.Matcher;


public class TradeMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
        TileManager.gameLog.append(TileManager.time + '#' + "TRADE_MENU" + '\n');
        TradeMenuController tradeController = new TradeMenuController();
        Main.stage = stage;
        Pane pane = new Pane();
        pane.setPrefSize(1000, 900);
        pane.setMaxSize(1000, 900);
        tradeController.designTradeMenu(tradeController, pane, stage);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
    }
}

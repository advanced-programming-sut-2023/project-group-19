package view;

import controller.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Empire;
import model.Manage;
import view.Messages.GameMenuMessages;

import java.util.regex.Matcher;

public class GameMenu extends Application {

    public static Empire currentEmpire = Manage.getCurrentEmpire();
    public static GameController gameController = new GameController();

    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;
        Pane pane = new Pane();
        pane.setPrefSize(1530, 800);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
    }
}

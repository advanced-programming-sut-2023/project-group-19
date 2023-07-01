package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.Controllers.SendRequestController;

public class SendRequestMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        SendRequestController sendRequestController = new SendRequestController();
        Main.stage = stage;
        Pane pane = new Pane();
        pane.setPrefSize(1000, 900);
        pane.setMaxSize(1000, 900);
        sendRequestController.designTheMenu(pane);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
    }
}

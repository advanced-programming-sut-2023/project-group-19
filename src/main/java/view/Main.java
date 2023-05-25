package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.LoginMenu;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) throws IOException , InterruptedException {
        launch(args);
//        LoginMenu.run(new Scanner(System.in));
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
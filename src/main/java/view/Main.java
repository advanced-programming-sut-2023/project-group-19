package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Building.Shop;
import model.Empire;
import model.Manage;
import model.Map;
import model.User;
import view.LoginMenu;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main extends Application {
    public static Stage stage;

    public static void main(String[] args) throws IOException, InterruptedException {

    }

    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        Button button = new Button();
        button.setText("LoginMenu");
        pane.setPrefSize(1530, 800);
        button.setLayoutX(700);
        button.setLayoutY(250);
        button.setPrefSize(200, 70);
        pane.getChildren().add(button);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                LoginMenu loginMenu = new LoginMenu();
                try {
                    loginMenu.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        stage.setScene(scene);
        stage.show();
    }
}

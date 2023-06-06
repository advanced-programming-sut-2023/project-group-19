package view;

import controller.CreateMapController;
//import controller.NextTurnController;
import controller.JsonController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.User;
import view.Commands.MainMenuCommands;
import view.ImageAndBackground.GameImages;

import java.io.IOException;
import java.util.Scanner;

public class MainMenu extends Application {
    public GameImages gameImages = new GameImages();
    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;
        Pane pane = new Pane();
        gameImages.loadImages();
        pane.setBackground(gameImages.getMainMenuBackground());

        Scene scene = new Scene(pane);
        Button button = new Button();
        button.setText("CreateMapMenu");
        button.setLayoutX(700);
        button.setLayoutY(250);
        button.setPrefSize(200,70);
        button.setStyle("-fx-background-color: #cba883");
        button.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CreateMapMenu createMapMenu = new CreateMapMenu();
                try {
                    createMapMenu.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Button button1 = new Button();
        button1.setText("ProfileMenu");
        button1.setLayoutX(700);
        button1.setLayoutY(350);
        button1.setPrefSize(200,70);
        button1.setStyle("-fx-background-color: #cba883");
        button1.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        button1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ProfileMenu profileMenu = new ProfileMenu();
                try {
                    profileMenu.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Button button2 = new Button();
        button2.setText("GameMenu");
        button2.setLayoutX(700);
        button2.setLayoutY(450);
        button2.setPrefSize(200,70);
        button2.setStyle("-fx-background-color: #cba883");
        button2.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));

        button2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TileManager tileManager = new TileManager();
                try {
                    tileManager.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        pane.getChildren().addAll(button,button1,button2);
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
    }

}

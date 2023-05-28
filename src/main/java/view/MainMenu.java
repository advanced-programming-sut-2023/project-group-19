package view;

import controller.CreateMapController;
import controller.NextTurnController;
import controller.JsonController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;
import view.Commands.MainMenuCommands;

import java.io.IOException;
import java.util.Scanner;

public class MainMenu extends Application {
//    public static void run(Scanner scanner) throws InterruptedException, IOException {
//        System.out.println("Welcome to view.Main menu!");
//        String command;
//        while (true) {
//            command = scanner.nextLine();
//            if (MainMenuCommands.getMatcher(command, MainMenuCommands.ENTER_PROFILE_MENU) != null) {
//                System.out.println("entered profile menu successfully");
//                ProfileMenu.run(scanner);
//            } else if (MainMenuCommands.getMatcher(command, MainMenuCommands.LOGOUT) != null) {
//                User.setCurrentUser(null);
//                JsonController.emptyFile();
//                System.out.println("logged out");
//                return;
//            } else if (command.matches("\\s*enter\\s+map\\s+menu\\s*")) {
//                System.out.println("Entered map menu successfully!");
//                //CreateMapMenu.run(scanner);
//            } else if (command.matches("\\s*enter\\s+game\\s+menu")) {
//                System.out.println("Entered game menu successfully!");
//                enterGameMenu(scanner);
//            } else System.out.println("Invalid command!");
//        }
//    }

//    private static void enterGameMenu(Scanner scanner) throws IOException, InterruptedException {
//        if (CreateMapController.numberOfEmpires >= 2) {
//            NextTurnController nextTurnController = new NextTurnController();
//            System.out.println(nextTurnController.game(scanner));
//            CreateMapController.recovery();
//        } else {
//            System.out.println("more castle must be build!");
//        }
//    }

    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;
        Pane pane = new Pane();
        pane.setPrefSize(1530,800);
        Scene scene = new Scene(pane);
        Button button = new Button();
        button.setText("CreateMapMenu");
        button.setLayoutX(700);
        button.setLayoutY(250);
        button.setPrefSize(200,70);
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

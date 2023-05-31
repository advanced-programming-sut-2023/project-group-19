package view;

import controller.GameController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Building.Shop;
import model.Empire;
import model.Human.Troop.ArchersAndThrowers;
import model.Human.Troop.Army;
import model.Manage;
import model.Map;
import model.User;
import view.Animations.MoveAnimation;
import view.LoginMenu;

import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {
    public static Stage stage;
    public static void main(String[] args) throws IOException, InterruptedException {
        User newUser = new User("user6", "aa", "ali", "a", "1", "1", 1);
        User newUser1 = new User("user6", "aa", "dorsa", "a", "1", "1", 1);
        Empire Ali = new Empire();
        Empire Dorsa = new Empire();
        Ali.setUser(newUser);
        Dorsa.setUser(newUser1);
        Manage.setCurrentEmpire(Ali);
        Map.CreateMap(200);
        Map.mapSize = 200;
        Manage.getAllEmpires().add(Dorsa);
        Manage.getAllEmpires().add(Ali);
        Shop shop = new Shop(Ali);
        ShopMenu.currentShop = shop;
        Map.getBuildingMap()[1][2].add(shop);
        launch(args);
//        LoginMenu.run(new Scanner(System.in));
    }

    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        Button button = new Button();
        button.setText("LoginMenu");
        pane.setPrefSize(1530,800);
        button.setLayoutX(700);
        button.setLayoutY(250);
        button.setPrefSize(200,70);
//        pane.getChildren().add(button);
//        ArchersAndThrowers archersAndThrowers = new ArchersAndThrowers(Manage.getCurrentEmpire());
//        archersAndThrowers.setHeight(100);
//        archersAndThrowers.setWidth(100);
//        archersAndThrowers.setLayoutX(100);
//        archersAndThrowers.setLayoutY(300);
//        archersAndThrowers.archer(100,300);
//        pane.getChildren().add(archersAndThrowers);
//        GameController gameController = new GameController();
//        gameController.selectedUnit.add(archersAndThrowers);
//        gameController.setPathForUnits(100,400);
//        MoveAnimation moveAnimation = new MoveAnimation(archersAndThrowers);
//        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                LoginMenu loginMenu = new LoginMenu();
//                try {
//                    loginMenu.start(stage);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
        stage.setScene(scene);
        stage.show();
    }
}

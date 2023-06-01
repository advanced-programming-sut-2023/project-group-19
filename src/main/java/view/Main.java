package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Building.Shop;
import model.Empire;
import model.Manage;
import model.Map;
import model.User;

import java.io.IOException;

public class Main extends Application {
    public static Stage stage;
    public Pane pane;
    static {
        User user = null;
        try {
            user = new User("a","sSdd55@","f","g.@s.s","q","x",4);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User.setCurrentUser(user);
    }
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
        this.pane = pane ;
        Scene scene = new Scene(pane);
        VBox vBox = new VBox();
        vBox.setTranslateX(700);
        vBox.setTranslateY(250);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(30);

        createButton(vBox);
        stage.setScene(scene);
        stage.show();
    }
    private void createButton(VBox vBox){
        pane.setPrefSize(1530,800);
        Button button = new Button();
        button.setText("Profile Menu");
        button.setPrefWidth(100);
        button.setPrefHeight(100);
        button.setPrefSize(200,70);
        vBox.getChildren().add(button);
        pane.getChildren().add(vBox);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                RegisterMenu registerMenu = new RegisterMenu();
                try {
                    ProfileMenu profileMenu = new ProfileMenu();
                    profileMenu.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });


        Button button2 = new Button();
        button2.setPrefWidth(100);
        button2.setPrefHeight(100);
        button2.setText("ScoreBoard Menu");
        vBox.getChildren().add(button2);
        button2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    ScoreBoardMenu scoreBoardMenu = new ScoreBoardMenu();
                    scoreBoardMenu.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}

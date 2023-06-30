package view;

import controller.method.MapMethod;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Manage;
import model.Map;
import model.Obstacle.SavedObstacles;
import view.ImageAndBackground.GameImages;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.PublicKey;
import java.util.ArrayList;

public class SelectMapMenu extends Application {
    public Pane pane;
    public TextField addMapByMyself;
    public Button refresh;
    public Button back;
    public Scene scene;
    public Stage stage;
    public VBox vBox;
    public ScrollPane scrollPane;
    public MapMethod mapMethod;

    @Override
    public void start(Stage stage) throws Exception {
        RegisterMenu registerMenu = new RegisterMenu();
        Socket socket = new Socket("localhost",8888);
        Manage.masterServerDataInputStream = new DataInputStream(socket.getInputStream());
        Manage.masterServerDataOutputStream = new DataOutputStream(socket.getOutputStream());
        mapMethod = new MapMethod();
        GameImages gameImages = new GameImages();
        gameImages.loadImages();
        Main.stage = stage;
        Pane pane = new Pane();
        this.pane = pane;
        pane.setBackground(gameImages.getProfileBackground());
        Scene scene = new Scene(pane);
        this.scene = scene;
        stage.setScene(scene);
        designButtons(gameImages);
        designMaps(gameImages);
        stage.show();
        stage.setFullScreen(true);
    }

    private void designMaps(GameImages gameImages) throws IOException {
        int i = 0;
        ArrayList<ArrayList<SavedObstacles>> arrayList = MapMethod.getMapsFromServer();
        vBox = new VBox();
        scrollPane = new ScrollPane();
        vBox.setStyle("-fx-background-radius: 10px");
        vBox.setSpacing(2);

        for (ArrayList<SavedObstacles> savedObstacles : arrayList) {

            ImageView imageView = null;
            if (i % 3 ==0) imageView = new ImageView(gameImages.getMap1());
            else if (i % 3 ==1) {
                imageView = new ImageView(gameImages.getMap2());
            } else if (i % 3 ==2) {
                imageView = new ImageView(gameImages.getMap3());
            }
            i++;
            imageView.setFitHeight(230);
            imageView.setFitWidth(580);
            Text text = new Text();

            if(Map.getMapWithName(savedObstacles.get(0).getNameOfMap()) != null){
                text.setText("My map");
                text.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 14));
                imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Download Map From Server");
                        alert.setContentText("You can not download the map you own!");
                        alert.showAndWait();
                        MapMethod.makeMapFromSaveObstacle(savedObstacles);
                    }
                });
                // darim
            }else{
                imageView.setStyle("-fx-background-radius: 10px;");
                imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Download Map From Server");
                        alert.setContentText("The chosen map is downloaded");
                        alert.showAndWait();
                        MapMethod.makeMapFromSaveObstacle(savedObstacles);
                    }
                });
                // nadarim
            }


            vBox.getChildren().add(imageView);
            if(!text.getText().equals("")) {
                text.setTranslateY(-25);
                vBox.getChildren().add(text);
            }

        }
        scrollPane.setPrefWidth(600);
        scrollPane.setPrefHeight(400);
        scrollPane.setContent(vBox);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setLayoutX(500);
        scrollPane.setLayoutY(100);

        scrollPane.setVisible(true);
        pane.getChildren().add(scrollPane);

    }

    private void designButtons(GameImages gameImages){
        back = new Button("Back");
        back.setLayoutX(20);
        back.setLayoutY(20);
        back.setPrefSize(150, 50);
        back.setStyle("-fx-background-color: rgb(203,168,131); -fx-text-fill: #000000;-fx-background-radius: 10px");
        back.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 18));
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.close();
            }
        });

        refresh = new Button("Refresh");
        refresh.setLayoutX(20);
        refresh.setLayoutY(80);
        refresh.setPrefSize(150, 50);
        refresh.setStyle("-fx-background-color: rgb(203,168,131); -fx-text-fill: #000000;-fx-background-radius: 10px");
        refresh.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 18));
        refresh.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    pane.getChildren().clear();
                    designButtons(gameImages);
                    designMaps(gameImages);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        addMapByMyself = new TextField();
        addMapByMyself.setPromptText("Enter Map's name");
        addMapByMyself.setPrefSize(450, 30);
        addMapByMyself.setTranslateX(550);
        addMapByMyself.setTranslateY(40);
        addMapByMyself.setFocusTraversable(false);
        addMapByMyself.setStyle("-fx-background-color: rgb(203,168,131); -fx-prompt-text-fill: #000000;" +
                "-fx-text-fill: #000000;-fx-background-radius: 10px");
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();
                if (keyName.equals("Enter")) {
                    try {
                        System.out.println("Done");
                        MapMethod.addNewMapToServer(addMapByMyself.getText());
                        addMapByMyself.clear();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        pane.getChildren().add(back);
        pane.getChildren().add(refresh);
        pane.getChildren().add(addMapByMyself);
    }
}
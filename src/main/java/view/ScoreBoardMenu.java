package view;

import java.io.IOException;
import java.util.Collections;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.User;
import view.ImageAndBackground.GameImages;
import view.ImageAndBackground.TradeAndShopImages;

public class ScoreBoardMenu extends Application {
    {
        Collections.sort(User.users);
    }

    public ScrollPane scrollPane;
    public static Stage stage;

    public void start(Stage stage) throws IOException {
        GameImages gameImages = new GameImages();
        gameImages.loadImages();
        ScoreBoardMenu.stage = stage;
        createUser();
        ScrollPane scrollPane = new ScrollPane();
        this.scrollPane = scrollPane;
        Pane pane = new Pane();
        pane.setBackground(gameImages.getProfileBackground());
//        pane.getChildren().add(scrollPane);
        Scene scene = new Scene(pane);
        //
        createTableOfUsers(pane);
        setBackButton();
        //
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
    }
    //rank - picture - username - highScore

    private void setBackButton() {


    }

    private void createTableOfUsers(Pane pane) {
        Button back = new Button("Back");
        back.setPrefSize(100, 40);
        back.setLayoutX(20);
        back.setLayoutY(20);
        back.setStyle("-fx-background-color: #cba883");
        back.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Main main = new Main();
                try {
                    main.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        pane.getChildren().add(back);
        VBox vBox = new VBox();
        setSettingToMainVbox(vBox);
        for (User user : User.users) {
            //HBox
            HBox hBox = new HBox();
            setSettingsToHBox(hBox);
            //Rank Label
            Label rank = new Label();
            setSettingToRankLabel(rank, user);
            hBox.getChildren().add(rank);
            //Image Avatar
            ImageView imageView = new ImageView();
            setSettingsToImageView(imageView, user);
            hBox.getChildren().add(imageView);
            //Username
            Label username = new Label();
            setSettingToUsernameLabel(username, user);
            hBox.getChildren().add(username);
            //HighScore
            Label highScore = new Label();
            setSettingToHighSocreLabel(highScore, user);
            hBox.getChildren().add(highScore);
            //
            vBox.getChildren().add(hBox);
        }
        scrollPane.setContent(vBox);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setLayoutX(580);
        scrollPane.setLayoutY(200);
        scrollPane.setPrefWidth(400);
        scrollPane.setPrefHeight(400);
        pane.getChildren().add(scrollPane);
    }

    private void setSettingToHighSocreLabel(Label highScore, User user) {
        highScore.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        highScore.setMaxHeight(20);
        highScore.setText("" + user.getHighScore());
        highScore.setTranslateX(-10);

    }

    private void setSettingToUsernameLabel(Label username, User user) {
        username.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        username.setMaxHeight(20);
        username.setText(user.getUsername());
    }

    private void createUser() throws IOException {
        new User("z", "s", "a", "s", "w", "q", 3);
        new User("ab", "s", "a", "s", "w", "q", 3);
        new User("ac", "s", "a", "s", "w", "q", 3);
        new User("ad", "s", "a", "s", "w", "q", 3);
        new User("ae", "s", "a", "s", "w", "q", 3);
        new User("af", "s", "a", "s", "w", "q", 3);
        new User("ag", "s", "a", "s", "w", "q", 3);
        new User("ah", "s", "a", "s", "w", "q", 3);
        new User("s", "s", "a", "s", "w", "q", 3);
        new User("s", "s", "a", "s", "w", "q", 3);
        new User("s", "s", "a", "s", "w", "q", 3);
        new User("s", "s", "a", "s", "w", "q", 3);
    }

    private void setSettingToMainVbox(VBox vBox) {
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: #871818");
        vBox.setSpacing(20);
        vBox.setLayoutX(600);
        vBox.setLayoutY(40);
        vBox.setPrefWidth(380);

    }

    private void setSettingToRankLabel(Label label, User user) {
        label.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        label.setMaxHeight(20);
        label.setText("" + (User.users.indexOf(user) + 1));
        label.setTranslateX(10);
    }

    private void setSettingsToImageView(ImageView imageView, User user) {
        imageView.setFitWidth(60);
        imageView.setFitHeight(60);
        imageView.setImage(user.getAvatar().getImage());
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                User.getCurrentUser().setAvatar(imageView);
            }
        });
    }

    private void setSettingsToHBox(HBox hBox) {
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(85);
        hBox.setStyle("-fx-background-color: #cba883");
    }
}
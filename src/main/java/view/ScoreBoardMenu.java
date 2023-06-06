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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class ScoreBoardMenu extends Application {
    {
        Collections.sort(User.users);
    }

    public ScrollPane scrollPane;
    public static Stage stage;

    public void start(Stage stage) throws IOException {
        ScoreBoardMenu.stage = stage;
        createUser();
        ScrollPane scrollPane = new ScrollPane();
        this.scrollPane = scrollPane;
        Scene scene = new Scene(scrollPane, 500, 500);
        //
        createTableOfUsers();
        setBackButton();
        //
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
    }
    //rank - picture - username - highScore

    private void setBackButton() {


    }

    private void createTableOfUsers() {
        Button button = new Button("Back");
        button.setTranslateX(0);
        button.setTranslateY(600);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
        VBox vBox = new VBox();
        vBox.getChildren().add(button);
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
    }

    private void setSettingToHighSocreLabel(Label highScore, User user) {
        highScore.setStyle("-fx-font-size: 25");
        highScore.setMaxHeight(20);
        highScore.setText("" + user.getHighScore());
    }

    private void setSettingToUsernameLabel(Label username, User user) {
        username.setStyle("-fx-font-size: 25");
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
        vBox.setStyle("-fx-background-color: #5c5cf3");
        vBox.setSpacing(20);
        vBox.setTranslateX(600);
        vBox.setTranslateY(40);
    }

    private void setSettingToRankLabel(Label label, User user) {
        label.setStyle("-fx-font-size: 25");
        label.setMaxHeight(20);
        label.setText("" + (User.users.indexOf(user) + 1));
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
        hBox.setStyle("-fx-background-color: #8181de");
    }
}
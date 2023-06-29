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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
    public ScrollPane friendShipScrollPane = new ScrollPane();

    public void start(Stage stage) throws IOException {
        User.makeUsersFromJson();
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
        designVBoxOfFriendShip(pane);
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
            System.out.println(hBox.getWidth()+" "+hBox.getHeight());
        }
        scrollPane.setContent(vBox);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setLayoutX(880);
        scrollPane.setLayoutY(200);
        scrollPane.setPrefWidth(400);
        scrollPane.setPrefHeight(400);
        pane.getChildren().add(scrollPane);
    }

    private void setSettingToHighSocreLabel(Label highScore, User user) {
        highScore.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
        highScore.setMaxHeight(20);
        highScore.setText("" + user.getHighScore());
        highScore.setTranslateX(-10);

    }

    private void setSettingToUsernameLabel(Label username, User user) {
        username.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
        username.setMaxHeight(20);
        username.setText(user.getUsername());
    }

    private void createUser() throws IOException {
        User user = new User("Doreece", "s", "a", "s", "a", "q", 2);
        User user2 = new User("Armin", "s", "a", "s", "w", "q", 3);
        User user3 = new User("Arian", "s", "a", "s", "w", "q", 3);
        User user4 = new User("ad", "s", "a", "s", "w", "q", 3);
        User user5 = new User("ae", "s", "a", "s", "w", "q", 3);
        User.setCurrentUser(user);

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
        label.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
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

    private void designVBoxOfFriendShip(Pane pane) {
        pane.getChildren().remove(friendShipScrollPane);
        VBox friendShipBox = new VBox();
        friendShipBox.setStyle("-fx-background-color: #871818");
        friendShipBox.setSpacing(20);
        if (User.users.size() > 1) {
            for (User user : User.users) {
                if (!user.getUsername().equals(User.getCurrentUser().getUsername())) {
                    HBox friendBox = new HBox();
                    friendBox.setPrefSize(384, 30);

                    Text userName = new Text();
                    userName.setText("Id: " + user.getUsername());
                    userName.setFill(Color.BLACK);
                    userName.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
                    userName.setTranslateX(40);
                    userName.setTranslateY(5);
                    userName.prefWidth(30);
                    userName.prefHeight(10);

                    ImageView avatar = user.getAvatar();
                    avatar.setFitWidth(60);
                    avatar.setFitHeight(60);
                    avatar.setTranslateX(15);
                    avatar.setLayoutY(5);


                    Button following = new Button();
                    if (findFriends(user)) following.setText("Following");
                    else following.setText("Follow");
                    following.setPrefSize(70, 8);
                    following.setStyle("-fx-background-color: #871818; -fx-text-fill: #cba883");
                    following.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 10));
                    following.setTranslateX(100);
                    following.setTranslateY(30);
                    following.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            sendFriendShipRequest(user, following);
                        }
                    });


                    friendBox.setStyle("-fx-background-color: #cba883;");
                    friendBox.getChildren().add(avatar);
                    friendBox.getChildren().add(following);
                    friendBox.getChildren().add(userName);
                    friendShipBox.getChildren().add(friendBox);
                }

            }
            Button close = new Button("Close");
            close.setStyle("-fx-background-color: #cba883");
            close.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 14));
            close.setLayoutX(1000);
            close.setLayoutY(550);
            close.setPrefSize(100, 30);
            close.setFocusTraversable(false);
            close.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        pane.getChildren().remove(friendShipScrollPane);
                        pane.getChildren().remove(close);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            friendShipScrollPane.setPrefWidth(400);
            friendShipScrollPane.setContent(friendShipBox);
            friendShipScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            friendShipScrollPane.setLayoutX(200);
            friendShipScrollPane.setLayoutY(200);

            friendShipScrollPane.setStyle("-fx-background-color: #cba883");
            friendShipScrollPane.setVisible(true);
            pane.getChildren().add(friendShipScrollPane);
        }
    }

    private void sendFriendShipRequest(User user, Button followButton) {
        followButton.setText("Requested");
        //Todo: Send request to server
    }

    public boolean findFriends(User user){
        for (User myFriend : User.getCurrentUser().myFriends) {
            if (myFriend.getUsername().equals(user.getUsername())){
                return true;
            }
        }
        return false;
    }
}
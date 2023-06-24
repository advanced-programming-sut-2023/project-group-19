package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Game;
import model.Manage;
import model.User;
import view.ImageAndBackground.GameImages;
import javafx.scene.control.Button;
import java.io.IOException;

public class Lobby extends Application {
    public GameImages gameImages;
    public Pane pane;
    public VBox listOfAllGames;


    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;
        gameImages = new GameImages();
        gameImages.loadImages();
        Pane pane = new Pane();
        this.pane = pane;
        pane.setBackground(gameImages.getLobbyBackground());
        designLobby(gameImages);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
    }

    private void designLobby(GameImages gameImages) throws IOException {
        Button back = new Button("Back");
        back.setLayoutX(300);
        back.setLayoutY(70);
        back.setPrefSize(160, 50);
        back.setStyle("-fx-background-color: #1b1073; -fx-text-fill: #d3c4c4");
        back.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));

        Button createNewRequest = new Button("New Game Request");
        createNewRequest.setLayoutX(500);
        createNewRequest.setLayoutY(70);
        createNewRequest.setPrefSize(160, 50);
        createNewRequest.setStyle("-fx-background-color: #1b1073; -fx-text-fill: #d3c4c4");
        createNewRequest.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));


        User user1 = new User("z", "s", "a", "s", "w", "q", 3);
        User user2 = new User("ab", "s", "a", "s", "w", "q", 3);
        User user3 = new User("ac", "s", "a", "s", "w", "q", 3);
        User user4 = new User("ad", "s", "a", "s", "w", "q", 3);
        User user5 = new User("ae", "s", "a", "s", "w", "q", 3);
        User user6 = new User("af", "s", "a", "s", "w", "q", 3);
        User.setCurrentUser(user1);


        Game game = new Game(user1,"MyGame1",true, 5);
        Manage.allGames.add(game);
        game.addToAllPlayers(user1);
        game.addToAllPlayers(user2);
        game.addToAllPlayers(user3);
        game.addToAllPlayers(user4);
        game.addToAllPlayers(user5);

        Game game2 = new Game(user1,"MyGame2",true, 5);
        Manage.allGames.add(game2);
        Game game3 = new Game(user1,"MyGame3",true, 5);
        Manage.allGames.add(game3);
        Game game4 = new Game(user1,"MyGame4",true, 5);
        Manage.allGames.add(game4);
        Game game5 = new Game(user1,"MyGame5",true, 5);
        Manage.allGames.add(game5);

        VBox listOfAllGames = new VBox();
        this.listOfAllGames = listOfAllGames;
        listOfAllGames.setPrefSize(400,250);
        designListOfAllGames(gameImages);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(listOfAllGames);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setLayoutX(300);
        scrollPane.setLayoutY(200);
        scrollPane.setPrefWidth(415);
        scrollPane.setPrefHeight(250);
        scrollPane.setStyle("-fx-background-color: #1b1073");

        pane.getChildren().add(back);
        pane.getChildren().add(createNewRequest);
        pane.getChildren().add(scrollPane);
    }

    private void designListOfAllGames(GameImages gameImages) {
        listOfAllGames.setStyle("-fx-background-color: #03183b");
        listOfAllGames.setSpacing(2);
        ImageView shieldImage = null;
        listOfAllGames.setLayoutX(100);
        listOfAllGames.setLayoutY(200);
        for (int i = 0 ; i < Manage.allGames.size() ; i++) {
            Game game = Manage.allGames.get(i);
            if (i % 4 == 0){
                shieldImage = new ImageView(gameImages.getShield0());
            } else if (i % 4 == 1) {
                shieldImage = new ImageView(gameImages.getShield1());
            } else if (i % 4 == 2) {
                shieldImage = new ImageView(gameImages.getShield2());
            } else if (i % 4 == 3) {
                shieldImage = new ImageView(gameImages.getShield3());
            }
            game.setImageView(shieldImage);
            shieldImage.setFitWidth(50);
            shieldImage.setFitHeight(50);
            HBox gameIdHBox = new HBox();
            gameIdHBox.setPrefSize(20,10);
            String id = game.getId();
            Text gameId = new Text();
            gameId.setText("Id: "+game.getId());
            gameId.setFill(Color.WHITE);
            gameId.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
            gameId.setTranslateX(-25);
            gameId.setTranslateY(5);
            gameIdHBox.setStyle("-fx-background-color: #1b1073;");
            gameIdHBox.getChildren().add(shieldImage);
            gameIdHBox.getChildren().add(gameId);
            gameIdHBox.setSpacing(100);
            
            gameIdHBox.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    designVboxOfGameInfo(id);
                }
            });
            listOfAllGames.getChildren().add(gameIdHBox);

        }

    }
    public void designVboxOfGameInfo(String gameId){
        VBox listOfGameInfo = new VBox();
        listOfGameInfo.setSpacing(5);
        String listOfGamePlayers = "Players:\n";
        Game game = Manage.findGameById(gameId);
        if (game != null){
            ImageView imageView = new ImageView(game.getImageView().getImage());
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            imageView.setTranslateX(75);


            Text idOfGame = new Text("Game Id: "+game.getId());
            idOfGame.setFill(Color.WHITE);
            idOfGame.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
            idOfGame.setTranslateX(17);
            idOfGame.setTranslateY(20);

            Text capacity = new Text("Game capacity: "+game.getCapacity());
            capacity.setFill(Color.WHITE);
            capacity.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
            capacity.setTranslateX(17);
            capacity.setTranslateY(30);

            Text admin = new Text("Admin: "+game.getGameAdmin().getNickname());
            admin.setFill(Color.WHITE);
            admin.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
            admin.setTranslateX(17);
            admin.setTranslateY(40);

            for (int i = 1 ; i < game.getAllPlayers().size() ; i++) {
                User player = game.getAllPlayers().get(i);
                listOfGamePlayers = listOfGamePlayers.concat(player.getNickname());
                if (i != game.getAllPlayers().size() - 1) listOfGamePlayers = listOfGamePlayers.concat(",");
                if (i % 5 == 0) listOfGamePlayers = listOfGamePlayers.concat("\n");
            }

            Text players = new Text(listOfGamePlayers);
            players.setFill(Color.WHITE);
            players.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
            players.setTranslateX(17);
            players.setTranslateY(50);


            listOfGameInfo.getChildren().add(imageView);
            listOfGameInfo.getChildren().add(idOfGame);
            listOfGameInfo.getChildren().add(capacity);
            listOfGameInfo.getChildren().add(admin);
            listOfGameInfo.getChildren().add(players);
            designButtonsOfChosenGame(listOfGameInfo, game);
            listOfGameInfo.setPrefSize(230,300);
            listOfGameInfo.setStyle("-fx-background-color: #1b1073");
            listOfGameInfo.setLayoutX(1000);
            listOfGameInfo.setLayoutY(200);

            pane.getChildren().add(listOfGameInfo);
        }
    }

    private void designButtonsOfChosenGame(VBox listOfGameInfo,Game game) {
        //TODO : Access of other members to private and public games
        User currentUser = User.getCurrentUser();

        if (currentUser.getUsername().equals(game.getGameAdmin().getUsername())){ //Admin

            Button changePrivacy = new Button();
            if (game.isPublic()) {
                changePrivacy.setText("Private");
            }
            else {
                changePrivacy.setText("Public");
            }
            changePrivacy.setTranslateX(17);
            changePrivacy.setTranslateY(70);
            changePrivacy.setPrefSize(50, 10);
            changePrivacy.setStyle("-fx-background-color: #55288c; -fx-text-fill: #d3c4c4");
            changePrivacy.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 10));

            Button leaveGame = new Button();
            leaveGame.setText("Leave");
            leaveGame.setTranslateX(80);
            leaveGame.setTranslateY(40);
            leaveGame.setPrefSize(50, 10);
            leaveGame.setStyle("-fx-background-color: #55288c; -fx-text-fill: #d3c4c4");
            leaveGame.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 10));



            listOfGameInfo.getChildren().add(changePrivacy);
            listOfGameInfo.getChildren().add(leaveGame);

        } else if (game.isMemberOfGame(currentUser.getUsername()) != null) { //Game Member




        } else { //Stranger




        }


    }

}

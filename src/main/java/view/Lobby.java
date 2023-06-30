package view;

import controller.method.ChatMethods;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Chat;
import model.Game;
import model.Manage;

import model.User;
import view.ImageAndBackground.GameImages;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;


public class Lobby extends Application {
    public GameImages gameImages;
    public static Socket socket  ;

    public Pane pane;
    public VBox listOfAllGames;
    public VBox listOfGameInfo;
    public VBox chatBox;
    public ScrollPane scrollPaneForMainList = new ScrollPane();
    public ScrollPane scrollPaneForSearchedList = new ScrollPane();
    public ScrollPane scrollPaneForChatList = new ScrollPane();
    public ScrollPane scrollPaneForChatBox = new ScrollPane();
    public Button send = new Button();
    public TextField chatTextField = new TextField();
    public Text emptyGameId = new Text("Game Id field is empty!");
    public Text emptyCapacity = new Text("Capacity field is empty!");
    public Text emptyTypeOfGame = new Text("Game's Type field is empty!");
    public Text invalidTypeOfGame = new Text("The given game type is invalid!");
    public Text invalidCapacity = new Text("The given capacity is invalid!");

    //TODO : EventHandler for Back Button
    //TODO : Game should be closed if the number of members reach to the settled capacity

    static {
    }


    @Override
    public void start(Stage stage) throws Exception {
        User.makeUsersFromJson();

        User user1 = new User("z", "s", "a", "s", "w", "q", 3);
        User user2 = new User("ab", "s", "a", "s", "w", "q", 3);
        User user3 = new User("ac", "s", "a", "s", "w", "q", 3);
        User user4 = new User("ad", "s", "a", "s", "w", "q", 3);
        User user5 = new User("ae", "s", "a", "s", "w", "q", 3);
        User.setCurrentUser(user2);


        Game game = new Game(user1, "MyGame1", true, 5);
        Manage.allGames.add(game);
        game.addToAllPlayers(user1);
        game.addToAllPlayers(user2);
        game.addToAllPlayers(user3);
        game.addToAllPlayers(user4);
        game.addToAllPlayers(user5);

        Game game2 = new Game(user1, "MyGame2", true, 5);
        Manage.allGames.add(game2);
        Game game3 = new Game(user1, "MyGame3", true, 5);
        Manage.allGames.add(game3);
        Game game4 = new Game(user1, "MyGame4", true, 5);
        Manage.allGames.add(game4);
        Game game5 = new Game(user1, "MyGame5", true, 5);
        Manage.allGames.add(game5);

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
        //Get data from server

        Button back = new Button("Back");
        back.setLayoutX(350);
        back.setLayoutY(70);
        back.setPrefSize(160, 50);
        back.setStyle("-fx-background-color: rgba(27,16,115,0.71); -fx-text-fill: #d3c4c4");
        back.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));

        Button createNewRequest = new Button("New Game Request");
        createNewRequest.setLayoutX(550);
        createNewRequest.setLayoutY(70);
        createNewRequest.setPrefSize(160, 50);
        createNewRequest.setStyle("-fx-background-color: rgba(27,16,115,0.71)3; -fx-text-fill: #d3c4c4");
        createNewRequest.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
        createNewRequest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                createNewRequestStage();
            }
        });

        Button chat = new Button("Chat");
        chat.setLayoutX(750);
        chat.setLayoutY(70);
        chat.setPrefSize(160, 50);
        chat.setStyle("-fx-background-color: rgba(27,16,115,0.71); -fx-text-fill: #d3c4c4");
        chat.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
        chat.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                pane.getChildren().clear();
                try {
                    designChat(gameImages);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Button refresh = new Button("Refresh");
        refresh.setLayoutX(950);
        refresh.setLayoutY(70);
        refresh.setPrefSize(160, 50);
        refresh.setStyle("-fx-background-color: rgba(27,16,115,0.71); -fx-text-fill: #d3c4c4");
        refresh.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
        refresh.setOnMouseClicked(new EventHandler<MouseEvent>() {
            //TODO : Random 10 Players
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    pane.getChildren().clear();
                    designLobby(gameImages);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        TextField search = new TextField();
        search.setPromptText("Search Game Id");
        search.setPrefSize(560, 30);
        search.setTranslateX(500);
        search.setTranslateY(160);
        search.setFocusTraversable(false);
        search.setStyle("-fx-background-color: rgba(27,16,115,0.71); -fx-prompt-text-fill: white;" +
                "-fx-text-fill: white;");

        Button searchButton = new Button();
        searchButton.setLayoutX(1010);
        searchButton.setLayoutY(145);
        searchButton.setPrefSize(160, 50);
        searchButton.setBackground(new Background(new BackgroundImage(gameImages.getSearch(), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        searchButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                searchForGivenGameId(search.getText());
            }
        });


        VBox listOfAllGames = new VBox();
        if (pane.getChildren().contains(listOfAllGames)) pane.getChildren().remove(listOfAllGames);
        this.listOfAllGames = listOfAllGames;
        listOfAllGames.setPrefSize(400, 250);
        designListOfAllGames(gameImages);


        scrollPaneForMainList.setContent(listOfAllGames);
        scrollPaneForMainList.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPaneForMainList.setLayoutX(300);
        scrollPaneForMainList.setLayoutY(300);
        scrollPaneForMainList.setPrefWidth(415);
        scrollPaneForMainList.setPrefHeight(250);
        scrollPaneForMainList.setStyle("-fx-background-color: #1b1073");
        scrollPaneForMainList.setVisible(true);

        pane.getChildren().add(back);
        pane.getChildren().add(createNewRequest);
        pane.getChildren().add(chat);
        pane.getChildren().add(refresh);
        pane.getChildren().add(search);
        pane.getChildren().add(searchButton);
        pane.getChildren().add(scrollPaneForMainList);
    }

    private void designListOfAllGames(GameImages gameImages) {
        listOfAllGames.setStyle("-fx-background-color: #03183b");
        listOfAllGames.setSpacing(2);
        listOfAllGames.setLayoutX(100);
        listOfAllGames.setLayoutY(200);

        ImageView shieldImage = null;

        for (int i = 0; i < Manage.allGames.size(); i++) {
            Game game = Manage.allGames.get(i);

            if (i % 4 == 0) {
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
            gameIdHBox.setPrefSize(20, 10);

            String id = game.getId();
            Text gameId = new Text();
            gameId.setText("Id: " + game.getId());
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

    public void designVboxOfGameInfo(String gameId) {
        if (pane.getChildren().contains(listOfGameInfo)) pane.getChildren().remove(listOfGameInfo);
        this.listOfGameInfo = new VBox();
        listOfGameInfo.setSpacing(5);
        String listOfGamePlayers = "Players:\n";
        Game game = Manage.findGameById(gameId);

        if (game != null) {

            ImageView imageView = new ImageView(game.getImageView().getImage());
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            imageView.setTranslateX(75);

            Text idOfGame = new Text("Game Id: " + game.getId());
            idOfGame.setFill(Color.WHITE);
            idOfGame.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
            idOfGame.setTranslateX(17);
            idOfGame.setTranslateY(20);

            Text capacity = new Text("Game capacity: " + game.getCapacity());
            capacity.setFill(Color.WHITE);
            capacity.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
            capacity.setTranslateX(17);
            capacity.setTranslateY(30);

            Text admin = new Text("Admin: " + game.getGameAdmin().getNickname());
            admin.setFill(Color.WHITE);
            admin.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
            admin.setTranslateX(17);
            admin.setTranslateY(40);

            for (int i = 1; i < game.getAllPlayers().size(); i++) {
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

            listOfGameInfo.setPrefSize(230, 300);
            listOfGameInfo.setStyle("-fx-background-color: #1b1073");
            listOfGameInfo.setLayoutX(1000);
            listOfGameInfo.setLayoutY(300);

            pane.getChildren().add(listOfGameInfo);
        }
    }

    private void designButtonsOfChosenGame(VBox listOfGameInfo, Game game) {
        //TODO : Access of other members to private and public games
        User currentUser = User.getCurrentUser();

        if (currentUser.getUsername().equals(game.getGameAdmin().getUsername())) { //Admin

            Button changePrivacy = new Button();
            if (game.isPublic()) {
                changePrivacy.setText("Private");
            } else {
                changePrivacy.setText("Public");
            }
            changePrivacy.setTranslateX(30);
            changePrivacy.setTranslateY(70);
            changePrivacy.setPrefSize(50, 10);
            changePrivacy.setStyle("-fx-background-color: #55288c; -fx-text-fill: #d3c4c4");
            changePrivacy.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 10));
            changePrivacy.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (changePrivacy.getText().equals("Private")) changePrivacyOfGame(game, false);
                    if (changePrivacy.getText().equals("Public")) changePrivacyOfGame(game, true);
                }
            });

            Button leaveGame = new Button();
            leaveGame.setText("Leave");
            leaveGame.setTranslateX(100);
            leaveGame.setTranslateY(47);
            leaveGame.setPrefSize(50, 10);
            leaveGame.setStyle("-fx-background-color: #55288c; -fx-text-fill: #d3c4c4");
            leaveGame.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 10));
            leaveGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    leaveTheGame(game);
                }
            });

            Button close = new Button();
            close.setText("Close");
            close.setTranslateX(170);
            close.setTranslateY(24);
            close.setPrefSize(50, 10);
            close.setStyle("-fx-background-color: #55288c; -fx-text-fill: #d3c4c4");
            close.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 10));
            close.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    closeTheInfoBox(listOfGameInfo);
                }
            });


            listOfGameInfo.getChildren().add(changePrivacy);
            listOfGameInfo.getChildren().add(leaveGame);
            listOfGameInfo.getChildren().add(close);

        } else if (game.isMemberOfGame(currentUser.getUsername()) != null) { //Game Member
            Button leaveGame = new Button();
            leaveGame.setText("Leave");
            leaveGame.setTranslateX(50);
            leaveGame.setTranslateY(60);
            leaveGame.setPrefSize(50, 10);
            leaveGame.setStyle("-fx-background-color: #55288c; -fx-text-fill: #d3c4c4");
            leaveGame.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 10));
            leaveGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    leaveTheGame(game);
                }
            });

            Button close = new Button();
            close.setText("Close");
            close.setTranslateX(130);
            close.setTranslateY(38);
            close.setPrefSize(50, 10);
            close.setStyle("-fx-background-color: #55288c; -fx-text-fill: #d3c4c4");
            close.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 10));
            close.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    closeTheInfoBox(listOfGameInfo);
                }
            });

            listOfGameInfo.getChildren().add(leaveGame);
            listOfGameInfo.getChildren().add(close);

        } else { //Stranger
            Button join = new Button();
            join.setText("Join");
            join.setTranslateX(50);
            join.setTranslateY(60);
            join.setPrefSize(50, 10);
            join.setStyle("-fx-background-color: #55288c; -fx-text-fill: #d3c4c4");
            join.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 10));
            join.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    joinTheGame(game);
                }
            });

            Button close = new Button();
            close.setText("Close");
            close.setTranslateX(130);
            close.setTranslateY(38);
            close.setPrefSize(50, 10);
            close.setStyle("-fx-background-color: #55288c; -fx-text-fill: #d3c4c4");
            close.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 10));
            close.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    closeTheInfoBox(listOfGameInfo);
                }
            });

            if (game.getAllPlayers().size() < game.getCapacity()) {
                listOfGameInfo.getChildren().add(join);
            }
            listOfGameInfo.getChildren().add(close);

        }
    }

    private void changePrivacyOfGame(Game game, boolean status) {
        //TODO : It should change the privacy settings in the server as well;
        game.setPublic(status);

    }

    private void leaveTheGame(Game game) {
        //TODO : You should remove the player from the list in the server
        game.getAllPlayers().remove(User.getCurrentUser());
        User.getCurrentUser().getMyGameList().remove(game);
        if (User.getCurrentUser().getUsername().equals(game.getGameAdmin().getUsername())) {
            game.setGameAdmin(game.getAllPlayers().get(0));
        }
    }

    private void closeTheInfoBox(VBox listOfGameInfo) {
        pane.getChildren().remove(listOfGameInfo);
    }

    private void joinTheGame(Game game) {
        //TODO : You should add the user to the list of users of game in the server
        game.getAllPlayers().add(User.getCurrentUser());
        User.getCurrentUser().getMyGameList().add(game);
    }

    private void searchForGivenGameId(String searchedGameId) {
        pane.getChildren().remove(scrollPaneForSearchedList);
        VBox similarResultsInSearch = new VBox();

        ArrayList<Game> allMatchedGames = findAllMatchingGames(searchedGameId);
        scrollPaneForMainList.setVisible(false);
        pane.getChildren().remove(listOfGameInfo);
        if (allMatchedGames != null && allMatchedGames.size() > 0) {

            similarResultsInSearch.setStyle("-fx-background-color: #03183b");
            similarResultsInSearch.setSpacing(2);
            similarResultsInSearch.setLayoutX(100);
            similarResultsInSearch.setLayoutY(200);

            for (int i = 0; i < allMatchedGames.size(); i++) {
                Game game = allMatchedGames.get(i);

                game.getImageView().setFitWidth(50);
                game.getImageView().setFitHeight(50);

                HBox gameIdHBox = new HBox();
                gameIdHBox.setPrefSize(70, 10);

                String id = game.getId();
                Text gameId = new Text();
                gameId.setText("Id: " + game.getId());
                gameId.setFill(Color.WHITE);
                gameId.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
                gameId.setTranslateX(-25);
                gameId.setTranslateY(5);

                gameIdHBox.setStyle("-fx-background-color: #1b1073;");
                gameIdHBox.getChildren().add(game.getImageView());
                gameIdHBox.getChildren().add(gameId);
                gameIdHBox.setSpacing(100);

                gameIdHBox.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        designVboxOfGameInfo(id);
                    }
                });

                similarResultsInSearch.getChildren().add(gameIdHBox);
            }
            scrollPaneForSearchedList.setPrefWidth(400);
            scrollPaneForSearchedList.setContent(similarResultsInSearch);
            scrollPaneForSearchedList.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scrollPaneForSearchedList.setLayoutX(300);
            scrollPaneForSearchedList.setLayoutY(300);
            scrollPaneForSearchedList.setPrefWidth(400);


            scrollPaneForSearchedList.setStyle("-fx-background-color: #1b1073");
            scrollPaneForSearchedList.setVisible(true);
            pane.getChildren().add(scrollPaneForSearchedList);
        }
    }

    private ArrayList<Game> findAllMatchingGames(String searchedStatement) {
        ArrayList<Game> allMatchedGames = new ArrayList<>();
        for (Game game : Manage.allGames) {
            if (game.getId().contains(searchedStatement)) {
                allMatchedGames.add(game);
            }
        }
        return allMatchedGames;
    }

    private void createNewRequestStage() {

        pane.getChildren().remove(listOfAllGames);
        pane.getChildren().remove(listOfGameInfo);
        pane.getChildren().remove(scrollPaneForSearchedList);
        pane.getChildren().remove(scrollPaneForMainList);

        TextField gameId = new TextField();
        gameId.setPrefSize(260, 60);
        gameId.setTranslateX(300);
        gameId.setTranslateY(310);
        gameId.setPromptText("Enter Game Id");
        gameId.setFocusTraversable(false);
        gameId.setStyle("-fx-background-color: rgba(85,40,140,0.71); -fx-prompt-text-fill: #ffffff;" +
                "-fx-text-fill: #ffffff");

        TextField capacity = new TextField();
        capacity.setPrefSize(260, 60);
        capacity.setTranslateX(620);
        capacity.setTranslateY(310);
        capacity.setPromptText("Enter Game's Capacity");
        capacity.setFocusTraversable(false);
        capacity.setStyle("-fx-background-color: rgba(85,40,140,0.71); -fx-prompt-text-fill: #ffffff;" +
                "-fx-text-fill: #ffffff");

        TextField typeOfGame = new TextField();
        typeOfGame.setPrefSize(260, 60);
        typeOfGame.setTranslateX(940);
        typeOfGame.setTranslateY(310);
        typeOfGame.setPromptText("Enter Game's Type");
        typeOfGame.setFocusTraversable(false);
        typeOfGame.setStyle("-fx-background-color: rgba(85,40,140,0.71); -fx-prompt-text-fill: #ffffff;" +
                "-fx-text-fill: #ffffff");

        Button createRequest = new Button("Create Request");
        createRequest.setLayoutX(665);
        createRequest.setLayoutY(500);
        createRequest.setPrefSize(160, 50);
        createRequest.setStyle("-fx-background-color: rgba(27,16,115,0.71); -fx-text-fill: #d3c4c4");
        createRequest.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
        createRequest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    if (createRequestLogicStuff(gameId.getText(), capacity.getText(), typeOfGame.getText())) {
                        pane.getChildren().clear();
                        designLobby(gameImages);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        pane.getChildren().add(gameId);
        pane.getChildren().add(capacity);
        pane.getChildren().add(typeOfGame);
        pane.getChildren().add(createRequest);
    }

    private boolean createRequestLogicStuff(String gameId, String capacity, String typeOfGame) {
        pane.getChildren().remove(emptyGameId);
        pane.getChildren().remove(emptyCapacity);
        pane.getChildren().remove(emptyTypeOfGame);
        pane.getChildren().remove(invalidTypeOfGame);
        pane.getChildren().remove(invalidCapacity);


        emptyGameId.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        emptyGameId.setFill(Color.rgb(255, 255, 255, 1));
        emptyGameId.setVisible(false);
        emptyGameId.setTranslateX(300);
        emptyGameId.setTranslateY(290);

        emptyCapacity.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        emptyCapacity.setFill(Color.rgb(255, 255, 255, 1));
        emptyCapacity.setVisible(false);
        emptyCapacity.setTranslateX(630);
        emptyCapacity.setTranslateY(290);

        emptyTypeOfGame.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        emptyTypeOfGame.setFill(Color.rgb(255, 255, 255, 1));
        emptyTypeOfGame.setVisible(false);
        emptyTypeOfGame.setTranslateX(950);
        emptyTypeOfGame.setTranslateY(290);

        invalidTypeOfGame.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        invalidTypeOfGame.setFill(Color.rgb(255, 255, 255, 1));
        invalidTypeOfGame.setVisible(false);
        invalidTypeOfGame.setTranslateX(950);
        invalidTypeOfGame.setTranslateY(290);

        invalidCapacity.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        invalidCapacity.setFill(Color.rgb(255, 255, 255, 1));
        invalidCapacity.setVisible(false);
        invalidCapacity.setTranslateX(630);
        invalidCapacity.setTranslateY(290);

        if (gameId != null && gameId.length() != 0) {
            if (capacity != null && capacity.length() != 0) {
                if (typeOfGame != null && typeOfGame.length() != 0) {
                    boolean isPublic = false;
                    boolean validType;
                    if (typeOfGame.equalsIgnoreCase("private")) {
                        validType = true;
                    } else if (typeOfGame.equalsIgnoreCase("public")) {
                        isPublic = true;
                        validType = true;
                    } else {
                        validType = false;
                    }
                    if (isNumber(capacity)) {
                        if (validType) {
                            Game game = new Game(User.getCurrentUser(), gameId, isPublic, Integer.parseInt(capacity));
                            Manage.allGames.add(game);
                            return true;
                        } else {
                            invalidTypeOfGame.setVisible(true);
                            pane.getChildren().add(invalidTypeOfGame);
                        }
                    } else {
                        invalidCapacity.setVisible(true);
                        pane.getChildren().add(invalidCapacity);
                    }
                } else {
                    emptyTypeOfGame.setVisible(true);
                    pane.getChildren().add(emptyTypeOfGame);
                    return false;
                }
            } else {
                emptyCapacity.setVisible(true);
                pane.getChildren().add(emptyCapacity);
                return false;
            }
        } else {
            emptyGameId.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
            emptyGameId.setFill(Color.rgb(255, 255, 255, 1));
            emptyGameId.setTranslateX(300);
            emptyGameId.setTranslateY(290);
            emptyGameId.setVisible(true);
            pane.getChildren().add(emptyGameId);
            return false;
        }
        return false;
    }

    public boolean isNumber(String givenString) {
        return givenString.matches("\\d+");
    }

    private void designChat(GameImages gameImages) throws IOException {

        Button refresh = new Button("Refresh");
        refresh.setLayoutX(1050);
        refresh.setLayoutY(70);
        refresh.setPrefSize(160, 50);
        refresh.setStyle("-fx-background-color: rgba(27,16,115,0.71); -fx-text-fill: #d3c4c4");
        refresh.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
        refresh.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                pane.getChildren().clear();
                try {
                    designChat(gameImages);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Button lobby = new Button("Lobby");
        lobby.setLayoutX(350);
        lobby.setLayoutY(70);
        lobby.setPrefSize(160, 50);
        lobby.setStyle("-fx-background-color: rgba(27,16,115,0.71); -fx-text-fill: #d3c4c4");
        lobby.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
        lobby.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    pane.getChildren().clear();
                    designLobby(gameImages);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        TextField search = new TextField();
        search.setPromptText("Search player's Id");
        search.setPrefSize(280, 30);
        search.setTranslateX(300);
        search.setTranslateY(160);
        search.setFocusTraversable(false);
        search.setStyle("-fx-background-color: rgba(27,16,115,0.71); -fx-prompt-text-fill: white;" +
                "-fx-text-fill: white;");

        Button searchButton = new Button();
        searchButton.setLayoutX(590);
        searchButton.setLayoutY(157);
        searchButton.setPrefSize(30, 30);
        searchButton.setBackground(new Background(new BackgroundImage(gameImages.getSearch(), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        searchButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //TODO : We should find out from the server whether the given username exists or not

            }
        });

        Button privateChatList = new Button();
        privateChatList.setLayoutX(300);
        privateChatList.setLayoutY(200);
        privateChatList.setPrefSize(80, 80);
        privateChatList.setBackground(new Background(new BackgroundImage(gameImages.getPrivateChat(), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        privateChatList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //Todo: here we should get all the private chats from server
                showAllPrivateChats();
            }
        });

        Button groupChatList = new Button();
        groupChatList.setLayoutX(400);
        groupChatList.setLayoutY(200);
        groupChatList.setPrefSize(80, 80);
        groupChatList.setBackground(new Background(new BackgroundImage(gameImages.getGroupChat(), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        groupChatList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //Todo: here we should get all the groups from server
                showAllGroupChats();
            }
        });

        Button globalChat = new Button();
        globalChat.setLayoutX(500);
        globalChat.setLayoutY(200);
        globalChat.setPrefSize(80, 80);
        globalChat.setBackground(new Background(new BackgroundImage(gameImages.getGlobalChat(), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        globalChat.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //Todo: here we should get global chat from server
                try {
                    Socket socket =  new Socket("localhost",6220);
                    ChatMethods chatMethods =  new ChatMethods(socket);
                    chatMethods.enterToChat();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                showGlobalChat();
            }
        });

        designScrollPaneOfAllChats();


        pane.getChildren().add(lobby);
        pane.getChildren().add(refresh);
        pane.getChildren().add(search);
        pane.getChildren().add(searchButton);
        pane.getChildren().add(privateChatList);
        pane.getChildren().add(groupChatList);
        pane.getChildren().add(globalChat);


    }

    private User searchForGivenUserId(String text) {
        for (User user : Manage.allUsers) {
            if (user.getUsername().equals(text)) {
                return user;
            }
        }
        return null;
    }

    private void designScrollPaneOfAllChats() {
        VBox chatList = new VBox();
        chatList.setStyle("-fx-background-color: #4b187e");
        chatList.setSpacing(0.5);
        if (!User.getCurrentUser().getChats().isEmpty()) {
            for (Chat chat : User.getCurrentUser().getChats()) {
                User receiver = searchForGivenUserId(chat.getName());
                Circle clip = new Circle(25, 25, 25);
                Image profile = receiver.getAvatar().getImage();
                clip.setFill(new ImagePattern(profile));
                clip.setStroke(Color.rgb(75,24,126));
                HBox chatBox = new HBox();
                chatBox.setPrefSize(284.1, 10);
                chatBox.setSpacing(70);

                Text chatName = new Text();
                chatName.setText(receiver.getUsername());
                chatName.setFill(Color.WHITE);
                chatName.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
                chatName.setTranslateX(-50);
                chatName.setTranslateY(10);

                chatBox.setStyle("-fx-background-color: rgba(27,16,115,0.71);");
                chatBox.getChildren().add(clip);
                chatBox.getChildren().add(chatName);
                chatBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        openChat(chat);
                    }
                });
                chatList.getChildren().add(chatBox);
            }
            scrollPaneForChatList.setPrefWidth(300);
            scrollPaneForChatList.setContent(chatList);
            scrollPaneForChatList.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scrollPaneForChatList.setLayoutX(300);
            scrollPaneForChatList.setLayoutY(300);

            scrollPaneForChatList.setStyle("-fx-background-color: #1b1073");
            scrollPaneForChatList.setVisible(true);
            pane.getChildren().add(scrollPaneForChatList);
        }
    }

    private void openChat(Chat chat) {
        pane.getChildren().remove(chatBox);
        pane.getChildren().remove(send);
        pane.getChildren().remove(chatTextField);
        pane.getChildren().remove(scrollPaneForChatBox);
        
        chatBox = new VBox();
        chatBox.setStyle("-fx-background-color: #4b187e");
        chatBox.setSpacing(0.5);


        chatTextField.setPromptText("Send Message");
        chatTextField.setPrefSize(280, 30);
        chatTextField.setTranslateX(860);
        chatTextField.setTranslateY(640);
        chatTextField.setFocusTraversable(false);
        chatTextField.setStyle("-fx-background-color: rgba(89,29,180,0.71); -fx-prompt-text-fill: white;" +
                "-fx-text-fill: white;");


        send.setLayoutX(1160);
        send.setLayoutY(617);
        send.setPrefSize(80, 80);
        send.setBackground(new Background(new BackgroundImage(gameImages.getSend(), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        send.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //TODO :Here we should send the message to the chat server in order to deliver it to the reciever
            }
        });

        //TODO :Here we should get all the messages from chat server and then loop through it

        scrollPaneForChatBox.setPrefWidth(300);
        scrollPaneForChatBox.setContent(chatBox);
        scrollPaneForChatBox.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPaneForChatBox.setLayoutX(850);
        scrollPaneForChatBox.setLayoutY(300);

        scrollPaneForChatBox.setStyle("-fx-background-color: #1b1073");
        scrollPaneForChatBox.setVisible(true);
        pane.getChildren().add(chatTextField);
        pane.getChildren().add(send);
        pane.getChildren().add(scrollPaneForChatBox);
    }

    public void showAllPrivateChats(){
        removeRemainedStuff();

    }
    public void showAllGroupChats(){
        removeRemainedStuff();

    }
    public void showGlobalChat(){
        removeRemainedStuff();
    }

    public void removeRemainedStuff(){
        pane.getChildren().remove(scrollPaneForChatList);
        pane.getChildren().remove(scrollPaneForChatBox);
        pane.getChildren().remove(send);
        pane.getChildren().remove(chatTextField);
    }
}

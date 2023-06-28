package view;

import javafx.application.Application;
import javafx.css.Match;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import model.*;

import view.ImageAndBackground.GameImages;
import view.Model.NewHBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Lobby extends Application {
    boolean isPrivateChatPressed = false;
    boolean isGroupChatPressed = false;
    public GameImages gameImages;
    public static Socket socket;

    public Pane pane;
    public VBox listOfAllGames;
    public VBox listOfGameInfo;
    public static VBox chatBox;
    public VBox chatRoomForm;
    public HBox chatMenuHeader;
    public ScrollPane scrollPaneForMainList = new ScrollPane();
    public ScrollPane scrollPaneForSearchedList = new ScrollPane();
    public ScrollPane scrollPaneForChatList = new ScrollPane();
    public ScrollPane scrollPaneForChatBox = new ScrollPane();
    public ScrollPane scrollPaneForChatCheckBox;

    public Button send = new Button();
    public TextField chatTextField = new TextField();
    public Text emptyGameId = new Text("Game Id field is empty!");
    public Text emptyCapacity = new Text("Capacity field is empty!");
    public Text emptyTypeOfGame = new Text("Game's Type field is empty!");
    public Text invalidTypeOfGame = new Text("The given game type is invalid!");
    public Text invalidCapacity = new Text("The given capacity is invalid!");
    public Text typeOfChatMenu = new Text("Chats");
    public TextField search = new TextField();
    public Button searchButton = new Button();
    public Button exit = new Button();
    public TextField searchBarForUsers = new TextField();
    public Button searchButtonForUsers = new Button();
    public ArrayList<NewHBox> chosenMembersForRoom = new ArrayList<>();
    public static ArrayList<Message> messages = new ArrayList<>();
    public static ArrayList<Text> texts = new ArrayList<>();
    public static ChatMethods chatMethods;
    public ArrayList<Game> allGameRequests = new ArrayList<>();
    public DataInputStream masterServerDataInputStream;
    public DataOutputStream masterServerDataOutputStream;

    //TODO : EventHandler for Back Button
    //TODO : Game should be closed if the number of members reach to the settled capacity

    public VBox cornerVBox = new VBox();
    public User user1;

    @Override
    public void start(Stage stage) throws Exception {

        Socket socket1 = new Socket("localhost", 8080);
        masterServerDataOutputStream = new DataOutputStream(socket1.getOutputStream());
        masterServerDataInputStream = new DataInputStream(socket1.getInputStream());
        user1 = new User("z", "s", "a", "s", "w", "q", 3);

        User user2 = new User("ali", "s", "a", "s", "w", "q", 3);
        User user3 = new User("ac", "s", "a", "s", "w", "q", 3);
        User user4 = new User("ad", "s", "a", "s", "w", "q", 3);
        User user5 = new User("ae", "s", "a", "s", "w", "q", 3);
        User.users.add(user2);
        User.users.add(user3);
        User.users.add(user4);
        User.users.add(user5);
        User.users.add(user1);

        User.setCurrentUser(user1);

        Game game = new Game(user2, "MyGame1", true, 5);
        allGameRequests.add(game);
        game.addToAllPlayers(user1);
        game.addToAllPlayers(user2);
        game.addToAllPlayers(user3);
        game.addToAllPlayers(user4);
        game.addToAllPlayers(user5);

        Game game2 = new Game(user1, "MyGame2", true, 5);
        allGameRequests.add(game2);
        Game game3 = new Game(user1, "MyGame3", true, 5);
        allGameRequests.add(game3);
        Game game4 = new Game(user1, "MyGame4", true, 5);
        allGameRequests.add(game4);
        Game game5 = new Game(user1, "MyGame5", true, 5);
        allGameRequests.add(game5);

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
        back.setLayoutX(350);
        back.setLayoutY(70);
        back.setPrefSize(160, 50);
        back.setStyle("-fx-background-color: rgba(27,16,115,0.71); -fx-text-fill: #d3c4c4");
        back.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
        //TODO : EventHandler

        Button createNewRequest = new Button("New Game Request");
        createNewRequest.setLayoutX(550);
        createNewRequest.setLayoutY(70);
        createNewRequest.setPrefSize(160, 50);
        createNewRequest.setStyle("-fx-background-color: rgba(27,16,115,0.71)3; -fx-text-fill: #d3c4c4");
        createNewRequest.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
        createNewRequest.setOnMouseClicked(mouseEvent -> createNewRequestStage());

        Button chat = new Button("Chat");
        chat.setLayoutX(750);
        chat.setLayoutY(70);
        chat.setPrefSize(160, 50);
        chat.setStyle("-fx-background-color: rgba(27,16,115,0.71); -fx-text-fill: #d3c4c4");
        chat.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
        chat.setOnMouseClicked(mouseEvent -> {
            pane.getChildren().clear();
            try {
                designChat(gameImages);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Button refresh = new Button("Refresh");
        refresh.setLayoutX(950);
        refresh.setLayoutY(70);
        refresh.setPrefSize(160, 50);
        refresh.setStyle("-fx-background-color: rgba(27,16,115,0.71); -fx-text-fill: #d3c4c4");
        refresh.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
        //TODO : Random 10 Players
        refresh.setOnMouseClicked(mouseEvent -> {
            try {
                refreshLobby();
                pane.getChildren().clear();
                designLobby(gameImages);
            } catch (IOException e) {
                throw new RuntimeException(e);
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
        searchButton.setOnMouseClicked(mouseEvent -> searchForGivenGameId(search.getText()));


        VBox listOfAllGames = new VBox();
        pane.getChildren().remove(listOfAllGames);
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

    public void refreshLobby() throws IOException {
        allGameRequests.clear();
        masterServerDataOutputStream.writeUTF("REFRESH_LOBBY");
        String input = masterServerDataInputStream.readUTF();
        String[] split = input.split("\n");
        for (int j = 0; j < split.length; j += 2) {
            String[] game = split[j].split("#");
            User user = User.getUserByName(game[0]);
            if (game[3].equals("public")) {
                Game game2 = new Game(user, game[1], true, Integer.parseInt(game[2]));
                addMembers(split, j, game2);
            } else {
                Game game1 = new Game(user, game[1], false, Integer.parseInt(game[2]));
                addMembers(split, j, game1);
            }
        }
    }

    private void addMembers(String[] split, int j, Game game1) {
        String[] members = split[j + 1].split("#");
        for (int g = 0; g < members.length; g++) {
            User user2 = User.getUserByName(members[g]);
            game1.allPlayers.add(user2);
        }
        allGameRequests.add(game1);
    }

    private void designListOfAllGames(GameImages gameImages) {

        listOfAllGames.setStyle("-fx-background-color: #03183b");
        listOfAllGames.setSpacing(2);
        listOfAllGames.setLayoutX(100);
        listOfAllGames.setLayoutY(200);

        ImageView shieldImage = null;
        ArrayList<Game> controlledView = new ArrayList<>();
        for (Game allGameRequest : allGameRequests) {
            if (allGameRequest.isPublic()) {
                controlledView.add(allGameRequest);
            } else {
                if (allGameRequest.getGameAdmin().getUsername().equals(User.getCurrentUser().getUsername())) {
                    controlledView.add(allGameRequest);
                }
            }
        }
        while (controlledView.size() > 10) {
            controlledView.remove(controlledView.size() - 1);
        }

        for (int i = 0; i < controlledView.size(); i++) {
            Game game = controlledView.get(i);

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

            gameIdHBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    designVboxOfGameInfo(id);
                }
            });
            listOfAllGames.getChildren().add(gameIdHBox);

        }

    }

    public Game findGameById(String gameId) {
        for (int h = 0; h < allGameRequests.size(); h++) {
            if (allGameRequests.get(h).getId().equals(gameId)) {
                return allGameRequests.get(h);
            }
        }
        return null;
    }

    public void designVboxOfGameInfo(String gameId) {
        pane.getChildren().remove(listOfGameInfo);
        this.listOfGameInfo = new VBox();
        listOfGameInfo.setSpacing(5);
        String listOfGamePlayers = "Players:\n";

        Game game = findGameById(gameId);

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

            Text admin = new Text("Admin: " + game.getGameAdmin().getUsername());
            admin.setFill(Color.WHITE);
            admin.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
            admin.setTranslateX(17);
            admin.setTranslateY(40);

            for (int i = 1; i < game.getAllPlayers().size(); i++) {
                User player = game.getAllPlayers().get(i);
                listOfGamePlayers = listOfGamePlayers.concat(player.getUsername());
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
            changePrivacy.setTranslateX(10);
            changePrivacy.setTranslateY(70);
            changePrivacy.setPrefSize(50, 10);
            changePrivacy.setStyle("-fx-background-color: #55288c; -fx-text-fill: #d3c4c4");
            changePrivacy.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 8));
            changePrivacy.setOnMouseClicked(mouseEvent -> {
                if (changePrivacy.getText().equals("Private")) {
                    try {
                        changePrivacyOfGame(game, false);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (changePrivacy.getText().equals("Public")) {
                    try {
                        changePrivacyOfGame(game, true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            Button leaveGame = new Button();
            leaveGame.setText("Leave");
            leaveGame.setTranslateX(70);
            leaveGame.setTranslateY(47);
            leaveGame.setPrefSize(50, 10);
            leaveGame.setStyle("-fx-background-color: #55288c; -fx-text-fill: #d3c4c4");
            leaveGame.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 8));
            leaveGame.setOnMouseClicked(mouseEvent -> {
                try {
                    leaveTheGame(game);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            Button close = new Button();
            close.setText("Close");
            close.setTranslateX(140);
            close.setTranslateY(24);
            close.setPrefSize(50, 10);
            close.setStyle("-fx-background-color: #55288c; -fx-text-fill: #d3c4c4");
            close.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 8));
            close.setOnMouseClicked(mouseEvent -> closeTheInfoBox(listOfGameInfo));

            Button startGame = new Button();
            startGame.setText("Start");
            startGame.setTranslateX(130);
            startGame.setTranslateY(24);
            startGame.setPrefSize(50, 10);
            startGame.setStyle("-fx-background-color: #55288c; -fx-text-fill: #d3c4c4");
            startGame.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 8));
            startGame.setOnMouseClicked(mouseEvent -> closeTheInfoBox(listOfGameInfo));


            listOfGameInfo.getChildren().add(changePrivacy);
            listOfGameInfo.getChildren().add(leaveGame);
            listOfGameInfo.getChildren().add(close);
            listOfGameInfo.getChildren().add(startGame);

        } else if (game.isMemberOfGame(currentUser.getUsername()) != null) { //Game Member
            Button leaveGame = new Button();
            leaveGame.setText("Leave");
            leaveGame.setTranslateX(50);
            leaveGame.setTranslateY(60);
            leaveGame.setPrefSize(50, 10);
            leaveGame.setStyle("-fx-background-color: #55288c; -fx-text-fill: #d3c4c4");
            leaveGame.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 10));
            leaveGame.setOnMouseClicked(mouseEvent -> {
                try {
                    leaveTheGame(game);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            Button close = new Button();
            close.setText("Close");
            close.setTranslateX(130);
            close.setTranslateY(38);
            close.setPrefSize(50, 10);
            close.setStyle("-fx-background-color: #55288c; -fx-text-fill: #d3c4c4");
            close.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 10));
            close.setOnMouseClicked(mouseEvent -> closeTheInfoBox(listOfGameInfo));

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
            join.setOnMouseClicked(mouseEvent -> {
                try {
                    joinTheGame(game);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            Button close = new Button();
            close.setText("Close");
            close.setTranslateX(130);
            close.setTranslateY(38);
            close.setPrefSize(50, 10);
            close.setStyle("-fx-background-color: #55288c; -fx-text-fill: #d3c4c4");
            close.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 10));
            close.setOnMouseClicked(mouseEvent -> closeTheInfoBox(listOfGameInfo));

            if (game.getAllPlayers().size() < game.getCapacity()) {
                listOfGameInfo.getChildren().add(join);
            }
            listOfGameInfo.getChildren().add(close);

        }
    }

    private void changePrivacyOfGame(Game game, boolean status) throws IOException {
        masterServerDataOutputStream.writeUTF("PRIVATE_PUBLIC");
        if (status) {
            masterServerDataOutputStream.writeUTF(game.getId() + '#' + "public");
        } else {
            masterServerDataOutputStream.writeUTF(game.getId() + '#' + "private");
        }
        refreshLobby();
        pane.getChildren().clear();
        designLobby(gameImages);

    }

    public synchronized void leaveTheGame(Game game) throws IOException {
        masterServerDataOutputStream.writeUTF("LEAVE-GAME");
        masterServerDataOutputStream.writeUTF(game.getId() + '#' + User.getCurrentUser().getUsername());
        refreshLobby();
        pane.getChildren().clear();
        designLobby(gameImages);
    }

    private void closeTheInfoBox(VBox listOfGameInfo) {
        pane.getChildren().remove(listOfGameInfo);
    }


    private synchronized void joinTheGame(Game game) throws IOException {
        addToGameRequest(game);
//        game.getAllPlayers().add(User.getCurrentUser());
//        User.getCurrentUser().getMyGameList().add(game);
    }

    public void addToGameRequest(Game game) throws IOException {
        masterServerDataOutputStream.writeUTF("JOIN-GAME");
        masterServerDataOutputStream.writeUTF(game.getId() + '#' + User.getCurrentUser().getUsername());
        refreshLobby();
        pane.getChildren().clear();
        designLobby(gameImages);

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

                gameIdHBox.onMouseClickedProperty().set(mouseEvent -> designVboxOfGameInfo(id));

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
        for (Game game : allGameRequests) {
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
        createRequest.setOnMouseClicked(mouseEvent -> {
            try {
                if (createRequestLogicStuff(gameId.getText(), capacity.getText(), typeOfGame.getText())) {
                    masterServerDataOutputStream.writeUTF("ADD_NEW_GAME_REQUEST");
                    //TODO : replace username with this User.getCurrentUser()
                    masterServerDataOutputStream.writeUTF(User.getCurrentUser().getUsername() + "#" + gameId.getText() + "#" + capacity.getText() + "#" + typeOfGame.getText());
                    pane.getChildren().clear();
                    designLobby(gameImages);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
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
                            allGameRequests.add(game);
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
        pane.setBackground(null);
        cornerVBox.setStyle("-fx-background-color: #1384ee");
        cornerVBox.setPrefWidth(100);
        cornerVBox.setPrefHeight(870);

        pane.getChildren().add(cornerVBox);
        designButtonsOfChatMenu();
        designHeaderOfChatMenu();
        designSearchBarOfChatMenu();
        designScrollPaneOfAllChats();

    }

    private void designSearchBarOfChatMenu() {
        search.setPromptText("Search player's Id");
        search.setPrefSize(440, 30);
        search.setLayoutX(110);
        search.setLayoutY(170);
        search.setFocusTraversable(false);
        search.setStyle("-fx-background-color: rgba(19,132,238,0.85); -fx-prompt-text-fill: white;" +
                "-fx-text-fill: white;-fx-background-radius: 10px");

        searchButton.setLayoutX(570);
        searchButton.setLayoutY(170);
        searchButton.setPrefSize(30, 30);
        searchButton.setBackground(new Background(new BackgroundImage(gameImages.getSearch(), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        searchButton.setOnMouseClicked(mouseEvent -> {
            if (isPrivateChatPressed) {
                try {
                    System.out.println("entered try catch");
                    isPrivateChatPressed = false;
                    Chat privateChat = ChatMethods.addNewPrivateChat(search.getText());
                    showPrivateChatBox(privateChat);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else if (isGroupChatPressed) {
                try {
                    System.out.println("Entered try catch");
                    isGroupChatPressed = false;
                    Chat privateChat = ChatMethods.addNewGroupChat(search.getText());
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        pane.getChildren().add(search);
        pane.getChildren().add(searchButton);
    }


    private void designHeaderOfChatMenu() {
        chatMenuHeader = new HBox();
        chatMenuHeader.setStyle("-fx-background-color: rgba(19,132,238,0.85); -fx-background-radius: 10px;");
        chatMenuHeader.setPrefWidth(500);
        chatMenuHeader.setPrefHeight(150);
        chatMenuHeader.setLayoutX(110);
        chatMenuHeader.setLayoutY(10);
        typeOfChatMenu.setText("Chats");
        typeOfChatMenu.setFill(Color.WHITE);
        typeOfChatMenu.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 40));
        chatMenuHeader.getChildren().add(typeOfChatMenu);
        typeOfChatMenu.setTextAlignment(TextAlignment.CENTER);
        typeOfChatMenu.setTranslateX(200);
        typeOfChatMenu.setTranslateY(40);
        pane.getChildren().add(chatMenuHeader);
    }

    private void designButtonsOfChatMenu() throws IOException {
        Button refresh = new Button();
        refresh.setLayoutX(25);
        refresh.setLayoutY(70);
        refresh.setPrefSize(50, 50);
        refresh.setBackground(new Background(new BackgroundImage(gameImages.getReload(), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        refresh.setOnMouseClicked(mouseEvent -> {
            pane.getChildren().clear();
            try {
                designChat(gameImages);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Button lobby = new Button();
        lobby.setLayoutX(25);
        lobby.setLayoutY(170);
        lobby.setPrefSize(50, 50);
        lobby.setBackground(new Background(new BackgroundImage(gameImages.getLobby(), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        lobby.setOnMouseClicked(mouseEvent -> {
            try {
                pane.getChildren().clear();
                designLobby(gameImages);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Button createRoom = new Button();
        createRoom.setLayoutX(25);
        createRoom.setLayoutY(260);
        createRoom.setPrefSize(50, 50);
        createRoom.setBackground(new Background(new BackgroundImage(gameImages.getCreateRoom(), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        createRoom.setOnMouseClicked(mouseEvent -> {
            createChatRoom();
        });

        Button makePrivateChat = new Button();
        makePrivateChat.setLayoutX(25);
        makePrivateChat.setLayoutY(370);
        makePrivateChat.setPrefSize(50, 50);
        makePrivateChat.setBackground(new Background(new BackgroundImage(gameImages.getPrivateChat(), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        makePrivateChat.setOnMouseClicked(mouseEvent -> {
            isPrivateChatPressed = true;

        });

        Button groupChatList = new Button();
        groupChatList.setLayoutX(25);
        groupChatList.setLayoutY(480);
        groupChatList.setPrefSize(50, 50);
        groupChatList.setBackground(new Background(new BackgroundImage(gameImages.getGroupChat(), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        groupChatList.setOnMouseClicked(mouseEvent -> {
            //Todo: here we should get all the groups from server
            showAllGroupChats();
        });

        Button globalChat = new Button();
        globalChat.setLayoutX(25);
        globalChat.setLayoutY(590);
        globalChat.setPrefSize(50, 50);
        globalChat.setBackground(new Background(new BackgroundImage(gameImages.getGlobalChat(), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        socket = new Socket("localhost", 8080);
        globalChat.setOnMouseClicked(mouseEvent -> {
            try {
                Socket socket = new Socket("localhost", 8000);
                chatMethods = new ChatMethods(socket);
                messages = chatMethods.enterToChat();
                showGlobalChat(messages, chatMethods);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        pane.getChildren().add(lobby);
        pane.getChildren().add(createRoom);
        pane.getChildren().add(refresh);
        pane.getChildren().add(makePrivateChat);
        pane.getChildren().add(groupChatList);
        pane.getChildren().add(globalChat);
    }

    private void searchBoxOfChatMenu() {

    }

    private User searchForGivenUserId(String text) {
        for (User user : Manage.allUsers) {
            if (user.getUsername().equals(text)) {
                return user;
            }
        }
        return null;
    }

    private void designScrollPaneOfAllChats() throws IOException {
        Manage.masterServerDataInputStream = new DataInputStream(socket.getInputStream());
        Manage.masterServerDataOutputStream = new DataOutputStream(socket.getOutputStream());
        Manage.masterServerDataOutputStream.writeUTF("GET_CHATS");
        String allChats = Manage.masterServerDataInputStream.readUTF();
        ArrayList<Chat> myChats = Chat.convertChatsToJsonForm(allChats);
        System.out.println(myChats.size());
        User.getCurrentUser().setChats(myChats);
        setChatList();
    }

    private void setChatList() {
        VBox chatList = new VBox();
        chatList.setStyle("-fx-background-color: #4b187e; " +
                "-fx-background-radius: 10px;");
        chatList.setSpacing(0.5);
        if (!User.getCurrentUser().getChats().isEmpty()) {
            for (Chat chat : User.getCurrentUser().getChats()) {

                User receiver = searchForGivenUserId(chat.getName());//TODO : Do we need to take it directly from server?
                Circle clip = new Circle(25, 25, 25);
                Image profile = receiver.getAvatar().getImage();
                clip.setFill(new ImagePattern(profile));
                clip.setStroke(Color.rgb(26, 11, 136));

                HBox chatBox = new HBox();
                chatBox.setPrefSize(284.1, 10);
                chatBox.setSpacing(70);

                Text chatName = new Text();
                chatName.setText(receiver.getUsername());
                chatName.setFill(Color.WHITE);
                chatName.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
                chatName.setTranslateX(-50);
                chatName.setTranslateY(10);

                chatBox.setStyle("-fx-background-color: rgba(27,16,115,0.71);" +
                        "-fx-background-radius: 10px;");
                chatBox.getChildren().add(clip);
                chatBox.getChildren().add(chatName);
                chatBox.setOnMouseClicked(mouseEvent -> openChat(chat));
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
        send.setOnMouseClicked(mouseEvent -> {
            //TODO :Here we should send the message to the chat server in order to deliver it to the receiver
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


    private void showPrivateChatBox(Chat privateChat) throws IOException { //110 / 10 / 150
        NewHBox pvBox = new NewHBox(privateChat.getSocket().getPort(), privateChat.getName());
        pvBox.setLayoutX(110);
        pvBox.setLayoutY(200);
        pvBox.setSpacing(50);
        User receiver = User.getUserByName(privateChat.getName());
        Circle clip = new Circle(25, 25, 25);
        Image profile = receiver.getAvatar().getImage();
        clip.setFill(new ImagePattern(profile));
        clip.setStroke(Color.rgb(26, 11, 136));
        Text chatName = new Text(receiver.getUsername());
        chatName.setFill(Color.WHITE);
        chatName.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
        pvBox.getChildren().add(clip);
        pvBox.getChildren().add(chatName);
        pane.getChildren().add(pvBox);

    }

    public void showAllGroupChats() {
        removeRemainedStuff();

    }

    public void showGlobalChat(ArrayList<Message> messages, ChatMethods chatMethods) {
        pane.getChildren().remove(chatTextField);
        pane.getChildren().remove(send);
        removeRemainedStuff();
        chatBox = new VBox();
        chatBox.setSpacing(15);

        exit = new Button("exit");
        exit.setLayoutX(1160);
        exit.setLayoutY(200);
        exit.setPrefSize(80, 80);
        exit.setStyle("-fx-background-color: rgba(27,16,115,0.71); -fx-text-fill: #d3c4c4");
        exit.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
        exit.setOnMouseClicked(mouseEvent -> {
            //TODO :Here we should send the message to the chat server in order to deliver it to the receiver
            try {
                pane.getChildren().remove(scrollPaneForChatBox);
                pane.getChildren().remove(chatTextField);
                pane.getChildren().remove(send);
                chatMethods.exitFromChat();
                pane.getChildren().remove(exit);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        for (Message message : messages) {
            HBox messageBox = new HBox();
            messageBox.setSpacing(70);
            messageBox.setStyle("-fx-background-color: rgba(27,16,115,0.71);-fx-background-radius: 20px");
            messageBox.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) { //---> Edit
                    System.out.println("Clicked");
                    try {
                        Message message1 = new Message(message.getSender(), "", message.isSeen(), message.getAvatar());
                        chatMethods.editMessage(message1, message);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) { // delete just for me
                    try {
                        Message message1 = new Message(message.getSender(), "HI", message.isSeen(), message.getAvatar());
                        chatMethods.deleteJustForMe(message1);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            Text content = new Text(message.getContent());
            texts.add(content);
            content.setFill(Color.WHITE);
            content.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 14));
            content.setTranslateX(10);
            content.setTranslateY(1);
            TextFlow textFlow = new TextFlow(content);
            textFlow.setStyle("-fx-background-radius: 20px; -fx-background-color: rgba(86,46,155,0.74)");
            messageBox.getChildren().add(textFlow);
            if (!message.getSender().equals("ali")) {
                textFlow.setTextAlignment(TextAlignment.RIGHT);
            } else textFlow.setTextAlignment(TextAlignment.LEFT);
            chatBox.getChildren().add(messageBox);
        }


        chatBox.setStyle("-fx-background-color: rgba(80,11,143,0.71);");
        scrollPaneForChatBox.setPrefWidth(300);
        scrollPaneForChatBox.setContent(chatBox);
        scrollPaneForChatBox.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPaneForChatBox.setLayoutX(850);
        scrollPaneForChatBox.setLayoutY(300);
        scrollPaneForChatBox.setStyle("-fx-background-color: #1b1073");
        scrollPaneForChatBox.setVisible(true);
        scrollPaneForChatBox.heightProperty().addListener((observableValue, number, t1) -> scrollPaneForChatBox.setVvalue((Double) t1));


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
        send.setOnMouseClicked(mouseEvent -> {
            //TODO :Here we should send the message to the chat server in order to deliver it to the receiver
            try {
                chatMethods.sendMessage(chatTextField.getText());
                chatTextField.setText("");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        pane.getChildren().add(scrollPaneForChatBox);
        pane.getChildren().add(chatTextField);
        pane.getChildren().add(send);
        pane.getChildren().add(exit);
    }


    public void removeRemainedStuff() {
        pane.getChildren().remove(scrollPaneForChatList);
        pane.getChildren().remove(scrollPaneForChatBox);
        pane.getChildren().remove(send);
        pane.getChildren().remove(chatTextField);
    }

    private void createChatRoom() {
        removeRemainedStuff();
        pane.getChildren().remove(search);
        pane.getChildren().remove(searchButton);

        searchBarForUsers.setPromptText("Search player's Id");
        searchBarForUsers.setPrefSize(280, 30);
        searchBarForUsers.setTranslateX(300);
        searchBarForUsers.setTranslateY(160);
        searchBarForUsers.setFocusTraversable(false);
        searchBarForUsers.setStyle("-fx-background-color: rgba(27,16,115,0.71); -fx-prompt-text-fill: white;" +
                "-fx-text-fill: white;");

        searchButtonForUsers.setLayoutX(590);
        searchButtonForUsers.setLayoutY(157);
        searchButtonForUsers.setPrefSize(30, 30);
        searchButtonForUsers.setBackground(new Background(new BackgroundImage(gameImages.getSearch(), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        searchButtonForUsers.setOnMouseClicked(mouseEvent -> {
            //TODO : We should find out from the server whether the given username exists or not
            ArrayList<String> searchResult = findAllMatchingChats(search.getText());
            try {
                if (searchResult != null && searchResult.size() != 0) {
                    designChatRoomCheckBox(searchResult);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        designChatRoomFillBox();

        pane.getChildren().add(searchBarForUsers);
        pane.getChildren().add(searchButtonForUsers);


    }

    private void designChatRoomCheckBox(ArrayList<String> searchResult) throws IOException {
        chatRoomForm = new VBox();
        chatRoomForm.setSpacing(2);
        scrollPaneForChatCheckBox = new ScrollPane();
        for (String result : searchResult) {
            System.out.println(result);
//            Socket socket = new Socket("localhost", 8080);
            NewHBox newHBox = new NewHBox(8080, result);
            newHBox.setPrefSize(284.1, 40);
            newHBox.setSpacing(70);
            newHBox.setStyle("-fx-background-color: rgba(27,16,115,0.71);");
            newHBox.setOnMouseClicked(mouseEvent -> {
                //TODO :Here we can get the port and socket and pass it to the server
                //  If we choose a box we add it to the group

            });


            Text chatName = new Text();
            chatName.setText(result);
            chatName.setFill(Color.WHITE);
            chatName.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 14));
            chatName.setTranslateX(10);
            chatName.setTranslateY(1);

            newHBox.getChildren().add(chatName);
            chatRoomForm.setStyle("-fx-background-color: rgba(80,11,143,0.71);");
            chatRoomForm.getChildren().add(newHBox);

        }


        scrollPaneForChatCheckBox.setPrefWidth(300);
        scrollPaneForChatCheckBox.setContent(chatRoomForm);
        scrollPaneForChatCheckBox.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPaneForChatCheckBox.setLayoutX(300);
        scrollPaneForChatCheckBox.setLayoutY(300);

        scrollPaneForChatCheckBox.setStyle("-fx-background-color: #1b1073");
        scrollPaneForChatCheckBox.setVisible(true);
        pane.getChildren().add(scrollPaneForChatCheckBox);

    }

    private void designChatRoomFillBox() {
        chatRoomForm = new VBox();
        chatRoomForm.setSpacing(5);
        chatRoomForm.setStyle("-fx-background-color: rgba(80,11,143,0.55);-fx-background-radius: 30px");
        chatRoomForm.setLayoutX(870);
        chatRoomForm.setLayoutY(230);

        Label header = new Label();
        header.setText("Complete the Chat Room Information!");
        header.setStyle("-fx-text-fill: #ffffff; -fx-alignment: center; " +
                "-fx-background-radius: 30px; -fx-background-color: rgba(27,16,115,0.71)");
        header.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 17));
        header.setLayoutX(870);
        header.setLayoutY(180);
        header.setPrefSize(300, 35);
        header.setContentDisplay(ContentDisplay.CENTER);


        TextField chatName = new TextField();
        chatName.setPromptText("Enter Group's Name:");
        chatName.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 12));
        chatName.setTranslateX(45);
        chatName.setTranslateY(30);
        chatName.setFocusTraversable(false);
        chatName.setStyle("-fx-background-color: rgba(80,11,143,0.6); -fx-prompt-text-fill: white;" +
                "-fx-text-fill: white;-fx-background-radius: 30px;");
        chatName.setPrefSize(200, 20);
        chatName.setMaxSize(200, 20);
        chatName.setMinSize(200, 20);

        Button create = new Button("Create Chat");
        create.setTranslateX(100);
        create.setTranslateY(70);
        create.setPrefSize(80, 10);
        create.setStyle("-fx-background-color: rgba(80,11,143,0.6); -fx-text-fill: #ffffff; -fx-background-radius: 30px");
        create.setOnMouseClicked(mouseEvent -> {
            //Todo: here we should send the information of new Group to the server

        });


        chatRoomForm.getChildren().add(chatName);
        chatRoomForm.getChildren().add(create);
        chatRoomForm.setPrefSize(300, 150);
        pane.getChildren().add(chatRoomForm);
        pane.getChildren().add(header);

    }

    private ArrayList<String> findAllMatchingChats(String text) {
        ArrayList<String> str = new ArrayList<>();
        str.add("HI");
        str.add("Hello");
        str.add("Doreece");
        str.add("Blah Blah Blah");
        return str;
    }

    public synchronized static void addNewMessageToChat(Message message) {
        System.out.println("Message : " + message.getContent());
        if (message != null) {
            HBox messageBox = new HBox();
            messageBox.setPrefSize(50, 20);
            messageBox.setSpacing(70);
            messageBox.setStyle("-fx-background-color: rgba(27,16,115,0.71);");
            messageBox.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) { //---> Edit
                    System.out.println("Clicked");
                    try {
                        Message message1 = new Message(message.getSender(), "", message.isSeen(), message.getAvatar());
                        chatMethods.editMessage(message1, message);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) { // delete just for me
                    try {
                        Message message1 = new Message(message.getSender(), "HI", message.isSeen(), message.getAvatar());
                        chatMethods.deleteJustForMe(message1);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            Text content = new Text(message.getContent());
            content.setFill(Color.WHITE);
            content.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 14));
            content.setTranslateX(10);
            content.setTranslateY(1);
            TextFlow textFlow = new TextFlow(content);
            textFlow.setStyle("-fx-background-radius: 20px; -fx-background-color: rgba(86,46,155,0.74)");
            texts.add(content);
            messageBox.getChildren().add(textFlow);
            messageBox.setAlignment(Pos.BOTTOM_LEFT);
            chatBox.getChildren().add(messageBox);
        }
    }

    public synchronized static void editMessage(Message message) throws IOException {
        String[] subStrings = message.getContent().split("#");
        System.out.println("Entered editMessage");
        ArrayList<Message> messages = chatMethods.getAllMessages();
        int index = Integer.parseInt(subStrings[1]);
        String newContent = "";
        for (int i = 2; i < subStrings.length; i++) {
            newContent = newContent.concat(subStrings[i]);
        }
        System.out.println("NewContent:" + newContent + "length:" + newContent.length());
        if (newContent.length() != 0) {
            texts.get(index).setText(newContent);
        } else {
            texts.get(index).setText("");
        }
    }

    public synchronized static void deleteMessageJustForMe(Message message) throws IOException {
        String[] subStrings = message.getContent().split("#");
        System.out.println("Entered deleteJustForMe");
        ArrayList<Message> messages = chatMethods.getAllMessages();
        String userName = subStrings[1];
        String content = "";
        if (User.getCurrentUser().getUsername().equals(userName)) {
            for (int i = 2; i < subStrings.length; i++) {
                content = content.concat(subStrings[i]);
            }
            for (int i = 0; i < messages.size(); i++) {
                if (messages.get(i).getContent().equals(content)) {
                    texts.get(i - 1).setText("");
                    break;
                }
            }
        }
    }

}

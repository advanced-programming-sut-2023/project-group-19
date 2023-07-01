package view;

import controller.method.ChatMethods;
import controller.method.MapMethod;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.css.Match;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.*;

import view.ImageAndBackground.GameImages;
import view.Model.NewHBox;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.*;
import java.util.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Lobby extends Application {
    boolean isPrivateChatPressed = false;
    boolean isGroupChatPressed = false;
    public static GameImages gameImages;
    public static Socket socket;
    public static NewHBox pvBox = null;

//    static {
//        try {
//            socket = new Socket("localhost", 8888);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static Pane pane = new Pane();
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

    public static Button send = new Button();
    public static TextField chatTextField = new TextField();
    public Text emptyGameId = new Text("Game Id field is empty!");
    public Text emptyCapacity = new Text("Capacity field is empty!");
    public Text emptyTypeOfGame = new Text("Game's Type field is empty!");
    public Text invalidTypeOfGame = new Text("The given game type is invalid!");
    public Text invalidCapacity = new Text("The given capacity is invalid!");
    public Text typeOfChatMenu = new Text("Chats");
    public static Text showMessageData = new Text();
    public TextField search = new TextField();
    public Button searchButton = new Button();
    public Button exit = new Button();
    public TextField searchBarForUsers = new TextField();
    public Button showAllChats = new Button();
    public Button searchButtonForUsers = new Button();
    public ArrayList<NewHBox> chosenMembersForRoom = new ArrayList<>();
    public static ArrayList<Message> messages = new ArrayList<>();
    public ArrayList<String> usersToBeAddedToGroup = new ArrayList<>();
    public static ArrayList<Text> texts = new ArrayList<>();
    public static ChatMethods chatMethods;
    public ArrayList<Game> allGameRequests = new ArrayList<>();
    public static DataInputStream masterServerDataInputStream;
    public static DataOutputStream masterServerDataOutputStream;
    public static User user1;
    public static User user2;
    public static User user3;
    public static User user4;
    public static User user5;
    static {
        try {
            user1 = new User("z", "s", "a", "s", "w", "q", 3);
            user2 = new User("ali", "s", "a", "s", "w", "q", 3);
            user3 = new User("ac", "s", "a", "s", "w", "q", 3);
            user4 = new User("ad", "s", "a", "s", "w", "q", 3);
            user5 = new User("ae", "s", "a", "s", "w", "q", 3);
            User.setCurrentUser(user2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            socket = new Socket("localhost", 8888);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            masterServerDataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            masterServerDataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    masterServerDataOutputStream.writeUTF("START_GAME");
                    masterServerDataOutputStream.writeUTF(User.getCurrentUser().getUsername());
                    String input = masterServerDataInputStream.readUTF();
                    if(input.equals("start")){
                        timer.cancel();
                        TileManager tileManager = new TileManager();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Stage stage1 = new Stage();
                                try {
                                    tileManager.start(stage1);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }, 0, 1000);

    }
    public Label headerForChat;
    public VBox createGroupBox;
    public AnchorPane chatPane;
    public static Button edit = new Button("Edit");
    public static Button deleteforMe = new Button("Delete");
    public static Button like = new Button();
    public static Button dislike = new Button();
    public static Button heart = new Button();
    public static TextField editTextField = new TextField();
    public static Button editSendButton = new Button();


    //TODO : EventHandler for Back Button

    public VBox cornerVBox = new VBox();
    public Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
//
//        masterServerDataOutputStream = new DataOutputStream(socket.getOutputStream());
//        masterServerDataInputStream = new DataInputStream(socket.getInputStream());
//        Manage.masterServerDataOutputStream = masterServerDataOutputStream;
//        Manage.masterServerDataInputStream = masterServerDataInputStream;
        System.out.println("HIIIIIIIII");
        user1 = new User("dorsa", "s", "a", "s", "w", "q", 3);

        User user2 = new User("ali", "s", "a", "s", "w", "q", 3);
        User user3 = new User("qqqqq", "s", "a", "s", "w", "q", 3);
        User user4 = new User("Boom", "s", "a", "s", "w", "q", 3);
        User user5 = new User("ae", "s", "a", "s", "w", "q", 3);
        User.users.add(user2);
        User.users.add(user3);
        User.users.add(user4);
        User.users.add(user5);
        User.users.add(user1);

//        User.setCurrentUser(user2);

//        String data = User.convertUserToJson(user2);
//        Manage.masterServerDataInputStream = new DataInputStream(socket.getInputStream());
//        Manage.masterServerDataOutputStream = new DataOutputStream(socket.getOutputStream());
//        Manage.masterServerDataOutputStream.writeUTF("LOGIN_USER");
//        Manage.masterServerDataOutputStream.writeUTF(data);
//        String a = Manage.masterServerDataInputStream.readUTF();
//        System.out.println("A: "+a);
//        System.out.println(Manage.masterServerDataInputStream.readUTF() + User.getCurrentUser().getUsername());
        User.users.add(user1);
        User.users.add(user2);

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
        this.stage = stage;
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
        back.setLayoutX(150);
        back.setLayoutY(70);
        back.setPrefSize(160, 50);
        back.setStyle("-fx-background-color: rgba(27,16,115,0.71); -fx-text-fill: #d3c4c4");
        back.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));


        Button selectMap = new Button("Select Map");
        selectMap.setLayoutX(350);
        selectMap.setLayoutY(70);
        selectMap.setPrefSize(160, 50);
        selectMap.setStyle("-fx-background-color: rgba(27,16,115,0.71); -fx-text-fill: #d3c4c4");
        selectMap.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
        selectMap.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                SelectMapMenu selectMapMenu = new SelectMapMenu();
                try {
                    selectMapMenu.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

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
        pane.getChildren().add(selectMap);
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
        String input = null;
        while (input == null)
            input = masterServerDataInputStream.readUTF();
        String[] split = input.split("\n");
        for (int j = 0; j < split.length; j += 2) {
            String[] game = split[j].split("#");
            User user = User.getUserByName(game[0]);
            if(game.length > 2) {
                if (game[3].equals("public")) {
                    Game game2 = new Game(user, game[1], true, Integer.parseInt(game[2]));
                    addMembers(split, j, game2);
                } else {
                    Game game1 = new Game(user, game[1], false, Integer.parseInt(game[2]));
                    addMembers(split, j, game1);
                }
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
            } else if (allGameRequest.getAllPlayers().contains(User.getCurrentUser())) {
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
            changePrivacy.setTranslateY(62);
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
            leaveGame.setTranslateY(42);
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
            close.setTranslateX(138);
            close.setTranslateY(22);
            close.setPrefSize(50, 10);
            close.setStyle("-fx-background-color: #55288c; -fx-text-fill: #d3c4c4");
            close.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 8));
            close.setOnMouseClicked(mouseEvent -> {
                closeTheInfoBox(listOfGameInfo);
            });

            Button startGame = new Button();
            startGame.setText("Start");
            startGame.setTranslateX(10);
            startGame.setTranslateY(25);
            startGame.setPrefSize(50, 10);
            startGame.setStyle("-fx-background-color: #55288c; -fx-text-fill: #d3c4c4");
            startGame.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 8));
            startGame.setOnMouseClicked(mouseEvent -> {
                startGame();
            });

            Button rooms = new Button();
            rooms.setText("Room");
            rooms.setTranslateX(70);
            rooms.setTranslateY(5);
            rooms.setPrefSize(50, 10);
            rooms.setStyle("-fx-background-color: #55288c; -fx-text-fill: #d3c4c4");
            rooms.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 8));
            rooms.setOnMouseClicked(mouseEvent -> {
                try {
                    pane.getChildren().clear();
                    designChat(gameImages);
                    //ArminsMethod
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            listOfGameInfo.getChildren().add(changePrivacy);
            listOfGameInfo.getChildren().add(leaveGame);
            listOfGameInfo.getChildren().add(close);
            listOfGameInfo.getChildren().add(startGame);
            listOfGameInfo.getChildren().add(rooms);

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
    public void startGame(){
        TileManager tileManager = new TileManager();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Stage stage1 = new Stage();
                try {
                    tileManager.start(stage1);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
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
                    masterServerDataOutputStream.writeUTF(User.getCurrentUser().getUsername()
                            + "#" + gameId.getText() + "#" + capacity.getText() + "#" + typeOfGame.getText());
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
        pane.setStyle("-fx-background-color: rgb(25,55,109)");
        cornerVBox.setStyle("-fx-background-color: #1384ee");
        cornerVBox.setPrefWidth(100);
        cornerVBox.setPrefHeight(870);

        pane.getChildren().add(cornerVBox);
        designButtonsOfChatMenu();
        designHeaderOfChatMenu();
        designSearchBarOfChatMenu();

    }

    private synchronized void designSearchBarOfChatMenu() {
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
                    User.getCurrentUser().getChats().add(privateChat);
                    NewHBox newHBox = showPrivateChatBox(privateChat);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else if (isGroupChatPressed) {
                try {
                    System.out.println("Entered try catch");
                    isGroupChatPressed = false;
                    Chat groupChat = ChatMethods.addNewGroupChat(search.getText());
                    User.getCurrentUser().getChats().add(groupChat);
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
            try {
                User.getCurrentUser().getChats().clear();
                ChatMethods.refreshChats();
                setChatList();
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
                pane.setBackground(gameImages.getLobbyBackground());
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

        Button globalChat = new Button();
        globalChat.setLayoutX(25);
        globalChat.setLayoutY(590);
        globalChat.setPrefSize(50, 50);
        globalChat.setBackground(new Background(new BackgroundImage(gameImages.getGlobalChat(), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        globalChat.setOnMouseClicked(mouseEvent -> {
            try {
                Socket socket = new Socket("localhost", 6000);
                chatMethods = new ChatMethods(socket);
                messages = chatMethods.enterToChat();
                openChat(messages);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        Circle clip = new Circle(25, 25, 25);
        Image profile = User.getCurrentUser().getAvatar().getImage();
        clip.setFill(new ImagePattern(profile));
        clip.setStroke(Color.rgb(26, 11, 136));
        clip.setLayoutX(25);
        clip.setLayoutY(690);

        Text me = new Text();
        me.setText(User.getCurrentUser().getUsername());
        me.setFill(Color.rgb(26, 11, 136));
        me.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
        me.setLayoutX(25);
        me.setLayoutY(750);


        pane.getChildren().add(lobby);
        pane.getChildren().add(createRoom);
        pane.getChildren().add(refresh);
        pane.getChildren().add(makePrivateChat);
        pane.getChildren().add(globalChat);
        pane.getChildren().add(clip);
        pane.getChildren().add(me);

    }


    private User searchForGivenUserId(String text) {
        for (User user : User.users) {
            if (user.getUsername().equals(text)) {
                return user;
            }
        }
        return null;
    }


    private void setChatList() {
        VBox chatList = new VBox();
        chatList.setStyle("-fx-background-color: #4b187e; " +
                "-fx-background-radius: 10px;");
        chatList.setSpacing(1);
        if (!User.getCurrentUser().getChats().isEmpty()) {
            for (Chat chat : User.getCurrentUser().getChats()) {
                User receiver = searchForGivenUserId(chat.getName());//TODO : Do we need to take it directly from server?
//                Circle clip = new Circle(25, 25, 25);
//                Image profile = receiver.getAvatar().getImage();
//                clip.setFill(new ImagePattern(profile));
//                clip.setStroke(Color.rgb(26, 11, 136));

                NewHBox chatBox = new NewHBox(chat, chat.getName());
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
//                chatBox.getChildren().add(clip);
                chatBox.getChildren().add(chatName);
                setEventHandlerForChatBox(chatBox);
                chatList.getChildren().add(chatBox);
            }
            scrollPaneForChatList.setPrefWidth(300);
            scrollPaneForChatList.setPrefHeight(300);
            scrollPaneForChatList.setContent(chatList);
            scrollPaneForChatList.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scrollPaneForChatList.setLayoutX(110);
            scrollPaneForChatList.setLayoutY(480);

            scrollPaneForChatList.setStyle("-fx-background-color: #1b1073");
            scrollPaneForChatList.setVisible(true);
            pane.getChildren().add(scrollPaneForChatList);
        }
    }

    private void openChat(ArrayList<Message> myMessages) {
        pane.getChildren().remove(chatPane);
        pane.getChildren().remove(chatBox);
        pane.getChildren().remove(exit);
        pane.getChildren().remove(scrollPaneForChatBox);
        pane.getChildren().remove(chatTextField);
        pane.getChildren().remove(send);
        removeRemainedStuff();

        chatPane = new AnchorPane();
        chatPane.setPrefSize(916, 750);
        chatPane.setMaxSize(916, 750);
        chatPane.setMinSize(916, 750);

        chatBox = new VBox();
        chatBox.setSpacing(15);
        chatBox.setStyle("-fx-background-radius: 20px; -fx-border-radius: 20px");
        chatBox.setPrefSize(916, 750);
        chatBox.setMaxSize(916, 750);
        chatBox.setMinSize(916, 750);

        exit = new Button("exit");
        exit.setLayoutX(25);
        exit.setLayoutY(480);
        exit.setPrefSize(50, 50);
        exit.setStyle("-fx-background-color: rgba(27,16,115,0.71); -fx-text-fill: #d3c4c4; -fx-background-radius: 10px");
        exit.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 10));
        exit.setOnMouseClicked(mouseEvent -> {
            try {
                pane.getChildren().remove(scrollPaneForChatBox);
                pane.getChildren().remove(chatTextField);
                pane.getChildren().remove(send);
                pane.getChildren().remove(chatPane);
                chatMethods.exitFromChat();
                pane.getChildren().remove(exit);
                pane.getChildren().remove(pvBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        setStyleToMessages(myMessages);

        chatPane.setStyle("-fx-background-color: #1384ee; -fx-border-color: #1384ee;" +
                " -fx-opacity: 60;");
        scrollPaneForChatBox.setPrefSize(910, 750);
        scrollPaneForChatBox.setMaxSize(910, 750);
        scrollPaneForChatBox.setMinSize(910, 750);
        scrollPaneForChatBox.setContent(chatBox);
        scrollPaneForChatBox.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPaneForChatBox.setStyle("-fx-background: rgba(19,132,238,0.81); -fx-border-color: rgba(19,132,238,0.81);" +
                "-fx-progress-color: rgba(19,132,238,0.81); ");
        scrollPaneForChatBox.setVisible(true);
        scrollPaneForChatBox.heightProperty().addListener((observableValue, number, t1) ->
                scrollPaneForChatBox.setVvalue((Double) t1));


        chatTextField.setPromptText("Send Message");
        chatTextField.setPrefSize(850, 30);
        chatTextField.setLayoutX(620);
        chatTextField.setLayoutY(780);
        chatTextField.setFocusTraversable(false);
        chatTextField.setStyle("-fx-background-color: rgba(19,132,238,0.81); -fx-prompt-text-fill: white;" +
                "-fx-text-fill: white; -fx-background-radius: 20px; -fx-border-radius: 20px");

        send.setLayoutX(1460);
        send.setLayoutY(750);
        send.setPrefSize(80, 80);
        send.setBackground(new Background(new BackgroundImage(gameImages.getSend(), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        send.setOnMouseClicked(mouseEvent -> {
            try {
                chatMethods.sendMessage(chatTextField.getText());
                chatTextField.setText("");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        chatPane.getChildren().add(scrollPaneForChatBox);
        chatPane.setLayoutX(620);
        chatPane.setLayoutY(10);

        pane.getChildren().add(chatPane);
        pane.getChildren().add(chatTextField);
        pane.getChildren().add(send);
        pane.getChildren().add(exit);

    }


    private NewHBox showPrivateChatBox(Chat privateChat) throws IOException { //110 / 10 / 150
        User receiver = User.getUserByName(privateChat.getName());
        if (receiver != null) {
            System.out.println("Enter design");
            pvBox = new NewHBox(privateChat, privateChat.getName());
            pvBox.setStyle("-fx-background-radius: 10px; -fx-background-color: rgba(19,132,238,0.6)");
            pvBox.setLayoutX(62);
            pvBox.setLayoutY(200);
            pvBox.setSpacing(50);
            pvBox.setPrefSize(500, 120);

            Circle clip = new Circle(50, 50, 50);
            Image profile = receiver.getAvatar().getImage();
            clip.setFill(new ImagePattern(profile));
            clip.setStroke(Color.rgb(19, 132, 238, 0.5));
            clip.setTranslateX(10);
            clip.setTranslateY(10);
            Text chatName = new Text(receiver.getUsername());
            chatName.setFill(Color.WHITE);
            chatName.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 24));
            chatName.setTranslateX(40);
            chatName.setTranslateY(30);
            pvBox.getChildren().add(clip);
            pvBox.setTranslateX(50);
            pvBox.setTranslateY(10);
            pvBox.getChildren().add(chatName);
            pane.getChildren().add(pvBox);
            System.out.println("Type: " + privateChat.getType());
            if (privateChat.getType().equals("PRIVATE")) {
                System.out.println("Pv");
                setEventHandlerForChatBox(pvBox);
            }
        }
        return pvBox;
    }

    private NewHBox showGroupChatBox(Chat privateChat) throws IOException { //110 / 10 / 150
        System.out.println("Enter design");
        pane.getChildren().remove(searchButtonForUsers);
        pane.getChildren().remove(searchBarForUsers);
        pane.getChildren().remove(scrollPaneForChatCheckBox);
        pane.getChildren().remove(createGroupBox);
        pane.getChildren().remove(headerForChat);
        pvBox = new NewHBox(privateChat, privateChat.getName());
        pvBox.setStyle("-fx-background-radius: 10px; -fx-background-color: rgba(42,115,250,0.71)");
        pvBox.setLayoutX(62);
        pvBox.setLayoutY(200);
        pvBox.setSpacing(50);
        pvBox.setPrefSize(500, 70);

        Text chatName = new Text(privateChat.getName());
        chatName.setFill(Color.WHITE);
        chatName.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 24));
        chatName.setTranslateX(50);
        chatName.setTranslateY(15);
        pvBox.setTranslateX(50);
        pvBox.setTranslateY(10);
        pvBox.getChildren().add(chatName);
        pane.getChildren().add(pvBox);
        setEventHandlerForChatBox(pvBox);
        return pvBox;
    }


    private void setEventHandlerForChatBox(NewHBox chatHBox) {
        chatHBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Socket chatHBoxSocket = chatHBox.getChat().getSocket();
                    ChatMethods chatMethods1 = new ChatMethods(chatHBoxSocket);
                    chatMethods = chatMethods1;
                    messages.clear();
                    messages = chatMethods1.enterToChat();
                    openChat(messages);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void showAllGroupChats() {
        removeRemainedStuff();

    }


    public void removeRemainedStuff() {
        pane.getChildren().remove(scrollPaneForChatList);
        pane.getChildren().remove(scrollPaneForChatBox);
        pane.getChildren().remove(send);
        pane.getChildren().remove(chatTextField);
    }

    private void createChatRoom() {
        removeRemainedStuff();
        if (chatPane != null) {
            chatPane.getChildren().clear();
        }
        pane.getChildren().remove(chatPane);
        searchBarForUsers.setPromptText("Search player's Id");
        searchBarForUsers.setPrefSize(460, 30);
        searchBarForUsers.setLayoutX(110);
        searchBarForUsers.setLayoutY(175);
        searchBarForUsers.setFocusTraversable(false);
        searchBarForUsers.setStyle("-fx-background-color: rgba(19,132,238,0.85); -fx-prompt-text-fill: white;" +
                "-fx-text-fill: white; -fx-background-radius: 10px;");

        searchButtonForUsers.setLayoutX(597);
        searchButtonForUsers.setLayoutY(175);
        searchButtonForUsers.setPrefSize(30, 30);
        searchButtonForUsers.setBackground(new Background(new BackgroundImage(gameImages.getSearch(), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        searchButtonForUsers.setOnMouseClicked(mouseEvent -> {

            ArrayList<User> searchResult = findAllMatchingChats(searchBarForUsers.getText());
            try {
                if (searchResult != null && searchResult.size() != 0) {
                    designChatRoomCheckBox(searchResult);
                } else pane.getChildren().remove(scrollPaneForChatCheckBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        designChatRoomFillBox();
        pane.getChildren().add(searchBarForUsers);
        pane.getChildren().add(searchButtonForUsers);

    }

    private void designChatRoomCheckBox(ArrayList<User> searchResult) throws IOException {
        pane.getChildren().remove(scrollPaneForChatCheckBox);
        chatRoomForm = new VBox();
        chatRoomForm.setSpacing(2);
        scrollPaneForChatCheckBox = new ScrollPane();
        for (User user : searchResult) {
            HBox newHBox = new HBox();
            newHBox.setPrefSize(483, 121);
            newHBox.setSpacing(70);
            newHBox.setStyle("-fx-background-color: rgba(19,132,238,0.6);");
            newHBox.setOnMouseClicked(mouseEvent -> {
                usersToBeAddedToGroup.add(user.getUsername());
            });

            Circle clip = new Circle(50, 50, 50);
            Image profile = user.getAvatar().getImage();
            clip.setFill(new ImagePattern(profile));
            clip.setStroke(Color.rgb(26, 11, 136));
            clip.setTranslateX(10);
            clip.setTranslateY(10);

            Text chatName = new Text();
            chatName.setText(user.getUsername());
            chatName.setFill(Color.WHITE);
            chatName.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 24));
            chatName.setTranslateX(100);
            chatName.setTranslateY(30);

            chatRoomForm.setStyle("-fx-background-color: rgb(255,255,255);");
            newHBox.getChildren().add(clip);
            newHBox.getChildren().add(chatName);
            chatRoomForm.getChildren().add(newHBox);
        }
        scrollPaneForChatCheckBox.setPrefWidth(500);
        scrollPaneForChatCheckBox.setPrefHeight(370);
        scrollPaneForChatCheckBox.setContent(chatRoomForm);
        scrollPaneForChatCheckBox.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPaneForChatCheckBox.setLayoutX(110);
        scrollPaneForChatCheckBox.setLayoutY(480);

        scrollPaneForChatCheckBox.setStyle("-fx-background-color: #2a73fa; -fx-background-radius: 10px;");
        scrollPaneForChatCheckBox.setVisible(true);
        if (chatRoomForm.getChildren().size() != 0)
            pane.getChildren().add(scrollPaneForChatCheckBox);
    }

    private void designChatRoomFillBox() {
        pane.getChildren().remove(search);
        pane.getChildren().remove(searchButton);
        createGroupBox = new VBox();
        createGroupBox.setSpacing(5);
        createGroupBox.setStyle("-fx-background-color: rgba(19,132,238,0.6);-fx-background-radius: 30px");
        createGroupBox.setLayoutX(110);
        createGroupBox.setLayoutY(260);

        headerForChat = new Label();
        headerForChat.setText("Complete the Chat Room Information!");
        headerForChat.setStyle("-fx-text-fill: #ffffff; -fx-alignment: center; " +
                "-fx-background-radius: 30px; -fx-background-color: rgba(19,132,238,0.6)");
        headerForChat.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 17));
        headerForChat.setLayoutX(110);
        headerForChat.setLayoutY(220);
        headerForChat.setPrefSize(500, 35);
        headerForChat.setContentDisplay(ContentDisplay.CENTER);


        TextField chatName = new TextField();
        chatName.setPromptText("Enter Group's Name:");
        chatName.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
        chatName.setTranslateX(105);
        chatName.setTranslateY(30);
        chatName.setFocusTraversable(false);
        chatName.setStyle("-fx-background-color: rgba(15,71,236,0.52); -fx-prompt-text-fill: white;" +
                "-fx-text-fill: white;-fx-background-radius: 30px;");
        chatName.setPrefSize(284, 40);
        chatName.setMaxSize(284, 40);
        chatName.setMinSize(284, 40);

        Button create = new Button("Create Chat");
        create.setTranslateX(180);
        create.setTranslateY(70);
        create.setPrefSize(120, 40);
        create.setStyle("-fx-background-color: rgba(15,71,236,0.52); -fx-text-fill: #ffffff; -fx-background-radius: 30px");
        create.setOnMouseClicked(mouseEvent -> {
            try {
                Chat newGroup = ChatMethods.addNewGroupChat(chatName.getText());
                ChatMethods chatMethods1 = new ChatMethods(newGroup.getSocket());
                chatMethods1.addUsersToGroup(usersToBeAddedToGroup, newGroup.getName());
                usersToBeAddedToGroup.clear();
                NewHBox newHBox = showGroupChatBox(newGroup);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

        createGroupBox.getChildren().add(chatName);
        createGroupBox.getChildren().add(create);
        createGroupBox.setPrefSize(500, 200);
        pane.getChildren().add(createGroupBox);
        pane.getChildren().add(headerForChat);
    }

    private ArrayList<User> findAllMatchingChats(String text) {
        ArrayList<User> users = new ArrayList<>();
        for (User user : Manage.allUsers) {
            if (user.getUsername().contains(text)) {
                users.add(user);
            }
        }
        return users;
    }

    public synchronized static void addNewMessageToChat(Message message) {
        ArrayList<Message> myMessages = new ArrayList<>();
        myMessages.add(message);
        setStyleToMessages(myMessages);
        System.out.println("Message : " + message.getContent());

//        if (message != null) {
//            HBox messageBox = new HBox();
//            messageBox.setPrefSize(50, 20);
//            messageBox.setSpacing(70);
//            messageBox.setStyle("-fx-background-color: rgba(27,16,115,0.71);");
//            messageBox.setOnMouseClicked(mouseEvent -> {
//                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) { //---> Edit
//                    System.out.println("Clicked");
//                    try {
//                        Message message1 = new Message(message.getSender(), "", message.isSeen(), message.getAvatar());
//                        chatMethods.editMessage(message1, message);
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                } else if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) { // delete just for me
//                    try {
//                        Message message1 = new Message(message.getSender(), "HI", message.isSeen(), message.getAvatar());
//                        chatMethods.deleteJustForMe(message1);
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            });
//            Text content = new Text(message.getContent());
//            content.setFill(Color.WHITE);
//            content.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 14));
//            content.setTranslateX(10);
//            content.setTranslateY(1);
//            TextFlow textFlow = new TextFlow(content);
//            textFlow.setStyle("-fx-background-radius: 20px; -fx-background-color: rgba(86,46,155,0.74)");
//            texts.add(content);
//            messageBox.getChildren().add(textFlow);
//            messageBox.setAlignment(Pos.BOTTOM_LEFT);
//            chatBox.getChildren().add(messageBox);
//        }
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
            message.setContent(newContent);
            texts.get(index).setText(newContent);
        } else {
            message.setContent("");
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

    public static void setStyleToMessages(ArrayList<Message> myMessages) {
        ImageView imageView = null;
        ImageView imageView1 = null;
        Image image = null;
        for (Message myMessage : myMessages) {
            HBox messageBox = new HBox();
            messageBox.setPadding(new Insets(5, 5, 5, 10));
            messageBox.setOnMouseClicked(mouseEvent -> {
                setButtonsOfChatFeature(myMessage);

            });
            messageBox.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    //todo: hover location
                    String status;
                    String[] time = myMessage.getSentTime().split("\\.");
                    if (myMessage.isSeen()) status = "Seen";
                    else status = "Haven't seen";
                    PointerInfo a = MouseInfo.getPointerInfo();
                    Point b = a.getLocation();
                    int x = (int) b.getX();
                    int y = (int) b.getY();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Sender: ").append(myMessage.getSender())
                            .append("\nTime: ").append(time[0]);
                    if (myMessage.getSender().equals(User.getCurrentUser().getUsername())){
                        stringBuilder.append("\nStatus: ").append(status);
                    }
                    showMessageData.setText(stringBuilder.toString());
                    showMessageData.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 8));
                    if (myMessage.getSender().equals(User.getCurrentUser().getUsername())) {
                        showMessageData.setTranslateX(700);
                        showMessageData.setTranslateY(y - 70);
                    } else {
                        showMessageData.setTranslateX(300);
                        showMessageData.setTranslateY(y - 70);
                    }

                    if (showMessageData != null)
                        chatBox.getChildren().add(showMessageData);
                }
            });
            messageBox.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    chatBox.getChildren().remove(showMessageData);
                }
            });

            if (myMessage.getReaction() != null && myMessage.getReaction().length() != 0) {
                imageView = new ImageView(new Image(TileManager.class.getResource
                        (myMessage.getReaction()).toExternalForm()));
                messageBox.getChildren().add(imageView);
            }

            Text content = new Text(myMessage.getContent());
            texts.add(content);
            content.setFill(Color.WHITE);
            content.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 14));
            TextFlow textFlow = new TextFlow(content);
            textFlow.setStyle("-fx-background-radius: 20px; -fx-background-color: #1b1073;" +
                    "-fx-text-fill: #ffffff;");
            textFlow.setPadding(new Insets(5, 10, 5, 10));
            User user = User.getUserByName(myMessage.getSender());
            if (user != null) {
                image = user.getAvatar().getImage();
                imageView1 = new ImageView(image);
                imageView1.setFitWidth(30);
                imageView1.setFitHeight(30);
            }
            if (myMessage.getSender().equals(User.getCurrentUser().getUsername())) {
                messageBox.setAlignment(Pos.CENTER_RIGHT);
                messageBox.getChildren().add(textFlow);
                messageBox.getChildren().add(imageView1);
            } else {
                messageBox.setAlignment(Pos.CENTER_LEFT);
                messageBox.getChildren().add(imageView1);
                messageBox.getChildren().add(textFlow);
            }
            chatBox.getChildren().add(messageBox);
        }
    }

    private static void setButtonsOfChatFeature(Message myMessage) {
        String content = chatTextField.getText();
        pane.getChildren().remove(chatTextField);
        pane.getChildren().remove(send);
        edit = new Button("Edit");
        edit.setLayoutX(620);
        edit.setLayoutY(780);
        edit.setPrefSize(150, 50);
        edit.setStyle("-fx-background-color: rgb(42,115,250); -fx-text-fill: #ffffff;-fx-background-radius: 10px");
        edit.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 18));
        edit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                removeChatStuff();
                setStyleToChatStuffAfter(myMessage);

            }
        });

        deleteforMe = new Button("Delete For Me");
        deleteforMe.setLayoutX(790);
        deleteforMe.setLayoutY(780);
        deleteforMe.setPrefSize(150, 50);
        deleteforMe.setStyle("-fx-background-color: rgb(42,115,250); -fx-text-fill: #ffffff;-fx-background-radius: 10px");
        deleteforMe.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 18));
        deleteforMe.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    removeChatStuff();
                    setStyleToChatStuff();
                    Message message1 = new Message(myMessage.getSender(), myMessage.getContent(), myMessage.isSeen(), myMessage.getAvatar());
                    chatMethods.deleteJustForMe(message1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        like = new Button();
        like.setLayoutX(960);
        like.setLayoutY(780);
        like.setPrefSize(150, 50);
        like.setStyle("-fx-background-color: rgb(42,115,250); -fx-text-fill: #ffffff;-fx-background-radius: 10px");
        like.setGraphic(new ImageView(new Image(TileManager.class.getResource("/image/LobbyImages/like.png").toExternalForm())));
        like.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    removeChatStuff();
                    setStyleToChatStuff();
                    myMessage.setReaction("/image/LobbyImages/like.png");
                    chatMethods.sendReaction(myMessage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        dislike = new Button();
        dislike.setLayoutX(1130);
        dislike.setLayoutY(780);
        dislike.setPrefSize(150, 50);
        dislike.setStyle("-fx-background-color: rgb(42,115,250); -fx-text-fill: #ffffff;-fx-background-radius: 10px");
        dislike.setGraphic(new ImageView(new Image(TileManager.class.getResource("/image/LobbyImages/dislike.png").toExternalForm())));
        dislike.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    removeChatStuff();
                    setStyleToChatStuff();
                    myMessage.setReaction("/image/LobbyImages/dislike.png");
                    chatMethods.sendReaction(myMessage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        heart = new Button();
        heart.setLayoutX(1300);
        heart.setLayoutY(780);
        heart.setPrefSize(150, 50);
        heart.setStyle("-fx-background-color: rgb(42,115,250); -fx-text-fill: #ffffff;-fx-background-radius: 10px");
        heart.setGraphic(new ImageView(new Image(TileManager.class.getResource("/image/LobbyImages/heart.png").toExternalForm())));
        heart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    myMessage.setReaction("/image/LobbyImages/heart.png");
                    chatMethods.sendReaction(myMessage);
                    removeChatStuff();
                    setStyleToChatStuff();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        pane.getChildren().add(edit);
        pane.getChildren().add(deleteforMe);
        pane.getChildren().add(like);
        pane.getChildren().add(dislike);
        pane.getChildren().add(heart);
    }

    private static void setStyleToChatStuffAfter(Message myMessage) {
        editTextField.setPromptText("Send Message");
        editTextField.setPrefSize(850, 30);
        editTextField.setLayoutX(620);
        editTextField.setLayoutY(780);
        editTextField.setFocusTraversable(false);
        editTextField.setStyle("-fx-background-color: rgba(19,132,238,0.81); -fx-prompt-text-fill: white;" +
                "-fx-text-fill: white; -fx-background-radius: 20px; -fx-border-radius: 20px");

        editSendButton.setLayoutX(1460);
        editSendButton.setLayoutY(750);
        editSendButton.setPrefSize(80, 80);
        editSendButton.setBackground(new Background(new BackgroundImage(gameImages.getSend(), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        editSendButton.setOnMouseClicked(mouseEvent -> {
            try {
                System.out.println("Entered successfully");
                Message message1 = new Message(myMessage.getSender(), editTextField.getText(), myMessage.isSeen(), myMessage.getAvatar());
                chatMethods.editMessage(message1, myMessage);
                pane.getChildren().remove(editTextField);
                pane.getChildren().remove(editSendButton);
                setStyleToChatStuff();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        pane.getChildren().add(editTextField);
        pane.getChildren().add(editSendButton);
    }

    public static void setStyleToChatStuff() {
        chatTextField.setPromptText("Send Message");
        chatTextField.setPrefSize(850, 30);
        chatTextField.setLayoutX(620);
        chatTextField.setLayoutY(780);
        chatTextField.setFocusTraversable(false);
        chatTextField.setStyle("-fx-background-color: rgba(19,132,238,0.81); -fx-prompt-text-fill: white;" +
                "-fx-text-fill: white; -fx-background-radius: 20px; -fx-border-radius: 20px");

        send.setLayoutX(1460);
        send.setLayoutY(750);
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
        pane.getChildren().add(chatTextField);
        pane.getChildren().add(send);
    }


    public static void removeChatStuff() {
        pane.getChildren().remove(edit);
        pane.getChildren().remove(deleteforMe);
        pane.getChildren().remove(like);
        pane.getChildren().remove(dislike);
        pane.getChildren().remove(heart);
    }

}

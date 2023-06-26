package view;

import controller.AttackArmyToArmyController;
import controller.Building.BuildingController;
import controller.Building.SelectedBuildingController;
import controller.GameController;
import controller.NextTurnController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Building.Castle;
import model.Empire;
import model.Human.Troop.Army;
import model.Manage;
import model.Map;
import model.Obstacle.Stone;
import model.Obstacle.Tree;
import model.Obstacle.WaterSources;
import model.User;
import view.Commands.SelectedBuildingCommands;
import view.GameButtons.BottomBarBuildings;
import view.GameButtons.BottomBarButtons;
import view.GameButtons.DropUnitDesign;
import view.GameButtons.SelectedBuildingButtons;
import view.ImageAndBackground.BottomBarImages;
import view.ImageAndBackground.BuildingImages;
import view.ImageAndBackground.GameImages;
import view.ImageAndBackground.UnitImages;
import view.Model.NewButton;
import view.OldView.SelectedBuildingMenu;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.util.*;

public class TileManager extends Application {
    //TODO : create a loading screen  for the game for about 9 seconds before the game starts and then we play the game
    //TODO : fix the stages fullscreen after exiting from shop menu
    //TODO : fix the music of the game fo creating troops
    //TODO :fix the drop building logic of stockpiles
    public ArrayList<String> cellArmyNameType = new ArrayList<>();
    public Text showCellData = new Text();
    public int avgDamage;
    public static boolean deleteOn;
    public int avgSpeed;
    public boolean selectedMenuActive;
    public BottomBarImages bottomBarImages;
    public HBox avgDetailHBox;
    public BuildingImages buildingImages;
    public BottomBarBuildings bottomBarBuildings;
    public BottomBarButtons bottomBarButtons;
    public GameImages gameImages;
    public UnitImages unitImages;
    public SelectedBuildingButtons selectedBuildingButtons;

    public TilePane view = new TilePane();
    public Button repair;
    public static StringBuilder gameLog = new StringBuilder();
    public ArrayList<NewButton>[][] allButtons;
    public ArrayList<NewButton> selectedButtons;
    public ArrayList<NewButton> selectedBuildingGraphic;
    public Text selectedBuildingTextField;
    public Text selectedBuildingHP;
    public String log = "" +
            "0:3#DROP_UNIT_GAME#5#14#0#6#5#14\n" +
            "0:3#MOUSE_CLICK#DRAW_REC#5#5#14#14\n" +
            "0:4#SELECT_UNIT#archer#4#5#14\n" +
//            "0:4#AVERAGE_DETAIL\n" +
//            "0:5#CLOSE_AVERAGE_DETAIL\n" +
            "0:5#MOVE_UNIT#8#16\n" +
            "0:16#MOUSE_CLICK#DELETE_BUTTON\n" +
            "0:16#MOUSE_CLICK#DRAW_REC#5#5#14#14\n" +
            "0:16#DROP_BUILDING#WoodCutter#5#16\n" +
            "0:17#MOUSE_CLICK#NORMAL_REMOVE#5#14\n" +
            "0:18#CLEAR_SELECTED_BUTTONS\n" +
            "0:19#MOUSE_CLICK#UNDO_BUTTON\n" +
            "0:19#MOUSE_CLICK#NORMAL_REMOVE#5#14\n" +
            "0:20#MOUSE_CLICK#DRAW_REC#4#9#9#18\n";
    //            "0:11#MOUSE_CLICK#DRAW_REC#10#10#10#10\n" +
//            "0:11#MOUSE_CLICK#NORMAL_REMOVE\n" +
//            "0:13#MOUSE_CLICK#DRAW_REC#4#9#10#15\n" +
//            "0:14#MOUSE_CLICK#DRAW_REC#4#4#17#17\n" +
//            "0:14#MOUSE_CLICK#NORMAL_REMOVE\n" +
//            "0:15#MOUSE_CLICK#DRAW_REC#3#8#11#17\n" +
//            "0:16#MOUSE_CLICK#DRAW_REC#10#10#10#10\n" +
//            "0:16#MOUSE_CLICK#NORMAL_REMOVE\n" +
//            "0:17#MOUSE_CLICK#DRAW_REC#7#9#14#20\n" +
//            "0:18#MOUSE_CLICK#DRAW_REC#7#9#23#27\n" +
//            "0:19#MOUSE_CLICK#DRAW_REC#2#6#5#11";
    public ImageView selectBackground;
    public Pane pane = new Pane();
    public int avgHp;
    public final static int[] seconds = {0};
    public final static int[] minute = {0};
    public int avgProduction;
    public int moveX;
    public int moveY;
    public Stage stage;
    public int leastProduction;
    public int mostProduction;
    public int numberOfMySoldiers;
    public double width;
    public double height;
    public ArrayList<Button> minimapButtons = new ArrayList<>();
    public ClipboardContent content;
    public Clipboard cb;
    public NewButton selectedButton;
    public ArrayList<Node> list = new ArrayList<>();

    public Scene scene;
    //TODO : stay alert to fix this after fixing the function
    public boolean playReplay = true;
    public double verticalSize = 51.2;
    public int horizontalSize = 54;
    public int viewButtonSize = 50;
    public int verticalButtons = 30;
    public int horizontalButtons = 16;
    public int zoomSize = 1;
    public static String time;
    Point firstPoint = new Point();
    Point secondPoint = new Point();
    public Map map;
    private boolean drawIsOn;
    public TileManager tileManager;
    private boolean moveIsOn;
    public String clipboardData;
    public GameController gameController = new GameController();
    public String[] lines;
    public String[] commandsTime;
    public String[][] allCommandParts;

    public void zoom1() {
        verticalSize = 51.2;
        horizontalSize = 54;
        viewButtonSize = 50;
        verticalButtons = 30;
        horizontalButtons = 16;
    }

    private void stopAllMusic() {
        if (RegisterMenu.mediaPlayer != null) RegisterMenu.mediaPlayer.stop();
        if (ProfileMenu.mediaPlayer != null) ProfileMenu.mediaPlayer.stop();
        if (MainMenu.mediaPlayer != null) MainMenu.mediaPlayer.stop();
        if (CreateMapMenu.mediaPlayer != null) CreateMapMenu.mediaPlayer.stop();
    }

    public void zoom2() {
        verticalSize = 59;
        horizontalSize = 58;
        viewButtonSize = 62;
        verticalButtons = 26;
        horizontalButtons = 14;
    }

    public void zoom3() {
        verticalSize = 70;
        horizontalSize = 68;
        viewButtonSize = 86;
        verticalButtons = 22;
        horizontalButtons = 12;
    }

    public MediaPlayer mediaPlayer;

    private void playLoginMusic() {
        stopAllMusic();
//        String defultSong = RegisterMenu.class.getResource("/Music/gameMenu.mp3").toString();
//        Media media = new Media(defultSong);
//        MediaPlayer mediaPlayer2 = new MediaPlayer(media);
//        mediaPlayer = mediaPlayer2;
//        mediaPlayer2.setAutoPlay(true);
//        mediaPlayer.setCycleCount(-1);
    }

    @Override
    public void start(Stage stage) throws Exception {
        if (playReplay) {
            lines = log.split("\n");
            allCommandParts = new String[lines.length - 1][10];
            for (int i = 0; i < lines.length - 1; i++) {
                allCommandParts[i] = lines[i].split("#");
            }
        }
        playLoginMusic();
        new CreateMapMenu();
        map = CreateMapMenu.finalMap;
        tileManager = this;
        createButtonsArraylist();
        for (int j = 0; j < 103; j++) {
            for (int i = 0; i < 100; i++) {
                NewButton newButton = new NewButton(j, i);
                applyingMouseEventForButton(newButton, stage);
                newButton.setPrefSize(verticalSize, horizontalSize);
                newButton.setFocusTraversable(false);
                newButton.setText(String.valueOf(j * 100 + i));
                list.add(newButton);
            }
        }
        width = 1530;
        height = 800;

        bottomBarImages = new BottomBarImages();
        bottomBarImages.loadImages();
        buildingImages = new BuildingImages();
        buildingImages.loadImage();
        gameImages = new GameImages();
        gameImages.loadImages();
        unitImages = new UnitImages();
        unitImages.loadImages();
        createMapGame();
        pane.requestFocus();
        pane.setFocusTraversable(false);

        createViewScene(stage);
        createMinimap(pane);

        scene = new Scene(pane, width - 50, height - 50);
        if (!playReplay) {
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    String keyName = keyEvent.getCode().getName();
                    if (keyName.equals("Add")) {
                        time = (minute[0] + ":" + seconds[0]);
                        gameLog.append(time + '#' + "ZOOM_IN" + '\n');
                        playSoundEffect("shortCut.wav");
                        zoomIn();
                    } else if (keyName.equals("Subtract")) {
                        time = (minute[0] + ":" + seconds[0]);
                        gameLog.append(time + '#' + "ZOOM_OUT" + '\n');
                        playSoundEffect("shortCut.wav");
                        zoomOut();
                    } else if (keyName.equals("F6")) {
                        time = (minute[0] + ":" + seconds[0]);
                        gameLog.append(time + '#' + "NEXT_TURN" + '\n');
                        playSoundEffect("shortCut.wav");
                        nextTurn();
                    } else if (keyName.equals("F1")) {
                        time = (minute[0] + ":" + seconds[0]);
                        gameLog.append(time + '#' + "CLEAR_SELECTED_BUTTONS" + '\n');
                        playSoundEffect("shortCut.wav");
                        removeColorOfSelectedButtons();
                    }
                    //TODO : save the game log into a file with json
                    else if (keyName.equals("F8")) {
                        log = gameLog.toString();
                        System.out.println(log);
                    } else if (keyName.equals("F3")) {
                        time = (minute[0] + ":" + seconds[0]);
                        gameLog.append(time + '#' + "DROP_UNIT" + '\n');
                        playSoundEffect("shortCut.wav");
                        dropUnitHbox();
                    } else if (keyName.equals("F4")) {
                        playSoundEffect("shortCut.wav");
                        designBoxOfMoveCommand();
                    } else if (keyName.equals("C")) {
                        time = (minute[0] + ":" + seconds[0]);
                        gameLog.append(time + '#' + "COPY_BUILDING" + '\n');
                        playSoundEffect("shortCut.wav");
                        copy();
                    } else if (keyName.equals("P")) {
                        time = (minute[0] + ":" + seconds[0]);
                        gameLog.append(time + '#' + "PASTE_BUILDING" + '\n');
                        playSoundEffect("shortCut.wav");
                        paste();
                    } else if (keyName.equals("F5")) {
                        time = (minute[0] + ":" + seconds[0]);
                        gameLog.append(time + '#' + "AVERAGE_DETAIL" + '\n');
                        playSoundEffect("shortCut.wav");
                        avgDetail();

                    }
                }
            });
        }

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seconds[0]++;
                if (seconds[0] >= 60) {
                    minute[0]++;
                    seconds[0] = 0;
                }
                if (playReplay) {
                    try {
                        replayGame();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, 0, 1000);


        stage.setTitle("Tile Pane");
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
        stage.setResizable(false);
    }

    public String copyContent;

    public void avgDetail() {
        if (selectedButtons.size() != 0) {
            int totalNumberOfTroops = totalNumberOfSoldiersInTiles();
            ArrayList<Double> averageDetails;
            averageDetails = countTheProductionAveragesOnTiles();
            designHBoxOfAverageDetails(totalNumberOfTroops, averageDetails);
        } else {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alarm = new Alert(Alert.AlertType.ERROR);
                    alarm.setTitle("Map Error!");
                    alarm.setHeaderText("Error in Map Commands");
                    alarm.setContentText("You didn't choose any cell!");
                    alarm.showAndWait();
                }
            });
        }
    }

    public void closeAvgDetail() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                pane.getChildren().remove(avgDetailHBox);
            }
        });
    }

    public void copy() {
        content = new ClipboardContent();
        if (selectedButton.getBuilding() != null) {
            content.putString(selectedButton.getBuilding().getName());
        } else {
            content.putString("");
        }
        javafx.scene.input.Clipboard.getSystemClipboard().setContent(content);
    }

    public void paste() {
        clipboardData = content.getString();
        bottomBarBuildings.fuckingSuperHardcodeCreateBuilding(pane, clipboardData, buildingImages);
    }

    public void replayCopy() {
        if (selectedButton.getBuilding() != null) {
            copyContent = selectedButton.getBuilding().getName();
        } else {
            copyContent = "";
        }
    }

    public void replayPaste() {
        BottomBarBuildings.x = selectedButton.getY();
        BottomBarBuildings.y = selectedButton.getX();
        bottomBarBuildings.fuckingSuperHardcodeCreateBuilding(pane, copyContent, buildingImages);
    }

    public void dropUnitHbox() {
        DropUnitDesign dropUnitDesign = new DropUnitDesign();
        dropUnitDesign.designHBoxForDropUnit(pane, gameController, selectedButtons);
    }

    public void closeDropUnitHbox() {
        DropUnitDesign dropUnitDesign = new DropUnitDesign();
        pane.getChildren().remove(dropUnitDesign.gethBox());
        dropUnitDesign.designHBoxForDropUnit(pane, gameController, selectedButtons);
    }

    public void zoomIn() {
        if (zoomSize != 3) {
            zoomSize++;
            if (zoomSize == 3) {
                zoom3();
            } else if (zoomSize == 2) {
                zoom2();
            }
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                pane.getChildren().clear();
                createViewScene(stage);
                bottomBarBuildings.setAllButtons(allButtons);
                scene.setRoot(pane);
            }
        });
    }

    public void zoomOut() {
        if (zoomSize != 1) {
            zoomSize--;
            if (zoomSize == 2) {
                zoom2();
            } else if (zoomSize == 1) {
                zoom1();
            }
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                pane.getChildren().clear();
                createViewScene(stage);
                bottomBarBuildings.setAllButtons(allButtons);
                scene.setRoot(pane);
            }
        });
    }

    public void nextTurn() {
        NextTurnController nextTurnController = new NextTurnController();
        nextTurnController.tileManager = tileManager;
        nextTurnController.attackArmyToArmyController = new AttackArmyToArmyController(tileManager);
        nextTurnController.nextTurn();
    }

    private void treesOfMap() {
        for (int i = 0; i < Map.mapSize; i++) {
            for (int j = 0; j < Map.mapSize; j++) {
                if (map.getObstacleMap()[i][j].isEmpty() || !(map.getObstacleMap()[i][j].get(0) instanceof Tree))
                    continue;
                NewButton castleButton = (NewButton) list.get(i * 100 + j);
                ImageView treeImage = new ImageView(new Image(TileManager.class.getResource("/image/tree/" + 1 + ".png").toExternalForm()));
                castleButton.setImageView(treeImage);
            }
        }
    }

    public int logLineReader = 0;

    public void replayGame() throws Exception {
        if (logLineReader < allCommandParts.length) {
            TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
            while (time.equals(allCommandParts[logLineReader][0])) {
                handleLogCommands(allCommandParts[logLineReader]);
                logLineReader++;
                if (logLineReader == allCommandParts.length) {
                    logLineReader--;
                    break;
                }
            }
        }

    }

    public void handleLogCommands(String[] command) throws Exception {
        System.out.println(Arrays.toString(command));
        switch (command[1]) {
            case "MOUSE_CLICK":
                mouseClickCommands(command);
                break;
            case "RIGHT_CLICK":
                break;
            case "ZOOM_IN":
                zoomIn();
                break;
            case "ZOOM_OUT":
                zoomOut();
                break;
            case "NEXT_TURN":
                nextTurn();
                break;
            case "DROP_UNIT":
                dropUnitHbox();
                break;
            case "CLOSE_DROP_UNIT":
                closeDropUnitHbox();
                break;
            case "CLEAR_SELECTED_BUTTONS":
                removeColorOfSelectedButtons();
                break;
            case "COPY_BUILDING":
                replayCopy();
                break;
            case "MOVE_UNIT":
                System.out.println(selectedButton.getX() + "    " + selectedButton.getY());
                System.out.println(selectedButton.getArmy().size());
                gameController.moveUnit(Integer.parseInt(command[2]), Integer.parseInt(command[3]), selectedButton, pane, list);
                break;
            case "PASTE_BUILDING":
                replayPaste();
                break;
            case "AVERAGE_DETAIL":
                avgDetail();
                break;
            case "CLOSE_AVERAGE_DETAIL":
                closeAvgDetail();
                break;
            case "SELECT_UNIT":
                selectUnit(command);
                break;
            case "UNDO_BUTTON":
                BottomBarButtons.undo(pane, map);
                break;
            case "DROP_UNIT_GAME":
                NewButton newButton3 = allButtons[Integer.parseInt(command[6])][Integer.parseInt(command[7])].get(0);
                gameController.dropUnits(Integer.parseInt(command[2]), Integer.parseInt(command[3]), Integer.parseInt(command[4])
                        , Integer.parseInt(command[5]), newButton3);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        pane.getChildren().clear();
                        createViewScene(stage);
                        bottomBarBuildings.setAllButtons(allButtons);
                        scene.setRoot(pane);
                    }
                });
                break;
            case "DROP_BUILDING":
                BottomBarBuildings.replayGame = true;
                BottomBarBuildings.x = Integer.parseInt(command[4]);
                BottomBarBuildings.y = Integer.parseInt(command[3]);
                bottomBarBuildings.allButtons = allButtons;
                bottomBarBuildings.fuckingSuperHardcodeCreateBuilding(pane, command[2], buildingImages);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        pane.getChildren().clear();
                        createViewScene(stage);
                        bottomBarBuildings.setAllButtons(allButtons);
                        scene.setRoot(pane);
                    }
                });
        }
    }

    public void selectUnit(String[] command) {
        gameController.selectUnitForLog(command , allButtons);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                pane.getChildren().clear();
                createViewScene(stage);
                bottomBarBuildings.setAllButtons(allButtons);
                scene.setRoot(pane);
            }
        });
    }

    public void delete(NewButton newButton) {
        System.out.println(newButton.getX() + "   " + newButton.getY());
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                pane.getChildren().remove(newButton);
                newButton.setGraphic(null);
                newButton.setImageView(null);
                newButton.setBuilding(null);
            }
        });
        int x = newButton.getX();
        int y = newButton.getY();
        if (map.buildingMap[x][y].size() != 0)
            map.buildingMap[x][y].remove(0);
        map.notPassable[x][y] = false;
        map.notBuildable[x][y] = false;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                pane.getChildren().add(newButton);
            }
        });
        time = (minute[0] + ":" + seconds[0]);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                pane.getChildren().clear();
                createViewScene(stage);
                bottomBarBuildings.setAllButtons(allButtons);
                scene.setRoot(pane);
            }
        });
    }

    public void mouseClickCommands(String[] command) throws Exception {
        switch (command[2]) {
            case "DRAW_REC":
                removeColorOfSelectedButtons();
                selectedButtons.clear();
                coloringButtons(Integer.parseInt(command[3]), Integer.parseInt(command[4]),
                        Integer.parseInt(command[5]), Integer.parseInt(command[6]));
                NewButton newButton = allButtons[Integer.parseInt(command[3])][Integer.parseInt(command[5])].get(0);
                normalRemove(newButton);
                break;
            case "NORMAL_REMOVE":
                System.out.println(command.length);
                NewButton newButton1 = allButtons[Integer.parseInt(command[3])][Integer.parseInt(command[4])].get(0);
                normalRemove(newButton1);
                break;
            case "DELETE_BUTTON":
                delete(selectedButton);
                break;
            case "SELECTED_REMOVE":
                selectedRemove();
                break;
            case "SELECTED_GRAPHIC":
                NewButton newButton2 = allButtons[Integer.parseInt(command[3])][Integer.parseInt(command[4])].get(0);
                selectedBuildingBottomGraphic(newButton2);
                break;
            case "REPAIR":
                repair(selectedBuildingMenu);
                break;
        }
    }

    private void stonesOfMap() {
        for (int i = 0; i < Map.mapSize; i++) {
            for (int j = 0; j < Map.mapSize; j++) {
                if (map.getObstacleMap()[i][j].isEmpty() || !(map.getObstacleMap()[i][j].get(0) instanceof Stone))
                    continue;
                NewButton castleButton = (NewButton) list.get(i * 100 + j);
                ImageView treeImage = new ImageView(new Image(TileManager.class.getResource("/image/Stone/" + 1 + ".png").toExternalForm()));
                castleButton.setImageView(treeImage);
            }
        }
    }

    private void waterOfMap() {
        for (int i = 0; i < Map.mapSize; i++) {
            for (int j = 0; j < Map.mapSize; j++) {
                if (map.getObstacleMap()[i][j].isEmpty() || !(map.getObstacleMap()[i][j].get(0) instanceof WaterSources))
                    continue;
                NewButton castleButton = (NewButton) list.get(i * 100 + j);
                ImageView treeImage = new ImageView(new Image(TileManager.class.getResource("/image/SeaImages/" + 1 + ".jpg").toExternalForm()));
                castleButton.setImageView(treeImage);
            }
        }
    }

    private void createMapGame() throws IOException {
        User newUser = new User("user6", "aa", "ali", "a", "1", "1", 1);
        User newUser1 = new User("user7", "aa", "dorsa", "a", "1", "1", 1);

        if (!map.getObstacleMap()[5][22].isEmpty()) {
            map.getObstacleMap()[5][22].set(0, null);
        }
        if (!map.getObstacleMap()[9][3].isEmpty()) {
            map.getObstacleMap()[9][3].set(0, null);
        }

        BuildingController buildingController = new BuildingController();
        Empire sallahDin = new Empire();
        sallahDin.setUser(newUser);
        Castle castleSallah = new Castle(sallahDin);
        castleSallah.castle();

        NewButton castleButtonSllah = (NewButton) list.get(5 * 100 + 22);
        Manage.setCurrentEmpire(sallahDin);
        System.out.println("enter tile manager");

//        this.stage = stage;
//        tileManager = new TileManager();
//        User newUser = new User("user6", "aa", "ali", "a", "1", "1", 1);
//        User newUser1 = new User("user7", "aa", "dorsa", "a", "1", "1", 1);
//        Map.CreateMap(100);
//        Empire empire = new Empire();
//        Empire empire2 = new Empire();
//        empire.setUser(newUser);
//        empire2.setUser(newUser1);
//        Manage.setCurrentEmpire(empire);
//        Manage.allEmpires.add(empire);
//        Manage.allEmpires.add(empire2);
//        BuildingController.currentEmpire = empire;

        castleButtonSllah.setBuilding(castleSallah);
        ImageView castleImage = new ImageView(new Image(TileManager.class.getResource("/image/BuildingImages/castle.png").toExternalForm()));
        castleButtonSllah.setImageView(castleImage);
        Empire richard = new Empire();
        richard.setUser(newUser1);
        Castle castleRichard = new Castle(richard);
        castleRichard.castle();
        Manage.allEmpires.add(richard);
        Manage.allEmpires.add(sallahDin);
        Manage.setCurrentEmpire(sallahDin);
        BuildingController.currentEmpire = Manage.getCurrentEmpire();
        buildingController.dropBuilding(5, 22, "Castle");
        dropStockFunction(5, 22, sallahDin);
        NewButton castleButton = (NewButton) list.get(9 * 100 + 3);
        Manage.setCurrentEmpire(richard);
        buildingController.dropBuilding(9, 3, "Castle");
        castleButton.setBuilding(castleRichard);
        ImageView castleImage2 = new ImageView(new Image(TileManager.class.getResource("/image/BuildingImages/castle.png").toExternalForm()));
        castleButton.setImageView(castleImage2);
        castleButton.setImageView(castleImage2);
        dropStockFunction(9, 3, richard);
        Manage.setCurrentEmpire(sallahDin);
        BuildingController.currentEmpire = sallahDin;
        Manage.getAllEmpires().add(sallahDin);
        Manage.getAllEmpires().add(richard);
        treesOfMap();
        stonesOfMap();
        waterOfMap();
    }

    private void dropStockFunction(int x, int y, Empire empire) {
        Manage.setCurrentEmpire(empire);
        BuildingController.dropFirstStockpile(x, y);
        ImageView foodRecource = new ImageView(new Image(TileManager.class.getResource("/image/foodRecource.png").toExternalForm()));
        NewButton foodRecourceBtn = (NewButton) list.get((x - 1) * 100 + y);
        foodRecourceBtn.setImageView(foodRecource);
        ImageView stockPile = new ImageView(new Image(TileManager.class.getResource("/image/stock.gif").toExternalForm()));
        NewButton sourceStock = (NewButton) list.get((x + 1) * 100 + y);
        sourceStock.setImageView(stockPile);
    }


    private void designHBoxOfAverageDetails(int totalNumberOfTroops, ArrayList<Double> averageDetails) {
        avgDetailHBox = new HBox();
        BackgroundImage map = new BackgroundImage(new Image(GameController.class.
                getResource("/image/GameMenu/map.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        avgDetailHBox.setBackground(new Background(map));
        avgDetailHBox.setPrefSize(800, 300);
        avgDetailHBox.setLayoutX(350);
        avgDetailHBox.setLayoutY(150);
        Text header = new Text();
        header.setText("Information");
        header.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 30));
        Text unitNumber = new Text();
        unitNumber.setText("Number of your Empire's Troops: " + totalNumberOfTroops);
        unitNumber.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        Text buildingMinAverage = new Text();
        buildingMinAverage.setText("Minimum Average of Buildings of Your Own :" + averageDetails.get(0));
        buildingMinAverage.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        Text buildingMidAverage = new Text();
        buildingMidAverage.setText("Mid Average of Buildings of Your Own :" + averageDetails.get(1));
        buildingMidAverage.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        Text buildingMaxAverage = new Text();
        buildingMaxAverage.setText("Maximum Average of Buildings of Your Own :" + averageDetails.get(0));
        buildingMaxAverage.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        Button close = new Button();
        ImageView closeIconImage = new ImageView(gameImages.getDone());
        close.setBackground(null);
        closeIconImage.setFitHeight(40);
        closeIconImage.setFitWidth(40);
        close.setGraphic(closeIconImage);
        avgDetailHBox.getChildren().add(close);
        close.setTranslateX(380);
        close.setTranslateY(115);
        close.setMinSize(40, 40);
        close.setPrefSize(40, 40);
        VBox vBox = new VBox();
        vBox.getChildren().add(header);
        vBox.getChildren().add(unitNumber);
        vBox.getChildren().add(buildingMinAverage);
        vBox.getChildren().add(buildingMidAverage);
        vBox.getChildren().add(buildingMaxAverage);
        vBox.getChildren().add(close);
        header.setTranslateX(330);
        header.setTranslateY(25);
        unitNumber.setTranslateX(155);
        unitNumber.setTranslateY(60);
        buildingMinAverage.setTranslateX(150);
        buildingMinAverage.setTranslateY(70);
        buildingMidAverage.setTranslateX(150);
        buildingMidAverage.setTranslateY(60);
        buildingMidAverage.setTranslateX(150);
        buildingMidAverage.setTranslateY(85);
        buildingMaxAverage.setTranslateX(150);
        buildingMaxAverage.setTranslateY(100);
        avgDetailHBox.getChildren().add(vBox);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                pane.getChildren().add(avgDetailHBox);
            }
        });

        close.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
                TileManager.gameLog.append(TileManager.time + '#' + "CLOSE_AVERAGE_DETAIL" + '\n');
                pane.getChildren().remove(avgDetailHBox);
            }
        });
    }

    private void dropWater(int x, int y) {
        WaterSources waterSources = new WaterSources();
        map.getObstacleMap()[x][y].add(waterSources);
        map.notBuildable[x][y] = true;
        map.notPassable[x][y] = true;
        ImageView imageView = new ImageView(new Image(TileManager.class.getResource("/images/water.png").toExternalForm()));
    }

    public void designBoxOfMoveCommand() {
        HBox hBox = new HBox();
        BackgroundImage map = new BackgroundImage(new Image(GameController.class.
                getResource("/image/GameMenu/map.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        hBox.setBackground(new Background(map));
        hBox.setPrefSize(800, 300);
        hBox.setLayoutX(350);
        hBox.setLayoutY(150);
        Text text = new Text("Move Command");
        text.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 26));
        text.setTranslateX(310);
        text.setTranslateY(30);
        TextField x = new TextField();
        x.setPromptText("Enter x of Destination");
        x.setTranslateX(20);
        x.setTranslateY(175);
        TextField y = new TextField();
        y.setPromptText("Enter y of Destination");
        y.setTranslateX(120);
        y.setTranslateY(175);
        Button close = new Button();
        close.setTranslateX(-100);
        close.setTranslateY(230);
        close.setMinSize(40, 40);
        close.setPrefSize(40, 40);
        ImageView closeIconImage = new ImageView(gameImages.getDone());
        close.setBackground(null);
        closeIconImage.setFitHeight(40);
        closeIconImage.setFitWidth(40);
        close.setGraphic(closeIconImage);
        hBox.getChildren().add(text);
        hBox.getChildren().add(x);
        hBox.getChildren().add(y);
        hBox.getChildren().add(close);
        pane.getChildren().add(hBox);
        close.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
                gameLog.append(TileManager.time + '#' + "CLOSE_MOVE_UNIT" + '\n');
                pane.getChildren().remove(hBox);
                if (x.getText() != null && y.getText() != null && x.getText().matches("\\d+") && y.getText().matches("\\d+")) {
                    int xOfDestination = Integer.parseInt(x.getText());
                    int yOfDestination = Integer.parseInt(y.getText());
                    time = (minute[0] + ":" + seconds[0]);
                    gameLog.append(time + '#' + "MOVE_UNIT" + '#' + xOfDestination + '#' + yOfDestination + '\n');
                    gameController.moveUnit(xOfDestination, yOfDestination, selectedButton, pane, list);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Game Error!");
                    alert.setHeaderText("Error in Move Command!");
                    alert.setContentText("You didn't fill the fields properly!");
                    alert.showAndWait();
                }
            }
        });

    }

    private void setButtonsOfMenus(Pane pane, BottomBarImages bottomBarImages, BuildingImages buildingImages) {
        bottomBarButtons = new BottomBarButtons();
        bottomBarBuildings = new BottomBarBuildings();
        bottomBarButtons.createButtons(pane, bottomBarImages, bottomBarBuildings, buildingImages);
        bottomBarBuildings.createCastleButtons(pane, buildingImages);
    }

    public void mouseMovement(int x1, int y1, int x2, int y2, Stage stage) {
        int maxX = (int) (x2 / 51.2);
        int minX = (int) (x1 / 51.2);
        int maxY = y2 / 54;
        int minY = y1 / 54;
        moveX += minY - maxY;
        moveY += minX - maxX;
        if (moveY + 30 > 100) {
            moveY = 70;
        }
        if (moveX + 16 > 103) {
            moveX = 87;
        }
        if (moveX < 0) {
            moveX = 0;
        }
        if (moveY < 0) {
            moveY = 0;
        }
        time = (minute[0] + ":" + seconds[0]);
        gameLog.append(time + '#' + "RIGHT_CLICK" + '#' + "MOVE_MAP" + '#' + moveX + '#' + moveY + '\n');
        pane.getChildren().clear();
        createViewScene(stage);
        bottomBarBuildings.setAllButtons(allButtons);
        scene.setRoot(pane);
    }

    private void drawRec(int x1, int y1, int x2, int y2, ArrayList<NewButton>[][] allButtons) {
        selectedButtons.clear();
        int maxX, minX, maxY, minY;
        if (x2 - x1 >= 0) {
            maxX = (int) (x2 / 51.2);
            minX = (int) (x1 / 51.2);
        } else {
            maxX = (int) (x1 / 51.2);
            minX = (int) (x2 / 51.2);
        }
        if (y2 - y1 >= 0) {
            maxY = y2 / 54;
            minY = y1 / 54;
        } else {
            maxY = y1 / 54;
            minY = y2 / 54;
        }
        coloringButtons(minY, maxY, minX, maxX);
        time = (minute[0] + ":" + seconds[0]);
        gameLog.append(time + '#' + "MOUSE_CLICK" + '#' + "DRAW_REC" + '#' + minY + '#' + maxY + '#' + minX + '#' + maxX + '\n');
        gameController.selectUnit(selectedButtons, pane);
    }

    public void coloringButtons(int y1, int y2, int x1, int x2) {
        for (int j = y1; j <= y2; j++) {
            for (int i = x1; i <= x2; i++) {
                NewButton newButton = allButtons[j][i].get(0);
                newButton.setStyle("-fx-border-color: rgba(4,17,104,0.78)");
                selectedButtons.add(newButton);
            }
        }

    }

    private int getRandomX(NewButton newButton) {
        Random random = new Random();
        int limit = (int) (newButton.getLayoutX() + 1);
        int randomX = random.nextInt(limit, limit + 10);
        return randomX;
    }

    private int getRandomY(NewButton newButton) {
        Random random = new Random();
        int limit = (int) (newButton.getLayoutY() + 1);
        int randomY = random.nextInt(limit, limit + 10);
        return randomY;
    }

    public ImageView fireImage = new ImageView(new Image(TileManager.class.getResource("/image/burning.gif").toExternalForm()));
    public ImageView sickImage = new ImageView(new Image(NextTurnController.class.getResource("/image/badSmell.gif").toExternalForm()));

    public void createViewScene(Stage stage) {
        createButtonsArraylist();
        for (int u = 0; u < horizontalButtons; u++) {
            for (int g = 0; g < verticalButtons; g++) {
                bottomBarImages.getBckGroundImage(map.getGroundType()[u + moveX][g + moveY].get(0).getGroundType());
                ((NewButton) list.get((u + moveX) * 100 + (g + moveY))).setBackground(bottomBarImages.getBackground());
                NewButton button = (NewButton) list.get((u + moveX) * 100 + (g + moveY));
                button.setLayoutX(g * verticalSize);
                button.setLayoutY(u * horizontalSize);
                button.setMinSize(viewButtonSize, viewButtonSize);
                pane.getChildren().add(button);
                if (button.isSickButton()) {
                    sickImage.setFitHeight(viewButtonSize);
                    sickImage.setFitWidth(viewButtonSize);
                    button.setGraphic(sickImage);
                }
                if (button.getImageView() != null) {
                    ImageView view;
                    if (button.getBuilding() != null && button.getBuilding().onFire) {
                        view = fireImage;
                    } else {
                        view = button.getImageView();
                    }
                    view.setFitHeight(viewButtonSize);
                    view.setFitWidth(viewButtonSize);
                    button.setGraphic(view);
                } else {
                    for (Army army : button.getArmy()) {
                        ImageView view = army.getImageView();
                        view.setImage(view.getImage());
                        view.setFitHeight(viewButtonSize);
                        view.setFitWidth(viewButtonSize);
                        int randomX = getRandomX(button);
                        int randomY = getRandomY(button);
                        view.setLayoutX(randomX);
                        view.setLayoutY(randomY);
                        pane.getChildren().add(view);
                    }
                }
                allButtons[u][g].add(button);
            }
        }
        setButtonsOfMenus(pane, bottomBarImages, buildingImages);
        createMinimap(pane);

    }

    public void getCellData(NewButton newButton) {
        cellArmyNameType.clear();
        int damage = 0;
        int hp = 0;
        int speed = 0;
        int i;
        for (i = 0; i < newButton.getArmy().size(); i++) {
            if (!cellArmyNameType.contains(newButton.getArmy().get(i).getNames().getName())) {
                cellArmyNameType.add(newButton.getArmy().get(i).getNames().getName());
            }
            damage += newButton.getArmy().get(i).getAttackPower();
            hp += newButton.getArmy().get(i).getHp();
            speed += newButton.getArmy().get(i).getSpeed();
        }
        if (i != 0) {
            avgHp = hp / i;
            avgSpeed = speed / i;
            avgDamage = damage / i;
        }
    }

    public void createButtonsArraylist() {
        allButtons = new ArrayList[16][30];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 30; j++) {
                allButtons[i][j] = new ArrayList<>();
            }
        }

    }

    public void numberOfAllSoldiers() {
        for (NewButton selectedButton : selectedButtons) {
            int x = selectedButton.getX();
            int y = selectedButton.getY();
            if (map.getTroopMap()[x][y].size() != 0) {
                for (Army army : map.getTroopMap()[x][y]) {
                    if (army.getOwner().equals(Manage.getCurrentEmpire())) {
                        numberOfMySoldiers++;
                    }
                }
            }
        }
    }

    public void clearSelectedButtons() {
        //TODO: if button is selected :
        selectedButtons.clear();
    }

    public void removeColorOfSelectedButtons() {
        for (NewButton selectedButton : selectedButtons) {
            selectedButton.setStyle("");
        }
        drawIsOn = false;
    }

    private void applyingMouseEventForButton(NewButton newButton, Stage stage) {
        selectedButtons = new ArrayList<>();
        if (!playReplay) {
            EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    showCellData.setText("");
                }
            };
            EventHandler<MouseEvent> event3 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    avgHp = 0;
                    avgDamage = 0;
                    avgSpeed = 0;
                    //TODO : The data of present army in the cell and groundType collides
                    getCellData(newButton);
                    PointerInfo a = MouseInfo.getPointerInfo();
                    Point b = a.getLocation();
                    int x = (int) b.getX();
                    int y = (int) b.getY() - 110;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("AVG Hp : " + avgHp + '\n' + "AVG Damage : " + avgDamage + '\n' +
                            "AVG Speed : " + avgSpeed + '\n' + "Ground Type : " + map.getGroundType()[newButton.getY()][newButton.getX()].get(0) + '\n');
                    for (int i = 0; i < cellArmyNameType.size(); i++) {
                        stringBuilder.append(cellArmyNameType.get(i) + " ");
                    }
                    showCellData.setText(stringBuilder.toString());
                    //TODO : The color of text must change
                    showCellData.setStyle("-fx-text-fill: #0a6562;");
                    showCellData.setX(x);
                    showCellData.setY(y);
                    if (showCellData != null && !pane.getChildren().contains(showCellData))
                        pane.getChildren().add(showCellData);
                }
            };

            EventHandler<MouseEvent> event4 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                        if (!drawIsOn) {
                            removeColorOfSelectedButtons();
                        }
                        PointerInfo a = MouseInfo.getPointerInfo();
                        firstPoint = a.getLocation();
                        firstPoint.setLocation(a.getLocation().getX(), a.getLocation().getY());
                        drawIsOn = true;
                    } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                        PointerInfo a = MouseInfo.getPointerInfo();
                        firstPoint = a.getLocation();
                        firstPoint.setLocation(a.getLocation().getX(), a.getLocation().getY());
                        moveIsOn = true;
                    }
                }
            };
            EventHandler<MouseEvent> event5 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && drawIsOn) {
                        PointerInfo a = MouseInfo.getPointerInfo();
                        playSoundEffect("clickOnBtn.mp3");
                        secondPoint.setLocation(a.getLocation().getX(), a.getLocation().getY());
                        drawRec(firstPoint.x, firstPoint.y, secondPoint.x, secondPoint.y, allButtons);
                        drawIsOn = false;
                    } else if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                        if (moveIsOn) {
                            PointerInfo a = MouseInfo.getPointerInfo();
                            secondPoint.setLocation(a.getLocation().getX(), a.getLocation().getY());
                            mouseMovement(firstPoint.x, firstPoint.y, secondPoint.x, secondPoint.y, stage);
                        }
                    }
                }
            };
            EventHandler<MouseEvent> event6 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                        selectedButtons.add(newButton);
                        PointerInfo a = MouseInfo.getPointerInfo();
                        Point b = a.getLocation();
                        int x = (int) b.getX();
                        int y = (int) b.getY() - 50;
                        StringBuilder stringBuilder = new StringBuilder();
                        numberOfAllSoldiers();
                        stringBuilder.append("Soldier Num: " + numberOfMySoldiers + "\n" + "Min Production: " + leastProduction +
                                "\nMax Production: " + mostProduction + "\nAVG Production: " + avgProduction);
                    }
                }
            };
            EventHandler<MouseEvent> event7 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (deleteOn) {
                        pane.getChildren().remove(newButton);
                        newButton.setGraphic(null);
                        newButton.setImageView(null);
                        newButton.setBuilding(null);
                        int x = newButton.getX();
                        int y = newButton.getY();
                        if (map.buildingMap[x][y].size() != 0)
                            map.buildingMap[x][y].remove(0);
                        map.notPassable[x][y] = false;
                        map.notBuildable[x][y] = false;
                        pane.getChildren().add(newButton);
                    } else {
                        time = (minute[0] + ":" + seconds[0]);
                        normalRemove(newButton);
                        gameLog.append(time + '#' + "MOUSE_CLICK" + '#' + "NORMAL_REMOVE" + '#' + newButton.getX() + '#' + newButton.getY() + '\n');
                        if (selectedMenuActive) {
                            gameLog.append(time + '#' + "MOUSE_CLICK" + '#' + "SELECTED_REMOVE" + '#' + newButton.getX() + '#' + newButton.getY() + '\n');
                            if (selectedBuildingButtons.getGatehouseText() != null) {
                                pane.getChildren().remove(selectedBuildingButtons.getGatehouseText());
                            }
                            selectedRemove();
                        }
                        if (newButton.getBuilding() != null) {
                            try {
                                gameLog.append(time + '#' + "MOUSE_CLICK" + '#' + "SELECTED_GRAPHIC" + '#' + newButton.getX() + '#' + newButton.getY() + '\n');
                                selectedBuildingBottomGraphic(newButton);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                }
            };
            newButton.setOnMousePressed(event4);
            newButton.setOnMouseReleased(event5);
            newButton.setOnMouseExited(event2);
            newButton.setOnMouseEntered(event3);
            newButton.setOnMouseClicked(event7);
        }
    }

    public void selectedRemove() {
        pane.getChildren().remove(selectedBuildingButtons.selectedBuildingsAddedButtons);
        pane.getChildren().remove(selectedBuildingHP);
        pane.getChildren().remove(repair);
    }

    public void normalRemove(NewButton newButton) {
        selectedButton = newButton;
        pane.getChildren().remove(selectedBuildingGraphic);
        pane.getChildren().remove(selectedBuildingTextField);
        pane.getChildren().remove(selectBackground);
    }

    private void playSoundEffect(String name) {
        String defultSong = RegisterMenu.class.getResource("/Music/" + name).toString();
        Media media = new Media(defultSong);
        MediaPlayer mediaPlayer2 = new MediaPlayer(media);
        mediaPlayer2.setAutoPlay(true);
    }

    public void selectedBuildingBottomGraphic(NewButton newButton) throws Exception {
        selectedBuildingGraphic = new ArrayList<>();
        selectBackground = new ImageView(bottomBarImages.getSelectedBuildingBackground());
        selectBackground.setFitWidth(980);
        selectBackground.setFitHeight(200);
        selectBackground.setLayoutX(100);
        selectBackground.setLayoutY(675);
        pane.getChildren().add(selectBackground);
        selectBuildingLogic(newButton);
    }

    public SelectedBuildingMenu selectedBuildingMenu;

    public void selectBuildingLogic(NewButton newButton) throws Exception {
        SelectedBuildingMenu.selectedBuilding = newButton.getBuilding();
        selectedBuildingMenu = new SelectedBuildingMenu();
        SelectedBuildingController.selectedBuilding = newButton.getBuilding();
        String buildingName = newButton.getBuilding().getName();
        setSelectedBuildingProperGraphic(newButton, buildingName, selectedBuildingMenu, unitImages);
    }

    public void setSelectedBuildingProperGraphic(NewButton newButton, String buildingName, SelectedBuildingMenu selectedBuildingMenu, UnitImages unitImages) throws Exception {
        selectedMenuActive = true;
        selectedBuildingButtons = new SelectedBuildingButtons();
        selectedBuildingTextField = new Text();
        selectedBuildingTextField.setText(buildingName);
        selectedBuildingTextField.setStyle("-fx-font: 24 arial");
        selectedBuildingTextField.setLayoutX(550);
        selectedBuildingTextField.setLayoutY(715);
        if (SelectedBuildingCommands.getMatcher(buildingName, SelectedBuildingCommands.REPAIR_SHOW_NAME) != null) {
            selectedBuildingHP = new Text();
            selectedBuildingHP.setText("HP : " + newButton.getBuilding().getHp());
            selectedBuildingHP.setStyle("-fx-font: 24 arial");
            selectedBuildingHP.setLayoutX(200);
            selectedBuildingHP.setLayoutY(715);
            repair = new Button();
            repair.setText("repair");
            repair.setBackground(null);
            repair.setStyle("-fx-font: 24 arial");
            repair.setLayoutX(850);
            repair.setLayoutY(700);
            EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    time = (minute[0] + ":" + seconds[0]);
                    gameLog.append(time + '#' + "MOUSE_CLICK" + '#' + "REPAIR" + newButton.getX() + '#' + newButton.getY() + '\n');
                    repair(selectedBuildingMenu);
                }
            };
            repair.setOnMouseClicked(event);
            pane.getChildren().add(selectedBuildingHP);
            pane.getChildren().add(repair);
        }
        pane.getChildren().add(selectedBuildingTextField);
        if (buildingName.equals("Barracks")) {
            selectedBuildingButtons.barracks(pane, selectedBuildingMenu, unitImages);
        } else if (buildingName.equals("Shop")) {
            selectedBuildingButtons.shop(pane);
        } else if (buildingName.equals("Mercenary")) {
            selectedBuildingButtons.mercenary(pane, selectedBuildingMenu, unitImages);
        } else if (buildingName.equals("EngineerGuild")) {
            selectedBuildingButtons.engineerGuild(pane, selectedBuildingMenu, unitImages);
        } else if (buildingName.equals("SiegeTent")) {
            selectedBuildingButtons.siegeTent(pane, selectedBuildingMenu, unitImages);
        } else if (buildingName.equals("BigChurch")) {
            selectedBuildingButtons.church(pane, selectedBuildingMenu, unitImages);
        } else if (buildingName.equals("SmallStoneGatehouse") | buildingName.equals("BigStoneGatehouse")) {
            selectedBuildingButtons.gatehouse(pane, selectedBuildingMenu, unitImages);
        } else if (buildingName.equals("DrawBridge")) {
            selectedBuildingButtons.drawBridge(pane, selectedBuildingMenu, unitImages);
        }
    }

    public void showError(String output) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("DROP BUILDING FAILED");
        error.setContentText(output);
        error.show();
    }

    public void repair(SelectedBuildingMenu selectedBuildingMenu) {
        String output = String.valueOf(selectedBuildingMenu.repair());
        if (!output.equals("building repaired successfully")) {
            showError(output);
        }
    }

    public void createMinimap(Pane pane) {
        for (int i = 0; i < horizontalButtons; i++) {
            for (int j = 0; j < verticalButtons; j++) {
                NewButton button = (NewButton) list.get((i + moveX) * 100 + (j + moveY));
                Button test = new Button();
                test.setBackground(null);
                int x = button.getX();
                int y = button.getY();
                if (map.obstacleMap[x][y].size() != 0 && map.obstacleMap[x][y].get(0) instanceof Tree) {
                    test.setStyle("-fx-background-color: #33ce12;");
                } else if (map.obstacleMap[x][y].size() != 0 && map.obstacleMap[x][y].get(0) instanceof WaterSources) {
                    test.setStyle("-fx-background-color: #091a5b;");
                } else if (map.obstacleMap[x][y].size() != 0 && map.obstacleMap[x][y].get(0) instanceof Stone) {
                    test.setStyle("-fx-background-color: #353333;");
                } else if (map.buildingMap[x][y].size() != 0 && map.buildingMap[x][y].get(0).getName().equals("Castle")) {
                    test.setStyle("-fx-background-color: #ff4a00;");
                } else if (map.buildingMap[x][y].size() != 0) {
                    test.setStyle("-fx-background-color: #4d2e0a;");
                } else if (map.troopMap[x][y].size() != 0 && map.troopMap[x][y].size() != 0) {
                    test.setStyle("-fx-background-color: #a00101;");
                } else {
                    test.setStyle("-fx-background-color: rgba(102,255,199,0.3);");
                }
                test.setLayoutX(1200 + 5 * j);
                test.setLayoutY(697 + 9.7 * i);
                test.setMinSize(5, 9.7);
                test.setMaxSize(5, 9.7);
                pane.getChildren().add(test);
            }
        }
    }

    public int totalNumberOfSoldiersInTiles() {
        int number = 0;
        for (NewButton button : selectedButtons) {
            number += button.getArmy().size();
        }
        return number;
    }

    public ArrayList<Double> countTheProductionAveragesOnTiles() {
        double minAvg = 0, maxAvg = 0, midAvg;
        ArrayList<Integer> allNumbers = new ArrayList<>();
        ArrayList<Double> finalAverages = new ArrayList<>();
        for (NewButton button : selectedButtons) {
            if (button.getBuilding() != null) {
                switch (button.getBuilding().getName()) {
                    case "IronDig":
                        minAvg = calculateMin(Manage.getCurrentEmpire().getIronMineRate(), minAvg);
                        maxAvg = calculateMax(Manage.getCurrentEmpire().getIronMineRate(), maxAvg);
                        allNumbers.add(Manage.getCurrentEmpire().getIronMineRate());
                        break;
                    case "PitchRig":
                        minAvg = calculateMin(Manage.getCurrentEmpire().getPitchRigRate(), minAvg);
                        maxAvg = calculateMax(Manage.getCurrentEmpire().getPitchRigRate(), maxAvg);
                        allNumbers.add(Manage.getCurrentEmpire().getPitchRigRate());
                        break;
                    case "Quarry":
                        minAvg = calculateMin(Manage.getCurrentEmpire().getQuarryRate(), minAvg);
                        maxAvg = calculateMax(Manage.getCurrentEmpire().getQuarryRate(), maxAvg);
                        allNumbers.add(Manage.getCurrentEmpire().getQuarryRate());
                        break;
                    case "WoodCutter":
                        minAvg = calculateMin(Manage.getCurrentEmpire().getWoodCutterRate(), minAvg);
                        maxAvg = calculateMax(Manage.getCurrentEmpire().getWoodCutterRate(), maxAvg);
                        allNumbers.add(Manage.getCurrentEmpire().getWoodCutterRate());
                        break;
                    case "AppleFarm":
                        minAvg = calculateMin(Manage.getCurrentEmpire().getAppleFarmRate(), minAvg);
                        maxAvg = calculateMax(Manage.getCurrentEmpire().getAppleFarmRate(), maxAvg);
                        allNumbers.add(Manage.getCurrentEmpire().getAppleFarmRate());
                        break;
                    case "OatFarm":
                        minAvg = calculateMin(Manage.getCurrentEmpire().getOatFarmRate(), minAvg);
                        maxAvg = calculateMax(Manage.getCurrentEmpire().getOatFarmRate(), maxAvg);
                        allNumbers.add(Manage.getCurrentEmpire().getOatFarmRate());
                        break;
                    case "HuntingPost":
                        minAvg = calculateMin(Manage.getCurrentEmpire().getHuntingPostRate(), minAvg);
                        maxAvg = calculateMax(Manage.getCurrentEmpire().getHuntingPostRate(), maxAvg);
                        allNumbers.add(Manage.getCurrentEmpire().getHuntingPostRate());
                        break;
                    case "BearFactory":
                        minAvg = calculateMin(Manage.getCurrentEmpire().getBeerFactoryRate(), minAvg);
                        maxAvg = calculateMax(Manage.getCurrentEmpire().getHuntingPostRate(), maxAvg);
                        allNumbers.add(Manage.getCurrentEmpire().getHuntingPostRate());
                        break;
                    case "Bakery":
                        minAvg = calculateMin(Manage.getCurrentEmpire().getBakeryRate(), minAvg);
                        maxAvg = calculateMax(Manage.getCurrentEmpire().getBakeryRate(), maxAvg);
                        allNumbers.add(Manage.getCurrentEmpire().getBakeryRate());
                        break;
                    case "Mill":
                        minAvg = calculateMin(Manage.getCurrentEmpire().getMillRate(), minAvg);
                        maxAvg = calculateMax(Manage.getCurrentEmpire().getMillRate(), maxAvg);
                        allNumbers.add(Manage.getCurrentEmpire().getMillRate());
                        break;
                    case "DairyProduct":
                        minAvg = calculateMin(Manage.getCurrentEmpire().getDairyFactoryRate(), minAvg);
                        maxAvg = calculateMax(Manage.getCurrentEmpire().getDairyFactoryRate(), maxAvg);
                        allNumbers.add(Manage.getCurrentEmpire().getDairyFactoryRate());
                        break;
                    case "WheatFarm":
                        minAvg = calculateMin(Manage.getCurrentEmpire().getWheatFactoryRate(), minAvg);
                        maxAvg = calculateMax(Manage.getCurrentEmpire().getWheatFactoryRate(), maxAvg);
                        allNumbers.add(Manage.getCurrentEmpire().getWheatFactoryRate());
                        break;
                    case "Inn":
                        minAvg = calculateMin(Manage.getCurrentEmpire().getInnRate(), minAvg);
                        maxAvg = calculateMax(Manage.getCurrentEmpire().getInnRate(), maxAvg);
                        allNumbers.add(Manage.getCurrentEmpire().getInnRate());
                        break;
                }
            }
        }
        midAvg = calculateAverage(allNumbers);
        finalAverages.add(minAvg);
        finalAverages.add(midAvg);
        finalAverages.add(maxAvg);
        return finalAverages;
    }

    public double calculateMin(double number, double min) {
        double minimum;
        if (min == 0) {
            minimum = number;
        } else if (number >= min) {
            minimum = min;
        } else {
            minimum = number;
        }
        return minimum;
    }

    public double calculateMax(double number, double max) {
        double maximum;
        if (max == 0) {
            maximum = number;
        } else if (number >= max) {
            maximum = number;
        } else {
            maximum = max;
        }
        return maximum;
    }

    public double calculateAverage(ArrayList<Integer> allNumbers) {
        double adding = 0;
        for (Integer allNumber : allNumbers) {
            adding += allNumber;
        }
        if (allNumbers.size() > 0) {
            return adding / allNumbers.size();
        }
        return 0.0;
    }

}

package view;

import controller.AttackArmyToArmyController;
import controller.Building.BuildingController;
import controller.Building.SelectedBuildingController;
import controller.GameController;
import controller.NextTurnController;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import model.Human.Troop.ArchersAndThrowers;
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
import java.util.ArrayList;
import java.util.Random;

public class TileManager extends Application {
    //TODO : Dear TeamMates please pay attention that you should set
    // the coordinates of your node first then you can set imageView for it.

    //TODO : Check that selected unit would be empty or not in GameController if it was full
    // show an error that user should make a decision for them

    //TODO : Method which calculates the Production things on a tile
    public Map map ;

    public ArrayList<String> cellArmyNameType = new ArrayList<>();
    public Text showCellData = new Text();
    public int avgDamage;
    public static boolean deleteOn;
    public int avgSpeed;
    public boolean selectedMenuActive;
    public BottomBarImages bottomBarImages;
    public BuildingImages buildingImages;
    public BottomBarBuildings bottomBarBuildings;
    public BottomBarButtons bottomBarButtons;
    public GameImages gameImages;
    public UnitImages unitImages;
    public SelectedBuildingButtons selectedBuildingButtons;

    public TilePane view = new TilePane();
    public Button repair;
    public ArrayList<NewButton>[][] allButtons;
    public ArrayList<NewButton> selectedButtons;
    public ArrayList<NewButton> selectedBuildingGraphic;
    public Text selectedBuildingTextField;
    public Text selectedBuildingHP;
    public ImageView selectBackground;
    public Pane pane = new Pane();
    public int avgHp;
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
    public double verticalSize = 51.2;
    public int horizontalSize = 54;
    public int viewButtonSize = 50;
    public int verticalButtons = 30;
    public int horizontalButtons = 16;
    public int zoomSize = 1;
    Point firstPoint = new Point();
    Point secondPoint = new Point();
    private boolean drawIsOn;
    public TileManager tileManager;
    private boolean moveIsOn;
    public String clipboardData;
    public GameController gameController = new GameController();

    public void zoom1() {
        verticalSize = 51.2;
        horizontalSize = 54;
        viewButtonSize = 50;
        verticalButtons = 30;
        horizontalButtons = 16;
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
    public MediaPlayer mediaPlayer ;
    private void playLoginMusic(){
        String defultSong  = RegisterMenu.class.getResource("/Music/gameMenu.mp3").toString();
        Media media = new Media(defultSong);
        MediaPlayer mediaPlayer2 = new MediaPlayer(media);
        mediaPlayer = mediaPlayer2 ;
        mediaPlayer2.setAutoPlay(true);
        mediaPlayer.setCycleCount(-1);
    }

    @Override
    public void start(Stage stage) throws Exception {
        playLoginMusic();
        new CreateMapMenu();
        map = CreateMapMenu.finalMap ;
        this.stage = stage;
        tileManager = this ;
//        treesOfMap();
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
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();
                if (keyName.equals("Add")) {
                    if (zoomSize != 3) {
                        zoomSize++;
                        if (zoomSize == 3) {
                            zoom3();
                        } else if (zoomSize == 2) {
                            zoom2();
                        }
                    }
                } else if (keyName.equals("Subtract")) {
                    if (zoomSize != 1) {
                        zoomSize--;
                        if (zoomSize == 2) {
                            zoom2();
                        } else if (zoomSize == 1) {
                            zoom1();
                        }
                    }
                } else if (keyName.equals("F6")) {
                    NextTurnController nextTurnController = new NextTurnController();
                    nextTurnController.tileManager = tileManager;
                    nextTurnController.attackArmyToArmyController = new AttackArmyToArmyController(tileManager);
                    nextTurnController.nextTurn();
                } else if (keyName.equals("F1")) {
                    removeColorOfSelectedButtons();
                } else if (keyName.equals("F3")) {
                    DropUnitDesign dropUnitDesign = new DropUnitDesign();
                    dropUnitDesign.designHBoxForDropUnit(pane, gameController, selectedButtons);
                } else if (keyName.equals("F4")) {
                    designBoxOfMoveCommand();
                } else if (keyName.equals("C")) {
                    content = new ClipboardContent();
                    if (selectedButton.getBuilding() != null) {
                        content.putString(selectedButton.getBuilding().getName());
                    } else {
                        content.putString("");
                    }
                    javafx.scene.input.Clipboard.getSystemClipboard().setContent(content);
                } else if (keyName.equals("P")) {
                    clipboardData = content.getString();
                    bottomBarBuildings.fuckingSuperHardcodeCreateBuilding(pane, clipboardData, buildingImages);
                } else if (keyName.equals("F5")) {
                    if (selectedButtons.size() != 0) {
                        int totalNumberOfTroops = totalNumberOfSoldiersInTiles();
                        ArrayList<Double> averageDetails;
                        averageDetails = countTheProductionAveragesOnTiles();
                        designHBoxOfAverageDetails(totalNumberOfTroops, averageDetails);
                    } else {
                        Alert alarm = new Alert(Alert.AlertType.ERROR);
                        alarm.setTitle("Map Error!");
                        alarm.setHeaderText("Error in Map Commands");
                        alarm.setContentText("You didn't choose any cell!");
                        alarm.showAndWait();
                    }
                }
            }
        });
        stage.setTitle("Tile Pane");
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
        stage.setResizable(false);
    }


    private void dropTree(int x, int y, int number) {
        Tree tree = new Tree();
        map.getObstacleMap()[x][y].add(tree);
//        NewButton castleButton = (NewButton) list.get(x * 100 + y);
//        ImageView treeImage = new ImageView(new Image(TileManager.class.getResource("/image/tree/" + number + ".png").toExternalForm()));
//        castleButton.setImageView(treeImage);
        map.notBuildable[x][y] = true;
    }
    private void treesOfMap(){
        for(int i = 0 ; i < Map.mapSize ; i ++){
            for(int j = 0 ; j < Map.mapSize ; j ++){
                if(map.getObstacleMap()[i][j].isEmpty() || !(map.getObstacleMap()[i][j].get(0) instanceof Tree)) continue;
                NewButton castleButton = (NewButton) list.get(i * 100 + j);
                ImageView treeImage = new ImageView(new Image(TileManager.class.getResource("/image/tree/" + 1 + ".png").toExternalForm()));
                castleButton.setImageView(treeImage);
            }
        }
    }
    private void stonesOfMap(){
        for(int i = 0 ; i < Map.mapSize ; i ++){
            for(int j = 0 ; j < Map.mapSize ; j ++){
                if(map.getObstacleMap()[i][j].isEmpty() || !(map.getObstacleMap()[i][j].get(0) instanceof Stone)) continue;
                NewButton castleButton = (NewButton) list.get(i * 100 + j);
                ImageView treeImage = new ImageView(new Image(TileManager.class.getResource("/image/Stone/" + 1 + ".png").toExternalForm()));
                castleButton.setImageView(treeImage);
            }
        }
    }
    private void waterOfMap(){
        for(int i = 0 ; i < Map.mapSize ; i ++){
            for(int j = 0 ; j < Map.mapSize ; j ++){
                if(map.getObstacleMap()[i][j].isEmpty() || !(map.getObstacleMap()[i][j].get(0) instanceof WaterSources)) continue;
                NewButton castleButton = (NewButton) list.get(i * 100 + j);
                ImageView treeImage = new ImageView(new Image(TileManager.class.getResource("/image/SeaImages/" + 1 + ".jpg").toExternalForm()));
                castleButton.setImageView(treeImage);
            }
        }
    }


    private void dropStone(int x, int y, int number) {
        Stone stone = new Stone();
        map.getObstacleMap()[x][y].add(stone);
//        NewButton castleButton = (NewButton) list.get(x * 100 + y);
//        ImageView treeImage = new ImageView(new Image(TileManager.class.getResource("/image/Stone/" + number + ".png").toExternalForm()));
//        castleButton.setImageView(treeImage);
        map.notBuildable[x][y] = true;
    }

    private void dropSea(int x, int y, int number) {
        WaterSources waterSources = new WaterSources();
        map.getObstacleMap()[x][y].add(waterSources);
//        NewButton castleButton = (NewButton) list.get(x * 100 + y);
//        ImageView treeImage = new ImageView(new Image(TileManager.class.getResource("/image/SeaImages/" + number + ".jpg").toExternalForm()));
//        castleButton.setImageView(treeImage);
        map.notBuildable[x][y] = true;
    }

    private void createMapGame() throws IOException {
        User newUser = new User("user6", "aa", "ali", "a", "1", "1", 1);
        User newUser1 = new User("user7", "aa", "dorsa", "a", "1", "1", 1);

        if(!map.getObstacleMap()[5][22].isEmpty()) {
            map.getObstacleMap()[5][22].set(0, null);
        }
        if(!map.getObstacleMap()[9][3].isEmpty()){
            map.getObstacleMap()[9][3].set(0, null);
        }

        BuildingController buildingController = new BuildingController();
        Empire sallahDin = new Empire();
        sallahDin.setUser(newUser);
        Castle castleSallah = new Castle(sallahDin);
        castleSallah.castle();

        NewButton castleButtonSllah = (NewButton) list.get(5 * 100 + 22);
        Manage.setCurrentEmpire(sallahDin);

//        buildingController.dropBuilding(5, 22, "Castle");
        dropStockFunction(5, 22, sallahDin);
        castleButtonSllah.setBuilding(castleSallah);
        ImageView castleImage = new ImageView(new Image(TileManager.class.getResource("/image/BuildingImages/castle.png").toExternalForm()));
        castleButtonSllah.setImageView(castleImage);



        Empire richard = new Empire();
        richard.setUser(newUser1);
        Castle castleRichard = new Castle(richard);
        castleRichard.castle();

        NewButton castleButton = (NewButton) list.get(9 * 100 + 3);
        Manage.setCurrentEmpire(richard);
//        buildingController.dropBuilding(9, 3, "Castle");
        castleButton.setBuilding(castleRichard);
        ImageView castleImage2 = new ImageView(new Image(TileManager.class.getResource("/image/BuildingImages/castle.png").toExternalForm()));
        castleButton.setImageView(castleImage2);
        dropStockFunction(9, 3, richard);
        Manage.setCurrentEmpire(sallahDin);
        BuildingController.currentEmpire = sallahDin;
        Manage.getAllEmpires().add(sallahDin);
        Manage.getAllEmpires().add(richard);
//        artOfTree();
        treesOfMap();
        stonesOfMap();
        waterOfMap();

    }

    private void dropStockFunction(int x, int y, Empire empire) {
        Manage.setCurrentEmpire(empire);
//        BuildingController.dropFirstStockpile(x, y);
        ImageView foodRecource = new ImageView(new Image(TileManager.class.getResource("/image/foodRecource.png").toExternalForm()));
        NewButton foodRecourceBtn = (NewButton) list.get((x - 1) * 100 + y);
        foodRecourceBtn.setImageView(foodRecource);

        ImageView stockPile = new ImageView(new Image(TileManager.class.getResource("/image/stock.gif").toExternalForm()));
        NewButton sourceStock = (NewButton) list.get((x + 1) * 100 + y);
        sourceStock.setImageView(stockPile);

    }


    private void designHBoxOfAverageDetails(int totalNumberOfTroops, ArrayList<Double> averageDetails) {
        HBox hBox = new HBox();
        BackgroundImage map = new BackgroundImage(new Image(GameController.class.
                getResource("/image/GameMenu/map.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        hBox.setBackground(new Background(map));
        hBox.setPrefSize(800, 300);
        hBox.setLayoutX(350);
        hBox.setLayoutY(150);
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
        hBox.getChildren().add(close);
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
        hBox.getChildren().add(vBox);
        pane.getChildren().add(hBox);

        close.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                pane.getChildren().remove(hBox);
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
                pane.getChildren().remove(hBox);
                if (x.getText() != null && y.getText() != null && x.getText().matches("\\d+") && y.getText().matches("\\d+")) {
                    int xOfDestination = Integer.parseInt(x.getText());
                    int yOfDestination = Integer.parseInt(y.getText());
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
        for (int j = minY; j <= maxY; j++) {
            for (int i = minX; i <= maxX; i++) {
                NewButton newButton = allButtons[j][i].get(0);
                newButton.setStyle("-fx-border-color: rgba(4,17,104,0.78)");
                selectedButtons.add(newButton);
            }
        }
        gameController.selectUnit(selectedButtons, pane);
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
    ImageView treeImage = new ImageView(new Image(TileManager.class.getResource("/image/tree/" + 1 + ".png").toExternalForm()));
    public ImageView fireImage = new ImageView(new Image(TileManager.class.getResource("/image/burning.gif").toExternalForm()));
    public ImageView sickImage = new ImageView(new Image(NextTurnController.class.getResource("/image/badSmell.gif").toExternalForm()));
    public ImageView seaImage = new ImageView(new Image(TileManager.class.getResource("/image/SeaImages/" + 1 + ".jpg").toExternalForm()));

    public ImageView stoneIMage = new ImageView(new Image(TileManager.class.getResource("/image/Stone/" + 1 + ".png").toExternalForm()));

    public void createViewScene(Stage stage) {
        createButtonsArraylist();
        for (int u = 0; u < horizontalButtons; u++) {
            for (int g = 0; g < verticalButtons; g++) {
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
//                if(!map.getObstacleMap()[u][g].isEmpty() && map.getObstacleMap()[u][g].get(0) instanceof Tree){
//                    System.out.println("hi");
//                    button.setImageView(treeImage);
//                }
//                else if(!map.getObstacleMap()[u][g].isEmpty() && map.getObstacleMap()[u][g].get(0) instanceof Stone){
//                    button.setImageView(stoneIMage);
//                }
//                else if(!map.getObstacleMap()[u][g].isEmpty() && map.getObstacleMap()[u][g].get(0) instanceof WaterSources){
//                    button.setImageView(seaImage);
//                }
                if (button.getImageView() != null) {
                    ImageView view;
                    if (button.getBuilding() != null && button.getBuilding().onFire) {
                        view = fireImage ;
                    }
                    else {
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
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                newButton.setStyle("-fx-border-color: brown");
            }
        };
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
                } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    //Do sth else
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
                    selectedButton = newButton;
                    pane.getChildren().remove(selectedBuildingGraphic);
                    pane.getChildren().remove(selectedBuildingTextField);
                    pane.getChildren().remove(selectBackground);
                    if (selectedMenuActive) {
                        if (selectedBuildingButtons.getGatehouseText() != null)
                            pane.getChildren().remove(selectedBuildingButtons.getGatehouseText());
                        pane.getChildren().remove(selectedBuildingButtons.selectedBuildingsAddedButtons);
                        pane.getChildren().remove(selectedBuildingHP);
                        pane.getChildren().remove(repair);
                    }
                    if (newButton.getBuilding() != null) {
                        try {
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

    public void selectBuildingLogic(NewButton newButton) throws Exception {
        SelectedBuildingMenu.selectedBuilding = newButton.getBuilding();
        SelectedBuildingMenu selectedBuildingMenu = new SelectedBuildingMenu();
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
                    String output = String.valueOf(selectedBuildingMenu.repair());
                    if (!output.equals("building repaired successfully")) {
                        showError(output);
                    }
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

    private void dropTreeToLocation(int x, int y1, int y2, int number) {
        for (int i = y1; i <= y2; i++) {
            dropTree(x, i, number);
        }
    }

    private void dropSeaoLocation(int x, int y1, int y2, int number) {
        for (int i = y1; i <= y2; i++) {
            dropSea(x, i, number);
        }
    }

    private void dropStoneLocation(int x, int y1, int y2, int number) {
        for (int i = y1; i <= y2; i++) {
            dropStone(x, i, number);
        }
    }

    private void artOfTree() {
         dropTreeToLocation(0, 0, 12, 5);
         dropTreeToLocation(0, 3, 8, 1);
         dropTreeToLocation(1, 0, 10, 5);
         dropTreeToLocation(2, 1, 8, 5);
         dropTreeToLocation(2, 0, 4, 1);
         dropTreeToLocation(3, 6, 7, 2);
         dropTreeToLocation(4, 0, 1, 2);
         dropTreeToLocation(4, 3, 4, 2);
         dropTreeToLocation(4, 6, 6, 1);
        dropStoneLocation(3, 8, 8, 8);
        dropStoneLocation(4, 7, 7, 8);
        dropSea(3, 2, 1);
        dropSea(3, 5, 1);
        dropSea(4, 2, 1);
        dropSea(4, 5, 1);
        dropSea(3, 1, 1);
        dropSea(4, 5, 1);
        dropSea(3, 1, 1);
        dropSea(3, 4, 1);
        dropSea(3, 3, 1);
        dropTree(3, 0, 2);
        dropTree(4, 0, 2);
        dropTree(2, 0, 2);
         dropTreeToLocation(5, 0, 6, 5);
         dropTreeToLocation(6, 1, 3, 5);
         dropTreeToLocation(7, 0, 2, 5);
        dropStoneLocation(0, 13, 17, 8);
        dropStoneLocation(1, 18, 19, 6);
        dropStoneLocation(2, 9, 9, 6);
        dropStoneLocation(5, 7, 7, 8);
        dropStoneLocation(6, 4, 5, 8);
        dropStoneLocation(7, 3, 4, 8);
         dropTreeToLocation(8, 0, 2, 1);
         dropTreeToLocation(9, 1, 2, 5);
         dropTreeToLocation(10, 0, 1, 1);
        dropSeaoLocation(11, 0, 3, 1);
        dropStoneLocation(11, 4, 4, 6);
        dropStoneLocation(12, 0, 5, 3);
         dropTreeToLocation(0, 18, 29, 1);
         dropTreeToLocation(1, 20, 29, 1);
         dropTreeToLocation(1, 22, 26, 5);
         dropTreeToLocation(2, 19, 29, 1);
        dropTreeToLocation(2, 21, 25, 5);
        dropSeaoLocation(3, 27, 29, 1);
        dropSeaoLocation(5, 24, 25, 1);
        dropSeaoLocation(4, 27, 29, 1);
        dropSeaoLocation(5, 27, 29, 1);
         dropTreeToLocation(3, 21, 26, 1);
         dropTreeToLocation(4, 23, 26, 5);
        dropTree(5, 26, 1);
         dropTreeToLocation(6, 23, 26, 5);
         dropTreeToLocation(7, 21, 29, 1);
         dropTreeToLocation(8, 27, 29, 5);
        dropStoneLocation(9, 21, 28, 3);
        dropStoneLocation(10, 19, 21, 1);
        dropStoneLocation(11, 18, 20, 2);
         dropTreeToLocation(10, 22, 29, 5);
         dropTreeToLocation(11, 21, 29, 5);
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

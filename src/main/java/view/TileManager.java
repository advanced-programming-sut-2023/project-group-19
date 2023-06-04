package view;

import controller.Building.BuildingController;
import controller.AttackArmyToArmyController;
import controller.Building.SelectedBuildingController;
import controller.GameController;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Empire;
import model.Human.Troop.ArchersAndThrowers;
import model.Human.Troop.Army;
import model.Human.Troop.Soldiers;
import model.Manage;
import model.Map;
import model.User;
import javafx.scene.control.Button;
import view.Commands.SelectedBuildingCommands;
import view.GameButtons.BottomBarBuildings;
import view.GameButtons.BottomBarButtons;
import view.Commands.SelectedBuildingCommands;
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
    public ArrayList<String> cellArmyNameType = new ArrayList<>();
    public Text showCellData = new Text();
    public int avgDamage;
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
    Point firstPoint = new Point();
    Point secondPoint = new Point();
    private boolean drawIsOn;
    private boolean moveIsOn;
    public String clipboardData;
    public GameController gameController = new GameController();

    @Override
    public void start(Stage stage) throws Exception {

        Map.CreateMap(100);
        Empire empire = new Empire();
        Empire empire2 = new Empire();
        Manage.setCurrentEmpire(empire);
        Manage.allEmpires.add(empire);
        Manage.allEmpires.add(empire2);
        BuildingController.currentEmpire = empire;
        createButtonsArraylist();

        for (int j = 0; j < 103; j++) {
            for (int i = 0; i < 100; i++) {
                NewButton newButton = new NewButton(j, i);
                applyingMouseEventForButton(newButton, stage);
                newButton.setPrefSize(51, 54);
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


//        Soldiers soldiers = new Soldiers(Manage.getCurrentEmpire());
//        Manage.getCurrentEmpire().empireArmy.add(soldiers);
//        soldiers.setState(Army.StateOfStanding.FRONT);
//        soldiers.Swordsmen(1, 2);
//        NewButton button2 = (NewButton) list.get(1 * 100 + 2);
//        button2.setBackground(null);
//        button2.getArmy().add(soldiers);
//        button2.setImageView(soldiers.getImageView());


        ArchersAndThrowers archersAndThrowers = new ArchersAndThrowers(Manage.getCurrentEmpire());
        archersAndThrowers.archer(2, 1);
        archersAndThrowers.getImageView().setFitHeight(200);
        archersAndThrowers.getImageView().setFitWidth(200);
        pane.getChildren().add(archersAndThrowers.getImageView());
        NewButton newButton = (NewButton) list.get(2 * 100 + 1);
        newButton.setBackground(null);
        archersAndThrowers.getImageView().setLayoutX(newButton.getX());
        archersAndThrowers.getImageView().setLayoutY(newButton.getY());
        newButton.getArmy().add(archersAndThrowers);
        newButton.setImageView(archersAndThrowers.getImageView());
        Manage.getCurrentEmpire().empireArmy.add(archersAndThrowers);
        newButton.getArmy().add(archersAndThrowers);
//        SlaveAnimation slaveAnimation  =  new SlaveAnimation();
//        slaveAnimation.setArmyToAnimation(soldiers2);
//        slaveAnimation.play();
//        DeadSlaveAnimation deadSlaveAnimation = new DeadSlaveAnimation(this);
//        deadSlaveAnimation.setArmyToAnimation(soldiers2);
//        deadSlaveAnimation.play();

//        AttackArmyToArmyController attackArmyToArmyController = new AttackArmyToArmyController(this);
//        attackArmyToArmyController.battleWithEnemy();

//        swordManAnimation.setArmyToAnimation(soldiers);
//        swordManAnimation.setArmyToAnimation(soldiers2);
//        attackArmyToArmyController.swordManAnimation.play();
//        SequentialTransition sequentialTransitionSwordMan = new SequentialTransition(attackArmyToArmyController.swordManAnimation, attackArmyToArmyController.swordManDeadAnimation);
//        sequentialTransitionSwordMan.play();
//
//        SequentialTransition sequentialTransitionSlave = new SequentialTransition(attackArmyToArmyController.slaveAnimation, attackArmyToArmyController.deadSlaveAnimation);
//        sequentialTransitionSlave.play();
//
//        SequentialTransition sequentialTransitionAssasin = new SequentialTransition(attackArmyToArmyController.asssasinAnimation, attackArmyToArmyController.deadAssasinAnimation);
//        sequentialTransitionAssasin.play();
//
//        SequentialTransition sequentialTransitionMaceMan = new SequentialTransition(attackArmyToArmyController.maceManAnimation, attackArmyToArmyController.deadMaceManAnimation);
//        sequentialTransitionMaceMan.play();
//
//        SequentialTransition sequentialTransitionMonk = new SequentialTransition(attackArmyToArmyController.monkAnimation, attackArmyToArmyController.deadMonkAnimation);
//        sequentialTransitionMonk.play();
//
//        SequentialTransition sequentialTransitionShortBow = new SequentialTransition(attackArmyToArmyController.shortBowAnimation, attackArmyToArmyController.deadShortBowAnimation);
//        sequentialTransitionShortBow.play();
//
//        SequentialTransition sequentialTransitionSlinger = new SequentialTransition(attackArmyToArmyController.slingerAnimation, attackArmyToArmyController.deadSlingerAnimation);
//        sequentialTransitionSlinger.play();
//
//        SequentialTransition sequentialTransitionArcher = new SequentialTransition(attackArmyToArmyController.archerAnimation, attackArmyToArmyController.deadArcherAnimation);
//        sequentialTransitionArcher.play();

        //TODO picture of sword man after war // DORSA


//       ==================================================================================================================================================

//        view.setBackground(new Background( new BackgroundImage( new Image(Game.class.getResource("/image/cegla2.jpg").toExternalForm()) ,
//                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

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

                } else if (keyName.equals("Subtract")) {

                } else if (keyName.equals("F1")) {
                    removeColorOfSelectedButtons();
                } else if (keyName.equals("F3")) {
                    DropUnitDesign dropUnitDesign = new DropUnitDesign();
                    dropUnitDesign.designHBoxForDropUnit(pane, gameController, selectedButtons);
                } else if (keyName.equals("F4")) {
                    GameController gameController = new GameController();
                    gameController.selectedUnit.add(archersAndThrowers);
                    Manage.getCurrentEmpire().empireArmy.add(archersAndThrowers);
                    archersAndThrowers.getImageView().setLayoutX(newButton.getLayoutX());
                    archersAndThrowers.getImageView().setLayoutY(newButton.getLayoutY());
                    gameController.moveUnit(5, 5, newButton, pane, list);
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

    private void designHBoxOfAverageDetails(int totalNumberOfTroops, ArrayList<Double> averageDetails) {
        //TODO : CLOSE button
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
        System.out.println("test");
        System.out.println(moveX);
        System.out.println(moveY);
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

    public void createViewScene(Stage stage) {
        createButtonsArraylist();
        for (int u = 0; u < 16; u++) {
            for (int g = 0; g < 30; g++) {
                ((NewButton) list.get((u + moveX) * 100 + (g + moveY))).setBackground(bottomBarImages.getBackground());
                NewButton button = (NewButton) list.get((u + moveX) * 100 + (g + moveY));
                button.setLayoutX(g * 51.2);
                button.setLayoutY(u * 54);
                if (button.getImageView() != null) {
                    ImageView view = button.getImageView();
                    view.setFitHeight(50);
                    view.setFitWidth(50);
                    button.setGraphic(view);
                    button.setMinSize(50, 50);
                    pane.getChildren().add(button);
                } else {
                    pane.getChildren().add(button);
                    for (Army army : button.getArmy()) {
                        ImageView view = army.getImageView();
                        view.setImage(view.getImage());
                        view.setFitHeight(50);
                        view.setFitWidth(50);
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
            if (Map.getTroopMap()[x][y].size() != 0) {
                for (Army army : Map.getTroopMap()[x][y]) {
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
                        "AVG Speed : " + avgSpeed + '\n' + "Ground Type : " + Map.getGroundType()[newButton.getY()][newButton.getX()].get(0)+'\n');
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
//                    TextInputDialog textInputDialog = new TextInputDialog();
//                    textInputDialog.setHeaderText("Enter the name and number of required army :");
//                    textInputDialog.setContentText("Name of Army: \nNumber:");
//                    Optional<String> result = textInputDialog.showAndWait();
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
                    selectedBuildingBottomGraphic(newButton);
                }
            }
        };
        newButton.setOnMousePressed(event4);
        newButton.setOnMouseReleased(event5);
        newButton.setOnMouseExited(event2);
        newButton.setOnMouseEntered(event3);
        newButton.setOnMouseClicked(event7);
    }

    public void selectedBuildingBottomGraphic(NewButton newButton) {
        selectedBuildingGraphic = new ArrayList<>();
        selectBackground = new ImageView(bottomBarImages.getSelectedBuildingBackground());
        selectBackground.setFitWidth(980);
        selectBackground.setFitHeight(200);
        selectBackground.setLayoutX(100);
        selectBackground.setLayoutY(675);
        pane.getChildren().add(selectBackground);
        selectBuildingLogic(newButton);
    }

    public void selectBuildingLogic(NewButton newButton) {
        SelectedBuildingMenu.selectedBuilding = newButton.getBuilding();
        SelectedBuildingMenu selectedBuildingMenu = new SelectedBuildingMenu();
        SelectedBuildingController.selectedBuilding = newButton.getBuilding();
        String buildingName = newButton.getBuilding().getName();
        setSelectedBuildingProperGraphic(newButton, buildingName, selectedBuildingMenu, unitImages);
    }

    public void setSelectedBuildingProperGraphic(NewButton newButton, String buildingName, SelectedBuildingMenu selectedBuildingMenu, UnitImages unitImages) {
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
    public void createMinimap(Pane pane){
        for(int i = 0 ; i < 16 ; i++){
            for(int j = 0 ; j < 30 ; j++){
                Button test = new Button();
                test.setBackground(null);
                //building brown
                //tree green
                //water blue
                //stone gray
                //default ground range dorsa
                //castle black
                //troop red
                if(i % 2 == 0)
                    test.setStyle("-fx-background-color: #805300;");
                else
                    test.setStyle("-fx-background-color: #33ce12;");
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

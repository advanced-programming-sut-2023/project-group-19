package view;

import controller.Building.BuildingController;
import controller.Building.SelectedBuildingController;
import controller.GameController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Empire;
import model.Human.Troop.ArchersAndThrowers;
import model.Human.Troop.Army;
import model.Manage;
import model.Map;
import model.User;
import view.Animations.MoveAnimation;
import view.Commands.SelectedBuildingCommands;
import view.GameButtons.BottomBarBuildings;
import view.GameButtons.BottomBarButtons;
import view.GameButtons.SelectedBuildingButtons;
import view.ImageAndBackground.BottomBarImages;
import view.ImageAndBackground.BuildingImages;
import view.Model.NewButton;
import view.OldView.SelectedBuildingMenu;

import java.awt.*;
import java.util.ArrayList;

public class TileManager extends Application {
    //TODO : Dear TeamMates please pay attention that you should set
    // the coordinates of your node first then you can set imageView for it.

    //TODO : Show Map ---> Armin's Method
    //TODO : Check that selected unit would be empty or not in GameController if it was full
    // show an error that user should make a decision for them
    //TODO : Select Unit must change
    //TODO : Method which calculates the Production things on a tile
    // width  = 1530
    // height = 800
    public ArrayList<String> cellArmyNameType = new ArrayList<>();
    public Text showCellData = new Text();
    public int avgDamage;
    public int avgSpeed;
    public BottomBarImages bottomBarImages;
    public BuildingImages buildingImages;
    public BottomBarBuildings bottomBarBuildings;
    public BottomBarButtons bottomBarButtons;
    public SelectedBuildingButtons selectedBuildingButtons;

    public TilePane view = new TilePane();

    public ArrayList<NewButton>[][] allButtons;
    public ArrayList<NewButton> selectedButtons;
    public ArrayList<NewButton> selectedBuildingGraphic;
    public Text selectedBuildingTextField;
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
    public ArrayList<Node> list = new ArrayList<>();


    public Scene scene;
    Point firstPoint = new Point();
    Point secondPoint = new Point();
    private boolean drawIsOn;
    private boolean moveIsOn;

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
//        tilePane.setLayoutX(-100);
//        tilePane.setLayoutY(-100);
//        tilePane.setPrefColumns(100);
//        tilePane.setMaxWidth(10000);
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

        ArchersAndThrowers archersAndThrowers = new ArchersAndThrowers(Manage.getCurrentEmpire());
        archersAndThrowers.archer(2, 1);
        NewButton newButton = (NewButton) list.get(2 * 100 + 1);
        newButton.setBackground(null);
        newButton.getArmy().add(archersAndThrowers);
        newButton.setImageView(archersAndThrowers.getImageView());
//       ==================================================================================================================================================

//        view.setBackground(new Background( new BackgroundImage( new Image(Game.class.getResource("/image/cegla2.jpg").toExternalForm()) ,
//                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

//        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
//        width = resolution.getWidth();
//        height = resolution.getHeight();
        pane.requestFocus();
        pane.setFocusTraversable(false);

        createViewScene(stage);
        bottomBarBuildings.setAllButtons(allButtons);
        GameController gameController = new GameController();
        gameController.selectedUnit.add(archersAndThrowers);
        gameController.setPathForUnits(3,3);
        MoveAnimation moveAnimation = new MoveAnimation(archersAndThrowers,newButton,list,pane,this);
        System.out.println(archersAndThrowers.myPath.size());
        moveAnimation.play();

        scene = new Scene(pane, width - 50, height - 50);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();
                if (keyName.equals("Add")) {

                } else if (keyName.equals("Subtract")) {

                } else if (keyName.equals("F1")) {
                    removeColorOfSelectedButtons();
                }
            }
        });
        stage.setTitle("Tile Pane");
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
        stage.setResizable(false);
    }
    private void setButtonsOfMenus(Pane pane, BottomBarImages bottomBarImages, BuildingImages buildingImages) {
        bottomBarButtons = new BottomBarButtons();
        bottomBarBuildings = new BottomBarBuildings();
        bottomBarButtons.createButtons(pane, bottomBarImages , bottomBarBuildings , buildingImages );
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

        //gameController.selectUnit(selectedButtons, pane);

    }
    public void createViewScene(Stage stage) {
        createButtonsArraylist();
        for (int u = 0; u < 16; u++) {
            for (int g = 0; g < 30; g++) {
                ((NewButton) list.get((u + moveX) * 100 + (g + moveY))).setBackground(bottomBarImages.getBackground());
                NewButton button = (NewButton) list.get((u + moveX) * 100 + (g + moveY));
                button.setLayoutX(g * 51.2);
                button.setLayoutY(u * 54);
                if(button.getImageView() != null) {
                    ImageView view = button.getImageView();
                    view.setFitHeight(50);
                    view.setFitWidth(50);
                    button.setGraphic(view);
                    button.setMinSize(50, 50);
                    pane.getChildren().add(button);
                }
                else {
                    pane.getChildren().add(button);
                }

                allButtons[u][g].add(button);
            }
        }

        setButtonsOfMenus(pane, bottomBarImages, buildingImages);
//        selectedBuildingBottomGraphic();
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
                getCellData(newButton);
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) b.getX();
                int y = (int) b.getY() - 100;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("AVG Hp : " + avgHp + '\n' + "AVG Damage : " + avgDamage + '\n' +
                        "AVG Speed : " + avgSpeed + '\n');
                for (int i = 0; i < cellArmyNameType.size(); i++) {
                    stringBuilder.append(cellArmyNameType.get(i) + " ");
                }
                showCellData.setText(stringBuilder.toString());
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
                pane.getChildren().remove(selectedBuildingGraphic);
                pane.getChildren().remove(selectedBuildingTextField);
                pane.getChildren().remove(selectBackground);
                if(newButton.getBuilding() != null){
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
    public void selectedBuildingBottomGraphic(NewButton newButton){
        selectedBuildingGraphic = new ArrayList<>();
        selectBackground = new ImageView(bottomBarImages.getSelectedBuildingBackground());
        selectBackground.setFitWidth(980);
        selectBackground.setFitHeight(200);
        selectBackground.setLayoutX(100);
        selectBackground.setLayoutY(675);
        pane.getChildren().add(selectBackground);
        selectBuildingLogic(newButton);
    }
    public void selectBuildingLogic(NewButton newButton){
        SelectedBuildingMenu.selectedBuilding = newButton.getBuilding();
        SelectedBuildingMenu selectedBuildingMenu = new SelectedBuildingMenu();
        SelectedBuildingController.selectedBuilding = newButton.getBuilding();
        String buildingName = newButton.getBuilding().getName();
        setSelectedBuildingProperGraphic(buildingName , selectedBuildingMenu);
    }
    public void setSelectedBuildingProperGraphic(String buildingName , SelectedBuildingMenu selectedBuildingMenu){
        selectedBuildingButtons = new SelectedBuildingButtons();
        selectedBuildingTextField = new Text();
        selectedBuildingTextField.setText(buildingName);
        selectedBuildingTextField.setStyle("-fx-font: 24 arial");
        selectedBuildingTextField.setLayoutX(550);
        selectedBuildingTextField.setLayoutY(715);
        pane.getChildren().add(selectedBuildingTextField);
        if (buildingName.equals("Barracks")){

        }
        else if (buildingName.equals("Mercenary")){

        }
        else if (buildingName.equals("EngineerGuild")){

        }
        else if (buildingName.equals("SiegeTent")){

        }
        else if (buildingName.equals("BigChurch") | buildingName.equals("SmallChurch")){

        }
        else if (buildingName.equals("SmallStoneGatehouse") | buildingName.equals("BigStoneGatehouse")){

        }
        else if (buildingName.equals("DrawBridge")){

        }
    }

}

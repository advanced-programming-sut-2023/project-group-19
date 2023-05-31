package view;

import controller.GameController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Empire;
import model.Human.Troop.ArchersAndThrowers;
import model.Human.Troop.Army;
import model.Manage;
import model.Map;
import model.User;
import view.Animations.MoveAnimation;
import view.ImageAndBackground.BottomBarImages;
import view.Model.NewButton;

import java.awt.*;
import java.util.ArrayList;

public class TileManager extends Application {
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

    public TilePane view = new TilePane();

    public ArrayList<NewButton>[][] allButtons;
    public ArrayList<NewButton> selectedButtons;
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

        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < 100; i++) {
                NewButton newButton = new NewButton(j, i);
                applyingMouseEventForButton(newButton, stage);
//                mouseMovement();
                newButton.setPrefSize(51, 54);
                newButton.setFocusTraversable(false);
                newButton.setText(String.valueOf(j * 100 + i));
                list.add(newButton);
            }
        }
//
        bottomBarImages = new BottomBarImages();
        bottomBarImages.loadImages();
//       ===================================================================================================================================================
        User newUser = new User("user6", "aa", "ali", "a", "1", "1", 1);
        User newUser1 = new User("user6", "aa", "dorsa", "a", "1", "1", 1);
        Empire Ali = new Empire();
        Empire Dorsa = new Empire();
        Ali.setUser(newUser);
        Dorsa.setUser(newUser1);
        Manage.setCurrentEmpire(Ali);
        Map.CreateMap(100);
        Map.mapSize = 100;
        Manage.getAllEmpires().add(Dorsa);
        Manage.getAllEmpires().add(Ali);




        ArchersAndThrowers archersAndThrowers = new ArchersAndThrowers(Manage.getCurrentEmpire());
        archersAndThrowers.archer(10, 3);
        NewButton newButton = (NewButton) list.get(10 * 100 + 3);
        System.out.println(newButton.getX() + " " + newButton.getY());
        newButton.getArmy().add(archersAndThrowers);
//       ==================================================================================================================================================

//        view.setBackground(new Background( new BackgroundImage( new Image(Game.class.getResource("/image/cegla2.jpg").toExternalForm()) ,
//                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
        width = resolution.getWidth();
        height = resolution.getHeight();
        pane.requestFocus();

        createViewScene(stage);
        GameController gameController = new GameController();
        gameController.selectedUnit.add(archersAndThrowers);
        gameController.setPathForUnits(10,5);
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
    }

    private void setButtonsOfMenus(Stage stage) {
        Pane pane1 = new Pane();
        ImageView imageView = new ImageView(bottomBarImages.getBottomImage());
        imageView.setFitHeight(250);
        imageView.setFitWidth(1200);
        imageView.setX(-10);
        imageView.setY(650);
        pane.getChildren().add(imageView);
        ImageView imageView2 = new ImageView(bottomBarImages.getBottomCoverImage());
        imageView2.setFitHeight(40);
        imageView2.setFitWidth(925);
        imageView2.setX(110);
        imageView2.setY(835);
        pane.getChildren().add(imageView2);
        ImageView imageView3 = new ImageView(bottomBarImages.getBottomSideCoverImage());
        imageView3.setFitHeight(350);
        imageView3.setFitWidth(50);
        imageView3.setX(1030);
        imageView3.setY(670);
        pane.getChildren().add(imageView3);

        Button castleButton = new Button();
        ImageView castleImage = new ImageView(bottomBarImages.getCastle());
        castleButton.setBackground(null);
        castleImage.setFitHeight(35);
        castleImage.setFitWidth(35);
        castleButton.setGraphic(castleImage);
        castleButton.setLayoutX(120);
        castleButton.setLayoutY(825);
        castleButton.setMinSize(50, 50);
        pane.getChildren().add(castleButton);

        Button foodButton = new Button();
        ImageView foodImage = new ImageView(bottomBarImages.getFood());
        foodButton.setBackground(null);
        foodImage.setFitHeight(30);
        foodImage.setFitWidth(30);
        foodButton.setGraphic(foodImage);
        foodButton.setLayoutX(180);
        foodButton.setLayoutY(825);
        foodButton.setMinSize(50, 50);
        pane.getChildren().add(foodButton);

        Button hammerButton = new Button();
        ImageView hammerImage = new ImageView(bottomBarImages.getHammer());
        hammerButton.setBackground(null);
        hammerImage.setFitHeight(30);
        hammerImage.setFitWidth(30);
        hammerButton.setGraphic(hammerImage);
        hammerButton.setLayoutX(240);
        hammerButton.setLayoutY(825);
        hammerButton.setMinSize(50, 50);
        pane.getChildren().add(hammerButton);

        Button homeButton = new Button();
        ImageView homeImage = new ImageView(bottomBarImages.getHome());
        homeButton.setBackground(null);
        homeImage.setFitHeight(30);
        homeImage.setFitWidth(30);
        homeButton.setGraphic(homeImage);
        homeButton.setLayoutX(300);
        homeButton.setLayoutY(825);
        homeButton.setMinSize(50, 50);
        pane.getChildren().add(homeButton);

        Button shieldButton = new Button();
        ImageView shieldImage = new ImageView(bottomBarImages.getShield());
        shieldButton.setBackground(null);
        shieldImage.setFitHeight(30);
        shieldImage.setFitWidth(30);
        shieldButton.setGraphic(shieldImage);
        shieldButton.setLayoutX(360);
        shieldButton.setLayoutY(825);
        shieldButton.setMinSize(50, 50);
        pane.getChildren().add(shieldButton);

        Button sickleButton = new Button();
        ImageView sickleImage = new ImageView(bottomBarImages.getSickle());
        sickleButton.setBackground(null);
        sickleImage.setFitHeight(30);
        sickleImage.setFitWidth(30);
        sickleButton.setGraphic(sickleImage);
        sickleButton.setLayoutX(410);
        sickleButton.setLayoutY(825);
        sickleButton.setMinSize(50, 50);
        pane.getChildren().add(sickleButton);

        Button gameOptionButton = new Button();
        ImageView gameOptionImage = new ImageView(bottomBarImages.getKey());
        gameOptionButton.setBackground(null);
        gameOptionImage.setFitHeight(40);
        gameOptionImage.setFitWidth(40);
        gameOptionButton.setGraphic(gameOptionImage);
        gameOptionButton.setLayoutX(1027);
        gameOptionButton.setLayoutY(675);
        gameOptionButton.setMinSize(50, 50);
        pane.getChildren().add(gameOptionButton);

        Button informationButton = new Button();
        ImageView informationImage = new ImageView(bottomBarImages.getExclamation());
        informationButton.setBackground(null);
        informationImage.setFitHeight(30);
        informationImage.setFitWidth(30);
        informationButton.setGraphic(informationImage);
        informationButton.setLayoutX(1030);
        informationButton.setLayoutY(725);
        informationButton.setMinSize(50, 50);
        pane.getChildren().add(informationButton);

        Button deleteButton = new Button();
        ImageView deleteImage = new ImageView(bottomBarImages.getClose());
        deleteButton.setBackground(null);
        deleteImage.setFitHeight(30);
        deleteImage.setFitWidth(30);
        deleteButton.setGraphic(deleteImage);
        deleteButton.setLayoutX(1033);
        deleteButton.setLayoutY(775);
        deleteButton.setMinSize(50, 50);
        pane.getChildren().add(deleteButton);

        Button undoButton = new Button();
        ImageView undoImage = new ImageView(bottomBarImages.getUndo());
        undoButton.setBackground(null);
        undoImage.setFitHeight(40);
        undoImage.setFitWidth(40);
        undoButton.setGraphic(undoImage);
        undoButton.setLayoutX(1030);
        undoButton.setLayoutY(825);
        undoButton.setMinSize(50, 50);
        pane.getChildren().add(undoButton);


        castleButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });

        foodButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });

        hammerButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });

        homeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });

        shieldButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });

        sickleButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
        gameOptionButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
        informationButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
        deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
        undoButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });


//        Button castle = new javafx.scene.control.Button();
//        castle.setBackground(new Background( new BackgroundImage( image ,
//                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT )));
//        castle.setLayoutX(0);
//        castle.setLayoutY(650);
//        castle.setPrefSize(100,100);
//        castle.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//
//            }
//        });
//        pane.getChildren().add(castle);
//        Button hammer = new javafx.scene.control.Button();
//        button.setBackground(new Background( new BackgroundImage(bottomImage ,
//                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT )));
//        button.setLayoutX(0);
//        button.setLayoutY(650);
//        button.setMinWidth(3300);
//        button.setMinHeight(220);
//        button.setPrefSize(1900,220);
//        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                BuildingMenu buildingMenu = new BuildingMenu();
//                try {
//                    buildingMenu.start(stage);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        Button home = new javafx.scene.control.Button();
//        button.setBackground(new Background( new BackgroundImage(bottomImage ,
//                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT )));
//        button.setLayoutX(0);
//        button.setLayoutY(650);
//        button.setMinWidth(3300);
//        button.setMinHeight(220);
//        button.setPrefSize(1900,220);
//        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                BuildingMenu buildingMenu = new BuildingMenu();
//                try {
//                    buildingMenu.start(stage);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        Button food = new javafx.scene.control.Button();
//        button.setBackground(new Background( new BackgroundImage(bottomImage ,
//                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT )));
//        button.setLayoutX(0);
//        button.setLayoutY(650);
//        button.setMinWidth(3300);
//        button.setMinHeight(220);
//        button.setPrefSize(1900,220);
//        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                BuildingMenu buildingMenu = new BuildingMenu();
//                try {
//                    buildingMenu.start(stage);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        Button shield = new javafx.scene.control.Button();
//        button.setBackground(new Background( new BackgroundImage(bottomImage ,
//                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT )));
//        button.setLayoutX(0);
//        button.setLayoutY(650);
//        button.setMinWidth(3300);
//        button.setMinHeight(220);
//        button.setPrefSize(1900,220);
//        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                BuildingMenu buildingMenu = new BuildingMenu();
//                try {
//                    buildingMenu.start(stage);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        Button sickle = new javafx.scene.control.Button();
//        button.setBackground(new Background( new BackgroundImage(bottomImage ,
//                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT )));
//        button.setLayoutX(0);
//        button.setLayoutY(650);
//        button.setMinWidth(3300);
//        button.setMinHeight(220);
//        button.setPrefSize(1900,220);
//        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                BuildingMenu buildingMenu = new BuildingMenu();
//                try {
//                    buildingMenu.start(stage);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });


//        pane.getChildren().addAll(castle);
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
        if (moveX + 16 > 100) {
            moveX = 84;
        }
        if (moveX < 0) {
            moveX = 0;
        }
        if (moveY < 0) {
            moveY = 0;
        }
        pane.getChildren().clear();
        createViewScene(stage);
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


    public void createViewScene(Stage stage) {
        createButtonsArraylist();
        for (int u = 0; u < 16; u++) {
            for (int g = 0; g < 30; g++) {
                ((NewButton) list.get((u + moveX) * 100 + (g + moveY))).setBackground(bottomBarImages.getBackground());
                NewButton button = (NewButton) list.get((u + moveX) * 100 + (g + moveY));
                button.setLayoutX(g * 51.2);
                button.setLayoutY(u * 54);
                pane.getChildren().add(list.get((u + moveX) * 100 + (g + moveY)));
                allButtons[u][g].add(button);
                for (int i = 0; i < button.getArmy().size(); i++) {
                    (button.getArmy().get(i)).imageView().setX(g * 51.2);
//                    (button.getArmy().get(i)).setX(g * 51.2);
//                    button.setGraphic(button.getArmy().get(i).imageView());
                    (button.getArmy().get(i)).imageView().setY(u * 54);
                    pane.getChildren().add((button.getArmy().get(i)).imageView());
                }

            }
        }
        setButtonsOfMenus(stage);
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

//                if(selectedButtons.size() == 1 ) {
//                    newButton.setStyle(null);
//                }
                showCellData.setText("");
                pane.getChildren().remove(showCellData);
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

        EventHandler<MouseEvent> event4 = new EventHandler<MouseEvent>() {//-----> Start of Number 11
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
        EventHandler<MouseEvent> event5 = new EventHandler<MouseEvent>() {// -------> Number 11
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
        EventHandler<MouseEvent> event6 = new EventHandler<MouseEvent>() { //----> Number 3
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

        newButton.setOnMousePressed(event4);
        newButton.setOnMouseReleased(event5);
        //newButton.setOnMouseExited(event2);
        newButton.setOnMouseMoved(event3);
        //newButton.setOnMouseClicked(event6);
    }


}

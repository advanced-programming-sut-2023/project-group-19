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
import view.Animations.troopFights.HorseRiderAnimation.HorseRiderAnimation;
import view.GameButtons.BottomBarBuildings;
import view.GameButtons.BottomBarButtons;
import view.Commands.SelectedBuildingCommands;
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
    //TODO : Select Unit must change
    //TODO : Method which calculates the Production things on a tile
    // width  = 1530
    // height = 800
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
    public ClipboardContent content;
    public Clipboard cb;
    public NewButton selectedButton;
    public ArrayList<Node> list = new ArrayList<>();


    public Scene scene;
    Point firstPoint = new Point();
    Point secondPoint = new Point();
    private boolean drawIsOn;
    private boolean moveIsOn;
    public int controllerOfDropUnit = 1;
    public boolean isFive = true;
    public String clipboardData;

    public GameController gameController = new GameController();
    public String nameOfUnit;

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


        NewButton button = (NewButton) list.get(3 * 100 + 3);
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
        archersAndThrowers.ArcherBow(3,3);
        button.getArmy().add(archersAndThrowers);
//        SlaveAnimation slaveAnimation  =  new SlaveAnimation();
//        slaveAnimation.setArmyToAnimation(soldiers2);
//        slaveAnimation.play();
//        DeadSlaveAnimation deadSlaveAnimation = new DeadSlaveAnimation(this);
//        deadSlaveAnimation.setArmyToAnimation(soldiers2);
//        deadSlaveAnimation.play();

        AttackArmyToArmyController attackArmyToArmyController = new AttackArmyToArmyController(this);
        attackArmyToArmyController.battleWithEnemy();

//        swordManAnimation.setArmyToAnimation(soldiers);
//        swordManAnimation.setArmyToAnimation(soldiers2);
//        attackArmyToArmyController.swordManAnimation.play();
        SequentialTransition sequentialTransitionSwordMan = new SequentialTransition(attackArmyToArmyController.swordManAnimation, attackArmyToArmyController.swordManDeadAnimation);
        sequentialTransitionSwordMan.play();

        SequentialTransition sequentialTransitionSlave = new SequentialTransition(attackArmyToArmyController.slaveAnimation, attackArmyToArmyController.deadSlaveAnimation);
        sequentialTransitionSlave.play();

        SequentialTransition sequentialTransitionAssasin = new SequentialTransition(attackArmyToArmyController.asssasinAnimation, attackArmyToArmyController.deadAssasinAnimation);
        sequentialTransitionAssasin.play();

        SequentialTransition sequentialTransitionMaceMan = new SequentialTransition(attackArmyToArmyController.maceManAnimation, attackArmyToArmyController.deadMaceManAnimation);
        sequentialTransitionMaceMan.play();

        SequentialTransition sequentialTransitionMonk = new SequentialTransition(attackArmyToArmyController.monkAnimation, attackArmyToArmyController.deadMonkAnimation);
        sequentialTransitionMonk.play();

        SequentialTransition sequentialTransitionShortBow = new SequentialTransition(attackArmyToArmyController.shortBowAnimation, attackArmyToArmyController.deadShortBowAnimation);
        sequentialTransitionShortBow.play();

        SequentialTransition sequentialTransitionSlinger = new SequentialTransition(attackArmyToArmyController.slingerAnimation, attackArmyToArmyController.deadSlingerAnimation);
        sequentialTransitionSlinger.play();

        SequentialTransition sequentialTransitionArcher = new SequentialTransition(attackArmyToArmyController.archerAnimation, attackArmyToArmyController.deadArcherAnimation);
        sequentialTransitionArcher.play();

        SequentialTransition sequentialTransitionHorseRider = new SequentialTransition(attackArmyToArmyController.horseRiderAnimation, attackArmyToArmyController.deadHorseRiderAnimation);
        sequentialTransitionHorseRider.play();
        

        //TODO picture of sword man after war // DORSA

        //TODO : after each turn clear the armies animation


//
//        ArchersAndThrowers archersAndThrowers2 = new ArchersAndThrowers(Manage.getCurrentEmpire());
//        archersAndThrowers2.HorseArchers(2, 1);
//        NewButton newButton2 = (NewButton) list.get(2 * 100 + 1);
//        newButton2.setBackground(null);
//        newButton2.getArmy().add(archersAndThrowers2);
//        newButton2.setImageView(archersAndThrowers2.getImageView());
//       ==================================================================================================================================================

//        view.setBackground(new Background( new BackgroundImage( new Image(Game.class.getResource("/image/cegla2.jpg").toExternalForm()) ,
//                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        pane.requestFocus();
        pane.setFocusTraversable(false);

        createViewScene(stage);

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
                    designHboxForDropUnit();
                } else if (keyName.equals("F4")) {
                    GameController gameController = new GameController();
                    gameController.selectedUnit.add(archersAndThrowers);
                    Manage.getCurrentEmpire().empireArmy.add(archersAndThrowers);
                    archersAndThrowers.getImageView().setLayoutX(newButton.getLayoutX());
                    archersAndThrowers.getImageView().setLayoutY(newButton.getLayoutY());
                    gameController.moveUnit(5,5,newButton,pane,list);
                }
                else if (keyName.equals("C")) {
                    content = new ClipboardContent();
                    if(selectedButton.getBuilding() != null) {
                        content.putString(selectedButton.getBuilding().getName());
                    }
                    else {
                        content.putString("");
                    }
                    javafx.scene.input.Clipboard.getSystemClipboard().setContent(content);
                }
                else if (keyName.equals("P")) {
                    clipboardData = content.getString();
                    bottomBarBuildings.fuckingSuperHardcodeCreateBuilding(pane , clipboardData , buildingImages);
                }
            }
        });
        stage.setTitle("Tile Pane");
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
        stage.setResizable(false);
    }



    private void designHboxForDropUnit() {
        HBox hBox = new HBox();
        BackgroundImage map = new BackgroundImage(new Image(GameController.class.
                getResource("/image/GameMenu/map.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        hBox.setBackground(new Background(map));
        hBox.setPrefSize(800, 300);
        hBox.setLayoutX(350);
        hBox.setLayoutY(150);

        Button prev = new Button();
        ImageView returnIconImage = new ImageView(buildingImages.getReturnIcon());
        prev.setBackground(null);
        returnIconImage.setFitHeight(40);
        returnIconImage.setFitWidth(40);
        prev.setGraphic(returnIconImage);
        hBox.getChildren().add(prev);
        prev.setTranslateX(150);
        prev.setTranslateY(240);
        prev.setMinSize(40, 40);
        prev.setPrefSize(40, 40);

        Button next = new Button();
        ImageView nextIconImage = new ImageView(gameImages.getNext());
        next.setBackground(null);
        nextIconImage.setFitHeight(40);
        nextIconImage.setFitWidth(40);
        next.setGraphic(nextIconImage);
        hBox.getChildren().add(next);
        next.setTranslateX(565);
        next.setTranslateY(240);
        next.setMinSize(40, 40);
        next.setPrefSize(40, 40);

        Button done = new Button();
        ImageView doneIconImage = new ImageView(gameImages.getDone());
        done.setBackground(null);
        doneIconImage.setFitHeight(40);
        doneIconImage.setFitWidth(40);
        done.setGraphic(doneIconImage);
        hBox.getChildren().add(done);
        done.setTranslateX(300);
        done.setTranslateY(240);
        done.setMinSize(40, 40);
        done.setPrefSize(40, 40);

        ArrayList<Spinner<Integer>> spinners = createSpinnersForUnits();
        ArrayList<ImageView> imageViews = setTheLocationOfImages();
        makeTheDefaultViewOfDrop(spinners, imageViews, hBox);
        prev.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (controllerOfDropUnit > 1) {
                    if (controllerOfDropUnit == 5) {
                        for (int i = 1; i <= 6; i++) {
                            hBox.getChildren().remove(hBox.getChildren().size() - 1);
                        }
                        for (int j = 15; j <= 19; j++) {
                            hBox.getChildren().add(imageViews.get(j));
                        }
                        for (int k = 15; k <= 19; k++) {
                            hBox.getChildren().add(spinners.get(k));
                        }
                    } else {
                        for (int i = 1; i <= 10; i++) {
                            hBox.getChildren().remove(hBox.getChildren().size() - 1);
                        }
                        int firstIndex = (controllerOfDropUnit - 2) * 5;
                        int lastIndex = (controllerOfDropUnit - 1) * 5 - 1;
                        for (int j = firstIndex; j <= lastIndex; j++) {
                            hBox.getChildren().add(imageViews.get(j));
                        }
                        for (int k = firstIndex; k <= lastIndex; k++) {
                            hBox.getChildren().add(spinners.get(k));
                        }
                    }
                    isFive = true;
                    controllerOfDropUnit--;
                }
            }
        });

        next.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (controllerOfDropUnit < 5) {
                    if (controllerOfDropUnit == 4) {
                        isFive = false;
                        for (int i = 1; i <= 10; i++) {
                            hBox.getChildren().remove(hBox.getChildren().size() - 1);
                        }
                        for (int j = 20; j <= 22; j++) {
                            hBox.getChildren().add(imageViews.get(j));
                        }
                        for (int k = 20; k <= 22; k++) {
                            hBox.getChildren().add(spinners.get(k));
                        }

                    } else {
                        for (int i = 1; i <= 10; i++) {
                            hBox.getChildren().remove(hBox.getChildren().size() - 1);
                        }
                        int firstIndex = controllerOfDropUnit * 5;
                        int lastIndex = ((controllerOfDropUnit + 1) * 5) - 1;
                        for (int j = firstIndex; j <= lastIndex; j++) {
                            hBox.getChildren().add(imageViews.get(j));
                        }
                        for (int k = firstIndex; k <= lastIndex; k++) {
                            hBox.getChildren().add(spinners.get(k));
                        }
                    }
                    controllerOfDropUnit++;
                }
            }
        });
        done.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                pane.getChildren().remove(hBox);
                for (int i = 0; i < spinners.size(); i++) {
                    gameController.dropUnits(selectedButtons.get(0).getX(), selectedButtons.get(0).getY()
                            , i, spinners.get(i).getValue(), selectedButtons.get(0));
                }
            }
        });
        pane.getChildren().add(hBox);
    }

    private void makeTheDefaultViewOfDrop(ArrayList<Spinner<Integer>> spinners, ArrayList<ImageView> imageViews, HBox hBox) {
        for (int i = 0; i < 5; i++) {
            hBox.getChildren().add(imageViews.get(i));
        }
        for (int j = 0; j < 5; j++) {
            hBox.getChildren().add(spinners.get(j));
        }
    }

    private ArrayList<Spinner<Integer>> createSpinnersForUnits() {
        ArrayList<Spinner<Integer>> spinners = new ArrayList<>();

        Spinner<Integer> archer = new Spinner<>(0, Manage.getCurrentEmpire().getEuropeArcherCount(), 0);
        Spinner<Integer> crossbowMen = new Spinner<>(0, Manage.getCurrentEmpire().getCrossbowManCount(), 0);
        Spinner<Integer> spearMen = new Spinner<>(0, Manage.getCurrentEmpire().getSpearCount(), 0);
        Spinner<Integer> pikeMen = new Spinner<>(0, Manage.getCurrentEmpire().getPikeManCount(), 0);
        Spinner<Integer> maceMen = new Spinner<>(0, Manage.getCurrentEmpire().getMaceManCount(), 0);

        Spinner<Integer> swordsMen = new Spinner<>(0, Manage.getCurrentEmpire().getSwordManCount(), 0);
        Spinner<Integer> knight = new Spinner<>(0, Manage.getCurrentEmpire().getKnightCount(), 0);
        Spinner<Integer> tunneler = new Spinner<>(0, Manage.getCurrentEmpire().getTunnelerCount(), 0);
        Spinner<Integer> ladderMen = new Spinner<>(0, Manage.getCurrentEmpire().getLadderManCount(), 0);
        Spinner<Integer> blackMonk = new Spinner<>(0, Manage.getCurrentEmpire().getBlackMonkCount(), 0);

        Spinner<Integer> archerBow = new Spinner<>(0, Manage.getCurrentEmpire().getArabianBowCount(), 0);
        Spinner<Integer> slaves = new Spinner<>(0, Manage.getCurrentEmpire().getSlaveCount(), 0);
        Spinner<Integer> slingers = new Spinner<>(0, Manage.getCurrentEmpire().getSlingerCount(), 0);
        Spinner<Integer> assassins = new Spinner<>(0, Manage.getCurrentEmpire().getAssassinCount(), 0);
        Spinner<Integer> horseArchers = new Spinner<>(0, Manage.getCurrentEmpire().getHorseArcherCount(), 0);

        Spinner<Integer> arabianSwordMen = new Spinner<>(0, Manage.getCurrentEmpire().getArabianSwordManCount(), 0);
        Spinner<Integer> fireThrowers = new Spinner<>(0, Manage.getCurrentEmpire().getFireThrowerCount(), 0);
        Spinner<Integer> catapult = new Spinner<>(0, Manage.getCurrentEmpire().getCatapultCount(), 0);
        Spinner<Integer> trebuchet = new Spinner<>(0, Manage.getCurrentEmpire().getTrebuchetCount(), 0);
        Spinner<Integer> siegeTower = new Spinner<>(0, Manage.getCurrentEmpire().getSiegeTowerCount(), 0);

        Spinner<Integer> fireBallista = new Spinner<>(0, Manage.getCurrentEmpire().getFireBalistaCount(), 0);
        Spinner<Integer> batteringRam = new Spinner<>(0, Manage.getCurrentEmpire().getBatteringRamCount(), 0);
        Spinner<Integer> portableShield = new Spinner<>(0, Manage.getCurrentEmpire().getPortableShieldCount(), 0);

        archer.setTranslateX(-200);
        archer.setTranslateY(170);
        archer.setPrefSize(52, 20);
        archer.setEditable(true);
        crossbowMen.setTranslateX(-150);
        crossbowMen.setTranslateY(170);
        crossbowMen.setPrefSize(52, 20);
        crossbowMen.setEditable(true);
        spearMen.setTranslateX(-110);
        spearMen.setTranslateY(170);
        spearMen.setPrefSize(52, 20);
        spearMen.setEditable(true);
        pikeMen.setTranslateX(-60);
        pikeMen.setTranslateY(170);
        pikeMen.setPrefSize(52, 20);
        pikeMen.setEditable(true);
        maceMen.setTranslateX(-10);
        maceMen.setTranslateY(170);
        maceMen.setPrefSize(52, 20);
        maceMen.setEditable(true);

        swordsMen.setTranslateX(-200);
        swordsMen.setTranslateY(170);
        swordsMen.setPrefSize(52, 20);
        swordsMen.setEditable(true);
        knight.setTranslateX(-150);
        knight.setTranslateY(170);
        knight.setPrefSize(52, 20);
        knight.setEditable(true);
        tunneler.setTranslateX(-110);
        tunneler.setTranslateY(170);
        tunneler.setPrefSize(52, 20);
        tunneler.setEditable(true);
        ladderMen.setTranslateX(-60);
        ladderMen.setTranslateY(170);
        ladderMen.setPrefSize(52, 20);
        ladderMen.setEditable(true);
        blackMonk.setTranslateX(-10);
        blackMonk.setTranslateY(170);
        blackMonk.setPrefSize(52, 20);
        blackMonk.setEditable(true);
        //


        archerBow.setTranslateX(-200);
        archerBow.setTranslateY(170);
        archerBow.setPrefSize(52, 20);
        archerBow.setEditable(true);
        slaves.setTranslateX(-150);
        slaves.setTranslateY(170);
        slaves.setPrefSize(52, 20);
        slaves.setEditable(true);
        slingers.setTranslateX(-110);
        slingers.setTranslateY(170);
        slingers.setPrefSize(52, 20);
        slingers.setEditable(true);
        assassins.setTranslateX(-60);
        assassins.setTranslateY(170);
        assassins.setPrefSize(52, 20);
        assassins.setEditable(true);
        horseArchers.setTranslateX(-10);
        horseArchers.setTranslateY(170);
        horseArchers.setPrefSize(52, 20);
        horseArchers.setEditable(true);

        arabianSwordMen.setTranslateX(-200);
        arabianSwordMen.setTranslateY(170);
        arabianSwordMen.setPrefSize(52, 20);
        arabianSwordMen.setEditable(true);
        fireThrowers.setTranslateX(-150);
        fireThrowers.setTranslateY(170);
        fireThrowers.setPrefSize(52, 20);
        fireThrowers.setEditable(true);
        catapult.setTranslateX(-110);
        catapult.setTranslateY(170);
        catapult.setPrefSize(52, 20);
        catapult.setEditable(true);
        trebuchet.setTranslateX(-60);
        trebuchet.setTranslateY(170);
        trebuchet.setPrefSize(52, 20);
        trebuchet.setEditable(true);
        siegeTower.setTranslateX(-10);
        siegeTower.setTranslateY(170);
        siegeTower.setPrefSize(52, 20);
        siegeTower.setEditable(true);

        fireBallista.setTranslateX(-100);
        fireBallista.setTranslateY(170);
        fireBallista.setPrefSize(52, 20);
        fireBallista.setEditable(true);
        batteringRam.setTranslateX(-50);
        batteringRam.setTranslateY(170);
        batteringRam.setPrefSize(52, 20);
        batteringRam.setEditable(true);
        portableShield.setTranslateX(10);
        portableShield.setTranslateY(170);
        portableShield.setPrefSize(52, 20);
        portableShield.setEditable(true);


        spinners.add(archer);
        spinners.add(crossbowMen);
        spinners.add(spearMen);
        spinners.add(pikeMen);
        spinners.add(maceMen);

        spinners.add(swordsMen);
        spinners.add(knight);
        spinners.add(tunneler);
        spinners.add(ladderMen);
        spinners.add(blackMonk);

        spinners.add(archerBow);
        spinners.add(slaves);
        spinners.add(slingers);
        spinners.add(assassins);
        spinners.add(horseArchers);

        spinners.add(arabianSwordMen);
        spinners.add(fireThrowers);
        spinners.add(catapult);
        spinners.add(trebuchet);
        spinners.add(siegeTower);

        spinners.add(fireBallista);
        spinners.add(batteringRam);
        spinners.add(portableShield);

        return spinners;
    }

    private ArrayList<ImageView> setTheLocationOfImages() {
        ImageView archer = new ImageView(unitImages.archer);
        ImageView crossbowMen = new ImageView(unitImages.crossbowMen);
        ImageView spearMen = new ImageView(unitImages.spearMen);
        ImageView pikeMen = new ImageView(unitImages.pikeMen);
        ImageView maceMen = new ImageView(unitImages.maceMen);

        ImageView swordsMen = new ImageView(unitImages.swordsMen);
        ImageView knight = new ImageView(unitImages.knight);
        ImageView tunneler = new ImageView(unitImages.tunneler);
        ImageView ladderMen = new ImageView(unitImages.ladderMen);
        ImageView blackMonk = new ImageView(unitImages.blackMonk);

        ImageView archerBow = new ImageView(unitImages.archerBow);
        ImageView slaves = new ImageView(unitImages.slaves);
        ImageView slingers = new ImageView(unitImages.slingers);
        ImageView assassins = new ImageView(unitImages.assassins);
        ImageView horseArchers = new ImageView(unitImages.horseArchers);

        ImageView arabianSwordMen = new ImageView(unitImages.arabianSwordMen);
        ImageView fireThrowers = new ImageView(unitImages.fireThrowers);
        ImageView catapult = new ImageView(unitImages.catapult);
        ImageView trebuchet = new ImageView(unitImages.trebuchet);
        ImageView siegeTower = new ImageView(unitImages.siegeTower);

        ImageView fireBallista = new ImageView(unitImages.fireBallista);
        ImageView batteringRam = new ImageView(unitImages.batteringRam);
        ImageView portableShield = new ImageView(unitImages.portableShield);

        archer.setTranslateX(50);
        archer.setTranslateY(50);
        archer.setFitWidth(50);
        archer.setFitHeight(80);
        crossbowMen.setTranslateX(100);
        crossbowMen.setTranslateY(50);
        crossbowMen.setFitWidth(50);
        crossbowMen.setFitHeight(80);
        spearMen.setTranslateX(150);
        spearMen.setTranslateY(50);
        spearMen.setFitWidth(50);
        spearMen.setFitHeight(80);
        pikeMen.setTranslateX(200);
        pikeMen.setTranslateY(50);
        pikeMen.setFitWidth(50);
        pikeMen.setFitHeight(80);
        maceMen.setTranslateX(250);
        maceMen.setTranslateY(50);
        maceMen.setFitWidth(50);
        maceMen.setFitHeight(80);

        swordsMen.setTranslateX(50);
        swordsMen.setTranslateY(50);
        swordsMen.setFitWidth(50);
        swordsMen.setFitHeight(80);
        knight.setTranslateX(100);
        knight.setTranslateY(50);
        knight.setFitWidth(50);
        knight.setFitHeight(80);
        tunneler.setTranslateX(150);
        tunneler.setTranslateY(50);
        tunneler.setFitWidth(50);
        tunneler.setFitHeight(80);
        ladderMen.setTranslateX(200);
        ladderMen.setTranslateY(50);
        ladderMen.setFitWidth(50);
        ladderMen.setFitHeight(80);
        blackMonk.setTranslateX(250);
        blackMonk.setTranslateY(50);
        blackMonk.setFitWidth(50);
        blackMonk.setFitHeight(80);

        archerBow.setTranslateX(50);
        archerBow.setTranslateY(50);
        archerBow.setFitWidth(50);
        archerBow.setFitHeight(80);
        slaves.setTranslateX(100);
        slaves.setTranslateY(50);
        slaves.setFitWidth(50);
        slaves.setFitHeight(80);
        slingers.setTranslateX(150);
        slingers.setTranslateY(50);
        slingers.setFitWidth(50);
        slingers.setFitHeight(80);
        assassins.setTranslateX(200);
        assassins.setTranslateY(50);
        assassins.setFitWidth(50);
        assassins.setFitHeight(80);
        horseArchers.setTranslateX(250);
        horseArchers.setTranslateY(50);
        horseArchers.setFitWidth(50);
        horseArchers.setFitHeight(80);

        arabianSwordMen.setTranslateX(50);
        arabianSwordMen.setTranslateY(50);
        arabianSwordMen.setFitWidth(50);
        arabianSwordMen.setFitHeight(80);
        fireThrowers.setTranslateX(100);
        fireThrowers.setTranslateY(50);
        fireThrowers.setFitWidth(50);
        fireThrowers.setFitHeight(80);
        catapult.setTranslateX(150);
        catapult.setTranslateY(50);
        catapult.setFitWidth(50);
        catapult.setFitHeight(80);
        trebuchet.setTranslateX(200);
        trebuchet.setTranslateY(50);
        trebuchet.setFitWidth(50);
        trebuchet.setFitHeight(80);
        siegeTower.setTranslateX(250);
        siegeTower.setTranslateY(50);
        siegeTower.setFitWidth(50);
        siegeTower.setFitHeight(80);

        fireBallista.setTranslateX(50);
        fireBallista.setTranslateY(50);
        fireBallista.setFitWidth(50);
        fireBallista.setFitHeight(80);
        batteringRam.setTranslateX(100);
        batteringRam.setTranslateY(50);
        batteringRam.setFitWidth(50);
        batteringRam.setFitHeight(80);
        portableShield.setTranslateX(150);
        portableShield.setTranslateY(50);
        portableShield.setFitWidth(50);
        portableShield.setFitHeight(80);

        ArrayList<ImageView> imageViews = new ArrayList<>();
        imageViews.add(archer);
        imageViews.add(crossbowMen);
        imageViews.add(spearMen);
        imageViews.add(pikeMen);
        imageViews.add(maceMen);
        imageViews.add(swordsMen);
        imageViews.add(knight);
        imageViews.add(tunneler);
        imageViews.add(ladderMen);
        imageViews.add(blackMonk);
        imageViews.add(archerBow);
        imageViews.add(slaves);
        imageViews.add(slingers);
        imageViews.add(assassins);
        imageViews.add(horseArchers);
        imageViews.add(arabianSwordMen);
        imageViews.add(fireThrowers);
        imageViews.add(catapult);
        imageViews.add(trebuchet);
        imageViews.add(siegeTower);
        imageViews.add(fireBallista);
        imageViews.add(batteringRam);
        imageViews.add(portableShield);
        return imageViews;
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
    private int getRandomX(NewButton newButton){
        Random random = new Random();
        int limit = (int)(newButton.getLayoutX() + 1);
        int randomX = random.nextInt( limit,limit +  10);
        return randomX ;
    }
    private int getRandomY(NewButton newButton){
        Random random = new Random();
        int limit = (int)(newButton.getLayoutY() + 1);
        int randomY = random.nextInt( limit,limit +  10);
        return randomY ;
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
                }
                allButtons[u][g].add(button);
            }
        }
//        pane.getChildren().add(button);
//        button.setMinSize(50, 50);
//        for(Army army : button.getArmy()) {
//            ImageView view = army.getImageView();
//            view.setImage(view.getImage());
//            view.setFitHeight(60);
//            view.setFitWidth(60);
//            int randomX = getRandomX(button);
//            int randomY = getRandomY(button);
//            view.setLayoutX(randomX);
//            view.setLayoutY(randomY);
//            pane.getChildren().add(view);
//        }
//                System.out.println(((NewButton)list.get(100 * 2 + 1)).getArrows().size());
//        allButtons[u][g].add(button);
        setButtonsOfMenus(pane, bottomBarImages, buildingImages);

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
                selectedButton = newButton;
                pane.getChildren().remove(selectedBuildingGraphic);
                pane.getChildren().remove(selectedBuildingTextField);
                pane.getChildren().remove(selectBackground);
                if (selectedMenuActive) {
                    if(selectedBuildingButtons.getGatehouseText() != null)
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
        setSelectedBuildingProperGraphic(newButton ,buildingName, selectedBuildingMenu, unitImages);
    }

    public void setSelectedBuildingProperGraphic(NewButton newButton , String buildingName, SelectedBuildingMenu selectedBuildingMenu, UnitImages unitImages) {
        selectedMenuActive = true;
        selectedBuildingButtons = new SelectedBuildingButtons();
        selectedBuildingTextField = new Text();
        selectedBuildingTextField.setText(buildingName);
        selectedBuildingTextField.setStyle("-fx-font: 24 arial");
        selectedBuildingTextField.setLayoutX(550);
        selectedBuildingTextField.setLayoutY(715);
        if(SelectedBuildingCommands.getMatcher(buildingName, SelectedBuildingCommands.REPAIR_SHOW_NAME) != null){
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
                    if(!output.equals("building repaired successfully")){
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
    public void showError(String output){
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("DROP BUILDING FAILED");
        error.setContentText(output);
        error.show();
    }

}

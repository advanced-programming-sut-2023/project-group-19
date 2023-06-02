package view.GameButtons;

import controller.Building.BuildingController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import model.Building.*;
import model.Human.Troop.ArchersAndThrowers;
import model.Human.Troop.Army;
import model.Manage;
import view.ImageAndBackground.BottomBarImages;
import view.ImageAndBackground.BuildingImages;
import view.Model.NewButton;
import view.TileManager;

import java.awt.*;
import java.util.ArrayList;

import static view.Main.stage;

public class BottomBarBuildings {
    //TODO : handle the draw bridge for now i set the x and y  to 0 , 0
    //TODO : handle the Siegetroop for now I set the x and y  to 0 , 0
    //TODO : add sickness logic to the game and fix its eventhandler
    public ArrayList<Button> addedButtons = new ArrayList<>();
    public ArrayList<ImageView> addedImages = new ArrayList<>();
    BuildingController buildingController = new BuildingController();
    public ArrayList<NewButton>[][] allButtons;
    public Building building ;
    public Army troop;
    public void showError(String output){
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("DROP BUILDING FAILED");
        error.setContentText(output);
        error.show();
    }

    public void createCastleButtons(Pane pane, BuildingImages buildingImages) {
        Button wallStairButton = new Button();
        ImageView wallStairImage = new ImageView(buildingImages.getWallStair());
        wallStairButton.setBackground(null);
        wallStairImage.setFitHeight(100);
        wallStairImage.setFitWidth(100);
        wallStairButton.setGraphic(wallStairImage);
        wallStairButton.setLayoutX(110);
        wallStairButton.setLayoutY(700);
        wallStairButton.setMinSize(100, 100);
        addedButtons.add(wallStairButton);
        pane.getChildren().add(wallStairButton);
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Wall stair = new Wall(Manage.getCurrentEmpire());
                stair.stair();
                building = stair;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(wallStairImage);
                    newbutton.setBuilding(stair);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        wallStairButton.setOnMouseReleased(event);

        Button lowWallButton = new Button();
        ImageView lowWallImage = new ImageView(buildingImages.getLowWall());
        lowWallButton.setBackground(null);
        lowWallImage.setFitHeight(100);
        lowWallImage.setFitWidth(100);
        lowWallButton.setGraphic(lowWallImage);
        lowWallButton.setLayoutX(210);
        lowWallButton.setLayoutY(715);
        lowWallButton.setMinSize(100, 100);
        addedButtons.add(lowWallButton);
        pane.getChildren().add(lowWallButton);
        EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Wall lowWall = new Wall(Manage.getCurrentEmpire());
                lowWall.smallWall();
                building = lowWall;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(lowWallImage);
                    newbutton.setBuilding(lowWall);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        lowWallButton.setOnMouseReleased(event2);

        Button stoneWallButton = new Button();
        ImageView stoneWallImage = new ImageView(buildingImages.getStoneWall());
        stoneWallButton.setBackground(null);
        stoneWallImage.setFitHeight(100);
        stoneWallImage.setFitWidth(100);
        stoneWallButton.setGraphic(stoneWallImage);
        stoneWallButton.setLayoutX(340);
        stoneWallButton.setLayoutY(700);
        stoneWallButton.setMinSize(100, 100);
        addedButtons.add(stoneWallButton);
        pane.getChildren().add(stoneWallButton);
        EventHandler<MouseEvent> event3 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Wall bigWall = new Wall(Manage.getCurrentEmpire());
                bigWall.bigWall();
                building = bigWall;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(stoneWallImage);
                    newbutton.setBuilding(bigWall);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        stoneWallButton.setOnMouseReleased(event3);
        Button crenulatedWallButton = new Button();
        ImageView crenulatedWallImage = new ImageView(buildingImages.getCrenulatedWall());
        crenulatedWallButton.setBackground(null);
        crenulatedWallImage.setFitHeight(100);
        crenulatedWallImage.setFitWidth(100);
        crenulatedWallButton.setGraphic(crenulatedWallImage);
        crenulatedWallButton.setLayoutX(470);
        crenulatedWallButton.setLayoutY(700);
        crenulatedWallButton.setMinSize(100, 100);
        addedButtons.add(crenulatedWallButton);
        pane.getChildren().add(crenulatedWallButton);
        EventHandler<MouseEvent> event4 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Wall bigWall = new Wall(Manage.getCurrentEmpire());
                bigWall.bigWall();
                building = bigWall;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(crenulatedWallImage);
                    newbutton.setBuilding(bigWall);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        crenulatedWallButton.setOnMouseReleased(event4);

        Button barracksButton = new Button();
        ImageView barracksImage = new ImageView(buildingImages.getBarracks());
        barracksButton.setBackground(null);
        barracksImage.setFitHeight(100);
        barracksImage.setFitWidth(100);
        barracksButton.setGraphic(barracksImage);
        barracksButton.setLayoutX(600);
        barracksButton.setLayoutY(700);
        barracksButton.setMinSize(100, 100);
        addedButtons.add(barracksButton);
        pane.getChildren().add(barracksButton);
        EventHandler<MouseEvent> event5 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                PrepareLaboursAndFighters barracks = new PrepareLaboursAndFighters(Manage.getCurrentEmpire());
                barracks.barracks();
                building = barracks;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(barracksImage);
                    newbutton.setBuilding(barracks);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        barracksButton.setOnMouseReleased(event5);

        Button mercenaryButton = new Button();
        ImageView mercenaryImage = new ImageView(buildingImages.getMercenary());
        mercenaryButton.setBackground(null);
        mercenaryImage.setFitHeight(100);
        mercenaryImage.setFitWidth(100);
        mercenaryButton.setGraphic(mercenaryImage);
        mercenaryButton.setLayoutX(730);
        mercenaryButton.setLayoutY(700);
        mercenaryButton.setMinSize(100, 100);
        addedButtons.add(mercenaryButton);
        pane.getChildren().add(mercenaryButton);
        EventHandler<MouseEvent> event6 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                PrepareLaboursAndFighters mercenary = new PrepareLaboursAndFighters(Manage.getCurrentEmpire());
                mercenary.mercenary();
                building = mercenary;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(mercenaryImage);
                    newbutton.setBuilding(mercenary);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        mercenaryButton.setOnMouseReleased(event6);

        Button armoryButton = new Button();
        ImageView armoryImage = new ImageView(buildingImages.getArmory());
        armoryButton.setBackground(null);
        armoryImage.setFitHeight(100);
        armoryImage.setFitWidth(100);
        armoryButton.setGraphic(armoryImage);
        armoryButton.setLayoutX(860);
        armoryButton.setLayoutY(700);
        armoryButton.setMinSize(100, 100);
        addedButtons.add(armoryButton);
        pane.getChildren().add(armoryButton);
        EventHandler<MouseEvent> event7 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Armoury armoury = new Armoury(Manage.getCurrentEmpire());
                armoury.armoury();
                building = armoury;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(armoryImage);
                    newbutton.setBuilding(armoury);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        armoryButton.setOnMouseReleased(event7);

        Button gatehouseIconButton = new Button();
        ImageView gatehouseIconImage = new ImageView(buildingImages.getGatehouseIcon());
        gatehouseIconButton.setBackground(null);
        gatehouseIconImage.setFitHeight(40);
        gatehouseIconImage.setFitWidth(40);
        gatehouseIconButton.setGraphic(gatehouseIconImage);
        gatehouseIconButton.setLayoutX(980);
        gatehouseIconButton.setLayoutY(690);
        gatehouseIconButton.setMinSize(40, 40);
        addedButtons.add(gatehouseIconButton);
        pane.getChildren().add(gatehouseIconButton);
        EventHandler<MouseEvent> event8 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                clearPane(pane);
                createGatehouseButtons(pane , buildingImages);
            }
        };
        gatehouseIconButton.setOnMouseClicked(event8);


        Button towerIconButton = new Button();
        ImageView towerIconImage = new ImageView(buildingImages.getTowersIcon());
        towerIconButton.setBackground(null);
        towerIconImage.setFitHeight(40);
        towerIconImage.setFitWidth(40);
        towerIconButton.setGraphic(towerIconImage);
        towerIconButton.setLayoutX(980);
        towerIconButton.setLayoutY(740);
        towerIconButton.setMinSize(40, 40);
        addedButtons.add(towerIconButton);
        pane.getChildren().add(towerIconButton);
        EventHandler<MouseEvent> event9 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                clearPane(pane);
                createTowerButtons(pane , buildingImages);
            }
        };
        towerIconButton.setOnMouseClicked(event9);

        Button militaryBuildingsIconButton = new Button();
        ImageView militaryBuildingsIconImage = new ImageView(buildingImages.getMilitaryBuildingsIcon());
        militaryBuildingsIconButton.setBackground(null);
        militaryBuildingsIconImage.setFitHeight(40);
        militaryBuildingsIconImage.setFitWidth(40);
        militaryBuildingsIconButton.setGraphic(militaryBuildingsIconImage);
        militaryBuildingsIconButton.setLayoutX(980);
        militaryBuildingsIconButton.setLayoutY(790);
        militaryBuildingsIconButton.setMinSize(40, 40);
        addedButtons.add(militaryBuildingsIconButton);
        pane.getChildren().add(militaryBuildingsIconButton);
        EventHandler<MouseEvent> event10 = new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                clearPane(pane);
                createMilitaryBuildingsButtons(pane , buildingImages);
            }
        };
        militaryBuildingsIconButton.setOnMouseClicked(event10);
    }

    public void createGatehouseButtons(Pane pane, BuildingImages buildingImages) {
        Button returnIconButton = new Button();
        ImageView returnIconImage = new ImageView(buildingImages.getReturnIcon());
        returnIconButton.setBackground(null);
        returnIconImage.setFitHeight(40);
        returnIconImage.setFitWidth(40);
        returnIconButton.setGraphic(returnIconImage);
        returnIconButton.setLayoutX(120);
        returnIconButton.setLayoutY(740);
        returnIconButton.setMinSize(40, 40);
        addedButtons.add(returnIconButton);
        pane.getChildren().add(returnIconButton);
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                clearPane(pane);
                createCastleButtons(pane , buildingImages);
            }
        };
        returnIconButton.setOnMouseClicked(event);

        Button smallGatehouseButton = new Button();
        ImageView smallGatehouseImage = new ImageView(buildingImages.getSmallStoneGatehouse());
        smallGatehouseButton.setBackground(null);
        smallGatehouseImage.setFitHeight(100);
        smallGatehouseImage.setFitWidth(100);
        smallGatehouseButton.setGraphic(smallGatehouseImage);
        smallGatehouseButton.setLayoutX(220);
        smallGatehouseButton.setLayoutY(700);
        smallGatehouseButton.setMinSize(100, 100);
        addedButtons.add(smallGatehouseButton);
        pane.getChildren().add(smallGatehouseButton);
        EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                StoneGateWay stoneGateWay = new StoneGateWay(Manage.getCurrentEmpire());
                stoneGateWay.smallGateWay(Names.NS);
                building = stoneGateWay;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(smallGatehouseImage);
                    newbutton.setBuilding(stoneGateWay);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        smallGatehouseButton.setOnMouseReleased(event1);

        Button bigGatehouseButton = new Button();
        ImageView bigGatehouseImage = new ImageView(buildingImages.getBigStoneGateHouse());
        bigGatehouseButton.setBackground(null);
        bigGatehouseImage.setFitHeight(100);
        bigGatehouseImage.setFitWidth(100);
        bigGatehouseButton.setGraphic(bigGatehouseImage);
        bigGatehouseButton.setLayoutX(360);
        bigGatehouseButton.setLayoutY(700);
        bigGatehouseButton.setMinSize(100, 100);
        addedButtons.add(bigGatehouseButton);
        pane.getChildren().add(bigGatehouseButton);
        EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                StoneGateWay stoneGateWay = new StoneGateWay(Manage.getCurrentEmpire());
                stoneGateWay.bigGateWay(Names.NS);
                building = stoneGateWay;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(bigGatehouseImage);
                    newbutton.setBuilding(stoneGateWay);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        bigGatehouseButton.setOnMouseReleased(event2);

        Button drawBridgeButton = new Button();
        ImageView drawBridgeImage = new ImageView(buildingImages.getDrawBridge());
        drawBridgeButton.setBackground(null);
        drawBridgeImage.setFitHeight(100);
        drawBridgeImage.setFitWidth(100);
        drawBridgeButton.setGraphic(drawBridgeImage);
        drawBridgeButton.setLayoutX(500);
        drawBridgeButton.setLayoutY(700);
        drawBridgeButton.setMinSize(100, 100);
        addedButtons.add(drawBridgeButton);
        pane.getChildren().add(drawBridgeButton);
        EventHandler<MouseEvent> event3 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                DrawBridge drawBridge = new DrawBridge(Manage.getCurrentEmpire());
                drawBridge.drawBridge(0 , 0);
                building = drawBridge;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(drawBridgeImage);
                    newbutton.setBuilding(drawBridge);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        drawBridgeButton.setOnMouseReleased(event3);

        Button cagedDogsButton = new Button();
        ImageView cagedDogsImage = new ImageView(buildingImages.getCagedWarDogs());
        cagedDogsButton.setBackground(null);
        cagedDogsImage.setFitHeight(150);
        cagedDogsImage.setFitWidth(150);
        cagedDogsButton.setGraphic(cagedDogsImage);
        cagedDogsButton.setLayoutX(660);
        cagedDogsButton.setLayoutY(700);
        addedButtons.add(cagedDogsButton);
        cagedDogsButton.setMinSize(100, 100);
        pane.getChildren().add(cagedDogsButton);
        EventHandler<MouseEvent> event4 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                CagedWarDogs cagedWarDogs = new CagedWarDogs(Manage.getCurrentEmpire());
                cagedWarDogs.cagedWarDogs();
                building = cagedWarDogs;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(cagedDogsImage);
                    newbutton.setBuilding(cagedWarDogs);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        cagedDogsButton.setOnMouseReleased(event4);

        Button pitchDitchButton = new Button();
        ImageView pitchDitchImage = new ImageView(buildingImages.getPitchDitch());
        pitchDitchButton.setBackground(null);
        pitchDitchImage.setFitHeight(50);
        pitchDitchImage.setFitWidth(50);
        pitchDitchButton.setGraphic(pitchDitchImage);
        pitchDitchButton.setLayoutX(800);
        pitchDitchButton.setLayoutY(740);
        pitchDitchButton.setMinSize(50, 50);
        addedButtons.add(pitchDitchButton);
        pane.getChildren().add(pitchDitchButton);
        EventHandler<MouseEvent> event5 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                PitchDitch pitchDitch = new PitchDitch(Manage.getCurrentEmpire());
                pitchDitch.pitchDitch();
                building = pitchDitch;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(pitchDitchImage);
                    newbutton.setBuilding(pitchDitch);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        pitchDitchButton.setOnMouseReleased(event5);

        Button killingPitButton = new Button();
        ImageView killingPitImage = new ImageView(buildingImages.getKillingPit());
        killingPitButton.setBackground(null);
        killingPitImage.setFitHeight(30);
        killingPitImage.setFitWidth(30);
        killingPitButton.setGraphic(killingPitImage);
        killingPitButton.setLayoutX(920);
        killingPitButton.setLayoutY(750);
        killingPitButton.setMinSize(30, 30);
        addedButtons.add(killingPitButton);
        pane.getChildren().add(killingPitButton);
        EventHandler<MouseEvent> event6 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                KillingPit killingPit = new KillingPit(Manage.getCurrentEmpire());
                killingPit.killingPit();
                building = killingPit;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(killingPitImage);
                    newbutton.setBuilding(killingPit);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        killingPitButton.setOnMouseReleased(event6);

    }

    public void createTowerButtons(Pane pane, BuildingImages buildingImages) {
        Button returnIconButton = new Button();
        ImageView returnIconImage = new ImageView(buildingImages.getReturnIcon());
        returnIconButton.setBackground(null);
        returnIconImage.setFitHeight(40);
        returnIconImage.setFitWidth(40);
        returnIconButton.setGraphic(returnIconImage);
        returnIconButton.setLayoutX(120);
        returnIconButton.setLayoutY(740);
        returnIconButton.setMinSize(40, 40);
        addedButtons.add(returnIconButton);
        pane.getChildren().add(returnIconButton);
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                clearPane(pane);
                createCastleButtons(pane , buildingImages);
            }
        };
        returnIconButton.setOnMouseClicked(event);

        Button lookoutTowerButton = new Button();
        ImageView lookoutTowerImage = new ImageView(buildingImages.getLookoutTower());
        lookoutTowerButton.setBackground(null);
        lookoutTowerImage.setFitHeight(140);
        lookoutTowerImage.setFitWidth(50);
        lookoutTowerButton.setGraphic(lookoutTowerImage);
        lookoutTowerButton.setLayoutX(240);
        lookoutTowerButton.setLayoutY(680);
        lookoutTowerButton.setMinSize(100, 100);
        addedButtons.add(lookoutTowerButton);
        pane.getChildren().add(lookoutTowerButton);
        EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Tower tower = new Tower(Manage.getCurrentEmpire());
                tower.lookoutTower();
                building = tower;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(lookoutTowerImage);
                    newbutton.setBuilding(tower);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        lookoutTowerButton.setOnMouseReleased(event1);

        Button premierTowerButton = new Button();
        ImageView premierTowerImage = new ImageView(buildingImages.getPremierTurret());
        premierTowerButton.setBackground(null);
        premierTowerImage.setFitHeight(100);
        premierTowerImage.setFitWidth(100);
        premierTowerButton.setGraphic(premierTowerImage);
        premierTowerButton.setLayoutX(380);
        premierTowerButton.setLayoutY(710);
        premierTowerButton.setMinSize(100, 100);
        addedButtons.add(premierTowerButton);
        pane.getChildren().add(premierTowerButton);
        EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Tower tower = new Tower(Manage.getCurrentEmpire());
                tower.perimeterTower();
                building = tower;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(premierTowerImage);
                    newbutton.setBuilding(tower);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        premierTowerButton.setOnMouseReleased(event2);

        Button defenceTowerButton = new Button();
        ImageView defenceTowerImage = new ImageView(buildingImages.getDefenceTurret());
        defenceTowerButton.setBackground(null);
        defenceTowerImage.setFitHeight(100);
        defenceTowerImage.setFitWidth(100);
        defenceTowerButton.setGraphic(defenceTowerImage);
        defenceTowerButton.setLayoutX(520);
        defenceTowerButton.setLayoutY(710);
        defenceTowerButton.setMinSize(100, 100);
        addedButtons.add(defenceTowerButton);
        pane.getChildren().add(defenceTowerButton);
        EventHandler<MouseEvent> event3 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Tower tower = new Tower(Manage.getCurrentEmpire());
                tower.defendTower();
                building = tower;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(defenceTowerImage);
                    newbutton.setBuilding(tower);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        defenceTowerButton.setOnMouseReleased(event3);

        Button squareTowerButton = new Button();
        ImageView squareTowerImage = new ImageView(buildingImages.getSquareTower());
        squareTowerButton.setBackground(null);
        squareTowerImage.setFitHeight(100);
        squareTowerImage.setFitWidth(100);
        squareTowerButton.setGraphic(squareTowerImage);
        squareTowerButton.setLayoutX(660);
        squareTowerButton.setLayoutY(710);
        squareTowerButton.setMinSize(100, 100);
        addedButtons.add(squareTowerButton);
        pane.getChildren().add(squareTowerButton);
        EventHandler<MouseEvent> event4 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Tower tower = new Tower(Manage.getCurrentEmpire());
                tower.squareTower();
                building = tower;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(squareTowerImage);
                    newbutton.setBuilding(tower);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        squareTowerButton.setOnMouseReleased(event4);

        Button roundTowerButton = new Button();
        ImageView roundTowerImage = new ImageView(buildingImages.getRoundTower());
        roundTowerButton.setBackground(null);
        roundTowerImage.setFitHeight(100);
        roundTowerImage.setFitWidth(100);
        roundTowerButton.setGraphic(roundTowerImage);
        roundTowerButton.setLayoutX(800);
        roundTowerButton.setLayoutY(710);
        roundTowerButton.setMinSize(100, 100);
        addedButtons.add(roundTowerButton);
        pane.getChildren().add(roundTowerButton);
        EventHandler<MouseEvent> event5 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Tower tower = new Tower(Manage.getCurrentEmpire());
                tower.roundTower();
                building = tower;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(roundTowerImage);
                    newbutton.setBuilding(tower);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        roundTowerButton.setOnMouseReleased(event5);
    }

    public void createMilitaryBuildingsButtons(Pane pane, BuildingImages buildingImages) {
        Button returnIconButton = new Button();
        ImageView returnIconImage = new ImageView(buildingImages.getReturnIcon());
        returnIconButton.setBackground(null);
        returnIconImage.setFitHeight(40);
        returnIconImage.setFitWidth(40);
        returnIconButton.setGraphic(returnIconImage);
        returnIconButton.setLayoutX(120);
        returnIconButton.setLayoutY(740);
        returnIconButton.setMinSize(40, 40);
        addedButtons.add(returnIconButton);
        pane.getChildren().add(returnIconButton);
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                clearPane(pane);
                createCastleButtons(pane , buildingImages);
            }
        };
        returnIconButton.setOnMouseClicked(event);

        Button engineersGuildButton = new Button();
        ImageView engineersGuildImage = new ImageView(buildingImages.getEngineerGuild());
        engineersGuildButton.setBackground(null);
        engineersGuildImage.setFitHeight(100);
        engineersGuildImage.setFitWidth(100);
        engineersGuildButton.setGraphic(engineersGuildImage);
        engineersGuildButton.setLayoutX(220);
        engineersGuildButton.setLayoutY(710);
        engineersGuildButton.setMinSize(100, 100);
        addedButtons.add(engineersGuildButton);
        pane.getChildren().add(engineersGuildButton);
        EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                PrepareLaboursAndFighters engineer = new PrepareLaboursAndFighters(Manage.getCurrentEmpire());
                engineer.engineerGuild();
                building = engineer;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(engineersGuildImage);
                    newbutton.setBuilding(engineer);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        engineersGuildButton.setOnMouseReleased(event1);

        Button mangonelButton = new Button();
        ImageView mangonelImage = new ImageView(buildingImages.getMangonel());
        mangonelButton.setBackground(null);
        mangonelImage.setFitHeight(200);
        mangonelImage.setFitWidth(200);
        mangonelButton.setGraphic(mangonelImage);
        mangonelButton.setLayoutX(360);
        mangonelButton.setLayoutY(710);
        mangonelButton.setMinSize(100, 100);
        mangonelButton.setMaxSize(100, 100);
        addedButtons.add(mangonelButton);
        pane.getChildren().add(mangonelButton);
//        EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                PointerInfo a = MouseInfo.getPointerInfo();
//                Point b = a.getLocation();
//                int x = (int) ((int) b.getX() / 51);
//                int y = (int) b.getY() / 54;
//                NewButton newbutton = allButtons[y][x].get(0);
//                ArchersAndThrowers archersAndThrowers = new ArchersAndThrowers(Manage.getCurrentEmpire());
//                archersAndThrowers.trebuchet(0 , 0);
//                troop = archersAndThrowers;
//                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
//                if (output.equals("building created successfully")) {
//                    pane.getChildren().remove(allButtons[y][x].get(0));
//                    newbutton.setImageView(stoneWallImage);
//                    newbutton.setBuilding(archersAndThrowers);
//                    pane.getChildren().add(newbutton);
//                }
//                else {
//                    showError(output);
//                }
//            }
//        };
//        mangonelButton.setOnMouseClicked(event2);

        Button ballistaButton = new Button();
        ImageView ballistaImage = new ImageView(buildingImages.getBallista());
        ballistaButton.setBackground(null);
        ballistaImage.setFitHeight(130);
        ballistaImage.setFitWidth(130);
        ballistaButton.setGraphic(ballistaImage);
        ballistaButton.setLayoutX(490);
        ballistaButton.setLayoutY(720);
        ballistaButton.setMinSize(100, 100);
        ballistaButton.setMaxSize(100, 100);
        addedButtons.add(ballistaButton);
        pane.getChildren().add(ballistaButton);
//        EventHandler<MouseEvent> event3 = new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                PointerInfo a = MouseInfo.getPointerInfo();
//                Point b = a.getLocation();
//                int x = (int) ((int) b.getX() / 51);
//                int y = (int) b.getY() / 54;
//                NewButton newbutton = allButtons[y][x].get(0);
//                ArchersAndThrowers archersAndThrowers = new ArchersAndThrowers(Manage.getCurrentEmpire());
//                archersAndThrowers.fireBallista(0 , 0);
//                troop = archersAndThrowers;
//                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
//                if (output.equals("building created successfully")) {
//                    pane.getChildren().remove(allButtons[y][x].get(0));
//                    newbutton.setImageView(stoneWallImage);
//                    newbutton.setBuilding(bigWall);
//                    pane.getChildren().add(newbutton);
//                }
//                else {
//                    showError(output);
//                }
//            }
//        };
//        ballistaButton.setOnMouseClicked(event3);

        Button stableButton = new Button();
        ImageView stableImage = new ImageView(buildingImages.getStable());
        stableButton.setBackground(null);
        stableImage.setFitHeight(100);
        stableImage.setFitWidth(100);
        stableButton.setGraphic(stableImage);
        stableButton.setLayoutX(630);
        stableButton.setLayoutY(710);
        stableButton.setMinSize(100, 100);
        addedButtons.add(stableButton);
        pane.getChildren().add(stableButton);
        EventHandler<MouseEvent> event4 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Stable stable = new Stable(Manage.getCurrentEmpire());
                stable.stable();
                building= stable;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(stableImage);
                    newbutton.setBuilding(stable);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        stableButton.setOnMouseReleased(event4);

        Button tunnelersGuildButton = new Button();
        ImageView tunnelersGuildImage = new ImageView(buildingImages.getTunnelersGuild());
        tunnelersGuildButton.setBackground(null);
        tunnelersGuildImage.setFitHeight(100);
        tunnelersGuildImage.setFitWidth(100);
        tunnelersGuildButton.setGraphic(tunnelersGuildImage);
        tunnelersGuildButton.setLayoutX(770);
        tunnelersGuildButton.setLayoutY(710);
        tunnelersGuildButton.setMinSize(100, 100);
        addedButtons.add(tunnelersGuildButton);
        pane.getChildren().add(tunnelersGuildButton);
        EventHandler<MouseEvent> event5 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                PrepareLaboursAndFighters prepareLaboursAndFighters = new PrepareLaboursAndFighters(Manage.getCurrentEmpire());
                prepareLaboursAndFighters.tunnelerGuild();
                building = prepareLaboursAndFighters;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(tunnelersGuildImage);
                    newbutton.setBuilding(prepareLaboursAndFighters);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        tunnelersGuildButton.setOnMouseReleased(event5);

        Button oilSmelterButton = new Button();
        ImageView oilSmelterImage = new ImageView(buildingImages.getOilSmelter());
        oilSmelterButton.setBackground(null);
        oilSmelterImage.setFitHeight(100);
        oilSmelterImage.setFitWidth(100);
        oilSmelterButton.setGraphic(oilSmelterImage);
        oilSmelterButton.setLayoutX(910);
        oilSmelterButton.setLayoutY(710);
        oilSmelterButton.setMinSize(100, 100);
        addedButtons.add(oilSmelterButton);
        pane.getChildren().add(oilSmelterButton);
        EventHandler<MouseEvent> event6 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                OilSmelter oilSmelter = new OilSmelter(Manage.getCurrentEmpire());
                oilSmelter.oilSmelter();
                building = oilSmelter;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(oilSmelterImage);
                    newbutton.setBuilding(oilSmelter);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        oilSmelterButton.setOnMouseReleased(event6);
    }

    public void createIndustryButtons(Pane pane, BuildingImages buildingImages) {
        Button stockpileButton = new Button();
        ImageView stockpileImage = new ImageView(buildingImages.getStockpile());
        stockpileButton.setBackground(null);
        stockpileImage.setFitHeight(100);
        stockpileImage.setFitWidth(100);
        stockpileButton.setGraphic(stockpileImage);
        stockpileButton.setLayoutX(120);
        stockpileButton.setLayoutY(710);
        stockpileButton.setMinSize(100, 100);
        addedButtons.add(stockpileButton);
        pane.getChildren().add(stockpileButton);
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Stockpile stockpile = new Stockpile(Manage.getCurrentEmpire());
                stockpile.resourcesStockpile();
                building = stockpile;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(stockpileImage);
                    newbutton.setBuilding(stockpile);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        stockpileButton.setOnMouseReleased(event);

        Button woodCutterButton = new Button();
        ImageView woodCutterImage = new ImageView(buildingImages.getWoodCutter());
        woodCutterButton.setBackground(null);
        woodCutterImage.setFitHeight(150);
        woodCutterImage.setFitWidth(150);
        woodCutterButton.setGraphic(woodCutterImage);
        woodCutterButton.setLayoutX(240);
        woodCutterButton.setLayoutY(710);
        woodCutterButton.setMinSize(100, 100);
        woodCutterButton.setMaxSize(100, 100);
        addedButtons.add(woodCutterButton);
        pane.getChildren().add(woodCutterButton);
        EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Industry industry = new Industry(Manage.getCurrentEmpire());
                industry.woodCutter();
                building = industry;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(woodCutterImage);
                    newbutton.setBuilding(industry);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        woodCutterButton.setOnMouseReleased(event1);

        Button quarryButton = new Button();
        ImageView quarryImage = new ImageView(buildingImages.getQuarry());
        quarryButton.setBackground(null);
        quarryImage.setFitHeight(100);
        quarryImage.setFitWidth(100);
        quarryButton.setGraphic(quarryImage);
        quarryButton.setLayoutX(360);
        quarryButton.setLayoutY(710);
        quarryButton.setMinSize(100, 100);
        addedButtons.add(quarryButton);
        pane.getChildren().add(quarryButton);
        EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Industry industry = new Industry(Manage.getCurrentEmpire());
                industry.quarry();
                building = industry;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(quarryImage);
                    newbutton.setBuilding(industry);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        quarryButton.setOnMouseReleased(event2);

        Button oxTetherButton = new Button();
        ImageView oxTetherImage = new ImageView(buildingImages.getOxTheater());
        oxTetherButton.setBackground(null);
        oxTetherImage.setFitHeight(100);
        oxTetherImage.setFitWidth(100);
        oxTetherButton.setGraphic(oxTetherImage);
        oxTetherButton.setLayoutX(480);
        oxTetherButton.setLayoutY(710);
        oxTetherButton.setMinSize(100, 100);
        addedButtons.add(oxTetherButton);
        pane.getChildren().add(oxTetherButton);
        EventHandler<MouseEvent> event3 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Industry industry = new Industry(Manage.getCurrentEmpire());
                industry.oxTether();
                building = industry;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(oxTetherImage);
                    newbutton.setBuilding(industry);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        oxTetherButton.setOnMouseReleased(event3);

        Button ironMineButton = new Button();
        ImageView ironMineImage = new ImageView(buildingImages.getIronMine());
        ironMineButton.setBackground(null);
        ironMineImage.setFitHeight(100);
        ironMineImage.setFitWidth(100);
        ironMineButton.setGraphic(ironMineImage);
        ironMineButton.setLayoutX(600);
        ironMineButton.setLayoutY(710);
        ironMineButton.setMinSize(100, 100);
        addedButtons.add(ironMineButton);
        pane.getChildren().add(ironMineButton);
        EventHandler<MouseEvent> event4 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Industry industry = new Industry(Manage.getCurrentEmpire());
                industry.ironDig();
                building = industry;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(ironMineImage);
                    newbutton.setBuilding(industry);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        ironMineButton.setOnMouseReleased(event4);

        Button pitchRigButton = new Button();
        ImageView pitchRigImage = new ImageView(buildingImages.getPitchRig());
        pitchRigButton.setBackground(null);
        pitchRigImage.setFitHeight(150);
        pitchRigImage.setFitWidth(150);
        pitchRigButton.setGraphic(pitchRigImage);
        pitchRigButton.setLayoutX(720);
        pitchRigButton.setLayoutY(710);
        pitchRigButton.setMinSize(100, 100);
        pitchRigButton.setMaxSize(100, 100);
        addedButtons.add(pitchRigButton);
        pane.getChildren().add(pitchRigButton);
        EventHandler<MouseEvent> event5 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Industry industry = new Industry(Manage.getCurrentEmpire());
                industry.pitchRig();
                building = industry;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(pitchRigImage);
                    newbutton.setBuilding(industry);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        pitchRigButton.setOnMouseReleased(event5);
        //TODO : fix the market
        Button marketButton = new Button();
        ImageView marketImage = new ImageView(buildingImages.getMarket());
        marketButton.setBackground(null);
        marketImage.setFitHeight(100);
        marketImage.setFitWidth(100);
        marketButton.setGraphic(marketImage);
        marketButton.setLayoutX(840);
        marketButton.setLayoutY(710);
        marketButton.setMinSize(100, 100);
        addedButtons.add(marketButton);
        pane.getChildren().add(marketButton);
        EventHandler<MouseEvent> event6 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Shop shop = new Shop(Manage.getCurrentEmpire());
                shop.shop();
                building = shop;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(marketImage);
                    newbutton.setBuilding(shop);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        marketButton.setOnMouseReleased(event6);
    }

    public void createFarmButtons(Pane pane, BuildingImages buildingImages) {
        Button dairyFarmButton = new Button();
        ImageView dairyFarmImage = new ImageView(buildingImages.getDairyFarm());
        dairyFarmButton.setBackground(null);
        dairyFarmImage.setFitHeight(100);
        dairyFarmImage.setFitWidth(100);
        dairyFarmButton.setGraphic(dairyFarmImage);
        dairyFarmButton.setLayoutX(120);
        dairyFarmButton.setLayoutY(710);
        dairyFarmButton.setMinSize(100, 100);
        addedButtons.add(dairyFarmButton);
        pane.getChildren().add(dairyFarmButton);
        ImageView dairy = new ImageView(buildingImages.getDairy());
        dairy.setFitWidth(50);
        dairy.setFitHeight(50);
        dairy.setLayoutX(130);
        dairy.setLayoutY(780);
        pane.getChildren().add(dairy);
        addedImages.add(dairy);
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Goods goods = new Goods(Manage.getCurrentEmpire());
                goods.dairyProduct();
                building = goods;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(dairyFarmImage);
                    newbutton.setBuilding(goods);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        dairyFarmButton.setOnMouseReleased(event);

        Button appleFarmButton = new Button();
        ImageView appleFarmImage = new ImageView(buildingImages.getAppleFarm());
        appleFarmButton.setBackground(null);
        appleFarmImage.setFitHeight(100);
        appleFarmImage.setFitWidth(100);
        appleFarmButton.setGraphic(appleFarmImage);
        appleFarmButton.setLayoutX(280);
        appleFarmButton.setLayoutY(710);
        appleFarmButton.setMinSize(100, 100);
        addedButtons.add(appleFarmButton);
        pane.getChildren().add(appleFarmButton);
        ImageView apple = new ImageView(buildingImages.getApple());
        apple.setFitWidth(50);
        apple.setFitHeight(50);
        apple.setLayoutX(290);
        apple.setLayoutY(780);
        pane.getChildren().add(apple);
        addedImages.add(apple);
        EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Goods goods = new Goods(Manage.getCurrentEmpire());
                goods.appleFarm();
                building = goods;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(appleFarmImage);
                    newbutton.setBuilding(goods);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        appleFarmButton.setOnMouseReleased(event1);

        Button wheatFarmButton = new Button();
        ImageView wheatFarmImage = new ImageView(buildingImages.getWheatFarm());
        wheatFarmButton.setBackground(null);
        wheatFarmImage.setFitHeight(100);
        wheatFarmImage.setFitWidth(100);
        wheatFarmButton.setGraphic(wheatFarmImage);
        wheatFarmButton.setLayoutX(440);
        wheatFarmButton.setLayoutY(710);
        wheatFarmButton.setMinSize(100, 100);
        addedButtons.add(wheatFarmButton);
        pane.getChildren().add(wheatFarmButton);
        ImageView wheat = new ImageView(buildingImages.getWheat());
        wheat.setFitWidth(50);
        wheat.setFitHeight(50);
        wheat.setLayoutX(450);
        wheat.setLayoutY(780);
        addedImages.add(wheat);
        pane.getChildren().add(wheat);
        EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Goods goods = new Goods(Manage.getCurrentEmpire());
                goods.wheatFarm();
                building = goods;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(wheatFarmImage);
                    newbutton.setBuilding(goods);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        wheatFarmButton.setOnMouseReleased(event2);

        Button hopsFarmButton = new Button();
        ImageView hopsFarmImage = new ImageView(buildingImages.getHopsFarm());
        hopsFarmButton.setBackground(null);
        hopsFarmImage.setFitHeight(100);
        hopsFarmImage.setFitWidth(100);
        hopsFarmButton.setGraphic(hopsFarmImage);
        hopsFarmButton.setLayoutX(600);
        hopsFarmButton.setLayoutY(710);
        hopsFarmButton.setMinSize(100, 100);
        addedButtons.add(hopsFarmButton);
        pane.getChildren().add(hopsFarmButton);
        ImageView hops = new ImageView(buildingImages.getHops());
        hops.setFitWidth(50);
        hops.setFitHeight(50);
        hops.setLayoutX(610);
        hops.setLayoutY(780);
        addedImages.add(hops);
        pane.getChildren().add(hops);
        EventHandler<MouseEvent> event3 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Goods goods = new Goods(Manage.getCurrentEmpire());
                goods.oatFarm();
                building = goods;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(hopsFarmImage);
                    newbutton.setBuilding(goods);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        hopsFarmButton.setOnMouseReleased(event3);
    }

    public void createTownButtons(Pane pane, BuildingImages buildingImages) {
        Button hovelButton = new Button();
        ImageView hovelImage = new ImageView(buildingImages.getHovel());
        hovelButton.setBackground(null);
        hovelImage.setFitHeight(100);
        hovelImage.setFitWidth(100);
        hovelButton.setGraphic(hovelImage);
        hovelButton.setLayoutX(120);
        hovelButton.setLayoutY(710);
        hovelButton.setMinSize(100, 100);
        addedButtons.add(hovelButton);
        pane.getChildren().add(hovelButton);
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                House house = new House(Manage.getCurrentEmpire());
                house.house();
                building = house;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(hovelImage);
                    newbutton.setBuilding(house);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        hovelButton.setOnMouseReleased(event);

        Button smallChurchButton = new Button();
        ImageView smallChurchImage = new ImageView(buildingImages.getSmallChurch());
        smallChurchButton.setBackground(null);
        smallChurchImage.setFitHeight(100);
        smallChurchImage.setFitWidth(100);
        smallChurchButton.setGraphic(smallChurchImage);
        smallChurchButton.setLayoutX(280);
        smallChurchButton.setLayoutY(710);
        smallChurchButton.setMinSize(100, 100);
        addedButtons.add(smallChurchButton);
        pane.getChildren().add(smallChurchButton);
        EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Church church = new Church(Manage.getCurrentEmpire());
                church.smallChurch();
                building = church;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(smallChurchImage);
                    newbutton.setBuilding(church);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        smallChurchButton.setOnMouseReleased(event1);

        Button bigChurchButton = new Button();
        ImageView bigChurchImage = new ImageView(buildingImages.getBigChurch());
        bigChurchButton.setBackground(null);
        bigChurchImage.setFitHeight(100);
        bigChurchImage.setFitWidth(100);
        bigChurchButton.setGraphic(bigChurchImage);
        bigChurchButton.setLayoutX(440);
        bigChurchButton.setLayoutY(710);
        bigChurchButton.setMinSize(100, 100);
        addedButtons.add(bigChurchButton);
        pane.getChildren().add(bigChurchButton);
        EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Church church = new Church(Manage.getCurrentEmpire());
                church.bigChurch();
                building = church;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(bigChurchImage);
                    newbutton.setBuilding(church);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        bigChurchButton.setOnMouseReleased(event2);

        Button apothecaryButton = new Button();
        ImageView apothecaryImage = new ImageView(buildingImages.getApothecary());
        apothecaryButton.setBackground(null);
        apothecaryImage.setFitHeight(100);
        apothecaryImage.setFitWidth(100);
        apothecaryButton.setGraphic(apothecaryImage);
        apothecaryButton.setLayoutX(600);
        apothecaryButton.setLayoutY(710);
        apothecaryButton.setMinSize(100, 100);
        addedButtons.add(apothecaryButton);
        pane.getChildren().add(apothecaryButton);
        //TODO : add this to the source code
//        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                Goods goods = new Goods(Manage.getCurrentEmpire());
//                goods.dairyProduct();
//                building = goods;
//            }
//        };

        Button tortureBuildingButton = new Button();
        ImageView tortureBuildingImage = new ImageView(buildingImages.getTortureBuilding());
        tortureBuildingButton.setBackground(null);
        tortureBuildingImage.setFitHeight(100);
        tortureBuildingImage.setFitWidth(100);
        tortureBuildingButton.setGraphic(tortureBuildingImage);
        tortureBuildingButton.setLayoutX(760);
        tortureBuildingButton.setLayoutY(710);
        tortureBuildingButton.setMinSize(100, 100);
        addedButtons.add(tortureBuildingButton);
        pane.getChildren().add(tortureBuildingButton);
        EventHandler<MouseEvent> event4 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                FearControl fearControl = new FearControl(Manage.getCurrentEmpire());
                fearControl.tortureChamber();
                building = fearControl;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(tortureBuildingImage);
                    newbutton.setBuilding(fearControl);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        tortureBuildingButton.setOnMouseReleased(event4);

        Button gardenButton = new Button();
        ImageView gardenImage = new ImageView(buildingImages.getGarden());
        gardenButton.setBackground(null);
        gardenImage.setFitHeight(100);
        gardenImage.setFitWidth(100);
        gardenButton.setGraphic(gardenImage);
        gardenButton.setLayoutX(920);
        gardenButton.setLayoutY(710);
        gardenButton.setMinSize(100, 100);
        addedButtons.add(gardenButton);
        pane.getChildren().add(gardenButton);
        EventHandler<MouseEvent> event5 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                FearControl fearControl = new FearControl(Manage.getCurrentEmpire());
                fearControl.garden();
                building = fearControl;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(gardenImage);
                    newbutton.setBuilding(fearControl);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        gardenButton.setOnMouseReleased(event5);

    }

    public void createWeaponsButtons(Pane pane, BuildingImages buildingImages) {
        Button fletcherButton = new Button();
        ImageView fletcherImage = new ImageView(buildingImages.getFletcher());
        fletcherButton.setBackground(null);
        fletcherImage.setFitHeight(100);
        fletcherImage.setFitWidth(100);
        fletcherButton.setGraphic(fletcherImage);
        fletcherButton.setLayoutX(120);
        fletcherButton.setLayoutY(710);
        fletcherButton.setMinSize(100, 100);
        addedButtons.add(fletcherButton);
        pane.getChildren().add(fletcherButton);
        ImageView bow = new ImageView(buildingImages.getBow());
        bow.setFitWidth(80);
        bow.setFitHeight(80);
        bow.setLayoutX(150);
        bow.setLayoutY(750);
        addedImages.add(bow);
        pane.getChildren().add(bow);
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Weapon weapon = new Weapon(Manage.getCurrentEmpire());
                weapon.fletcher();
                building = weapon;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(fletcherImage);
                    newbutton.setBuilding(weapon);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        fletcherButton.setOnMouseReleased(event);

        Button poleTurnerButton = new Button();
        ImageView poleTurnerImage = new ImageView(buildingImages.getPoleTurner());
        poleTurnerButton.setBackground(null);
        poleTurnerImage.setFitHeight(100);
        poleTurnerImage.setFitWidth(100);
        poleTurnerButton.setGraphic(poleTurnerImage);
        poleTurnerButton.setLayoutX(260);
        poleTurnerButton.setLayoutY(710);
        poleTurnerButton.setMinSize(100, 100);
        addedButtons.add(poleTurnerButton);
        pane.getChildren().add(poleTurnerButton);
        ImageView pike = new ImageView(buildingImages.getPike());
        pike.setFitWidth(80);
        pike.setFitHeight(80);
        pike.setLayoutX(290);
        pike.setLayoutY(750);
        addedImages.add(pike);
        pane.getChildren().add(pike);
        EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Weapon weapon = new Weapon(Manage.getCurrentEmpire());
                weapon.poleTurner();
                building = weapon;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(poleTurnerImage);
                    newbutton.setBuilding(weapon);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        poleTurnerButton.setOnMouseReleased(event1);

        Button blacksmithButton = new Button();
        ImageView blacksmithImage = new ImageView(buildingImages.getBlacksmith());
        blacksmithButton.setBackground(null);
        blacksmithImage.setFitHeight(100);
        blacksmithImage.setFitWidth(100);
        blacksmithButton.setGraphic(blacksmithImage);
        blacksmithButton.setLayoutX(400);
        blacksmithButton.setLayoutY(710);
        blacksmithButton.setMinSize(100, 100);
        addedButtons.add(blacksmithButton);
        pane.getChildren().add(blacksmithButton);
        ImageView sword = new ImageView(buildingImages.getSword());
        sword.setFitWidth(50);
        sword.setFitHeight(50);
        sword.setLayoutX(450);
        sword.setLayoutY(760);
        addedImages.add(sword);
        pane.getChildren().add(sword);
        EventHandler<MouseEvent> event2 = new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) b.getX() / 51;
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Weapon weapon = new Weapon(Manage.getCurrentEmpire());
                weapon.blacksmith();
                building = weapon;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(blacksmithImage);
                    newbutton.setBuilding(weapon);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        blacksmithButton.setOnMouseReleased(event2);

        Button tannersButton = new Button();
        ImageView tannersImage = new ImageView(buildingImages.getTanner());
        tannersButton.setBackground(null);
        tannersImage.setFitHeight(100);
        tannersImage.setFitWidth(100);
        tannersButton.setGraphic(tannersImage);
        tannersButton.setLayoutX(540);
        tannersButton.setLayoutY(710);
        tannersButton.setMinSize(100, 100);
        addedButtons.add(tannersButton);
        pane.getChildren().add(tannersButton);
        ImageView latherArmor = new ImageView(buildingImages.getLeatherArmor());
        latherArmor.setFitWidth(50);
        latherArmor.setFitHeight(50);
        latherArmor.setLayoutX(590);
        latherArmor.setLayoutY(760);
        addedImages.add(latherArmor);
        pane.getChildren().add(latherArmor);

        Button armourerButton = new Button();
        ImageView armourerImage = new ImageView(buildingImages.getArmourer());
        armourerButton.setBackground(null);
        armourerImage.setFitHeight(100);
        armourerImage.setFitWidth(100);
        armourerButton.setGraphic(armourerImage);
        armourerButton.setLayoutX(680);
        armourerButton.setLayoutY(710);
        armourerButton.setMinSize(100, 100);
        addedButtons.add(armourerButton);
        pane.getChildren().add(armourerButton);
        ImageView metalArmour = new ImageView(buildingImages.getMetalArmor());
        metalArmour.setFitWidth(50);
        metalArmour.setFitHeight(50);
        metalArmour.setLayoutX(690);
        metalArmour.setLayoutY(770);
        addedImages.add(metalArmour);
        pane.getChildren().add(metalArmour);
        EventHandler<MouseEvent> event3 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Weapon weapon = new Weapon(Manage.getCurrentEmpire());
                weapon.armourer();
                building = weapon;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(armourerImage);
                    newbutton.setBuilding(weapon);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        armourerButton.setOnMouseReleased(event3);

        Button siegeTentButton = new Button();
        ImageView siegeTentImage = new ImageView(buildingImages.getSiegeTent());
        siegeTentButton.setBackground(null);
        siegeTentImage.setFitHeight(100);
        siegeTentImage.setFitWidth(100);
        siegeTentButton.setGraphic(siegeTentImage);
        siegeTentButton.setLayoutX(820);
        siegeTentButton.setLayoutY(710);
        siegeTentButton.setMinSize(100, 100);
        addedButtons.add(siegeTentButton);
        pane.getChildren().add(siegeTentButton);
        EventHandler<MouseEvent> event4 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                SiegeTent siegeTent = new SiegeTent(Manage.getCurrentEmpire());
                siegeTent.siegeTent();
                building = siegeTent;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(siegeTentImage);
                    newbutton.setBuilding(siegeTent);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        siegeTentButton.setOnMouseReleased(event4);
    }

    public void createFoodButtons(Pane pane, BuildingImages buildingImages) {
        Button granaryButton = new Button();
        ImageView granaryImage = new ImageView(buildingImages.getGranary());
        granaryButton.setBackground(null);
        granaryImage.setFitHeight(100);
        granaryImage.setFitWidth(100);
        granaryButton.setGraphic(granaryImage);
        granaryButton.setLayoutX(120);
        granaryButton.setLayoutY(710);
        granaryButton.setMinSize(100, 100);
        addedButtons.add(granaryButton);
        pane.getChildren().add(granaryButton);
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Stockpile stockpile = new Stockpile(Manage.getCurrentEmpire());
                stockpile.foodStockpile();
                building = stockpile;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(granaryImage);
                    newbutton.setBuilding(stockpile);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        granaryButton.setOnMouseReleased(event);

        Button bakeryButton = new Button();
        ImageView bakeryImage = new ImageView(buildingImages.getBakery());
        bakeryButton.setBackground(null);
        bakeryImage.setFitHeight(100);
        bakeryImage.setFitWidth(100);
        bakeryButton.setGraphic(bakeryImage);
        bakeryButton.setLayoutX(280);
        bakeryButton.setLayoutY(710);
        bakeryButton.setMinSize(100, 100);
        addedButtons.add(bakeryButton);
        pane.getChildren().add(bakeryButton);
        ImageView bread = new ImageView(buildingImages.getBread());
        bread.setFitWidth(60);
        bread.setFitHeight(60);
        bread.setLayoutX(330);
        bread.setLayoutY(760);
        addedImages.add(bread);
        pane.getChildren().add(bread);
        EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Goods goods = new Goods(Manage.getCurrentEmpire());
                goods.bakery();
                building = goods;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(bakeryImage);
                    newbutton.setBuilding(goods);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        bakeryButton.setOnMouseReleased(event1);

        Button breweryButton = new Button();
        ImageView breweryImage = new ImageView(buildingImages.getBrewery());
        breweryButton.setBackground(null);
        breweryImage.setFitHeight(100);
        breweryImage.setFitWidth(100);
        breweryButton.setGraphic(breweryImage);
        breweryButton.setLayoutX(440);
        breweryButton.setLayoutY(710);
        breweryButton.setMinSize(100, 100);
        addedButtons.add(breweryButton);
        pane.getChildren().add(breweryButton);
        ImageView barrel = new ImageView(buildingImages.getBarrel());
        barrel.setFitWidth(60);
        barrel.setFitHeight(60);
        barrel.setLayoutX(490);
        barrel.setLayoutY(760);
        addedImages.add(barrel);
        pane.getChildren().add(barrel);
        EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Goods goods = new Goods(Manage.getCurrentEmpire());
                goods.bearFactory();
                building = goods;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(breweryImage);
                    newbutton.setBuilding(goods);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        breweryButton.setOnMouseReleased(event2);

        Button millButton = new Button();
        ImageView millImage = new ImageView(buildingImages.getMill());
        millButton.setBackground(null);
        millImage.setFitHeight(100);
        millImage.setFitWidth(100);
        millButton.setGraphic(millImage);
        millButton.setLayoutX(600);
        millButton.setLayoutY(710);
        millButton.setMinSize(100, 100);
        addedButtons.add(millButton);
        pane.getChildren().add(millButton);
        ImageView flour = new ImageView(buildingImages.getFlour());
        flour.setFitWidth(60);
        flour.setFitHeight(60);
        flour.setLayoutX(650);
        flour.setLayoutY(760);
        addedImages.add(flour);
        pane.getChildren().add(flour);
        EventHandler<MouseEvent> event3 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Industry industry = new Industry(Manage.getCurrentEmpire());
                industry.mill();
                building = industry;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(millImage);
                    newbutton.setBuilding(industry);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        millButton.setOnMouseReleased(event3);

        Button innButton = new Button();
        ImageView innImage = new ImageView(buildingImages.getInn());
        innButton.setBackground(null);
        innImage.setFitHeight(100);
        innImage.setFitWidth(100);
        innButton.setGraphic(innImage);
        innButton.setLayoutX(760);
        innButton.setLayoutY(710);
        innButton.setMinSize(100, 100);
        addedButtons.add(innButton);
        pane.getChildren().add(innButton);
        ImageView beer = new ImageView(buildingImages.getBeer());
        beer.setFitWidth(80);
        beer.setFitHeight(80);
        beer.setLayoutX(810);
        beer.setLayoutY(760);
        addedImages.add(beer);
        pane.getChildren().add(beer);
        EventHandler<MouseEvent> event4 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) ((int) b.getX() / 51);
                int y = (int) b.getY() / 54;
                NewButton newbutton = allButtons[y][x].get(0);
                Inn inn = new Inn(Manage.getCurrentEmpire());
                inn.inn();
                building = inn;
                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(innImage);
                    newbutton.setBuilding(inn);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            }
        };
        innButton.setOnMouseReleased(event4);
    }

    public void clearPane(Pane pane){
        pane.getChildren().removeAll(addedButtons);
        pane.getChildren().removeAll(addedImages);
        addedButtons.clear();
        addedImages.clear();
    }

    public ArrayList<NewButton>[][] getAllButtons() {
        return allButtons;
    }

    public void setAllButtons(ArrayList<NewButton>[][] allButtons) {
        this.allButtons = allButtons;
    }

    public void fuckingSuperHardcodeCreateBuilding(Pane pane , String buildingName , BuildingImages buildingImages){
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        int x = (int) ((int) b.getX() / 51);
        int y = (int) b.getY() / 54;
        System.out.println("x " + x + " y " + y);
        NewButton newbutton = allButtons[y][x].get(0);
        String output;
        switch (buildingName)
        {
            case "Armoury":
                ImageView armoryImage = new ImageView(buildingImages.getArmory());
                Armoury armoury = new Armoury(Manage.getCurrentEmpire());
                armoury.armoury();
                building = armoury;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(armoryImage);
                    newbutton.setBuilding(armoury);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case "Barracks":
                ImageView barracksImage = new ImageView(buildingImages.getBarracks());
                PrepareLaboursAndFighters barracks = new PrepareLaboursAndFighters(Manage.getCurrentEmpire());
                barracks.barracks();
                building = barracks;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(barracksImage);
                    newbutton.setBuilding(barracks);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"CagedWarDogs":
                ImageView cagedDogsImage = new ImageView(buildingImages.getCagedWarDogs());
                CagedWarDogs cagedWarDogs = new CagedWarDogs(Manage.getCurrentEmpire());
                cagedWarDogs.cagedWarDogs();
                building = cagedWarDogs;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(cagedDogsImage);
                    newbutton.setBuilding(cagedWarDogs);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            case"SmallChurch":
                ImageView smallChurchImage = new ImageView(buildingImages.getSmallChurch());
                Church church = new Church(Manage.getCurrentEmpire());
                church.smallChurch();
                building = church;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(smallChurchImage);
                    newbutton.setBuilding(church);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"BigChurch":
                ImageView bigChurchImage = new ImageView(buildingImages.getBigChurch());
                Church church2 = new Church(Manage.getCurrentEmpire());
                church2.bigChurch();
                building = church2;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(bigChurchImage);
                    newbutton.setBuilding(church2);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case "DrawBridge":
                ImageView drawBridgeImage = new ImageView(buildingImages.getDrawBridge());
                DrawBridge drawBridge = new DrawBridge(Manage.getCurrentEmpire());
                drawBridge.drawBridge(0 , 0);
                building = drawBridge;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(drawBridgeImage);
                    newbutton.setBuilding(drawBridge);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"AppleFarm":
                ImageView appleFarmImage = new ImageView(buildingImages.getAppleFarm());
                Goods goods2 = new Goods(Manage.getCurrentEmpire());
                goods2.appleFarm();
                building = goods2;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(appleFarmImage);
                    newbutton.setBuilding(goods2);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"DairyProduct":
                ImageView dairyFarmImage = new ImageView(buildingImages.getDairyFarm());
                Goods goods = new Goods(Manage.getCurrentEmpire());
                goods.dairyProduct();
                building = goods;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(dairyFarmImage);
                    newbutton.setBuilding(goods);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"OatFarm":
                ImageView hopsFarmImage = new ImageView(buildingImages.getHopsFarm());
                Goods goods5 = new Goods(Manage.getCurrentEmpire());
                goods5.oatFarm();
                building = goods5;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(hopsFarmImage);
                    newbutton.setBuilding(goods5);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"HuntingPost":
            case"WheatFarm":
                ImageView wheatFarmImage = new ImageView(buildingImages.getWheatFarm());
                Goods goods1 = new Goods(Manage.getCurrentEmpire());
                goods1.wheatFarm();
                building = goods1;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(wheatFarmImage);
                    newbutton.setBuilding(goods1);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"Bakery":
                ImageView bakeryImage = new ImageView(buildingImages.getBakery());
                Goods goods6 = new Goods(Manage.getCurrentEmpire());
                goods6.bakery();
                building = goods6;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(bakeryImage);
                    newbutton.setBuilding(goods6);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"BeerFactory":
                ImageView breweryImage = new ImageView(buildingImages.getBrewery());
                Goods goods7 = new Goods(Manage.getCurrentEmpire());
                goods7.bearFactory();
                building = goods7;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(breweryImage);
                    newbutton.setBuilding(goods7);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"House":
                ImageView hovelImage = new ImageView(buildingImages.getHovel());
                House house = new House(Manage.getCurrentEmpire());
                house.house();
                building = house;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(hovelImage);
                    newbutton.setBuilding(house);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"Mill":
                ImageView millImage = new ImageView(buildingImages.getMill());
                Industry industry6 = new Industry(Manage.getCurrentEmpire());
                industry6.mill();
                building = industry6;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(millImage);
                    newbutton.setBuilding(industry6);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"IronDig":
                ImageView ironMineImage = new ImageView(buildingImages.getIronMine());
                Industry industry3 = new Industry(Manage.getCurrentEmpire());
                industry3.ironDig();
                building = industry3;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(ironMineImage);
                    newbutton.setBuilding(industry3);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"OxTether":
                ImageView oxTetherImage = new ImageView(buildingImages.getOxTheater());
                Industry industry2 = new Industry(Manage.getCurrentEmpire());
                industry2.oxTether();
                building = industry2;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(oxTetherImage);
                    newbutton.setBuilding(industry2);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"OilSmelter":
                ImageView oilSmelterImage = new ImageView(buildingImages.getOilSmelter());
                OilSmelter oilSmelter = new OilSmelter(Manage.getCurrentEmpire());
                oilSmelter.oilSmelter();
                building = oilSmelter;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(oilSmelterImage);
                    newbutton.setBuilding(oilSmelter);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"Quarry":
                ImageView quarryImage = new ImageView(buildingImages.getQuarry());
                Industry industry1 = new Industry(Manage.getCurrentEmpire());
                industry1.quarry();
                building = industry1;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(quarryImage);
                    newbutton.setBuilding(industry1);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"WoodCutter":
                ImageView woodCutterImage = new ImageView(buildingImages.getWoodCutter());
                Industry industry = new Industry(Manage.getCurrentEmpire());
                industry.woodCutter();
                building = industry;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(woodCutterImage);
                    newbutton.setBuilding(industry);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"Inn":
                ImageView innImage = new ImageView(buildingImages.getInn());
                Inn inn = new Inn(Manage.getCurrentEmpire());
                inn.inn();
                building = inn;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(innImage);
                    newbutton.setBuilding(inn);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"KillingPit":
                Button killingPitButton = new Button();
                ImageView killingPitImage = new ImageView(buildingImages.getKillingPit());
                KillingPit killingPit = new KillingPit(Manage.getCurrentEmpire());
                killingPit.killingPit();
                building = killingPit;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(killingPitImage);
                    newbutton.setBuilding(killingPit);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            case "Market":
                ImageView marketImage = new ImageView(buildingImages.getMarket());
                Shop shop = new Shop(Manage.getCurrentEmpire());
                shop.shop();
                building = shop;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(marketImage);
                    newbutton.setBuilding(shop);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"PitchRig":
                ImageView pitchRigImage = new ImageView(buildingImages.getPitchRig());
                Industry industry4 = new Industry(Manage.getCurrentEmpire());
                industry4.pitchRig();
                building = industry4;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(pitchRigImage);
                    newbutton.setBuilding(industry4);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"PitchDitch":
                ImageView pitchDitchImage = new ImageView(buildingImages.getPitchDitch());
                PitchDitch pitchDitch = new PitchDitch(Manage.getCurrentEmpire());
                pitchDitch.pitchDitch();
                building = pitchDitch;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(pitchDitchImage);
                    newbutton.setBuilding(pitchDitch);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            case"Mercenary":
                ImageView mercenaryImage = new ImageView(buildingImages.getMercenary());
                PrepareLaboursAndFighters mercenary = new PrepareLaboursAndFighters(Manage.getCurrentEmpire());
                mercenary.mercenary();
                building = mercenary;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(mercenaryImage);
                    newbutton.setBuilding(mercenary);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"EngineerGuild":
                ImageView engineersGuildImage = new ImageView(buildingImages.getEngineerGuild());
                PrepareLaboursAndFighters engineer = new PrepareLaboursAndFighters(Manage.getCurrentEmpire());
                engineer.engineerGuild();
                building = engineer;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(engineersGuildImage);
                    newbutton.setBuilding(engineer);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"TunnelerGuild":
                ImageView tunnelersGuildImage = new ImageView(buildingImages.getTunnelersGuild());
                PrepareLaboursAndFighters prepareLaboursAndFighters = new PrepareLaboursAndFighters(Manage.getCurrentEmpire());
                prepareLaboursAndFighters.tunnelerGuild();
                building = prepareLaboursAndFighters;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(tunnelersGuildImage);
                    newbutton.setBuilding(prepareLaboursAndFighters);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"Shop":
            case"SiegeTent":
                ImageView siegeTentImage = new ImageView(buildingImages.getSiegeTent());
                SiegeTent siegeTent = new SiegeTent(Manage.getCurrentEmpire());
                siegeTent.siegeTent();
                building = siegeTent;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(siegeTentImage);
                    newbutton.setBuilding(siegeTent);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"Stable":
                ImageView stableImage = new ImageView(buildingImages.getStable());
                Stable stable = new Stable(Manage.getCurrentEmpire());
                stable.stable();
                building= stable;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(stableImage);
                    newbutton.setBuilding(stable);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"ResourcesStockpile":
                ImageView stockpileImage = new ImageView(buildingImages.getStockpile());
                Stockpile resourceStockpile = new Stockpile(Manage.getCurrentEmpire());
                resourceStockpile.resourcesStockpile();
                building = resourceStockpile;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(stockpileImage);
                    newbutton.setBuilding(resourceStockpile);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"FoodStockpile":
                ImageView granaryImage = new ImageView(buildingImages.getGranary());
                Stockpile stockpile2 = new Stockpile(Manage.getCurrentEmpire());
                stockpile2.foodStockpile();
                building = stockpile2;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(granaryImage);
                    newbutton.setBuilding(stockpile2);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
            case"SmallStoneGatehouse":
                ImageView smallGatehouseImage = new ImageView(buildingImages.getSmallStoneGatehouse());
                StoneGateWay stoneGateWay = new StoneGateWay(Manage.getCurrentEmpire());
                stoneGateWay.smallGateWay(Names.NS);
                building = stoneGateWay;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(smallGatehouseImage);
                    newbutton.setBuilding(stoneGateWay);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"BigStoneGatehouse":
                ImageView bigGatehouseImage = new ImageView(buildingImages.getBigStoneGateHouse());
                StoneGateWay stoneGateWayBig = new StoneGateWay(Manage.getCurrentEmpire());
                stoneGateWayBig.bigGateWay(Names.NS);
                building = stoneGateWayBig;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(bigGatehouseImage);
                    newbutton.setBuilding(stoneGateWayBig);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"LookoutTower":
                ImageView lookoutTowerImage = new ImageView(buildingImages.getLookoutTower());
                Tower tower = new Tower(Manage.getCurrentEmpire());
                tower.lookoutTower();
                building = tower;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(lookoutTowerImage);
                    newbutton.setBuilding(tower);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"PerimeterTower":
                ImageView premierTowerImage = new ImageView(buildingImages.getPremierTurret());
                Tower tower2 = new Tower(Manage.getCurrentEmpire());
                tower2.perimeterTower();
                building = tower2;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(premierTowerImage);
                    newbutton.setBuilding(tower2);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"DefendTower":
                ImageView defenceTowerImage = new ImageView(buildingImages.getDefenceTurret());
                Tower tower3 = new Tower(Manage.getCurrentEmpire());
                tower3.defendTower();
                building = tower3;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(defenceTowerImage);
                    newbutton.setBuilding(tower3);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"SquareTower":
                ImageView squareTowerImage = new ImageView(buildingImages.getSquareTower());
                Tower tower4 = new Tower(Manage.getCurrentEmpire());
                tower4.squareTower();
                building = tower4;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(squareTowerImage);
                    newbutton.setBuilding(tower4);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"RoundTower":
                ImageView roundTowerImage = new ImageView(buildingImages.getRoundTower());
                Tower tower5 = new Tower(Manage.getCurrentEmpire());
                tower5.roundTower();
                building = tower5;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(roundTowerImage);
                    newbutton.setBuilding(tower5);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"Tunnel":
            case"BigWall":
                ImageView stoneWallImage = new ImageView(buildingImages.getStoneWall());
                Wall bigWall = new Wall(Manage.getCurrentEmpire());
                bigWall.bigWall();
                building = bigWall;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(stoneWallImage);
                    newbutton.setBuilding(bigWall);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"SmallWall":
                ImageView lowWallImage = new ImageView(buildingImages.getLowWall());
                Wall lowWall = new Wall(Manage.getCurrentEmpire());
                lowWall.smallWall();
                building = lowWall;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(lowWallImage);
                    newbutton.setBuilding(lowWall);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"Stair":
                ImageView wallStairImage = new ImageView(buildingImages.getWallStair());
                Wall stair = new Wall(Manage.getCurrentEmpire());
                stair.stair();
                building = stair;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(wallStairImage);
                    newbutton.setBuilding(stair);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"Castle":
            case"Armourer":
                ImageView armourerImage = new ImageView(buildingImages.getArmourer());
                Weapon weapon4 = new Weapon(Manage.getCurrentEmpire());
                weapon4.armourer();
                building = weapon4;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(armourerImage);
                    newbutton.setBuilding(weapon4);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"Blacksmith":
                ImageView blacksmithImage = new ImageView(buildingImages.getBlacksmith());
                Weapon weapon3 = new Weapon(Manage.getCurrentEmpire());
                weapon3.blacksmith();
                building = weapon3;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(blacksmithImage);
                    newbutton.setBuilding(weapon3);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"Fletcher":
                ImageView fletcherImage = new ImageView(buildingImages.getFletcher());
                Weapon weapon = new Weapon(Manage.getCurrentEmpire());
                weapon.fletcher();
                building = weapon;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(fletcherImage);
                    newbutton.setBuilding(weapon);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"PoleTurner":
                ImageView poleTurnerImage = new ImageView(buildingImages.getPoleTurner());
                Weapon weapon1 = new Weapon(Manage.getCurrentEmpire());
                weapon1.poleTurner();
                building = weapon1;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(poleTurnerImage);
                    newbutton.setBuilding(weapon1);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"Garden":
                ImageView gardenImage = new ImageView(buildingImages.getGarden());
                FearControl fearControl2 = new FearControl(Manage.getCurrentEmpire());
                fearControl2.garden();
                building = fearControl2;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(gardenImage);
                    newbutton.setBuilding(fearControl2);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
            case"TortureChamber":
                ImageView tortureBuildingImage = new ImageView(buildingImages.getTortureBuilding());
                FearControl fearControl = new FearControl(Manage.getCurrentEmpire());
                fearControl.tortureChamber();
                building = fearControl;
                output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
                if (output.equals("building created successfully")) {
                    pane.getChildren().remove(allButtons[y][x].get(0));
                    newbutton.setImageView(tortureBuildingImage);
                    newbutton.setBuilding(fearControl);
                    pane.getChildren().add(newbutton);
                }
                else {
                    showError(output);
                }
                break;
        }
    }
}

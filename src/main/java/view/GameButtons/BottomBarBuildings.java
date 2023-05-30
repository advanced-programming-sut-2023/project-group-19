package view.GameButtons;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import view.ImageAndBackground.BuildingImages;

public class BottomBarBuildings {


    public void createCastleButtons(Pane pane , BuildingImages buildingImages){
        Button wallStairButton = new Button();
        ImageView wallStairImage = new ImageView(buildingImages.getWallStair());
        wallStairButton.setBackground(null);
        wallStairImage.setFitHeight(100);
        wallStairImage.setFitWidth(100);
        wallStairButton.setGraphic(wallStairImage);
        wallStairButton.setLayoutX(110);
        wallStairButton.setLayoutY(700);
        wallStairButton.setMinSize(100, 100);
        pane.getChildren().add(wallStairButton);

        Button lowWallButton = new Button();
        ImageView lowWallImage = new ImageView(buildingImages.getLowWall());
        lowWallButton.setBackground(null);
        lowWallImage.setFitHeight(100);
        lowWallImage.setFitWidth(100);
        lowWallButton.setGraphic(lowWallImage);
        lowWallButton.setLayoutX(210);
        lowWallButton.setLayoutY(715);
        lowWallButton.setMinSize(100, 100);
        pane.getChildren().add(lowWallButton);

        Button stoneWallButton = new Button();
        ImageView stoneWallImage = new ImageView(buildingImages.getStoneWall());
        stoneWallButton.setBackground(null);
        stoneWallImage.setFitHeight(100);
        stoneWallImage.setFitWidth(100);
        stoneWallButton.setGraphic(stoneWallImage);
        stoneWallButton.setLayoutX(340);
        stoneWallButton.setLayoutY(700);
        stoneWallButton.setMinSize(100, 100);
        pane.getChildren().add(stoneWallButton);

        Button crenulatedWallButton = new Button();
        ImageView crenulatedWallImage = new ImageView(buildingImages.getCrenulatedWall());
        crenulatedWallButton.setBackground(null);
        crenulatedWallImage.setFitHeight(100);
        crenulatedWallImage.setFitWidth(100);
        crenulatedWallButton.setGraphic(crenulatedWallImage);
        crenulatedWallButton.setLayoutX(470);
        crenulatedWallButton.setLayoutY(700);
        crenulatedWallButton.setMinSize(100, 100);
        pane.getChildren().add(crenulatedWallButton);

        Button barracksButton = new Button();
        ImageView barracksImage = new ImageView(buildingImages.getBarracks());
        barracksButton.setBackground(null);
        barracksImage.setFitHeight(100);
        barracksImage.setFitWidth(100);
        barracksButton.setGraphic(barracksImage);
        barracksButton.setLayoutX(600);
        barracksButton.setLayoutY(700);
        barracksButton.setMinSize(100, 100);
        pane.getChildren().add(barracksButton);

        Button mercenaryButton = new Button();
        ImageView mercenaryImage = new ImageView(buildingImages.getMercenary());
        mercenaryButton.setBackground(null);
        mercenaryImage.setFitHeight(100);
        mercenaryImage.setFitWidth(100);
        mercenaryButton.setGraphic(mercenaryImage);
        mercenaryButton.setLayoutX(730);
        mercenaryButton.setLayoutY(700);
        mercenaryButton.setMinSize(100, 100);
        pane.getChildren().add(mercenaryButton);

        Button armoryButton = new Button();
        ImageView armoryImage = new ImageView(buildingImages.getArmory());
        armoryButton.setBackground(null);
        armoryImage.setFitHeight(100);
        armoryImage.setFitWidth(100);
        armoryButton.setGraphic(armoryImage);
        armoryButton.setLayoutX(860);
        armoryButton.setLayoutY(700);
        armoryButton.setMinSize(100, 100);
        pane.getChildren().add(armoryButton);

        Button gatehouseIconButton = new Button();
        ImageView gatehouseIconImage = new ImageView(buildingImages.getGatehouseIcon());
        gatehouseIconButton.setBackground(null);
        gatehouseIconImage.setFitHeight(40);
        gatehouseIconImage.setFitWidth(40);
        gatehouseIconButton.setGraphic(gatehouseIconImage);
        gatehouseIconButton.setLayoutX(980);
        gatehouseIconButton.setLayoutY(690);
        gatehouseIconButton.setMinSize(40, 40);
        pane.getChildren().add(gatehouseIconButton);

        Button towerIconButton = new Button();
        ImageView towerIconImage = new ImageView(buildingImages.getTowersIcon());
        towerIconButton.setBackground(null);
        towerIconImage.setFitHeight(40);
        towerIconImage.setFitWidth(40);
        towerIconButton.setGraphic(towerIconImage);
        towerIconButton.setLayoutX(980);
        towerIconButton.setLayoutY(740);
        towerIconButton.setMinSize(40, 40);
        pane.getChildren().add(towerIconButton);

        Button militaryBuildingsIconButton = new Button();
        ImageView militaryBuildingsIconImage = new ImageView(buildingImages.getMilitaryBuildingsIcon());
        militaryBuildingsIconButton.setBackground(null);
        militaryBuildingsIconImage.setFitHeight(40);
        militaryBuildingsIconImage.setFitWidth(40);
        militaryBuildingsIconButton.setGraphic(militaryBuildingsIconImage);
        militaryBuildingsIconButton.setLayoutX(980);
        militaryBuildingsIconButton.setLayoutY(790);
        militaryBuildingsIconButton.setMinSize(40, 40);
        pane.getChildren().add(militaryBuildingsIconButton);


    }
    public void createGatehouseButtons(Pane pane , BuildingImages buildingImages){
        Button returnIconButton = new Button();
        ImageView returnIconImage = new ImageView(buildingImages.getReturnIcon());
        returnIconButton.setBackground(null);
        returnIconImage.setFitHeight(40);
        returnIconImage.setFitWidth(40);
        returnIconButton.setGraphic(returnIconImage);
        returnIconButton.setLayoutX(120);
        returnIconButton.setLayoutY(740);
        returnIconButton.setMinSize(40, 40);
        pane.getChildren().add(returnIconButton);

        Button smallGatehouseButton = new Button();
        ImageView smallGatehouseImage = new ImageView(buildingImages.getSmallStoneGatehouse());
        smallGatehouseButton.setBackground(null);
        smallGatehouseImage.setFitHeight(100);
        smallGatehouseImage.setFitWidth(100);
        smallGatehouseButton.setGraphic(smallGatehouseImage);
        smallGatehouseButton.setLayoutX(220);
        smallGatehouseButton.setLayoutY(700);
        smallGatehouseButton.setMinSize(100, 100);
        pane.getChildren().add(smallGatehouseButton);

        Button bigGatehouseButton = new Button();
        ImageView bigGatehouseImage = new ImageView(buildingImages.getBigStoneGateHouse());
        bigGatehouseButton.setBackground(null);
        bigGatehouseImage.setFitHeight(100);
        bigGatehouseImage.setFitWidth(100);
        bigGatehouseButton.setGraphic(bigGatehouseImage);
        bigGatehouseButton.setLayoutX(360);
        bigGatehouseButton.setLayoutY(700);
        bigGatehouseButton.setMinSize(100, 100);
        pane.getChildren().add(bigGatehouseButton);

        Button drawBridgeButton = new Button();
        ImageView drawBridgeImage = new ImageView(buildingImages.getDrawBridge());
        drawBridgeButton.setBackground(null);
        drawBridgeImage.setFitHeight(100);
        drawBridgeImage.setFitWidth(100);
        drawBridgeButton.setGraphic(drawBridgeImage);
        drawBridgeButton.setLayoutX(500);
        drawBridgeButton.setLayoutY(700);
        drawBridgeButton.setMinSize(100, 100);
        pane.getChildren().add(drawBridgeButton);

        Button cagedDogsButton = new Button();
        ImageView cagedDogsImage = new ImageView(buildingImages.getCagedWarDogs());
        cagedDogsButton.setBackground(null);
        cagedDogsImage.setFitHeight(100);
        cagedDogsImage.setFitWidth(100);
        cagedDogsButton.setGraphic(cagedDogsImage);
        cagedDogsButton.setLayoutX(660);
        cagedDogsButton.setLayoutY(700);
        cagedDogsButton.setMinSize(100, 100);
        pane.getChildren().add(cagedDogsButton);

        Button pitchDitchButton = new Button();
        ImageView pitchDitchImage = new ImageView(buildingImages.getPitchDitch());
        pitchDitchButton.setBackground(null);
        pitchDitchImage.setFitHeight(50);
        pitchDitchImage.setFitWidth(50);
        pitchDitchButton.setGraphic(pitchDitchImage);
        pitchDitchButton.setLayoutX(800);
        pitchDitchButton.setLayoutY(740);
        pitchDitchButton.setMinSize(50, 50);
        pane.getChildren().add(pitchDitchButton);

        Button killingPitButton = new Button();
        ImageView killingPitImage = new ImageView(buildingImages.getKillingPit());
        killingPitButton.setBackground(null);
        killingPitImage.setFitHeight(30);
        killingPitImage.setFitWidth(30);
        killingPitButton.setGraphic(killingPitImage);
        killingPitButton.setLayoutX(920);
        killingPitButton.setLayoutY(750);
        killingPitButton.setMinSize(30, 30);
        pane.getChildren().add(killingPitButton);

    }
    public void createTowerButtons(Pane pane , BuildingImages buildingImages){
        Button returnIconButton = new Button();
        ImageView returnIconImage = new ImageView(buildingImages.getReturnIcon());
        returnIconButton.setBackground(null);
        returnIconImage.setFitHeight(40);
        returnIconImage.setFitWidth(40);
        returnIconButton.setGraphic(returnIconImage);
        returnIconButton.setLayoutX(120);
        returnIconButton.setLayoutY(740);
        returnIconButton.setMinSize(40, 40);
        pane.getChildren().add(returnIconButton);

        Button lookoutTowerButton = new Button();
        ImageView lookoutTowerImage = new ImageView(buildingImages.getLookoutTower());
        lookoutTowerButton.setBackground(null);
        lookoutTowerImage.setFitHeight(140);
        lookoutTowerImage.setFitWidth(50);
        lookoutTowerButton.setGraphic(lookoutTowerImage);
        lookoutTowerButton.setLayoutX(240);
        lookoutTowerButton.setLayoutY(680);
        lookoutTowerButton.setMinSize(100, 100);
        pane.getChildren().add(lookoutTowerButton);

        Button premierTowerButton = new Button();
        ImageView premierTowerImage = new ImageView(buildingImages.getPremierTurret());
        premierTowerButton.setBackground(null);
        premierTowerImage.setFitHeight(100);
        premierTowerImage.setFitWidth(100);
        premierTowerButton.setGraphic(premierTowerImage);
        premierTowerButton.setLayoutX(380);
        premierTowerButton.setLayoutY(710);
        premierTowerButton.setMinSize(100, 100);
        pane.getChildren().add(premierTowerButton);

        Button defenceTowerButton = new Button();
        ImageView defenceTowerImage = new ImageView(buildingImages.getDefenceTurret());
        defenceTowerButton.setBackground(null);
        defenceTowerImage.setFitHeight(100);
        defenceTowerImage.setFitWidth(100);
        defenceTowerButton.setGraphic(defenceTowerImage);
        defenceTowerButton.setLayoutX(520);
        defenceTowerButton.setLayoutY(710);
        defenceTowerButton.setMinSize(100, 100);
        pane.getChildren().add(defenceTowerButton);

        Button squareTowerButton = new Button();
        ImageView squareTowerImage = new ImageView(buildingImages.getSquareTower());
        squareTowerButton.setBackground(null);
        squareTowerImage.setFitHeight(100);
        squareTowerImage.setFitWidth(100);
        squareTowerButton.setGraphic(squareTowerImage);
        squareTowerButton.setLayoutX(660);
        squareTowerButton.setLayoutY(710);
        squareTowerButton.setMinSize(100, 100);
        pane.getChildren().add(squareTowerButton);

        Button roundTowerButton = new Button();
        ImageView roundTowerImage = new ImageView(buildingImages.getRoundTower());
        roundTowerButton.setBackground(null);
        roundTowerImage.setFitHeight(100);
        roundTowerImage.setFitWidth(100);
        roundTowerButton.setGraphic(roundTowerImage);
        roundTowerButton.setLayoutX(800);
        roundTowerButton.setLayoutY(710);
        roundTowerButton.setMinSize(100, 100);
        pane.getChildren().add(roundTowerButton);
    }
    public void createMilitaryBuildingsButtons(Pane pane , BuildingImages buildingImages){
        Button returnIconButton = new Button();
        ImageView returnIconImage = new ImageView(buildingImages.getReturnIcon());
        returnIconButton.setBackground(null);
        returnIconImage.setFitHeight(40);
        returnIconImage.setFitWidth(40);
        returnIconButton.setGraphic(returnIconImage);
        returnIconButton.setLayoutX(120);
        returnIconButton.setLayoutY(740);
        returnIconButton.setMinSize(40, 40);
        pane.getChildren().add(returnIconButton);

        Button engineersGuildButton = new Button();
        ImageView engineersGuildImage = new ImageView(buildingImages.getEngineerGuild());
        engineersGuildButton.setBackground(null);
        engineersGuildImage.setFitHeight(100);
        engineersGuildImage.setFitWidth(100);
        engineersGuildButton.setGraphic(engineersGuildImage);
        engineersGuildButton.setLayoutX(220);
        engineersGuildButton.setLayoutY(710);
        engineersGuildButton.setMinSize(100, 100);
        pane.getChildren().add(engineersGuildButton);

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
        pane.getChildren().add(mangonelButton);

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
        pane.getChildren().add(ballistaButton);

        Button stableButton = new Button();
        ImageView stableImage = new ImageView(buildingImages.getStable());
        stableButton.setBackground(null);
        stableImage.setFitHeight(100);
        stableImage.setFitWidth(100);
        stableButton.setGraphic(stableImage);
        stableButton.setLayoutX(630);
        stableButton.setLayoutY(710);
        stableButton.setMinSize(100, 100);
        pane.getChildren().add(stableButton);

        Button tunnelersGuildButton = new Button();
        ImageView tunnelersGuildImage = new ImageView(buildingImages.getTunnelersGuild());
        tunnelersGuildButton.setBackground(null);
        tunnelersGuildImage.setFitHeight(100);
        tunnelersGuildImage.setFitWidth(100);
        tunnelersGuildButton.setGraphic(tunnelersGuildImage);
        tunnelersGuildButton.setLayoutX(770);
        tunnelersGuildButton.setLayoutY(710);
        tunnelersGuildButton.setMinSize(100, 100);
        pane.getChildren().add(tunnelersGuildButton);

        Button oilSmelterButton = new Button();
        ImageView oilSmelterImage = new ImageView(buildingImages.getOilSmelter());
        oilSmelterButton.setBackground(null);
        oilSmelterImage.setFitHeight(100);
        oilSmelterImage.setFitWidth(100);
        oilSmelterButton.setGraphic(oilSmelterImage);
        oilSmelterButton.setLayoutX(910);
        oilSmelterButton.setLayoutY(710);
        oilSmelterButton.setMinSize(100, 100);
        pane.getChildren().add(oilSmelterButton);
    }
    public void createIndustryButtons(Pane pane , BuildingImages buildingImages){

    }
    public void createFarmButtons(Pane pane , BuildingImages buildingImages){

    }
    public void createTownButtons(Pane pane , BuildingImages buildingImages){

    }
    public void createWeaponsButtons(Pane pane , BuildingImages buildingImages){

    }
    public void createFoodButtons(Pane pane , BuildingImages buildingImages){

    }



    public void clearCastleButtons(){

    }
    public void clearIndustryButtons(){

    }
    public void clearFarmButtons(){

    }
    public void clearTownButtons(){

    }
    public void clearWeaponsButtons(){

    }
    public void clearFoodButtons(){

    }
}

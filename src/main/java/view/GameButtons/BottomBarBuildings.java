package view.GameButtons;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import view.ImageAndBackground.BottomBarImages;
import view.ImageAndBackground.BuildingImages;

public class BottomBarBuildings {


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
        pane.getChildren().add(stockpileButton);

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
        pane.getChildren().add(woodCutterButton);

        Button quarryButton = new Button();
        ImageView quarryImage = new ImageView(buildingImages.getQuarry());
        quarryButton.setBackground(null);
        quarryImage.setFitHeight(100);
        quarryImage.setFitWidth(100);
        quarryButton.setGraphic(quarryImage);
        quarryButton.setLayoutX(360);
        quarryButton.setLayoutY(710);
        quarryButton.setMinSize(100, 100);
        pane.getChildren().add(quarryButton);

        Button oxTetherButton = new Button();
        ImageView oxTetherImage = new ImageView(buildingImages.getOxTheater());
        oxTetherButton.setBackground(null);
        oxTetherImage.setFitHeight(100);
        oxTetherImage.setFitWidth(100);
        oxTetherButton.setGraphic(oxTetherImage);
        oxTetherButton.setLayoutX(480);
        oxTetherButton.setLayoutY(710);
        oxTetherButton.setMinSize(100, 100);
        pane.getChildren().add(oxTetherButton);

        Button ironMineButton = new Button();
        ImageView ironMineImage = new ImageView(buildingImages.getIronMine());
        ironMineButton.setBackground(null);
        ironMineImage.setFitHeight(100);
        ironMineImage.setFitWidth(100);
        ironMineButton.setGraphic(ironMineImage);
        ironMineButton.setLayoutX(600);
        ironMineButton.setLayoutY(710);
        ironMineButton.setMinSize(100, 100);
        pane.getChildren().add(ironMineButton);

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
        pane.getChildren().add(pitchRigButton);

        Button marketButton = new Button();
        ImageView marketImage = new ImageView(buildingImages.getMarket());
        marketButton.setBackground(null);
        marketImage.setFitHeight(100);
        marketImage.setFitWidth(100);
        marketButton.setGraphic(marketImage);
        marketButton.setLayoutX(840);
        marketButton.setLayoutY(710);
        marketButton.setMinSize(100, 100);
        pane.getChildren().add(marketButton);
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
        pane.getChildren().add(dairyFarmButton);
        ImageView dairy = new ImageView(buildingImages.getDairy());
        dairy.setFitWidth(50);
        dairy.setFitHeight(50);
        dairy.setLayoutX(130);
        dairy.setLayoutY(780);
        pane.getChildren().add(dairy);

        Button appleFarmButton = new Button();
        ImageView appleFarmImage = new ImageView(buildingImages.getAppleFarm());
        appleFarmButton.setBackground(null);
        appleFarmImage.setFitHeight(100);
        appleFarmImage.setFitWidth(100);
        appleFarmButton.setGraphic(appleFarmImage);
        appleFarmButton.setLayoutX(280);
        appleFarmButton.setLayoutY(710);
        appleFarmButton.setMinSize(100, 100);
        pane.getChildren().add(appleFarmButton);
        ImageView apple = new ImageView(buildingImages.getApple());
        apple.setFitWidth(50);
        apple.setFitHeight(50);
        apple.setLayoutX(290);
        apple.setLayoutY(780);
        pane.getChildren().add(apple);

        Button wheatFarmButton = new Button();
        ImageView wheatFarmImage = new ImageView(buildingImages.getWheatFarm());
        wheatFarmButton.setBackground(null);
        wheatFarmImage.setFitHeight(100);
        wheatFarmImage.setFitWidth(100);
        wheatFarmButton.setGraphic(wheatFarmImage);
        wheatFarmButton.setLayoutX(440);
        wheatFarmButton.setLayoutY(710);
        wheatFarmButton.setMinSize(100, 100);
        pane.getChildren().add(wheatFarmButton);
        ImageView wheat = new ImageView(buildingImages.getWheat());
        wheat.setFitWidth(50);
        wheat.setFitHeight(50);
        wheat.setLayoutX(450);
        wheat.setLayoutY(780);
        pane.getChildren().add(wheat);

        Button hopsFarmButton = new Button();
        ImageView hopsFarmImage = new ImageView(buildingImages.getHopsFarm());
        hopsFarmButton.setBackground(null);
        hopsFarmImage.setFitHeight(100);
        hopsFarmImage.setFitWidth(100);
        hopsFarmButton.setGraphic(hopsFarmImage);
        hopsFarmButton.setLayoutX(600);
        hopsFarmButton.setLayoutY(710);
        hopsFarmButton.setMinSize(100, 100);
        pane.getChildren().add(hopsFarmButton);
        ImageView hops = new ImageView(buildingImages.getHops());
        hops.setFitWidth(50);
        hops.setFitHeight(50);
        hops.setLayoutX(610);
        hops.setLayoutY(780);
        pane.getChildren().add(hops);
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
        pane.getChildren().add(hovelButton);

        Button smallChurchButton = new Button();
        ImageView smallChurchImage = new ImageView(buildingImages.getSmallChurch());
        smallChurchButton.setBackground(null);
        smallChurchImage.setFitHeight(100);
        smallChurchImage.setFitWidth(100);
        smallChurchButton.setGraphic(smallChurchImage);
        smallChurchButton.setLayoutX(280);
        smallChurchButton.setLayoutY(710);
        smallChurchButton.setMinSize(100, 100);
        pane.getChildren().add(smallChurchButton);

        Button bigChurchButton = new Button();
        ImageView bigChurchImage = new ImageView(buildingImages.getBigChurch());
        bigChurchButton.setBackground(null);
        bigChurchImage.setFitHeight(100);
        bigChurchImage.setFitWidth(100);
        bigChurchButton.setGraphic(bigChurchImage);
        bigChurchButton.setLayoutX(440);
        bigChurchButton.setLayoutY(710);
        bigChurchButton.setMinSize(100, 100);
        pane.getChildren().add(bigChurchButton);

        Button apothecaryButton = new Button();
        ImageView apothecaryImage = new ImageView(buildingImages.getApothecary());
        apothecaryButton.setBackground(null);
        apothecaryImage.setFitHeight(100);
        apothecaryImage.setFitWidth(100);
        apothecaryButton.setGraphic(apothecaryImage);
        apothecaryButton.setLayoutX(600);
        apothecaryButton.setLayoutY(710);
        apothecaryButton.setMinSize(100, 100);
        pane.getChildren().add(apothecaryButton);

        Button tortureBuildingButton = new Button();
        ImageView tortureBuildingImage = new ImageView(buildingImages.getTortureBuilding());
        tortureBuildingButton.setBackground(null);
        tortureBuildingImage.setFitHeight(100);
        tortureBuildingImage.setFitWidth(100);
        tortureBuildingButton.setGraphic(tortureBuildingImage);
        tortureBuildingButton.setLayoutX(760);
        tortureBuildingButton.setLayoutY(710);
        tortureBuildingButton.setMinSize(100, 100);
        pane.getChildren().add(tortureBuildingButton);

        Button gardenButton = new Button();
        ImageView gardenImage = new ImageView(buildingImages.getGarden());
        gardenButton.setBackground(null);
        gardenImage.setFitHeight(100);
        gardenImage.setFitWidth(100);
        gardenButton.setGraphic(gardenImage);
        gardenButton.setLayoutX(920);
        gardenButton.setLayoutY(710);
        gardenButton.setMinSize(100, 100);
        pane.getChildren().add(gardenButton);
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
        pane.getChildren().add(fletcherButton);
        ImageView bow = new ImageView(buildingImages.getBow());
        bow.setFitWidth(80);
        bow.setFitHeight(80);
        bow.setLayoutX(150);
        bow.setLayoutY(750);
        pane.getChildren().add(bow);

        Button poleTurnerButton = new Button();
        ImageView poleTurnerImage = new ImageView(buildingImages.getPoleTurner());
        poleTurnerButton.setBackground(null);
        poleTurnerImage.setFitHeight(100);
        poleTurnerImage.setFitWidth(100);
        poleTurnerButton.setGraphic(poleTurnerImage);
        poleTurnerButton.setLayoutX(260);
        poleTurnerButton.setLayoutY(710);
        poleTurnerButton.setMinSize(100, 100);
        pane.getChildren().add(poleTurnerButton);
        ImageView pike = new ImageView(buildingImages.getPike());
        pike.setFitWidth(80);
        pike.setFitHeight(80);
        pike.setLayoutX(290);
        pike.setLayoutY(750);
        pane.getChildren().add(pike);

        Button blacksmithButton = new Button();
        ImageView blacksmithImage = new ImageView(buildingImages.getBlacksmith());
        blacksmithButton.setBackground(null);
        blacksmithImage.setFitHeight(100);
        blacksmithImage.setFitWidth(100);
        blacksmithButton.setGraphic(blacksmithImage);
        blacksmithButton.setLayoutX(400);
        blacksmithButton.setLayoutY(710);
        blacksmithButton.setMinSize(100, 100);
        pane.getChildren().add(blacksmithButton);
        ImageView sword = new ImageView(buildingImages.getSword());
        sword.setFitWidth(50);
        sword.setFitHeight(50);
        sword.setLayoutX(450);
        sword.setLayoutY(760);
        pane.getChildren().add(sword);

        Button tannersButton = new Button();
        ImageView tannersImage = new ImageView(buildingImages.getTanner());
        tannersButton.setBackground(null);
        tannersImage.setFitHeight(100);
        tannersImage.setFitWidth(100);
        tannersButton.setGraphic(tannersImage);
        tannersButton.setLayoutX(540);
        tannersButton.setLayoutY(710);
        tannersButton.setMinSize(100, 100);
        pane.getChildren().add(tannersButton);
        ImageView latherArmor = new ImageView(buildingImages.getLeatherArmor());
        latherArmor.setFitWidth(50);
        latherArmor.setFitHeight(50);
        latherArmor.setLayoutX(590);
        latherArmor.setLayoutY(760);
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
        pane.getChildren().add(armourerButton);
        ImageView metalArmour = new ImageView(buildingImages.getMetalArmor());
        metalArmour.setFitWidth(50);
        metalArmour.setFitHeight(50);
        metalArmour.setLayoutX(690);
        metalArmour.setLayoutY(770);
        pane.getChildren().add(metalArmour);

        Button siegeTentButton = new Button();
        ImageView siegeTentImage = new ImageView(buildingImages.getSiegeTent());
        siegeTentButton.setBackground(null);
        siegeTentImage.setFitHeight(100);
        siegeTentImage.setFitWidth(100);
        siegeTentButton.setGraphic(siegeTentImage);
        siegeTentButton.setLayoutX(820);
        siegeTentButton.setLayoutY(710);
        siegeTentButton.setMinSize(100, 100);
        pane.getChildren().add(siegeTentButton);
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
        pane.getChildren().add(granaryButton);

        Button bakeryButton = new Button();
        ImageView bakeryImage = new ImageView(buildingImages.getBakery());
        bakeryButton.setBackground(null);
        bakeryImage.setFitHeight(100);
        bakeryImage.setFitWidth(100);
        bakeryButton.setGraphic(bakeryImage);
        bakeryButton.setLayoutX(280);
        bakeryButton.setLayoutY(710);
        bakeryButton.setMinSize(100, 100);
        pane.getChildren().add(bakeryButton);
        ImageView bread = new ImageView(buildingImages.getBread());
        bread.setFitWidth(60);
        bread.setFitHeight(60);
        bread.setLayoutX(330);
        bread.setLayoutY(760);
        pane.getChildren().add(bread);

        Button breweryButton = new Button();
        ImageView breweryImage = new ImageView(buildingImages.getBrewery());
        breweryButton.setBackground(null);
        breweryImage.setFitHeight(100);
        breweryImage.setFitWidth(100);
        breweryButton.setGraphic(breweryImage);
        breweryButton.setLayoutX(440);
        breweryButton.setLayoutY(710);
        breweryButton.setMinSize(100, 100);
        pane.getChildren().add(breweryButton);
        ImageView barrel = new ImageView(buildingImages.getBarrel());
        barrel.setFitWidth(60);
        barrel.setFitHeight(60);
        barrel.setLayoutX(490);
        barrel.setLayoutY(760);
        pane.getChildren().add(barrel);

        Button millButton = new Button();
        ImageView millImage = new ImageView(buildingImages.getMill());
        millButton.setBackground(null);
        millImage.setFitHeight(100);
        millImage.setFitWidth(100);
        millButton.setGraphic(millImage);
        millButton.setLayoutX(600);
        millButton.setLayoutY(710);
        millButton.setMinSize(100, 100);
        pane.getChildren().add(millButton);
        ImageView flour = new ImageView(buildingImages.getFlour());
        flour.setFitWidth(60);
        flour.setFitHeight(60);
        flour.setLayoutX(650);
        flour.setLayoutY(760);
        pane.getChildren().add(flour);

        Button innButton = new Button();
        ImageView innImage = new ImageView(buildingImages.getInn());
        innButton.setBackground(null);
        innImage.setFitHeight(100);
        innImage.setFitWidth(100);
        innButton.setGraphic(innImage);
        innButton.setLayoutX(760);
        innButton.setLayoutY(710);
        innButton.setMinSize(100, 100);
        pane.getChildren().add(innButton);
        ImageView beer = new ImageView(buildingImages.getBeer());
        beer.setFitWidth(80);
        beer.setFitHeight(80);
        beer.setLayoutX(810);
        beer.setLayoutY(760);
        pane.getChildren().add(beer);
    }


    public void clearCastleButtons() {

    }

    public void clearIndustryButtons() {

    }

    public void clearFarmButtons() {

    }

    public void clearTownButtons() {

    }

    public void clearWeaponsButtons() {

    }

    public void clearFoodButtons() {

    }
}

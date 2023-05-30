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

    }
    public void createMilitaryBuildingsButtons(Pane pane , BuildingImages buildingImages){

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

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


    }
    public void createIndustryButtons(){

    }
    public void createFarmButtons(){

    }
    public void createTownButtons(){

    }
    public void createWeaponsButtons(){

    }
    public void createFoodButtons(){

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

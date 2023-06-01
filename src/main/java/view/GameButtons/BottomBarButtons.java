package view.GameButtons;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.Building.Castle;
import model.Building.Stockpile;
import model.Manage;
import view.ImageAndBackground.BottomBarImages;
import view.ImageAndBackground.BuildingImages;

public class BottomBarButtons {
    public void createButtons(Pane pane, BottomBarImages bottomBarImages, BottomBarBuildings bottomBarBuildings, BuildingImages buildingImages) {
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
        ;

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

        Button minimapFrameButton = new Button();
        ImageView minimapFrameImage = new ImageView(bottomBarImages.getMinimapFrame());
        minimapFrameButton.setBackground(null);
        minimapFrameImage.setFitHeight(190);
        minimapFrameImage.setFitWidth(193);
        minimapFrameButton.setGraphic(minimapFrameImage);
        minimapFrameButton.setLayoutX(1170);
        minimapFrameButton.setLayoutY(675);
        minimapFrameButton.setMinSize(200, 200);
        pane.getChildren().add(minimapFrameButton);


        Button dataButton = new Button();
        ImageView dataImage = new ImageView(bottomBarImages.showEmpireDetail);
        dataButton.setBackground(null);
        dataImage.setFitHeight(190);
        dataImage.setFitWidth(190);
        dataButton.setGraphic(dataImage);
        dataButton.setLayoutX(1350);
        dataButton.setLayoutY(675);
        dataButton.setMinSize(200, 200);
        pane.getChildren().add(dataButton);

//        Button testButton = new Button();
//        ImageView testImage = new ImageView(bottomBarImages.getTest());
//        testButton.setBackground(null);
//        testImage.setFitHeight(100);
//        testImage.setFitWidth(100);
//        testButton.setGraphic(testImage);
//        testButton.setLayoutX(600);
//        testButton.setLayoutY(400);
//        testButton.setMinSize(200, 200);
//        pane.getChildren().add(testButton);

        ImageView face = new ImageView(bottomBarImages.getFaceImage());
        face.setFitHeight(63);
        face.setFitWidth(70);
        face.setX(1415);
        face.setY(690);
        pane.getChildren().add(face);

        //TODO : fix the gold source
        Text popularity = new Text();
        popularity.setText("POPULARITY : " + 100);
        popularity.setX(1415);
        popularity.setY(770);
        pane.getChildren().add(popularity);

        //TODO : fix the gold source
        Text gold = new Text();
        gold.setText("GOLD : " + 2000);
        gold.setX(1415);
        gold.setY(790);
        pane.getChildren().add(gold);

        //TODO : fix the gold source
        Text population = new Text();
        population.setText("POPULATION : " + 20);
        population.setX(1415);
        population.setY(810);
        pane.getChildren().add(population);

        castleButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bottomBarBuildings.clearPane(pane);
                bottomBarBuildings.createCastleButtons(pane, buildingImages);
            }
        });

        foodButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bottomBarBuildings.clearPane(pane);
                bottomBarBuildings.createFarmButtons(pane, buildingImages);
            }
        });

        hammerButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bottomBarBuildings.clearPane(pane);
                bottomBarBuildings.createIndustryButtons(pane, buildingImages);
            }
        });

        homeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bottomBarBuildings.clearPane(pane);
                bottomBarBuildings.createTownButtons(pane, buildingImages);
            }
        });

        shieldButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bottomBarBuildings.clearPane(pane);
                bottomBarBuildings.createWeaponsButtons(pane, buildingImages);
            }
        });

        sickleButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bottomBarBuildings.clearPane(pane);
                bottomBarBuildings.createFoodButtons(pane, buildingImages);
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
    }
}

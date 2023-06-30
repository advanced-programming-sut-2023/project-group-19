package view.GameButtons;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Building.Castle;
import model.Building.Stockpile;
import model.Empire;
import model.Manage;
import model.Map;
import view.ImageAndBackground.BottomBarImages;
import view.ImageAndBackground.BuildingImages;
import view.OldView.EmpireMenu;
import view.TileManager;

import java.io.IOException;
import java.util.Collection;

public class BottomBarButtons {
    public Map map ;

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
        ImageView face;
        if (Manage.getCurrentEmpire().getTotalPopularity() > 66) {
            face = new ImageView(bottomBarImages.getFaceImage1());
        } else if (Manage.getCurrentEmpire().getTotalPopularity() > 33) {
            face = new ImageView(bottomBarImages.getFaceImage2());
        } else {
            face = new ImageView(bottomBarImages.getFaceImage3());
        }
        face.setFitHeight(63);
        face.setFitWidth(70);
        face.setX(1415);
        face.setY(690);
        pane.getChildren().add(face);

        Text popularity = new Text();
        popularity.setText("POPULARITY : " + 100);
        popularity.setX(1415);
        popularity.setY(770);
        pane.getChildren().add(popularity);

        Text gold = new Text();
        gold.setText("GOLD : " + Manage.getCurrentEmpire().getGoldCount());
        gold.setX(1415);
        gold.setY(790);
        pane.getChildren().add(gold);

        Text population = new Text();
        population.setText("POPULATION : " + Manage.getCurrentEmpire().getPopulation());
        population.setX(1415);
        population.setY(810);
        pane.getChildren().add(population);

        dataButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
                TileManager.gameLog.append(TileManager.time + '#' + "LEFT_CLICK" + '#' + "DATA_BUTTON" +  '\n');
                bottomBarBuildings.clearPane(pane);
                EmpireButtons empireButtons = new EmpireButtons();
                EmpireMenu empireMenu = new EmpireMenu();
                empireButtons.createButtons(pane, bottomBarImages, buildingImages, empireMenu);
            }
        });

        castleButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
                TileManager.gameLog.append(TileManager.time + '#' + "LEFT_CLICK" + '#' + "CASTLE_BUTTON" +  '\n');
                bottomBarBuildings.clearPane(pane);
                bottomBarBuildings.createCastleButtons(pane, buildingImages);
            }
        });

        foodButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
                TileManager.gameLog.append(TileManager.time + '#' + "LEFT_CLICK" + '#' + "FOOD_BUTTON" +  '\n');
                bottomBarBuildings.clearPane(pane);
                bottomBarBuildings.createFarmButtons(pane, buildingImages);
            }
        });

        hammerButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
                TileManager.gameLog.append(TileManager.time + '#' + "LEFT_CLICK" + '#' + "HAMMER_BUTTON" +  '\n');
                bottomBarBuildings.clearPane(pane);
                bottomBarBuildings.createIndustryButtons(pane, buildingImages);
            }
        });

        homeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
                TileManager.gameLog.append(TileManager.time + '#' + "LEFT_CLICK" + '#' + "HOME_BUTTON" +  '\n');
                bottomBarBuildings.clearPane(pane);
                bottomBarBuildings.createTownButtons(pane, buildingImages);
            }
        });

        shieldButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
                TileManager.gameLog.append(TileManager.time + '#' + "LEFT_CLICK" + '#' + "SHIELD_BUTTON" +  '\n');
                bottomBarBuildings.clearPane(pane);
                bottomBarBuildings.createWeaponsButtons(pane, buildingImages);
            }
        });

        sickleButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
                TileManager.gameLog.append(TileManager.time + '#' + "LEFT_CLICK" + '#' + "SICKLE_BUTTON" +  '\n');
                bottomBarBuildings.clearPane(pane);
                bottomBarBuildings.createFoodButtons(pane, buildingImages);
            }
        });
        gameOptionButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
                TileManager.gameLog.append(TileManager.time + '#' + "LEFT_CLICK" + '#' + "GAME_OPTION_BUTTON" +  '\n');
                Pane pane1 = new Pane();
                Button button = new Button();
                Button button1 = new Button();
                Stage stage2 = new Stage();
                button.setText("Exit");
                button1.setText("continue");
                EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        stage2.close();
                    }
                };
                button.setLayoutX(175);
                button.setLayoutY(200);
                button1.setLayoutX(175);
                button1.setLayoutY(150);
                button.setOnAction(event);
                pane1.getChildren().addAll(button, button1);
                stage2.setTitle("Game Options");
                Scene scene = new Scene(pane1, 400, 400);
                stage2.setScene(scene);
                stage2.show();
            }
        });
        informationButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
                TileManager.gameLog.append(TileManager.time + '#' + "LEFT_CLICK" + '#' + "INFORMATION_BUTTON" +  '\n');
                Pane pane1 = new Pane();
                Text text = new Text();
                Text text2 = new Text();
                Button button = new Button();
                Stage stage2 = new Stage();
                button.setText("Exit");
                EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        stage2.close();
                    }
                };
                text2.setText(Manage.allEmpires.get(0).getName() + "                   Gold : " + Manage.allEmpires.get(0).getGoldCount());
                text2.setX(170);
                text2.setY(125);
                text.setX(150);
                text.setY(150);
                button.setLayoutX(175);
                button.setLayoutY(200);
                button.setOnAction(event);
                text.setText(Manage.allEmpires.get(1).getName() + "                   Gold : " + Manage.allEmpires.get(1).getGoldCount());
                pane1.getChildren().addAll(text, button, text2);

                stage2.setTitle("Info");
                Scene scene = new Scene(pane1, 400, 400);
                stage2.setScene(scene);
                stage2.show();
            }
        });
        deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
                TileManager.gameLog.append(TileManager.time + '#' + "LEFT_CLICK" + '#' + "DELETE_BUTTON" +  '\n');
                delete();
            }
        });
        undoButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
                TileManager.gameLog.append(TileManager.time + '#' + "LEFT_CLICK" + '#' + "UNDO_BUTTON" +  '\n');
                undo(pane , map);
            }
        });
    }
    public static void delete(){
        if (TileManager.deleteOn) {
            TileManager.deleteOn = false;
        } else {
            TileManager.deleteOn = true;
        }
    }
    public static void undo(Pane pane , Map map){
        if (BottomBarBuildings.lastButton != null) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    pane.getChildren().remove(BottomBarBuildings.lastButton);
                    BottomBarBuildings.lastButton.setGraphic(null);
                    BottomBarBuildings.lastButton.setImageView(null);
                    BottomBarBuildings.lastButton.setBuilding(null);
                }
            });
            int x = BottomBarBuildings.lastButton.getX();
            int y = BottomBarBuildings.lastButton.getY();
            if (map.buildingMap[x][y].size() != 0)
                map.buildingMap[x][y].remove(0);
            map.notPassable[x][y] = false;
            map.notBuildable[x][y] = false;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    pane.getChildren().add(BottomBarBuildings.lastButton);
                }
            });

        }
    }
}

package view.GameButtons;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Building.PrepareLaboursAndFighters;
import model.Building.Wall;
import model.Manage;
import view.Messages.SelectedBuildingMessages;
import view.Model.NewButton;
import view.OldView.SelectedBuildingMenu;

import java.awt.*;
import java.util.ArrayList;

public class SelectedBuildingButtons {
    public ArrayList<Button> selectedBuildingsAddedButtons = new ArrayList<>();
    public void showError(String output){
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("DROP BUILDING FAILED");
        error.setContentText(output);
        error.show();
    }

    public void barracks(Pane pane, SelectedBuildingMenu selectedBuildingMenu) {
        Button archerButton = new Button();
        ImageView archerImage = new ImageView();
        archerButton.setBackground(null);
        archerImage.setFitHeight(100);
        archerImage.setFitWidth(100);
        archerButton.setGraphic(archerImage);
        archerButton.setLayoutX(110);
        archerButton.setLayoutY(730);
        archerButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(archerButton);
        pane.getChildren().add(archerButton);
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("archer", 1));
                if(!output.equals("troop created successfully")){
                    showError(output);
                }
            }
        };
        archerButton.setOnMouseClicked(event);

        Button spearManButton = new Button();
        ImageView spearManImage = new ImageView();
        spearManButton.setBackground(null);
        spearManImage.setFitHeight(100);
        spearManImage.setFitWidth(100);
        spearManButton.setGraphic(archerImage);
        spearManButton.setLayoutX(250);
        spearManButton.setLayoutY(730);
        spearManButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(spearManButton);
        pane.getChildren().add(spearManButton);
        EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("spearMan", 1));
                if(!output.equals("troop created successfully")){
                    showError(output);
                }
            }
        };
        spearManButton.setOnMouseClicked(event1);

        Button maceManButton = new Button();
        ImageView maceManImage = new ImageView();
        maceManButton.setBackground(null);
        maceManImage.setFitHeight(100);
        maceManImage.setFitWidth(100);
        maceManButton.setGraphic(maceManImage);
        maceManButton.setLayoutX(390);
        maceManButton.setLayoutY(730);
        maceManButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(maceManButton);
        pane.getChildren().add(maceManButton);
        EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("maceMan", 1));
                if(!output.equals("troop created successfully")){
                    showError(output);
                }
            }
        };
        maceManButton.setOnMouseClicked(event2);

        Button crossbowManButton = new Button();
        ImageView crossbowManImage = new ImageView();
        crossbowManButton.setBackground(null);
        crossbowManImage.setFitHeight(100);
        crossbowManImage.setFitWidth(100);
        crossbowManButton.setGraphic(crossbowManImage);
        crossbowManButton.setLayoutX(530);
        crossbowManButton.setLayoutY(730);
        crossbowManButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(crossbowManButton);
        pane.getChildren().add(crossbowManButton);
        EventHandler<MouseEvent> event3 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("crossbowMan", 1));
                if(!output.equals("troop created successfully")){
                    showError(output);
                }
            }
        };
        crossbowManButton.setOnMouseClicked(event3);

        Button pikeManButton = new Button();
        ImageView pikeManImage = new ImageView();
        pikeManButton.setBackground(null);
        pikeManImage.setFitHeight(100);
        pikeManImage.setFitWidth(100);
        pikeManButton.setGraphic(pikeManImage);
        pikeManButton.setLayoutX(670);
        pikeManButton.setLayoutY(730);
        pikeManButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(pikeManButton);
        pane.getChildren().add(pikeManButton);
        EventHandler<MouseEvent> event4 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("pikeMan", 1));
                if(!output.equals("troop created successfully")){
                    showError(output);
                }
            }
        };
        pikeManButton.setOnMouseClicked(event4);

        Button swordManButton = new Button();
        ImageView swordManImage = new ImageView();
        swordManButton.setBackground(null);
        swordManImage.setFitHeight(100);
        swordManImage.setFitWidth(100);
        swordManButton.setGraphic(swordManImage);
        swordManButton.setLayoutX(810);
        swordManButton.setLayoutY(730);
        swordManButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(swordManButton);
        pane.getChildren().add(swordManButton);
        EventHandler<MouseEvent> event5 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("swordMan", 1));
                if(!output.equals("troop created successfully")){
                    showError(output);
                }
            }
        };
        swordManButton.setOnMouseClicked(event5);

        Button knightButton = new Button();
        ImageView knightImage = new ImageView();
        knightButton.setBackground(null);
        knightImage.setFitHeight(100);
        knightImage.setFitWidth(100);
        knightButton.setGraphic(knightImage);
        knightButton.setLayoutX(950);
        knightButton.setLayoutY(730);
        knightButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(knightButton);
        pane.getChildren().add(knightButton);
        EventHandler<MouseEvent> event6 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("knight", 1));
                if(!output.equals("troop created successfully")){
                    showError(output);
                }
            }
        };
        knightButton.setOnMouseClicked(event6);
    }

    public void mercenary() {

    }

    public void siegeTent() {

    }

    public void engineerGuild() {

    }

    public ArrayList<Button> getSelectedBuildingsAddedButtons() {
        return selectedBuildingsAddedButtons;
    }

    public void setSelectedBuildingsAddedButtons(ArrayList<Button> selectedBuildingsAddedButtons) {
        this.selectedBuildingsAddedButtons = selectedBuildingsAddedButtons;
    }
}

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
        archerButton.setLayoutY(700);
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

package view.GameButtons;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Building.Wall;
import model.Manage;
import view.Model.NewButton;

import java.awt.*;

public class SelectedBuildingButtons {

    public void barracks(){
//        Button archerButton = new Button();
//        ImageView archerImage = new ImageView(buildingImages.getWallStair());
//        wallStairButton.setBackground(null);
//        wallStairImage.setFitHeight(100);
//        wallStairImage.setFitWidth(100);
//        wallStairButton.setGraphic(wallStairImage);
//        wallStairButton.setLayoutX(110);
//        wallStairButton.setLayoutY(700);
//        wallStairButton.setMinSize(100, 100);
//        addedButtons.add(wallStairButton);
//        pane.getChildren().add(wallStairButton);
//        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                PointerInfo a = MouseInfo.getPointerInfo();
//                Point b = a.getLocation();
//                int x = (int) ((int) b.getX() / 51);
//                int y = (int) b.getY() / 54;
//                NewButton newbutton = allButtons[y][x].get(0);
//                Wall stair = new Wall(Manage.getCurrentEmpire());
//                stair.stair();
//                building = stair;
//                String output = buildingController.dropBuilding(y, x, building.getName()).getMessages();
//                if (output.equals("building created successfully")) {
//                    pane.getChildren().remove(allButtons[y][x].get(0));
//                    newbutton.setImageView(wallStairImage);
//                    newbutton.setBuilding(stair);
//                    pane.getChildren().add(newbutton);
//                }
//                else {
//                    showError(output);
//                }
//            }
//        };
//        wallStairButton.setOnMouseReleased(event);
    }
    public void mercenary(){

    }
    public void siegeTent(){

    }
    public void engineerGuild(){

    }

}

package view;

import controller.ShowMapController;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import model.Map;
import view.Model.NewButton;
import view.Model.NewRectangle;

import java.awt.*;
import java.util.ArrayList;


public class TileManager extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        TilePane tilePane = new TilePane();
//        tilePane.setLayoutX(-100);
//        tilePane.setLayoutY(-100);
        tilePane.setTileAlignment(Pos.CENTER);
        tilePane.setPrefColumns(100);
        tilePane.setMaxWidth(10000);
        ArrayList<Shape> list = new ArrayList<>();
        TilePane view  = new TilePane();
        for(int j = 0 ; j < 100 ; j++) {
            for (int i = 0; i < 100; i++) {
                NewRectangle newButton = new NewRectangle(j , i);
                newButton.setWidth(100);
                newButton.setHeight(100);
//                newButton.setFill(Color.BLUE);
                list.add(newButton);
            }
        }
//         width  = 1530
//         height = 800
        Map.CreateMap(200);
        ShowMapController.showMap(list,view);
        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
        double width = resolution.getWidth();
        double height = resolution.getHeight();
        Scene scene = new Scene(view , width-50 , height-50);
        stage.setTitle("Tile Pane");
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
    }
    private void applyingEventForButton(Button newButton){
        newButton.setOnMouseClicked(event ->
        {
            if(event.getButton() == MouseButton.PRIMARY){
                //TODO : DEPLOY
            }
            if(event.getButton() == MouseButton.SECONDARY){
                //TODO : ...
            }
        });
        newButton.setOnMouseDragged(event ->
        {
            if(event.getButton() == MouseButton.PRIMARY){
                //TODO : select
            }
            if(event.getButton() == MouseButton.SECONDARY){
                //TODO : move
            }
        });
    }
}

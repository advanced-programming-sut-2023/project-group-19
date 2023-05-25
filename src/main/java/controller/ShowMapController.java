package controller;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.*;
import model.Building.Building;
import model.Human.Names;
import model.Human.Troop.Army;
import model.Obstacle.Obstacle;

import model.Obstacle.ObstacleName;
import model.Obstacle.WaterSources;
import model.Obstacle.*;
import view.TileManager;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowMapController {
    public static void showMap(ArrayList<Shape> list, TilePane tilePane){
        //default
        Image image = new Image(TileManager.class.getResource("/image/groundTypes/alaf.png").toExternalForm());
        for(int u = 0 ; u < 16 ; u++){
            for(int g = 0 ;  g < 30 ; g++ ){
                switch (Map.getGroundType()[u][g].get(0)){
                    case STONE_ROCK:
                        image = new Image(TileManager.class.getResource("/image/cegla2.jpg").toExternalForm());
                        break;
                    case STONE:
                        image = new Image(TileManager.class.getResource("/image/cegla2.jpg").toExternalForm());
                        break;
                    case GROUND_WITH_STONE :
                        image = new Image(TileManager.class.getResource("/image/cegla2.jpg").toExternalForm());
                        break;
                    case IRON:
                        image = new Image(TileManager.class.getResource("/image/cegla2.jpg").toExternalForm());
                        break;
                    case GRASS:
                        image = new Image(TileManager.class.getResource("/image/cegla2.jpg").toExternalForm());
                        break;
                    case DASH:
                        image = new Image(TileManager.class.getResource("/image/cegla2.jpg").toExternalForm());
                        break;
                    case PLAIN:
                        image = new Image(TileManager.class.getResource("/image/cegla2.jpg").toExternalForm());
                        break;
                    case FILLFUL_DASH:
                        image = new Image(TileManager.class.getResource("/image/cegla2.jpg").toExternalForm());
                        break;
                }
                list.get((u) * 100 + (g)).setFill(new ImagePattern(image));
                tilePane.getChildren().add(list.get((u) * 100 + (g)));
            }
        }
    }
}
// new Image(TileManager.class.getResource("/image/cegla2.jpg").toExternalForm()


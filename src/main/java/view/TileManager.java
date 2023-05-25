package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import view.Model.NewButton;

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
        ArrayList<Node> list = new ArrayList<>();
        TilePane view  = new TilePane();
        for(int j = 0 ; j < 100 ; j++) {
            for (int i = 0; i < 100; i++) {

                NewButton newButton = new NewButton(j , i);
                EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (mouseEvent.getEventType().getName().equals(MouseEvent.MOUSE_DRAGGED.getName())){
//                            moveTheMap(view,list);
                            System.out.println("Dragged");
                        }else {
                            System.out.println("Clicked");
                        }
                    }
                };
//                EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent mouseEvent) {
//                        System.out.println("x : " + newButton.getX() );
//                        System.out.println("y : " + newButton.getY());
////                        button.setStyle("-fx-background-color: red;" + "-fx-text-fill: white");
//                    }
//                };
                newButton.setPrefSize(51 , 54);
                newButton.setFocusTraversable(false);
                newButton.setOnMouseDragged(event1);
                newButton.setOnMouseClicked(event1);
                list.add(newButton);
            }
        }
//         width  = 1530
//         height = 800
        for(int u = 0 ; u < 16 ; u++){
            for(int g = 0 ;  g < 30 ; g++ ){
                ((Button)list.get((u + 3) * 100 + (g + 10))).setBackground(new Background(new BackgroundImage(
                        new Image(TileManager.class.getResource("/image/cegla2.jpg").toExternalForm()) ,
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                view.getChildren().add(list.get((u + 3) * 100 + (g + 10)));
            }
        }
        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
        double width = resolution.getWidth();
        double height = resolution.getHeight();
        Scene scene = new Scene(view , width-50 , height-50);

        stage.setTitle("Tile Pane");
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
    }

    private static void moveTheMap(TilePane view ,ArrayList<Node> list ){
    }
}

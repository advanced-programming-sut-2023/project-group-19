package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Empire;
import model.Human.Troop.Army;
import model.Manage;
import org.w3c.dom.ls.LSOutput;
import view.Model.NewButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class TileManager extends Application {

    public ArrayList<String> cellArmyNameType = new ArrayList<>();
    public Text showCellData = new Text();
    public int avgDamage;
    public int avgSpeed;
    public TilePane view  = new TilePane();
    public int avgHp;

    @Override
    public void start(Stage stage) throws Exception {
        TilePane tilePane = new TilePane();
//        tilePane.setLayoutX(-100);
//        tilePane.setLayoutY(-100);
        tilePane.setTileAlignment(Pos.CENTER);
//        tilePane.setPrefColumns(100);
//        tilePane.setMaxWidth(10000);
        ArrayList<Node> list = new ArrayList<>();
        for(int j = 0 ; j < 100 ; j++) {
            for (int i = 0; i < 100; i++) {
                NewButton newButton = new NewButton(j , i);
                setEventHandler(newButton);
                newButton.setPrefSize(51 , 54);
                newButton.setFocusTraversable(false);

                list.add(newButton);
            }
        }

//         width  = 1530
//         height = 800


        for(int u = 0 ; u < 16 ; u++){
            for(int g = 0 ;  g < 30 ; g++ ){
//                ((Button)list.get((u + 3) * 100 + (g + 10))).setBackground(new Background(new BackgroundImage(
//                        new Image("C:\\Users\\F1\\Desktop\\chert\\ProjectGroup19\\src\\main\\resources\\image\\cegla2.jpg") ,
//                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                view.getChildren().add(list.get((u + 3) * 100 + (g + 10)));
            }
        }

//        view.setBackground(new Background( new BackgroundImage( new Image(Game.class.getResource("/image/cegla2.jpg").toExternalForm()) ,
//                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
        double width = resolution.getWidth();
        double height = resolution.getHeight();
        view.requestFocus();
        view.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();
//                System.out.println(keyName);
                 if (keyName.equals("Add")) {

                } else if (keyName.equals("Subtract")) {

                }
            }
        });
        Scene scene = new Scene(view , width-50 , height-50);

        stage.setTitle("Tile Pane");
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
    }
    public void setEventHandler(NewButton newButton){
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("x : " + newButton.getX());
                System.out.println("y : " + newButton.getY());
                newButton.setStyle("-fx-border-color: brown");
            }
        };
        EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                newButton.setStyle(null);
                showCellData.setText("");
                view.getChildren().remove(showCellData);
            }
        };
        EventHandler<MouseEvent> event3 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                getCellData(newButton);
//                PointerInfo a = MouseInfo.getPointerInfo();
//                Point b = a.getLocation();
//                int x = (int) b.getX();
//                int y = (int) b.getY();
//                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append("AVG Hp : " + avgHp + '\n'  + "AVG Damage : " + avgDamage + '\n' +
//                        "AVG Speed : " + avgSpeed + '\n' + cellArmyNameType);
//                showCellData.setText(stringBuilder.toString());
//                showCellData.setX(x);
//                showCellData.setY(y);
                showCellData.setText("ali");
                showCellData.setX(500);
                showCellData.setY(500);
                view.getChildren().add(showCellData);
            }
        };
//        newButton.setOnMouseClicked(event);
        newButton.setOnMouseClicked(event);
        newButton.setOnMouseExited(event2);
        newButton.setOnMouseEntered(event3);
    }
    public void getCellData(NewButton newButton){
        cellArmyNameType.clear();
        int damage = 0;
        int hp = 0;
        int speed = 0;
        for(int i = 0 ;  i < newButton.getArmy().size() ; i++){
            if(!cellArmyNameType.contains(newButton.getArmy().get(i).getNames().getName())){
                cellArmyNameType.add(newButton.getArmy().get(i).getNames().getName());
            }
            damage += newButton.getArmy().get(i).getAttackPower();
            hp += newButton.getArmy().get(i).getHp();
            speed += newButton.getArmy().get(i).getSpeed();
        }
    }
}

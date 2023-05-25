package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import model.Empire;
import model.Human.Troop.ArchersAndThrowers;
import model.Human.Troop.Army;
import model.Human.Troop.Soldiers;
import model.Manage;
import org.w3c.dom.ls.LSOutput;
import view.Model.NewButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.PrimitiveIterator;
import java.util.Timer;
import java.util.TimerTask;


public class TileManager extends Application {
    //TODO : Show Map ---> Armin's Method
    public ArrayList<String> cellArmyNameType = new ArrayList<>();
    public Text showCellData = new Text();
    public int avgDamage;
    public int avgSpeed;
    public TilePane view  = new TilePane();
    public Pane pane = new Pane();
    public int avgHp;

    @Override
    public void start(Stage stage) throws Exception {
//        tilePane.setLayoutX(-100);
//        tilePane.setLayoutY(-100);
//        tilePane.setPrefColumns(100);
//        tilePane.setMaxWidth(10000);
        ArrayList<Node> list = new ArrayList<>();
        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < 100; i++) {
                NewButton newButton = new NewButton(j , i);
                setEventHandler(newButton);
                newButton.setPrefSize(51 , 54);
                newButton.setFocusTraversable(false);
                Soldiers soldiers = new Soldiers(Manage.getCurrentEmpire());
                soldiers.BlackMonk(5 , 16);
                ArchersAndThrowers archersAndThrowers = new ArchersAndThrowers(Manage.getCurrentEmpire());
                archersAndThrowers.archer(5 , 16);
                newButton.getArmy().add(archersAndThrowers );
                newButton.getArmy().add(soldiers );
                newButton.setText(String.valueOf(j * 100 + i));
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
                Button button = (Button) list.get((u + 3) * 100 + (g + 10));
                button.setLayoutX(g * 51.2);
                button.setLayoutY(u * 54);
                pane.getChildren().add(list.get((u + 3) * 100 + (g + 10)));
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
        pane.getChildren().add(view);
        Scene scene = new Scene(pane , width-50 , height-50);

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
                pane.getChildren().remove(showCellData);
            }
        };
        EventHandler<MouseEvent> event3 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                getCellData(newButton);
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                int x = (int) b.getX();
                int y = (int) b.getY() - 50;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("AVG Hp : " + avgHp + '\n'  + "AVG Damage : " + avgDamage + '\n' +
                        "AVG Speed : " + avgSpeed + '\n');
                for(int i = 0 ; i < cellArmyNameType.size() ; i++){
                    stringBuilder.append(cellArmyNameType.get(i) + " ");
                }
                showCellData.setText(stringBuilder.toString());
                showCellData.setX(x);
                showCellData.setY(y);
                if(showCellData != null && !pane.getChildren().contains(showCellData))
                    pane.getChildren().add(showCellData);
            }
        };
//        newButton.setOnMouseClicked(event);
        newButton.setOnMouseClicked(event);
        newButton.setOnMouseExited(event2);
        newButton.setOnMouseMoved(event3);
//        newButton.setOnMouse(event3);
    }
    public void getCellData(NewButton newButton){
        cellArmyNameType.clear();
        int damage = 0;
        int hp = 0;
        int speed = 0;
        int i;
        for(i = 0 ;  i < newButton.getArmy().size() ; i++){
            if(!cellArmyNameType.contains(newButton.getArmy().get(i).getNames().getName())){
                cellArmyNameType.add(newButton.getArmy().get(i).getNames().getName());
            }
            damage += newButton.getArmy().get(i).getAttackPower();
            hp += newButton.getArmy().get(i).getHp();
            speed += newButton.getArmy().get(i).getSpeed();
        }
        avgHp = hp/i;
        avgSpeed = speed/i;
        avgDamage = damage/i;
    }
}

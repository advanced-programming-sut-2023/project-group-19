package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.Model.NewButton;
import java.awt.*;
import java.util.ArrayList;

public class TileManager extends Application {
    //TODO : Show Map ---> Armin's Method
    public ArrayList<String> cellArmyNameType = new ArrayList<>();
    public Text showCellData = new Text();
    public int avgDamage;
    public int avgSpeed;

    public TilePane view = new TilePane();

    public ArrayList<NewButton>[][] allButtons;
    public ArrayList<NewButton> selectedButtons;
    public Pane pane = new Pane();
    public int avgHp;
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private boolean drawIsOn;

    @Override
    public void start(Stage stage) throws Exception {
//        tilePane.setLayoutX(-100);
//        tilePane.setLayoutY(-100);
//        tilePane.setPrefColumns(100);
//        tilePane.setMaxWidth(10000);
        createButtonsArraylist();
        ArrayList<Node> list = new ArrayList<>();
        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < 100; i++) {
                NewButton newButton = new NewButton(j, i);
                setEventHandler(newButton);
                newButton.setPrefSize(51, 54);
                newButton.setFocusTraversable(false);
                list.add(newButton);
            }
        }

//         width  = 1530
//         height = 800

        for (int u = 0; u < 16; u++) {
            for (int g = 0; g < 30; g++) {
//                ((Button)list.get((u + 3) * 100 + (g + 10))).setBackground(new Background(new BackgroundImage(
//                        new Image("C:\\Users\\F1\\Desktop\\chert\\ProjectGroup19\\src\\main\\resources\\image\\cegla2.jpg") ,
//                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                NewButton button = (NewButton) list.get((u + 3) * 100 + (g + 10));
                button.setLayoutX(g * 51.2);
                button.setLayoutY(u * 54);
                pane.getChildren().add(list.get((u + 3) * 100 + (g + 10)));
                allButtons[u][g].add(button);
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
        Scene scene = new Scene(pane, width - 50, height - 50);

        stage.setTitle("Tile Pane");
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
    }

    public void setEventHandler(NewButton newButton) {
        selectedButtons = new ArrayList<>();

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
                stringBuilder.append("AVG Hp : " + avgHp + '\n' + "AVG Damage : " + avgDamage + '\n' +
                        "AVG Speed : " + avgSpeed + '\n');
                for (int i = 0; i < cellArmyNameType.size(); i++) {
                    stringBuilder.append(cellArmyNameType.get(i) + " ");
                }
                showCellData.setText(stringBuilder.toString());
                showCellData.setX(x);
                showCellData.setY(y);
                if (showCellData != null && !pane.getChildren().contains(showCellData))
                    pane.getChildren().add(showCellData);
            }
        };

        EventHandler<MouseEvent> event4 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
                    x1 = (int) (newButton.getX() % 51.2);
                    y1 = newButton.getY() % 54;
                    System.out.println("x1= "+x1+" y1= "+y1);
                }
            }
        };
        EventHandler<MouseEvent> event5 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
                    x2 = (int) (newButton.getX() % 51.2);
                    y2 = newButton.getY() % 54;
                    System.out.println("x2= "+x2+" y2= "+y2);
                    //drawRec(x1,y1,x2,y2,allButtons);
                }
            }
        };

        newButton.setOnMousePressed(event4);
        newButton.setOnMouseReleased(event5);
//        newButton.setOnMouseExited(event2);
//        newButton.setOnMouseMoved(event3);
    }

    private void drawRec(int x1, int y1, int x2, int y2, ArrayList<NewButton>[][] allButtons) {
        System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
        int maxX, minX, maxY, minY;
        if (x2 - x1 >= 0) {
            maxX = x2;
            minX = x1;
        } else {
            maxX = x1;
            minX = x2;
        }
        if (y2 - y1 >= 0) {
            maxY = y2;
            minY = y1;
        } else {
            maxY = y1;
            minY = y2;
        }
        for (int j = minY; j <= maxY; j++) {
            for (int i = minX; i <= maxX; i++) {
                NewButton newButton = allButtons[j][i].get(0);
                newButton.setStyle("-fx-background-color: brown");

            }
        }
    }

    public void getCellData(NewButton newButton) {
        cellArmyNameType.clear();
        int damage = 0;
        int hp = 0;
        int speed = 0;
        int i;
        for (i = 0; i < newButton.getArmy().size(); i++) {
            if (!cellArmyNameType.contains(newButton.getArmy().get(i).getNames().getName())) {
                cellArmyNameType.add(newButton.getArmy().get(i).getNames().getName());
            }
            damage += newButton.getArmy().get(i).getAttackPower();
            hp += newButton.getArmy().get(i).getHp();
            speed += newButton.getArmy().get(i).getSpeed();
        }
        if (i != 0) {
            avgHp = hp / i;
            avgSpeed = speed / i;
            avgDamage = damage / i;
        }
    }

    public void createButtonsArraylist() {
        allButtons = new ArrayList[16][30];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 30; j++) {
                allButtons[i][j] = new ArrayList<>();
            }
        }
    }
}

package view;

import controller.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Map;
import model.Obstacle.Stone;
import model.Obstacle.Tree;
import model.Obstacle.WaterSources;
import view.Model.NewRadioButton;

public class CreateMapMenu extends Application {
    public ToggleGroup toggleGroup = new ToggleGroup();
    public ToggleGroup mapToggleGroup = new ToggleGroup();
    public Pane pane ;
    public Stage stage ;

    public Map map  ;
    public RadioButton tree;
    public RadioButton water;

    public RadioButton stone;
    public TextField getX0 ;
    public TextField getX1 ;
    public TextField getY0 ;
    public TextField getY1 ;
    public static Map defaultMap ;
    public static Map finalMap ;
    public CreateMapController createMapController ;
    static {
        defaultMap = new Map();
        defaultMap.CreateMap(Map.mapSize);
        artOfTree(defaultMap);
        Map.getSavedMaps().add(defaultMap);
        finalMap = defaultMap;
    }
    @Override
    public void start(Stage stage) throws Exception {
        setSettings();
        Main.stage = stage;
        this.stage = stage ;
        Pane pane = new Pane();
        this.pane  = pane ;
        Scene scene = new Scene(pane,500,500);
        handleDesign();
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
    }

    private void setSettings() {
        map =  new Map();
        map.CreateMap(100);
        createMapController = new CreateMapController(map);
        Map.getSavedMaps().add(map);
    }

    private void handleDesign() {
        placeBackButton();
        VBox mainVbox = new VBox();
        mainVbox.setAlignment(Pos.CENTER);
        setSettingToMainVbox(mainVbox);
        handleGetCoordinate(mainVbox);
        handleCheckBox(mainVbox);
        placeSubmitButton(mainVbox);
        selectMap(mainVbox);
    }

    private void placeBackButton() {
        Button button = new Button();
        button.setText("back");
        pane.getChildren().add(button);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                saveChanges();
                MainMenu mainMenu = new MainMenu();
                try {
                    mainMenu.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private boolean checkSelectedToggle() {
        NewRadioButton rdbtn = (NewRadioButton)(mapToggleGroup.getSelectedToggle());
        return rdbtn.getMap().equals(defaultMap);

    }

    private void saveChanges() {
       NewRadioButton radioButton = (NewRadioButton) mapToggleGroup.getSelectedToggle();
        finalMap = radioButton.getMap();
    }

    private void placeSubmitButton(VBox vBox){
        Button submit = new Button("submit");
        submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(checkSelectedToggle()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("You can't edit default map!");
                    alert.showAndWait();
                    return;
                }
                if(toggleGroup.getSelectedToggle() == null) return;
                if(toggleGroup.getSelectedToggle().equals(tree)){
                    dropTree();
                }else if(toggleGroup.getSelectedToggle().equals(stone)){
                    dropStone();
                } else if(toggleGroup.getSelectedToggle().equals(water)){
                    dropWater();
                }
                recovery();
            }
        });
        vBox.getChildren().add(submit);
    }
    private void recovery(){
        getX0.setText("");
        getX1.setText("");
        getY0.setText("");
        getY1.setText("");
    }
    private void selectMap(VBox vBox){
        HBox hBox =  new HBox();
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);
        int counter = 0 ;
        for(Map map : Map.getSavedMaps()){
            counter ++;
            NewRadioButton radioButton = new NewRadioButton("map" + counter);
            radioButton.setToggleGroup(mapToggleGroup);
            if(counter == 1) radioButton.setSelected(true);
            radioButton.setMap(map);
            hBox.getChildren().add(radioButton);
        }
        vBox.getChildren().add(hBox);
    }

    private void dropWater() {
        int x0  =  Integer.parseInt(getX0.getText());
        int x1  =  Integer.parseInt(getX1.getText());
        int y0  =  Integer.parseInt(getY0.getText());
        int y1  =  Integer.parseInt(getY1.getText());
        createMapController.dropSeveralSea(x0,x1,y0,y1);
    }

    private void dropStone() {
        int x0  =  Integer.parseInt(getX0.getText());
        int x1  =  Integer.parseInt(getX1.getText());
        int y0  =  Integer.parseInt(getY0.getText());
        int y1  =  Integer.parseInt(getY1.getText());
        createMapController.dropSeveralStone(x0,x1,y0,y1);
    }

    private void dropTree(){
        int x0  =  Integer.parseInt(getX0.getText());
        int x1  =  Integer.parseInt(getX1.getText());
        int y0  =  Integer.parseInt(getY0.getText());
        int y1  =  Integer.parseInt(getY1.getText());
        createMapController.dropSeveralTrees(x0,x1,y0,y1);
    }
    private void handleGetCoordinate(VBox vBox) {
        placeTextFields(vBox);
    }
    private void placeTextFields(VBox vBox){
        getX0 = new TextField();
        getX0.setPromptText("x start");
        getX1 = new TextField();
        getX1.setPromptText("x end");
        getY0 = new TextField();
        getY0.setPromptText("y start");
        getY1 = new TextField();
        getY1.setPromptText("y end");
        HBox hBox1 = new HBox(getX0,getX1);
        HBox hBox2 = new HBox(getY0,getY1);
        hBox1.setMaxWidth(200);
        hBox1.setSpacing(20);
        hBox2.setMaxWidth(200);
        hBox2.setSpacing(20);
        vBox.getChildren().addAll(hBox1,hBox2);
    }
    private void setSettingToMainVbox(VBox vBox){
        vBox.setSpacing(20);
        vBox.setLayoutX(650);
        vBox.setLayoutY(300);
        vBox.setAlignment(Pos.CENTER);
        pane.getChildren().add(vBox);
    }
    private void handleCheckBox(VBox vBox){
        HBox hBox = new HBox();
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);
        tree = new RadioButton("tree");
        tree.setToggleGroup(toggleGroup);
        water = new RadioButton("water");
        water.setToggleGroup(toggleGroup);
        stone = new RadioButton("stone");
        stone.setToggleGroup(toggleGroup);
        hBox.getChildren().addAll(tree,water,stone);
        vBox.getChildren().add(hBox);
    }

    private static void dropTreeToLocation(int x, int y1, int y2, int number,Map map) {
        for (int i = y1; i <= y2; i++) {
            dropTree(x, i, number,map);
        }
    }

    private static void dropSeaoLocation(int x, int y1, int y2, int number,Map map) {
        for (int i = y1; i <= y2; i++) {
            dropSea(x, i, number,map);
        }
    }

    private static void dropStoneLocation(int x, int y1, int y2, int number,Map map) {
        for (int i = y1; i <= y2; i++) {
            dropStone(x, i, number,map);
        }
    }
    private static void artOfTree(Map map) {
        dropTreeToLocation(0, 0, 12, 5,map);
        dropTreeToLocation(0, 3, 8, 1,map);
        dropTreeToLocation(1, 0, 10, 5,map);
        dropTreeToLocation(2, 1, 8, 5,map);
        dropTreeToLocation(2, 0, 4, 1,map);
        dropTreeToLocation(3, 6, 7, 2,map);
        dropTreeToLocation(4, 0, 1, 2,map);
        dropTreeToLocation(4, 3, 4, 2,map);
        dropTreeToLocation(4, 6, 6, 1,map);
        dropStoneLocation(3, 8, 8, 8,map);
        dropStoneLocation(4, 7, 7, 8,map);
        dropSea(3, 2, 1,map);
        dropSea(3, 5, 1,map);
        dropSea(4, 2, 1,map);
        dropSea(4, 5, 1,map);
        dropSea(3, 1, 1,map);
        dropSea(4, 5, 1,map);
        dropSea(3, 1, 1,map);
        dropSea(3, 4, 1,map);
        dropSea(3, 3, 1,map);
        dropTree(3, 0, 2,map);
        dropTree(4, 0, 2,map);
        dropTree(2, 0, 2,map);
        dropTreeToLocation(5, 0, 6, 5,map);
        dropTreeToLocation(6, 1, 3, 5,map);
        dropTreeToLocation(7, 0, 2, 5,map);
        dropStoneLocation(0, 13, 17, 8,map);
        dropStoneLocation(1, 18, 19, 6,map);
        dropStoneLocation(2, 9, 9, 6,map);
        dropStoneLocation(5, 7, 7, 8,map);
        dropStoneLocation(6, 4, 5, 8,map);
        dropStoneLocation(7, 3, 4, 8,map);
        dropTreeToLocation(8, 0, 2, 1,map);
        dropTreeToLocation(9, 1, 2, 5,map);
        dropTreeToLocation(10, 0, 1, 1,map);
        dropSeaoLocation(11, 0, 3, 1,map);
        dropStoneLocation(11, 4, 4, 6,map);
        dropStoneLocation(12, 0, 5, 3,map);
        dropTreeToLocation(0, 18, 29, 1,map);
        dropTreeToLocation(1, 20, 29, 1,map);
        dropTreeToLocation(1, 22, 26, 5,map);
        dropTreeToLocation(2, 19, 29, 1,map);
        dropTreeToLocation(2, 21, 25, 5,map);
        dropSeaoLocation(3, 27, 29, 1,map);
        dropSeaoLocation(5, 24, 25, 1,map);
        dropSeaoLocation(4, 27, 29, 1,map);
        dropSeaoLocation(5, 27, 29, 1,map);
        dropTreeToLocation(3, 21, 26, 1,map);
        dropTreeToLocation(4, 23, 26, 5,map);
        dropTree(5, 26, 1,map);
        dropTreeToLocation(6, 23, 26, 5,map);
        dropTreeToLocation(7, 21, 29, 1,map);
        dropTreeToLocation(8, 27, 29, 5,map);
        dropStoneLocation(9, 21, 28, 3,map);
        dropStoneLocation(10, 19, 21, 1,map);
        dropStoneLocation(11, 18, 20, 2,map);
        dropTreeToLocation(10, 22, 29, 5,map);
        dropTreeToLocation(11, 21, 29, 5,map);
    }
    private static void dropTree(int x, int y, int number,Map map) {
        Tree tree = new Tree();
        map.getObstacleMap()[x][y].add(tree);
        map.notBuildable[x][y] = true;
    }
    private static void dropSea(int x, int y, int number,Map map) {
        WaterSources waterSources = new WaterSources();
        map.getObstacleMap()[x][y].add(waterSources);
        map.notBuildable[x][y] = true;
    }


    private static void dropStone(int x, int y, int number,Map map) {
        Stone stone = new Stone();
        map.getObstacleMap()[x][y].add(stone);
        map.notBuildable[x][y] = true;
    }
}


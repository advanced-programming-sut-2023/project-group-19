package view;

import controller.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Map;
import model.User;
import view.Commands.CreateMapCommands;
import view.Commands.MainMenuCommands;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateMapMenu extends Application {
    public ToggleGroup toggleGroup = new ToggleGroup();
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
    public CreateMapController createMapController ;
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
        createMapController = new CreateMapController(map);
    }

    private void handleDesign() {
        placeBackButton();
        VBox mainVbox = new VBox();
        mainVbox.setAlignment(Pos.CENTER);
        setSettingToMainVbox(mainVbox);
        handleGetCoordinate(mainVbox);
        handleCheckBox(mainVbox);
        placeSubmitButton(mainVbox);
    }

    private void placeBackButton() {
        Button button = new Button();
        button.setText("back");
        pane.getChildren().add(button);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MainMenu mainMenu = new MainMenu();
                try {
                    mainMenu.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void placeSubmitButton(VBox vBox){
        Button submit = new Button("submit");
        submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(toggleGroup.getSelectedToggle().equals(tree)){
                    dropTree();
                }
            }
        });
        vBox.getChildren().add(submit);
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
        getY0.setPromptText("x start");
        getY1 = new TextField();
        getY1.setPromptText("x end");
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
}


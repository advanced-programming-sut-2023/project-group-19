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
    @Override
    public void start(Stage stage) throws Exception {
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

    private void handleDesign() {
        placeBackButton();
        VBox mainVbox = new VBox();
        mainVbox.setAlignment(Pos.CENTER);
        setSettingToMainVbox(mainVbox);
        handleGetCoordinate(mainVbox);
        handleCheckBox(mainVbox,"tree","water","stone");
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
        vBox.getChildren().add(submit);
    }
    private void handleGetCoordinate(VBox vBox) {
        placeTextFields(vBox,"x0","x1");
        placeTextFields(vBox,"y0","y1");
    }
    private void placeTextFields(VBox vBox,String promptText1, String promptText2){
        TextField getT0 = new TextField();
        TextField getT1 = new TextField();
        getT0.setPromptText(promptText1);
        getT1.setPromptText(promptText2);
        HBox hBox = new HBox(getT0,getT1);
        hBox.setMaxWidth(200);
        hBox.setSpacing(20);
        vBox.getChildren().add(hBox);
    }

    private void setSettingToMainVbox(VBox vBox){
        vBox.setSpacing(20);
        vBox.setLayoutX(650);
        vBox.setLayoutY(300);
        vBox.setAlignment(Pos.CENTER);
        pane.getChildren().add(vBox);
    }
    private void handleCheckBox(VBox vBox,String h1,String h2,String h3){
        HBox hBox = new HBox();
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);
        RadioButton check1 = new RadioButton(h1);
        check1.setToggleGroup(toggleGroup);
        RadioButton check2 = new RadioButton(h2);
        check2.setToggleGroup(toggleGroup);
        RadioButton check3 = new RadioButton(h3);
        check3.setToggleGroup(toggleGroup);
        hBox.getChildren().addAll(check1,check2,check3);
        vBox.getChildren().add(hBox);
    }
}


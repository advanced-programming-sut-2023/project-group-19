package view;

import controller.JsonController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;
import controller.LoginController;
import view.Commands.LoginAndRegisterCommands;
import view.Messages.RegisterMessages;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu extends Application {
    public TextField email;
    public TextField nickname;
    public TextField slogan;
    public CheckBox check;
    public TextField passwordShow;
    public PasswordField passwordHide;
    //    public CheckBox check;
    @FXML
    private TextField username;
    private Pane pane;

    private Label label;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        URL url = LoginMenu.class.getResource("/fxml/loginMenu.fxml");
        Pane pane = FXMLLoader.load(url);
        this.pane = pane ;
        callFunctions(); //call functions
        Scene scene = new Scene(pane);
        //
        //
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();

    }
    public void showAndHidePassword(){
        if(passwordHide.isVisible()){
            passwordShow.setVisible(true);
            System.out.println("visible");
            passwordHide.setVisible(false);
        }else{
            System.out.println("visible");
            passwordShow.setVisible(false);
            passwordHide.setVisible(true);
        }
    }
    public void callFunctions(){
    }

    @FXML
    public void initialize() {
        username.textProperty().addListener((observable, oldText, newText) -> {
            System.out.println(newText);
        });
//        password.textProperty().addListener((observable, oldText, newText) -> {
//            System.out.println("password");
//        });
    }

    public void checkingSlogan(MouseEvent mouseEvent) {
        slogan.setVisible(!slogan.isVisible());
    }
}
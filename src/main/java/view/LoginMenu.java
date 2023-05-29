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
    public Button randomSloganButton;
    //    public CheckBox check;
    @FXML
    private TextField username;
    private Pane pane;

    @FXML
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
        String text ;
        if(passwordHide.isVisible()){
            passwordShow.setVisible(true);
            text = passwordHide.getText();
            passwordShow.setText(text);
            passwordHide.setVisible(false);
        }else{
            text = passwordShow.getText();
            passwordShow.setVisible(false);
            passwordHide.setVisible(true);
            passwordHide.setText(text);
        }
    }
    public void callFunctions(){
    }

    @FXML
    public void initialize() {
        ListenerToUsernameField();
        ListenerToPassword();
    }
    private void checkPasswordError(String password){
        RegisterMessages messages =  LoginController.checkPassword(password);
        String text = null;
        switch (messages){
            case WEAK_PASSWORD_FOR_LOWERCASE :
                text  =  "You should use lowercase characters in uor password!";
                break;
            case WEAK_PASSWORD_FOR_UPPERCASE  :
                text  =  "You should use uppercase characters in uor password!";
                break;
            case WEAK_PASSWORD_FOR_LENGTH:
                text = "Length of your password must be more than five!";
                break;
            case WEAK_PASSWORD_FOR_NUMBER:
                text  =  "You should use number characters in uor password!";
                break;
            case WEAK_PASSWORD_FOR_NOTHING_CHARS_EXCEPT_ALPHABETICAL:
                text = "You should use characters except alphabetical!";
                break;
            case SUCCESS:
                text  = "Fill register form";
                break;
        }
        label.setText(text);
    }
    private void ListenerToPassword(){
        passwordShow.textProperty().addListener((observable, oldText, newText) -> {
            checkPasswordError(newText);
        });
        passwordHide.textProperty().addListener((observable, oldText, newText) -> {
            checkPasswordError(newText);
        });
        
    }
    private void ListenerToUsernameField(){
        username.textProperty().addListener((observable, oldText, newText) -> {
            RegisterMessages messages = LoginController.checkUsername(newText);
            switch (messages){
                case USERNAME_REPEATED :
                    label.setText("Your username is repeated but username " +
                            LoginController.makeUserNameForUser(newText) +
                            " is exist now!");
                    break;
                case INCORRECT_FORM_OF_USERNAME:
                    label.setText("Invalid form of username!");
                    break;
                case SUCCESS:
                    label.setText("Fill register form");
            }
        });
    }

    public void checkingSlogan(MouseEvent mouseEvent) {
        slogan.setVisible(!slogan.isVisible());
        randomSloganButton.setVisible(!randomSloganButton.isVisible());
    }

    public void randomPassword(MouseEvent mouseEvent) {
        if(passwordHide.isVisible()){
            passwordHide.setText(LoginController.generateRandomPassword());
        }else{
            passwordShow.setText(LoginController.generateRandomPassword());
        }
    }

    public void randomSlogan(MouseEvent mouseEvent) {
        slogan.setText(LoginController.getRandomSlogan());
    }
}
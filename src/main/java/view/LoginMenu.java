package view;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import view.Messages.RegisterMessages;

import java.net.URL;

public class LoginMenu extends Application {
    public Pane pane ;
    public Label usernameError;
    public Label passwordError;
    public TextField username;
    public PasswordField password;
    public VBox vBoxErrorHandling;
    public TextField answer;
    public Label secQuestionLabel;
    public VBox secQestionVbox;
    public ImageView captchaImage;
    public String captchaNumber ;
    public TextField captchaAnswer;
    public static Stage stage ;

    @Override
    public void start(Stage stage) throws Exception {
        LoginMenu.stage = stage ;
        User user = new User("armin","123","jeneral","email.com","mamad","khas",2);
        URL url = RegisterMenu.class.getResource("/fxml/loginMenu.fxml");
        Pane pane = FXMLLoader.load(url);
        this.pane = pane;
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
    public void initialize(){
        captchaNumber = LoginController.setImageCaptcha(captchaImage);
    }

    public void forgotPassword(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        if(username.getText().equals("")){
            alert.setContentText("At first fill username field!");
            alert.showAndWait();
        }else{
            if(LoginController.checkExitUser(username.getText()).equals(RegisterMessages.NOT_EXIST_USER)){
                alert.setContentText("this user is not exist!");
                alert.showAndWait();
            }else{
                prepareSerQustion();
            }
        }
    }

    private void prepareSerQustion() {
        secQuestionLabel.setText(LoginController.getSecQuestion(username.getText()));
        secQestionVbox.setVisible(true);
    }

    public void closePopup(MouseEvent mouseEvent) {
        String password ;
        if((password = LoginController.checkAnswerTrue(username.getText(),answer.getText())) != null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Recovery password");
            alert.setContentText("Your password is: " + password);
            alert.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Recovery password");
            alert.setContentText("Your answer is incorrect");
            alert.show();
        }
        secQestionVbox.setVisible(false);
    }

    public void anotherCaptcha(MouseEvent mouseEvent) {
        captchaNumber = LoginController.setImageCaptcha(captchaImage);
    }

    public void submitAndLogin(MouseEvent mouseEvent) throws Exception {
        if(username.getText().equals("")) usernameError.setText("Empty field!");
        if(password.getText().equals(""))passwordError.setText("Empty field!");
        if(!captchaAnswer.getText().equals(captchaNumber)){
            captchaNumber = LoginController.setImageCaptcha(captchaImage);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Captcha is wrong!");
            alert.showAndWait();
        }
        else if(LoginController.LoginUser(username.getText(),password.getText())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Login successfully!");
            alert.showAndWait();
            MainMenu mainMenu = new MainMenu();
            mainMenu.start(stage);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("username and password did not match!");
            alert.showAndWait();
        }
        captchaNumber = LoginController.setImageCaptcha(captchaImage);
    }
}

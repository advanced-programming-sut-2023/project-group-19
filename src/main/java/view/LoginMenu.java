package view;

import controller.LoginController;
import controller.SendInformationToMasterServer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Manage;
import model.User;
import view.ImageAndBackground.GameImages;
import view.Messages.RegisterMessages;

import java.io.CharArrayReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.Optional;

public class LoginMenu extends Application {
    //TODO : ArrayList of users have to change when a user is register !
    public Pane pane;
    public TextField username = new TextField();
    public PasswordField password = new PasswordField();
    public String securityQuestionAnswer;
    public ImageView captchaImage = new ImageView();
    public String captchaNumber;
    public TextField captchaAnswer = new TextField();
    public static Stage stage;

    public static DataOutputStream dataOutputStream ;

    @Override
    public void start(Stage stage) throws Exception {
        User.makeUsersFromJson();
        LoginMenu.stage = stage;
        User user = new User("armin", "123", "jeneral", "email.com", "mamad", "khas", 2);
        Pane pane = new Pane();
        this.pane = pane;
        GameImages gameImages = new GameImages();
        gameImages.loadImages();
        pane.setBackground(gameImages.getLoginBackground());
        designLoginMenu();
        backToRegisterMenu();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    private void designLoginMenu() {
        username.setPromptText("Enter username");
        username.setPrefSize(300, 35);
        username.setLayoutX(650);
        username.setLayoutY(100);

        password.setPromptText("Enter password");
        password.setPrefSize(300, 35);
        password.setLayoutX(650);
        password.setLayoutY(175);

        Button forgot = new Button("Forgot");
        forgot.setStyle("-fx-background-color: #cba883");
        forgot.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        forgot.setLayoutX(740);
        forgot.setLayoutY(240);
        forgot.setPrefSize(100, 40);
        forgot.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                forgotPassword(mouseEvent);
            }
        });

        Button again = new Button("Again");
        again.setStyle("-fx-background-color: #cba883");
        again.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        again.setLayoutX(860);
        again.setLayoutY(320);
        again.setPrefSize(80, 40);
        again.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                anotherCaptcha(mouseEvent);
            }
        });

        captchaImage = new ImageView();
        captchaNumber = LoginController.setImageCaptcha(captchaImage);
        captchaImage.setTranslateX(650);
        captchaImage.setTranslateY(320);

        captchaAnswer.setPromptText("Enter captcha");
        captchaAnswer.setPrefSize(300, 35);
        captchaAnswer.setLayoutX(650);
        captchaAnswer.setLayoutY(425);

        Button submit = new Button("Submit");
        submit.setStyle("-fx-background-color: #cba883");
        submit.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        submit.setLayoutX(740);
        submit.setLayoutY(480);
        submit.setPrefSize(100, 40);
        submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    submitAndLogin(mouseEvent);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        pane.getChildren().add(username);
        pane.getChildren().add(password);
        pane.getChildren().add(forgot);
        pane.getChildren().add(again);
        pane.getChildren().add(captchaImage);
        pane.getChildren().add(captchaAnswer);
        pane.getChildren().add(submit);

    }

    public void backToRegisterMenu() {  //----> back button
        Button back = new Button("Back");
        back.setStyle("-fx-background-color: #cba883");
        back.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        back.setLayoutY(20);
        back.setLayoutX(20);
        back.setPrefSize(100, 40);
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                RegisterMenu registerMenu = new RegisterMenu();
                try {
                    registerMenu.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        pane.getChildren().add(back);
    }
    public void forgotPassword(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("LoginMenu Error!");
        alert.setHeaderText("Error in Forgot Password Operation!");
        if (username.getText().equals("")) {
            alert.setContentText("You should fill the username field first!");
            alert.showAndWait();
        } else {
            if (LoginController.checkExitUser(username.getText()).equals(RegisterMessages.NOT_EXIST_USER)) {
                alert.setContentText("This user doesn't exist!");
                alert.showAndWait();
            } else {
                prepareSecQuestion();
            }
        }
    }

    private void prepareSecQuestion() {
        TextInputDialog securityQuestion = new TextInputDialog();
        securityQuestion.setTitle("Security Question");
        securityQuestion.setHeaderText(LoginController.getSecQuestion(username.getText()));
        Optional<String> result = securityQuestion.showAndWait();
        if (result.isPresent()) {
            securityQuestionAnswer = result.get();
            closePopup();
        }
    }

    public void closePopup() {
        String password;
        System.out.println(securityQuestionAnswer);
        if ((password = LoginController.checkAnswerTrue(username.getText(), securityQuestionAnswer)) != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Recovery password");
            alert.setContentText("Your password is: " + password);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Recovery password");
            alert.setContentText("Your answer is incorrect!");
            alert.showAndWait();
        }
    }

    public void anotherCaptcha(MouseEvent mouseEvent) {
        captchaNumber = LoginController.setImageCaptcha(captchaImage);
    }

    public void submitAndLogin(MouseEvent mouseEvent) throws Exception {
        if (username.getText().equals("")) {
            Alert usernameAlert = new Alert(Alert.AlertType.ERROR);
            usernameAlert.setTitle("LoginMenu Error!");
            usernameAlert.setHeaderText("Error in filling Username field!");
            usernameAlert.setContentText("Username field is Empty!");
            usernameAlert.show();
        } else if (password.getText().equals("")) {
            Alert passwordAlert = new Alert(Alert.AlertType.ERROR);
            passwordAlert.setTitle("LoginMenu Error!");
            passwordAlert.setHeaderText("Error in filling Password field!");
            passwordAlert.setContentText("Password field is Empty!");
            passwordAlert.show();
        } else if (!captchaAnswer.getText().equals(captchaNumber)) {
            captchaNumber = LoginController.setImageCaptcha(captchaImage);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("LoginMenu Error!");
            alert.setHeaderText("Error in filling Captcha!");
            alert.setContentText("Captcha field didn't fill correctly!");
            alert.showAndWait();
        } else if (LoginController.LoginUser(username.getText(), password.getText())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("LoginMenu Information");
            alert.setHeaderText("Success!");
            User user =  User.getUserByName(username.getText());
            User.setCurrentUser(user);
            SendInformationToMasterServer.sendCurrentUser(user);
            alert.setContentText("Login successfully!");
            alert.showAndWait();
            //connect user to masterServer
            //
            MainMenu mainMenu = new MainMenu();
            mainMenu.start(stage);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("LoginMenu Error!");
            alert.setHeaderText("Error in logging in!");
            alert.setContentText("The username and password did not match!");
            alert.showAndWait();
        }
        captchaNumber = LoginController.setImageCaptcha(captchaImage);
    }
}

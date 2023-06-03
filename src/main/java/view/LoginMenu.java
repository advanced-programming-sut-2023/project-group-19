package view;

import controller.LoginController;
import javafx.application.Application;
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
import model.User;
import view.ImageAndBackground.GameImages;
import view.Messages.RegisterMessages;

import java.io.CharArrayReader;
import java.net.URL;

public class LoginMenu extends Application {
    public Pane pane;
    public Label usernameError = new Label();
    public Label passwordError = new Label();
    public TextField username = new TextField();
    public PasswordField password = new PasswordField();
    public VBox vBoxErrorHandling = new VBox();
    public TextField answer = new TextField();
    public Label secQuestionLabel = new Label();
    public VBox secQestionVbox = new VBox();
    public ImageView captchaImage = new ImageView();
    public String captchaNumber;
    public TextField captchaAnswer = new TextField();
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        LoginMenu.stage = stage;
        User user = new User("armin", "123", "jeneral", "email.com", "mamad", "khas", 2);
        URL url = RegisterMenu.class.getResource("/fxml/loginMenu.fxml");
        Pane pane = new Pane();
        this.pane = pane;
        GameImages gameImages = new GameImages();
        gameImages.loadImages();
        pane.setBackground(gameImages.getBackground());
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
        again.setLayoutY(350);
        again.setPrefSize(80, 40);
        again.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                anotherCaptcha(mouseEvent);
            }
        });

//        captchaImage.setTranslateX(-50);
//        captchaImage.setTranslateY(-50);

        pane.getChildren().add(username);
        pane.getChildren().add(password);
        pane.getChildren().add(forgot);
        pane.getChildren().add(again);
        pane.getChildren().add(captchaImage);
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

    public void initialize() {
        captchaNumber = LoginController.setImageCaptcha(captchaImage);
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
        secQuestionLabel.setText(LoginController.getSecQuestion(username.getText()));
        secQestionVbox.setVisible(true);
    }

    public void closePopup(MouseEvent mouseEvent) {
        String password;
        if ((password = LoginController.checkAnswerTrue(username.getText(), answer.getText())) != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Recovery password");
            alert.setContentText("Your password is: " + password);
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Recovery password");
            alert.setContentText("Your answer is incorrect!");
            alert.show();
        }
        secQestionVbox.setVisible(false);
    }

    public void anotherCaptcha(MouseEvent mouseEvent) {
        captchaNumber = LoginController.setImageCaptcha(captchaImage);
    }

    public void submitAndLogin(MouseEvent mouseEvent) throws Exception {
        if (username.getText().equals("")) usernameError.setText("Empty field!");
        if (password.getText().equals("")) passwordError.setText("Empty field!");
        if (!captchaAnswer.getText().equals(captchaNumber)) {
            captchaNumber = LoginController.setImageCaptcha(captchaImage);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("LoginMenu Error!");
            alert.setHeaderText("Error in filling Captcha!");
            alert.setContentText("Captcha is wrong!");
            alert.showAndWait();
        } else if (LoginController.LoginUser(username.getText(), password.getText())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("LoginMenu Information");
            alert.setHeaderText("Success!");
            alert.setContentText("Login successfully!");
            alert.showAndWait();
            Main mainMenu = new Main();
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

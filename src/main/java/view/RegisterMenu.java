package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Popup;
import javafx.scene.media.Media;
import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import controller.LoginController;
import javafx.util.Duration;
import view.Messages.RegisterMessages;

import java.awt.*;
import java.io.File;
import java.net.URL;

public class RegisterMenu extends Application {
    public static Stage stage ;
    public TextField email;
    public TextField nickname;
    public TextField slogan;
    public CheckBox check;
    public TextField passwordShow;
    public PasswordField passwordHide;
    public Button randomSloganButton;

    //Labels error handeleng
    public Label usernameError;
    public Label passwordError;
    public Label emailError;
    public Label nicknameError;
    public Label sloganError;
    public VBox vBoxErrorHandling;
    public RadioButton Q1;
    public RadioButton Q2;
    public RadioButton Q3;
    public TextField answer;
    public VBox secQestionVbox;
    public VBox captchaBox;
    public ImageView captchaImage;
    public TextField answerOfCaptcha;
    public Button famousSlogans;
    public VBox famousDialogue;
    public Label loginLabel;
    //    public CheckBox check;
    @FXML
    private TextField username;
    private Pane pane;

    @FXML
    private Label label;
    public ToggleGroup toggleGroup ;
    public String captchaNumber ;
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        RegisterMenu.stage = stage  ;

        String path = RegisterMenu.class.getResource("/Intro.mp4").toExternalForm();
        Media media = new Media(path);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
        mediaView.setFitWidth(1550);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(36.5), actionEvent -> {
            pane.getChildren().remove(mediaView);
//            playLoginMusic();
        }));
        timeline.play();


        URL url = RegisterMenu.class.getResource("/fxml/registerMenu.fxml");
        Pane pane = FXMLLoader.load(url);
        this.pane = pane;
        pane.getChildren().add(mediaView);
        Scene scene = new Scene(pane);
        Image image = new Image(RegisterMenu.class.getResource("/sowrd.png").toExternalForm());
        scene.setCursor(new ImageCursor(image));
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();

    }
    private void playLoginMusic(){
        String defultSong  = getClass().getResource("/Music/register.mp3").toString();
        Media media = new Media(defultSong);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(-1);
        mediaPlayer.setAutoPlay(true);
    }
//    public VBox createVboxAnsStructure(Popup popup){
//
////        root.setBackground(new Color(red));
//        return root ;
//    }

    public void addPopup(){
        Popup popup = new Popup();
        Button button = new Button();
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!popup.isShowing())
                    popup.show(stage);
                else
                    popup.hide();
            }
        });
        pane.getChildren().add(button);
//        popup.getContent().add(createVboxAnsStructure(popup));
        popup.show(stage);
    }

    public void showAndHidePassword() {
        String text;
        if (passwordHide.isVisible()) {
            passwordShow.setVisible(true);
            text = passwordHide.getText();
            passwordShow.setText(text);
            passwordHide.setVisible(false);
        } else {
            text = passwordShow.getText();
            passwordShow.setVisible(false);
            passwordHide.setVisible(true);
            passwordHide.setText(text);
        }
    }

    @FXML
    public void initialize() {
        ListenerToUsernameField();
        ListenerToPassword();
    }

    private void checkPasswordError(Label label,String textSucess) {
        String password ;
        if(passwordHide.isVisible()) password = passwordHide.getText();
        else password = passwordShow.getText();
        RegisterMessages messages = LoginController.checkPassword(password);
        String text = null;
        switch (messages) {
            case EMPTY_FIELD:
                text = " You have empty Field";
                break;
            case WEAK_PASSWORD_FOR_LOWERCASE:
                text = "You should use lowercase characters in your password!";
                break;
            case WEAK_PASSWORD_FOR_UPPERCASE:
                text = "You should use uppercase characters in your password!";
                break;
            case WEAK_PASSWORD_FOR_LENGTH:
                text = "Length of your password must be more than five!";
                break;
            case WEAK_PASSWORD_FOR_NUMBER:
                text = "You should use number characters in your password!";
                break;
            case WEAK_PASSWORD_FOR_NOTHING_CHARS_EXCEPT_ALPHABETICAL:
                text = "You should use characters except alphabetical!";
                break;
            case SUCCESS:
                text = textSucess;
                break;
        }
        label.setText(text);
    }

    private void ListenerToPassword() {
        passwordShow.textProperty().addListener((observable, oldText, newText) -> {
            checkPasswordError(label,"Fill register form");
        });
        passwordHide.textProperty().addListener((observable, oldText, newText) -> {
            checkPasswordError(label,"Fill register form");
        });

    }

    private void ListenerToUsernameField() {
        username.textProperty().addListener((observable, oldText, newText) -> {
            usernameError(label, "Fill register form");
        });
    }

    public void checkingSlogan(MouseEvent mouseEvent) {
        if (slogan.isVisible()) slogan.setText("empty");
        else slogan.setText("");
        slogan.setVisible(!slogan.isVisible());
        randomSloganButton.setVisible(!randomSloganButton.isVisible());
        famousSlogans.setVisible(!famousSlogans.isVisible());
    }

    public void randomPassword(MouseEvent mouseEvent) {
        if (passwordHide.isVisible()) {
            passwordHide.setText(LoginController.generateRandomPassword());
        } else {
            passwordShow.setText(LoginController.generateRandomPassword());
        }
    }

    public void randomSlogan(MouseEvent mouseEvent) {
        slogan.setText(LoginController.getRandomSlogan());
    }

    public void sloganCheck() {
        if (slogan.getText().equals("")) sloganError.setText("Empty field");
        else sloganError.setText("");
    }
    public void submit(MouseEvent mouseEvent) throws Exception {
        stepOneRegister(false);
    }
    public void stepOneRegister(boolean isEnd) throws Exception {
        usernameError(usernameError,"");
        checkPasswordError(passwordError,"");
        checkEmail();
        nicknameError();
        sloganCheck();
        if(!isEnd) vBoxErrorHandling.setVisible(true);
        checkRegisterSucess(isEnd);
    }

    private void checkRegisterSucess(boolean isEnd) throws Exception {
        if(usernameError.getText().equals("") &&
            passwordError.getText().equals("") &&
                emailError.getText().equals("") &&
                sloganError.getText().equals("") ) {
            if(!isEnd) askQuestionShow();
            else{
                submitUser();
            }
        }

    }

    private void submitUser() throws Exception {
        int number ;
        if(toggleGroup.getSelectedToggle().equals(Q1)) number = 1 ;
        else if(toggleGroup.getSelectedToggle().equals(Q2)) number = 2 ;
        else number = 3 ;
        String password ;
        if(passwordHide.isVisible()) password = passwordHide.getText();
        else password = passwordShow.getText();
        LoginController.register(username.getText(),password,nickname.getText(),
                email.getText(),answer.getText(),slogan.getText(),"" + number);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Register");
        alert.setContentText("Successfully Registered");
        alert.showAndWait();
        RegisterMenu login = new RegisterMenu();
        login.start(stage);
    }

    private void askQuestionShow() {
        toggleGroup = new ToggleGroup();
        Q1.setToggleGroup(toggleGroup);
        Q2.setToggleGroup(toggleGroup);
        Q3.setToggleGroup(toggleGroup);
        secQestionVbox.setVisible(true);
    }

    public void usernameError(Label label,String textSuccess){
        String newText =  username.getText();
        RegisterMessages messages = LoginController.checkUsername(newText);
        switch (messages){
            case EMPTY_FIELD:
                label.setText("You have empty field");
                break;
            case USERNAME_REPEATED :
                label.setText("Your username is repeated but username " +
                        LoginController.makeUserNameForUser(newText) +
                        " is exist now!");
                break;
            case INCORRECT_FORM_OF_USERNAME:
                label.setText("Invalid form of username!");
                break;
            case SUCCESS:
                label.setText(textSuccess);
        }
    }
    public void nicknameError(){
        String newText = nickname.getText();
        if(newText.equals("")) nicknameError.setText("Empty field");
        else nicknameError.setText("");
    }
    //Dd3Rr5Tt8#
    //Vv8Ww3Cc5#
    public void checkEmail(){
        String newText =  email.getText();
        RegisterMessages messages = LoginController.checkEmail(newText);
        switch (messages){
            case EMPTY_FIELD :
                emailError.setText("You have empty field");
                break;
            case REPEATED_EMAIL:
                emailError.setText("Your email is repeated");
                break;
            case INVALID_FORM_EMAIL:
                emailError.setText("Your form if email is invalid!");
                break;
            case SUCCESS:
                emailError.setText("");
                break;
        }
    }
    public void closePopup(MouseEvent mouseEvent) {
        if(!answer.getText().equals("") && toggleGroup.getSelectedToggle() != null) {
            secQestionVbox.setVisible(false);
            captchaNumber = LoginController.setImageCaptcha(captchaImage);
            captchaBox.setVisible(true);
        }
    }
    public void anotherCaptcha(MouseEvent mouseEvent) {
        captchaNumber = LoginController.setImageCaptcha(captchaImage);
    }

    public void submitWholeRegister(MouseEvent mouseEvent) throws Exception {
        if(answerOfCaptcha.getText().equals(captchaNumber)) stepOneRegister(true);
        else {
            captchaNumber = LoginController.setImageCaptcha(captchaImage);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Your input is wrong");
            alert.show();
        }
    }

    public void showFamousSlogan(MouseEvent mouseEvent) {
        famousDialogue.setVisible(!famousDialogue.isVisible());

    }

    public void enterLoginMenu(MouseEvent mouseEvent) throws Exception {
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.start(stage);
    }

}
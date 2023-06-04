package view;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafx.stage.Stage;
import controller.LoginController;
import model.Empire;
import model.Manage;
import view.ImageAndBackground.GameImages;
import view.Messages.RegisterMessages;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegisterMenu extends Application {
    public static Stage stage;
    public TextField email = new TextField();

    public TextField nickname = new TextField();

    public TextField slogan = new TextField();
    public CheckBox check;
    public CheckBox passwordVisibility = new CheckBox();
    public TextField passwordShow = new TextField();
    public PasswordField passwordHide = new PasswordField();
    public Button showSlogan = new Button("Slogan Option");
    public Button randomSloganButton = new Button("Random");
    public Button randomPassword = new Button("Random");

    public ArrayList<String> listOfFamousSlogans = new ArrayList<>();

    ChoiceBox<String> famousDialogue = new ChoiceBox<>();

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
    public Button famousSlogans = new Button("Famous Slogans");
    public Label loginLabel;
    //    public CheckBox check;
    private TextField username = new TextField();
    private Pane pane;

    private Label headerLabel = new Label();
    public ToggleGroup toggleGroup;
    public String captchaNumber;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        RegisterMenu.stage = stage;
//        URL url = RegisterMenu.class.getResource("/fxml/registerMenu.fxml");
        Pane pane = new Pane();
        GameImages gameImages = new GameImages();
        gameImages.loadImages();
        pane.setBackground(gameImages.getRegisterBackground());
        this.pane = pane;
        designRegisterMenu();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    private void designRegisterMenu() {
        headerLabel.setText("Complete the registration form");
        headerLabel.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 26));
        headerLabel.setTranslateX(610);
        headerLabel.setTranslateY(50);

        username.setPrefSize(260,30);
        username.setTranslateX(200);
        username.setTranslateY(130);
        username.setPromptText("Enter username");
        username.setFocusTraversable(false);
        username.setStyle("-fx-background-color: #cba883; -fx-prompt-text-fill: black");

        passwordHide.setPrefSize(260,30);
        passwordHide.setTranslateX(200);
        passwordHide.setTranslateY(220);
        passwordHide.setPromptText("Enter password");
        passwordHide.setFocusTraversable(false);
        passwordHide.setStyle("-fx-background-color: #cba883; -fx-prompt-text-fill: black");

        passwordShow.setPrefSize(260,30);
        passwordShow.setTranslateX(200);
        passwordShow.setTranslateY(220);
        passwordShow.setPromptText("Enter password");
        passwordShow.setVisible(false);
        passwordShow.setStyle("-fx-background-color: #cba883; -fx-prompt-text-fill: black");

        passwordVisibility.setPrefSize(30,30);
        passwordVisibility.setTranslateX(480);
        passwordVisibility.setTranslateY(220);

        passwordVisibility.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                showAndHidePassword();
            }
        });

        randomPassword.setStyle("-fx-background-color: #cba883");
        randomPassword.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 12));
        randomPassword.setLayoutX(510);
        randomPassword.setLayoutY(220);
        randomPassword.setPrefSize(80, 40);
        randomPassword.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                randomPassword(mouseEvent);
            }
        });



        email.setPrefSize(260,30);
        email.setTranslateX(200);
        email.setTranslateY(310);
        email.setPromptText("Enter email");
        email.setFocusTraversable(false);
        email.setStyle("-fx-background-color: #cba883; -fx-prompt-text-fill: black");

        nickname.setPrefSize(260,30);
        nickname.setTranslateX(200);
        nickname.setTranslateY(400);
        nickname.setPromptText("Enter nickname");
        nickname.setFocusTraversable(false);
        nickname.setStyle("-fx-background-color: #cba883; -fx-prompt-text-fill: black");

        showSlogan.setStyle("-fx-background-color: #cba883");
        showSlogan.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
        showSlogan.setLayoutX(200);
        showSlogan.setLayoutY(490);
        showSlogan.setPrefSize(200, 40);
        showSlogan.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                checkingSlogan(mouseEvent);
            }
        });

        slogan.setPrefSize(260,30);
        slogan.setTranslateX(200);
        slogan.setTranslateY(580);
        slogan.setPromptText("Enter slogan");
        slogan.setFocusTraversable(false);
        slogan.setStyle("-fx-background-color: #cba883; -fx-prompt-text-fill: black");
        slogan.setVisible(false);

        randomSloganButton.setStyle("-fx-background-color: #cba883");
        randomSloganButton.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 12));
        randomSloganButton.setLayoutX(200);
        randomSloganButton.setLayoutY(670);
        randomSloganButton.setPrefSize(80, 40);
        randomSloganButton.setVisible(false);
        randomSloganButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                checkingSlogan(mouseEvent);
            }
        });

        famousSlogans.setStyle("-fx-background-color: #cba883");
        famousSlogans.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 12));
        famousSlogans.setLayoutX(290);
        famousSlogans.setLayoutY(670);
        famousSlogans.setPrefSize(100, 40);
        famousSlogans.setVisible(false);
        famousSlogans.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                showFamousSlogan(mouseEvent);
            }
        });


        famousDialogue.setVisible(false);
        famousDialogue.setPrefWidth(200);
        famousDialogue.setLayoutX(960);
        famousDialogue.setLayoutY(130);
        famousDialogue.setStyle("-fx-background-color: #cba883");
        famousDialogue.getItems().add("I march to death...Though I wish it was my own...");
        famousDialogue.getItems().add("They think I'm monster...and I prove them right");
        famousDialogue.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value) {
                slogan.setText(famousDialogue.getItems().get(new_value.intValue()));

            }
        });



        pane.getChildren().add(headerLabel);
        pane.getChildren().add(username);
        pane.getChildren().add(passwordHide);
        pane.getChildren().add(passwordShow);
        pane.getChildren().add(passwordVisibility);
        pane.getChildren().add(randomPassword);
        pane.getChildren().add(email);
        pane.getChildren().add(nickname);
        pane.getChildren().add(showSlogan);
        pane.getChildren().add(slogan);
        pane.getChildren().add(randomSloganButton);
        pane.getChildren().add(famousSlogans);
        pane.getChildren().add(famousDialogue);
    }
//    public VBox createVboxAnsStructure(Popup popup){
//
////        root.setBackground(new Color(red));
//        return root ;
//    }

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

    private void checkPasswordError(Label label, String textSucess) {
        String password;
        if (passwordHide.isVisible()) password = passwordHide.getText();
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
            checkPasswordError(headerLabel, "Complete the registration form");
        });
        passwordHide.textProperty().addListener((observable, oldText, newText) -> {
            checkPasswordError(headerLabel, "Complete the registration form");
        });

    }

    private void ListenerToUsernameField() {
        username.textProperty().addListener((observable, oldText, newText) -> {
            usernameError(headerLabel, "Complete the registration form");
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
        usernameError(usernameError, "");
        checkPasswordError(passwordError, "");
        checkEmail();
        nicknameError();
        sloganCheck();
        if (!isEnd) vBoxErrorHandling.setVisible(true);
        checkRegisterSucess(isEnd);
    }

    private void checkRegisterSucess(boolean isEnd) throws Exception {
        if (usernameError.getText().equals("") &&
                passwordError.getText().equals("") &&
                emailError.getText().equals("") &&
                sloganError.getText().equals("")) {
            if (!isEnd) askQuestionShow();
            else {
                submitUser();
            }
        }

    }

    private void submitUser() throws Exception {
        int number;
        if (toggleGroup.getSelectedToggle().equals(Q1)) number = 1;
        else if (toggleGroup.getSelectedToggle().equals(Q2)) number = 2;
        else number = 3;
        String password;
        if (passwordHide.isVisible()) password = passwordHide.getText();
        else password = passwordShow.getText();
        LoginController.register(username.getText(), password, nickname.getText(),
                email.getText(), answer.getText(), slogan.getText(), "" + number);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Register");
        alert.setContentText("Successfully Registered!");
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

    public void usernameError(Label label, String textSuccess) {
        String newText = username.getText();
        RegisterMessages messages = LoginController.checkUsername(newText);
        switch (messages) {
            case EMPTY_FIELD:
                label.setText("You have empty field!");
                break;
            case USERNAME_REPEATED:
                label.setText("Your username is repeated but username " +
                        LoginController.makeUserNameForUser(newText) +
                        " is available!");
                break;
            case INCORRECT_FORM_OF_USERNAME:
                label.setText("Invalid form of username!");
                break;
            case SUCCESS:
                label.setText(textSuccess);
        }
    }

    public void nicknameError() {
        String newText = nickname.getText();
        if (newText.equals("")) nicknameError.setText("Empty field!");
        else nicknameError.setText("");
    }

    //Dd3Rr5Tt8#
    //Vv8Ww3Cc5#
    public void checkEmail() {
        String newText = email.getText();
        RegisterMessages messages = LoginController.checkEmail(newText);
        switch (messages) {
            case EMPTY_FIELD:
                emailError.setText("You have empty field!");
                break;
            case REPEATED_EMAIL:
                emailError.setText("Your email is repeated!");
                break;
            case INVALID_FORM_EMAIL:
                emailError.setText("Your form of email is invalid!");
                break;
            case SUCCESS:
                emailError.setText("");
                break;
        }
    }

    public void closePopup(MouseEvent mouseEvent) {
        if (!answer.getText().equals("") && toggleGroup.getSelectedToggle() != null) {
            secQestionVbox.setVisible(false);
            captchaNumber = LoginController.setImageCaptcha(captchaImage);
            captchaBox.setVisible(true);
        }
    }

    public void anotherCaptcha(MouseEvent mouseEvent) {
        captchaNumber = LoginController.setImageCaptcha(captchaImage);
    }

    public void submitWholeRegister(MouseEvent mouseEvent) throws Exception {
        if (answerOfCaptcha.getText().equals(captchaNumber)) stepOneRegister(true);
        else {
            captchaNumber = LoginController.setImageCaptcha(captchaImage);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Captcha field didn't fill correctly!");
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
package view;

import controller.LoginController;
import controller.ProfileController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.User;
import view.Messages.RegisterMessages;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import static controller.LoginController.checkPassword;

public class ProfileMenu extends Application {
    public ImageView draggedAvatarImage = new ImageView();
    public ImageView avatar;
    public Pane pane ;
    public static Stage stage ;
    public ImageView imageView1;
    public ImageView imageView2;
    public ImageView imageView3;
    public ImageView imageView4;
    public ImageView imageView5;
    public ImageView imageView6;
    public ImageView imageView7;
    public ImageView imageView8;
    public ImageView imageView9;
    public Scene scene ;
    public VBox vBoxOfSelectImage;


    public TextField username;
    public TextField email;
    public Label label;
    public TextField nickname;
    public TextField slogan;
    public VBox captchaBox;
    public ImageView captchaImage;
    public TextField answerOfCaptcha;
    public VBox vBoxErrorHandling;
    public Label usernameError;
    public Label emailError;
    public Label nicknameError;
    public Label sloganError;
    public VBox passwordBox;
    public TextField oldPassword;
    public TextField RetypeNewPassword;
    public TextField newPassword;
    public Label oldPasswordError;
    public Label newPasswordError;
    public Label confirmPasswordError;

    @Override
    public void start(Stage stage) throws Exception {

        ProfileMenu.stage = stage ;
        URL url = RegisterMenu.class.getResource("/fxml/profileMenu.fxml");
        Pane pane = FXMLLoader.load(url);
        this.pane = pane;
        StartingFunctions();
        Scene scene = new Scene(pane);
        this.scene = scene ;
        DragAndDropImage();
        setBackButton();

        Button button = new Button("click");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                draggedAvatarImage.setImage(null);
            }
        });
        pane.getChildren().add(button);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
    private void setBackButton(){
        Button button = new Button("Back");
        button.setTranslateX(0);
        button.setTranslateY(600);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Main main = new Main();
                try {
                    main.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        pane.getChildren().add(button);
    }

    private void StartingFunctions() {
        draggedAvatarImage.setFitWidth(100);
        draggedAvatarImage.setFitHeight(100);
        draggedAvatarImage.setTranslateX(10);
        draggedAvatarImage.setTranslateY(10);
        pane.getChildren().add(draggedAvatarImage);
    }

    public void initialize(){
        setAvatarImage();
        initializeDefaultAvatarsImage();
        fillWholeFields();
        ListenerToUsernameField();
        ListenerToPassword();
        setFirstPassword();
    }

    private void setFirstPassword() {
        User user = User.getCurrentUser();
        newPassword.setText(user.getPassword());
        oldPassword.setText(user.getPassword());
        RetypeNewPassword.setText(user.getPassword());
    }

    private void ListenerToPassword() {
        newPassword.textProperty().addListener((observable, oldText, newText) -> {
            checkPasswordError(label,"Fill register form");
        });
    }
    private void checkPasswordError(Label label,String textSucess) {
        String password = newPassword.getText();
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

    public void usernameError(Label label,String textSuccess){
        String newText =  username.getText();
        RegisterMessages messages = LoginController.checkUsername(newText);
        switch (messages){
            case EMPTY_FIELD:
                label.setText("You have empty field");
                break;
            case USERNAME_REPEATED :
                if(!User.getCurrentUser().getUsername().equals(username.getText())) {
                    label.setText("Your username is repeated but username " +
                            LoginController.makeUserNameForUser(newText) +
                            " is exist now!");
                }else{
                    label.setText(textSuccess);
                }
                break;
            case INCORRECT_FORM_OF_USERNAME:
                label.setText("Invalid form of username!");
                break;
            case SUCCESS:
                label.setText(textSuccess);
        }
    }
    private void ListenerToUsernameField() {
        username.textProperty().addListener((observable, oldText, newText) -> {
            usernameError(label, "Fill register form");
        });
    }
    private void fillWholeFields(){
        User user = User.getCurrentUser();
        username.setText(user.getUsername());
        email.setText(user.getEmail());
        slogan.setText(user.getSlogan());
        nickname.setText(user.getNickname());
    }

    private void DragAndDropImage() {
        scene.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });

        // Dropping over surface
        scene.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    String filePath = null;
                    for (File file:db.getFiles()) {
                        filePath = file.getAbsolutePath();
                        System.out.println(filePath);
                        Image image  = new Image(filePath);
                        User.getCurrentUser().setAvatar(draggedAvatarImage);
                        draggedAvatarImage.setImage(image);
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
    }

    public void setAvatarImage(){
//        if(dra)dra
        avatar.setImage(User.getCurrentUser().getAvatar().getImage());
        avatar.setFitWidth(100);
        avatar.setFitHeight(100);
    }
    public void initializeDefaultAvatarsImage(){
        ArrayList<ImageView> imageViews = new ArrayList<>();
        imageViews.add(imageView1); imageViews.add(imageView2); imageViews.add(imageView3);
        imageViews.add(imageView4); imageViews.add(imageView5); imageViews.add(imageView6);
        imageViews.add(imageView7); imageViews.add(imageView8); imageViews.add(imageView9);
        for(ImageView imageView : imageViews){
            Image image = new Image(ProfileMenu.class.getResource(
                    "/avatars/" + (imageViews.indexOf(imageView) + 1) + ".png").toExternalForm());
            imageView.setImage(image);
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    draggedAvatarImage.setImage(null);
                    System.out.println(draggedAvatarImage.isVisible());
                    User.getCurrentUser().setAvatar(imageView);
                    setAvatarImage();
                }
            });
        }
    }

    public void ChooseFileFromSystem(MouseEvent mouseEvent) {
        final String[] text = new String[1];
        FileChooser fil_chooser = new FileChooser();
        File file = fil_chooser.showOpenDialog(stage);

        if (file != null) {
            text[0] = file.getAbsolutePath();
            System.out.println(text[0]);
            Image image = new Image(text[0]);
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            User.getCurrentUser().setAvatar(imageView);
            setAvatarImage();
        }
    }

    public void hideShowSelectImage(MouseEvent mouseEvent) {
        vBoxOfSelectImage.setVisible(!vBoxOfSelectImage.isVisible());
    }
    public void checkEmail(){
        String newText =  email.getText();
        RegisterMessages messages = LoginController.checkEmail(newText);
        switch (messages){
            case EMPTY_FIELD :
                emailError.setText("You have empty field");
                break;
            case REPEATED_EMAIL:
                if(!email.getText().equals(User.getCurrentUser().getEmail())){
                    emailError.setText("Your email is repeated");
                }else{
                    emailError.setText("");
                }
                break;
            case INVALID_FORM_EMAIL:
                emailError.setText("Your form if email is invalid!");
                break;
            case SUCCESS:
                emailError.setText("");
                break;
        }
    }
    public void sloganCheck() {
        if (slogan.getText().equals("")) sloganError.setText("Empty field");
        else sloganError.setText("");
    }
    public void nicknameError(){
        String newText = nickname.getText();
        if(newText.equals("")) nicknameError.setText("Empty field");
        else nicknameError.setText("");
    }

    public void submit(MouseEvent mouseEvent) throws Exception {
        usernameError(usernameError,"");
        checkPassword();
        checkEmail();
        nicknameError();
        sloganCheck();
        checkFinalChanges();

    }
    private void checkPassword(){
        if(oldPassword.getText().equals("")) {
            oldPasswordError.setText("Empty Field");
        }
        else if(!ProfileController.checkOldPassword(oldPassword.getText())) oldPasswordError.setText("Old password is wrong!");
        else oldPasswordError.setText("");
        checkPasswordError(newPasswordError,"");
        if(RetypeNewPassword.getText().equals("")) confirmPasswordError.setText("Empty Field");
        else if(!newPassword.getText().equals(RetypeNewPassword.getText())) confirmPasswordError.setText("Is not equal!");
        else confirmPasswordError.setText("");
    }

    private void checkFinalChanges() throws Exception {
        if (usernameError.getText().equals("") &&
                emailError.getText().equals("") &&
                nicknameError.getText().equals("") &&
                sloganError.getText().equals("") &&
                oldPasswordError.getText().equals("") &&
                newPasswordError.getText().equals("") &&
                confirmPasswordError.getText().equals("")
        ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successfully");
            System.out.println("username is : " + username.getText());
            System.out.println("password is : " + email.getText());
            System.out.println("nickname is : " +  nickname.getText());
            System.out.println("slogan is : " + slogan.getText());
            System.out.println("password is : " + newPassword.getText());

            alert.setContentText(ProfileController.changingFields(username.getText(),email.getText()
                    ,nickname.getText(),slogan.getText(),newPassword.getText()));

            ProfileController.editProfile(username.getText(),email.getText()
                    ,nickname.getText(),slogan.getText(),newPassword.getText());
            alert.showAndWait();
            Main mainMenu = new Main();
            mainMenu.start(stage);
        }else {
            vBoxErrorHandling.setVisible(true);
        }
    }

    public void anotherCaptcha(MouseEvent mouseEvent) {
    }

    public void removeSlogan(MouseEvent mouseEvent) {
        slogan.setText("slogan is");
    }

    public void changePassword(MouseEvent mouseEvent) {
        if(!passwordBox.isVisible()){
            newPassword.setText("");
            oldPassword.setText("");
            RetypeNewPassword.setText("");
        }else{
            User user = User.getCurrentUser();
            newPassword.setText(user.getPassword());
            oldPassword.setText(user.getPassword());
            RetypeNewPassword.setText(user.getPassword());
            System.out.println(newPassword.getText());
            System.out.println(oldPassword.getText());
            System.out.println(RetypeNewPassword.getText());
        }
        passwordBox.setVisible(!passwordBox.isVisible());
    }
}
package view;

import controller.LoginController;
import controller.ProfileController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.User;
import view.ImageAndBackground.GameImages;
import view.Messages.RegisterMessages;

import java.io.File;
import java.util.ArrayList;


public class ProfileMenu extends Application {
    public Pane pane ;
    public static Stage stage ;
    public ImageView draggedAvatarImage = new ImageView();
    public ImageView avatar = new ImageView();
    public ImageView imageView1 = new ImageView(new Image(ProfileMenu.class.getResource("/avatars/1.png").toExternalForm()));
    public ImageView imageView2 = new ImageView(new Image(ProfileMenu.class.getResource("/avatars/2.png").toExternalForm()));
    public ImageView imageView3 = new ImageView(new Image(ProfileMenu.class.getResource("/avatars/3.png").toExternalForm()));
    public ImageView imageView4 = new ImageView(new Image(ProfileMenu.class.getResource("/avatars/4.png").toExternalForm()));
    public ImageView imageView5 = new ImageView(new Image(ProfileMenu.class.getResource("/avatars/5.png").toExternalForm()));
    public ImageView imageView6 = new ImageView(new Image(ProfileMenu.class.getResource("/avatars/6.png").toExternalForm()));
    public ImageView imageView7 = new ImageView(new Image(ProfileMenu.class.getResource("/avatars/7.png").toExternalForm()));
    public ImageView imageView8 = new ImageView(new Image(ProfileMenu.class.getResource("/avatars/8.png").toExternalForm()));
    public ImageView imageView9 = new ImageView(new Image(ProfileMenu.class.getResource("/avatars/9.png").toExternalForm()));
    public Scene scene;
    public VBox vBoxOfSelectImage = new VBox();


    public TextField username = new TextField();
    public TextField email = new TextField();
    public Label headerLabel = new Label();
    public TextField nickname = new TextField();
    public TextField slogan = new TextField();
    public ImageView captchaImage = new ImageView();
    public TextField answerOfCaptcha = new TextField();
    public VBox vBoxErrorHandling = new VBox();
    public Label usernameError = new Label();
    public Label emailError = new Label();
    public Label nicknameError = new Label();
    public Label sloganError = new Label();
    public VBox passwordBox = new VBox();
    public TextField oldPassword = new TextField();
    public TextField retypeNewPassword = new TextField();
    public TextField newPassword = new TextField();
    public Label oldPasswordError = new Label();
    public Label newPasswordError = new Label();
    public Label confirmPasswordError = new Label();
    public String captchaNumber;
    public Button remove = new Button("Remove");
    public Button changePasswordButton = new Button("Change Password");
    public Button submit = new Button("Submit");
    public Button selectImage = new Button("Select Image");
    public Button selectImageFromSystem = new Button("Choose");
    public Button refresh = new Button("Refresh");
    @Override
    public void start(Stage stage) throws Exception {
        ProfileMenu.stage = stage ;
        Pane pane = new Pane();
        this.pane = pane;
        StartingFunctions();
        designProfileMenu();
        Scene scene = new Scene(pane);
        this.scene = scene ;
        DragAndDropImage();
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    private void designProfileMenu() {
        Button backButton = new Button("Back");
        backButton.setTranslateX(20);
        backButton.setTranslateY(20);
        backButton.setStyle("-fx-background-color: #cba883");
        backButton.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        backButton.setPrefSize(100,40);
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
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

        headerLabel.setText("Profile Menu");
        headerLabel.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 26));
        headerLabel.setTranslateX(700);
        headerLabel.setTranslateY(50);

        username.setPrefSize(260,30);
        username.setTranslateX(200);
        username.setTranslateY(130);
        username.setPromptText("Enter new username");
        username.setFocusTraversable(false);
        username.setStyle("-fx-background-color: #cba883; -fx-prompt-text-fill: black");
        username.textProperty().addListener((observable, oldText, newText) -> {
            usernameError(headerLabel, "ProfileMenu");
        });

        email.setPrefSize(260,30);
        email.setTranslateX(200);
        email.setTranslateY(220);
        email.setPromptText("Enter new email");
        email.setFocusTraversable(false);
        email.setStyle("-fx-background-color: #cba883; -fx-prompt-text-fill: black");

        nickname.setPrefSize(260,30);
        nickname.setTranslateX(200);
        nickname.setTranslateY(310);
        nickname.setPromptText("Enter new nickname");
        nickname.setFocusTraversable(false);
        nickname.setStyle("-fx-background-color: #cba883; -fx-prompt-text-fill: black");

        slogan.setPrefSize(260,30);
        slogan.setTranslateX(200);
        slogan.setTranslateY(400);
        slogan.setPromptText("Enter new slogan");
        slogan.setFocusTraversable(false);
        slogan.setStyle("-fx-background-color: #cba883; -fx-prompt-text-fill: black");

        remove.setStyle("-fx-background-color: #cba883");
        remove.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 12));
        remove.setLayoutX(200);
        remove.setLayoutY(490);
        remove.setPrefSize(80, 40);
        remove.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                removeSlogan(mouseEvent);
            }
        });
        remove.setFocusTraversable(false);

        changePasswordButton.setStyle("-fx-background-color: #cba883");
        changePasswordButton.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 12));
        changePasswordButton.setLayoutX(290);
        changePasswordButton.setLayoutY(490);
        changePasswordButton.setPrefSize(130, 40);
        changePasswordButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                changePassword(mouseEvent);
            }
        });
        changePasswordButton.setFocusTraversable(false);

        submit.setStyle("-fx-background-color: #cba883");
        submit.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        submit.setLayoutX(700);
        submit.setLayoutY(670);
        submit.setPrefSize(130, 40);
        submit.setFocusTraversable(false);
        submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    submit(mouseEvent);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        avatar.setTranslateX(1000);
        avatar.setTranslateY(100);
        avatar.setVisible(true);

        selectImage.setStyle("-fx-background-color: #cba883");
        selectImage.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        selectImage.setLayoutX(1200);
        selectImage.setLayoutY(80);
        selectImage.setPrefSize(140, 40);
        selectImage.setFocusTraversable(false);
        selectImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    hideShowSelectImage(mouseEvent);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        designVBoxOfImage();

        selectImageFromSystem.setStyle("-fx-background-color: #cba883");
        selectImageFromSystem.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
        selectImageFromSystem.setLayoutX(1010);
        selectImageFromSystem.setLayoutY(460);
        selectImageFromSystem.setPrefSize(80, 40);
        selectImageFromSystem.setFocusTraversable(false);
        selectImageFromSystem.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    ChooseFileFromSystem(mouseEvent);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        selectImageFromSystem.setVisible(false);

        Button button = new Button("Dragged Image");
        button.setStyle("-fx-background-color: #cba883");
        button.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
        button.setLayoutX(1200);
        button.setLayoutY(125);
        button.setPrefSize(140,40);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                draggedAvatarImage.setImage(null);
            }
        });

        designVboxOfChangePassword();

        refresh.setStyle("-fx-background-color: #cba883");
        refresh.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 16));
        refresh.setFocusTraversable(false);
        refresh.setLayoutX(990);
        refresh.setLayoutY(630);
        refresh.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    anotherCaptcha(mouseEvent);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        refresh.setVisible(false);
        designVboxOfErrors();

        pane.getChildren().add(headerLabel);
        pane.getChildren().add(backButton);
        pane.getChildren().add(username);
        pane.getChildren().add(email);
        pane.getChildren().add(nickname);
        pane.getChildren().add(slogan);
        pane.getChildren().add(remove);
        pane.getChildren().add(changePasswordButton);
        pane.getChildren().add(submit);
        pane.getChildren().add(avatar);
        pane.getChildren().add(selectImage);
        pane.getChildren().add(button);
        pane.getChildren().add(vBoxOfSelectImage);
        pane.getChildren().add(selectImageFromSystem);
        pane.getChildren().add(passwordBox);
        pane.getChildren().add(refresh);
        pane.getChildren().add(vBoxErrorHandling);
    }

    private void designVboxOfChangePassword() {
        HBox hBox = new HBox();
        hBox.setSpacing(20);
        passwordBox.setSpacing(20);
        oldPasswordError.setStyle("-fx-text-fill: #871818");
        oldPassword.setPromptText("Old password");
        oldPassword.setMaxWidth(100);
        oldPassword.setTranslateX(30);
        newPasswordError.setStyle("-fx-text-fill: #871818");
        newPassword.setPromptText("New password");
        newPassword.setMaxWidth(100);
        newPassword.setTranslateX(30);
        newPassword.textProperty().addListener((observable, oldText, newText) -> {
            checkPasswordError(headerLabel,"Profile Menu");
        });
        confirmPasswordError.setStyle("-fx-text-fill: #871818");
        retypeNewPassword.setPromptText("Repeat new password");
        retypeNewPassword.setMaxWidth(100);
        retypeNewPassword.setTranslateX(30);
        answerOfCaptcha.setMaxWidth(100);
        answerOfCaptcha.setMaxWidth(100);
        answerOfCaptcha.setTranslateX(30);
        hBox.getChildren().add(answerOfCaptcha);
        passwordBox.setTranslateX(950);
        passwordBox.setTranslateY(250);
        passwordBox.setPrefSize(100,300);
        passwordBox.getChildren().add(oldPasswordError);
        passwordBox.getChildren().add(oldPassword);
        passwordBox.getChildren().add(newPasswordError);
        passwordBox.getChildren().add(newPassword);
        passwordBox.getChildren().add(confirmPasswordError);
        passwordBox.getChildren().add(retypeNewPassword);
        passwordBox.getChildren().add(captchaImage);
        passwordBox.getChildren().add(answerOfCaptcha);
        passwordBox.getChildren().add(hBox);
        passwordBox.setStyle("-fx-background-color: #cba883");
        passwordBox.setVisible(false);

    }

    public void designVboxOfErrors(){
        usernameError.setText("random text 1");
        usernameError.setStyle("-fx-text-fill: #871818");
        emailError.setText("random text 3");
        emailError.setStyle("-fx-text-fill: #871818");
        nicknameError.setText("random text 4");
        nicknameError.setStyle("-fx-text-fill: #871818");
        sloganError.setText("random text 5");
        sloganError.setStyle("-fx-text-fill: #871818");
        vBoxErrorHandling.setSpacing(70);
        vBoxErrorHandling.getChildren().add(usernameError);
        vBoxErrorHandling.getChildren().add(emailError);
        vBoxErrorHandling.getChildren().add(nicknameError);
        vBoxErrorHandling.getChildren().add(sloganError);
        vBoxErrorHandling.setTranslateX(200);
        vBoxErrorHandling.setTranslateY(110);
        vBoxErrorHandling.setVisible(false);
    }


    public void designVBoxOfImage(){
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        HBox hBox3 = new HBox();
        hBox1.setSpacing(30);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().add(imageView1);
        hBox1.getChildren().add(imageView2);
        hBox1.getChildren().add(imageView3);
        hBox2.setSpacing(30);
        hBox2.setAlignment(Pos.CENTER);
        hBox2.getChildren().add(imageView4);
        hBox2.getChildren().add(imageView5);
        hBox2.getChildren().add(imageView6);
        hBox3.setSpacing(30);
        hBox3.setAlignment(Pos.CENTER);
        hBox3.getChildren().add(imageView7);
        hBox3.getChildren().add(imageView8);
        hBox3.getChildren().add(imageView9);

        vBoxOfSelectImage.setSpacing(20);
        vBoxOfSelectImage.getChildren().add(hBox1);
        vBoxOfSelectImage.getChildren().add(hBox2);
        vBoxOfSelectImage.getChildren().add(hBox3);
        vBoxOfSelectImage.setTranslateX(950);
        vBoxOfSelectImage.setTranslateY(250);
        vBoxOfSelectImage.setStyle("-fx-background-color: #cba883");
        vBoxOfSelectImage.setVisible(false);

    }

    private void StartingFunctions() {
        GameImages gameImages = new GameImages();
        gameImages.loadImages();
        pane.setBackground(gameImages.getProfileBackground());
        setAvatarImage();
        initializeDefaultAvatarsImage();
        fillWholeFields();
        setFirstPassword();
        draggedAvatarImage.setFitWidth(100);
        draggedAvatarImage.setFitHeight(100);
        draggedAvatarImage.setTranslateX(10);
        draggedAvatarImage.setTranslateY(10);
        pane.getChildren().add(draggedAvatarImage);
    }

    private void setFirstPassword() {
        User user = User.getCurrentUser();
        newPassword.setText(user.getPassword());
        oldPassword.setText(user.getPassword());
        retypeNewPassword.setText(user.getPassword());
        captchaNumber = LoginController.setImageCaptcha(captchaImage);
        answerOfCaptcha.setText(captchaNumber);
    }
    private void checkPasswordError(Label label,String textSucess) {
        String password = newPassword.getText();
        RegisterMessages messages = LoginController.checkPassword(password);
        String text = null;
        switch (messages) {
            case EMPTY_FIELD:
                text = "You have empty Field!";
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
                text = "You should use numbers in your password!";
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
                            " is available!");
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
        selectImageFromSystem.setVisible(vBoxOfSelectImage.isVisible());
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
                emailError.setText("Your form of email is invalid!");
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
        if(!captchaNumber.equals(answerOfCaptcha.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("captcha is wrong!");
            alert.showAndWait();
            return;
        }
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
        if(retypeNewPassword.getText().equals("")) confirmPasswordError.setText("Empty Field");
        else if(!newPassword.getText().equals(retypeNewPassword.getText())) confirmPasswordError.setText("Is not equal!");
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
        captchaNumber = LoginController.setImageCaptcha(captchaImage);
    }

    public void removeSlogan(MouseEvent mouseEvent) {
        slogan.setText("");
        slogan.setPromptText("Slogan is");
    }

    public void changePassword(MouseEvent mouseEvent) {
        if(!passwordBox.isVisible()){
            newPassword.setText("");
            oldPassword.setText("");
            retypeNewPassword.setText("");
            answerOfCaptcha.setText("");
        }else{
            User user = User.getCurrentUser();
            newPassword.setText(user.getPassword());
            oldPassword.setText(user.getPassword());
            retypeNewPassword.setText(user.getPassword());
            answerOfCaptcha.setText(captchaNumber);
            System.out.println(newPassword.getText());
            System.out.println(oldPassword.getText());
            System.out.println(retypeNewPassword.getText());
        }
        passwordBox.setVisible(!passwordBox.isVisible());
        refresh.setVisible(passwordBox.isVisible());
    }
}
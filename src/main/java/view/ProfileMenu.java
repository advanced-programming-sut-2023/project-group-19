package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.User;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

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

    @Override
    public void start(Stage stage) throws Exception {
        User user = new User("s","s","a","f","w","a",3);
        User.setCurrentUser(user);
        URL url = RegisterMenu.class.getResource("/fxml/profileMenu.fxml");
        Pane pane = FXMLLoader.load(url);
        this.pane = pane;
        StartingFunctions();
        Scene scene = new Scene(pane);
        this.scene = scene ;
        DragAndDropImage();

        Button button = new Button("click");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                draggedAvatarImage.setVisible(!draggedAvatarImage.isVisible());
            }
        });
        pane.getChildren().add(button);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    private void StartingFunctions() {
        draggedAvatarImage.setFitWidth(100);
        draggedAvatarImage.setFitHeight(100);
        draggedAvatarImage.setTranslateX(200);
        draggedAvatarImage.setTranslateY(200);
        pane.getChildren().add(draggedAvatarImage);
    }

    public void initialize(){
        setAvatarImage();
        initializeDefaultAvatarsImage();
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
//                    avatar.setVisible(true);
//                    draggedAvatarImage.setVisible(false);
//                    draggedAvatarImage.setOpacity(0);
//                    System.out.println("i am here");
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
}
package view.ImageAndBackground;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import view.TileManager;

public class GameImages {

    public Image next;
    public Image done;
    public Image close;
    public Background loginBackground;
    public Background registerBackground;

    public Background profileBackground;
    public Background mainMenuBackground;


    public void loadImages(){
        loginBackground = new Background(new BackgroundImage(new Image
                (TileManager.class.getResource("/image/MenuImages/loginBackground.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        registerBackground = new Background(new BackgroundImage(new Image
                (TileManager.class.getResource("/image/MenuImages/registerBackground.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        profileBackground =  new Background(new BackgroundImage(new Image
                (TileManager.class.getResource("/image/MenuImages/ProfileMenuImage.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        mainMenuBackground = new Background(new BackgroundImage(new Image
                (TileManager.class.getResource("/image/MenuImages/MainMenu.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));

        next = new Image(TileManager.class.getResource("/image/GameMenu/next.png").toExternalForm());
        done = new Image(TileManager.class.getResource("/image/GameMenu/done.png").toExternalForm());
        close = new Image(TileManager.class.getResource("/image/GameMenu/close.png").toExternalForm());
    }


    public Image getNext() {
        return next;
    }


    public Image getDone() {
        return done;
    }



    public Background getLoginBackground() {
        return loginBackground;
    }

    public Background getRegisterBackground() {
        return registerBackground;
    }

    public Background getProfileBackground() {
        return profileBackground;
    }

    public Background getMainMenuBackground() {
        return mainMenuBackground;
    }
}

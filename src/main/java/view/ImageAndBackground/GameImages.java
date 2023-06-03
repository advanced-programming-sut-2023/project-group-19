package view.ImageAndBackground;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import view.TileManager;

import java.security.PublicKey;

public class GameImages {

    public Image next;
    public Image done;
    public Image close;
    public Background background;


    public void loadImages(){
        background = new Background(new BackgroundImage(new Image
                (TileManager.class.getResource("/image/LoginMenuImages/background.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        next = new Image(TileManager.class.getResource("/image/GameMenu/next.png").toExternalForm());
        done = new Image(TileManager.class.getResource("/image/GameMenu/done.png").toExternalForm());
        close = new Image(TileManager.class.getResource("/image/GameMenu/close.png").toExternalForm());
    }


    public Image getNext() {
        return next;
    }

    public void setNext(Image next) {
        this.next = next;
    }

    public Image getDone() {
        return done;
    }

    public void setDone(Image done) {
        this.done = done;
    }

    public Image getClose() {
        return close;
    }

    public void setClose(Image close) {
        this.close = close;
    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background;
    }
}

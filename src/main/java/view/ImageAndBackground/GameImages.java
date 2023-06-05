package view.ImageAndBackground;

import javafx.scene.image.Image;
import view.TileManager;

import java.security.PublicKey;

public class GameImages {

    public Image next;
    public Image done;
    public Image close;


    public void loadImages(){
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
}

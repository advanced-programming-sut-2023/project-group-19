package view.ImageAndBackground;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import view.TileManager;

public class BottomBarImages {
    public Background background;
    public Image bottomImage;
    public Image castle;
    public Image hammer;
    public Image home;
    public Image food;
    public Image shield;
    public Image sickle;
    public Image key;
    public Image exclamation;
    public Image close;
    public Image undo;
    public Image minimapFrame;
    public Image showEmpireDetail;
    public Image bottomCoverImage;
    public Image faceImage;
    public Image bottomSideCoverImage;
    public Image apple;
    public Image hops;
    public Image wheat;
    public Image dairy;
    public Image test;
    public void loadImages(){
        background = new Background(new BackgroundImage(new Image
                (TileManager.class.getResource("/image/desert_tile.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        bottomImage = new Image(TileManager.class.getResource("/image/BottomBar/bottomPicture.png").toExternalForm());
        bottomCoverImage = new Image(TileManager.class.getResource("/image/BottomBar/bottomCover.jpg").toExternalForm());
        bottomSideCoverImage = new Image(TileManager.class.getResource("/image/BottomBar/sideCover.jpg").toExternalForm());
        castle = new Image(TileManager.class.getResource("/image/BottomBar/castle.png").toExternalForm());
        food = new Image(TileManager.class.getResource("/image/BottomBar/food.png").toExternalForm());
        hammer = new Image(TileManager.class.getResource("/image/BottomBar/hammer.png").toExternalForm());
        home = new Image(TileManager.class.getResource("/image/BottomBar/home.png").toExternalForm());
        shield = new Image(TileManager.class.getResource("/image/BottomBar/shield.png").toExternalForm());
        sickle = new Image(TileManager.class.getResource("/image/BottomBar/sickle.png").toExternalForm());
        key = new Image(TileManager.class.getResource("/image/BottomBar/key.png").toExternalForm());
        exclamation = new Image(TileManager.class.getResource("/image/BottomBar/exclamation-mark.png").toExternalForm());
        close = new Image(TileManager.class.getResource("/image/BottomBar/close.png").toExternalForm());
        undo = new Image(TileManager.class.getResource("/image/BottomBar/undo.png").toExternalForm());
        minimapFrame = new Image(TileManager.class.getResource("/image/BottomBar/minimapFrame.jpg").toExternalForm());
        showEmpireDetail = new Image(TileManager.class.getResource("/image/BottomBar/showEmpireDetails.png").toExternalForm());
        faceImage = new Image(TileManager.class.getResource("/image/BottomBar/angry.png").toExternalForm());
        apple = new Image(TileManager.class.getResource("/image/BottomBar/apple.png").toExternalForm());
        wheat = new Image(TileManager.class.getResource("/image/BottomBar/wheat.png").toExternalForm());
        hops = new Image(TileManager.class.getResource("/image/BottomBar/hops.png").toExternalForm());
        dairy = new Image(TileManager.class.getResource("/image/BottomBar/cheese.png").toExternalForm());
//        test = new Image(TileManager.class.getResource("/image/BuildingImages/wheatfarm.gif").toExternalForm());
    }

    public Image getApple() {
        return apple;
    }

    public Image getHops() {
        return hops;
    }

    public Image getWheat() {
        return wheat;
    }

    public Image getDairy() {
        return dairy;
    }

    public Background getBackground() {
        return background;
    }

    public Image getBottomImage() {
        return bottomImage;
    }

    public Image getCastle() {
        return castle;
    }

    public Image getHammer() {
        return hammer;
    }

    public Image getHome() {
        return home;
    }

    public Image getFood() {
        return food;
    }

    public Image getShield() {
        return shield;
    }

    public Image getSickle() {
        return sickle;
    }

    public Image getKey() {
        return key;
    }

    public Image getExclamation() {
        return exclamation;
    }

    public Image getClose() {
        return close;
    }

    public Image getUndo() {
        return undo;
    }

    public Image getMinimapFrame() {
        return minimapFrame;
    }

    public Image getShowEmpireDetail() {
        return showEmpireDetail;
    }

    public Image getBottomCoverImage() {
        return bottomCoverImage;
    }

    public Image getFaceImage() {
        return faceImage;
    }

    public Image getBottomSideCoverImage() {
        return bottomSideCoverImage;
    }

    public Image getTest() {
        return test;
    }
}

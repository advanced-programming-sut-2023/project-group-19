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
        test = new Image(TileManager.class.getResource("/image/BottomBarBuildingImages/wheatfarm.gif").toExternalForm());
    }

    public Image getTest() {
        return test;
    }

    public void setTest(Image test) {
        this.test = test;
    }

    public Image getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(Image faceImage) {
        this.faceImage = faceImage;
    }

    public Image getShowEmpireDetail() {
        return showEmpireDetail;
    }

    public void setShowEmpireDetail(Image showEmpireDetail) {
        this.showEmpireDetail = showEmpireDetail;
    }

    public Image getMinimapFrame() {
        return minimapFrame;
    }

    public void setMinimapFrame(Image minimapFrame) {
        this.minimapFrame = minimapFrame;
    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public Image getBottomImage() {
        return bottomImage;
    }

    public void setBottomImage(Image bottomImage) {
        this.bottomImage = bottomImage;
    }

    public Image getCastle() {
        return castle;
    }

    public void setCastle(Image castle) {
        this.castle = castle;
    }

    public Image getHammer() {
        return hammer;
    }

    public void setHammer(Image hammer) {
        this.hammer = hammer;
    }

    public Image getHome() {
        return home;
    }

    public void setHome(Image home) {
        this.home = home;
    }

    public Image getFood() {
        return food;
    }

    public void setFood(Image food) {
        this.food = food;
    }

    public Image getShield() {
        return shield;
    }

    public void setShield(Image shield) {
        this.shield = shield;
    }

    public Image getSickle() {
        return sickle;
    }

    public void setSickle(Image sickle) {
        this.sickle = sickle;
    }

    public Image getBottomCoverImage() {
        return bottomCoverImage;
    }

    public void setBottomCoverImage(Image bottomCoverImage) {
        this.bottomCoverImage = bottomCoverImage;
    }

    public Image getBottomSideCoverImage() {
        return bottomSideCoverImage;
    }

    public void setBottomSideCoverImage(Image bottomSideCoverImage) {
        this.bottomSideCoverImage = bottomSideCoverImage;
    }

    public Image getKey() {
        return key;
    }

    public void setKey(Image key) {
        this.key = key;
    }

    public Image getExclamation() {
        return exclamation;
    }

    public void setExclamation(Image exclamation) {
        this.exclamation = exclamation;
    }

    public Image getClose() {
        return close;
    }

    public void setClose(Image close) {
        this.close = close;
    }

    public Image getUndo() {
        return undo;
    }

    public void setUndo(Image undo) {
        this.undo = undo;
    }
}

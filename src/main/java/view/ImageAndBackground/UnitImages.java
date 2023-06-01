package view.ImageAndBackground;

import javafx.scene.image.Image;
import view.TileManager;

import java.util.ArrayList;

public class UnitImages {
    public ArrayList<Image> images;
    public Image archer;
    public Image crossbowMen;
    public Image spearMen;
    public Image pikeMen;
    public Image maceMen;
    public Image swordsMen;
    public Image knight;
    public Image tunneler;
    public Image ladderMen;
    public Image blackMonk;
    public Image archerBow;
    public Image slaves;
    public Image slingers;
    public Image assassins;
    public Image horseArchers;
    public Image arabianSwordMen;
    public Image fireThrowers;
    public Image catapult;
    public Image trebuchet;
    public Image siegeTower;
    public Image fireBallista;
    public Image batteringRam;
    public Image portableShield;
    public Image hboxOfDropUnit;
    public void loadImages(){
        archer = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/archer.png").toExternalForm());
        crossbowMen = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/crossbowMen.png").toExternalForm());
        spearMen =  new Image(TileManager.class.getResource("/image/Units/IntroductionPics/spearMen.png").toExternalForm());
        pikeMen = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/pikeMen.png").toExternalForm());
        maceMen = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/maceMen.png").toExternalForm());
        swordsMen = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/swordsMen.png").toExternalForm());
        knight = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/knight.png").toExternalForm());
        tunneler = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/tunneler.png").toExternalForm());
        ladderMen = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/ladderMen.png").toExternalForm());
        blackMonk = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/blackMonk.png").toExternalForm());
        archerBow = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/archerBow.png").toExternalForm());
        slaves = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/slaves.png").toExternalForm());
        slingers = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/slingers.png").toExternalForm());
        assassins = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/assassins.png").toExternalForm());
        horseArchers = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/horseArchers.png").toExternalForm());
        arabianSwordMen = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/arabianSwordMen.png").toExternalForm());
        fireThrowers = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/fireThrowers.png").toExternalForm());
        catapult = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/catapult.png").toExternalForm());
        trebuchet = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/trebuchet.png").toExternalForm());
        siegeTower = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/siegeTower.png").toExternalForm());
        fireBallista = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/fireBallista.png").toExternalForm());
        batteringRam = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/batteringRam.png").toExternalForm());
        portableShield = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/portableShield.png").toExternalForm());
        gatheringImages();
    }
    public void gatheringImages(){
        images = new ArrayList<>();
        images.add(archer);
        images.add(crossbowMen);
        images.add(spearMen);
        images.add(pikeMen);
        images.add(maceMen);
        images.add(swordsMen);
        images.add(knight);
        images.add(tunneler);
        images.add(ladderMen);
        images.add(blackMonk);
        images.add(archerBow);
        images.add(slaves);
        images.add(slingers);
        images.add(assassins);
        images.add(horseArchers);
        images.add(arabianSwordMen);
        images.add(fireThrowers);
        images.add(catapult);
        images.add(trebuchet);
        images.add(siegeTower);
        images.add(fireBallista);
        images.add(batteringRam);
        images.add(portableShield);
    }
    public Image getHboxOfDropUnit() {
        return hboxOfDropUnit;
    }

    public void setHboxOfDropUnit(Image hboxOfDropUnit) {
        this.hboxOfDropUnit = hboxOfDropUnit;
    }
}

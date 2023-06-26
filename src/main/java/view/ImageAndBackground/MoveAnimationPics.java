package view.ImageAndBackground;

import javafx.scene.image.Image;
import view.TileManager;

public class MoveAnimationPics {
    public Image archerForwardRight;
    public Image archerForwardLeft;
    public Image archerBackwardRight;
    public Image archerBackwardLeft;
    public Image archerEastRight;
    public Image archerEastLeft;
    public Image archerWestRight;
    public Image archerWestLeft;

    public Image crossbowMenForward; //TODO : FIND PICTURE
    public Image crossbowMenBackward;
    public Image crossbowMenEast;
    public Image crossbowMenWest;



    public Image maceMenForward;
    public Image maceMenForwardLeft;
    public Image maceMenBackward;
    public Image maceMenBackwardLeft;
    public Image maceMenEast;
    public Image maceMenEastLeft;
    public Image maceMenWest;
    public Image maceMenWestLeft;

    public Image swordsMenForward;
    public Image swordsMenForwardLeft;
    public Image swordsMenBackward;
    public Image swordsMenBackwardLeft;
    public Image swordsMenEast;
    public Image swordsMenEastLeft;
    public Image swordsMenWest;
    public Image swordsMenWestLeft;



    public Image tunnelerForward;
    public Image tunnelerForwardLeft;
    public Image tunnelerBackward;
    public Image tunnelerBackwardLeft;
    public Image tunnelerEast;
    public Image tunnelerEastLeft;
    public Image tunnelerWest;
    public Image tunnelerWestLeft;

    public Image ladderMenForward;
    public Image ladderMenForwardLeft;
    public Image ladderMenBackward;
    public Image ladderMenBackwardLeft;
    public Image ladderMenEast;
    public Image ladderMenEastLeft;
    public Image ladderMenWest;
    public Image ladderMenWestLeft;

    public Image blackMonkForward;
    public Image blackMonkForwardLeft;
    public Image blackMonkBackward;
    public Image blackMonkBackwardLeft;
    public Image blackMonkEast;
    public Image blackMonkEastLeft;
    public Image blackMonkWest;
    public Image blackMonkWestLeft;

    public Image archerBowForward;
    public Image archerBowForwardLeft;
    public Image archerBowBackward;
    public Image archerBowBackwardLeft;
    public Image archerBowEast;
    public Image archerBowEastLeft;
    public Image archerBowWest;
    public Image archerBowWestLeft;

    public Image slavesForward;
    public Image slavesForwardLeft;
    public Image slavesBackward;
    public Image slavesBackwardLeft;
    public Image slavesEast;
    public Image slavesEastLeft;
    public Image slavesWest;
    public Image slavesWestLeft;

    public Image slingersForward;
    public Image slingersForwardLeft;
    public Image slingersBackward;
    public Image slingersBackwardLeft;
    public Image slingersEast;
    public Image slingersEastLeft;
    public Image slingersWest;
    public Image slingersWestLeft;

    public Image assassinsForward;
    public Image assassinsForwardLeft;
    public Image assassinsBackward;
    public Image assassinsBackwardLeft;
    public Image assassinsEast;
    public Image assassinsEastLeft;
    public Image assassinsWest;
    public Image assassinsWestLeft;

    public Image horseArchersForward;
    public Image horseArchersForwardLeft;
    public Image horseArchersBackward;
    public Image horseArchersBackwardLeft;
    public Image horseArchersEast;
    public Image horseArchersEastLeft;
    public Image horseArchersWest;
    public Image horseArchersWestLeft;


    public Image arabianSwordMenForward;
    public Image arabianSwordMenForwardLeft;
    public Image arabianSwordMenBackward;
    public Image arabianSwordMenBackwardLeft;
    public Image arabianSwordMenEast;
    public Image arabianSwordMenEastLeft;
    public Image arabianSwordMenWest;
    public Image arabianSwordMenWestLeft;

    public Image fireBallistaForward;
    public Image fireBallistaForwardLeft;
    public Image fireBallistaBackward;
    public Image fireBallistaBackwardLeft;
    public Image fireBallistaEast;
    public Image fireBallistaEastLeft;
    public Image fireBallistaWest;
    public Image fireBallistaWestLeft;

    public Image batteringRamForward;
    public Image batteringRamForwardLeft;
    public Image batteringRamBackward;
    public Image batteringRamBackwardLeft;
    public Image batteringRamEast;
    public Image batteringRamEastLeft;
    public Image batteringRamWest;
    public Image batteringRamWestLeft;

    public Image engineerForward;
    public Image engineerForwardLeft;
    public Image engineerBackward;
    public Image engineerBackwardLeft;
    public Image engineerEast;
    public Image engineerEastLeft;
    public Image engineerWest;
    public Image engineerWestLeft;



    public Image spearMen;
    public Image pikeMen;
    public Image knight;
    public Image fireThrowers; //TODO : Needs pics
    public Image catapult;
    public Image trebuchet;
    public Image siegeTower; //TODO : Pics
    public Image portableShield;


    public void loadImages() {//make all forwards ---> left

        arabianSwordMenForward = new Image(TileManager.class.getResource("/image/Units/MovePics/arabianSwordMen/forward.png").toExternalForm());
        arabianSwordMenBackward = new Image(TileManager.class.getResource("/image/Units/MovePics/arabianSwordMen/backward.png").toExternalForm());
        arabianSwordMenEast = new Image(TileManager.class.getResource("/image/Units/MovePics/arabianSwordMen/east.png").toExternalForm());
        arabianSwordMenWest = new Image(TileManager.class.getResource("/image/Units/MovePics/arabianSwordMen/west.png").toExternalForm());

        archerForwardLeft = new Image(TileManager.class.getResource("/image/Units/MovePics/archer/forwardLeft.png").toExternalForm());
        archerForwardRight = new Image(TileManager.class.getResource("/image/Units/MovePics/archer/forwardRight.png").toExternalForm());
        archerBackwardRight = new Image(TileManager.class.getResource("/image/Units/MovePics/archer/backwardRight.png").toExternalForm());
        archerBackwardLeft = new Image(TileManager.class.getResource("/image/Units/MovePics/archer/backwardLeft.png").toExternalForm());
        archerEastRight = new Image(TileManager.class.getResource("/image/Units/MovePics/archer/eastRight.png").toExternalForm());
        archerEastLeft = new Image(TileManager.class.getResource("/image/Units/MovePics/archer/eastLeft.png").toExternalForm());
        archerWestRight = new Image(TileManager.class.getResource("/image/Units/MovePics/archer/westRight.png").toExternalForm());
        archerWestLeft = new Image(TileManager.class.getResource("/image/Units/MovePics/archer/westLeft.png").toExternalForm());

        archerBowForward = new Image(TileManager.class.getResource("/image/Units/MovePics/archerBow/forward.png").toExternalForm());
        archerBowBackward = new Image(TileManager.class.getResource("/image/Units/MovePics/archerBow/backward.png").toExternalForm());
        archerBowEast = new Image(TileManager.class.getResource("/image/Units/MovePics/archerBow/east.png").toExternalForm());
        archerBowWest = new Image(TileManager.class.getResource("/image/Units/MovePics/archerBow/west.png").toExternalForm());

        assassinsForward = new Image(TileManager.class.getResource("/image/Units/MovePics/assassins/forward.png").toExternalForm());
        assassinsBackward = new Image(TileManager.class.getResource("/image/Units/MovePics/assassins/backward.png").toExternalForm());
        assassinsEast = new Image(TileManager.class.getResource("/image/Units/MovePics/assassins/east.png").toExternalForm());
        assassinsWest = new Image(TileManager.class.getResource("/image/Units/MovePics/assassins/west.png").toExternalForm());

        batteringRamForward = new Image(TileManager.class.getResource("/image/Units/MovePics/batteringRam/forward.png").toExternalForm());
        batteringRamBackward = new Image(TileManager.class.getResource("/image/Units/MovePics/batteringRam/backward.png").toExternalForm());
        batteringRamEast = new Image(TileManager.class.getResource("/image/Units/MovePics/batteringRam/east.png").toExternalForm());
        batteringRamWest = new Image(TileManager.class.getResource("/image/Units/MovePics/batteringRam/west.png").toExternalForm());

        blackMonkForward = new Image(TileManager.class.getResource("/image/Units/MovePics/blackMonk/forward.png").toExternalForm());
        blackMonkBackward = new Image(TileManager.class.getResource("/image/Units/MovePics/blackMonk/backward.png").toExternalForm());
        blackMonkEast = new Image(TileManager.class.getResource("/image/Units/MovePics/blackMonk/east.png").toExternalForm());
        blackMonkWest = new Image(TileManager.class.getResource("/image/Units/MovePics/blackMonk/west.png").toExternalForm());

        engineerForward = new Image(TileManager.class.getResource("/image/Units/MovePics/engineer/forward.png").toExternalForm());
        engineerBackward = new Image(TileManager.class.getResource("/image/Units/MovePics/engineer/backward.png").toExternalForm());
        engineerEast = new Image(TileManager.class.getResource("/image/Units/MovePics/engineer/east.png").toExternalForm());
        engineerWest = new Image(TileManager.class.getResource("/image/Units/MovePics/engineer/west.png").toExternalForm());

        fireBallistaForward = new Image(TileManager.class.getResource("/image/Units/MovePics/fireBallista/forward.png").toExternalForm());
        fireBallistaBackward = new Image(TileManager.class.getResource("/image/Units/MovePics/fireBallista/backward.png").toExternalForm());
        fireBallistaEast = new Image(TileManager.class.getResource("/image/Units/MovePics/fireBallista/east.png").toExternalForm());
        fireBallistaWest = new Image(TileManager.class.getResource("/image/Units/MovePics/fireBallista/west.png").toExternalForm());

        horseArchersForward = new Image(TileManager.class.getResource("/image/Units/MovePics/horseArchers/forward.png").toExternalForm());
        horseArchersBackward = new Image(TileManager.class.getResource("/image/Units/MovePics/horseArchers/backward.png").toExternalForm());
        horseArchersEast = new Image(TileManager.class.getResource("/image/Units/MovePics/horseArchers/east.png").toExternalForm());
        horseArchersWest = new Image(TileManager.class.getResource("/image/Units/MovePics/horseArchers/west.png").toExternalForm());

        ladderMenForward = new Image(TileManager.class.getResource("/image/Units/MovePics/ladderMen/forward.png").toExternalForm());
        ladderMenBackward = new Image(TileManager.class.getResource("/image/Units/MovePics/ladderMen/backward.png").toExternalForm());
        ladderMenEast = new Image(TileManager.class.getResource("/image/Units/MovePics/ladderMen/east.png").toExternalForm());
        ladderMenWest = new Image(TileManager.class.getResource("/image/Units/MovePics/ladderMen/west.png").toExternalForm());

        maceMenForward = new Image(TileManager.class.getResource("/image/Units/MovePics/maceMen/forward.png").toExternalForm());
        maceMenBackward = new Image(TileManager.class.getResource("/image/Units/MovePics/maceMen/backward.png").toExternalForm());
        maceMenEast = new Image(TileManager.class.getResource("/image/Units/MovePics/maceMen/east.png").toExternalForm());
        maceMenWest = new Image(TileManager.class.getResource("/image/Units/MovePics/maceMen/west.png").toExternalForm());

        slavesForward = new Image(TileManager.class.getResource("/image/Units/MovePics/slaves/forward.png").toExternalForm());
        slavesBackward = new Image(TileManager.class.getResource("/image/Units/MovePics/slaves/backward.png").toExternalForm());
        slavesEast = new Image(TileManager.class.getResource("/image/Units/MovePics/slaves/east.png").toExternalForm());
        slavesWest = new Image(TileManager.class.getResource("/image/Units/MovePics/slaves/west.png").toExternalForm());

        slingersForward = new Image(TileManager.class.getResource("/image/Units/MovePics/slingers/forward.png").toExternalForm());
        slingersBackward = new Image(TileManager.class.getResource("/image/Units/MovePics/slingers/backward.png").toExternalForm());
        slingersEast = new Image(TileManager.class.getResource("/image/Units/MovePics/slingers/east.png").toExternalForm());
        slingersWest = new Image(TileManager.class.getResource("/image/Units/MovePics/slingers/west.png").toExternalForm());

        swordsMenForward = new Image(TileManager.class.getResource("/image/Units/MovePics/swordsMen/forward.png").toExternalForm());
        swordsMenBackward = new Image(TileManager.class.getResource("/image/Units/MovePics/swordsMen/backward.png").toExternalForm());
        swordsMenEast = new Image(TileManager.class.getResource("/image/Units/MovePics/swordsMen/east.png").toExternalForm());
        swordsMenWest = new Image(TileManager.class.getResource("/image/Units/MovePics/swordsMen/west.png").toExternalForm());

        tunnelerForward = new Image(TileManager.class.getResource("/image/Units/MovePics/tunneler/forward.png").toExternalForm());
        tunnelerBackward = new Image(TileManager.class.getResource("/image/Units/MovePics/tunneler/backward.png").toExternalForm());
        tunnelerEast = new Image(TileManager.class.getResource("/image/Units/MovePics/tunneler/east.png").toExternalForm());
        tunnelerWest = new Image(TileManager.class.getResource("/image/Units/MovePics/tunneler/west.png").toExternalForm());


        fireThrowers = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/fireThrowers.png").toExternalForm());
        catapult = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/catapult.png").toExternalForm());
        trebuchet = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/trebuchet.png").toExternalForm());
        siegeTower = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/siegeTower.png").toExternalForm());
        crossbowMenForward = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/crossbowMen.png").toExternalForm());
        spearMen = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/spearMen.png").toExternalForm());
        pikeMen = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/pikeMen.png").toExternalForm());
        knight = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/knight.png").toExternalForm());
        portableShield = new Image(TileManager.class.getResource("/image/Units/IntroductionPics/portableShield.png").toExternalForm());

    }

    public Image getArcherForwardRight() {
        return archerForwardRight;
    }

    public Image getArcherForwardLeft() {
        return archerForwardLeft;
    }

    public Image getArcherBackwardRight() {
        return archerBackwardRight;
    }

    public Image getArcherBackwardLeft() {
        return archerBackwardLeft;
    }

    public Image getArcherEastRight() {
        return archerEastRight;
    }

    public Image getArcherEastLeft() {
        return archerEastLeft;
    }

    public Image getArcherWestRight() {
        return archerWestRight;
    }

    public Image getArcherWestLeft() {
        return archerWestLeft;
    }

    public Image getCrossbowMenForward() {
        return crossbowMenForward;
    }

    public Image getCrossbowMenBackward() {
        return crossbowMenBackward;
    }

    public Image getCrossbowMenEast() {
        return crossbowMenEast;
    }

    public Image getCrossbowMenWest() {
        return crossbowMenWest;
    }

    public Image getMaceMenForward() {
        return maceMenForward;
    }

    public Image getMaceMenForwardLeft() {
        return maceMenForwardLeft;
    }

    public Image getMaceMenBackward() {
        return maceMenBackward;
    }

    public Image getMaceMenBackwardLeft() {
        return maceMenBackwardLeft;
    }

    public Image getMaceMenEast() {
        return maceMenEast;
    }

    public Image getMaceMenEastLeft() {
        return maceMenEastLeft;
    }

    public Image getMaceMenWest() {
        return maceMenWest;
    }

    public Image getMaceMenWestLeft() {
        return maceMenWestLeft;
    }

    public Image getSwordsMenForward() {
        return swordsMenForward;
    }

    public Image getSwordsMenForwardLeft() {
        return swordsMenForwardLeft;
    }

    public Image getSwordsMenBackward() {
        return swordsMenBackward;
    }

    public Image getSwordsMenBackwardLeft() {
        return swordsMenBackwardLeft;
    }

    public Image getSwordsMenEast() {
        return swordsMenEast;
    }

    public Image getSwordsMenEastLeft() {
        return swordsMenEastLeft;
    }

    public Image getSwordsMenWest() {
        return swordsMenWest;
    }

    public Image getSwordsMenWestLeft() {
        return swordsMenWestLeft;
    }

    public Image getTunnelerForward() {
        return tunnelerForward;
    }

    public Image getTunnelerForwardLeft() {
        return tunnelerForwardLeft;
    }

    public Image getTunnelerBackward() {
        return tunnelerBackward;
    }

    public Image getTunnelerBackwardLeft() {
        return tunnelerBackwardLeft;
    }

    public Image getTunnelerEast() {
        return tunnelerEast;
    }

    public Image getTunnelerEastLeft() {
        return tunnelerEastLeft;
    }

    public Image getTunnelerWest() {
        return tunnelerWest;
    }

    public Image getTunnelerWestLeft() {
        return tunnelerWestLeft;
    }

    public Image getLadderMenForward() {
        return ladderMenForward;
    }

    public Image getLadderMenForwardLeft() {
        return ladderMenForwardLeft;
    }

    public Image getLadderMenBackward() {
        return ladderMenBackward;
    }

    public Image getLadderMenBackwardLeft() {
        return ladderMenBackwardLeft;
    }

    public Image getLadderMenEast() {
        return ladderMenEast;
    }

    public Image getLadderMenEastLeft() {
        return ladderMenEastLeft;
    }

    public Image getLadderMenWest() {
        return ladderMenWest;
    }

    public Image getLadderMenWestLeft() {
        return ladderMenWestLeft;
    }

    public Image getBlackMonkForward() {
        return blackMonkForward;
    }

    public Image getBlackMonkForwardLeft() {
        return blackMonkForwardLeft;
    }

    public Image getBlackMonkBackward() {
        return blackMonkBackward;
    }

    public Image getBlackMonkBackwardLeft() {
        return blackMonkBackwardLeft;
    }

    public Image getBlackMonkEast() {
        return blackMonkEast;
    }

    public Image getBlackMonkEastLeft() {
        return blackMonkEastLeft;
    }

    public Image getBlackMonkWest() {
        return blackMonkWest;
    }

    public Image getBlackMonkWestLeft() {
        return blackMonkWestLeft;
    }

    public Image getArcherBowForward() {
        return archerBowForward;
    }

    public Image getArcherBowForwardLeft() {
        return archerBowForwardLeft;
    }

    public Image getArcherBowBackward() {
        return archerBowBackward;
    }

    public Image getArcherBowBackwardLeft() {
        return archerBowBackwardLeft;
    }

    public Image getArcherBowEast() {
        return archerBowEast;
    }

    public Image getArcherBowEastLeft() {
        return archerBowEastLeft;
    }

    public Image getArcherBowWest() {
        return archerBowWest;
    }

    public Image getArcherBowWestLeft() {
        return archerBowWestLeft;
    }

    public Image getSlavesForward() {
        return slavesForward;
    }

    public Image getSlavesForwardLeft() {
        return slavesForwardLeft;
    }

    public Image getSlavesBackward() {
        return slavesBackward;
    }

    public Image getSlavesBackwardLeft() {
        return slavesBackwardLeft;
    }

    public Image getSlavesEast() {
        return slavesEast;
    }

    public Image getSlavesEastLeft() {
        return slavesEastLeft;
    }

    public Image getSlavesWest() {
        return slavesWest;
    }

    public Image getSlavesWestLeft() {
        return slavesWestLeft;
    }

    public Image getSlingersForward() {
        return slingersForward;
    }

    public Image getSlingersForwardLeft() {
        return slingersForwardLeft;
    }

    public Image getSlingersBackward() {
        return slingersBackward;
    }

    public Image getSlingersBackwardLeft() {
        return slingersBackwardLeft;
    }

    public Image getSlingersEast() {
        return slingersEast;
    }

    public Image getSlingersEastLeft() {
        return slingersEastLeft;
    }

    public Image getSlingersWest() {
        return slingersWest;
    }

    public Image getSlingersWestLeft() {
        return slingersWestLeft;
    }

    public Image getAssassinsForward() {
        return assassinsForward;
    }

    public Image getAssassinsForwardLeft() {
        return assassinsForwardLeft;
    }

    public Image getAssassinsBackward() {
        return assassinsBackward;
    }

    public Image getAssassinsBackwardLeft() {
        return assassinsBackwardLeft;
    }

    public Image getAssassinsEast() {
        return assassinsEast;
    }

    public Image getAssassinsEastLeft() {
        return assassinsEastLeft;
    }

    public Image getAssassinsWest() {
        return assassinsWest;
    }

    public Image getAssassinsWestLeft() {
        return assassinsWestLeft;
    }

    public Image getHorseArchersForward() {
        return horseArchersForward;
    }

    public Image getHorseArchersForwardLeft() {
        return horseArchersForwardLeft;
    }

    public Image getHorseArchersBackward() {
        return horseArchersBackward;
    }

    public Image getHorseArchersBackwardLeft() {
        return horseArchersBackwardLeft;
    }

    public Image getHorseArchersEast() {
        return horseArchersEast;
    }

    public Image getHorseArchersEastLeft() {
        return horseArchersEastLeft;
    }

    public Image getHorseArchersWest() {
        return horseArchersWest;
    }

    public Image getHorseArchersWestLeft() {
        return horseArchersWestLeft;
    }

    public Image getArabianSwordMenForward() {
        return arabianSwordMenForward;
    }

    public Image getArabianSwordMenForwardLeft() {
        return arabianSwordMenForwardLeft;
    }

    public Image getArabianSwordMenBackward() {
        return arabianSwordMenBackward;
    }

    public Image getArabianSwordMenBackwardLeft() {
        return arabianSwordMenBackwardLeft;
    }

    public Image getArabianSwordMenEast() {
        return arabianSwordMenEast;
    }

    public Image getArabianSwordMenEastLeft() {
        return arabianSwordMenEastLeft;
    }

    public Image getArabianSwordMenWest() {
        return arabianSwordMenWest;
    }

    public Image getArabianSwordMenWestLeft() {
        return arabianSwordMenWestLeft;
    }

    public Image getFireBallistaForward() {
        return fireBallistaForward;
    }

    public Image getFireBallistaForwardLeft() {
        return fireBallistaForwardLeft;
    }

    public Image getFireBallistaBackward() {
        return fireBallistaBackward;
    }

    public Image getFireBallistaBackwardLeft() {
        return fireBallistaBackwardLeft;
    }

    public Image getFireBallistaEast() {
        return fireBallistaEast;
    }

    public Image getFireBallistaEastLeft() {
        return fireBallistaEastLeft;
    }

    public Image getFireBallistaWest() {
        return fireBallistaWest;
    }

    public Image getFireBallistaWestLeft() {
        return fireBallistaWestLeft;
    }

    public Image getBatteringRamForward() {
        return batteringRamForward;
    }

    public Image getBatteringRamForwardLeft() {
        return batteringRamForwardLeft;
    }

    public Image getBatteringRamBackward() {
        return batteringRamBackward;
    }

    public Image getBatteringRamBackwardLeft() {
        return batteringRamBackwardLeft;
    }

    public Image getBatteringRamEast() {
        return batteringRamEast;
    }

    public Image getBatteringRamEastLeft() {
        return batteringRamEastLeft;
    }

    public Image getBatteringRamWest() {
        return batteringRamWest;
    }

    public Image getBatteringRamWestLeft() {
        return batteringRamWestLeft;
    }

    public Image getEngineerForward() {
        return engineerForward;
    }

    public Image getEngineerForwardLeft() {
        return engineerForwardLeft;
    }

    public Image getEngineerBackward() {
        return engineerBackward;
    }

    public Image getEngineerBackwardLeft() {
        return engineerBackwardLeft;
    }

    public Image getEngineerEast() {
        return engineerEast;
    }

    public Image getEngineerEastLeft() {
        return engineerEastLeft;
    }

    public Image getEngineerWest() {
        return engineerWest;
    }

    public Image getEngineerWestLeft() {
        return engineerWestLeft;
    }

    public Image getSpearMen() {
        return spearMen;
    }

    public Image getPikeMen() {
        return pikeMen;
    }

    public Image getKnight() {
        return knight;
    }

    public Image getFireThrowers() {
        return fireThrowers;
    }

    public Image getCatapult() {
        return catapult;
    }

    public Image getTrebuchet() {
        return trebuchet;
    }

    public Image getSiegeTower() {
        return siegeTower;
    }

    public Image getPortableShield() {
        return portableShield;
    }
}

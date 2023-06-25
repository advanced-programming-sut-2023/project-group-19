package view.GameButtons;

import controller.GameController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Building.Building;
import model.Manage;
import view.GameMenu;
import view.ImageAndBackground.BuildingImages;
import view.ImageAndBackground.GameImages;
import view.ImageAndBackground.UnitImages;
import view.Model.NewButton;
import view.RegisterMenu;
import view.TileManager;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;

public class DropUnitDesign {
    public UnitImages unitImages = new UnitImages();
    public BuildingImages buildingImages = new BuildingImages();
    public GameImages gameImages = new GameImages();
    public static boolean closeDropUnit = false;
    public int controllerOfDropUnit = 1;
    public HBox hBox;
    public boolean isFive = true;

    public void designHBoxForDropUnit(Pane pane, GameController gameController, ArrayList<NewButton> selectedButtons) {
        unitImages.loadImages();
        buildingImages.loadImage();
        gameImages.loadImages();
        hBox = new HBox();
        BackgroundImage map = new BackgroundImage(new Image(GameController.class.
                getResource("/image/GameMenu/map.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        hBox.setBackground(new Background(map));
        hBox.setPrefSize(800, 300);
        hBox.setLayoutX(350);
        hBox.setLayoutY(150);

        Button prev = new Button();
        ImageView returnIconImage = new ImageView(buildingImages.getReturnIcon());
        prev.setBackground(null);
        returnIconImage.setFitHeight(40);
        returnIconImage.setFitWidth(40);
        prev.setGraphic(returnIconImage);
        hBox.getChildren().add(prev);
        prev.setTranslateX(150);
        prev.setTranslateY(240);
        prev.setMinSize(40, 40);
        prev.setPrefSize(40, 40);

        Button next = new Button();
        ImageView nextIconImage = new ImageView(gameImages.getNext());
        next.setBackground(null);
        nextIconImage.setFitHeight(40);
        nextIconImage.setFitWidth(40);
        next.setGraphic(nextIconImage);
        hBox.getChildren().add(next);
        next.setTranslateX(565);
        next.setTranslateY(240);
        next.setMinSize(40, 40);
        next.setPrefSize(40, 40);

        Button done = new Button();
        ImageView doneIconImage = new ImageView(gameImages.getDone());
        done.setBackground(null);
        doneIconImage.setFitHeight(40);
        doneIconImage.setFitWidth(40);
        done.setGraphic(doneIconImage);
        hBox.getChildren().add(done);
        done.setTranslateX(300);
        done.setTranslateY(240);
        done.setMinSize(40, 40);
        done.setPrefSize(40, 40);

        ArrayList<Spinner<Integer>> spinners = createSpinnersForUnits();
        ArrayList<ImageView> imageViews = setTheLocationOfImages();
        makeTheDefaultViewOfDrop(spinners, imageViews, hBox);
        prev.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
                TileManager.gameLog.append(TileManager.time + '#' + "PREV_DROP_UNIT" + '\n');
                if (controllerOfDropUnit > 1) {
                    if (controllerOfDropUnit == 5) {
                        for (int i = 1; i <= 6; i++) {
                            hBox.getChildren().remove(hBox.getChildren().size() - 1);
                        }
                        for (int j = 15; j <= 19; j++) {
                            hBox.getChildren().add(imageViews.get(j));
                        }
                        for (int k = 15; k <= 19; k++) {
                            hBox.getChildren().add(spinners.get(k));
                        }
                    } else {
                        for (int i = 1; i <= 10; i++) {
                            hBox.getChildren().remove(hBox.getChildren().size() - 1);
                        }
                        int firstIndex = (controllerOfDropUnit - 2) * 5;
                        int lastIndex = (controllerOfDropUnit - 1) * 5 - 1;
                        for (int j = firstIndex; j <= lastIndex; j++) {
                            hBox.getChildren().add(imageViews.get(j));
                        }
                        for (int k = firstIndex; k <= lastIndex; k++) {
                            hBox.getChildren().add(spinners.get(k));
                        }
                    }
                    isFive = true;
                    controllerOfDropUnit--;
                }
            }
        });

        next.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
                TileManager.gameLog.append(TileManager.time + '#' + "NEXT_DROP_UNIT" + '\n');
                if (controllerOfDropUnit < 5) {
                    if (controllerOfDropUnit == 4) {
                        isFive = false;
                        for (int i = 1; i <= 10; i++) {
                            hBox.getChildren().remove(hBox.getChildren().size() - 1);
                        }
                        for (int j = 20; j <= 22; j++) {
                            hBox.getChildren().add(imageViews.get(j));
                        }
                        for (int k = 20; k <= 22; k++) {
                            hBox.getChildren().add(spinners.get(k));
                        }

                    } else {
                        for (int i = 1; i <= 10; i++) {
                            hBox.getChildren().remove(hBox.getChildren().size() - 1);

                        }
                        int firstIndex = controllerOfDropUnit * 5;
                        int lastIndex = ((controllerOfDropUnit + 1) * 5) - 1;
                        for (int j = firstIndex; j <= lastIndex; j++) {
                            hBox.getChildren().add(imageViews.get(j));
                        }
                        for (int k = firstIndex; k <= lastIndex; k++) {
                            hBox.getChildren().add(spinners.get(k));
                        }
                    }
                    controllerOfDropUnit++;
                }
            }
        });
        if(closeDropUnit){
            pane.getChildren().remove(hBox);
            for (int i = 0; i < spinners.size(); i++) {
                if(spinners.get(i).getValue() != 0)
                    gameController.dropUnits(selectedButtons.get(0).getX(), selectedButtons.get(0).getY()
                            , i, spinners.get(i).getValue(), selectedButtons.get(0));
            }
            closeDropUnit = false;
        }
        done.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                playSoundEffect("dropUnit.mp3");
//                closeDropUnit(pane , hBox , spinners , gameController , selectedButtons);
                pane.getChildren().remove(hBox);
                for (int i = 0; i < spinners.size(); i++) {
                    if(spinners.get(i).getValue() != 0)
                        gameController.dropUnits(selectedButtons.get(0).getX(), selectedButtons.get(0).getY()
                                , i, spinners.get(i).getValue(), selectedButtons.get(0));
                }
                TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
                TileManager.gameLog.append(TileManager.time + '#' + "CLOSE_DROP_UNIT" + '\n');

            }

        });
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                pane.getChildren().add(hBox);
            }
        });

    }
    public MediaPlayer mediaPlayer ;
    private void playSoundEffect(String name) {
        String defultSong  = RegisterMenu.class.getResource("/Music/" + name).toString();
        Media media = new Media(defultSong);
        MediaPlayer mediaPlayer2 = new MediaPlayer(media);
        mediaPlayer = mediaPlayer2;
        mediaPlayer2.setAutoPlay(true);
    }

    private void makeTheDefaultViewOfDrop(ArrayList<Spinner<Integer>> spinners, ArrayList<ImageView> imageViews, HBox hBox) {
        for (int i = 0; i < 5; i++) {
            hBox.getChildren().add(imageViews.get(i));
        }
        for (int j = 0; j < 5; j++) {
            hBox.getChildren().add(spinners.get(j));
        }
    }

    public HBox gethBox() {
        return hBox;
    }

    private ArrayList<Spinner<Integer>> createSpinnersForUnits() {
        ArrayList<Spinner<Integer>> spinners = new ArrayList<>();

        Spinner<Integer> archer = new Spinner<>(0, Manage.getCurrentEmpire().getEuropeArcherCount(), 0);
        Spinner<Integer> crossbowMen = new Spinner<>(0, Manage.getCurrentEmpire().getCrossbowManCount(), 0);
        Spinner<Integer> spearMen = new Spinner<>(0, Manage.getCurrentEmpire().getSpearCount(), 0);
        Spinner<Integer> pikeMen = new Spinner<>(0, Manage.getCurrentEmpire().getPikeManCount(), 0);
        Spinner<Integer> maceMen = new Spinner<>(0, Manage.getCurrentEmpire().getMaceManCount(), 0);

        Spinner<Integer> swordsMen = new Spinner<>(0, Manage.getCurrentEmpire().getSwordManCount(), 0);
        Spinner<Integer> knight = new Spinner<>(0, Manage.getCurrentEmpire().getKnightCount(), 0);
        Spinner<Integer> tunneler = new Spinner<>(0, Manage.getCurrentEmpire().getTunnelerCount(), 0);
        Spinner<Integer> ladderMen = new Spinner<>(0, Manage.getCurrentEmpire().getLadderManCount(), 0);
        Spinner<Integer> blackMonk = new Spinner<>(0, Manage.getCurrentEmpire().getBlackMonkCount(), 0);

        Spinner<Integer> archerBow = new Spinner<>(0, Manage.getCurrentEmpire().getArabianBowCount(), 0);
        Spinner<Integer> slaves = new Spinner<>(0, Manage.getCurrentEmpire().getSlaveCount(), 0);
        Spinner<Integer> slingers = new Spinner<>(0, Manage.getCurrentEmpire().getSlingerCount(), 0);
        Spinner<Integer> assassins = new Spinner<>(0, Manage.getCurrentEmpire().getAssassinCount(), 0);
        Spinner<Integer> horseArchers = new Spinner<>(0, Manage.getCurrentEmpire().getHorseArcherCount(), 0);

        Spinner<Integer> arabianSwordMen = new Spinner<>(0, Manage.getCurrentEmpire().getArabianSwordManCount(), 0);
        Spinner<Integer> fireThrowers = new Spinner<>(0, Manage.getCurrentEmpire().getFireThrowerCount(), 0);
        Spinner<Integer> catapult = new Spinner<>(0, Manage.getCurrentEmpire().getCatapultCount(), 0);
        Spinner<Integer> trebuchet = new Spinner<>(0, Manage.getCurrentEmpire().getTrebuchetCount(), 0);
        Spinner<Integer> siegeTower = new Spinner<>(0, Manage.getCurrentEmpire().getSiegeTowerCount(), 0);

        Spinner<Integer> fireBallista = new Spinner<>(0, Manage.getCurrentEmpire().getFireBalistaCount(), 0);
        Spinner<Integer> batteringRam = new Spinner<>(0, Manage.getCurrentEmpire().getBatteringRamCount(), 0);
        Spinner<Integer> portableShield = new Spinner<>(0, Manage.getCurrentEmpire().getPortableShieldCount(), 0);

        archer.setTranslateX(-200);
        archer.setTranslateY(170);
        archer.setPrefSize(52, 20);
        archer.setEditable(true);
        crossbowMen.setTranslateX(-150);
        crossbowMen.setTranslateY(170);
        crossbowMen.setPrefSize(52, 20);
        crossbowMen.setEditable(true);
        spearMen.setTranslateX(-110);
        spearMen.setTranslateY(170);
        spearMen.setPrefSize(52, 20);
        spearMen.setEditable(true);
        pikeMen.setTranslateX(-60);
        pikeMen.setTranslateY(170);
        pikeMen.setPrefSize(52, 20);
        pikeMen.setEditable(true);
        maceMen.setTranslateX(-10);
        maceMen.setTranslateY(170);
        maceMen.setPrefSize(52, 20);
        maceMen.setEditable(true);

        swordsMen.setTranslateX(-200);
        swordsMen.setTranslateY(170);
        swordsMen.setPrefSize(52, 20);
        swordsMen.setEditable(true);
        knight.setTranslateX(-150);
        knight.setTranslateY(170);
        knight.setPrefSize(52, 20);
        knight.setEditable(true);
        tunneler.setTranslateX(-110);
        tunneler.setTranslateY(170);
        tunneler.setPrefSize(52, 20);
        tunneler.setEditable(true);
        ladderMen.setTranslateX(-60);
        ladderMen.setTranslateY(170);
        ladderMen.setPrefSize(52, 20);
        ladderMen.setEditable(true);
        blackMonk.setTranslateX(-10);
        blackMonk.setTranslateY(170);
        blackMonk.setPrefSize(52, 20);
        blackMonk.setEditable(true);

        archerBow.setTranslateX(-200);
        archerBow.setTranslateY(170);
        archerBow.setPrefSize(52, 20);
        archerBow.setEditable(true);
        slaves.setTranslateX(-150);
        slaves.setTranslateY(170);
        slaves.setPrefSize(52, 20);
        slaves.setEditable(true);
        slingers.setTranslateX(-110);
        slingers.setTranslateY(170);
        slingers.setPrefSize(52, 20);
        slingers.setEditable(true);
        assassins.setTranslateX(-60);
        assassins.setTranslateY(170);
        assassins.setPrefSize(52, 20);
        assassins.setEditable(true);
        horseArchers.setTranslateX(-10);
        horseArchers.setTranslateY(170);
        horseArchers.setPrefSize(52, 20);
        horseArchers.setEditable(true);

        arabianSwordMen.setTranslateX(-200);
        arabianSwordMen.setTranslateY(170);
        arabianSwordMen.setPrefSize(52, 20);
        arabianSwordMen.setEditable(true);
        fireThrowers.setTranslateX(-150);
        fireThrowers.setTranslateY(170);
        fireThrowers.setPrefSize(52, 20);
        fireThrowers.setEditable(true);
        catapult.setTranslateX(-110);
        catapult.setTranslateY(170);
        catapult.setPrefSize(52, 20);
        catapult.setEditable(true);
        trebuchet.setTranslateX(-60);
        trebuchet.setTranslateY(170);
        trebuchet.setPrefSize(52, 20);
        trebuchet.setEditable(true);
        siegeTower.setTranslateX(-10);
        siegeTower.setTranslateY(170);
        siegeTower.setPrefSize(52, 20);
        siegeTower.setEditable(true);

        fireBallista.setTranslateX(-100);
        fireBallista.setTranslateY(170);
        fireBallista.setPrefSize(52, 20);
        fireBallista.setEditable(true);
        batteringRam.setTranslateX(-50);
        batteringRam.setTranslateY(170);
        batteringRam.setPrefSize(52, 20);
        batteringRam.setEditable(true);
        portableShield.setTranslateX(10);
        portableShield.setTranslateY(170);
        portableShield.setPrefSize(52, 20);
        portableShield.setEditable(true);


        spinners.add(archer);
        spinners.add(crossbowMen);
        spinners.add(spearMen);
        spinners.add(pikeMen);
        spinners.add(maceMen);

        spinners.add(swordsMen);
        spinners.add(knight);
        spinners.add(tunneler);
        spinners.add(ladderMen);
        spinners.add(blackMonk);

        spinners.add(archerBow);
        spinners.add(slaves);
        spinners.add(slingers);
        spinners.add(assassins);
        spinners.add(horseArchers);

        spinners.add(arabianSwordMen);
        spinners.add(fireThrowers);
        spinners.add(catapult);
        spinners.add(trebuchet);
        spinners.add(siegeTower);

        spinners.add(fireBallista);
        spinners.add(batteringRam);
        spinners.add(portableShield);

        return spinners;
    }

    private ArrayList<ImageView> setTheLocationOfImages() {
        ImageView archer = new ImageView(unitImages.archer);
        ImageView crossbowMen = new ImageView(unitImages.crossbowMen);
        ImageView spearMen = new ImageView(unitImages.spearMen);
        ImageView pikeMen = new ImageView(unitImages.pikeMen);
        ImageView maceMen = new ImageView(unitImages.maceMen);

        ImageView swordsMen = new ImageView(unitImages.swordsMen);
        ImageView knight = new ImageView(unitImages.knight);
        ImageView tunneler = new ImageView(unitImages.tunneler);
        ImageView ladderMen = new ImageView(unitImages.ladderMen);
        ImageView blackMonk = new ImageView(unitImages.blackMonk);

        ImageView archerBow = new ImageView(unitImages.archerBow);
        ImageView slaves = new ImageView(unitImages.slaves);
        ImageView slingers = new ImageView(unitImages.slingers);
        ImageView assassins = new ImageView(unitImages.assassins);
        ImageView horseArchers = new ImageView(unitImages.horseArchers);

        ImageView arabianSwordMen = new ImageView(unitImages.arabianSwordMen);
        ImageView fireThrowers = new ImageView(unitImages.fireThrowers);
        ImageView catapult = new ImageView(unitImages.catapult);
        ImageView trebuchet = new ImageView(unitImages.trebuchet);
        ImageView siegeTower = new ImageView(unitImages.siegeTower);

        ImageView fireBallista = new ImageView(unitImages.fireBallista);
        ImageView batteringRam = new ImageView(unitImages.batteringRam);
        ImageView portableShield = new ImageView(unitImages.portableShield);

        archer.setTranslateX(50);
        archer.setTranslateY(50);
        archer.setFitWidth(50);
        archer.setFitHeight(80);
        crossbowMen.setTranslateX(100);
        crossbowMen.setTranslateY(50);
        crossbowMen.setFitWidth(50);
        crossbowMen.setFitHeight(80);
        spearMen.setTranslateX(150);
        spearMen.setTranslateY(50);
        spearMen.setFitWidth(50);
        spearMen.setFitHeight(80);
        pikeMen.setTranslateX(200);
        pikeMen.setTranslateY(50);
        pikeMen.setFitWidth(50);
        pikeMen.setFitHeight(80);
        maceMen.setTranslateX(250);
        maceMen.setTranslateY(50);
        maceMen.setFitWidth(50);
        maceMen.setFitHeight(80);

        swordsMen.setTranslateX(50);
        swordsMen.setTranslateY(50);
        swordsMen.setFitWidth(50);
        swordsMen.setFitHeight(80);
        knight.setTranslateX(100);
        knight.setTranslateY(50);
        knight.setFitWidth(50);
        knight.setFitHeight(80);
        tunneler.setTranslateX(150);
        tunneler.setTranslateY(50);
        tunneler.setFitWidth(50);
        tunneler.setFitHeight(80);
        ladderMen.setTranslateX(200);
        ladderMen.setTranslateY(50);
        ladderMen.setFitWidth(50);
        ladderMen.setFitHeight(80);
        blackMonk.setTranslateX(250);
        blackMonk.setTranslateY(50);
        blackMonk.setFitWidth(50);
        blackMonk.setFitHeight(80);

        archerBow.setTranslateX(50);
        archerBow.setTranslateY(50);
        archerBow.setFitWidth(50);
        archerBow.setFitHeight(80);
        slaves.setTranslateX(100);
        slaves.setTranslateY(50);
        slaves.setFitWidth(50);
        slaves.setFitHeight(80);
        slingers.setTranslateX(150);
        slingers.setTranslateY(50);
        slingers.setFitWidth(50);
        slingers.setFitHeight(80);
        assassins.setTranslateX(200);
        assassins.setTranslateY(50);
        assassins.setFitWidth(50);
        assassins.setFitHeight(80);
        horseArchers.setTranslateX(250);
        horseArchers.setTranslateY(50);
        horseArchers.setFitWidth(50);
        horseArchers.setFitHeight(80);

        arabianSwordMen.setTranslateX(50);
        arabianSwordMen.setTranslateY(50);
        arabianSwordMen.setFitWidth(50);
        arabianSwordMen.setFitHeight(80);
        fireThrowers.setTranslateX(100);
        fireThrowers.setTranslateY(50);
        fireThrowers.setFitWidth(50);
        fireThrowers.setFitHeight(80);
        catapult.setTranslateX(150);
        catapult.setTranslateY(50);
        catapult.setFitWidth(50);
        catapult.setFitHeight(80);
        trebuchet.setTranslateX(200);
        trebuchet.setTranslateY(50);
        trebuchet.setFitWidth(50);
        trebuchet.setFitHeight(80);
        siegeTower.setTranslateX(250);
        siegeTower.setTranslateY(50);
        siegeTower.setFitWidth(50);
        siegeTower.setFitHeight(80);

        fireBallista.setTranslateX(50);
        fireBallista.setTranslateY(50);
        fireBallista.setFitWidth(50);
        fireBallista.setFitHeight(80);
        batteringRam.setTranslateX(100);
        batteringRam.setTranslateY(50);
        batteringRam.setFitWidth(50);
        batteringRam.setFitHeight(80);
        portableShield.setTranslateX(150);
        portableShield.setTranslateY(50);
        portableShield.setFitWidth(50);
        portableShield.setFitHeight(80);

        ArrayList<ImageView> imageViews = new ArrayList<>();
        imageViews.add(archer);
        imageViews.add(crossbowMen);
        imageViews.add(spearMen);
        imageViews.add(pikeMen);
        imageViews.add(maceMen);
        imageViews.add(swordsMen);
        imageViews.add(knight);
        imageViews.add(tunneler);
        imageViews.add(ladderMen);
        imageViews.add(blackMonk);
        imageViews.add(archerBow);
        imageViews.add(slaves);
        imageViews.add(slingers);
        imageViews.add(assassins);
        imageViews.add(horseArchers);
        imageViews.add(arabianSwordMen);
        imageViews.add(fireThrowers);
        imageViews.add(catapult);
        imageViews.add(trebuchet);
        imageViews.add(siegeTower);
        imageViews.add(fireBallista);
        imageViews.add(batteringRam);
        imageViews.add(portableShield);
        return imageViews;
    }
}

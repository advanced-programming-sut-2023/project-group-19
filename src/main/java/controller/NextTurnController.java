package controller;

import controller.Building.BuildingController;
import controller.Building.FunctionBuildingController;
import controller.Building.SelectedBuildingController;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import model.Empire;
import model.Manage;
import model.Map;
import model.User;
import view.Animations.troopFights.SwordManAnimation.SwordManAnimation;
import view.GameMenu;
import view.Model.NewButton;
import view.RegisterMenu;
import view.TileManager;

import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

public class NextTurnController {
    public Map map;
    public static Empire currentEmpire;
    public TileManager tileManager;
    public static int index = 1 ;
    public int mapSize = Map.mapSize ;
    public AttackArmyToArmyController attackArmyToArmyController ;

    public void nextTurn(){
        System.out.println(Manage.getAllEmpires().size());
        System.out.println(Manage.getCurrentEmpire().getUser().getUsername());
        if (Manage.allEmpires.size() != 1) {
            GameController gameController = tileManager.gameController;
            findCurrentEmpire();
            callStartingTurnFunctions(gameController);
            callEndingTurnFunctions();
        }
        else {
            User user = Manage.getAllEmpires().get(0).getUser();
            int oldScore = user.getHighScore();
            int newScore = oldScore + 100;
            user.setHighScore(newScore);
            Collections.sort(User.users);
            index = 0;
        }
    }

    public void findCurrentEmpire() {
        Manage.setCurrentEmpire(Manage.allEmpires.get(index));
        currentEmpire = Manage.allEmpires.get(index);
        index = ++index % Manage.allEmpires.size();
        BuildingController.currentEmpire = currentEmpire;
        FunctionBuildingController.empire = currentEmpire;
        SelectedBuildingController.empire = currentEmpire;
    }
    //TODO : check set enemy to target
    public void callStartingTurnFunctions(GameController gameController) {
        attackArmyToArmyController.clearAllAnimationsArrayList();
        currentEmpire.setSickness(Math.random() < 0.5);
        putGraphicSickEmpire(currentEmpire);
        empireTotalPopularity();
        sicknessLogic();
        buildingFire();
        EmpireController.setFearFactor();
        EmpireController.taxImpactOnEmpire(currentEmpire, currentEmpire.getTaxRateNumber());
//        currentEmpire.independentProductionBuilding();
        EmpireController.functionBuildings();
        EmpireController.findFoodDiversity();
        EmpireController.givingPeopleFood(currentEmpire);
        resetTroopsMovesLeft();
    }
    public void attackBanner(){
        System.out.println(attackArmyToArmyController.isInAttack);
        if(!attackArmyToArmyController.isInAttack) return;
        designAttackBanner();

    }
    public MediaPlayer mediaPlayer ;
    private void playLoginMusic(){
        String defultSong  = RegisterMenu.class.getResource("/Music/warScream.mp3").toString();
        Media media = new Media(defultSong);
        MediaPlayer mediaPlayer2 = new MediaPlayer(media);
        mediaPlayer = mediaPlayer2 ;
        mediaPlayer2.setAutoPlay(true);
    }
    public void designAttackBanner(){
        playLoginMusic();
        HBox hBox = new HBox();
        hBox.setSpacing(50);
        hBox.setAlignment(Pos.CENTER);
        BackgroundImage map = new BackgroundImage(new Image(GameController.class.
                getResource("/image/GameMenu/map.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        hBox.setBackground(new Background(map));
        hBox.setPrefSize(300,60);
        hBox.setLayoutX(620);
        hBox.setOpacity(0.9);
        hBox.setLayoutY(3);
        Label label = new Label();
        label.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 26));
        label.setText("attack mode");
        hBox.getChildren().add(label);
        tileManager.pane.getChildren().add(hBox);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(6), actionEvent -> {
            tileManager.pane.getChildren().remove(hBox);
        }));
        timeline.play();
    }
    private void putGraphicSickEmpire(Empire empire) {
        if(!empire.isSickness()) return;
        int x = empire.castleXCoordinate ;
        int y = empire.castleYCCoordinate ;
        int x1, x2, y1, y2;
        x1 = x - 1;
        x2 = x + 1;
        y1 = y - 1;
        y2 = y + 1;
        if (x1 <= 0) x1 = 0;
        if (x2 >= Map.mapSize) x2 = Map.mapSize - 1;
        if (y1 <= 0) y1 = 0;
        if (y2 >= Map.mapSize) y2 = Map.mapSize - 1;
        for(int i = x1 ; i <= x2 ; i ++){
            for(int j = y1 ; j <= y2 ; j ++){
                NewButton newButton = ((NewButton)(tileManager.list.get(100 * i + j)));
                if(newButton.getImageView() != null || !newButton.getArmy().isEmpty()) continue;
                newButton.setSickButton(true);
            }
        }
    }
    public void buildingFire(){
        for(int i = 0 ; i < Manage.burningEmpires.size() ; i++ ){
            if(Manage.burningEmpires.get(i).isOnFire() && Manage.burningEmpires.get(i).getFireCount() != 0){
                Manage.burningEmpires.get(i).setHp(Manage.burningEmpires.get(i).getHp() - 20);
                Manage.burningEmpires.get(i).setFireCount(Manage.burningEmpires.get(i).getFireCount() - 1);
            }
            if(Manage.burningEmpires.get(i).getFireCount() == 0){
                Manage.burningEmpires.remove(i);
                i--;
            }
        }
    }
    private void startFightAnimations(){
        SequentialTransition sequentialTransitionSwordMan = new SequentialTransition(attackArmyToArmyController.swordManAnimation, attackArmyToArmyController.swordManDeadAnimation);
        sequentialTransitionSwordMan.play();

        SequentialTransition sequentialTransitionSlave = new SequentialTransition(attackArmyToArmyController.slaveAnimation, attackArmyToArmyController.deadSlaveAnimation);
        sequentialTransitionSlave.play();

        SequentialTransition sequentialTransitionAssasin = new SequentialTransition(attackArmyToArmyController.asssasinAnimation, attackArmyToArmyController.deadAssasinAnimation);
        sequentialTransitionAssasin.play();

        SequentialTransition sequentialTransitionMaceMan = new SequentialTransition(attackArmyToArmyController.maceManAnimation, attackArmyToArmyController.deadMaceManAnimation);
        sequentialTransitionMaceMan.play();

        SequentialTransition sequentialTransitionMonk = new SequentialTransition(attackArmyToArmyController.monkAnimation, attackArmyToArmyController.deadMonkAnimation);
        sequentialTransitionMonk.play();

        SequentialTransition sequentialTransitionShortBow = new SequentialTransition(attackArmyToArmyController.shortBowAnimation, attackArmyToArmyController.deadShortBowAnimation);
        sequentialTransitionShortBow.play();

        SequentialTransition sequentialTransitionSlinger = new SequentialTransition(attackArmyToArmyController.slingerAnimation, attackArmyToArmyController.deadSlingerAnimation);
        sequentialTransitionSlinger.play();

        SequentialTransition sequentialTransitionArcher = new SequentialTransition(attackArmyToArmyController.archerAnimation, attackArmyToArmyController.deadArcherAnimation);
        sequentialTransitionArcher.play();

        SequentialTransition sequentialTransitionHorseRider = new SequentialTransition(attackArmyToArmyController.horseRiderAnimation, attackArmyToArmyController.deadHorseRiderAnimation);
        sequentialTransitionHorseRider.play();

        SequentialTransition sequentialTransitiongrendiar = new SequentialTransition(attackArmyToArmyController.grendiarAnimation,attackArmyToArmyController.deadGrendiarAnimation);
        sequentialTransitiongrendiar.play();
    }
    public void empireTotalPopularity(){
        int popularity = Manage.getCurrentEmpire().getTotalPopularity();
        int popularityChange = Manage.getCurrentEmpire().getPopularity();
        if(popularity + popularityChange > 100){
            Manage.getCurrentEmpire().setTotalPopularity(100);
        }
        else if(popularity + popularityChange < 0){
            Manage.getCurrentEmpire().setTotalPopularity(0);
        }
        else{
            Manage.getCurrentEmpire().setTotalPopularity(popularity + popularityChange);
        }
    }

    public void callEndingTurnFunctions() {
        attackArmyToArmyController.battleWithEnemy();
        attackBanner();
        playerHasLost();
        startFightAnimations();
    }
    public void sicknessLogic(){
        if (Manage.getCurrentEmpire().getApothecary()) {
            Manage.getCurrentEmpire().setSickness(false);
            Manage.getCurrentEmpire().setSicknessImpactOnPopularity(0);
        }
        if (Manage.getCurrentEmpire().isSickness())
            Manage.getCurrentEmpire().setSicknessImpactOnPopularity(-2);
    }

    public void playerHasLost() {
        int size = Manage.allEmpires.size();
        for (int i = 0; i < size; i++) {
            Empire empire = Manage.allEmpires.get(i);
            boolean isDestroyed = map.getBuildingMap()[empire.castleXCoordinate][empire.castleYCCoordinate].isEmpty();
//            if (isDestroyed) {
//                System.out.println("desyroy");
//                GameController.removeEmpireTroopsFromGame(currentEmpire);
//                Manage.allEmpires.remove(i);
////                if (index != 0) NextTurnController.index--;
//                if(i == size - 1) index = 0 ;
//                else if(index > i) index -- ;
//                i--;
//                size--;
//            }
        }
    }

    public void resetTroopsMovesLeft() {
        for (int i = 0; i < Manage.allEmpires.size(); i++) {
            Empire empire = Manage.allEmpires.get(i);
            for (int j = 0; j < empire.empireArmy.size(); j++) {
                empire.empireArmy.get(j).restOfMoves = empire.empireArmy.get(j).speed();
            }
        }
    }
}

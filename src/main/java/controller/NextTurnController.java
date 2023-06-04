package controller;

import controller.Building.BuildingController;
import controller.Building.FunctionBuildingController;
import controller.Building.SelectedBuildingController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Empire;
import model.Manage;
import model.Map;
import model.User;
import view.GameMenu;
import view.Model.NewButton;
import view.TileManager;

import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

public class NextTurnController {
    public static Empire currentEmpire;
    public static int index;
    public int mapSize = 200 ;
    public TileManager tileManager ;

//    public String game(Scanner scanner) throws IOException, InterruptedException {

//        while (true) {
//          if(Manage.allEmpires.size() == 0){
//                //mosavi
//            }
//            if (Manage.allEmpires.size() != 1) {
//                GameController gameController = new GameController();
//                setGameController(gameController);
//                findCurrentEmpire();
//                System.out.println("Current Empire To Play : " + Manage.getCurrentEmpire().getName());
//                callStartingTurnFunctions(gameController);
//                GameMenu gameMenu = new GameMenu();
//                gameMenu.run(scanner);
//                callEndingTurnFunctions(gameController);
//
//            } else {
//                User user = Manage.getAllEmpires().get(0).getUser();
//                int oldScore = user.getHighScore();
//                int newScore = oldScore + 100;
//                user.setHighScore(newScore);
//                Collections.sort(User.users);
//                index = 0;
//                return ("Winner is: " + Manage.allEmpires.get(0).getName());
//            }
//        }
//    }

    public void findCurrentEmpire() {
        Manage.setCurrentEmpire(Manage.allEmpires.get(index));
        currentEmpire = Manage.allEmpires.get(index);
        index = ++index % Manage.allEmpires.size();
        BuildingController.currentEmpire = currentEmpire;
        FunctionBuildingController.empire = currentEmpire;
        SelectedBuildingController.empire = currentEmpire;
        //tradeController.currentEmpire = currentEmpire;
    }

    public void callStartingTurnFunctions(GameController gameController) {
        currentEmpire.setSickness(Math.random() < 0.5);
        putGraphicSickEmpire(currentEmpire);
        buildingFire();
        EmpireController.setFearFactor();
        EmpireController.taxImpactOnEmpire(currentEmpire, currentEmpire.getTaxRateNumber());
        currentEmpire.independentProductionBuilding();
        EmpireController.functionBuildings();
        EmpireController.findFoodDiversity();
        EmpireController.givingPeopleFood(currentEmpire);
        gameController.setEnemyToTarget();
        resetTroopsMovesLeft();
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
        if (x2 >= mapSize) x2 = mapSize - 1;
        if (y1 <= 0) y1 = 0;
        if (y2 >= mapSize) y2 = mapSize - 1;
        for(int i = x1 ; i <= x2 ; i ++){
            for(int j = y1 ; j <= y2 ; j ++){
                NewButton newButton = ((NewButton)(tileManager.list.get(100 * i + j)));
                if(newButton.getImageView() != null || !newButton.getArmy().isEmpty()) continue;
                newButton.setSickButton(true);
            }
        }
    }

    //TODO : remove destroyed buildings
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

    public void setGameController(GameController gameController) {
        GameController.gameController = gameController;
        GameMenu.gameController = gameController;
    }


//    public void callEndingTurnFunctions(GameController gameController) {
//        gameController.DrawBridge();
//        gameController.cagedWarDogsAttack();
//        gameController.setStateArmy();
//        AttackArmyToArmyController.setFightMode(gameController);
//        gameController.fight();
//        playerHasLost();
//    }

    public void playerHasLost() {
        int size = Manage.allEmpires.size();
        for (int i = 0; i < size; i++) {
            Empire empire = Manage.allEmpires.get(i);
            boolean isDestroyed = Map.getBuildingMap()[empire.castleXCoordinate][empire.castleYCCoordinate].isEmpty();
            if (isDestroyed) {
                GameController.removeEmpireTroopsFromGame(currentEmpire);
                Manage.allEmpires.remove(i);
//                if (index != 0) NextTurnController.index--;
                if(i == size - 1) index = 0 ;
                else if(index > i) index -- ;
                i--;
                size--;
            }
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

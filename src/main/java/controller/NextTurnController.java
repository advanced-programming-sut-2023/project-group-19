package controller;

import controller.Building.BuildingController;
import controller.Building.FunctionBuildingController;
import controller.Building.SelectedBuildingController;
import model.Building.Building;
import model.Empire;
import model.Human.Troop.Army;
import model.Manage;
import model.Map;
import model.User;
import view.GameMenu;

import java.io.IOException;
import java.util.Collections;
import java.util.Formattable;
import java.util.Scanner;

public class NextTurnController {
    public static Empire currentEmpire;
    public static int index;

    public void game(Scanner scanner) throws IOException, InterruptedException {
        while (true) {
            if (Manage.allEmpires.size() != 1) {
                GameController gameController = new GameController();
                setGameController(gameController);
                findCurrentEmpire();
                System.out.println(Manage.getCurrentEmpire().getName());
                callStartingTurnFunctions(gameController);
                GameMenu gameMenu = new GameMenu();
                gameMenu.run(scanner);
                System.out.println( "past next turn" + Manage.getCurrentEmpire().empireArmy.size());
                callEndingTurnFunctions(gameController);
                System.out.println( "after next turn" + Manage.getCurrentEmpire().empireArmy.size());

            } else {
                System.out.println("Winner is: " + Manage.allEmpires.get(0).getName());
                User user = Manage.getAllEmpires().get(0).getUser();
                int oldScore = user.getHighScore();
                int newScore = oldScore + 100;
                user.setHighScore(newScore);
                Collections.sort(User.users);
                break;
            }
        }
    }

    public void findCurrentEmpire() {
        Manage.setCurrentEmpire(Manage.allEmpires.get(index));
        System.out.println(Manage.getCurrentEmpire().getName());
        currentEmpire = Manage.allEmpires.get(index);
        index = ++index % Manage.allEmpires.size();
        BuildingController.currentEmpire = currentEmpire;
        FunctionBuildingController.empire = currentEmpire;
        SelectedBuildingController.empire = currentEmpire;
        TradeController.currentEmpire = currentEmpire;
    }

    public void callStartingTurnFunctions(GameController gameController) {
        EmpireController.setFearFactor();
        EmpireController.taxImpactOnEmpire(currentEmpire, currentEmpire.getTaxRateNumber());
        currentEmpire.independentProductionBuilding();
        EmpireController.functionBuildings();
        EmpireController.findFoodDiversity();
        EmpireController.givingPeopleFood(currentEmpire);
        gameController.setEnemyToTarget();
        resetTroopsMovesLeft();
    }

    public void setGameController(GameController gameController) {
        GameController.gameController = gameController;
        GameMenu.gameController = gameController;
    }

    public void callEndingTurnFunctions(GameController gameController) {
        gameController.DrawBridge();
        gameController.cagedWarDogsAttack();
        gameController.setStateArmy();
        AttackArmyToArmyController.setFightMode(gameController);
        gameController.fight();
        playerHasLost();
    }

    public void playerHasLost() {
        int size = Manage.allEmpires.size();
        for (int i = 0; i < size; i++) {
            Empire empire = Manage.allEmpires.get(i);
            boolean isDestroyed = Map.getBuildingMap()[empire.castleXCoordinate][empire.castleYCCoordinate].isEmpty();
            if (isDestroyed) {
                GameController.removeEmpireTroopsFromGame(currentEmpire);
                Manage.allEmpires.remove(i);
                if(index != 0) NextTurnController.index--;
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

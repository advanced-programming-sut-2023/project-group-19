package controller;

import model.Building.Building;
import model.Building.Castle;
import model.Empire;
import model.Manage;
import model.Map;
import model.User;
import view.GameMenu;

import java.util.Collections;
import java.util.Scanner;

public class NextTurnController {
    public static Empire currentEmpire;
    public static int index = 0;

    public void game(Scanner scanner) {
        while (true) {
            if (Manage.allEmpires.size() != 1) {
                GameController gameController = new GameController();
                setGameController(gameController);
                findCurrentEmpire();
                callStartingTurnFunctions(gameController);
                GameMenu gameMenu = new GameMenu();
                gameMenu.run(scanner);
                callEndingTurnFunctions(gameController);
            } else {
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
        currentEmpire = Manage.allEmpires.get(index);
        index = index++ % Manage.allEmpires.size();
    }

    public void callStartingTurnFunctions(GameController gameController) {
        currentEmpire.setFearFactor();
        currentEmpire.taxImpactOnEmpire(currentEmpire, currentEmpire.getTaxRateNumber());
        currentEmpire.independentProductionBuilding();
        currentEmpire.functionBuildings();
        currentEmpire.givingPeopleFood(currentEmpire);
        resetTroopsMovesLeft();
        gameController.setEnemyToTarget();
    }

    public void setGameController(GameController gameController) {
        GameController.gameController = gameController;
        GameMenu.gameController = gameController;
    }

    public void callEndingTurnFunctions(GameController gameController) {
        gameController.cagedWarDogsAttack();
        gameController.setStateArmy();
        AttackArmyToArmyController.setFightMode(gameController);
        gameController.fight();
        playerHasLost();
    }

    public void playerHasLost() {
        for (int i = 0; i < Manage.allEmpires.size(); i++) {
            Empire empire = Manage.allEmpires.get(i);
            Building castle = Map.getBuildingMap()[empire.castleXCoordinate][empire.castleXCoordinate].get(0);
            if (castle.getHp() <= 0) {
                GameController.removeEmpireTroopsFromGame(currentEmpire);
                Manage.allEmpires.remove(i);
                NextTurnController.index--;
                i--;
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

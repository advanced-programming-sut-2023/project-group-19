package controller;

//import javafx.scene.web.WebHistory;

import model.Empire;
import model.Human.Troop.ArchersAndThrowers;
import model.Human.Troop.Army;
import model.Human.Troop.Soldiers;

import java.lang.reflect.Array;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;

import model.*;
import model.Building.*;

public class AttackArmyToArmyController {
    public static double fearTroopImpact = Manage.getCurrentEmpire().getFearTroopImpact();
    private static int mapSize = CreateMapController.getSizeOfMap();

    public static void battleWithEnemy() {
        for (Army army : Manage.getCurrentEmpire().empireArmy) {
            findEnemyToFight(army);
        }
        findArcher();
        killUnit();
    }

    //TODO : jolgeh
    //TODO : sleep in login and register
    private static void killUnit() {
        for (Empire empire : Manage.getAllEmpires()) {
            for (Army army : empire.empireArmy) {
                if (army.getHp() <= 0) {
                    int x = army.xCoordinate - 1;
                    int y = army.yCoordinate - 1;
                    Map.getTroopMap()[x][y].remove(army);
                    empire.empireArmy.remove(army);
                }
            }
        }
    }

    private static boolean isArcher(Army army) {
        if (army instanceof ArchersAndThrowers) return true;
        else return false;
    }

    private static void findEnemyToFight(Army army) {
        int x = army.xCoordinate ;
        int y = army.yCoordinate ;
        for (Army enemy : Map.getTroopMap()[x][y]) {
            if (enemy.getEmpire().equals(army.getEmpire()) || enemy.getHp() <= 0) continue;
            int newHitPoint = enemy.hp() - army.getAttackPower() + enemy.getDefencePower();
            GameController.removeKilledUnitFromEmpireHashmap(enemy.getNames().getName() , enemy.getEmpire());
            enemy.setHp(newHitPoint);
            return;
        }
        findBuildingToBeAttacked(army);
    }

    private static void findArcher() {
        for (Empire empire : Manage.getAllEmpires()) {
            for (Army army : empire.empireArmy) {
                if (!isArcher(army)) continue;
                findEnemyInRange(army);
            }
        }
    }

    private static int archerRange;

    private static void findEnemyInRange(Army army) {
        determineRange(army);
        int x = army.xCoordinate - 1;
        int y = army.yCoordinate - 1;
        int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
        for (int i = 1; i <= archerRange; i++) {
            x1 = x - i;
            x2 = x + i;
            y1 = y - i;
            y2 = y + i;
            if (x1 <= 0) x1 = 0;
            if (x2 >= mapSize) x2 = mapSize - 1;
            if (y1 <= 0) y1 = 0;
            if (y2 >= mapSize) y2 = mapSize - 1;
            if (applyDamageWithArcher(x, y, x1, x2, y1, y2, army)) return;
        }
        for (int i = 1; i <= archerRange; i++) {
            x1 = x - i;
            x2 = x + i;
            y1 = y - i;
            y2 = y + i;
            if (x1 <= 0) x1 = 0;
            if (x2 >= mapSize) x2 = mapSize - 1;
            if (y1 <= 0) y1 = 0;
            if (y2 >= mapSize) y2 = mapSize - 1;
            if (applyDamageWithBuildingByArcher(x, y, x1, x2, y1, y2, army)) return;
        }
    }
    private static void findBuildingToBeAttacked(Army army){
        int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
        x1 = army.getCurrentX() - 1;
        x2 = army.getCurrentX() + 1;
        y1 = army.getCurrentY() - 1;
        y2 = army.getCurrentY() + 1;
        if (x1 <= 0) x1 = 0;
        if (x2 >= mapSize) x2 = mapSize - 1;
        if (y1 <= 0) y1 = 0;
        if (y2 >= mapSize) y2 = mapSize - 1;
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (Map.getBuildingMap()[i][j].isEmpty() || Map.getBuildingMap()[i][j].get(0).getOwner().equals(Manage.getCurrentEmpire())) continue;
                int newHp = Map.getBuildingMap()[i][j].get(0).getHp() - army.getAttackPower();
                Map.getBuildingMap()[i][j].get(0).setHp(newHp);
                if (Map.getBuildingMap()[i][j].get(0).getHp() <= 0){
                    Map.getBuildingMap()[i][j].remove(0);
                }
                return;
            }
        }
    }
    private static void determineRange(Army army) {
        int height;
        ArrayList<Building> buildings = Map.getBuildingMap()[army.xCoordinate][army.yCoordinate];
        if (buildings.isEmpty()) height = 0;
        else height = buildings.get(0).getHeight();
        archerRange = army.getAttackRange() + height;
    }

    private static boolean applyDamageWithArcher(int x, int y, int x1, int x2, int y1, int y2, Army army) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (i == x || j == y) continue;
                for (Army enemy : Map.getTroopMap()[i][j]) {
                    if (enemy.getEmpire().equals(army.getEmpire()) || enemy.getHp() <= 0) continue;
                    int newHitPoint = enemy.hp() - army.getAttackPower() + enemy.getDefencePower();
                    GameController.removeKilledUnitFromEmpireHashmap(enemy.getNames().getName() , enemy.getEmpire());
                    enemy.setHp(newHitPoint);
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean applyDamageWithBuildingByArcher(int x, int y, int x1, int x2, int y1, int y2, Army army){
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (i == x || j == y) continue;
                if (!Map.getBuildingMap()[i][j].isEmpty()){
                    if (Map.getBuildingMap()[i][j].get(0).getHp() <= 0){
                        Map.getBuildingMap()[i][j].remove(0);
                    }
                    if (Map.getBuildingMap()[i][j].get(0).getOwner().equals(army.getEmpire())) continue;
                    int newHitPoint = Map.getBuildingMap()[i][j].get(0).hp() - army.getAttackPower();
                    Map.getBuildingMap()[i][j].get(0).setHp(newHitPoint);
                    return true;
                }
            }
        }
        return false;
    }
}
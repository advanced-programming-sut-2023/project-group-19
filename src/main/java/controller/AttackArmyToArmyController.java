package controller;

import model.Empire;
import model.Human.Names;
import model.Human.Troop.ArchersAndThrowers;
import model.Human.Troop.Army;

import java.util.ArrayList;

import model.*;
import model.Building.*;
import view.Animations.SlaveAnimation.DeadSlaveAnimation;
import view.Animations.SlaveAnimation.SlaveAnimation;
import view.Animations.SwordManAnimation.SwordManDeadAnimation;
import view.Animations.SwordManAnimation.SwordManAnimation;
import view.Model.NewButton;
import view.TileManager;

public class AttackArmyToArmyController {
    public  SwordManAnimation swordManAnimation = new SwordManAnimation();
    public SlaveAnimation slaveAnimation = new SlaveAnimation();

    private static int mapSize = CreateMapController.getSizeOfMap();
    TileManager tileManager ;
    public SwordManDeadAnimation swordManDeadAnimation;
    public DeadSlaveAnimation deadSlaveAnimation ;

    public AttackArmyToArmyController(TileManager tileManager){
        this.tileManager =  tileManager ;
        swordManDeadAnimation = new SwordManDeadAnimation(tileManager);
        deadSlaveAnimation = new DeadSlaveAnimation(tileManager);
    }

    public void battleWithEnemy() {
        for (Empire empire : Manage.allEmpires) {
            for (Army army : empire.empireArmy) {
                findEnemyToFight(army);
            }
        }
//        findArcher();
        killUnit();
    }

    private void killUnit() {
        for (int i = 0; i < Manage.allEmpires.size(); i++) {
            Empire empire = Manage.allEmpires.get(i);
            int size = empire.empireArmy.size();
            for (int j = 0; j < size; j++) {
                Army army = empire.empireArmy.get(j);
                if (army.getHp() <= 0) {
                    killUnitSetAnimation(army);
                }
            }
        }
    }
    private void killUnitSetAnimation(Army army){
        switch (army.getNames()){
            case SWORDSMEN:
                swordManDeadAnimation.setArmyToAnimation(army);
                break;
            case SLAVES:
                deadSlaveAnimation.setArmyToAnimation(army);
                break;
        }
    }

    private boolean isArcher(Army army) {
        return army instanceof ArchersAndThrowers;
    }

    private void findEnemyToFight(Army army) {
        int x = army.xCoordinate;
        int y = army.yCoordinate;
        for (Army enemy : ((NewButton)(tileManager.list.get(100 * x + y))).getArmy()) {
            if (enemy.getEmpire().equals(army.getEmpire()) || enemy.getHp() <= 0) continue;
            int newHitPoint = enemy.hp() - army.getAttackPower();
            enemy.setHp(newHitPoint);
            setAnimationToFight(army);
            return;
        }
//        findBuildingToBeAttacked(army);
    }
    private void setAnimationToFight(Army army){
        switch (army.getNames()){
            case SWORDSMEN:
                swordManAnimation.setArmyToAnimation(army);
                break;
            case SLAVES:
                slaveAnimation.setArmyToAnimation(army);
                break;
        }
    }

    private void findArcher() {
        for (Empire empire : Manage.getAllEmpires()) {
            for (Army army : empire.empireArmy) {
                if (!isArcher(army)) continue;
                findEnemyInRange(army);
            }
        }
    }

    private int archerRange;

    private void findEnemyInRange(Army army) {
        determineRange(army);
        int x = army.xCoordinate;
        int y = army.yCoordinate;
        int x1, x2, y1, y2;
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

    private void findBuildingToBeAttacked(Army army) {
        int x1, x2, y1, y2;
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
                if (Map.getBuildingMap()[i][j].isEmpty() ||
                        Map.getBuildingMap()[i][j].get(0).getHp() <= 0 || Map.getBuildingMap()[i][j].get(0).getOwner().equals(Manage.getCurrentEmpire()))
                    continue;
                int newHp = Map.getBuildingMap()[i][j].get(0).getHp() - army.getAttackPower();
                Map.getBuildingMap()[i][j].get(0).setHp(newHp);
                if (Map.getBuildingMap()[i][j].get(0).getHp() <= 0) {
                    Map.getBuildingMap()[i][j].remove(0);
                    Map.notPassable[i][j] = false ;
                    Map.notBuildable[i][j] = false ;
                }
                return;
            }
        }
    }

    private void determineRange(Army army) {
        int height;
        ArrayList<Building> buildings = Map.getBuildingMap()[army.xCoordinate][army.yCoordinate];
        if (buildings.isEmpty()) height = 0;
        else height = buildings.get(0).getHeight();
        archerRange = army.getAttackRange() + height;
    }

    private boolean applyDamageWithArcher(int x, int y, int x1, int x2, int y1, int y2, Army army) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {

                if (i == x && j == y) continue;
                for (Army enemy : Map.getTroopMap()[i][j]) {
                    if (enemy.getEmpire().equals(army.getEmpire()) || enemy.getHp() <= 0) continue;
                    int newHitPoint = enemy.hp() - army.getAttackPower();
                    enemy.setHp(newHitPoint);
                    enemy.setArcherAttacker((ArchersAndThrowers) army);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean applyDamageWithBuildingByArcher(int x, int y, int x1, int x2, int y1, int y2, Army army) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (i == x && j == y) continue;
                if (!Map.getBuildingMap()[i][j].isEmpty()) {
                    if (Map.getBuildingMap()[i][j].get(0).getOwner().equals(army.getEmpire()) ||
                            Map.getBuildingMap()[i][j].get(0).getHp() <= 0) continue;
                    int newHitPoint = Map.getBuildingMap()[i][j].get(0).hp() - army.getAttackPower();
                    Map.getBuildingMap()[i][j].get(0).setHp(newHitPoint);

                    if (Map.getBuildingMap()[i][j].get(0).getHp() <= 0) {
                        Map.getBuildingMap()[i][j].remove(0);
                        Map.notPassable[i][j] = false ;
                        Map.notBuildable[i][j] = false ;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public void setFightMode(GameController gameController) {
        gameController.selectedUnit.clear();
        Empire empire = Manage.getCurrentEmpire();
        for (Army army : empire.empireArmy) {
            if (!army.isIntFight() || isArcher(army)) continue;
            gameController.selectedUnit.add(army);
            findEnemyForFightMode(army, gameController);
            gameController.selectedUnit.clear();
        }
    }

    private void findEnemyForFightMode(Army army, GameController gameController) {
        int range = army.getAttackRange();
        int x = army.xCoordinate;
        int y = army.yCoordinate;
        int x1, x2, y1, y2;
        for (int i = 1; i <= range; i++) {
            x1 = x - i;
            x2 = x + i;
            y1 = y - i;
            y2 = y + i;
            if (x1 <= 0) x1 = 0;
            if (x2 >= mapSize) x2 = mapSize - 1;
            if (y1 <= 0) y1 = 0;
            if (y2 >= mapSize) y2 = mapSize - 1;
            if (setFightModeIntoArmy(x, y, x1, x2, y1, y2, army, gameController)) return;
        }
    }

    private boolean setFightModeIntoArmy(int x, int y, int x1, int x2, int y1, int y2, Army army, GameController gameController) {
        for (Army enemy : Map.getTroopMap()[x][y]) {
            if (!enemy.getEmpire().equals(army.getEmpire())) return true;
        }
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                for (Army enemy : Map.getTroopMap()[i][j]) {
                    if (enemy.getEmpire().equals(army.getEmpire()) || enemy.getHp() <= 0) continue;
                    gameController.moveUnit(enemy.xCoordinate , enemy.yCoordinate );
                    return true;
                }
            }
        }
        int left, right, up, down;
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (Map.getBuildingMap()[i][j].isEmpty()) continue;
                int xBuilding = i;
                int yBuilding = j;
                up = xBuilding - 1;
                down = xBuilding + 1;
                left = yBuilding - 1;
                right = yBuilding + 1;
                if (left <= 0) left = 0;
                if (right >= mapSize) right = mapSize - 1;
                if (up <= 0) up = 0;
                if (down >= mapSize) down = mapSize - 1;
                for (int len = up; len <= down; len++) {
                    for (int h = left; h <= right; h++) {
                        if (!Map.notPassable[len][h]) {
                            gameController.moveUnit(len, h);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


}
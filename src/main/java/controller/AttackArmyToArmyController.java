package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.Empire;
import model.Human.Names;
import model.Human.Troop.ArchersAndThrowers;
import model.Human.Troop.Army;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayList;

import model.*;
import model.Building.*;
import view.Animations.troopFights.ArcherAnimation.ArcherAnimation;
import view.Animations.troopFights.ArcherAnimation.DeadArcherAnimation;
import view.Animations.troopFights.AssasinAnimation.AsssasinAnimation;
import view.Animations.troopFights.AssasinAnimation.DeadAssasinAnimation;
import view.Animations.troopFights.GrendiarAnimation.DeadGrendiarAnimation;
import view.Animations.troopFights.GrendiarAnimation.GrendiarAnimation;
import view.Animations.troopFights.HorseRiderAnimation.DeadHorseRiderAnimation;
import view.Animations.troopFights.HorseRiderAnimation.HorseRiderAnimation;
import view.Animations.troopFights.MaceManAnimation.DeadMaceManAnimation;
import view.Animations.troopFights.MaceManAnimation.MaceManAnimation;
import view.Animations.troopFights.MachineAnimation.DeadMachineAnimation;
import view.Animations.troopFights.MachineAnimation.MachineAnimation;
import view.Animations.troopFights.MonkAnimation.DeadMonkAnimation;
import view.Animations.troopFights.MonkAnimation.MonkAnimation;
import view.Animations.troopFights.ShortBowAnimation.DeadShortBowAnimation;
import view.Animations.troopFights.ShortBowAnimation.ShortBowAnimation;
import view.Animations.troopFights.SlaveAnimation.DeadSlaveAnimation;
import view.Animations.troopFights.SlaveAnimation.SlaveAnimation;
import view.Animations.troopFights.SlingerAnimation.DeadSlingerAnimation;
import view.Animations.troopFights.SlingerAnimation.SlingerAnimation;
import view.Animations.troopFights.SwordManAnimation.SwordManDeadAnimation;
import view.Animations.troopFights.SwordManAnimation.SwordManAnimation;
import view.Model.NewButton;
import view.TileManager;

public class AttackArmyToArmyController {
    public  SwordManAnimation swordManAnimation = new SwordManAnimation();
    public SlaveAnimation slaveAnimation = new SlaveAnimation();
    public AsssasinAnimation asssasinAnimation = new AsssasinAnimation();
    public MaceManAnimation maceManAnimation = new MaceManAnimation();
    public MonkAnimation monkAnimation = new MonkAnimation();
    public ShortBowAnimation shortBowAnimation = new ShortBowAnimation();
    public SlingerAnimation slingerAnimation = new SlingerAnimation();
    public ArcherAnimation archerAnimation = new ArcherAnimation();
    public GrendiarAnimation grendiarAnimation = new GrendiarAnimation();
    public HorseRiderAnimation horseRiderAnimation = new HorseRiderAnimation();
    public MachineAnimation machineAnimation = new MachineAnimation();

    private static int mapSize = 200;
    TileManager tileManager ;
    public SwordManDeadAnimation swordManDeadAnimation;
    public DeadSlaveAnimation deadSlaveAnimation ;
    public DeadAssasinAnimation deadAssasinAnimation ;
    public DeadMaceManAnimation deadMaceManAnimation ;
    public DeadMonkAnimation deadMonkAnimation ;
    public DeadShortBowAnimation deadShortBowAnimation ;
    public DeadSlingerAnimation deadSlingerAnimation ;
    public DeadArcherAnimation deadArcherAnimation ;
    public DeadGrendiarAnimation deadGrendiarAnimation ;
    public DeadHorseRiderAnimation deadHorseRiderAnimation ;
    public DeadMachineAnimation deadMachineAnimation ;

    public AttackArmyToArmyController(TileManager tileManager){
        this.tileManager =  tileManager ;
        swordManDeadAnimation = new SwordManDeadAnimation(tileManager);
        deadSlaveAnimation = new DeadSlaveAnimation(tileManager);
        deadAssasinAnimation = new DeadAssasinAnimation(tileManager);
        deadMaceManAnimation = new DeadMaceManAnimation(tileManager);
        deadMonkAnimation = new DeadMonkAnimation(tileManager);
        deadShortBowAnimation = new DeadShortBowAnimation(tileManager);
        deadSlingerAnimation = new DeadSlingerAnimation(tileManager);
        deadArcherAnimation = new DeadArcherAnimation(tileManager);
        deadGrendiarAnimation = new DeadGrendiarAnimation(tileManager);
        deadHorseRiderAnimation = new DeadHorseRiderAnimation(tileManager);
        deadMachineAnimation = new DeadMachineAnimation(tileManager);
    }
    public void battleWithEnemy() {
        for (Empire empire : Manage.allEmpires) {
            for (Army army : empire.empireArmy) {
                findEnemyToFight(army);
            }
        }
        findArcher();
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
    //TODO : fire ballista
    //catapult‏
    //trebuchet‏
    //shield‏
    //siege tower‏
    private void killUnitSetAnimation(Army army){
        switch (army.getNames()){
            case SWORDSMEN:
                swordManDeadAnimation.setArmyToAnimation(army);
                break;
            case SLAVES:
                deadSlaveAnimation.setArmyToAnimation(army);
                break;
            case ASSASSINS:
                deadAssasinAnimation.setArmyToAnimation(army);
                break;
            case MACE_MEN:
                deadMaceManAnimation.setArmyToAnimation(army);
            case BLACK_MONK:
                deadMonkAnimation.setArmyToAnimation(army);
                break;
            case ARCHER_BOW:
                deadShortBowAnimation.setArmyToAnimation(army);
                break;
            case SLINGERS:
                deadSlingerAnimation.setArmyToAnimation(army);
                break;
            case ARCHER:
                deadArcherAnimation.setArmyToAnimation(army);
                break;
            case FireThrowers:
                deadGrendiarAnimation.setArmyToAnimation(army);
                break;
            case HORSE_ARCHERS:
                deadHorseRiderAnimation.setArmyToAnimation(army);
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
        findBuildingToBeAttacked(army);
    }
    private void setAnimationToFight(Army army){
        switch (army.getNames()){
            case SWORDSMEN:
                swordManAnimation.setArmyToAnimation(army);
                break;
            case SLAVES:
                slaveAnimation.setArmyToAnimation(army);
                break;
            case ASSASSINS:
                asssasinAnimation.setArmyToAnimation(army);
                break;
            case MACE_MEN:
                maceManAnimation.setArmyToAnimation(army);
                break;
            case BLACK_MONK:
                monkAnimation.setArmyToAnimation(army);
                break;
            case ARCHER_BOW:
                shortBowAnimation.setArmyToAnimation(army);
                break;
            case SLINGERS:
                slingerAnimation.setArmyToAnimation(army);
                break;
            case ARCHER:
                archerAnimation.setArmyToAnimation(army);
                break;
            case FireThrowers:
                grendiarAnimation.setArmyToAnimation(army);
            case HORSE_ARCHERS:
                horseRiderAnimation.setArmyToAnimation(army);
                break;
        }
    }
    private boolean IsEnemyIntoCell(Army army){
        int x = army.xCoordinate;
        int y = army.yCoordinate;
        for (Army enemy : ((NewButton)(tileManager.list.get(100 * x + y))).getArmy()) {
            if (enemy.getEmpire().equals(army.getEmpire())) continue;
            return  true ;
        }
        return false ;
    }

    private void findArcher() {
        for (Empire empire : Manage.getAllEmpires()) {
            for (Army army : empire.empireArmy) {
                if (!isArcher(army) || IsEnemyIntoCell(army)) continue;
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
        for (int i = 1; i <= army.getAttackRange(); i++) {
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
        for (int i = 1; i <= army.getAttackRange() ; i++) {
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
                Building building = ((NewButton)(tileManager.list.get(100 * i + j))).getBuilding();
                if (building == null || building.getHp() <= 0 || building.getOwner().equals(Manage.getCurrentEmpire()))
                    continue;
                int newHp = building.getHp() - army.getAttackPower();
                setDirectionArmyToAttackBuilding(army,i,j);
                setAnimationToFight(army);
                building.setHp(newHp);
                if (building.getHp() <= 0) {
                    ((NewButton)(tileManager.list.get(100 * i + j))).setBuilding(null);
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
    private void setArcherDirection(Army army , Army enemy){
        int deltaX = enemy.xCoordinate - army.xCoordinate ;
        int deltaY = enemy.yCoordinate - army.yCoordinate ;
        System.out.println(deltaX);
        System.out.println(deltaY);
        if(deltaX > 0) army.setState(Army.StateOfStanding.FRONT);
        else if( deltaX < 0) army.setState(Army.StateOfStanding.BACK);
        else if(deltaY > 0 ) army.setState(Army.StateOfStanding.RIGHT);
        else if( deltaY < 0) army.setState(Army.StateOfStanding.LEFT);
        else army.setState(Army.StateOfStanding.RIGHT);
    }

    private boolean applyDamageWithArcher(int x, int y, int x1, int x2, int y1, int y2, Army army) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (i == x && j == y) continue;
                for (Army enemy : ((NewButton)(tileManager.list.get(100 * i + j))).getArmy()) {
                    if (enemy.getEmpire().equals(army.getEmpire()) || enemy.getHp() <= 0) continue;
                    int newHitPoint = enemy.hp() - army.getAttackPower();
                    enemy.setHp(newHitPoint);
                    enemy.setArcherAttacker((ArchersAndThrowers) army);
                    setArcherDirection(army,enemy);
                    setAnimationToFight(army);
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
//                System.out.println("i is: " + i + " and j is: " + j);
                Building building = ((NewButton)(tileManager.list.get(100 * i + j))).getBuilding();
                if (building != null) {
                    if (building.getOwner().equals(army.getEmpire())) continue;
                    //building.getHp() <= 0
                    int newHitPoint = building.hp() - army.getAttackPower();
                    System.out.println(newHitPoint);
                    if(army.getNames().equals(Names.FireThrowers)){
                        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(6000),actionEvent -> {
                            try {
                                setTimeLIneToFire(building);
                            } catch (AWTException e) {
                                throw new RuntimeException(e);
                            }
                        }));
                        timeline.play();
                        ;
                        building.setFireCount(3);
                        Manage.burningEmpires.add(building);
                    }
                    setDirectionArmyToAttackBuilding(army,i,j);
                    building.setHp(newHitPoint);
                    setAnimationToFight(army);
                    if (building.getHp() <= 0) {
//                        ((NewButton)(tileManager.list.get(100 * i + j))).setBuilding(null);
                        Map.notPassable[i][j] = false ;
                        Map.notBuildable[i][j] = false ;
                    }
                    return true;
                }
            }
        }
        return false;
    }
    private void setTimeLIneToFire(Building building) throws AWTException {
        building.onFire = true ;
        Robot rob = new Robot();
        rob.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        rob.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }
    private void setDirectionArmyToAttackBuilding(Army army , int x , int y){
        int deltaX = x - army.xCoordinate ;
        int deltaY = y - army.yCoordinate ;
        if(deltaX > 0) army.setState(Army.StateOfStanding.RIGHT);
        else if( deltaX < 0) army.setState(Army.StateOfStanding.LEFT);
        else if(deltaY > 0 ) army.setState(Army.StateOfStanding.BACK);
        else if( deltaY < 0) army.setState(Army.StateOfStanding.FRONT);
        else army.setState(Army.StateOfStanding.RIGHT);
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
                    //gameController.moveUnit(enemy.xCoordinate , enemy.yCoordinate );
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
                            //gameController.moveUnit(len, h);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


}
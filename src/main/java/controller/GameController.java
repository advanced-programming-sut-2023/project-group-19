package controller;

import model.Building.*;
import model.Empire;
import model.GroundType;
import model.Human.Names;
import model.Human.Troop.*;
import model.Manage;
import model.Map;
import model.Obstacle.ObstacleName;
import view.Commands.GameMenuCommands;
import view.Messages.GameMenuMessages;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.regex.Matcher;

public class GameController {
    //TODO : Remember that you should delete an army if its hp <= 0 from myUnit
    //TODO : Every time you enter gameMenu and every turn you should call isMyArmyDeployed
    //TODO : All buildings require fire state boolean
    //TODO : Check the output of your Functions
    //TODO : CALL MAKE SIEGE MACHINE... EVERY TURN
    //TODO : SAVE PAST COORDINATE OF ALL ARMIES
    //TODO : WHAT HAPPENS IF THE ENEMY DIES WHEN WE HAVE AN OFFENSIVE
    //TODO : WE SHOULD SET THE FORM OF ARMY WHEN THE PATH.LIST IS NULL
    private static final int mapSize = CreateMapController.getSizeOfMap();
    public static GameController gameController;
    public ArrayList<Army> selectedUnit = new ArrayList<>();
    public ArrayList<ArchersAndThrowers> throwers = new ArrayList<>();

    public GameMenuMessages selectUnit(Matcher x, Matcher y) {
        int flag = 0;
        int xCoordinate = Integer.parseInt(x.group("x"));
        int yCoordinate = Integer.parseInt(y.group("y"));
        if (!Map.getTroopMap()[xCoordinate][yCoordinate].isEmpty()) {
            for (int i = 0; i < Map.getTroopMap()[xCoordinate][yCoordinate].size(); i++) {
                if (Map.getTroopMap()[xCoordinate][yCoordinate].get(i).getOwner().getName()
                        .equals(Manage.getCurrentEmpire().getName())) {
                    flag = 1;
                    selectedUnit.add(Map.getTroopMap()[xCoordinate][yCoordinate].get(i));
                }
            }
            if (flag == 1) return GameMenuMessages.SELECT_UNIT_SUCCEEDED;
            else return GameMenuMessages.NO_UNIT_IN_CELL;
        }
        return GameMenuMessages.NO_UNIT_IN_CELL;
    }

    private static boolean isArcher(Army army) {
        return army instanceof ArchersAndThrowers;
    }

    public GameMenuMessages setFormOfUnit(Matcher xCoordinate, Matcher yCoordinate ,Matcher form){
        int x = Integer.parseInt(xCoordinate.group("x"));
        int y = Integer.parseInt(yCoordinate.group("y"));
        String formOfUnit = form.group("type");
        if (!Map.getTroopMap()[x][y].isEmpty()){
            for (Army army : Map.getTroopMap()[x][y]){
                if (army.getOwner().equals(Manage.getCurrentEmpire())){
                    army.setArmyForm(formOfUnit);
                }
            }
            return GameMenuMessages.SUCCESS;
        }
        return GameMenuMessages.NO_UNIT_IN_CELL;
    }
    public GameMenuMessages readyToAttack(Matcher enemy) {
        int x = Integer.parseInt(enemy.group("enemyx"));
        int y = Integer.parseInt(enemy.group("enemyy"));
        for (Army army : selectedUnit ){
            army.isIntFight = true;
        }
        setPathForUnits(x,y);
        moveUnit(x,y);
        return GameMenuMessages.ATTACK_ORDER_HANDELED;
    }
    public GameMenuMessages attackAllSelectedArchers(Matcher xCoordinate , Matcher yCoordinate) {
        int x = Integer.parseInt(xCoordinate.group("x"));
        int y = Integer.parseInt(yCoordinate.group("y"));
        x--;
        y--;
        for (Army army : selectedUnit) {
            if (!isArcher(army)) continue;
            for (Army enemy : Map.getTroopMap()[x][y]) {
                if (enemy.getEmpire().equals(army.getEmpire()) || enemy.getHp() <= 0) continue;
                int newHitPoint = enemy.hp() - army.getAttackPower();
                enemy.setHp(newHitPoint);
                break;
            }
        }
        return GameMenuMessages.SUCCESS;
    }

    public GameMenuMessages disbandUnit() {
        if (selectedUnit.isEmpty()) return GameMenuMessages.INVALID_COMMAND;
        for (Army army : selectedUnit) {
            int x = army.getCurrentX() - 1;
            int y = army.getCurrentY() - 1;
            Empire empire = army.getEmpire();
            empire.empireArmy.remove(army);
            Map.getTroopMap()[x][y].remove(army);
        }
        return GameMenuMessages.SUCCESS;
    }

    public void setStateArmy() {
        selectedUnit.clear();
        for (Army army : Manage.getCurrentEmpire().empireArmy) {
            if (isArcher(army) || army.getArmyForm().equals(Names.STANDING_AMRY.getName()) || army.isIntFight || army.myPath != null) continue;
            selectedUnit.add(army);
            findEnemyInRange(army, army.getArmyForm());
            selectedUnit.clear();
        }
    }

    public GameMenuMessages dropUnit(Matcher x1, Matcher y1, Matcher count, Matcher type) {
        int x = Integer.parseInt(x1.group("x"));
        int y = Integer.parseInt(y1.group("y"));
        int countOfUnits = Integer.parseInt(count.group("count"));
        String typeOfUnit = type.group("type");
        if (checkGroundTypeForUnits(x, y)) {
            if (checkTypeOfUnitWithLocation(x, y, typeOfUnit)) {
                addUnitsToMap(x, y, countOfUnits, typeOfUnit);
                return GameMenuMessages.SUCCESS;
            }
            return GameMenuMessages.IMPROPER_UNIT;
        }
        return GameMenuMessages.IMPROPER_LOCATION;
    }

    private static void addUnitsToMap(int x, int y, int count, String typeOfUnit) {
        for (int i = 0; i < count; i++) {
            switch (typeOfUnit) {
                case "Archer":
                    ArchersAndThrowers archer = new ArchersAndThrowers(Manage.getCurrentEmpire());
                    archer.archer(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(archer);
                    Map.getTroopMap()[x][y].add(archer);
                case "Crossbowmen":
                    ArchersAndThrowers crossBowMan = new ArchersAndThrowers(Manage.getCurrentEmpire());
                    crossBowMan.Crossbowmen(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(crossBowMan);
                    Map.getTroopMap()[x][y].add(crossBowMan);
                case "ArcherBow":
                    ArchersAndThrowers archerBow = new ArchersAndThrowers(Manage.getCurrentEmpire());
                    archerBow.ArcherBow(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(archerBow);
                    Map.getTroopMap()[x][y].add(archerBow);
                case "Slingers":
                    ArchersAndThrowers slingers = new ArchersAndThrowers(Manage.getCurrentEmpire());
                    slingers.Slingers(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(slingers);
                    Map.getTroopMap()[x][y].add(slingers);
                case "HorseArchers":
                    ArchersAndThrowers horseArcher = new ArchersAndThrowers(Manage.getCurrentEmpire());
                    horseArcher.HorseArchers(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(horseArcher);
                    Map.getTroopMap()[x][y].add(horseArcher);
                case "FireThrowers":
                    ArchersAndThrowers fireThrower = new ArchersAndThrowers(Manage.getCurrentEmpire());
                    fireThrower.FireThrowers(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(fireThrower);
                    Map.getTroopMap()[x][y].add(fireThrower);
                case "SpearMen":
                    Climbers spearMen = new Climbers(Manage.getCurrentEmpire());
                    spearMen.SpearMen(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(spearMen);
                    Map.getTroopMap()[x][y].add(spearMen);
                case "MaceMen":
                    Climbers maceMen = new Climbers(Manage.getCurrentEmpire());
                    maceMen.MaceMen(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(maceMen);
                    Map.getTroopMap()[x][y].add(maceMen);
                case "LadderMen":
                    Climbers ladderMen = new Climbers(Manage.getCurrentEmpire());
                    ladderMen.LadderMen(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(ladderMen);
                    Map.getTroopMap()[x][y].add(ladderMen);
                case "Assassins":
                    Climbers assassin = new Climbers(Manage.getCurrentEmpire());
                    assassin.Assassins(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(assassin);
                    Map.getTroopMap()[x][y].add(assassin);
                case "Engineer":
                    Engineer engineer = new Engineer(Manage.getCurrentEmpire());
                    engineer.engineer(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(engineer);
                    Map.getTroopMap()[x][y].add(engineer);
                case "BlackMonk":
                    Soldiers blackMonk = new Soldiers(Manage.getCurrentEmpire());
                    blackMonk.BlackMonk(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(blackMonk);
                    Map.getTroopMap()[x][y].add(blackMonk);
                case "Knight":
                    Soldiers knight = new Soldiers(Manage.getCurrentEmpire());
                    knight.Knight(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(knight);
                    Map.getTroopMap()[x][y].add(knight);
                case "Swordsmen":
                    Soldiers swordMen = new Soldiers(Manage.getCurrentEmpire());
                    swordMen.Swordsmen(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(swordMen);
                    Map.getTroopMap()[x][y].add(swordMen);
                case "PikeMen":
                    Soldiers pikeMen = new Soldiers(Manage.getCurrentEmpire());
                    pikeMen.PikeMen(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(pikeMen);
                    Map.getTroopMap()[x][y].add(pikeMen);
                case "Slaves":
                    Soldiers slave = new Soldiers(Manage.getCurrentEmpire());
                    slave.Slaves(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(slave);
                    Map.getTroopMap()[x][y].add(slave);
                case "ArabianSwordsmen":
                    Soldiers arabSwordMen = new Soldiers(Manage.getCurrentEmpire());
                    arabSwordMen.ArabianSwordsmen(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(arabSwordMen);
                    Map.getTroopMap()[x][y].add(arabSwordMen);
                case "Tunneler":
                    Tunneler tunneler = new Tunneler(Manage.getCurrentEmpire());
                    tunneler.Tunneler(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(tunneler);
                    Map.getTroopMap()[x][y].add(tunneler);
            }
        }
    }

    public GameMenuMessages buildEquipment(Matcher name) {
        String nameOfEquipment = name.group("equipment name");
        Empire empire = Manage.getCurrentEmpire();
        for (java.util.Map.Entry<String, Integer> siegeTentTroop : empire.getSiegeTentTroopsCount().entrySet()) {
            if (siegeTentTroop.getKey().equals(nameOfEquipment)) {
                empire.getSiegeTentTroopsCount().replace(nameOfEquipment, siegeTentTroop.getValue() + 1);
                return GameMenuMessages.SUCCESS;
            }
        }
        return GameMenuMessages.WRONG_SIEGE_NAME;
    }

    private static boolean checkGroundTypeForUnits(int x, int y) {
        return !Map.getGroundType()[x][y].get(0).getGroundType().equals(GroundType.PLAIN.getGroundType())
                && !Map.getGroundType()[x][y].get(0).getGroundType().equals(GroundType.STONE.getGroundType())
                && !Map.getGroundType()[x][y].get(0).getGroundType().equals(ObstacleName.BIG_POND.getObstacleName())
                && !Map.getGroundType()[x][y].get(0).getGroundType().equals(ObstacleName.SMALL_POND.getObstacleName())
                && !Map.getGroundType()[x][y].get(0).getGroundType().equals(ObstacleName.RIVER.getObstacleName())
                && !Map.getGroundType()[x][y].get(0).getGroundType().equals(ObstacleName.SEA.getObstacleName());
    }

    private static boolean checkTypeOfUnitWithLocation(int x, int y, String type) {
        return (Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.TUNNEL) && type.equals(Names.TUNNELER.getName())
                || ((!Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.TUNNEL) && !type.equals(Names.TUNNELER.getName())
                && !Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.PITCH_DITCH) && !type.equals(Names.SPEAR_MEN.getName())))
                || (Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.PITCH_DITCH) && !type.equals(Names.SPEAR_MEN.getName())));
    }

    private static void findEnemyInRange(Army army, String State) {
        int x = army.xCoordinate ;
        int y = army.yCoordinate ;
        int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
        for (int i = 1; i <= army.getAttackRange(); i++) {
            x1 = x - i;
            x2 = x + i;
            y1 = y - i;
            y2 = y + i;
            if (x1 <= 0) x1 = 0;
            if (x2 >= mapSize) x2 = mapSize - 1;
            if (y1 <= 0) y1 = 0;
            if (y2 >= mapSize) y2 = mapSize - 1;
            if (State.equals(Names.OFFENSIVE.getName()))
                if (moveUnitToEnemyLocationAngry(x, y, x1, x2, y1, y2, army, i)) return;
                else {
                    if (moveUnitToEnemyLocationDefensive(x, y, x1, x2, y1, y2, army, i)) return;
                }
        }
    }

    private static boolean isSameGridIntoRange(int x, int y, Army army, int chosenX, int chosenY) {
        int x1 = x - army.getAttackRange();
        int x2 = x + army.getAttackRange();
        int y1 = y - army.getAttackRange();
        int y2 = y + army.getAttackRange();
        if (x1 <= 0) x1 = 0;
        if (x2 >= mapSize) x2 = mapSize - 1;
        if (y1 <= 0) y1 = 0;
        if (y2 >= mapSize) y2 = mapSize - 1;
        return chosenX >= x1 && chosenX <= x2 && chosenY >= y1 && chosenY <= y2;
    }

    private static boolean moveUnitToEnemyLocationDefensive(int x, int y, int x1, int x2, int y1, int y2, Army army, int range) {
        for (Army enemy : Map.getTroopMap()[x][y]) {
            if (!enemy.getEmpire().equals(army.getEmpire())) return true;
        }
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (i == x && j == y) continue;
                for (Army enemy : Map.getTroopMap()[i][j]) {
                    if(army.getPastXcordinate() == army.getCurrentX() && army.getPastYcordinate() == army.getCurrentY()){
                        army.hasMovedForDeffensiveState = false ;
                    }
                    if (!army.getEmpire().equals(enemy.getEmpire())) {
                        if (!army.hasMovedForDeffensiveState) {
                            army.setPastXcordinate(x);
                            army.setPastYcordinate(y);
                            gameController.moveUnit(i, j);
                            army.hasMovedForDeffensiveState = true ;
                            return true;
                        }
                        if (isSameGridIntoRange(army.getPastXcordinate(), army.getPastYcordinate(), army, i, j)) {
                            gameController.moveUnit(i, j);
                            return true;
                        }
                    }
                }
            }
        }
        if (range == army.getAttackRange()) {
            gameController.moveUnit(army.getPastXcordinate(), army.getPastYcordinate());
            return true;
        } else {
            return false;
        }
    }

    //TODO : After every next turn please call it!
    public void setEnemyToTarget() {
        selectedUnit.clear();
        for (Army army : Manage.getCurrentEmpire().empireArmy) {
            checkIfTargetIsAlive(army);
            if (army.getEnemy() == null) continue;
            selectedUnit.add(army);
            gameController.moveUnit(army.getEnemy().xCoordinate, army.getEnemy().yCoordinate);
            selectedUnit.clear();
        }
    }

    private static void checkIfTargetIsAlive(Army army){
        Empire empire = army.getEnemy().getOwner();
        for (Army army1 : empire.empireArmy){
            if (army1.equals(army.getEnemy())){
                return;
            }
        }
        army.setEnemy(null);
    }

    private static boolean moveUnitToEnemyLocationAngry(int x, int y, int x1, int x2, int y1, int y2, Army army , int range) {
        for (Army enemy : Map.getTroopMap()[x][y]) {
            if (!enemy.getEmpire().equals(army.getEmpire())) return true;
        }
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                for (Army enemy : Map.getTroopMap()[i][j]) {
                    if (enemy.getEmpire().equals(army.getEmpire()) || enemy.getHp() <= 0) continue;
                    army.setEnemy(enemy);
                    gameController.moveUnit(army.getEnemy().xCoordinate, army.getEnemy().yCoordinate);
                    return true;
                }
            }
        }
        if(range == army.getAttackRange()){
            Army enemy ;
            if((enemy = army.getArcherAttacker()) != null){
                army.setEnemy(enemy);
                gameController.moveUnit(army.getEnemy().xCoordinate, army.getEnemy().yCoordinate);
            }
        }
        return false;
    }

    //TODO : Special condition which we call setStateArmy first
    public GameMenuMessages moveUnit(int xCoordinate, int yCoordinate) {
        if (selectedUnit.size() != 0) {
            if (validFinalLocation(xCoordinate, yCoordinate)) {
                if (!isTrebuchet()) {
                    setPathForUnits(xCoordinate, yCoordinate);
                    for (Army myUnit : Manage.getCurrentEmpire().empireArmy) {
                        List<Integer> pathList = myUnit.myPath;
                        if (pathList != null && pathList.size() != 0) {
                            for (int i = 0; i < pathList.size(); i++) {
                                if (myUnit.restOfMoves != 0) {
                                    // goal in here means next
                                    myUnit.goalXCoordinate = pathList.get(i) / PathFindingController.size;
                                    myUnit.goalYCoordinate = pathList.get(i) % PathFindingController.size;
                                    Map.getTroopMap()[myUnit.getCurrentX()][myUnit.getCurrentY()].remove(myUnit);
                                    if (validSquareBySquareCell(myUnit)) {
                                        Manage.getCurrentEmpire().empireArmy.remove(myUnit);
                                        break;
                                    }
                                    if (Map.getObstacleMap()[xCoordinate][yCoordinate].get(0).getName().getObstacleName()
                                            .equals(GroundType.PLAIN.getGroundType())) {
                                        Manage.getCurrentEmpire().empireArmy.remove(myUnit);
                                        break;
                                    }
                                    myUnit.xCoordinate = myUnit.goalXCoordinate;
                                    myUnit.yCoordinate = myUnit.goalYCoordinate;
                                    myUnit.restOfMoves--;
                                    Map.getTroopMap()[myUnit.xCoordinate][myUnit.yCoordinate].add(myUnit);
                                    pathList.remove(i);
                                    i--;
                                }
                            }
                            if (pathList.size() <= myUnit.speed()) {
                                if (myUnit.getArmyForm().equals(Names.PATROL_UNIT.getName())) {
                                    setPathForPatrols(myUnit.getStartX(), myUnit.getStartY(), myUnit);
                                } else {
                                    myUnit.myPath = null;
                                }
                            }
                        }
                    }
                    if (isMyArmyDeployed()) return GameMenuMessages.ARMY_DEPLOYED;
                    else return GameMenuMessages.ARMY_IN_PROCESS_OF_DEPLOYING;
                } else return GameMenuMessages.UNABLE_TO_MOVE_TREBUCHET;
            }
            return GameMenuMessages.LOCATION_CONTAINS_WATERSOURCES_OR_HIGH_PLACES;
        }
        return GameMenuMessages.NO_UNIT_SELECTED;
    }

    public void setPathForUnits(int xCoordinate, int yCoordinate) {
        for (Army army : selectedUnit) {
            if (army.getNames().getName().equals(Names.ASSASSINS.getName()) ||
                    army.getNames().getName().equals(Names.LADDER_MEN.getName())) {
                PathFindingController.notPassable = Map.wallPassable;
            } else {
                PathFindingController.notPassable = Map.notPassable;
            }
            PathFindingController.wall = Map.wall;
            PathFindingController.startX = army.getCurrentX() - 1;
            PathFindingController.startY = army.getCurrentY() - 1;
            PathFindingController.goalX = xCoordinate - 1;
            PathFindingController.goalY = yCoordinate - 1;
            army.myPath = PathFindingController.pathFinding();

        }
    }

    //TODO : Cancel selected unit where is necessary
    public void setPathForPatrols(int xCoordinate, int yCoordinate, Army patrol) {
        PathFindingController.notPassable = Map.notPassable;
        PathFindingController.wall = Map.wall;
        PathFindingController.startX = patrol.getCurrentX() - 1;
        PathFindingController.startY = patrol.getCurrentY() - 1;
        PathFindingController.goalX = xCoordinate - 1;
        PathFindingController.goalY = yCoordinate - 1;
        patrol.myPath = PathFindingController.pathFinding();
    }

    public boolean isMyArmyDeployed() {
        for (Army myArmy : selectedUnit) {
            if (myArmy.myPath != null) {
                return false;
            }
        }
        return true;
    }

    public boolean isTrebuchet() {
        for (Army army : selectedUnit) {
            if (army instanceof ArchersAndThrowers) {
                if (army.getNames().equals(Names.TREBUCHET)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean validFinalLocation(int x, int y) {
        return Map.getObstacleMap()[x][y].get(0).getName() != ObstacleName.BIG_POND
                && Map.getObstacleMap()[x][y].get(0).getName() != ObstacleName.SMALL_POND
                && Map.getObstacleMap()[x][y].get(0).getName() != ObstacleName.RIVER
                && Map.getObstacleMap()[x][y].get(0).getName() != ObstacleName.SEA;
    }

    public boolean validSquareBySquareCell(Army myUnit) {
        return (Map.getBuildingMap()[myUnit.goalXCoordinate][myUnit.goalYCoordinate].get(0) instanceof KillingPit ||
                Map.getBuildingMap()[myUnit.goalXCoordinate][myUnit.goalYCoordinate].get(0) instanceof PitchDitch) &&
                !(myUnit.getNames().getName().equals(Names.SPEAR_MEN.getName()));
    }

    public void patrolUnit(Matcher x1, Matcher y1, Matcher x2, Matcher y2) {
        int xOne = Integer.parseInt(x1.group("x"));
        int xTwo = Integer.parseInt(x2.group("x"));
        int yOne = Integer.parseInt(y1.group("y"));
        int yTwo = Integer.parseInt(y2.group("y"));
        setCoordinatesForPatrols(xOne, yOne, xTwo, yTwo);
        String unitMoved = moveUnit(xTwo, yTwo).getMessages();
    }

    public void setCoordinatesForPatrols(int x1, int y1, int x2, int y2) {
        for (Army army : selectedUnit) {
            army.setArmyForm(Names.PATROL_UNIT.getName());
            army.startXCoordinate = x1;
            army.startYCoordinate = y1;
            army.finalXCoordinate = x2;
            army.finalYCoordinate = y2;
        }
    }

    public GameMenuMessages stopPatrols() {
        for (Army army : Manage.getCurrentEmpire().empireArmy) {
            if (army.getArmyForm().equals(Names.PATROL_UNIT.getName())) {
                army.setArmyForm(Names.STANDING_AMRY.getName());
            }
        }
        return GameMenuMessages.SUCCESS;
    }

    public GameMenuMessages PitchDitchHauntsEnemy(Matcher x1, Matcher y1) {
        int xOfPitch = Integer.parseInt(x1.group("x"));
        int yOfPitch = Integer.parseInt(y1.group("y"));
        if (!Map.getBuildingMap()[xOfPitch][yOfPitch].isEmpty() &&
                Map.getBuildingMap()[xOfPitch][yOfPitch].get(0) instanceof PitchDitch) {
            if (checkIfAllAreArchers()) {
                ((PitchDitch) Map.getBuildingMap()[xOfPitch][yOfPitch].get(0)).fireState = true;
                Map.getBuildingMap()[xOfPitch][yOfPitch].clear();
                Map.getTroopMap()[xOfPitch][yOfPitch].clear();
                Map.getObstacleMap()[xOfPitch][yOfPitch].clear();
            }
            return GameMenuMessages.IMPROPER_UNIT;
        }
        return GameMenuMessages.IMPROPER_LOCATION;
    }

    public boolean checkIfAllAreArchers() {
        int number = 0;
        for (Army army : selectedUnit) {
            if (army instanceof ArchersAndThrowers) {
                number++;
            }
        }
        return number == selectedUnit.size();
    }

    //TODO : Conditions for not getting out of borders
    public GameMenuMessages pourOil(String direction) {
        int y0fPossibleEnemy;
        int xOfPossibleEnemy;
        int killCount = Manage.getCurrentEmpire().getEngineerCount();
        for (int j = 0; j < Empire.pourOilCoordinate.size(); j++) {
            int x = Empire.pourOilCoordinate.get(j) / Map.mapSize;
            int y = Empire.pourOilCoordinate.get(j) % Map.mapSize;
            if (direction.equals(Names.NORTH.getName())) {
                for (int i = -1; i <= 1; i++) {
                    y0fPossibleEnemy = y + i;
                    if (!Map.getTroopMap()[x - 2][y0fPossibleEnemy].isEmpty()) {
                        if (killCount != 0) {
                            killTroopsOfEnemy(x - 2, y0fPossibleEnemy, killCount);
                        }
                    }
                }
            } else if (direction.equals(Names.SOUTH.getName())) {
                for (int i = -1; i <= 1; i++) {
                    y0fPossibleEnemy = y + i;
                    if (!Map.getTroopMap()[x + 2][y0fPossibleEnemy].isEmpty()) {
                        if (killCount != 0) {
                            killTroopsOfEnemy(x + 2, y0fPossibleEnemy, killCount);
                        }
                    }
                }
            } else if (direction.equals(Names.WEST.getName())) {
                for (int i = -1; i <= 1; i++) {
                    xOfPossibleEnemy = x + i;
                    if (!Map.getTroopMap()[xOfPossibleEnemy][y - 2].isEmpty()) {
                        if (killCount != 0) {
                            killTroopsOfEnemy(xOfPossibleEnemy, y - 2, killCount);
                        }
                    }
                }

            } else if (direction.equals(Names.EAST.getName())) {
                for (int i = -1; i <= 1; i++) {
                    xOfPossibleEnemy = x + i;
                    if (!Map.getTroopMap()[xOfPossibleEnemy][y + 2].isEmpty()) {
                        if (killCount != 0) {
                            killTroopsOfEnemy(xOfPossibleEnemy, y + 2, killCount);
                        }
                    }
                }
            }
        }
        return GameMenuMessages.SUCCESS;
    }

    public void cagedWarDogsAttack() {
        for (int j = 0; j < Empire.cagedWarDogsCoordinate.size(); j++) {
            int x = Empire.cagedWarDogsCoordinate.get(j) / Map.mapSize;
            int y = Empire.cagedWarDogsCoordinate.get(j) / Map.mapSize;
            int floorOfX, floorOfY, ceilOfX, ceilOfY;
            for (int i = 0; i < 3; i++) {
                floorOfX = x - i;
                floorOfY = y - i;
                ceilOfX = x + i;
                ceilOfY = y + i;
                for (int m = floorOfX; m <= ceilOfX; m++) {
                    for (int n = floorOfY; n <= ceilOfY; n++) {
                        if (!Map.getTroopMap()[m][n].isEmpty()) {
                            if (killedByDogs(m, n)) {
                                Map.getBuildingMap()[x][y].clear();
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    public void killTroopsOfEnemy(int x, int y, int killCount) {
        for (int i = 0; i < Map.getTroopMap()[x][y].size(); i++) {
            Army army = Map.getTroopMap()[x][y].get(i);
            if (!army.getOwner().equals(Manage.getCurrentEmpire()) && killCount != 0) {
                Map.getTroopMap()[army.xCoordinate][army.yCoordinate].remove(army);
                Manage.getEmpireByNickname(army.getOwner().getName()).empireArmy.remove(army);
                i--;
                killCount--;
            }
        }
    }

    public static boolean killedByDogs(int x, int y) {
        for (int i = 0; i < Map.getTroopMap()[x][y].size(); i++) {
            if (!Map.getTroopMap()[x][y].get(i).getOwner().equals(Manage.getCurrentEmpire())) {
                Map.getTroopMap()[x][y].remove(Map.getTroopMap()[x][y].get(i));
                Empire empire = Map.getTroopMap()[x][y].get(i).getEmpire();
                empire.empireArmy.remove(Map.getTroopMap()[x][y].get(i));
                return true;
            }
        }
        return false;
    }

    public GameMenuMessages conquerGates(Matcher x1, Matcher y1) {
        int xOfGate = Integer.parseInt(x1.group("x"));
        int yOfGate = Integer.parseInt(y1.group("y"));
        int index;
        if (isGate(xOfGate, yOfGate)) {
            for (int i = 0; i < Map.wall.length; i++) {
                for (int j = 0; j < Map.wall[i].length; j++) {
                    if (!Map.getBuildingMap()[i][j].get(0).getOwner().equals(Manage.getCurrentEmpire())
                            && isWall(i, j)) {
                        if (checkIfWallIsBesideGate(i, j, xOfGate, yOfGate)) {
                            selectedUnit.clear();
                            Climbers ladderMan = new Climbers(Manage.getCurrentEmpire());
                            selectedUnit.add(ladderMan);
                            setPathForUnits(i, j);
                            index = ladderMan.myPath.size() - 1;
                            int xOfLadderMan = ladderMan.myPath.get(index) / PathFindingController.size;
                            int yOfLadderMan = ladderMan.myPath.get(index) % PathFindingController.size;
                            ladderMan.LadderMen(xOfLadderMan, yOfLadderMan);
                            Map.getTroopMap()[xOfLadderMan][yOfLadderMan].add(ladderMan);
                            Manage.getCurrentEmpire().empireArmy.add(ladderMan);
                            ((StoneGateWay) Map.getBuildingMap()[xOfGate][yOfGate].get(0)).flagOfEnemy = true;
                            Map.notPassable[xOfGate][yOfGate] = false;
                            ((StoneGateWay) Map.getBuildingMap()[xOfGate][yOfGate].get(0)).setGateOpen(true);
                            return GameMenuMessages.SUCCESS;
                        }
                    }
                }
            }
        }
        return GameMenuMessages.WRONG_COORDINATE_FOR_BUILDING_TYPE;
    }

    private static boolean checkIfWallIsBesideGate(int x, int y, int xOfGate, int yOfGate) {
        return Map.getBuildingMap()[x][y].get(0).equals(Map.getBuildingMap()[xOfGate][yOfGate - 1].get(0))
                || Map.getBuildingMap()[x][y].get(0).equals(Map.getBuildingMap()[xOfGate][yOfGate + 1].get(0))
                || Map.getBuildingMap()[x][y].get(0).equals(Map.getBuildingMap()[xOfGate + 1][yOfGate].get(0))
                || Map.getBuildingMap()[x][y].get(0).equals(Map.getBuildingMap()[xOfGate - 1][yOfGate].get(0));
    }

    public GameMenuMessages defenceByPortableShields(Matcher x1, Matcher y1) {
        int x = Integer.parseInt(x1.group("x"));
        int y = Integer.parseInt(y1.group("y"));
        ArchersAndThrowers shield = new ArchersAndThrowers(Manage.getCurrentEmpire());
        if (Map.getTroopMap()[x][y].isEmpty()) {
            shield.portableShield(x, y);
            Manage.getCurrentEmpire().empireArmy.add(0, shield);
            Map.getTroopMap()[x][y].add(shield);
        }
        return GameMenuMessages.LOCATION_CONTAINS_BUILDING;
    }

    public GameMenuMessages damageByBatteringRam(Matcher x1, Matcher y1) {
        int x = Integer.parseInt(x1.group("x"));
        int y = Integer.parseInt(y1.group("y"));
        if (isGate(x, y) || isWall(x, y) || isTower(x, y)) {
            if (isAllUnitsBatteringRam()) {
                moveUnit(x, y);
                int damage = Map.getBuildingMap()[x][y].get(0).getHp() - (selectedUnit.get(0)).getAttackPower();
                Map.getBuildingMap()[x][y].get(0).setHp(damage);
                if (checkIfRemoveBuildingPossible(damage)) Map.getBuildingMap()[x][y].remove(0);
            }
            return GameMenuMessages.IMPROPER_UNIT;
        }
        return GameMenuMessages.IMPROPER_LOCATION;
    }

    public boolean isAllUnitsBatteringRam() {
        int number = 0;
        for (Army army : selectedUnit) {
            if (army.getNames().getName().equals(Names.BATTERING_RAM.getName())) {
                number++;
            }
        }
        return number == selectedUnit.size();
    }

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

    public void setSieges() {
        for (Empire empire : Manage.getAllEmpires()) {
            for (Army army : empire.empireArmy) {
                if (army.getNames().equals(Names.TREBUCHET) || army.getNames().equals(Names.CATAPULT)
                        || army.getNames().equals(Names.FireThrowers)) {
                    throwers.add((ArchersAndThrowers) army);
                }
            }
            makeSiegesWorkAutomatically();
        }
        killUnit();
    }

    public void makeSiegesWorkAutomatically() {
        if (!throwers.isEmpty()) {
            for (ArchersAndThrowers throwers : throwers) {
                setRangeLookingForEnemy(throwers);
            }
        }
    }

    public void setRangeLookingForEnemy(ArchersAndThrowers seige) {
        int floorOfX, floorOfY, ceilOfX, ceilOfY;
        for (int i = 1; i <= seige.getAttackRange(); i++) {
            floorOfX = seige.getCurrentX() - i;
            floorOfY = seige.getCurrentY() - i;
            ceilOfX = seige.getCurrentX() + i;
            ceilOfY = seige.getCurrentY() + i;
            if (floorOfX < 0) floorOfX = 0;
            if (floorOfY < 0) floorOfY = 0;
            if (ceilOfX > Map.mapSize) ceilOfX = Map.mapSize - 1;
            if (ceilOfY > Map.mapSize) ceilOfY = Map.mapSize - 1;
            if (LookForEnemyInRangeForBuilding(floorOfX, floorOfY, ceilOfX, ceilOfY, seige)) return;
        }
        for (int i = 1; i <= seige.getAttackRange(); i++) {
            floorOfX = seige.getCurrentX() - i;
            floorOfY = seige.getCurrentY() - i;
            ceilOfX = seige.getCurrentX() + i;
            ceilOfY = seige.getCurrentY() + i;
            if (floorOfX < 0) floorOfX = 0;
            if (floorOfY < 0) floorOfY = 0;
            if (ceilOfX > Map.mapSize) ceilOfX = Map.mapSize - 1;
            if (ceilOfY > Map.mapSize) ceilOfY = Map.mapSize - 1;
            if (LookForEnemyInRangeForTroops(floorOfX, floorOfY, ceilOfX, ceilOfY, seige)) return;
        }

    }

    public boolean LookForEnemyInRangeForBuilding(int floorX, int floorY, int ceilX, int ceilY, ArchersAndThrowers seige) {
        for (int i = floorX; i <= ceilX; i++) {
            for (int j = floorY; j <= ceilY; j++) {
                if (i == seige.getCurrentX() && j == seige.getCurrentY()) continue;
                if (!Map.getBuildingMap()[i][j].isEmpty() && !Map.getBuildingMap()[i][j].get(0).getOwner().equals(Manage.getCurrentEmpire())) {
                    Map.getBuildingMap()[i][j].remove(0);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean LookForEnemyInRangeForTroops(int floorX, int floorY, int ceilX, int ceilY, ArchersAndThrowers seige) {
        for (int i = floorX; i <= ceilX; i++) {
            for (int j = floorY; j <= ceilY; j++) {
                if (i == seige.getCurrentX() && j == seige.getCurrentY()) continue;
                if (!Map.getTroopMap()[i][j].isEmpty()) {
                    for (int k = 0; k < Map.getTroopMap()[i][j].size(); k++) {
                        Army army = Map.getTroopMap()[i][j].get(k);
                        if (!army.getOwner().equals(Manage.getCurrentEmpire())) {
                            army.setHp(0);
                            removeKilledUnitFromEmpireHashmap(army.getNames().getName(), army.getEmpire());
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public GameMenuMessages digDitch(Matcher x1, Matcher y1) {
        int x = Integer.parseInt(x1.group("x"));
        int y = Integer.parseInt(y1.group("y"));
        if (Map.getBuildingMap()[x][y].isEmpty()) {
            if (selectedUnit.size() != 0) {
                if (validationOfArmiesType(Names.SPEAR_MEN.getName())) {
                    String unitMoved = moveUnit(x, y).getMessages();
                    if (unitMoved.equals(GameMenuMessages.ARMY_DEPLOYED.getMessages())) {
                        PitchDitch pitchDitch = new PitchDitch(Manage.getCurrentEmpire());
                        Map.getBuildingMap()[x][y].add(0, pitchDitch);
                        pitchDitch.digState = false;
                    } else System.out.println(unitMoved);
                } else return GameMenuMessages.IMPROPER_UNIT;
            } else return GameMenuMessages.NO_UNIT_SELECTED;
        }
        return GameMenuMessages.LOCATION_CONTAINS_BUILDING;
    }

    public GameMenuMessages removePitchDitch(Matcher x1, Matcher y1) {
        int x = Integer.parseInt(x1.group("x"));
        int y = Integer.parseInt(y1.group("y"));
        if (Map.getBuildingMap()[x][y].get(0) instanceof PitchDitch) {
            if (((PitchDitch) Map.getBuildingMap()[x][y].get(0)).digState) {
                Map.getBuildingMap()[x][y].remove(0);
                return GameMenuMessages.BUILDING_REMOVED;
            } else {
                Map.getBuildingMap()[x][y].remove(0);
                return GameMenuMessages.DITCH_DIGGING_STOPPED;
            }
        }
        return GameMenuMessages.WRONG_COORDINATE_FOR_BUILDING_TYPE;
    }

    public boolean validationOfArmiesType(String typeOfArmy) {
        int number = 0;
        for (Army army : selectedUnit) {
            if (army.getNames().getName().equals(typeOfArmy)) {
                number++;
            }
        }
        return number == selectedUnit.size();
    }

    //TODO : CHECK RETURN SENTENCES
    public GameMenuMessages digTunnel(Matcher x1, Matcher y1) {
        int x = Integer.parseInt(x1.group("x"));
        int y = Integer.parseInt(y1.group("y"));
        setPathForUnits(x, y);
        for (Army army : selectedUnit) {
            if (army instanceof Tunneler) {
                List<Integer> pathList = army.myPath;
                for (int i = 0; i < pathList.size(); i++) {
                    if (i == army.speed()) {
                        break;
                    }
                    int nextX = pathList.get(i) / PathFindingController.size;
                    int nextY = pathList.get(i) % PathFindingController.size;
                    if (!isTower(nextX, nextY) && !(Map.getBuildingMap()[nextX][nextY].get(0) instanceof PitchDitch)) {
                        if (Map.getBuildingMap()[nextX][nextY].get(0) instanceof Wall) {
                            Manage.getEmpireByNickname(army.getEmpire().getName()).empireArmy.remove(army);
                            Map.getTroopMap()[army.getCurrentX()][army.getCurrentY()].remove(army);
                            Map.getBuildingMap()[nextX][nextY].clear();
                            Map.notPassable[nextX][nextY] = false;
                            Map.wall[nextX][nextY] = false;
                        }
                    }
                }
                removeKilledUnitFromEmpireHashmap(army.getNames().getName(), army.getOwner());
                break;
            }
        }
        return GameMenuMessages.TUNNEL_DUG;
    }

    public GameMenuMessages fillDitch(Matcher x1, Matcher y1) {
        int x = Integer.parseInt(x1.group("x"));
        int y = Integer.parseInt(y1.group("y"));
        if (!Map.getBuildingMap()[x][y].get(0).getOwner().equals(Manage.getCurrentEmpire())) {
            if (!(validationOfArmiesType(Names.WORKER.getName()))) {
                String unitMoved = moveUnit(x, y).getMessages();
                if (unitMoved.equals(GameMenuMessages.ARMY_DEPLOYED.getMessages())) {
                    Map.getBuildingMap()[x][y].remove(0);
                    return GameMenuMessages.DITCH_FILLED;
                } else System.out.println(unitMoved);
            }
            return GameMenuMessages.IMPROPER_UNIT;
        }
        return GameMenuMessages.FILLING_YOUR_DITCH;
    }

    public GameMenuMessages siegeTowersAction(Matcher x1, Matcher y1) {
        int x = Integer.parseInt(x1.group("x"));
        int y = Integer.parseInt(y1.group("y"));
        if (validLocationForSiegeTower(x, y)) {
            setPathForUnits(x, y);
            for (Army army : selectedUnit) {
                int index = army.myPath().size() - 1;
                int xOfSiegeTower = army.myPath.get(index) / PathFindingController.size;
                int yOfSiegeTower = army.myPath.get(index) % PathFindingController.size;
                moveUnit(xOfSiegeTower, yOfSiegeTower);
            }
            return GameMenuMessages.SUCCESS;
        }
        return GameMenuMessages.IMPROPER_LOCATION;
    }

    public boolean checkIfRemoveBuildingPossible(int hpOfBuilding) {
        return hpOfBuilding <= 0;
    }

    public boolean isGate(int x, int y) {
        if (!Map.getBuildingMap()[x][y].get(0).getOwner().equals(Manage.getCurrentEmpire())) {
            return Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.SMALL_STONE_GATE_HOUSE) ||
                    Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.BIG_STONE_GATE_HOUSE);
        }
        return false;
    }

    public boolean isWall(int x, int y) {
        if (!Map.getBuildingMap()[x][y].get(0).getOwner().equals(Manage.getCurrentEmpire())) {
            return Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.SMALL_WALL) ||
                    Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.BIG_WALL);
        }
        return false;
    }

    public boolean isTower(int x, int y) {
        if (!Map.getBuildingMap()[x][y].get(0).getOwner().equals(Manage.getCurrentEmpire())) {
            return Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.DEFEND_TOWER) ||
                    Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.LOOKOUT_TOWER) ||
                    Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.ROUND_TOWER) ||
                    Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.PERIMETER_TOWER) ||
                    Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.SQUARE_TOWER);
        }
        return false;
    }

    public boolean isDefenceBuildingOfEnemy(int x, int y) {
        if (Map.getBuildingMap()[x][y].get(0).getOwner() != Manage.getCurrentEmpire()) {
            return Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.PERIMETER_TOWER) ||
                    Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.DEFEND_TOWER) ||
                    Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.SQUARE_TOWER) ||
                    Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.KILLING_PIT) ||
                    Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.PITCH_RIG);
        }
        return false;
    }

    public int findTroopInMap(int x, int y) {
        for (int i = 0; i < Map.getTroopMap()[x][y].size(); i++) {
            if (!Map.getTroopMap()[x][y].get(i).getOwner().getName().equals(Manage.getCurrentEmpire().getName())) {
                return i;
            }
        }
        return -1;
    }

    public boolean validLocationForSiegeTower(int x, int y) {
        return Map.getBuildingMap()[x + 1][y].get(0).getName().getName().equals(model.Building.Names.BIG_WALL.getName())
                || Map.getBuildingMap()[x + 1][y].get(0).getName().getName().equals(model.Building.Names.SMALL_WALL.getName())
                || Map.getBuildingMap()[x - 1][y].get(0).getName().getName().equals(model.Building.Names.BIG_WALL.getName())
                || Map.getBuildingMap()[x - 1][y].get(0).getName().getName().equals(model.Building.Names.SMALL_WALL.getName())
                || Map.getBuildingMap()[x][y - 1].get(0).getName().getName().equals(model.Building.Names.BIG_WALL.getName())
                || Map.getBuildingMap()[x][y - 1].get(0).getName().getName().equals(model.Building.Names.SMALL_WALL.getName())
                || Map.getBuildingMap()[x][y + 1].get(0).getName().getName().equals(model.Building.Names.BIG_WALL.getName())
                || Map.getBuildingMap()[x][y + 1].get(0).getName().getName().equals(model.Building.Names.SMALL_WALL.getName());
    }

    public void removeAllSelectedUnits() {
        for (Army army : selectedUnit) {
            Manage.getCurrentEmpire().empireArmy.remove(army);
        }
    }

    public void fight() {
        AttackArmyToArmyController.battleWithEnemy();
        makeSiegesWorkAutomatically();
    }

    public static void removeEmpireTroopsFromGame(Empire empire) {
        for (int i = 0; i < empire.empireArmy.size(); i++) {
            int x = empire.empireArmy.get(i).xCoordinate;
            int y = empire.empireArmy.get(i).yCoordinate;
            Map.troopMap[x][y].remove(empire.empireArmy.get(i));
            empire.empireArmy.remove(i);
            i--;
        }
    }

    public static void removeKilledUnitFromEmpireHashmap(String troopName, Empire empire) {
        switch (troopName) {
            case "archer":
                empire.setEuropeArcherCount(empire.getEuropeArcherCount() - 1);
            case "spearMan":
                empire.setSpearManCount(empire.getSpearManCount() - 1);
            case "maceMan":
                empire.setMaceManCount(empire.getMaceManCount() - 1);
            case "crossbowMan":
                empire.setCrossbowManCount(empire.getCrossbowManCount() - 1);
            case "pikeMan":
                empire.setPikeManCount(empire.getPikeManCount() - 1);
            case "swordMan":
                empire.setSwordManCount(empire.getSwordManCount() - 1);
            case "knight":
                empire.setKnightCount(empire.getKnightCount() - 1);
            case "blackMonk":
                empire.setBlackMonkCount(empire.getBlackMonkCount() - 1);
            case "catapult":
                empire.setCatapultCount(empire.getCatapultCount() - 1);
            case "trebuchet":
                empire.setTrebuchetCount(empire.getTrebuchetCount() - 1);
            case "siegeTower":
                empire.setSiegeTowerCount(empire.getSiegeTowerCount() - 1);
            case "fireBalista":
                empire.setFireBalistaCount(empire.getFireBalistaCount() - 1);
            case "batteringRam":
                empire.setBatteringRamCount(empire.getBatteringRamCount() - 1);
            case "portableShield":
                empire.setPortableShieldCount(empire.getPortableShieldCount() - 1);
            case "arabianBow":
                empire.setArabianBowCount(empire.getArabianBowCount() - 1);
            case "slave":
                empire.setSlaveCount(empire.getSlaveCount() - 1);
            case "slinger":
                empire.setSlingerCount(empire.getSlingerCount() - 1);
            case "assassin":
                empire.setAssassinCount(empire.getAssassinCount() - 1);
            case "horseArcher":
                empire.setHorseArcherCount(empire.getHorseArcherCount() - 1);
            case "arabianSwordMan":
                empire.setArabianSwordManCount(empire.getArabianSwordManCount() - 1);
            case "fireThrower":
                empire.setFireThrowerCount(empire.getFireThrowerCount() - 1);
            case "engineer":
                empire.setEngineerCount(empire.getEngineerCount() - 1);
            case "ladderMan":
                empire.setLadderManCount(empire.getLadderManCount() - 1);
            case "tunneler":
                empire.setTunnelerCount(empire.getTunnelerCount() - 1);
        }
    }

    public static boolean enemyInRange(int x, int y) {
        int floorOfX, floorOfY, ceilOfX, ceilOfY;
        for (int i = 0; i < 3; i++) {
            floorOfX = x - i;
            floorOfY = y - i;
            ceilOfX = x + i;
            ceilOfY = y + i;
            for (int j = floorOfX; j <= ceilOfX; j++) {
                for (int k = floorOfY; k <= ceilOfY; k++) {
                    if (j == x && k == y) continue;
                    if (isEnemyBuilding(j, k) || isEnemyUnit(j, k)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isEnemyBuilding(int j, int k) {
        return !Map.getBuildingMap()[j][k].isEmpty() && !Map.getBuildingMap()[j][k].get(0).getOwner().equals(Manage.getCurrentEmpire());
    }

    private static boolean isEnemyUnit(int j, int k) {
        for (Army army : Map.getTroopMap()[j][k]) {
            if (!army.getOwner().equals(Manage.getCurrentEmpire())) {
                return true;
            }
        }
        return false;
    }

}

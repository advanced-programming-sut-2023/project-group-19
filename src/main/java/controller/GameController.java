package controller;

import model.Building.*;
import model.Empire;
import model.GroundType;
import model.Human.Names;
import model.Human.Troop.*;
import model.Manage;
import model.Map;
import model.Obstacle.ObstacleName;
import model.Obstacle.WaterSources;
import view.Messages.GameMenuMessages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
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
    public static double fearTroopImpact = Manage.getCurrentEmpire().getFearTroopImpact();
    private static int mapSize = CreateMapController.getSizeOfMap();
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

    public GameMenuMessages attackAllSelectedArchers(int x, int y) {
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
//
//    //TODO : run after change turn
//    {
//        gameController.setStateArmy();
//    }

    private void setStateArmy() {
        selectedUnit.clear();
        for (Army army : Manage.getCurrentEmpire().empireArmy) {
            if (isArcher(army) || army.getArmyForm().equals(Names.STANDING_AMRY.getName())) continue;
            selectedUnit.add(army);
            findEnemyInRange(army, army.getArmyForm());
            selectedUnit.clear();
        }
    }

    private static GameMenuMessages dropUnit(Matcher x1, Matcher y1, Matcher count, Matcher type) {
        int x = Integer.parseInt(x1.group("x"));
        int y = Integer.parseInt(y1.group("y"));
        int countOfUnits = Integer.parseInt(count.group("count"));
        String typeOfUnit = type.group("type");
        if (checkGroundTypeForUnits(x,y)){
            if (checkTypeOfUnitWithLocation(x,y,typeOfUnit)){
                addUnitsToMap(x,y,countOfUnits,typeOfUnit);
                return GameMenuMessages.SUCCESS;
            }
            return GameMenuMessages.IMPROPER_UNIT;
        }
        return GameMenuMessages.IMPROPER_LOCATION;
    }

    private static boolean isSpearMan(String type){
        return type.equals(Names.SPEAR_MEN.getName());
    }
    private static void addUnitsToMap(int x , int y,int count , String typeOfUnit){
        for (int i = 0 ; i < count ; i++){
            switch (typeOfUnit){
                case "Archer":
                    ArchersAndThrowers archer = new ArchersAndThrowers(Manage.getCurrentEmpire());
                    archer.archer(x,y);
                    Manage.getCurrentEmpire().empireArmy.add(archer);
                    Map.getTroopMap()[x][y].add(archer);
                case "Crossbowmen":
                    ArchersAndThrowers crossBowMan = new ArchersAndThrowers(Manage.getCurrentEmpire());
                    crossBowMan.Crossbowmen(x,y);
                    Manage.getCurrentEmpire().empireArmy.add(crossBowMan);
                    Map.getTroopMap()[x][y].add(crossBowMan);
                case "ArcherBow":
                    ArchersAndThrowers archerBow = new ArchersAndThrowers(Manage.getCurrentEmpire());
                    archerBow.ArcherBow(x,y);
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
                case "Catapult":
                    ArchersAndThrowers catapult = new ArchersAndThrowers(Manage.getCurrentEmpire());
                    catapult.catapult(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(catapult);
                    Map.getTroopMap()[x][y].add(catapult);
                    Map.getTroopMap()[x][y].add(catapult);
                case "Trebuchet":
                    ArchersAndThrowers trebuchet = new ArchersAndThrowers(Manage.getCurrentEmpire());
                    trebuchet.trebuchet(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(trebuchet);
                    Map.getTroopMap()[x][y].add(trebuchet);
                case "SiegeTower":
                    ArchersAndThrowers siegeTower = new ArchersAndThrowers(Manage.getCurrentEmpire());
                    siegeTower.siegeTower(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(siegeTower);
                    Map.getTroopMap()[x][y].add(siegeTower);
                case "FireBallista":
                    ArchersAndThrowers fireBallista = new ArchersAndThrowers(Manage.getCurrentEmpire());
                    fireBallista.fireBallista(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(fireBallista);
                    Map.getTroopMap()[x][y].add(fireBallista);
                case "BatteringRam":
                    ArchersAndThrowers batteringRam = new ArchersAndThrowers(Manage.getCurrentEmpire());
                    batteringRam.batteringRam(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(batteringRam);
                    Map.getTroopMap()[x][y].add(batteringRam);
                case "PortableShield":
                    ArchersAndThrowers portableShield = new ArchersAndThrowers(Manage.getCurrentEmpire());
                    portableShield.portableShield(x, y);
                    Manage.getCurrentEmpire().empireArmy.add(portableShield);
                    Map.getTroopMap()[x][y].add(portableShield);
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
                    arabSwordMen.ArabianSwordsmen(x,y);
                    Manage.getCurrentEmpire().empireArmy.add(arabSwordMen);
                    Map.getTroopMap()[x][y].add(arabSwordMen);
                case "Tunneler":
                    Tunneler tunneler = new Tunneler(Manage.getCurrentEmpire());
                    tunneler.Tunneler(x,y);
                    Manage.getCurrentEmpire().empireArmy.add(tunneler);
                    Map.getTroopMap()[x][y].add(tunneler);
            }
        }
    }
    private static boolean checkGroundTypeForUnits(int x , int y){
        return !Map.getGroundType()[x][y].get(0).getGroundType().equals(GroundType.PLAIN.getGroundType())
                && !Map.getGroundType()[x][y].get(0).getGroundType().equals(GroundType.STONE.getGroundType())
                && !Map.getGroundType()[x][y].get(0).getGroundType().equals(ObstacleName.BIG_POND.getObstacleName())
                && !Map.getGroundType()[x][y].get(0).getGroundType().equals(ObstacleName.SMALL_POND.getObstacleName())
                && !Map.getGroundType()[x][y].get(0).getGroundType().equals(ObstacleName.RIVER.getObstacleName())
                && !Map.getGroundType()[x][y].get(0).getGroundType().equals(ObstacleName.SEA.getObstacleName());
    }

    private static boolean checkTypeOfUnitWithLocation(int x, int y ,String type){
        return (Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.TUNNEL) && type.equals(Names.TUNNELER.getName())
                || ((!Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.TUNNEL) && !type.equals(Names.TUNNELER.getName())
                && !Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.PITCH_DITCH) && !type.equals(Names.SPEAR_MEN.getName())))
                || (Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.PITCH_DITCH) && !type.equals(Names.SPEAR_MEN.getName())));
    }

    private static void findEnemyInRange(Army army, String State) {
        int x = army.xCoordinate - 1;
        int y = army.yCoordinate - 1;
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
                if (moveUnitToEnemyLocationAngry(x, y, x1, x2, y1, y2, army)) return;
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
                    if (!army.getEmpire().equals(enemy.getEmpire())) {
                        if (army.getPastXcordinate() == x && army.getPastYcordinate() == y) {
                            army.setPastXcordinate(x);
                            army.setPastYcordinate(y);
                            gameController.moveUnit(i, j);
                            return true;
                        }
                        if (isSameGridIntoRange(army.getPastXcordinate(), army.getPastYcordinate(), army, i, j)) {
                            gameController.moveUnit(i, j);
                            return true;
                        }
                    }
                    //TODO : fight wall
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

    // range archer + height archer - enemy height
    //TODO : After every next turn please call it!
    private void setEnemyToTarget() {
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
    private static boolean moveUnitToEnemyLocationAngry(int x, int y, int x1, int x2, int y1, int y2, Army army) {
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
                                if(myUnit.restOfMoves != 0) {
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
                                    //TODO : RemoveTroop function
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
            if (army.getNames().getName().equals(Names.ASSASSINS.getName())) {
                PathFindingController.notPassable = Map.wallPassable;
            }else{
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
        setCoordinatesForPatrols(xOne,yOne,xTwo,yTwo);
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

    public void stopPatrols() {
        for (Army army : Manage.getCurrentEmpire().empireArmy) {
            if (army.getArmyForm().equals(Names.PATROL_UNIT.getName())) {
                army.setArmyForm(Names.STANDING_AMRY.getName());
            }
        }
    }

    public GameMenuMessages killingPitHauntsEnemy(int x, int y) {
        if (Map.getBuildingMap()[x][y].get(0) instanceof KillingPit) {
            for (int i = 0; i < Map.getTroopMap()[x][y].size(); i++) {
                int index = findTroopInMap(x, y);
                if (index != -1) {
                    Empire empireEnemy = Map.getTroopMap()[x][y].get(index).getOwner();
                    empireEnemy.empireArmy.remove(Map.getTroopMap()[x][y].get(index));
                    Map.getTroopMap()[x][y].remove(index);
                }
            }
            return GameMenuMessages.SUCCESS;
        }
        return GameMenuMessages.WRONG_COORDINATE_FOR_BUILDING_TYPE;
    }

    public GameMenuMessages PitchDitchHauntsEnemy(int xOfPitch, int yOfPitch) {
        if (!Map.getBuildingMap()[xOfPitch][yOfPitch].isEmpty() &&
                Map.getBuildingMap()[xOfPitch][yOfPitch].get(0) instanceof PitchDitch) {
            if (checkIfAllAreArchers()) {
                ((PitchDitch) Map.getBuildingMap()[xOfPitch][yOfPitch].get(0)).fireState = true;
                Map.getBuildingMap()[xOfPitch][yOfPitch].clear();
                Map.getTroopMap()[xOfPitch][yOfPitch].clear();
                Map.getObstacleMap()[xOfPitch][yOfPitch].clear();
                //TODO :  Ashes on the ground
                //TODO : Manghal atish??
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

    public GameMenuMessages BoilingPetrol(int xOfOilSmelter, int yOfOilSmelter) {
        //Todo: the boss of oils-melter is set at the moment it created so no need to check it
        //TODO : IN THE FIGHT ALGORITHM WE SHOULD CALL THESE FUNCTIONS ALL AFTER MOVING
        if (Map.getBuildingMap()[xOfOilSmelter][yOfOilSmelter].get(0) instanceof OilSmelter) {
            if (selectedUnit.get(0).getNames().getName().equals(Names.ENGINEER.getName())) {
                String unitMoved = moveUnit(xOfOilSmelter, yOfOilSmelter).getMessages();
                if (unitMoved.equals(GameMenuMessages.ARMY_DEPLOYED.getMessages())) {
                    ((Engineer) selectedUnit.get(0)).isBowlFullOfOil = true;
                    return GameMenuMessages.SUCCESS;
                }
                System.out.println(unitMoved);
            } else return GameMenuMessages.IMPROPER_UNIT;
        }
        return GameMenuMessages.IMPROPER_LOCATION;
    }

    /*public GameMenuMessages conquerGates(int xOfGate, int yOfGate) {
        int index;
        int xOfWall = 0, yOfWall = 0;
        if (!Map.getBuildingMap()[xOfWall][yOfWall].get(0).getOwner().equals(Manage.getCurrentEmpire())) {
            if ((index = properUnitsToConquerGates()) != -1) {
                String unitMoved = moveUnit(xOfWall, yOfWall).getMessages();
                if (unitMoved.equals(GameMenuMessages.ARMY_DEPLOYED.getMessages())) {
                    ((Climbers) selectedUnit.get(index)).ladderIsOn = true;
                    moveUnit(xOfGate + 1, yOfGate);
                    ((Wall) Map.getBuildingMap()[xOfGate + 1][yOfGate].get(0)).enemyConqueredWall = true;
                    return GameMenuMessages.CONQUERED_GATE;
                } else System.out.println(unitMoved);
            } else return GameMenuMessages.IMPROPER_UNIT;
        }return GameMenuMessages.IMPROPER_LOCATION;
    }*/
    public GameMenuMessages defenceByPortableShields(int x, int y) {
        ArchersAndThrowers shield = new ArchersAndThrowers(Manage.getCurrentEmpire());
        if (Map.getTroopMap()[x][y].isEmpty()) {
            shield.portableShield(x, y);
            Manage.getCurrentEmpire().empireArmy.add(0, shield);
            Map.getTroopMap()[x][y].add(shield);
        }
        return GameMenuMessages.LOCATION_CONTAINS_BUILDING;
    }

    //TODO: STILL HAVE BUGS
    public GameMenuMessages damageByBatteringRam(int x, int y, ArchersAndThrowers batteringRam) { //TODO : The given coordinate is for the target
        if (isGate(x, y) || isWall(x, y) || isTower(x, y)) {
            if (batteringRam.getNames().getName().equals(Names.BATTERING_RAM.getName())) {
                selectedUnit.add(batteringRam);
                moveUnit(x, y);
                int damage = Map.getBuildingMap()[x][y].get(0).getHp() - (selectedUnit.get(0)).getAttackPower();
                Map.getBuildingMap()[x][y].get(0).setHp(damage);
                if (checkIfRemoveBuildingPossible(damage)) Map.getBuildingMap()[x][y].remove(0);
            }
            return GameMenuMessages.IMPROPER_UNIT;
        }
        return GameMenuMessages.IMPROPER_LOCATION;
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
        int floorOfX = 0, floorOfY, ceilOfX, ceilOfY;
        for (int i = 1 ; i <= seige.getAttackRange() ; i++) {
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
        for (int i = 1 ; i <= seige.getAttackRange() ; i++) {
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
                        if (!Map.getTroopMap()[i][j].get(k).getOwner().equals(Manage.getCurrentEmpire())) {
                            Map.getTroopMap()[i][j].get(k).setHp(0);
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public void damageByFireThrowersOnBuildings(int x, int y, Army fireBallista) {
        if (fireBallista.getNames().getName().equals(Names.FIRE_BALLISTA.getName())) {
            if (!Map.getBuildingMap()[x][y].isEmpty()) {
                if (isDefenceBuildingOfEnemy(x, y)) {//todo : why?
                    //TODO : Define range
                    int index = Map.getBuildingMap()[x][y].size() - 1;
                    int hpOfBuildings = Map.getBuildingMap()[x][y].get(index).getHp() - fireBallista.getAttackPower();
                    if (checkIfRemoveBuildingPossible(hpOfBuildings)) Map.getBuildingMap()[x][y].remove(index);
                    else Map.getBuildingMap()[x][y].get(index).setHp(hpOfBuildings);
                }
            } else if (!Map.getTroopMap()[x][y].isEmpty()) {
                for (int i = 0; i < 2; i++) {
                    if (i < Map.getTroopMap()[x][y].size()) {
                        Map.getTroopMap()[x][y].remove(i);
                        i--;
                    }
                }
            }
        }
    }

    public GameMenuMessages digDitch(int x, int y) {
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

    public GameMenuMessages removePitchDitch(int x, int y) {
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

    public GameMenuMessages digTunnel(Matcher x1, Matcher y1) {
        int x = Integer.parseInt(x1.group("x"));
        int y = Integer.parseInt(y1.group("y"));
        if (Map.getBuildingMap()[x][y].get(0) != null && !Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.PITCH_DITCH)
                && !Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.KILLING_PIT)
                && (!isTower(x, y) || Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.LOOKOUT_TOWER))) {
            if (validationOfArmiesType(Names.TUNNELER.getName())) {
                String unitMoved = moveUnit(x, y).getMessages();
                if (unitMoved.equals(GameMenuMessages.ARMY_DEPLOYED.getMessages())) {
                    //todo : remove buildings according to direction
                    Map.getBuildingMap()[x][y] = null;
                    Tunnel tunnel = new Tunnel(Manage.getCurrentEmpire());
                    tunnel.length = selectedUnit.get(0).speed();
                    Map.getBuildingMap()[x][y].add(tunnel);
                    removeAllSelectedUnits();
                    selectedUnit = null;
                }
                return GameMenuMessages.TUNNEL_DUG;
            }
            return GameMenuMessages.IMPROPER_UNIT;
        }
        return GameMenuMessages.IMPROPER_LOCATION;
    }

    public GameMenuMessages fillDitch(int x, int y) {
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

    public void siegeTowersAction(int x, int y) {
        if (validLocationForSiegeTower(x, y)) {
            //TODO :Check Call move units
        }
    }

    public boolean checkIfRemoveTroopsPossible(int hpOfTroops) {
        return hpOfTroops <= 0;
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
    public static void removeEmpireTroopsFromGame(Empire empire){
        for(int i = 0 ; i < empire.empireArmy.size() ; i++)
        {
            int x = empire.empireArmy.get(i).xCoordinate;
            int y = empire.empireArmy.get(i).yCoordinate;
            Map.troopMap[x][y].remove(empire.empireArmy.get(i));
            empire.empireArmy.remove(i);
            i--;
        }
    }
}

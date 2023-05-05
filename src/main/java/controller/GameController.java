package controller;

import model.Building.*;
import model.Empire;
import model.GroundType;
import model.Human.Names;
import model.Human.Troop.*;
import model.Human.Worker;
import model.Manage;
import model.Map;
import model.Obstacle.ObstacleName;
import view.Messages.GameMenuMessages;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;


public class GameController {
    //TODO : Remember that you should delete an army if its hp <= 0 from myUnit
    //TODO : Every time you enter gameMenu and every turn you should call isMyArmyDeployed
    //TODO : All buildings require fire state boolean
    //TODO : Check the output of your Funtions
    private static int mapSize = CreateMapController.getSizeOfMap();
    public static GameController gameController = new GameController();

    public ArrayList<Army> selectedUnit = new ArrayList<>();

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
        if (army instanceof ArchersAndThrowers) return true;
        else return false;
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
    {
        gameController.setStateArmy();
    }
    private void setStateArmy(){
        selectedUnit.clear();
        for(Empire empire : Manage.allEmpires){
            for(Army army : empire.empireArmy){
                if(isArcher(army) || army.getArmyForm().equals(Army.StateOfEnemy.STANDING)) continue;
                selectedUnit.add(army);
                findEnemyInRange(army,army.getArmyForm());
                selectedUnit.clear();
            }
        }
    }
    private static void findEnemyInRange(Army army,Army.StateOfEnemy State){
        int x = army.xCoordinate - 1;
        int y = army.yCoordinate - 1;
        int x1 = 0 , x2 = 0 , y1 = 0  , y2 = 0;
        for(int i = 1 ; i <= army.getAttackRange() ; i ++){
            x1 = x - i ;
            x2 = x + i ;
            y1 = y - i ;
            y2 = y + i ;
            if(x1 <= 0) x1 = 0 ;
            if(x2 >= mapSize) x2 = mapSize - 1 ;
            if(y1 <= 0) y1 = 0 ;
            if(y2 >= mapSize) y2 = mapSize - 1;
            if(State.equals(Army.StateOfEnemy.OFFENSIVE))
                if(moveUnitToEnemyLocationAngry(x,y,x1,x2,y1,y2,army)) return;
            else{
                if(moveUnitToEnemyLocationDefensive(x,y,x1,x2,y1,y2,army)) return;
                }
        }
    }

    private static boolean moveUnitToEnemyLocationDefensive(int x, int y, int x1, int x2, int y1, int y2, Army army) {
        for(Army enemy : Map.getTroopMap()[x][y]){
            if(!enemy.getEmpire().equals(army.getEmpire())) return true ;
        }
        for(int i = x1 ; i <= x2 ; i ++){
            for(int j = y1 ; j <= y2 ; j ++){
                for(Army enemy : Map.getTroopMap()[i][j]){
                    if(!army.getEmpire().equals(enemy.getEmpire())){
                        if(army.getPastXcordinate() == 500 && army.getPastYcordinate() == 500){
                            army.setPastXcordinate(x);
                            army.setPastYcordinate(y);
                            gameController.moveUnit(i,j);
                        }else{
                            
                        }

                    }
                }
            }
        }


    }

    private static boolean moveUnitToEnemyLocationAngry(int x , int y  , int x1 , int x2 , int y1 , int y2 , Army army){
        for(Army enemy : Map.getTroopMap()[x][y]){
            if(!enemy.getEmpire().equals(army.getEmpire())) return true ;
        }
        for(int i = x1 ; i <= x2 ; i ++){
            for(int j = y1 ; j <= y2 ; j ++){
                for(Army enemy : Map.getTroopMap()[i][j]){
                    if(enemy.getEmpire().equals(army.getEmpire()) || enemy.getHp() <= 0) continue;
                    gameController.moveUnit(x,y);
                    return true ;
                }
            }
        }
        return false ;
    }

    public GameMenuMessages moveUnit(int xCoordinate, int yCoordinate) {
        if (selectedUnit.size() != 0) {
            if (validFinalLocation(xCoordinate, yCoordinate)) {
                if (!isTrebuchet()) {
                    setPathForUnits(xCoordinate, yCoordinate);
                    for (Army myUnit : Manage.getCurrentEmpire().empireArmy) {
                        List<Integer> pathList = myUnit.myPath;
                        if (pathList != null && pathList.size() != 0) {
                            for (int i = 0; i < pathList.size(); i++) {
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
                                Map.getTroopMap()[myUnit.xCoordinate][myUnit.yCoordinate].add(myUnit);
                                pathList.remove(i);
                                i--;
                            }
                            if (pathList.size() <= myUnit.speed()) {
                                myUnit.myPath = null;
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
            PathFindingController.startX = army.getCurrentX() - 1;
            PathFindingController.startY = army.getCurrentY() - 1;
            PathFindingController.goalX = xCoordinate - 1;
            PathFindingController.goalY = yCoordinate - 1;
            army.myPath = PathFindingController.pathFinding();
        }
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
        for (int i = 0; i < selectedUnit.size(); i++) {
            if (selectedUnit.get(i) instanceof ArchersAndThrowers) {
                if (selectedUnit.get(i).getNames().equals(Names.TREBUCHET)) {
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
        if ((Map.getBuildingMap()[myUnit.goalXCoordinate][myUnit.goalYCoordinate].get(0) instanceof KillingPit ||
                Map.getBuildingMap()[myUnit.goalXCoordinate][myUnit.goalYCoordinate].get(0) instanceof PitchDitch) &&
                !(myUnit.getNames().getName().equals(Names.SPEAR_MEN.getName()))) {
            return true;
        }
        return false;
    }
    /*public void TowersGame() {
        //if (selectedUnit instanceof A)
    }*/

    public GameMenuMessages killingPitHauntsEnemy(int x, int y, Army enemy) {
        if (Map.getBuildingMap()[x][y].get(0) instanceof KillingPit) {
            for (int i = 0; i < Map.getTroopMap().length; i++) {
                int index = findTroopInMap(x, y, enemy);
                removeTroopFromMyArmy(Map.getTroopMap()[x][y].get(index));
                Map.getTroopMap()[x][y].remove(index);
            }
            return GameMenuMessages.SUCCESS;
        }
        return GameMenuMessages.WRONG_COORDINATE_FOR_BUILDING_TYPE;
    }

    public void PitchDitchHauntsEnemy(int xOfPitch, int yOfPitch) {
        //TODO : Is there any possible unit or whatever which throws fire except firethrowers?
        if (!Map.getBuildingMap()[xOfPitch][yOfPitch].isEmpty() &&
                Map.getBuildingMap()[xOfPitch][yOfPitch].get(0) instanceof PitchDitch) {
            if (selectedUnit.get(0) instanceof ArchersAndThrowers) {
                if (selectedUnit.get(0).getNames().getName().equals(Names.FireThrowers.getName())) {
                    ((PitchDitch) Map.getBuildingMap()[xOfPitch][yOfPitch].get(0)).fireState = true;
                    removeAllObjectsOnPitchDitch(xOfPitch, yOfPitch);
                    //TODO :  Ashes on the ground
                }
            }
        }
    }

    public void removeAllObjectsOnPitchDitch(int xOfPitch, int yOfPitch) {
        for (int i = 1; i < Map.getBuildingMap()[xOfPitch][yOfPitch].size(); i++) {
            Map.getBuildingMap()[xOfPitch][yOfPitch].remove(i);
            i--;
        }
        for (int j = 0; j < Map.getTroopMap()[xOfPitch][yOfPitch].size(); j++) {
            Map.getTroopMap()[xOfPitch][yOfPitch].remove(j);
            j--;
        }
        for (int k = 0; k < Map.getObstacleMap()[xOfPitch][yOfPitch].size(); k++) {
            Map.getObstacleMap()[xOfPitch][yOfPitch].remove(k);
            k--;
        }
    }
    public void BoilingPetrol(int x, int y) {

    }

    public void conquerGates() {

    }

    public void defenceByPortableShields(int x, int y) {
        ArchersAndThrowers shield = new ArchersAndThrowers(Manage.getCurrentEmpire());
        shield.portableShield(x, y + 1);//TODO:set the direction
        shield.shieldDirection = Names.VERTICAL;
        Manage.getCurrentEmpire().empireArmy.add(0, shield);
    }

    public void damageByBatteringRam(int x, int y) {
        if ( isGate(x, y) || isWall(x, y) || isTower(x, y)) {
            int damage = Map.getBuildingMap()[x][y].get(0).getHp() - (selectedUnit.get(0)).getAttackPower();
            Map.getBuildingMap()[x][y].get(0).setHp(damage);
            if (checkIfRemoveBuildingPossible(damage)) Map.getBuildingMap()[x][y].remove(0);
        }
    }

    public void damageByCatapult(int x, int y) {

    }

    public void damageByTrebuchet(int x, int y) {

    }
    public void damageByFireThrowersOnBuildings(int x , int y , ArchersAndThrowers fireBallista){
        if (fireBallista.getNames().getName().equals(Names.FIRE_BALLISTA.getName())) {
            if (!Map.getBuildingMap()[x][y].isEmpty()) {
                if (isDefenceBuildingOfEnemy(x, y)) {
                    int index = Map.getBuildingMap()[x][y].size() - 1;
                    int hpOfBuildings = Map.getBuildingMap()[x][y].get(index).getHp() - fireBallista.getAttackPower();
                    if (checkIfRemoveBuildingPossible(hpOfBuildings)) Map.getBuildingMap()[x][y].remove(index);
                    else Map.getBuildingMap()[x][y].get(index).setHp(hpOfBuildings);
                }
            } else if (!Map.getTroopMap()[x][y].isEmpty()) {
                for (int i = 0 ; i < 2 ; i++){
                    if (i < Map.getTroopMap()[x][y].size()){
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
                    PitchDitch pitchDitch = new PitchDitch(Manage.getCurrentEmpire());
                    Map.getBuildingMap()[x][y].add(0, pitchDitch);
                    pitchDitch.digState = 0;
                    for (Army army : selectedUnit) {
                        Map.getTroopMap()[x][y].add(army);
                        //TODO : IF THERE'S ENEMY THERE
                    }
                }else return GameMenuMessages.IMPROPER_UNIT;
            } else return GameMenuMessages.NO_UNIT_SELECTED;
        }return GameMenuMessages.LOCATION_CONTAINS_BUILDING;
    }
    public GameMenuMessages removePitchDitch(int x, int y) {
        if (Map.getBuildingMap()[x][y].get(0) instanceof PitchDitch) {
            if (((PitchDitch) Map.getBuildingMap()[x][y].get(0)).digState == 1) {
                Map.getBuildingMap()[x][y].remove(0);
                return GameMenuMessages.BUILDING_REMOVED;
            } else if (((PitchDitch) Map.getBuildingMap()[x][y].get(0)).digState == 0) {
                Map.getBuildingMap()[x][y].remove(0);
                return GameMenuMessages.DITCH_DIGGING_STOPPED;
            }
        }return GameMenuMessages.WRONG_COORDINATE_FOR_BUILDING_TYPE;
    }

    public boolean validationOfArmiesType(String typeOfArmy) {
        int number = 0;
        for (int i = 0; i < selectedUnit.size(); i++) {
            if (selectedUnit.get(i).getNames().getName().equals(typeOfArmy)) {
                number++;
            }
        }
        if (number == selectedUnit.size()) return true;
        return false;
    }

    public GameMenuMessages digTunnel (int x , int y){
        if (Map.getBuildingMap()[x][y].get(0) != null && !Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.PITCH_DITCH)
            &&  !Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.KILLING_PIT)
            && (!isTower(x , y) || Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.LOOKOUT_TOWER))){
            if (validationOfArmiesType(Names.TUNNELER.getName())){
                //TODO: CALL MOVE UNIT FOR TUNNELERS
                Map.getBuildingMap()[x][y] = null;
                Tunnel tunnel = new Tunnel(Manage.getCurrentEmpire());
                tunnel.length = selectedUnit.get(0).speed();
                Map.getBuildingMap()[x][y].add(tunnel);
                selectedUnit = null;
                return GameMenuMessages.TUNNEL_DUG;
            }return GameMenuMessages.IMPROPER_UNIT;
        }return GameMenuMessages.IMPROPER_LOCATION;
    }

    public GameMenuMessages fillDitch(int x , int y){
        if (!Map.getBuildingMap()[x][y].get(0).getOwner().equals(Manage.getCurrentEmpire())){
            if (!(validationOfArmiesType(Names.WORKER.getName()))){
                //TODO : CALL MOVE UNIT FOR SELCETED UNITS
                Map.getBuildingMap()[x][y].remove(0);
                return GameMenuMessages.DITCH_FILLED;
            }return GameMenuMessages.IMPROPER_UNIT;
        }return GameMenuMessages.FILLING_YOUR_DITCH;
    }

    public void seigeTowersAction(int x , int y){
        if (validLocationForSeigeTower(x, y)){
            //TODO :Check Call move units
        }
    }
    public boolean checkIfRemoveTroopsPossible(int hpOfTroops) {
        if (hpOfTroops <= 0) {
            return true;
        }
        return false;
    }

    public boolean checkIfRemoveBuildingPossible(int hpOfBuilding) {
        if (hpOfBuilding <= 0) {
            return true;
        }
        return false;
    }
    public boolean isGate(int x , int y){
        if (Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.SMALL_STONE_GATE_HOUSE) ||
                Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.BIG_STONE_GATE_HOUSE)){
            return true;
        }return false;
    }
    public boolean isWall(int x , int y){
        if (Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.SMALL_WALL) ||
                Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.BIG_WALL)){
            return true;
        }return false;
    }
    public boolean isTower(int x, int y) {
        if (Map.getBuildingMap()[x][y].get(0).getOwner() != Manage.getCurrentEmpire()) {
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

    public int findTroopInMap(int x, int y, Army army) {
        for (int i = 0; i < Map.getTroopMap()[x][y].size(); i++) {
            if (Map.getTroopMap()[x][y].get(i).getArmyForm().equals(army.getArmyForm()) &&
                    !Map.getTroopMap()[x][y].get(i).getOwner().getName().equals(Manage.getCurrentEmpire().getName())) {
                return i;
            }
        }
        return -1;
    }
    public int sumAllHpsOfTroopsInACell(int x, int y) {
        int sum = 0;
        for (Army army : Map.getTroopMap()[x][y]) {
            sum += army.getHp();
        }
        return sum;
    }

    public void removeTroopFromMyArmy(Army army) {
        for (Army myArmy : Manage.getCurrentEmpire().empireArmy) {
            if (myArmy.equals(army)) {
                Manage.getCurrentEmpire().empireArmy.remove(myArmy);
            }
        }
    }
    public boolean validLocationForSeigeTower(int x , int y){
        if (Map.getBuildingMap()[x+1][y].get(0).getName().getName().equals(model.Building.Names.BIG_WALL.getName())
            || Map.getBuildingMap()[x+1][y].get(0).getName().getName().equals(model.Building.Names.SMALL_WALL.getName())
            || Map.getBuildingMap()[x-1][y].get(0).getName().getName().equals(model.Building.Names.BIG_WALL.getName())
            || Map.getBuildingMap()[x-1][y].get(0).getName().getName().equals(model.Building.Names.SMALL_WALL.getName())
            || Map.getBuildingMap()[x][y-1].get(0).getName().getName().equals(model.Building.Names.BIG_WALL.getName())
            || Map.getBuildingMap()[x][y-1].get(0).getName().getName().equals(model.Building.Names.SMALL_WALL.getName())
            || Map.getBuildingMap()[x][y+1].get(0).getName().getName().equals(model.Building.Names.BIG_WALL.getName())
            || Map.getBuildingMap()[x][y+1].get(0).getName().getName().equals(model.Building.Names.SMALL_WALL.getName())){
            return true;
        }return false;
    }
}

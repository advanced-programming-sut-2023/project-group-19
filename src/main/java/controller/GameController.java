package controller;

import com.sun.source.tree.ReturnTree;
import model.Building.*;
import model.Empire;
import model.GroundType;
import model.Human.Human;
import model.Human.Names;
import model.Human.Troop.*;
import model.Human.Worker;
import model.Manage;
import model.Map;
import model.Obstacle.ObstacleName;
import view.Messages.GameMenuMessages;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class GameController {
    //TODO : Remember that you should delete an army if its hp <= 0 from myUnit
    //TODO : Every time you enter gameMenu and every turn you should call isMyArmyDeployed
    public ArrayList<Army> selectedUnit = new ArrayList<>();

    public GameMenuMessages selectUnit(Matcher x, Matcher y) {
        int flag = 0;
        int xCoordinate = Integer.parseInt(x.group("x"));
        int yCoordinate = Integer.parseInt(y.group("y"));
        if (!Map.getTroopMap()[xCoordinate][yCoordinate].isEmpty()) {
            for (int i = 0 ; i < Map.getTroopMap()[xCoordinate][yCoordinate].size() ; i++) {
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
                                if (Map.getBuildingMap()[myUnit.goalXCoordinate][myUnit.goalYCoordinate].get(0) instanceof KillingPit
                                        || Map.getBuildingMap()[myUnit.goalXCoordinate][myUnit.goalYCoordinate].get(0) instanceof PitchDitch) {
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
            }return GameMenuMessages.LOCATION_CONTAINS_WATERSOURCES_OR_HIGH_PLACES;
        }return GameMenuMessages.NO_UNIT_SELECTED;
    }

    public void setPathForUnits(int xCoordinate, int yCoordinate) {
        for (Army army : selectedUnit) {
            PathFindingController.startX = army.getCurrentX() - 1;
            PathFindingController.startY = army.getCurrentY() - 1;
            PathFindingController.goalX = xCoordinate - 1;
            PathFindingController.goalY = yCoordinate - 1;
            army.myPath = PathFindingController.pathFinding();
            break;
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
                if (((ArchersAndThrowers) selectedUnit.get(i)).getNames().equals(Names.TREBUCHET)) {
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

    public void TowersGame() {
        //if (selectedUnit instanceof A)
    }

    public void KillingPitHauntsEnemy(int x, int y, Army enemy) {
        int index = findTroop(x, y, enemy);
        if (Map.getBuildingMap()[x][y].get(0) instanceof KillingPit &&
                index != -1) {
            Map.getBuildingMap()[x][y].remove(0);
            if (enemy.getHp() - Map.getBuildingMap()[x][y].get(0).getHp() <= 0) {
                Map.getTroopMap()[x][y].remove(index);
            }
        }
    }

    /*public void PitchDitchHauntsEnemy(int x , int y ){
        //Is there any possible unit or whatever which throws fire?
        if (Map.getBuildingMap()[x][y].get(0) instanceof PitchDitch){
            if (selectedUnit instanceof ArchersAndThrowers){
                if (((ArchersAndThrowers) selectedUnit).getNames().equals(Names.FireThrowers)){
                    //TODO : Findpath algorithm to find the closest pitchdith;
                    ((PitchDitch)Map.getBuildingMap()[x][y].get(0)).fireState = true ;
                    if (Map.getBuildingMap()[x][y].get(0) != null){
                        Map.getBuildingMap()[x][y].remove(0);
                    }
                    if (Map.getObstacleMap()[x][y].get(0) != null){
                        Map.getObstacleMap()[x][y].remove(0);
                    }
                    for (int i = 0 ; i < Map.getTroopMap()[x][y].size() ; i++) {
                        if (Map.getTroopMap()[x][y].get(i) != null) {
                            Map.getTroopMap()[x][y].remove(i);
                            i--;
                        }
                    }
                }
            }
        }
    }*/
    public void BoilingPetrol(int x, int y) {

    }

    public void conquerGates() {

    }

    public void defenceByPortableShields(int x, int y) {
        //TODO : THE DIRECTION OF EVERY SHIELD WILL BE SET BY MOVE DIRECTION
        ArchersAndThrowers shieldUp = new ArchersAndThrowers(Manage.getCurrentEmpire());
        ArchersAndThrowers shieldDown = new ArchersAndThrowers(Manage.getCurrentEmpire());
        ArchersAndThrowers shieldRight = new ArchersAndThrowers(Manage.getCurrentEmpire());
        ArchersAndThrowers shieldLeft = new ArchersAndThrowers(Manage.getCurrentEmpire());
        shieldUp.portableShield(x, y + 1);
        shieldUp.shieldDirection = Names.VERTICAL;
        shieldDown.portableShield(x, y - 1);
        shieldDown.shieldDirection = Names.VERTICAL;
        shieldRight.portableShield(x + 1, y);
        shieldRight.shieldDirection = Names.HORIZONTAL;
        shieldLeft.portableShield(x - 1, y);
        shieldLeft.shieldDirection = Names.HORIZONTAL;
    }
    /*public void damageByBatteringRam(int x  , int y ){
        if (Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.SMALL_STONE_GATE_HOUSE) ||
            Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.BIG_STONE_GATE_HOUSE) ||
            Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.SMALL_WALL) ||
            Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.BIG_WALL) ||
            isTower(x , y)){
            Map.getBuildingMap()[x][y].get(0).setHp(Map.getBuildingMap()[x][y].get(0).getHp()-((ArchersAndThrowers)selectedUnit).getAttackPower());
        }
    }*/

    public void damageByCatapult(int x, int y) {

    }

    public void damageByTrebuchet(int x, int y) {

    }
    /*public void damageByFireThrowers(int x , int y){
        if (Map.getTroopMap()[x][y].get(0) != null){
            int hpOfTroops=Map.getTroopMap()[x][y].get(0).getHp() - ((ArchersAndThrowers)selectedUnit).getAttackPower();
            if (removeTroops(hpOfTroops)) Map.getTroopMap()[x][y].remove(0);
            else Map.getTroopMap()[x][y].get(0).setHp(hpOfTroops);
        } else if (Map.getBuildingMap()[x][y].get(0) != null) {
            int hpOfBuildings = Map.getBuildingMap()[x][y].get(0).getHp() - ((ArchersAndThrowers)selectedUnit).getAttackPower();
            if (removeBuilding(hpOfBuildings)) Map.getBuildingMap()[x][y].remove(0);
            else Map.getBuildingMap()[x][y].get(0).setHp(hpOfBuildings);
        }
    }*/

    /*public GameMenuMessages digDitch(int x ,int y){
       if (Map.getBuildingMap()[x][y].get(0) == null) {
           if (selectedUnit instanceof Climbers && ((Climbers) selectedUnit).getNames().equals(Names.SPEAR_MEN)) {
               // TODO:TIMELINE FOR DIGGING THE DIG
           } else return GameMenuMessages.WRONG_UNIT_FOR_COMMAND;
       }return GameMenuMessages.LOCATION_CONTAINS_BUILDING;
    }*/
    public GameMenuMessages stopDitchDigging(int x, int y) {
        if (Map.getBuildingMap()[x][y].get(0) instanceof PitchDitch) {
            ((PitchDitch) Map.getBuildingMap()[x][y].get(0)).digState = false;
            return GameMenuMessages.DITCH_DIGGING_STOPPED;
        }
        return GameMenuMessages.WRONG_COORDINATE_FOR_BUILDING_TYPE;
    }

    public GameMenuMessages removePitchDitch(int x, int y) {
        if (Map.getBuildingMap()[x][y].get(0) instanceof PitchDitch) {
            if (((PitchDitch) Map.getBuildingMap()[x][y].get(0)).digCompleted) {
                Map.getBuildingMap()[x][y].remove(0);
                return GameMenuMessages.BUILDING_REMOVED;
            }
            return GameMenuMessages.INCOMPLETE_PITCH_DITCH;
        }
        return GameMenuMessages.WRONG_COORDINATE_FOR_BUILDING_TYPE;
    }

    /*public GameMenuMessages digTunnel (int x , int y){
        if (!isTower(x , y) && !Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.PITCH_DITCH)
            && Map.getBuildingMap()[x][y].get(0) != null){
            if (selectedUnit instanceof Tunneler){
                Tunnel tunnel = new Tunnel(Manage.getCurrentEmpire());
                Map.getBuildingMap()[x][y].add(tunnel);
                //TODO : TIMELINE FOR DIGGING TUNNEL PER TURN;
                Map.getBuildingMap()[x][y].remove(0);
                return GameMenuMessages.TUNNEL_DUG;
            }return GameMenuMessages.IMPROPER_UNIT;
        }return GameMenuMessages.IMPROPER_LOCATION;
    }

    public GameMenuMessages fillDitch(int x , int y){
        if (!Map.getBuildingMap()[x][y].get(0).getOwner().equals(Manage.getCurrentEmpire())){
            if (!(selectedUnit instanceof Worker)){
                Map.getBuildingMap()[x][y].remove(0);
                return GameMenuMessages.DITCH_FILLED;
            }return GameMenuMessages.WRONG_UNIT_FOR_COMMAND;
        }return GameMenuMessages.FILLING_YOUR_DITCH;
    }*/
    public boolean removeTroops(int hpOfTroops) {
        if (hpOfTroops <= 0) {
            return true;
        }
        return false;
    }

    public boolean removeBuilding(int hpOfBuilding) {
        if (hpOfBuilding <= 0) {
            return true;
        }
        return false;
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

    public int findTroop(int x, int y, Army army) {
        for (int i = 0; i < Map.getTroopMap()[x][y].size(); i++) {
            if (Map.getTroopMap()[x][y].get(i).getArmyForm().equals(army.getArmyForm()) &&
                    !Map.getTroopMap()[x][y].get(i).getOwner().getName().equals(Manage.getCurrentEmpire().getName())) {
                return i;
            }
        }
        return -1;
    }
}

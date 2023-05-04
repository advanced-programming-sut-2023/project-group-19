package controller;

import javafx.scene.web.WebHistory;
import model.Empire;
import model.Human.Troop.ArchersAndThrowers;
import model.Human.Troop.Army;
import model.Human.Troop.Soldiers;

import java.lang.reflect.Array;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;

import model.* ;

public class AttackArmyToArmyController {
    private static int mapSize = CreateMapController.getSizeOfMap();
//    public static void createArmy(Empire empire , int number , ArrayList<Army> armies){
//        for(int i = 0 ; i  < number ; i ++){
//            Soldiers soldier = new Soldiers(empire);
//            soldier.ArabianSwordsmen(3,3);
//            armies.add(soldier);
//        }
//    }
//    public static void gatheringArmiesArrayTakingDamageAndKill(ArrayList<Army> armies){
//        ArrayList<Empire> empires = getEmpiers(armies);
//        HashMap<Empire,ArrayList<Army>> divisionArmy = divideSoliders(empires,armies);
//        ArrayList<ArrayList<Army>> armiesIntoArrayList = convertHashMapIntoArrayList(divisionArmy);
//        for(int i = 0 ; i < armiesIntoArrayList.size() ; i ++){
//            for(int j = i + 1 ; j < armiesIntoArrayList.size() ; j ++){
//                takeDamageToArmies(armiesIntoArrayList.get(i),armiesIntoArrayList.get(j));
//                takeDamageToArmies(armiesIntoArrayList.get(j),armiesIntoArrayList.get(i));
//                killUnit(armies);
//            }
//        }
//    }
//    private static void killUnit(ArrayList<Army> armies){
////        armies.removeIf(army -> army.getHp() <= 0);
//        for(Army army : armies){
//            if(army.getHp() <= 0){
//                Empire empire = army.getEmpire();
//                empire.empireArmy.remove(army);
//                armies.remove(army);
//            }
//        }
//    }
//    private static void takeDamageToArmies(ArrayList<Army> redTeam , ArrayList<Army> blueTeam){
//        int i = 0 ;
//        for(int j = 0 ; j < redTeam.size() ; j ++){
//            if(blueTeam.get(i).getHp() <= 0 && i != blueTeam.size() - 1) i ++ ;
//            else if(blueTeam.get(i).getHp() <= 0 && i == blueTeam.size() - 1) break;
//            int newHitPoint = blueTeam.get(i).getHp() - redTeam.get(j).getAttackPower();
//            blueTeam.get(i).setHp(newHitPoint);
//        }
//    }
//    private static ArrayList<ArrayList<Army>> convertHashMapIntoArrayList
//            (HashMap<Empire,ArrayList<Army>> divisionArmy){
//        /////////  //// /// //
//        ArrayList<ArrayList<Army>> convertedArrayList = new ArrayList<>();
//        for(Empire empire : divisionArmy.keySet()){
//            int i = 0 ;
//            convertedArrayList.add(new ArrayList<>());
//            for(Army army : divisionArmy.get(empire)){
//                convertedArrayList.get(i).add(army);
//                i ++ ;
//            }
//        }
//        return convertedArrayList ;
//    }
//
//    private static ArrayList<Empire> getEmpiers(ArrayList<Army> armies){
//        ArrayList<Empire> empires = new ArrayList<>();
//        for(Army army : (armies)){
//            if(!empires.contains(army.getEmpire())) empires.add(army.getEmpire());
//        }
//        return empires  ;
//    }
//    private static HashMap<Empire,ArrayList<Army>>  divideSoliders(ArrayList<Empire> empires,ArrayList<Army> armies){
//        HashMap<Empire,ArrayList<Army>> divisionArmy = new HashMap<>();
//        for(Empire empire : empires){
//            divisionArmy.put(empire,new ArrayList<>());
//            for(Army army : armies){
//                if(army.getEmpire().equals(empire)) divisionArmy.get(empire).add(army);
//            }
//        }
//        return divisionArmy ;
//    }


    public static void battleWithEnemy(){
        for(Empire empire : Manage.getAllEmpires()){
            for(Army army : empire.empireArmy){
                findEnemyToFight(army);
            }
        }
        findArcher();
        killUnit();
    }
    //TODO : jolgeh
    //TODO : sleep in login and register
    private static void killUnit(){
        for(Empire empire : Manage.getAllEmpires()){
            for(Army army : empire.empireArmy){
                if(army.getHp() <= 0){
                    int x = army.xCoordinate - 1 ;
                    int y = army.yCoordinate - 1 ;
                    Map.getTroopMap()[x][y].remove(army);
                    empire.empireArmy.remove(army);
                }
            }
        }
    }
    private static boolean isArcher(Army army){
        if(army instanceof ArchersAndThrowers) return true ;
        else return  false ;
    }

    private static void findEnemyToFight(Army army) {
        int x = army.xCoordinate - 1 ;
        int y = army.yCoordinate - 1 ;
        for(Army enemy : Map.getTroopMap()[x][y]){
            if(enemy.getEmpire().equals(army.getEmpire()) || enemy.getHp() <= 0) continue;
            int newHitPoint = enemy.hp() - army.getAttackPower();
            enemy.setHp(newHitPoint);
            break;
        }

    }

    private static void findArcher(){
        for(Empire empire : Manage.getAllEmpires()){
            for(Army army : empire.empireArmy){
                if(!isArcher(army)) continue;
                findEnemyForArcher(army);
            }
        }
    }
    private static final int archerRange = 2 ;
    private static void findEnemyForArcher(Army army){
        int x = army.xCoordinate - 1;
        int y = army.yCoordinate - 1;
        int x1 = 0 , x2 = 0 , y1 = 0  , y2 = 0;

        for(int i = 1 ; i <= archerRange ; i ++){
            x1 = x - i ;
            x2 = x + i ;
            y1 = y - i ;
            y2 = y + i ;
            if(x1 <= 0) x1 = 0 ;
            if(x2 >= mapSize) x2 = mapSize - 1 ;
            if(y1 <= 0) y1 = 0 ;
            if(y2 >= mapSize) y2 = mapSize - 1;
            if(applyDamageWithArcher(x,y,x1,x2,y1,y2,army)) return;
        }

//        for(int i = 1 ; i <= archerRange ; i ++){
//        }
    }

    private static boolean applyDamageWithArcher(int x , int y ,int x1, int x2, int y1, int y2, Army army) {
        for(int i = x1 ; i <= x2 ; i ++){
            for(int j = y1 ; j <= y2 ; j ++){
                if(i == x || j == y) continue;
                for(Army enemy : Map.getTroopMap()[i][j]){
                    if(enemy.getEmpire().equals(army.getEmpire()) || enemy.getHp() <= 0) continue;
                    int newHitPoint = enemy.hp() - army.getAttackPower();
                    enemy.setHp(newHitPoint);
                    return true ;
                }
            }
        }
        return false ;
    }



}

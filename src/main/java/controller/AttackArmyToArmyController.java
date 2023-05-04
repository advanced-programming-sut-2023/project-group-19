package controller;

import javafx.scene.web.WebHistory;
import model.Empire;
import model.Human.Troop.Army;
import model.Human.Troop.Soldiers;

import java.lang.reflect.Array;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AttackArmyToArmyController {
    public static void createArmy(Empire empire , int number , ArrayList<Army> armies){
        for(int i = 0 ; i  < number ; i ++){
            Soldiers soldier = new Soldiers(empire);
            soldier.ArabianSwordsmen(3,3);
            armies.add(soldier);
        }
    }
    public static void gatheringArmiesArrayTakingDamageAndKill(ArrayList<Army> armies){
        ArrayList<Empire> empires = getEmpiers(armies);
        HashMap<Empire,ArrayList<Army>> divisionArmy = divideSoliders(empires,armies);
        ArrayList<ArrayList<Army>> armiesIntoArrayList = convertHashMapIntoArrayList(divisionArmy);
        for(int i = 0 ; i < armiesIntoArrayList.size() ; i ++){
            for(int j = i + 1 ; j < armiesIntoArrayList.size() ; j ++){
                takeDamageToArmies(armiesIntoArrayList.get(i),armiesIntoArrayList.get(j));
                takeDamageToArmies(armiesIntoArrayList.get(j),armiesIntoArrayList.get(i));
                killUnit(armies);
            }
        }
    }
    private static void killUnit(ArrayList<Army> armies){
//        armies.removeIf(army -> army.getHp() <= 0);
        for(Army army : armies){
            if(army.getHp() <= 0){
                Empire empire = army.getEmpire();
                empire.empireArmy.remove(army);
                armies.remove(army);
            }
        }
    }
    private static void takeDamageToArmies(ArrayList<Army> redTeam , ArrayList<Army> blueTeam){
        int i = 0 ;
        for(int j = 0 ; j < redTeam.size() ; j ++){
            if(blueTeam.get(i).getHp() <= 0 && i != blueTeam.size() - 1) i ++ ;
            else if(blueTeam.get(i).getHp() <= 0 && i == blueTeam.size() - 1) break;
            int newHitPoint = blueTeam.get(i).getHp() - redTeam.get(j).getAttackPower();
            blueTeam.get(i).setHp(newHitPoint);
        }
    }
    private static ArrayList<ArrayList<Army>> convertHashMapIntoArrayList
            (HashMap<Empire,ArrayList<Army>> divisionArmy){
        /////////  //// /// //
        ArrayList<ArrayList<Army>> convertedArrayList = new ArrayList<>();
        for(Empire empire : divisionArmy.keySet()){
            int i = 0 ;
            convertedArrayList.add(new ArrayList<>());
            for(Army army : divisionArmy.get(empire)){
                convertedArrayList.get(i).add(army);
                i ++ ;
            }
        }
        return convertedArrayList ;

    }

    private static ArrayList<Empire> getEmpiers(ArrayList<Army> armies){
        ArrayList<Empire> empires = new ArrayList<>();
        for(Army army : (armies)){
            if(!empires.contains(army.getEmpire())) empires.add(army.getEmpire());
        }
        return empires  ;
    }
    private static HashMap<Empire,ArrayList<Army>>  divideSoliders(ArrayList<Empire> empires,ArrayList<Army> armies){
        HashMap<Empire,ArrayList<Army>> divisionArmy = new HashMap<>();
        for(Empire empire : empires){
            divisionArmy.put(empire,new ArrayList<>());
            for(Army army : armies){
                if(army.getEmpire().equals(empire)) divisionArmy.get(empire).add(army);
            }
        }
        return divisionArmy ;
    }


}

import controller.BuildingController;
import model.Building.Armoury;
import model.Building.Building;
import model.Building.StoneGateWay;
import model.Building.Tower;
import model.Empire;
import model.Human.Troop.ArabArmy;
import model.Human.Troop.Army;
import model.Map;
import view.*;
import view.Commands.BuildingMessages;
import view.LoginMenu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Empire empire = new Empire();
        Map.CreateMap(200);
        StoneGateWay stoneGateWay = new StoneGateWay(empire);
        stoneGateWay.smallGateWay();
        Tower tower = new Tower(empire);
        tower.lookoutTower();
        System.out.println(tower);
        Map.buildingMap[0][1].add(tower);
        System.out.println(Map.buildingMap[0][1].get(0).getMaxHp());
        System.out.println(stoneGateWay.getMaxHP());
        Map.buildingMap[0][0].add(stoneGateWay);
        System.out.println(Map.buildingMap[0][0].get(0).hp());
        LoginMenu.run(new Scanner(System.in));
    }
}
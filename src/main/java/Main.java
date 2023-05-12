import controller.GameController;
import controller.JsonController;
import controller.PathFindingController;
import model.*;
import model.Building.*;
import model.Empire;
import model.Human.Troop.ArchersAndThrowers;
import model.Manage;
import model.Map;
import model.User;
import view.BuildingMenu;
import view.GameMenu;
import view.LoginMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Math.floor;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
//        String command = "drop building -x  1 -y 2";
//        System.out.println(BuildingCommands.getMatcher(command, BuildingCommands.BUILDING_COMMANDS_DROP_BUILDING));


        Scanner scanner = new Scanner(System.in);
//        LoginMenu.run(scanner);


//        Scanner scanner = new Scanner(System.in);
//        EmpireMenu empireMenu = new EmpireMenu();
//        Empire empire = new Empire();
//        Manage.setCurrentEmpire(empire);
//        Map.CreateMap(200);
//        empireMenu.run(scanner);

        User newUser2 = new User("user5", "aa", "reza", "a", "1", "1", 1);
        User newUser = new User("user6", "aa", "ali", "a", "1", "1", 1);
        Empire empire = new Empire();
        Manage.setCurrentEmpire(empire);
        Empire newEmpire = new Empire();
        newEmpire.setUser(newUser2);
        Map.CreateMap(200);
        empire.setUser(newUser);
        empire.setEngineerCount(60000000);
        empire.setWorkerCount(500000);
        empire.setWoodCount(20000000);
//        empire.setStoneCount(300000);
        empire.setGoldCount(1000000);
        empire.setWoodCount(1000000);
        empire.setOilAmount(1000000);
        empire.setIronCount(10000000);
        empire.setSwordCount(1000000);
        empire.setSpearCount(100000000);
        empire.setHorseCount(10000000);
        empire.setLeatherArmourCount(1000000000);
        empire.setMetalArmourCount(100000000);
        empire.setMaceCount(100000000);
        empire.setBowCount(1000000000);
        empire.setPeakCount(100000000);
        Tower tower = new Tower(empire);
        tower.lookoutTower();
        ArchersAndThrowers archer = new ArchersAndThrowers(newEmpire);
        archer.archer(3, 5);
//        Map.troopMap[3][5].add(archer);
        Map.buildingMap[5][5].add(tower);
        empire.setPeasantCount(100000000);
        BuildingMenu buildingMenu = new BuildingMenu();
        buildingMenu.run(scanner);

//        Industry woodCutter = new Industry(empire);
//        woodCutter.woodCutter();
//        Map.buildingMap[0][0].add(woodCutter);
//        System.out.println(Map.buildingMap[0][0].get(0).getName());
//        empire.setFearRateNumber(-5);
//        EmpireController.setFearFactor();
//        System.out.println(empire.getResourcesCapacity());
//        System.out.println(empire.getFearWorkerImpact());
//        empire.setWoodCutterCount(1);
//        System.out.println(empire.getWoodCutterCount());
//        empire.independentProductionBuilding();
//        System.out.println(empire.getWoodCount());


//        Army army =new Army(empire);
//        Army army1 =new Army(empire);
//        Map.getTroopMap()[1][1].add(army);
//        Map.getTroopMap()[1][1].add(army1);
//        GameMenu gameMenu = new GameMenu();
//        gameMenu.run(scanner);


//        Empire empire = new Empire();
//        empire.setName("arian");
//        Map.CreateMap(200);
//        ArchersAndThrowers archer = new ArchersAndThrowers(empire);
//        archer.ArcherBow(0,0);
//        ArchersAndThrowers shield = new ArchersAndThrowers(empire);
//        shield.portableShield(3,4);
//        ArchersAndThrowers archer2 = new ArchersAndThrowers(empire);
//        archer2.HorseArchers(2,2);
//        ArchersAndThrowers archer3 = new ArchersAndThrowers(empire);
//        archer3.batteringRam(5,6);
//        ArrayList <ArchersAndThrowers> arrayList = new ArrayList<>();
//        arrayList.add(archer);
//        arrayList.add(archer2);
//        arrayList.add(archer3);
//        arrayList.add(0,shield);
//        for (ArchersAndThrowers i : arrayList) {
//            System.out.println(i.getNames().getName());
//        }


//        empire.setName("arian");
//        Map.CreateMap(200);
//        Manage.getAllEmpires().add(empire);
//        Shop shop = new Shop(empire);
//        shop.shop();
//        Map.getBuildingMap()[1][5].add(shop);
//        GameMenu gameMenu = new GameMenu();
//        gameMenu.run(scanner);


    }
}
import controller.JsonController;
import controller.PathFindingController;
import model.Building.*;
import model.Empire;
import model.Human.Troop.ArchersAndThrowers;
import model.Human.Troop.Army;
import model.Manage;
import model.Map;
import model.User;
import view.Commands.BuildingCommands;
import view.EmpireMenu;
import view.GameMenu;
import view.LoginMenu;
import java.io.IOException;
import java.util.*;

import static java.lang.Math.floor;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {


//        Scanner scanner = new Scanner(System.in);
//        EmpireMenu empireMenu = new EmpireMenu();
//        Empire empire = new Empire();
//        Manage.setCurrentEmpire(empire);
//        Map.CreateMap(200);
//        empireMenu.run(scanner);

//        LoginMenu.run(new Scanner(System.in));


//          Scanner scanner = new Scanner(System.in);
//        Empire empire = new Empire();
//        Map.CreateMap(200);
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
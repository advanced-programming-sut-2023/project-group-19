import controller.GameController;
import controller.JsonController;
import controller.PathFindingController;
import model.*;
import model.Building.*;
import model.Human.Troop.ArchersAndThrowers;
import model.Human.Troop.Army;
import model.Human.Troop.Climbers;
import model.Human.Troop.Tunneler;
import model.Obstacle.Obstacle;
import model.Obstacle.ObstacleName;
import model.Obstacle.WaterSources;
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
        Scanner scanner = new Scanner(System.in);
        User newUser = new User("user6", "aa", "ali", "a", "1", "1", 1);
        User newUser1 = new User("user6", "aa", "dorsa", "a", "1", "1", 1);
        Empire Ali = new Empire();
        Empire Dorsa = new Empire();
        Ali.setUser(newUser);
        Dorsa.setUser(newUser1);
        Manage.setCurrentEmpire(Ali);
        Map.CreateMap(200);
        Map.mapSize = 200;

        ArchersAndThrowers archerEnemy = new ArchersAndThrowers(Dorsa);
        archerEnemy.archer(3,1);
        Map.getTroopMap()[3][1].add(archerEnemy);
        Dorsa.empireArmy.add(archerEnemy);
        System.out.println("Army :"+ archerEnemy+ " owner: "+archerEnemy.getOwner().getName());
        ArchersAndThrowers archerEnemy2 = new ArchersAndThrowers(Dorsa);
        archerEnemy2.archer(2,4);
        Dorsa.empireArmy.add(archerEnemy2);
        Map.getTroopMap()[2][4].add(archerEnemy2);
        System.out.println("Army :"+ archerEnemy2+ " owner: "+archerEnemy2.getOwner().getName());
        ArchersAndThrowers archerEnemy3 = new ArchersAndThrowers(Dorsa);
        archerEnemy3.archer(2,3);
        Map.getTroopMap()[2][3].add(archerEnemy3);
        Dorsa.empireArmy.add(archerEnemy3);
        System.out.println("Army :"+ archerEnemy3+ " owner: "+archerEnemy3.getOwner().getName());
        ArchersAndThrowers archerEnemy4 = new ArchersAndThrowers(Dorsa);
        archerEnemy4.archer(2,4);
        Map.getTroopMap()[2][4].add(archerEnemy4);
        Dorsa.empireArmy.add(archerEnemy4);
        System.out.println("Army :"+ archerEnemy4+ " owner: "+archerEnemy4.getOwner().getName());


        ArchersAndThrowers archers1 = new ArchersAndThrowers(Ali);
        archers1.archer(0,0);
        System.out.println("Army : "+archers1+" owner: "+archers1.getOwner().getName());
        ArchersAndThrowers archers2 = new ArchersAndThrowers(Ali);
        archers2.archer(0,0);
        System.out.println("Army : "+archers2+" owner: "+archers2.getOwner().getName());
        ArchersAndThrowers archers3 = new ArchersAndThrowers(Ali);
        archers3.archer(0,0);
        System.out.println("Army : "+archers3+" owner: "+archers3.getOwner().getName());
        ArchersAndThrowers archers4 = new ArchersAndThrowers(Ali);
        archers4.archer(2,3);
        System.out.println("Army : "+archers4+" owner: "+archers4.getOwner().getName());
        ArchersAndThrowers archers5 = new ArchersAndThrowers(Ali);
        archers5.trebuchet(2,3);




        Map.getTroopMap()[2][0].add(archers1);
        Map.getTroopMap()[2][0].add(archers2);
        Map.getTroopMap()[3][1].add(archers3);
        Map.getTroopMap()[3][1].add(archers4);
        Map.getTroopMap()[3][1].add(archers5);


        Ali.empireArmy.add(archers1);
        Ali.empireArmy.add(archers2);
        Ali.empireArmy.add(archers3);
        Ali.empireArmy.add(archers4);
        Ali.empireArmy.add(archers5);
        Map.notPassable[2][3] = false;

        StoneGateWay stoneGateWay = new StoneGateWay(Dorsa);
        stoneGateWay.smallGateWay(Names.NS);
        stoneGateWay.setGateOpen(false);
        Map.getBuildingMap()[3][1].add(0,stoneGateWay);
        Wall wall = new Wall(Dorsa);
        wall.bigWall();
        Wall wall1 = new Wall(Dorsa);
        wall1.bigWall();
        Map.getBuildingMap()[3][0].add(0,wall1);
        Map.getBuildingMap()[3][2].add(0,wall);
        Tower tower = new Tower(Dorsa);
        tower.lookoutTower();
        Map.getBuildingMap()[3][5].add(0,tower);
        Tunneler tunneler = new Tunneler(Ali);
        tunneler.Tunneler(2,3);
        GameMenu gameMenu = new GameMenu();
        gameMenu.run(scanner);

//        GameController gameController = new GameController();
//        gameController.cagedWarDogsAttack();

    }
}
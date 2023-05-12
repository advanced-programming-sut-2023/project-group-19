import controller.GameController;
import controller.JsonController;
import controller.PathFindingController;
import model.*;
import model.Building.*;
import model.Human.Troop.ArchersAndThrowers;
import model.Human.Troop.Army;
import model.Human.Troop.Climbers;
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
        Manage.getAllEmpires().add(Dorsa);
        Manage.getAllEmpires().add(Ali);
        ArchersAndThrowers archerEnemy = new ArchersAndThrowers(Dorsa);
        archerEnemy.archer(5,2);
        Map.getTroopMap()[5][2].add(archerEnemy);
        Dorsa.empireArmy.add(archerEnemy);
        System.out.println("Army :"+ archerEnemy+ " owner: "+archerEnemy.getOwner().getName());

        ArchersAndThrowers archers1 = new ArchersAndThrowers(Ali);
        archers1.fireBallista(2,0);
        System.out.println("Army : "+archers1+" owner: "+archers1.getOwner().getName());
        ArchersAndThrowers archers2 = new ArchersAndThrowers(Ali);
        archers2.catapult(2,0);

        Map.getTroopMap()[2][0].add(archers1);
        Ali.empireArmy.add(archers1);
        Wall wall = new Wall(Dorsa);
        wall.bigWall();
        Map.getBuildingMap()[5][1].add(wall);
        GameController gameController = new GameController();
        gameController.setSieges();
        for (Army army : Dorsa.empireArmy){
            System.out.println(army);
        }
        System.out.println(Dorsa.empireArmy.size());
        System.out.println("building : "+Map.getBuildingMap()[5][1].size());
    }
}
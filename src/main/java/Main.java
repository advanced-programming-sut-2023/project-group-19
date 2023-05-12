import model.Empire;
import model.Human.Troop.ArchersAndThrowers;
import model.Manage;
import model.Map;
import model.User;
import view.GameMenu;
import view.LoginMenu;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        /*Scanner scanner = new Scanner(System.in);
        LoginMenu.run(scanner);*/
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
        ArchersAndThrowers archers1 = new ArchersAndThrowers(Ali);
        archers1.archer(2,0);
        archers1.fireBallista(2,0);
        System.out.println("Army : "+archers1+" owner: "+archers1.getOwner().getName());
        ArchersAndThrowers archers2 = new ArchersAndThrowers(Ali);
        archers2.archer(2,0);
        System.out.println("Army : "+archers2+" owner: "+archers2.getOwner().getName());
        Ali.empireArmy.add(archers1);
        Ali.empireArmy.add(archers2);
        Map.getTroopMap()[2][0].add(archers1);
        Map.getTroopMap()[2][0].add(archers2);
        GameMenu gameMenu = new GameMenu();
        gameMenu.run(scanner);
    }
}
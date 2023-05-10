import model.Building.*;
import model.Empire;
import model.Manage;
import model.Map;
import view.GameMenu;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        Empire empire = new Empire();
        Empire empire1 = new Empire();
        empire.setName("arian");
        empire1.setName("doreece");
        Manage.setCurrentEmpire(empire1);
        Map.CreateMap(200);
        Map.mapSize = 200;
        Manage.getAllEmpires().add(empire);
        Manage.getAllEmpires().add(empire1);
        Shop shop = new Shop(empire);
        shop.shop();
        shop.setOwner(empire);
        empire.setAppleCount(6);
        empire.setCheeseCount(3);
        empire1.setLeatherArmour(15);
        empire1.setOilAmount(17);
        empire1.setGoldCount(100000);
        empire.setEuropeArcherCount(80);
        Map.getBuildingMap()[1][5].add(shop);
        GameMenu gameMenu = new GameMenu();
        gameMenu.run(scanner);
    }
}
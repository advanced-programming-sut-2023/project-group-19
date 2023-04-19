import model.Building.Armoury;
import model.Building.Building;
import model.Empire;
import model.Human.Troop.ArabArmy;
import model.Human.Troop.Army;
import model.Map;
import view.*;
import view.LoginMenu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        LoginMenu.run(scanner);
    }
}
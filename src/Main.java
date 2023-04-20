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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        LoginMenu.run(scanner);
//        String regex = "[\\W_]";
//        Scanner scanner = new Scanner(System.in);
//        String text = scanner.nextLine();
//        Matcher matcher = Pattern.compile(regex).matcher(text);
//        if(matcher.find()) System.out.println("yes");
//        else System.out.println("no");
    }
}
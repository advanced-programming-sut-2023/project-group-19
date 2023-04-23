import controller.CreateMapController;
import controller.JsonController;
import model.Building.Armoury;
import model.Building.Building;
import model.Building.StoneGateWay;
import model.Building.Tower;
import model.Empire;
import model.Human.Troop.Army;
import model.Manage;
import model.Map;
import model.Obstacle.Tree;
import model.User;
import view.LoginMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Map.CreateMap(200);
        Empire emoire = new Empire();
        Army army = new Army(emoire);
        Map.getTroopMap()[199][197].add(army);
//        System.out.println(CreateMapController.showMap(199,199));

//        String regex =  "(?<number>\\d+)";
//        String text = "5";
//        Matcher matcher = Pattern.compile(regex).matcher(text);
//        matcher.find();
//        System.out.println(matcher.group("number"));
//        matcher.find();
//        System.out.println(matcher.group("number"));


//        System.out.println(Manage.allUsers.size());
//        User user2 = new User("afgjfgj" , "12jgjg3" , "mamagjjgjd" , "String emgjgjail", "String rjgjgjgjgecoveryQuestion", "Strgjgjgjing slogan", 0);

//        System.out.println(Manage.allUsers.size());
//        Scanner scanner = new Scanner(System.in);
//        LoginMenu.run(scanner);
//        user1.addUserToAllUsersArrayList(user1);
//        System.out.println(Manage.allUsers.get(0).getPassword());

//        JsonController.writeIntoFile(Manage.allUsers , "User.json");
//        JsonController.writeIntoFile(user2 , "LoggedInUser.json");
//        JsonController.readDataFile("User.json");
//        JsonController.saveAllUsersFileData();
//        JsonController.readDataFile("LoggedInUser.json");
//        User loggedInUser = JsonController.saveLoggedInUserFileData();
//        System.out.println(loggedInUser.getPassword());
//        System.out.println(Manage.allUsers.get(1).getUsername());
//        try{
//            System.out.println(loggedInUser.getUsername());
//        }
//        catch (NullPointerException e){
//            System.out.println("no user logged in");
//        }


//        Empire empire = new Empire();
//        Map.CreateMap(200);
//        StoneGateWay stoneGateWay = new StoneGateWay(empire);
//        stoneGateWay.smallGateWay();
//        Tower tower = new Tower(empire);
//        tower.lookoutTower();
//        System.out.println(tower);
//        Map.buildingMap[0][1].add(tower);
//        System.out.println(Map.buildingMap[0][1].get(0).getMaxHp());
//        System.out.println(stoneGateWay.getMaxHP());
//        Map.buildingMap[0][0].add(stoneGateWay);
//        System.out.println(Map.buildingMap[0][0].get(0).hp());
//        LoginMenu.run(new Scanner(System.in));
    }
}
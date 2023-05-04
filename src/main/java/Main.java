import controller.JsonController;
import controller.PathFindingController;
import model.Building.Names;
import model.Building.SiegeTent;
import model.Building.StoneGateWay;
import model.Building.Tower;
import model.Empire;
import model.Manage;
import model.Map;
import model.User;
import view.Commands.BuildingCommands;
import view.LoginMenu;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Math.floor;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
//        System.out.println(BuildingCommands.INVALID_COMMAND.getName());
//        System.out.println(Names.APOTHECARY.equals(Names.APOTHECARY));
//        System.out.println((int) floor(34 * 1.6));
//        Scanner scanner = new Scanner(System.in);
//        LoginMenu.run(scanner);
//        User user1 = new User("ali" , "123" , "mamad" , "String email", "String recoveryQuestion", "String slogan", 2);
//        User user2 = new User("afgjfgj" , "12jgjg3" , "mamagjjgjd" , "String emgjgjail", "String rjgjgjgjgecoveryQuestion", "Strgjgjgjing slogan", 0);
//        Manage.allUsers.add(user1);
//        Manage.allUsers.add(user2);
//        System.out.println(Manage.allUsers.get(0).getUsername());
//        JsonController.writeIntoFile(Manage.allUsers , "User.json");
//        JsonController.writeIntoFile(user2 , "LoggedInUser.json");
//        JsonController.readDataFile("User.json");
//        JsonController.saveAllUsersFileData();
//        JsonController.readDataFile("LoggedInUser.json");
//        User loggedInUser = JsonController.saveLoggedInUserFileData();
//        System.out.println(Manage.allUsers.get(1).getUsername());
//        try{
//            System.out.println(loggedInUser.getUsername());
//        }
//        catch (NullPointerException e){
//            System.out.println("no user logged in");
//        }

        // TODO : pathfinding sample

//        Map.CreateMap(200);
//        Map.mapSize = 200;
//        Map.notPassable[5][5] = true;
//        PathFindingController.size = Map.mapSize;
//        PathFindingController.notPassable = Map.notPassable;
//        PathFindingController.goalX = 199;
//        PathFindingController.goalY = 199;
//        PathFindingController.startX = 0;
//        PathFindingController.startY = 0;
//        List<Integer> path = PathFindingController.pathFinding();
//        int maxMove = 5;
//        if(path == null)
//            System.out.println("not possible move");
//        else {
//            System.out.println("x : " + path.get(maxMove ) / Map.mapSize + " y : " + path.get(maxMove ) % Map.mapSize);
//        }



//        Empire empire = new Empire();
//        StoneGateWay stoneGateWay = new StoneGateWay(empire);
//        stoneGateWay.smallGateWay();
//        SiegeTent siegeTent = new SiegeTent(empire);
//        siegeTent.siegeTent();
//        System.out.println(siegeTent.showBuildingName());
//        Map.buildingMap[0][1].add(siegeTent);
//        System.out.println(Map.buildingMap[0][1].get(0).showBuildingName());
//        System.out.println(stoneGateWay.getMaxHP());
//        Map.buildingMap[0][0].add(stoneGateWay);
//        System.out.println(Map.buildingMap[0][0].get(0).hp());
//        LoginMenu.run(new Scanner(System.in));
    }
}
package model;

import model.Building.Building;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Manage {
    public static ArrayList<Game> allGames = new ArrayList<>();
    public static ArrayList<Empire> allEmpires = new ArrayList<>();
    public static ArrayList<Building> burningEmpires = new ArrayList<>();
    public final static ArrayList<String> namesOfAllPossibleBuildings = new ArrayList<>();

    public static DataInputStream masterServerDataInputStream;
    public static DataOutputStream masterServerDataOutputStream;

    static {
        namesOfAllPossibleBuildings.add("Armoury");
        namesOfAllPossibleBuildings.add("Barracks");
        namesOfAllPossibleBuildings.add("Apothecary");
        namesOfAllPossibleBuildings.add("CagedWarDogs");
        namesOfAllPossibleBuildings.add("SmallChurch");
        namesOfAllPossibleBuildings.add("BigChurch");
        namesOfAllPossibleBuildings.add("DrawBridge");
        namesOfAllPossibleBuildings.add("AppleFarm");
        namesOfAllPossibleBuildings.add("DairyProduct");
        namesOfAllPossibleBuildings.add("WheatFarm");
        namesOfAllPossibleBuildings.add("Bakery");
        namesOfAllPossibleBuildings.add("BeerFactory");
        namesOfAllPossibleBuildings.add("House");
        namesOfAllPossibleBuildings.add("Mill");
        namesOfAllPossibleBuildings.add("IronDig");
        namesOfAllPossibleBuildings.add("OxTether");
        namesOfAllPossibleBuildings.add("OilSmelter");
        namesOfAllPossibleBuildings.add("Quarry");
        namesOfAllPossibleBuildings.add("WoodCutter");
        namesOfAllPossibleBuildings.add("Inn");
        namesOfAllPossibleBuildings.add("KillingPit");
        namesOfAllPossibleBuildings.add("Market");
        namesOfAllPossibleBuildings.add("PitchRig");
        namesOfAllPossibleBuildings.add("PitchDitch");
        namesOfAllPossibleBuildings.add("Mercenary");
        namesOfAllPossibleBuildings.add("EngineerGuild");
        namesOfAllPossibleBuildings.add("TunnelerGuild");
        namesOfAllPossibleBuildings.add("Shop");
        namesOfAllPossibleBuildings.add("SiegeTent");
        namesOfAllPossibleBuildings.add("Stable");
        namesOfAllPossibleBuildings.add("ResourcesStockpile");
        namesOfAllPossibleBuildings.add("FoodStockpile");
        namesOfAllPossibleBuildings.add("SmallStoneGatehouse");
        namesOfAllPossibleBuildings.add("BigStoneGatehouse");
        namesOfAllPossibleBuildings.add("LookoutTower");
        namesOfAllPossibleBuildings.add("PerimeterTower");
        namesOfAllPossibleBuildings.add("DefendTower");
        namesOfAllPossibleBuildings.add("SquareTower");
        namesOfAllPossibleBuildings.add("RoundTower");
        namesOfAllPossibleBuildings.add("Tunnel");
        namesOfAllPossibleBuildings.add("BigWall");
        namesOfAllPossibleBuildings.add("SmallWall");
        namesOfAllPossibleBuildings.add("Stair");
        namesOfAllPossibleBuildings.add("Castle");
        namesOfAllPossibleBuildings.add("Armourer");
        namesOfAllPossibleBuildings.add("Blacksmith");
        namesOfAllPossibleBuildings.add("Fletcher");
        namesOfAllPossibleBuildings.add("PoleTurner");
        namesOfAllPossibleBuildings.add("Garden");
        namesOfAllPossibleBuildings.add("TortureChamber");
    }

    private static Empire currentEmpire;
    public static ArrayList<User> allUsers = new ArrayList<>();

    public Manage() throws IOException {
    }

    public static Empire getCurrentEmpire() {
        return currentEmpire;
    }

    public static ArrayList<String> getNamesOfAllPossibleBuildings() {
        return namesOfAllPossibleBuildings;
    }

    public static void setCurrentEmpire(Empire currentEmpire) {
        Manage.currentEmpire = currentEmpire;
    }

    public static ArrayList<Empire> getAllEmpires() {
        return allEmpires;
    }

    public static Empire getEmpireByNickname(String nickname) {
        for (Empire empire : allEmpires) {
            if (empire.getName().equals(nickname)) {
                return empire;
            }
        }
        return null;
    }
    public static Game findGameById(String gameId){
        for (Game game : allGames) {
            if (game.getId().equals(gameId)){
                return game;
            }
        }
        return null;
    }

    public static void connectUserToMasterServer() throws IOException {
        Socket socket = new Socket("localhost", 8568);
        masterServerDataInputStream = new DataInputStream(socket.getInputStream());
        masterServerDataOutputStream = new DataOutputStream(socket.getOutputStream());
        System.out.println("into connect user to the master server!!!!!!!!!!!!!!");
    }
    public static void connectUserToGlobalChat() throws IOException {
        Socket socket = new Socket("localhost", 6000);
        Chat chat = new Chat(socket, "Global","PUBLIC");
        User.getCurrentUser().getChats().add(chat);
    }
}

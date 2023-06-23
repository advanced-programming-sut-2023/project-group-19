package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Manage;
import model.Obstacle.SavedObstacles;
import model.User;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class JsonController {
    public static String content;

    public static void writeIntoFile(Object object, String fileName) throws IOException {
        readDataFile("map.json");
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(SavedObstacles.class, new ObstacleAdapter());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        try (FileWriter file = new FileWriter(fileName)) {
            String jsonAsString = gson.toJson(object);
            StringBuilder sb = new StringBuilder();
            if(content != null)sb.append(content);
            sb.append(jsonAsString);
            file.write(sb.toString());
            file.flush();
        } catch (IOException ignored) {
            System.out.println("couldn't save into file");
        }
    }

    public static void emptyFile() {
        String fileName = "LoggedInUser.json";
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(gson.toJson(null));
            file.flush();
        } catch (IOException ignored) {
            System.out.println("couldn't save into file");
        }
    }

    public static void readDataFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        if (br.readLine() == null) {
            content = null;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine())
            stringBuilder.append(sc.nextLine()).append('\n');
        content = stringBuilder.toString();
//        System.out.println(content);
    }

    public static void saveAllUsersFileData() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Type allUsersType = new TypeToken<ArrayList<User>>() {
        }.getType();
        if (content == null) return;
        User.users = gson.fromJson(content, allUsersType);
        Manage.allUsers = User.users;
    }

    public static User saveLoggedInUserFileData() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        if (content == null) return null;
        return gson.fromJson(content, User.class);
    }
    public static ArrayList<SavedObstacles> getSavedObstacle(){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(SavedObstacles.class, new ObstacleAdapter());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        if (content == null) return null;
        Type type = new TypeToken<ArrayList<SavedObstacles>>(){}.getType();
        ArrayList<SavedObstacles> a2 = gson.fromJson(content,type);
        return a2;
    }
}
//        GsonBuilder builder = new GsonBuilder();
////        Gson builder = new GsonBuilder();
////                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
////                .create();
//        builder.setPrettyPrinting();
//        Gson gson = builder.create();

//obstacle : no
//troop :

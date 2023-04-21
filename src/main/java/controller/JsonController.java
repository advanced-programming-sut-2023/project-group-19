package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Manage;
import model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class JsonController {
    static String content;
    public static void writeIntoFile(Object object , String fileName) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        try(FileWriter file = new FileWriter(fileName)){
            file.write(gson.toJson(object));
            file.flush();
        }
        catch (IOException ignored){
            System.out.println("couldn't save into file");
        }

    }
    public static void readDataFile(String fileName) throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(fileName);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine())
            stringBuilder.append(sc.nextLine()).append('\n');
        content = stringBuilder.toString();

    }
    public static void saveAllUsersFileData(){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Type allUsersType = new TypeToken<ArrayList<User>>(){}.getType();
        Manage.allUsers = gson.fromJson(content , allUsersType);
    }
    public static User saveLoggedInUserFileData(){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        return gson.fromJson(content , User.class);
    }
}

package controller;
import model.*;

import java.util.Scanner;

public class ProfileController {
    static User isUser;
    public static String currentMenu;

    public static void run(Scanner scanner){

    }
    public String getUserNameOfCurrentUser(){
        return isUser.getUsername();
    }
}

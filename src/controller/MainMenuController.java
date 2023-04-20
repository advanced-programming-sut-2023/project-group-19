package controller;
import model.*;

import java.util.Scanner;

public class MainMenuController {
    public static void logout(){
        User.setCurrentUser(null);
    }
}


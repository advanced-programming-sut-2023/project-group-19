import view.LoginMenu;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException , InterruptedException {
        LoginMenu.run(new Scanner(System.in));
    }
}
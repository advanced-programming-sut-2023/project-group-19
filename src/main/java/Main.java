import controller.*;
import model.Building.*;
import model.Empire;
import model.Human.Troop.ArchersAndThrowers;
import model.Human.Troop.Army;
import model.Manage;
import model.Map;
import model.User;
import view.BuildingMenu;
import view.Commands.BuildingCommands;
import view.EmpireMenu;
import view.GameMenu;
import view.LoginMenu;
import model.Human.Troop.*;

import javax.sound.sampled.Line;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import static java.lang.Math.floor;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
//        Scanner scanner = new Scanner(System.in);
//        LoginMenu.run(scanner);
        CreateMapController.CreateMap(200);
        Empire pig = new Empire();
        Manage.allEmpires.add(pig);
        Soldiers swordman = new Soldiers(pig);
        swordman.Knight(3,3);
        pig.empireArmy.add(swordman);
        Map.getTroopMap()[3][3].add(swordman);

        Empire RichardTheLionHeart = new Empire();
        Manage.allEmpires.add(RichardTheLionHeart);

        Soldiers yy = new Soldiers(RichardTheLionHeart);
        yy.Swordsmen(3,3);
        Map.getTroopMap()[3][3].add(yy);
        RichardTheLionHeart.empireArmy.add(yy);

        ArchersAndThrowers archer = new ArchersAndThrowers(RichardTheLionHeart);
        archer.HorseArchers(3,2);
        Map.getTroopMap()[3][2].add(archer);
        RichardTheLionHeart.empireArmy.add(archer);

        AttackArmyToArmyController.battleWithEnemy();

        System.out.println(ShowMapController.showMap(4,5,false));
        System.out.println(yy.getHp());
        System.out.println(swordman.getHp());

//        System.out.println(archer.getAttackPower());

    }
}
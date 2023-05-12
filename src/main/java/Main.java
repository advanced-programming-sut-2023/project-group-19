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
        Empire RichardTheLionHeart = new Empire();
        Manage.allEmpires.add(RichardTheLionHeart);
        Manage.setCurrentEmpire(RichardTheLionHeart);

        GameController gameController2 = new GameController();
        GameController.gameController = gameController2 ;

        Soldiers attacker = new Soldiers(RichardTheLionHeart);
        attacker.Knight(47,20);
        RichardTheLionHeart.empireArmy.add(attacker);
        attacker.isIntFight = true ;
        Map.getTroopMap()[47][20].add(attacker);

        Armoury armoury = new Armoury(pig);
        armoury.armoury();
        Map.getBuildingMap()[51][23].add(armoury);

        AttackArmyToArmyController.setFightMode(gameController2);



//        ArchersAndThrowers fighter2 = new ArchersAndThrowers(pig);
//        fighter2.HorseArchers(53,23);
//        Map.getTroopMap()[53][23].add(fighter2);
//        pig.empireArmy.add(fighter2);

        AttackArmyToArmyController.battleWithEnemy();
        System.out.println(ShowMapController.showMap(52,20,false));
        System.out.println(armoury.getHp());
        System.out.println(attacker.xCoordinate);
        System.out.println(attacker.yCoordinate);

//        attacker.setArmyForm("offensive");
//        GameController gameController2 = new GameController();
//        GameController.gameController = gameController2 ;
//        RichardTheLionHeart.empireArmy.add(attacker);
//        Manage.setCurrentEmpire(RichardTheLionHeart);




//
//        gameController2.setStateArmy();
//        NextTurnController nextTurnController = new NextTurnController();
//        gameController2.moveUnit(54,19);
//        for(int i = 0 ; i < 4 ; i ++) {
//            gameController2.setStateArmy();
//            nextTurnController.resetTroopsMovesLeft();
//            System.out.println(ShowMapController.showMap(53,20,false));
//            AttackArmyToArmyController.battleWithEnemy();
//        }





//3 4


//        System.out.println(ShowMapController.showMap(53,20,false));
//        System.out.println(attacker.getHp());
//        System.out.println(fighter.getHp());
//        System.out.println(fighter2.getHp());
//
//        System.out.println("knight");
//        System.out.println(attacker.xCoordinate);
//        System.out.println(attacker.yCoordinate);
//
//        System.out.println("fighter");
//        System.out.println(fighter.xCoordinate);
//        System.out.println(fighter.yCoordinate);





//        System.out.println(archer.getAttackPower());

    }
//            ArchersAndThrowers archer = new ArchersAndThrowers(RichardTheLionHeart);
//        archer.HorseArchers(3,2);
//        Map.getTroopMap()[3][2].add(archer);
//        RichardTheLionHeart.empireArmy.add(archer);

//            Soldiers yy = new Soldiers(RichardTheLionHeart);
//            yy.Swordsmen(3,3);
//            Map.getTroopMap()[3][3].add(yy);
//            RichardTheLionHeart.empireArmy.add(yy);
}
package view.OldView;

import controller.EmpireController;
import model.Manage;
import view.Commands.EmpireCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class EmpireMenu {
    public int sicknessImpactOnPopularity;

    //TODO : handle these commands here for the legacy part
    public String setFoodRate(int foodRateNumber) {
        if (foodRateNumber < -2 | foodRateNumber > 2)
            return "rate out of bounds";
        else {
            Manage.getCurrentEmpire().setFoodRateNumber(foodRateNumber);
            return "successful";
        }
    }

    public String setTaxRate(int taxRate) {
        if (taxRate < -3 | taxRate > 8) {
            return "rate out of bounds";
        } else {
            Manage.getCurrentEmpire().setTaxRateNumber(taxRate);
            return "successful";
        }
    }

    public String setFearRate(int fearRate) {
        if (fearRate > 5 | fearRate < -5) {
            return "rate out of bounds";
        } else {
            Manage.getCurrentEmpire().setFearRateNumber(fearRate);
            return "successful";
        }
    }


//    public void run(Scanner scanner) {
//        Matcher matcher;
//        System.out.println("Welcome to EmpireMenu!");
//        String command;
//        while (true) {
//            if (Manage.getCurrentEmpire().getApothecary()) {
//                Manage.getCurrentEmpire().setSickness(false);
//                sicknessImpactOnPopularity = 0;
//            }
//            if (Manage.getCurrentEmpire().isSickness())
//                sicknessImpactOnPopularity = -2;
//            command = scanner.nextLine();
//
//        }
//        if (EmpireCommands.getMatcher(command, EmpireCommands.SHOW_FOOD_LIST) != null) {
//            System.out.print(EmpireController.showFoodList());
//        }else if (EmpireCommands.getMatcher(command, EmpireCommands.SHOW_FOOD_RATE) != null) {
//            System.out.println("Your food rate is :" + Manage.getCurrentEmpire().getFoodRateNumber());
//        } else if ((matcher = EmpireCommands.getMatcher(command, EmpireCommands.SET_TAX_RATE_NUMBER)) != null) {
//
//        } else if (EmpireCommands.getMatcher(command, EmpireCommands.SHOW_TAX_RATE) != null) {
//            System.out.println("Your tax rate is :" + Manage.getCurrentEmpire().getTaxRateNumber());
//        } else if ((matcher = EmpireCommands.getMatcher(command, EmpireCommands.SET_FEAR_RATE)) != null) {
//
//        }
//    }

}

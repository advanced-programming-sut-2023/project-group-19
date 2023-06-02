package view.OldView;

import controller.EmpireController;
import model.Manage;
import view.Commands.EmpireCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class EmpireMenu {
    public int sicknessImpactOnPopularity;

    public void run(Scanner scanner) {
        Matcher matcher;
        System.out.println("Welcome to EmpireMenu!");
        while (true) {
            if (Manage.getCurrentEmpire().getApothecary()) {
                Manage.getCurrentEmpire().setSickness(false);
                sicknessImpactOnPopularity = 0;
            }
            if (Manage.getCurrentEmpire().isSickness())
                sicknessImpactOnPopularity = -2;
            String command = scanner.nextLine();
            if (EmpireCommands.getMatcher(command, EmpireCommands.SHOW_FACTORS) != null) {
                System.out.println("Popularity Factors :\n" +
                        "1.Food : " + (Manage.getCurrentEmpire().getPopularityFactorFood() + Manage.getCurrentEmpire().getFoodDiversity()) + '\n' +
                        "2.Tax : " + Manage.getCurrentEmpire().getPopularityFactorTax() + '\n' +
                        "3.Religion : " + Manage.getCurrentEmpire().getPopularityFactorReligious() + '\n' +
                        "4.Fear : " + Manage.getCurrentEmpire().getPopularityFactorFear() + '\n' +
                        "5.Sickness : " + sicknessImpactOnPopularity);
            } else if (EmpireCommands.getMatcher(command, EmpireCommands.SHOW_POPULARITY) != null) {
                System.out.println("Your Popularity is :" + Manage.getCurrentEmpire().getPopularity() + sicknessImpactOnPopularity);
            } else if (EmpireCommands.getMatcher(command, EmpireCommands.SHOW_FOOD_LIST) != null) {
                System.out.print(EmpireController.showFoodList());
            } else if ((matcher = EmpireCommands.getMatcher(command, EmpireCommands.SET_FOOD_RATE_NUMBER)) != null) {
                int foodRateNumber = Integer.parseInt(matcher.group("rateNumber"));
                if (foodRateNumber < -2 | foodRateNumber > 2)
                    System.out.println("rate out of bounds");
                else {
                    Manage.getCurrentEmpire().setFoodRateNumber(foodRateNumber);
                    System.out.println("food rate changed successful");
                }
            } else if (EmpireCommands.getMatcher(command, EmpireCommands.SHOW_FOOD_RATE) != null) {
                System.out.println("Your food rate is :" + Manage.getCurrentEmpire().getFoodRateNumber());
            } else if ((matcher = EmpireCommands.getMatcher(command, EmpireCommands.SET_TAX_RATE_NUMBER)) != null) {
                int taxRate = Integer.parseInt(matcher.group("rateNumber"));
                if (taxRate < -3 | taxRate > 8) {
                    System.out.println("rate out of bounds");
                } else {
                    Manage.getCurrentEmpire().setTaxRateNumber(taxRate);
                    System.out.println("tax rate changed successfully");
                }
            } else if (EmpireCommands.getMatcher(command, EmpireCommands.SHOW_TAX_RATE) != null) {
                System.out.println("Your tax rate is :" + Manage.getCurrentEmpire().getTaxRateNumber());
            } else if ((matcher = EmpireCommands.getMatcher(command, EmpireCommands.SET_FEAR_RATE)) != null) {
                int fearRate = Integer.parseInt(matcher.group("rateNumber"));
                if (fearRate > 5 | fearRate < -5) {
                    System.out.println("rate out of bounds");
                } else {
                    Manage.getCurrentEmpire().setFearRateNumber(fearRate);
                    System.out.println("fear rate changed successful");
                }
            } else if (EmpireCommands.getMatcher(command, EmpireCommands.LOGOUT) != null) {
                System.out.println("welcome to the game menu");
                break;
            } else System.out.println("Invalid Command!");
        }
    }
}

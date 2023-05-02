package view;

import controller.EmpireController;
import model.Empire;
import model.Manage;
import view.Commands.EmpireCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class EmpireMenu {
    //TODO : CALL FOODDIVERSITY AND RELIGION FUNCTION EVERY TURN IN GAME MENU
    public void run(Scanner scanner){
        Matcher matcher;
        EmpireController empireController = new EmpireController();
        while (true){
            String command = scanner.nextLine();
            if (EmpireCommands.getMatcher(command , EmpireCommands.SHOW_FACTORS) != null){
                System.out.println(empireController.showPopularityFactors());
            } else if (EmpireCommands.getMatcher(command , EmpireCommands.SHOW_POPULARITY) != null) {
                System.out.println("Your Popularity is :" + Manage.getCurrentEmpire().getPopularity());
            } else if (EmpireCommands.getMatcher(command , EmpireCommands.SHOW_FOOD_LIST) != null) {
                System.out.println(empireController.showFoodList());
            } else if (EmpireCommands.getMatcher(command , EmpireCommands.SET_FOOD_RATE_NUMBER) != null) {
                Manage.getCurrentEmpire().findFoodDiversity();
            } else if (EmpireCommands.getMatcher(command , EmpireCommands.SHOW_FOOD_RATE) != null) {
                System.out.println("Your food rate is :"+Manage.getCurrentEmpire().getFearRateNumber());
            } else if ((matcher = EmpireCommands.getMatcher(command , EmpireCommands.SET_TAX_RATE_NUMBER)) != null) {
                int taxRate = Integer.parseInt(matcher.group("rateNumber"));
                Manage.getCurrentEmpire().taxImpactOnEmpire(Manage.getCurrentEmpire(),taxRate);
            } else if (EmpireCommands.getMatcher(command , EmpireCommands.SHOW_TAX_RATE) != null) {
                System.out.println("Your tax rate is :"+Manage.getCurrentEmpire().getTaxRateNumber());
            } else if (EmpireCommands.getMatcher(command , EmpireCommands.SET_FEAR_RATE) != null) {
                //TODO : FEAR FUNCTION
            } else if (EmpireCommands.getMatcher(command , EmpireCommands.LOGOUT) != null) {
                break;
            }else System.out.println("Invalid Command!");
        }
    }
}

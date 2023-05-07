package view;

import model.Manage;
import view.Commands.EmpireCommands;
import view.Messages.EmpireMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class EmpireMenu {
    //TODO : CALL FOOD DIVERSITY AND RELIGION FUNCTION EVERY TURN IN GAME MENU
    public void run(Scanner scanner){
        Matcher matcher;
        System.out.println("Welcome to EmpireMenu!");
        while (true){
            String command = scanner.nextLine();
            if (EmpireCommands.getMatcher(command , EmpireCommands.SHOW_FACTORS) != null){
                System.out.println(EmpireMessages.POPULARITY_FACTOR.getMessages());
            } else if (EmpireCommands.getMatcher(command , EmpireCommands.SHOW_POPULARITY) != null) {
                System.out.println("Your Popularity is :" + Manage.getCurrentEmpire().getPopularity());
            } else if (EmpireCommands.getMatcher(command , EmpireCommands.SHOW_FOOD_LIST) != null) {
                String foodList = Manage.getCurrentEmpire().showFoodList();
                System.out.println(foodList);
            } else if (EmpireCommands.getMatcher(command , EmpireCommands.SET_FOOD_RATE_NUMBER) != null) {
                Manage.getCurrentEmpire().findFoodDiversity();
            } else if (EmpireCommands.getMatcher(command , EmpireCommands.SHOW_FOOD_RATE) != null) {
                System.out.println("Your food rate is :"+Manage.getCurrentEmpire().getFearRateNumber());
            } else if ((matcher = EmpireCommands.getMatcher(command , EmpireCommands.SET_TAX_RATE_NUMBER)) != null) {
                int taxRate = Integer.parseInt(matcher.group("rateNumber"));
                Manage.getCurrentEmpire().setTaxRateNumber(taxRate);
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

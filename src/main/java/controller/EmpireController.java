package controller;

import controller.Building.FunctionBuildingController;
import model.Empire;
import model.Manage;

import java.util.Map;

import static java.lang.Math.floor;

public class EmpireController {
    public static Empire empire = Manage.getCurrentEmpire();

    public static void functionBuildings() {
        FunctionBuildingController.transformWheatToFlour();
        FunctionBuildingController.transformFlourToBread();
        FunctionBuildingController.transformOatToBeer();
        FunctionBuildingController.increasePopularityWithBeer();
        FunctionBuildingController.transformIronToMetalArmour();
        FunctionBuildingController.transformIronToSwordOrMace();
        FunctionBuildingController.transformWoodToBow();
        FunctionBuildingController.transformWoodToSpearOrPeak();
    }

    public static void taxImpactOnEmpire(Empire empire, int taxRate) {
        switch (taxRate) {
            case -3:
                empire.setGoldCount(empire.getGoldCount() - empire.getPopulation());
                empire.setPopularityFactorTax(7);
                break;
            case -2:
                empire.setGoldCount(empire.getGoldCount() - (int) floor(empire.getPopulation() * 0.8));
                empire.setPopularityFactorTax(5);
                break;
            case -1:
                empire.setGoldCount(empire.getGoldCount() - (int) floor(empire.getPopulation() * 0.6));
                empire.setPopularityFactorTax(3);
                break;
            case 0:
                empire.setPopularityFactorTax(1);
                break;
            case 1:
                empire.setGoldCount(empire.getGoldCount() + (int) floor(empire.getPopulation() * 0.6));
                empire.setPopularityFactorTax(-2);
                break;
            case 2:
                empire.setGoldCount(empire.getGoldCount() + (int) floor(empire.getPopulation() * 0.8));
                empire.setPopularityFactorTax(-4);
                break;
            case 3:
                empire.setGoldCount(empire.getGoldCount() + empire.getPopulation());
                empire.setPopularityFactorTax(-6);
                break;
            case 4:
                empire.setGoldCount(empire.getGoldCount() + (int) floor(empire.getPopulation() * 1.2));
                empire.setPopularityFactorTax(-8);
                break;
            case 5:
                empire.setGoldCount(empire.getGoldCount() + (int) floor(empire.getPopulation() * 1.4));
                empire.setPopularityFactorTax(-12);
                break;
            case 6:
                empire.setGoldCount(empire.getGoldCount() + (int) floor(empire.getPopulation() * 1.6));
                empire.setPopularityFactorTax(-16);
                break;
            case 7:
                empire.setGoldCount(empire.getGoldCount() + (int) floor(empire.getPopulation() * 1.8));
                empire.setPopularityFactorTax(-20);
                break;
            case 8:
                empire.setGoldCount(empire.getGoldCount() + empire.getPopulation() * 2);
                empire.setPopularityFactorTax(-24);
                break;
        }
    }

    public static String showFoodList() {
        return "apple : " + empire.allFood.get("apple") + '\n' +
                "bread : " + empire.allFood.get("bread") + '\n' +
                "cheese : " + empire.allFood.get("cheese") + '\n' +
                "meat : " + empire.allFood.get("meat") + '\n';
    }

    public static void setFearFactor() {
        empire.setFearWorkerImpact(1 - empire.getFearRateNumber() * 0.1);
        empire.setFearTroopImpact(1 + empire.getFearRateNumber() * 0.1);
    }

    public static void findFoodDiversity() {
        int foodDiversity = 0;
        if (empire.allFood.get("apple") > 0) foodDiversity++;
        if (empire.allFood.get("bread") > 0) foodDiversity++;
        if (empire.allFood.get("cheese") > 0) foodDiversity++;
        if (empire.allFood.get("meat") > 0) foodDiversity++;
        System.out.println("food diversity : " + foodDiversity);
        empire.setFoodDiversity(foodDiversity--);
//        switch (foodDiversity) {
//            case 2:
//                empire.setFoodDiversity(1);
//            case 3:
//                empire.setFoodDiversity(2);
//            case 4:
//                empire.setFoodDiversity(3);
//        }
    }

    public static void givingPeopleFood(Empire empire) {
        double foodPerPearson = 0;
        int foodRate = empire.getFoodRateNumber();
        switch (foodRate) {
            case -2:
                empire.setFoodPopularityRate(-8);
                foodPerPearson = 0;
                break;
            case -1:
                empire.setFoodPopularityRate(-4);
                foodPerPearson = 0.5;
                break;
            case 0:
                foodPerPearson = 1;
                break;
            case 1:
                empire.setFoodPopularityRate(4);
                foodPerPearson = 1.5;
                break;
            case 2:
                empire.setFoodPopularityRate(8);
                foodPerPearson = 2;
                break;
        }
        int totalFoodThatBeGivenToPeople = (int) foodPerPearson * empire.getPopulation();
        System.out.println(empire.getTotalFoodCount());
        System.out.println(totalFoodThatBeGivenToPeople);
        if ((int)(foodPerPearson * empire.getPopulation()) <= empire.getTotalFoodCount()) {
            while (totalFoodThatBeGivenToPeople != 0) {
                if (empire.allFood.get("apple") != 0) {
                    empire.allFood.replace("apple", empire.allFood.get("apple") - 1);
                    totalFoodThatBeGivenToPeople--;
                }
                if (empire.allFood.get("cheese") != 0) {
                    empire.allFood.replace("cheese", empire.allFood.get("cheese") - 1);
                    totalFoodThatBeGivenToPeople--;
                }
                if (empire.allFood.get("bread") != 0) {
                    empire.allFood.replace("bread", empire.allFood.get("bread") - 1);
                    totalFoodThatBeGivenToPeople--;
                }
                if (empire.allFood.get("meat") != 0) {
                    empire.allFood.replace("meat", empire.allFood.get("meat") - 1);
                    totalFoodThatBeGivenToPeople--;
                }
            }

        } else {
            empire.setFoodRateNumber(-2);
        }
    }

    public static int calculateTotalFoodCount() {
        int total = 0;
        for (Map.Entry<String, Integer> food : Manage.getCurrentEmpire().getAllFood().entrySet()) {
            total += food.getValue();
        }
        return total;
    }

    public static int calculateTotalFightStuffCount() {
        int total = 0;
        for (Map.Entry<String, Integer> weapon : Manage.getCurrentEmpire().getAllWeaponTools().entrySet()) {
            total += weapon.getValue();
        }
        return total;
    }

    public static int calculateTotalResourcesCount() {
        int total = 0;
        for (Map.Entry<String, Integer> resource : Manage.getCurrentEmpire().stores.entrySet()) {
            total += resource.getValue();
        }
        return total;
    }

}
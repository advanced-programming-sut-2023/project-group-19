package controller;

import controller.Building.FunctionBuildingController;
import model.Empire;
import model.Manage;

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
                empire.setPopularity(empire.getPopularity() + 7);
                empire.setPopularityFactorTax(empire.getPopularityFactorFood() + 7);
            case -2:
                empire.setGoldCount(empire.getGoldCount() - (int) floor(empire.getPopulation() * 0.8));
                empire.setPopularity(empire.getPopularity() + 5);
                empire.setPopularityFactorTax(empire.getPopularityFactorFood() + 5);
            case -1:
                empire.setGoldCount(empire.getGoldCount() - (int) floor(empire.getPopulation() * 0.6));
                empire.setPopularity(empire.getPopularity() + 3);
                empire.setPopularityFactorTax(empire.getPopularityFactorFood() + 3);
            case 0:
                empire.setPopularity(empire.getPopularity() + 1);
                empire.setPopularityFactorTax(empire.getPopularityFactorFood() + 1);
            case 1:
                empire.setGoldCount(empire.getGoldCount() + (int) floor(empire.getPopulation() * 0.6));
                empire.setPopularity(empire.getPopularity() - 2);
                empire.setPopularityFactorTax(empire.getPopularityFactorFood() - 2);
            case 2:
                empire.setGoldCount(empire.getGoldCount() + (int) floor(empire.getPopulation() * 0.8));
                empire.setPopularity(empire.getPopularity() - 4);
                empire.setPopularityFactorTax(empire.getPopularityFactorFood() - 4);
            case 3:
                empire.setGoldCount(empire.getGoldCount() + empire.getPopulation());
                empire.setPopularity(empire.getPopularity() - 6);
                empire.setPopularityFactorTax(empire.getPopularityFactorFood() - 6);
            case 4:
                empire.setGoldCount(empire.getGoldCount() + (int) floor(empire.getPopulation() * 1.2));
                empire.setPopularity(empire.getPopularity() - 8);
                empire.setPopularityFactorTax(empire.getPopularityFactorFood() - 8);
            case 5:
                empire.setGoldCount(empire.getGoldCount() + (int) floor(empire.getPopulation() * 1.4));
                empire.setPopularity(empire.getPopularity() - 12);
                empire.setPopularityFactorTax(empire.getPopularityFactorFood() - 12);
            case 6:
                empire.setGoldCount(empire.getGoldCount() + (int) floor(empire.getPopulation() * 1.6));
                empire.setPopularity(empire.getPopularity() - 16);
                empire.setPopularityFactorTax(empire.getPopularityFactorFood() - 16);
            case 7:
                empire.setGoldCount(empire.getGoldCount() + (int) floor(empire.getPopulation() * 1.8));
                empire.setPopularity(empire.getPopularity() - 20);
                empire.setPopularityFactorTax(empire.getPopularityFactorFood() - 20);
            case 8:
                empire.setGoldCount(empire.getGoldCount() + empire.getPopulation() * 2);
                empire.setPopularity(empire.getPopularity() - 24);
                empire.setPopularityFactorTax(empire.getPopularityFactorFood() - 24);
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
        empire.setFoodDiversity(foodDiversity);
        switch (foodDiversity) {
            case 2:
                empire.setPopularity(empire.getPopularity() + 1);
            case 3:
                empire.setPopularity(empire.getPopularity() + 2);
            case 4:
                empire.setPopularity(empire.getPopularity() + 3);
        }
    }

    public static void givingPeopleFood(Empire empire) {
        double foodPerPearson = 0;
        int foodRate = empire.getFoodRateNumber();
        switch (foodRate) {
            case -2:
                empire.setPopularity(empire.getPopularity() - 8);
                foodPerPearson = 0;
            case -1:
                empire.setPopularity(empire.getPopularity() - 4);
                foodPerPearson = 0.5;
            case 0:
                foodPerPearson = 1;
            case 1:
                empire.setPopularity(empire.getPopularity() + 4);
                foodPerPearson = 1.5;
            case 2:
                empire.setPopularity(empire.getPopularity() + 8);
                foodPerPearson = 2;
        }
        int totalFoodThatBeGivenToPeople = (int) foodPerPearson * empire.getPopulation();
        if (foodPerPearson * empire.getPopulation() >= empire.getTotalFoodCount()) {
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
}
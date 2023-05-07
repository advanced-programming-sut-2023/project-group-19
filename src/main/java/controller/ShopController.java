package controller;

import model.*;
import model.Building.Shop;
import view.Commands.ShopMenuCommands;
import view.Messages.ShopMenuMessages;
import view.ShopMenu;

import javax.swing.*;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopController {
    public static String currentMenu;

    public void showPricelist() {
        int number = 1;
        System.out.println("Attention:");
        System.out.println("Buying prices are settled for 5 numbers of every good.");
        System.out.println("Sell prices are settled for every single good.");
        for (Map.Entry<String, Integer> goodsForBuying : ShopMenu.currentShop.getListOfGoodsBuyPrice().entrySet()) {
            String nameOfGoodsForBuying = goodsForBuying.getKey();
            for (Map.Entry<String, Integer> goodsForSelling : ShopMenu.currentShop.getListOfGoodsSellPrice().entrySet()) {
                String nameOfGoodsForSelling = goodsForSelling.getKey();
                if (nameOfGoodsForBuying.equals(nameOfGoodsForSelling)) {
                    int resourceCount = getResourceCount(nameOfGoodsForBuying).getValue();
                    System.out.println(number + ". ");
                    System.out.println("Name: " + nameOfGoodsForBuying);
                    System.out.println("Buying Price: " + goodsForBuying.getValue());
                    System.out.println("Sell Price: " + goodsForSelling.getValue());
                    System.out.println("Resource count: " + resourceCount);
                    number++;
                    break;
                }
            }
        }
    }

    //TODO : Item amount would be a scale
    public ShopMenuMessages buyItem(Matcher itemName, Matcher itemAmount, Scanner scanner) {
        String nameOfGood = itemName.group("itemName");
        Map.Entry<String, Integer> chosenGood = findGoodToBuy(nameOfGood);
        int amount = Integer.parseInt(itemAmount.group("itemAmount"));
        if (chosenGood != null) {
            if (amount <= ShopMenu.currentShop.getCapacity() && amount % 5 == 0) {
                if (checkStorageOfUser(nameOfGood, amount)) {
                    validationFormForBuying(nameOfGood, chosenGood.getValue(), amount);
                    String answer = scanner.nextLine();
                    if (ShopMenuCommands.getMatcher(answer, ShopMenuCommands.OPERATION_ACCEPTED) != null) {
                        Manage.getCurrentEmpire().setGoldCount(Manage.getCurrentEmpire().getGoldCount() - chosenGood.getValue());
                        ShopMenu.currentShop.setGoldCount(ShopMenu.currentShop.getGoldCount() + chosenGood.getValue());
                        setResourceCount(nameOfGood, amount, "Buy");
                        return ShopMenuMessages.BUYING_OPERATION_SUCCEEDED;
                    } else if (ShopMenuCommands.getMatcher(answer, ShopMenuCommands.OPERATION_CANCELLED) != null) {
                        return ShopMenuMessages.OPERATION_CANCELLED;
                    } else return ShopMenuMessages.INVALID_ANSWER;
                } else return ShopMenuMessages.NOT_ENOUGH_CAPACITY_FOR_EMPIRE;
            } else return ShopMenuMessages.INVALID_AMOUNT_TO_BUY;
        }
        return ShopMenuMessages.INVALID_NAME_OF_ITEM;
    }

    public ShopMenuMessages sellItem(Matcher itemName, Matcher itemAmount, Scanner scanner) {
        String nameOfGood = itemName.group("itemName");
        Map.Entry<String, Integer> chosenGood = findGoodToSell(nameOfGood);
        int amount = Integer.parseInt(itemAmount.group("itemAmount"));
        if (chosenGood != null) {
            if (amount <= getResourceCount(nameOfGood).getValue()) {
                if (checkStorageOfShop(nameOfGood, amount)) {
                    validationFormForSelling(nameOfGood, chosenGood.getValue(), amount);
                    String answer = scanner.nextLine();
                    if (ShopMenuCommands.getMatcher(answer, ShopMenuCommands.OPERATION_ACCEPTED) != null) {
                        Manage.getCurrentEmpire().setGoldCount(Manage.getCurrentEmpire().getGoldCount() + chosenGood.getValue());
                        ShopMenu.currentShop.setGoldCount(ShopMenu.currentShop.getGoldCount() - chosenGood.getValue());
                        setResourceCount(nameOfGood, amount, "Sell");
                        return ShopMenuMessages.SELLING_OPERATION_SUCCEEDED;
                    } else if (ShopMenuCommands.getMatcher(answer, ShopMenuCommands.OPERATION_CANCELLED) != null) {
                        return ShopMenuMessages.OPERATION_CANCELLED;
                    } else return ShopMenuMessages.INVALID_ANSWER;
                } else return ShopMenuMessages.NOT_ENOUGH_CAPACITY_FOR_SHOP;
            } else return ShopMenuMessages.NOT_ENOUGH_AMOUNT_TO_SELL;
        }
        return ShopMenuMessages.INVALID_NAME_OF_ITEM;
    }

    public Map.Entry<String, Integer> getResourceCount(String resource) {
        for (Map.Entry<String, Integer> food : Manage.getCurrentEmpire().getAllFood().entrySet()) {
            if (food.getKey().equals(resource)) {
                return food;
            }
        }
        for (Map.Entry<String, Integer> weaponTool : Manage.getCurrentEmpire().getAllWeaponTools().entrySet()) {
            if (weaponTool.getKey().equals(resource)) {
                return weaponTool;
            }
        }
        for (Map.Entry<String, Integer> store : Manage.getCurrentEmpire().getStores().entrySet()) {
            if (store.getKey().equals(resource)) {
                return store;
            }
        }
        for (Map.Entry<String, Integer> europeTroop : Manage.getCurrentEmpire().getEuropeTroopCount().entrySet()) {
            if (europeTroop.getKey().equals(resource)) {
                return europeTroop;
            }
        }
        for (Map.Entry<String, Integer> arabTroop : Manage.getCurrentEmpire().getArabTroopCount().entrySet()) {
            if (arabTroop.getKey().equals(resource)) {
                return arabTroop;
            }
        }
        for (Map.Entry<String, Integer> engineerGuild : Manage.getCurrentEmpire().getEngineerGuildTroopCount().entrySet()) {
            if (engineerGuild.getKey().equals(resource)) {
                return engineerGuild;
            }
        }
        for (Map.Entry<String, Integer> siegeTentTroop : Manage.getCurrentEmpire().getSiegeTentTroopsCount().entrySet()) {
            if (siegeTentTroop.getKey().equals(resource)) {
                return siegeTentTroop;
            }
        }
        return null;
    }

    public void setResourceCount(String resource, int count, String typeOfAction) {
        Empire empire = Manage.getCurrentEmpire();
        if (typeOfAction.equals("Sell")) count = -1 * count;
        for (Map.Entry<String, Integer> food : empire.getAllFood().entrySet()) {
            if (food.getKey().equals(resource)) {
                empire.getAllFood().replace(resource, food.getValue() + count);
            }
        }
        for (Map.Entry<String, Integer> weaponTool : empire.getAllWeaponTools().entrySet()) {
            if (weaponTool.getKey().equals(resource)) {
                empire.getAllWeaponTools().replace(resource, weaponTool.getValue() + count);
            }
        }
        for (Map.Entry<String, Integer> store : empire.getStores().entrySet()) {
            if (store.getKey().equals(resource)) {
                empire.getStores().replace(resource, store.getValue() + count);
            }
        }
        for (Map.Entry<String, Integer> europeTroop : empire.getEuropeTroopCount().entrySet()) {
            if (europeTroop.getKey().equals(resource)) {
                empire.getEuropeTroopCount().replace(resource, europeTroop.getValue() + count);
            }
        }
        for (Map.Entry<String, Integer> arabTroop : empire.getArabTroopCount().entrySet()) {
            if (arabTroop.getKey().equals(resource)) {
                empire.getArabTroopCount().replace(resource, arabTroop.getValue() + count);
            }
        }
        for (Map.Entry<String, Integer> engineerGuild : empire.getEngineerGuildTroopCount().entrySet()) {
            if (engineerGuild.getKey().equals(resource)) {
                empire.getEngineerGuildTroopCount().replace(resource, engineerGuild.getValue() + count);
            }
        }
        for (Map.Entry<String, Integer> siegeTentTroop : empire.getSiegeTentTroopsCount().entrySet()) {
            if (siegeTentTroop.getKey().equals(resource)) {
                empire.getSiegeTentTroopsCount().replace(resource, siegeTentTroop.getValue() + count);
            }
        }
    }

    public Map.Entry<String, Integer> findGoodToBuy(String enteredName) {
        for (Map.Entry<String, Integer> good : ShopMenu.currentShop.getListOfGoodsBuyPrice().entrySet()) {
            if (good.getKey().equals(enteredName)) {
                return good;
            }
        }
        return null;
    }

    public Map.Entry<String, Integer> findGoodToSell(String enteredName) {
        for (Map.Entry<String, Integer> good : ShopMenu.currentShop.getListOfGoodsSellPrice().entrySet()) {
            if (good.getKey().equals(enteredName)) {
                return good;
            }
        }
        return null;
    }

    public boolean checkStorageOfUser(String resourceName, int amount) {
        return getResourceCount(resourceName).getValue() + amount <= Manage.getCurrentEmpire().getResourcesCapacity();
    }

    public void validationFormForBuying(String goodName, int price, int amount) {
        System.out.println("Dear customer from " + Manage.getCurrentEmpire() + " Empire\n" +
                "You've asked for product : " + goodName + " in " + amount + " quantity , with price : " + price + "\n" +
                "We need your approval before preparing your order.If you're certain about your order,please enter yes.\n" +
                "Otherwise,you're order will be cancelled.");
    }

    public void validationFormForSelling(String goodName, int price, int amount) {
        System.out.println("Dear seller from " + Manage.getCurrentEmpire() + " Empire\n" +
                "You've wanted to sell product : " + goodName + " in " + amount + " quantity , with price : " + price + "\n" +
                "We need your approval before buying your product.If you're certain about your offer,please enter yes.\n" +
                "Otherwise,the deal will be cancelled.");
    }

    public boolean checkStorageOfShop(String resourceName, int amount) {
        return findGoodToBuy(resourceName).getValue() + amount <= Manage.getCurrentEmpire().getResourcesCapacity();
    }
}

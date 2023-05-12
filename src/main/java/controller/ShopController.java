package controller;

import model.*;
import view.Commands.ShopMenuCommands;
import view.Messages.ShopMenuMessages;
import view.ShopMenu;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopController {
    public static Empire ownerOfShop = ShopMenu.currentShop.getOwner();

    public void showPriceList() {
        int number = 1;
        System.out.println("Attention:");
        System.out.println("Buying prices are settled for 5 numbers of every good.");
        System.out.println("Sell prices are settled for every single good.");
        for (Map.Entry<String, Integer> goodsForBuying : ShopMenu.currentShop.getListOfGoodsBuyPrice().entrySet()) {
            String nameOfGoodsForBuying = goodsForBuying.getKey();
            for (Map.Entry<String, Integer> goodsForSelling : ShopMenu.currentShop.getListOfGoodsSellPrice().entrySet()) {
                String nameOfGoodsForSelling = goodsForSelling.getKey();
                if (nameOfGoodsForBuying.equals(nameOfGoodsForSelling)) {
                    int resourceCount = getNumberOfGoods(nameOfGoodsForBuying, ownerOfShop);
                    System.out.print(number + ". ");
                    System.out.println("Name: " + nameOfGoodsForBuying);
                    System.out.println("\tBuying Price: " + goodsForBuying.getValue());
                    System.out.println("\tSell Price: " + goodsForSelling.getValue());
                    System.out.println("\tResource count: " + resourceCount);
                    number++;
                    break;
                }
            }
        }
    }

    public ShopMenuMessages buyItem(Matcher itemName, Matcher itemAmount, Scanner scanner) {
        String nameOfGood = itemName.group("itemName");
        Map.Entry<String, Integer> chosenGood = findGoodToBuy(nameOfGood);
        int amount = Integer.parseInt(itemAmount.group("itemAmount"));
        if (chosenGood != null) {
            if (checkTheCapacity(amount, chosenGood.getKey(), Manage.getCurrentEmpire())) {
                if (enoughMoneyToBuy(Manage.getCurrentEmpire(), chosenGood.getValue())) {
                    validationFormForBuying(nameOfGood, chosenGood.getValue(), amount);
                    String answer = scanner.nextLine();
                    if (ShopMenuCommands.getMatcher(answer, ShopMenuCommands.OPERATION_ACCEPTED) != null) {
                        Manage.getCurrentEmpire().setGoldCount(Manage.getCurrentEmpire().getGoldCount() - chosenGood.getValue());
                        ownerOfShop.setGoldCount(ownerOfShop.getGoldCount() + chosenGood.getValue());
                        setNumberOfGoods(Manage.getCurrentEmpire(), ownerOfShop, amount * 5, chosenGood.getKey());
                        return ShopMenuMessages.BUYING_OPERATION_SUCCEEDED;
                    } else return ShopMenuMessages.OPERATION_CANCELLED;
                } else return ShopMenuMessages.NOT_ENOUGH_MONEY_TO_BUY;
            } else return ShopMenuMessages.NOT_ENOUGH_CAPACITY_FOR_EMPIRE;
        }
        return ShopMenuMessages.INVALID_NAME_OF_ITEM;
    }

    public ShopMenuMessages sellItem(Matcher itemName, Matcher itemAmount, Scanner scanner) {
        String nameOfGood = itemName.group("itemName");
        Map.Entry<String, Integer> chosenGood = findGoodToSell(nameOfGood);
        int amount = Integer.parseInt(itemAmount.group("itemAmount"));
        if (chosenGood != null) {
            if (getNumberOfGoods(chosenGood.getKey(), Manage.getCurrentEmpire()) >= amount) {
                if (checkTheCapacity(amount, chosenGood.getKey(), ownerOfShop)) {
                    if (enoughMoneyToBuy(ownerOfShop, chosenGood.getValue())) {
                        validationFormForSelling(nameOfGood, chosenGood.getValue(), amount);
                        String answer = scanner.nextLine();
                        if (ShopMenuCommands.getMatcher(answer, ShopMenuCommands.OPERATION_ACCEPTED) != null) {
                            Manage.getCurrentEmpire().setGoldCount(Manage.getCurrentEmpire().getGoldCount() + chosenGood.getValue() * amount);
                            ShopMenu.currentShop.setGoldCount(ShopMenu.currentShop.getGoldCount() - chosenGood.getValue() * amount);
                            setNumberOfGoods(ownerOfShop, Manage.getCurrentEmpire(), amount, chosenGood.getKey());
                            return ShopMenuMessages.SELLING_OPERATION_SUCCEEDED;
                        } else return ShopMenuMessages.OPERATION_CANCELLED;
                    } else return ShopMenuMessages.NOT_ENOUGH_MONEY_TO_BUY;
                } else return ShopMenuMessages.NOT_ENOUGH_CAPACITY_FOR_SHOP;
            } else return ShopMenuMessages.NOT_ENOUGH_AMOUNT_TO_SELL;
        }
        return ShopMenuMessages.INVALID_NAME_OF_ITEM;
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

    public void validationFormForBuying(String goodName, int price, int amount) {
        System.out.println("Dear customer from " + Manage.getCurrentEmpire().getName() + " Empire\n" +
                "You've asked for product : " + goodName + " in " + amount * 5 + " quantity , with price : " + price + "\n" +
                "We need your approval before preparing your order.If you're certain about your order,please enter yes.\n" +
                "Otherwise,you're order will be cancelled.");
    }

    public void validationFormForSelling(String goodName, int price, int amount) {
        System.out.println("Dear seller from " + Manage.getCurrentEmpire().getName() + " Empire\n" +
                "You've wanted to sell product : " + goodName + " in " + amount + " quantity , with price : " + price * amount + "\n" +
                "We need your approval before buying your product.If you're certain about your offer,please enter yes.\n" +
                "Otherwise,the deal will be cancelled.");
    }

    public int getNumberOfGoods(String goodName, Empire empire) {
        return switch (goodName) {
            case "meat" -> empire.getMeatCount();
            case "hops" -> empire.getOatCount();
            case "ironArmor" -> empire.getMetalArmour();
            case "leatherArmor" -> empire.getLeatherArmour();
            case "sword" -> empire.getSwordCount();
            case "mace" -> empire.getMaceCount();
            case "bow" -> empire.getBowCount();
            case "oil" -> empire.getOilAmount();
            case "iron" -> empire.getIronCount();
            case "stone" -> empire.getStoneCount();
            case "wood" -> empire.getWoodCount();
            case "flour" -> empire.getFlour();
            case "wheat" -> empire.getWheatCount();
            case "apple" -> empire.getAppleCount();
            case "cheese" -> empire.getCheeseCount();
            default -> 0;
        };
    }

    public void setNumberOfGoods(Empire customer, Empire seller, int count, String goodName) {
        switch (goodName) {
            case "meat" -> {
                customer.setMeatCount(customer.getMeatCount() + count);
            }
            case "hops" -> {
                customer.setOatCount(customer.getOatCount() + count);
            }
            case "ironArmour" -> {
                customer.setMetalArmour(customer.getMetalArmour() + count);
            }
            case "leatherArmour" -> {
                customer.setLeatherArmour(customer.getLeatherArmour() + count);
            }
            case "sword" -> {
                customer.setSwordCount(customer.getSwordCount() + count);
            }
            case "bow" -> {
                customer.setBowCount(customer.getBowCount() + count);
            }
            case "mace" -> {
                customer.setMaceCount(customer.getMaceCount() + count);
            }
            case "oil" -> {
                customer.setOilAmount(customer.getOilAmount() + count);
            }
            case "iron" -> {
                customer.setIronCount(customer.getIronCount() + count);
            }
            case "stone" -> {
                customer.setStoneCount(customer.getStoneCount() + count);
            }
            case "wood" -> {
                customer.setWoodCount(customer.getWoodCount() + count);
            }
            case "flour" -> {
                customer.setFlour(customer.getFlour() + count);
            }
            case "wheat" -> {
                customer.setWheatCount(customer.getWheatCount() + count);
            }
            case "apple" -> {
                customer.setAppleCount(customer.getAppleCount() + count);
            }
            case "cheese" -> {
                customer.setCheeseCount(customer.getCheeseCount() + count);
            }
        }
    }

    public boolean checkTheCapacity(int count, String goodName, Empire empire) {
        return switch (goodName) {
            case "meat", "apple", "cheese" ->
                    count + EmpireController.calculateTotalFoodCount() <= empire.getFoodCapacity();
            case "hops", "oil", "flour", "wheat", "iron", "wood", "stone" ->
                    count + EmpireController.calculateTotalResourcesCount() <= empire.getResourcesCapacity();
            case "ironArmor", "bow", "sword", "mace", "leatherArmor" ->
                    count + EmpireController.calculateTotalFightStuffCount() <= empire.getWeaponsCapacity();
            default -> false;
        };
    }

    public boolean enoughMoneyToBuy(Empire empire, int price) {
        return empire.getGoldCount() >= price;
    }
}

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
                    int resourceCount = getNumberOfGoods(nameOfGoodsForBuying , ownerOfShop);
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
            if (checkTheCapacity(amount,chosenGood.getKey(),ownerOfShop)) {
                if (checkTheCapacity(amount, chosenGood.getKey(), Manage.getCurrentEmpire())) {
                    validationFormForBuying(nameOfGood, chosenGood.getValue(), amount);
                    String answer = scanner.nextLine();
                    if (ShopMenuCommands.getMatcher(answer, ShopMenuCommands.OPERATION_ACCEPTED) != null) {
                        Manage.getCurrentEmpire().setGoldCount(Manage.getCurrentEmpire().getGoldCount() - chosenGood.getValue());
                        ownerOfShop.setGoldCount(ownerOfShop.getGoldCount() + chosenGood.getValue());
                        setNumberOfGoods(Manage.getCurrentEmpire(),ownerOfShop,amount,chosenGood.getKey());
                        return ShopMenuMessages.BUYING_OPERATION_SUCCEEDED;
                    } else return ShopMenuMessages.OPERATION_CANCELLED;
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
            if (checkTheCapacity(amount,chosenGood.getKey(),Manage.getCurrentEmpire())) {
                if (checkTheCapacity(amount, chosenGood.getKey() ,ownerOfShop)) {
                    validationFormForSelling(nameOfGood, chosenGood.getValue(), amount);
                    String answer = scanner.nextLine();
                    if (ShopMenuCommands.getMatcher(answer, ShopMenuCommands.OPERATION_ACCEPTED) != null) {
                        Manage.getCurrentEmpire().setGoldCount(Manage.getCurrentEmpire().getGoldCount() + chosenGood.getValue());
                        ShopMenu.currentShop.setGoldCount(ShopMenu.currentShop.getGoldCount() - chosenGood.getValue());
                        setNumberOfGoods(ownerOfShop,Manage.getCurrentEmpire(),amount,chosenGood.getKey());
                        return ShopMenuMessages.SELLING_OPERATION_SUCCEEDED;
                    } else return ShopMenuMessages.OPERATION_CANCELLED;
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
                "You've asked for product : " + goodName + " in " + amount*5 + " quantity , with price : " + price + "\n" +
                "We need your approval before preparing your order.If you're certain about your order,please enter yes.\n" +
                "Otherwise,you're order will be cancelled.");
    }

    public void validationFormForSelling(String goodName, int price, int amount) {
        System.out.println("Dear seller from " + Manage.getCurrentEmpire().getName() + " Empire\n" +
                "You've wanted to sell product : " + goodName + " in " + amount + " quantity , with price : " + price + "\n" +
                "We need your approval before buying your product.If you're certain about your offer,please enter yes.\n" +
                "Otherwise,the deal will be cancelled.");
    }

    public int getNumberOfGoods(String goodName , Empire empire) {
        return switch (goodName) {
            case "meat" -> empire.getMeatCount();
            case "hops" -> empire.getOatCount();
            case "barrel" -> empire.getBeerCount();
            case "ironArmor" -> empire.getMetalArmour();
            case "leatherArmor" -> empire.getLeatherArmour();
            case "sword" -> empire.getSwordCount();
            //case "ironAxe" -> 0;
            //case "crossbow" -> 0;
            case "mace" -> empire.getMaceCount();
            case "bow" -> empire.getBowCount();
            //case "dart" -> 0;
            case "oil" -> empire.getOilAmount();
            case "iron" -> empire.getIronCount();
            case "stone" -> empire.getStoneCount();
            case "wood" -> empire.getWoodCount();
            case "flour" -> empire.getFlour();
            case "wheat" -> empire.getWheatCount();
            //case "bead" -> 0;
            case "apple" -> empire.getAppleCount();
            case "cheese" -> empire.getCheeseCount();
            default -> 0;
        };
    }
    public void setNumberOfGoods(Empire customer , Empire seller , int count , String goodName){
        switch (goodName) {
            case "meat" -> {
                customer.setMeatCount(customer.getMeatCount() + count);
                seller.setMeatCount(seller.getMeatCount() - count);
            }
            case "hops" -> {
                customer.setOatCount(customer.getOatCount() + count);
                seller.setOatCount(seller.getOatCount() - count);
            }
            case "barrel" -> {
                customer.setBeerCount(customer.getBeerCount() + count);
                seller.setBeerCount(seller.getBeerCount() - count);
            }
            case "ironArmour" -> {
                customer.setMetalArmour(customer.getMetalArmour() + count);
                seller.setMetalArmour(seller.getMetalArmour() - count);
            }
            case "leatherArmour" -> {
                customer.setLeatherArmour(customer.getLeatherArmour() + count);
                seller.setLeatherArmour(seller.getLeatherArmour() - count);
            }
            case "sword" -> {
                customer.setSwordCount(customer.getSwordCount() + count);
                seller.setSwordCount(seller.getSwordCount() - count);
            }
            //case "ironAxe";
            //case "crossbow";
            case "bow" -> {
                customer.setBowCount(customer.getBowCount() + count);
                seller.setBowCount(seller.getBowCount() - count);
            }
            case "mace" -> {
                customer.setMaceCount(customer.getMaceCount() + count);
                seller.setMaceCount(seller.getMaceCount() - count);
            }

            //case "dart";
            case  "oil" -> {
                customer.setOilAmount(customer.getOilAmount() + count);
                seller.setOilAmount(seller.getOilAmount() - count);
            }
            case "iron" -> {
                customer.setIronCount(customer.getIronCount() + count);
                seller.setIronCount(seller.getIronCount() - count);
            }
            case "stone" -> {
                customer.setStoneCount(customer.getStoneCount() + count);
                seller.setStoneCount(seller.getStoneCount() - count);
            }
            case "wood" -> {
                customer.setWoodCount(customer.getWoodCount() + count);
                seller.setWoodCount(seller.getWoodCount() - count);
            }
            case "flour" -> {
                customer.setFlour(customer.getFlour() + count);
                seller.setFlour(seller.getFlour() - count);
            }
            case "wheat" -> {
                customer.setWheatCount(customer.getWheatCount() + count);
                seller.setWheatCount(seller.getWheatCount() - count);
            }
            //case "bead";
            case "apple" -> {
                customer.setAppleCount(customer.getAppleCount() + count);
                seller.setAppleCount(seller.getAppleCount() - count);
            }
            case "cheese" -> {
                customer.setCheeseCount(customer.getCheeseCount() + count);
                seller.setCheeseCount(seller.getCheeseCount() - count);
            }
        }
    }
    public boolean checkTheCapacity(int count , String goodName , Empire empire){
        return switch (goodName) {
            case "meat" -> count + empire.getMeatCount() <= empire.getFoodCapacity();
            case "hops" -> count + empire.getOatCount() <= empire.getFoodCapacity();
            case "barrel" -> count + empire.getBeerCount() <= empire.getFoodCapacity();
            case"ironArmor" -> count + empire.getMetalArmour() <= empire.getWeaponsCapacity();
            case "leatherArmor" -> count + empire.getLeatherArmour() <= empire.getWeaponsCapacity();
            case "sword" -> count + empire.getSwordCount()<= empire.getWeaponsCapacity();
            //case "ironAxe";
            //case "crossbow";
            case  "mace" -> count + empire.getMaceCount() <= empire.getWeaponsCapacity();
            case "bow" -> count + empire.getBowCount()<= empire.getWeaponsCapacity();
            //case "dart";
            case "oil" -> count + empire.getOilAmount() <= empire.getFoodCapacity();
            case "iron" -> count + empire.getIronCount() <= empire.getResourcesCapacity();
            case "stone" -> count + empire.getStoneCount() <=  empire.getResourcesCapacity();
            case "wood" -> count +  empire.getWoodCount() <= empire.getResourcesCapacity();
            case "flour" -> count + empire.getFlour() <= empire.getFoodCapacity();
            case "wheat" -> count + empire.getWheatCount() <= empire.getFoodCapacity() ;
            //case "bead";
            case "apple" -> count + empire.getAppleCount() <= empire.getFoodCapacity();
            case "cheese" -> count + empire.getCheeseCount() <= empire.getFoodCapacity();
            default -> false;
        };
    }
}

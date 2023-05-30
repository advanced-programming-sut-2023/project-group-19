package controller;

import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.*;
import view.Commands.ShopMenuCommands;
import view.Messages.ShopMenuMessages;
import view.ShopMenu;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopController {
    public Empire ownerOfShop = ShopMenu.currentShop.getOwner();

    public ArrayList<Group> showPriceList() {
        int number = 0;
        ArrayList<Group> textAndImages = new ArrayList<>();
        ImageView imageView;
        Text text;
        int ySetter = 50;
        for (Map.Entry<String, Integer> goodsForBuying : ShopMenu.currentShop.getListOfGoodsBuyPrice().entrySet()) {
            number++;
            String nameOfGoodsForBuying = goodsForBuying.getKey();
            for (Map.Entry<String, Integer> goodsForSelling : ShopMenu.currentShop.getListOfGoodsSellPrice().entrySet()) {
                String nameOfGoodsForSelling = goodsForSelling.getKey();
                if (nameOfGoodsForBuying.equals(nameOfGoodsForSelling)) {
                    int resourceCount = getNumberOfGoods(nameOfGoodsForBuying, ownerOfShop);
                    CheckBox c = new CheckBox();
                    imageView = new ImageView(imageSetter(nameOfGoodsForBuying));
                    text = new Text("\tBuying Price: "
                            + goodsForBuying.getValue() + "\n\tSell Price: " + goodsForSelling.getValue() + "\n\tResource count: Infinite\n");
                    if (number <= 8) {
                        c.setLayoutX(250);
                        c.setLayoutY(ySetter);
                        c.setPrefSize(30, 30);
                        imageView.setLayoutX(270);
                        imageView.setLayoutY(ySetter);
                        text.setLayoutX(350);
                        text.setLayoutY(ySetter + 20);
                        if (number == 8) {
                            ySetter = 50;
                        }
                    } else {
                        c.setLayoutX(700);
                        c.setLayoutY(ySetter - 100);
                        c.setPrefSize(30, 30);
                        imageView.setX(725);
                        imageView.setY(ySetter - 100);
                        text.setX(805);
                        text.setY(ySetter - 90);
                    }

                    imageView.setFitHeight(70);
                    imageView.setFitWidth(70);
                    imageView.setPreserveRatio(true);

                    Group group = new Group();
                    group.getChildren().addAll(c, imageView, text);
                    textAndImages.add(group);
                    ySetter += 100;
                    break;
                }
            }
        }
        return textAndImages;
    }

    public ShopMenuMessages buyItem(String nameOfGood,int amount) {
        Map.Entry<String, Integer> chosenGood = findGoodToBuy(nameOfGood);
        if (chosenGood != null) {
            if (checkTheCapacity(amount, chosenGood.getKey(), Manage.getCurrentEmpire())) {
                if (enoughMoneyToBuy(Manage.getCurrentEmpire(), chosenGood.getValue())) {
                    boolean answer = setConfirmationBox(validationFormForBuying(nameOfGood, chosenGood.getValue(), amount));
                    if (answer) {
                        Manage.getCurrentEmpire().setGoldCount(Manage.getCurrentEmpire().getGoldCount() - chosenGood.getValue() * amount);
                        setNumberOfGoods(Manage.getCurrentEmpire(), 1, amount * 5, chosenGood.getKey());
                        return ShopMenuMessages.BUYING_OPERATION_SUCCEEDED;
                    } else return ShopMenuMessages.OPERATION_CANCELLED;
                } else return ShopMenuMessages.NOT_ENOUGH_MONEY_TO_BUY;
            } else return ShopMenuMessages.NOT_ENOUGH_CAPACITY_FOR_EMPIRE;
        }
        return ShopMenuMessages.INVALID_NAME_OF_ITEM;
    }

    public ShopMenuMessages sellItem(String nameOfGood, int amount) {
        Map.Entry<String, Integer> chosenGood = findGoodToSell(nameOfGood);
        if (chosenGood != null) {
            if (getNumberOfGoods(chosenGood.getKey(), Manage.getCurrentEmpire()) >= amount) {
                boolean answer = setConfirmationBox(validationFormForSelling(nameOfGood, chosenGood.getValue(), amount));
                if (answer) {
                    Manage.getCurrentEmpire().setGoldCount(Manage.getCurrentEmpire().getGoldCount() + chosenGood.getValue() * amount);
                    setNumberOfGoods(Manage.getCurrentEmpire(), -1, amount, chosenGood.getKey());
                    return ShopMenuMessages.SELLING_OPERATION_SUCCEEDED;
                } else return ShopMenuMessages.OPERATION_CANCELLED;
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

    public String validationFormForBuying(String goodName, int price, int amount) {
        return ("Dear customer from " + Manage.getCurrentEmpire().getName() + " Empire\n" +
                "You've asked for product : " + goodName + " in " + amount * 5 + " quantity , with price : " + price + "\n" +
                "We need your approval before preparing your order.If you're certain about your order,please enter yes.\n" +
                "Otherwise,you're order will be cancelled.");
    }

    public String validationFormForSelling(String goodName, int price, int amount) {
        return ("Dear seller from " + Manage.getCurrentEmpire().getName() + " Empire\n" +
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

    public void setNumberOfGoods(Empire customer, int multiplied, int count, String goodName) {
        switch (goodName) {
            case "meat" -> {
                customer.setMeatCount(customer.getMeatCount() + (multiplied) * count);
            }
            case "hops" -> {
                customer.setOatCount(customer.getOatCount() + (multiplied) * count);
            }
            case "ironArmour" -> {
                customer.setMetalArmour(customer.getMetalArmour() + (multiplied) * count);
            }
            case "leatherArmour" -> {
                customer.setLeatherArmour(customer.getLeatherArmour() + (multiplied) * count);
            }
            case "sword" -> {
                customer.setSwordCount(customer.getSwordCount() + (multiplied) * count);
            }
            case "bow" -> {
                customer.setBowCount(customer.getBowCount() + (multiplied) * count);
            }
            case "mace" -> {
                customer.setMaceCount(customer.getMaceCount() + (multiplied) * count);
            }
            case "oil" -> {
                customer.setOilAmount(customer.getOilAmount() + (multiplied) * count);
            }
            case "iron" -> {
                customer.setIronCount(customer.getIronCount() + (multiplied) * count);
            }
            case "stone" -> {
                customer.setStoneCount(customer.getStoneCount() + (multiplied) * count);
            }
            case "wood" -> {
                customer.setWoodCount(customer.getWoodCount() + (multiplied) * count);
            }
            case "flour" -> {
                customer.setFlour(customer.getFlour() + (multiplied) * count);
            }
            case "wheat" -> {
                customer.setWheatCount(customer.getWheatCount() + (multiplied) * count);
            }
            case "apple" -> {
                customer.setAppleCount(customer.getAppleCount() + (multiplied) * count);
            }
            case "cheese" -> {
                customer.setCheeseCount(customer.getCheeseCount() + (multiplied) * count);
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

    public Image imageSetter(String goodName) {
        Image image;
        switch (goodName) {
            case "meat" -> {
                image = new Image(ShopController.class.getResource("/image/ShopMenuImages/meat.png").toExternalForm());
                return image;
            }
            case "hops" -> {
                image = new Image(ShopController.class.getResource("/image/ShopMenuImages/hop.png").toExternalForm());
                return image;
            }
            case "ironArmour" -> {
                image = new Image(ShopController.class.getResource("/image/ShopMenuImages/armour.png").toExternalForm());
                return image;
            }
            case "leatherArmour" -> {
                image = new Image(ShopController.class.getResource("/image/ShopMenuImages/knight.png").toExternalForm());
                return image;
            }
            case "sword" -> {
                image = new Image(ShopController.class.getResource("/image/ShopMenuImages/sword.png").toExternalForm());
                return image;
            }
            case "bow" -> {
                image = new Image(ShopController.class.getResource("/image/ShopMenuImages/archery.png").toExternalForm());
                return image;
            }
            case "mace" -> {
                image = new Image(ShopController.class.getResource("/image/ShopMenuImages/mace.png").toExternalForm());
                return image;
            }
            case "oil" -> {
                image = new Image(ShopController.class.getResource("/image/ShopMenuImages/oil.png").toExternalForm());
                return image;
            }
            case "iron" -> {
                image = new Image(ShopController.class.getResource("/image/ShopMenuImages/steel.png").toExternalForm());
                return image;
            }
            case "stone" -> {
                image = new Image(ShopController.class.getResource("/image/ShopMenuImages/stone.png").toExternalForm());
                return image;
            }
            case "wood" -> {
                image = new Image(ShopController.class.getResource("/image/ShopMenuImages/wood.png").toExternalForm());
                return image;
            }
            case "flour" -> {
                image = new Image(ShopController.class.getResource("/image/ShopMenuImages/flour.png").toExternalForm());
                return image;
            }
            case "wheat" -> {
                image = new Image(ShopController.class.getResource("/image/ShopMenuImages/wheat.png").toExternalForm());
                return image;
            }
            case "apple" -> {
                image = new Image(ShopController.class.getResource("/image/ShopMenuImages/apple.png").toExternalForm());
                return image;
            }
            case "cheese" -> {
                image = new Image(ShopController.class.getResource("/image/ShopMenuImages/cheese.png").toExternalForm());
                return image;
            }
        }
        return null;
    }

    public boolean setConfirmationBox(String validationForm) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Shop Operations");
        alert.setTitle("Confirmation Form :");
        alert.setContentText(validationForm);
        alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK) {
            return true;
        } else if (result.get() == ButtonType.CANCEL) {
            return false;
        }
         return false;
    }
}

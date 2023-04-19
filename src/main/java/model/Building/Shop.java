package model.Building;

import model.Empire;

import java.util.HashMap;

public class Shop extends Building {

    Shop(Empire government) {
        super(government);
    }

    private HashMap<String, Integer> listOfGoodsBuyPrice = new HashMap<>(); // good name and its buy price

    {//price as 5
        listOfGoodsBuyPrice.put("meat", 40);
        listOfGoodsBuyPrice.put("hops", 75);
        listOfGoodsBuyPrice.put("barrel", 100);
        listOfGoodsBuyPrice.put("ironArmor", 290);
        listOfGoodsBuyPrice.put("leatherArmor", 125);
        listOfGoodsBuyPrice.put("sword", 290);
        listOfGoodsBuyPrice.put("ironAxe", 180);
        listOfGoodsBuyPrice.put("crossbow", 290);
        listOfGoodsBuyPrice.put("mace", 290);
        listOfGoodsBuyPrice.put("bow", 155);
        listOfGoodsBuyPrice.put("dart", 100);
        listOfGoodsBuyPrice.put("oil", 100);
        listOfGoodsBuyPrice.put("iron", 225);
        listOfGoodsBuyPrice.put("stone", 70);
        listOfGoodsBuyPrice.put("wood", 20);
        listOfGoodsBuyPrice.put("flour", 160);
        listOfGoodsBuyPrice.put("wheat", 115);
        listOfGoodsBuyPrice.put("bead", 40);
        listOfGoodsBuyPrice.put("apple", 40);
        listOfGoodsBuyPrice.put("cheese", 40);
    }

    private HashMap<String, Integer> listOfGoodsSellPrice = new HashMap<>(); // good name and its sell price

    {//price as 1
        listOfGoodsSellPrice.put("meat", 5);
        listOfGoodsSellPrice.put("hops", 10);
        listOfGoodsSellPrice.put("barrel", 15);
        listOfGoodsSellPrice.put("ironArmor", 40);
        listOfGoodsSellPrice.put("leatherArmor", 20);
        listOfGoodsSellPrice.put("sword", 40);
        listOfGoodsSellPrice.put("ironAxe", 30);
        listOfGoodsSellPrice.put("crossbow", 40);
        listOfGoodsSellPrice.put("mace", 40);
        listOfGoodsSellPrice.put("bow", 25);
        listOfGoodsSellPrice.put("dart", 15);
        listOfGoodsSellPrice.put("oil", 15);
        listOfGoodsSellPrice.put("iron", 20);
        listOfGoodsSellPrice.put("stone", 10);
        listOfGoodsSellPrice.put("wood", 2);
        listOfGoodsSellPrice.put("flour", 25);
        listOfGoodsSellPrice.put("wheat", 20);
        listOfGoodsSellPrice.put("bead", 5);
        listOfGoodsSellPrice.put("apple", 5);
        listOfGoodsSellPrice.put("cheese", 5);

    }


}

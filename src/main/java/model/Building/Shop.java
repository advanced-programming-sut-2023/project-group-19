package model.Building;

import model.Empire;
import model.GroundType;
import model.Manage;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Shop extends Building implements BuildingConstantFunctions {

    public Shop(Empire government) {
        super(government);
    }

    public LinkedHashMap<String, Integer> listOfGoodsBuyPrice = new LinkedHashMap<>(); // good name and its buy price

    {
        listOfGoodsBuyPrice.put("meat", 40);
        listOfGoodsBuyPrice.put("hops", 75);
        listOfGoodsBuyPrice.put("ironArmor", 290);
        listOfGoodsBuyPrice.put("leatherArmor", 125);
        listOfGoodsBuyPrice.put("sword", 290);
        listOfGoodsBuyPrice.put("mace", 290);
        listOfGoodsBuyPrice.put("bow", 155);
        listOfGoodsBuyPrice.put("oil", 100);
        listOfGoodsBuyPrice.put("iron", 225);
        listOfGoodsBuyPrice.put("stone", 70);
        listOfGoodsBuyPrice.put("wood", 20);
        listOfGoodsBuyPrice.put("flour", 160);
        listOfGoodsBuyPrice.put("wheat", 115);
        listOfGoodsBuyPrice.put("apple", 40);
        listOfGoodsBuyPrice.put("cheese", 40);
    }

    public LinkedHashMap<String, Integer> listOfGoodsSellPrice = new LinkedHashMap<>(); // good name and its sell price

    {
        listOfGoodsSellPrice.put("meat", 5);
        listOfGoodsSellPrice.put("hops", 10);
        listOfGoodsSellPrice.put("ironArmor", 40);
        listOfGoodsSellPrice.put("leatherArmor", 20);
        listOfGoodsSellPrice.put("sword", 40);
        listOfGoodsSellPrice.put("mace", 40);
        listOfGoodsSellPrice.put("bow", 25);
        listOfGoodsSellPrice.put("oil", 15);
        listOfGoodsSellPrice.put("iron", 20);
        listOfGoodsSellPrice.put("stone", 10);
        listOfGoodsSellPrice.put("wood", 2);
        listOfGoodsSellPrice.put("flour", 25);
        listOfGoodsSellPrice.put("wheat", 20);
        listOfGoodsSellPrice.put("apple", 5);
        listOfGoodsSellPrice.put("cheese", 5);

    }

    private int goldCount = Manage.getCurrentEmpire().getGoldCount();

    public Names getNames() {
        return this.name;
    }

    public int getGoldCount() {
        return goldCount;
    }

    public void setGoldCount(int goldCount) {
        this.goldCount = goldCount;
    }

    public void createBuildingCost(int wood, int stone, int gold, int iron, int oil) {
        this.cost.put("wood", wood);
        this.cost.put("stone", stone);
        this.cost.put("gold", gold);
        this.cost.put("iron", iron);
        this.cost.put("oil", oil);
    }

    public HashMap<String, Integer> getListOfGoodsBuyPrice() {
        return listOfGoodsBuyPrice;
    }

    public HashMap<String, Integer> getListOfGoodsSellPrice() {
        return listOfGoodsSellPrice;
    }

    public void createBuildingWorkersNeeded(int engineer, int worker) {
        this.workersNeeded.put("engineer", engineer);
        this.workersNeeded.put("worker", worker);

    }

    public void shop() {
        this.hp = 600;
        this.maxHp = 600;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        this.name = Names.SHOP;
        createBuildingCost(5, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 1);
    }

    @Override
    public int maxHp() {
        return this.maxHp;
    }

    @Override
    public int hp() {
        return this.hp;
    }

    @Override
    public String groundType() {
        return this.requiredGroundType;
    }

    @Override
    public int height() {
        return this.height;
    }

    @Override
    public String showBuildingName() {
        return this.name.getName();
    }
}

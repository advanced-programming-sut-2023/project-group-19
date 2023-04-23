package model.Building;

import model.Empire;

import java.util.HashMap;

public class Shop extends Building implements BuildingConstantFunctions {

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

    private Names names;

    public Names getNames() {
        return names;
    }

    public void setNames(Names names) {
        this.names = names;
    }


    @Override
    public int maxHp() {
        return 0;
    }

    @Override
    public int hp() {
        return 0;
    }

    @Override
    public String groundType() {
        return null;
    }

    @Override
    public String showBuildingName() {
        return "shop";
    }
    public HashMap<String, Integer> cost = new HashMap<>();

    //TODO call createBuildingCost before usage
    public void createBuildingCost(int wood, int stone, int gold, int iron, int oil) {
        cost.put("wood", wood);
        cost.put("stone", stone);
        cost.put("gold", gold);
        cost.put("iron", iron);
        cost.put("oil", oil);
    }
    public HashMap<String, Integer> workersNeeded = new HashMap<>();
    public void createBuildingWorkersNeeded(int engineer, int worker) {
        workersNeeded.put("engineer", engineer);
        workersNeeded.put("worker", worker);

    }
    public void shop() {
        names = Names.PITCH_DITCH;
        createBuildingCost(5, 0, 0, 0, 2);
        createBuildingWorkersNeeded(0 , 1);
    }
}

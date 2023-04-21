package model;

import controller.Building.FunctionBuildingController;

import java.util.ArrayList;
import java.util.HashMap;

public class Empire {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private int strayCount;
    private int engineerCount;
    private int troopCount;
    private int worker;

    public int getStrayCount() {
        return strayCount;
    }

    public void setStrayCount(int strayCount) {
        this.strayCount = strayCount;
    }

    public int getEngineerCount() {
        return engineerCount;
    }

    public void setEngineerCount(int engineerCount) {
        this.engineerCount = engineerCount;
    }

    public int getTroopCount() {
        return troopCount;
    }

    public void setTroopCount(int troopCount) {
        this.troopCount = troopCount;
    }

    public int getWorker() {
        return worker;
    }

    public void setWorker(int worker) {
        this.worker = worker;
    }

    private int foodCapacity;

    public int getFoodCapacity() {
        return foodCapacity;
    }

    public void setFoodCapacity(int foodCapacity) {
        this.foodCapacity = foodCapacity;
    }

    private String name;
    private int population;
    private int maxPossiblePopulation;

    public int getMaxPossiblePopulation() {
        return maxPossiblePopulation;
    }

    public void setMaxPossiblePopulation(int maxPossiblePopulation) {
        this.maxPossiblePopulation = maxPossiblePopulation;
    }

    public int popularity;
    private int goldCount;
    private int foodRateNumber;
    private int totalFoodCount;
    private int foodDiversity;
    private int taxRateNumber;
    private int fearRateNumber;

    public int getOatCount() {
        return stores.get("oat");
    }

    public void setOatCount(int oatCount) {
        stores.replace("oat", oatCount);
    }

    public int getWheatCount() {
        return stores.get("wheat");
    }

    public void setWheatCount(int wheatCount) {
        stores.replace("wheat", wheatCount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getGoldCount() {
        return goldCount;
    }

    public void setGoldCount(int goldCount) {
        this.goldCount = goldCount;
    }

    public int getWoodCount() {
        return stores.get("wood");
    }

    public void setWoodCount(int woodCount) {
        stores.replace("wood", woodCount);
    }

    public int getStoneCount() {
        return stores.get("stone");
    }

    public void setStoneCount(int stoneCount) {
        stores.replace("stone", stoneCount);
    }

    public int getIronCount() {
        return stores.get("iron");
    }

    public void setIronCount(int ironCount) {
        stores.replace("iron", ironCount);
    }

    public int getOilAmount() {
        return stores.get("oil");
    }

    public void setOilAmount(int oilAmount) {
        stores.replace("oil", oilAmount);
    }

    public int getFoodRateNumber() {
        return foodRateNumber;
    }

    public void setFoodRateNumber(int foodRateNumber) {
        this.foodRateNumber = foodRateNumber;
    }

    public int getTotalFoodCount() {
        return totalFoodCount;
    }

    public void setTotalFoodCount(int totalFoodCount) {
        this.totalFoodCount = totalFoodCount;
    }

    public int getFoodDiversity() {
        return foodDiversity;
    }

    public void setFoodDiversity(int foodDiversity) {
        this.foodDiversity = foodDiversity;
    }

    public int getTaxRateNumber() {
        return taxRateNumber;
    }

    public void setTaxRateNumber(int taxRateNumber) {
        this.taxRateNumber = taxRateNumber;
    }

    public int getFearRateNumber() {
        return fearRateNumber;
    }

    public void setFearRateNumber(int fearRateNumber) {
        this.fearRateNumber = fearRateNumber;
    }

    public HashMap<String, Integer> getAllFood() {
        return allFood;
    }

    public void setAllFood(HashMap<String, Integer> allFood) {
        this.allFood = allFood;
    }

    public ArrayList<String> getRequest() {
        return request;
    }

    public void setRequest(ArrayList<String> request) {
        this.request = request;
    }

    //TODO : when you create a quarry make sure you set the ox tether count to 1


    public int getIronMineRate() {
        return productionBuildingRate.get("ironMine");
    }

    public void setIronMineRate(int ironMineRate) {
        productionBuildingRate.replace("ironMine", ironMineRate);
    }

    public int getPitchRigRate() {
        return productionBuildingRate.get("pitchRig");
    }

    public void setPitchRigRate(int pitchRigRate) {
        productionBuildingRate.replace("pitchRig", pitchRigRate);
    }

    public int getQuarryRate() {
        return productionBuildingRate.get("quarry");
    }

    public void setQuarryRate(int quarryRate) {
        productionBuildingRate.replace("quarry", quarryRate);
    }

    public int getWoodCutterRate() {
        return productionBuildingRate.get("woodCutter");
    }

    public void setWoodCutterRate(int woodCutterRate) {
        productionBuildingRate.replace("woodCutter", woodCutterRate);
    }

    public int getAppleFarmRate() {
        return productionBuildingRate.get("appleFarm");
    }

    public void setAppleFarmRate(int appleFarmRate) {
        productionBuildingRate.replace("appleFarm", appleFarmRate);
    }

    public int getOatFarmRate() {
        return productionBuildingRate.get("oatFarm");
    }

    public void setOatFarmRate(int oatFarmRate) {
        productionBuildingRate.replace("oatFarm", oatFarmRate);
    }

    public int getHuntingPostRate() {
        return productionBuildingRate.get("huntingPost");
    }

    public void setHuntingPostRate(int huntingPostRate) {
        productionBuildingRate.replace("huntingPost", huntingPostRate);
    }

    public int getBeerFactoryRate() {
        return productionBuildingRate.get("beerFactory");
    }

    public void setBeerFactoryRate(int beerFactoryRate) {
        productionBuildingRate.replace("beerFactory", beerFactoryRate);
    }

    public int getBakeryRate() {
        return productionBuildingRate.get("bakery");
    }

    public void setBakeryRate(int bakeryRate) {
        productionBuildingRate.replace("bakery", bakeryRate);
    }

    public int getMillRate() {
        return productionBuildingRate.get("mill");
    }

    public void setMillRate(int millRate) {
        productionBuildingRate.replace("mill", millRate);
    }

    public int getDairyFactoryRate() {
        return productionBuildingRate.get("dairyFactory");
    }

    public void setDairyFactoryRate(int dairyFactoryRate) {
        productionBuildingRate.replace("dairyFactory", dairyFactoryRate);
    }


    public int getIronMineCount() {
        return productionBuildingCount.get("ironMine");
    }

    public void setIronMineCount(int ironMineCount) {
        productionBuildingCount.replace("ironMine", ironMineCount);
    }

    public int getOxTetherCount() {
        return productionBuildingCount.get("oxTether");
    }

    public void setOxTetherCount(int oxTetherCount) {
        productionBuildingCount.replace("oxTether", oxTetherCount);
    }

    public int getPitchRigCount() {
        return productionBuildingCount.get("pitchRig");
    }

    public void setPitchRigCount(int pitchRigCount) {
        productionBuildingCount.replace("pitchRig", pitchRigCount);
    }

    public int getQuarryCount() {
        return productionBuildingCount.get("quarry");
    }

    public void setQuarryCount(int quarryCount) {
        productionBuildingCount.replace("quarry", quarryCount);
    }

    public int getWoodCutterCount() {
        return productionBuildingCount.get("woodCutter");
    }

    public void setWoodCutterCount(int woodCutterCount) {
        productionBuildingCount.replace("woodCutter", woodCutterCount);
    }

    public int getAppleFarmCount() {
        return productionBuildingCount.get("appleFarm");
    }

    public void setAppleFarmCount(int appleFarmCount) {
        productionBuildingCount.replace("appleFarm", appleFarmCount);
    }

    public int getOatFarmCount() {
        return productionBuildingCount.get("oatFarm");
    }

    public void setOatFarmCount(int oatFarmCount) {
        productionBuildingCount.replace("oatFarm", oatFarmCount);
    }

    public int getHuntingPostCount() {
        return productionBuildingCount.get("huntingPost");
    }

    public void setHuntingPostCount(int huntingPostCount) {
        productionBuildingCount.replace("huntingPost", huntingPostCount);
    }

    public int getWheatFarmCount() {
        return productionBuildingCount.get("wheatFarm");
    }

    public void setWheatFarmCount(int wheatFarmCount) {
        productionBuildingCount.replace("wheatFarm", wheatFarmCount);
    }

    public int getBeerFactoryCount() {
        return productionBuildingCount.get("beerFactory");
    }

    public void setBeerFactoryCount(int beerFactoryCount) {
        productionBuildingCount.replace("beerFactory", beerFactoryCount);
    }

    public int getBakeryCount() {
        return productionBuildingCount.get("bakery");
    }

    public void setBakeryCount(int bakeryCount) {
        productionBuildingCount.replace("bakery", bakeryCount);
    }

    public int getMillCount() {
        return productionBuildingCount.get("mill");
    }

    public void setMillCount(int millCount) {
        productionBuildingCount.replace("mill", millCount);
    }

    public int getFlour() {
        return stores.get("flour");
    }

    public void setFlour(int flour) {
        stores.replace("flour", flour);
    }

    public int getBeer() {
        return stores.get("beer");
    }

    public void setBeer(int beer) {
        stores.replace("beer", beer);
    }

    public int getBread() {
        return allFood.get("bread");
    }

    public void setBread(int bread) {
        allFood.replace("bread", bread);
    }

    public int getArmourerBuildingRate() {
        return createWeaponBuildingRate.get("armourer");
    }

    public void setArmourerBuildingRate(int armourerBuildingRate) {
        createWeaponBuildingRate.replace("armourer", armourerBuildingRate);
    }

    public int getBlacksmithBuildingRate() {
        return createWeaponBuildingRate.get("blacksmith");
    }

    public void setBlacksmithBuildingRate(int blacksmithBuildingRate) {
        createWeaponBuildingRate.replace("blacksmith", blacksmithBuildingRate);
    }

    public int getFletcherBuildingRate() {
        return createWeaponBuildingRate.get("fletcher");
    }

    public void setFletcherBuildingRate(int fletcherBuildingRate) {
        createWeaponBuildingRate.replace("fletcher", fletcherBuildingRate);
    }

    public int getPoleTurnerBuildingRate() {
        return createWeaponBuildingRate.get("poleTurner");
    }

    public void setPoleTurnerBuildingRate(int poleTurnerBuildingRate) {
        createWeaponBuildingRate.replace("poleTurner", poleTurnerBuildingRate);
    }


    public int getArmourerBuildingCount() {
        return createWeaponBuildingCount.get("armourer");
    }

    public void setArmourerBuildingCount(int armourerBuildingCount) {
        createWeaponBuildingCount.replace("armourer", armourerBuildingCount);
    }

    public int getBlacksmithBuildingCount() {
        return createWeaponBuildingCount.get("blacksmith");
    }

    public void setBlacksmithBuildingCount(int blacksmithBuildingCount) {
        createWeaponBuildingCount.replace("blacksmith", blacksmithBuildingCount);
    }

    public int getFletcherBuildingCount() {
        return createWeaponBuildingCount.get("fletcher");
    }

    public void setFletcherBuildingCount(int fletcherBuildingCount) {
        createWeaponBuildingCount.replace("fletcher", fletcherBuildingCount);
    }

    public int getPoleTurnerBuildingCount() {
        return createWeaponBuildingCount.get("poleTurner");
    }

    public void setPoleTurnerBuildingCount(int poleTurnerBuildingCount) {
        createWeaponBuildingCount.replace("poleTurner", poleTurnerBuildingCount);
    }

    public int getLeatherArmourCount() {
        return allWeaponTools.get("leatherArmour");
    }

    public void setLeatherArmourCount(int leatherArmourCount) {
        allWeaponTools.replace("leatherArmour", leatherArmourCount);
    }

    public int getMetalArmourCount() {
        return allWeaponTools.get("metalArmour");
    }

    public void setMetalArmourCount(int metalArmourCount) {
        allWeaponTools.replace("metalArmour", metalArmourCount);
    }

    public int getBowCount() {
        return allWeaponTools.get("bow");
    }

    public void setBowCount(int bowCount) {
        allWeaponTools.replace("bow", bowCount);
    }

    public int getSwordCount() {
        return allWeaponTools.get("sword");
    }

    public void setSwordCount(int swordCount) {
        allWeaponTools.replace("sword", swordCount);
    }

    public int getMaceCount() {
        return allWeaponTools.get("mace");
    }

    public void setMaceCount(int maceCount) {
        allWeaponTools.replace("mace", maceCount);
    }

    public int getSpearCount() {
        return allWeaponTools.get("spear");
    }

    public void setSpearCount(int spearCount) {
        allWeaponTools.replace("spear", spearCount);
    }

    public int getPeakCount() {
        return allWeaponTools.get("peak");
    }

    public void setPeakCount(int peakCount) {
        allWeaponTools.replace("peak", peakCount);
    }

    public ArrayList<String> getDonation() {
        return donation;
    }

    public void setDonation(ArrayList<String> donation) {
        this.donation = donation;
    }

    // TODO call createFoodList function before usage
    public HashMap<String, Integer> allFood = new HashMap<>();

    {
        allFood.put("apple", 0);
        allFood.put("bread", 0);
        allFood.put("cheese", 0);
        allFood.put("meat", 0);
    }

    public HashMap<String, Integer> allWeaponTools = new HashMap<>();

    {
        allWeaponTools.put("leatherArmour", 0);
        allWeaponTools.put("metalArmour", 0);
        allWeaponTools.put("bow", 0);
        allWeaponTools.put("sword", 0);
        allWeaponTools.put("mace", 0);
        allWeaponTools.put("spear", 0);
        allWeaponTools.put("peak", 0);
    }


    public HashMap<String, Integer> stores = new HashMap<>();

    {
        stores.put("wood", 0);
        stores.put("iron", 0);
        stores.put("stone", 0);
        stores.put("oat", 0);
        stores.put("wheat", 0);
        stores.put("beer", 0);
        stores.put("flour", 0);
        stores.put("oil", 0);
    }

    public HashMap<String, Integer> productionBuildingCount = new HashMap<>();

    {
        productionBuildingCount.put("ironMine", 0);
        productionBuildingCount.put("oxTether", 0);
        productionBuildingCount.put("pitchRig", 0);
        productionBuildingCount.put("quarry", 0);
        productionBuildingCount.put("woodCutter", 0);
        productionBuildingCount.put("appleFarm", 0);
        productionBuildingCount.put("oatFarm", 0);
        productionBuildingCount.put("huntingPost", 0);
        productionBuildingCount.put("beerFactory", 0);
        productionBuildingCount.put("bakery", 0);
        productionBuildingCount.put("mill", 0);
        productionBuildingCount.put("dairyFactory", 0);
        productionBuildingCount.put("wheatFactory", 0);
    }

    public HashMap<String, Integer> productionBuildingRate = new HashMap<>();

    {
        productionBuildingRate.put("ironMine", 30);
        productionBuildingRate.put("pitchRig", 4);
        productionBuildingRate.put("quarry", 12);
        productionBuildingRate.put("woodCutter", 20);
        productionBuildingRate.put("appleFarm", 20);
        productionBuildingRate.put("oatFarm", 20);
        productionBuildingRate.put("huntingPost", 20);
        productionBuildingRate.put("beerFactory", 5);
        productionBuildingRate.put("bakery", 5);
        productionBuildingRate.put("mill", 5);
        productionBuildingRate.put("dairyFactory", 5);
        productionBuildingRate.put("wheatFactory", 20);
    }

    public HashMap<String, Integer> createWeaponBuildingCount = new HashMap<>();

    {
        createWeaponBuildingCount.put("armourer", 0);
        createWeaponBuildingCount.put("blacksmith", 0);
        createWeaponBuildingCount.put("fletcher", 0);
        createWeaponBuildingCount.put("poleTurner", 0);

    }

    public HashMap<String, Integer> createWeaponBuildingRate = new HashMap<>();

    {
        createWeaponBuildingRate.put("armourer", 5);
        createWeaponBuildingRate.put("blacksmith", 5);
        createWeaponBuildingRate.put("fletcher", 5);
        createWeaponBuildingRate.put("poleTurner", 5);
    }


    public ArrayList<String> request = new ArrayList<>();
    public ArrayList<String> donation = new ArrayList<>();

    //TODO : MAKE SURE TO CALL THIS FUNCTION EVERY TIME ITS THE PLAYERS TURN BEFORE ANYTHING
    public void independentProductionBuilding() { //part 1 of 5TYPE algorithm
        stores.replace("iron", stores.get("iron") + productionBuildingRate.get("ironMine") * productionBuildingCount.get("ironMine"));
        stores.replace("oil", stores.get("oil") + productionBuildingRate.get("pitchRig") * productionBuildingCount.get("pitchRig"));
        stores.replace("stone", stores.get("stone") + productionBuildingRate.get("quarry") * productionBuildingCount.get("quarry"));
        stores.replace("wood", stores.get("wood") + productionBuildingRate.get("woodCutter") * productionBuildingCount.get("woodCutter"));
        allFood.replace("apple", allFood.get("apple") + productionBuildingRate.get("appleFarm") * productionBuildingCount.get("appleFarm"));
        stores.replace("oat", stores.get("oat") + productionBuildingRate.get("oatFarm") * productionBuildingCount.get("oatFarm"));
        allFood.replace("meat", allFood.get("meat") + productionBuildingRate.get("huntingPost") * productionBuildingCount.get("huntingPost"));
        stores.replace("wheat", stores.get("wheat") + productionBuildingRate.get("wheatFactory") * productionBuildingCount.get("wheatFactory"));
    }

    public void functionBuildings() { //part 2 of 5TYPE algorithm
        FunctionBuildingController.empire = this;
        FunctionBuildingController.transformWheatToFlour();
        FunctionBuildingController.transformFlourToBread();
        FunctionBuildingController.transformOatToBeer();
        FunctionBuildingController.transformIronToMetalArmour();
        FunctionBuildingController.transformIronToSwordOrMace();
        FunctionBuildingController.transformWoodToBow();
        FunctionBuildingController.transformWoodToSpearOrPike();
    }

}

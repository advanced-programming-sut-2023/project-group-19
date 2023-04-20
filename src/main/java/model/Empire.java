package model;

import model.*;
import model.Building.Building;

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

    private int popularity;
    private int goldCount;
    private int woodCount;
    private int stoneCount;
    private int ironCount;
    private int oilAmount;
    private int wheatCount;
    private int oatCount;
    private int foodRateNumber;
    private int totalFoodCount;
    private int foodDiversity;
    private int taxRateNumber;
    private int fearRateNumber;

    public int getOatCount() {
        return oatCount;
    }

    public void setOatCount(int oatCount) {
        this.oatCount = oatCount;
    }

    public int getWheatCount() {
        return wheatCount;
    }

    public void setWheatCount(int wheatCount) {
        this.wheatCount = wheatCount;
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
        return woodCount;
    }

    public void setWoodCount(int woodCount) {
        this.woodCount = woodCount;
    }

    public int getStoneCount() {
        return stoneCount;
    }

    public void setStoneCount(int stoneCount) {
        this.stoneCount = stoneCount;
    }

    public int getIronCount() {
        return ironCount;
    }

    public void setIronCount(int ironCount) {
        this.ironCount = ironCount;
    }

    public int getOilAmount() {
        return oilAmount;
    }

    public void setOilAmount(int oilAmount) {
        this.oilAmount = oilAmount;
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
    private int ironMineRate = 20;
    private int pitchRigRate = 4;
    private int quarryRate = 12; //--> ox tether * 12
    private int woodCutterRate = 20;
    private int appleFarmRate = 20;
    private int oatFarmRate = 20;
    private int huntingPostRate = 20;
    private int wheatFarmRate = 20;


    private int ironMineCount; //--> ox tether
    private int oxTetherCount;
    private int pitchRigCount;
    private int quarryCount;
    private int woodCutterCount;
    private int appleFarmCount;
    private int oatFarmCount;
    private int huntingPostCount;
    private int wheatFarmCount;

    public int getIronMineRate() {
        return ironMineRate;
    }

    public void setIronMineRate(int ironMineRate) {
        this.ironMineRate = ironMineRate;
    }

    public int getPitchRigRate() {
        return pitchRigRate;
    }

    public void setPitchRigRate(int pitchRigRate) {
        this.pitchRigRate = pitchRigRate;
    }

    public int getQuarryRate() {
        return quarryRate;
    }

    public void setQuarryRate(int quarryRate) {
        this.quarryRate = quarryRate;
    }

    public int getWoodCutterRate() {
        return woodCutterRate;
    }

    public void setWoodCutterRate(int woodCutterRate) {
        this.woodCutterRate = woodCutterRate;
    }

    public int getAppleFarmRate() {
        return appleFarmRate;
    }

    public void setAppleFarmRate(int appleFarmRate) {
        this.appleFarmRate = appleFarmRate;
    }

    public int getOatFarmRate() {
        return oatFarmRate;
    }

    public void setOatFarmRate(int oatFarmRate) {
        this.oatFarmRate = oatFarmRate;
    }

    public int getHuntingPostRate() {
        return huntingPostRate;
    }

    public void setHuntingPostRate(int huntingPostRate) {
        this.huntingPostRate = huntingPostRate;
    }

    public int getWheatFarmRate() {
        return wheatFarmRate;
    }

    public void setWheatFarmRate(int wheatFarmRate) {
        this.wheatFarmRate = wheatFarmRate;
    }

    public int getIronMineCount() {
        return ironMineCount;
    }

    public void setIronMineCount(int ironMineCount) {
        this.ironMineCount = ironMineCount;
    }

    public int getOxTetherCount() {
        return oxTetherCount;
    }

    public void setOxTetherCount(int oxTetherCount) {
        this.oxTetherCount = oxTetherCount;
    }

    public int getPitchRigCount() {
        return pitchRigCount;
    }

    public void setPitchRigCount(int pitchRigCount) {
        this.pitchRigCount = pitchRigCount;
    }

    public int getQuarryCount() {
        return quarryCount;
    }

    public void setQuarryCount(int quarryCount) {
        this.quarryCount = quarryCount;
    }

    public int getWoodCutterCount() {
        return woodCutterCount;
    }

    public void setWoodCutterCount(int woodCutterCount) {
        this.woodCutterCount = woodCutterCount;
    }

    public int getAppleFarmCount() {
        return appleFarmCount;
    }

    public void setAppleFarmCount(int appleFarmCount) {
        this.appleFarmCount = appleFarmCount;
    }

    public int getOatFarmCount() {
        return oatFarmCount;
    }

    public void setOatFarmCount(int oatFarmCount) {
        this.oatFarmCount = oatFarmCount;
    }

    public int getHuntingPostCount() {
        return huntingPostCount;
    }

    public void setHuntingPostCount(int huntingPostCount) {
        this.huntingPostCount = huntingPostCount;
    }

    public int getWheatFarmCount() {
        return wheatFarmCount;
    }

    public void setWheatFarmCount(int wheatFarmCount) {
        this.wheatFarmCount = wheatFarmCount;
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

    private ArrayList<String> request = new ArrayList<>();
    private ArrayList<String> donation = new ArrayList<>();

    //TODO : MAKE SURE TO CALL THIS FUNCTION EVERY TIME ITS THE PLAYERS TURN BEFORE ANYTHING
    public void independentProductionBuilding() {
        ironCount += ironMineRate * ironMineCount;
        oilAmount += pitchRigRate * pitchRigCount;
        stoneCount += quarryCount * quarryRate * oxTetherCount;
        woodCount += woodCutterCount * woodCutterRate;
        allFood.replace("apple", allFood.get("apple") + (appleFarmCount * appleFarmRate));
        oatCount += oatFarmCount * oatFarmRate;
        allFood.replace("meat", allFood.get("meat") + (huntingPostCount * huntingPostRate));
        wheatCount += wheatFarmCount * wheatFarmRate;
    }
}

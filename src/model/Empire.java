package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Empire {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    private int popularity;
    private int goldCount;
    private int woodCount;
    private int stoneCount;
    private int ironCount;
    private int oilAmount;
    private int foodRateNumber;
    private int totalFoodCount;
    private int foodDiversity;
    private int taxRateNumber;
    private int fearRateNumber;

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

    public ArrayList<String> getDonation() {
        return donation;
    }

    public void setDonation(ArrayList<String> donation) {
        this.donation = donation;
    }
// TODO: call createFoodList function before usage

    private HashMap<String , Integer> allFood = new HashMap<>();
    private ArrayList<String> request = new ArrayList<>();
    private ArrayList<String> donation = new ArrayList<>();

    public void CreateFoodList(){
        allFood.put("apple" , 0);
        allFood.put("bread" , 0);
        allFood.put("cheese" , 0);
        allFood.put("meat", 0);
    }

}

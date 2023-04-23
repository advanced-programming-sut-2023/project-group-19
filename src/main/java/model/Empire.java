package model;

import controller.Building.FunctionBuildingController;
import model.Human.Troop.Army;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Math.floor;

public class Empire {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private int foodCapacity = 50;
    private int foodCount;
    private int weaponsCapacity = 50;
    private int weaponsCount;
    private int resourcesCapacity = 100;
    private int resourcesCount;
    private int maxPossiblePopulation;
    private int PeasantCount;
    private int troopCount;
    private int workerCount;

    public ArrayList<Army> empireArmy = new ArrayList<>();


    public int getPeasantCount() {
        return PeasantCount;
    }

    public void setPeasantCount(int peasantCount) {
        PeasantCount = peasantCount;
    }

    public int getTroopCount() {
        return troopCount;
    }

    public void setTroopCount(int troopCount) {
        this.troopCount = troopCount;
    }

    public int getWorkerCount() {
        return workerCount;
    }

    public void setWorkerCount(int worker) {
        this.workerCount = worker;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    public int getWeaponsCount() {
        return weaponsCount;
    }

    public void setWeaponsCount(int weaponsCount) {
        this.weaponsCount = weaponsCount;
    }

    public int getResourcesCount() {
        return resourcesCount;
    }

    public void setResourcesCount(int resourcesCount) {
        this.resourcesCount = resourcesCount;
    }

    public int getFoodCapacity() {
        return foodCapacity;
    }

    public void setFoodCapacity(int foodCapacity) {
        this.foodCapacity = foodCapacity;
    }

    private String name;
    private int population;


    public int getMaxPossiblePopulation() {
        return maxPossiblePopulation;
    }

    public void setMaxPossiblePopulation(int maxPossiblePopulation) {
        this.maxPossiblePopulation = maxPossiblePopulation;
    }

    public int popularity;
    private int goldCount;
    private int foodRateNumber = -2;
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

    public int getWeaponsCapacity() {
        return weaponsCapacity;
    }

    public void setWeaponsCapacity(int weaponsCapacity) {
        this.weaponsCapacity = weaponsCapacity;
    }

    public int getResourcesCapacity() {
        return resourcesCapacity;
    }

    public void setResourcesCapacity(int resourcesCapacity) {
        this.resourcesCapacity = resourcesCapacity;
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
        createWeaponBuildingCount.replace("armourer", blacksmithBuildingCount);
    }

    public int getStableBuildingCount() {
        return createWeaponBuildingCount.get("stable");
    }

    public void setStableBuildingCount(int stableBuildingCount) {
        createWeaponBuildingCount.replace("stable", stableBuildingCount);
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

    public int getHorseCount() {
        return allWeaponTools.get("horse");
    }

    public void setHorseCount(int horseCount) {
        allWeaponTools.replace("horse", horseCount);
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

    public int getEuropeArcherCount() {
        return europeTroopCount.get("archer");
    }

    public void setEuropeArcherCount(int europeArcherCount) {
        europeTroopCount.replace("archer", europeArcherCount);
    }

    public int getSpearManCount() {
        return europeTroopCount.get("spearMan");
    }

    public void setSpearManCount(int spearManCount) {
        europeTroopCount.replace("spearMan", spearManCount);
    }

    public int getMaceManCount() {
        return europeTroopCount.get("maceMan");
    }

    public void setMaceManCount(int maceManCount) {
        europeTroopCount.replace("maceMan", maceManCount);
    }

    public int getCrossbowManCount() {
        return europeTroopCount.get("crossbowMan");
    }

    public void setCrossbowManCount(int crossbowManCount) {
        europeTroopCount.replace("crossbowMan", crossbowManCount);
    }

    public int getPikeManCount() {
        return europeTroopCount.get("pikeMan");
    }

    public void setPikeManCount(int pikeManCount) {
        europeTroopCount.replace("pikeMan", pikeManCount);
    }

    public int getSwordManCount() {
        return europeTroopCount.get("swordMan");
    }

    public void setSwordManCount(int swordManCount) {
        europeTroopCount.replace("swordMan", swordManCount);
    }

    public int getKnightCount() {
        return europeTroopCount.get("knight");
    }

    public void setKnightCount(int knightCount) {
        europeTroopCount.replace("knight", knightCount);
    }
    public int getBlackMonkCount() {
        return europeTroopCount.get("blackMonk");
    }

    public void setBlackMonkCount(int blackMonkCount) {
        europeTroopCount.replace("blackMonk", blackMonkCount);
    }

    public int getArabianBowCount() {
        return arabTroopCount.get("arabianBow");
    }

    public void setArabianBowCount(int arabianBowCount) {
        arabTroopCount.replace("arabianBow", arabianBowCount);
    }

    public int getSlaveCount() {
        return arabTroopCount.get("slave");
    }

    public void setSlaveCount(int slaveCount) {
        arabTroopCount.replace("slave", slaveCount);
    }

    public int getSlingerCount() {
        return arabTroopCount.get("slinger");
    }

    public void setSlingerCount(int slingerCount) {
        arabTroopCount.replace("slinger", slingerCount);
    }

    public int getAssassinCount() {
        return arabTroopCount.get("assassin");
    }

    public void setAssassinCount(int assassinCount) {
        arabTroopCount.replace("assassin", assassinCount);
    }

    public int getHorseArcherCount() {
        return arabTroopCount.get("horseArcher");
    }

    public void setHorseArcherCount(int horseArcherCount) {
        arabTroopCount.replace("horseArcher", horseArcherCount);
    }

    public int getArabianSwordManCount() {
        return arabTroopCount.get("arabianSwordMan");
    }

    public void setArabianSwordManCount(int arabianSwordManCount) {
        arabTroopCount.replace("arabianSwordMan", arabianSwordManCount);
    }

    public int getFireThrowerCount() {
        return arabTroopCount.get("fireThrower");
    }

    public void setFireThrowerCount(int fireThrowerCount) {
        arabTroopCount.replace("fireThrower", fireThrowerCount);
    }


    public int getEngineerCount() {
        return engineerGuildTroopCount.get("engineer");
    }

    public void setEngineerCount(int engineerCount) {
        engineerGuildTroopCount.replace("engineer", engineerCount);
    }

    public int getLadderManCount() {
        return engineerGuildTroopCount.get("ladderMan");
    }

    public void setLadderManCount(int ladderManCount) {
        engineerGuildTroopCount.replace("ladderMan", ladderManCount);
    }

    public int getTunnelerCount() {
        return engineerGuildTroopCount.get("tunneler");
    }

    public void setTunnelerCount(int tunnelerCount) {
        engineerGuildTroopCount.replace("tunneler", tunnelerCount);
    }
    public int getPopularityFactorFood() {
        return popularityFactors.get("food");
    }

    public void setPopularityFactorFood(int popularityFactorFood) {
        popularityFactors.replace("food", popularityFactorFood);
    }
    public int getPopularityFactorTax() {
        return popularityFactors.get("tax");
    }

    public void setPopularityFactorTax(int popularityFactorTax) {
        popularityFactors.replace("tax", popularityFactorTax);
    }
    public int getPopularityFactorReligious() {
        return popularityFactors.get("religious");
    }

    public void setPopularityFactorReligious(int popularityFactorReligious) {
        popularityFactors.replace("religious", popularityFactorReligious);
    }
    public int getPopularityFactorFear() {
        return popularityFactors.get("fear");
    }

    public void setPopularityFactorFear(int popularityFactorFear) {
        popularityFactors.replace("fear", popularityFactorFear);
    }


    public ArrayList<String> getDonation() {
        return donation;
    }

    public void setDonation(ArrayList<String> donation) {
        this.donation = donation;
    }

    public int getCatapultCount() {
        return popularityFactors.get("catapult");
    }

    public void setCatapultCount(int catapultCount) {
        popularityFactors.replace("catapult", catapultCount);
    }
    public int getTrebuchetCount() {
        return popularityFactors.get("trebuchet");
    }

    public void setTrebuchetCount(int trebuchetCount) {
        popularityFactors.replace("trebuchet", trebuchetCount);
    }
    public int getSiegeTowerCount() {
        return popularityFactors.get("siegeTower");
    }

    public void setSiegeTowerCount(int siegeTowerCount) {
        popularityFactors.replace("siegeTower", siegeTowerCount);
    }
    public int getFireBalistaCount() {
        return popularityFactors.get("fireBalista");
    }

    public void setFireBalistaCount(int fireBalistaCount) {
        popularityFactors.replace("fireBalista", fireBalistaCount);
    }
    public int getBatteringRamCount() {
        return popularityFactors.get("batteringRam");
    }

    public void setBatteringRamCount(int batteringRamCount) {
        popularityFactors.replace("batteringRam", batteringRamCount);
    }
    public int getPortableShieldCount() {
        return popularityFactors.get("portableShield");
    }

    public void setPortableShieldCount(int portableShieldCount) {
        popularityFactors.replace("portableShield", portableShieldCount);
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
        allWeaponTools.put("horse", 0);
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
        createWeaponBuildingCount.put("stable", 0);

    }

    public HashMap<String, Integer> createWeaponBuildingRate = new HashMap<>();

    {
        createWeaponBuildingRate.put("armourer", 5);
        createWeaponBuildingRate.put("blacksmith", 5);
        createWeaponBuildingRate.put("fletcher", 5);
        createWeaponBuildingRate.put("poleTurner", 5);
        createWeaponBuildingRate.put("stable", 4);
    }

    public HashMap<String, Integer> europeTroopCount = new HashMap<>();

    {
        europeTroopCount.put("archer", 0);
        europeTroopCount.put("spearMan", 0);
        europeTroopCount.put("maceMan", 0);
        europeTroopCount.put("crossbowMan", 0);
        europeTroopCount.put("pikeMan", 0);
        europeTroopCount.put("swordMan", 0);
        europeTroopCount.put("knight", 0);
        europeTroopCount.put("blackMonk", 0);
    }

    public HashMap<String, Integer> arabTroopCount = new HashMap<>();

    {
        arabTroopCount.put("arabianBow", 0);
        arabTroopCount.put("slave", 0);
        arabTroopCount.put("slinger", 0);
        arabTroopCount.put("assassin", 0);
        arabTroopCount.put("horseArcher", 0);
        arabTroopCount.put("arabianSwordMan", 0);
        arabTroopCount.put("fireThrower", 0);
    }

    public HashMap<String, Integer> engineerGuildTroopCount = new HashMap<>();

    {
        engineerGuildTroopCount.put("engineer", 0);
        engineerGuildTroopCount.put("ladderMan", 0);
        engineerGuildTroopCount.put("tunneler", 0);
    }
    public HashMap<String, Integer> popularityFactors = new HashMap<>();

    {
        popularityFactors.put("food", 0);
        popularityFactors.put("tax", 0);
        popularityFactors.put("religious", 0);
        popularityFactors.put("fear", 0);
    }
    public HashMap<String, Integer> siegeTentTroopsCount = new HashMap<>();

    {
        siegeTentTroopsCount.put("catapult", 0);
        siegeTentTroopsCount.put("trebuchet", 0);
        siegeTentTroopsCount.put("siegeTower", 0);
        siegeTentTroopsCount.put("fireBalista", 0);
        siegeTentTroopsCount.put("batteringRam", 0);
        siegeTentTroopsCount.put("portableShield", 0);
    }

    public ArrayList<String> request = new ArrayList<>();
    public ArrayList<String> donation = new ArrayList<>();

    //TODO : MAKE SURE TO CALL THIS FUNCTION EVERY TIME ITS THE PLAYERS TURN BEFORE ANYTHING
    // call these functions in the order that has been written in this part


    public void findFoodDiversity(){
        int foodDiversity = 0;
        if(allFood.get("apple") > 0) foodDiversity++;
        if(allFood.get("bread") > 0 ) foodDiversity++;
        if(allFood.get("cheese") > 0) foodDiversity++;
        if(allFood.get("meat") > 0 ) foodDiversity++;
        setFoodDiversity(foodDiversity);
        switch (foodDiversity){
            case 2 :
                setPopularity(getPopularity() + 1);
            case 3 :
                setPopularity(getPopularity() + 2);
            case 4 :
                setPopularity(getPopularity() + 3);
        }
    }
    public void taxImpactOnEmpire(Empire empire, int taxRate) {
        switch (taxRate) {
            case -3:
                empire.setGoldCount(empire.getGoldCount() - empire.getPopulation());
                empire.setPopularity(empire.getPopularity() + 7);
                setPopularityFactorTax(getPopularityFactorFood() + 7);
            case -2:
                empire.setGoldCount(empire.getGoldCount() - (int) floor(empire.getPopulation() * 0.8));
                empire.setPopularity(empire.getPopularity() + 5);
                setPopularityFactorTax(getPopularityFactorFood() + 5);
            case -1:
                empire.setGoldCount(empire.getGoldCount() - (int) floor(empire.getPopulation() * 0.6));
                empire.setPopularity(empire.getPopularity() + 3);
                setPopularityFactorTax(getPopularityFactorFood() + 3);
            case 0:
                empire.setPopularity(empire.getPopularity() + 1);
                setPopularityFactorTax(getPopularityFactorFood() + 1);
            case 1:
                empire.setGoldCount(empire.getGoldCount() + (int) floor(empire.getPopulation() * 0.6));
                empire.setPopularity(empire.getPopularity() - 2);
                setPopularityFactorTax(getPopularityFactorFood() - 2);
            case 2:
                empire.setGoldCount(empire.getGoldCount() + (int) floor(empire.getPopulation() * 0.8));
                empire.setPopularity(empire.getPopularity() - 4);
                setPopularityFactorTax(getPopularityFactorFood() - 4);
            case 3:
                empire.setGoldCount(empire.getGoldCount() + empire.getPopulation());
                empire.setPopularity(empire.getPopularity() - 6);
                setPopularityFactorTax(getPopularityFactorFood() - 6);
            case 4:
                empire.setGoldCount(empire.getGoldCount() + (int) floor(empire.getPopulation() * 1.2));
                empire.setPopularity(empire.getPopularity() - 8);
                setPopularityFactorTax(getPopularityFactorFood() - 8);
            case 5:
                empire.setGoldCount(empire.getGoldCount() + (int) floor(empire.getPopulation() * 1.4));
                empire.setPopularity(empire.getPopularity() - 12);
                setPopularityFactorTax(getPopularityFactorFood() - 12);
            case 6:
                empire.setGoldCount(empire.getGoldCount() + (int) floor(empire.getPopulation() * 1.6));
                empire.setPopularity(empire.getPopularity() - 16);
                setPopularityFactorTax(getPopularityFactorFood() - 16);
            case 7:
                empire.setGoldCount(empire.getGoldCount() + (int) floor(empire.getPopulation() * 1.8));
                empire.setPopularity(empire.getPopularity() - 20);
                setPopularityFactorTax(getPopularityFactorFood() - 20);
            case 8:
                empire.setGoldCount(empire.getGoldCount() + empire.getPopulation() * 2);
                empire.setPopularity(empire.getPopularity() - 24);
                setPopularityFactorTax(getPopularityFactorFood() -24);
        }
    }

    public void independentProductionBuilding() { //part 1 of 5TYPE algorithm
        if (resourcesCount + productionBuildingRate.get("ironMine") * productionBuildingCount.get("ironMine") <= resourcesCapacity) {
            stores.replace("iron", stores.get("iron") + productionBuildingRate.get("ironMine") * productionBuildingCount.get("ironMine"));
        }
        if (resourcesCount + productionBuildingRate.get("pitchRig") * productionBuildingCount.get("pitchRig") <= resourcesCapacity) {
            stores.replace("oil", stores.get("oil") + productionBuildingRate.get("pitchRig") * productionBuildingCount.get("pitchRig"));
        }
        if (resourcesCount + productionBuildingRate.get("quarry") * productionBuildingCount.get("quarry") <= resourcesCapacity) {
            stores.replace("stone", stores.get("stone") + productionBuildingRate.get("quarry") * productionBuildingCount.get("quarry"));
        }
        if (resourcesCount + productionBuildingRate.get("woodCutter") * productionBuildingCount.get("woodCutter") <= resourcesCapacity) {
            stores.replace("wood", stores.get("wood") + productionBuildingRate.get("woodCutter") * productionBuildingCount.get("woodCutter"));
        }
        if (foodCount + productionBuildingRate.get("appleFarm") * productionBuildingCount.get("appleFarm") <= foodCapacity) {
            allFood.replace("apple", allFood.get("apple") + productionBuildingRate.get("appleFarm") * productionBuildingCount.get("appleFarm"));
        }
        if (resourcesCount + productionBuildingRate.get("oatFarm") * productionBuildingCount.get("oatFarm") <= resourcesCapacity) {
            stores.replace("oat", stores.get("oat") + productionBuildingRate.get("oatFarm") * productionBuildingCount.get("oatFarm"));
        }
        if (foodCount + productionBuildingRate.get("huntingPost") * productionBuildingCount.get("huntingPost") <= foodCapacity) {
            allFood.replace("meat", allFood.get("meat") + productionBuildingRate.get("huntingPost") * productionBuildingCount.get("huntingPost"));
        }
        if (resourcesCount + productionBuildingRate.get("wheatFactory") * productionBuildingCount.get("wheatFactory") <= resourcesCapacity) {
            stores.replace("wheat", stores.get("wheat") + productionBuildingRate.get("wheatFactory") * productionBuildingCount.get("wheatFactory"));
        }
        if (foodCount + productionBuildingRate.get("dairyFactory") * productionBuildingCount.get("dairyFactory") <= foodCapacity) {
            allFood.replace("cheese", allFood.get("cheese") + productionBuildingRate.get("dairyFactory") * productionBuildingCount.get("dairyFactory"));
        }
        if (weaponsCount + productionBuildingRate.get("dairyFactory") * productionBuildingCount.get("dairyFactory") <= weaponsCapacity) {
            allWeaponTools.replace("leatherArmour", allWeaponTools.get("leatherArmour") + productionBuildingRate.get("dairyFactory") * productionBuildingCount.get("dairyFactory"));
        }
        if (weaponsCount + productionBuildingRate.get("stable") * productionBuildingCount.get("stable") <= weaponsCapacity) {
            allWeaponTools.replace("horse", allWeaponTools.get("horse") + productionBuildingRate.get("stable") * productionBuildingCount.get("stable"));
        }
    }

    public void functionBuildings() { //part 2 of 5TYPE algorithm
        FunctionBuildingController.empire = this;
        FunctionBuildingController.transformWheatToFlour();
        FunctionBuildingController.transformFlourToBread();
        FunctionBuildingController.transformOatToBeer();
        FunctionBuildingController.transformIronToMetalArmour();
        FunctionBuildingController.transformIronToSwordOrMace();
        FunctionBuildingController.transformWoodToBow();
        FunctionBuildingController.transformWoodToSpearOrPeak();
    }

}

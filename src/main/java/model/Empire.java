package model;

import model.Human.Troop.Army;

import java.util.ArrayList;
import java.util.HashMap;


public class Empire {
    private User user;
    public int castleXCoordinate;
    public int castleYCCoordinate;
    public ArrayList<Integer> pourOilCoordinate = new ArrayList<>();
    public ArrayList<Integer> cagedWarDogsCoordinate = new ArrayList<>();
    public ArrayList<Integer> DrawBridge = new ArrayList<>();

    public User getUser() {
        return user;
    }

    public void setUser(User newUser) {
        user = newUser;
    }

    private int foodCapacity = 2500;
    private int foodCount;
    public int foodDiversity;
    private int weaponsCapacity = 100;
    private int weaponsCount;
    private int resourcesCapacity = 15000;
    private int resourcesCount;
    private int maxPossiblePopulation;
    private int PeasantCount = 50;
    private int priestCount;
    private boolean sickness;
    private int troopCount;
    private boolean apothecary;
    private int workerCount = 50;
    private int notificationOfRequest;
    private int notificationOfDonation;
    public ArrayList<Army> empireArmy = new ArrayList<>();

    public int getNotificationOfRequest() {
        return notificationOfRequest;
    }

    public void setNotificationOfRequest(int notificationOfRequest) {
        this.notificationOfRequest = notificationOfRequest;
    }

    public boolean isSickness() {
        return sickness;
    }

    public void setSickness(boolean sickness) {
        this.sickness = sickness;
    }

    public int getNotificationOfDonation() {
        return notificationOfDonation;
    }

    public void setNotificationOfDonation(int notificationOfDonation) {
        this.notificationOfDonation = notificationOfDonation;
    }

    public boolean getApothecary() {
        return apothecary;
    }

    public void setApothecary(boolean apothecary) {
        this.apothecary = apothecary;
    }

    public int getPriestCount() {
        return priestCount;
    }

    public void setPriestCount(int priestCount) {
        this.priestCount = priestCount;
    }

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

    public int getWeaponsCount() {
        return weaponsCount;
    }

    public int getFoodCapacity() {
        return foodCapacity;
    }

    public void setFoodCapacity(int foodCapacity) {
        this.foodCapacity = foodCapacity;
    }

    private int population = 10;
    public int popularity;
    private int goldCount = 100000000;
    private int foodRateNumber = -2;
    private int foodPopularityRate;
    private int taxRateNumber;

    public int getFoodDiversity() {
        return foodDiversity;
    }

    private int fearRateNumber;
    private double fearWorkerImpact;
    private double fearTroopImpact;

    public int getFoodPopularityRate() {
        return foodPopularityRate;
    }

    public void setFoodPopularityRate(int foodPopularityRate) {
        popularityFactors.replace("food", foodPopularityRate);
    }

    public double getFearWorkerImpact() {
        return fearWorkerImpact;
    }

    public void setFearWorkerImpact(double fearWorkerImpact) {
        this.fearWorkerImpact = fearWorkerImpact;
    }

    public void setFearTroopImpact(double fearTroopImpact) {
        this.fearTroopImpact = fearTroopImpact;
    }

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
        return user.getNickname();
    }

    public void setName(String name) {
        user.setNickname(name);
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getPopularity() {
        return popularityFactors.get("food") + Manage.getCurrentEmpire().getFoodDiversity() + popularityFactors.get("fear")
                + popularityFactors.get("tax") + popularityFactors.get("religious");
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
        return allFood.get("apple") + allFood.get("bread")
                + allFood.get("meat") + allFood.get("cheese");
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
        return popularityFactors.get("fear");
    }

    public void setFearRateNumber(int fearRateNumber) {
        this.fearRateNumber = fearRateNumber;
        popularityFactors.replace("fear", fearRateNumber);
    }

    public HashMap<String, Integer> getAllFood() {
        return allFood;
    }
    public int getWheatFactoryRate(){
        return productionBuildingRate.get("wheatFactory");
    }

    public int getHuntingPostRate(){
        return productionBuildingRate.get("huntingPost");
    }

    public int getOatFarmRate(){
        return productionBuildingRate.get("oatFarm");
    }

    public int getAppleFarmRate(){
        return productionBuildingRate.get("appleFarm");
    }
     public int getQuarryRate(){
        return productionBuildingRate.get("quarry");
     }

     public int getPitchRigRate(){
        return productionBuildingRate.get("pitchRig");
     }

     public int getIronMineRate(){
         return productionBuildingRate.get("ironMine");
     }

     public int getWoodCutterRate(){
         return productionBuildingRate.get("woodCutter");
     }


    public int getBeerFactoryRate() {
        return productionBuildingRate.get("beerFactory");
    }

    public int getBakeryRate() {
        return productionBuildingRate.get("bakery");
    }

    public int getMillRate() {
        return productionBuildingRate.get("mill");
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

    public int getInnCount() {
        return productionBuildingCount.get("inn");
    }

    public int getInnRate() {
        return productionBuildingRate.get("inn");
    }

    public void setInnCount(int innCount) {
        productionBuildingCount.replace("inn", innCount);
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

    public int getBlacksmithBuildingRate() {
        return createWeaponBuildingRate.get("blacksmith");
    }

    public int getFletcherBuildingRate() {
        return createWeaponBuildingRate.get("fletcher");
    }


    public int getPoleTurnerBuildingRate() {
        return createWeaponBuildingRate.get("poleTurner");
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

    public void setBeerCount(int beerCount) {
        stores.replace("beer", beerCount);
    }

    public int getBeerCount() {
        return stores.get("beer");
    }

    public int getMetalArmour() {
        return allWeaponTools.get("metalArmour");
    }

    public void setMetalArmour(int metalArmour) {
        allWeaponTools.replace("metalArmour", metalArmour);
    }

    public int getLeatherArmour() {
        return allWeaponTools.get("leatherArmour");
    }

    public void setLeatherArmour(int leatherArmour) {
        allWeaponTools.replace("leatherArmour", leatherArmour);
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

    public int getPopularityFactorTax() {
        return popularityFactors.get("tax");
    }

    public void setPopularityFactorTax(int popularityFactorTax) {
        popularityFactors.replace("tax", popularityFactorTax);
    }

    public int getPopularityFactorReligious() {
        return popularityFactors.get("religious");
    }

    public void setPopularityFactorReligious(int popularityFactor) {
        popularityFactors.replace("religious", popularityFactor);
    }

    public int getPopularityFactorFear() {
        return popularityFactors.get("fear");
    }


    public int getMeatCount() {
        return allFood.get("meat");
    }

    public int getCatapultCount() {
        return siegeTentTroopsCount.get("catapult");
    }

    public void setCatapultCount(int catapultCount) {
        siegeTentTroopsCount.replace("catapult", catapultCount);
    }

    public int getTrebuchetCount() {
        return siegeTentTroopsCount.get("trebuchet");
    }

    public void setTrebuchetCount(int trebuchetCount) {
        siegeTentTroopsCount.replace("trebuchet", trebuchetCount);
    }

    public int getSiegeTowerCount() {
        return siegeTentTroopsCount.get("siegeTower");
    }

    public void setSiegeTowerCount(int siegeTowerCount) {
        siegeTentTroopsCount.replace("siegeTower", siegeTowerCount);
    }

    public int getFireBalistaCount() {
        return siegeTentTroopsCount.get("fireBallista");
    }

    public void setFireBalistaCount(int fireBalistaCount) {
        siegeTentTroopsCount.replace("fireBallista", fireBalistaCount);
    }

    public int getBatteringRamCount() {
        return siegeTentTroopsCount.get("batteringRam");
    }

    public void setBatteringRamCount(int batteringRamCount) {
        siegeTentTroopsCount.replace("batteringRam", batteringRamCount);
    }

    public int getPortableShieldCount() {
        return siegeTentTroopsCount.get("portableShield");
    }

    public void setPortableShieldCount(int portableShieldCount) {
        siegeTentTroopsCount.replace("portableShield", portableShieldCount);
    }

    public int getAppleCount() {
        return allFood.get("apple");
    }

    public void setAppleCount(int appleCount) {
        allFood.replace("apple", appleCount);
    }

    public int getCheeseCount() {
        return allFood.get("cheese");
    }

    public void setCheeseCount(int cheeseCount) {
        allFood.replace("cheese", cheeseCount);
    }

    public void setMeatCount(int meatCount) {
        allFood.replace("meat", meatCount);
    }

    public HashMap<String, Integer> getAllWeaponTools() {
        return allWeaponTools;
    }

    public HashMap<String, Integer> getSiegeTentTroopsCount() {
        return siegeTentTroopsCount;
    }

    public HashMap<String, Integer> allFood = new HashMap<>();

    {
        allFood.put("apple", 100);
        allFood.put("bread", 100);
        allFood.put("cheese", 100);
        allFood.put("meat", 100);
    }

    public HashMap<String, Integer> allWeaponTools = new HashMap<>();

    {
        allWeaponTools.put("leatherArmour", 0);
        allWeaponTools.put("metalArmour", 0);
        allWeaponTools.put("bow", 5);
        allWeaponTools.put("sword", 0);
        allWeaponTools.put("mace", 0);
        allWeaponTools.put("spear", 0);
        allWeaponTools.put("peak", 0);
        allWeaponTools.put("horse", 0);
    }


    public HashMap<String, Integer> stores = new HashMap<>();

    {
        stores.put("wood", 50);
        stores.put("iron", 50);
        stores.put("stone", 50);
        stores.put("oat", 50);
        stores.put("wheat", 50);
        stores.put("beer", 50);
        stores.put("flour", 50);
        stores.put("oil", 50);
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
        productionBuildingCount.put("inn", 0);
        productionBuildingCount.put("beerFactory", 0);
        productionBuildingCount.put("bakery", 0);
        productionBuildingCount.put("mill", 0);
        productionBuildingCount.put("dairyFactory", 0);
        productionBuildingCount.put("wheatFarm", 0);
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
        productionBuildingRate.put("inn", 5);
    }

    public HashMap<String, Integer> createWeaponBuildingCount = new HashMap<>();

    {
        createWeaponBuildingCount.put("armourer", 0);
        createWeaponBuildingCount.put("blacksmith", 0);
        createWeaponBuildingCount.put("fletcher", 0);
        createWeaponBuildingCount.put("poleTurner", 0);
        createWeaponBuildingCount.put("stable", 0);
        createWeaponBuildingCount.put("inn", 0);

    }

    public HashMap<String, Integer> createWeaponBuildingRate = new HashMap<>();

    {
        createWeaponBuildingRate.put("armourer", 10);
        createWeaponBuildingRate.put("blacksmith", 10);
        createWeaponBuildingRate.put("fletcher", 10);
        createWeaponBuildingRate.put("poleTurner", 10);
        createWeaponBuildingRate.put("stable", 10);
    }

    public HashMap<String, Integer> europeTroopCount = new HashMap<>();

    {
        europeTroopCount.put("archer", 10);
        europeTroopCount.put("spearMan", 10);
        europeTroopCount.put("maceMan", 10);
        europeTroopCount.put("crossbowMan", 10);
        europeTroopCount.put("pikeMan", 10);
        europeTroopCount.put("swordMan", 10);
        europeTroopCount.put("knight", 10);
        europeTroopCount.put("blackMonk", 10);
    }

    public HashMap<String, Integer> arabTroopCount = new HashMap<>();

    {
        arabTroopCount.put("arabianBow", 10);
        arabTroopCount.put("slave", 10);
        arabTroopCount.put("slinger", 10);
        arabTroopCount.put("assassin", 10);
        arabTroopCount.put("horseArcher", 10);
        arabTroopCount.put("arabianSwordMan", 10);
        arabTroopCount.put("fireThrower", 10);
    }

    public HashMap<String, Integer> engineerGuildTroopCount = new HashMap<>();

    {
        engineerGuildTroopCount.put("engineer", 10);
        engineerGuildTroopCount.put("ladderMan", 10);
        engineerGuildTroopCount.put("tunneler", 10);
    }

    public HashMap<String, Integer> popularityFactors = new HashMap<>();

    {
        popularityFactors.put("food", -2);
        popularityFactors.put("tax", 1);
        popularityFactors.put("religious", 0);
        popularityFactors.put("fear", 0);
    }

    public HashMap<String, Integer> siegeTentTroopsCount = new HashMap<>();

    {
        siegeTentTroopsCount.put("catapult",10);
        siegeTentTroopsCount.put("trebuchet", 10);
        siegeTentTroopsCount.put("siegeTower", 10);
        siegeTentTroopsCount.put("fireBallista", 10);
        siegeTentTroopsCount.put("batteringRam", 10);
        siegeTentTroopsCount.put("portableShield", 10);
    }

    public ArrayList<Request> allRequests = new ArrayList<>();

    public ArrayList<Request> getAllRequests() {
        return allRequests;
    }

    public ArrayList<Request> allDonations = new ArrayList<>();

    public ArrayList<Request> getAllDonations() {
        return allDonations;
    }

    public void independentProductionBuilding() {
        System.out.println(this.getWoodCount());
        if (resourcesCount + productionBuildingRate.get("ironMine") * productionBuildingCount.get("ironMine") * fearWorkerImpact <= resourcesCapacity) {
            stores.replace("iron", (int) (stores.get("iron") + productionBuildingRate.get("ironMine") * productionBuildingCount.get("ironMine") * fearWorkerImpact));
            resourcesCount += (int) (stores.get("iron") + productionBuildingRate.get("ironMine") * productionBuildingCount.get("ironMine") * fearWorkerImpact);
        }
        if (resourcesCount + productionBuildingRate.get("pitchRig") * productionBuildingCount.get("pitchRig") * fearWorkerImpact <= resourcesCapacity) {
            stores.replace("oil", (int) (stores.get("oil") + productionBuildingRate.get("pitchRig") * productionBuildingCount.get("pitchRig") * fearWorkerImpact));
            resourcesCount += (int) (stores.get("oil") + productionBuildingRate.get("pitchRig") * productionBuildingCount.get("pitchRig") * fearWorkerImpact);
        }
        if (resourcesCount + productionBuildingRate.get("quarry") * productionBuildingCount.get("quarry") * fearWorkerImpact <= resourcesCapacity) {
            stores.replace("stone", (int) (stores.get("stone") + productionBuildingRate.get("quarry") * productionBuildingCount.get("quarry") * fearWorkerImpact));
            resourcesCount += (int) (stores.get("stone") + productionBuildingRate.get("quarry") * productionBuildingCount.get("quarry") * fearWorkerImpact);
        }
        if (resourcesCount + productionBuildingRate.get("woodCutter") * productionBuildingCount.get("woodCutter") * fearWorkerImpact <= resourcesCapacity) {
            stores.replace("wood", (int) (stores.get("wood") + productionBuildingRate.get("woodCutter") * productionBuildingCount.get("woodCutter") * fearWorkerImpact));
            resourcesCount += (int) (stores.get("wood") + productionBuildingRate.get("woodCutter") * productionBuildingCount.get("woodCutter") * fearWorkerImpact);
        }
        if (foodCount + productionBuildingRate.get("appleFarm") * productionBuildingCount.get("appleFarm") * fearWorkerImpact <= foodCapacity) {
            allFood.replace("apple", (int) (allFood.get("apple") + productionBuildingRate.get("appleFarm") * productionBuildingCount.get("appleFarm") * fearWorkerImpact));
            foodCount += (int) (allFood.get("apple") + productionBuildingRate.get("appleFarm") * productionBuildingCount.get("appleFarm") * fearWorkerImpact);
        }
        if (resourcesCount + productionBuildingRate.get("oatFarm") * productionBuildingCount.get("oatFarm") * fearWorkerImpact <= resourcesCapacity) {
            stores.replace("oat", (int) (stores.get("oat") + productionBuildingRate.get("oatFarm") * productionBuildingCount.get("oatFarm") * fearWorkerImpact));
            resourcesCount += (int) (stores.get("oat") + productionBuildingRate.get("oatFarm") * productionBuildingCount.get("oatFarm") * fearWorkerImpact);
        }
        if (foodCount + productionBuildingRate.get("huntingPost") * productionBuildingCount.get("huntingPost") * fearWorkerImpact <= foodCapacity) {
            allFood.replace("meat", (int) (allFood.get("meat") + productionBuildingRate.get("huntingPost") * productionBuildingCount.get("huntingPost") * fearWorkerImpact));
            foodCount += (int) (allFood.get("meat") + productionBuildingRate.get("huntingPost") * productionBuildingCount.get("huntingPost") * fearWorkerImpact);
        }
        if (resourcesCount + productionBuildingRate.get("wheatFactory") * productionBuildingCount.get("wheatFactory") * fearWorkerImpact <= resourcesCapacity) {
            stores.replace("wheat", (int) (stores.get("wheat") + productionBuildingRate.get("wheatFactory") * productionBuildingCount.get("wheatFactory") * fearWorkerImpact));
            resourcesCount += (int) (stores.get("wheat") + productionBuildingRate.get("wheatFactory") * productionBuildingCount.get("wheatFactory") * fearWorkerImpact);
        }
        if (foodCount + productionBuildingRate.get("dairyFactory") * productionBuildingCount.get("dairyFactory") * fearWorkerImpact <= foodCapacity) {
            allFood.replace("cheese", (int) (allFood.get("cheese") + productionBuildingRate.get("dairyFactory") * productionBuildingCount.get("dairyFactory") * fearWorkerImpact));
            foodCount += (int) (allFood.get("cheese") + productionBuildingRate.get("dairyFactory") * productionBuildingCount.get("dairyFactory") * fearWorkerImpact);
        }
        if (weaponsCount + productionBuildingRate.get("dairyFactory") * productionBuildingCount.get("dairyFactory") * fearWorkerImpact <= weaponsCapacity) {
            allWeaponTools.replace("leatherArmour", (int) (allWeaponTools.get("leatherArmour") + productionBuildingRate.get("dairyFactory") * productionBuildingCount.get("dairyFactory") * fearWorkerImpact));
            weaponsCount += (int) (allWeaponTools.get("leatherArmour") + productionBuildingRate.get("dairyFactory") * productionBuildingCount.get("dairyFactory") * fearWorkerImpact);
        }
        if (weaponsCount + createWeaponBuildingRate.get("stable") * createWeaponBuildingCount.get("stable") * fearWorkerImpact <= weaponsCapacity) {
            allWeaponTools.replace("horse", (int) (allWeaponTools.get("horse") + createWeaponBuildingRate.get("stable") * createWeaponBuildingCount.get("stable") * fearWorkerImpact));
            weaponsCapacity += (int) (allWeaponTools.get("horse") + createWeaponBuildingRate.get("stable") * createWeaponBuildingCount.get("stable") * fearWorkerImpact);
        }
    }
}
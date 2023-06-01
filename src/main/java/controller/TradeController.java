package controller;

import model.*;

import java.util.regex.Matcher;

import view.Messages.TradeMenuMessages;
import model.TradeAbleGoods;

public class TradeController {
    public static Empire selectedEmpire;
    public static Empire currentEmpire ;

    public void showAllEmpires() {
        int number = 1;
        System.out.println("List of all empires :");
        for (Empire empire : Manage.getAllEmpires()) {
            System.out.println(number + ". " + empire.getName());
            number++;
        }
    }

    public TradeMenuMessages sendRequest(String goodType, int amount, String messageOfRequest) {
        String[] values = messageOfRequest.split(" ");
        String valueOfRequest = values[0];
        int price = Integer.parseInt(values[1]);
        if (selectedEmpire != null) {
            if (typeOfResources(goodType)) {
                if (getNumberOfGoods(valueOfRequest,currentEmpire) >= price) {
                    if (amount > 0 && checkTheCapacity(amount, goodType, currentEmpire)) {
                        String id = idProvider(currentEmpire, currentEmpire.getAllRequests().size() + 1);
                        Request request = new Request(messageOfRequest, price, amount, goodType, id, currentEmpire, selectedEmpire,valueOfRequest);
                        request.setStatus("Not accepted yet!");
                        currentEmpire.getAllRequests().add(request);
                        selectedEmpire.getAllDonations().add(request);
                        return TradeMenuMessages.REQUEST_SENT_SUCCESSFULLY;
                    } else return TradeMenuMessages.INVALID_AMOUNT;
                } else return TradeMenuMessages.INVALID_PRICE;
            } else return TradeMenuMessages.INVALID_RESOURCE_TYPE;
        }
        return TradeMenuMessages.NO_EMPIRE_HAS_BEEN_CHOSEN;
    }

    public String idProvider(Empire empire, int number) {
        return empire.getName().concat(String.valueOf(number));
    }


    public void showDonations() {
        int number = 1;
        System.out.println("Donation List :");
        for (Request donation : currentEmpire.getAllDonations()) {
            System.out.println("\t" + number + ".Sender: " + donation.getSender().getName() + "\n\t\s\s\sId: " + donation.getId() + "\n\t\s\s\sStatus :" + donation.getStatus()
                    +"\n\t\s\s\sReceiver: "+donation.getReceiver().getName()+"\n\t\s\s\sMessage: "+donation.getMessage());
            number++;
        }
    }

    public void showRequests() {
        int number = 1;
        System.out.println("Request List :");
        for (Request request : currentEmpire.getAllRequests()) {
            System.out.println("\t" + number + ".Sender: " + request.getSender().getName() + "\n\t\s\s\sId: " + request.getId() + "\n\t\s\s\sStatus :" + request.getStatus()
                    +"\n\t\s\s\sReceiver: "+request.getReceiver().getName()+"\n\t\s\s\sMessage: "+request.getMessage());
            number++;
        }
    }

    public void showTradeList() {
        showRequests();
        showDonations();
    }

    public TradeMenuMessages tradeAcceptance(Matcher idOfRequest, Matcher messageOfRequest) {
        Request request;
        String id = idOfRequest.group("id");
        String message = messageOfRequest.group("tradeMessage");
        if ((request = findDonation(id, currentEmpire)) != null) {
            String goodName = request.getGoodName();
            int amount = request.getAmount();
            int price = request.getPrice();
            String tradableThing = request.tradableThing;
            Empire empire = request.getSender();
            if (Manage.getEmpireByNickname(empire.getName()) != null) {
                if (getNumberOfGoods(goodName, currentEmpire) >= amount) {
                    Request request1 = findRequest(id, empire);
                    request1.setFromSellerMessage(message);
                    request.setFromSellerMessage(message);
                    setNumberOfGoods(currentEmpire,empire,price,tradableThing);
                    request1.setAcceptance(true);
                    request.setAcceptance(true);
                    setNumberOfGoods(empire, currentEmpire, amount, goodName);
                    request.setStatus("Accepted!");
                    request1.setStatus("Accepted!");
                    return TradeMenuMessages.SUCCESS;
                } else return TradeMenuMessages.NOT_ENOUGH_RESOURCES;
            } else return TradeMenuMessages.INVALID_EMPIRE;
        }
        return TradeMenuMessages.NO_DONATION;
    }

    public boolean typeOfResources(String typeOfResource) {
        for (TradeAbleGoods tradeAbleGoods : TradeAbleGoods.values()) {
            if (tradeAbleGoods.getGoodName().equals(typeOfResource)) {
                return true;
            }
        }
        return false;
    }

    public Request findRequest(String id, Empire empire) {
        for (Request request : empire.getAllRequests()) {
            if (request.getId().equals(id)) {
                return request;
            }
        }
        return null;
    }

    public Request findDonation(String id, Empire empire) {
        for (Request request : empire.getAllDonations()) {
            if (request.getId().equals(id)) {
                return request;
            }
        }
        return null;
    }

    public boolean checkTheCapacity(int count, String requestedGood, Empire empire) {
        return switch (requestedGood) {
            case "meat", "apple", "cheese" ->
                    count + EmpireController.calculateTotalFoodCount() <= empire.getFoodCapacity();
            case "hops", "flour", "wheat", "stone", "wood", "oil", "iron", "beer" ->
                    count + EmpireController.calculateTotalResourcesCount() <= empire.getResourcesCapacity();
            case "ironArmor", "leatherArmor", "mace", "bow", "sword", "horse" ->
                    count + EmpireController.calculateTotalFightStuffCount() <= empire.getWeaponsCapacity();
            case "archer", "spearMan", "maceMan", "crossbowMan", "pikeMan", "swordMan", "blackMonk",
                    "knight", "arabianBow", "slave", "slinger", "assassin", "horseArcher", "arabianSwordMan", "fireThrower", "engineer", "ladderMan"
                    , "tunneler", "catapult", "trebuchet", "siegeTower", "fireBallista", "batteringRam", "portableShield" ->
                    count <= empire.getPeasantCount();
            default -> false;

        };
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
            case "beer" -> empire.getBeerCount();
            case "horse" -> empire.getHorseCount();
            case "archer" -> empire.getEuropeArcherCount();
            case "spearMan" -> empire.getSpearManCount();
            case "maceMan" -> empire.getMaceManCount();
            case "crossbowMan" -> empire.getCrossbowManCount();
            case "pikeMan" -> empire.getPikeManCount();
            case "swordMan" -> empire.getSwordManCount();
            case "blackMonk" -> empire.getBlackMonkCount();
            case "knight" -> empire.getKnightCount();
            case "arabianBow" -> empire.getArabianBowCount();
            case "slave" -> empire.getSlaveCount();
            case "slinger" -> empire.getSlingerCount();
            case "assassin" -> empire.getAssassinCount();
            case "horseArcher" -> empire.getHorseArcherCount();
            case "arabianSwordMan" -> empire.getArabianSwordManCount();
            case "fireThrower" -> empire.getFireThrowerCount();
            case "engineer" -> empire.getEngineerCount();
            case "ladderMan" -> empire.getLadderManCount();
            case "tunneler" -> empire.getTunnelerCount();
            case "catapult" -> empire.getCatapultCount();
            case "trebuchet" -> empire.getTrebuchetCount();
            case "siegeTower" -> empire.getSiegeTowerCount();
            case "fireBallista" -> empire.getFireBalistaCount();
            case "batteringRam" -> empire.getBatteringRamCount();
            case "portableShield" -> empire.getPortableShieldCount();
            default -> 0;
        };
    }

    public void setNumberOfGoods(Empire customer, Empire seller, int count, String goodName) {
        switch (goodName) {
            case "meat" -> {
                customer.setMeatCount(customer.getMeatCount() + count);
                seller.setMeatCount(seller.getMeatCount() - count);
            }
            case "hops" -> {
                customer.setOatCount(customer.getOatCount() + count);
                seller.setOatCount(seller.getOatCount() - count);
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
            case "bow" -> {
                customer.setBowCount(customer.getBowCount() + count);
                seller.setBowCount(seller.getBowCount() - count);
            }
            case "mace" -> {
                customer.setMaceCount(customer.getMaceCount() + count);
                seller.setMaceCount(seller.getMaceCount() - count);
            }
            case "oil" -> {
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
            case "apple" -> {
                customer.setAppleCount(customer.getAppleCount() + count);
                seller.setAppleCount(seller.getAppleCount() - count);
            }
            case "cheese" -> {
                customer.setCheeseCount(customer.getCheeseCount() + count);
                seller.setCheeseCount(seller.getCheeseCount() - count);
            }
            case "beer" -> {
                customer.setBeerCount(customer.getBeerCount() + count);
                seller.setBeerCount(seller.getBeerCount() - count);
            }
            case "horse" -> {
                customer.setHorseCount(customer.getHorseCount() + count);
                seller.setHorseCount(seller.getHorseCount() - count);
            }
            case "archer" -> {
                customer.setEuropeArcherCount(customer.getEuropeArcherCount() + count);
                seller.setEuropeArcherCount(seller.getEuropeArcherCount() - count);
            }
            case "spearMan" -> {
                customer.setSpearManCount(customer.getSpearManCount() + count);
                seller.setSpearManCount(seller.getSpearManCount() - count);
            }
            case "maceMan" -> {
                customer.setMaceManCount(customer.getMaceManCount() + count);
                seller.setMaceManCount(seller.getMaceManCount() - count);
            }
            case "crossbowMan" -> {
                customer.setCrossbowManCount(customer.getCrossbowManCount() + count);
                seller.setCrossbowManCount(seller.getCrossbowManCount() - count);
            }
            case "pikeMan" -> {
                customer.setPikeManCount(customer.getPikeManCount() + count);
                seller.setPikeManCount(seller.getPikeManCount() - count);
            }
            case "swordMan" -> {
                customer.setSwordManCount(customer.getSwordManCount() + count);
                seller.setSwordManCount(seller.getSwordManCount() - count);
            }
            case "blackMonk" -> {
                customer.setBlackMonkCount(customer.getBlackMonkCount() + count);
                seller.setBlackMonkCount(seller.getBlackMonkCount() - count);
            }
            case "knight" -> {
                customer.setKnightCount(customer.getKnightCount() + count);
                seller.setKnightCount(seller.getKnightCount() - count);
            }
            case "arabianBow" -> {
                customer.setArabianBowCount(customer.getArabianBowCount() + count);
                seller.setArabianBowCount(seller.getArabianBowCount() - count);
            }
            case "slave" -> {
                customer.setSlaveCount(customer.getSlaveCount() + count);
                seller.setSlaveCount(seller.getSlaveCount() - count);
            }
            case "slinger" -> {
                customer.setSlingerCount(customer.getSlingerCount() + count);
                seller.setSlingerCount(seller.getSlingerCount() - count);
            }
            case "assassin" -> {
                customer.setAssassinCount(customer.getAssassinCount() + count);
                seller.setAssassinCount(seller.getAssassinCount() - count);
            }
            case "horseArcher" -> {
                customer.setHorseArcherCount(customer.getHorseArcherCount() + count);
                seller.setHorseArcherCount(seller.getHorseArcherCount() - count);
            }
            case "arabianSwordMan" -> {
                customer.setArabianSwordManCount(customer.getArabianSwordManCount() + count);
                seller.setArabianSwordManCount(seller.getArabianSwordManCount() - count);
            }
            case "fireThrower" -> {
                customer.setFireThrowerCount(customer.getFireThrowerCount() + count);
                seller.setFireThrowerCount(seller.getFireThrowerCount() - count);
            }
            case "engineer" -> {
                customer.setEngineerCount(customer.getEngineerCount() + count);
                seller.setEngineerCount(seller.getEngineerCount() - count);
            }
            case "ladderMan" -> {
                customer.setLadderManCount(customer.getLadderManCount() + count);
                seller.setLadderManCount(seller.getLadderManCount() - count);
            }
            case "tunneler" -> {
                customer.setTunnelerCount(customer.getTunnelerCount() + count);
                seller.setTunnelerCount(seller.getTunnelerCount() - count);
            }
            case "catapult" -> {
                customer.setCatapultCount(customer.getCatapultCount() + count);
                seller.setCatapultCount(seller.getCatapultCount() - count);
            }
            case "trebuchet" -> {
                customer.setTrebuchetCount(customer.getTrebuchetCount() + count);
                seller.setTrebuchetCount(seller.getTrebuchetCount() - count);
            }
            case "siegeTower" -> {
                customer.setSiegeTowerCount(customer.getSiegeTowerCount() + count);
                seller.setSiegeTowerCount(seller.getSiegeTowerCount() - count);
            }
            case "fireBallista" -> {
                customer.setFireBalistaCount(customer.getFireBalistaCount() + count);
                seller.setFireBalistaCount(seller.getFireBalistaCount() - count);
            }
            case "batteringRam" -> {
                customer.setBatteringRamCount(customer.getBatteringRamCount() + count);
                seller.setBatteringRamCount(seller.getBatteringRamCount() - count);
            }
            case "portableShield" -> {
                customer.setPortableShieldCount(customer.getPortableShieldCount() + count);
                seller.setPortableShieldCount(seller.getPortableShieldCount() - count);
            }
        }
    }

    public void showTradeHistory() {
        int number = 1;
        System.out.println("List of Accepted Requests:");
        for (Request request : currentEmpire.getAllRequests()) {
            if (request.isAcceptance()) {
                System.out.println("\t" + number + ". Sender: " + request.getSender());
                System.out.println("\t\s\sGood : " + request.getGoodName());
                System.out.println("\t\s\sAmount: " + request.getAmount());
                System.out.println("\t\s\sPrice: " + request.getPrice());
                System.out.println("\t\s\sCustomer Message : " + request.getMessage());
                System.out.println("\t\s\sSeller Message : " + request.getFromSellerMessage());
                number++;
            }
        }
        System.out.println("List of Accepted Donations:");
        for (Request request : currentEmpire.getAllDonations()) {
            if (request.isAcceptance()) {
                System.out.println("\t" + number + ". Sender: " + request.getSender().getName());
                System.out.println("\t\s\sGood : " + request.getGoodName());
                System.out.println("\t\s\sAmount: " + request.getAmount());
                System.out.println("\t\s\sPrice: " + request.getPrice());
                System.out.println("\t\s\sCustomer Message : " + request.getMessage());
                System.out.println("\t\s\sSeller Message : " + request.getFromSellerMessage());
                number++;
            }
        }
    }

    public void notification() {
        int number = 1;
        System.out.println("Notifications : ");
        System.out.println("List Of New Requests : ");
        for (int j = currentEmpire.getNotificationOfRequest(); j < currentEmpire.getAllRequests().size(); j++) {
            System.out.println(number+".Id: "+currentEmpire.getAllRequests().get(j).getId());
            System.out.println("Message: "+currentEmpire.getAllRequests().get(j).getMessage());
            number++;
        }
        number = 1;
        System.out.println("List Of New Donations : ");
        for (int i = currentEmpire.getNotificationOfDonation(); i < currentEmpire.getAllDonations().size(); i++) {
            System.out.println(number+".Id: "+currentEmpire.getAllDonations().get(i).getId());
            System.out.println("Message: "+currentEmpire.getAllDonations().get(i).getMessage());
        }
        currentEmpire.setNotificationOfDonation(currentEmpire.getAllDonations().size());
        currentEmpire.setNotificationOfRequest(currentEmpire.getAllRequests().size());
    }

    public boolean enoughMoneyToBuy(Empire empire, int price) {
        return empire.getGoldCount() >= price && price > 0;
    }
}

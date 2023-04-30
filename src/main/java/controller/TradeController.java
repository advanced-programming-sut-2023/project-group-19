package controller;

import model.*;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import view.Commands.TradeMenuCommands;
import view.Messages.TradeMenuMessages;
import model.TradableGoods;

public class TradeController {
    public static String currentMenu;
    public static Empire selectedEmpire=null;
    public void showAllEmpires(Scanner scanner){
        int number=1;
        System.out.println("List of all empires :");
        for (Empire empire : Manage.getAllEmpires()){
            System.out.println(number+". "+empire.getName());
            number++;
        }
        /*System.out.println("Enter the number of your considered empire : ");
        String numberOfChosenEmpire=scanner.nextLine();
        if (TradeMenuCommands.getMatcher(numberOfChosenEmpire , TradeMenuCommands.FORMAT_OF_NUMBER_FOR_SELECTED_BUILDING) != null) {
            int chosenEmpire = Integer.parseInt(numberOfChosenEmpire);
            if (chosenEmpire < 1 || chosenEmpire > number) System.out.println("The number you've entered is invalid!");
            else setSelectedEmpire(chosenEmpire);
        }else System.out.println(TradeMenuMessages.INVALID_ANSWER_FOR_SELECTED_EMPIRE.getMessages());*/
    }

    public void showRequests(){
        int number=1;
        System.out.println("Request List :");
        for (String request : Manage.getCurrentEmpire().getRequest()){
            System.out.println(number+". "+request);
            number++;
        }
    }

    public TradeMenuMessages sendRequest(Matcher resourceType , Matcher resourceAmount , Matcher price , Matcher message){
        String typeOfResource= resourceType.group("resourceType");
        int amountOfResource=Integer.parseInt(resourceAmount.group("resourceAmount"));
        int cost=Integer.parseInt(price.group("resourcePrice"));
        String messageAttachedToRequest=message.group("resourceMessage");
        if (selectedEmpire != null) {
            if (typeOfResources(typeOfResource) != null) {
                if (amountOfResource > 0) {
                    if (cost >= 0) {
                        if (messageAttachedToRequest != null) {
                            String request = "Sender: " + Manage.getCurrentEmpire() + "\n" +
                                    "Requested resource: " + typeOfResource + "\n" +
                                    "Amount of resource: " + amountOfResource + "\n" +
                                    "Attached Message: " + messageAttachedToRequest + "\n" +
                                    "Status: Not Accepted Yet!";
                            if (cost == 0) selectedEmpire.getDonation().add(request);
                            else selectedEmpire.getRequest().add(request);
                            System.out.println("Your request is sent to " + selectedEmpire + " court!");
                            selectedEmpire = null;
                        } else return TradeMenuMessages.EMPTY_MESSAGE_PART;
                    } else return TradeMenuMessages.INVALID_PRICE;
                } else return TradeMenuMessages.INVALID_AMOUNT;
            } else return TradeMenuMessages.INVALID_RESOURCE_TYPE;
        }return TradeMenuMessages.NO_EMPIRE_HAS_BEEN_CHOSEN;
    }

    public void setSelectedEmpire(int number){
        for (int i = 0 ; i < Manage.getAllEmpires().size() ; i++){
            if (i==number-1){
                selectedEmpire = Manage.getAllEmpires().get(i);
                break;
            }
        }
    }
    public void showTradeList(){
        showRequests();
        showDonations();
    }
    public void showDonations(){
        int number = 1 ;
        System.out.println("Donation List :");
        for (String donation : Manage.getCurrentEmpire().getDonation()){
            System.out.println(number+". "+donation);
            number++;
        }
    }
    public TradeMenuMessages tradeAcceptance(Matcher id , Matcher message){
        Matcher matcher;
        String request;
        String donation;
        String idOfEmpire = id.group("id");
        String messageFromEmpire = message.group("tradeMessage");
        Empire empire=Manage.getEmpireByNickname(idOfEmpire);
        if (empire!=null) {
            if (messageFromEmpire != null) {
                if ((request=findRequest(idOfEmpire, messageFromEmpire, empire)) != null) {
                    matcher=TradeMenuCommands.getMatcher(request , TradeMenuCommands.RESOURCE_AMOUNT_OF_REQUEST);
                    int amount=Integer.parseInt(matcher.group("amount"));
                    matcher=TradeMenuCommands.getMatcher(request , TradeMenuCommands.RESOURCE_TYPE);
                    String requestResource=matcher.group("type");
                    if (getResourceCount(requestResource)!=-1) {
                        if (empire.getResourcesCount() + amount <= empire.getResourcesCapacity()) {
                            setResourceCount(requestResource , amount , idOfEmpire);
                            setResourceCount(requestResource , amount , Manage.getCurrentEmpire().getName());
                            StringTokenizer stringToken = new StringTokenizer(request , "Status");
                            request = stringToken.nextToken();
                            request=request.concat("Status: Accepted");
                            return TradeMenuMessages.SUCCESS;
                        }
                    }
                } else if ((donation=findDonation(idOfEmpire, messageFromEmpire, empire) )!= null) {
                    matcher=TradeMenuCommands.getMatcher(donation , TradeMenuCommands.RESOURCE_AMOUNT_OF_REQUEST);
                    int amount=Integer.parseInt(matcher.group("amount"));
                    matcher=TradeMenuCommands.getMatcher(donation , TradeMenuCommands.RESOURCE_TYPE);
                    String requestResource=matcher.group("type");
                    if (getResourceCount(requestResource)!=-1 && getResourceCount(requestResource) >= amount) {
                        if (empire.getResourcesCount() + amount <= empire.getResourcesCapacity()) {
                            setResourceCount(requestResource , amount , idOfEmpire);
                            setResourceCount(requestResource , amount , Manage.getCurrentEmpire().getName());
                            return TradeMenuMessages.SUCCESS;
                        }else return TradeMenuMessages.NOT_ENOUGH_SPACE;
                    }else return TradeMenuMessages.NOT_ENOUGH_RESOURCES;
                }else return TradeMenuMessages.NO_REQUEST_OR_DONATION;
            }else return TradeMenuMessages.EMPTY_MESSAGE_PART;
        }else return TradeMenuMessages.INVALID_EMPIRE;
        return null;
    }

    public String typeOfResources(String typeOfResource){
        for (TradableGoods tradableGoods : TradableGoods.values()){
            if (tradableGoods.getGoodName().equals(typeOfResource)){
                return tradableGoods.getGoodName();
            }
        }
        return null;
    }
    public String findRequest(String id , String message , Empire empire){
        Matcher matcher;
        Matcher matcherForMessage;
        for (int i = 0 ; i < empire.getRequest().size() ; i++){
            matcher=TradeMenuCommands.getMatcher(empire.getRequest().get(i),TradeMenuCommands.SENDER_OF_REQUEST);
            matcherForMessage=TradeMenuCommands.getMatcher(empire.getDonation().get(i),TradeMenuCommands.MESSAGE_OF_REQUEST_OR_DONATION);
            if (matcher!=null && matcher.group("sender").equals(id)
                    && matcherForMessage!=null && matcherForMessage.group("message").equals(message)){
                return empire.getRequest().get(i);
            }
        }
        return null;
    }

    public String findDonation(String id , String message , Empire empire){
        Matcher matcherForId;
        Matcher matcherForMessage;
        for (int i = 0 ; i < empire.getDonation().size() ; i++){
            matcherForId=TradeMenuCommands.getMatcher(empire.getDonation().get(i),TradeMenuCommands.SENDER_OF_REQUEST);
            matcherForMessage=TradeMenuCommands.getMatcher(empire.getDonation().get(i),TradeMenuCommands.MESSAGE_OF_REQUEST_OR_DONATION);
            if (matcherForId!=null && matcherForId.group("sender").equals(id)
                && matcherForMessage!=null && matcherForMessage.group("message").equals(message)){
                return empire.getDonation().get(i);
            }
        }
        return null;
    }

    public int getResourceCount(String resource){
        for (Map.Entry<String , Integer> food : Manage.getCurrentEmpire().getAllFood().entrySet()){
            if (food.getKey().equals(resource)){
                return food.getValue();
            }
        }
        for (Map.Entry<String , Integer> weaponTool : Manage.getCurrentEmpire().getAllWeaponTools().entrySet()){
            if (weaponTool.getKey().equals(resource)){
                return weaponTool.getValue();
            }
        }
        for (Map.Entry<String , Integer> store : Manage.getCurrentEmpire().getStores().entrySet()){
            if (store.getKey().equals(resource)){
                return store.getValue();
            }
        }
        for (Map.Entry<String , Integer> europeTroop : Manage.getCurrentEmpire().getEuropeTroopCount().entrySet()){
            if (europeTroop.getKey().equals(resource)){
                return europeTroop.getValue();
            }
        }
        for (Map.Entry<String , Integer> arabTroop : Manage.getCurrentEmpire().getArabTroopCount().entrySet()){
            if (arabTroop.getKey().equals(resource)){
                return arabTroop.getValue();
            }
        }
        for (Map.Entry<String , Integer> engineerGuild : Manage.getCurrentEmpire().getEngineerGuildTroopCount().entrySet()){
            if (engineerGuild.getKey().equals(resource)){
                return engineerGuild.getValue();
            }
        }
        for (Map.Entry<String , Integer> siegeTentTroop : Manage.getCurrentEmpire().getSiegeTentTroopsCount().entrySet()){
            if (siegeTentTroop.getKey().equals(resource)){
                return siegeTentTroop.getValue();
            }
        }
        return -1;
    }
    public void setResourceCount(String resource , int count , String empireName){
        Empire empire = Manage.getEmpireByNickname(empireName);
        if (empireName.equals(Manage.getCurrentEmpire().getName())) count=-1 * count;
        for (Map.Entry<String , Integer> food : empire.getAllFood().entrySet()){
            if (food.getKey().equals(resource)){
                Manage.getCurrentEmpire().getAllFood().replace(resource , food.getValue()+count);
            }
        }
        for (Map.Entry<String , Integer> weaponTool : empire.getAllWeaponTools().entrySet()){
            if (weaponTool.getKey().equals(resource)){
                Manage.getCurrentEmpire().getAllWeaponTools().replace(resource , weaponTool.getValue()+count);
            }
        }
        for (Map.Entry<String , Integer> store : empire.getStores().entrySet()){
            if (store.getKey().equals(resource)){
                Manage.getCurrentEmpire().getStores().replace(resource , store.getValue()+count);
            }
        }
        for (Map.Entry<String , Integer> europeTroop : empire.getEuropeTroopCount().entrySet()){
            if (europeTroop.getKey().equals(resource)){
                Manage.getCurrentEmpire().getEuropeTroopCount().replace(resource , europeTroop.getValue()+count);
            }
        }
        for (Map.Entry<String , Integer> arabTroop : empire.getArabTroopCount().entrySet()){
            if (arabTroop.getKey().equals(resource)){
                Manage.getCurrentEmpire().getArabTroopCount().replace(resource , arabTroop.getValue()+count);
            }
        }
        for (Map.Entry<String , Integer> engineerGuild : empire.getEngineerGuildTroopCount().entrySet()){
            if (engineerGuild.getKey().equals(resource)){
                Manage.getCurrentEmpire().getEngineerGuildTroopCount().replace(resource , engineerGuild.getValue()+count);
            }
        }
        for (Map.Entry<String , Integer> siegeTentTroop : empire.getSiegeTentTroopsCount().entrySet()){
            if (siegeTentTroop.getKey().equals(resource)){
                Manage.getCurrentEmpire().getSiegeTentTroopsCount().replace(resource , siegeTentTroop.getValue()+count);
            }
        }
    }
    public void showTradeHistory(){

    }
}

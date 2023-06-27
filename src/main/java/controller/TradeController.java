package controller;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

import java.awt.*;
import java.util.Optional;
import java.util.regex.Matcher;

import view.ListOfReceivedRequestsMenu;
import view.Messages.TradeMenuMessages;
import model.TradeAbleGoods;

public class TradeController {
    public Empire selectedEmpire;
    public void sendRequest(String goodType, int amount, String messageOfRequest) {
        String[] values = messageOfRequest.split(" ");
        if (values.length < 2) {
            Alert OutOfSize = new Alert(Alert.AlertType.ERROR);
            OutOfSize.setTitle("TradeMenu Error");
            OutOfSize.setHeaderText("Error in format of Input");
            OutOfSize.setContentText("The format of your input is wrong.\nThe Format is GoodName + Number or " +
                    "Number + GoodName.");
            OutOfSize.showAndWait();
        } else {
            String valueOfRequest = values[0];
            int price = Integer.parseInt(values[1]);
            if (selectedEmpire != null && !selectedEmpire.getName().equals(Manage.getCurrentEmpire().getName())) {
                if (typeOfResources(goodType)) {
                    if (getNumberOfGoods(valueOfRequest, Manage.getCurrentEmpire()) >= price) {
                        if (amount > 0 && checkTheCapacity(amount, goodType, Manage.getCurrentEmpire())) {
                            String id = idProvider(Manage.getCurrentEmpire(), Manage.getCurrentEmpire().getAllRequests().size() + 1);
                            Request request = new Request(messageOfRequest, price, amount, goodType, id, Manage.getCurrentEmpire(), selectedEmpire, valueOfRequest);
                            request.setStatus("Not accepted yet!");
                            Manage.getCurrentEmpire().getAllRequests().add(request);
                            selectedEmpire.getAllDonations().add(request);
                            Alert success = new Alert(Alert.AlertType.INFORMATION);
                            success.setTitle("TradeMenu Information");
                            success.setHeaderText("Success!");
                            success.setContentText("Your request is sent to the court of chosen Empire My Lord.\n" + "" +
                                    "They will answer to you as soon as they receive your request.");
                            success.showAndWait();
                        } else {
                            Alert notEnoughCapacity = new Alert(Alert.AlertType.ERROR);
                            notEnoughCapacity.setTitle("TradeMenu Error");
                            notEnoughCapacity.setHeaderText("Error in Treasury Capacity");
                            notEnoughCapacity.setContentText("""
                                    Invalid amount!
                                    Possible reasons:
                                    1.The given amount is invalid!
                                    2.Your treasury can't save this amount of good!""");
                            notEnoughCapacity.showAndWait();
                        }
                    } else {
                        Alert notEnoughTradingObj = new Alert(Alert.AlertType.ERROR);
                        notEnoughTradingObj.setTitle("TradeMenu Error");
                        notEnoughTradingObj.setHeaderText("Error in amount of Good");
                        notEnoughTradingObj.setContentText("I'm afraid you don't have the entered amount of good to " +
                                "trade with selected Empire");
                        notEnoughTradingObj.showAndWait();
                    }
                } else {
                    Alert noChosenGood = new Alert(Alert.AlertType.ERROR);
                    noChosenGood.setTitle("TradeMenu Error");
                    noChosenGood.setHeaderText("Error in Chosen Good");
                    noChosenGood.setContentText("""
                            Invalid Good Name
                            Possible Reasons :
                            1.You haven't filled the good field.
                            2.The chosen good doesn't exist.""");
                    noChosenGood.showAndWait();
                }
            } else {
                Alert noChosenEmpire = new Alert(Alert.AlertType.ERROR);
                noChosenEmpire.setTitle("TradeMenu Error");
                noChosenEmpire.setHeaderText("Error in Chosen Empire");
                noChosenEmpire.setContentText("""
                        Invalid Empire Name
                        Possible Reasons :
                        1.You haven't filled the empire field.
                        2.The chosen empire doesn't exist.
                        3.You entered the name of your own empire.""");
                noChosenEmpire.showAndWait();
            }
        }
    }

    public String idProvider(Empire empire, int number) {
        return empire.getName().concat(String.valueOf(number));
    }


    public Group showDonations() {
        int number = 1;
        Group group = new Group();
        int ySetter = 10;
        int xSetter = 500;
        for (Request request : Manage.getCurrentEmpire().getAllDonations()) {
            VBox vBox = new VBox();
            vBox.setBackground(new Background(new BackgroundImage(new Image(TradeController.class.getResource("/image/rawButton.jpg").toExternalForm()),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            vBox.setPrefSize(385, 80);
            Text sender = new Text();
            sender.setText(number + ".Sender: " + request.getSender().getName());
            sender.setFill(Color.rgb(203,168,131));
            sender.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 10));
            Text id = new Text();
            id.setText("Id: " + request.getId());
            Text status = new Text();
            status.setText("Status : " + request.getStatus());
            Text message = new Text();
            message.setText("Message: " + request.getMessage());
            Button accept = new Button();
            accept.setPrefSize(50, 30);
            accept.setText("Accept");
            accept.setStyle("-fx-background-color: #cba883");
            accept.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 10));
            Button deny = new Button();
            deny.setPrefSize(50, 30);
            deny.setText("Deny");
            deny.setStyle("-fx-background-color: #cba883");
            deny.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 10));
            vBox.getChildren().addAll(sender, id, message, status, accept, deny);
            accept.setTranslateX(300);
            accept.setTranslateY(-50);
            deny.setTranslateX(300);
            deny.setTranslateY(-30);
            accept.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    tradeAcceptance(request.getId(), request.getMessage(), request);
                    System.out.println("accept works");
                }
            });
            deny.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    tradeDenied(request.getId(), request.getMessage(), request);
                }
            });
            vBox.setLayoutX(xSetter);
            vBox.setLayoutY(ySetter);
            ySetter += 80;
            group.getChildren().add(vBox);
            number++;
        }
        return group;
    }

    public Group showRequests() {
        int number = 1;
        Group group = new Group();
        int ySetter = 10;
        int xSetter = 500;
        for (Request request : Manage.getCurrentEmpire().getAllRequests()) {
            VBox vBox = new VBox();
            vBox.setBackground(new Background(new BackgroundImage(new Image(TradeController.class.getResource("/image/rawButton.jpg").toExternalForm()),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            vBox.setPrefSize(385, 50);
            Text sender = new Text();
            sender.setText(number + ".Sender: " + request.getSender().getName());
            //sender.setStyle("-fx-text-fill: #cba883");
            sender.setStyle("-fx-text-color: red;");
            //sender.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 10));
            Text id = new Text();
            id.setText("Id: " + request.getId());
            Text status = new Text();
            status.setText("Status : " + request.getStatus());
            Text receiver = new Text();
            receiver.setText("Receiver: " + request.getReceiver().getName());
            Text message = new Text();
            message.setText("Message: " + request.getMessage());
            vBox.getChildren().addAll(sender, id, receiver, message, status);
            vBox.setLayoutX(xSetter);
            vBox.setLayoutY(ySetter);
            ySetter += 80;
            group.getChildren().add(vBox);
            number++;
        }
        return group;
    }

    public void tradeAcceptance(String id, String message, Request request) {
        String goodName = request.getGoodName();
        int amount = request.getAmount();
        int price = request.getPrice();
        String tradableThing = request.tradableThing;
        Empire empire = request.getSender();
        System.out.println("sender :" + request.getSender());
        if (getNumberOfGoods(goodName, Manage.getCurrentEmpire()) >= amount) {
            Request request1 = findRequest(id, empire);
            Request request2 = findDonation(id, Manage.getCurrentEmpire());
            setNumberOfGoods(Manage.getCurrentEmpire(), empire, price, tradableThing);
            request1.setAcceptance(true);
            request2.setAcceptance(true);
            setNumberOfGoods(empire, Manage.getCurrentEmpire(), amount, goodName);
            request2.setStatus("Accepted!");
            request1.setStatus("Accepted!");
            System.out.println("Request - status :" + request2.getStatus());
            System.out.println("Request1 - status :" + request1.getStatus());
            Alert success = new Alert(Alert.AlertType.ERROR);
            success.setTitle("TradeMenu Information!");
            success.setHeaderText("Success!");
            success.setContentText("The trade operation is done!");
            success.showAndWait();
        } else {
            Alert notEnoughResources = new Alert(Alert.AlertType.ERROR);
            notEnoughResources.setTitle("TradeMenu Error!");
            notEnoughResources.setHeaderText("Error in Amount Of Good!");
            notEnoughResources.setContentText("I'm afraid you don't have that much of chosen resource to trade with mentioned empire!");
            notEnoughResources.showAndWait();
        }
    }

    private void tradeDenied(String id, String message, Request request) {
        Empire empire = request.getSender();
        Request request1 = findRequest(id, empire);
        request1.setAcceptance(false);
        request.setAcceptance(false);
        request.setStatus("Denied!");
        request1.setStatus("Denied!");
        System.out.println("Request :" + request.getStatus());
        System.out.println("Request1 :" + request1.getStatus());
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

    public void notification(Stage stage) throws Exception {
        int number = 1;
        if (Manage.getCurrentEmpire().getNotificationOfDonation() >= 1) {
            String notification = "";
            for (int i = Manage.getCurrentEmpire().getNotificationOfDonation(); i < Manage.getCurrentEmpire().getAllDonations().size(); i++) {
                notification = notification.concat(number + ".Id: " + Manage.getCurrentEmpire().getAllDonations().get(i).getId() +
                        "\nMessage: " + Manage.getCurrentEmpire().getAllDonations().get(i).getMessage());
            }
            notification.contains("\nPress OK to see them!");
            Manage.getCurrentEmpire().setNotificationOfDonation(Manage.getCurrentEmpire().getAllDonations().size());
            Alert notificationAlert = new Alert(Alert.AlertType.INFORMATION);
            notificationAlert.setTitle("Notification Message");
            notificationAlert.setHeaderText("New Messages!");
            notificationAlert.setContentText(notification);
            Optional<ButtonType> result = notificationAlert.showAndWait();
            if (result.get() == ButtonType.OK) {
                ListOfReceivedRequestsMenu list = new ListOfReceivedRequestsMenu();
                list.start(stage);
            }
        }
    }
}

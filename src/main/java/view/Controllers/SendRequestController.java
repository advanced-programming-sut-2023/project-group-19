package view.Controllers;

import controller.TradeController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import model.Empire;
import model.Manage;
import model.Map;
import model.User;
import view.ImageAndBackground.TradeAndShopImages;
import view.Messages.TradeMenuMessages;

import java.io.IOException;
import java.util.ArrayList;

public class SendRequestController {
    public TradeAndShopImages tradeAndShopImages = new TradeAndShopImages();
    public TradeController tradeController = new TradeController();
    public int typeOfTradeAction = 0;
    public TradeMenuMessages messages;

    public void designTheMenu(Pane pane) throws IOException {
        tradeAndShopImages.loadImages();
        ImageView background = new ImageView(tradeAndShopImages.getBackground());
        pane.getChildren().add(background);
        makeTheRequestForm(pane);
    }

    public void makeTheEmpiresChoiceBox(Pane pane, TextField empireName) {
        ArrayList<String> nameOfEmpires = new ArrayList<>();
        for (Empire allEmpire : Manage.getAllEmpires()) {
            nameOfEmpires.add(allEmpire.getName());
        }
        ChoiceBox<String> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(nameOfEmpires));
        choiceBox.setPrefWidth(200);
        choiceBox.setLayoutY(6);
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value) {
                empireName.setText(nameOfEmpires.get(new_value.intValue()));

            }
        });
        pane.getChildren().add(choiceBox);
    }

    public void makeTheGoodsChoiceBox(Pane pane, TextField goodName) {
        String[] tradingThings = new String[41];
        for (int i = 0; i < 41; i++) {
            tradingThings[i] = getNameOfGood(i);
        }
        ChoiceBox<String> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(tradingThings));
        choiceBox.setPrefWidth(200);
        choiceBox.setLayoutX(1340);
        choiceBox.setLayoutY(6);
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value) {
                goodName.setText(tradingThings[new_value.intValue()]);

            }
        });
        pane.getChildren().add(choiceBox);
    }


    public void makeTheRequestForm(Pane pane) {
        TextField empireName = new TextField();
        empireName.setPromptText("Choose the receiver :");
        empireName.setPrefSize(300, 40);
        empireName.setPrefWidth(300);
        empireName.setLayoutX(625);
        empireName.setLayoutY(100);
        pane.getChildren().add(empireName);

        TextField goodName = new TextField();
        goodName.setPromptText("Choose a good to be received :");
        goodName.setPrefSize(300, 40);
        goodName.setPrefWidth(300);
        goodName.setLayoutX(625);
        goodName.setLayoutY(200);
        pane.getChildren().add(goodName);

        Spinner<Integer> amount = new Spinner<Integer>(1, 50, 1);
        amount.setEditable(true);
        amount.setMinSize(300, 40);
        amount.setMinWidth(300);
        amount.setMinHeight(40);
        amount.setLayoutX(625);
        amount.setLayoutY(300);
        pane.getChildren().add(amount);

        Button request = new Button();
        ImageView requestImage = new ImageView(tradeAndShopImages.getRequest());
        request.setBackground(null);
        requestImage.setFitWidth(300);
        requestImage.setFitHeight(40);
        requestImage.setPreserveRatio(true);
        request.setStyle("-fx-background-color: #9a0d0d");
        request.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        request.setText("Request");
        request.setLayoutX(620);
        request.setLayoutY(400);
        request.setMinSize(300, 40);
        request.setPrefSize(300, 40);
        pane.getChildren().add(request);


        Button donate = new Button();
        ImageView donateImage = new ImageView(tradeAndShopImages.getDonate());
        donateImage.setFitWidth(310);
        donateImage.setFitHeight(200);
        donateImage.setPreserveRatio(true);
        donate.setStyle("-fx-background-color: #9a0d0d");
        donate.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        donate.setText("Donate");
        donate.setLayoutX(620);
        donate.setLayoutY(500);
        donate.setMinSize(300, 40);
        donate.setPrefSize(300, 40);
        pane.getChildren().add(donate);


        TextField message = new TextField();
        message.setPromptText("Enter message :");
        message.setMinSize(300, 40);
        message.setPrefWidth(300);
        message.setPrefHeight(40);
        message.setLayoutX(625);
        message.setLayoutY(600);
        pane.getChildren().add(message);


        Button sendButton = new Button();
        sendButton.setPrefSize(150, 30);
        sendButton.setLayoutX(700);
        sendButton.setLayoutY(700);
        ImageView send = new ImageView(tradeAndShopImages.getSend());
        send.setFitWidth(300);
        send.setFitHeight(200);
        send.setPreserveRatio(true);
        sendButton.setStyle("-fx-background-color: #9a0d0d");
        sendButton.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        sendButton.setText("Send");
        pane.getChildren().add(sendButton);

        donate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                typeOfTradeAction = 1;
            }
        });

        request.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                typeOfTradeAction = 2;
            }
        });

        sendButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (setEventHandler(empireName, goodName, amount, message)) {
                    String receiver = empireName.getText();
                    String good = goodName.getText();
                    int amountOfNeededGood = amount.getValue();
                    String myMessage = message.getText();
                    System.out.println(Manage.getCurrentEmpire().getName());
                    tradeController.selectedEmpire = Manage.getEmpireByNickname(receiver);
                    tradeController.sendRequest(good, amountOfNeededGood, myMessage);
                    typeOfTradeAction = 0;
                }
            }
        });
        makeTheGoodsChoiceBox(pane, goodName);
        makeTheEmpiresChoiceBox(pane, empireName);
    }

    public boolean setEventHandler(TextField empireName, TextField goodName, Spinner<Integer> amount, TextField message) {
        String receiver = empireName.getText();
        String good = goodName.getText();
        String myMessage = message.getText();
        if (receiver == null) {
            Alert receiverAlert = new Alert(Alert.AlertType.ERROR);
            receiverAlert.setTitle("Error!");
            receiverAlert.setHeaderText("Error in entering receiver!");
            receiverAlert.setContentText("The entering receiver part is empty!");
            receiverAlert.showAndWait();
            return false;
        } else if (good == null) {
            Alert goodAlert = new Alert(Alert.AlertType.ERROR);
            goodAlert.setTitle("Error!");
            goodAlert.setHeaderText("Error in entering required good!");
            goodAlert.setContentText("The entering good part is empty!");
            goodAlert.showAndWait();
            return false;
        } else if (myMessage == null) {
            Alert messageAlert = new Alert(Alert.AlertType.ERROR);
            messageAlert.setTitle("Error!");
            messageAlert.setHeaderText("Error in entering message!");
            messageAlert.setContentText("The entering message part is empty!");
            messageAlert.showAndWait();
            return false;
        }
        return true;
    }

    public String getNameOfGood(int number) {
        return switch (number) {
            case 0 -> "meat";
            case 1 -> "hops";
            case 2 -> "ironArmor";
            case 3 -> "leatherArmor";
            case 4 -> "sword";
            case 5 -> "mace";
            case 6 -> "bow";
            case 7 -> "oil";
            case 8 -> "iron";
            case 9 -> "stone";
            case 10 -> "wood";
            case 11 -> "flour";
            case 12 -> "wheat";
            case 13 -> "apple";
            case 14 -> "cheese";
            //////////////////
            case 15 -> "beer";
            case 16 -> "horse";
            case 17 -> "archer";
            case 18 -> "spearMan";
            case 19 -> "maceMan";
            case 20 -> "crossbowMan";
            case 21 -> "pikeMan";
            case 22 -> "swordMan";
            case 23 -> "blackMonk";
            case 24 -> "knight";
            case 25 -> "arabianBow";
            case 26 -> "slave";
            case 27 -> "slinger";
            case 28 -> "assassin";
            case 29 -> "horseArcher";
            case 30 -> "arabianSwordMan";
            case 31 -> "fireThrower";
            case 32 -> "engineer";
            case 33 -> "ladderMan";
            case 34 -> "tunneler";
            case 35 -> "catapult";
            case 36 -> "trebuchet";
            case 37 -> "siegeTower";
            case 38 -> "fireBallista";
            case 39 -> "batteringRam";
            case 40 -> "portableShield";
            default -> null;
        };
    }
}

package view.Controllers;

import controller.ShopController;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import view.GameMenu;
import view.Messages.ShopMenuMessages;
import view.OldView.TradeMenu;
import view.TileManager;

import java.util.ArrayList;

public class ShopMenuController {
    //TODO : Bugs confirmation form pops up twice , you should make the pops-up attached to the menu,
    // Bar should be attached to the upper region of menu,background
    public ShopController shopController = new ShopController();
    public int number;
    ArrayList<Integer> getTheSuggestion = new ArrayList<>();
    public void showPriceList(ArrayList<Group> list, Pane pane, Stage stage) {
        Button tradeMenu = new Button();
        Button back = new Button();
        Button sell = new Button();
        Button buy = new Button();
        tradeMenu.setPrefSize(100, 40);
        back.setPrefSize(100,40);
        sell.setPrefSize(100, 10);
        buy.setPrefSize(100, 10);
        tradeMenu.setLayoutX(1400);
        tradeMenu.setLayoutY(80);
        back.setLayoutX(1400);
        back.setLayoutY(20);
        sell.setLayoutX(700);
        sell.setLayoutY(780);
        buy.setLayoutX(810);
        buy.setLayoutY(780);
        tradeMenu.setText("trade");
        back.setText("back");
        sell.setText("Sell");
        buy.setText("Buy");
        back.setStyle("-fx-background-color: #cba883");
        tradeMenu.setStyle("-fx-background-color: #cba883");
        sell.setStyle("-fx-background-color: #cba883");
        buy.setStyle("-fx-background-color: #cba883");
        back.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        tradeMenu.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        sell.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        buy.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        makeTheButtonsReady(sell, buy, back,tradeMenu,list,stage);
        pane.getChildren().addAll(list);
        pane.getChildren().addAll(sell, buy,back,tradeMenu);
    }

    private void makeTheButtonsReady(Button sell, Button buy,Button back , Button tradeMenu ,ArrayList<Group> list,Stage stage) {
        for (int i = 0 ; i < list.size() ; i++) {
            Group group = list.get(i);
            CheckBox checkBox = (CheckBox) group.getChildren().get(0);
            int finalIndex = i;
            checkBox.selectedProperty().addListener(
                    (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                        if (new_val) {
                            TextInputDialog textInputDialog = new TextInputDialog();
                            textInputDialog.setHeaderText("Enter the number of selected good :");
                            textInputDialog.setContentText("Number: ");
                            textInputDialog.showAndWait();
                            String result = textInputDialog.getEditor().getText();
                            System.out.println("index :" + finalIndex + " input: " + result);
                            int num = Integer.parseInt(result);
                            getTheSuggestion.add(finalIndex);
                            getTheSuggestion.add(num);
                            System.out.println("size of array list :" + getTheSuggestion.size());
                        }else{
                            checkBox.setSelected(false);
                        }
                    });
        }
        sell.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String name = goodFinder(getTheSuggestion.get(0));
                int amount = getTheSuggestion.get(1);
                ShopMenuMessages messages = shopController.sellItem(name,amount);
                if (messages.getMessages().equals(ShopMenuMessages.SELLING_OPERATION_SUCCEEDED.getMessages())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Result of Selling Operation");
                    alert.setContentText(ShopMenuMessages.SELLING_OPERATION_SUCCEEDED.getMessages());
                    alert.showAndWait();
                    stage.show();
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Result of Selling Operation");
                    alert.setContentText(messages.getMessages());
                    alert.showAndWait();
                }
                getTheSuggestion.clear();
            }
        });
        buy.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String name = goodFinder(getTheSuggestion.get(0));
                int amount = getTheSuggestion.get(1);
                ShopMenuMessages messages = shopController.buyItem(name,amount);
                if (messages.getMessages().equals(ShopMenuMessages.BUYING_OPERATION_SUCCEEDED.getMessages())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Result of Buying Operation");
                    alert.setContentText(ShopMenuMessages.BUYING_OPERATION_SUCCEEDED.getMessages());
                    alert.showAndWait();
                    stage.show();
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Result of Buying Operation");
                    alert.setContentText(messages.getMessages());
                    alert.showAndWait();
                }
                getTheSuggestion.clear();
            }
        });

        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TileManager tileManager = new TileManager();
                try {
                    tileManager.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        tradeMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TradeMenu tradeMenu1 = new TradeMenu();
                //tradeMenu1.show(stage);
            }
        });
    }

    public String goodFinder(int index) {
        switch (index) {
            case 0 -> {
                return "meat";
            }
            case 1 -> {
                return "hops";
            }
            case 2 -> {
                return "ironArmour";
            }
            case 3 -> {
                return "leatherArmour";
            }
            case 4 -> {
                return "sword";
            }
            case 5 -> {
                return "bow";
            }
            case 6 -> {
                return "mace";
            }
            case 7 -> {
                return "oil";
            }
            case 8 -> {
                return "iron";
            }
            case 9 -> {
                return "stone";
            }
            case 10 -> {
                return "wood";
            }
            case 11 -> {
                return "flour";
            }
            case 12 -> {
                return "wheat";
            }
            case 13 -> {
                return "apple";
            }
            case 14 -> {
                return "cheese";
            }
        }
        return null;
    }

}

package view.GameButtons;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Building.Shop;
import model.Manage;
import view.ImageAndBackground.UnitImages;
import view.OldView.SelectedBuildingMenu;
import view.ShopMenu;

import java.util.ArrayList;

public class SelectedBuildingButtons {
    public ArrayList<Button> selectedBuildingsAddedButtons = new ArrayList<>();
    public TextField gatehouseText;

    public TextField getGatehouseText() {
        return gatehouseText;
    }

    public void setGatehouseText(TextField gatehouseText) {
        this.gatehouseText = gatehouseText;
    }

    public void showError(String output) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("DROP BUILDING FAILED");
        error.setContentText(output);
        error.show();
    }

    public void shop(Pane pane) throws Exception {
        Stage stage = new Stage();
        System.out.println(5);
        ShopMenu shopMenu = new ShopMenu();
        Shop shop = new Shop(Manage.getCurrentEmpire());
        shop.shop();
        shopMenu.currentShop = shop;

        shopMenu.start(stage);
        stage.setFullScreen(true);
    }

    public void barracks(Pane pane, SelectedBuildingMenu selectedBuildingMenu, UnitImages unitImages) {
        Button archerButton = new Button();
        ImageView archerImage = new ImageView(unitImages.getArcher());
        archerButton.setBackground(null);
        archerButton.setGraphic(archerImage);
        archerButton.setLayoutX(110);
        archerButton.setLayoutY(730);
        archerButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(archerButton);
        pane.getChildren().add(archerButton);
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("archer", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        archerButton.setOnMouseClicked(event);

        Button spearManButton = new Button();
        ImageView spearManImage = new ImageView(unitImages.getSpearMen());
        spearManButton.setBackground(null);
        spearManButton.setGraphic(spearManImage);
        spearManButton.setLayoutX(250);
        spearManButton.setLayoutY(730);
        spearManButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(spearManButton);
        pane.getChildren().add(spearManButton);
        EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("spearMan", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        spearManButton.setOnMouseClicked(event1);

        Button maceManButton = new Button();
        ImageView maceManImage = new ImageView(unitImages.getMaceMen());
        maceManButton.setBackground(null);
        maceManButton.setGraphic(maceManImage);
        maceManButton.setLayoutX(390);
        maceManButton.setLayoutY(730);
        maceManButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(maceManButton);
        pane.getChildren().add(maceManButton);
        EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("maceMan", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        maceManButton.setOnMouseClicked(event2);

        Button crossbowManButton = new Button();
        ImageView crossbowManImage = new ImageView(unitImages.getCrossbowMen());
        crossbowManButton.setBackground(null);
        crossbowManButton.setGraphic(crossbowManImage);
        crossbowManButton.setLayoutX(530);
        crossbowManButton.setLayoutY(730);
        crossbowManButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(crossbowManButton);
        pane.getChildren().add(crossbowManButton);
        EventHandler<MouseEvent> event3 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("crossbowMan", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        crossbowManButton.setOnMouseClicked(event3);

        Button pikeManButton = new Button();
        ImageView pikeManImage = new ImageView(unitImages.getPikeMen());
        pikeManButton.setBackground(null);
        pikeManButton.setGraphic(pikeManImage);
        pikeManButton.setLayoutX(670);
        pikeManButton.setLayoutY(730);
        pikeManButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(pikeManButton);
        pane.getChildren().add(pikeManButton);
        EventHandler<MouseEvent> event4 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("pikeMan", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        pikeManButton.setOnMouseClicked(event4);

        Button swordManButton = new Button();
        ImageView swordManImage = new ImageView(unitImages.getSwordsMen());
        swordManButton.setBackground(null);
        swordManButton.setGraphic(swordManImage);
        swordManButton.setLayoutX(810);
        swordManButton.setLayoutY(730);
        swordManButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(swordManButton);
        pane.getChildren().add(swordManButton);
        EventHandler<MouseEvent> event5 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("swordMan", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        swordManButton.setOnMouseClicked(event5);
        Button knightButton = new Button();
        ImageView knightImage = new ImageView(unitImages.getKnight());
        knightButton.setBackground(null);
        knightButton.setGraphic(knightImage);
        knightButton.setLayoutX(950);
        knightButton.setLayoutY(730);
        knightButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(knightButton);
        pane.getChildren().add(knightButton);
        EventHandler<MouseEvent> event6 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("knight", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        knightButton.setOnMouseClicked(event6);
    }

    public void mercenary(Pane pane, SelectedBuildingMenu selectedBuildingMenu, UnitImages unitImages) {
        Button arabianBowButton = new Button();
        ImageView arabianBowImage = new ImageView(unitImages.getArcherBow());
        arabianBowButton.setBackground(null);
        arabianBowButton.setGraphic(arabianBowImage);
        arabianBowButton.setLayoutX(110);
        arabianBowButton.setLayoutY(730);
        arabianBowButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(arabianBowButton);
        pane.getChildren().add(arabianBowButton);
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("arabianBow", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        arabianBowButton.setOnMouseClicked(event);

        Button slaveButton = new Button();
        ImageView slaveImage = new ImageView(unitImages.getSlaves());
        slaveButton.setBackground(null);
        slaveButton.setGraphic(slaveImage);
        slaveButton.setLayoutX(250);
        slaveButton.setLayoutY(730);
        slaveButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(slaveButton);
        pane.getChildren().add(slaveButton);
        EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("slave", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        slaveButton.setOnMouseClicked(event1);

        Button slingerButton = new Button();
        ImageView slingerImage = new ImageView(unitImages.getSlingers());
        slingerButton.setBackground(null);
        slingerButton.setGraphic(slingerImage);
        slingerButton.setLayoutX(390);
        slingerButton.setLayoutY(730);
        slingerButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(slingerButton);
        pane.getChildren().add(slingerButton);
        EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("slinger", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        slingerButton.setOnMouseClicked(event2);

        Button assassinButton = new Button();
        ImageView assassinImage = new ImageView(unitImages.getAssassins());
        assassinButton.setBackground(null);
        assassinButton.setGraphic(assassinImage);
        assassinButton.setLayoutX(530);
        assassinButton.setLayoutY(730);
        assassinButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(assassinButton);
        pane.getChildren().add(assassinButton);
        EventHandler<MouseEvent> event3 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("assassin", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        assassinButton.setOnMouseClicked(event3);

        Button horseArcherButton = new Button();
        ImageView horseArcherImage = new ImageView(unitImages.getHorseArchers());
        horseArcherButton.setBackground(null);
        horseArcherButton.setGraphic(horseArcherImage);
        horseArcherButton.setLayoutX(670);
        horseArcherButton.setLayoutY(730);
        horseArcherButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(horseArcherButton);
        pane.getChildren().add(horseArcherButton);
        EventHandler<MouseEvent> event4 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("horseArcher", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        horseArcherButton.setOnMouseClicked(event4);

        Button arabianSwordManButton = new Button();
        ImageView arabianSwordManImage = new ImageView(unitImages.getArabianSwordMen());
        arabianSwordManButton.setBackground(null);
        arabianSwordManButton.setGraphic(arabianSwordManImage);
        arabianSwordManButton.setLayoutX(810);
        arabianSwordManButton.setLayoutY(730);
        arabianSwordManButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(arabianSwordManButton);
        pane.getChildren().add(arabianSwordManButton);
        EventHandler<MouseEvent> event5 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("arabianSwordMan", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        arabianSwordManButton.setOnMouseClicked(event5);

        Button fireThrowerButton = new Button();
        ImageView fireThrowerImage = new ImageView(unitImages.getFireThrowers());
        fireThrowerButton.setBackground(null);
        fireThrowerButton.setGraphic(fireThrowerImage);
        fireThrowerButton.setLayoutX(950);
        fireThrowerButton.setLayoutY(730);
        fireThrowerButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(fireThrowerButton);
        pane.getChildren().add(fireThrowerButton);
        EventHandler<MouseEvent> event6 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("fireThrower", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        fireThrowerButton.setOnMouseClicked(event6);
    }

    public void siegeTent(Pane pane, SelectedBuildingMenu selectedBuildingMenu, UnitImages unitImages) {
        Button catapultButton = new Button();
        ImageView catapultImage = new ImageView(unitImages.getCatapult());
        catapultButton.setBackground(null);
        catapultButton.setGraphic(catapultImage);
        catapultButton.setLayoutX(110);
        catapultButton.setLayoutY(730);
        catapultButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(catapultButton);
        pane.getChildren().add(catapultButton);
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("catapult", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        catapultButton.setOnMouseClicked(event);

        Button trebuchetButton = new Button();
        ImageView trebuchetImage = new ImageView(unitImages.getTrebuchet());
        trebuchetButton.setBackground(null);
        trebuchetButton.setGraphic(trebuchetImage);
        trebuchetButton.setLayoutX(250);
        trebuchetButton.setLayoutY(730);
        trebuchetButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(trebuchetButton);
        pane.getChildren().add(trebuchetButton);
        EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("trebuchet", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        trebuchetButton.setOnMouseClicked(event1);

        Button siegeTowerButton = new Button();
        ImageView siegeTowerImage = new ImageView(unitImages.getSiegeTower());
        siegeTowerButton.setBackground(null);
        siegeTowerButton.setGraphic(siegeTowerImage);
        siegeTowerButton.setLayoutX(390);
        siegeTowerButton.setLayoutY(730);
        siegeTowerButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(siegeTowerButton);
        pane.getChildren().add(siegeTowerButton);
        EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("siegeTower", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        siegeTowerButton.setOnMouseClicked(event2);

        Button batteringRamButton = new Button();
        ImageView batteringRamImage = new ImageView(unitImages.getBatteringRam());
        batteringRamButton.setBackground(null);
        batteringRamButton.setGraphic(batteringRamImage);
        batteringRamButton.setLayoutX(530);
        batteringRamButton.setLayoutY(730);
        batteringRamButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(batteringRamButton);
        pane.getChildren().add(batteringRamButton);
        EventHandler<MouseEvent> event3 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("batteringRam", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        batteringRamButton.setOnMouseClicked(event3);

        Button portableShieldButton = new Button();
        ImageView portableShieldImage = new ImageView(unitImages.getPortableShield());
        portableShieldButton.setBackground(null);
        portableShieldButton.setGraphic(portableShieldImage);
        portableShieldButton.setLayoutX(670);
        portableShieldButton.setLayoutY(730);
        portableShieldButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(portableShieldButton);
        pane.getChildren().add(portableShieldButton);
        EventHandler<MouseEvent> event4 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("portableShield", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        portableShieldButton.setOnMouseClicked(event4);

        Button fireBallistaButton = new Button();
        ImageView fireBallistaImage = new ImageView(unitImages.getFireBallista());
        fireBallistaButton.setBackground(null);
        fireBallistaButton.setGraphic(fireBallistaImage);
        fireBallistaButton.setLayoutX(810);
        fireBallistaButton.setLayoutY(730);
        fireBallistaButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(fireBallistaButton);
        pane.getChildren().add(fireBallistaButton);
        EventHandler<MouseEvent> event5 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("fireBallista", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        fireBallistaButton.setOnMouseClicked(event5);
    }

    public void engineerGuild(Pane pane, SelectedBuildingMenu selectedBuildingMenu, UnitImages unitImages) {
        Button engineerButton = new Button();
        ImageView engineerImage = new ImageView(unitImages.getEngineer());
        engineerButton.setBackground(null);
        engineerButton.setGraphic(engineerImage);
        engineerButton.setLayoutX(110);
        engineerButton.setLayoutY(730);
        engineerButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(engineerButton);
        pane.getChildren().add(engineerButton);
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("engineer", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        engineerButton.setOnMouseClicked(event);

        Button ladderManButton = new Button();
        ImageView ladderManImage = new ImageView(unitImages.getLadderMen());
        ladderManButton.setBackground(null);
        ladderManButton.setGraphic(ladderManImage);
        ladderManButton.setLayoutX(250);
        ladderManButton.setLayoutY(730);
        ladderManButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(ladderManButton);
        pane.getChildren().add(ladderManButton);
        EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("ladderMan", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        ladderManButton.setOnMouseClicked(event1);

        Button tunnelerButton = new Button();
        ImageView tunnelerImage = new ImageView(unitImages.getTunneler());
        tunnelerButton.setBackground(null);
        tunnelerButton.setGraphic(tunnelerImage);
        tunnelerButton.setLayoutX(390);
        tunnelerButton.setLayoutY(730);
        tunnelerButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(tunnelerButton);
        pane.getChildren().add(tunnelerButton);
        EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("tunneler", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        tunnelerButton.setOnMouseClicked(event2);
    }

    public void church(Pane pane, SelectedBuildingMenu selectedBuildingMenu, UnitImages unitImages) {
        Button blackMonkButton = new Button();
        ImageView blackMonkImage = new ImageView(unitImages.getBlackMonk());
        blackMonkButton.setBackground(null);
        blackMonkButton.setGraphic(blackMonkImage);
        blackMonkButton.setLayoutX(450);
        blackMonkButton.setLayoutY(730);
        blackMonkButton.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(blackMonkButton);
        pane.getChildren().add(blackMonkButton);
        EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.createUnit("blackMonk", 1));
                if (!output.equals("troop created successfully")) {
                    showError(output);
                }
            }
        };
        blackMonkButton.setOnMouseClicked(event1);
    }

    public void gatehouse(Pane pane, SelectedBuildingMenu selectedBuildingMenu, UnitImages unitImages) {
        Button submit = new Button();
        submit.setBackground(null);
        submit.setText("SUBMIT");
        submit.setLayoutX(450);
        submit.setLayoutY(750);
        submit.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(submit);

        gatehouseText = new TextField();
        gatehouseText.setPromptText("tax rate");
        gatehouseText.setLayoutX(550);
        gatehouseText.setLayoutY(786);

        selectedBuildingsAddedButtons.add(submit);
        EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String taxRate = gatehouseText.getText();
                if (taxRate.matches("[-0-9]+")) {
                    String output = String.valueOf(selectedBuildingMenu.setTax(Integer.parseInt(taxRate)));
                    if (!output.equals("tax rate changed successfully")) {
                        showError(output);
                    }
                } else {
                    showError("only use number in this field");
                }
                gatehouseText.setText("");
            }
        };
        submit.setOnMouseClicked(event1);
        pane.getChildren().add(submit);
        pane.getChildren().add(gatehouseText);
    }

    public void drawBridge(Pane pane, SelectedBuildingMenu selectedBuildingMenu, UnitImages unitImages) {
        Button open = new Button();
        open.setBackground(null);
        open.setText("OPEN");
        open.setLayoutX(350);
        open.setLayoutY(730);
        open.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(open);
        pane.getChildren().add(open);
        EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.drawBridge("down"));
                if (!output.equals("bridge opened")) {
                    showError(output);
                }
            }
        };
        open.setOnMouseClicked(event1);

        Button close = new Button();
        close.setText("CLOSE");
        close.setBackground(null);
        close.setLayoutX(550);
        close.setLayoutY(730);
        close.setMinSize(100, 100);
        selectedBuildingsAddedButtons.add(close);
        pane.getChildren().add(close);
        EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String output = String.valueOf(selectedBuildingMenu.drawBridge("up"));
                if (!output.equals("bridge closed")) {
                    showError(output);
                }
            }
        };
        close.setOnMouseClicked(event2);
    }

    public ArrayList<Button> getSelectedBuildingsAddedButtons() {
        return selectedBuildingsAddedButtons;
    }

    public void setSelectedBuildingsAddedButtons(ArrayList<Button> selectedBuildingsAddedButtons) {
        this.selectedBuildingsAddedButtons = selectedBuildingsAddedButtons;
    }
}

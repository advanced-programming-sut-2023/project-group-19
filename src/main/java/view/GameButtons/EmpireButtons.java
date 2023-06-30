package view.GameButtons;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Empire;
import model.Manage;
import view.ImageAndBackground.BottomBarImages;
import view.ImageAndBackground.BuildingImages;
import view.OldView.EmpireMenu;
import view.TileManager;


import java.util.ArrayList;

public class EmpireButtons {

    public ArrayList<Button> addedButtons = new ArrayList<>();
    public ArrayList<Text> addedText = new ArrayList<>();
    public BottomBarImages testbottomBarImages;
    public TextField foodRate;
    public TextField fearRate;
    public TextField taxRate;
    public Label l;
    public Slider slider;
    public int rate;


    public void createButtons(Pane pane, BottomBarImages bottomBarImages, BuildingImages buildingImages, EmpireMenu empireMenu) {
        testbottomBarImages = bottomBarImages;
        ImageView selectBackground = new ImageView(bottomBarImages.getSelectedBuildingBackground());
        selectBackground.setFitWidth(980);
        selectBackground.setFitHeight(200);
        selectBackground.setLayoutX(100);
        selectBackground.setLayoutY(675);
        pane.getChildren().add(selectBackground);

        Button popularityButton = new Button();
        ImageView popularityImage = new ImageView(bottomBarImages.popularity);
        popularityButton.setBackground(null);
        popularityImage.setFitHeight(230);
        popularityImage.setFitWidth(230);
        popularityButton.setGraphic(popularityImage);
        popularityButton.setLayoutX(700);
        popularityButton.setLayoutY(730);
        popularityButton.setMinSize(50, 50);
        popularityButton.setMaxSize(200, 100);
        addedButtons.add(popularityButton);
        pane.getChildren().add(popularityButton);

        Button setRateButton = new Button();
        ImageView setRateImage = new ImageView(bottomBarImages.setRate);
        setRateButton.setBackground(null);
        setRateImage.setFitHeight(230);
        setRateImage.setFitWidth(230);
        setRateButton.setGraphic(setRateImage);
        setRateButton.setLayoutX(400);
        setRateButton.setLayoutY(730);
        setRateButton.setMinSize(50, 50);
        setRateButton.setMaxSize(200, 100);
        addedButtons.add(setRateButton);
        pane.getChildren().add(setRateButton);


        popularityButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
                TileManager.gameLog.append(TileManager.time + '#' + "LEFT_CLICK" + '#' + "POPULARITY_BUTTON" +  '\n');
                clearPane(pane);
                showPopularityFactors(pane, buildingImages, empireMenu);
            }
        });

        setRateButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
                TileManager.gameLog.append(TileManager.time + '#' + "LEFT_CLICK" + '#' + "SET_RATE_BUTTON" +  '\n');
                clearPane(pane);
                setRateMenu(pane, buildingImages, empireMenu);
            }
        });

    }

    public void showPopularityFactors(Pane pane, BuildingImages buildingImages, EmpireMenu empireMenu) {
        Text food = new Text();
        food.setText("Food : " + (Manage.getCurrentEmpire().getPopularityFactorFood() + Manage.getCurrentEmpire().getFoodDiversity()));
        if (Manage.getCurrentEmpire().getPopularityFactorFood() + Manage.getCurrentEmpire().getFoodDiversity() >= 0) {
            food.setStroke(Color.GREEN);
        } else {
            food.setStroke(Color.RED);
        }
        food.setLayoutX(200);
        food.setLayoutY(715);
        addedText.add(food);
        pane.getChildren().add(food);

        Text tax = new Text();
        tax.setText("Tax : " + Manage.getCurrentEmpire().getPopularityFactorTax());
        if (Manage.getCurrentEmpire().getPopularityFactorTax() >= 0) {
            tax.setStroke(Color.GREEN);
        } else {
            tax.setStroke(Color.RED);
        }

        tax.setLayoutX(300);
        tax.setLayoutY(715);
        addedText.add(tax);
        pane.getChildren().add(tax);

        Text fear = new Text();
        fear.setText("Fear : " + Manage.getCurrentEmpire().getPopularityFactorFear());
        if (Manage.getCurrentEmpire().getPopularityFactorFear() >= 0) {
            fear.setStroke(Color.GREEN);
        } else {
            fear.setStroke(Color.RED);
        }
        fear.setLayoutX(400);
        fear.setLayoutY(715);
        addedText.add(fear);
        pane.getChildren().add(fear);

        Text religion = new Text();
        religion.setText("Religion : " + Manage.getCurrentEmpire().getPopularityFactorReligious());
        if (Manage.getCurrentEmpire().getPopularityFactorReligious() >= 0) {
            religion.setStroke(Color.GREEN);
        } else {
            religion.setStroke(Color.RED);
        }
        religion.setLayoutX(500);
        religion.setLayoutY(715);
        addedText.add(religion);
        pane.getChildren().add(religion);

        Text sickness = new Text();
        if (!Manage.getCurrentEmpire().isSickness()) {
            sickness.setText("sickness : " + 0);
            sickness.setStroke(Color.GREEN);
        } else {
            sickness.setText("sickness : " + -2);
            sickness.setStroke(Color.RED);
        }
        sickness.setLayoutX(600);
        sickness.setLayoutY(715);
        addedText.add(sickness);
        pane.getChildren().add(sickness);


        Text popularity = new Text();
        popularity.setText("Popularity : " + (Manage.getCurrentEmpire().getPopularity() + Manage.getCurrentEmpire().getSicknessImpactOnPopularity()));
        if (Manage.getCurrentEmpire().getPopularity() >= 0) {
            popularity.setStroke(Color.GREEN);
        } else {
            popularity.setStroke(Color.RED);
        }
        popularity.setLayoutX(400);
        popularity.setLayoutY(760);
        addedText.add(popularity);
        pane.getChildren().add(popularity);

        Button returnIconButton = new Button();
        ImageView returnIconImage = new ImageView(buildingImages.getReturnIcon());
        returnIconButton.setBackground(null);
        returnIconImage.setFitHeight(40);
        returnIconImage.setFitWidth(40);
        returnIconButton.setGraphic(returnIconImage);
        returnIconButton.setLayoutX(120);
        returnIconButton.setLayoutY(740);
        returnIconButton.setMinSize(40, 40);
        addedButtons.add(returnIconButton);
        pane.getChildren().add(returnIconButton);
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
                TileManager.gameLog.append(TileManager.time + '#' + "LEFT_CLICK" + '#' + "RETURN_BUTTON" +  '\n');
                clearPane(pane);
                createButtons(pane, testbottomBarImages, buildingImages, empireMenu);
            }
        };
        returnIconButton.setOnMouseClicked(event);
    }

    public void setRateMenu(Pane pane, BuildingImages buildingImages, EmpireMenu empireMenu) {
        Button returnIconButton = new Button();
        ImageView returnIconImage = new ImageView(buildingImages.getReturnIcon());
        returnIconButton.setBackground(null);
        returnIconImage.setFitHeight(40);
        returnIconImage.setFitWidth(40);
        returnIconButton.setGraphic(returnIconImage);
        returnIconButton.setLayoutX(120);
        returnIconButton.setLayoutY(740);
        returnIconButton.setMinSize(40, 40);
        addedButtons.add(returnIconButton);
        pane.getChildren().add(returnIconButton);
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
                TileManager.gameLog.append(TileManager.time + '#' + "LEFT_CLICK" + '#' + "RETURN_BUTTON" +  '\n');
                clearPane(pane);
                createButtons(pane, testbottomBarImages, buildingImages, empireMenu);
            }
        };
        returnIconButton.setOnMouseClicked(event);

        Button submit1 = new Button();
        submit1.setBackground(null);
        submit1.setText("SUBMIT");
        submit1.setLayoutX(400);
        submit1.setLayoutY(700);
        submit1.setMinSize(100, 100);


        foodRate = new TextField();
        foodRate.setPromptText("Food Rate");
        foodRate.setLayoutX(250);
        foodRate.setLayoutY(736);

        addedButtons.add(submit1);
        EventHandler<MouseEvent> event1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
                TileManager.gameLog.append(TileManager.time + '#' + "LEFT_CLICK" + '#' + "SUBMIT_FOOD_RATE_BUTTON" +  '\n');
                String Rate = foodRate.getText();
                if (Rate.matches("[-0-9]+")) {
                    String output = String.valueOf(empireMenu.setFoodRate(Integer.parseInt(Rate)));
                    if (!output.equals("successful")) {
                        showError(output);
                    }
                } else {
                    showError("only use number in this field");
                }
                foodRate.setText("");
            }
        };
        submit1.setOnMouseClicked(event1);
        pane.getChildren().add(submit1);
        pane.getChildren().add(foodRate);


        Button submit2 = new Button();
        submit2.setBackground(null);
        submit2.setText("SUBMIT");
        submit2.setLayoutX(750);
        submit2.setLayoutY(700);
        submit2.setMinSize(100, 100);


        taxRate = new TextField();
        taxRate.setPromptText("Tax Rate");
        taxRate.setLayoutX(600);
        taxRate.setLayoutY(736);

        addedButtons.add(submit2);
        EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
                TileManager.gameLog.append(TileManager.time + '#' + "LEFT_CLICK" + '#' + "SUBMIT_TAX_RATE_BUTTON" +  '\n');
                String Rate = taxRate.getText();
                if (Rate.matches("[-0-9]+")) {
                    String output = String.valueOf(empireMenu.setTaxRate(Integer.parseInt(Rate)));
                    if (!output.equals("successful")) {
                        showError(output);
                    }
                } else {
                    showError("only use number in this field");
                }
                taxRate.setText("");
            }
        };
        submit2.setOnMouseClicked(event2);
        pane.getChildren().add(submit2);
        pane.getChildren().add(taxRate);

        Button submit3 = new Button();
        submit3.setBackground(null);
        submit3.setText("SUBMIT");
        submit3.setLayoutX(640);
        submit3.setLayoutY(780);
        submit3.setMinSize(100, 100);
        addedButtons.add(submit3);


        l = new Label(" ");
        l.setTextFill(Color.BLACK);
        slider = new Slider();
        slider.setMin(-5);
        slider.setMax(+5);
        slider.setValue(0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setBlockIncrement(1);

        slider.valueProperty().addListener(
                new ChangeListener<Number>() {

                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue, Number newValue) {
                        rate = newValue.intValue();
                        l.setText("Fear Rate: " + newValue.intValue());
                    }
                });
        slider.setLayoutX(500);
        slider.setLayoutY(790);
        l.setLayoutX(530);
        l.setLayoutY(820);

        EventHandler<MouseEvent> event3 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TileManager.time = (TileManager.minute[0] + ":" + TileManager.seconds[0]);
                TileManager.gameLog.append(TileManager.time + '#' + "LEFT_CLICK" + '#' + "RATE_BUTTON" +  '\n');
                int Rate = rate;
                String output = String.valueOf(empireMenu.setFearRate(Rate));
                if (!output.equals("successful")) {
                    showError(output);
                }
            }
        };

        submit3.setOnMouseClicked(event3);
        pane.getChildren().add(submit3);
        pane.getChildren().addAll(slider, l);

    }

    public void clearPane(Pane pane) {
        pane.getChildren().removeAll(addedButtons);
        pane.getChildren().removeAll(addedText);
        pane.getChildren().removeAll(l, slider);
        addedText.clear();
        addedButtons.clear();
    }

    public void showError(String output) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("DROP BUILDING FAILED");
        error.setContentText(output);
        error.show();
    }

}

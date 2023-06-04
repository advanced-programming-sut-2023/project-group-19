package view.GameButtons;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Manage;
import view.ImageAndBackground.BottomBarImages;
import view.ImageAndBackground.BuildingImages;

import java.util.ArrayList;

public class EmpireButtons {

    public ArrayList<Button> addedButtons = new ArrayList<>();
    public ArrayList<Text> addedText = new ArrayList<>();
    public BottomBarImages testbottomBarImages;

    public void createButtons(Pane pane, BottomBarImages bottomBarImages, BuildingImages buildingImages) {
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
                clearPane(pane);
                showPopularityFactors(pane, buildingImages);
            }
        });

        setRateButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                clearPane(pane);
                setRateMenu(pane);
            }
        });

    }

    public void showPopularityFactors(Pane pane, BuildingImages buildingImages) {
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
            sickness.setText("Food : " + 0);
            sickness.setStroke(Color.GREEN);
        } else {
            sickness.setText("Food : " + -2);
            sickness.setStroke(Color.RED);
        }
        sickness.setLayoutX(600);
        sickness.setLayoutY(715);
        addedText.add(sickness);
        pane.getChildren().add(sickness);


        Text popularity = new Text();
        popularity.setText("Popularity : " + Manage.getCurrentEmpire().getPopularity());
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
                clearPane(pane);
                createButtons(pane, testbottomBarImages, buildingImages);
            }
        };
        returnIconButton.setOnMouseClicked(event);
    }

    public void setRateMenu(Pane pane) {

    }

    public void clearPane(Pane pane) {
        pane.getChildren().removeAll(addedButtons);
        pane.getChildren().removeAll(addedText);
        addedText.clear();
        addedButtons.clear();
    }
}

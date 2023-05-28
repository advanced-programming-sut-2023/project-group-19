package view.Controllers;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ShopMenuController {
    public void showPriceList(String list, Pane pane){
        HBox hBox = new HBox();
        hBox.setPrefSize(30,30);
        Text text = new Text();
        text.setText(list);
        text.setStyle("-fx-font-size: 8");
        hBox.getChildren().add(text);
        pane.getChildren().add(hBox);
    }

    public void setButtons(){

    }

}

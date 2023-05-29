package view.Controllers;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ShopMenuController {
    public void showPriceList(String list, Pane pane){
        VBox vBox = new VBox();
        vBox.setPrefSize(400,600);
        Text text = new Text();
        text.setText(list);
        text.setStyle("-fx-font-size: 28");
        vBox.getChildren().add(text);
        pane.getChildren().add(vBox);
    }

    public void setButtons(){

    }

}

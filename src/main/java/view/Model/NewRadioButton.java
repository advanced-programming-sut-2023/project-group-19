package view.Model;

import javafx.scene.control.RadioButton;
import model.Map;

public class NewRadioButton extends RadioButton {
    private Map map ;
    public NewRadioButton(String s){
        super(s);
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}

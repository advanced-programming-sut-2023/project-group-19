package view.Model;

import javafx.scene.control.Button;

public class NewButton extends Button {
    private int x;
    private int y;
    public NewButton(int x , int y){
        super();
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

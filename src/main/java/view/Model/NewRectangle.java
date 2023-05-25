package view.Model;

import javafx.scene.shape.Rectangle;

import java.awt.*;

public class NewRectangle extends Rectangle {
    private int x2;
    private int y2;
    public NewRectangle(int x , int y){
        super();
        this.x2 = x;
        this.y2 = y;
    }

    public int getX2() {
        return x2;
    }

    public void setX(int x) {
        this.x2 = x;
    }

    public int getY2() {
        return y2;
    }

    public void setY(int y) {
        this.y2 = y;
    }
}

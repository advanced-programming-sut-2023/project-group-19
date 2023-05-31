package view.Model;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import model.Building.Building;
import model.Human.Troop.Army;

import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class NewButton extends Button {
    private int x;
    private int y;
    private Building building;
    private ImageView imageView;
    private ArrayList<Army> army = new ArrayList<>();
    private boolean isSelected = false;

    public NewButton(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
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

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public ArrayList<Army> getArmy() {
        return army;
    }

    public void setArmy(ArrayList<Army> army) {
        this.army = army;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

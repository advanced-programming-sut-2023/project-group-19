package view.Model;

import javafx.scene.control.Button;
import model.Building.Building;
import model.Human.Troop.Army;

import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class NewButton extends Button {
    private int x;
    private int y;
    private Building building;
    private ArrayList<Army> army = new ArrayList<>();

    public NewButton(int x, int y) {
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
}

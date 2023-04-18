package model.Building;

import model.Empire;

public abstract class Building {
    private Empire owner;
    Building (Empire government){
        this.owner = government;
    }

    public Empire getOwner() {
        return owner;
    }

    public void setOwner(Empire owner) {
        this.owner = owner;
    }
}

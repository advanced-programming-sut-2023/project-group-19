package model.Building;

import model.Empire;

import java.util.HashMap;

public class Tunnel extends Building {
    Tunnel(Empire government) {
        super(government);
    }

    private Names names;

    public Names getNames() {
        return names;
    }

    public void tunnel() {
        names = Names.TUNNEL;

    }
}

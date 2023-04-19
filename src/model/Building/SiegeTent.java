package model.Building;

import model.Empire;

import java.util.HashMap;

public class SiegeTent extends Building {
    SiegeTent(Empire government) {
        super(government);
    }

    private Names names;

    public Names getNames() {
        return names;
    }

    //TODO add WORKER
    public void siegeTent() {
        names = Names.SIEGE_TENT;
    }
}

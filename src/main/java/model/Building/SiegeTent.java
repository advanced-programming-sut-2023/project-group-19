package model.Building;

import model.Empire;

import java.util.HashMap;

public class SiegeTent extends Building implements BuildingHPAndGroundType {
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

    @Override
    public int maxHp() {
        return 0;
    }

    @Override
    public int hp() {
        return 0;
    }

    @Override
    public String groundType() {
        return null;
    }
}

package model.Building;

import model.Empire;

public class Tunnel extends Building implements BuildingConstantFunctions {
    public Tunnel(Empire government) {
        super(government);
    }
    public int length;
    private Names names;

    public Names getNames() {
        return names;
    }

    public void tunnel() {
        names = Names.TUNNEL;

    }

    public int getLength() {
        return length;
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

    @Override
    public String showBuildingName() {
        return names.getName();
    }
}

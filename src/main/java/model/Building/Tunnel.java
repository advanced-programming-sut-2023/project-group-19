package model.Building;

import model.Empire;

public class Tunnel extends Building implements BuildingConstructorsFunctions {
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

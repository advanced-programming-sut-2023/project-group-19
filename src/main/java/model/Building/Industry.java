package model.Building;

import model.Empire;
import model.GroundType;


public class Industry extends Building implements BuildingConstantFunctions {

    public Industry(Empire government) {
        super(government);
    }

    public Names getNames() {
        return this.name;
    }

    public void setNames(Names names) {
        this.name = names;
    }

    public void createBuildingCost(int wood, int stone, int gold, int iron, int oil) {
        this.cost.put("wood", wood);
        this.cost.put("stone", stone);
        this.cost.put("gold", gold);
        this.cost.put("iron", iron);
        this.cost.put("oil", oil);
    }

    public void createBuildingWorkersNeeded(int engineer, int worker) {
        this.workersNeeded.put("engineer", engineer);
        this.workersNeeded.put("worker", worker);

    }

    public void mill() {
        this.name = Names.MILL;
        this.hp = 500;
        this.maxHp = 500;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        createBuildingCost(20, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 3);
    }

    public void ironDig() {
        this.name = Names.IRON_DIG;
        this.hp = 500;
        this.maxHp = 500;
        this.requiredGroundType = GroundType.IRON.getGroundType();
        createBuildingCost(20, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 2);
    }

    public void oxTether() {
        this.name = Names.OX_TETHER;
        this.hp = 500;
        this.maxHp = 500;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        createBuildingCost(5, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 1);
    }

    public void pitchRig() {
        this.name = Names.PITCH_RIG;
        this.hp = 500;
        this.maxHp = 500;
        this.requiredGroundType = GroundType.PLAIN.getGroundType();
        createBuildingCost(20, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 1);
    }

    public void quarry() {
        this.name = Names.QUARRY;
        this.hp = 500;
        this.maxHp = 500;
        this.requiredGroundType = GroundType.STONE.getGroundType();
        createBuildingCost(20, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 3);
    }

    public void woodCutter() {
        this.name = Names.WOODCUTTER;
        this.hp = 500;
        this.maxHp = 500;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        createBuildingCost(3, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 1);
    }

    @Override
    public int maxHp() {
        return this.maxHp;
    }

    @Override
    public int hp() {
        return this.hp;
    }

    @Override
    public String groundType() {
        return this.requiredGroundType;
    }

    @Override
    public int height() {
        return this.height;
    }

    @Override
    public String showBuildingName() {
        return this.name.getName();
    }
}

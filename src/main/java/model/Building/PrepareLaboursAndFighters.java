package model.Building;

import model.Empire;
import model.GroundType;

import java.util.HashMap;

public class PrepareLaboursAndFighters extends Building implements BuildingConstantFunctions {
    public PrepareLaboursAndFighters(Empire government) {
        super(government);
    }


    public String getName() {
        return this.name.getName();
    }

    public void setName(Names name) {
        this.name = name;
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

    public void barracks() {
        this.name = Names.BARRACK;
        createBuildingCost(0, 15, 0, 0, 0);
        createBuildingWorkersNeeded(0, 0);
    }

    public void mercenary() {
        this.hp = 400;
        this.maxHp = 400;
        this.name = Names.MERCENARY_POST;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        createBuildingCost(10, 0, 0, 0, 0);
        createBuildingWorkersNeeded(0, 0);
    }

    public void engineerGuild() {
        this.hp = 400;
        this.maxHp = 400;
        this.name = Names.ENGINEER_GUILD;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        createBuildingCost(10, 0, 100, 0, 0);
        createBuildingWorkersNeeded(0, 0);
    }

    public void tunnelerGuild() {
        this.hp = 400;
        this.maxHp = 400;
        this.requiredGroundType = GroundType.DEFAULT.getGroundType();
        this.name = Names.TUNNELER;
        createBuildingCost(10, 0, 100, 0, 0);
        createBuildingWorkersNeeded(0, 0);
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

package model.Human.Troop;

import model.Empire;
import model.Human.Human;
import model.Human.Names;

public class ArabArmy extends Army {
    public ArabArmy(Empire government) {
        super(government);
    }

    private Names names;

    public Names getNames() {
        return names;
    }

    private String armyForm;

    public void setNames(Names names) {
        this.names = names;
    }

    @Override
    public String getArmyForm() {
        return armyForm;
    }

    @Override
    public void setArmyForm(String armyForm) {
        this.armyForm = armyForm;
    }

    private int hp;
    private int speed;
    private int defencePower;
    private int attackPower;
    private int attackRange;

    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDefencePower() {
        return defencePower;
    }

    public void setDefencePower(int defencePower) {
        this.defencePower = defencePower;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public void archer() {
        names = Names.ARCHER;

    }

    public void Crossbowmen() {
        names = Names.CROSSBOWMEN;

    }

    public void SpearMen() {
        names = Names.SPEAR_MEN;

    }

    public void PikeMen() {
        names = Names.PIKE_MEN;

    }

    public void MaceMen() {
        names = Names.MACE_MEN;

    }

    public void Swordsmen() {
        names = Names.SWORDSMEN;

    }

    public void Knight() {
        names = Names.KNIGHT;

    }

    public void Tunneler() {
        names = Names.TUNNELER;

    }

    public void LadderMen() {
        names = Names.LADDER_MEN;

    }

    public void BlackMonk() {
        names = Names.BLACK_MONK;

    }


}

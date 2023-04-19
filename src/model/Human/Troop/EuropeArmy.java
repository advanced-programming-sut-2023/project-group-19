package model.Human.Troop;

import model.Empire;
import model.Human.Human;
import model.Human.Names;

import java.security.PublicKey;

public class EuropeArmy extends Army {
    public EuropeArmy(Empire government) {
        super(government);
    }

    private Names names;

    public Names getNames() {
        return names;
    }

    private String ArmyForm;

    public void setNames(Names names) {
        this.names = names;
    }

    @Override
    public String getArmyForm() {
        return ArmyForm;
    }

    @Override
    public void setArmyForm(String armyForm) {
        ArmyForm = armyForm;
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

    public void ArcherBow() {
        names = Names.ARCHER_BOW;

    }

    public void Slaves() {
        names = Names.SLAVES;

    }

    public void Slingers() {
        names = Names.SLINGERS;

    }

    public void Assassins() {
        names = Names.ASSASSINS;
    }

    public void HorseArchers() {
        names = Names.HORSE_ARCHERS;

    }

    public void ArabianSwordsmen() {
        names = Names.ARABIAN_SWORDSMEN;

    }

    public void FireThrowers() {
        names = Names.FireThrowers;

    }
}

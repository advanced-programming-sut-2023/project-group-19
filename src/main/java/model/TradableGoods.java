package model;

public enum TradableGoods {
    APPLE("apple"),
    BREAD("bread"),
    CHEESE("cheese"),
    MEAT("meat"),
    LEATHER_ARMOUR("leatherArmour"),
    METAL_ARMOUR("metalArmour"),
    BOW("bow"),
    SWORD("sword"),
    MACE("mace"),
    SPEAR("spear"),
    PEAK("peak"),
    HORSE("horse"),
    WOOD("wood"),
    IRON("iron"),
    STONE("stone"),
    OAT("oat"),
    WHEAT("wheat"),
    BEER("beer"),
    FLOUR("flour"),
    OIL("oil"),
    ARCHER("archer"),
    SPEAR_MAN("spearMan"),
    MACE_MAN("maceMan"),
    CROSSBOWMAN("crossbowMan"),
    PIKE_MAN("pikeMan"),
    SWORD_MAN("swordMan"),
    KNIGHT("knight"),
    BLACK_MONK("blackMonk"),
    ARABIAN_BOW("arabianBow"),
    SLAVE("slave"),
    SLINGER("slinger"),
    ASSASSIN("assassin"),
    HORSE_ARCHER("horseArcher"),
    ARABIAN_SWORD_MAN("arabianSwordMan"),
    FIRE_THROWER("fireThrower"),
    ENGINEER("engineer"),
    LADDER_MAN("ladderMan"),
    TUNNELER("tunneler"),
    CATAPULT("catapult"),
    TREBUCHET("trebuchet"),
    SIEGE_TOWER("siegeTower"),
    FIRE_BALLISTA("fireBallista"),
    BATTERING_RAM("batteringRam"),
    PORTABLE_SHIELD("portableShield");


    private  String goodName;

    TradableGoods (String goodName) {
        this.goodName = goodName;
    }

    public String getGoodName() {
        return goodName;
    }
}

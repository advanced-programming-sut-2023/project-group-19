package controller;

import model.Building.*;
import model.Empire;
import model.GroundType;
import model.Human.Names;
import model.Human.Troop.*;
import model.Manage;
import model.Map;
import model.Obstacle.ObstacleName;
import view.Messages.GameMenuMessages;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class GameController {
    //TODO : DropUnit minus the count
    //TODO : Minus 1 xCoordinates and yCoordinates
    //TODO : SAVE PAST COORDINATE OF ALL ARMIES
    //TODO : WHAT HAPPENS IF THE ENEMY DIES WHEN WE HAVE AN OFFENSIVE
    //TODO : WE SHOULD SET THE FORM OF ARMY WHEN THE PATH.LIST IS NULL
    private static final int mapSize = Map.mapSize;
    public static GameController gameController;
    public ArrayList<Army> selectedUnit = new ArrayList<>();
    public ArrayList<ArchersAndThrowers> throwers = new ArrayList<>();

    public GameMenuMessages selectUnit(Matcher x, Matcher y) {
        int flag = 0;
        int xCoordinate = Integer.parseInt(x.group("x"));
        int yCoordinate = Integer.parseInt(y.group("y"));
        if (validCoordinates(xCoordinate, yCoordinate)) {
            if (!Map.getTroopMap()[xCoordinate][yCoordinate].isEmpty()) {
                selectedUnit.clear();
                for (int i = 0; i < Map.getTroopMap()[xCoordinate][yCoordinate].size(); i++) {
                    Army army = Map.getTroopMap()[xCoordinate][yCoordinate].get(i);
                    if (army.getOwner().getName().equals(Manage.getCurrentEmpire().getName())) {
                        flag = 1;
                        selectedUnit.add(army);
                    }
                }
                if (flag == 1) return GameMenuMessages.SELECT_UNIT_SUCCEEDED;
                else return GameMenuMessages.NO_UNIT_IN_CELL;
            } else return GameMenuMessages.NO_UNIT_IN_CELL;
        }
        return GameMenuMessages.COORDINATES_OUT_OF_BOUNDS;
    }

    private static boolean isArcher(Army army) {
        return army instanceof ArchersAndThrowers;
    }

    public GameMenuMessages setFormOfUnit(Matcher xCoordinate, Matcher yCoordinate, Matcher form) {
        int flag = 0;
        int x = Integer.parseInt(xCoordinate.group("x"));
        int y = Integer.parseInt(yCoordinate.group("y"));
        String formOfUnit = form.group("type");
        if (validCoordinates(x, y)) {
            if (!Map.getTroopMap()[x][y].isEmpty()) {
                for (Army army : Map.getTroopMap()[x][y]) {
                    if (army.getOwner().equals(Manage.getCurrentEmpire())) {
                        flag = 1;
                        army.setArmyForm(formOfUnit);
                    }
                }
                if (flag == 1) return GameMenuMessages.SUCCESS;
                else return GameMenuMessages.NO_UNIT_IN_CELL;
            } else return GameMenuMessages.NO_UNIT_IN_CELL;
        }
        return GameMenuMessages.COORDINATES_OUT_OF_BOUNDS;
    }

    public GameMenuMessages readyToAttack(Matcher enemy) {
        int x = Integer.parseInt(enemy.group("x"));
        int y = Integer.parseInt(enemy.group("y"));
        if (validCoordinates(x, y)) {
            for (Army army : selectedUnit) {
                army.isIntFight = true;
            }
            String unitMoved = moveUnit(x, y).getMessages();
            //TODO : DO WE NEED TO CALL OTHER ATTACK FUNCTIONS ?
            return GameMenuMessages.ATTACK_ORDER_HANDLED;
        }
        return GameMenuMessages.COORDINATES_OUT_OF_BOUNDS;
    }

    public GameMenuMessages attackAllSelectedArchers(Matcher xCoordinate, Matcher yCoordinate) {
        int x = Integer.parseInt(xCoordinate.group("x"));
        int y = Integer.parseInt(yCoordinate.group("y"));
        x--;
        y--;
        if (validCoordinates(x, y)) {
            for (Army army : selectedUnit) {
                if (!isArcher(army)) continue;
                for (Army enemy : Map.getTroopMap()[x][y]) {
                    if (enemy.getEmpire().equals(army.getEmpire()) || enemy.getHp() <= 0) continue;
                    int newHitPoint = enemy.hp() - army.getAttackPower();
                    enemy.setHp(newHitPoint);
                    break;
                }
            }
            return GameMenuMessages.SUCCESS;
        }
        return GameMenuMessages.COORDINATES_OUT_OF_BOUNDS;
    }

    public GameMenuMessages disbandUnit() {
        if (selectedUnit.isEmpty()) return GameMenuMessages.NO_UNIT_SELECTED;
        for (Army army : selectedUnit) {
            int x = army.getCurrentX() - 1;
            int y = army.getCurrentY() - 1;
            Empire empire = army.getEmpire();
            removeKilledUnitFromEmpireHashmap(army.getNames().getName(), empire);
            empire.empireArmy.remove(army);
            Map.getTroopMap()[x][y].remove(army);
        }
        return GameMenuMessages.SUCCESS;
    }

    public void setStateArmy() {
        selectedUnit.clear();
        for (Army army : Manage.getCurrentEmpire().empireArmy) {
            if (isArcher(army) || army.getArmyForm().equals(Names.STANDING_AMRY.getName())
                    || army.isIntFight || (army.myPath != null && !army.hasMovedForDefensiveState ))
                continue;
            selectedUnit.add(army);
            System.out.println("in set state army");
            findEnemyInRange(army, army.getArmyForm());
            selectedUnit.clear();
        }
    }

    public GameMenuMessages dropUnit(Matcher x1, Matcher y1, Matcher count, Matcher type) {
        int x = Integer.parseInt(x1.group("x"));
        int y = Integer.parseInt(y1.group("y"));
        int countOfUnits = Integer.parseInt(count.group("count"));
        String typeOfUnit = type.group("type");
        if (countOfUnits <= 0) return GameMenuMessages.INVALID_NUMBER_OF_UNITS_TO_DROP;
        if (validCoordinates(x, y)) {
            if (checkGroundTypeForUnits(x, y)) {
                if (checkTypeOfUnitWithLocation(x, y, typeOfUnit)) {
                    if (addUnitsToMap(x, y, countOfUnits, typeOfUnit)) {
                        return GameMenuMessages.SUCCESS;
                    } else return GameMenuMessages.NOT_ENOUGH_UNITS_TO_DEPLOY;
                } else return GameMenuMessages.IMPROPER_UNIT;
            } else return GameMenuMessages.IMPROPER_LOCATION;
        }
        return GameMenuMessages.COORDINATES_OUT_OF_BOUNDS;
    }

    private static boolean addUnitsToMap(int x, int y, int count, String typeOfUnit) {
        switch (typeOfUnit) {
            case "Archer":
                if (Manage.getCurrentEmpire().getEuropeArcherCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        ArchersAndThrowers archer = new ArchersAndThrowers(Manage.getCurrentEmpire());
                        archer.archer(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(archer);
                        Map.getTroopMap()[x][y].add(archer);
                    }
                    Manage.getCurrentEmpire().setEuropeArcherCount(Manage.getCurrentEmpire().getEuropeArcherCount() - count);
                    return true;
                } else return false;
            case "Crossbowmen":
                if (Manage.getCurrentEmpire().getCrossbowManCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        ArchersAndThrowers crossBowMan = new ArchersAndThrowers(Manage.getCurrentEmpire());
                        crossBowMan.Crossbowmen(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(crossBowMan);
                        Map.getTroopMap()[x][y].add(crossBowMan);
                    }
                    Manage.getCurrentEmpire().setCrossbowManCount(Manage.getCurrentEmpire().getCrossbowManCount() - count);
                    return true;
                } else return false;
            case "ArcherBow":
                if (Manage.getCurrentEmpire().getArabianBowCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        ArchersAndThrowers archerBow = new ArchersAndThrowers(Manage.getCurrentEmpire());
                        archerBow.ArcherBow(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(archerBow);
                        Map.getTroopMap()[x][y].add(archerBow);
                    }
                    Manage.getCurrentEmpire().setArabianBowCount(Manage.getCurrentEmpire().getArabianBowCount() - count);
                    return true;
                } else return false;
            case "Slingers":
                if (Manage.getCurrentEmpire().getSlingerCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        ArchersAndThrowers slingers = new ArchersAndThrowers(Manage.getCurrentEmpire());
                        slingers.Slingers(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(slingers);
                        Map.getTroopMap()[x][y].add(slingers);
                    }
                    Manage.getCurrentEmpire().setSlingerCount(Manage.getCurrentEmpire().getSlingerCount() - count);
                    return true;
                } else return false;
            case "HorseArchers":
                if (Manage.getCurrentEmpire().getHorseArcherCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        ArchersAndThrowers horseArcher = new ArchersAndThrowers(Manage.getCurrentEmpire());
                        horseArcher.HorseArchers(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(horseArcher);
                        Map.getTroopMap()[x][y].add(horseArcher);
                    }
                    Manage.getCurrentEmpire().setHorseArcherCount(Manage.getCurrentEmpire().getHorseArcherCount() - count);
                    return true;
                } else return false;
            case "FireThrowers":
                if (Manage.getCurrentEmpire().getFireThrowerCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        ArchersAndThrowers fireThrower = new ArchersAndThrowers(Manage.getCurrentEmpire());
                        fireThrower.FireThrowers(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(fireThrower);
                        Map.getTroopMap()[x][y].add(fireThrower);
                    }
                    Manage.getCurrentEmpire().setFireThrowerCount(Manage.getCurrentEmpire().getFireThrowerCount() - count);
                    return true;
                } else return false;
            case "SpearMen":
                if (Manage.getCurrentEmpire().getSpearManCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        Climbers spearMen = new Climbers(Manage.getCurrentEmpire());
                        spearMen.SpearMen(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(spearMen);
                        Map.getTroopMap()[x][y].add(spearMen);
                    }
                    Manage.getCurrentEmpire().setSpearManCount(Manage.getCurrentEmpire().getSpearManCount() - count);
                    return true;
                } else return false;
            case "MaceMen":
                if (Manage.getCurrentEmpire().getMaceManCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        Climbers maceMen = new Climbers(Manage.getCurrentEmpire());
                        maceMen.MaceMen(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(maceMen);
                        Map.getTroopMap()[x][y].add(maceMen);
                    }
                    Manage.getCurrentEmpire().setMaceManCount(Manage.getCurrentEmpire().getMaceManCount() - count);
                    return true;
                } else return false;
            case "LadderMen":
                if (Manage.getCurrentEmpire().getLadderManCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        Climbers ladderMen = new Climbers(Manage.getCurrentEmpire());
                        ladderMen.LadderMen(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(ladderMen);
                        Map.getTroopMap()[x][y].add(ladderMen);
                    }
                    Manage.getCurrentEmpire().setLadderManCount(Manage.getCurrentEmpire().getLadderManCount() - count);
                    return true;
                } else return false;
            case "Assassins":
                if (Manage.getCurrentEmpire().getAssassinCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        Climbers assassin = new Climbers(Manage.getCurrentEmpire());
                        assassin.Assassins(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(assassin);
                        Map.getTroopMap()[x][y].add(assassin);
                    }
                    Manage.getCurrentEmpire().setAssassinCount(Manage.getCurrentEmpire().getAssassinCount() - count);
                    return true;
                } else return false;
            case "Engineer":
                if (Manage.getCurrentEmpire().getEngineerCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        Engineer engineer = new Engineer(Manage.getCurrentEmpire());
                        engineer.engineer(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(engineer);
                        Map.getTroopMap()[x][y].add(engineer);
                    }
                    Manage.getCurrentEmpire().setEngineerCount(Manage.getCurrentEmpire().getEngineerCount() - count);
                    return true;
                } else return false;
            case "BlackMonk":
                if (Manage.getCurrentEmpire().getBlackMonkCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        Soldiers blackMonk = new Soldiers(Manage.getCurrentEmpire());
                        blackMonk.BlackMonk(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(blackMonk);
                        Map.getTroopMap()[x][y].add(blackMonk);
                    }
                    Manage.getCurrentEmpire().setBlackMonkCount(Manage.getCurrentEmpire().getBlackMonkCount() - count);
                    return true;
                } else return false;
            case "Knight":
                if (Manage.getCurrentEmpire().getKnightCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        Soldiers knight = new Soldiers(Manage.getCurrentEmpire());
                        knight.Knight(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(knight);
                        Map.getTroopMap()[x][y].add(knight);
                    }
                    Manage.getCurrentEmpire().setKnightCount(Manage.getCurrentEmpire().getKnightCount() - count);
                    return true;
                } else return false;
            case "Swordsmen":
                if (Manage.getCurrentEmpire().getSwordManCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        Soldiers swordMen = new Soldiers(Manage.getCurrentEmpire());
                        swordMen.Swordsmen(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(swordMen);
                        Map.getTroopMap()[x][y].add(swordMen);
                    }
                    Manage.getCurrentEmpire().setSwordManCount(Manage.getCurrentEmpire().getSwordManCount() - count);
                    return true;
                } else return false;
            case "PikeMen":
                if (Manage.getCurrentEmpire().getPikeManCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        Soldiers pikeMen = new Soldiers(Manage.getCurrentEmpire());
                        pikeMen.PikeMen(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(pikeMen);
                        Map.getTroopMap()[x][y].add(pikeMen);
                    }
                    Manage.getCurrentEmpire().setPikeManCount(Manage.getCurrentEmpire().getPikeManCount() - count);
                    return true;
                } else return false;
            case "Slaves":
                if (Manage.getCurrentEmpire().getSlaveCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        Soldiers slave = new Soldiers(Manage.getCurrentEmpire());
                        slave.Slaves(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(slave);
                        Map.getTroopMap()[x][y].add(slave);
                    }
                    Manage.getCurrentEmpire().setSlaveCount(Manage.getCurrentEmpire().getSlaveCount() - count);
                    return true;
                } else return false;
            case "ArabianSwordsmen":
                if (Manage.getCurrentEmpire().getArabianSwordManCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        Soldiers arabSwordMen = new Soldiers(Manage.getCurrentEmpire());
                        arabSwordMen.ArabianSwordsmen(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(arabSwordMen);
                        Map.getTroopMap()[x][y].add(arabSwordMen);
                    }
                    Manage.getCurrentEmpire().setArabianSwordManCount(Manage.getCurrentEmpire().getArabianSwordManCount() - count);
                    return true;
                } else return false;
            case "Tunneler":
                if (Manage.getCurrentEmpire().getTunnelerCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        Tunneler tunneler = new Tunneler(Manage.getCurrentEmpire());
                        tunneler.Tunneler(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(tunneler);
                        Map.getTroopMap()[x][y].add(tunneler);
                    }
                    Manage.getCurrentEmpire().setTunnelerCount(Manage.getCurrentEmpire().getTunnelerCount() - count);
                    return true;
                } else return false;
            case "Catapult":
                if (Manage.getCurrentEmpire().getCatapultCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        ArchersAndThrowers catapult = new ArchersAndThrowers(Manage.getCurrentEmpire());
                        catapult.catapult(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(catapult);
                        Map.getTroopMap()[x][y].add(catapult);
                    }
                    Manage.getCurrentEmpire().setCatapultCount(Manage.getCurrentEmpire().getCatapultCount() - count);
                    return true;
                } else return false;
            case "Trebuchet":
                if (Manage.getCurrentEmpire().getTrebuchetCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        ArchersAndThrowers trebuchet = new ArchersAndThrowers(Manage.getCurrentEmpire());
                        trebuchet.trebuchet(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(trebuchet);
                        Map.getTroopMap()[x][y].add(trebuchet);
                    }
                    Manage.getCurrentEmpire().setTrebuchetCount(Manage.getCurrentEmpire().getTrebuchetCount() - count);
                    return true;
                } else return false;
            case "SiegeTower":
                if (Manage.getCurrentEmpire().getSiegeTowerCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        ArchersAndThrowers siegeTower = new ArchersAndThrowers(Manage.getCurrentEmpire());
                        siegeTower.siegeTower(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(siegeTower);
                        Map.getTroopMap()[x][y].add(siegeTower);
                    }
                    Manage.getCurrentEmpire().setSiegeTowerCount(Manage.getCurrentEmpire().getSiegeTowerCount() - count);
                    return true;
                } else return false;
            case "FireBallista":
                if (Manage.getCurrentEmpire().getFireBalistaCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        ArchersAndThrowers fireBallista = new ArchersAndThrowers(Manage.getCurrentEmpire());
                        fireBallista.fireBallista(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(fireBallista);
                        Map.getTroopMap()[x][y].add(fireBallista);
                    }
                    Manage.getCurrentEmpire().setFireBalistaCount(Manage.getCurrentEmpire().getFireBalistaCount() - count);
                    return true;
                } else return false;
            case "BatteringRam":
                if (Manage.getCurrentEmpire().getBatteringRamCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        ArchersAndThrowers batteringRam = new ArchersAndThrowers(Manage.getCurrentEmpire());
                        batteringRam.batteringRam(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(batteringRam);
                        Map.getTroopMap()[x][y].add(batteringRam);
                    }
                    Manage.getCurrentEmpire().setBatteringRamCount(Manage.getCurrentEmpire().getBatteringRamCount() - count);
                    return true;
                } else return false;
            case "PortableShield":
                if (Manage.getCurrentEmpire().getPortableShieldCount() >= count) {
                    for (int i = 0; i < count; i++) {
                        ArchersAndThrowers portableShield = new ArchersAndThrowers(Manage.getCurrentEmpire());
                        portableShield.portableShield(x, y);
                        Manage.getCurrentEmpire().empireArmy.add(portableShield);
                        Map.getTroopMap()[x][y].add(0, portableShield);
                    }
                    Manage.getCurrentEmpire().setPortableShieldCount(Manage.getCurrentEmpire().getPortableShieldCount() - count);
                    return true;
                } else return false;
        }
        return false;
    }

    public GameMenuMessages buildEquipment(Matcher name) {
        String nameOfEquipment = name.group("equipmentName");
        Empire empire = Manage.getCurrentEmpire();
        for (java.util.Map.Entry<String, Integer> siegeTentTroop : empire.getSiegeTentTroopsCount().entrySet()) {
            if (siegeTentTroop.getKey().equals(nameOfEquipment)) {
                empire.getSiegeTentTroopsCount().replace(nameOfEquipment, siegeTentTroop.getValue() + 1);
                return GameMenuMessages.SUCCESS;
            }
        }
        return GameMenuMessages.WRONG_SIEGE_NAME;
    }

    private static boolean checkGroundTypeForUnits(int x, int y) {
        return !Map.notPassable[x][y];
    }

    private static boolean checkTypeOfUnitWithLocation(int x, int y, String type) {
        if (Map.getBuildingMap()[x][y].size() != 0) {
            if ((!Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.TUNNEL.getName()) && !type.equals(Names.TUNNELER.getName())
                    && !Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.PITCH_DITCH.getName()) && !type.equals(Names.SPEAR_MEN.getName()))) {
                return true;
            } else
                return Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.TUNNEL.getName()) && type.equals(Names.TUNNELER.getName())
                        || Map.getBuildingMap()[x][y].get(0).getName().equals(model.Building.Names.PITCH_DITCH.getName()) && !type.equals(Names.SPEAR_MEN.getName());
        }
        return true;
    }

    private static void findEnemyInRange(Army army, String State) {
        int x = army.xCoordinate;
        int y = army.yCoordinate;
        int x1, x2, y1, y2;
        for (int i = 1; i <= army.getAttackRange(); i++) {
            x1 = x - i;
            x2 = x + i;
            y1 = y - i;
            y2 = y + i;
            if (x1 <= 0) x1 = 0;
            if (x2 >= mapSize) x2 = mapSize - 1;
            if (y1 <= 0) y1 = 0;
            if (y2 >= mapSize) y2 = mapSize - 1;
            if (State.equals(Names.OFFENSIVE.getName())) {
                if (moveUnitToEnemyLocationAngry(x, y, x1, x2, y1, y2, army, i)) return;
            }else {
                if (moveUnitToEnemyLocationDefensive(x, y, x1, x2, y1, y2, army, i)) return;
            }
        }
    }

    private static boolean isSameGridIntoRange(int x, int y, Army army, int chosenX, int chosenY) {
        int x1 = x - army.getAttackRange();
        int x2 = x + army.getAttackRange();
        int y1 = y - army.getAttackRange();
        int y2 = y + army.getAttackRange();
        if (x1 <= 0) x1 = 0;
        if (x2 >= mapSize) x2 = mapSize - 1;
        if (y1 <= 0) y1 = 0;
        if (y2 >= mapSize) y2 = mapSize - 1;
        return chosenX >= x1 && chosenX <= x2 && chosenY >= y1 && chosenY <= y2;
    }

    private static boolean moveUnitToEnemyLocationDefensive(int x, int y, int x1, int x2, int y1, int y2, Army army, int range) {
//        System.out.println("into defensive");
        for (Army enemy : Map.getTroopMap()[x][y]) {
            if (!enemy.getEmpire().equals(army.getEmpire())) return true;
        }
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
//                System.out.println("x is: " + i + " y is: " + j);
                if (i == x && j == y) continue;
                for (Army enemy : Map.getTroopMap()[i][j]) {
//                    System.out.println("x: " + i + " y: " + j);
                    if (army.getPastXcordinate() == army.getCurrentX() && army.getPastYcordinate() == army.getCurrentY()) {
                        army.hasMovedForDefensiveState = false;
                    }
                    if (!army.getEmpire().equals(enemy.getEmpire())) {
                        if (!army.hasMovedForDefensiveState) {
                            army.setPastXcordinate(x);
                            army.setPastYcordinate(y);
                            System.out.println("x ia: " + i + " y is: " + j);
                            gameController.moveUnit(i + 1, j + 1);
                            System.out.println("move from first location");
                            army.hasMovedForDefensiveState = true;
                            return true;
                        }
                        if (isSameGridIntoRange(army.getPastXcordinate(), army.getPastYcordinate(), army, i, j)) {
                            System.out.println("moing in middele of way");
                            gameController.moveUnit(i + 1, j + 1);
                            return true;
                        }
                    }
                }
            }
        }
        if (range == army.getAttackRange()) {
//            System.out.println("x is: " + army.getPastXcordinate() + "  y is: " + army.getPastXcordinate());
            System.out.println("return to first place");
            gameController.moveUnit(army.getPastXcordinate() + 1, army.getPastYcordinate() + 1);
            return true;
        } else {
            return false;
        }
    }

    //TODO : After every next turn please call it!
    public void setEnemyToTarget() {
        selectedUnit.clear();
        for (Army army : Manage.getCurrentEmpire().empireArmy) {
            checkIfTargetIsAlive(army);
            if (army.getEnemy() == null) continue;
            selectedUnit.add(army);
            gameController.moveUnit(army.getEnemy().xCoordinate, army.getEnemy().yCoordinate);
            selectedUnit.clear();
        }
    }

    private static void checkIfTargetIsAlive(Army army) {
        Empire empire = army.getEnemy().getOwner();
        for (Army army1 : empire.empireArmy) {
            if (army1.equals(army.getEnemy())) {
                return;
            }
        }
        army.setEnemy(null);
    }

    private static boolean moveUnitToEnemyLocationAngry(int x, int y, int x1, int x2, int y1, int y2, Army army, int range) {
        for (Army enemy : Map.getTroopMap()[x][y]) {
            if (!enemy.getEmpire().equals(army.getEmpire())) return true;
        }
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                for (Army enemy : Map.getTroopMap()[i][j]) {
                    if (enemy.getEmpire().equals(army.getEmpire()) || enemy.getHp() <= 0) continue;
                    army.setEnemy(enemy);
                    gameController.moveUnit(army.getEnemy().xCoordinate + 1, army.getEnemy().yCoordinate + 1);
                    return true;
                }
            }
        }
        if (range == army.getAttackRange()) {
            Army enemy;
            if ((enemy = army.getArcherAttacker()) != null) {
                army.setEnemy(enemy);
                gameController.moveUnit(army.getEnemy().xCoordinate + 1, army.getEnemy().yCoordinate + 1);
            }
        }
        return false;
    }

    //TODO : Special condition which we call setStateArmy first
    public GameMenuMessages moveUnit(int xCoordinate, int yCoordinate) {
        if (validCoordinates(xCoordinate, yCoordinate)) {
            if (selectedUnit.size() != 0) {
                if (checkGroundTypeForUnits(xCoordinate, yCoordinate)) {
                    isTrebuchet();
                    if (selectedUnit.size() != 0) {
                        if (setPathForUnits(xCoordinate, yCoordinate)) {
                            for (Army myUnit : Manage.getCurrentEmpire().empireArmy) {
                                List<Integer> pathList = myUnit.myPath;
                                if (pathList != null && pathList.size() != 0) {
                                    for (int i = 0; i < pathList.size(); i++) {
                                        if (myUnit.restOfMoves != 0) {
                                            // goal in here means next
                                            myUnit.goalXCoordinate = pathList.get(i) / PathFindingController.size;
                                            myUnit.goalYCoordinate = pathList.get(i) % PathFindingController.size;
                                            Map.getTroopMap()[myUnit.getCurrentX()][myUnit.getCurrentY()].remove(myUnit);
                                            if (validSquareBySquareCell(myUnit) || isPlain(myUnit)) {
                                                removeKilledUnitFromEmpireHashmap(myUnit.getNames().getName(), myUnit.getEmpire());
                                                Manage.getCurrentEmpire().empireArmy.remove(myUnit);
                                                break;
                                            }
                                            myUnit.xCoordinate = myUnit.goalXCoordinate;
                                            myUnit.yCoordinate = myUnit.goalYCoordinate;
                                            myUnit.restOfMoves--;
                                            Map.getTroopMap()[myUnit.xCoordinate][myUnit.yCoordinate].add(myUnit);
                                            pathList.remove(i);
                                            i--;
                                        }
                                    }
                                    if (pathList.size() <= myUnit.speed()) {
                                        if (myUnit.typeOfArmy().getName().equals(Names.PATROL_UNIT.getName())) {
                                            setPathForPatrols(myUnit.getStartX(), myUnit.getStartY(), myUnit);
                                        } else {
                                            myUnit.myPath = null;
                                        }
                                    }
                                }
                            }
                            if (isMyArmyDeployed()) return GameMenuMessages.ARMY_DEPLOYED;
                            else return GameMenuMessages.ARMY_IN_PROCESS_OF_DEPLOYING;
                        } else return GameMenuMessages.COORDINATES_OUT_OF_BOUNDS;
                    } else return GameMenuMessages.UNABLE_TO_MOVE_TREBUCHET;
                }
                return GameMenuMessages.LOCATION_CONTAINS_WATERSOURCES_OR_HIGH_PLACES;
            }
            return GameMenuMessages.NO_UNIT_SELECTED;
        }
        return GameMenuMessages.COORDINATES_OUT_OF_BOUNDS;
    }

    public boolean setPathForUnits(int xCoordinate, int yCoordinate) {
        if (validCoordinates(xCoordinate, yCoordinate)) {
            for (Army army : selectedUnit) {
                if (army.getNames().getName().equals(Names.ASSASSINS.getName()) ||
                        army.getNames().getName().equals(Names.LADDER_MEN.getName())) {
                    PathFindingController.notPassable = Map.wallPassable;
                } else {
                    PathFindingController.notPassable = Map.notPassable;
                }
                PathFindingController.wall = Map.wall;
                PathFindingController.startX = army.getCurrentX() - 1;
                PathFindingController.startY = army.getCurrentY() - 1;
                PathFindingController.goalX = xCoordinate - 1;
                PathFindingController.goalY = yCoordinate - 1;
                army.myPath = PathFindingController.pathFinding();
            }
            return true;
        }
        return false;
    }

    //TODO : Cancel selected unit where is necessary
    public void setPathForPatrols(int xCoordinate, int yCoordinate, Army patrol) {
        PathFindingController.notPassable = Map.notPassable;
        PathFindingController.wall = Map.wall;
        PathFindingController.startX = patrol.getCurrentX() - 1;
        PathFindingController.startY = patrol.getCurrentY() - 1;
        PathFindingController.goalX = xCoordinate - 1;
        PathFindingController.goalY = yCoordinate - 1;
        patrol.myPath = PathFindingController.pathFinding();
    }

    public boolean isMyArmyDeployed() {
        for (Army myArmy : selectedUnit) {
            if (myArmy.myPath != null) {
                return false;
            }
        }
        return true;
    }

    public void isTrebuchet() {
        for (int i = 0; i < selectedUnit.size(); i++) {
            if (selectedUnit.get(i) instanceof ArchersAndThrowers) {
                if (selectedUnit.get(i).getNames().getName().equals(Names.TREBUCHET.getName())) {
                    selectedUnit.remove(selectedUnit.get(i));
                    i--;
                }
            }
        }

    }

    public boolean validFinalLocation(int x, int y) {
        if(Map.getObstacleMap()[x][y].isEmpty()) return true ;

        return  Map.getObstacleMap()[x][y].get(0).getName() != ObstacleName.BIG_POND
                && Map.getObstacleMap()[x][y].get(0).getName() != ObstacleName.SMALL_POND
                && Map.getObstacleMap()[x][y].get(0).getName() != ObstacleName.RIVER
                && Map.getObstacleMap()[x][y].get(0).getName() != ObstacleName.SEA;
    }

    public boolean validSquareBySquareCell(Army myUnit) {
        if (!Map.getBuildingMap()[myUnit.goalXCoordinate][myUnit.goalYCoordinate].isEmpty()) {
            return (Map.getBuildingMap()[myUnit.goalXCoordinate][myUnit.goalYCoordinate].get(0) instanceof KillingPit ||
                    Map.getBuildingMap()[myUnit.goalXCoordinate][myUnit.goalYCoordinate].get(0) instanceof PitchDitch) &&
                    !(myUnit.getNames().getName().equals(Names.SPEAR_MEN.getName()));
        }
        return false;
    }

    public boolean isPlain(Army myUnit) {
        if (!Map.getObstacleMap()[myUnit.goalXCoordinate][myUnit.goalYCoordinate].isEmpty()) {
            return Map.getObstacleMap()[myUnit.goalXCoordinate][myUnit.goalYCoordinate].get(0).getName()
                    .getObstacleName().equals(GroundType.PLAIN.getGroundType());
        }
        return false;
    }

    public void patrolUnit(Matcher x1, Matcher y1, Matcher x2, Matcher y2) {
        int xOne = Integer.parseInt(x1.group("x"));
        int xTwo = Integer.parseInt(x2.group("x"));
        int yOne = Integer.parseInt(y1.group("y"));
        int yTwo = Integer.parseInt(y2.group("y"));
        if (validFinalLocation(xOne, yOne) && validFinalLocation(xTwo, yTwo)) {
            setCoordinatesForPatrols(xOne, yOne, xTwo, yTwo);
            String unitMoved = moveUnit(xTwo, yTwo).getMessages();
        } else System.out.println(GameMenuMessages.COORDINATES_OUT_OF_BOUNDS.getMessages());
    }

    public void setCoordinatesForPatrols(int x1, int y1, int x2, int y2) {
        for (Army army : selectedUnit) {
            army.setArmyForm(Names.PATROL_UNIT.getName());
            army.startXCoordinate = x1;
            army.startYCoordinate = y1;
            army.finalXCoordinate = x2;
            army.finalYCoordinate = y2;
        }
    }

    public GameMenuMessages stopPatrols(int x, int y) {
        if (validCoordinates(x, y)) {
            for (Army army : Manage.getCurrentEmpire().empireArmy) {
                if (army.getArmyForm().equals(Names.PATROL_UNIT.getName()) &&
                        army.getCurrentX() == x && army.getCurrentY() == y) {
                    army.setArmyForm(Names.STANDING_AMRY.getName());
                }
            }
            return GameMenuMessages.SUCCESS;
        }
        return GameMenuMessages.COORDINATES_OUT_OF_BOUNDS;
    }

    public GameMenuMessages PitchDitchHauntsEnemy(Matcher x1, Matcher y1) {
        int xOfPitch = Integer.parseInt(x1.group("x"));
        int yOfPitch = Integer.parseInt(y1.group("y"));
        ArchersAndThrowers archer;
        if (validCoordinates(xOfPitch, yOfPitch)) {
            if (!Map.getBuildingMap()[xOfPitch][yOfPitch].isEmpty() &&
                    Map.getBuildingMap()[xOfPitch][yOfPitch].get(0) instanceof PitchDitch) {
                if ((archer = checkIfSomeAreArchers()) != null) {
                    setPathForUnits(xOfPitch, yOfPitch);
                    if (archer.myPath.size() <= archer.getAttackRange()) {
                        ((PitchDitch) Map.getBuildingMap()[xOfPitch][yOfPitch].get(0)).fireState = true;
                        Map.getBuildingMap()[xOfPitch][yOfPitch].clear();
                        Map.getObstacleMap()[xOfPitch][yOfPitch].clear();
                        for (Army army : Map.getTroopMap()[xOfPitch][yOfPitch]) {
                            removeKilledUnitFromEmpireHashmap(army.getNames().getName(), army.getEmpire());
                        }
                        Map.getTroopMap()[xOfPitch][yOfPitch].clear();
                        archer.myPath.clear();
                        return GameMenuMessages.SUCCESS;
                    }
                    return GameMenuMessages.OUT_OF_ARCHER_RANGE;
                }
                return GameMenuMessages.IMPROPER_UNIT;
            }
            return GameMenuMessages.IMPROPER_LOCATION;
        }
        return GameMenuMessages.COORDINATES_OUT_OF_BOUNDS;
    }

    public ArchersAndThrowers checkIfSomeAreArchers() {
        int number = 0;
        for (Army army : selectedUnit) {
            if (army.getNames().getName().equals(Names.ARCHER.getName()) || army.getNames().getName().equals(Names.ARCHER_BOW.getName())
                    || army.getNames().getName().equals(Names.CROSSBOWMEN.getName()) || army.getNames().getName().equals(Names.HORSE_ARCHERS.getName())
                    || army.getNames().getName().equals(Names.FireThrowers.getName())) {
                return (ArchersAndThrowers) army;
            }
        }
        return null;
    }

    public GameMenuMessages pourOil(String direction) {
        int y0fPossibleEnemy;
        int xOfPossibleEnemy;
        int killCount = Manage.getCurrentEmpire().getEngineerCount();
        if (checkDirection(direction)) {
            for (int j = 0; j < Empire.pourOilCoordinate.size(); j++) {
                int x = Empire.pourOilCoordinate.get(j) / Map.mapSize;
                int y = Empire.pourOilCoordinate.get(j) % Map.mapSize;
                if (direction.equals(Names.NORTH.getName())) {
                    for (int i = -1; i <= 1; i++) {
                        y0fPossibleEnemy = y + i;
                        if (!Map.getTroopMap()[x - 2][y0fPossibleEnemy].isEmpty()) {
                            if (killCount != 0) {
                                killTroopsOfEnemy(x - 2, y0fPossibleEnemy, killCount);
                            }
                        }
                    }
                } else if (direction.equals(Names.SOUTH.getName())) {
                    for (int i = -1; i <= 1; i++) {
                        y0fPossibleEnemy = y + i;
                        if (!Map.getTroopMap()[x + 2][y0fPossibleEnemy].isEmpty()) {
                            if (killCount != 0) {
                                killTroopsOfEnemy(x + 2, y0fPossibleEnemy, killCount);
                            }
                        }
                    }
                } else if (direction.equals(Names.WEST.getName())) {
                    for (int i = -1; i <= 1; i++) {
                        xOfPossibleEnemy = x + i;
                        if (!Map.getTroopMap()[xOfPossibleEnemy][y - 2].isEmpty()) {
                            if (killCount != 0) {
                                killTroopsOfEnemy(xOfPossibleEnemy, y - 2, killCount);
                            }
                        }
                    }

                } else if (direction.equals(Names.EAST.getName())) {
                    for (int i = -1; i <= 1; i++) {
                        xOfPossibleEnemy = x + i;
                        if (!Map.getTroopMap()[xOfPossibleEnemy][y + 2].isEmpty()) {
                            if (killCount != 0) {
                                killTroopsOfEnemy(xOfPossibleEnemy, y + 2, killCount);
                            }
                        }
                    }
                }
            }
            return GameMenuMessages.SUCCESS;
        }
        return GameMenuMessages.INVALID_DIRECTION;
    }

    public boolean checkDirection(String direction) {
        return direction.equals(Names.NORTH.getName()) || direction.equals(Names.SOUTH.getName())
                || direction.equals(Names.WEST.getName()) || direction.equals(Names.EAST.getName());
    }

    public boolean findShop(Matcher x1, Matcher y1) {
        int x = Integer.parseInt(x1.group("x"));
        int y = Integer.parseInt(y1.group("y"));
        if (validCoordinates(x, y)) {
            return Map.getBuildingMap()[x][y].get(0) instanceof Shop;

        }
        return false;
    }

    public void cagedWarDogsAttack() {
        for (int j = 0; j < Empire.cagedWarDogsCoordinate.size(); j++) {
            int x = Empire.cagedWarDogsCoordinate.get(j) / mapSize;
            int y = Empire.cagedWarDogsCoordinate.get(j) / mapSize;
            int floorOfX, floorOfY, ceilOfX, ceilOfY;
            for (int i = 0; i < 3; i++) {
                floorOfX = x - i;
                floorOfY = y - i;
                ceilOfX = x + i;
                ceilOfY = y + i;
                if (floorOfX < 0) floorOfX = 0;
                if (floorOfY < 0) floorOfY = 0;
                if (ceilOfX > mapSize) ceilOfX = mapSize - 1;
                if (ceilOfY > mapSize) ceilOfY = mapSize - 1;
                for (int m = floorOfX; m <= ceilOfX; m++) {
                    for (int n = floorOfY; n <= ceilOfY; n++) {
                        if (!Map.getTroopMap()[m][n].isEmpty()) {
                            if (killedByDogs(m, n)) {
                                Map.getBuildingMap()[x][y].clear();
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    public void DrawBridge() {
        for (int i = 0; i < Empire.DrawBride.size(); i++) {
            int x = Empire.DrawBride.get(i) / mapSize;
            int y = Empire.DrawBride.get(i) % mapSize;
            if (GameController.enemyInRange(x, y)) {
                DrawBridge drawBridge = (DrawBridge) Map.getBuildingMap()[x][y].get(0);
                drawBridge.bridgeOpen = false;
            }
        }
    }

    public void killTroopsOfEnemy(int x, int y, int killCount) {
        for (int i = 0; i < Map.getTroopMap()[x][y].size(); i++) {
            Army army = Map.getTroopMap()[x][y].get(i);
            if (!army.getOwner().equals(Manage.getCurrentEmpire()) && killCount != 0) {
                removeKilledUnitFromEmpireHashmap(army.getNames().getName(), army.getEmpire());
                Map.getTroopMap()[army.xCoordinate][army.yCoordinate].remove(army);
                army.getOwner().empireArmy.remove(army);
                i--;
                killCount--;
            }
        }
    }

    public static boolean killedByDogs(int x, int y) {
        for (int i = 0; i < Map.getTroopMap()[x][y].size(); i++) {
            Army army = Map.getTroopMap()[x][y].get(i);
            if (!army.getOwner().equals(Manage.getCurrentEmpire())) {
                removeKilledUnitFromEmpireHashmap(army.getNames().getName(), army.getEmpire());
                Map.getTroopMap()[x][y].remove(army);
                Empire empire = army.getEmpire();
                empire.empireArmy.remove(army);
                return true;
            }
        }
        return false;
    }

    public GameMenuMessages conquerGates(Matcher x1, Matcher y1) {
        int xOfGate = Integer.parseInt(x1.group("x"));
        int yOfGate = Integer.parseInt(y1.group("y"));
        int index;
        if (validFinalLocation(xOfGate, yOfGate)) {
            if (isGate(xOfGate, yOfGate)) {
                for (int i = 0; i < Map.wall.length; i++) {
                    for (int j = 0; j < Map.wall[i].length; j++) {
                        if (!Map.getBuildingMap()[i][j].get(0).getOwner().equals(Manage.getCurrentEmpire())
                                && isWall(i, j)) {
                            if (checkIfWallIsBesideGate(i, j, xOfGate, yOfGate)) {
                                selectedUnit.clear();
                                Army ladderMan = ifLadderManIsAvailable();
                                if (ladderMan != null) {
                                    selectedUnit.add(ladderMan);
                                    setPathForUnits(i, j);
                                    index = ladderMan.myPath.size() - 1;
                                    int xOfLadderMan = ladderMan.myPath.get(index) / PathFindingController.size;
                                    int yOfLadderMan = ladderMan.myPath.get(index) % PathFindingController.size;
                                    if (moveUnit(xOfLadderMan, yOfLadderMan).getMessages().equals(GameMenuMessages.ARMY_DEPLOYED.getMessages())) {
                                        ((Climbers) ladderMan).LadderMen(xOfLadderMan, yOfLadderMan);
                                        Map.getTroopMap()[xOfLadderMan][yOfLadderMan].add(ladderMan);
                                        Manage.getCurrentEmpire().empireArmy.add(ladderMan);
                                        ((StoneGateWay) Map.getBuildingMap()[xOfGate][yOfGate].get(0)).flagOfEnemy = true;
                                        Map.notPassable[xOfGate][yOfGate] = false;
                                        ((StoneGateWay) Map.getBuildingMap()[xOfGate][yOfGate].get(0)).setGateOpen(true);
                                        return GameMenuMessages.SUCCESS;
                                    } else return GameMenuMessages.ARMY_IN_PROCESS_OF_DEPLOYING;
                                }
                                return GameMenuMessages.NOT_ENOUGH_UNITS_TO_DEPLOY;
                            }
                        }
                    }
                }
            }
            return GameMenuMessages.WRONG_COORDINATE_FOR_BUILDING_TYPE;
        }
        return GameMenuMessages.COORDINATES_OUT_OF_BOUNDS;
    }

    private static boolean checkIfWallIsBesideGate(int x, int y, int xOfGate, int yOfGate) {
        return ((validCoordinates(xOfGate, yOfGate - 1) && Map.getBuildingMap()[x][y].get(0).equals(Map.getBuildingMap()[xOfGate][yOfGate - 1].get(0)))
                || (validCoordinates(xOfGate, yOfGate + 1) && Map.getBuildingMap()[x][y].get(0).equals(Map.getBuildingMap()[xOfGate][yOfGate + 1].get(0)))
                || (validCoordinates(xOfGate + 1, yOfGate) && Map.getBuildingMap()[x][y].get(0).equals(Map.getBuildingMap()[xOfGate + 1][yOfGate].get(0)))
                || (validCoordinates(xOfGate, yOfGate - 1) && Map.getBuildingMap()[x][y].get(0).equals(Map.getBuildingMap()[xOfGate - 1][yOfGate].get(0))));
    }

    private static Army ifLadderManIsAvailable() {
        for (Army army : Manage.getCurrentEmpire().empireArmy) {
            if (army.getNames().getName().equals(Names.LADDER_MEN.getName())) {
                return army;
            }
        }
        return null;
    }

    public GameMenuMessages damageByBatteringRam(Matcher x1, Matcher y1) {
        int x = Integer.parseInt(x1.group("x"));
        int y = Integer.parseInt(y1.group("y"));
        if (validFinalLocation(x, y)) {
            if (isGate(x, y) || isWall(x, y) || isTower(x, y)) {
                if (isAllUnitsBatteringRam()) {
                    String unitMoved = moveUnit(x, y).getMessages();
                    if (unitMoved.equals(GameMenuMessages.ARMY_DEPLOYED.getMessages())) {
                        int damage = Map.getBuildingMap()[x][y].get(0).getHp() - ((selectedUnit.get(0)).getAttackPower() * selectedUnit.size());
                        Map.getBuildingMap()[x][y].get(0).setHp(damage);
                        if (checkIfRemoveBuildingPossible(damage)) Map.getBuildingMap()[x][y].remove(0);
                        return GameMenuMessages.SUCCESS;
                    } else return GameMenuMessages.ARMY_IN_PROCESS_OF_DEPLOYING;
                } else return GameMenuMessages.IMPROPER_UNIT;
            } else return GameMenuMessages.IMPROPER_LOCATION;
        }
        return GameMenuMessages.COORDINATES_OUT_OF_BOUNDS;
    }

    public boolean isAllUnitsBatteringRam() {
        int number = 0;
        for (Army army : selectedUnit) {
            if (army.getNames().getName().equals(Names.BATTERING_RAM.getName())) {
                number++;
            }
        }
        return number == selectedUnit.size();
    }

    private static void killUnit() {
        for (Empire empire : Manage.getAllEmpires()) {
            for (Army army : empire.empireArmy) {
                if (army.getHp() <= 0) {
                    int x = army.xCoordinate - 1;
                    int y = army.yCoordinate - 1;
                    removeKilledUnitFromEmpireHashmap(army.getNames().getName(), army.getEmpire());
                    Map.getTroopMap()[x][y].remove(army);
                    empire.empireArmy.remove(army);
                }
            }
        }
    }

    public void setSieges() {
        for (Empire empire : Manage.getAllEmpires()) {
            for (Army army : empire.empireArmy) {
                if (army.getNames().equals(Names.TREBUCHET) || army.getNames().equals(Names.CATAPULT)
                        || army.getNames().equals(Names.FireThrowers)) {
                    throwers.add((ArchersAndThrowers) army);
                }
            }
            makeSiegesWorkAutomatically();
        }
        killUnit();
        throwers.clear();
    }

    public void makeSiegesWorkAutomatically() {
        if (!throwers.isEmpty()) {
            for (ArchersAndThrowers throwers : throwers) {
                setRangeLookingForEnemy(throwers);
            }
        }
    }

    public void setRangeLookingForEnemy(ArchersAndThrowers siege) {
        int floorOfX, floorOfY, ceilOfX, ceilOfY;
        for (int i = 1; i <= siege.getAttackRange(); i++) {
            floorOfX = siege.getCurrentX() - i;
            floorOfY = siege.getCurrentY() - i;
            ceilOfX = siege.getCurrentX() + i;
            ceilOfY = siege.getCurrentY() + i;
            if (floorOfX < 0) floorOfX = 0;
            if (floorOfY < 0) floorOfY = 0;
            if (ceilOfX > mapSize) ceilOfX = mapSize - 1;
            if (ceilOfY > mapSize) ceilOfY = mapSize - 1;
            if (LookForEnemyInRangeForBuilding(floorOfX, floorOfY, ceilOfX, ceilOfY, siege)) return;
        }
        for (int i = 1; i <= siege.getAttackRange(); i++) {
            floorOfX = siege.getCurrentX() - i;
            floorOfY = siege.getCurrentY() - i;
            ceilOfX = siege.getCurrentX() + i;
            ceilOfY = siege.getCurrentY() + i;
            if (floorOfX < 0) floorOfX = 0;
            if (floorOfY < 0) floorOfY = 0;
            if (ceilOfX > mapSize) ceilOfX = mapSize - 1;
            if (ceilOfY > mapSize) ceilOfY = mapSize - 1;
            if (LookForEnemyInRangeForTroops(floorOfX, floorOfY, ceilOfX, ceilOfY, siege)) return;
        }

    }

    public boolean LookForEnemyInRangeForBuilding(int floorX, int floorY, int ceilX, int ceilY, ArchersAndThrowers siege) {
        for (int i = floorX; i <= ceilX; i++) {
            for (int j = floorY; j <= ceilY; j++) {
                if (i == siege.getCurrentX() && j == siege.getCurrentY()) continue;
                if (!Map.getBuildingMap()[i][j].isEmpty() && !Map.getBuildingMap()[i][j].get(0).getOwner().equals(Manage.getCurrentEmpire())) {
                    Map.getBuildingMap()[i][j].remove(0);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean LookForEnemyInRangeForTroops(int floorX, int floorY, int ceilX, int ceilY, ArchersAndThrowers siege) {
        for (int i = floorX; i <= ceilX; i++) {
            for (int j = floorY; j <= ceilY; j++) {
                if (i == siege.getCurrentX() && j == siege.getCurrentY()) continue;
                if (!Map.getTroopMap()[i][j].isEmpty()) {
                    for (int k = 0; k < Map.getTroopMap()[i][j].size(); k++) {
                        Army army = Map.getTroopMap()[i][j].get(k);
                        if (!army.getOwner().equals(Manage.getCurrentEmpire())) {
                            army.setHp(0);
                            removeKilledUnitFromEmpireHashmap(army.getNames().getName(), army.getEmpire());
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public GameMenuMessages digDitch(Matcher x1, Matcher y1) {
        int x = Integer.parseInt(x1.group("x"));
        int y = Integer.parseInt(y1.group("y"));
        if (validFinalLocation(x, y)) {
            if (Map.getBuildingMap()[x][y].isEmpty()) {
                if (selectedUnit.size() != 0) {
                    if (validationOfArmiesType(Names.SPEAR_MEN.getName())) {
                        String unitMoved = moveUnit(x, y).getMessages();
                        if (unitMoved.equals(GameMenuMessages.ARMY_DEPLOYED.getMessages())) {
                            PitchDitch pitchDitch = new PitchDitch(Manage.getCurrentEmpire());
                            Map.getBuildingMap()[x][y].add(0, pitchDitch);
                            pitchDitch.digState = true;
                        } else return GameMenuMessages.ARMY_IN_PROCESS_OF_DEPLOYING;
                    } else return GameMenuMessages.IMPROPER_UNIT;
                } else return GameMenuMessages.NO_UNIT_SELECTED;
            } else return GameMenuMessages.LOCATION_CONTAINS_BUILDING;
        }
        return GameMenuMessages.COORDINATES_OUT_OF_BOUNDS;
    }

    public GameMenuMessages removePitchDitch(Matcher x1, Matcher y1) {
        int x = Integer.parseInt(x1.group("x"));
        int y = Integer.parseInt(y1.group("y"));
        if (validFinalLocation(x, y)) {
            if (Map.getBuildingMap()[x][y].get(0) instanceof PitchDitch) {
                if (((PitchDitch) Map.getBuildingMap()[x][y].get(0)).digState) {
                    Map.getBuildingMap()[x][y].remove(0);
                    return GameMenuMessages.BUILDING_REMOVED;
                } else {
                    Map.getBuildingMap()[x][y].remove(0);
                    return GameMenuMessages.DITCH_DIGGING_STOPPED;
                }
            } else return GameMenuMessages.WRONG_COORDINATE_FOR_BUILDING_TYPE;
        }
        return GameMenuMessages.COORDINATES_OUT_OF_BOUNDS;
    }

    public boolean validationOfArmiesType(String typeOfArmy) {
        int number = 0;
        for (Army army : selectedUnit) {
            if (army.getNames().getName().equals(typeOfArmy)) {
                number++;
            }
        }
        return number == selectedUnit.size();
    }

    public GameMenuMessages digTunnel(Matcher x1, Matcher y1) {
        int x = Integer.parseInt(x1.group("x"));
        int y = Integer.parseInt(y1.group("y"));
        if (validFinalLocation(x, y)) {
            setPathForUnits(x, y);
            for (Army army : selectedUnit) {
                if (army instanceof Tunneler) {
                    List<Integer> pathList = army.myPath;
                    for (Integer integer : pathList) {
                        if (army.restOfMoves != 0) {
                            int nextX = integer / PathFindingController.size;
                            int nextY = integer % PathFindingController.size;
                            if (!isTower(nextX, nextY) && !(Map.getBuildingMap()[nextX][nextY].get(0) instanceof PitchDitch)) {
                                if (Map.getBuildingMap()[nextX][nextY].get(0) instanceof Wall) {
                                    army.getEmpire().empireArmy.remove(army);
                                    Map.getTroopMap()[army.getCurrentX()][army.getCurrentY()].remove(army);
                                    Map.getBuildingMap()[nextX][nextY].clear();
                                    Map.notPassable[nextX][nextY] = false;
                                    Map.wall[nextX][nextY] = false;
                                }
                            }
                            army.restOfMoves--;
                        }
                    }
                    removeKilledUnitFromEmpireHashmap(army.getNames().getName(), army.getOwner());
                    break;
                }
            }
            return GameMenuMessages.TUNNEL_DUG;
        }
        return GameMenuMessages.COORDINATES_OUT_OF_BOUNDS;
    }

    public GameMenuMessages fillDitch(Matcher x1, Matcher y1) {
        int x = Integer.parseInt(x1.group("x"));
        int y = Integer.parseInt(y1.group("y"));
        if (validFinalLocation(x, y)) {
            if (Map.getBuildingMap()[x][y].get(0) instanceof PitchDitch) {
                if (!Map.getBuildingMap()[x][y].get(0).getOwner().equals(Manage.getCurrentEmpire())) {
                    if (!(validationOfArmiesType(Names.WORKER.getName()))) {
                        String unitMoved = moveUnit(x, y).getMessages();
                        if (unitMoved.equals(GameMenuMessages.ARMY_DEPLOYED.getMessages())) {
                            Map.getBuildingMap()[x][y].remove(0);
                            return GameMenuMessages.DITCH_FILLED;
                        } else System.out.println(unitMoved);
                    } else return GameMenuMessages.IMPROPER_UNIT;
                } else return GameMenuMessages.FILLING_YOUR_DITCH;
            } else return GameMenuMessages.WRONG_COORDINATE_FOR_BUILDING_TYPE;
        }
        return GameMenuMessages.COORDINATES_OUT_OF_BOUNDS;
    }

    public GameMenuMessages siegeTowersAction(Matcher x1, Matcher y1) {
        int x = Integer.parseInt(x1.group("x"));
        int y = Integer.parseInt(y1.group("y"));
        if (validLocationForSiegeTower(x, y)) {
            if (isWall(x, y)) {
                setPathForUnits(x, y);
                for (Army army : selectedUnit) {
                    int index = army.myPath().size() - 1;
                    int xOfSiegeTower = army.myPath.get(index) / PathFindingController.size;
                    int yOfSiegeTower = army.myPath.get(index) % PathFindingController.size;
                    return moveUnit(xOfSiegeTower, yOfSiegeTower);
                }
                return GameMenuMessages.SUCCESS;
            }
        }
        return GameMenuMessages.IMPROPER_LOCATION;
    }

    public boolean checkIfRemoveBuildingPossible(int hpOfBuilding) {
        return hpOfBuilding <= 0;
    }

    public boolean isGate(int x, int y) {
        if (!Map.getBuildingMap()[x][y].get(0).getOwner().equals(Manage.getCurrentEmpire())) {
            return Map.getBuildingMap()[x][y].get(0) instanceof StoneGateWay;
        }
        return false;
    }

    public boolean isWall(int x, int y) {
        if (!Map.getBuildingMap()[x][y].get(0).getOwner().equals(Manage.getCurrentEmpire())) {
            return Map.getBuildingMap()[x][y].get(0) instanceof Wall;
        }
        return false;
    }

    public boolean isTower(int x, int y) {
        if (!Map.getBuildingMap()[x][y].get(0).getOwner().equals(Manage.getCurrentEmpire())) {
            return Map.getBuildingMap()[x][y].get(0) instanceof Tower;
        }
        return false;
    }

    public boolean validLocationForSiegeTower(int x, int y) {
        return ((Map.getBuildingMap()[x + 1][y].get(0) instanceof Wall && validLocationForSiegeTower(x + 1, y))
                || (Map.getBuildingMap()[x - 1][y].get(0) instanceof Wall && validLocationForSiegeTower(x - 1, y))
                || (Map.getBuildingMap()[x][y - 1].get(0) instanceof Wall && validLocationForSiegeTower(x, y - 1))
                || (Map.getBuildingMap()[x][y + 1].get(0) instanceof Wall && validLocationForSiegeTower(x, y + 1)));
    }

    public void fight() {
        AttackArmyToArmyController.battleWithEnemy();
        setSieges();
    }

    public static void removeEmpireTroopsFromGame(Empire empire) {
        for (int i = 0; i < empire.empireArmy.size(); i++) {
            int x = empire.empireArmy.get(i).xCoordinate;
            int y = empire.empireArmy.get(i).yCoordinate;
            Map.troopMap[x][y].remove(empire.empireArmy.get(i));
            empire.empireArmy.remove(i);
            i--;
        }
    }

    public static void removeKilledUnitFromEmpireHashmap(String troopName, Empire empire) {
        switch (troopName) {
            case "archer":
                empire.setEuropeArcherCount(empire.getEuropeArcherCount() - 1);
                break;
            case "spearMan":
                empire.setSpearManCount(empire.getSpearManCount() - 1);
                break;
            case "maceMan":
                empire.setMaceManCount(empire.getMaceManCount() - 1);
                break;
            case "crossbowMan":
                empire.setCrossbowManCount(empire.getCrossbowManCount() - 1);
                break;
            case "pikeMan":
                empire.setPikeManCount(empire.getPikeManCount() - 1);
                break;
            case "swordMan":
                empire.setSwordManCount(empire.getSwordManCount() - 1);
                break;
            case "knight":
                empire.setKnightCount(empire.getKnightCount() - 1);
                break;
            case "blackMonk":
                empire.setBlackMonkCount(empire.getBlackMonkCount() - 1);
                break;
            case "catapult":
                empire.setCatapultCount(empire.getCatapultCount() - 1);
                break;
            case "trebuchet":
                empire.setTrebuchetCount(empire.getTrebuchetCount() - 1);
                break;
            case "siegeTower":
                empire.setSiegeTowerCount(empire.getSiegeTowerCount() - 1);
                break;
            case "fireBalista":
                empire.setFireBalistaCount(empire.getFireBalistaCount() - 1);
                break;
            case "batteringRam":
                empire.setBatteringRamCount(empire.getBatteringRamCount() - 1);
                break;
            case "portableShield":
                empire.setPortableShieldCount(empire.getPortableShieldCount() - 1);
                break;
            case "arabianBow":
                empire.setArabianBowCount(empire.getArabianBowCount() - 1);
                break;
            case "slave":
                empire.setSlaveCount(empire.getSlaveCount() - 1);
                break;
            case "slinger":
                empire.setSlingerCount(empire.getSlingerCount() - 1);
                break;
            case "assassin":
                empire.setAssassinCount(empire.getAssassinCount() - 1);
                break;
            case "horseArcher":
                empire.setHorseArcherCount(empire.getHorseArcherCount() - 1);
                break;
            case "arabianSwordMan":
                empire.setArabianSwordManCount(empire.getArabianSwordManCount() - 1);
                break;
            case "fireThrower":
                empire.setFireThrowerCount(empire.getFireThrowerCount() - 1);
                break;
            case "engineer":
                empire.setEngineerCount(empire.getEngineerCount() - 1);
                break;
            case "ladderMan":
                empire.setLadderManCount(empire.getLadderManCount() - 1);
                break;
            case "tunneler":
                empire.setTunnelerCount(empire.getTunnelerCount() - 1);
                break;
        }
    }

    public static boolean enemyInRange(int x, int y) {
        int floorOfX, floorOfY, ceilOfX, ceilOfY;
        for (int i = 0; i < 5; i++) {
            floorOfX = x - i;
            floorOfY = y - i;
            ceilOfX = x + i;
            ceilOfY = y + i;
            if (floorOfX < 0) floorOfX = 0;
            if (floorOfY < 0) floorOfY = 0;
            if (ceilOfX > mapSize) ceilOfX = mapSize - 1;
            if (ceilOfY > mapSize) ceilOfY = mapSize - 1;
            for (int j = floorOfX; j <= ceilOfX; j++) {
                for (int k = floorOfY; k <= ceilOfY; k++) {
                    if (j == x && k == y) continue;
                    if (isEnemyBuilding(j, k) || isEnemyUnit(j, k)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isEnemyBuilding(int j, int k) {
        return !Map.getBuildingMap()[j][k].isEmpty() && !Map.getBuildingMap()[j][k].get(0).getOwner().equals(Manage.getCurrentEmpire());
    }

    private static boolean isEnemyUnit(int j, int k) {
        for (Army army : Map.getTroopMap()[j][k]) {
            if (!army.getOwner().equals(Manage.getCurrentEmpire())) {
                return true;
            }
        }
        return false;
    }

    private static boolean validCoordinates(int x, int y) {
        return x >= 0 && y >= 0 && x <= Map.mapSize && y <= Map.mapSize;
    }

}

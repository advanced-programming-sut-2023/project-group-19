package controller.Building;

import model.Empire;

import javax.swing.plaf.PanelUI;
import java.security.PublicKey;

public class FunctionBuildingController {
    public static Empire empire;
    public static void transformWheatToFlour() {
        int empiresWheatCount = empire.getWheatCount();
        int millRate = empire.getMillRate();
        int millCount = empire.getMillCount();
        if (millRate * millCount <= empiresWheatCount) {
            empire.setWheatCount(empiresWheatCount - millRate * millCount);
            empire.setFlour(empire.getFlour() + millRate * millCount);
        } else {
            empire.setWheatCount(0);
            empire.setFlour(empire.getFlour() + empiresWheatCount);
        }
    }

    public static void transformFlourToBread() {
        int empiresFlourCount = empire.getFlour();
        int backerRate = empire.getBakeryRate();
        int backerCount = empire.getBakeryCount();
        if (empire.getFoodCount() + backerRate * backerCount <= empire.getFoodCapacity()) {
            if (backerRate * backerCount <= empiresFlourCount) {
                empire.setFlour(empiresFlourCount - backerRate * backerCount);
                empire.setBread(empire.getBread() + backerRate * backerCount);
            } else {
                empire.setFlour(0);
                empire.setBread(empire.getBread() + empiresFlourCount);
            }
        }
    }
    public static void increasePopularityWithBeer() {
        int empiresBeerCount = empire.getBeer();
        int innRate = empire.getInnRate();
        int innCount = empire.getInnCount();
        if (empire.getFoodCount() + innRate * innCount <= empire.getFoodCapacity()) {
            if (innRate * innCount <= empiresBeerCount) {
                empire.setBeer(empiresBeerCount - innRate * innCount);
                empire.setPopularity(empire.getPopularity() + innCount);
            } else {
                empire.setBeer(0);
                empire.setPopularity(empire.getPopularity() + empiresBeerCount/innRate);
            }
        }
    }

    public static void transformOatToBeer() {
        int empiresOatCount = empire.getOatCount();
        int beerFactoryRate = empire.getBeerFactoryRate();
        int beerFactoryCount = empire.getBeerFactoryCount();
        if (beerFactoryRate * beerFactoryCount <= empiresOatCount) {
            empire.setOatCount(empiresOatCount - beerFactoryRate * beerFactoryCount);
            empire.setBeer(empire.getBeer() + beerFactoryRate * beerFactoryCount);
        } else {
            empire.setOatCount(0);
            empire.setBeer(empire.getBeer() + empiresOatCount);
        }
    }

    public static void transformIronToMetalArmour() {
        int empiresIronCount = empire.getIronCount();
        int armourerBuildingRate = empire.getArmourerBuildingRate();
        int armourerBuildingCount = empire.getArmourerBuildingCount();
        if (empire.getWeaponsCount() + armourerBuildingRate * armourerBuildingCount <= empire.getWeaponsCapacity()) {
            if (armourerBuildingRate * armourerBuildingCount <= empiresIronCount) {
                empire.setIronCount(empiresIronCount - armourerBuildingRate * armourerBuildingCount);
                empire.setMetalArmourCount(empire.getMetalArmourCount() + armourerBuildingRate * armourerBuildingCount);
            } else {
                empire.setIronCount(0);
                empire.setMetalArmourCount(empire.getMetalArmourCount() + empiresIronCount);
            }
        }
    }

    public static void transformWoodToBow() {
        int empiresWoodCount = empire.getWoodCount();
        int fletcherBuildingRate = empire.getFletcherBuildingRate();
        int fletcherBuildingCount = empire.getFletcherBuildingCount();
        if (empire.getWeaponsCount() + fletcherBuildingRate * fletcherBuildingCount <= empire.getWeaponsCapacity()) {
            if (fletcherBuildingRate * fletcherBuildingCount <= empiresWoodCount) {
                empire.setWoodCount(empiresWoodCount - fletcherBuildingRate * fletcherBuildingCount);
                empire.setBowCount(empire.getBowCount() + fletcherBuildingRate * fletcherBuildingCount);
            } else {
                empire.setWoodCount(0);
                empire.setBowCount(empire.getBowCount() + empiresWoodCount);
            }
        }
    }

    public static void transformIronToSwordOrMace() {
        int empiresIronCount = empire.getIronCount();
        int blacksmithBuildingRate = empire.getBlacksmithBuildingRate();
        int blacksmithBuildingCount = empire.getBlacksmithBuildingCount();
        if (empire.getWeaponsCount() + 2 * blacksmithBuildingRate * blacksmithBuildingCount <= empire.getWeaponsCapacity()) {
            if (blacksmithBuildingRate * blacksmithBuildingCount * 2 <= empiresIronCount) {
                empire.setIronCount(empire.getIronCount() - 2 * blacksmithBuildingRate * blacksmithBuildingCount);
                empire.setSwordCount(empire.getSwordCount() + blacksmithBuildingRate * blacksmithBuildingCount);
                empire.setMaceCount(empire.getMaceCount() + blacksmithBuildingRate * blacksmithBuildingCount);
            } else {
                empire.setIronCount(0);
                empire.setSwordCount(empire.getSwordCount() + empiresIronCount / 2);
                empire.setMaceCount(empire.getMaceCount() + empiresIronCount / 2);
            }
        } else {
            if (empire.getWeaponsCapacity() - empire.getWeaponsCount() <= empiresIronCount) {
                empire.setIronCount(empire.getIronCount() - (empire.getWeaponsCapacity() - empire.getWeaponsCount()));
                empire.setSwordCount(empire.getSwordCount() + (empire.getWeaponsCapacity() - empire.getWeaponsCount()) / 2);
                empire.setMaceCount(empire.getMaceCount() + (empire.getWeaponsCapacity() - empire.getWeaponsCount()) / 2);
            } else {
                empire.setIronCount(0);
                empire.setSwordCount(empire.getSwordCount() + empiresIronCount / 2);
                empire.setMaceCount(empire.getMaceCount() + empiresIronCount / 2);
            }
        }
    }

    public static void transformWoodToSpearOrPeak() {
        int empiresWoodCount = empire.getWoodCount();
        int poleTurnerBuildingRate = empire.getPoleTurnerBuildingRate();
        int poleTurnerBuildingCount = empire.getPoleTurnerBuildingCount();
        if (empire.getWeaponsCount() + 2 * poleTurnerBuildingRate * poleTurnerBuildingCount <= empire.getWeaponsCapacity()) {
            if (poleTurnerBuildingRate * poleTurnerBuildingCount * 2 <= empiresWoodCount) {
                empire.setWoodCount(empire.getWoodCount() - 2 * poleTurnerBuildingRate * poleTurnerBuildingCount);
                empire.setSpearCount(empire.getSpearCount() + poleTurnerBuildingRate * poleTurnerBuildingCount);
                empire.setPeakCount(empire.getPeakCount() + poleTurnerBuildingRate * poleTurnerBuildingCount);
            } else {
                empire.setWoodCount(0);
                empire.setSpearCount(empire.getSpearCount() + empiresWoodCount / 2);
                empire.setPeakCount(empire.getPeakCount() + empiresWoodCount / 2);
            }
        } else {
            if (empire.getWeaponsCapacity() - empire.getWeaponsCount() <= empiresWoodCount) {
                empire.setWoodCount(empire.getWoodCount() - (empire.getWeaponsCapacity() - empire.getWeaponsCount()));
                empire.setSpearCount(empire.getSpearCount() + (empire.getWeaponsCapacity() - empire.getWeaponsCount()) / 2);
                empire.setPeakCount(empire.getPeakCount() + (empire.getWeaponsCapacity() - empire.getWeaponsCount()) / 2);
            } else {
                empire.setWoodCount(0);
                empire.setSpearCount(empire.getSpearCount() + empiresWoodCount / 2);
                empire.setPeakCount(empire.getPeakCount() + empiresWoodCount / 2);
            }
        }
    }
}

package controller.Building;

import model.Empire;
import model.Manage;

import javax.swing.plaf.PanelUI;
import java.security.PublicKey;

public class FunctionBuildingController {
    public static Empire empire = Manage.getCurrentEmpire();
    public static double fearWorkerImpact = empire.getFearWorkerImpact();

    public static void transformWheatToFlour() {

        int empiresWheatCount = empire.getWheatCount();
        int millRate = empire.getMillRate();
        int millCount = empire.getMillCount();
        if (millRate * millCount * fearWorkerImpact <= empiresWheatCount) {
            empire.setWheatCount((int) (empiresWheatCount - millRate * millCount * fearWorkerImpact));
            empire.setFlour((int) (empire.getFlour() + millRate * millCount * fearWorkerImpact));
        } else {
            empire.setWheatCount(0);
            empire.setFlour(empire.getFlour() + empiresWheatCount);
        }
    }

    public static void transformFlourToBread() {
        int empiresFlourCount = empire.getFlour();
        int backerRate = empire.getBakeryRate();
        int backerCount = empire.getBakeryCount();
        if (empire.getFoodCount() + backerRate * backerCount * fearWorkerImpact <= empire.getFoodCapacity()) {
            if (backerRate * backerCount * fearWorkerImpact <= empiresFlourCount) {
                empire.setFlour((int) (empiresFlourCount - backerRate * backerCount * fearWorkerImpact));
                empire.setBread((int) (empire.getBread() + backerRate * backerCount * fearWorkerImpact));
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
        if (empire.getFoodCount() + innRate * innCount * fearWorkerImpact <= empire.getFoodCapacity()) {
            if (innRate * innCount * fearWorkerImpact <= empiresBeerCount) {
                empire.setBeer((int) (empiresBeerCount - innRate * innCount * fearWorkerImpact));
                empire.setPopularity((int) (empire.getPopularity() + innCount * fearWorkerImpact));
            } else {
                empire.setBeer(0);
                empire.setPopularity(empire.getPopularity() + empiresBeerCount / innRate);
            }
        }
    }

    public static void transformOatToBeer() {
        int empiresOatCount = empire.getOatCount();
        int beerFactoryRate = empire.getBeerFactoryRate();
        int beerFactoryCount = empire.getBeerFactoryCount();
        if (beerFactoryRate * beerFactoryCount * fearWorkerImpact <= empiresOatCount) {
            empire.setOatCount((int) (empiresOatCount - beerFactoryRate * beerFactoryCount * fearWorkerImpact));
            empire.setBeer((int) (empire.getBeer() + beerFactoryRate * beerFactoryCount * fearWorkerImpact));
        } else {
            empire.setOatCount(0);
            empire.setBeer(empire.getBeer() + empiresOatCount);
        }
    }

    public static void transformIronToMetalArmour() {
        int empiresIronCount = empire.getIronCount();
        int armourerBuildingRate = empire.getArmourerBuildingRate();
        int armourerBuildingCount = empire.getArmourerBuildingCount();
        if (empire.getWeaponsCount() + armourerBuildingRate * armourerBuildingCount * fearWorkerImpact <= empire.getWeaponsCapacity()) {
            if (armourerBuildingRate * armourerBuildingCount * fearWorkerImpact <= empiresIronCount) {
                empire.setIronCount((int) (empiresIronCount - armourerBuildingRate * armourerBuildingCount * fearWorkerImpact));
                empire.setMetalArmourCount((int) (empire.getMetalArmourCount() + armourerBuildingRate * armourerBuildingCount * fearWorkerImpact));
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
        if (empire.getWeaponsCount() + fletcherBuildingRate * fletcherBuildingCount * fearWorkerImpact <= empire.getWeaponsCapacity()) {
            if (fletcherBuildingRate * fletcherBuildingCount * fearWorkerImpact <= empiresWoodCount) {
                empire.setWoodCount((int) (empiresWoodCount - fletcherBuildingRate * fletcherBuildingCount * fearWorkerImpact));
                empire.setBowCount((int) (empire.getBowCount() + fletcherBuildingRate * fletcherBuildingCount * fearWorkerImpact));
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
        if (empire.getWeaponsCount() + 2 * blacksmithBuildingRate * blacksmithBuildingCount * fearWorkerImpact <= empire.getWeaponsCapacity()) {
            if (blacksmithBuildingRate * blacksmithBuildingCount * fearWorkerImpact * 2 <= empiresIronCount) {
                empire.setIronCount((int) (empire.getIronCount() - 2 * blacksmithBuildingRate * blacksmithBuildingCount * fearWorkerImpact));
                empire.setSwordCount((int) (empire.getSwordCount() + blacksmithBuildingRate * blacksmithBuildingCount * fearWorkerImpact));
                empire.setMaceCount((int) (empire.getMaceCount() + blacksmithBuildingRate * blacksmithBuildingCount * fearWorkerImpact));
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
        if (empire.getWeaponsCount() + 2 * poleTurnerBuildingRate * poleTurnerBuildingCount * fearWorkerImpact <= empire.getWeaponsCapacity()) {
            if (poleTurnerBuildingRate * poleTurnerBuildingCount * fearWorkerImpact * 2 <= empiresWoodCount) {
                empire.setWoodCount((int) (empire.getWoodCount() - 2 * poleTurnerBuildingRate * poleTurnerBuildingCount * fearWorkerImpact));
                empire.setSpearCount((int) (empire.getSpearCount() + poleTurnerBuildingRate * poleTurnerBuildingCount * fearWorkerImpact));
                empire.setPeakCount((int) (empire.getPeakCount() + poleTurnerBuildingRate * poleTurnerBuildingCount * fearWorkerImpact));
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

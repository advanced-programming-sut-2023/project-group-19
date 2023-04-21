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
        if (millRate * millCount < empiresWheatCount) {
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
        if (backerRate * backerCount < empiresFlourCount) {
            empire.setFlour(empiresFlourCount - backerRate * backerCount);
            empire.setBread(empire.getBread() + backerRate * backerCount);
        } else {
            empire.setFlour(0);
            empire.setBread(empire.getBread() + empiresFlourCount);
        }
    }

    public static void transformOatToBeer() {
        int empiresOatCount = empire.getOatCount();
        int beerFactoryRate = empire.getBeerFactoryRate();
        int beerFactoryCount = empire.getBeerFactoryCount();
        if (beerFactoryRate * beerFactoryCount < empiresOatCount) {
            empire.setOatCount(empiresOatCount - beerFactoryRate * beerFactoryCount);
            empire.setBeer(empire.getBeer() + beerFactoryRate * beerFactoryCount);
        } else {
            empire.setOatCount(0);
            empire.setBeer(empire.getBeer() + empiresOatCount);
        }
    }

    public static void transformIronToMetalArmour() {
        int empiresIronCount = empire.getIronCount();
        int millRate = empire.get;
        int millCount = empire.getMillCount();
        if (millRate * millCount < empiresWheatCount) {
            empire.setWheatCount(empiresWheatCount - millRate * millCount);
            empire.setFlour(empire.getFlour() + millRate * millCount);
        } else {
            empire.setWheatCount(0);
            empire.setFlour(empire.getFlour() + empiresWheatCount);
        }
    }

    public static void transformIronToSwordOrMace() {
        int empiresWheatCount = empire.getWheatCount();
        int millRate = empire.getMillRate();
        int millCount = empire.getMillCount();
        if (millRate * millCount < empiresWheatCount) {
            empire.setWheatCount(empiresWheatCount - millRate * millCount);
            empire.setFlour(empire.getFlour() + millRate * millCount);
        } else {
            empire.setWheatCount(0);
            empire.setFlour(empire.getFlour() + empiresWheatCount);
        }
    }

    public static void transformWoodToBow() {
        int empiresWheatCount = empire.getWheatCount();
        int millRate = empire.getMillRate();
        int millCount = empire.getMillCount();
        if (millRate * millCount < empiresWheatCount) {
            empire.setWheatCount(empiresWheatCount - millRate * millCount);
            empire.setFlour(empire.getFlour() + millRate * millCount);
        } else {
            empire.setWheatCount(0);
            empire.setFlour(empire.getFlour() + empiresWheatCount);
        }
    }

    public static void transformWoodToSpearOrPike() {
        int empiresWheatCount = empire.getWheatCount();
        int millRate = empire.getMillRate();
        int millCount = empire.getMillCount();
        if (millRate * millCount < empiresWheatCount) {
            empire.setWheatCount(empiresWheatCount - millRate * millCount);
            empire.setFlour(empire.getFlour() + millRate * millCount);
        } else {
            empire.setWheatCount(0);
            empire.setFlour(empire.getFlour() + empiresWheatCount);
        }
    }

}

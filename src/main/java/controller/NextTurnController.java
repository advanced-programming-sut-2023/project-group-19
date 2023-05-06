package controller;

import model.Empire;
import model.Manage;
import model.Map;
import view.GameMenu;

public class NextTurnController {
    public static Empire currentEmpire;
    public static int index = 0;

    public void game(){
        while (true)
        {
            GameController gameController = new GameController();
            setGameController(gameController);
            findCurrentEmpire();
            callStartingTurnFunctions();
            callEndingTurnFunctions();




        }
    }
    public void findCurrentEmpire(){
        Manage.setCurrentEmpire(Manage.allEmpires.get(index));
        currentEmpire = Manage.allEmpires.get(index);
        index = index++ % Manage.allEmpires.size();
    }
    public void callStartingTurnFunctions(){
        currentEmpire.setFearFactor();
        currentEmpire.taxImpactOnEmpire(currentEmpire , currentEmpire.getTaxRateNumber());
        currentEmpire.independentProductionBuilding();
        currentEmpire.functionBuildings();
        currentEmpire.givingPeopleFood(currentEmpire);

    }
    public void setGameController(GameController gameController){
        GameController.gameController = gameController;
        GameMenu.gameController = gameController;
    }
    public void callEndingTurnFunctions(){

    }





}

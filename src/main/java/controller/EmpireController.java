package controller;

import model.Building.Names;
import model.Manage;
import model.Map;
import view.Messages.EmpireMessages;

public class EmpireController {
    public String showPopularityFactors(){
        return EmpireMessages.POPULARITY_FACTOR.getMessages();
    }
    public String showFoodList(){
        String foodList = "Apple: "+Manage.getCurrentEmpire().allFood.get("apple")+"\n"
                        + "Cheese: "+Manage.getCurrentEmpire().allFood.get("cheese")+"\n"
                        + "Bread: "+Manage.getCurrentEmpire().allFood.get("bread")+"\n"
                        + "Meat: "+Manage.getCurrentEmpire().allFood.get("meat");
        return foodList;
    }
    public void setPopularityBasedOnFoodDiversity(){
        int foodDiversity = 0;
        if (Manage.getCurrentEmpire().allFood.get("apple") > 0){
            foodDiversity++;
        }
        if (Manage.getCurrentEmpire().allFood.get("cheese") > 0){
            foodDiversity++;
        }
        if (Manage.getCurrentEmpire().allFood.get("bread") > 0){
            foodDiversity++;
        }
        if (Manage.getCurrentEmpire().allFood.get("meat") > 0){
            foodDiversity++;
        }
        Manage.getCurrentEmpire().setPopularity(Manage.getCurrentEmpire().getPopularity() + foodDiversity);
    }
    public void setPopularityBasedOnReligion(){
        numberOfChurches();
        //TODO : HOW DO PEOPLE BECOME RELIGIOUS
    }
    public void numberOfChurches(){
        for (int x = 0 ; x < Map.mapSize ; x++){
            for (int y = 0 ; y < Map.mapSize ; y++){
                if (Map.getBuildingMap()[x][y].get(0).getName().equals(Names.BIG_CHURCH)
                    && Map.getBuildingMap()[x][y].get(0).getOwner().equals(Manage.getCurrentEmpire())){
                    Manage.getCurrentEmpire().setPopularity(Manage.getCurrentEmpire().getPopularity() + 2);
                } else if (Map.getBuildingMap()[x][y].get(0).getName().equals(Names.SMALL_CHURCH)
                            && Map.getBuildingMap()[x][y].get(0).getOwner().equals(Manage.getCurrentEmpire())){
                    Manage.getCurrentEmpire().setPopularity(Manage.getCurrentEmpire().getPopularity() + 1);
                }
            }
        }
    }
    public void setPopularityBasedOnFear(){

    }
}

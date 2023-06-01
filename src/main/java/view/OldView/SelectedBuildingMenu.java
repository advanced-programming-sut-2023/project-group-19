package view.OldView;

import controller.Building.BuildingController;
import controller.Building.SelectedBuildingController;
import controller.GameController;
import model.Building.Building;
import view.Commands.SelectedBuildingCommands;
import view.Messages.SelectedBuildingMessages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelectedBuildingMenu {
    public static int buildingXCoordinate;
    public static int buildingYCoordinate;
    public static Building selectedBuilding;
    public SelectedBuildingController selectedBuildingController = new SelectedBuildingController();
    public String buildingName = selectedBuilding.getNameEnum().getName();

    public Pattern createUnit(String troopName , int matcherCount ){
        if (SelectedBuildingCommands.getMatcher(troopName,
                SelectedBuildingCommands.SELECTED_BUILDING_BARRACKS_TROOP_NAME_CHECK) != null && buildingName.equals("Barracks")) {
            return selectedBuildingController.Barracks(troopName, matcherCount).getName();
        } else if (SelectedBuildingCommands.getMatcher(troopName,
                SelectedBuildingCommands.SELECTED_BUILDING_MERCENARY_TROOP_NAME_CHECK) != null && buildingName.equals("Mercenary")) {
            return selectedBuildingController.mercenary(troopName, matcherCount).getName();
        } else if (SelectedBuildingCommands.getMatcher(troopName,
                SelectedBuildingCommands.SELECTED_BUILDING_SIEGE_TENT_TROOP_NAME_CHECK) != null && buildingName.equals("SiegeTent")) {
            return selectedBuildingController.siegeTent(troopName, matcherCount).getName();
        } else if (SelectedBuildingCommands.getMatcher(troopName,
                SelectedBuildingCommands.SELECTED_BUILDING_CHURCH_TROOP_NAME_CHECK) != null
                && (buildingName.equals("SmallChurch") | buildingName.equals("BigChurch"))) {
            return selectedBuildingController.church(matcherCount).getName();
        } else if (SelectedBuildingCommands.getMatcher(troopName,
                SelectedBuildingCommands.SELECTED_BUILDING_ENGINEER_GUILD_TROOP_NAME_CHECK) != null
                && buildingName.equals("EngineerGuild")) {
            return selectedBuildingController.engineerGuild(troopName, matcherCount).getName();
        }
        return null;
    }
    public Pattern setTax(int matcherTaxRate) {
        return selectedBuildingController.gatehouse(matcherTaxRate).getName();
    }
    public Pattern drawBridge(String matcherBridgeCondition){
        return selectedBuildingController.drawBridge(matcherBridgeCondition).getName();
    }
    public Pattern repair(){
        if (!GameController.enemyInRange(buildingXCoordinate, buildingYCoordinate)) {
            if (SelectedBuildingCommands.getMatcher(buildingName, SelectedBuildingCommands.REPAIR_SHOW_NAME) != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(buildingName + "\n");
                stringBuilder.append(BuildingController.repairBuilding(selectedBuilding).getMessages());
                return Pattern.compile(stringBuilder.toString());
            } else {
                return Pattern.compile(BuildingController.repairBuilding(selectedBuilding).getMessages());
            }
        } else {
            return SelectedBuildingMessages.ENEMY_IN_RANGE.getName();
        }
    }

}

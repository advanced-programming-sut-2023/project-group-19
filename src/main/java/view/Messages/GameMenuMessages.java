package view.Messages;

public enum GameMenuMessages {
    SELECT_UNIT_SUCCEEDED("The considered unit is chosen!"),
    NO_UNIT_IN_CELL("The chosen cell doesn't contain any unit that belongs to your empire!"),
    NO_UNIT_SELECTED("You haven't selected a unit yet!"),
    LOCATION_CONTAINS_BUILDING("The chosen coordinate contains a building"),
    LOCATION_CONTAINS_WATERSOURCES_OR_HIGH_PLACES("The chosen coordinate contains water sources or high towers which can't be moved to them at once!!"),
    EMPTY_COORDINATE_FIELD("Some of your coordinate fields are empty or invalid!"),
    EMPTY_DIRECTION_FIELD("The direction field is empty in your entry!"),
    EMPTY_EQUIPMENT_FIELD("The equipment field is empty in your entry!"),
    UNABLE_TO_MOVE_TREBUCHET("You can't move a trebuchet!"),
    DITCH_DIGGING_STOPPED("The ditch digging operation stopped due to your order!"),
    WRONG_COORDINATE_FOR_BUILDING_TYPE("The chosen coordinate doesn't contain required type of building!"),
    BUILDING_REMOVED("The building removed successfully!"),
    FILLING_YOUR_DITCH("This action can't be done.You're filling your ditch!"),
    IMPROPER_UNIT("The type of unit you've selected is improper"),
    IMPROPER_LOCATION("The chosen location is improper!"),
    DITCH_FILLED("The ditch is filled!"),
    TUNNEL_DUG("The tunnel dug successfully!"),
    ARMY_DEPLOYED("Your army deployed completely!"),
    ARMY_IN_PROCESS_OF_DEPLOYING("Your army will be deployed in forward turns!"),
    WRONG_SIEGE_NAME("The name of siege you've entered is invalid!"),
    ATTACK_ORDER_HANDLED("Your attack order is given to soldiers!"),
    COORDINATES_OUT_OF_BOUNDS("The given coordinate is out of bound!"),
    INVALID_NUMBER_OF_UNITS_TO_DROP("The number you entered is invalid!"),
    NOT_ENOUGH_UNITS_TO_DEPLOY("You don't have enough units with given type to deploy or use!"),
    INVALID_DIRECTION("The given direction is invalid!"),
    VALID_COMMAND("Valid command!"),
    INVALID_COMMAND("Invalid command!"),
    SUCCESS("The order is successfully done!"),
    LOGOUT("Logout"),;
    private String messages;

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    GameMenuMessages(String messages) {
        this.messages=messages;
    }
}

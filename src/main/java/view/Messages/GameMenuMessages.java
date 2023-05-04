package view.Messages;

public enum GameMenuMessages {
    SELECT_UNIT_SUCCEEDED("The considered unit is chosen!"),
    WRONG_UNIT_FOR_COMMAND("This command can't be done by the selected unit!"),
    NO_UNIT_IN_CELL("The chosen cell doesn't contain any unit!"),
    NO_UNIT_SELECTED("You haven't selected a unit yet!"),
    LOCATION_CONTAINS_BUILDING("The chosen coordinate contains a building"),
    LOCATION_CONTAINS_WATERSOURCES_OR_HIGH_PLACES("The chosen coordinate contains water sources or high towers which can't be moved to them at once!!"),
    EMPTY_COORDINATE_FIELD("Some of your coordinate fields are empty!"),
    EMPTY_DIRECTION_FIELD("The direction field is empty in your entry!"),
    EMPTY_EQUIPMENT_FIELD("The equipment field is empty in your entry!"),
    UNABLE_TO_MOVE_TREBUCHET("You can't move a trebuchet!"),
    ATTACKING_YOURSELF("This action can't be done.You're attacking yourself!"),
    DITCH_DIGGING_STOPPED("The ditch digging operation stopped due to your order!"),
    WRONG_COORDINATE_FOR_BUILDING_TYPE("The chosen coordinate doesn't contain required type of building!"),
    BUILDING_REMOVED("The building removed successfully!"),
    INCOMPLETE_PITCH_DITCH("You can't remove an incomplete ditch!"),
    FILLING_YOUR_DITCH("This action can't be done.You're filling your ditch!"),
    IMPROPER_LOCATION("The coordinate you've selected is improper!"),
    IMPROPER_UNIT("The type of unit you've selected is improper"),
    DITCH_FILLED("The ditch is filled!"),
    TUNNEL_DUG("The tunnel dug successfully!"),
    ARMY_DEPLOYED("Your army deployed completely!"),
    ARMY_IN_PROCESS_OF_DEPLOYING("Your army will be deployed in forward turns!"),
    VALID_COMMAND("Valid command!"),
    INVALID_COMMAND("Invalid command!"),
    CONTINUE("Continue!"),
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

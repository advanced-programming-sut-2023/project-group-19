package view.Messages;

public enum BuildingMessages {
    INVALID_COORDINATE("Invalid coordinate!"),
    ENTER_DIRECTION("Choose a direction for your considered building!"),
    INVALID_DIRECTION("The given direction is invalid!"),
    INPROPER_COORDINATE("The chosen coordinate is not proper for this type of building!"),
    NO_ACCESS("You don't have the permission to select your enemy's building!"),
    EMPTY_CELL("The given cell is empty!"),
    FILLED_CELL("The given cell is filled with another building!"),
    IMPROPER_GROUND_TYPE("The type of ground is not proper for your considered building!"),
    INVALID_BUILDING_NAME("Invalid building name!"),
    NOT_ENOUGH_STONE("Not enough stone!"),
    FULL_CAPACITY_OF_BUILDING("The gatehouse's capacity is full!"),
    HP_ALREADY_FULL("the building is at max hp!"),
    SUCCESSFUL_REPAIR("building repaired successfully"),
    SUCCESSFUL_SELECT("building selected successfully"),
    INSUFFICIENT_STONE("insufficient stone"),
    INSUFFICIENT_RESOURCES_TO_BUILD_THE_BUILDING("empire doesnt have enough resources to build the building"),
    NOT_ENOUGH_WORKERS_TO_BUILD_BUILDING("empire doesnt have enough workers to build the building"),
    CONTINUE("Continue"),
    STOCK_PILE_ERROR("cant build a stock pile in here"),
    BUILDING_IS_NOT_FOR_THIS_EMPIRE("this building does not belong to this empire"),
    SUCCESS("building created successfully"),
    ;
    private String messages;

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    BuildingMessages(String messages) {
        this.messages=messages;
    }
}

package view.Commands;

public enum GameMessages {
    INVALID_COORDINATE("Invalid coordinate!"),
    NO_ACCESS("You don't have the permission to select your enemy's building!"),
    EMPTY_CELL("The given cell is empty!"),
    FILLED_CELL("The given cell is filled with another building!"),
    INPROPER_GROUND_TYPE("The type of ground is not proper for your considered building!"),
    INVALID_BUILDING_NAME("Invalid building name!"),
    NOT_ENOUGH_STONE("Not enough stone!"),
    FULL_CAPACITY_OF_BUILDING("The gatehouse's capacity is full!"),
    CONTINUE("Continue"),
    SUCCESS("Success");
    private String messages;
    GameMessages(String messages) {
        this.messages=messages;
    }
}

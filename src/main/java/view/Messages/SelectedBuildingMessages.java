package view.Messages;

import view.Commands.SelectedBuildingCommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SelectedBuildingMessages {
    TAX_RATE_OUT_OF_BONDS("tax rate is out of bounds"),
    TAX_RATE_CHANGE_SUCCESSFUL("tax rate changed successfully"),
    BRIDGE_OPENED("bridge opened"),
    BRIDGE_CLOSED("bridge closed"),
    BRIDGE_ALREADY_OPEN("bridge is already open"),
    BRIDGE_ALREADY_CLOSE("bridge is already closed"),
    NOT_ENOUGH_PEASANTS("not enough peasants"),
    NOT_ENOUGH_GOLD("not enough gold"),
    NOT_ENOUGH_RESOURCES("not enough resources"),
    ENOUGH_RESOURCES("enough resources"),
    NOT_ENOUGH_ENGINEERS("not enough engineers"),

    INVALID_COMMAND("invalid command"),
    INVALID_TROOP_NAME("invalid troop name"),
    BUILDING_CANT_CREATE_UNIT("this building cant create troops"),
    WRONG_BUILDING_TO_CREATE_TROOP("this building can't create this troop"),
    WRONG_BUILDING_CHOSEN("cant do this action in this building"),
    ENEMY_IN_RANGE("enemy in range"),
    PURCHASE_SUCCESS("troop created successfully");
    private Pattern name;

    public Pattern getName() {
        return name;
    }

    public void setName(Pattern name) {
        this.name = name;
    }

    SelectedBuildingMessages(String name) {
        this.name = Pattern.compile(name);
    }

    public static Matcher getMatcher(String input, SelectedBuildingMessages command) {
        Matcher matcher = command.name.matcher(input);
        return matcher.matches() ? matcher : null;
    }

}

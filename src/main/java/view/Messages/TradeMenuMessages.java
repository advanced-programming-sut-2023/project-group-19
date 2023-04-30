package view.Messages;

public enum TradeMenuMessages {
    MESSAGE_EMPTY_FIELD_IN_ENTRY("The message field in your entry is empty!"),
    PRICE_EMPTY_FIELD_IN_ENTRY("The price field in your entry is empty!"),
    RESOURCE_AMOUNT_EMPTY_FIELD_IN_ENTRY("The resource amount field in your entry is empty!"),
    RESOURCE_TYPE_EMPTY_FIELD_IN_ENTRY("The resource type field in your entry is empty!"),
    ID_EMPTY_FIELD_IN_ENTRY("The id field in your entry is empty!"),
    EMPTY_MESSAGE_PART("The Message part is empty!"),
    INVALID_PRICE("The given price is invalid!"),
    INVALID_AMOUNT("The given amount is invalid!"),
    INVALID_RESOURCE_TYPE("The given type of resource is invalid!"),
    NO_EMPIRE_HAS_BEEN_CHOSEN("You have not chosen any empire yet!"),
    NO_REQUEST_OR_DONATION("There 's no request or donation with given information!"),
    NOT_ENOUGH_RESOURCES("The empire doesn't have enough resource to be shared or traded!"),
    NOT_ENOUGH_SPACE("The guest empire doesn't have enough capacity to save the mentioned good!"),
    INVALID_EMPIRE("There's no empire with the given name in the game!"),
    INVALID_ANSWER_FOR_SELECTED_EMPIRE("The format of your entry was invalid.You're back to the TradeMenu!"),
    VALID_COMMAND("Valid command!"),
    INVALID_COMMAND("Invalid command!"),
    SUCCESS("The trade operation has been done completely!");

    private String messages;

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    TradeMenuMessages(String messages) {
        this.messages=messages;
    }
}

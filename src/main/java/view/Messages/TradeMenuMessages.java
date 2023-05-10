package view.Messages;

public enum TradeMenuMessages {
    MESSAGE_EMPTY_FIELD_IN_ENTRY("The message field in your entry is empty!"),
    PRICE_EMPTY_FIELD_IN_ENTRY("The price field in your entry is empty!"),
    RESOURCE_AMOUNT_EMPTY_FIELD_IN_ENTRY("The resource amount field in your entry is empty!"),
    RESOURCE_TYPE_EMPTY_FIELD_IN_ENTRY("The resource type field in your entry is empty!"),
    ID_EMPTY_FIELD_IN_ENTRY("The id field in your entry is empty!"),
    EMPTY_MESSAGE_PART("The Message part is empty!"),
    INVALID_AMOUNT("""
            Invalid amount!
            Possible reasons:
            1.The given amount is invalid!
            2.Your treasury can't save this amount of good!"""),
    INVALID_PRICE("""
            Invalid price!
            Possible reasons:
            1.The given price is invalid!
            2.Your treasury doesn't have that much gold"""),
    INVALID_RESOURCE_TYPE("The given type of resource is invalid!"),
    NO_EMPIRE_HAS_BEEN_CHOSEN("You have not chosen any empire yet!"),
    NO_REQUEST("There 's no request with given information!"),
    NO_DONATION("There 's no donation with given information!"),
    NOT_ENOUGH_RESOURCES("The empire doesn't have enough resource to be shared or traded!"),
    INVALID_EMPIRE("There's no empire with the given name in the game!"),
    EMPIRE_CHOSEN_SUCCESSFULLY("The empire chose successfully!"),
    REQUEST_SENT_SUCCESSFULLY("Your request is sent to the chosen empire successfully!"),
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

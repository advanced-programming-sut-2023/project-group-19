package view.Messages;

public enum ShopMenuMessages {
    ITEM_NAME_EMPTY_FIELD("The itemName field in your entry is empty!"),
    ITEM_AMOUNT_EMPTY_FIELD("The itemAmount field in your entry is empty!"),
    INVALID_NAME_OF_ITEM("There's no item similar to the entered name"),
    INVALID_AMOUNT_TO_BUY("Invalid amount!\nPossible reason: There's not enough amount of chosen good in the shop!"),
    NOT_ENOUGH_AMOUNT_TO_SELL("You don't have enough amount of considered good!"),
    NOT_ENOUGH_CAPACITY_FOR_EMPIRE("You don't have enough capacity for this amount of good!"),
    NOT_ENOUGH_CAPACITY_FOR_SHOP("The shop doesn't have enough capacity!"),
    VALID_COMMAND("Valid command!"),
    INVALID_COMMAND("Invalid command!"),
    BUYING_OPERATION_SUCCEEDED("The buying operation is done successfully!"),
    SELLING_OPERATION_SUCCEEDED("The selling operation is done successfully!"),
    OPERATION_CANCELLED("The operation cancelled!"),
    ;
    private String messages;

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    ShopMenuMessages(String messages) {
        this.messages=messages;
    }
}

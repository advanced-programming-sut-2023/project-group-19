package model;

public class Request {
    String message;
    String fromSellerMessage;
    int price;
    int amount;
    String goodName;
    String id;
    Empire sender;
    Empire receiver;
    String status;
    boolean acceptance;

    public Request(String message, int price, int amount, String goodName, String id, Empire sender, Empire receiver) {
        this.message = message;
        this.price = price;
        this.amount = amount;
        this.goodName = goodName;
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public String getGoodName() {
        return goodName;
    }

    public String getId() {
        return id;
    }

    public Empire getSender() {
        return sender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAcceptance() {
        return acceptance;
    }

    public void setAcceptance(boolean acceptance) {
        this.acceptance = acceptance;
    }

    public String getFromSellerMessage() {
        return fromSellerMessage;
    }

    public void setFromSellerMessage(String fromSellerMessage) {
        this.fromSellerMessage = fromSellerMessage;
    }

    public Empire getReceiver() {
        return receiver;
    }
}

package basicGameModel;

public class Request {
    public String message;
    public String fromSellerMessage;
    public int price;
    public int amount;
    public String goodName;
    public String id;
    public Empire sender;
    public Empire receiver;
    public String status;
    public boolean acceptance;
    public String tradableThing;
    public Request(String message, int price, int amount, String goodName,
                   String id, Empire sender, Empire receiver,String tradableThing) {
        this.message = message;
        this.price = price;
        this.amount = amount;
        this.goodName = goodName;
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.tradableThing = tradableThing;
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


    public void setAcceptance(boolean acceptance) {
        this.acceptance = acceptance;
    }


    public Empire getReceiver() {
        return receiver;
    }
}

package view.ImageAndBackground;

import javafx.scene.image.Image;


public class TradeAndShopImages {
    public Image background;
    public Image sendRequest;
    public Image viewPreviousTrades;
    public Image send;
    public Image back;
    public Image amount;
    public Image request;
    public Image donate;
    public Image listOfPrevReq;
    public Image listOfDonationReq;

    public void loadImages() {
        background = new Image(TradeAndShopImages.class.getResource("/image/TradeMenuImages/TradeMenuImage.jpg").toExternalForm(),
                1560, 900, false, true);
        sendRequest = new Image(TradeAndShopImages.class.getResource("/image/TradeMenuImages/SendRequest.png").toExternalForm());
        viewPreviousTrades = new Image(TradeAndShopImages.class.getResource("/image/TradeMenuImages/PreviousTrades.png").toExternalForm());
        send = new Image(TradeAndShopImages.class.getResource("/image/TradeMenuImages/Send.png").toExternalForm());
        back = new Image(TradeAndShopImages.class.getResource("/image/TradeMenuImages/back.png").toExternalForm());
        amount = new Image(TradeAndShopImages.class.getResource("/image/TradeMenuImages/amount.png").toExternalForm());
        request = new Image(TradeAndShopImages.class.getResource("/image/TradeMenuImages/request.png").toExternalForm());
        donate = new Image(TradeAndShopImages.class.getResource("/image/TradeMenuImages/donate.png").toExternalForm());
        listOfPrevReq = new Image(TradeAndShopImages.class.getResource("/image/TradeMenuImages/ListOfPreviousTrades.png").toExternalForm());
        listOfDonationReq = new Image(TradeAndShopImages.class.getResource("/image/TradeMenuImages/ListOfDonationRequests.png").toExternalForm());
    }

    public Image getSendRequest() {
        return sendRequest;
    }


    public Image getViewPreviousTrades() {
        return viewPreviousTrades;
    }


    public Image getBackground() {
        return background;
    }

    public void setBackground(Image background) {
        this.background = background;
    }

    public Image getSend() {
        return send;
    }


    public Image getBack() {
        return back;
    }

    public void setBack(Image back) {
        this.back = back;
    }



    public Image getRequest() {
        return request;
    }


    public Image getDonate() {
        return donate;
    }


    public Image getListOfPrevReq() {
        return listOfPrevReq;
    }


    public Image getListOfDonationReq() {
        return listOfDonationReq;
    }

}

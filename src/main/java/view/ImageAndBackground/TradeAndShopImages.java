package view.ImageAndBackground;

import javafx.scene.layout.*;
import view.OldView.TradeMenu;
import view.TileManager;
import javafx.scene.image.Image;

import java.awt.*;

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

    public void setSendRequest(Image sendRequest) {
        this.sendRequest = sendRequest;
    }

    public Image getViewPreviousTrades() {
        return viewPreviousTrades;
    }

    public void setViewPreviousTrades(Image viewPreviousTrades) {
        this.viewPreviousTrades = viewPreviousTrades;
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

    public void setSend(Image send) {
        this.send = send;
    }

    public Image getBack() {
        return back;
    }

    public void setBack(Image back) {
        this.back = back;
    }

    public Image getAmount() {
        return amount;
    }

    public void setAmount(Image amount) {
        this.amount = amount;
    }

    public Image getRequest() {
        return request;
    }

    public void setRequest(Image request) {
        this.request = request;
    }

    public Image getDonate() {
        return donate;
    }

    public void setDonate(Image donate) {
        this.donate = donate;
    }

    public Image getListOfPrevReq() {
        return listOfPrevReq;
    }

    public void setListOfPrevReq(Image listOfPrevReq) {
        this.listOfPrevReq = listOfPrevReq;
    }

    public Image getListOfDonationReq() {
        return listOfDonationReq;
    }

    public void setListOfDonationReq(Image listOfDonationReq) {
        this.listOfDonationReq = listOfDonationReq;
    }
}

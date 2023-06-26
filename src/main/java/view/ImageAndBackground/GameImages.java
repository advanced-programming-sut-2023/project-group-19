package view.ImageAndBackground;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import view.TileManager;

public class GameImages {

    public Image next;
    public Image done;
    public Image close;
    public Image shield0;
    public Image shield1;
    public Image shield2;
    public Image shield3;
    public Image search;
    public Image privateChat;
    public Image groupChat;
    public Image globalChat;
    public Image send;
    public Image like;
    public Image dislike;
    public Image heart;
    public Background loginBackground;
    public Background registerBackground;

    public Background profileBackground;
    public Background mainMenuBackground;
    public Background lobbyBackground;



    public void loadImages() {
        loginBackground = new Background(new BackgroundImage(new Image
                (TileManager.class.getResource("/image/MenuImages/loginBackground.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        registerBackground = new Background(new BackgroundImage(new Image
                (TileManager.class.getResource("/image/MenuImages/registerBackground.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        profileBackground = new Background(new BackgroundImage(new Image
                (TileManager.class.getResource("/image/MenuImages/ProfileMenuImage.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        mainMenuBackground = new Background(new BackgroundImage(new Image
                (TileManager.class.getResource("/image/MenuImages/MainMenu.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        lobbyBackground = new Background(new BackgroundImage(new Image
                (TileManager.class.getResource("/image/MenuImages/Lobby.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));

        next = new Image(TileManager.class.getResource("/image/GameMenu/next.png").toExternalForm());
        done = new Image(TileManager.class.getResource("/image/GameMenu/done.png").toExternalForm());
        close = new Image(TileManager.class.getResource("/image/GameMenu/close.png").toExternalForm());
        shield0 = new Image(TileManager.class.getResource("/image/LobbyImages/shield0.png").toExternalForm());
        shield1 = new Image(TileManager.class.getResource("/image/LobbyImages/shield1.png").toExternalForm());
        shield2 = new Image(TileManager.class.getResource("/image/LobbyImages/shield2.png").toExternalForm());
        shield3 = new Image(TileManager.class.getResource("/image/LobbyImages/shield3.png").toExternalForm());
        search = new Image(TileManager.class.getResource("/image/LobbyImages/search.png").toExternalForm());
        privateChat = new Image(TileManager.class.getResource("/image/LobbyImages/privateChat.png").toExternalForm());
        groupChat = new Image(TileManager.class.getResource("/image/LobbyImages/groupChat.png").toExternalForm());
        globalChat = new Image(TileManager.class.getResource("/image/LobbyImages/globalChat.png").toExternalForm());
        send = new Image(TileManager.class.getResource("/image/LobbyImages/send.png").toExternalForm());
        like = new Image(TileManager.class.getResource("/image/LobbyImages/like.png").toExternalForm());
        dislike = new Image(TileManager.class.getResource("/image/LobbyImages/dislike.png").toExternalForm());
        heart = new Image(TileManager.class.getResource("/image/LobbyImages/heart.png").toExternalForm());
    }


    public Image getNext() {
        return next;
    }


    public Image getDone() {
        return done;
    }


    public Background getLoginBackground() {
        return loginBackground;
    }

    public Background getRegisterBackground() {
        return registerBackground;
    }

    public Background getProfileBackground() {
        return profileBackground;
    }

    public Background getMainMenuBackground() {
        return mainMenuBackground;
    }

    public Image getClose() {
        return close;
    }

    public Background getLobbyBackground() {
        return lobbyBackground;
    }

    public Image getShield0() {
        return shield0;
    }

    public Image getShield1() {
        return shield1;
    }

    public Image getShield2() {
        return shield2;
    }

    public Image getShield3() {
        return shield3;
    }

    public Image getSearch() {
        return search;
    }

    public Image getPrivateChat() {
        return privateChat;
    }

    public Image getGroupChat() {
        return groupChat;
    }

    public Image getGlobalChat() {
        return globalChat;
    }

    public Image getSend() {
        return send;
    }

    public Image getLike() {
        return like;
    }

    public Image getDislike() {
        return dislike;
    }

    public Image getHeart() {
        return heart;
    }
}

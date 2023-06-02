package view;

import controller.TradeController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.*;
import view.ImageAndBackground.TradeAndShopImages;
import view.OldView.TradeMenu;

public class ListOfReceivedRequestsMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        TradeAndShopImages tradeAndShopImages =  new TradeAndShopImages();
        tradeAndShopImages.loadImages();
        User newUser = new User("user6", "aa", "ali", "a", "1", "1", 1);
        User newUser1 = new User("user6", "aa", "dorsa", "a", "1", "1", 1);
        Empire Ali = new Empire();
        Empire Dorsa = new Empire();
        Ali.setUser(newUser);
        Dorsa.setUser(newUser1);
        Manage.setCurrentEmpire(Ali);
        Map.CreateMap(100);
        Map.mapSize = 100;
        Manage.getAllEmpires().add(Dorsa);
        Manage.getAllEmpires().add(Ali);

        TradeController tradeController = new TradeController();
        String id = tradeController.idProvider(Manage.getCurrentEmpire(), Manage.getCurrentEmpire().getAllRequests().size() + 1);
        Request request = new Request("Wood 100",100 , 50, "cheese", id,Manage.getCurrentEmpire(),Dorsa,"Wood");
        request.setStatus("Not accepted yet!");
        Manage.getCurrentEmpire().getAllRequests().add(request);
        Dorsa.getAllDonations().add(request);
        String id1 = tradeController.idProvider(Manage.getCurrentEmpire(), Manage.getCurrentEmpire().getAllRequests().size() + 1);
        Request request1 = new Request("Wood 100",100 , 50, "cheese", id1,Manage.getCurrentEmpire(),Dorsa,"Wood");
        request1.setStatus("Not accepted yet!");
        Manage.getCurrentEmpire().getAllRequests().add(request1);
        Dorsa.getAllDonations().add(request1);
        String id2 = tradeController.idProvider(Manage.getCurrentEmpire(), Manage.getCurrentEmpire().getAllRequests().size() + 1);
        Request request2 = new Request("Wood 100",100 , 50, "cheese", id2,Manage.getCurrentEmpire(),Dorsa,"Wood");
        request2.setStatus("Not accepted yet!");
        Manage.getCurrentEmpire().getAllRequests().add(request2);
        Dorsa.getAllDonations().add(request2);
        String id3 = tradeController.idProvider(Manage.getCurrentEmpire(), Manage.getCurrentEmpire().getAllRequests().size() + 1);
        Request request3 = new Request("Wood 100",100 , 50, "cheese", id3,Manage.getCurrentEmpire(),Dorsa,"Wood");
        request3.setStatus("Not accepted yet!");
        Manage.getCurrentEmpire().getAllRequests().add(request3);
        Dorsa.getAllDonations().add(request3);
        String id4 = tradeController.idProvider(Manage.getCurrentEmpire(), Manage.getCurrentEmpire().getAllRequests().size() + 1);
        Request request4 = new Request("Wood 100",100 , 50, "cheese", id4,Manage.getCurrentEmpire(),Dorsa,"Wood");
        request4.setStatus("Not accepted yet!");
        Manage.getCurrentEmpire().getAllRequests().add(request4);
        Dorsa.getAllDonations().add(request4);
        String id5 = tradeController.idProvider(Manage.getCurrentEmpire(), Manage.getCurrentEmpire().getAllRequests().size() + 1);
        Request request5 = new Request("Wood 100",100 , 50, "cheese", id5,Manage.getCurrentEmpire(),Dorsa,"Wood");
        request5.setStatus("Not accepted yet!");
        Manage.getCurrentEmpire().getAllRequests().add(request5);
        Dorsa.getAllDonations().add(request5);
        String id6 = tradeController.idProvider(Manage.getCurrentEmpire(), Manage.getCurrentEmpire().getAllRequests().size() + 1);
        Request request6 = new Request("Wood 100",100 , 50, "cheese", id6,Manage.getCurrentEmpire(),Dorsa,"Wood");
        request6.setStatus("Not accepted yet!");
        Manage.getCurrentEmpire().getAllRequests().add(request6);
        Dorsa.getAllDonations().add(request6);
        String id7 = tradeController.idProvider(Manage.getCurrentEmpire(), Manage.getCurrentEmpire().getAllRequests().size() + 1);
        Request request7 = new Request("Wood 100",100 , 50, "cheese", id7,Manage.getCurrentEmpire(),Dorsa,"Wood");
        request7.setStatus("Not accepted yet!");
        Manage.getCurrentEmpire().getAllRequests().add(request7);
        Dorsa.getAllDonations().add(request7);
        String id8 = tradeController.idProvider(Manage.getCurrentEmpire(), Manage.getCurrentEmpire().getAllRequests().size() + 1);
        Request request8 = new Request("Wood 100",100 , 50, "cheese", id8,Manage.getCurrentEmpire(),Dorsa,"Wood");
        request8.setStatus("Not accepted yet!");
        Manage.getCurrentEmpire().getAllRequests().add(request8);
        Dorsa.getAllDonations().add(request8);
        String id9 = tradeController.idProvider(Manage.getCurrentEmpire(), Manage.getCurrentEmpire().getAllRequests().size() + 1);
        Request request9 = new Request("Wood 100",100 , 50, "cheese", id9,Manage.getCurrentEmpire(),Dorsa,"Wood");
        request9.setStatus("Not accepted yet!");
        Manage.getCurrentEmpire().getAllRequests().add(request9);
        Dorsa.getAllDonations().add(request9);
        String id10 = tradeController.idProvider(Manage.getCurrentEmpire(), Manage.getCurrentEmpire().getAllRequests().size() + 1);
        Request request10 = new Request("Wood 100",100 , 50, "cheese", id10,Manage.getCurrentEmpire(),Dorsa,"Wood");
        request10.setStatus("Not accepted yet!");
        Manage.getCurrentEmpire().getAllRequests().add(request10);
        Dorsa.getAllDonations().add(request10);
        String id11 = tradeController.idProvider(Manage.getCurrentEmpire(), Manage.getCurrentEmpire().getAllRequests().size() + 1);
        Request request11 = new Request("Wood 100",100 , 50, "cheese", id11,Manage.getCurrentEmpire(),Dorsa,"Wood");
        request11.setStatus("Not accepted yet!");
        Manage.getCurrentEmpire().getAllRequests().add(request11);
        Dorsa.getAllDonations().add(request11);
        Manage.setCurrentEmpire(Dorsa);

        Main.stage = stage;
//        Button back = new Button();
//        back.setPrefSize(100,40);
//        back.setLayoutX(20);
//        back.setLayoutY(20);
//        back.setStyle("-fx-background-color: #cba883");
//        back.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
//        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                TradeMenu tradeMenu = new TradeMenu();
//                try {
//                    tradeMenu.start(stage);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
        Button back = new Button();
        back.setPrefSize(100,40);
        back.setLayoutX(20);
        back.setLayoutY(20);
        back.setStyle("-fx-background-color: #cba883");
        back.setFont(Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TradeMenu tradeMenu = new TradeMenu();
                try {
                    tradeMenu.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Group group = tradeController.showDonations();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(group);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        ImageView backGround = new ImageView(tradeAndShopImages.getBackground());
        Pane pane = new Pane();
        pane.getChildren().addAll(backGround,scrollPane,back);
        scrollPane.setLayoutX(580);
        scrollPane.setLayoutY(200);
        scrollPane.setPrefWidth(400);
        scrollPane.setPrefHeight(400);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
    }
}

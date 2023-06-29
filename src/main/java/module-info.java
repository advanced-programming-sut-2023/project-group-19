module com.example.projectgroup19 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;
    requires java.desktop;
//    requires com.google.gson;



    exports view;
    exports model;
    exports controller;
    opens view to javafx.fxml;
    opens model to javafx.fxml;
    opens controller to javafx.fxml;
    exports view.OldView;
    opens view.OldView to javafx.fxml;
    exports controller.method;
    opens controller.method to javafx.fxml;
}
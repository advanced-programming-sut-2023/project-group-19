module com.example.projectgroup19 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
//    requires com.google.gson;
    requires java.desktop;
    requires com.google.gson;
//    requires gson;

    exports view;
    exports model;
    exports controller;
    opens view to javafx.fxml;
    opens model to javafx.fxml;
    opens controller to javafx.fxml;
}
module Main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;

    exports view;
    exports model;
    opens view to javafx.fxml;
    opens model to javafx.fxml;
    exports controller;
    opens controller to javafx.fxml;
}
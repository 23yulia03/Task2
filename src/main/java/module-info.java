module com.example.task2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.task2 to javafx.fxml;
    exports com.example.task2;
    exports model;
    opens model to javafx.fxml;
    exports factory;
    opens factory to javafx.fxml;
    exports memento;
    opens memento to javafx.fxml;
}
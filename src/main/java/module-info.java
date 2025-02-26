module com.example.task2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.task2 to javafx.fxml;
    exports com.example.task2;
//    exports model;
//    opens model to javafx.fxml;
    exports model.fabrica;
    opens model.fabrica to javafx.fxml;
    exports model.memento;
    opens model.memento to javafx.fxml;
    exports model.shapes;
    opens model.shapes to javafx.fxml;
}
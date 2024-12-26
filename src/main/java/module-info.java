module com.example.task2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.task2 to javafx.fxml;  // Разрешить доступ к пакету для FXML

    exports com.example.task2;
}

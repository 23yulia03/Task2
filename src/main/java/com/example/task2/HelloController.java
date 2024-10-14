package com.example.task2;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private Canvas canvas;

    @FXML
    private TextField value1;

    private ShapeFactory shapeFactory = new ShapeFactory();

    @FXML
    public void addPicture() {
        GraphicsContext gr = canvas.getGraphicsContext2D();

        // Проверка на корректность ввода
        if (!checkWithRegExp(value1.getText())) {
            showAlert("Ошибка ввода!", "Введено не число или число не из диапазона от 0 до 5!");
            return;
        }

        int numberOfSides = Integer.parseInt(value1.getText());
        HelloApplication.Shape shape1 = shapeFactory.createShape(numberOfSides);

        if (shape1 != null) {
            gr.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Очистка холста
            shape1.draw(gr); // Рисуем фигуру
        } else {
            showAlert("Ошибка", "Фигура с таким количеством сторон не существует!");
        }
    }

    private boolean checkWithRegExp(String text) {
        try {
            int number = Integer.parseInt(text);
            return number >= 0 && number <= 5;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

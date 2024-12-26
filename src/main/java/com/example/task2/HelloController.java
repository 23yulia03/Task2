package com.example.task2;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Label lastDrawnLabel;

    private String lastDrawnShape;

    @FXML
    public void onDrawRectangle() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Rectangle rectangle = new Rectangle(colorPicker.getValue(), 100, 50);
        rectangle.setPosition(50, 50);
        rectangle.draw(gc);

        lastDrawnShape = "Прямоугольник";
        lastDrawnLabel.setText("Последняя фигура: " + lastDrawnShape);
    }

    @FXML
    public void onDrawSquare() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Square square = new Square(colorPicker.getValue(), 100);
        square.setPosition(50, 50);
        square.draw(gc);

        lastDrawnShape = "Квадрат";
        lastDrawnLabel.setText("Последняя фигура: " + lastDrawnShape);
    }

    @FXML
    public void onDrawCircle() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Circle circle = new Circle(colorPicker.getValue(), 50);
        circle.setPosition(100, 100);
        circle.draw(gc);

        lastDrawnShape = "Круг";
        lastDrawnLabel.setText("Последняя фигура: " + lastDrawnShape);
    }

    @FXML
    public void onDrawEllipse() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Ellipse ellipse = new Ellipse(colorPicker.getValue(), 80, 40);
        ellipse.setPosition(60, 80);
        ellipse.draw(gc);

        lastDrawnShape = "Эллипс";
        lastDrawnLabel.setText("Последняя фигура: " + lastDrawnShape);
    }

    @FXML
    public void onDrawRoundedRectangle() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        RoundedRectangle roundedRectangle = new RoundedRectangle(colorPicker.getValue(), 120, 60, 20, 20);
        roundedRectangle.setPosition(30, 30);
        roundedRectangle.draw(gc);

        lastDrawnShape = "Скруглённый прямоугольник";
        lastDrawnLabel.setText("Последняя фигура: " + lastDrawnShape);
    }
}

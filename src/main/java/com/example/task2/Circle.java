package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle implements HelloApplication.Shape {

    @Override
    public void draw(GraphicsContext gr) {
        gr.setFill(Color.GREEN);
        gr.fillOval(100, 50, 200, 200); // Рисуем круг
    }

    @Override
    public String descriptor() {
        return "Circle";
    }
}

package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line implements HelloApplication.Shape {

    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(Color.BLACK);
        gr.setLineWidth(5);
        gr.strokeLine(50, 150, 300, 150); // Рисуем отрезок
    }

    @Override
    public String descriptor() {
        return "Line";
    }
}

package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Angle extends Shape {

    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(Color.GRAY);
        gr.setLineWidth(10);
        gr.strokeLine(50, 150, 300, 150); // Горизонтальная линия
        gr.strokeLine(50, 150, 50, 300); // Вертикальная линия
    }

    @Override
    public String descriptor() {
        return "Angle";
    }
}

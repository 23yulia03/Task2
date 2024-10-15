package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square implements Shape {

    @Override
    public void draw(GraphicsContext gr) {
        gr.setFill(Color.BLUE);
        gr.fillRect(100, 100, 150, 150); // Рисуем квадрат
    }

    @Override
    public String descriptor() {
        return "Square";
    }
}

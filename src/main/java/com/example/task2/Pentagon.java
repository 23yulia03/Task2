package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pentagon extends Shape {

    @Override
    public void draw(GraphicsContext gr) {
        double[] xPoints = {150, 250, 200, 100, 50};
        double[] yPoints = {50, 100, 200, 200, 100};

        gr.setFill(Color.ORANGE);
        gr.fillPolygon(xPoints, yPoints, 5); // Рисуем пятиугольник
    }

    @Override
    public String descriptor() {
        return "Pentagon";
    }
}

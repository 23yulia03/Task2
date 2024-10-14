package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle implements HelloApplication.Shape {

    @Override
    public void draw(GraphicsContext gr) {
        double[] xPoints = {150, 250, 50};
        double[] yPoints = {50, 200, 200};

        gr.setFill(Color.RED);
        gr.fillPolygon(xPoints, yPoints, 3); // Рисуем треугольник
    }

    @Override
    public String descriptor() {
        return "Triangle";
    }
}

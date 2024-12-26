package com.example.task2;

import javafx.scene.canvas.GraphicsContext;

public class Triangle extends Shape {
    private static final double SIDE = 60;

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        gc.setFill(color);
        double[] xPoints = new double[]{
                x,
                x - SIDE / 2,
                x + SIDE / 2
        };
        double[] yPoints = new double[]{
                y - SIDE * Math.sqrt(3) / 2,
                y + SIDE * Math.sqrt(3) / 6,
                y + SIDE * Math.sqrt(3) / 6
        };
        gc.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public String toString() {
        return "Треугольник";
    }
}
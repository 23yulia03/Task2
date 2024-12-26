package com.example.task2;

import javafx.scene.canvas.GraphicsContext;

public class Circle extends Shape {
    private static final double RADIUS = 30;

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        gc.setFill(color);
        gc.fillOval(x - RADIUS, y - RADIUS, 2 * RADIUS, 2 * RADIUS);
    }

    @Override
    public String toString() {
        return "Круг";
    }
}
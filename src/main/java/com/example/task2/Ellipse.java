package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ellipse extends Shape {
    private double radiusX, radiusY;

    public Ellipse(Color color, double radiusX, double radiusY) {
        super(color);
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    @Override
    double area() {
        return Math.PI * radiusX * radiusY;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(x, y, radiusX * 2, radiusY * 2);
        System.out.println("Ellipse color is " + color + " and area is: " + area());
    }
}

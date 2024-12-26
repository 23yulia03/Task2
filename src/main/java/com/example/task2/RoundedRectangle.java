package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RoundedRectangle extends Shape {
    private double length, width, arcWidth, arcHeight;

    public RoundedRectangle(Color color, double length, double width, double arcWidth, double arcHeight) {
        super(color);
        this.length = length;
        this.width = width;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }

    @Override
    double area() {
        return length * width;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRoundRect(x, y, length, width, arcWidth, arcHeight);
        System.out.println("Rounded Rectangle color is " + color + " and area is: " + area());
    }
}

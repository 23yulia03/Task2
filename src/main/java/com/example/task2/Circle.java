package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {
    private double radius;
    private ShapeAnimation animation;

    public Circle(Color fillColor, Color strokeColor, double strokeWidth, String fillStyle, String animationType, double radius) {
        super(fillColor, strokeColor, strokeWidth, fillStyle, animationType);
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setFill(fillColor);
        gr.setStroke(strokeColor);
        gr.setLineWidth(strokeWidth);

        if ("Пунктир".equals(fillStyle)) {
            gr.setLineDashes(5);
        } else {
            gr.setLineDashes(0);
        }

        if (animation == null && !"Нет".equals(animationType)) {
            animation = new ShapeAnimation(gr, this, animationType);
        }

        gr.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        gr.strokeOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    @Override
    public Shape clone() {
        return new Circle(fillColor, strokeColor, strokeWidth, fillStyle, animationType, radius);
    }

    public void stopAnimation() {
        if (animation != null) {
            animation.stop();
        }
    }

    @Override
    public void setFillColor(Color color) {

    }

    @Override
    public void setStrokeColor(Color color) {
        this.strokeColor = color;
    }
}
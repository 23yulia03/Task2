package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Shape {
    private double length;
    private ShapeAnimation animation;

    public Line(Color fillColor, Color strokeColor, double strokeWidth, String fillStyle, String animationType, double length) {
        super(fillColor, strokeColor, strokeWidth, fillStyle, animationType);
        this.length = length;
    }

    @Override
    public double area() {
        return 0; // Линия не имеет площади
    }

    @Override
    public void draw(GraphicsContext gr) {
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

        gr.strokeLine(x, y, x + length, y);
    }

    @Override
    public Shape clone() {
        return new Line(fillColor, strokeColor, strokeWidth, fillStyle, animationType, length);
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
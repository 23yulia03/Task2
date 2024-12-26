package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape {
    private double side;
    private ShapeAnimation animation;

    public Square(Color fillColor, Color strokeColor, double strokeWidth, String fillStyle, String animationType, double side) {
        super(fillColor, strokeColor, strokeWidth, fillStyle, animationType);
        this.side = side;
    }

    @Override
    public double area() {
        return side * side;
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

        gr.fillRect(x, y, side, side);
        gr.strokeRect(x, y, side, side);
    }

    @Override
    public Shape clone() {
        return new Square(fillColor, strokeColor, strokeWidth, fillStyle, animationType, side);
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
package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pentagon extends Shape {
    private double side;
    private ShapeAnimation animation;

    public Pentagon(Color fillColor, Color strokeColor, double strokeWidth, String fillStyle, String animationType, double side) {
        super(fillColor, strokeColor, strokeWidth, fillStyle, animationType);
        this.side = side;
    }

    @Override
    public double area() {
        double apothem = side / (2 * Math.tan(Math.PI / 5));
        return 5 * side * apothem / 2;
    }

    @Override
    public void draw(GraphicsContext gr) {
        double angle = Math.toRadians(72);
        double[] xPoints = new double[5];
        double[] yPoints = new double[5];
        for (int i = 0; i < 5; i++) {
            xPoints[i] = x + side * Math.cos(angle * i);
            yPoints[i] = y + side * Math.sin(angle * i);
        }
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

        gr.fillPolygon(xPoints, yPoints, 5);
        gr.strokePolygon(xPoints, yPoints, 5);
    }

    @Override
    public Shape clone() {
        return new Pentagon(fillColor, strokeColor, strokeWidth, fillStyle, animationType, side);
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
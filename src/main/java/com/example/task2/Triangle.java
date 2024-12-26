package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape {
    private double base, height;
    private ShapeAnimation animation;

    public Triangle(Color fillColor, Color strokeColor, double strokeWidth, String fillStyle, String animationType, double base, double height) {
        super(fillColor, strokeColor, strokeWidth, fillStyle, animationType);
        this.base = base;
        this.height = height;
    }

    @Override
    public double area() {
        return 0.5 * base * height;
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

        // Рисуем треугольник с вершиной вверх
        gr.fillPolygon(
                new double[]{x - base / 2, x, x + base / 2},  // X координаты вершин
                new double[]{y + height, y, y + height},      // Y координаты вершин
                3
        );
        gr.strokePolygon(
                new double[]{x - base / 2, x, x + base / 2},  // X координаты вершин
                new double[]{y + height, y, y + height},      // Y координаты вершин
                3
        );
    }

    @Override
    public Shape clone() {
        return new Triangle(fillColor, strokeColor, strokeWidth, fillStyle, animationType, base, height);
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
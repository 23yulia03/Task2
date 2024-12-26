package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Angle extends Shape {
    private double side;
    private ShapeAnimation animation;

    public Angle(Color fillColor, Color strokeColor, double strokeWidth, String fillStyle, String animationType, double side) {
        super(fillColor, strokeColor, strokeWidth, fillStyle, animationType);
        this.side = side;
    }

    @Override
    public double area() {
        return 0.5 * side * side; // Площадь прямого угла как половина квадрата
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(strokeColor);  // Используем stroke, а не fill, чтобы не закрашивать угол
        gr.setLineWidth(strokeWidth);    // Устанавливаем толщину линии

        if ("Пунктир".equals(fillStyle)) {
            gr.setLineDashes(5);
        } else {
            gr.setLineDashes(0);
        }

        if (animation == null && !"Нет".equals(animationType)) {
            animation = new ShapeAnimation(gr, this, animationType);
        }

        gr.strokeLine(x, y, x + side, y);           // Линия от точки (x, y) вправо
        gr.strokeLine(x, y, x, y - side);           // Линия вверх от точки (x, y)
    }

    @Override
    public Shape clone() {
        return new Angle(fillColor, strokeColor, strokeWidth, fillStyle, animationType, side);
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
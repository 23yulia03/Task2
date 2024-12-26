package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
    protected Color fillColor;
    protected Color strokeColor;
    protected double strokeWidth;
    protected String fillStyle;
    protected String animationType;
    protected double x, y;
    protected double width, height;

    // Конструктор
    public Shape(Color fillColor, Color strokeColor, double strokeWidth, String fillStyle, String animationType) {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
        this.fillStyle = fillStyle;
        this.animationType = animationType;
    }

    public abstract double area();

    // Абстрактный метод для рисования
    abstract void draw(GraphicsContext gr);

    // Метод для рисования в заданных координатах
    public void draw(GraphicsContext gr, double x, double y) {
        this.x = x;
        this.y = y;
        draw(gr);
    }

    // Метод для задания позиции
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Метод для получения ширины (если нужно для правильного размещения фигур в ряд)
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    // Метод для получения цвета
    public Color getFillColor() {
        return fillColor;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    // Метод для клонирования фигуры
    public abstract Shape clone();

    public void stopAnimation() {
    }

    public abstract void setFillColor(Color color);

    public abstract void setStrokeColor(Color color);
}
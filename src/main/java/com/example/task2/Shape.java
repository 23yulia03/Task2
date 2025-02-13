package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
    protected Color color;
    protected double x, y;
    protected double width, height;

    public Shape(Color color) {
        this.color = color;
    }

    public abstract double area();
    public abstract void draw(GraphicsContext gr);

    // Установка позиции фигуры
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Получение ширины фигуры (например, для линий)
    public double getWidth() {
        return width;
    }

    // Установка ширины фигуры
    public void setWidth(double width) {
        this.width = width;
    }

    // Получение цвета фигуры
    public Color getColor() {
        return color;
    }

    // Установка цвета фигуры
    public void setColor(Color color) {
        this.color = color;
    }

    // Абстрактный метод для клонирования фигуры
    public abstract Shape clone();
}

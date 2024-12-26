package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
    protected Color color;
    protected double x, y;

    // Конструктор
    public Shape(Color color) {
        this.color = color;
    }

    // Абстрактные методы
    abstract double area();

    abstract void draw(GraphicsContext gc); //рисование фигур

    // Установка координат
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

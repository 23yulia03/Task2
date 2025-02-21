/**
 * Абстрактный класс, представляющий базовый шаблон для различных фигур.
 * Класс предоставляет общие методы для работы с цветом, позицией и размерами фигур,
 * а также абстрактные методы для вычисления площади и отрисовки фигуры.
 */
package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
    protected Color color; // Цвет фигуры
    protected double x, y; // Координаты позиции фигуры
    protected double width; // Ширина фигуры

    /**
     * Конструктор для создания фигуры с заданным цветом.
     *
     * @param color Цвет фигуры.
     */
    public Shape(Color color) {
        this.color = color;
    }

    /**
     * Абстрактный метод для вычисления площади фигуры.
     * Каждый конкретный класс фигуры должен реализовать этот метод.
     *
     * @return Площадь фигуры.
     * TODO: Реализовать поддержку вычисления площади для более сложных фигур.
     */
    public abstract double area();

    /**
     * Абстрактный метод для отрисовки фигуры на холсте.
     * Каждый конкретный класс фигуры должен реализовать этот метод.
     *
     * @param gr Контекст графики для рисования фигуры.
     */
    public abstract void draw(GraphicsContext gr);

    /**
     * Устанавливает позицию фигуры.
     *
     * @param x Координата X позиции.
     * @param y Координата Y позиции.
     */
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Получает координату X позиции фигуры.
     *
     * @return Координата X.
     */
    public double getX() {
        return x;
    }

    /**
     * Получает координату Y позиции фигуры.
     *
     * @return Координата Y.
     */
    public double getY() {
        return y;
    }

    /**
     * Получает цвет фигуры.
     *
     * @return Цвет фигуры.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Устанавливает цвет фигуры.
     *
     * @param color Новый цвет фигуры.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Получает ширину фигуры.
     *
     * @return Ширина фигуры.
     */
    public double getWidth() {
        return width; // Возвращаем ширину
    }

    /**
     * Устанавливает ширину фигуры.
     *
     * @param width Новая ширина фигуры.
     */
    public void setWidth(double width) {
        this.width = width; // Устанавливаем ширину
    }

    /**
     * Создает копию текущей фигуры.
     *
     * @return Клонированная фигура.
     */
    public abstract Shape clone();

    /**
     * Проверяет, содержится ли точка с координатами (x, y) внутри фигуры.
     * Каждый конкретный класс фигуры должен реализовать этот метод.
     *
     * @param x Координата X точки.
     * @param y Координата Y точки.
     * @return true, если точка содержится в фигуре, иначе false.
     * TODO: Оптимизировать этот метод для фигур со сложными контурами.
     */
    public abstract boolean contains(double x, double y);
}

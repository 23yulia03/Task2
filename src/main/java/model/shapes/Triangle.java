/**
 * Класс для создания и рисования треугольника
 */
package model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape {
    private double base, height;

    public Triangle(Color color, double base, double height) {
        super(color);
        this.base = base;
        this.height = height;
    }

    @Override
    public double area() {
        return 0.5 * base * height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        // Рисуем треугольник с вершиной вверх
        gc.fillPolygon(
                new double[]{x - base / 2, x, x + base / 2},  // X координаты вершин
                new double[]{y + height, y, y + height},      // Y координаты вершин
                3
        );
    }

    @Override
    public Shape clone() {
        return new Triangle(color, base, height);
    }

    // Добавляем метод getBase()
    public double getBase() {
        return base;
    }

    @Override
    public boolean contains(double x, double y) {
        double x1 = this.x - base / 2;
        double y1 = this.y + height;
        double x2 = this.x;
        double y2 = this.y;
        double x3 = this.x + base / 2;
        double y3 = this.y + height;

        double a = (x1 - x) * (y2 - y1) - (x2 - x1) * (y1 - y);
        double b = (x2 - x) * (y3 - y2) - (x3 - x2) * (y2 - y);
        double c = (x3 - x) * (y1 - y3) - (x1 - x3) * (y3 - y);

        return (a >= 0 && b >= 0 && c >= 0) || (a <= 0 && b <= 0 && c <= 0);
    }

    // Добавляем метод getHeight()
    public double getHeight() {
        return height;
    }
}
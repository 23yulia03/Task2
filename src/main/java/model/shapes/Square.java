/**
 * Класс для создания и рисования квадрата
 */
package model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape {
    private double size;

    public Square(Color color, double size) {
        super(color);
        this.size = size;
    }

    @Override
    public double area() {
        return size * size;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x, y, size, size); // Исправлено: gr -> gc
    }

    @Override
    public Shape clone() {
        return new Square(color, size);
    }

    @Override
    public boolean contains(double x, double y) {
        return x >= this.x && x <= this.x + size && y >= this.y && y <= this.y + size;
    }

    // Добавляем метод getSize()
    public double getSize() {
        return size;
    }
}
/**
 * Класс для управления группами фигур, включая добавление, удаление, проверку содержимого,
 * отрисовку, клонирование и вычисление площади всех фигур в группе.
 */
package model.shapes;

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.List;

public class ShapeGroup extends Shape {
    private final List<Shape> shapes = new ArrayList<>();

    public ShapeGroup() {
        super(null); // Группе не нужен цвет
    }

    // Добавление фигуры в группу
    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    // Удаление фигуры из группы
    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    // Проверка, пуста ли группа
    public boolean isEmpty() {
        return shapes.isEmpty();
    }

    // Получение списка фигур в группе
    public List<Shape> getShapes() {
        return shapes;
    }

    // Очистка группы
    public void clear() {
        shapes.clear();
    }

    // Отрисовка всех фигур в группе
    @Override
    public void draw(GraphicsContext gr) {
        for (Shape shape : shapes) {
            shape.draw(gr);
        }
    }

    // Описание группы
    @Override
    public String descriptor() {
        return "Группа фигур";
    }

    // Клонирование группы (глубокая копия)
    @Override
    public Shape clone() {
        ShapeGroup clonedGroup = new ShapeGroup();
        for (Shape shape : shapes) {
            clonedGroup.addShape(shape.clone());
        }
        return clonedGroup;
    }

    // Проверка, содержится ли точка внутри группы
    @Override
    public boolean contains(double x, double y) {
        for (Shape shape : shapes) {
            if (shape.contains(x, y)) {
                return true;
            }
        }
        return false;
    }

    // Площадь группы - сумма площадей фигур
    @Override
    public double area() {
        double totalArea = 0;
        for (Shape shape : shapes) {
            totalArea += shape.area();
        }
        return totalArea;
    }

    public void add(Shape currentShape) {
    }
}

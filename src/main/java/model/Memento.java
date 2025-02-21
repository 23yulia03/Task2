/**
 * Класс Memento, который хранит состояние фигуры (например, ширину и цвет) для восстановления.
 */
package model;

import javafx.scene.paint.Color;

public class Memento {

    private Shape snapShot;
    private double width;  // Ширина
    private Color color;   // Цвет

    // Конструктор, сохраняющий состояние фигуры
    public Memento(Shape state) {
        this.snapShot = state.clone(); // Клонируем фигуру для сохранения состояния
        this.width = state.getWidth();  // Сохраняем ширину
        this.color = state.getColor();  // Сохраняем цвет
    }

    // Возвращает восстановленное состояние
    public Shape getState() {
        snapShot.setWidth(width);  // Восстанавливаем ширину
        snapShot.setColor(color);  // Восстанавливаем цвет
        return snapShot;
    }
}
package com.example.task2;

public class ShapeFactory {

    public Shape createShape(int numberOfSides) {
        if (numberOfSides == 5) {
            return new Pentagon();
        } else if (numberOfSides == 4) {
            return new Square();
        } else if (numberOfSides == 3) {
            return new Triangle();
        } else if (numberOfSides == 2) {
            return new Angle();
        } else if (numberOfSides == 1) {
            return new Line();
        } else if (numberOfSides == 0) {
            return new Circle();
        } else {
            return null; // В случае некорректного числа сторон
        }
    }
}

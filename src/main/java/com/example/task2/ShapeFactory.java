package com.example.task2;

import javafx.scene.paint.Color;

public class ShapeFactory {
    public Shape createShape(String shapeType, Color fillColor, Color strokeColor, double strokeWidth, String fillStyle, String animationType, double... params) {
        switch (shapeType.toLowerCase()) {
            case "line":
                return new Line(fillColor, strokeColor, strokeWidth, fillStyle, animationType, params[0]);
            case "square":
                return new Square(fillColor, strokeColor, strokeWidth, fillStyle, animationType, params[0]);
            case "triangle":
                return new Triangle(fillColor, strokeColor, strokeWidth, fillStyle, animationType, params[0], params[1]);
            case "circle":
                return new Circle(fillColor, strokeColor, strokeWidth, fillStyle, animationType, params[0]);
            case "angle":
                return new Angle(fillColor, strokeColor, strokeWidth, fillStyle, animationType, params[0]);
            case "pentagon":
                return new Pentagon(fillColor, strokeColor, strokeWidth, fillStyle, animationType, params[0]);
            default:
                throw new IllegalArgumentException("Неверный тип фигуры: " + shapeType);
        }
    }
}
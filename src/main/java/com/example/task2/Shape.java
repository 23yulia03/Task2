package com.example.task2;

import javafx.scene.canvas.GraphicsContext;

public abstract class Shape {
    public abstract void draw(GraphicsContext gr);

    public String descriptor() {
        return this.getClass().getSimpleName();
    }
}
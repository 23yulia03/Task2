package com.example.task2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ShapeAnimation {
    private Timeline timeline;
    private GraphicsContext gc;
    private Shape shape;
    private String animationType;

    public ShapeAnimation(GraphicsContext gc, Shape shape, String animationType) {
        this.gc = gc;
        this.shape = shape;
        this.animationType = animationType;
        createAnimation();
    }

    private void createAnimation() {
        if ("Мерцание".equals(animationType)) {
            timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, e -> drawShape(shape.getStrokeColor())),
                    new KeyFrame(Duration.seconds(0.2), e -> drawShape(Color.rgb(255, 0, 0))), // Красный цвет
                    new KeyFrame(Duration.seconds(0.4), e -> drawShape(Color.rgb(0, 255, 0))), // Зеленый цвет
                    new KeyFrame(Duration.seconds(0.6), e -> drawShape(Color.rgb(0, 0, 255))), // Синий цвет
                    new KeyFrame(Duration.seconds(0.8), e -> drawShape(shape.getStrokeColor()))
            );
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        } else if ("Постепенное появление".equals(animationType)) {
            timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, e -> drawShape(0.0)),
                    new KeyFrame(Duration.seconds(1), e -> drawShape(1.0))
            );
            timeline.setCycleCount(1);
            timeline.play();
        }
    }

    private void drawShape(Color color) {
        gc.save();
        shape.setStrokeColor(color);
        shape.draw(gc);
        gc.restore();
    }

    private void drawShape(double opacity) {
        gc.save();
        gc.setGlobalAlpha(opacity);
        shape.draw(gc);
        gc.restore();
    }

    public void stop() {
        if (timeline != null) {
            timeline.stop();
        }
    }
}
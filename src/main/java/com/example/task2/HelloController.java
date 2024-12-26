package com.example.task2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class HelloController {

    @FXML
    private Pane indicatorPane;
    @FXML
    private Label startField, stopField, measureField;
    @FXML
    private Button startButton, stopButton, pauseButton, applyDelayButton;
    @FXML
    private TextField delayField;

    private boolean isPlaying = false;
    private boolean isPaused = false;
    private Director director = new Director();
    private Timeline timeline;
    private double currentPosition = 0;
    private Circle movingIndicator;  // Используем Circle для индикатора

    public void initialize() {
        startField.setText("1");
        updateStopField();
        updateMeasureField();
        pauseButton.setDisable(true);  // Пауза выключена, пока анимация не начнется
        stopButton.setDisable(true);   // Стоп выключен, пока анимация не начнется
    }

    // Метод обновления временной шкалы с новой задержкой
    @FXML
    public void applyDelay() {
        try {
            int delay = Integer.parseInt(delayField.getText());
            System.out.println("Задержка установлена: " + delay + " мс");
        } catch (NumberFormatException e) {
            System.out.println("Некорректное значение задержки");
        }
    }

    // Метод запуска анимации
    @FXML
    public void startAnimation() {
        if (!isPlaying) {
            // Инициализация анимации
            float start = Float.parseFloat(startField.getText());
            float stop = Float.parseFloat(stopField.getText());
            float measure = Float.parseFloat(measureField.getText());

            indicatorPane.getChildren().clear(); // Очистить текущий индикатор

            Builder builder = new ConcreteBuilder();
            director.constructIndicator(builder, start, stop, measure); // Создать новый индикатор

            Indicator indicator = builder.build();
            indicator.show(indicatorPane); // Отобразить индикатор на панели

            // Создание анимации для перемещения индикатора
            movingIndicator = new Circle(10, Color.RED);  // Индикатор - круг
            movingIndicator.setTranslateX(5);
            movingIndicator.setTranslateY(50);  // Начальная позиция (по Y)
            indicatorPane.getChildren().add(movingIndicator);  // Добавляем в панель

            timeline = createAnimation(start, stop);
            timeline.setCycleCount(Timeline.INDEFINITE); // Бесконечный цикл

            // Запуск анимации
            timeline.play();
            isPlaying = true;
            isPaused = false;
            startButton.setDisable(true);  // Отключаем кнопку Старт
            pauseButton.setDisable(false); // Включаем кнопку Пауза
            stopButton.setDisable(false);  // Включаем кнопку Стоп
        }
    }

    // Метод приостановки анимации
    @FXML
    public void pauseAnimation() {
        if (isPlaying && !isPaused) {
            timeline.pause();
            pauseButton.setText("Продолжить");
            isPaused = true;
        }
    }

    // Метод остановки анимации
    @FXML
    public void stopAnimation() {
        if (isPlaying) {
            timeline.stop();
            movingIndicator.setTranslateX(0);  // Сбросить позицию индикатора на начальную
            isPlaying = false;
            isPaused = false;
            startButton.setDisable(false); // Включаем кнопку Старт
            pauseButton.setDisable(true);  // Отключаем кнопку Пауза
            stopButton.setDisable(true);   // Отключаем кнопку Стоп
            pauseButton.setText("Пауза");
        }
    }

    // Метод создания анимации
    private Timeline createAnimation(float start, float stop) {
        double range = stop - start; // Диапазон движения индикатора
        double animationRange = 200; // Примерная длина движения

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.1), e -> {
            currentPosition += 2;  // Увеличиваем скорость движения
            if (currentPosition > animationRange) {
                currentPosition = 0;  // Сбросить позицию индикатора
            }
            movingIndicator.setTranslateX(currentPosition);  // Обновляем позицию индикатора
        });

        return new Timeline(keyFrame);
    }

    // Метод обновления поля "стоп" с указанием общего количества изображений
    private void updateStopField() {
        stopField.setText("10"); // Например, жестко задаем количество индикаторов
    }

    // Метод обновления поля измерения и положения индикатора
    private void updateMeasureField() {
        measureField.setText("1"); // Например, задаем текущую позицию
    }
}
